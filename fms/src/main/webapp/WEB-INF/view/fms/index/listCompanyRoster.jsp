<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/listCompanyRosterInit.js"></script>

<input type="hidden" name="roleId" id="comRoster_roleId" value="${roleId}">

<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="通讯录信息查询" collapsible="true">
		<form id="CompanyRosterForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">姓名</td>
						<td align="left"><input name = "chnName" id="listComPanyRoster_chnName" class="table_input"></td>
						<td class="table_text" align = "right">手机</td>
						<td align="left"><input name="mobile" id = "listComPanyRoster_telephone" class = "table_input"></td>
						<td class="table_text" align = "right">公司固话</td>
						<td align="left">
						<input name="officeTel" id = "listComPanyRoster_officeTel" class = "table_input"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearCompanyRosterFormList()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchCompanyRoster()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="companyRosterTable"></table>
		</div>
	</div>
</div>