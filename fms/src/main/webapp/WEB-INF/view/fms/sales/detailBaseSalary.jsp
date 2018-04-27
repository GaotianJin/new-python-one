<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/detailBaseSalaryInit.js"></script>


<input type="hidden" name="month" id="detailBaseSalary_month" class="inpuntHidden" value="${month}">
<div id="tabdiv10" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="薪资信息" collapsible="true">
		<form id="detailBaseSalaryForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">部门</td>
						<td align="left"><input name="department" id="detailBaseSalary_department" class="table_input"></td>
						<td class="table_text" align="right">姓名</td>
						<td align="left"><input name="chnName" id="detailBaseSalary_chnName" class="table_input"></td>
						<td>&nbsp;</td>
						<td align="left"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearBaseSalaryCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryBaseSalaryList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="detailBaseSalaryTable"></table>
		</div>
		<!-- <div>
					<a href="#" onclick="back()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">返回</a>
				</div> -->
	</div>
</div>

