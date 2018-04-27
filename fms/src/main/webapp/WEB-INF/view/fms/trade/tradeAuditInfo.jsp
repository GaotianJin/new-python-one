<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/tradeAuditInfoInit.js"></script>
<form id="tradeInfoForm_Audit">
<div class="easyui-panel" title="基本信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易号码</td>
				<td>
				<input class="inpuntHidden" id="tradeId_Audit_In" name="tradeInfoId" type="hidden">
				<input class="table_input" id="tradeNo_Audit_In" name="tradeNo" disabled="disabled">
				</td>
				<td class="table_text" align="right">交易类型</td>
				<td align="left">
					<span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="tradeType_Audit_In" name="tradeType" data-options="required:true"></td>
				<!-- <td class="table_text" align="right">录入日期</td>
				<td><span class="comboSpan"></span> <input
					class="table_input easyui-datebox" id="tradeInputDate_Audit_In" name="tradeInputDate"></td> -->
				<td class="table_text" align="right">币种</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="currency_Audit" name="currency"></td>
			</tr>
			<tr>
				<td class="table_text" align="right">财富顾问</td>
				<td><span class="comboSpan"></span> 
					<input class="table_input  easyui-combobox1" id="agentID_Audit_In" name="agentId"  data-options="required:true,editable:false">
				</td>
				<td class="table_text" align="right">所属机构</td>
				<td align="left">
					<span class="comboSpan"></span> 
					<input class="table_input  easyui-combobox1" id="companyID_Audit_In" name="companyId" data-options="editable:false"></td>
				<!-- <td class="table_text" align="right" style="display:none;">所属网点</td>
				<td style="display:none;"><span class="comboSpan"></span> 
				    <input class="table_input easyui-combobox1" id="storeID_Audit_In" name="storeId" data-options="editable:false"></td> -->
			    <td class="table_text" align="right">交易机构</td>
				<td><span class="comboSpan"></span> 
					<input class="table_input easyui-combobox1" id="tradeComID_Audit_In" name="tradeComId" data-options="required:true"></td>
			</tr>
			<tr>
				<!-- <td class="table_text" align="right" style="display:none;">交易网点</td>
				<td align="left" style="display:none;"><span class="comboSpan"></span> 
				<input class="table_input easyui-combobox1" id="tradeStoreID_Audit_In" name="tradeStoreId" data-options="required:true"></td> -->
				<td class="table_text" align="right">合同号</td>
				<td><input class="table_input" id="tradeInfoNo_Input" name="tradeInfoNo" data-options="required:true"></td>
				<!-- <td class="table_text" align="right">投保日/认购日</td>
				<td align="left"><span class="comboSpan"></span> <input
					class="table_input easyui-datebox" id="tradeDate_Input" name="tradeDate" data-options="required:true"></td> -->
				<!-- <td class="table_text" align="right">&nbsp;</td>
				<td>&nbsp;<input class="table_input" id="tradeId_Audit_In" name="tradeInfoId" type="hidden"></td> -->
			   	<td>&nbsp;</td>
			   	<td>&nbsp;</td>
			   	<td>&nbsp;</td>
			   	<td>&nbsp;</td>
			</tr>
			<!-- <tr>
				
				<td class="table_text" align="right">&nbsp;</td>
				    <td align="left">&nbsp;</td>
				<td class="table_text" align="right">&nbsp;</td>
					<td align="left">&nbsp;</td>
			</tr> -->
		</table>
		<div>
			<a class="easyui-linkbutton" id="submitAuditTradeInfoButton" 
			onclick="submitAuditTradeInfo();" data-options="iconCls:'icon-ok'" >提交</a> 
		</div>
	</div>
</div>
<!-- <div class="easyui-panel" title="交易信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			
		</table>
		
	</div>
</div> -->
</form>
<div  class="tableOuterDiv">
	<div class="easyui-panel" title="客户信息" collapsible="true">
		<table id="customInfoAuditId" width="100%">
		</table>
		<div>
			<a class="easyui-linkbutton" id="submitAuditTradeCusInfoButton"
			 onclick="submitAuditTradeCusInfo();" data-options="iconCls:'icon-ok'">提交</a>
		</div> 
	</div>
</div>

<div id="roleInfoAuditPanelId"  class="tableOuterDiv">
	<div class="easyui-panel" title="角色信息" 
		collapsible="true">
		<table id="roleInfoAuditId">
		</table>
		<div>
			<a class="easyui-linkbutton" id="submitAuditTradeRoleButton" 
			onclick="submitAuditTradeRole();" data-options="iconCls:'icon-ok'" >提交</a> 
		</div>
	</div>
</div>
<div id="bankInfoAuditPanelId"  class="tableOuterDiv">
	<div class="easyui-panel" title="交易账户信息" 
		collapsible="true" >
		<table id="tradeBankInfoAuditId">
		</table>
	</div>
