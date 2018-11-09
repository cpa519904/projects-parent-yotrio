layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //公司管理
    table.render({
        elem: '#LAY-company-manage'
        , url: '/company/list'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 50, title: 'ID', sort: true}
            , {field: 'code', title: '公司编码'}
            , {field: 'compName', title: '公司名称'}
            , {field: 'description', title: '描述'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
            // , {field: 'status', title: '状态', templet: '#buttonTpl', align: 'center'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-company'}
        ]]
        , page: true
        , height: 'full-200'
        , text: {
            none: '暂无相关数据'
        }
    });

    //监听工具条
    table.on('tool(LAY-company-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此公司？', function (index) {
                // console.log(obj);
                //提交 Ajax 成功后，静态更新表格中的数据
                $.ajax({
                    type: 'get',
                    url: '/company/delete',
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
                , title: '编辑公司'
                , content: '/company/form.htm'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-company-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        console.log("fieldL", field);
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            type: 'post',
                            url: '/company/update',
                            data: field,
                            cache: false,
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 0) {
                                    table.reload('LAY-company-manage'); //数据刷新
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
                    body.find("#compName").val(obj.data.compName);
                    body.find("#code").val(obj.data.code);
                    body.find("#description").val(obj.data.description);
                    // 记得重新渲染表单
                    form.render();
                }
            })
        }
    });

    exports('company', {})
});