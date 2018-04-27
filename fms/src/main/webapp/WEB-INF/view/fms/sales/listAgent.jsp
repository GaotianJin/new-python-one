<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/listAgentInit.js"></script>
<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="员工信息" collapsible="true">
		<form id="listAgent_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">工号</td>
						<td align="left"><input name="agentCode" id="agentCode" class="table_input"></td>
						<td class="table_text" align="right">姓名</td>
						<td align="left"><span class="comboSpan"></span><input name="agentId" id="agentId" class="table_input"></td>
						<td class="table_text" align="right">所属机构</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="comId" name="comId"></td>
					</tr>
					<tr>
						<!-- <td class="table_text" align="right" style="display:none;">所属网点</td>
						<td align="left" style="display:none;"><span class="comboSpan"></span><input class="table_input"  id="storeId" name="storeId"></td> -->
						<td class="table_text" align="right">所属部门</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="departmentId" name="departmentId"></td>
						<td class="table_text" align="right">在职状态</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="workState" name="workState"></td>
					<td class="table_text" align="right">职级</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="positionCode" name="positionCode"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">入司时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="joinDateBegin" id="joinDateBegin" class="table_input2 easyui-datebox"></input>至
							<input name="joinDateEnd" id="joinDateEnd" class="table_input2 easyui-datebox"></input>
						</td>
						<td class="table_text" align="right">级别</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="gradeCode" name="gradeCode"></td>
					    <td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<!-- <tr>
						<td class="table_text" align="right">级别</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="gradeCode" name="gradeCode"></td>
					</tr> -->
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;"> -->
				<div>
					<a href="#" onclick="clearQueryAgentCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryAgentList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="agentTable"></table>
		</div>
	</div>
	
</div>


