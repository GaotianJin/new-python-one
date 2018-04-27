<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/importCompanyRosterInit.js"></script>
<script type="text/javascript" >

</script>

<div id="tabdiv">
	<form id="comPanyRosterImportForm" action="<%=request.getContextPath()%>/incomeDis/importIncomeDisFile" enctype="multipart/form-data" method="post" >
		<div id="smsaccordion" class="easyui-panel" title="通讯录批量导入"  collapsible="true">
			<div class="top_table">
				<input type="file" name="rosterFileName" id="rosterFileName"/>
				<a href="#" onclick="importRosterFile()"class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导入</a>
				<a href="#" onclick="back()"class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</div>
		</div>
	</form>
</div>
