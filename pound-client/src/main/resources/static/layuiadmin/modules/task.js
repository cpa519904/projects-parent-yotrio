layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //任务管理
    table.render({
        elem: '#LAY-task-manage'
        , url: '/task/list'
        , cols: [[
            // {type: 'checkbox', fixed: 'left'}
             {field: 'id', width: 50, title: 'ID', sort: true}
            , {field: 'taskName', title: '任务'}
            , {field: 'description', title: '描述'}
            , {field: 'otherId', title: 'OtherId'}
            // , {field: 'weight', title: '权重值'}
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
        }
    });


    exports('task', {})
});