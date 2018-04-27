<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/basiclaw/addBasicLawProductInit.js"></script>
<div id="tabdiv">
	<form id="add_basiclawProductForm">
	<!-- 基本信息 -->
		<div id="add_basiclawProductDiv" class="easyui-panel" title="基本信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">版本编号</td>
						<td align="left"><span class="comboSpan"><select name="basicLawId" id="add_versionCode" class="table_select easyui-combobox easyui-validatebox" 
						data-options="required:true"></td>
						<td class="table_text" align="right">版本名称</td>
						<td align="left"><input name="versionName" id="add_versionName" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">执行状态</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox" id="add_execState" name="execState" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行开始日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox " name="execStartdate" id="add_execStartDate"disabled="disabled" /></td>
						<td class="table_text" align="right">执行结束日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox " name="execEnddate" id="add_execEndDate" disabled="disabled"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">产品类型</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox easyui-validatebox" id="add_productType" name="productType"
						data-options="required:true"></td>
						<td class="table_text" align="right">产品子类</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox easyui-validatebox" id="add_productSubType" name="productSubType"
						data-options="required:true"></td>
						<td class="table_text" align="right">产品</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox " id="add_productId" name="productId"></td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		</form>
			<!--财富产品薪资参数-奖金比例 -->
			<div id="basicLawWealthInfo">
				<div  class="easyui-panel" title="财富产品薪资参数-奖金比例" collapsible="true" >
					<div id="basicLawWealthTable_tb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="BasicLawWealthTableAddOneRow()">新增</a> 
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="BasicLawWealthTableRemoveOneRow()">删除</a> 
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="BasicLawWealthTableLockOneRow()">锁定</a>
					</div>
					<div class="top_table">
					    <table id="BasicLawWealthTable"></table>
					</div>
				</div>
				<br>
			</div>
	        <!--银行保险薪资参数-奖金比例 -->
	        <div id="BasicLawYBInfo">
				<div class="easyui-panel" title="银行保险薪资参数-奖金比例"  collapsible="true">
					<div id="BasicLawYBTable_tb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="BasicLawYBTableAddOneRow()">新增</a> 
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="BasicLawYBTableRemoveOneRow()">删除</a> 
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="BasicLawYBTableLockOneRow()">锁定</a>
					</div>
					<div class="top_table">
				    	<table id="BasicLawYBTable"></table>
					</div>
				</div>
				<br>
			</div>
			
			 <!--个人寿险薪资参数-奖金比例 -->
			<div id="BasicLawInsInfo">
				<div  class="easyui-panel" title="个人寿险薪资参数-奖金比例"  collapsible="true">
					<div id="BasicLawInsTable_tb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="BasicLawInsTableAddOneRow()">新增</a> 
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="BasicLawInsTableRemoveOneRow()">删除</a> 
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="BasicLawInsTableLockOneRow()">锁定</a>
					</div>
					<div class="top_table">
				    	<table id="BasicLawInsTable"></table>
					</div>
				</div>
				<br/>
			</div>
		<!--考核参数信息-->
		<div id="BasicLawAssess" class="easyui-panel" title="考核参数信息"  collapsible="true">
			<div class="top_table">
				<div id="BasicLawAssessTable_tb" style="height: auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="BasicLawAssessTableAddOneRow()">新增</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="BasicLawAssessTableRemoveOneRow()">删除</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="BasicLawAssessTableLockOneRow()">锁定</a>
				</div>
				<div class="top_table">
			    	<table id="BasicLawAssessTable"></table>
				</div>
			</div>
		</div>
		<div>
			<a href="#" onclick="addBasicLawSave()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			<a href="#" onclick="backListBasicLawProductPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>	
		</div>
</div>

