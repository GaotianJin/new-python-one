<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/importFloatCopiesFileInit.js"></script>

<div id="tabdiv">
	<form id="FloatCopiesImportForm" action="<%=request.getContextPath()%>/tradeS/importFloatCopiesFile" enctype="multipart/form-data" method="post" >
		<div id="smsaccordion" class="easyui-panel"  collapsible="false">
			<div class="top_table">
				<input type="file" name="floatCopiesFile" id="floatCopiesFile"/>
				<a href="#" onclick="importFloatCopiesFile()"class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导入</a>
			</div>
			
		</div>
	</form>
</div>
