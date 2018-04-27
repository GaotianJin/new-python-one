<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/transFereeInputInit.js"></script>
<input type="hidden" name="orTradeId" id="tradeTransFeree_hOrTradeId" value="${orTradeId}">
<input type="hidden" name="orAgentId" id="tradeTransFeree_horAgentId" value="${orAgentId}">
<input type="hidden" name="productId" id="tradeTransFeree_hproductId" value="${productId}">
<input type="hidden" name="tradeFundsShareChangeId" id="tradeTransFeree_htradeFundsShareChangeId" value="${tradeFundsShareChangeId}">
<input type="hidden" name="tradeStatus" id="tradeTransFeree_htradeStatus" value="${tradeStatus}">
<div class="easyui-panel" title="基本信息" collapsible="true">
	<form id="tradeTransFereeInfoForm_input">
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="right">交易号码</td>
				<td>
				    <input class="inpuntHidden" id="tradeId_Input" name="tradeInfoId" type="hidden" value=""/>
				    <input class="table_input" id="tradeNo_Input" name="tradeNo" readonly="readonly" value="${tradeNo}" />
				<input class="inpuntHidden" id="contractNumber_Input" name="contractNum" type="hidden" value="${contractNum}"/>
				</td>
				<td class="table_text" align="right">交易类型</td>
				<td align="left">
				    <span class="comboSpan"></span>
				    <input class="table_input easyui-combobox1" id="tradeType_Input" name="tradeType" data-options="editable:false" disabled="disabled">
				</td>
				<td class="table_text" align="right">录入日期</td>
				<td><span class="comboSpan"></span> 
					<input class="table_input easyui-datebox1" id="tradeInputDate_Input" name="tradeInputDate">
				</td>
			</tr>
			<tr>
				<td class="table_text" align="right">财富顾问</td>
				<td><span class="comboSpan"></span>
					<!-- <input class="table_input" id="agentName_Input" name="agentName"  data-options="editable:false"></input> -->
					<input class="table_input easyui-combobox1" id="agentID_Input" name="agentId"  data-options="editable:false" disabled="disabled"></input>
				</td>
				<td class="table_text" align="right">所属机构</td>
				<td align="left"><span class="comboSpan"></span> 
					<input class="table_input easyui-combobox1" id="companyID_Input" name="companyId" data-options="editable:false" disabled="disabled">
				</td>
			    <td class="table_text" align="right">交易机构</td>
				<td>
					<span class="comboSpan"></span> 
					<input class="table_input  easyui-combobox1" id="tradeComID_Input" name="tradeComId" data-options="required:true">
				</td>
			</tr>
			<tr>
			   <td class="table_text" align="right">原财富顾问</td>
				<td><span class="comboSpan"></span>
			 <!--   <input class="table_input easyui-combobox1" id="orAgentName_Input" name="orAgentName"> -->
			   <input class="table_input easyui-combobox1" id="orAgentId_Input" name="orAgentId" data-options="editable:false" disabled="disabled"/>
			   </td>
			   <td class="table_text" align="right">合同号</td>
				<td>
				<span class="comboSpan"></span><input class="table_input easyui-validatebox" id="tradeInfoNo_Input" name="tradeInfoNo" data-options="validType:['length[0,40]','validCode']"></td>
				<!-- <td class="table_text" align="right">认购日/投保日</td>
				<td align="left">
				<span class="comboSpan"></span> 
				 <input
					class="table_input easyui-datebox" id="tradeDate_Input" name="tradeDate" data-options="required:true"></td> -->
			<td class="table_text" align="right">币种</td>
				<td>
				<span class="comboSpan"></span> 
			   <input class="table_input  easyui-combobox1" id="currency_Input" name="currency"></td>
			    <td class="table_text" align="right"></td>
				<td align="left"></td>
				 <td class="table_text" align="right"></td>
				<td align="left"></td>
			</tr>
		<!-- 	<tr>
			   <td class="table_text" align="right">币种</td>
				<td>
				<span class="comboSpan"></span> 
			   <input class="table_input  easyui-combobox1" id="currency_Input" name="currency"></td>
			    <td class="table_text" align="right"></td>
				<td align="left"></td>
				 <td class="table_text" align="right"></td>
				<td align="left"></td>
			</tr> -->
		</table>
		
		<div style="margin-bottom: 3px;" >
			<a class="easyui-linkbutton e-cis_button" id="submitTradeTransFereeInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTradeTreansFereeInfo();">提交</a>
		</div>
	</div>
	</form>
