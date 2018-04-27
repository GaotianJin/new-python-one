<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/manager/listManagerInit.js"></script>
<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="内勤人员信息" collapsible="true">
		<form id="listManager_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">姓名</td>
						<td align="left"><span class="comboSpan"></span><input name="chnName" id="chnName" class="table_input"></td>
						<td class="table_text" align="right">手机号</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input"  id="mobile" name="mobile"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearQueryManagerCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryManagerList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="managerTable"></table>
		</div>
	</div>
	
</div>


