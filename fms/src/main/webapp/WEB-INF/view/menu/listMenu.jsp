<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/menu/listMenuInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="menuBasicInfoDiv" class="easyui-panel" fit="true" title="菜单信息" iconCls="icon-ok" collapsible="true">
		<form id="menuForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">菜单编码</td>
						<td align="left"><input name="privilegeCode" id="listmenu_privilegecode" class="table_input"></td>
						<td class="table_text" align="right">菜单名称</td>
						<td><input name="privilegeName" id="listmenu_privilegename" class="table_input"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearMenuFormList()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchMenu()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="menuTable"></table>
		</div>
	</div>
</div>

