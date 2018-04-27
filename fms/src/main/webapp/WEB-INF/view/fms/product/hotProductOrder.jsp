<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/hotProductOrderInit.js"></script>

<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="热门产品信息查询" collapsible="true">
		<form id="listHotProduct_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">合作机构</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="hotPdOrder_agencyComId" class="table_input">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="hotPdOrder_productId" class="table_input">
						</td>
						<td class="table_text" align="right">产品状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="pdOrderStatus" id="hotPdOrder_productOrderStatus" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">开放日</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="expectOpenDayStart" id="hotPdOrder_expectOpenDayStart" class="table_input2 easyui-datebox"></input>至
							<input name="expectOpenDayEnd" id="hotPdOrder_expectOpenDayEnd" class="table_input2 easyui-datebox"></input>
						</td>
						<td class="table_text" align="right">封账日</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="investEndDateStart" id="hotPdOrder_investEndDateStart" class="table_input2 easyui-datebox"></input>至
							<input name="investEndDateEnd" id="hotPdOrder_investEndDateEnd" class="table_input2 easyui-datebox"></input>
						</td>
						<td class="table_text" align="right">成立日</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="foundDateStart" id="hotPdOrder_foundDateStart" class="table_input2 easyui-datebox"></input>至
							<input name="foundDateEnd" id="hotPdOrder_foundDateEnd" class="table_input2 easyui-datebox"></input>
						</td>
					</tr>
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">-->
				<div>
					<a href="#" onclick="clearQueryHotProductCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryHotProductOrderList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div> 
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="hotProductOrderInfoTable"></table>
		</div>
	</div>
	
</div>