<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sms/listMessageAuditInit.js"></script>

<div id="tabdiv" class="outerPanel">
	<div id="smsAudit" class="easyui-panel" title="短信审核" collapsible="true">
		<form id="smsAudit_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="smsAudit_productId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">短信类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="smsType" id="smsAudit_smsType" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">短信处理状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="smsAudit_smsStatus" name="smsStatus">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">短信生成起期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="smsCreateDateStart" id="smsAudit_smsCreateDateStart" class="easyui-datebox table_input"
								data-options="validType:['validDate']">
						</td>
						<td class="table_text" align="right">短信生成止期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="smsAudit_smsCreateDateEnd" name="smsCreateDateEnd"
								data-options="validType:['validDate']">
						</td>
						<td>&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearSmsAuditCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="smsQuery()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="smsDelete()" class="easyui-linkbutton e-cis_button" iconCls="icon-remove">删除</a>
					<a href="#" onclick="smsIncomeDis()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">生成收益分配短信</a>
				</div>
			</div>
		</form>
		

		<!-- 产品成立短信表格 -->
		<div class="tableOuterDiv" id="smsAuditTableDiv">
			<table id="smsAuditTable"></table>
		</div>
		
	</div>	

</div>


