<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/listStoreInit.js"></script>
<div class="outerPanel" id="tabdiv">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="网点信息查询"collapsible="true">
		<form id="list_storeForm">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="table_text" align="right">网点代码</td>
							<td><input name="storeCode" id="list_storeCode" class="table_input"/></td>
							<td class="table_text" align="right">网点名称</td>
							<td align="left"><input name="storeName" id="list_storeName" class="table_input"/></td>
							<td class="table_text" align="right">网点类型</td>
							<td align="left"><span class="comboSpan"/>
							<select name="type" id="list_type" class="table_select easyui-combobox"/></td>
						</tr>
						<tr>
							<!--修改-->
							<td class="table_text" align="right">上级机构</td>
							<td align="left"><span class="comboSpan"/>
							<select class = "table_select easyui-combobox" name="comId"  id = "list_storeComCode"/></td>
						</tr>
					</table>
					<div>
						<a href="#" onclick="clearStoreFormList()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
						<a href="#" onclick="searchStore()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					</div>
				</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="list_storeTable"></table>
		</div>
	</div>
</div>
