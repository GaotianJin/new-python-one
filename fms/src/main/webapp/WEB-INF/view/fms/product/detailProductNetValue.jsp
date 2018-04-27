<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/detailProductNetValueInit.js"></script>
<input type="hidden" name="ProductId" id="ProductId" value="${productId}">
<input type="hidden" name="ProductType" id="ProductId" value="${productTypeCode}">
<input type="hidden" name="ProductSubType" id="ProductId" value="${productSubTypeCode}">


<!-- 浮动产品净值走势图 -->
<div id="tabdiv11" class="outerPanel">
	<div  class="easyui-panel" title="浮动产品净值走势图"   collapsible="true">
		<div id="netValueInfo_DetailFormDiv" style="height:300px;width:1100px;margin:20px auto;border:1px solid #ccc;">
		</div>
		<!-- <!--撑开5个像素的间隔-->
		<div class="tableOuterDiv"></div>
	</div> 
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="netValueDetailId"></table>
		</div>
</div>