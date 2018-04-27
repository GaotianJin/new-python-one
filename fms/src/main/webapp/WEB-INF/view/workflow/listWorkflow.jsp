<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="../js/cis/workflow/listWorkflowInit.js"></script>

<div id="tabdiv">
	<form id="workflowForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="工作流管理" iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="125" class="table_text" align="right">流程名称：</td>
						<td width="200" align="left"><input name="name" value=""
							class="table_input"></td>
						<td width="125" style="border-right: none;">&nbsp;</td>
						<td width="200" style="border-right: none;">&nbsp;</td>
						<td width="125" style="border-right: none;">&nbsp;</td>
						<td width="200" style="border-right: none;">&nbsp;</td>
					</tr>
				</table>
				<input type="hidden" id="id" name="id" value="${id}">
				<div>
					<a href="#" onclick="searchWorkflow()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
</div>

<div style="margin-top: 2px;" id="tabdiv">
	<table id="workflowTable"></table>
</div>
