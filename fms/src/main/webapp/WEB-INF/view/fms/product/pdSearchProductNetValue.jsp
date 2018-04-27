<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/pdSearchProductNetValueInit.js"></script>
<input type="hidden" name="ProductId" id="pdProductId" value="${productId}">
<input type="hidden" name="ProductSubType" id="pdTypeCode" value="${productSubType}">


<!-- 浮动产品净值走势图 -->
<div id="tabdiv11" class="outerPanel">
	<div  class="easyui-panel" title="浮动产品净值走势图"   collapsible="true">
		<div id="pdnetValueInfo_DetailFormDiv" style="height:300px;width:1100px;margin:20px auto;border:1px solid #ccc;">
		</div>
		<!-- <!--撑开5个像素的间隔-->
		<div class="tableOuterDiv"></div>
		<div style="margin-bottom: 3px;padding-left:120px">
			<td class="table_text " align="right">请选择查看范围：</td>
			<td align="left">
				<span class="comboSpan"></span>
				<input class="table_input easyui-combobox" id="chooseCategory" name="chooseCategory">
			</td>
		</div>
	</div> 
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="pdnetValueDetailId"></table>
		</div>
</div>