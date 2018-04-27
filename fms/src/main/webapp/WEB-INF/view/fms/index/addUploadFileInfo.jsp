<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/addUploadFileInfoInit.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.all.js"> </script> 
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/lang/zh-cn/zh-cn.js"></script>
<link href="<%=request.getContextPath()%>/js/ueditor1.1.0/themes/default/css/ueditor.css" rel="stylesheet" />

<input type="hidden" name="ProductId" id="addProductEditorInfo_ProductId" value="${productId}">
<input type="hidden" name="ProductType" id="addProductEditorInfo_ProductType" value="${productType}">
<input type="hidden" name="ProductSubType" id="addProductEditorInfo_ProductSubType" value="${productSubType}">

<div id="tabdiv" class="outerPanel">
		
		
				
<form id="addFileUeditor" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
			<tr>
							<td class="table_text" align="right">标题</td>
							<td align="left">
								<input class="table_input easyui-validatebox" name="title" id="addUploadFile_title" data-options="required:true,validType:['length[0,100]']"/>
							</td>
			</tr>
		</table>
   <!-- 加载编辑器的容器 -->
   <script id="editor" name="content" type="text/plain" style="width:100%;height:400px;">
            
   </script>
</form>

<!-- 实例化一个编辑器 -->
<script type="text/javascript">
	UE.getEditor("editor");
</script>

<a href="#" onclick="addFileUploadInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
<a href="#" onclick="backListProductPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		
</div>



