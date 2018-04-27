<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/stockProductListInit.js"></script>

<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="股权产品列表" collapsible="true">
		<form id="stockProductListForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="stockPD_comId"
							name="agencyComId"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left"><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="stockPD_productId"
							name="productId"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearStockProductForm()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchStockProductList()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="stockProductTable"></table>
		</div>
	</div> 
</div>

