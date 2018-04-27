<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

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


<style type="text/css">
body {
	font-size: 12px;
	margin: 0;
	padding: 0;
	width: 100%;
	background-image: url("img/login.jpg");
}

#main { /* background: url("images/login.jpg") no-repeat; */
	background-position: center;
	background-attachment: fixed;
	width: 300px;
	height: 220px;
	margin: 200px auto;
	padding-top: 35px;
	padding-left: 60px;
	font-size: 15px;
	background-color: white;
}

#tb input{
	width: 100px;
	height: 28px;
	border:none;
	background-color: #98B2D2;
	border-radius:35px;
	color: white;
	margin-left: 5px;

}
</style>
</head>
<body>
	<div id="main">
		<form id="loginform" method="post">
			<table id="input">
			<tr><td colspan="2" align="center">CRM系统用户登录</td></tr>
			<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input type="text" class="easyui-textbox"
						 name="userName" id="uName"
						onblur="isuName(this.value)" />
					</td>
					<td style="color: red;display: none" id="uNameexec"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td class="name"><input type="password"
						class="easyui-textbox" name="userPASSWORD"
						id="uPwd" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>

			<div id="tb">
				<input type="button" id="lg" value="登录" onclick="login();" />&nbsp;&nbsp; 
				<input type="button" value="重置" onclick="resetValue();" />
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">
	function login() {
		if ($("#loginform").form("validate")) {
			$.post("/EighthCRMItem/Login.eighth", $("#loginform").serialize(),
					function(result) {
						if (result.success) {
							$.messager.alert("系统提示", result.message);
							window.location.href = "main.jsp";/* ?uName="+$('#uName').val(); */
							resetValue();
						} else {
							$.messager.alert("系统提示", result.message);
						}
					});
		}
	}

	function resetValue() {
		$('#uName').textbox("clear");
		$('#uPwd').textbox("clear");
	}
	$(function(){
			$('#loginform').form('clear');
			$("body").keydown(function(e){
				var curKye=e.which;
				if(curKye==13){
					login();
				}
			});
		});
</script>
</html>