</div>
<!-- <div class="easyui-panel" title="客户信息" collapsible="true">
	<table id="customInfoInputId" width="100%"> </table>
	<div style="margin-bottom: 3px;" >
		<a class="easyui-linkbutton e-cis_button" id="submitTradeTransFerCusInfoButton" 
			data-options="iconCls:'icon-tick'" onClick="submitTradeTransFerCusInfo();">提交</a>
		<span id="custOperationPrompt" style="color: red;size:18">（新增或修改操作后请再次提交！）</span>
	</div>
</div> -->
<div class="easyui-panel" title="客户信息" collapsible="true">
<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">选择客户</td>
				<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="chooseCustomers_input" name="custlist" ></td>
				<td class="table_text" align="right"></td>
				<td></td>
				<td class="table_text" align="right"></td>
				<td></td>
			</tr>
		</table>
</div>
	<table id="customInfoInputId" width="100%"> </table>
	<div style="margin-bottom: 3px;" class="wealthHidden" hidden><!-- class为wealthHidden的提交按钮财富产品都不显示 -->
		<a class="easyui-linkbutton e-cis_button" id="submitTradeCustomersInfoButton" 
			data-options="iconCls:'icon-tick'" onClick="submitTradeCusInfo();">提交</a>
		<span id="customerOperationPrompt" style="color: red;size:18">（新增或修改操作后请再次提交！）</span>
	</div>
</div>
<!-- <div id="bankInfoInputPanelId">
	<div class="easyui-panel" title="交易账户信息" collapsible="true" >
		<table id="tradeBankInfoInputId"> </table>
		<div style="margin-bottom: 3px;" > 
			<a class="easyui-linkbutton e-cis_button" id="submitTradeInputBankInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTradeTransFerBankInfoInputId();">提交</a>
		</div>
	</div>
</div> -->
<div id="bankInfoInputPanelId">
	<div class="easyui-panel" title="交易账户信息" collapsible="true" >
		<table id="tradeBankInfoInputId"> </table>
		<div style="margin-bottom: 3px;" class="wealthHidden" hidden> 
			<a class="easyui-linkbutton e-cis_button" id="submitTradeInputBankInfoButton" 
				data-options="iconCls:'icon-tick'" onclick="submitTradeTransFerBankInfoInputId();">提交</a>
		</div>
	</div>
</div>
<!-- <div id="addressInfoInputPanelId">
	<div class="easyui-panel" title="交易地址信息" collapsible="true" >
		<table id="tradeAddressInfoInputId"></table>
		<div style="margin-bottom: 3px;" >
			<a class="easyui-linkbutton e-cis_button" id="submitTradeInputAddressInfoButton"
				data-options="iconCls:'icon-tick'" onclick="submitTradeTransFerAddressInfoInputId();">提交</a>
		</div>
	</div>
</div> -->
<div id="addressInfoInputPanelId">
	<div class="easyui-panel" title="交易地址信息" collapsible="true" >
		<table id="tradeAddressInfoInputId"></table>
		<div style="margin-bottom: 3px;" >
			<span class="wealthHidden" hidden><a class="easyui-linkbutton e-cis_button" id="submitTradeInputAddressInfoButton"
				data-options="iconCls:'icon-tick'" onclick="submitTradeTransFerAddressInfoInputId();" >提交</a></span>
			<a class="easyui-linkbutton e-cis_button" id="submitTradeCustomerInfoButton"
				data-options="iconCls:'icon-tick'" onclick="submitTradeCustomerInfo();">提交</a>
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
	
	<table id="monProInfoInputId"> </table>
	
	<div class="top_table" id="monProInfoSelectDiv">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">产品</td>
				<td>
					<input class="table_input easyui-combobox1" id="tradeWealthProId" name="tradeWealthProId" style="width: 300px; height: 20px; line-height: 20px;"disabled="disabled" >
					</td>
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
			data-options="iconCls:'icon-tick'" onclick="submitTradeTransFerPro();">提交</a>
	</div>
</div>
<div style="margin-bottom: 10px;">
	<a class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'" 
		id="submitTradeTransFereeButton" onclick="submitTradeTransFeree();">提交受让复核</a> 
	<a class="easyui-linkbutton e-cis_button" id="tradeInputBackButton" 
		onclick="tradeTransFereeInputBack();" data-options="iconCls:'icon-back'">返回</a> 
</div>