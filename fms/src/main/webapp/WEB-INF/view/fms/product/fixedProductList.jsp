<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/fixedProductListInit.js"></script>

<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="固定产品列表" collapsible="true">
		<form id="fixedProductListForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="fixedPD_comId"
							name="agencyComId"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left"><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="fixedPD_productId"
							name="productId"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearFixedProductForm()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchFixedProductList()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="fixedProductTable"></table>
		</div>
	</div> 
</div>

