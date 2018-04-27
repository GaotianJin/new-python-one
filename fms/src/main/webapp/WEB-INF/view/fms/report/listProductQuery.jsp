<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/report/listProductQueryInit.js"></script>

<div id="tabdiv" class="outerPanel">
<div id="smsaccordion" class="easyui-panel" fit="true" title="产品查询列表" collapsible="true">
<form id="productQueryForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="input-table" >
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="ProductQuery_AgencyComId" name="agencyComId"></td>
					    <td class="table_text" align="right">产品</td>
					    <td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox" id="ProductQuery_ProductId" name="productId"></td>
					    <td class="table_text" align="right">产品类型</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="ProductQuery_ProductType" name="productType"></td>
					</tr>
					<tr><td class="table_text" align="right">产品子类型</td>
				        <td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="ProductQuery_ProductSubType" name="productSubType"></td>
						<!-- <td class="table_text" align="right">产品状态</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="ProductQuery_ProductStatus" name="productStatus"></td> -->
						<td class="table_text" align="right">销售状态</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="ProductQuery_SalesStatus" name="salesStatus"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearSearchInfo()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchProductInfo()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
		  <table id="listProductQueryTable"></table>
		</div>
</div>
</div>