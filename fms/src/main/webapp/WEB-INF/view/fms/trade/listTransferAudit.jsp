<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/listTransferAuditInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="转让审核"
		collapsible="true">
		<form id="transferAuditForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="input_table">
					<tr>
						<td class="table_text" align="right">客户名</td>
						<td align="left"><input class="table_input"
							id="transferAudit_custName" name="custName"></td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearTransferAudit()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryTransferAuditList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div class="easyui-panel" style="margin-top: 3px;">
			<table id="transferAuditTable"></table>
		</div>
	</div>
</div>