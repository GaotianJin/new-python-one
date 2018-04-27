<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/role/listRoleInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="角色信息" iconCls="icon-ok" collapsible="true">
		<form id="listRoleForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">角色名称</td>
						<td  align="left"><input name="roleName" class="table_input"></td>
						<td class="table_text" align="right">角色编码</td>
						<td  align="left"><input name="roleCode" class="table_input"></td>
						<td class="table_text" align="right">角色查询权限</td>
						<td align="left"><span class="comboSpan"/>
						<input name="rolePrivilege" id="rolePrivilege" ></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearRoleFormList()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchRole()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv"> 
			<table id="roleTable"></table>
		</div>
	</div>
</div>


