<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="../js/cis/quartz/addBaseTaskInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="新增基本任务"  iconCls="icon-ok" collapsible="true">
<form id="BaseTaskForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">基本任务名称：</td>
<td width="200" align="left"><input id="jobtname" name="jobtname" class="table_input"></td>
<td width="125" class="table_text" align="right">任务处理接口名：</td>
<td width="200" align="left"><select id="job_name" name="job_name" class="table_select"></select></td>
<td width="125" class="table_text" align="right">备注：</td>
<td width="200" align="left"><input id="remark" name="remark" class="table_input"></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">新增任务日期：</td>
<td width="200" align="left"><input id="makedate" name="makedate" class="table_input"></td>
<td width="125" style="border-right:none;">&nbsp;</td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" style="border-right:none;">&nbsp;</td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
</tr>

</table>
<div><a href="#" onclick="addBaseTask()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增基本任务</a></div>
</div>
</form>
</div>
</div>