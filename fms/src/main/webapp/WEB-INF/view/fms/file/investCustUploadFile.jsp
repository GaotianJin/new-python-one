<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript">
	var transParam = <%=request.getParameter("param")%>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/file/investCustUploadFileInit.js"></script>

<input type="hidden" name="flag" id="operateFlag" value="${flag}">

<div id="investCustBaseInfo_tabdiv">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="客户信息" collapsible="true">
		<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td class="table_text" align="left">客户姓名</td>
						<td>
							<input class="table_input" id="custUpgradeBaseInfo_chnName" name="chnName" disabled="disabled">
						</td>
						<td class="table_text" align="left">财富顾问</td>
						<td>
							<input class="table_input" id="custUpgradeBaseInfo_agentName" name="agentName" disabled="disabled">
						</td>
				</table>
		</div>
	</div>
</div>
<div id="tabdiv">
	<table id="investCustUploadFile_fileListTable"></table>
</div>
<div id="investCustUploadFile_tabdiv" class="tableOuterDiv">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="文件信息" collapsible="true">
		<form id="investCustUploadFile_fileForm" action="<%=request.getContextPath()%>/fileUpload/uploadFile" enctype="multipart/form-data" method="post" >
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						
						<td class="table_text" align="right">上传文件</td>
						<td >
							<span class="comboSpan"></span>
							<input type="file" multiple="multiple"  name="uploadFileName" id="investCustUploadFile_uploadFileName"/>
						</td>
						
						<td class="table_text" align="right">附件子类型</td>
					    <td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox" id="investCustUploadFile_businessSubType" 
					    name="businessSubType"></td>
						
				
							<input type="hidden" id="investCustUploadFile_businessNo" name="businessNo" value="${businessNo}">
							<input type="hidden" id="investCustUploadFile_businessType" name="businessType" value="${businessType}">
							<input type="hidden" id="investCustUploadFile_investAuditStateCode" name="investAuditStateCode" value="${investAuditStateCode}">
							<%-- <input type="hidden" id="investCustUploadFile_chnName" name=chnName value="${chnName}">
							<input type="hidden" id="investCustUploadFile_customerNo" name="customerNo" value="${customerNo}">
							<input type="hidden" id="investCustUploadFile_agentName" name="agentName" value="${agentName}"> --%>
					</tr>
					<tr>
						<td class="table_text" align="right">附件描述</td>
						<td colspan=3>
							<span class="comboSpan"></span>
							<textarea rows="3" cols="80" id="investCustUploadFile_fileDescribe" name="fileDescribe" ></textarea>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<div id="custUpgradeAuditConclusionDiv">
<div class="easyui-panel" title="客户升级审核结论" collapsible="true" > 
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="left">审核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox" id="custUpgradeAuditConclusion_Input" name="tradeCheckConclusion" data-options="required:true" disabled="disabled"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" id="custUpgradeAuditRemark_Input" disabled="disabled"></textarea></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</div>
</div>
<a href="javascript:void(0)" id="investCustUploadFileButton" class="easyui-linkbutton e-cis_button" iconCls='icon-redo' onclick="fileUpload()">上传</a>
<a href="javascript:void(0)" id="deleteInvestCustFileButton"class="easyui-linkbutton e-cis_button" iconCls='icon-delete' onclick="fileDelete()">删除</a>
<a href="javascript:void(0)" id="backButton"class="easyui-linkbutton e-cis_button" iconCls='icon-back' onclick="backParentPage()">返回</a>
<p><a href="javascript:void(0)" id="investCustAuditButton"class="easyui-linkbutton e-cis_button" iconCls='icon-redo' onclick="investCustAudit()">提交复核</a></p>

