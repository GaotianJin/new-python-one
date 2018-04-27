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

<title>My JSP 'MyJsp.jsp' starting page</title>

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


</head>
<script type="text/javascript">
	$(function() {
		initWeightSet();//初始化自动分量
	});
	//保存开关信息
	function saveSetCom() {
		$.ajax({
			url : 'modOn.eighth',
			type : 'post',
			data : {
				"isOn" : $("#wSet")[0].checked
			},
			dataType : 'json',
			success : function(data) {
				if (data == 1) {
					$.messager.alert("系统提示", "保存成功");
				} else {
					$.messager.alert("系统提示", "保存失败");
				}
			}
		}).style(top = 100);
	}

	//panel("options").top=$(document).scrollTop()+200
	//初始化自动分量
	function initWeightSet() {
		$.ajax({
			url : 'getAll.eighth',
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(data) {
			//alert("ss"+data);
			if (data == 1) {
				$("#wSet").attr("checked", "checked");
				$(".switchbutton-inner")
				.attr("style","width: 172px; height: 30px; line-height: 30px; margin-left: 0px;");
				} else {
					$("#wSet").attr("checked");
					$(".switchbutton-inner")
					.attr("style","width: 172px; height: 30px; line-height: 30px; margin-left: -72px;")
					}
				}
			});
		}
</script>
<body style="margin: 0px;">
	<div id="win" class="easyui-window" title="分量设置"
		style="width: 400px;height: 300px;text-align: center; padding-top: 20%;"
		minimizable="false" maximizable="false" closable="false"
		collapsible="false">
		<div>
			程序自动分配开关:<input id="wSet" name="wSet" class="easyui-switchbutton"
				style="width:100px;height:30px"> <a 
				class="easyui-linkbutton" iconCls="icon-save" onclick="saveSetCom()">保存</a>
		</div>
	</div>
</body>
</html>
