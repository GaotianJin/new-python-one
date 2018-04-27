<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/cooperation/updateAgencyComInit.js"></script>
<div id="tabdiv">
<!-- 修改合作机构基本信息 -->
	<form id="updateAgencyComBasicInfo">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">基金管理人代码</td>
						<td align="left"><input name = "agencyCode" id="modifyAgencyCode" class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,20]','validCode']"></td>
						<td class="table_text" align = "right">基金管理人名称</td>
						<td align="left"><input name = "agencyName" id="modifyAgencyName" class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,100]']"></td>
						<td class="table_text" align = "right">基金管理人类型</td>
						<td align="left"><span class="comboSpan"></span><select name="agencyType"  id = "modifyAgencyType" class = "table_input easyui-combobox easyui-validatebox"
						data-options="required:true"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">合作状态</td>
						<td align="left"><span class="comboSpan"></span><select name="state"  id = "modifyState" class = "table_input easyui-combobox easyui-validatebox"
						data-options="required:true"></td>
						<td class="table_text" align = "right">合作开始日期</td>
						<td align="left"><span class="comboSpan"/><input class="table_input easyui-datebox easyui-validatebox" 
						name="startDate" id="modifyStartDate" data-options="validType:['length[0,10]','validDate']"/></td></td>
						<td class="table_text" align = "right">合作结束日期</td>
						<td align="left"><span class="comboSpan"/><input class="table_input easyui-datebox easyui-validatebox" 
						name="endDate" id="modifyEndDate" data-options="validType:['length[0,10]','validDate']"/></td></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">组织机构代码</td>
						<td align="left" ><input name = "organizationCode" id="modifyOrganizationCode" class="table_input easyui-validatebox"
						data-options="validType:['length[0,30]','validCode']"></td>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">省</td>
						<td align="left"><span class="comboSpan"></span><input name="province"  id = "modifyProvince" class = "table_input easyui-combobox "></td>
						<td class="table_text" align = "right">市</td>
						<td align="left"><span class="comboSpan"></span><input name="city"  id = "modifyCity" class = "table_input easyui-combobox "></td>
						<td class="table_text" align = "right">区/县</td>
						<td align="left"><span class="comboSpan"></span><input name="country"  id = "modifyCountry" class = "table_input easyui-combobox "></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">详细地址</td>
						<td align="left" colspan="3"><input name="street" id="modifyStreet" class="table_input1" width="600px"></td>
						<td class="table_text" align = "right">邮编</td>
						<td align="left"><input name = "zipCode" id="modifyZipCode" class="table_input easyui-validatebox"
						data-options="validType:['validZip']"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">联系电话</td>
						<td align="left"><input name = "phone" id="modifyPhone" class="table_input easyui-validatebox"
						data-options="validType:['length[0,20]','validTel']"></td>
						<td class="table_text" align = "right">传真</td>
						<td align="left"><input name = "fax" id="modifyFax" class="table_input easyui-validatebox"
						data-options="validType:['length[0,20]','validTel']"></td>
						<td class="table_text" align = "right">Email</td>
						<td align="left"><input name = "email" id="modifyEmail" class="table_input easyui-validatebox"
						data-options="validType:['email']"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">公司简介</td>
						<td align="left" rowspan="2" colspan="5"><textarea cols="150" id="profile" name="profile" class="textarea-box"></textarea></td>
					</tr>
					<tr>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">历史信息</td>
						<td align="left" rowspan="2" colspan="5"><textarea  cols="150" id="cooperationHistory" name="cooperationHistory" class="textarea-box"></textarea></td>
					</tr>
					<tr>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<!-- 联系人信息可编辑表格 -->
	<form id="modifyAgencyComRelationInfo">
	<div id="relationInfo">
		<div class="easyui-panel" title="联系人信息" collapsible="true">
			<div id="relationTable_tb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="modifyAgencyComRelationTableAddOneRow()">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="modifyAgencyComRelationTableRemoveOneRow()">删除</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="modifyAgencyComRelationTableLockOneRow()">锁定</a>
			</div>
			<div class="top_table">
				<table id="modifyAgencyComRelationTable"></table>
				<br>
			</div>
		</div>
	</div>
	</form>
	<!-- 提交按钮 -->
	<form id="">
		<div>
			<a href="#" onclick="commitAgencyCom()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
			<a href="#" onclick="backListAgencyComPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>
