<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/detailDepartmentInit.js"></script>
<div id="tabdiv">
	<div id="department">
		<div id="departmentBasic" class="easyui-panel" title="基本信息" collapsible="false">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="input_table">
					<tr>
						<td class="table_text" align="right">部门</td>
						<td align="left"><input name="departmentCode" id="departmentCode" class="table_input"  disabled="disabled"></td>
						<td class="table_text" align="right">部门名称</td>
						<td align="left"><input name="departmentName" id="departmentName" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">负责人姓名</td>
						<td align="left"><input name="managerName" id="managerName" class="table_input" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">成立日期</td>
						<td align="left"><span class="comboSpan"></span><input name="foundDate" id="foundDate" class="table_input easyui-datebox" disabled="disabled"></td>
						<!-- <td class="table_text" align="right">结束日期</td>
						<td align="left"><span class="comboSpan"></span><input  name="endDate" id = "endDate" class = "table_input easyui-datebox" disabled="disabled"></input></td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left"><input name="managerMobile" id="managerMobile" class="table_input" disabled="disabled"></input></td> -->
						<td class="table_text" align="right">部门状态</td>
						<td align="left"><span class="comboSpan"></span><input name="state"  id = "ywbstate" class="table_select easyui-combobox"  disabled="disabled"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<!-- <tr>
						<td class="table_text" align="right">部门状态</td>
						<td align="left"><span class="comboSpan"></span><input name="state"  id = "ywbstate" class="table_select easyui-combobox"  disabled="disabled"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr> -->
				</table>
			</div>
		</div>
		<div id="smsaccordion" class="easyui-panel" title="归属信息" collapsible="false">
			<div class="top_table">
				<table id="departmentBelongInfoTable"></table>
			</div>
		</div>
		<div>
			<a href="#" onclick="backListdetailDepartmentPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</div>
	
</div>