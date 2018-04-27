<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/searchProductInvestMentDetailInit.js"></script>
<input type="hidden" id="detail_productInvestMentId" name="productId" value="${productId}"/>
<input type="hidden" id="detail_productInvestMentSubType" name="productSubType" value="${productSubTypeCode}"/>
<%-- <input type="hidden" name="customerNo" id="investMentCustomercustomerNoId" value="${customerNo}"> --%>
<div id="tabdiv11" class="outerPanel">
	<div id="searchProductInvestMentDetail" class="easyui-panel" title="产品详细信息" style="height:auto;" collapsible="true">
		<form id="detailProductInvestMentDetailForm">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td align="right" class="table_text">产品代码</td>
							<td align="left"><input class="table_input easyui-validatebox" id="detail_productInvestMentCode" name="productCode" ></td>
							<td align="right" class="table_text">产品名称</td>
							<td align="left"><input class="table_input easyui-validatebox" name="productName" id="detail_productInvestMentName" /></td>
							<td align="right" class="table_text">产品备注</td>
							<td align="left"><input class="table_input easyui-validatebox" name="remark" id="detail_InvestMentremark" /></td>
						</tr>
						<tr>
							<td align="right" class="table_text">销售状态</td>
							<td align="left"><input class="table_input easyui-validatebox" id="detail_InvestMentsaleStatus" name="saleStatus"></td>
							<td align="right" class="table_text">产品经理姓名</td>
							<td align="left"><input class="table_input easyui-validatebox" id="detail_productInvestMentManager" name="productManager" ></td>
							<!-- <td align="right" class="table_text">&nbsp;&nbsp;</td>
							<td></td> -->
							<td align="right" class="table_text">产品投资</td>
							<td align="left" class="investStrategy"><input class="table_input easyui-validatebox" id="detail_investMentStrategy" name="investStrategy"/></td>
							<td align="left" class="investDirection"><input id="detail_investMentDirection" class="table_input1 easyui-validatebox"  name="investDirection" /></td>
							<td align="left" class="investScope"><input id="detail_investMentScope" class="table_input1 easyui-validatebox"  name="investScope" /></td>
						</tr>		
						<tr>
							<td align="right" class="table_text">成立日</td>
							<td align="left"><input class="table_input easyui-validatebox" name="foundDate" id="detail_InvestMentfoundDate"/></td>						
						 	<td align="right" class="table_text">风险等级</td>
							<td align="left"><input class="table_input easyui-validatebox" name="grade" id="detail_InvestMentgrade"/></td>
							<td align="right" class="table_text subscriptionFeeRatio">认购费率</td>
							<td align="left" class="subscriptionFeeRatio"><input class="table_input easyui-validatebox" name="subscriptionFeeRatio" id="detail_InvestMentsubscriptionFeeRatio"/></td>
							<td></td>
						</tr>
						<tr class="hideInfo2">
							<td align="right" class="table_text investPeriodDeclare">封闭期特殊说明</td>
							<td class="investPeriodDeclare"><input class="table_input easyui-validatebox" id="detail_investMentPeriodDeclare" name="investPeriodDeclare"/></td>	
							<td align="right" class="table_text expectOpenDayRules">开放日规则</td>
							<td class="expectOpenDayRules"><input class="table_input easyui-validatebox" id="detail_InvestMentexpectOpenDayRules" name="expectOpenDayRules"></td>
							<td align="right" class="table_text remainScale">存续规模(万元)</td>
							<td align="left" class="remainScale"><input class="table_input easyui-validatebox" name="remainScale" id="detail_remainScaleInvestMent"/></td>
						</tr>	
						<tr class="hideInfo1">
							<td align="right" class="table_text closeDperiods">产品封闭期</td>
							<td align="left" class="closeDperiods"><input class="table_input easyui-validatebox" name="closeDperiods" id="detail_closeDperiodsInvestMent"/></td>
							<!-- <td align="right" class="table_text subscriptionFeeRatio">认购费率</td>
							<td align="left" class="subscriptionFeeRatio"><input class="table_input easyui-validatebox" name="subscriptionFeeRatio" id="detail_subscriptionFeeRatio"/></td> -->
							<td align="right" class="table_text closeDperiodFee">赎回费率</td>
							<td align="left" calss="closeDperiodFee"><input class="table_input easyui-validatebox" name="closeDperiodFee" id="detail_closeDperiodFeeInvestMent"/></td>
						</tr>	
						<tr class="hideInfo3">
							<td align="right" class="table_text foundScale" >成立规模(万元)</td> 
							<td class="foundScale"><input class="table_input easyui-validatebox" id="detail_foundScaleInvestMent" name="foundScale"></td>
							<td align="right" class="table_text productDperiods" >产品期限</td>
							<td class="productDperiods"><input class="table_input easyui-validatebox" id="detail_closeDperiodsInvestMent" name="closeDperiods"/></td>	
							<td align="right" class="table_text productEndDate" >产品到期日</td>
							<td class="productEndDate"><input class="table_input easyui-validatebox" id="detail_productEndDateInvestMent" name="endDate"/></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	<!-- 财富类费用比例 -->
	<div id="feeInvestMentInfo">
		<div id="fixedWealthFeeInvestMentInfo" class="easyui-panel" title="财富费用信息" style="height:auto;" collapsible="true">
			<table id="fixedWealthRateInvestMentTable"></table>
		</div>
	</div>
	<!-- 分类信息(固定类) -->
	<div id="diffrentInvestMentInfo">
		<div id="fixedDiffrentInvestMentInfo" class="easyui-panel" style="height:auto;"  title="分类信息" collapsible="true">
			<form id="fixedDiffrentInvestMentInfoForm">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td align="right" class="table_text">咨询服务费支付时间</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="serviceFeePayDate" id="modify_serviceFeePayDateInvestMent"/></td>
							<td align="right">税费率(%)</td>
							<td align="left"><input class="table_input easyui-validatebox " name="taxFee" id="productDetail_taxFeeInvestMent"  /></td>
							<td align="right">通道管理费(%)</td>
							<td align="left"><input class="table_input easyui-validatebox " name="channelFee" id="productDetail_channelFeeInvestMent" /></td>
						</tr>
					</table>
				</div>
			</form>
			<div class="tableOuterDiv"></div>
		</div>
	</div>
	<!-- 固收产品分配记录 -->
	<div id="incomeDisInvestMentInfo">
		<div id="detialWealthIncomeDisInvestMentInfo" class="easyui-panel" title="固定收益类收益分配信息" style="height:auto;" collapsible="true">
				<table id="detialWealthIncomeDisInvestMentInfoTable"></table>
		</div>
	</div>
	<div style="margin-bottom: 3px;">
		<a href="#" onclick="lookFileInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">附件信息查看</a>
		<a href="#" id="productNetValueInvestMentInfo" onclick="productNetValueInvestMentInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">产品净值信息查看</a>
	</div>
</div>

