
layui.define(['table', 'form'], function (exports) {

    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //websocket实现
    var websocket;
    if ('WebSocket' in window) {
        console.log("此浏览器支持websocket");
        websocket = new WebSocket("ws://127.0.0.1:8000/websocket");
    } else if ('MozWebSocket' in window) {
        alert("此浏览器只支持MozWebSocket");
    } else {
        alert("此浏览器只支持SockJS");
    }
    websocket.onopen = function (evnt) {
        $("#tou").html("链接服务器成功!")
        console.log("链接服务器成功");
    };
    websocket.onmessage = function (evnt) {
        console.log("event", evnt);
        $("#currentWeight").text(evnt.data);
    };
    websocket.onerror = function (evnt) {

    };
    websocket.onclose = function (evnt) {
        $("#tou").html("与服务器断开了链接!");
        console.log("与服务器断开了链接");
    }
    $('#send').bind('click', function () {
        send();
    });

    function send() {
        if (websocket != null) {
            var message = document.getElementById('message').value;
            websocket.send(message);
        } else {
            alert('未与服务器链接.');
        }
    }
  
  exports('console', {})
});