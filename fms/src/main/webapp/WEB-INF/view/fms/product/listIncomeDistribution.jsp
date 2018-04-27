<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listIncomeDistributionInit.js"></script>

<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" title="收益分配计算查询"  collapsible="true">
		<form id="incomeDistribulationForm">
			<div class="top_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">产品名称</td>
						<td>
							<span class="comboSpan"></span>
							<input name="productId" id="incomeDistribution_productId" class="table_select easyui-combobox1">
						</td>
						<td class="table_text" align="right">分配日期起期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "easyui-datebox table_input"  name="distributeStartDate"  id = "incomeDistribution_distributeStartDate" />
						</td>
						<td class="table_text" align="right">分配日期止期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="distributeEndDate" id="incomeDistribution_distributeEndDate" class = "easyui-datebox table_input">
						</td>
						
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" id="incomeDistribute" onclick="incomeDistribute()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">收益分配</a>
					<a href="#" onclick="queryProductIncomeDistributeList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="IncomeDisTributionTable"></table>
		</div>
	</div>
</div>

