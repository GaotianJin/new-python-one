<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/productOrderQueryInit.js"></script>
<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="预约信息查询" collapsible="true">
		<form id="listPdAmountOrderQuery_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="pdAmountOrderQuery_agencyComId" class="table_input">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="pdAmountOrderQuery_productId" class="table_input">
						</td>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left">
							<input class="table_input easyui-validatebox" id="pdAmountOrderQuery_custName" name="custName">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">分公司</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="comId" id="pdAmountOrderQuery_comId" class="table_input">
						</td>
						<!-- <td class="table_text" align="right" style="display:none;">网点</td>
						<td align="left" style="display:none;">
							<span class="comboSpan"></span>
							<input name="storeId" id="pdAmountOrderQuery_storeId" class="table_input">
						</td> -->
						<td class="table_text" align="right">部门</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="departmentId" id="pdAmountOrderQuery_departmentId" class="table_input">
						</td>
					    <td class="table_text" align="right">财富顾问</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agentId" id="pdAmountOrderQuery_AgentId" class="table_input">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">预约时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="pdAmountOrderQuery_orderDate" name="orderDate">
						</td>
						<td class="table_text" align="right">预约状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="orderStatus" id="pdAmountOrderQuery_orderStatus" class="table_input">
						</td>
					    <td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">-->
				<div>
					<a href="#" onclick="clearQueryPdAmountOrderQueryCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryPdAmountOrderInfoList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="exportOrderData()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">数据导出</a>
				</div> 
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="custPdAmountOrderInfoTable"></table>
			<div id="custPdAmountOrderInfoTable_tb">
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="deletepdAmountOrderInfo()">撤销预约</a> -->
				&nbsp;&nbsp;&nbsp;&nbsp;<!-- <input class="table_input"  id="orderTotal" name="orderTotal" readonly="true"> -->
				<b>预约总额：<span id="orderTotal"></span></b>
			</div>
		</div>
	</div>
	
</div>