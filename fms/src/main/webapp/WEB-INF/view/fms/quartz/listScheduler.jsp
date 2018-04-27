<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/quartz/listSchedulerInit.js"></script>
<div id="tabdiv" class="outerPanel">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="查询条件"  collapsible="true">
		<form id="schedulerForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table" >
					<tr>
						<td class="table_text" align="right">调度名称</td>
						<td><span class="comboSpan"></span><input class="table_input" id="list_trigger_name" name="trigger_name" >
						</td>
						<td class="table_text" align="right">任务名称</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input" name="job_name" id="list_job_name" />
						</td>
	                   	<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearScheduler()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchScheduler()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
			<div style="margin-top: 3px;" id="tabdiv">
	          <table id="schedulerTable"></table>
            </div>
	</div>
</div>
