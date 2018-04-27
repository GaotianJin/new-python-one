<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/listSelfIntroductionInit.js"></script>
<div id="tabdiv" class="outerPanel">
<input type="hidden" name="userId" id="userId" value="${userId}">
 <div id=selfIntroduction class="easyui-panel" fit="true" title="个人信息" collapsible="true">
  	<textarea id="listSelfIntroduction" rows="10" cols="50"></textarea>
	<br>
	<br>
	<div>
	<a href="#" onclick="updateSelfIntroduction()" class="easyui-linkbutton e-cis_button" iconCls="icon-save">保存</a>
  </div>
 </div>
</div>