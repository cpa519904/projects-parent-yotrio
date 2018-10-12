
layui.define(['table', 'form'], function (exports) {

    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //过磅记录管理
    table.render({
        elem: '#LAY-inspection-manage'
        , url: 'http://localhost:8008/api/poundLog/list'
        // , toolbar: '#table-pound-log-toolbar'
        , totalRow: true

        , cols: [[
            {type: 'checkbox', fixed: 'left',totalRowText: '合计'}
            , {field: 'id', title: '报检单单号', sort: true}
            , {field: 'compName', title: '车牌号', minWidth: 80}
            , {field: 'unitName', title: '货品类型', minWidth: 100}
            , {field: 'grossWeight', title: '报价单重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'netWeight', title: '随车退货重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'plateNumb', title: '供应商名称', minWidth: 80}
            , {field: 'goodsName', title: '是否样品', minWidth: 80}
            , {title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-inspection'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
            // , {title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-pound-log'}
        ]]
        , limit: 2
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-inspection-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                //验证口令是否正确
                layer.close(index);
                layer.confirm('确定删除此地磅？', function (index) {
                    // console.log(obj);
                    //提交 Ajax 成功后，静态更新表格中的数据
                    $.ajax({
                        type: 'get',
                        url: '/inspection/delete',
                        data: {
                            id: data.id
                        },
                        cache: false,
                        dataType: 'json',
                        success: function (result) {
                            if (result.code == 0) {
                                obj.del();
                                layer.close(index);
                                layer.msg('已删除');
                            } else {
                                layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                            }
                        },
                        error: function (error) {
                            layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                        }
                    });

                });
            });
        } else if (obj.event === 'edit') {
            layer.open({
                type: 2
                , title: '扫码获取或者编辑报检单信息'
                , content: '/inspection/form.htm'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-pound-info-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        console.log("fieldL", field);
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            type: 'post',
                            url: '/poundInfo/update',
                            data: field,
                            cache: false,
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    table.reload('LAY-pound-info-manage'); //数据刷新
                                    layer.close(index); //关闭弹层
                                } else {
                                    layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                                }
                            },
                            error: function (error) {
                                layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                            }
                        });
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    // 取到弹出层里的元素，并把编辑的内容放进去

                    body.find("#id").val(obj.data.id);  //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
                    body.find("#poundName").val(obj.data.poundName);
                    body.find("#model").val(obj.data.model);
                    body.find("#unitId").val(obj.data.unitId);
                    if (obj.data.status == 1) {
                        body.find("#radio-1").attr("checked", "checked");
                    } else if (obj.data.status == 0) {
                        body.find("#radio-2").attr("checked", "checked");
                    }
                    // 记得重新渲染表单
                    form.render();
                }
            })
        }
    });

    //事件
    var active = {
        batchdel: function () {
            var checkStatus = table.checkStatus('LAY-inspection-manage')
                , checkData = checkStatus.data; //得到选中的数据

            var ids = [];
            for (var i = 0; i < checkData.length; i++) {
                var id = checkData[i].id;
                if (id) {
                    ids.push(id);
                }
            }
            if (checkData.length === 0) {
                return layer.msg('请选择数据');
            }

            console.log("checkData", checkData, "ids", ids);

            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                layer.close(index);

                layer.confirm('确定删除吗？', function (index) {

                    //提交 Ajax 成功后，静态更新表格中的数据
                    $.ajax({
                        type: 'get',
                        url: '/poundInfo/deleteByIds',
                        data: {
                            ids: ids.toString()
                        },
                        cache: false,
                        dataType: 'json',
                        success: function (result) {
                            if (result.code == 0) {
                                table.reload('LAY-pound-info-manage');
                                layer.msg('已删除');
                            } else {
                                layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                            }
                        },
                        error:function (error) {
                            layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                        }
                    });
                });
            });
        }
        , add: function () {
            layer.open({
                type: 2
                , title: '扫码获取或者编辑报检单信息'
                , content: '/inspection/form.htm'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-pound-info-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        console.log("field:", field);

                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            type: 'post',
                            url: '/poundInfo/update',
                            data: field,
                            cache: false,
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 1) {
                                    table.reload('LAY-pound-info-submit'); //数据刷新
                                    layer.close(index); //关闭弹层
                                } else {
                                    layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                                }
                            },
                            error:function (error) {
                                layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                            }
                        });
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    // 取到弹出层里的元素，并把编辑的内容放进去
                    body.find("#radio-1").attr("checked", "checked");
                    // 记得重新渲染表单
                    form.render();

                }
            });
        }
    }
    $('.layui-btn.btn-inspection').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

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
        // console.log("event", evnt);
        $("#currentWeight").val(evnt.data);
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