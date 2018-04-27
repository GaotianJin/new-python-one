<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listOpenDayFindInit.js"></script>
<!-- 开放日查询 -->
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="开放日信息查询" collapsible="true">
		<form id="queryOpenInfoCondition">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span> 
						<input class="table_input easyui-combobox" id="openDayAgencyComId" name="agencyComId"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="openDayProductId" name="productId">
						</td>
						<td class="table_text" align="right" >开放日</td>
						<td>
							<span class="comboSpan"></span>
							<input name="openDayStartDate" id="openDayStartDate" class="table_input2 easyui-datebox">
							至
							<input name="openDayEndDate" id="openDayEndDate" class="table_input2 easyui-datebox">
						</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchOpenDay()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="openDayGridId"></table>
		</div>
	</div>
</div>

<input type="hidden" name="modifywealthOpenDateId" id="modifywealthOpenDateId">
<input type="hidden" name="modifyAngecyName" id="modifyAngecyName">
<input type="hidden" name="modifyProductName" id="modifyProductName">