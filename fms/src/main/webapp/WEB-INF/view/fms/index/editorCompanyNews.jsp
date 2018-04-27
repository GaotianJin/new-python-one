<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.all.js"> </script> 
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/lang/zh-cn/zh-cn.js"></script>
<link href="<%=request.getContextPath()%>/js/ueditor1.1.0/themes/default/css/ueditor.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/editorCompanyNewsInit.js"></script>

<input type="hidden" name="custBaseInfoId" id="edtiorComPolicyInfo_custBaseInfoId" value="${custBaseInfoId}">

<form id="ComPolicyUeditor" method="post">
   <!-- 加载编辑器的容器 -->
   <script id="container" name="content" type="text/plain" style="width:100%;height:80%;">
            
   </script>
</form>

<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ueditor = UE.getEditor('container');
</script>