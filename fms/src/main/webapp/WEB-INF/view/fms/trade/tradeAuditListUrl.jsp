<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/tradeAuditListInit.js"></script>

<div class="outerPanel" id="outerPanel" >
	<div class="easyui-panel" title='交易信息查询' fit="true" id="tradeAuditCommonListQueryConditionDiv" data-options="collapsible:true" >
		<form id="tradeAuditCommonListQueryConditionForm">
			<div class="top_table">
				<div class="top_table">
					<table class='input_table' width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td class="table_text" align="right">交易号码</td>
							<td><input class="table_input" id = "tradeNo_Audit_Out" name = "tradeNo"></td>
							<td class="table_text" align="right">合同号</td>
							<td><input class="table_input" id="tradeInfoNo_Audit_Out" name="tradeInfoNo"></td>
							<td class="table_text" align="right">客户姓名</td>
							<td align="left"><input class="table_input" id="tradeAuditCustName" name="chnName"></td>
						</tr>
						<tr>
							<td class="table_text" align="right">产品</td>
							<td>
								<span class="comboSpan"></span>
								<input  class="table_input" id="tradeAuditProduct" name="productId">
							</td>
							<td class="table_text" align="right">交易机构</td>
							<td>
								<span class="comboSpan"></span>
								<input name="tradeComId" id="tradeAudit_tradeComId" class="table_input">
							</td>
					    	<td class="table_text" align="right">财富顾问</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="agentId" id="tradeAudit_agentId" class="table_input">
							</td>
							
						</tr>
						<!-- <tr>
					    	<td class="table_text" align="right">录入日期</td>
							<td>
								<span class="comboSpan"></span>
								<input name="inputDateStart" id="inputDateStart_Audit_Out" class="table_input2 easyui-datebox">
								至
								<input name="inputDateEnd" id="inputDateEnd_Audit_Out" class="table_input2 easyui-datebox">
							</td>
							<td class="table_text" align="right">交易日期</td>
							<td>
								<span class="comboSpan"></span>
								<input name="tradeDateStart" id="tradeDateStart_Audit_Out" class="table_input2 easyui-datebox">
								至
								<input name="tradeDateEnd" id="tradeDateEnd_Audit_Out" class="table_input2 easyui-datebox">
							</td>
							<td class="table_text" align="right">&nbsp;</td>
							<td>&nbsp;</td>
					    </tr> -->
					</table>
					<div>
				    	<a href="javascript:clearTradeAuditInfo();" class="easyui-linkbutton  e-cis_button" data-options="iconCls:'icon-reload'">清空</a> 
						<a href="javascript:queryTradeAuditInfoList();" class="easyui-linkbutton  e-cis_button" data-options="iconCls:'icon-search'">查询</a>
						<a class="easyui-linkbutton  e-cis_button" id="submitTradeAuditDetailButton" data-options="iconCls:'icon-tick'" onclick="tradeAuditDetail('','update');">交易终审</a>
				    </div>
				</div>
			</div>
		</form>
			
			<div class="easyui-panel"  style="margin-top: 3px;">
				<table id="tradeAuditCommonListId"></table>
			</div>
		
	</div>
</div>

