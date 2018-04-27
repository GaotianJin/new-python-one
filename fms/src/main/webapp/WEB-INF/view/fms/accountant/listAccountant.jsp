<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/accountant/listAccountant.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="理财师信息" iconCls="icon-ok" collapsible="true">
		<form id="actForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">理财师姓名</td>
						<td><input name="actName" id="actName" class="table_input"></td>
						<td class="table_text" align="right">理财师手机号</td>
						<td align="left"><input name="actPhone" id="actPhone"class="table_input"></td>
					</tr>

				</table>
				<div>
					<a href="#" onclick="clearActFormList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchAct()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="actTable"></table>
		</div>
	</div>
</div>