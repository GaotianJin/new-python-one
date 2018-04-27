<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listFixedProductIncomeInit.js"></script>

<!-- 固收产品收益查询 -->
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="固收产品收益信息查询" collapsible="true">
		<form id="fixedProductIncomeForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="fixedProductIncome_ComId"
							name="comId"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left"><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="fixedProductIncome_ProductId"
							name="productId"></td>
						<!-- <td class="table_text" align="right"></td>
						<td><span class="comboSpan"></span> <input
							name="publicDate" id="list_publicDate"
							class="table_input2 easyui-datebox">
						</td> -->
					</tr>
					<tr>
					<td class="table_text" align="right">预付息月份</td>
						<td align="left"><span class="comboSpan"></span><input name="fixedProductIncome_year" id="fixedProductIncome_year" class="table_input easyui-combobox1">年
						<input name="fixedProductIncome_month" id="fixedProductIncome_month" class="table_input easyui-combobox1">月</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearProductIncomeForm()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchProductIncomeList()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="fixedProductIncomeTable"></table>
		</div>
	</div> 
</div>
