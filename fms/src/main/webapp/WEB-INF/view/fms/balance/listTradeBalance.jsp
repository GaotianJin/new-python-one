<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/balance/listTradeBalanceInit.js"></script>

<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="结算条件" collapsible="true">
		<form id="TradeBalanceQueryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">管理机构</td>
						<td>
							<span class="comboSpan"></span>
							<input name="comId" id="tradeComId" class="table_input easyui-combobox1">
						</td>
					<!-- 	<td class="table_text" align="right" style="display:none;">网点</td>
						<td align="left" style="display:none;">
							<span class="comboSpan"></span>
							<input name="storeId" id="tradeStoreId" class="table_input easyui-combobox1">
						</td> -->
						<td class="table_text" align="right">部门</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="departmentId" id="tradeDepartmentId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="agencyComId" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">产品类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productType" id="productType" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">产品子类</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productSubType" id="productSubType" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">产品</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="productId" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">财富顾问</td>
						<td>
							<span class="comboSpan"></span>
							<input name="agentId" id="tradeAgentId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">交易号码</td>
						<td>
							<input name="tradeNo" id="tradeNo" class="table_input"/>
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">统计起期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="startDate" id="startDate" 
								data-options="required:true"/>
						</td>
						<td class="table_text" align="right">统计止期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "easyui-datebox table_input"  name="endDate"  id = "endDate" 
								data-options="required:true"/>
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>

				</table>
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryBalanceTradeInfoList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="tradeBalanceAndExport()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出</a>
					<%-- <a href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/viewController?method=exceltest');"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo">Excel测试</a>
					<a href="#" onclick="excelTest()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo">Excel测试</a> --%>
				</div>
			</div>
			</form>
			<div style="margin-top: 3px;" id="tabdiv">
				<table id="TradeBalanceTable"></table>
			</div>
	</div>
</div>
