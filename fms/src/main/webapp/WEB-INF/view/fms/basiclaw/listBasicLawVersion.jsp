<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/basiclaw/listBasicLawVersionInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="基本法版本设置" iconCls="icon-ok" collapsible="true">
		<form id="list_basicLawVersionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">版本编号</td>
						<td align="left"><input name = "versionCode" id="listv_versionCode" class="table_input"></td>
						<td class="table_text" align = "right">版本名称</td>
						<td align="left"><input name = "versionName" id="listv_versionCode" class="table_input"></td>
						<td class="table_text" align = "right">执行状态</td>
						<td><span class="comboSpan"></span><input class="table_select easyui-combobox"  id="listv_execState" name="execState"></td>
					</tr>
				<!-- 	<tr>
						<td class="table_text" align="right">执行开始日期</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="execStartDate" id="listv_execStartDate"
						data-options="validType:['length[0,10]','validDate']"/></td>
						<td class="table_text" align="right">至</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="execStartToDate" id="listv_execStartDate"
						data-options="validType:['length[0,10]','validDate']"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行结束日期</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="execEndDate" id="listv_execEndDate"
						data-options="validType:['length[0,10]','validDate']"/></td>
						<td class="table_text" align="right">至</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="execEndToDate" id="listv_execEndDate"
						data-options="validType:['length[0,10]','validDate']"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr> -->
				</table>
				<div>
					<a href="#" onclick="clearBasicLawVersionFormList()" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchBasicLawVersionInfo()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="list_basicLawVersionTable"></table>
		</div>
	</div>
</div>