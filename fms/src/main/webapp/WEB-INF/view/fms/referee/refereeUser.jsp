<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/referee/referee.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="推荐人信息" iconCls="icon-ok" collapsible="true">
		<form id="RefereeForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">推荐人</td>
						<td><input name="refereeName" id="refereeName" class="table_input"></td>
						<td class="table_text" align="right">推荐码</td>
						<td align="left"><input name="refereeCode" id="refereeCode" class="table_input"></td>
						<td class="table_text" align="right">推荐人状态</td>
						<td align="left"><input id="state" name="state"></td>
					</tr>

				</table>
				<div>
					<a href="#" onclick="clearRefereeFormList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchReferee()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="reftable"></table>
		</div>
	</div>
</div>



