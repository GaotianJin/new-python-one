<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sms/smsExtractionInit.js"></script>

<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion1" class="easyui-panel" title="查询条件" collapsible="true">
		<form id="smsExtraction_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="smsExtraction_productId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">短信类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="smsType" id="smsExtraction_smsType" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">短信处理状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox"  id="smsExtraction_smsStatus" name="smsStatus">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">短信生成起期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="smsCreateDateStart" id="smsExtraction_smsCreateDateStart" class="easyui-datebox table_input"
								data-options="validType:['validDate']">
						</td>
						<td class="table_text" align="right">短信生成止期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="smsExtraction_smsCreateDateEnd" name="smsCreateDateEnd"
								data-options="validType:['validDate']">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearSmsExtractionCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="createSms()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">生成短信</a>
					<a href="#" onclick="smsExtraction()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">短信提取</a>
				</div>
			</div>
		</form>
		

		<!-- 产品成立短信表格 -->
		<div class="tableOuterDiv" id="productFoundSmsTableDiv">
			<table id="productFoundSmsTable"></table>
		</div>
		
		<!-- 净值公布短信表格 -->
		<div class="tableOuterDiv" id="publicNetValueSmsTableDiv">
			<table id="publicNetValueSmsTable"></table>
		</div>
		
		<!-- 收益分配短信表格 -->
		<div class="tableOuterDiv" id="incomeDistributeSmsTableDiv">
			<table id="incomeDistributeSmsTable"></table>
		</div>
		
	</div>	

</div>


