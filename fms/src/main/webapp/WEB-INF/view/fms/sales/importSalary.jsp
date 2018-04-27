<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/importSalaryInit.js"></script>
<script type="text/javascript" >

</script>

<div id="tabdiv">
	<form id="salaryImportForm" action="<%=request.getContextPath()%>/sales/importSalaryFile" enctype="multipart/form-data" method="post" >
		<div id="smsaccordion" class="easyui-panel" title="薪资批量导入"  collapsible="true">
			<div class="top_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">文件类型</td>
				        <td align="left"><span class="comboSpan"></span><input name="salaryFileType" id="salaryFileType" class="table_input easyui-combobox" data-options="required:true"></td>
						<td class="table_text" align="right">薪资月份</td>
				        <td align="left"><span class="comboSpan"></span><input name="salaryFileYear" id="salaryFileYear" class="table_input easyui-combobox1" data-options="required:true">年
						<input name="salaryFileMonth" id="salaryFileMonth" class="table_input easyui-combobox1" data-options="required:true">月</td>
					</tr>
			</table>
				<input type="file" name="salaryFileName" id="salaryFileName"/>
				<a href="#" onclick="importSalaryFile()"class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导入</a>
				<a href="#" onclick="back()"class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</div>
		</div>
	</form>
</div>
