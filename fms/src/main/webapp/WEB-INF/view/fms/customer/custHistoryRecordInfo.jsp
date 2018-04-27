<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/custHistoryRecordInfoInit.js"></script>

<input type="hidden" name="custBaseInfoId" 	id="historyRecord_custBaseInfoId" value="${custBaseInfoId}"/>
<%-- <input type="hidden" name="custQuestionnaireId" id="historyRecordInfo_custQuestionnaireId" value="${custQuestionnaireId}"/> --%>
<input type="hidden" name="agentId" id="historyRecord_agentId" value="${agentId}"/>
<input type="hidden" name="loadFlag" id="historyRecord_loadFlag" 	value="${loadFlag}" />
<input type="hidden" name="tradeLoadFlag" id="historyRecord_tradeLoadFlag" value="${tradeLoadFlag}">


<div id = "historyRecordDiv" class="outerPanel">
	<div id = "historyRecordDiv1">
		<form id = "historyRecordForm">
			<div id = "historyRecordDiv2" class="easyui-panel" title="风控问卷调查" style="height:auto;" collapsible="true">
				<div class="top_table">
					<table id="historyRecordTb" width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right" >问卷类型</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="questionnaireType"  id="historyRecord_questionnaireType" 
									class="table_input easyui-combobox1"/>
								<input type="hidden" class="inpuntHidden" name="custQuestionnaireId" id="historyRecord_custQuestionnaireId" value="${custQuestionnaireId}"/>
							</td>
							<td class="table_text" align="right" >调查日期</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="submitDate"  id="historyRecord_SubmitDate" 
									class="table_input easyui-datebox" disabled="disabled"/>
							</td>
							
							<td class="table_text" align="right" >得分</td>
							<td align="left">
								<input name="score"  id="historyRecord_score" 
									class="table_input" disabled="disabled"/>
								<input type="hidden" id="allhistoryRecordQuestionAndAnswer" name="answerResults" class="inputHidden"/>
								<span id="riskhistoryRecordLevel"></span>
							</td>
						</tr>
					</table>
				</div>	
			</div>			
		</form>
	</div>
	<div style="margin-bottom: 3px;">
		<a class="easyui-linkbutton e-cis_button" id="queryhistoryRecordAttachInfoButton" 
			onclick="queryQuestionnaireAttachInfo();" data-options="iconCls:'icon-search'">查看风控问卷附件</a>
	</div>
</div>