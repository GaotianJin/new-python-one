<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fms/cooperation/updateAgencyProtocolInit.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<div id="tabdiv">
	<form id="updateAgencyProtocolForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="协议信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left"><span class="comboSpan"></span><input
							name="agencyComId" id="modify_agencyComId" class = "table_input easyui-combobox1" ></td>
						<td class="table_text" align="right">协议编号</td>
						<td align="left"><input name="protocolCode" id="modify_protocolCode" class="table_input" 
					 data-options="required:true"></td>
						<td class="table_text" align="right">协议名称</td>
						<td align="left"><input name="protocolName" id="modify_protocolName" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">协议类型</td>
						<td align="left"><span class="comboSpan"></span><input
							name="protocolType" id="modify_protocolType" class = "table_input easyui-combobox1"></td>
						<td class="table_text" align="right">所属框架协议</td>
						<td align="left"><span class="comboSpan"></span><input
							name="agencyProtocolId" id="modify_beloneProtocolId" class = "table_input easyui-combobox1"></td>
						<td class="table_text" align="right">协议状态</td>
						<td align="left"><span class="comboSpan"></span><input
							name="protocolState" id="modify_protocolState" class = "table_input easyui-combobox1"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">协议开始日期</td>
						<td><span class="comboSpan"></span><input name="protocolStartDate" id="modify_protocolStartDay"
							class="table_input easyui-datebox" data-options="validType:['length[0,10]','validDate']"></td>
						<td class="table_text" align="right">协议结束日期</td>
						<td><span class="comboSpan"></span><input
							name="protocolEndDate" id="modify_protocolEndDay"
							class="table_input easyui-datebox" data-options="validType:['length[0,10]','validDate']"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
		</form>
		<a href="#" onclick="updateAgencyProtocol()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick" id="updateAgencyProtocolSubmitButton">提交</a>
		<a href="#" onclick="AgencyProtocolFileUpload()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">附件信息修改</a>
		<a href="#" onclick="backListAgencyProtocolPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		
</div>

