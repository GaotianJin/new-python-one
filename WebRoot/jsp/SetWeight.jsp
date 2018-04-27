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

<title>My JSP 'SetWeight.jsp' starting page</title>

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
	<script type="text/javascript" src="/EighthCRMItem/js/myjs/easyUIvalidate.js"></script>
<script type="text/javascript">
	function handleAtion(value, row, index) {
		return '<a href="javascript:openUpdateAskers()" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconcls="icon-edit" plain="true" onclick="openUpdateAskers('
				+ "'"
				+ index
				+ "'"
				+ ')"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">编辑权重</span><span class="l-btn-icon icon-edit">&nbsp;</span></span></a>';
	}
</script>
<script type="text/javascript">
	//关闭
	var url;
	function closeRoleDialog() {
		$('#dlg').dialog("close");
	}
	//修改
	function openUpdateAskers(index) {

		var rows = $("#askersInfo").datagrid('getRows');
		var row = rows[index];
		$("#dlg").dialog("open").dialog("setTitle", "修改权重");
		$("#weight").val(row.weight);
		$("#askerId").val(row.askerId);
		$("#bakContent").val(row.bakContent);
		url="/EighthCRMItem/updateAskers.eighth";
	}
	//保存
	function saveWeight() {
		$('#fm').form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.errorMsg) {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				} else {
					$.messager.alert("系统提示", "保存成功");
					$("#dlg").dialog("close");
					$("#askersInfo").datagrid("reload");
				}
			}
		});
	}
	
</script>
</head>

<body>
	<table id="askersInfo" title="用户信息	" class="easyui-datagrid"
		style="width:auto;height:auto" rownumbers="true" pagination="true"
		pageSize="20" singleSelect="true" fit="true"
		url="/EighthCRMItem/showAllAsker.eighth">
		<thead>
			<tr>
				<th field="askerId" width="15%" align="center" hidden="true">编号</th>
				<th field="askerName" width="15%" align="center">用户名</th>
				<th field="roleName" width="15%" align="center">角色</th>
				<th field="weight" width="10%" align="center">权重</th>
				<th field="bakContent" width="20%" align="center">备注</th>
				<th field="setWeight" width="15%" align="center"
					formatter="handleAtion">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:250px;padding:20px 10px 0;" closed="true"
		buttons="#dlgbuttons">
		<form id="fm" method="post">
			<table>
				<tr hidden="true">
					<td>askerId：</td>
					<td><input type="text" name="askerId" id="askerId"
						class="easyui-validatebox" required="true">
					</td>
				</tr>
				<tr>
					<td>权重：</td>
					<td><input type="text" name="weight" id="weight"
						class="easyui-validatebox" data-options="required:true,validType:'onenum'" />
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td><textarea name="bakContent" id="bakContent" cols=30 rows=4></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlgbuttons">
		<a href="javascript:saveWeight()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeRoleDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
