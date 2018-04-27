<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/querySalaryDetailInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<div id="tabdiv" class="outerPanel">
	<form id="salaryDetailForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="工资明细" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">月份</td>
						<td align="left"><span class="comboSpan"></span><input name="salayDetail_year" id="salayDetail_year" class="table_input easyui-combobox1" data-options="required:true">年
						<input name="salayDetail_month" id="salayDetail_month" class="table_input easyui-combobox1" data-options="required:true">月
						<span class="comboSpan"></span>
						至
						<span class="comboSpan"></span>
						<input name="salayDetail_endyear" id="salayDetail_endyear" class="table_input easyui-combobox1" >年
						<input name="salayDetail_endmonth" id="salayDetail_endmonth" class="table_input easyui-combobox1" >月</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="querySalaryDetail()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
	<form>
		<div>
		    <table id="generalSalaryTable"></table>
		</div>
	</form>
</div>
