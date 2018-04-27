<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/preCustomer/showPreCustomerBasicInfoInit.js"></script>

<!-- 隐藏标签用于接收客户端传入参数 -->
<input type="hidden" name="preCustBaseInfoIdhidden" id="detailPreCustBaseInfoId" value="${preCustBaseInfoId}">
<input type="hidden" name="preCustNamehidden" id="detailPreCusName" value="${preCustName}">
<input type="hidden" name="preCustMobilehidden" id="detailPreCustMobile" value="${preCustMobile}">
<input type="hidden" name="preCustBuildingNohidden" id="detailPreCustBuildingNo" value="${preCustBuildingNo}">
<div id="tabdiv1">
	<form id="detail_PreCustomerForm">
		<!-- 基本信息 -->
		<div id="detail_PreCustomer" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">中文姓名</td>
						<td align="left"><input name="preCustName" id="detail_ChinaName" class="table_input easyui-validatebox" data-options="required:true"></td>
						<td class="table_text" align="right">手机号</td>
						<td align="left"><input name="preCustMobile" id="detail_MobilePhoneId" class="table_input easyui-validatebox"></td>
						<td class="table_text" align="right">性别</td>
						<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox1" id="detail_sexual" name="preCustSex"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">客户类型</td>
						<td align="left"><span class="comboSpan"></span>
							<input class="table_input easyui-combobox1" name="preCustType" id="detail_CustomerType"/></td>
						<!-- <td class="table_text" align="right">客户居住楼盘</td>
						<td align="left"><span class="comboSpan"></span>
							<input class="table_input easyui-combobox1" name="preCustResidentialBuilding" id="detail_CustomerProperty" /></td>
						<td class="table_text" align="right">楼号</td>
						<td align="left"> <input class="table_input easyui-validatebox" id="detail_floorId" name="preCustBuildingNo"></td> -->
					<!-- </tr>
					<tr> -->
						<td class="table_text" align="left">获客方式</td>
						<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="detail_getCustomerType" name="preCustObtainWay"></td>
					</tr>
				</table>
			</div>
		</div>
		<br>
	</form>
	<div id="visitCustomerInfo">
		<div class="easyui-panel" title="拜访信息" collapsible="true">
			<div class="top_table">
				<table id="preCustomervistorTable"></table>
			</div>
		</div>
		<br>
	</div>
	<!-- 保存与返回 -->
	<div>
		<a href="#" onclick="backListPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>	
	</div>

</div>