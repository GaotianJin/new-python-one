<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/listDepartmentInit.js"></script>
<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="部门信息查询" collapsible="true">
		<form id="departmentForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align = "right">上级机构</td>
						<td align="left"><span class="comboSpan"></span><input name="comId"  id = "ywbParentComCode" class = "table_input"></td>
						<td class="table_text" align = "right">部门名称</td>
						<td align="left"><span class="comboSpan"></span><input name = "departmentId" id="ywbdepartmentId" class="table_input"></td>
					</tr>
				</table>
				<div style="margin-bottom: 4px; margin-top: 4px; margin-left: 5px;">
					<a href="#" onclick="clearForm()" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'">清空</a> <a href="#"
						onclick="searchDepartment()" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="departmentTable"></table>
		</div>
	</div>
</div>
