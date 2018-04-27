<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/user/updateUserInit.js"></script>

<div id="tabdiv1">
	<form id="updateUserForm" method="post">
		<div id="updateUserInfoDiv" class="easyui-panel" fit="true" title="用户信息"
			iconCls="icon-ok" collapsible="true">
			<div class="top_table" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0">

					<tr>
						<td class="table_text" align="right">用户登陆账号</td>
						<td align="left"><input id="userCode" name="userCode" class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,20]','validCode']"></td>
						<td class="table_text" align="right">用户名称</td>
						<td align="left"><input id="userName" name="userName" class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,25]']" ></td>
						<td class="table_text" align="right">所属机构</td>
						<td align="left"><span class="comboSpan"/><select id="updateuser_comId" name="comId" class="table_input easyui-validatebox easyui-combobox1"
						data-options="required:true"></td>
					</tr>
					<tr>
						<!-- <td class="table_text" align="right" style="display:none;">所属网点</td>
						<td align="left" style="display:none;"><span class="comboSpan"/><select id="updateuser_storeId" name="storeId" class="table_input easyui-validatebox easyui-combobox1"></td> -->
						<td class="table_text" align="right">所属部门</td>
						<td align="left"><span class="comboSpan"/><select id="updateuser_departmentId" name="departmentId" class="table_input easyui-validatebox easyui-combobox1"></td>
						<td class="table_text" align="right">E-Mail</td>
						<td align="left"><input id="updateuser_email" name="email" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,50]','email']"></td>
						<td class="table_text" align="right">用户状态</td>
						<td align="left" ><span class="comboSpan"/><select id="updateuser_state" name="state" class="table_input easyui-validatebox easyui-combobox1"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">有效开始日期</td>
						<td align="left"><span class="comboSpan"/><input id="updateuser_validstartDate" name="validstartDate" class="table_input easyui-datebox easyui-validatebox"
						data-options="validType:['length[0,50]','validDate']"></td>
						<td class="table_text" align="right">有效结束日期</td>
						<td align="left"><span class="comboSpan"/><input id="updateuser_validendDate" name="validendDate" class="table_input easyui-datebox easyui-validatebox"
						data-options="validType:['length[0,50]','validDate']"></td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left" >&nbsp;</td>
					</tr>
				</table>
				<input type="hidden" name="userId" id="updateuser_userId"> 
				<div>
					<a href="#" onclick="updateUser()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>		
					<a href="#" onclick="backListUserPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
				</div>
			</div>
		</div>
	</form>
</div>
