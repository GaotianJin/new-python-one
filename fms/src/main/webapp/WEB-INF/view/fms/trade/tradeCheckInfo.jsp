<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/tradeCheckInfoInit.js"></script>
<form id="tradeInfoForm_check">
<div class="easyui-panel" title="基本信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易号码</td>
				<td>
				<input class="inpuntHidden" id="tradeId_Check_In" name="tradeInfoId" type="hidden">
				<input class="table_input" id="tradeNo_Check_In" name="tradeNo" readonly="readonly">
				</td>
				<td class="table_text" align="right">交易类型</td>
				<td align="left">
					<span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="tradeType_Check_In" name="tradeType" ></td>
				<!-- <td class="table_text" align="right">录入日期</td>
				<td><span class="comboSpan"></span> <input
					class="table_input easyui-datebox" id="tradeInputDate_Check_In" name="tradeInputDate" data-options="editable:false"></td> -->
			<td class="table_text" align="right">币种</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="currency_check" name="currency"></td>
			</tr>
			<tr>
				<td class="table_text" align="right">财富顾问</td>
				<td><span class="comboSpan"></span> 
					<input class="table_input  easyui-combobox1" id="agentID_Check_In" name="agentId"  data-options="editable:false">
				</td>
				<td class="table_text" align="right">所属机构</td>
				<td align="left">
					<span class="comboSpan"></span> 
					<input class="table_input  easyui-combobox1" id="companyID_Check_In" name="companyId" data-options="editable:false"></td>
				<!-- <td class="table_text" align="right" style="display:none;">所属网点</td>
				<td style="display:none;"><span class="comboSpan"></span> 
				    <input class="table_input easyui-combobox1" id="storeID_Check_In" name="storeId" data-options="editable:false"></td> -->
		    	<td class="table_text" align="right">交易机构</td>
				<td><span class="comboSpan"></span> 
					<input class="table_input easyui-combobox1" id="tradeComID_Check_In" name="tradeComId" ></td>
			</tr>
			<tr>
				<!-- <td class="table_text" align="right" style="display:none;">交易网点</td>
				<td align="left" style="display:none;"><span class="comboSpan"></span> <input
					class="table_input easyui-combobox1" id="tradeStoreID_Check_In" name="tradeStoreId" ></td> -->
				<td class="table_text" align="right">合同号</td>
				<td><input class="table_input easyui-validatebox" id="tradeInfoNo_Input" name="tradeInfoNo" data-options="validType:['length[0,40]','validCode']"></td>
				<!-- <td class="table_text" align="right">投保日/认购日</td>
				<td align="left"><span class="comboSpan"></span> <input
					class="table_input easyui-datebox" id="tradeDate_Input" name="tradeDate" data-options="required:true"></td> -->
				<!-- <td class="table_text" align="right">&nbsp;</td>
				<td>&nbsp;<input class="inpuntHidden" id="tradeId_Check_In" name="tradeInfoId" type="hidden"></td> -->
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
		<div style="margin-bottom: 3px;" >
			<a class="easyui-linkbutton e-cis_button" id="submitCheckTradeInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitCheckTradeInfo();">提交</a>
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
<div class="easyui-panel" title="客户信息" collapsible="true">
	<table id="customInfoCheckId" width="100%"></table>
	<div style="margin-bottom: 3px;" >
		<a class="easyui-linkbutton e-cis_button" id="submitCheckTradeCusInfoButton" 
			data-options="iconCls:'icon-tick'" onClick="submitCheckTradeCusInfo();">提交</a>
		<!-- <span id="custOperationPrompt" style="color: red;size:18">（新增或修改操作后请再次提交！）</span> -->
	</div>
</div>
<div id="roleInfoCheckPanelId">
	<div class="easyui-panel" title="角色信息" collapsible="true">
		<table id="roleInfoCheckId">
		</table>
		<div style="margin-bottom: 3px;" > 
			<a class="easyui-linkbutton e-cis_button" id="submitCheckTradeRoleButton" 
				data-options="iconCls:'icon-tick'" onclick="submitCheckTradeRole();">提交</a> 
		</div>
	</div>
</div>
<div id="bankInfoCheckPanelId">
	<div class="easyui-panel" title="交易账户信息" collapsible="true" >
		<table id="tradeBankInfoCheckId"></table>
		<div style="margin-bottom: 3px;" > 
			<a class="easyui-linkbutton e-cis_button" id="submitTradeCheckBankInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTradeBankInfoCheckId();">提交</a>
		</div>
	</div>
</div>
<div id="addressInfoCheckPanelId">
	<div class="easyui-panel" title="交易地址信息" collapsible="true" >
		<table id="tradeAddressInfoCheckId"></table>
		<div style="margin-bottom: 3px;" >
			<a class="easyui-linkbutton e-cis_button" id="submitTradeCheckAddressInfoButton"
				data-options="iconCls:'icon-tick'" onclick="submitTradeAddressInfoCheckId();">提交</a>
		</div>
	</div>
