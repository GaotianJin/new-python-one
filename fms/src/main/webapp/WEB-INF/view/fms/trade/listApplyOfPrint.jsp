<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/listApplyOfPrintInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div id="tabdiv">
	<form id="ApplyOfPrintForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="交易信息公共池查询" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">交易号码</td>
						<td align="left"><input name = "printNo" id="printNo" class="table_input"></td>
						<td class="table_text" align = "right">交易类型</td>
						<td align="left"><span class="comboSpan"></span><input name="printType"  id = "printType" class = "table_input"></td>
						<td class="table_text" align = "right">投保单号/理财协议号</td>
						<td align="left"><input name = "printInfoNo" id="printInfoNo" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">所属机构</td>
						<td align="left"><span class="comboSpan"></span><input name = "printcompanyId" id="printcompanyId" class="table_input"></td>
						<td class="table_text" align = "right">交易机构</td>
						<td align="left"><span class="comboSpan"></span><input name = "printComId" id="printComId" class="table_input"></td>
						<td class="table_text" align = "right">交易日期</td>
						<td>
						<span class="comboSpan"></span>
						<input name="printDateFrom" id="printDateFrom" class="table_input2 easyui-datebox">
						至
						<input name="printDateEnd" id="printDateEnd" class="table_input2 easyui-datebox">
						</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">所属网点</td>
						<td align="left"><span class="comboSpan"></span><input name="printstoreId"  id = "printstoreId" class = "table_input"></td>
						<td class="table_text" align = "right">交易网点</td>
						<td align="left"><span class="comboSpan"></span><input name = "printStoreId" id="printStoreId" class="table_input"></td>
						<td class="table_text" align = "right">录入日期</td>
						<td>
						<span class="comboSpan"></span>
						<input name="printInputFrom" id="printInputFrom" class="table_input2 easyui-datebox">
						至
						<input name="printInputEnd" id="printInputEnd" class="table_input2 easyui-datebox">
						</td>
					</tr>
					<tr>
						<td class="table_text" align = "right">币种</td>
						<td align="left"><span class="comboSpan"></span><input name="printcurrency"  id = "printcurrency" class = "table_input"></td>
						<td class="table_text" align = "right">财富顾问</td>
						<td align="left"><span class="comboSpan"></span><input name = "printagentId" id="printagentId" class="table_input"></td>
						<td class="table_text"  align = "right">&nbsp;</td>
						<td class="table_text"  align = "right">&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearApplyOfPrintInit()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchApplyOfPrintInit()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
</div>

<form>
	<div style="margin-top: 3px;" id="tabdiv">
		<table id="applyOfPrintTable"></table>
	</div>
	<div>
		<a href="#" onclick="printInit()" class="easyui-linkbutton e-cis_button" iconCls="icon-print">打印</a>
	</div>
</form>


<div id="applyOfPrintTab" class="easyui-tabs" fit="true" border="ture"  plain="true"></div>