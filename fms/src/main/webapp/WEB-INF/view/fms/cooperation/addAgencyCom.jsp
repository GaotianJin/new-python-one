<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/cooperation/addAgencyCom.js"></script>

<input type="hidden" name="operate" id="addAgency_operate" class="inpuntHidden" value="${operate}">
<div id="tabdiv">
<!-- 新增合作机构基本信息 -->
	<form id="addAgencyComBaiscInfo">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table" id="addAgencyComBaseDiv">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">基金管理人代码</td>
						
						<td align="left">
							<input type="hidden" name="agencyComId" id="addAgencyComId" class="inpuntHidden" value="${agencyComId}">
							<input name = "agencyCode" id="addAgencyCode" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,20]','validCode']">
						</td>
								
						<td class="table_text" align = "right">基金管理人名称</td>
						
						<td align="left"><input name = "agencyName" id="addAgencyName" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,100]']"></td>
						
						<td class="table_text" align = "right">基金管理人简称</td>
						
						<td align="left"><input name = "agencyShortName" id="addAgencyShortName" class="table_input" data-options="required:true,validType:['length[0,100]']"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">基金管理人类型</td>
						
						<td align="left">
							<span class="comboSpan"></span>
							<select name="agencyType"  id = "addAgencyType" class = "table_input easyui-combobox1"></select></td>
							
						<td class="table_text" align = "right">合作状态</td>
						
						<td align="left">
							<span class="comboSpan"></span>
							<select name="state"  id = "addState" class = "table_input easyui-combobox1"></select></td>
						
						<td class="table_text" align = "right">合作开始日期</td>
						
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox" name="startDate" id="addStartDate" 
								data-options="validType:['validDate']"/></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">合作结束日期</td>
						
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="endDate" id="addEndDate" 
								data-options="validType:['validDate']"/></td>
								
						<td class="table_text" align = "right">组织机构代码</td>
						
						<td align="left" ><input name = "organizationCode" id="addOrganizationCode" class="table_input easyui-validatebox"
						data-options="validType:['length[0,30]','validCode']"></td>
						
						<td align="left" >&nbsp;</td>
						
						<td align="left" >&nbsp;</td>
						
						<td align="left" >&nbsp;</td>
						
						<td align="left" >&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">省</td>
						<td align="left"><span class="comboSpan"></span>
							<input name="province"  id = "addProvince" class = "table_input easyui-combobox1"></td>
						<td class="table_text" align = "right">市</td>
						<td align="left"><span class="comboSpan"></span>
							<input name="city"  id = "addCity" class = "table_input easyui-combobox1"></td>
						<td class="table_text" align = "right">区/县</td>
						<td align="left"><span class="comboSpan"></span>
							<input name="country"  id = "addCountry" class = "table_input easyui-combobox1"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">详细地址</td>
						<td align="left" colspan="3"><input name="street" id="addStreet" class="table_input1"></td>
						<td class="table_text" align = "right">邮编</td>
						<td align="left"><input name = "zipCode" id="addZipCode" class="table_input easyui-validatebox"
						data-options="validType:['validZip']"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">联系电话</td>
						<td align="left"><input name = "phone" id="addPhone" class="table_input easyui-validatebox"
						data-options="validType:['length[0,20]','validTel']"></td>
						<td class="table_text" align = "right">传真</td>
						<td align="left"><input name = "fax" id="addFax" class="table_input easyui-validatebox"
						data-options="validType:['length[0,20]','validTel']"></td>
						<td class="table_text" align = "right">Email</td>
						<td align="left"><input name = "email" id="addEmail" class="table_input easyui-validatebox"
						data-options="validType:['email']"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">公司简介</td>
						<td align="left" colspan="3">
							<span class="comboSpan"></span>
							<textarea rows="2" cols="73" id="profile" name="profile" class="input_textarea"></textarea>
						</td>
						<td class="table_text" align = "right">&nbsp;</td>
						<td align="left" colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">历史信息</td>
						<td align="left" colspan="3">
							<span class="comboSpan"></span>
							<textarea rows="2" cols="73" id="cooperationHistory" name="cooperationHistory" class="input_textarea"></textarea>
						</td>
						<td class="table_text" align = "right">&nbsp;</td>
						<td align="left" colspan="3">
							&nbsp;
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<!-- 联系人信息可编辑表格 -->
	<form id="addAgencyComRelationInfo">
	<div id="relationInfo" class="tableOuterDiv">
		<div class="easyui-panel" title="联系人信息" collapsible="true">
			<div id="relationTable_tb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="addAgencyComRelationTableAddOneRow()">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="addAgencyComRelationTableRemoveOneRow()">删除</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="addAgencyComRelationTableLockOneRow()">锁定</a>
			</div>
			<div class="top_table">
				<table id="addAgencyComRelationTable"></table>
				<br>
			</div>
		</div>
	</div>
	</form>
	<!-- 提交按钮 -->
	<form id="">
		<div>
			<a href="#" id = "addAgency_submitButton" onclick="commitAgencyCom()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			<a href="#" onclick="backListAgencyComPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>
