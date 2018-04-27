<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/assessmentCaluationListInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<!--考核管理页面 -->
<div id="tabdiv" class="outerPanel">
	<form id="assessAccForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="考核结算" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">考核年份</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input" id="assesscal_year" name="assessYear"  ></td>
						<td class="table_text" align="right">考核月份</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input" id="assesscal_Month" name="assessMonth" ></td>
						<td class="table_text" align="right">考核单位</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input" id="assesscal_Unit" name="assessUnit" ></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="assessCal()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">考核预警</a>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchAssess()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</form>
	<form>
		<div>
			<table id="assessCalTable"></table>
		</div>
		<div>
			<a href="#" id="dd" class="easyui-linkbutton e-cis_button" iconCls="icon-add" onclick="assessmentForward()">考核结转</a>
		</div>
	</form>
</div>





