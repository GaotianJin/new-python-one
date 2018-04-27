<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/tradeInputListInit.js"></script>

<div class="outerPanel" id="outerPanel">
	<div class="easyui-panel" title='交易信息查询' data-options="collapsible:true" fit="true">
		<form id="tradeInputQueryTradeListForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="table_text" align="right">交易号码</td>
						<td><input class="table_input" id="tradeNo" name="tradeNo"></td>
						<!-- <td class="table_text" align="right">交易类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input  class="table_input" id="tradeType" name="tradeType">
						</td> -->
						<td class="table_text" align="right">交易状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input  class="table_input" id="tradeStaus" name="tradeStaus">
						</td>
						<td class="table_text" align="right">合同号</td>
						<td><input class="table_input" id="tradeInfoNo" name="tradeInfoNo"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left"><input class="table_input" id="tradeInputCustName" name="chnName"></td>
						<td class="table_text" align="right">产品</td>
						<td>
							<span class="comboSpan"></span>
							<input  class="table_input" id="tradeInputProduct" name="productId">
						</td>
						<td class="table_text" align="right">交易机构</td>
						<td>
							<span class="comboSpan"></span>
							<input name="tradeComId" id="tradeInput_tradeComId" class="table_input">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">财富顾问</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agentId" id="tradeInput_agentId" class="table_input">
						</td>
						<td class="table_text" align="right">是否出具确认书</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="isPrintTradeConfirmPDF" id="tradeInput_isPrintTradeConfirmPDF" class="table_input">
						</td>
						<td>&nbsp;</td>
				    	<td>&nbsp;</td>
				    	<td>&nbsp;</td>
				    	<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="javascript:clearTradeInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-reload'">清空</a> 
					<a href="javascript:queryTradeInfoList();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-search'">查询</a>
				</div> 
			</div>
		</form>
		
		<div style="margin-top: 3px;"  class="easyui-panel">
			<table id="tradeInputListId">
			</table>
		</div>
	</div>
</div>

<input type="hidden" id="hiddenValue"/>