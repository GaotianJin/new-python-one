<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/productOrderAuditInit.js"></script>

<div id="productOrderAuditLevel1" class="outerPanel">	
	<div id="productOrderAuditLevel21" class="easyui-panel" fit="true" title="预约审核信息查询" collapsible="true">
		<form id="PdAmountOrderAuditQueryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="pdAmountOrderAuditAgencyComId" class="table_input">
						</td>
						<td align="right" class="table_text">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="pdAmountOrderAuditProductId" class="table_input">
						</td>
						<td align="right" class="table_text">开放日</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="expectOpenStartDay" id="pdAmountOrderAuditExpectOpenStartDay" class="table_input2 easyui-datebox">
							至
							<input name="expectOpenEndDay" id="pdAmountOrderAuditExpectOpenEndDay" class="table_input2 easyui-datebox">
						</td>
					</tr>
					
					<tr>
						<td align="right" class="table_text">分公司</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="comId" id="pdAmountOrderAuditSubCompany" class="table_input easyui-combobox">
						</td>
						<td align="right" class="table_text">财富顾问</td>
						<td align="left">
						    <span class="comboSpan"></span>
							<input name="agentName" id="pdAmountOrderAuditAgentName" class="table_input">
						</td>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left">
							<input name="custName" id="pdAmountOrderAuditCustName" class="table_input easyui-validatebox">
						</td>
						
					</tr>
					
					<tr>
						<td align="right" class="table_text">预约状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="orderStatus" id="pdAmountOrderAuditOrderStatus" class="table_input easyui-combobox">
						</td>
						<td align="right" class="table_text">预约时间段</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="orderStartDate" id="pdAmountOrderAuditOrderStartDate" class="table_input2 easyui-datebox">
							至
							<input name="orderEndDate" id="pdAmountOrderAuditOrderEndDate" class="table_input2 easyui-datebox">
						</td>
						<td align="right" class="table_text">打款审核时间段</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="auditStartDate" id="pdAmountOrderAuditStartDate" class="table_input2 easyui-datebox">
							至
							<input name="auditEndDate" id="pdAmountOrderAuditEndDate" class="table_input2 easyui-datebox">
						</td>
					</tr>
				</table>

				<div>
					<a href="#" onclick="clearQueryPdAmountOrderAuditCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryProductdAmountOrderInfoList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="exportProductdAmountOrderInfoList()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">数据导出</a>
				</div> 
			</div>
		</form>
		<div id="productOrderAuditLevel22" style="margin-top: 3px;">
			<table id="custProductAmountOrderAuditInfoTable"></table>
			<div id="custProductAmountOrderAuditInfoTable_tb">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updatePdAmountOrderAudit()">预约修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-tick',plain:true" onclick="earnestAudit()">定金打款审核</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-tick',plain:true" onclick="auditPdAmountOrderAudit()">全款打款审核</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="amountPdAmountOrderAudit()">批量审核</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="cancelPdAmountOrderAudit()">撤销申请</a>
				&nbsp;&nbsp;&nbsp;&nbsp;<!-- <b>预约总额:</b><input class="table_input"  id="auditOrderTotal" name="orderTotal" readonly="true"> -->
				<b>预约总额：<span id="auditOrderTotal"></span></b>
			</div>
		</div>
	</div>
	
</div>