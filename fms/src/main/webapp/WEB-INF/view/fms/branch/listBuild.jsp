<%@ page language="java"import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/listBuildInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="楼盘信息查询" collapsible="true">
		<form id="buildForm">
			<div class="top_table">
				<table class="input_table" width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="table_text" align="right">楼盘代码</td>
						<td align="left"><input name="buildingCode" id="listBuildingCode"
							class="table_input"></td>
						<td class="table_text" align="right">楼盘名称</td>
						<td align="left"><input name="buildingName" id="listBuildingName"
							class="table_input"></td>
						<td class="table_text" align="right">楼盘类型</td>
						<td align="left"><span class="comboSpan"></span><input
							name="buildingType" id="listBuildingType"
							class=" table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">物业类型</td>
						<td align="left"><span class="comboSpan"></span><input
							name="estateType" id="listEstatetype"
							class="table_input"></td>
						<td class="table_text" align="right">房屋均价(元/㎡)</td>
						<td align="left"><input name="avgPriceFrom" id="listAvgPriceFrom"
							class="table_input"></td>
						<td class="table_text" align="right">至</td>
						<td align="left"><input name="avgPriceEnd" id="listAvgPriceEnd"
							class="table_input"></td>
					</tr>
				</table>
				<div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">
					<a href="#" onclick="clearForm()" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'">清空</a> 
					<a href="#" onclick="queryBuildList()" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
				</div>
			</div>
		</form>
		<div class="easyui-panel">
			<table id="buildTable"></table>
		</div>
	</div>
</div>