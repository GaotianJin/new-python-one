<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/addCompanyRoster.js"></script>

<input type="hidden" name="operate" id="addCompanyRoster_operate" class="inpuntHidden" value="${operate}">
<div id="tabdiv">
<!-- 新增合作机构基本信息 -->
	<form id="addCompanyRosterInfo">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table" id="addCompanyPolicyDiv">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">姓名</td>
						
						<td align="left">
							<input type="hidden" name="companyRosterInfoId" id="addCompanyRoster_companyRosterInfoId" class="inpuntHidden" value="${companyRosterInfoId}">
							<input name = "chnName" id="addCompanyRoster_chnName" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,50]']">
						</td>
								
						<td class="table_text" align = "right">固定电话</td>
						
						<td align="left"><input name = "mobile" id="addCompanyRoster_mobile" class="table_input easyui-validatebox" data-options="validType:['length[0,20]','validTel']"/></td>
						
						<td class="table_text" align = "right">手机</td>
						
						<td align="left"><input name = "telephone" id="addCompanyRoster_telephone" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,20]','validPhone']"/></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">E-Mail</td>
						<td align="left">
								<input class = "table_input easyui-validatebox" name="email"  id = "addCompanyRoster_email" 
									data-options="validType:['length[0,50]','email']"/></td>
							
						<td class="table_text" align = "right">传真</td>
						
						<td align="left"><input name = "fax" id="addCompanyRoster_fax" class="table_input easyui-validatebox"
						data-options="validType:['length[0,20]','validTel']"></td>
						
						<td class="table_text" align = "right">所属分公司</td>
						
						<td align="left">
						<span class="combospan"></span>
							<input class="table_input easyui-combobox" name="filiale" id="addCompanyRoster_filiale"/></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">所属部门</td>
						
						<td align="left">
						<span class="combospan"></span>
							<input class="table_input easyui-combobox" name="department" id="addCompanyRoster_department" data-options="required:true"/></td>
								
						<td class="table_text" align = "right">岗位</td>
						
						<td align="left" >
						<span class="combospan"></span>
						<input name = "post" id="addCompanyRoster_post" class="table_input easyui-combobox"/></td>
						
						<td align="left" >&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div>
			<a href="#" id = "addCompanyRoster_submitButton" onclick="commitCompanyRoster()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			<a href="#" onclick="backListCompanyRoster()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
</div>
