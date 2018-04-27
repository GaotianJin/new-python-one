<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fms/product/listProductReleaseInit.js"></script>
<!-- 产品发布 -->
<div class="outerPanel">
<div class="easyui-panel" title='产品信息查询'  id="smsaccordion" collapsible="true" fit="true">
	<form id="productReleaseForm">
		<div class="top_table">
			<table class='input_table' width="100%" border="0" cellspacing="0"cellpadding="0">
				<tr>
					<td class="table_text" align="right">基金管理人</td>
					<td><span class="comboSpan"></span> <input class="table_input easyui-combobox" id="productReleaseAgencyComId" name="agencyComId"></td>
					<td class="table_text" align="right">产品</td>
					<td><span class="comboSpan"></span><input class="table_input" id="productReleaseProductId" name="productId"></td>
					<td class="table_text" align="right">产品类型</td>
					<td><span class="comboSpan"></span> <input class="table_input easyui-combobox"  id="productReleaseProductType" name="productType"></td>
				</tr>
				<tr>
					<!-- <td class="table_text" align="right">产品子类</td>
					<td align="left"><span class="comboSpan"></span> <input class="table_input easyui-combobox" id="productReleaseProductSubType" name="productSubType"></td> -->
					<td class="table_text" align="right">销售状态</td>
					<td><span class="comboSpan"></span> <input class="table_input easyui-combobox" id="productReleaseSalesStatus" name="salesStatus"></td>
					<td class="table_text" align="right">产品状态</td>
					<td><span class="comboSpan"></span> <input class="table_input easyui-combobox" id="productReleaseProductStatus" name='productStatus'></td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				</tr>
			</table>
			<div>
			 <a href="#" onclick="clearFormInfo()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
       		 <a href="#" onclick="searchProductReleaseInfo()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
			</div>
		</div>
	</form>
	 <div style="margin-top: 3px;" id="tabdiv">
		  <table id="proReleaseListGrid"></table>
	</div>
    </div>
</div>

