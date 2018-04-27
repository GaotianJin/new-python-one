<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sms/listCustMessageAuditInit.js"></script>

<div class="outerPanel">
	<div id="smsCustAudit" class="easyui-panel" title="短信审核" collapsible="true">
		<form id="smsCustAudit_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">客户名称</td>
						<td align="left">
							<input name="custName" id="smsCustAudit_custName" class="table_input easyui-textbox">
						</td>
						<td class="table_text" align="right">短信类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="smsType" id="smsCustAudit_smsType" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">短信处理状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="smsCustAudit_smsStatus" name="smsStatus">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">短信生成起期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="smsCreateDateStart" id="smsCustAudit_smsCreateDateStart" class="easyui-datebox table_input"
								data-options="validType:['validDate']">
						</td>
						<td class="table_text" align="right">短信生成止期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="smsCustAudit_smsCreateDateEnd" name="smsCreateDateEnd"
								data-options="validType:['validDate']">
						</td>
						<td>&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearCustSmsAuditCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="smsCustQuery()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="smsCustDelete()" class="easyui-linkbutton e-cis_button" iconCls="icon-remove">删除</a>
					<!-- <a href="#" onclick="smsIncomeDis()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">生成收益分配短信</a> -->
				</div>
			</div>
		</form>
		

		<!-- 产品成立短信表格 -->
		<div class="tableOuterDiv" id="smsCustAuditTableDiv">
			<table id="smsCustAuditTable"></table>
		</div>
		
	</div>	

</div>


