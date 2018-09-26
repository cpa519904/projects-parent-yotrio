layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;


    //过磅记录管理
    table.render({
        elem: '#LAY-pound-log-manage'
        , url: '/poundLog/list' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', sort: true}
            , {field: 'compName', title: '供应商', minWidth: 80}
            , {field: 'unitName', title: '收货单位', minWidth: 100}
            , {field: 'goodsName', title: '货品', minWidth: 80}
            , {field: 'deliveryNumb', title: '送货单号', minWidth: 100}
            , {field: 'plateNumb', title: '车牌号', minWidth: 80}
            , {field: 'grossWeight', title: '总重', minWidth: 100, sort: true}
            , {field: 'tareWeight', title: '皮重', minWidth: 100, sort: true}
            , {field: 'netWeight', title: '净重', minWidth: 80, sort: true}
            , {field: 'grossImgUrl', title: '图1', templet: '#imgTpl'}
            , {field: 'tareImgUrl', title: '图2', templet: '#imgTpl'}
            , {field: 'createTime', title: '创建时间', sort: true, minWidth: 150}
            , {field: 'remark', title: '备注'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-pound-log'}
        ]]
        , page: true
        , limit: 20
        // , height: 'full-220'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-pound-log-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                layer.close(index);

                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑用户'
                , content: '../../../views/user/user/userform.html'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        table.reload('LAY-user-front-submit'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            });
        }
    });

    //地磅管理
    table.render({
        elem: '#LAY-pound-info-manage'
        , url: '/poundInfo/list'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 50, title: 'ID', sort: true}
            , {field: 'poundName', title: '磅名'}
            , {field: 'model', title: '型号'}
            , {field: 'unitId', title: '单位id'}
            , {field: 'createTime', title: '创建时间', sort: true}
            , {field: 'status', title: '状态', templet: '#buttonTpl', align: 'center'}
            // , {field: 'remark', title: '备注'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-pound-info'}
        ]]
        , page: true
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-pound-info-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                layer.close(index);
                layer.confirm('确定删除此管理员？', function (index) {
                    console.log(obj)
                    obj.del();
                    layer.close(index);
                });
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑管理员'
                , content: '/sysUser/adminform.htm'
                , area: ['420px', '420px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-back-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        table.reload('LAY-user-front-submit'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            })
        }
    });


    exports('pound', {})
});