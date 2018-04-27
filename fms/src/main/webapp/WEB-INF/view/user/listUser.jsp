<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/user/listUserInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="用户信息" iconCls="icon-ok" collapsible="true">
		<form id="userForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">用户登陆账号</td>
						<td><input name="userCode" id="listuser_userCode" class="table_input"></td>
						<td class="table_text" align="right">用户名称</td>
						<td align="left"><input name="userName" id="listuser_userName"class="table_input"></td>
						<td class="table_text" align="right">所属机构</td>
						<td align="left"><select class = "table_select easyui-combobox" name="comId"  id = "listuser_comId" ></select></td>
					</tr>

				</table>
				<div>
					<a href="#" onclick="clearUserFormList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchUser()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="userTable"></table>
		</div>
	</div>
</div>