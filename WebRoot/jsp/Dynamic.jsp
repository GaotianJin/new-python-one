<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'NetFollow.jsp' starting page</title>
    
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
			function logAction(value, row, index) {
		       var data = $("#DynamicInfo").datagrid('getData');
		       var row = data.rows[index];
		       if(row.content==null){
		       		row.content="";
		       }
		       return '<a  class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconcls="icon-search" plain="true" onclick="getDetail(' + "'" +row.dynamicContext + "',"+ "'"+ row.id+ "'"+ ')"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">查看</span><span class="l-btn-icon icon-search">&nbsp;</span></span></a>';
		    }
		   
		      //查看日志中的内容字段的详情信息
		    function getDetail(obj,id){
		    	$.post("/EighthCRMItem/modDynamic.eighth",{id:id},function(res){
		    		if(res){
		    			$.messager.alert("内容信息",window.decodeURI(obj),"info");
		    			$('#DynamicInfo').datagrid("reload");
		    		}else{
		    			$.messager.alert("内容信息","网络繁忙，请重试！","info");
		    			return;
		    		}
		    	});
		    }
  			 //日期格式转换
			function  formatterDate(value){
				if(value==null){
					return null;
				}else{
					var seperator1 = "-";
    				var seperator2 = ":";
					var date = new Date(value);
					var y=date.getFullYear();
					var m=date.getMonth()+1;
					var d=date.getDate();
					var h=date.getHours();
					var M=date.getMinutes();
					var s=date.getSeconds();
					return y+seperator1+fmt(m)+seperator1+fmt(d)+" "+fmt(h)+seperator2+fmt(M)+seperator2+fmt(s);
					}
				}
			function fmt(number){
				if (number >= 0 && number <= 9) {
	        	return "0" + number;
	    		}else{
	    		return number;
    			}
			}
			function changeDynamicValid(val){
			    
				if(val==1){
					return "已读";
				}else{
					return "未读";
				}
			}	
</script>
  </head>
  
  <body>
  <table id="DynamicInfo" title="动态消息" class="easyui-datagrid"
		style="width:auto;height:auto"  rownumbers="true"  
		 pagination="true" pageSize="20" singleSelect="true"
		 url="/EighthCRMItem/ShowDynamic.eighth"
		>
		
		 <thead>
			<tr>            
			    <th field="dystuName" width="10%" align="center" >学生名称</th>
				<th field="dynamicContext" width="50%" align="center">内容</th>
	            <th field="dycreateTime" width="15%" align="center" formatter="formatterDate">创建时间</th>    
	            <th field="dyisLook" width="10%" align="center" formatter="changeDynamicValid" >状态</th>     
 	            <th field="action" align="center" width="10%" align="center" formatter="logAction">操作</th> 
			</tr>
		 </thead>
 </table>
  </body>
</html>
