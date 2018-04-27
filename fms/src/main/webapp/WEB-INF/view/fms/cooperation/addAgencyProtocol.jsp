<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/cooperation/addAgencyProtocolInit.js"></script>

<input type="hidden" name="operate" id="addAgencyProtocol_operate" class="inpuntHidden" value="${operate}">
<input type="hidden" name="addAgencyProtocolComId" id="addAgencyProtocolComId" class="inpuntHidden" value="${agencyComId}">
<div id="tabdiv">
	<form id="addAgencyProtocolForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="协议信息" collapsible="true">
			<div class="top_table" id="addAgencyProtocolDiv">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left"><span class="comboSpan"></span><input
							name="agencyComId" id="add_agencyComId" class="table_input easyui-combobox1" 
						data-options="required:true,validType:['length[0,100]']" ></td>
						<td class="table_text" align="right">协议编号</td>
						<td align="left">
							<input type="hidden" name="addAgencyProtocolId" id="addAgencyProtocolId" class="inpuntHidden" value="${protocolId}">
							<input name="protocolCode" id="add_protocolCode" class="table_input easyui-validatebox" 
								 data-options="validType:['length[0,20]','validCode']"></td>
						<td class="table_text" align="right">协议名称</td>
						<td align="left"><input name="protocolName" id="add_protocolName" class="table_input"
						 data-options="required:true,validType:['length[0,100]']"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">协议类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input type="hidden" name="addAgencyProtocolType" id="addAgencyProtocolType" class="inpuntHidden" value="${protocolType}">
							<input name="protocolType" id="add_protocolType" class="table_input easyui-combobox1"></td>
						<!-- <td class="table_text" align="right">所属框架协议</td>
						<td align="left"><span class="comboSpan"></span><input
							name="protocolId" id="add_beloneProtocolId" class="table_input easyui-combobox1"></td> -->
						<td class="table_text" align="right">协议状态</td>
						<td align="left"><span class="comboSpan"></span><input
							name="protocolState" id="add_protocolState" class="table_input easyui-combobox1"></td>
						<td class="table_text" align="right">协议开始日期</td>
						<td><span class="comboSpan"></span><input name="protocolStartDay" id="add_protocolStartDay"
							class="table_input easyui-datebox" data-options="validType:['length[0,10]','validDate']"></td>
					</tr>
					<tr>
						
						<td class="table_text" align="right">协议结束日期</td>
						<td><span class="comboSpan" ></span><input
							name="protocolEndDay" id="add_protocolEndDay"
							class="table_input easyui-datebox" data-options="validType:['length[0,10]','validDate']"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
		</form>

		<a href="#" id="addAgencyProtocolSubmitButton" onclick="addAgencyProtocol()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
		<a href="#" id="addAgencyProtocolUploadFileButton" onclick="AgencyProtocolFileUpload()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">附件上传</a>
		<a href="#" id="addAgencyProtocolSearchFileButton"onclick="AgencyProtocolFileUpload()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查看附件</a>
		<a href="#" onclick="backListAgencyProtocolPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		
</div>
