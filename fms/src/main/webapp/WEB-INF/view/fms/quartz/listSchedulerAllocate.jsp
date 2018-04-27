<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript"
	src="../js/cis/quartz/listSchedulerAllocateInit.js"></script>
<script type="text/javascript"></script>

<div id="tabdiv">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="调度关系查询"
		iconCls="icon-ok" collapsible="true">
		<form id="salechnlForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="125" class="table_text" align="right">基本任务名称：</td>
						<td width="200" align="left"><select id="jobtname" name="jobtname" onchange="queryBaseTaskName()" class="table_select"></select></td>
						<td width="125" class="table_text" align="right">基本任务接口名：</td>
						<td width="200" align="left"><input id="jobname" name="jobname" class="table_input"></td>
					</tr>
				</table>
				<div>
					<a href="#" class="easyui-linkbutton e-cis_button"
						iconCls="icon-search" onclick="searchSalechnl()">查询</a> 
					<a href="#" class="easyui-linkbutton e-cis_button"
						iconCls="icon-reload" onclick="clearForm()">清空</a>
				</div>
			</div>
		</form>
	</div>
</div>
<div style="margin-top: 10px;" id="tabdiv">
	<table id="salechnlTable"></table>
</div>
<div id="salechnltab" class="easyui-tabs" fit="false" border="ture"
	plain="true"></div>