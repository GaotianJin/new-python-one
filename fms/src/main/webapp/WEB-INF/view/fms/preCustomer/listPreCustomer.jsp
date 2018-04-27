<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/preCustomer/listPreCustomerInit.js"></script>

<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="准客户信息" collapsible="true">
		 <form id="listPreCust_queryConditionForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
				        <td class="table_text" align="right">分公司</td>
                        <td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="listPreCust_comId" name="comId"></td>
						<td class="table_text" align="right">网点</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="listPreCust_storeId" name="storeId"></td>
						<!-- <td class="table_text" align="right">财富顾问工号</td>
						<td><input class="table_input easyui-validatebox" id="listPreCust_agentCode" name="agentCode"></td> -->
						<td class="table_text" align="right">财富顾问</td>
						<td><span class="comboSpan"></span>
							<input class="table_input esayui-combobox1" id="listPreCust_agentId" name="agentId"></td>
					</tr>
					<tr>
					    
						<td class="table_text" align="right">准客户姓名</td>
						<td align="left"><input class="table_input easyui-validatebox" name="preCustName" id="listPreCust_preCustName" /></td>
						<td class="table_text" align="right">拜访时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input2 easyui-datebox" name="preCustVisitTimeStart" id="listPreCust_visitorTimeBegin"/>
							至
							<input class = "table_input2 easyui-datebox" name="preCustVisitTimeEnd"  id = "listPreCust_visitorTimeEnd" />
							<td class="table_text" align="right">团队</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="listPreCust_departmentId" name="departmentId"></td>
						</td>
					</tr>
					<!-- <tr>
					
					<td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
					</tr> -->
	
				</table>
				<div>
					<a href="#" onclick="clearPreCustomer()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryPreCustomerList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listPreCust_preCustomerTable"></table>
		</div>
	</div>	
</div>
