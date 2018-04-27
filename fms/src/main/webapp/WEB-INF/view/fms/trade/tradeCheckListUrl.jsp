<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/tradeCheckListInit.js"></script> 

<div class="outerPanel" id="outerPanel">
<div class="easyui-panel" title='交易信息查询' id="tradeCheckCommonListQueryConditionDiv"
	data-options="collapsible:true">
	<form id="tradeCheckCommonListQueryConditionForm">
		<div class="top_table">
			<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="table_text" align="right">交易号码</td>
					<td><input class="table_input" id="tradeNo_Check_Out" name="tradeNo"></td>
					<td class="table_text" align="right">合同号</td>
					<td><input class="table_input" id="tradeInfoNo_Check_Out" name="tradeInfoNo"></td>
					<td class="table_text" align="right">客户姓名</td>
					<td align="left"><input class="table_input" id="tradeCheckCustName" name="chnName"></td>
				</tr>
			    <tr>
			    	<td class="table_text" align="right">产品</td>
					<td>
						<span class="comboSpan"></span>
						<input  class="table_input" id="tradeCheckProduct" name="productId">
					</td>
					<td class="table_text" align="right">交易机构</td>
					<td>
						<span class="comboSpan"></span>
						<input name="tradeComId" id="tradeCheck_tradeComId" class="table_input">
					</td>
			    	<td class="table_text" align="right">财富顾问</td>
					<td align="left">
						<span class="comboSpan"></span>
						<input name="agentId" id="tradeCheck_agentId" class="table_input">
					</td>
			    	
			    </tr>
			   <!--  <tr>
			    	<td class="table_text" align="right">交易日期</td>
					<td>
						<span class="comboSpan"></span>
						<input name="tradeDateStart" id="tradeDateStart_Check_Out" class="table_input2 easyui-datebox">
						至
						<input name="tradeDateEnd" id="tradeDateEnd_Check_Out" class="table_input2 easyui-datebox">
					</td>
					<td class="table_text" align="right">录入日期</td>
					<td>
						<span class="comboSpan"></span>
						<input name="inputDateStart" id="inputDateStart_Check_Out" class="table_input2 easyui-datebox">
						至
						<input name="inputDateEnd" id="inputDateEnd_Check_Out" class="table_input2 easyui-datebox">
					</td>
					<td class="table_text" align="right">&nbsp;</td>
					<td>&nbsp;</td>
			    </tr> -->
			</table>
		</div>
		<div>
			<a href="javascript:clearTradeCheckInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-reload'">清空</a> 
			<a href="javascript:queryTradeCheckInfoList();" class="easyui-linkbutton  e-cis_button" data-options="iconCls:'icon-search'">查询</a>
			<a class="easyui-linkbutton  e-cis_button" id="submitTradeCheckDetailButton" data-options="iconCls:'icon-tick'" onclick="tradeCheckDetail('','update');">交易复核</a>
		</div>
		<div id="tradeCheckCommonListDiv" style="margin-top: 3px;">
			<table id="tradeCheckCommonListId"></table> 
		</div> 
	</form>
</div>

</div>

