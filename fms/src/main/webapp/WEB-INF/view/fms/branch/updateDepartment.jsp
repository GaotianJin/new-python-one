<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/updateDepartmentInit.js"></script>
<div id="updateDepartment">
	<form id="updateDepartmentForm">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="input_table">
					<tr>
						<td class="table_text" align="right">部门代码</td>
						<td align="left"><input name="departmentCode" id="departmentCode" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,20]','validCode']"></td>
						<td class="table_text" align="right">部门名称</td>
						<td align="left"><input name="departmentName" id="departmentName" class="table_input easyui-validatebox" data-options="required:true"></td>
						<td class="table_text" align="right">部门状态</td>
						<td align="left"><span class="comboSpan"></span><input name="state"  id = "ywbstate" class = "table_select easyui-combobox" data-options="required:true"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">成立日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="foundDate" id="foundDate" class="easyui-datebox table_input"  data-options="validType:['length[0,10]','validDate']">
						</td>
						<!-- <td class="table_text" align="right">结束日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input  name="endDate" id = "endDate" class = "easyui-datebox table_input" data-options="validType:['length[0,10]','validDate']">
						</td> -->
						<!-- <td class="table_text" align="right">负责人姓名</td>
						<td align="left">
							<input name="managerName" id="managerName" class="table_input">
						</td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left" colspan="5">
							<input name="managerMobile" id="managerMobile"  class="table_input easyui-validatebox" data-options="validType:['length[0,20]','validPhone']">
						</td> -->
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<!-- <tr>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left" colspan="5"><input name="managerMobile" id="managerMobile"  class="table_input easyui-validatebox" data-options="validType:['length[0,20]','validPhone']"></td>
					</tr> -->
				</table>
			</div>
		</div>
		<div id="smsaccordion" class="easyui-panel" title="归属信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">上级机构</td>
						<td align="left"><span class="comboSpan"></span><select name="comId" id="belongComName" class="table_select easyui-combobox easyui-validatebox" data-options="required:true"></select></td>
						<td class="table_text" align="right">归属开始日期</td>
						<td align="left"><span class="comboSpan"></span><input name="belongStartDate" id="belongStartDate" class="table_input easyui-datebox" data-options="required:true,validType:['length[0,10]','validDate']"></td>
						<td class="table_text" align="right">归属结束日期</td>
						<td align="left"><span class="comboSpan"></span><input name="belongEndDate" id="belongEndDate" class="table_input easyui-datebox" data-options="validType:['length[0,10]','validDate']"></input></td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<span style="display:none"><input name="departmentId" id="departmentId" class="table_input"></span>
		</div>
		<div>
			<a href="#" onclick="updateDepartmentInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
			<a href="#" onclick="backListupdateDepartmentPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>