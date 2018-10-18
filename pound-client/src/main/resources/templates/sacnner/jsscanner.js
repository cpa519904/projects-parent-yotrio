
function JSScanner(callback) {
	this.upper = false; //开启大写字母标记
	this.barcode = '';
	this.delaytimes = 50; //keydown和keyup之间的延迟毫秒
	this.downkeys = [];
	this.upkeys = [];
	this.scanning = true; // 是否扫描模式
	this.callback = callback;
	this.dictionary = {
		16: 'Shift', 101: '-', 189: '-',
		48: '0', 49: '1', 50: '2', 51: '3', 52: '4', 53: '5', 54: '6', 55: '7', 56: '8', 57: '9',
		65: 'A', 66: 'B', 67: 'C', 68: 'D', 69: 'E', 70: 'F', 71: 'G', 72: 'H', 73: 'I', 74: 'J',
		75: 'K', 76: 'L', 77: 'M', 78: 'N', 79: 'O', 80: 'P', 81: 'Q', 82: 'R', 83: 'S', 84: 'T',
		85: 'U', 86: 'V', 87: 'W', 88: 'X', 89: 'Y', 90: 'Z'
	};
	var self = this;
	
	document.body.addEventListener('keydown', function(event){
		if(!self.scanning){
			return ;
		}
		var keycode = event.which;
		if(keycode in self.dictionary){
			var temp_time = new Date().getTime();
			//console.log(keycode+' : '+self.dictionary[keycode]+'; '+temp_time);
			self.downkeys.push({'key': self.dictionary[keycode], 'time': temp_time});
		}
	});
	
	document.body.addEventListener('keyup', function(event){
		if(!self.scanning){
			return ;
		}
		var keycode = event.which;
		if (keycode==13 || keycode==108){
			self.buildbarcode();
			//console.log('barcode: '+self.barcode);
			self.actionscan(self.barcode);
		}else if(keycode in self.dictionary){
			var temp_time = new Date().getTime();
			//console.log(keycode+' : '+self.dictionary[keycode]+'; '+temp_time);
			self.upkeys.push({'key': self.dictionary[keycode], 'time': temp_time});
		}else{
			console.log('无效输入，keyCode:'+keycode+'; 符号：'+String.fromCharCode(keycode));
		}
	});
}

JSScanner.prototype.actionscan = function(barcode){
	this.callback(barcode);
};

JSScanner.prototype.buildbarcode = function(){
	var self = this;
	self.barcode = '';
	var temptime = new Date().getTime(); 
	while(self.downkeys.length > 0){
		var downkeyobj = self.downkeys.shift();
		var downkey = downkeyobj.key;
		if(downkey=='Shift'){
			if(self.downkeys.length<=0){
				break;
			}
			self.upper = true;
			downkeyobj = self.downkeys.shift();
			downkey = downkeyobj.key;
			while(downkey=='Shift'){
				downkeyobj = self.downkeys.shift();
				downkey = downkeyobj.key;
			}
		}
		if(self.upkeys.length <= 0){
			break;
		}
		var downtemp = downkeyobj.time;
		var upkeyobj = self.upkeys.shift();
		var upkey = upkeyobj.key;
		if(upkey=='Shift'){
			upkeyobj = self.upkeys.shift();
			upkey = upkeyobj.key;
			self.upper = false;
		}else if(!self.upper){
			upkey = upkeyobj.key.toLowerCase();
		}
		if(upkeyobj.time - downkeyobj.time <= self.delaytimes){
			self.barcode += upkey;
		}
	}
	self.downkeys = [];
	self.upkeys = [];
};
