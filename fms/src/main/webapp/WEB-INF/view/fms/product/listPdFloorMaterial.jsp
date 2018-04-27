<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listPdFloorMaterialInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<!-- <div style="margin:10px 0;"></div>
<div class="easyui-tabs" id="listProductTabs"> -->
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="产品信息查询" collapsible="true">
		<form id="listPdfmForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="input-table" >
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="pdfmAgencyComId" name="agencyComId"></td>
					    <td class="table_text" align="right">产品</td>
					    <td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox" id="pdfmProductId" name="productId"></td>
					    <td class="table_text" align="right">产品类型</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="pdfmProductType" name="productType"></td>
					</tr>
					<tr><td class="table_text" align="right">产品子类型</td>
				        <td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="pdfmProductSubType" name="productSubType"></td>
						<td class="table_text" align="right">产品状态</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="pdfmProductStatus" name="productStatus"></td>
						<td class="table_text" align="right">销售状态</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="pdfmSalesStatus" name="salesStatus"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="PdfmclearSearchInfo()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="PdfmsearchproductInfo()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
		  <table id="listPdfmAppendixTable"></table>
		</div>
	</div>
