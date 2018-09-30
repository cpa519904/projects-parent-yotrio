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
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', sort: true, totalRowText: '合计'}
            , {field: 'compName', title: '供应商', minWidth: 80}
            , {field: 'unitName', title: '收货单位', minWidth: 100}
            , {field: 'goodsName', title: '货品', minWidth: 80}
            , {field: 'deliveryNumb', title: '送货单号', minWidth: 100}
            , {field: 'plateNumb', title: '车牌号', minWidth: 80}
            , {field: 'grossWeight', title: '总重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'tareWeight', title: '皮重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'netWeight', title: '净重', minWidth: 80, sort: true, totalRow: true}
            , {field: 'grossImgUrl', title: '图1', templet: '#imgTpl1'}
            , {field: 'tareImgUrl', title: '图2', templet: '#imgTpl2'}
            , {field: 'createTime', title: '创建时间', sort: true, minWidth: 150}
            , {field: 'remark', title: '备注'}
            // , {field: 'status', title: '状态'}
            // , {field: 'updateTime', title: '更新时间', sort: true}
            // , {title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-pound-log'}
        ]]
        , page: true
        , limit: 10
        , text: '对不起，加载出现异常！'
    });

    exports('pound', {})
});