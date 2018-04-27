<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sms/sendSmsInit.js"></script>

<input type="hidden" name="sysSmsBatchId" id="sendSms_sysSmsBatchId" value="${sysSmsBatchId}">
<input type="hidden" name="smsType" id="sendSms_smsType" value="${smsType}">
<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion1" class="easyui-panel" title="查询条件" collapsible="true">
		<form id="sendSms_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">客户名称</td>
						<td align="left">
							<input name="custName" id="sendSms_custName" class="table_input">
						</td>
						<td class="table_text" align="right">短信状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="sendSms_sendStatus" name="sendStatus">
						</td>
						<td class="table_text" align="right">发送对象类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="sendSms_sendObjType" name="sendObjType">
						</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearQuerySmsCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="querySmsByCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		

		<!-- 短信详细信息列表 -->
		<div class="tableOuterDiv" id="sendSms_smsDetailTableDiv">
			<table id="sendSms_smsDetailTable"></table>
			<div style="margin-bottom: 3px;" id="customerFamilyTableButton">
				<a href="#" onclick="sendSms()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">发送</a>
				<a href="#" onclick="backSmsExtraction()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</div>
		</div>		
	</div>	

</div>


