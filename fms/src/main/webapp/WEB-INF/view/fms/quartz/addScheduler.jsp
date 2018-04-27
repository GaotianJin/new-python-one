<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/quartz/addSchedulerInit.js"></script>
<form id="addSchedulerForm" method="post" style="margin: 0; text-align: left;">
	<table width="100%">
		<tr>
			<td nowrap style="width: 120px">调度类型</td>
			<td>
				<select id="trigger_type" class="easyui-combobox" name="trigger_type" >
					<option value="templatetrigger" selected>模板调度</option>
					<option value="simpletrigger">简单调度</option>
					<option value="crontrigger">Cron表达式调度</option>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap style="width: 120px">任务名称</td>
			<td><input id="job_name" name="job_name"></td>
		</tr>
	</table>
	<div id="templatetriggerdiv">
		<table width="100%">
			<tr>
				<td nowrap style="width: 120px">模板</td>
				<td>
					<select id="template_type" class="easyui-combobox" name="template_type" >
						<option value="template1" selected>每天定时</option>
					</select>
				</td>
			</tr>
			<tr id="template1div">
				<td nowrap style="width: 120px">执行时间</td>
				<td><input type="text" id="executeTime" name="executeTime" size="20"></td>
			</tr>
		</table>
	</div>
	<div id="crontriggerdiv">
		<table width="100%">
			<tr>
				<td nowrap style="width: 120px">Cron表达式</td>
				<td><input id="cron_expression" name="cron_expression" value=""></td>
			</tr>
		</table>
	</div>
	<div id="simpletriggerdiv">
		<table width="100%">

			<tr>
				<td nowrap style="width: 120px">开始时间</td>
				<td><input type="text" id="startTime" name="startTime"
					size="20"></td>
			</tr>
			<tr>
				<td nowrap style="width: 120px">结束时间</td>
				<td><input type="text" id="endTime" name="endTime"></td>
			</tr>
			<tr>
				<td nowrap style="width: 120px">执行次数</td>
				<td><input type="text" id="repeatCount" name="repeatCount"></td>
				<td><a href="#" title="表示Trigger启动后执行多少次结束，不填写默认只执行一次，-1是无限循环" class="easyui-tooltip">Tip（执行次数）</a></td>
			</tr>
			<tr>
				<td nowrap style="width: 120px">执行间隔</td>
				<td><input type="text" id="repeatInterval" class="easyui-numberbox" name="repeatInterval"> 
					<select id="unit" class="easyui-combobox" name="unit">
						<option value="1000">秒</option>
						<option value="60000">分钟</option>
						<option value="3600000">小时</option>
						<option value="86400000">天</option>
					</select>
				</td>
				<td><a href="#" title="表示Trigger间隔多长时间执行一次，不填写默认1分钟间隔" class="easyui-tooltip">Tip（执行间隔）</a></td>
			</tr>
		</table>
	</div>
	<table width="100%">
		<tr>
			<td nowrap><a href="#" onclick="addScheduler()" class="easyui-linkbutton" iconCls="icon-add">新增调度</a></td>
		</tr>
	</table>
</form>