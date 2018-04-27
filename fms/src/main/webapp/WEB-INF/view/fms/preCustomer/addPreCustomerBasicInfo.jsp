<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 引入公共js文件和初始化各个控件的js文件 -->
<script type="text/javascript" src ="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/preCustomer/addPreCustomerBasicInfoInit.js"></script>


<div id="addPreCustomerBasicInfo" title="准客户基本信息添加">
	<!-- 准客户基本信息选择与输入区 -->
	<form id="preCustomerBasicInfoForm">
		<!-- 准客户基本信息 -->
		<div id="preCustomerBasicInfoDiv" class="easyui-panel" title="准客户基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">中文姓名</td>
						<td align="left">
							<input name="preCustName" id="preCustName_input" class="table_input easyui-validatebox" data-options="required:true"> </td>
						<td class="table_text" align="right">手机号</td>
						<td align="left">
							<input name="preCustMobile" id="mobile_input" class="table_input easyui-validatebox"  data-options="validType:['length[0,20]','validPhone']"></td>
						<td class="table_text" align="right">性别</td>
						<td>
							<span class="comboSpan"></span>
							<input name="preCustSex" id="sex_select" class="table_input easyui-combobox1"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">客户类型</td>
						<td align="left"> 
							<span class="comboSpan"></span>
							<input name="preCustType" id="custType_select" class="table_input easyui-combobox1"> </td>
							<td class="table_text" align="right">楼号</td>
						<td align="left"> <input name="preCustBuildingNo" id="buildingNo_input" class="table_input"/></td>
						<td class="table_text" align="right">获客方式</td>
						<td><span class="comboSpan"></span>
							<input name="preCustObtainWay" id="preCustTypeObtain_select" class="table_input easyui-combobox1" ></td>
						</tr>
				</table>
			</div>
		</div>
	</form>
	
	<!-- 准客户拜访信息 -->
	<div id="preCustomerVisitInfomation">
		<div  class="easyui-panel" title="准客户拜访信息" collapsible="true">
			<div id="preCustVisitInfo" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true"   onclick="preCustomerVisitInfoTableAddOneRow()">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true"   onclick="preCustomerVisitInfoTableRemoveOneRow()">删除</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="preCustomerVisitInfoTableLockOneRow()">锁定</a>
			</div>
			<div class="top_table">
			    <table id="preCustomerVisitInfoTable"></table>
			</div>
		</div>
		<br>
	</div>

	<!-- 保存与返回 -->
	<div>
		<a href="#" onclick="addPreCustomerBasicInfoAndVistInfoSave()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
		<a href="#" onclick="backPreCustomerBasicInfoPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>	
	</div>
</div>


