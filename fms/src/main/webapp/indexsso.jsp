<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>µÇÂ¼½Ó¿ÚÆ½Ì¨</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
</head>

<script>
  jQuery(function($) {
  $('input[name="username"]').focus();
  var code = "${code}"; 
  var message = "${message}"; 
  var username = "${username}"; 
  var password = "${password}"; 
  if(((code)=="1")||((code)=="3")){
	  $('input[name="username"]').focus();
	  $('input[name="password"]').attr("value",password);
	  $('#usernamediv').addClass("error").append("<span class='help-inline'>"+message+"</span>");
  } else if(((code)=="2")||((code)=="4")) {
	  $('input[name="password"]').focus();
	  $('input[name="username"]').attr("value",username);
	  $('#passworddiv').addClass("error").append("<span class='help-inline'>"+message+"</span>");
  }  
  });
  
  </script>

<body>
	<div class="container" style="margin-top: 100px">
		<form action="welcome" class="well" style="width: 220px; margin: 0px auto;"
			method="post">
			<div id="usernamediv" class="control-group">
				<label class="control-label">ÕÊºÅ£º</label>
				<div class="controls">
					<input type="text" name="username" value="001">
				</div>
			</div>
			<div id="passworddiv" class="control-group">
				<label class="control-label">ÃÜÂë£º</label>
				<div class="controls">
					<input type="text" name="password" value="001">
				</div>
			</div>
			<button class="btn btn-success" type="submit">µÇÂ¼</button>
		</form>
	</div>
</body>
</html>
