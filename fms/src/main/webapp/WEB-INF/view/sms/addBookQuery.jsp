<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/cis/thirdparty/sms/addBookInit.js"></script>
<script type="text/javascript"></script>

<body style="margin:10px;">
<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="个人通讯录查询" iconCls="icon-ok" collapsible="true">
<form id="queryForm">			
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr align="left">
				    <td width="125" class="table_text" align="right">姓名：</td>
				    <td width="200" align="left"><select id="name" name="name" class="table_select"></td>
				    <td rowspan="4">&nbsp;</td>
				    </tr>
				</table>
				<div> <a href="#" class="easyui-linkbutton e-cis_button"
						iconCls="icon-reload" onclick="clearForm()">清空</a>
						<a href="#" class="easyui-linkbutton e-cis_button"
						iconCls="icon-search" onclick="searchAdd()">查询</a>
				  </div>
			</div>
</form>
</div>
</div>
<div id="tabdiv" style="margin-top:10px;">
	<table id="addBookTable"></table>
</div>
<div id="addBooktab" class="easyui-tabs" fit="false" border="ture" plain="true"></div>
</body>