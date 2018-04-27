<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/addDepartmentInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div id="tabdiv">
	<form id="addDepartmentForm">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">部门代码</td>
						<td align="left"><input name="departmentCode" id="adddepartmentCode" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,20]','validCode']"></td>
						<td class="table_text" align="right">部门名称</td>
						<td align="left"><input name="departmentName" id="adddepartmentName" class="table_input easyui-validatebox" 
							data-options="required:true,validType:['length[0,30]']"></input></td>
						<td class="table_text" align="right">部门状态</td>
						<td align="left"><span class="comboSpan"></span><input name="state"  id = "addstate" class = "table_input" 
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td class="table_text" align="right">成立日期</td>
						<td align="left"><span class="comboSpan"></span><input name="foundDate" id="addfoundDate" class="table_input easyui-datebox" 
							data-options="validType:['length[0,10]','validDate']"></input></td>
						<!-- <td class="table_text" align="right">负责人姓名</td>
						<td align="left"><input name="managerName" id="addmanagerName" class="table_input"></td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left"><input name="managerMobile" id="addmanagerMobile" class="table_input easyui-validatebox" 
							data-options="validType:['length[0,20]','validPhone']"></td> -->
						<td class="table_text" align="right">结束日期</td>
						<td align="left"><span class="comboSpan"></span><input name="endDate" id="addendDate" class="table_input easyui-datebox" 
							data-options="validType:['length[0,10]','validDate']"></input></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<!-- <tr>
						<td class="table_text" align="right">结束日期</td>
						<td align="left"><span class="comboSpan"></span><input name="endDate" id="addendDate" class="table_input easyui-datebox" 
							data-options="validType:['length[0,10]','validDate']"></input></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr> -->
				</table>
			</div>
		</div>
		<div id="smsaccordion" class="easyui-panel" title="归属信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">上级机构</td>
						<td align="left"><span class="comboSpan"></span><input name="comId" id="addParentComCode" class="table_input" 
							data-options="required:true"></input></td>
						<td class="table_text" align="right">归属开始日期</td>
						<td align="left"><span class="comboSpan"></span><input name="belongStartDate" id="addbelongStartDate" class="table_input easyui-datebox" 
							data-options="required:true,validType:['length[0,10]','validDate']"></input></td>
						<td class="table_text" align="right">归属结束日期</td>
						<td align="left"><span class="comboSpan"></span><input name="belongEndDate" id="addbelongEndDate" class="table_input easyui-datebox" 
							data-options="validType:['length[0,10]','validDate']"></input></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div>
	<a href="#" onclick="addDepartment()" class="easyui-linkbutton" iconCls="icon-add">提交</a>
	<a href="#" onclick="backListaddDepartmentPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
</div>
</div>