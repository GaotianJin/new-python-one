<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/transferAuditInputInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<input type="hidden" name="tradeFundsShareChangeId" id="transferAuditInput_tradeFundsShareChangeId" value="${tradeFundsShareChangeId}">
<input type="hidden" name="originTradeInfoId" id="transferAuditInput_originTradeInfoId" value="${originTradeInfoId}">
<input type="hidden" name="tradeInfoId" id="transferAuditInput_tradeInfoId" value="${tradeInfoId}">
<input type="hidden" name="productId" id="transferAuditInputproductId" value="${productId}">
<input type="hidden" name="tradeType" id="transferAuditInputtradeType" value="${tradeType}">
<input type="hidden" name="agentId" id="transferAuditInputagentId" value="${agentId}">
<form id="transferAuditInputForm">
	<div class="easyui-panel" title="基金份额转让审核" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="input_table">
					<tr>
						<td class="table_text" align="right">交易号码</td>
						<td align="left">
						<input class="table_input" id="transferAuditInput_tradeNo" name="tradeNo" value="${tradeNo}"  disabled>
						</td>
						<td class="table_text" align="right">交易类型</td>
						<td align="left"><span class="combospan"></span><input id="transferAuditInput_tradeType" class="table_input easyui-combobox" name="tradeType" value="${tradeType}"></td>
						<td class="table_text" align="right">录入日期</td>
						<td align="left"><span class="combospan"></span><input id="transferAuditInput_tradeinputDate" class="table_input easyui-datebox" name="tradeInputDate" value="${tradeInputDate}"  disabled></td>
					</tr>
					<tr>
					<td class="table_text" align="right">财富顾问</td>
					<td align="left"><span class="combospan"></span><input id="transferAuditInput_agentId" class="table_input easyui-combobox" name="agentId" value="${agentId}"></td>
					<td class="table_text" align="right">所属机构</td>
					<td align="left"><span class="combospan"></span><input id="transferAuditInput_companyId" class="table_input easyui-combobox" name="companyId" value="${companyId}"></td>
					<td class="table_text" align="right">交易机构</td>
					<td align="left"><span class="combospan"></span><input id="transferAuditInput_tradeComId" class="table_input easyui-combobox" name="tradeComId" value="${tradeComId}"></td>
					</tr>
					<tr>
					<td class="table_text" align="right">原财富顾问</td>
					<td align="left"><span class="combospan"></span><input id="transferAuditInput_originAgentId" class="table_input easyui-combobox" name="originAgentId" value="${originAgentId}"></td>
					<td class="table_text" align="right">合同号</td>
						<td align="left"><input class="table_input"
							id="transferAuditInput_tradeInfoNo" name="tradeInfoNo" value="${tradeInfoNo}"  disabled></td>
						<td class="table_text" align="right">认购日/投保日</td>
						<td align="left"><span class="combospan"></span><input id="transferAuditInput_tradeDate" class="table_input easyui-datebox" name="tradeDate" value="${tradeDate}"   disabled></td>	
					</tr>
					<tr>
					<td class="table_text" align="right">币种</td>
					<td align="left"><span class="combospan"></span><input id="transferAuditInput_currency" class="table_input easyui-combobox" name="currency" value="${currency}"></td>
					<td class="table_text" align="right">&nbsp;</td>
					<td align="left">&nbsp;</td>
					<td class="table_text" align="right">&nbsp;</td>
					<td align="left">&nbsp;</td>
					</tr>
				</table>
				<div style="margin-bottom: 3px;" >
			     <a class="easyui-linkbutton e-cis_button" id="submitTransferAuditInputButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTransferAuditInput();">提交</a>
		        </div>
			</div>
			</div>
		</form>
<div id="transferAuditInput_custInfoInputPanelId">
<div class="easyui-panel" title="客户信息" collapsible="true">
	<table id="transferAuditInput_custInfoId" width="100%"> </table>
	<div style="margin-bottom: 3px;" >
		<a class="easyui-linkbutton e-cis_button" id="submitTransferAuditInputCustInfoButton" 
			data-options="iconCls:'icon-tick'" onClick="submitTransferAuditInputCustInfo();">提交</a>
		<span id="custOperationPrompt" style="color: red;size:18">（新增或修改操作后请再次提交！）</span>
	</div>
