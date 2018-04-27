<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/redemptionConfirmAddInit.js"></script>


<!--赎回确认操作页面 -->
<div id="tabdiv">
	<!-- 基本信息 -->
	<form id="tradeRedemptionConfirmAddForm">
		<div id="custTradeInfo">
			<div class="easyui-panel" title="客户产品信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="input_table">
						<tr>
							<td class="table_text" align="right">客户姓名</td>
							<td>
								<input class="table_input" id="redemptionConfirmAdd_custName" name="custName">
							</td>
							<td class="table_text" align="right">产品名称</td>
							<td align="left">
								<input type="hidden" name="productId" id="redemptionConfirmAdd_productId"/>
								<input class="table_input" name="productName" id="redemptionConfirmAdd_productName"/>
							</td>
							<!-- <td class="table_text" align="right">理财经理</td>
							<td>
								<input class="table_input" id="redemptionConfirmAdd_agentName" name="agentName">
							</td> -->
							<td class="table_text" align="right">产品方名称</td>
							<td>
								<input class="table_input" name="agencyName" id="redemptionConfirmAdd_agencyName"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			 <div class="easyui-panel" title="赎回参考信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
							<tr>
		                    <td class="table_text" align="right">赎回申请对应开放日</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="table_input easyui-combobox" name="expectOpenDay" id="redemptionConfirmAdd_expectOpenDay"/>
		                    </td>
		                    <td class="table_text" align="right">赎回申请日期</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="easyui-datebox" name="applyDate" id="redemptionConfirmAdd_applyDate"/>
		                    </td>
		                    <td class="table_text" align="right">参考净值</td>
		                    <td>
		                    	<input class="table_input" name="netValue" id="redemptionConfirmAdd_netValue"/>
		                    </td>
		                   		                    
						</tr>
			<!-- 			<tr>
		                 
		                    <td class="table_text" align="right">&nbsp;</td>
		                    <td>&nbsp;</td>
		                    <td class="table_text" align="right">&nbsp;</td>
		                    <td>&nbsp;</td>
						</tr> -->
					</table>
				</div>
			</div>
			
		</div>
	</form>
	<div id="smsaccordion" class="easyui-panel" title="客户赎回信息">
		<table id="custAllTradeTable"></table>
	</div> 
	<div class="top_table" id="redemptionTotalTable" border="0">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">本次赎回份额合计</td>
				<td>
					<input class="table_input" id="redemptionConfirmAdd_redeemShares" name="redeemShares" readonly>（份）
				</td>
				<td class="table_text" align="right">本次赎回金额合计</td>
				<td>
					<input class="table_input" id="redemptionConfirmAdd_redeemMoney" name="redeemMoney" readonly>（元）
				</td>
			</tr>
		</table>
	</div>

	<div class="easyui-panel" title="赎回确认信息" collapsible="true" > 
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="left">赎回结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox"  id="redemptionConfirmAdd_Check" name="tradeAuditConclusion"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80"  id="redemptionConfirmAdd_Conclusion"></textarea></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</div>

	<div class="easyui-panel" title="赎回实际参数信息" collapsible="true" > 
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">实际赎回份额</td>
				<td>
					<input class="table_input" id="redemptionConfirmAdd_redeemShares" name="redeemShares" >
				</td>
				<td class="table_text" align="right">实际赎回金额</td>
				<td>
					<input class="table_input" id="redemptionConfirmAdd_redeemShares" name="redeemShares" >
				</td>
				<td class="table_text" align="right">实际赎回净值</td>
				<td>
					<input class="table_input" id="redemptionConfirmAdd_redeemShares" name="redeemShares" >
				</td>
		
				</tr><tr>
			</tr>
		</table>
	</div>
</div>
 

	<div>
		<a href="javascript:clearTradeInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'">确认</a> 
		<a href="javascript:clearTradeInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-undo'">查看影像件</a>
		<a href="javascript:clearTradeInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-back'">返回</a>
	</div> 
	
</div>
