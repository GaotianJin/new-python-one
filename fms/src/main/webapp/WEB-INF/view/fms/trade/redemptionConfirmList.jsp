<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/redemptionConfirmListInit.js"></script>

<div class="outerPanel" id="outerPanel">
	<div class="easyui-panel" title='交易信息查询' data-options="collapsible:true" fit="true">
		<form id="tradeRedemptionConfirmListForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					<!-- 	<td class="table_text" align="right">财富顾问</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input  class="table_input easyui-combobox" id="redemptionConfirmList_agentName" name="agentId">
						</td> -->
						<td class="table_text" align="right">客户姓名</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input  class="table_input easyui-combobox" id="redemptionConfirmList_custName" name="custNo">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input  class="table_input easyui-combobox" id="redemptionConfirmList_productName" name="productId">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="javascript:clearRedemptionConfirmInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-reload'">清空</a> 
					<a href="javascript:queryRedemptionConfirmInfoList();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-search'">查询</a>
				</div> 
			</div>
		</form>
		
		<div style="margin-top: 3px;"  class="easyui-panel">
			<table id="tradeRedemptionConfirmListId">
			</table>
		</div>
	</div>
</div>

<input type="hidden" id="hiddenValue"/>