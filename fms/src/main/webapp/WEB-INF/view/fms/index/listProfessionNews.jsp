<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/listProfessionNewsInit.js"></script>

<input type="hidden" name="roleId" id="proNews_roleId" value="${roleId}">

<div id="tabdiv" class="outerPanel">
		
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listProfessionNewsTable" ></table>
		</div>
		
		<!-- <div style="margin-top: 3px;" id="tabdiv">
			<a href="#" id="proNews_linkbutton" onclick="professionUploadFile()"  class="easyui-linkbutton e-cis_button" iconCls="icon-tick">上传</a>
		</div> -->
</div>
