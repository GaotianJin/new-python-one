<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="../js/cis/workflow/queryWorkflowInit.js"></script>
<script></script>

<div id="tabdiv">
	<form id="queryWorkflowForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="工作流查询"
			iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td  class="table_text" align="right">业务号：</td>
						<td  align="left"><input id="key" name="key" class="table_input" value=""></td>
						<td  style="border-right: none;">&nbsp;</td>
						<td  style="border-right: none;">&nbsp;</td>
						<td  style="border-right: none;">&nbsp;</td>
						<td  style="border-right: none;">&nbsp;</td>
					</tr>
				</table>
				<input type="hidden" id="id" name="id" value="${id}">
				<div>
					<a href="#" onclick="searchWorkflow()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
</div>

<div style="margin-top: 2px;" id="tabdiv">
	<table id="queryWorkflowTable"></table>
</div>