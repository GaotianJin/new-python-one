<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/listCustomerBelongInit.js"></script>
<input type="hidden" name="loadFlag" 			id="addCust_loadFlag" 			value="${loadFlag}">
<input type="hidden" name="tradeId" 				id="addCust_tradeId" 			value="${tradeId}">
<input type="hidden" name="rolePivilege" 	id="addCust_rolePivilege" 	value="${rolePivilege}">
<input type="hidden" name="agentId" 			id="addCust_agentId" 			value="${agentId}">
<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" title="客户信息" collapsible="true">
		<form id="listCustm_queryConditionForm">
			<div>
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">客户号</td>
							<td align="left">
								<input name="customerNo" id="addCust_CustomerNo" class="table_input"/> 
							</td>
							<td class="table_text" align="right">中文姓名</td>
							<td >
								<input name="chnName" id="addCust_ChnName" class="table_input easyui-validatebox"/>
							</td>
							<td class="table_text" align="right">客户级别</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class = "table_input easyui-validatebox" name="custLevel"  id ="lisCustm_custLevel"/>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">分公司</td>
							<td align="left">
							<span class="comboSpan"></span>
								<input name="comId" id="lisCustm_comId" class="table_input easyui-combobox1"/>
							</td>
							<td class="table_text" align="right">业务部</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="departmentId" id="lisCustm_DepartmentId" class="table_input easyui-combobox1"/>
							</td>
							<!-- <td class="table_text" align="right" style="display:none;">网点</td>
							<td align="left" style="display:none;">
								<span class="comboSpan"></span>
								<input name="storeId" id="lisCust_StoreId" class="table_input easyui-combobox1">
							</td> -->
							<td class="table_text" align="right">财富顾问</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class ="table_input easyui-validatebox" name="agentId"  id ="lisCustm_AgentId"/>
							</td>
						</tr>
						<tr>
							
							<!-- <td class="table_text" align="right">在职状态</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox1" name="lisCust_statusName" id="status_id"/>
							</td> -->
							<td align ="right" class= "table_text">调整时间</td>
                       			<td align ="left">
                                 	<span class="comboSpan"></span >
                                 	<input name="adjustStartTime" id= "listPreCustActivity_preCustVisitStartTime" class= "table_input2 easyui-datebox" >
                            			    至
                                 <input name="adjustEndTime" id= "listPreCustActivity_preCustVisitEndTime" class= "table_input2 easyui-datebox" >
                         </td>
                         <td class="table_text" align="right">&nbsp;</td>
						<td align="left" >&nbsp;</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left" >&nbsp;</td>
							
						</tr>
					</table>
					<div id='addCust_CustBaseInfoButton'>
						<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
						<a href="#" onclick="queryCustomList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
						<a href="#" onclick="exportCustomerBelongList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">导出</a>
					</div>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listBelong_CustomerTable"></table>
		</div>
	</div>
</div>
