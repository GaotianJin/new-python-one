<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/generalBaseSalaryInit.js"></script>


<input type="hidden" name="month" id="generalBaseSalary_month" class="inpuntHidden" value="${month}">
<div id="tabdiv10" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="薪资信息" collapsible="true">
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="generalBaseSalaryTable"></table>
		</div>
		<div>
					<a href="#" onclick="back()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</div>
</div>


