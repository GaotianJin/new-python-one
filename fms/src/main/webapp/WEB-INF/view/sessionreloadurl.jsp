<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" type="text/css" href="../js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../js/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<script type="text/javascript">
jQuery(function($) {
	$.messager.alert('提示', '登录超时，请点击确定重新登录！', 'info',function(){
		top.window.location=contextPath+"/index.jsp";
	});
});
</script>
