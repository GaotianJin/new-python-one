<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript">
	var transParam = <%=request.getParameter("param")%>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/file/uploadFileInit.js"></script>

<input type="hidden" name="flag" id="operateFlag" value="${flag}">
<input type="hidden" name="businessType" id="history_business_Type" value="${businessType}">
<div id="tabdiv">
	<table id="uploadFile_fileListTable"></table>
</div>
<div id="uploadFile_tabdiv" class="tableOuterDiv">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="文件信息" collapsible="true">
		<form id="uploadFile_fileForm" action="<%=request.getContextPath()%>/fileUpload/uploadFile" enctype="multipart/form-data" method="post" >
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						
						<td class="table_text" align="right">上传文件</td>
						<td >
							<span class="comboSpan"></span>
							<input type="file" multiple="multiple"  name="uploadFileName" id="uploadFile_uploadFileName"/>
							<!-- style="width:68px" -->
						</td>
						
						<td class="table_text" align="right">附件子类型</td>
					    <td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox" id="uploadFile_businessSubType" name="businessSubType"></td>
						
				
							<input type="hidden" id="uploadFile_businessNo" name="businessNo" value="${businessNo}">
							<input type="hidden" id="uploadFile_businessType" name="businessType" value="${businessType}">
			
							
						<!-- <td class="table_text" align="right">&nbsp;</td>
						<td>&nbsp;</td> -->
					</tr>
					<tr>
						<td class="table_text" align="right">附件描述</td>
						<td colspan=3>
							<span class="comboSpan"></span>
							<textarea rows="3" cols="80" id="uploadFile_fileDescribe" name="fileDescribe" ></textarea>
						</td>
						<!-- <td class="table_text" align="right">&nbsp;</td>
						<td>&nbsp;</td> -->
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<a href="javascript:void(0)" id="uploadFileButton" class="easyui-linkbutton e-cis_button" iconCls='icon-redo' onclick="fileUpload()">上传</a>
<a href="javascript:void(0)" id="deleteFileButton"class="easyui-linkbutton e-cis_button" iconCls='icon-delete' onclick="fileDelete()">删除</a>
<a href="javascript:void(0)" id="backButton"class="easyui-linkbutton e-cis_button" iconCls='icon-back' onclick="backParentPage()">返回</a>

