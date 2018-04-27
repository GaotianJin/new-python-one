<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listIncomeDisImportInit.js"></script>
<script type="text/javascript" >

</script>

<div id="tabdiv">
	<form id="IncomeDisImportForm" action="<%=request.getContextPath()%>/incomeDis/importIncomeDisFile" enctype="multipart/form-data" method="post" >
		<div id="smsaccordion" class="easyui-panel" title="收益分配明细导入"  collapsible="true">
			<div class="top_table">
				<!-- <input class="table_input" id="protocolName"/> -->
				<input type="file" name="incomeFileName" id="incomeFileName"/>
				<a href="#" onclick="importIncomeDisFile()"class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导入</a>
			</div>
			
		</div>
	</form>
</div>
