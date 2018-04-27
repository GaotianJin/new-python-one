<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/updateDepartmentInit.js"></script>
<div id="tabdiv">
	<form id="salesFormA">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">部门代码</td>
						<td align="left"><input name="financialAdvisorCode" id="financialAdvisorCode" class="table_input"></input></td>
						<td class="table_text" align="right">部门名称</td>
						<td align="left"><input name="salesname" id="salesname" class="table_input"></input></td>
						<td class="table_text" align="right">部门状态</td>
						<td align="left"><select class = "table_select" name="inBusiness"  id = "inBusiness" ></select></td>
					</tr>
					<tr>
						<td class="table_text" align="right">成立日期</td>
						<td align="left"><input class="easyui-datebox" name="inDivisionDate" id="inDivisionDate"></td>
						<td class="table_text" align="right">结束日期</td>
						<td align="left"><select class = "table_select" name="Institutions"  id = "Institutions" ></select></td>
						<td class="table_text" align="right">不责任姓名</td>
						<td align="left"><input name="financialAdvisorCode" id="financialAdvisorCode" class="table_input"></input></td>
					</tr>
					<tr>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left" colspan="5"><input name="Street" id="Street" class="table_input" value=""></input></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="addDepartment()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
				</div>
			</div>
		</div>
	</form>
	<form id="salesFormB">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="归属信息"
			iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">上级机构</td>
						<td align="left" colspan="2"><input name="salesname" id="salesname" class="table_input">-<input name="salesname" id="salesname" class="table_input"></input></td>
						<td class="table_text" align="right">归属开始日期</td>
						<td align="left" colspan="2"><input name="salesname" id="salesname" class="table_input"></input></td>
						<td class="table_text" align="right">归属结束日期</td>
						<td align="left" colspan="2"><input name="salesname" id="salesname" class="table_input"></input></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="addDepartment()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a><a href="#" onclick="addDepartment()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">删除</a>
				</div>
			</div>
		</div>
	</form>
</div>
<div style="margin-top: 3px;" id="tabdiv">
	<table id="updatedepartTable"></table>
</div>