<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/basiclaw/updateBasicLawVersionInit.js"></script>
<div id="tabdiv">
	<form id="update_basicLawVersionForm">
	<!-- 基本信息 -->
		<div id="update_basicLawVersionDiv" class="easyui-panel" title="基本信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">版本编号</td>
						<td align="left"><input name="versionCode"  id="update_versionCode" class="table_input  easyui-validatebox" 
						data-options="required:true,validType:['length[0,50]','validCode']"></td>
						<td class="table_text" align="right">版本名称</td>
						<td align="left"><input name="versionName"  id="update_versionName" class="table_input  easyui-validatebox"
						data-options="required:true,validType:['length[0,50]']"></td>
						<td class="table_text" align="right">执行状态</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox easyui-validatebox" 
						data-options="required:true" id="update_execState" name="execState"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行开始日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox  easyui-validatebox" 
						data-options="required:true,validType:['length[0,10]','validDate']" name="execStartdate" id="update_ExecStartDate"/></td>
						<td class="table_text" align="right">执行结束日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox  easyui-validatebox" 
						data-options="validType:['length[0,10]','validDate']" name="execEnddate" id="update_ExecEndDate"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
			<input name="basicLawId"  id="update_basicLawId" type="hidden"/>
		</div>
		</form>
</div>
<div>
	<a href="#" onclick="updateBasicLawVerisonSave()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
	<a href="#" onclick="backListBasicLawVersionPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	
</div>
