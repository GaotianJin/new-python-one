<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listNetValueInit.js"></script>

<!-- 净值信息查询 -->
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="净值信息查询" collapsible="true">
		<form id="netValuesForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="list_net_valueComId"
							name="valueComId"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left"><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="list_net_valueProductId"
							name="valueProductId"></td>
						<td class="table_text" align="right">公布日期</td>
						<td><span class="comboSpan"></span> <input
							name="publicDate" id="list_publicDate"
							class="table_input2 easyui-datebox">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">净值类型</td>
						<td><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="list_net_valueType"
							name="valueType"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearValueForm()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchNetValueList()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="netvalueInfoGridId"></table>
		</div>
	</div> 
</div>
<input type="hidden" name="modifyRecordNetWorthId" id="modifyRecordNetWorthId">
<input type="hidden" name="modifyAngecyName" id="modifyAngecyName">
<input type="hidden" name="modifyProductName" id="modifyProductName">