</div>
</div> 
<div id="transferAuditInput_bankInfoInputPanelId">
	<div class="easyui-panel" title="交易账户信息" collapsible="true" >
		<table id="transferAuditInput_tradeBankInfoId"> </table>
		<div style="margin-bottom: 3px;" > 
			<a class="easyui-linkbutton e-cis_button" id="submitTransferAuditInputBankInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTransferAuditInputBankInfo();">提交</a>
		</div>
	</div>
</div>
<div id="transferAuditInput_addressInfoInputPanelId">
	<div class="easyui-panel" title="交易地址信息" collapsible="true" >
		<table id="transferAuditInput_tradeAddressInfoId"></table>
		<div style="margin-bottom: 3px;" >
			<a class="easyui-linkbutton e-cis_button" id="submitTransferAuditInputAddressInfoButton"
				data-options="iconCls:'icon-tick'" onclick="submitTransferAuditInputAddressInfo();">提交</a>
		</div>
	</div>
</div>
<div class="easyui-panel" title="交易产品信息" collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易总额（元）</td>
				<td>
					<input class="table_input" value="0" readonly id="monTotalAssets_Input" name="tradeTotalAsset" >
				</td>
				<td class="table_text" align="right">交易总额大写</td>
				<td align="left">
					<input class="table_input" value="0" readonly id="chnMonTotalAssets_Input" name="chnMonTotalAssets" >
				</td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
	</div>
	
	<table id="transferAuditProInfoTable"> </table>
	
	<div class="top_table" id="transferAuditProInfoDiv">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<!-- <tr>
				<td class="table_text" align="right">产品</td>
				<td>
				<span class="combospan"></span>
					<input class="table_input easyui-combobox" id="transferAuditInput_productId" name="tradeWealthProId" style="width: 300px; height: 20px; line-height: 20px;" disabled="disabled">
					</td>
				<td class="table_text" align="right">&nbsp;</td>
				<td>&nbsp;</td>
				<td class="table_text" align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>  -->
			<tr>
				<td class="table_text" align="right">成立日期</td>
				<td>
					<input class="table_input" id="trade_Input_foundDate" name="foundDate" disabled="disabled">
				<td class="table_text" align="right">募集开始日期</td>
				<td>
					<input class="table_input" id="trade_Input_raiseStartDate" name="raiseStartDate" disabled="disabled">
				</td>
				<td class="table_text" align="right">募集结束日期</td>
				<td>
					<input class="table_input" id="trade_Input_raiseEndDate" name="raiseEndDate" disabled="disabled">
				</td>
			</tr>
		</table>
	</div>
	<div class="tableOuterDiv">
		<table id="transferAuditProInfoInputTable"></table>
	</div>
	<div>
	<div class="easyui-panel" title="转让审核"
	collapsible="true" id="tradeAuditDiv"> 
<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="left">审核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" id="transferAuditConclusion" name="transferAuditConclusion" data-options="required:true"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" id="transferAuditRemark"></textarea></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		 <!-- <a class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'" 
			id="submitTradeAuditButton" onclick="submitTradeAudit();">提交</a> 
			<a class="easyui-linkbutton" id="uploadTradeAuditAttachInfoButton" onclick="uploadTradeAuditAttachInfo();" data-options="iconCls:'icon-redo'">查看附件</a> 
			<br> -->
</div>
</div>
<div style="margin-bottom: 10px;">
	<a class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'" 
		id="submitTransferAuditInputButton" onclick="submitTransferAuditInput();">提交</a> 
	<a class="easyui-linkbutton e-cis_button" id="uploadTransferAuditInputButton" 
		onclick="uploadTransferAuditInput();" data-options="iconCls:'icon-redo'">上传附件</a>
	<a class="easyui-linkbutton e-cis_button" id="transferAuditInputBackButton" 
		onclick="transferAuditInputBack();" data-options="iconCls:'icon-back'">返回</a> 
</div>
</div>
</div>