</div>
<div id="addressInfoAuditPanelId"  class="tableOuterDiv">
	<div class="easyui-panel" title="交易地址信息" 
		collapsible="true" >
		<table id="tradeAddressInfoAuditId">
		</table>
		 <a class="easyui-linkbutton" id="submitTradeAuditAddressInfoButton"
			data-options="iconCls:'icon-ok'" onclick="submitTradeAddressInfoAuditId();">提交</a> <br>
	</div>
</div>
<div id="riskProAuditInfoPanelId">
<div class="easyui-panel" title="保险产品信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易总额（元）</td>
				<td>
					<input class="table_input" value="0" readonly id="riskTotalAssets_Audit_In" name="riskTotalAssets">
				</td>
				<td class="table_text" align="right">交易总额大写</td>
				<td align="left">
					<input class="table_input" value="0" readonly id="chnRiskTotalAssets_Audit_In" name="chnRiskTotalAssets">
				</td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div>
		<table id="riskProAuditInfoId"></table>
	</div>
	
	<!-- <div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">产品</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" id="tradeRiskProId_Audit_In" name="tradeRiskProId"></td>
				<td class="table_text" align="right">保障对象</td>
				<td><span class="comboSpan"></span>
					<input class="table_select easyui-combobox" id="tradeRiskProtObj_Audit_In" name="tradeRiskProtObj"></td>
				<td>
				</td>
				<td>
				</td>
			</tr>
		</table>
	</div> -->
	<div class="tableOuterDiv">
		<table id="riskProInfoObjAuditId"> </table>
	</div>
	
	<!-- <table>
		<tr>
			<td>保障对象</td>
			<td></td>
			<td><input name="insuredObj" type="Auditbox"> 被保人1</td>
			<td><input name="insuredObj" type="Auditbox"> 被保人2</td>
			<td><input name="insuredObj" type="Auditbox"> 投保人</td>
		</tr>
	</table> -->
	<br> 
	<div>
		<a class="easyui-linkbutton" id="submitAuditRiskProButton" 
		onclick="submitAuditRiskPro();" data-options="iconCls:'icon-ok'">提交</a>
	</div>
	<br>
</div>
</div>
<div id="monProInfoAuditPanelId">
<div class="easyui-panel" title="财富产品信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易总额（元）</td>
				<td>
					<input class="table_input" value="0" readonly id="monTotalAssets_Audit_In" name="monTotalAssets">
				</td>
				<td class="table_text" align="right">交易总额大写</td>
				<td align="left">
					<input class="table_input" value="0" readonly id="chnMonTotalAssets_Audit_In" name="chnMonTotalAssets">
				</td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div class="top_table" id="audit_monProInfoSelectDiv">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">成立日期</td>
				<td>
					<input class="table_input" id="trade_Audit_foundDate" name="foundDate" disabled="disabled">
				<td class="table_text" align="right">募集开始日期</td>
				<td>
					<input class="table_input" id="trade_Audit_raiseStartDate" name="raiseStartDate" disabled="disabled">
				</td>
				<td class="table_text" align="right">募集结束日期</td>
				<td>
					<input class="table_input" id="trade_Audit_raiseEndDate" name="raiseEndDate" disabled="disabled">
				</td>
			</tr>
		</table>
	</div>
	<div class="tableOuterDiv">
		<table id="monProInfoAuditId"></table>
	</div>
	
	<!-- <div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">产品</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" id="tradeWealthProId_Audit_In" name="tradeWealthProId"></td>
				<td class="table_text" align="right"></td>
				<td>
				</td>
				<td></td>
				<td>
				</td>
			</tr>
		</table>
	</div> -->
	<div class="tableOuterDiv">
		<table id="monProInfoObjAuditId"> </table>
	</div>
	
	<a class="easyui-linkbutton" id="submitAuditWealthProButton" 
	onclick="submitAuditWealthPro();" data-options="iconCls:'icon-ok'">提交</a>
</div>
</div>

<div class="easyui-panel" title="交易复核"
	collapsible="true"> 
<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="left">复核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" id="tradeCheckConclusion_Audit" name="tradeCheckConclusion" data-options="required:true" disabled="disabled"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" id="tradeCheckRemark_Audit" disabled="disabled"></textarea></td>
				<td></td>
				<td></td>
			</tr>
		</table>
</div>
</div>
<div class="easyui-panel" title="交易审核"
	collapsible="true" id="tradeAuditDiv"> 
<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="left">审核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" id="tradeAuditConclusion" name="tradeAuditConclusion" data-options="required:true"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" id="tradeAuditRemark"></textarea></td>
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
		id="submitTradeCheckButton" onclick="submitTradeAudit();">提交</a> 
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAuditAttachInfoButton" 
		onclick="uploadTradeVideo();" data-options="iconCls:'icon-redo'">上传签约视频</a>
	<a class="easyui-linkbutton e-cis_button" id="uploadTradeAuditAttachInfoButton" 
		onclick="uploadTradeAuditAttachInfo();" data-options="iconCls:'icon-redo'">上传附件</a>
	<a class="easyui-linkbutton e-cis_button" id="tradeAuditBackButton" 
		onclick="tradeAuditBack();" data-options="iconCls:'icon-back'">返回</a> 
</div>

