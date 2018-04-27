<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Modules.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
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

<script type="text/javascript">
	var url;
	$(function() {
		$("#module").tree({
			url : "showAllModules.eighth",
			method : "get",
			lines:true,
			formatter : function(node) {
				return node.moduleName;
			}
		});
	});
	function openMenuAddDialog() {
		$('#fm').form("clear");
		var row = $("#module").tree("getSelected");
		if (!row) {
			$('#dlg').dialog('open').dialog("setTitle", "添加菜单");
			$("#moduleParentId").val(0);
			$("#moduleParentName").val("跟菜单");
			$("#up").attr("hidden",false);
			url = "/EighthCRMItem/addModules.eighth";
		} else {
			$('#dlg').dialog('open').dialog("setTitle", "添加菜单");
			$("#moduleParentId").val(row.moduleId);
			$("#moduleParentName").val(row.moduleName);
			$("#up").attr("hidden",false);
			url = "/EighthCRMItem/addModules.eighth";
		}
	}

	function openModulesModifyDialog() {
		var row = $("#module").tree("getSelected");
		if (row) {
			$('#dlg').dialog('open').dialog("setTitle", "修改菜单");
			$("#up").attr("hidden",true);
			$("#moduleName").val(row.moduleName);
			$("modulePath").val(row.modulePath);
			url = "/EighthCRMItem/updModules.eighth?moduleId="+row.moduleId;
		} else {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
	}

	function saveModules() {
		if($("#fm").form("validate")) {
			$.post(url,$("#fm").serialize(),function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示",result.message);
					$("#dlg").dialog("close");
					$("#module").tree("reload");
				}else{
					$.messager.alert("系统提示",result.message);
					return;
				}
			});
		}
	}
	function delModules(){
		var row = $("#module").tree("getSelected");
		$.messager.confirm("系统提示","确定删除这条数据？",function(resss){
			if(resss){
				$.post("/EighthCRMItem/delModule.eighth",{mId:row.moduleId},function(result){
					if(result=="success"){
						$.messager.alert("系统提示","删除成功");
						$("#module").tree("reload");
					}else{
						$.messager.alert("系统提示","删除失败");
					}
				});
			}else{
				return;
			}
		});
		
	}
	
	function closeRoleDialog(){
		$('#dlg').dialog("close");
	}
</script>
</head>

<body>
	<div>
		<div>
			<a href="javascript:openMenuAddDialog()" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" plain="true">添加</a> <a
				href="javascript:openModulesModifyDialog()" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'" plain="true">修改</a> <a
				href="javascript:delModules()" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'" plain="true">删除</a>
		</div>
		<ul id="module" class="easyui-tree">
		</ul>
	</div>
	<div style="margin:200px auto;">
		<div id="dlg" class="easyui-dialog"
			style="width:400px;height:200px;padding:20px 10px 0;" closed="true"
			buttons="#dlgbuttons">
			<form id="fm" method="post" ModelAttribute="module">
				<table>
					<tr hidden="true">
						<td>上级菜单Id：</td>
						<td><input type="text" name="moduleParentId"
							id="moduleParentId" readonly="readonly"></td>
					</tr>
					<tr id="up">
						<td>上级菜单：</td>
						<td><input type="text" id="moduleParentName"
							readonly="readonly"></td>
					</tr>

					<tr>
						<td>菜单名称：</td>
						<td><input type="text" name="moduleName" id="moduleName"
							class="easyui-validatebox" required="true">
						</td>
					</tr>
					<tr>
						<td>菜单路径：</td>
						<td><input type="text" name="modulePath" id="modulePath"
							class="easyui-validatebox" required="true">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="dlgbuttons">
			<a href="javascript:saveModules()" class="easyui-linkbutton"
				iconCls="icon-ok">保存</a> <a href="javascript:closeRoleDialog()"
				class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
		</div>
	</div>
</body>
</html>
