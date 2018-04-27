<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/cooperation/detailAgencyComInit.js"></script>
<div id="tabdiv">
	<form id="detailAgencyComBasicInfo">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">基金管理人代码</td>
						<td align="left"><input name = "agencyCode" id="detailAgencyCode" class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,20]','validCode']" disabled="disabled"></td>
						<td class="table_text" align = "right">基金管理人名称</td>
						<td align="left"><input name = "agencyName" id="detailAgencyName" class="table_input easyui-validatebox"
						data-options="required:true,validType:['length[0,100]']" disabled="disabled"></td>
						<td class="table_text" align = "right">基金管理人类型</td>
						<td align="left"><span class="comboSpan"></span><select name="agencyType"  id = "detailAgencyType" class = "table_input easyui-combobox easyui-validatebox"
						data-options="required:true" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">合作状态</td>
						<td align="left"><span class="comboSpan"></span><select name="state"  id = "detailState" class = "table_input easyui-combobox easyui-validatebox"
						data-options="required:true" disabled="disabled"></td>
						<td class="table_text" align = "right">合作开始日期</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox easyui-validatebox" 
						name="startDate" id="detailStartDate" data-options="validType:['length[0,10]','validDate']" disabled="disabled"/></td></td>
						<td class="table_text" align = "right">合作结束日期</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox easyui-validatebox" 
						name="endDate" id="detailEndDate" data-options="validType:['length[0,10]','validDate']" disabled="disabled"/></td></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">组织机构代码</td>
						<td align="left" ><input name = "organizationCode" id="detailOrganizationCode" class="table_input easyui-validatebox"
						data-options="validType:['length[0,30]','validCode']" disabled="disabled"></td>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">省</td>
						<td align="left"><span class="comboSpan"></span><input name="province"  id = "detailProvince" class = "table_input easyui-combobox " disabled="disabled"></td>
						<td class="table_text" align = "right">市</td>
						<td align="left"><span class="comboSpan"></span><input name="city"  id = "detailCity" class = "table_input easyui-combobox " disabled="disabled"></td>
						<td class="table_text" align = "right">区/县</td>
						<td align="left"><span class="comboSpan"></span><input name="country"  id = "detailCountry" class = "table_input easyui-combobox " disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">详细地址</td>
						<td align="left" colspan="3"><input name="street" id="detailStreet" class="table_input1" width="600px" disabled="disabled"></td>
						<td class="table_text" align = "right">邮编</td>
						<td align="left"><input name = "zipCode" id="detailZipCode" class="table_input easyui-validatebox"
						data-options="validType:['validZip']" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">联系电话</td>
						<td align="left"><input name = "phone" id="detailPhone" class="table_input easyui-validatebox"
						data-options="validType:['length[0,20]','validTel']" disabled="disabled"></td>
						<td class="table_text" align = "right">传真</td>
						<td align="left"><input name = "fax" id="detailFax" class="table_input easyui-validatebox"
						data-options="validType:['length[0,20]','validTel']" disabled="disabled"></td>
						<td class="table_text" align = "right">Email</td>
						<td align="left"><input name = "email" id="detailEmail" class="table_input easyui-validatebox"
						data-options="validType:['email']" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">公司简介</td>
						<td align="left" rowspan="2" colspan="5"><textarea cols="150" id="profile" name="profile" class="textarea-box" disabled="disabled"></textarea></td>
					</tr>
					<tr>
						<td align="left" >&nbsp;</td>
						<td align="left" >&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">历史信息</td>
						<td align="left" rowspan="2" colspan="5"><textarea  cols="150" id="cooperationHistory" name="cooperationHistory" disabled="disabled" class="textarea-box"></textarea></td>
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
	<form id="detailAgencyComRelationInfo">
	<div id="relationInfo">
		<div class="easyui-panel" title="联系人信息" collapsible="true">
			<div class="top_table">
				<table id="detailAgencyComRelationTable"></table>
				<br>
			</div>
		</div>
	</div>
	</form>
	<div>
		<a href="#" onclick="backListAgencyComPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	</div>
</div>
