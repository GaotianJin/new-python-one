<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addProductAmountDisInit.js"></script>

<input type="hidden" name="operate" id="addPdAmountDis_operate" value="${operate}">
<input type="hidden" name="productId" id="addPdAmountDis_productId1" value="${productId}">
<input type="hidden" name="agencyComId" id="addPdAmountDis_agencyComId1" value="${agencyComId}">
<input type="hidden" name="expectOpenDay" id="addPdAmountDis_expectOpenDay1" value="${expectOpenDay}">
<input type="hidden" name="productType" id="addPdAmountDis_productType1" value="${productType}">
<input type="hidden" name="productSubType" id="addPdAmountDis_productSubType1" value="${productSubType}">
<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="产品选择" collapsible="true">
		<form id="addProductAmountDisInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="addPdAmountDis_agencyComId" class="table_input">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="addPdAmountDis_productId" class="table_input">
						</td>
						<td class="table_text" align="right">
							<span id="addPdAmountDis_expectOpenDayName" >期望开放日</span>
							 <span id="addPdAmountDis_foundDateName" hidden="true">成立日</span>
						</td>
						<td align="left">
							 <input name="expectOpenDay" id="addPdAmountDis_foundDate" class="table_input">
							 <span class="comboSpan"></span>
							<span id="addPdAmountDis_expectOpenDayText">
							<input name="expectOpenDay" id="addPdAmountDis_expectOpenDay" class="table_input">
							</span> 
						</td>
					</tr>
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">-->
				
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="companyAmountInfoTable"></table>
			<div id="companyAmountInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="companyAmountInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="companyAmountInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="companyAmountInfoTableLockOneRow()">锁定</a>
			</div>
		</div>
		<div class="top_table" id="pdAmountTotalInfo">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">产品融资规模（元）</td>
						<td align="left">
							<input name="financingScale" id="financingScale" class="table_input" disabled="disabled">
						</td>
						<td class="table_text" align="right">产品分配总额（元）</td>
						<td align="left">
							<input name="disTotalAmount" id="disTotalAmount" class="table_input" disabled="disabled">
						</td>
						<td class="table_text" align="right">剩余额度总和（元）</td>
						<td align="left">
							<input name="remainTotalAmount" id="remainTotalAmount" class="table_input" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">产品已预约总额（元）</td>
						<td align="left">
							<input name="orderTotalAmount" id="orderTotalAmount" class="table_input" disabled="disabled">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">
							&nbsp;
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">-->
				
			</div>
		<div>
			<a href="#" id="addPdAmountDis_submitButton" onclick="saveProductAmountDisInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
			<a href="#" onclick="backProductDisListPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div> 
	</div>
	
</div>