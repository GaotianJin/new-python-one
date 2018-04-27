<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/listComInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="机构信息" collapsible="true">
		<form id="list_comForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">机构代码</td>
						<td><input name="comCode" id="list_comCode" class="table_input" /></td>
						<td class="table_text" align="right">机构名称</td>
						<td align="left"><input name="comName" id="list_comName" class="table_input" /></td>
						<td class="table_text" align="right">上级机构</td>
						<td align="left"><span class="comboSpan" /><select class="table_select easyui-combobox" name="parentComId" id="list_comComCode" /></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearComFormList()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchCom()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="list_comTable"></table>
		</div>
	</div>
</div>
