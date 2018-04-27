<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/js/fms/sales/assessmentFindListInit.js"></script>
<!-- 考核查询 -->
<div id="tabdiv">
	<form id="assessForm">
		<div id="#" class="easyui-panel" fit="true" title="考核结算信息查询"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">管理机构</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="assessFind_comId" name="comId"></td>
						<td class="table_text" align="right">网点</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="assessFind_storeId" name="storeId"></td>
						<td class="table_text" align="right">部门</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="assessFind_departmentId" name="departmentId"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">财富顾问</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="assessFind_agentId" name="agentId"></td>
						<td class="table_text" align="right">考核年份</td>
						<td align="left"><span class="comboSpan"></span><input name="assessYear" id="assessFind_Year"  class="table_input"></td>
						<td class="table_text" align="right">考核月份</td>
						<td align="left"><span class="comboSpan"></span><input name="assessMonth" id="assessFind_Month" class="table_input"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="assessExport()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出</a>
					<a href="#" onclick="clearAssessInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchAssessInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
</div>
<div style="margin-top: 3px;" id="tabdiv">
	<table id="assessSearchTable"></table>
</div>
<div id="usertab" class="easyui-tabs" fit="false" border="ture" plain="true" style="height:180px;"></div>
