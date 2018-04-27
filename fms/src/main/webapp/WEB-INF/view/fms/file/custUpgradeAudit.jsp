<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript">
	var transParam = <%=request.getParameter("param")%>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/file/custUpgradeAuditInit.js"></script>

<input type="hidden" name="operateFlag" id="operateFlag" value="${operateFlag}">
<input type="hidden" id="custUpgradeAudit_businessNo" name="businessNo" value="${businessNo}">
<input type="hidden" id="custUpgradeAudit_businessType" name="businessType" value="${businessType}">
<div id="tabdiv">
	<table id="custUpgradeAudit_fileListTable"></table>
</div>
<div class="easyui-panel" title="升级客户审核"
	collapsible="true" id="custCheckDiv"> 
<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="table_text" align="left">审核结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox1" id="custCheckConclusion" name="custCheckConclusion"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
				<tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80" id="custCheckRemark"></textarea></td>
				<td></td>
				<td></td>
				</tr>
		</table>
</div>
</div>
<a href="javascript:void(0)" id="submitButton"class="easyui-linkbutton e-cis_button" iconCls='icon-add' onclick="investCustAudit()">提交审核</a>
<a href="javascript:void(0)" id="backButton"class="easyui-linkbutton e-cis_button" iconCls='icon-back' onclick="backParentPage()">返回</a>

