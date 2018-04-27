<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/tradeRedemptionListInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div class="easyui-panel" title='交易信息查询' data-options="collapsible:true" fit="true">
		<form id="tradeRedemptionQueryForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="table_text" align="right">客户姓名</td>
							<td>
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="tradeRedemptionList_custName" name="custName"  />
							</td>
							<td class="table_text" align="right">产品名称</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" name="productName" id="tradeRedemptionList_productName" />
							</td>
							<td class="table_text" align="right">赎回状态</td>
							<td>
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="tradeRedemptionList_redemptionStatus" name="redemptionStatus"  />
							</td>
						</tr>
				</table>
				<div>
					<a href="javascript:clearTradeRedemptionCondition();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-reload'">清空</a> 
					<a href="javascript:queryTradeRedemptionInfoList();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-search'">查询</a>
				</div> 
			</div>
		</form>
		
		<div style="margin-top: 3px;"  class="easyui-panel">
			<table id="tradeRedemptionListId">
			</table>
		</div>
	</div>
</div>

<input type="hidden" id="hiddenValue"/>