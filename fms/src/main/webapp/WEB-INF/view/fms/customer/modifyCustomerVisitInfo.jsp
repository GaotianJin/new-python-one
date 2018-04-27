<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/modifyCustomerVisitInfoInit.js"></script>

<input type="hidden" name="custBaseInfoId" id="modifyCustVisit_BaseInfoId" value="${custBaseInfoId}">
<input type="hidden" name="agentId" id="modifyCustVisit_agentId" value="${agentId}">
<input type="hidden" name="loadFlag" 			id="modifyCustomerBaseInfo_loadFlag" 		value="${loadFlag}" />
<input type="hidden" name="tradeLoadFlag" id="modifyCustVisit_tradeLoadFlag" value="${tradeLoadFlag}">

<div id = "modifyCustomerVisitInfoDiv">
   <div id = "modifyCustomerVisitInfoDiv1" class="easyui-panel" title="拜访信息" collapsible="true">
		<table id="modifyCustomerVisitTable"></table>
		<div id="modifyCustomerVisitTable_tb" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="modifyCustomerVisitTableAddOneRow()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="modifyCustomerVisitTableRemoveOneRow()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="modifyCustomerVisitTableLockOneRow()">锁定</a>
		</div>
   </div>
   <div style="margin-bottom: 3px;">
	<a href="#" onclick="submitAllCustomerVisitInfo()" id="modifyCustomerVisitInfo_submitCarInfoButton" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
	<!-- <a href="#" onclick="modifyCustomerVisitInfoBack()" id="modifyCustomerVisitInfo_backButton" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a> -->
    </div>
</div>