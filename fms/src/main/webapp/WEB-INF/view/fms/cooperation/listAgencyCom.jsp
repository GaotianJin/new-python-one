<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/cooperation/listAgencyComInit.js"></script>
<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="基金管理人信息查询" collapsible="true">
		<form id="AgencyComForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">基金管理人</td>
						<td align="left"><span class="comboSpan"></span><input name = "agencyCode" id="agencyCode" class="table_input"></td>
						<!-- <td class="table_text" align = "right">基金管理人名称</td>
						<td align="left"><span class="comboSpan"></span><input name="agencyName" id = "agencyName" class = "table_input"></td> -->
						<td class="table_text" align = "right">基金管理人类型</td>
						<td align="left"><span class="comboSpan"></span><input name="agencyType" id = "agencyType" class = "table_input"></td>
						<td class="table_text" align = "right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearAgencyComFormList()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchAgencyCom()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="agencyComTable"></table>
		</div>
	</div>
</div>
<input type="hidden" name="modifyAgencyComId" id="modifyAgencyComId">