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
            var strSeconds = date.getSeconds();
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
            if (strSeconds >= 0 && strSeconds <= 9) {
                strSeconds = "0" + strSeconds;
            }
            var currentDate = date.getFullYear().toString() + month.toString() + strDate.toString() + strHour.toString() + strMinute.toString() + strSeconds.toString();
            return currentDate;
        },
        //获取未处理过磅单以及当前过磅单信息
        listUnFinishedAndPoundLog: function (poundLogNo) {
            //查询条件
            var json = {};
            json.poundLogNo = poundLogNo;
            // console.log("json", json);
            $.ajax({
                type: 'get',
                url: '/poundLog/listUnFinishedAndPoundLog',
                data: json,
                cache: false,
                dataType: 'json',
                success: function (result) {
                    console.log("result:", result);
                    if (result.code == 0) {
                        var unfinishedLogs = result.data.unFinishedLogs;
                        if (unfinishedLogs) {
                            //显示表单
                            var htmlStr = '';
                            for (var i = 0; i < unfinishedLogs.length; i++) {
                                var item = unfinishedLogs[i];
                                if (item.plateNo) {
                                    if (item.poundLogNo == poundLogNo) {
                                        htmlStr += '<li class="layui-nav-item unfinished layui-this" plNo="' + item.poundLogNo + '"><a >' + item.plateNo + '</a></li>';
                                    } else {
                                        htmlStr += '<li class="layui-nav-item unfinished" plNo="' + item.poundLogNo + '"><a >' + item.plateNo + '</a></li>';
                                    }
                                } else {
                                    if (item.poundLogNo == poundLogNo) {
                                        htmlStr += '<li class="layui-nav-item unfinished layui-this" plNo="' + item.poundLogNo + '"><a >' + item.poundLogNo + '</a></li>';
                                    } else {
                                        htmlStr += '<li class="layui-nav-item unfinished" plNo="' + item.poundLogNo + '"><a >' + item.poundLogNo + '</a></li>';
                                    }

                                }
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
                        }

                        //显示当前过磅单信息
                        var currPoundLog = result.data.poundLog;
                        // console.log("currPoundLog", currPoundLog);
                        if (currPoundLog) {
                            //对表单进行初始赋值,两个时间由有台生成，前台不提交所以没有name属性，需要手动赋值
                            form.val("pound-log-fom", currPoundLog);
                            $("#firstTime").val(currPoundLog.firstTime);
                            $("#secondTime").val(currPoundLog.secondTime);
                            $("#grossImgUrl").attr("src", currPoundLog.grossImgUrl);
                            $("#tareImgUrl").attr("src", currPoundLog.tareImgUrl);
                            $("#diffWeight").val(currPoundLog.diffWeight);
                            $("#netWeight").val(currPoundLog.netWeight);
                            // console.log("currPoundLog.diff:", currPoundLog.diffWeight);
                            if (parseFloat(currPoundLog.diffWeight) > 10 || parseFloat(currPoundLog.diffWeight) < -10) {
                                $("#diffWeight").css("color", "#FF5722");
                            }
                            methods.drawCanvasImage("canvas1", currPoundLog.grossImgUrl);
                            methods.drawCanvasImage("canvas2", currPoundLog.tareImgUrl);
                        }
                    } else {
                        layer.alert("获取未处理过磅单异常：" + result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                    }
                },
                error: function (error) {
                    layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                }
            });
        },
        //刷新重量参数表单值
        reloadWeight: function (result) {
            // console.log("result:", result);

            //随车退货总重量
            var totalReturnWeight = 0;
            //样品净重
            var sampleNetWeight = 0;
            if (result.code == 0) {
                var data = result.data;
                for (var i = 0; i < data.length; i++) {
                    totalReturnWeight += data[i].returnWeight;
                    if (data[i].types == 1) {
                        sampleNetWeight += data[i].inspWeight;
                    }
                }
            }

            // console.log("sampleNetWeight", sampleNetWeight);
            $("#sampleNetWeight").val(sampleNetWeight);
            $("#returnWeightTotal").val(totalReturnWeight);
        }
        //获取过磅单号
        , getPoundLogNo: function () {
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
            return poundLogNoStr;
        }
        //canvas显示图片
        , drawCanvasImage(id, src) {
            var canvas = document.getElementById(id);
            var context = canvas.getContext('2d');
            var img = new Image();
            img.src = src;
            img.onload = function () {
                context.drawImage(img, 0, 0);
            };
        }
        //获取二维码扫码结果
        , getScanningCode: function () {

            var start = new Date();
            var code = "";
            document.onkeydown = function (event) {
                console.log("二维码扫描", event);
            };

        }
    }


    //获取过磅单号,定义全部变量
    var poundLogNoStr = methods.getPoundLogNo();

    //获取未处理榜单及当前过磅记录信息
    methods.listUnFinishedAndPoundLog(poundLogNoStr);

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
            , {field: 'plNo', title: '报检单单号', minWidth: 120}
            , {field: 'plateNo', title: '车牌号', minWidth: 80}
            , {field: 'goodsKindName', title: '货品', minWidth: 60, templet: '#goodsKindTpl'}
            , {field: 'inspWeight', title: '报价单重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'returnWeight', title: '随车退重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'compName', title: '供应商名称', minWidth: 80}
            , {field: 'types', title: '样品', minWidth: 60, templet: '#typesTpl'}
            , {title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-inspection'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
        ]]
        , limit: 10
        , text: {
            none: '请点击 “添加” 按钮添加报检单'
        }, done: function (result) {
            methods.reloadWeight(result);
        }
    });

    //监听报检单表格工具条
    table.on('tool(LAY-inspection-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {

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
                        , checkboxID = 'LAY-types-checkbox'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //获取过磅单号
                    var poundLogNo = methods.getPoundLogNo();

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        field.plNo = poundLogNo;

                        // console.log("fieldL", field);
                        if (field.types == 'on') {
                            field.types = 1;
                        } else {
                            field.types = 0;
                        }

                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            type: 'post',
                            url: '/inspection/update',
                            data: field,
                            cache: false,
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    //数据刷新
                                    table.reload('LAY-inspection-manage', {
                                        done: function (result) {
                                            methods.reloadWeight(result);
                                        }
                                    });

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
                    body.find("#inspNo").val(obj.data.inspNo);
                    body.find("#inspWeight").val(obj.data.inspWeight);
                    body.find("#compName").val(obj.data.compName);
                    body.find("#plateNo").val(obj.data.plateNo);
                    body.find("#returnWeight").val(obj.data.returnWeight);
                    body.find("#goodsKind").val(obj.data.goodsKind);
                    // body.find("#LAY-types-checkbox").val(obj.data.types);

                    if (obj.data.types == 1) {
                        body.find("#LAY-types-checkbox").attr("checked", "checked");
                    }
                    // 记得重新渲染表单
                    form.render();
                }
            })
        }
    });

    /**监听控制台表单提交**/
    //称毛重
    form.on('submit(weigh-gross-submit)', function (data) {
        //抓取图片并上传图片,返回图片url
        var canvas = document.getElementById("canvas1");
        var context = canvas.getContext('2d');
        context.drawImage(video, 0, 0, 330, 212, 0, 0, 120, 80);
        //将Canvas的数据转换为base64位编码的PNG图像
        var imageData = canvas.toDataURL("image/png");
        //截取22位以后的字符串作为图像数据
        var imgStr = imageData.substr(22);
        //Ajax上传图片
        $.ajax({
            type: 'post',
            url: '/file/uploadBase64Img',
            data: {
                imgStr: imgStr,
                poundLogNo: poundLogNoStr,
                uploadType: 1 //上传图片类型 1：称毛重图片 2：称皮重图片
            },
            cache: false,
            dataType: 'json',
            success: function (result) {
                // console.log("uploadBase64Img", result);
                if (result.code == 0) {
                    //执行称重跟表单提交
                    doFormSubmit();
                } else {
                    layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                    return;
                }
            },
            error: function (error) {
                layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
            }
        });

        function doFormSubmit() {
            var field = data.field; //获取提交的字段
            //获取地磅数据
            field.grossWeight = field.currentWeight;
            //更新磅单数据

            // console.log("fieldL", field);

            //提交 Ajax 成功后，静态更新表单中的数据
            $.ajax({
                type: 'post',
                url: '/poundLog/updateGross',
                data: field,
                cache: false,
                dataType: 'json',
                success: function (result) {
                    // console.log("result:update:", result);
                    if (result.code == 0) {
                        //数据刷新
                        $("#grossWeight").val(result.data.grossWeight);
                        $("#firstTime").val(result.data.firstTime);
                        $("#grossImgUrl").attr("src", result.data.grossImgUrl);
                        $("#diffWeight").val(result.data.diffWeight);
                        $("#netWeight").val(result.data.netWeight);
                        if (parseFloat(result.data.diffWeight) > 10 || parseFloat(result.data.diffWeight) < -10) {
                            $("#diffWeight").css("color", "#FF5722");
                        }
                        // todo: 显示图片
                    } else {
                        layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                    }
                },
                error: function (error) {
                    layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                }
            });
        }
    });
    //称皮重
    form.on('submit(weigh-tare-submit)', function (data) {
        // console.log("称毛重", data);
        //抓取图片并上传图片,返回图片url
        var canvas = document.getElementById("canvas2");
        var context = canvas.getContext('2d');
        context.drawImage(video, 0, 0, 330, 212, 0, 0, 120, 80);
        //将Canvas的数据转换为base64位编码的PNG图像
        var imageData = canvas.toDataURL("image/png");
        //截取22位以后的字符串作为图像数据
        var imgStr = imageData.substr(22);
        //Ajax上传图片
        $.ajax({
            type: 'post',
            url: '/file/uploadBase64Img',
            data: {
                imgStr: imgStr,
                poundLogNo: poundLogNoStr,
                uploadType: 2 //上传图片类型 1：称毛重图片 2：称皮重图片
            },
            cache: false,
            dataType: 'json',
            success: function (result) {
                // console.log("uploadBase64Img", result);
                if (result.code == 0) {
                    //执行称重跟表单提交
                    doFormSubmit();
                } else {
                    layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                    return;
                }
            },
            error: function (error) {
                layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
            }
        });

        function doFormSubmit() {
            var field = data.field; //获取提交的字段
            //获取地磅数据
            field.tareWeight = field.currentWeight;
            //更新磅单数据
            // console.log("fieldL", field);
            //提交 Ajax 成功后，静态更新表单中的数据
            $.ajax({
                type: 'post',
                url: '/poundLog/updateTare',
                data: field,
                cache: false,
                dataType: 'json',
                success: function (result) {
                    console.log("result:update:", result);
                    if (result.code == 0) {
                        //数据刷新
                        $("#tareWeight").val(result.data.tareWeight);
                        $("#secondTime").val(result.data.secondTime);
                        $("#tareImgUrl").attr("src", result.data.tareImgUrl);
                        $("#diffWeight").val(result.data.diffWeight);
                        $("#netWeight").val(result.data.netWeight);
                        if (parseFloat(result.data.diffWeight) > 10 || parseFloat(result.data.diffWeight) < -10) {
                            $("#diffWeight").css("color", "#FF5722");
                        }
                    } else {
                        //如果还未生成过磅单就称皮重，提示用户是否要执行退货或者卖废铁操作
                        if (result.msg == '找不到您要更新的过磅记录') {
                            layer.confirm('系统认定您要执行卖废品或者退货过磅，您确定要执行该操作吗？', {icon: 3, title: '提示'}, function (index) {
                                $.ajax({
                                    type: 'post',
                                    url: '/poundLog/saveReturnTare',
                                    data: field,
                                    cache: false,
                                    dataType: 'json',
                                    success: function (result) {
                                        if (result.code == 0) {
                                            $("#tareWeight").val(result.data.tareWeight);
                                            $("#secondTime").val(result.data.secondTime);
                                            $("#tareImgUrl").attr("src", result.data.tareImgUrl);
                                        } else {
                                            layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                                        }
                                    },
                                    error: function (error) {
                                        layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                                    }
                                });

                                layer.close(index);
                            });

                        } else {
                            layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                        }
                    }
                },
                error: function (error) {
                    layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                }
            });
        }

    })
    //保存
    form.on('submit(btn-save-submit)', function (data) {
        // console.log("保存数据", data);
        var field = data.field;
        var fieldData = {};
        //这里只保存备注跟收货单位两个字段，因为其他字段都已经保存过了，这里就不再保存一遍了
        fieldData.remark = field.remark;
        fieldData.unitName = field.unitName;
        fieldData.poundLogNo = field.poundLogNo;

        // console.log("保存数据", field);
        //提交 Ajax 成功后，静态更新表单中的数据
        $.ajax({
            type: 'post',
            url: '/poundLog/updatePoundLog',
            data: fieldData,
            cache: false,
            dataType: 'json',
            success: function (result) {
                console.log("result:update:", result);
                if (result.code == 0) {
                    //数据刷新
                    $("#unitName").val(result.data.unitName);
                    $("#remark").val(result.data.remark);
                    return layer.msg('保存成功');
                } else {
                    layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                }
            },
            error: function (error) {
                layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
            }
        });
    })
    //提交到服务器
    form.on('submit(btn-submit-server)', function (data) {
        // console.log("保存数据", data);
        var field = data.field;
        var fieldData = {};
        //这里只保存备注跟收货单位两个字段，因为其他字段都已经保存过了，这里就不再保存一遍了
        fieldData.remark = field.remark;
        fieldData.unitName = field.unitName;
        fieldData.poundLogNo = field.poundLogNo;

        // console.log("保存数据", field);
        //提交 Ajax 成功后，静态更新表单中的数据
        $.ajax({
            type: 'post',
            url: '/poundLog/updatePoundLog',
            data: fieldData,
            cache: false,
            dataType: 'json',
            success: function (result) {
                console.log("result:update:", result);
                if (result.code == 0) {
                    //数据刷新
                    $("#unitName").val(result.data.unitName);
                    $("#remark").val(result.data.remark);
                    return layer.msg('保存成功');
                } else {
                    layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
                }
            },
            error: function (error) {
                layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
            }
        });
    });
    //打印
    form.on('submit(btn-print)', function (data) {
        //执行打印操作
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

            layer.confirm('确定删除吗？', function (index) {

                //提交 Ajax 成功后，静态更新表格中的数据
                $.ajax({
                    type: 'get',
                    url: '/inspection/deleteByIds',
                    data: {
                        ids: ids.toString()
                    },
                    cache: false,
                    dataType: 'json',
                    success: function (result) {
                        if (result.code == 0) {
                            //数据刷新
                            table.reload('LAY-inspection-manage', {
                                done: function (result) {
                                    methods.reloadWeight(result);
                                }
                            });
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

                        // console.log("field:", field);
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            type: 'post',
                            url: '/inspection/save',
                            data: field,
                            cache: false,
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    //数据刷新
                                    table.reload('LAY-inspection-manage', {
                                        done: function (result) {
                                            methods.reloadWeight(result);
                                        }
                                    });
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

                    //监听扫码事件
                    // methods.getScanningCode();
                    body.find('#inspNo')[0].onkeydown = function (event) {

                        // 键入Enter表明扫码枪输入完毕
                        if (event.which == 13 && body.find('#inspNo')[0].value.length > 20) {
                            //获取扫码枪数据
                            var inputData = body.find('#inspNo')[0].value;
                            //todo 可以做一下数据校验工作
                            var dataArr = inputData.split("#");
                            if (dataArr.length >= 4) {
                                body.find("#inspNo")[0].value = dataArr[0];
                                body.find("#inspWeight")[0].value = dataArr[1];
                                body.find("#compName")[0].value = dataArr[2];
                                body.find("#plateNo")[0].value = dataArr[3];
                                form.render();
                            } else {
                                layer.alert("扫码获取失败，请手工填写", {icon: 5});
                            }
                        }
                    }
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

    //访问用户媒体设备的兼容方法
    function getUserMedia(constraints, success, error) {
        if (navigator.mediaDevices.getUserMedia) {
            //最新的标准API
            navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
        } else if (navigator.webkitGetUserMedia) {
            //webkit核心浏览器
            navigator.webkitGetUserMedia(constraints, success, error)
        } else if (navigator.mozGetUserMedia) {
            //firfox浏览器
            navigator.mozGetUserMedia(constraints, success, error);
        } else if (navigator.getUserMedia) {
            //旧版API
            navigator.getUserMedia(constraints, success, error);
        }
    }

    var video = document.getElementById('video');
    var canvas1 = document.getElementById('canvas1');
    var canvas2 = document.getElementById('canvas2');
    var context1 = canvas1.getContext('2d');
    var context2 = canvas2.getContext('2d');

    function success(stream) {
        //兼容webkit核心浏览器
        var CompatibleURL = window.URL || window.webkitURL;
        //将视频流设置为video元素的源
        // console.log(stream);
        //video.src = CompatibleURL.createObjectURL(stream);
        video.srcObject = stream;
        video.play();
    }

    function error(error) {
        console.log("访问用户媒体设备失败");
    }

    if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
        //调用用户媒体设备, 访问摄像头
        getUserMedia({video: {width: 330, height: 212}}, success, error);
    } else {
        alert('不支持访问用户媒体');
    }

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
})
;