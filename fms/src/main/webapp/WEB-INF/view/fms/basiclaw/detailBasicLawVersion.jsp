<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/basiclaw/detailBasicLawVersionInit.js"></script>
<div id="tabdiv">
	<!-- 基本信息 -->
		<div id="detail_basicLawVersionDiv" class="easyui-panel" title="基本信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">版本编号</td>
						<td align="left"><input name="versionCode"  id="detail_versionCode" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">版本名称</td>
						<td align="left"><input name="versionName"  id="detail_versionName" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">执行状态</td>
						<td><span class="comboSpan"></span><select class="table_select easyui-combobox" id="detail_execState" name="execState" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">执行开始日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox" name="execStartdate" id="detail_ExecStartDate" disabled="disabled"/></td>
						<td class="table_text" align="right">执行结束日期</td>
						<td align="left"><span class="comboSpan"></span><select class="table_input easyui-datebox" name="execEnddate" id="detail_ExecEndDate" disabled="disabled"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<a href="#" onclick="backListBasicLawVersionPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
</div>
