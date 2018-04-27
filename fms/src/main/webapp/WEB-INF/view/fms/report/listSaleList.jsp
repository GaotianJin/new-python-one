<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/report/listSaleListInit.js"></script>
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="业务列表" collapsible="true">
		 <form id="listSale_queryConditionForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
					<td class="table_text" align="right">年度</td>
						<td>
							<span class="comboSpan"></span>
							<input name="year" id="listSale_yearId" class="table_input"/>
						</td>
						<td class="table_text" align="right">月份</td>
						<td>
							<span class="comboSpan"></span>
							<input name="month" id="listSale_monthId" class="table_input"/>
						</td>
							<td align="right" class="table_text">成立时间</td>
					<td align="left">
							<span class="comboSpan"></span>
							<input name="saleProductsStartTime" id="listSale_saleProductsStartTime" class="table_input2 easyui-datebox">
							至
							<input name="saleProductsEndTime" id="listSale_saleProductsEndTime" class="table_input2 easyui-datebox">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">分公司</td>
						<td>
							<span class="comboSpan"></span>
							<input name="comId" id="listSale_comId" class="table_input"/>
						</td>
						<td class="table_text" align="right">部门</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="departmentId" id="listSale_DepartmentId" class="table_input"/>
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
						<span class="comboSpan"></span>
						<input name="productId" id="lisSale_ProductId" class="table_input"/>
						</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="querySaleProductsList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="exportDetailList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出</a>
						
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listSale_QueryTable"></table>
		</div>
	</div>	
</div>

