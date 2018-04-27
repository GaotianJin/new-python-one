<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/tradeInputInfoInit.js"></script>

<div class="easyui-panel" title="基本信息" collapsible="true">
	<form id="tradeInfoForm_input">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易号码</td>
				<td>
				    <input class="inpuntHidden" id="tradeId_Input" name="tradeInfoId" type="hidden" value="${tradeInfoId}"/>
				    <input class="inpuntHidden" id="productId_Input" name="productId" type="hidden" value=""/>
				    <input class="inpuntHidden" id="contractNum_Input" name="contractNum" type="hidden" value="${contractNum}"/>
				    <input class="table_input" id="tradeNo_Input" name="tradeNo" readonly="readonly" >
				</td>
				<td class="table_text" align="right">交易类型</td>
				<td align="left">
				    <span class="comboSpan"></span>
				    <input class="table_input easyui-combobox1" id="tradeType_Input" name="tradeType" data-options="required:true">
				</td>
				<td class="table_text" align="right">交易机构</td>
				<td>
					<span class="comboSpan"></span>
					<input class="table_input  easyui-combobox1" id="tradeComID_Input" name="tradeComId" data-options="required:true">
				</td>
				<!-- <td class="table_text" align="right">录入日期</td>
				<td><span class="comboSpan"></span> 
					<input class="table_input easyui-datebox1" id="tradeInputDate_Input" name="tradeInputDate">
				</td> -->
			</tr>
			<tr>
				<td class="table_text" align="right">财富顾问</td>
				<td><span class="comboSpan"></span> 
					<input class="table_input easyui-combobox1" id="agentID_Input" name="agentId"  data-options="editable:false"></input>
				</td>
				<td class="table_text" align="right">所属机构</td>
				<td align="left"><span class="comboSpan"></span> 
					<input class="table_input easyui-combobox1" id="companyID_Input" name="companyId" data-options="editable:false">
				</td>
				<!-- <td class="table_text" align="right" style="display:none;">所属网点</td>
				<td style="display:none;"><span class="comboSpan"></span> 
					<input class="table_input easyui-combobox1" id="storeID_Input" name="storeId" data-options="editable:false">
				</td> -->
			    <td class="table_text" align="right">币种</td>
				<td><span class="comboSpan"></span>
			   <input class="table_input  easyui-combobox1" id="currency_Input" name="currency"></td>
			</tr>
			<tr>
				<!-- <td class="table_text" align="right" style="display:none;">交易网点</td>
				<td align="left" style="display:none;">
					<span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="tradeStoreID_Input" name="tradeStoreId">
				</td> -->
				<td class="table_text" align="right">合同号</td>
				<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="tradeInfoNo_Input" name="tradeInfoNo" data-options="required:true"></td>
				 <!-- <td class="table_text" align="right">认购日/投保日</td>
				<td align="left"><span class="comboSpan"></span> <input
					class="table_input easyui-datebox" id="tradeDate_Input" name="tradeDate" data-options="required:true"></td> -->
				<!-- <td class="table_text" align="right">&nbsp;</td> -->
				<!-- <td>&nbsp;<input class="inpuntHidden" id="tradeId_Input" name="tradeInfoId" type="hidden" value=""/></td> -->
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
			<a class="easyui-linkbutton e-cis_button" id="submitTradeInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTradeInfo();">提交</a>
		</div>
	</div>
	</form>
</div>
<!-- <div class="easyui-panel" title="交易信息" 
	collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			
		</table>
		<br> <a class="easyui-linkbutton" id="submitTradeInfoButton"
			data-options="iconCls:'icon-ok'" onclick="submitTradeInfo();">提交</a> <br>
	</div>
</div> -->
<div class="easyui-panel" title="客户信息" collapsible="true">
<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">选择客户</td>
				<td><input class="table_input easyui-combobox1" id="chooseCust_input" name="custlist" ></td>
				<td class="table_text" align="right"></td>
				<td></td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
</div>
	<table id="customInfoInputId" width="100%"> </table>
	<div style="margin-bottom: 3px;" class="wealthHidden" hidden><!-- class为wealthHidden的提交按钮财富产品都不显示 -->
		<a class="easyui-linkbutton e-cis_button" id="submitTradeCusInfoButton" 
			data-options="iconCls:'icon-tick'" onClick="submitTradeCusInfo();">提交</a>
		<span id="custOperationPrompt" style="color: red;size:18">（新增或修改操作后请再次提交！）</span>
	</div>
</div>
<div id="roleInfoInputPanelId">
	<div class="easyui-panel" title="角色信息" collapsible="true" >
		<table id="roleInfoInputId"> </table>
		<div style="margin-bottom: 3px;" class="wealthHidden" hidden> 
			<a class="easyui-linkbutton e-cis_button" id="submitTradeRoleButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTradeRole();">提交</a> 
		</div>
	</div>
