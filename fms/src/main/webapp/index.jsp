<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中科软财富综合管理系统</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/easyui/themes/blue/easyui.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script> --%>
<link href="<%=request.getContextPath()%>/css/login.css" type="text/css"  rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/login2.css" rel="stylesheet" type="text/css">
</head>
<script>	 
jQuery(function($) {
	  $('#usercode').focus();
	  $('.combo').css('margin','5px 0 0 40px').css('width','230px').css('border','1px solid rgb(214,214,214)').css('height','25px');
	  $('.combo-text').css('width','205px').css('text-indent','5px');
	  $('.combo-arrow').css('background-color','#FFFFFF');
	});

function enterLogin(e){
	  var key = window.event?e.keyCode:e.which;
	  if(key == 13){
		  loginForm();
	  }
}

function loginForm(){
	var errCode = "";
	var errMes = ""; 
	var userCode = $('#userCode').val();
	var password = $('#password').val();
	if(userCode == null || userCode == ""){
		errCode = "1";
		errMes = "用户名不能为空";
		$('#usercode').focus();
		$('#userCodeMsg').css('display','');
		$('#userCodeMsg').html(errMes);
	}
	else{
		if(password == null || password == ""){
			errCode = "2";
			errMes = "密码不能为空";
			$('#password').focus();
			$('#passwordMsg').css('display','');
			$('#passwordMsg').html(errMes);
		}
		else{
				$.post('welcome/loginUrl', $("#fm").serializeArray(), function(data) {
					if(data.msg=='success'){
						fm.submit();
					}else{
						errCode = data.code;
						errMes = data.message;
						if(errCode=="1"){
							  $('#userCode').focus();
							  $('#userCodeMsg').css('display','');
							  $('#userCodeMsg').html(errMes);
						  }
						  else{
							  if(errCode=="2"){
								  $('#password').focus();
								  $('#passwordMsg').css('display','');
								  $('#passwordMsg').html(errMes);
							  }
						  }
					}
				});
			}
	}
}

function hiddenMeg(){
	$('#usercodeMsg').css('display','none');
	$('#passwordMsg').css('display','none');
}
</script>

<body>
<div class="bg_bigbox">
	<div class="bg_box">
    	<div class="logo_box">
        	<img src="images/logo_02.png" />
        </div>
        <div class="center_box">
        	<form id="fm" action="welcome" method="post">
        	<div class="user_box">
                <input class="user" placeholder="请输入用户名" name="userCode"  id="userCode"  onkeydown="enterLogin(event)" onclick="hiddenMeg();"/>
                <span id = "userCodeMsg" class = "message" ></span>
            </div>
            <div class="code_box">
                <input class="code" type="password" placeholder="请输入密码"  name="password"  id="password" onkeydown="enterLogin(event);" onclick="hiddenMeg();"/>
                <span id = "passwordMsg" class = "message" ></span>
            </div>
			<input class="login_button" value="登 录" type="button" onclick="loginForm();" />
			</form>
        </div>
        <div class="footer_box">
        	<p>地址：上海市江场三路56号8楼802室</p>
        </div>
    </div>
</div>
</body>
</html>
