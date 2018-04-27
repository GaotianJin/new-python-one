<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.all.js"> </script> 
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/lang/zh-cn/zh-cn.js"></script>
<link href="<%=request.getContextPath()%>/js/ueditor1.1.0/themes/default/css/ueditor.css" rel="stylesheet" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/updateProductEditorInit.js"></script>
<input type="hidden" name="ProductId" id="updateProductEdtiorInfo_ProductId" value="${updateTabProductId}">
<input type="hidden" name="ProductType" id="updateProductEdtiorInfo_ProductType" value="${updateTabProductType}">
<input type="hidden" name="ProductSubType" id="updateProductEdtiorInfo_ProductSubType" value="${updateTabProductSubType}">

<form id="updatePdUeditor" method="post">
   <script id="container"   name="content" type="text/plain" style="width:100%;height:400px;">
   </script>
</form>

<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ueditor = UE.getEditor('container');
</script>

<a href="#" onclick="updateProductEditorInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>






