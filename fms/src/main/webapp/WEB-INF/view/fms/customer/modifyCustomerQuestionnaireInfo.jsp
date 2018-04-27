<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/modifyCustomerQuestionnaireInfoInit.js"></script>

<input type="hidden" name="custBaseInfoId" 	id="modifyCustomerQuestionnaireInfo_custBaseInfoId" value="${custBaseInfoId}"/>
<input type="hidden" name="agentId" id="modifyCustomerQuestionnaireInfo_agentId" value="${agentId}"/>
<input type="hidden" name="loadFlag" id="modifyCustomerQuestionnaireInfo_loadFlag" 	value="${loadFlag}" />
<input type="hidden" name="tradeLoadFlag" id="modifyCustomerQuestionnaireInfo_tradeLoadFlag" value="${tradeLoadFlag}">


<div id = "modifyCustomerQuestionnaireInfoDiv" class="outerPanel">
		  <!-- 历史记录 -->
		  <div id="historyRecord" class="easyui-panel" title="历史记录" collapsible="true">
				<table id="historyRecordTable"></table>
		 </div>
	<div id = "modifyCustomerQuestionnaireInfoDiv1">
		<form id = "modifyCustomerQuestionnaireWealthInfoForm">
			<div id = "modifyCustomerQuestionnaireInfoDiv2" class="easyui-panel" title="新增风控问卷调查" style="height:auto;" collapsible="true">
				<div class="top_table">
					<table id="questionnaireTb" width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right" >问卷类型</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="questionnaireType"  id="modifyCustomerQuestionnaireInfo_questionnaireType" 
									class="table_input easyui-combobox1"/>
								<input type="hidden" class="inpuntHidden" name="custQuestionnaireId" id="modifyCustomerQuestionnaireInfo_custQuestionnaireId"/>
							</td>
							<td class="table_text" align="right" >调查日期</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="submitDate"  id="modifyCustomerQuestionnaireInfo_SubmitDate" 
									class="table_input easyui-datebox" data-options="required:true,validType:['validDate']"/>
							</td>
							
							<td class="table_text" align="right" >得分</td>
							<td align="left">
								<input name="score"  id="modifyCustomerQuestionnaireInfo_score" 
									class="table_input" readonly="true"/>
								<input type="hidden" id="allQuestionAndAnswer" name="answerResults" class="inputHidden"/>
								<span id="riskLevel"></span>
							</td>
						</tr>
					</table>
				</div>	
			</div>			
		</form>
	</div>
	<div style="margin-bottom: 3px;">
		<a href="#" onclick="submitModifyCustomerQuestionnaireInfo()" id="modifyCustomerQuestionnaireInfo_submitQuestionnaireInfoButton" 
			class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadQuestionnaireAttachInfoButton" 
			onclick="uploadQuestionnaireAttachInfo();" data-options="iconCls:'icon-redo'">上传风控问卷附件</a>
		<a class="easyui-linkbutton e-cis_button" id="queryQuestionnaireAttachInfoButton" 
			onclick="uploadQuestionnaireAttachInfo();" data-options="iconCls:'icon-redo'">查看风控问卷附件</a>
	</div>
</div>