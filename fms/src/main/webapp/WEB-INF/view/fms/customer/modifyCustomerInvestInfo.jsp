<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/modifyCustomerInvestInfoInit.js"></script>

<input type="hidden" name="customerNo" id="modifyCustomercustomerNo_Id" value="${customerNo}">
<input type="hidden" name="custBaseInfoId" id="modifyCustomerBaseInfo_Id" value="${custBaseInfoId}">
<input type="hidden" name="agentId"               id="modifyCustInvest_agentId"     value="${agentId}">
<input type="hidden" name="loadFlag" 			id="modifyCustomerBaseInfo_loadFlag" 		value="${loadFlag}" />
<input type="hidden" name="tradeLoadFlag" id="modifyCustInvest_tradeLoadFlag" value="${tradeLoadFlag}">


<%--    <div id = "modifyCustomerInvestInfoInitDiv" class="easyui-panel" title="其它投资信息" collapsible="true">
   其他投资记录
		<table id="modifyCustomerOtherInvestTable"></table>
		<div id="modifyCustomerOtherInvestTable_tb1">
			<div id="modifyCustomerOtherInvestTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true"
				id="modifyCustomerOtherInvestTableAddOneRowId" onclick="modifyCustomerOtherInvestTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true"
				id="modifyCustomerOtherInvestTableRemoveOneRowId" onclick="modifyCustomerOtherInvestTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true"
				id="modifyCustomerOtherInvestTableLockOneRowId" onclick="modifyCustomerOtherInvestTableLockOneRow()">锁定</a>
				<b style="float:right;text-align:center"><font color="red">投资总额：</font><span id="investTotalAmount"></span>(元)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
			</div>			
	    </div>
	</div>2017/4/24日注（chengong），新增海外，去掉其它投资记录  --%>
	  
	<div id = "modifyCustomerInvestInfoInitDiv1" class="easyui-panel" title="我司投资股权类信息" collapsible="true">
	<%--我司投资记录 --%>	
		<table id="modifyCustomerMyInvestTable"></table>
		<div id="modifyCustomerMyInvestTable_tb" style="height:auto">
		    <b style="float:right;text-align:center"><font color="red">投资总额：</font><span id="myInvestTotalAmount"></span>(元)&nbsp;<font color="red">存续金额：</font><span id="myInvestRemainAmount"></span>(元)</b>
		</div>
    </div>
    <div id = "modifyCustomerInvestInfoInitDiv2" class="easyui-panel" title="我司投资固定类信息" collapsible="true">
		
		<div id="modifyCustomerMyInvestTable02_tb" style="height:auto">
		    <b style="float:right;text-align:center"><font color="red">投资总额：</font><span id="myInvestTotalAmount02"></span>(元)&nbsp;<font color="red">存续金额：</font><span id="myInvestRemainAmount02"></span>(元)</b>
		</div>
		<table id="modifyCustomerMyInvestTable02"></table>
    </div>
    <div id = "modifyCustomerInvestInfoInitDiv3" class="easyui-panel" title="我司投资浮动类信息" collapsible="true">
		
		<div id="modifyCustomerMyInvestTable03_tb" style="height:auto">
		    <b style="float:right;text-align:center"><font color="red">投资份额：</font><span id="myInvestTotalShare"></span>(份)&nbsp;<font color="red">存续份额：</font><span id="myInvestRemainShare"></span>(份)</b>
		</div>
		<table id="modifyCustomerMyInvestTable03"></table>
    </div>
    
    <!-- 我司海外投资信息 -->
     <div id = "modifyCustomerInvestInfoInitDiv4" class="easyui-panel" title="我司投资海外类信息" collapsible="true">		
		<div id="modifyCustomerMyInvestTable04_tb" style="height:auto">
		    <b style="float:right;text-align:center"><font color="red">投资份额：</font><span id="myInvestTotalShare02"></span>(份)&nbsp;<font color="red">存续份额：</font><span id="myInvestRemainShare02"></span>(份)</b>
		</div>
		<table id="modifyCustomerMyInvestTable04"></table>
    </div>
   <!-- <div style="margin-bottom: 3px;">
	<a href="#" onclick="submitAllCustomerInvestInfo()" id="modifyCustomerInvestInfoInit_submitCarInfoButton" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
	<a href="#" onclick="modifyCustomerInvestInfoInitBack()" id="modifyCustomerInvestInfoInit_backButton" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
    </div> -->
</div>