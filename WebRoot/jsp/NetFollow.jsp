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
       var data = $("#netFollowInfo").datagrid('getData');
       var row = data.rows[index];
       if(row.content==null){
       		row.content="";
       }
       return '<a  class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconcls="icon-search" plain="true" onclick="getDetail(' + "'" +row.content+ "'" + ')"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">查看</span><span class="l-btn-icon icon-search">&nbsp;</span></span></a>';
   }
   
      //查看日志中的内容字段的详情信息
   function getDetail(obj){
       $.messager.alert("内容信息",window.decodeURI(obj),"info");
   }
   		
   	  function resultSearchNetFollow(){
   	  	 $('#studentName').textbox("clear");
		 $('#followState').combobox("setText","").combobox("setValue", "");
		 $('#followType').combobox("setText","").combobox("setValue", "");
		 $('#startFTime').combo("setValue", "").combo("setText", "");
		 $('#endFTime').combo("setValue", "").combo("setText", "");
		 $('#s_asker').combobox("setText","").combobox("setValue", "");
   	  	 $("#netFollowInfo").datagrid('load', {});
   	  }
      function searchNetFollow(){
	  //查询条件
	  $("#netFollowInfo").datagrid('load', {
			studentName : $('#studentName').val(),
			followState : $('#followState').combobox("getValue"),
			followType : $('#followType').combobox("getValue"),
			dateA : $('#startFTime').datebox("getValue"),
			dateB : $('#endFTime').datebox("getValue"),
			sasker:$('#s_asker').combobox("getValue"),
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
</script>
  </head>
  
  <body>
  
  <table style="font-size:12px;">
    <tbody>
        <tr>
		    <td>学员名称:</td>
		    <td><input id="studentName" name="studentName" style="border:1px solid #ccc" class="easyui-textbox"></td>
		    		
		    <td>跟踪者:</td>
		    <td><input id="s_asker" class="easyui-combobox"
	            data-options="valueField:'askerId',textField:'askerName',panelHeight:'auto',url:'/EighthCRMItem/showAllAsker.eighth'"/>
	        </td>
	    </tr>
        <tr>
		    <td>开始时间:</td>
		    <td>
               <input class="easyui-datebox" id="startFTime" name="dateA" >
		    </td>
		    <td>结束时间：</td>
			 <td>
               <input class="easyui-datebox" id="endFTime" name="dateB">
		    </td>				
	    </tr>
        <tr>
            <td>回访情况:</td>
            <td>
                <input id="followState" name="followState" class="easyui-combobox" data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                          data:[{'id':'','text':'--请选择--','selected':true},
                                {'id':'思量','text':'思量'},
                                {'id':'未上门','text':'未上门'},
                                {'id':'上门未报名','text':'上门未报名'},
                                {'id':'报名未进班','text':'报名未进班'},
                                {'id':'已进班','text':'已进班'},
                                {'id':'已结业','text':'已结业'},
                                {'id':'已就业','text':'已就业'}]"/>
            </td>
            <td>跟踪方式:</td>
            <td>                
                <input id="followType" name="followType" class="easyui-combobox"
					data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                        data:[{'id':'','text':'--请选择--','selected':true},
                              {'id':'电话','text':'电话'},
                              {'id':'网络','text':'网络'},
                              {'id':'面谈','text':'面谈'},
                              {'id':'宣讲','text':'宣讲'}]"/>
                <a class="easyui-linkbutton" iconCls="icon-search"  onclick="searchNetFollow()">查询</a>
                <a class="easyui-linkbutton" iconCls="icon-search"  onclick="resultSearchNetFollow()">重置</a>
            </td>  
        </tr>         
    </tbody>
  </table>
  
  
  <table id="netFollowInfo" title="学生信息" class="easyui-datagrid"
		style="width:auto;height:auto"  rownumbers="true"
		 pagination="true" pageSize="20" singleSelect="true"
		url="/EighthCRMItem/showAllFollow.eighth">
		 <thead>
			<tr>            
			    <th field="studentName" width="5%" align="center" >学生名称</th>
				<th field="followTime" width="15%" align="center" formatter="formatterDate" >跟踪时间</th>
				<th field="followState" width="10%" align="center" >回访情况</th>
				<th field="followType" width="5%" align="center" >跟踪方式</th>
				<th field="content" width="30%" align="center">内容</th> 
	            <th field="nextFollowTime" width="15%" align="center" formatter="formatterDate">下次跟踪时间</th>         
 	            <th field="action" align="center" width="10%" align="center" formatter="logAction">操作</th> 
			</tr>
		 </thead>
 </table>
  </body>
</html>
