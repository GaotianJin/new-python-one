<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/redemptionConfirmAddInit.js"></script>
<input type="hidden" name="redemptionInfoId" id="redemptionConfirmAdd_redemptionInfoId" value="${redemptionInfoId}">
<input type="hidden" name="custNo" id="redemptionConfirmAdd_custNo" value="${custNo}">

<!--赎回确认操作页面 -->
<div id="tabdiv">
	<!-- 基本信息 -->
	<form id="redemptionConfirmQueryForm">
			<div class="easyui-panel" title="客户产品信息" collapsible="true">
				<div class="top_table" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">客户姓名</td>
							<td>
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="redemptionConfirmAdd_custName" name="custName" readonly />
							</td>
							<td class="table_text" align="right">产品名称</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" name="productName" id="redemptionConfirmAdd_productName" readonly/>
							</td>
							<td class="table_text" align="right">证件号码</td>
							<td align="left">
								<input class="table_input " name="idNo" id="redemptionConfirmAdd_idNo" readonly/>
							</td>
						</tr>
					</table>
				</div>
			</div>
	</form>
	<br>
	<form id="redemptionConfirmRefenceInfoForm">
		 <div class="easyui-panel" title="赎回参考信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
		                    <td class="table_text" align="right">赎回申请对应开放日</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="table_input easyui-combobox" name="redemptionOpenday" id="redemptionConfirmAdd_expectOpenDay" data-options="disabled:true"/>
		                    </td>
		                    <td class="table_text" align="right">赎回申请日期</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="easyui-datebox" name="redemptionApplyDate" id="redemptionConfirmAdd_applyDate" data-options="disabled:true"/>
		                    </td>
		                    <td class="table_text" align="right">参考净值信息</td>
		                    <td>
		                    	<input class="table_input" name="referenceNetValue" id="redemptionConfirmAdd_netValue" readonly/>
		                    </td>
						</tr>
						<tr>
		                    <td class="table_text" align="right">净值公布日期</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="table_input easyui-combobox" name="publicDay" id="redemptionConfirmAdd_publicDay" data-options="disabled:true"/>
		                    </td>
		                   	<td class="table_text" align="right">&nbsp;</td>
							<td>&nbsp;</td>
							<td class="table_text" align="right">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
	</form>
	<br>
	<!-- 客户赎回信息 -->
	<form id="redemptionConfirmTradeInfoForm">
		<div id="smsaccordion" class="easyui-panel" title="客户赎回信息">
			<table id="custAllTradeTable"></table>
		</div> 
		<div class="top_table" id="redemptionTotalTable" border="0">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">本次赎回份额合计</td>
				<td>
					<input class="table_input" id="redemptionConfirmAdd_redeemShares" name="redemptionTotalShare" readonly>（份）
				</td>
				<td class="table_text" align="right">赎回金额合计(参考)</td>
				<td>
					<input class="table_input" id="redemptionConfirmAdd_redeemMoney" name="redemptionTotalMoney" readonly>（元）
				</td>
			</tr>
		</table>
		</div>
 </form>

<form id="redemptionConfirmCheckInfoForm">
 <div class="easyui-panel" title="赎回确认信息" collapsible="true" > 
	<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="left">赎回结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox"  id="redemptionConfirmAdd_Conclusion" name="conclusion">
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				 	<textarea rows="3" cols="80"  id="redemptionConfirmAdd_Remark" name="remark"></textarea></td>
				<td><input type="hidden" id="redemptionOperationId" name="redemptionOperationId"></td>
				<td></td>
			</tr>
		</table>
	</div>
 </div>
 <div>
		<a href="javascript:redemptionConfirmAddInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'">赎回确认</a> 
		<a href="javascript:queryRedemptionApplicationForm();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-undo'">上传影像件</a>
		<a href="javascript:returnRedemptionConfirmList();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-back'">返回</a>
	</div> 
</form>

	
	
</div>
