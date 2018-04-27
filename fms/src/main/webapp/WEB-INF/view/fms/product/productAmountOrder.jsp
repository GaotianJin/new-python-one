<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/productAmountOrderInit.js"></script>

<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="预约产品信息查询" collapsible="true">
		<form id="listProduct_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="pdAmountOrder_agencyComId" class="table_input">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="pdAmountOrder_productId" class="table_input">
						</td>
						<td class="table_text" align="right">产品状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="pdOrderStatus" id="pdAmountOrder_productOrderStatus" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">开放日</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="expectOpenDayStart" id="pdAmountOrder_expectOpenDayStart" class="table_input2 easyui-datebox"></input>至
							<input name="expectOpenDayEnd" id="pdAmountOrder_expectOpenDayEnd" class="table_input2 easyui-datebox"></input>
						</td>
						<td class="table_text" align="right">封账日</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="investEndDateStart" id="pdAmountOrder_investEndDateStart" class="table_input2 easyui-datebox"></input>至
							<input name="investEndDateEnd" id="pdAmountOrder_investEndDateEnd" class="table_input2 easyui-datebox"></input>
						</td>
						<td class="table_text" align="right">成立日</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="foundDateStart" id="pdAmountOrder_foundDateStart" class="table_input2 easyui-datebox"></input>至
							<input name="foundDateEnd" id="pdAmountOrder_foundDateEnd" class="table_input2 easyui-datebox"></input>
						</td>
					</tr>
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">-->
				<div>
					<a href="#" onclick="clearQueryProductCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryProductAmountOrderList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div> 
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="productAmountOrderInfoTable"></table>
		</div>
	</div>
	
</div>