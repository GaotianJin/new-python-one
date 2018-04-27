<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/validate/easyUIvalidate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/order/listOrder.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="交易信息查询" collapsible="true">
		<form id="OrderForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">产品名称</td>
						<td><input class="table_input" id="o_productName" name="o_productName" placeholder="可模糊查询..."></td>
						<td class="table_text" align="right">销售策略</td>
						<td><input class="table_input" id="strategyName" name="strategyName" placeholder="可模糊查询..."></td>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left"><input class="table_input" id="o_chnName" name="o_chnName" placeholder="可模糊查询..."></td>
					</tr>
					<tr>
						<td class="table_text" align="right">所属理财师</td>
						<td align="left"><input class="table_input" id="agentName" name="agentName" placeholder="可模糊查询..."></td>
						<td class="table_text" align = "right">订单状态</td>
						<td align="left">&nbsp;&nbsp;&nbsp;<input name = "o_orderStatus" id="o_orderStatus" class="table_input"></td>
						<td class="table_text" align = "right">合同签署状态</td>
						<td align="left">&nbsp;&nbsp;&nbsp;<input name = "contactStatus" id="contactStatus" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">预约时间起期</td>
						<td align="left"><span class="comboSpan"></span><input name="beginPreorderCreatetime" id="beginPreorderCreatetime" class="easyui-datebox"></td>
						<td class="table_text" align = "right">预约时间止期</td>
						<td align="left"><span class="comboSpan"></span><input name="endPreorderCreatetime" id="endPreorderCreatetime" class="easyui-datebox"></td>				
					</tr>
					<tr>
						<td class="table_text" align = "right">预约确认时间起期</td>
						<td align="left"><span class="comboSpan"></span><input name="beginPreorderCompletetime" id="beginPreorderCompletetime" class="easyui-datebox"></td>
						<td class="table_text" align = "right">预约确认时间止期</td>
						<td align="left"><span class="comboSpan"></span><input name="endPreorderCompletetime" id="endPreorderCompletetime" class="easyui-datebox"></td>				
					</tr>
					<tr>
						<td class="table_text" align = "right">划款时间起期</td>
						<td align="left"><span class="comboSpan"></span><input name="beginPayTime" id="beginPayTime" class="easyui-datebox"></td>
						<td class="table_text" align = "right">划款时间止期</td>
						<td align="left"><span class="comboSpan"></span><input name="endPayTime" id="endPayTime" class="easyui-datebox"></td>				
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearOrderForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryOrderList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
	<div  class="easyui-panel" id="modifyorder" title="订单信息"  collapsible="true">
		<table id="OrderTable"></table>
	</div>
	<div class="tableOuterDiv"></div>
	<div id="modifyCustomerAccountDiv" class="easyui-panel" title="订单明细" collapsible="true">
				<table id="modifyCustomerAccountTable"></table>
	</div> 
	</div>
	
	<div id="OrderDlg" class="easyui-dialog"
		style="width:400px;height:250px;padding:20px 10px 0;" closed="true"
		buttons="#dlgbuttons">
		<form id="AddOrderForm" method="post">
			<table>
				<tr>
					<td>产品名称：</td>
					<td><input name = "productId" id="productId" class="table_input">
				</tr>
				<tr>
					<td>客户名称：</td>
					<td><input name = "olientId" id="olientId" class="table_input">
				</tr>
				<tr>
					<td>预约金额：</td>
					<td><input class="table_input easyui-validatebox" id="amount" name="amount"
						data-options="required:true,validType:'currency'"></td>
				</tr>
				<tr>
					<td>预计签约日：</td>
					<td><input class="easyui-dateTimebox" name="preorderCreatetime" id="preorderCreatetime" required="true"></td>
				</tr>
				<tr>
					<td>预约状态：</td>
					<td><input class="table_input" name="orderStatus" id="orderStatus" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlgbuttons">
		<a href="javascript:saveOrder()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeOrderDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</div>