<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<!--
	    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
		-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/EighthCRMItem/js/myjs/easyUIvalidate.js"></script>


<style type="text/css">
input {
	width: 80px;
}
</style>
<script type="text/javascript">
			var url;
			function resultSearchRole(){
				$('#s_rolename').val("");
				$('#dg').datagrid('load',{});
			}
			function searchRole(){
				$('#dg').datagrid('load',{
					s_rolename:$('#s_rolename').val(),
				});				
			}
			function deleteRole(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length==0){
					$.messager.alert("系统提示","请选择要删除的数据！");
					return;
				}
				var row=selectedRows[0];
				$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
					if(r){
						$.post("/EighthCRMItem/delRole.eighth",{roleId:row.roleId},function(result){
							if(result.success){
								$.messager.alert("系统提示",result.message);
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示",result.message);
								return;
							}
						},"json");
					}
				});
			}
			function saveQuanXian(){
				var nodes = $('#treegrid').tree('getChecked');
				var selectedRows=$("#dg").datagrid('getSelections');
				var strIds=[];
				for(var i=0;i<nodes.length;i++){
					strIds.push(nodes[i].moduleId);
				}
				var ids=strIds.join(",");
				var row=selectedRows[0];
				$.post("/EighthCRMItem/AddRoleModules.eighth",({menuIds:ids,roleid:row.roleId}),function(result){
					if(result){
							$.messager.alert("系统提示","保存成功");
							$("#treedlg").dialog("close");
							$("#dg").datagrid("reload");
							$('#treegrid').tree("reload");
						}else{
							$.messager.alert("系统提示","保存失败");
							return;
						}
				});
			}
			//打开添加角色窗口
			function openRoleAddDialog(){
				$("#fm").form("clear");
				$('#dlg').dialog('open').dialog("setTitle","添加角色");
				url=("/EighthCRMItem/addRole.eighth");
			}
			
			function openRoleModifyDialog(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length!=1){
					$.messager.alert("系统提示","请选择一条要编辑的数据！");
					return;
				}
				var row=selectedRows[0];
				$("#dlg").dialog("open").dialog("setTitle","修改角色信息");
				$("#roleName").val(row.roleName);
				url="/EighthCRMItem/updRole.eighth?roleId="+row.roleId;
			}
			
			function closeRoleDialog(){
				$('#dlg').dialog("close");
				$('#treedlg').dialog("close");
			}
			
			function saveRole(){
				$('#fm').form("submit",{
					url:url,
					onSubmit:function(){
						return $(this).form("validate");
					},
					success:function(result){
						if(result==1){
							$.messager.alert("系统提示","保存成功");
							$("#dlg").dialog("close");
							$("#dg").datagrid("reload");
							
						}else{
							$.messager.alert("系统提示","错误！角色名重复！");
							return;
						}
					}
				});
			}
			function exportRole(){
				$('#search').form("submit",{
					url:"employee!exportRole"
				})
				//window.location.href="employee!exportRole";
			}
			function changeRoleValid(val){
				if(val==1){
					return "有效";
				}else{
					return "无效";
				}
			}
			//权限窗口
			function opentreedlg(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length!=1){
					$.messager.alert("系统提示","请选择一条要编辑的数据！");
					return;
				}
				$("#treegrid").tree({
					url:"/EighthCRMItem/getAllModuleByRoleId.eighth?roleId="+selectedRows[0].roleId,
					lines:true,
					checkbox:true
				});
				$("#treedlg").dialog("open").dialog("setTitle","设置角色权限");
			}
			$(function(){
				$("#treegrid").tree({
					formatter:function(tree){
						return tree.moduleName;
					}
				});
			});
		</script>
</head>
<body>
	<table id="dg" title="角色信息" class="easyui-datagrid"
		style="width:auto;height:auto" fitColumns="true" rownumbers="true"
	    pagination="true" pageSize="20" singleSelect="true" fit="true"
		url="/EighthCRMItem/showAllRole.eighth" toolbar="#tb">
		<thead>
			<tr>
				<th data-options="field:'roleId'" width="5" hidden="true">角色Id</th>
				<th data-options="field:'roleName'" width="10">角色名称</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:openRoleAddDialog()" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" plain="true">添加</a> <a
				href="javascript:openRoleModifyDialog()" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'" plain="true">修改</a> <a
				href="javascript:deleteRole()" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'" plain="true">删除</a> <a
				href="javascript:opentreedlg()" class="easyui-linkbutton"
				data-options="iconCls:'icon-man'" plain="true">权限</a>
		</div>
		<div>
			<form id="search" method="post">
				&nbsp;角色名称:&nbsp;<input type="text" name="s_rolename"
					id="s_rolename" size="10" /> <!-- <a href="javascript:searchRole()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a> -->
				<a class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="searchRole()">搜索</a>
				<a class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="resultSearchRole()">重置</a>
			</form>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:280px;height:170px;padding:30px 30px ;" closed="true"
		buttons="#dlgbuttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>角色名称：</td>
					<td><input type="text" width="100px" name="roleName"
						id="roleName" class="easyui-validatebox" data-options="required:true,validType:'chinesename'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="treedlg" class="easyui-dialog"
		style="width:500px;height:300px;padding:20px 10px 0;" closed="true"
		buttons="#dlgtreebuttons">
		<div id="treegrid" class="easyui-tree"></div>
	</div>
	<div id="dlgbuttons">
		<a href="javascript:saveRole()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeRoleDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<div id="dlgtreebuttons">
		<a href="javascript:saveQuanXian()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeRoleDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>