layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //过磅记录管理
    table.render({
        elem: '#LAY-pound-log-manage'
        , url: 'http://localhost:8008/api/poundLog/list'
        // , toolbar: '#table-pound-log-toolbar'
        , totalRow: true
        , where: {

        } //如果无需传递额外参数，可不加该参数
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', sort: true, totalRowText: '合计'}
            // , {field: 'types', title: '类别', minWidth: 60,templet:'#logTypes'}
            , {field: 'compName', title: '供应商', minWidth: 80}
            , {field: 'unitName', title: '收货单位', minWidth: 100}
            , {field: 'goodsName', title: '货品', minWidth: 80}
            , {field: 'plateNo', title: '车牌号', minWidth: 80}
            , {field: 'grossWeight', title: '总重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'tareWeight', title: '皮重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'netWeight', title: '净重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'diffWeight', title: '磅差', minWidth: 80, sort: true, totalRow: true}
            , {field: 'returnWeightTotal', title: '退货', minWidth: 80, sort: true, totalRow: true}
            , {field: 'sampleNetWeight', title: '样品', minWidth: 80, sort: true, totalRow: true}
            , {field: 'grossImgUrl', title: '图1', templet: '#imgTpl1'}
            , {field: 'tareImgUrl', title: '图2', templet: '#imgTpl2'}
            , {field: 'createTime', title: '创建时间', sort: true, minWidth: 150}
            , {field: 'remark', title: '备注'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
            // , {title: '操作', width: 120, align: 'center', fixed: 'right', toolbar: '#table-pound-log'}
        ]]
        , page: true
        , limit: 10
        , text: {
            none: '暂无相关数据'
        }
    });

    //监听搜索
    form.on('submit(LAY-pound-log-search)', function (data) {
        var field = data.field;
        field.poundId = $("#poundId").val();
        field.token = $("#token").val();
        //执行重载
        table.reload('LAY-pound-log-manage', {
            where: field
        });
    });

    //开始时间
    laydate.render({
        elem: '#laydate-type-datetime-start'
        , type: 'datetime'
    });
    //结束时间
    laydate.render({
        elem: '#laydate-type-datetime-end'
        , type: 'datetime'
    });
    exports('pound', {})
});