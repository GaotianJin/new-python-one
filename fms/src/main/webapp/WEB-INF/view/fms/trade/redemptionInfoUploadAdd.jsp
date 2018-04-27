<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/redemptionInfoUploadAddInit.js"></script>
<input type="hidden" name="redemptionInfoId" id="redemptionInfoUploadAdd_redemptionInfoId" value="${redemptionInfoId}">
<input type="hidden" name="loadFlag" id="redemptionInfoUploadAdd_loadFlag" value="${loadFlag}">

<!--赎回确认操作页面 -->
<div id="tabdiv">
	<!-- 基本信息 -->
	<form id="redemptionInfoUploadQueryForm">
			<div class="easyui-panel" title="客户产品信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">客户姓名</td>
							<td>
							 	<span class="comboSpan"></span>	
								<input class="table_input easyui-combobox" id="redemptionInfoUploadAdd_custName" name="custName" data-options="disabled:true"/>
							</td>
							<td class="table_text " align="right">产品名称</td>
							<td align="left">
							  	<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" name="productName" id="redemptionInfoUploadAdd_productName" data-options="disabled:true"/>
							</td>
							<td class="table_text " align="right">证件号码</td>
							<td align="left">
								<input class="table_input " name="idNo" id="redemptionInfoUploadAdd_idNo" data-options="disabled:true" readonly/>
							</td>
						</tr>
					</table>
				</div>
			</div>
	</form>
	<br>
	<form id="redemptionInfoUploadReferenceForm">
		 <div class="easyui-panel" title="赎回参考信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
							<tr>
		                    <td class="table_text" align="right">赎回申请对应开放日</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="table_input easyui-combobox" name="redemptionOpenday" id="redemptionInfoUploadAdd_expectOpenDay" data-options="disabled:true"/>
		                    </td>
		                    <td class="table_text" align="right">赎回申请日期</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="easyui-datebox" name="redemptionApplyDate" id="redemptionInfoUploadAdd_applyDate" data-options="disabled:true"/>
		                    </td>
		                    <td class="table_text" align="right">参考净值信息</td>
		                    <td>
		                    	<input class="table_input" name="referenceNetValue" id="redemptionInfoUploadAdd_netValue" readonly/>
		                    </td>
						</tr>
						<tr>
						 	<td class="table_text" align="right">参考净值公布日期</td>
		                    <td>
		                    	<span class="comboSpan"></span>	
		                    	<input class="table_input easyui-combobox " name="publicDay" id="redemptionInfoUploadAdd_pubicDay" data-options="disabled:true"/>
		                    </td>
						    <td class="table_text" align="right">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
		 </div>
	</form>
	<br>
	<form  id="redemptionUploadTradeInfoForm">
		<div id="smsaccordion" class="easyui-panel" title="客户赎回信息">
		<table id="custAllTradeTable"></table>
		</div> 
		<div class="top_table" id="redemptionTotalTable" border="0">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">预约赎回总份额</td>
				<td>
					<input class="table_input" id="redemptionInfoUploadAdd_redeemShares" name="redemptionTotalShare" readonly>（份）
				</td>
				<td class="table_text" align="right">预约赎回总金额(参考)</td>
				<td>
					<input class="table_input" id="redemptionInfoUploadAdd_redeemMoney" name="redemptionTotalMoney" readonly>（元）
				</td>
			</tr>
		</table>
		</div>
   </form>
   <br>
   <form id="redemptionUploadConclusionInfoForm">
		<div class="easyui-panel" title="赎回确认信息" collapsible="true" > 
		<div class="top_table">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="left">赎回结论</td>
				<td><span class="comboSpan"></span>
					<input class="table_input easyui-combobox"  id="redemptionInfoUploadAdd_Conclusion" name="conclusion"></td>
				<td></td>
				<td><input type="hidden" id="redemptionOperationId" name="redemptionOperationId"></td>
				<td></td>
				<td></td>
				</tr><tr>
				<td class="table_text" align="left">备注说明</td>
				<td align="left" colspan=3><span class="comboSpan"></span>
				<textarea rows="3" cols="80"  id="redemptionInfoUploadAdd_remark" name="remark"></textarea></td>
			</tr>
		</table>
	</div>
	</div>
</form>
<br>
<form id="redemptionUploadActuallyInfoForm">
  	<div class="easyui-panel" title="赎回实际参数信息" collapsible="true" > 
		<div class="top_table" id="redemptionUploadActuInfoDiv">
		<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">实际赎回总份额</td>
				<td>
					<input class="table_input" id="redemptionInfoUploadAdd_actuallyRedeemShares" name="actuRedemptionTotalShare" data-options="validType:['validDecNum','required:true']" />
				</td>
				<td class="table_text" align="right">实际赎回净值</td>
				<td>
					<input class="table_input" id="rredemptionInfoUploadAdd_actuallyNetValue" name="actuRedemptionNetValue"  data-options="validType:['validDecNum','required:true']"  onblur="calculateActuallyTotalMoney()"/>
				</td>
				<td class="table_text" align="right">实际赎回总金额</td>
				<td>
					<input class="table_input" id="redemptionInfoUploadAdd_actuallyRedeemMoney" name="actuRedemptionTotalMoney"  />
				</td>
			</tr>
		</table>
	</div>
	</div>
	<div>
		<a href="javascript:saveRedemptionInfoUpload();"   id="saveRedemptionInfoUploadButton" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'">确认</a> 
		<a href="javascript:queryRedemptionApplicationForm();"   id="queryRedemptionApplicationFormButton" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-undo'">查看影像件</a>
		<a href="javascript:returnRedemptionUploadInfoList();"  id="returnRedemptionUploadInfoListButton"  class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-back'">返回</a>
	</div> 
</form>
</div>
