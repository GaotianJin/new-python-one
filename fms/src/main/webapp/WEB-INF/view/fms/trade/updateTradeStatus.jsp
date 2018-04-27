<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/updateTradeStatusInit.js"></script>

<input type="hidden" id="operate" name="operate" value="${operate}"/>
<input type="hidden" id="tradeInfoId" name="tradeInfoId" value="${tradeInfoId}"/>
<input type="hidden" id="tradeType" name="tradeType" value="${tradeType}"/>
<input type="hidden" id="productSubType" name="productSubType" value="${productSubType}"/>
<input type="hidden" id="custBaseInfoId" name="custBaseInfoId" value="${custBaseInfoId}"/>
<input type="hidden" id="investCustomerType" name="investCustomerType" value="${investCustomerType}"/>
<div id="tabdiv">
	<div id="tradeDetailInfoDiv">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="交易详细信息" collapsible="true">
			<div  class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">基金管理人</td>
						<td align="left"><input name = "agencyComName" id="updTradeStatus_agencyComName" class="table_input" readonly="true"></td>
						<td class="table_text" align = "right">产品名称</td>
						<td align="left"><input name = "productName" id="updTradeStatus_productName" class="table_input" readonly="true"></td>
						<td class="table_text" align = "right">管理机构</td>
						<td align="left"><input name = "comName" id="updTradeStatus_comName" class="table_input" readonly="true"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">交易号码</td>
						<td align="left"><input name="tradeNo" id = "updTradeStatus_tradeNo" class = "table_input" readonly="true"></td>
						<td class="table_text" align = "right">合同号</td>
						<td align="left"><input name="tradeInfoNo" id = "updTradeStatus_tradeInfoNo" class = "table_input" readonly="true"></td>
						<td class="table_text" align = "right">投保人/认购人</td>
						<td align="left"><input name = "tradeAppant" id="updTradeStatus_tradeAppant" class="table_input" readonly="true"></td>
					</tr>
					<tr>
						<!-- <td class="table_text" align = "right">交易日期</td>
						<td align="left"><input name = "tradeDate" id="updTradeStatus_tradeDate" class="table_input" readonly="true"></td>
						<td class="table_text" align = "right">录入日期</td>
						<td align="left"><input name="tradeInputDate" id = "updTradeStatus_tradeInputDate" class = "table_input" readonly="true"></td> -->
						<td class="table_text" align = "right">财富顾问</td>
						<td align="left"><input name = "agentName" id="updTradeStatus_agentName" class="table_input" readonly="true"></td>
						
					
						<td class="table_text" align = "right">交易机构</td>
						<td align="left"><input name = "tradeComName" id="updTradeStatus_tradeComName" class="table_input" readonly="true"></td>
						<!-- <td class="table_text" align = "right" style="display:none;">交易网点</td>
						<td align="left" style="display:none;"><input name="tradeStoreName" id = "updTradeStatus_tradeStoreName" class = "table_input" readonly="true"></td> -->
						<td class="table_text" align = "right">交易总额</td>
						<td align="left"><input name = "tradeTotalAssets" id="updTradeStatus_tradeTotalAssets" class="table_input" readonly="true"></td>
						</tr>
						<tr>
					    <td class="table_text" align = "right">交易状态</td>
						<td align="left"><input name = "tradeStausName" id="updTradeStatus_tradeStausName" class="table_input" readonly="true"></td>
					
						<td class="table_text" align = "right">
							<span id="expectOpenDayName">期望开放日</span>
							<span id="foundDateName">成立日</span>
						</td>
						<td align="left"><input name = "expectOpenDay" id="updTradeStatus_expectOpenDay" class="table_input" readonly="true"></td>
						<td class="table_text" align = "right">交易净值</td>
						<td align="left"><input name = "netValue" id="updTradeStatus_netValue" class="table_input" readonly="true"></td>
					   
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	
	<div id="riskTradeStatus">
		<div id="smsaccordion" class="easyui-panel" title="保险交易状态维护" collapsible="true">
			<!-- <form id="TradeStausInsuranceForm">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align = "right">保单号</td>
							<td align="left">
								<input name = "policyNo" id="risk_policyNo" class="table_input">
								<input type="hidden" name = "tradeStatusInfoId" id="risk_tradeStatusInfoId" class="table_input">
							</td>
							<td class="table_text" align = "right">交易状态</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name = "tradeStatus" id="risk_tradeStatus" class="table_input easyui-combobox1">
							</td>
							<td class="table_text" align = "right">状态日期</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="statusDate" id = "risk_statusDate" class = "table_input easyui-datebox"></input>
							</td>
						</tr>
						<tr>
							<td class="table_text" align = "right">实际保费</td>
							<td align="left"><input name = "actuPremium" id="risk_actuPremium" class="table_input easyui-numberbox"></td>
							<td align="left"><input name = "tradeActuPrem" id="risk_tradeActuPrem" class="table_input  easyui-my97"></td>
							<td class="table_text" align = "right">&nbsp;</td>
							<td align="left">&nbsp;</td>
							<td class="table_text" align = "right">&nbsp;</td>
							<td align="left">&nbsp;</td>
						</tr>
					</table>
					<div class="tableOuterDiv"></div>
					<table id="riskTradeInfoTable"></table>
					<div style="margin-bottom: 3px;">
						<a href="#" onclick="submitTradeStatus()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
						<a href="#" onclick="backListTradeInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
					</div>
				</div>
			</form> -->
			
			
			<table id="riskTradeInfoTable"></table>
			<div style="margin-bottom: 3px;">
				<a href="#" onclick="submitTradeStatus()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
				<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
					onclick="uploadTradeAttachInfo();" data-options="iconCls:'icon-redo'">上传附件</a>
				<a href="#" onclick="backListTradeInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</div>
		</div>
	</div>
	<div id="wealthTradeStatus">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="财富交易状态维护" collapsible="true">
			<form id="TradeStausWealthForm">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align = "right">交易状态</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name = "tradeStatus" id="wealth_tradeStatus" class="able_input easyui-combobox1">
								<input type="hidden" name = "tradeStatusInfoId" id="wealth_tradeStatusInfoId" class="table_input">
							</td>
							<td class="table_text" align = "right">实际认购额</td>
							<td align="left"><input name = "actuSubscriptionAmount" id="wealth_actuSubscriptionAmount" class="table_input" onblur="getSubscriptionCopies(this);"></td>
							<td class="table_text" align = "right">认购份额</td>
							<td align="left"><input name = "subscriptionCopies" id="wealth_subscriptionCopies" class="table_input"></td>
						</tr>
					</table>
					<div style="margin-bottom: 3px;">
						<a href="#" onclick="submitTradeStatus()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
						<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
								onclick="uploadTradeAttachInfo();" data-options="iconCls:'icon-redo'">上传附件</a>
						<a href="#" onclick="backListTradeInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- <div id="tradeStausTab" class="easyui-tabs" fit="false" border="ture"  plain="true"></div> -->