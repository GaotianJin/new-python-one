<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/preCustomer/updatePreCustomerInit.js"></script>
<input type="hidden" name="preCustBaseInfoIdName" id="updatePreCustBaseInfoId" value="${modifyPreCustBaseInfoId}">
<div id="tabdiv1" class="outerPanel">
	<form id="update_PreCustomerForm">
		<!-- 基本信息 -->
		<div id="update_PreCustomer" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">中文姓名</td>
						<td align="left"><input name="preCustName" id="update_ChinaName" class="table_input easyui-validatebox" ></td>
						<td class="table_text" align="right">手机号</td>
						<td align="left"><input name="preCustMobile" id="update_MobilePhoneId" class="table_input easyui-validatebox" data-options="validType:['length[0,20]','validPhone']"></td>
						<td class="table_text" align="right">性别</td>
						<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox1" id="update_sexual" name="preCustSex"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">客户类型</td>
						<td align="left"><span class="comboSpan"></span>
							<input class="table_input easyui-combobox1 " name="preCustType" id="update_CustomerType"/></td>
						<td class="table_text" align="right"><!-- 客户居住楼盘 --></td>
						<td align="left"><span class="comboSpan"></span>
							<!-- <input class="table_input easyui-combobox1 " name="preCustResidentialBuilding" id="update_CustomerProperty" /> --></td>
						<td class="table_text" align="right"><!-- 楼号 --></td>
						<td align="left"> <!-- <input class="table_input easyui-validatebox" id="update_floorId" name="preCustBuildingNo"> --></td>
					</tr>
					<tr>
						<td class="table_text" align="right">获客方式</td>
						<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox1" id="update_getCustomerType" name="preCustObtainWay"></td>
					</tr>
				</table>
			</div>
		</div>
		<br>
	</form>
	<div id="visitCustomerInfo">
		<div class="easyui-panel" title="拜访信息" collapsible="true">
			<div id="visitCustomerTable_tb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true"
				onclick="preCustomervistorTableAddOneRow()">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true"
				onclick="preCustomervistorTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true"
				onclick="preCustomervistorTableLockOneRow()">锁定</a>
				
			</div>
			<div class="top_table">
				<table id="preCustomervistorTable"></table>
			</div>
		</div>
		<br>
	</div>

	<div>
		<a href="#" onclick="submitPreCusomerInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a> 
			<a href="#" onclick="backPreCustomerBasicInfoPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	</div>
</div>