</div>
<div id="riskProCheckInfoPanelId">
<div class="easyui-panel" title="保险产品信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易总额（元）</td>
				<td>
					<input class="table_input" value="0" readonly id="riskTotalAssets_Check_In" name="riskTotalAssets">
				</td>
				<td class="table_text" align="right">交易总额大写</td>
				<td align="left">
					<input class="table_input" value="0" readonly id="chnRiskTotalAssets_Check_In" name="chnRiskTotalAssets">
				</td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
	</div>
	<table id="riskProCheckInfoId">
	</table>
	<div class="top_table" id="riskProCheckSelectId">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">产品</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="tradeRiskProId_Check_In" name="tradeRiskProId"></td>
				<td class="table_text" align="right">保障对象</td>
				<td><span class="comboSpan"></span>
					<input class="table_select easyui-combobox1" id="tradeRiskProtObj_Check_In" name="tradeRiskProtObj"></td>
				<td>
				</td>
				<td>
				</td>
			</tr>
		</table>
	</div>
	<table id="riskProInfoObjCheckId"></table>
	<div style="margin-bottom: 3px;">
		<a class="easyui-linkbutton e-cis_button" id="submitCheckRiskProButton"
			data-options="iconCls:'icon-tick'" onclick="submitCheckRiskPro();">提交</a>
		<a class="easyui-linkbutton e-cis_button" id="deleteRiskProButton"
			data-options="iconCls:'icon-cancel'" onclick="delRiskProInfo();">删除</a>
	</div>
</div>
</div>
<div id="monProInfoCheckPanelId">
<div class="easyui-panel" title="财富产品信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易总额（元）</td>
				<td>
					<input class="table_input" value="0" readonly id="monTotalAssets_Check_In" name="monTotalAssets">
				</td>
				<td class="table_text" align="right">交易总额大写</td>
				<td align="left">
					<input class="table_input" value="0" readonly id="chnMonTotalAssets_Check_In" name="chnMonTotalAssets">
				</td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
	</div>
	<table id="monProInfoCheckId">
	</table>
	<div class="top_table" id="check_monProInfoSelectDiv">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">产品</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="tradeWealthProId_Check_In" name="tradeWealthProId" style="width: 300px; height: 20px; line-height: 20px;"></td>
				<td class="table_text" align="right"></td>
				<td>
				</td>
				<td></td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="table_text" align="right">成立日期</td>
				<td>
					<input class="table_input" id="trade_Check_foundDate" name="foundDate" disabled="disabled">
				<td class="table_text" align="right">募集开始日期</td>
				<td>
					<input class="table_input" id="trade_Check_raiseStartDate" name="raiseStartDate" disabled="disabled">
				</td>
				<td class="table_text" align="right">募集结束日期</td>
				<td>
					<input class="table_input" id="trade_Check_raiseEndDate" name="raiseEndDate" disabled="disabled">
				</td>
			</tr>
		</table>
	</div>
	<table id="monProInfoObjCheckId"></table>
	<div style="margin-bottom: 3px;">
		<a class="easyui-linkbutton e-cis_button" id="submitCheckWealthProButton"
			data-options="iconCls:'icon-tick'" onclick="submitCheckWealthPro();">提交</a>
		<a class="easyui-linkbutton e-cis_button" id="deleteWealthProButton"
			data-options="iconCls:'icon-cancel'" onclick="delMonProInfo();">删除</a>
	</div>

</div>
</div>

<div class="easyui-panel" title="交易复核"
	collapsible="true" id="tradeCheckDiv"> 
<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="left">复核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="tradeCheckConclusion" name="tradeCheckConclusion"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
				<tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" id="tradeCheckRemark"></textarea></td>
				<td></td>
				<td></td>
				</tr>
		</table>
		<!-- <br> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'" 
			id="submitTradeCheckButton" onclick="submitTradeCheck();">提交</a> 
			<a class="easyui-linkbutton" id="uploadTradeCheckAttachInfoButton" onclick="uploadTradeCheckAttachInfo();" data-options="iconCls:'icon-redo'">上传附件</a> --> 
</div>
</div>

<div id="tradeCheckAuditConclusionDiv">
	<div class="easyui-panel" title="交易审核结论" collapsible="true" > 
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="left">审核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" disabled="disabled" id="tradeAuditConclusion_Check" name="tradeAuditConclusion"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" disabled="disabled" id="tradeAuditRemark_Check"></textarea></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</div>
</div>

<div style="margin-bottom: 10px;">
	<a class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'" 
		id="submitTradeCheckButton" onclick="submitTradeCheck();">提交审核</a> 
	<a class="easyui-linkbutton e-cis_button" id="uploadTradeCheckAttachInfoButton" 
		onclick="uploadTradeCheckAttachInfo();" data-options="iconCls:'icon-redo'">上传附件</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeCheckAttachInfoButton" 
		onclick="uploadTradeVideo();" data-options="iconCls:'icon-redo'">上传签约视频</a>
	<a class="easyui-linkbutton e-cis_button" id="tradeCheckBackButton" 
		onclick="tradeCheckBack();" data-options="iconCls:'icon-back'">返回</a> 
</div>