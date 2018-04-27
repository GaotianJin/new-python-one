<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/listTradeFundsInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="基金份额转让" collapsible="true">
		<form id="TradeFundsForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">认购人</td>
						<td><input class="table_input" id="tradeFunds_custName" name="custName"></td>
						<td class="table_text" align="right">交易号码</td>
						<td><input class="table_input" id="tradeFunds_tradeInfoNo" name="tradeInfoNo"></td>
						<td class="table_text" align="right">产品名称</td>
						<td><input class="table_input easyui-combobox" id="tradeFunds_productName" name="productName"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearTradeFunds()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryTradeFundsList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
	<div  class="easyui-panel"  style="margin-top: 3px;">
		<table id="tradeFundsTable"></table>
	</div>
	</div>
</div>