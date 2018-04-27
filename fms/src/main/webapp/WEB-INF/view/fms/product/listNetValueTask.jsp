<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listNetValueTaskInit.js"></script>

<!-- 净值信息查询 -->
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="净值信息查询" collapsible="true">
		<form id="netValueTaskForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="list_net_value_taskComId" name="agencyComId"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left"><span class="comboSpan"></span> <input
							class="table_input easyui-combobox" id="list_net_value_taskProductId" name="productId"></td>
						<td class="table_text" align="right">创建日期</td>
						<td><span class="comboSpan"></span> <input name="createDate" id="list_createDate" class="table_input2 easyui-datebox"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearValueTaskForm()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchNetValueTaskList()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="netValueTaskTable"></table>
		</div>
	</div> 
</div>
<input type="hidden" name="addnetValueTaskId" id="addnetValueTaskId">
<input type="hidden" name="addAgencyComName" id="addAgencyComName">
<input type="hidden" name="addProductName" id="addProductName">
<input type="hidden" name="addProductId" id="addProductId">
