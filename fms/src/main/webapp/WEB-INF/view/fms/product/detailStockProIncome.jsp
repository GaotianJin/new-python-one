<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/detailStockProIncomeInit.js"></script>


<input type="hidden" name="productId" id="detailStockProIncome_productId" class="inpuntHidden" value="${productId}">
<input type="hidden" name="distributeDate" id="detailStockProIncome_distributeDate" class="inpuntHidden" value="${distributeDate}">
<div id="tabdiv10" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="详细信息" collapsible="true">
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="detailStockProIncomeTable"></table>
		</div>
		<div>
					<a href="#" onclick="back()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</div>
</div>