</div>
<div id="bankInfoInputPanelId">
	<div class="easyui-panel" title="交易账户信息" collapsible="true" >
		<table id="tradeBankInfoInputId"> </table>
		<div style="margin-bottom: 3px;" class="wealthHidden" hidden> 
			<a class="easyui-linkbutton e-cis_button" id="submitTradeInputBankInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTradeBankInfoInputId();">提交</a>
		</div>
	</div>
</div>
<div id="addressInfoInputPanelId">
	<div class="easyui-panel" title="交易地址信息" collapsible="true" >
		<table id="tradeAddressInfoInputId"></table>
		<div style="margin-bottom: 3px;" >
			<span class="wealthHidden" hidden><a class="easyui-linkbutton e-cis_button" id="submitTradeInputAddressInfoButton"
				data-options="iconCls:'icon-tick'" onclick="submitTradeAddressInfoInputId();" >提交</a></span>
			<a class="easyui-linkbutton e-cis_button" id="submitTradeCustomerInfoButton"
				data-options="iconCls:'icon-tick'" onclick="submitTradeCustomerInfo();">提交</a>
		</div>
	</div>
</div>
<div id="riskProInputInfoPanelId">
<div class="easyui-panel" title="保险产品信息" collapsible="true" >
	<div class="top_table" id="riskProTradeTotalDiv">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易总额（元）</td>
				<td><input class="table_input" value="0" readonly id="riskTotalAssets_Input" name="riskTotalAssets" ></td>
				<td class="table_text" align="right">交易总额大写</td>
				<td align="left">
					<input class="table_input" value="0" readonly id="chnRiskTotalAssets_Input" name="chnRiskTotalAssets" >
				</td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
	</div>
	
		<table id="riskProInputInfoId"> </table>
	
	<div class="top_table" id="riskProInputSelectId">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">产品</td>
				<td>
					<span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" data-options="required:true" id="tradeRiskProId" name="tradeRiskProId">
				</td>
				<td class="table_text" align="right">保障对象</td>
				<td>
					<span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" data-options="required:true" id="tradeRiskProtObj" name="tradeRiskProtObj">
				</td>
				<td class="table_text" align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<div class="tableOuterDiv">
		<table id="riskProInfoObjInputId"></table>
	</div>
	<div style="margin-bottom: 3px;">
		<a class="easyui-linkbutton e-cis_button" id="submitRiskProButton"
			data-options="iconCls:'icon-tick'" onclick="submitRiskPro();">提交</a>
		<a class="easyui-linkbutton e-cis_button" id="deleteRiskProButton"
			data-options="iconCls:'icon-cancel'" onclick="delRiskProInfo();">删除</a>
	</div>
</div>
</div>
<div id="monProInfoInputPanelId">

<div class="easyui-panel" title="财富产品信息" collapsible="true">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易总额（元）</td>
				<td>
					<input class="table_input" value="0" readonly id="monTotalAssets_Input" name="monTotalAssets" >
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
	
		<table id="monProInfoInputId"> </table>
	
	
	<div class="top_table" id="monProInfoSelectDiv">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">产品</td>
				<td>
					<span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="tradeWealthProId" name="tradeWealthProId" style="width: 300px; height: 20px; line-height: 20px;"></td>
				<td class="table_text" align="right">&nbsp;</td>
				<td>&nbsp;</td>
				<td class="table_text" align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
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
		<table id="monProInfoObjInputId"></table>
	</div>
	<div style="margin-bottom: 3px;">
		<a class="easyui-linkbutton e-cis_button" id="submitWealthProButton"
			data-options="iconCls:'icon-tick'" onclick="submitWealthPro();">提交</a>
		<a class="easyui-linkbutton e-cis_button" id="deleteWealthProButton"
			data-options="iconCls:'icon-cancel'" onclick="delMonProInfo();">删除</a>
	</div>
</div>
</div>

<div id="tradeInputCheckConclusionDiv">
<div class="easyui-panel" title="交易复核结论" collapsible="true" > 
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="left">复核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" id="tradeCheckConclusion_Input" name="tradeCheckConclusion" data-options="required:true" disabled="disabled"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" id="tradeCheckRemark_Input" disabled="disabled"></textarea></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</div>
</div>
<div id="tradeInputAuditConclusionDiv">
	<div class="easyui-panel" title="交易审核结论" collapsible="true" > 
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="left">审核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" disabled="disabled" id="tradeAuditConclusion_Input" name="tradeAuditConclusion"></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" disabled="disabled" id="tradeAuditRemark_Input"></textarea></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</div>
</div>
<div style="margin-bottom: 10px;">
	<a class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'" 
		id="submitTradeInputButton" onclick="submitTradeInput();">提交复核</a> 
	<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
		onclick="uploadTradeInputAttachInfo();" data-options="iconCls:'icon-redo'">上传其他附件</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
		onclick="uploadTradeVideo();" data-options="iconCls:'icon-redo'">上传签约视频</a>
	<a class="easyui-linkbutton e-cis_button" id="tradeInputBackButton" 
		onclick="tradeInputBack();" data-options="iconCls:'icon-back'">返回</a> 
</div>
