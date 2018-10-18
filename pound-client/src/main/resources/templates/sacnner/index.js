

	window.onload = function(){
		new JSScanner(function(barcode){
			document.getElementById('barcode').innerHTML = '条码：'+ barcode;
		});
	};
	