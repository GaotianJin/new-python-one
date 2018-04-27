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

<title>My JSP 'UserPassword.jsp' starting page</title>

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

<script>
	function updatePwd() {
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var newPwd1 = $("#newPwd1").val();
			$.post("/EighthCRMItem/updatePwd.eighth", {
				oldPwd : oldPwd,
				newPwd : newPwd,
				ifnewPwd:isPWD(newPwd),
				newPwd1 : newPwd1
			}, function(res) {
				if (res.success) {
					$.post('/EighthCRMItem/clearSession.eighth', function(ress) {
						$.messager.confirm("系统提示", res.message, function(resss) {
							window.open('/EighthCRMItem/index.jsp', '_parent');
						});
					});
				} else {
					$.messager.alert("系统提示", res.message);
				}
			});
		
	}
			function isPWD(s){
				var patrn=/^[a-zA-Z0-9]{6,15}$/;
				if(!patrn.exec(s)){
					return false;
				}else{
					return true;
				}
			}
</script>


<style type="text/css">
#save {
	border: none;
	width: 100px;
	line-height: 28px;
	height: 30px;
	background-color: #BEDBF4;
	color: white;
	font-family: sans-serif;
	border-radius: 35px;
}

#p {
	padding-top: 20px;
	padding-left: 30px;
	text-align: center;
}

#old {
	margin-right: 25px;
}
</style>
</head>

<body style="margin: 0px;">
	<div id="p" class="easyui-window" title="修改密码"
		style="width:350px;height:250px;padding:10px;background:#fafafa;"
		data-options="iconCls:'icon-save',closable:false,
                    collapsible:false,minimizable:false,maximizable:false">
		原密码：<input class="easyui-textbox" type="password"
			style="width:200px;height: 30px;" id="oldPwd"><br /> <br />
		新密码：<input class="easyui-textbox" type="password"
			style="width:200px;height: 30px;" id="newPwd"><br /> <br />
		<font id="old">确认新密码： <input class="easyui-textbox"
			type="password" style="width:200px;height: 30px;" id="newPwd1">
		</font> <br /> <br /> <input type="button" onclick="updatePwd()" id="save"
			value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="save" onclick="window.parent.closeTab('修改密码')"
			value="关闭" />
	</div>
</body>
</html>
