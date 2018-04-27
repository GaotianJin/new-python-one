<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/cooperation/addAgencySubProtocolInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div id="tabdiv">
	<form id="addAgencyProtocolForm">
		<div id="smsaccordion" class="easyui-panel"  fit="true" title="子协议信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">协议编号</td>
						<td align="left"><input name="protocolCode" id="protocolCode" class="table_input"></td>
						<td class="table_text" align="right">协议名称</td>
						<td align="left"><span class="comboSpan"></span><input name="subprotocolName" id = "subprotocolName" class = "table_input"></td>
						<td class="table_text" align="right">协议类型</td>
						<td align="left"><span class="comboSpan"></span><input name="subprotocolType" id="subprotocolType" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">协议状态</td>
						<td align="left"><span class="comboSpan"></span><input name="subprotocolState" id="subprotocolState" class="table_input"></td>
						<td class="table_text" align="right">所述框架协议</td>
						<td align="left"><span class="comboSpan"></span><input name="subkuangjia" id="subkuangjia" class="table_input"></td>
						<td class="table_text" align="right">产品</td>
						<td align="left"><span class="comboSpan"></span><input name="subproduct" id="subproduct" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">协议日期</td>
						<td>
						<span class="comboSpan"></span>
						<input name="protocolStartDay" id="protocolStartDay" class="table_input2 easyui-datebox">
						至
						<input name="protocolEndDay" id="protocolEndDay" class="table_input2 easyui-datebox">
						</td>
						<td class="table_text" align="right">协议文件</td>
						<td align="left" colspan="3"><input type="file" name="uploadify" id="uploadify" /></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="addAgencyProtocol()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
					<a href="#" onclick="delAgencyProtocol()" class="easyui-linkbutton" iconCls="icon-cancel">删除</a>
				</div>
			</div>
		</div>
	</form>
</div>

<div style="margin-top: 3px;" id="tabdiv">
	<table id="addAgencySubProtocolTable"></table>
</div>

<div id="addAgencyProtocoltab" class="easyui-tabs" fit="false" border="ture"  plain="true"></div>