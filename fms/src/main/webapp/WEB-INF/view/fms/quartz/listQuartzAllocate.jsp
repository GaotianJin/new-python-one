<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript"
	src="../js/cis/quartz/listQuartzAllocateInit.js"></script>

<div style="padding: 0px; margin: 0px;" border="false" id="tabdiv">
	<table class="easyui-datagrid" id="schedulerTable"></table>
</div>
<form id="schedulerForm">

	<div id="tb" class="datagrid-toolbar"
		style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="addSchedulerdialog('quartz/addQuartzAllocateUrl');">新增调度配置</a> 
		</div>
		调度名称: <input name="trigger_name" style="width: 200px"> 任务名称: <input
			name="job_name" style="width: 80px"> <a href="#"
			class="easyui-linkbutton" iconCls="icon-search"
			onclick="searchScheduler()">查询</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-reload"
			onclick="clearScheduler()">清空</a>
	</div>
</form>
<div id="dd">
	<iframe scrolling='auto' frameborder='0' id='dialogIframe' src=''
		style='width: 100%; height: 100%;'></iframe>
</div>

 