<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listProductReportInit.js"></script>

<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="产品报告查询" collapsible="true">
		<form id="productReportForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="input-table" >
					<tr>
					    <td class="table_text" align="right">产品名称</td>
					    <td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox" id="productReport_productId" name="productId"></td>
					    <td class="table_text" align="right">报告名称</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-validatebox" id="productReport_uploadFileName" name="uploadFileName"></td>
<!-- 						<td class="table_text" align="right">&nbsp;</td>
						<td>&nbsp;</td> -->
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearSearchPDReportInfo()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchProductReportInfo()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
		  <table id="listProductReportTable"></table>
		</div>
	</div>


