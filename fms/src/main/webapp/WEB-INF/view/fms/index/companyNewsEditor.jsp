<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/companyNewsEditorInit.js"></script>
<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="公司要闻列表" collapsible="true">
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="companyNewsEditorTable"></table>
		</div>
		<div>
		<a href="#" onclick="backListPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</div>
</div>