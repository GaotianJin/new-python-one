<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.4/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/myjs/currentdate.js"></script>


<script type="text/javascript">
	$(function() {
		$.post("getLoginUser.eighth", function(res) {
			var userId = $("#userId").html();
			if (userId == "" || userId == null) {
				window.location.href = "index.jsp";
			} else {
				if (res) {
					$('#tree').tree({
						url : "queryModByUid.eighth?userId=" + userId,
						formatter : function(node) {
							return node.moduleName;
						},
						onClick : onclickTree
					});
				}
			}
		});
	});

	function clearSession() {
		$.post('clearSession.eighth', function(res) {
			window.location.href = "index.jsp";
		});
	}

	function onclickTree(node) {
		//isLeaf方法：判断指定的节点是否是叶子节点，target参数是一个节点DOM对象。
		var isLeaf = $("#tree").tree("isLeaf", node.target);
		var node = $('#tree').tree("getSelected");
		console.log(node);
		if (isLeaf) {
			//新增选项卡
			if(node.moduleName=="动态消息"){
				$('#Dynamicpanel').hide();
			}
			openTab(node.moduleName, node.modulePath);
		} else {
			//打开或关闭节点的触发器，target参数是一个节点DOM对象
			$("#tree").tree("toggle", node.target);
		}
	}
	//关闭tab
	function closeTab(text){
		$('#tabs').tabs('close',text);
	}
	//新增tab
	function openTab(text, url) {
		if ($("#tabs").tabs('exists', text)) {
			$("#tabs").tabs('select', text);
		} else {
			var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;' src="
					+ url + "></iframe>";
			$("#tabs").tabs('add', {
				title : text,
				closable : true,
				content : content
			});
		}
	}
	//签到
	function Signin() {
		$.post("/EighthCRMItem/addUserChecks.eighth", function(res) {
			if (res.success) {
				$.messager.alert("系统提示", res.message);
				$("#Signin").hide();
				$("#Signon").show();
			} else {
				$.messager.alert("系统提示", res.message);
				$("#Signon").hide();
				$("#Signin").show();
			}
		});
	}
	//控制显示签退还是签到
	$(function(){
		$.post("/EighthCRMItem/showAValue.eighth",function(res){
			if(res){
				$("#Signon").hide();
				$("#Signin").show();
			}else{
				$("#Signin").hide();
				$("#Signon").show();
			}
		});
	})
	//签退
	function Signon() {
		$.post("/EighthCRMItem/userCheckout.eighth", function(res) {
			if (res.success) {
				$.messager.alert("系统提示", res.message);
				$("#Signon").hide();
				$("#Signin").show();
			} else {
				$.messager.alert("系统提示", res.message);
				$("#Signon").show();
				$("#Signin").hide();
			}
		});
	}
</script>
<style type="text/css">
	#welcome{
		margin: 30px 0px 10px;
		font-size: 16px;
	}
	#edit{
		text-decoration: none;
		color: black;
		float: right;
		margin-right: 20px;
		margin-top: 10px;
	}
	#edit:hover{
		color: red;
	}
	#Signin{
		display:block;
		text-decoration: none;
		width: 100px;
		height: 30px;
		background-image:url("img/btn.png");
		color: black;
		float: left;
		text-align: center;
		line-height: 30px;
		color: white;
		font-family:sans-serif;
		font-size: 14px;
		border-radius:35px;
		margin-left: 48%;
	}
	
	#Signon{
		display:block;
		text-decoration: none;
		width: 100px;
		height: 30px;
		background-image:url("img/btn1.png");
		color: black;
		float: left;
		text-align: center;
		line-height: 30px;
		color: white;
		font-family:sans-serif;
		font-size: 14px;
		border-radius:35px;
		margin-left: 48%;
	}
</style>
<title>客户关系管理系统</title>

</head>
<body class="easyui-layout">
	<span id="userId">${user.userId }</span>
	<div data-options="region:'north'"
		style="height:120px;text-align: center;">
		<a href="javascript:clearSession()" id="edit">安全退出</a>
		<div id="welcome">
			<font color="green">欢迎您：</font>&nbsp;<font color="red">${user.userName
				}&nbsp;&nbsp;</font><font color="green"><span id="currentdate"></span>
			</font>
		</div>
		<div>
			<a href="javascript:Signin()" id="Signin">签到</a><a href="javascript:Signon()" id="Signon"
				style="display: none">签退</a>
		</div>
	</div>
	<div data-options="region:'south'" style="height:30px;padding:5px;"
		align="center">
		<font color="green">版权所有&nbsp;&nbsp;</font><a
			href="http://Eighth.CRMItem.com"
			style="text-decoration: none;color: purple">ms@http://Eighth.CRMItem.com</a>
	</div>
	<div data-options="region:'west',split:true" title="导航菜单"
		style="width:200px;">
		<ul id="tree" class="easyui-tree"></ul>
	</div>
	<div data-options="region:'center'">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" style="padding:10px">
				<div align="center" style="padding-top:100px;">
					<font color="red" size="7">欢迎使用</font>
				</div>
			</div>
		</div>
		<div id="Dynamic" style="width: 200px; position:absolute;right: 15px;bottom: 10px;">
			<div id="Dynamicpanel" class="easyui-panel" style="text-align: center; padding: 0px 0px 20px;" fit="true">
				<div style="margin-bottom: 20px; padding-left:10px; background-color: red; font-weight: bold; font-size: 15px; color: white; text-align: left;">动态消息</div>
				<span style="font-size: 16px;">您有<span id="countnum" style="color: red;"></span>条未读消息！</span><br/><br/>
				<a class="easyui-linkbutton" onclick="searchDynamic()">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" onclick="hideDynamic()">取消</a>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
	function searchDynamic(){
		$('#Dynamicpanel').hide();
		var text="动态消息";
		var url="jsp/Dynamic.jsp";
		openTab(text, url);
		
	}
	function hideDynamic(){
		$('#Dynamicpanel').hide();
	}
	$(function(){
		$('#Dynamicpanel').hide();
		$.post("/EighthCRMItem/openDynamic.eighth",function(res){
			if(res.success){
				$('#countnum').html(res.count);
				$('#Dynamicpanel').show();
				opentimer();
			}
		});
	});
	function opentimer(){
		var oBox = document.getElementById("Dynamic");//要闪烁的div id  
	    var timer = null;   //定义时间器  
	    var i = 0;
	    clearInterval(timer); //先清空时间器  
	    oBox.onmouseover = function(){ //当鼠标移入div时 清空时间器  
	        clearInterval(timer);  
	    };
	    oBox.onmouseout = function(){ //当鼠标移入div时 清空时间器  
	        timer = setInterval(function () {  
	      		oBox.style.display = i++ % 2 ? "none" : "block";  // 有规律的控制div的展示与隐藏  
	      		//i > 8 && (clearInterval(timer))  //控制闪烁次数  
	    	}, 250 );  
	    };
	    timer = setInterval(function () {  
	      oBox.style.display = i++ % 2 ? "none" : "block";  // 有规律的控制div的展示与隐藏  
	      //i > 8 && (clearInterval(timer))  //控制闪烁次数  
	    }, 250 );
	};
</script>




</html>