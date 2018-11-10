layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //地磅管理
    table.render({
        elem: '#LAY-pound-info-manage'
        , url: '/poundInfo/list'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 50, title: 'ID', sort: true}
            , {field: 'poundName', title: '磅名'}
            , {field: 'adminEmpId', title: '工号'}
            , {field: 'adminName', title: '管理员'}
            , {field: 'adminMobile', title: '手机号'}
            // , {field: 'unitId', title: '单位id'}
            , {field: 'createTime', title: '创建时间', sort: true}
            // , {field: 'status', title: '状态', templet: '#buttonTpl', align: 'center'}
            // , {field: 'remark', title: '备注'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-pound-info'}
        ]]
        , page: true
        , height: 'full-200'
        , limit: 20
        , text: {
            none: '暂无相关数据'
        }
    });

    //监听工具条
    table.on('tool(LAY-pound-info-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此地磅？', function (index) {
                // console.log(obj);
                //提交 Ajax 成功后，静态更新表格中的数据
                $.ajax({
                    type: 'get',
                    url: '/poundInfo/delete',
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

        } else if (obj.event === 'edit') {
            layer.open({
                type: 2
                , title: '编辑地磅'
                , content: '/poundInfo/form.htm'
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
                    body.find("#adminEmpId").val(obj.data.adminEmpId);
                    body.find("#adminName").val(obj.data.adminName);
                    body.find("#adminMobile").val(obj.data.adminMobile);
                    // body.find("#model").val(obj.data.model);
                    // body.find("#unitId").val(obj.data.unitId);
                    // if (obj.data.status == 1) {
                    //     body.find("#radio-1").attr("checked", "checked");
                    // } else if (obj.data.status == 0) {
                    //     body.find("#radio-2").attr("checked", "checked");
                    // }
                    // 记得重新渲染表单
                    form.render();
                }
            })
        }
    });

    //过磅记录管理
    table.render({
        elem: '#LAY-pound-log-manage'
        , url: '/poundLog/list'
        , toolbar: '#table-pound-log-toolbar'
        , totalRow: true
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', sort: true, totalRowText: '合计', width: 60}
            // , {field: 'types', title: '类别', minWidth: 60,templet:'#logTypes'}
            , {field: 'poundLogNo', title: '过磅单单号', minWidth: 162, toolbar: '#table-pound-log'}
            , {field: 'poundName', title: '所属地磅', minWidth: 80}
            , {field: 'compName', title: '供应商', minWidth: 180}
            , {field: 'unitName', title: '组织', minWidth: 100}
            , {field: 'goodsName', title: '货品', minWidth: 60}
            , {field: 'plateNo', title: '车牌号', minWidth: 80}
            , {field: 'grossWeight', title: '总重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'tareWeight', title: '皮重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'netWeight', title: '净重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'diffWeight', title: '磅差', minWidth: 80, sort: true, totalRow: true}
            , {field: 'returnWeightTotal', title: '退货', minWidth: 80, sort: true, totalRow: true}
            , {field: 'sampleNetWeight', title: '样品', minWidth: 80, sort: true, totalRow: true}
            // , {field: 'grossImgUrl', title: '图1', templet: '#imgTpl1'}
            // , {field: 'tareImgUrl', title: '图2', templet: '#imgTpl2'}
            , {field: 'createTime', title: '创建时间', sort: true, minWidth: 150}
            // , {field: 'remark', title: '备注'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
            // , {title: '操作', width: 120, align: 'center', fixed: 'right'}
        ]]
        , page: true
        , limit: 20
        , height: 'full-200'
        , text: {
            none: '暂无相关数据'
        }
    });

    //监听工具条
    // table.on('tool(LAY-pound-log-manage)', function (obj) {
    //     var data = obj.data;
    //     if (obj.event === 'del') {
    //         layer.confirm('确定删除行么', function (index) {
    //             //提交 Ajax 成功后，静态更新表格中的数据
    //             $.ajax({
    //                 type: 'get',
    //                 url: '/poundLog/delete',
    //                 data: {
    //                     id: data.id
    //                 },
    //                 cache: false,
    //                 dataType: 'json',
    //                 success: function (result) {
    //                     if (result.code == 0) {
    //                         obj.del();
    //                         layer.close(index);
    //                         layer.msg('已删除');
    //                     } else {
    //                         layer.alert(result.msg, {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
    //                     }
    //                 },
    //                 error: function (error) {
    //                     layer.alert("数据请求异常", {icon: 5}); //这时如果你也还想执行yes回调，可以放在第三个参数中。
    //                 }
    //             });
    //         });
    //     } else
    //     if (obj.event === 'detail') {
    //         var index = layer.open({
    //             type: 2
    //             , title: '查看过磅记录'
    //             , content: '/poundLog/detail.htm?poundLogNo=' + data.poundLogNo
    //             , maxmin: true
    //             , area: ['500px', '450px']
    //             , btn: '关闭'
    //             , yes: function (index, layero) {
    //                 var iframeWindow = window['layui-layer-iframe' + index]
    //                     , submitID = 'LAY-pound-log-submit'
    //                     , submit = layero.find('iframe').contents().find('#' + submitID);
    //
    //                 layer.closeAll();
    //             }
    //             , success: function (layero, index) {
    //
    //             }
    //         });
    //
    //         layer.full(index);
    //     }
    // });

    //inspection列表
    var data = {};
    data.plNo = $("#poundLogNo").val();
    table.render({
        elem: '#LAY-inspection-manage'
        , url: '/inspection/list'
        , where: data //如果无需传递额外参数，可不加该参数
        , totalRow: true
        , cols: [[
            {field: 'inspNo', title: '报检单单号', minWidth: 120, totalRowText: '合计'}
            // , {field: 'goodsKindName', title: '货品', minWidth: 60, templet: '#goodsKindTpl'}
            , {field: 'inspWeight', title: '报检单重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'inspNetWeight', title: '结算重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'returnWeight', title: '随车退重量', minWidth: 80, sort: true, totalRow: true}
            , {field: 'types', title: '样品', minWidth: 60, templet: '#typesTpl'}
            // , {title: '操作', width: 80, align: 'center', fixed: 'right', toolbar: '#table-inspection'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
        ]]
        , limit: 10
        , text: {
            none: '暂无数据'
        }, done: function (result) {

        }
    });

    exports('pound', {})
});