<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
	 $.messager.confirm('提示', '是否退出系统？', function(r) {
		if (r) {
			top.window.location="<%=request.getContextPath()%>/index.jsp";		
		}else{
			window.parent.deletetab("重新登陆");
		}});
</script>
<div id="tabdiv">
</div>










	