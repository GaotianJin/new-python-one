<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/basiclaw/detailBasicLawProductInit.js"></script>
<div id="tabdiv">
	<form id="detail_basiclawProductForm">
	<!-- 基本信息 -->
		<div id="detail_basiclawProductDiv" class="easyui-panel" title="基本信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">版本编号</td>
						<td align="left"><span class="comboSpan"><input name="basicLawId" id="detail_versionCode" class="table_select easyui-combobox" disabled="disabled"></td>
						<td class="table_text" align="right">版本名称</td>
						<td align="left"><input name="versionName" id="detail_versionName" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">执行状态</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox" id="detail_execState" name="execState" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行开始日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox " name="execStartdate" id="detail_execStartDate"disabled="disabled" /></td>
						<td class="table_text" align="right">执行结束日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox " name="execEnddate" id="detail_execEndDate" disabled="disabled"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">产品类型</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox" id="detail_productType" name="productType" disabled="disabled"></td>
						<td class="table_text" align="right">产品子类</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox" id="detail_productSubType" name="productSubType" disabled="disabled"></td>
						<td class="table_text" align="right">产品</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox" id="detail_productId" name="productId" disabled="disabled"></td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		</form>
			<!--财富产品薪资参数-奖金比例 -->
			<div id="basicLawWealthInfo">
				<div  class="easyui-panel" title="财富产品薪资参数-奖金比例" collapsible="true" >
					<div class="top_table">
					    <table id="BasicLawWealthTable"></table>
					</div>
				</div>
				<br>
			</div>
	        <!--银行保险薪资参数-奖金比例 -->
	        <div id="BasicLawYBInfo">
				<div class="easyui-panel" title="银行保险薪资参数-奖金比例"  collapsible="true">
					<div class="top_table">
				    	<table id="BasicLawYBTable"></table>
					</div>
				</div>
				<br>
			</div>
			
			 <!--个人寿险薪资参数-奖金比例 -->
			<div id="BasicLawInsInfo">
				<div  class="easyui-panel" title="个人寿险薪资参数-奖金比例"  collapsible="true">
					<div class="top_table">
				    	<table id="BasicLawInsTable"></table>
					</div>
				</div>
				<br/>
			</div>
		<!--考核参数信息-->
		<div id="BasicLawAssess" class="easyui-panel" title="考核参数信息"  collapsible="true">
			<div class="top_table">
				<div class="top_table">
			    	<table id="BasicLawAssessTable"></table>
				</div>
			</div>
		</div>
		<div>
			<a href="#" onclick="backListBasicLawProductPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
</div>
