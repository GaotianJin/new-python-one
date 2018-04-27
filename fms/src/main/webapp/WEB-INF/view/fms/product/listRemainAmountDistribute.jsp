<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listRemainAmountDistributeInit.js"></script>
<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="队列预约信息查询" collapsible="true">
		<form id="listRemainAmountDistribute_Form">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="remainAmountDistribute_agencyComId" class="table_input">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="remainAmountDistribute_productId" class="table_input">
						</td>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left">
							<input class="table_input easyui-validatebox" id="remainAmountDistribute_custName" name="custName">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">分公司</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="comId" id="remainAmountDistribute_comId" class="table_input">
						</td>
					    <td class="table_text" align="right">财富顾问</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agentId" id="remainAmountDistribute_AgentId" class="table_input">
						</td>
						<td align="right" class="table_text">预约时间段</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="orderStartDate" id="remainAmountDistribute_OrderStartDate" class="table_input2 easyui-datebox">
							至
							<input name="orderEndDate" id="remainAmountDistribute_OrderEndDate" class="table_input2 easyui-datebox">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">预约状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="orderStatus" id="remainAmountDistribute_orderStatus" class="table_input">
						</td>
						<td class="table_text" align="right">是否已分配额度</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="queueIsDistribute" id="remainAmountDistribute_queueIsDistribute" class="table_input">
						</td>
					    <td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">-->
				<div>
					<a href="#" onclick="clearQueryRemainAmountDistribute()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryRemainAmountDistributeList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="exportRemainAmountData()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">数据导出</a>
				</div> 
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="remainAmountTable"></table>
			<div id="remainAmountTable_tb">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-tick',plain:true" onclick="remainAmountDistribute()">分配</a>
				&nbsp;&nbsp;&nbsp;&nbsp;<!-- <input class="table_input"  id="orderTotal" name="orderTotal" readonly="true"> -->
				<b>可预约总额：<span id="remainAmount_OrderTotal"></span></b>
			</div>
		</div>
	</div>
	
</div>