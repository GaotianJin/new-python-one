<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/ueditor.all.js"> </script> 
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/js/ueditor1.1.0/lang/zh-cn/zh-cn.js"></script>
<link href="<%=request.getContextPath()%>/js/ueditor1.1.0/themes/default/css/ueditor.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/updateProNewsFileInit.js"></script>

<input type="hidden" name="professionNewsInfoId" id="updProNews_professionNewsInfoId" value="${professionNewsInfoId}">

<div id="tabdiv" class="outerPanel">
<form id="updProNewsUeditor" method="post">
		<table width="150%" border="0" cellspacing="0" cellpadding="0" class="input_table">
			<tr>
							<td class="table_text" align="right">标题</td>
							<td align="left">
								<input class="table_input easyui-validatebox" name="title" id="updProNews_title" data-options="required:true,validType:['length[0,100]']"/>
							</td>
							<td class="table_text" align="right"> </td>
			</tr>
		</table>
   <!-- 加载编辑器的容器 -->
   <script id=container name="content" type="text/plain" style="width:100%;height:400px;">
      
   </script>
   
<a href="#" onclick="updateProNewsEditorInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">更新</a>
<a href="#" onclick="back()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
</form>

<!-- 实例化编辑器 -->
<script type="text/javascript">
var ueditor = UE.getEditor('container');
</script>
</div>
