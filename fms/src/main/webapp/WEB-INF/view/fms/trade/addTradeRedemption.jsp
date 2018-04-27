<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/addTradeRedemptionInit.js"></script>
<input type="hidden" name="loadFlag" id="addTradeRedemption_loadFlag" value="${loadFlag}">
<input type="hidden" name="redemptionInfoId" id="addTradeRedemption_redemptionInfoId" value="${redemptionInfoId}">
<input type="hidden" name="custNo" id="addTradeRedemption_custNo" value="${custNo}">


<!--交易预约赎回详细页面 -->
<div id="tabdiv">
	<!-- 赎回信息查询 -->
	<form id="redemptionInfoQeuryForm">
			<div class="easyui-panel" title="赎回信息查询" collapsible="true">
				<div class="top_table" >
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">客户姓名</td>
							<td>
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="addTradeRedemption_custName" name="custName" data-options="required:true">
							</td>
							<td class="table_text" align="right">产品名称</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" name="productName" id="addTradeRedemption_productName"  data-options="required:true"/>
							</td>
							<td class="table_text" align="right">证件号码</td>
							<td align="left">
								<input class="table_input" name="idNo" id="addTradeRedemption_idNo"  readonly/>
							</td>
						</tr>
					</table>
				</div>
				<div>
			  		<a  id="queryTradeInfoByCustandProButton" href="javascript:queryTradeInfoByCustandPro();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-tick'">查询</a>
			  		<a  id="clearTradeInfoByCustandProButton" href="javascript:clearTradeInfoByCustandPro();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-reload'">清空</a> 
				</div> 
			</div>
	</form>
	<br>
	<form id="redemptionRefenceInfoForm">
	       <div class="easyui-panel" title="赎回参考信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
		                    <td class="table_text" align="right">赎回对应开放日</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="table_input easyui-combobox" name="redemptionOpenday" id="addTradeRedemption_expectOpenDay" data-options="required:true" />
		                    </td>
		                    <td class="table_text" align="right">赎回申请日期</td>
		                    <td>
		                    	<span class="comboSpan"></span>
		                    	<input class="table_input easyui-datebox" name="redemptionApplyDate" id="addTradeRedemption_applyDate" data-options="required:true"/>
		                    </td>
		                    <td class="table_text" align="right">参考净值信息</td>
		                    <td>
		                    	<input class="table_input" name="referenceNetValue" id="addTradeRedemption_netValue" data-options="required:true"  readonly/>
		                    </td>
						</tr>
						<tr>
						   
						 	<td class="table_text" align="right">参考净值公布日</td>
		                    <td>
		                    	
		                    	<input class="table_input " name="publicDay" id="addTradeRedemption_publicDay" data-options="required:true" readonly />
		                    </td>
		                 	<td>&nbsp;</td>
		                    <td>&nbsp;</td>
		                    <td>&nbsp;</td>
		                    <td>&nbsp;</td>
						</tr>
						
					</table>
				</div>
		</div>
	</form>
	<br>
	<form id="redemptionTradeInfoForm">
	   <div id="smsaccordion" class="easyui-panel" title="赎回交易信息">
	     <div>
	       	<table id="custAllTradeTable"></table>
	     </div>
	     <div class="top_table" id="redemptionTotalTable" border="0">
		   	<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td class="table_text" align="right">您选择的赎回份额合计</td>
				<td>
					<input class="table_input" id="addTradeRedemption_redeemShares" name="redemptionTotalShare" readonly>（份）
				</td>
				<td class="table_text" align="right">赎回金额合计（参考）</td>
				<td>
					<input class="table_input" id="addTradeRedemption_redeemMoney" name="redemptionTotalMoney" readonly>（元）
				</td>
				</tr>
			</table>
	    	<div>
			  <a href="javascript:saveRedemptionTradeInfo();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-save'">保存</a> 
			  <a href="javascript:downloadApplicationForm();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-print'">下载赎回单</a> 
			  <!-- <a href="javascript:uploadApplicationForm();" class="easyui-linkbutton e-cis_button" data-options="iconCls:'icon-redo'">上传影像件</a>  -->
			  <a href="javascript:commitRedemptionCheck();" class="easyui-linkbutton   e-cis_button" data-options="iconCls:'icon-tick'">提交确认</a>
			  <a href="javascript:returnRedemptionList();" class="easyui-linkbutton    e-cis_button" data-options="iconCls:'icon-back'">返回</a>
	  		</div> 
	   </div>
	 </div> 
	</form>
</div>
