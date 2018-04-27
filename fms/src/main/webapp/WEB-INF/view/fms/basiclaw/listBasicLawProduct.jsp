<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/basiclaw/listBasicLawProductInit.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="基本法设置" collapsible="true">
		<form id="list_basicLawProductForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align = "right">版本编号</td>
						<td align="left"><input name = "versionCode" id="listp_versionCode" class="table_input"></td>
						<td class="table_text" align = "right">版本名称</td>
						<td align="left"><input name = "versionName" id="listp_versionName" class="table_input"></td>
						<td class="table_text" align = "right">版本执行状态</td>
						<td><span class="comboSpan"></span><input class="table_select easyui-combobox1"  id="listp_versionExecState" name="versionExecState"></td>
			
					</tr>
					<tr>
						<td class="table_text" align = "right">产品类别</td>
						<td align="left"><span class="comboSpan"><input name = "productType" id="listp_productType"class="table_select easyui-combobox1"></td>
						<td class="table_text" align = "right">产品子类</td>
						<td align="left"><span class="comboSpan"><input name = "productSubType" id="listp_productSubType" class="table_select easyui-combobox1"></td>
						<td class="table_text" align = "right">产品代码</td>
						<td align="left"><span class="comboSpan"><input name = "productId" id="listp_productId" class="table_select easyui-combobox1"></td>
					</tr>
					
			<!-- 		<tr>
						<td class="table_text" align="right">版本执行开始日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="execStartDate" id="listp_execStartDate" class="table_input2 easyui-datebox"
							data-options="validType:['length[0,10]','validDate']"></input>&nbsp;至
							<input name="execStartToDate" id="listp_execStartToDate" class="table_input2 easyui-datebox"
							data-options="validType:['length[0,10]','validDate']"></input>
						</td>
						<td class="table_text" align="right">版本执行结束日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="execEndDate" id="listp_execEndDate" class="table_input2 easyui-datebox"
							data-options="validType:['length[0,10]','validDate']"></input>&nbsp;至
							<input name="execEndToDate" id="listp_execEndToDate" class="table_input2 easyui-datebox"
							data-options="validType:['length[0,10]','validDate']"></input>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr> -->
				</table>
				<div>
					<a href="#" onclick="clearBasicLawProductFormList()" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchBasicLawProduct()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv"> 
		<table id="list_basicLawProductTable"></table>
		</div>
	</div>
</div>

