<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listIncomeDisDetailInit.js"></script>

<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" title="收益分配明细查询"collapsible="true">
		<form id="IncomeDisDetailQueryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">客户名称</td>
						<td align="left">
							<input name="custName" id="incomeDisDetail_custName" class="table_input">
						</td>
						<td class="table_text" align="right">产品方名称</td>
						<td>
							<span class="comboSpan"></span>
							<input name="agencyComId" id="incomeDisDetail_agencyComId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td>
							<span class="comboSpan"></span>
							<input name="productId" id="incomeDisDetail_productId" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">分配日期起期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="distributeStartDate" id="incomeDisDetail_distributeStartDate"/>
						</td>
						<td class="table_text" align="right">分配日期止期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "easyui-datebox table_input"  name="distributeEndDate"  id = "incomeDisDetail_distributeEndDate" />
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryProductIncomeDistributeDetailList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="incomeDisDetailTable"></table>
		</div>
	</div>
</div>
