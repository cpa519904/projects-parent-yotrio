layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //任务管理
    table.render({
        elem: '#LAY-task-manage'
        , url: '/task/list'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 50, title: 'ID', sort: true}
            , {field: 'taskName', title: '任务'}
            , {field: 'description', title: '描述'}
            , {field: 'otherId', title: 'OtherId'}
            , {field: 'weight', title: '权重值'}
            , {field: 'createTime', title: '创建时间', sort: true}
            , {field: 'updateTime', title: '更新时间', sort: true}
            , {field: 'executeTime', title: '执行时间', sort: true}
            , {field: 'status', title: '状态', templet: '#buttonTpl', align: 'center'}
            , {title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-task'}
        ]]
        , page: true
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-task-manage)', function (obj) {
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
            console.log("obj", obj);

            layer.open({
                type: 2
                , title: '编辑地磅'
                , content: '/task/form.htm'
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
                        //$.ajax({});
                        table.reload('LAY-task-submit'); //数据刷新
                        layer.close(index); //关闭弹层
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
                        body.find("#radio-1").attr("checked","checked");
                    }else if (obj.data.status == 0) {
                        body.find("#radio-2").attr("checked","checked");
                    }
                    // 记得重新渲染表单
                    form.render();

                    setTimeout(function () {
                        layui.layer.tips('点击此处返回地磅列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500);
                }
            })
        }
    });


    exports('task', {})
});