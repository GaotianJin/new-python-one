<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/uploadAgentPhotoInit.js"></script>
<input type="hidden" name="agentId" id="uploadImage_agentId" value="${agentId}">
<input type="hidden" name="agentImage" id="uploadImage_agentImage" value="${agentImage}">
<input type="hidden" name="operate" id="uploadImage_agentImageOperate" value="${operate}">
<div id="tabdiv">
	<div id="smsaccordion1" class="easyui-panel"  collapsible="true">
		<div style="width:210px;height:280px;margin:auto;margin-top:25px;margin-bottom:25px;border:1px solid red;">
			<img id="uploadImage_agentImageUrl" alt="" style="width:210px;height:280px;" src="<%=request.getContextPath()%>/img/unUploadImage.jpg">
		</div>
		<form id="uploadFile_fileForm" enctype="multipart/form-data" method="post" >
			<div class="top_table" id="uploadFile_fileFormTableDiv">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td class="table_text" align="right">请选择照片</td>
						<td >
							<span class="comboSpan"></span><!-- onchange="uploadAgentImage('select')" -->
							<input type="file" name="agentImage" id="uploadFile_agentImage"/>
							<span id="uploadFile_div">
								<a href="#" onclick="uploadAgentImage('upload')" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">上传</a>
							</span>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<!-- <div id="aa" class="easyui-accordion" style="width:auto;">   
	    <div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
	        <h3 style="color:#0099FF;">Accordion for jQuery</h3>   
	        <p>Accordion is a part of easyui framework for jQuery.     
	        It lets you define your accordion component on web page more easily.</p>   
	    </div>   
	    <div id="title2" title="Title2" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">   
	        content2    
	    </div>   
	    <div title="Title3">   
	        content3    
	    </div>   
	</div>   -->

	
</div>