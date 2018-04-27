<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/user/modifyUserInit.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/having.css" type="text/css">
<div id="tabdiv">
	<form id="modifyUserForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="密码修改" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">输入原始密码：</td>
						<td><input type="Password" id="password" name="password" class="table_input"></td>
						<td class="table_text" align="right">输入新密码：</td>
						<td align="left"><input type="Password" id="password1" name="password1" class="table_input"></td>
						<td class="table_text" align="right">确认新密码：</td>
						<td align="left"><input type="Password" id="password2" name="password2" class="table_input"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="modifyUser()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">确认</a>
					<a href="#" onclick="reset()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">重置</a>
				</div>
			</div>
		</div>
	</form>
</div>