<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/listSalaryInit.js"></script>
<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="基本工资信息" collapsible="true">
		<form id="listSalaryForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">月份</td>
						<td align="left"><span class="comboSpan"></span><input name="listSalary_year" id="listSalary_year" class="table_input easyui-combobox1" data-options="required:true">年
						<input name="listSalary_month" id="listSalary_month" class="table_input easyui-combobox1" data-options="required:true">月</td>
						<td>&nbsp;</td>
						<td align="left"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearSalaryCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="querySalaryList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="salaryTable"></table>
		</div>
	</div>
	
</div>


