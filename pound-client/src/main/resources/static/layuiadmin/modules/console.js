layui.define(['table', 'form'], function (exports) {

    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //方法封装
    var methods = {
        //获取当前时间字符串
        getNowFormatDate: function () {
            var date = new Date();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var strHour = date.getHours();
            var strMinute = date.getMinutes();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            if (strHour >= 0 && strHour <= 9) {
                strHour = "0" + strHour;
            }
            if (strMinute >= 0 && strMinute <= 9) {
                strMinute = "0" + strMinute;
            }
            var currentDate = date.getFullYear().toString() + month.toString() + strDate.toString() + strHour.toString() + strMinute.toString();
            return currentDate;
        },
        //获取未处理过磅单
        getUnFinishedPoundLog: function () {
            $.ajax({
                type: 'get',
                url: '/poundLog/listUnFinished',
                cache: false,
                dataType: 'json',
                success: function (result) {
                    if (result.code == 0) {
                        //显示表单
                        var htmlStr = '';
                        for (var i = 0; i < result.data.length; i++) {
                            var item = result.data[i];
                            htmlStr += '<li class="layui-nav-item unfinished" plNo="' + item.poundLogNo + '"><a >' + item.plateNo + '</a></li>';
                        }
                        $("#unFinishList").html(htmlStr);

                        //设置表单监听
                        $(".layui-nav-item.unfinished").click(function () {
                            var plNo = $(this).attr("plNo");
                            if (plNo) {
                                //【改】：修改poundLog表plNo字段
                                layui.data('pound_log', {
                                    key: 'plNo'
                                    , value: plNo
                                });
                                window.location.reload();
                            }
                        });
                    } else {
                        layer.alert("获取未处理过磅单异常：" + result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                    }
                },
                error: function (error) {
                    layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                }
            });
        }
    };

    //获取未处理榜单
    methods.getUnFinishedPoundLog();

    //获取过磅单号
    var poundLogNoStr = $("#poundLogNo").val();
    if (poundLogNoStr == undefined || poundLogNoStr == '') {
        //【查】：向pound_log表读取全部的数据
        var poundLogData = layui.data('pound_log');
        if (poundLogData) {
            poundLogNoStr = poundLogData.plNo;
        }
    }
    // console.log('poundLogNoStr:', poundLogNoStr);
    if (!poundLogNoStr) {
        //如果过磅单未生成，给过磅单号赋值-年月日时分
        poundLogNoStr = methods.getNowFormatDate();
        //【增】：向poundLog表插入一个plNo字段，如果该表不存在，则自动建立。
        layui.data('pound_log', {
            key: 'plNo'
            , value: poundLogNoStr
        });
    }
    $("#poundLogNo").val(poundLogNoStr);

    //查询条件
    var json = {};
    json.plNo = poundLogNoStr;
    //报检单列表管理
    table.render({
        elem: '#LAY-inspection-manage'
        , url: '/inspection/list'
        , where: json //如果无需传递额外参数，可不加该参数
        , totalRow: true
        , cols: [[
            {type: 'checkbox', fixed: 'left', totalRowText: '合计'}
            , {field: 'plNo', title: '报检单单号'}
            , {field: 'plateNo', title: '车牌号', minWidth: 80}
            , {field: 'goodsKindName', title: '货品类型', minWidth: 100}
            , {field: 'inspWeight', title: '报价单重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'returnWeight', title: '随车退重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'compName', title: '供应商名称', minWidth: 80}
            , {field: 'sample', title: '是否样品', minWidth: 80}
            , {title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-inspection'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
        ]]
        , limit: 10
        , text: {
            none: '请点击 “添加” 按钮添加报检单'
        }
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
                        , submitID = 'LAY-inspection-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //获取过磅单号
                    var poundLogNo = $("#poundLogNo").val();
                    console.log("获取过磅单号：" + poundLogNo);

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
                        error: function (error) {
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
                        , submitID = 'LAY-inspection-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //获取过磅单号
                    var poundLogNo = $("#poundLogNo").val();
                    //获取收货组织
                    var unitName = $("#unitName").val();
                    // console.log("获取过磅单号：" + poundLogNo);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        field.plNo = poundLogNo;
                        field.unitName = unitName;
                        //处理checkBox的状态值
                        var types = 0;
                        if (field.types == 'on') {
                            types = 1;
                        }
                        field.types = types;

                        console.log("field:", field);
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            type: 'post',
                            url: '/inspection/save',
                            data: field,
                            cache: false,
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    table.reload('LAY-inspection-manage'); //数据刷新
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
                    body.find("#radio-1").attr("checked", "checked");
                    // 记得重新渲染表单
                    form.render();

                }
            });
        }
        //新增过磅单
        , newPoundLog: function () {
            //【删除】：修改poundLog表plNo字段
            layui.data('pound_log', {
                key: 'plNo'
                , remove: true
            });
            //刷新页面
            window.location.reload();
        }
    }

    //监听点击事件
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