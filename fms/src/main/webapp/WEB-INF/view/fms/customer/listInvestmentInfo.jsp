<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.sinosoft.util.LoginInfo"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/listInvestmentInfoInit.js"></script>
<script type="text/javascript" >
	userId = '<%=((LoginInfo)request.getAttribute("userSessionInfo")).getUserId() %>';
	</script>
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="客户信息" collapsible="true">
		 <form id="listCustomer_queryConditionForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td class="table_text" align="right">分公司</td>
						<td>
							<span class="comboSpan"></span>
							<input name="comId" id="lisCustomer_comId" class="table_input"/>
						</td>
						<td class="table_text" align="right">部门</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="departmentId" id="lisCustomer_DepartmentId" class="table_input"/>
						</td>
						<!-- <td class="table_text" align="right" style="display:none;">网点</td>
						<td align="left" style="display:none;">
							<span class="comboSpan"></span>
							<input class = "table_input" name="storeId"  id = "lisCust_StoreId" />
						</td> -->
							<td class="table_text" align="right">财富顾问</td>
						<td>
							<span class="comboSpan"></span>
							<input name="agentId" id="lisCustomer_AgentId" class="table_input"/>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">客户号</td>
						<td><input name="customerNo" id="lisCustomer_CustomerNo" class="table_input"/></td>
						<td class="table_text" align="right">中文姓名</td>
						<td align="left"><input name="chnName" id="lisCustomer_ChnName" class="table_input"/></td>
						<!-- <td class="table_text" align="right">英文姓</td>
						<td align="left"><input class = "table_input" name="lastName"  id = "lisCust_LastName" /></td> -->
						<td class="table_text" align="right">客户级别</td>
						<td>
							<span class="comboSpan"></span>
							<input name="custLevel" id="lisCustomer_custLevel" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<!-- <td class="table_text" align="right">英文名</td>
						<td><input name="firstName" id="lisCust_FirstName" class="table_input"></td>
						<td class="table_text" align="right">证件类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="idType" id="lisCust_IdType" class="table_input easyui-combobox1"></td>
						<td class="table_text" align="right">证件号码</td>
						<td align="left"><input class = "table_input" name="idNo"  id = "lisCust_IdNo" /></td> -->
						<!-- <td class="table_text" align="right">获客方式</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="custObtainWay" id="lisCust_custObtainWay" class="table_input easyui-combobox1"></td> -->
						<td class="table_text" align="right">客户类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "table_input easyui-combobox1" name="custType"  id = "lisCustomer_custType" />
						</td>
						<td class="table_text" align="right">出生月份</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="birthMonth" class="table_input easyui-combobox1" id="ListCustomer_birthMonth">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
	
				</table>
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryCustomerList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
						<!-- <a href="#" onclick="exportCustBaseInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo" id="listCust_button">导出客户基本信息</a> -->
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listCustomer_CustomerTable"></table>
		</div>
	</div>	
</div>

<!-- <div id="customertab" class="easyui-tabs" fit="false" border="ture" plain="true" style="height:180px;"></div> -->