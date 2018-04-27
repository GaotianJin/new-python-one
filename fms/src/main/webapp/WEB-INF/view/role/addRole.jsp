<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/role/addRoleInit.js"></script>

<div id="tabdiv">
	<form id="addRoleForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="角色信息" iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">角色名称</td>
						<td align="left"><input name="roleName"  class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,50]']"></td>
						<td class="table_text" align="right">角色编码</td>
						<td align="left"><input name="roleCode" class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,20]','validCode']"></td>
						<td class="table_text" align="right">角色查询权限</td>
						<td align="left"><span class="comboSpan"/><select name="rolePrivilege" id="add_rolePrivilege" 
						class="table_select easyui-validatebox" data-options="required:true"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="addRole()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
					<a href="#" onclick="backListRolePage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
				</div>
			</div>
		</div>
	</form>
</div>


