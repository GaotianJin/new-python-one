<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="../js/cis/quartz/addQuartzAllocateInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="新增调度关系"  iconCls="icon-ok" collapsible="true">
<form id="SchedulerAllocateForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="125" class="table_text" align="right">基本任务名称：</td>
<td width="200" align="left"><input id="schedulername1" name="schedulername1" class="table_select"></td>
<td width="250" align="left" style="border-right:none;">&nbsp;&nbsp;</td>
<td width="125" class="table_text" align="right">前置任务名称:</td>
<td width="200" align="left"><select id="schedulername2" name="schedulername2" class="table_select"></select></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">调度名称：</td>
<td width="200" align="left"><input id="jobtname1" name="jobtname1" class="table_input"></td>
<td width="250" align="left" style="border-right:none;">&nbsp;&nbsp;</td>
<td width="125" class="table_text" align="right">前置调度名称:</td>
<td width="200" align="left"><input id="jobtname2" name="jobtname2" class="table_input"></td>

</tr>
<tr>
<td width="125" class="table_text" align="right">下次执行时间：</td>
<td width="200" align="left"><input id="nexttime1" name="nexttime1" class="table_input"></td>

<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">下次执行时间：</td>
<td width="200" align="left"><input id="nexttime2" name="nexttime2" class="table_input"></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">上次执行时间：</td>
<td width="200" align="left"><input id="lasttime1" name="lasttime1" class="table_input"></td>
<!-- <td width="125" style="border-right:none;">&nbsp;</td> -->
<td width="200" align="center" style="border-right:none;"><font color="red"> 请分配前置调度：>>>> </font></td>
<td width="125" class="table_text" align="right">上次执行时间：</td>
<td width="200" align="left"><input id="lasttime2" name="lasttime2" class="table_input"></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">执行状态：</td>
<td width="200" align="left"><input id="triggerstate1" name="triggerstate1" class="table_input"></td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">执行状态：</td>
<td width="200" align="left"><input id="triggerstate2" name="triggerstate2" class="table_input"></td>

</tr>
<tr>
<td width="125" class="table_text" align="right">类型：</td>
<td width="200" align="left"><input id="triggertype1" name="triggertype1" class="table_input"></td> 
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">类型：</td>
<td width="200" align="left"><input id="triggertype2" name="triggertype2" class="table_input"></td>

</tr>
<tr>
<td width="125" class="table_text" align="right">开始时间：</td>
<td width="200" align="left"><input id="starttime1" name="starttime1" class="table_input"></td>
 
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">开始时间：</td>
<td width="200" align="left"><input id="starttime2" name="starttime2" class="table_input"></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">结束时间：</td>
<td width="200" align="left"><input id="endtime1" name="endtime1" class="table_input"></td>
<!-- <td width="125" style="border-right:none;">&nbsp;</td> -->
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">结束时间：</td>
<td width="200" align="left"><input id="endtime2" name="endtime2" class="table_input"></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">cron表达式 ：</td>
<td width="200" align="left"><input id="cron1" name="cron1" class="table_input"></td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">cron表达式 ：</td>
<td width="200" align="left"><input id="cron2" name="cron2" class="table_input"></td>
</tr>
<!-- <tr>
<td width="125" class="table_text" align="right">计划 执行次数：</td>
<td width="200" align="left"><input id="startdate" name="startdate" class="table_input"></td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">计划 执行次数：</td>
<td width="200" align="left"><input id="enddate" name="enddate" class="table_input"></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">执行间隔：</td>
<td width="200" align="left"><input id="startdate" name="startdate" class="table_input"></td>
<td width="125" style="border-right:none;">&nbsp;</td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">执行间隔：</td>
<td width="200" align="left"><input id="enddate" name="enddate" class="table_input"></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">已执行次数：</td>
<td width="200" align="left"><input id="startdate" name="startdate" class="table_input"></td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">已执行次数：</td>
<td width="200" align="left"><input id="enddate" name="enddate" class="table_input"></td> -->
</tr>
<tr>
<td width="125" class="table_text" align="right">调度关系有效起始日期：</td>
<td width="200" align="left"><input id="startdate" name="startdate" class="table_input"></td>
<td width="200" align="left" style="border-right:none;">&nbsp;</td>
<td width="125" class="table_text" align="right">调度关系结束日期：</td>
<td width="200" align="left"><input id="enddate" name="enddate" class="table_input"></td>
</tr>

</table>
<div><a href="#" onclick=" addSchedulerAllocate()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">分配调度</a></div>
</div>
</form>
</div>
</div>