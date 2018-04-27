<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/listCompanyPolicyInit.js"></script>

<input type="hidden" name="roleId" id="comPolicy_roleId" value="${roleId}">

<div id="tabdiv" class="outerPanel">
		
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listCompanyPolicyTable"></table>
		</div>
		<!-- <div style="margin-top: 3px;" id="tabdiv">
			<a href="#" id="comPolicy_linkbutton" onclick="uploadCompanyPolicyFile()"  class="easyui-linkbutton e-cis_button" iconCls="icon-reload">上传文件</a>
		</div> -->
</div>
