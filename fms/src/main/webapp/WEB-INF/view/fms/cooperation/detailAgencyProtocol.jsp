<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fms/cooperation/detailAgencyProtocolInit.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div id="tabdiv">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="协议信息" collapsible="true">
		<form id="updateAgencyProtocolForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left"><span class="comboSpan"></span><input
							name="agencyComId" id="detail_agencyComId" class = "table_input easyui-combobox1" disabled="disabled"></td>
						<td class="table_text" align="right">协议编号</td>
						<td align="left"><input name="protocolCode" id="detail_protocolCode" class="table_input" 
					 data-options="required:true" disabled="disabled"></td>
						<td class="table_text" align="right">协议名称</td>
						<td align="left"><input name="protocolName" id="detail_protocolName" class="table_input" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">协议类型</td>
						<td align="left"><span class="comboSpan"></span><input
							name="protocolType" id="detail_protocolType" class = "table_input easyui-combobox1" disabled="disabled"></td>
						<td class="table_text" align="right">所属框架协议</td>
						<td align="left"><span class="comboSpan"></span><input
							name="agencyProtocolId" id="detail_beloneProtocolId" class = "table_input easyui-combobox1" disabled="disabled"></td>
						<td class="table_text" align="right">协议状态</td>
						<td align="left"><span class="comboSpan"></span><input
							name="protocolState" id="detail_protocolState" class = "table_input easyui-combobox1" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">协议开始日期</td>
						<td><span class="comboSpan"></span><input name="protocolStartDate" id="detail_protocolStartDay"
							class="table_input easyui-datebox" disabled="disabled"></td>
						<td class="table_text" align="right">协议结束日期</td>
						<td><span class="comboSpan"></span><input
							name="protocolEndDate" id="detail_protocolEndDay"
							class="table_input easyui-datebox" disabled="disabled"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<a href="#" onclick="AgencyProtocolFileUpload()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">附件信息查询</a>
<a href="#" onclick="backListAgencyProtocolPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>

