<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/updateTradeFundsInit.js"></script>

<input type="hidden" id="tradeInfoId" name="tradeInfoId" value="${tradeInfoId}"/>
<input type="hidden" id="tradeType" name="tradeType" value="${tradeType}"/>
<input type="hidden" id="productSubType" name="productSubType" value="${productSubType}"/>
<div id="tabdiv">
	<div id="tradeDetailInfoDiv">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="交易信息" collapsible="true">
			<div  class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">产品名称</td>
						<td align="left"><input name = "productName" id="updTradeFunds_productName" class="table_input" readonly="true"></td>
						<td class="table_text" align = "right">交易号码</td>
						<td align="left"><input name="tradeNo" id = "updTradeFunds_tradeNo" class = "table_input" readonly="true"></td>
						<td class="table_text" align = "right">认购人</td>
						<td align="left"><input name = "custName" id="updTradeFunds_custName" class="table_input" readonly="true"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">交易日期</td>
						<td align="left"><input name = "tradeDate" id="updTradeFunds_tradeDate" class="table_input" readonly="true"></td>
						<td class="table_text" align = "right">财富顾问</td>
						<td align="left"><input name = "agentName" id="updTradeFunds_agentName" class="table_input" readonly="true"></td>
						<td class="table_text" align = "right">交易总额</td>
						<td align="left"><input name = "tradeTotalAsset" id="updTradeFunds_tradeTotalAsset" class="table_input" readonly="true"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<div id="fundsShareChangeDiv">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="新增基金份额转让" collapsible="true">
			<form id="FundsShareChangeForm">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align = "right">转让类型</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name = "transferAll" id="fundsShare_transferAll" class="table_input easyui-combobox1">
							</td>
							<td class="table_text" align = "right">转让金额</td>
							<td align="left"><input name = "transferAsset" id="fundsShare_transferAsset" class="table_input"></td>
							
						</tr>
						<tr>
						<td class="table_text" align = "right">是否指定财富顾问</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name = "isOrder" id="fundsShare_isOrder" class="table_input easyui-combobox1">
							</td>
						<td class="table_text" align = "right" >财富顾问</td>
						<td align="left">
								<span class="comboSpan"></span>
								<input name = "agentId" id="fundsShare_agentId" class="table_input easyui-combobox1">
							</td>	
						<td></td>
						</tr>
					</table>
					<div style="margin-bottom: 3px;">
						<a href="#" onclick="submitTradeFunds()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
						<a href="#" onclick="backListTradeInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

