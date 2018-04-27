<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/balance/listTradeBalanceImportInit.js"></script>
<script type="text/javascript" >

</script>

<div id="tabdiv">
	<form id="TradeBalanceForm">
		
		<div id="smsaccordion" class="easyui-panel" title="结算结果导入"  collapsible="true">
			<div class="top_table">
				<input class="table_input" id="protocolName"/>
				<input type="file" style="width:68px"/>
				<a href="#" onclick="searchUser()"class="easyui-linkbutton e-cis_button" iconCls="icon-redo">上传</a>
			</div>
			
		</div>
	
		<div id="smsaccordion" class="easyui-panel" title="结算信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">管理机构</td>
						<td><span class="comboSpan"/><input name="idtype" id="idtype" class="table_select easyui-combobox"></td>
						<td class="table_text" align="right">网点</td>
						<td align="left"><span class="comboSpan"/><input name="idtype" id="idtype" class="table_select easyui-combobox"></td>
						<td class="table_text" align="right">部门</td>
						<td align="left"><span class="comboSpan"/><input name="idtype" id="idtype" class="table_select easyui-combobox"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">财富顾问</td>
						<td><span class="comboSpan"/><input name="idtype" id="idtype" class="table_select easyui-combobox"></td>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left"><span class="comboSpan"/><input name="idtype" id="idtype" class="table_select easyui-combobox"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left"><input class = "table_input" name="idno"  id = "idno" /></td>
					</tr>
					<tr>
						<td class="table_text" align="right">交易号码</td>
						<td><input name="sex" id="sex" class="table_input"/></td>
						<td class="table_text" align="right">统计起期</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datetimebox table_input" name="birthday_begin" id="birthday_begin"/></td>
						<td class="table_text" align="right">统计始期</td>
						<td align="left"><span class="comboSpan"/><input class = "easyui-datetimebox table_input"  name="birthday_end"  id = "birthday_end" /></td>
					</tr>

				</table>
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchUser()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="searchUser()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出</a>
				</div>
			</div>
		</div>
	</form>
</div>
<div style="margin-top: 3px;" id="tabdiv">
	<table id="TradeBalanceImportTable"></table>
</div>