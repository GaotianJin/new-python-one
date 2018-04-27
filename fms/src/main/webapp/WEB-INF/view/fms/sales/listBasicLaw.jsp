<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/listBasicLawInit.js"></script>
<div id="tabdiv">
	<form id="buildForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="基本法设置" iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">版本编号</td>
						<td align="left"><input name = "buildingcode" id="buildingcode" class="table_input"></td>
						<td class="table_text" align = "right">版本名称</td>
						<td align="left"><input name = "buildingname" id="buildingname" class="table_input"></td>
						<td class="table_text" align = "right">执行状态</td>
						<td><span class="comboSpan"></span><input class="table_input"  id="productSubType" name="productSubType"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行开始日期</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="startDate" id="startDate"/></td>
						<td class="table_text" align="right">至</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="startDate" id="startDate"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行结束日期</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="startDate" id="startDate"/></td>
						<td class="table_text" align="right">至</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="startDate" id="startDate"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearBuilding()" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchBuilding()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
</div>

<div style="margin-top: 3px;" id="tabdiv">
	<table id="basiclawTable"></table>
</div>
<div id="basiclawtab" class="easyui-tabs" fit="false" border="ture"  plain="true"></div>
