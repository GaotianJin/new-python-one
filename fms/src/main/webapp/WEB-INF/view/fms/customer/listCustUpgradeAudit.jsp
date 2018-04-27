<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.sinosoft.util.LoginInfo"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/listCustUpgradeAuditInit.js"></script>
<script type="text/javascript" >
	userId = '<%=((LoginInfo)request.getAttribute("userSessionInfo")).getUserId() %>';
	</script>
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="客户信息" collapsible="true">
		 <form id="listCustUpgradeAudit_queryConditionForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td class="table_text" align="right">客户号</td>
						<td><input name="customerNo" id="listCustUpgradeAudit_CustomerNo" class="table_input"/></td>
						<td class="table_text" align="right">中文姓名</td>
						<td align="left"><input name="chnName" id="listCustUpgradeAudit_ChnName" class="table_input"/></td>
						<td class="table_text" align="right">财富顾问</td>
						<td>
							<span class="comboSpan"></span>
							<input name="agentId" id="listCustUpgradeAudit_AgentId" class="table_input"/>
						</td>
					</tr>
	
				</table>
				<div>
					<a href="#" onclick="clearAuditForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryCustomerAuditList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listCustUpgradeAudit_CustomerTable"></table>
		</div>
	</div>	
</div>
