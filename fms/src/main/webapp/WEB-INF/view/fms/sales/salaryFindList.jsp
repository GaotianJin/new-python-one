<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript">
var contextpath='<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/salaryFindListInit.js"></script>
<!-- 薪资查询 -->
<div id="tabdiv">
	<form id="salaryFindForm">
		<div id="#" class="easyui-panel" fit="true" title="薪资结算信息查询"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">所属机构</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="salaryFind_comId" name="comId"></td>
						<td class="table_text" align="right">网点</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="salaryFind_storeId" name="storeId"></td>
						<td class="table_text" align="right">业务部</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="salaryFind_departmentId" name="departmentId"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">财富顾问</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="salaryFind_agentId" name="agentId"></td>
						<td class="table_text" align="right">薪资计算年</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="salaryFind_calYear" name="calYear"></td>
						<td class="table_text" align="right">薪资计算月</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="salaryFind_calMonth" name="calMonth"></td>
						<!-- <td class="table_text" align="right">职级</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="salaryFind_position" name="positionCode"></td>
						<td class="table_text" align="right">级别</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="salaryFind_grade" name="gradeCode"></td> -->
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearAgentWage()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchAgentWage()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="AgentWageExport()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出</a>
				</div>
			</div>
		</div>
	</form>
</div>

<div style="margin-top: 3px;" id="tabdiv">
	<table id="saleSearchTable"></table>
</div>
<div id="usertab" class="easyui-tabs" fit="false" border="ture" plain="true" style="height:180px;"></div>