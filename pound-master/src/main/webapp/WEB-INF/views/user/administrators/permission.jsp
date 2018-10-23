<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="utf-8">
  <title>权限管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../../layuiadmin/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            角色筛选
          </div>
          <div class="layui-inline">
            <select name="permissionName" lay-filter="LAY-user-adminpermission-type">
              <option value="-1">全部权限</option>
              <option value="0">管理员</option>
              <option value="1">超级管理员</option>
              <option value="2">纠错员</option>
              <option value="3">采购员</option>
              <option value="4">推销员</option>
              <option value="5">运营人员</option>
              <option value="6">编辑</option>
            </select>
          </div>
        </div>
      </div>
      <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
          <button class="layui-btn layuiadmin-btn-permission" data-type="batchdel">删除</button>
          <button class="layui-btn layuiadmin-btn-permission" data-type="add">添加</button>
        </div>
      
        <table id="LAY-user-back-permission" lay-filter="LAY-user-back-permission"></table>
        <script type="text/html" id="buttonTpl">
          {{#  if(d.check == true){ }}
            <button class="layui-btn layui-btn-xs">已审核</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">未审核</button>
          {{#  } }}
        </script>
        <script type="text/html" id="table-useradmin-admin">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
      </div>
    </div>
  </div>

 <script src="../../../layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '../../../layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'useradmin', 'table'], function(){
    var $ = layui.$
    ,form = layui.form
    ,table = layui.table;
    
    //搜索权限
    form.on('select(LAY-user-adminpermission-type)', function(data){
      //执行重载
      table.reload('LAY-user-back-permission', {
        where: {
          permisson: data.value
        }
      });
    });
  
    //事件
    var active = {
      batchdel: function(){
        var checkStatus = table.checkStatus('LAY-user-back-permission')
        ,checkData = checkStatus.data; //得到选中的数据

        if(checkData.length === 0){
          return layer.msg('请选择数据');
        }
        
        layer.confirm('确定删除吗？', function(index) {
            
          //执行 Ajax 后重载
          /*
          admin.req({
            url: 'xxx'
            //,……
          });
          */
          table.reload('LAY-user-back-permission');
          layer.msg('已删除');
        });
      },
      add: function(){
        layer.open({
          type: 2
          ,title: '添加新权限'
          ,content: '/sysPermission/permissionForm.htm'
          ,area: ['500px', '480px']
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
            ,submit = layero.find('iframe').contents().find("#LAY-user-permission-submit");

            //监听提交
            iframeWindow.layui.form.on('submit(LAY-user-permission-submit)', function(data){
              var field = data.field; //获取提交的字段
              
              //提交 Ajax 成功后，静态更新表格中的数据
              //$.ajax({});              
              table.reload('LAY-user-back-permission');
              layer.close(index); //关闭弹层
            });  
            
            submit.trigger('click');
          }
        }); 
      }
    }  
    $('.layui-btn.layuiadmin-btn-permission').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
  });
  </script>
</body>
</html>

