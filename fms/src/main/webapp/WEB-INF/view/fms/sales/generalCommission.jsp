<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/generalCommissionInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<input type="hidden" name="month" id="generalCommission_month" class="inpuntHidden" value="${month}">
<div id="tabdiv" class="outerPanel">
	
	<form>
		<div id="slyCommissionViewId">
			<table id="slyCommissionViewTable"></table>
		</div>
		<div id="saleCommissionId">
			<table id="saleCommissionTable"></table>
		</div>
		<div id="guojinCommissionId">
			<table id="guojinCommissionTable"></table>
		</div>
		<div id="overseasCommissionId">
			<table id="overseasCommissionTable"></table>
		</div>
		<div id="reissueId">
			<table id="reissueTable"></table>
		</div>
		<div id="withholdId">
			<table id="withholdTable"></table>
		</div>
		<div id="projectCommission">
			<table id="projectCommissionTable"></table>
		</div>
	</form>
	<div>
					<a href="#" onclick="back()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
</div>
