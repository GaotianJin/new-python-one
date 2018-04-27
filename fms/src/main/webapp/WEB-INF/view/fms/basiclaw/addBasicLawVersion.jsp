<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/basiclaw/addBasicLawVersionInit.js"></script>
<div id="tabdiv">
	<form id="add_basicLawVersionForm">
	<!-- 基本信息 -->
		<div id="smsaccordion" class="easyui-panel" title="基本信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">版本编号</td>
						<td align="left"><input name="versionCode" id="add_versionCode" class="table_select easyui-validatebox" 
						data-options="required:true,validType:['length[0,50]','validCode']"></td>
						<td class="table_text" align="right">版本名称</td>
						<td align="left"><input name="versionName" id="add_versionName" class="table_input  easyui-validatebox" 
						data-options="required:true,validType:['length[0,50]']"></td>
						<td class="table_text" align="right">执行状态</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox easyui-validatebox" 
						data-options="required:true" id="add_execState" name="execState" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行开始日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox  easyui-validatebox" 
						data-options="required:true,validType:['length[0,10]','validDate']" name="execStartdate" id="add_ExecStartDate"/></td>
						<td class="table_text" align="right">执行结束日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox  easyui-validatebox" 
						data-options="validType:['length[0,10]','validDate']" name="execEnddate" id="add_ExecEndDate"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
		</form>
</div>
<div>
	<a href="#" onclick="addBasicLawVerisonSave()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
	<a href="#" onclick="backListBasicLawVersionPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
</div>
