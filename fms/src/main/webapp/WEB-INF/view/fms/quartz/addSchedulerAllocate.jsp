<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="../js/cis/quartz/addSchedulerAllocateInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="新增调度关系"  iconCls="icon-ok" collapsible="true">
<form id="SchedulerAllocateForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="125" class="table_text" align="right">基本任务名称：</td>
<td width="200" align="left"><input id="jobtname1" name="jobtname1" class="table_input"></td>
<td width="125" class="table_text" align="right"><font color="red">----请选择--------</font></td>
<td width="125" class="table_text" align="left"><font color="red">--------------</font></td>
<!-- <td width="200" align="left" style="border-right:none;">&nbsp;</td> -->
<td width="125" class="table_text" align="right">被调度的基本任务名称:</td>
<td width="200" align="left"><select id="jobtname2" name="jobtname2" class="table_select"></select></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">调度关系起始日期：</td>
<td width="200" align="left"><input id="startdate" name="startdate" class="table_input"></td>
<td width="125" class="table_text" align="right">调度关系终止日期：</td>
<td width="200" align="left"><input id="enddate" name="enddate" class="table_input"></td>
<td width="125" style="border-right:none;">&nbsp;</td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
</tr>
</table>
<div><a href="#" onclick=" addSchedulerAllocate()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">分配调度</a></div>
</div>
</form>
</div>
</div>