<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/salaryCalculationListInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<!--薪资计算页面 -->
<div id="tabdiv" class="outerPanel">
	<form id="salesAccForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="薪资结算" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">薪资计算年</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input" id="salaycal_calYear" name="calYear"></td>
						<td class="table_text" align="right">薪资计算月</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input " id="salaycal_calMonth" name="calMonth"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="salaryCal()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">结算</a>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchSalary()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
	<form>
		<div>
			<table id="salaryCalTable"></table>
		</div>
		<div>
			<a href="#" id="dd" class="easyui-linkbutton e-cis_button" iconCls="icon-add" onclick="salaryForward()">结转</a>
		</div>
	</form>
</div>






