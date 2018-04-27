<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/detailProductCoreDefInit.js"></script>
<input type="hidden" name="modifyProductId" id="modifyProductId" value="${updateTabProductId}">
<input type="hidden" name="modifyProductTypeCode" id="modifyProductType" value="${updateTabProductType}">
<input type="hidden" name="modifyProductSubTypeCode" id="modifyProductSubType" value="${updateTabProductSubType}">
<input type="hidden" name="modifyProductStatus" id="modifyProductStatus" value="${updateTabProductStatus}">


<!--产品信息修改页面 -->
<div id="tabdiv11" class="outerPanel">
	<!-- 财富产品信息 -->
	<div id="wealthProductInfo" class="easyui-panel" title="财富产品信息" style="height:auto;" collapsible="true">
		<form id="wealthProductInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">产品类别</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_wealthCategory" name="wealthCategory" data-options="required:true"></td>
						<td align="right" class="table_text">成立日</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="foundDate" id="modify_foundDate" data-options="required:true"/></td>
						<td align="right" class="table_text">投资总额(万元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="financingScale" id="modify_financingScale" data-options="validType:['validDecNum']"/></td>
					</tr>
					<tr>
						<td align="right" class="table_text">风险等级</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_grade" name="grade"></td>
						<td align="right" class="table_text">受益权类型</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_beneficialTypes" name="beneficialTypes" data-options="required:true"></td>
						<td align="right" class="table_text">&nbsp;&nbsp;</td>
						<td></td>
					</tr>		
					 <tr>
						<td align="right" class="table_text">起投金额(元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="startInvestMoney" id="updatePdCoreInfo_startInvestMoney" data-options="validType:['validDecNum']" /></td>
						<td align="right" class="table_text">投资限额(元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="investLimitMoney" id="updatePdCoreInfo_investLimitMoney" data-options="validType:['validDecNum']" /></td>
					 	<td align="right" class="table_text">递增金额(元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="investIncreaseMoney" id="updatePdCoreInfo_investIncreaseMoney" data-options="validType:['validDecNum']" /></td>
					</tr>
					<tr>
						<td align="right" class="table_text">小号数量(上限)</td>
						<td align="left"><input class="table_input easyui-numberbox" name="smallBigint" id="modify_smallNumber" data-options="min:0"/></td>
						<td align="right" class="table_text">募集开始日</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="raiseStartDate" id="modify_raiseStartDate" data-options="required:true"/></td>
						<td align="right" class="table_text">募集结束日</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="raiseEndDate" id="modify_raiseEndDate" data-options="required:true"/></td>
					</tr>	
					<tr>
						<td align="right" class="table_text">封闭期(数值)</td>
						<td align="left"><input class="table_input easyui-validatebox"  id="modify_closeDperiod" name=closeDperiods data-options="validType:['closePeriod']" />以"/"隔开,如6/9</td>
						<td align="right" class="table_text">封闭期限(数值单位)</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_closeDperiodUnit" name="closeDperiodUnit"></td>
						<td align="right" class="table_text">封闭期特殊说明</td>
						<td></span><input class="table_input easyui-textbox" id="investPeriodDeclare" name="investPeriodDeclare"/></td>	
					</tr>					
					<!-- 封闭期新增 -->
					<tr id="closeDperiods_redemptionFee">					
					<td align="right" class="table_text">封闭期类型</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="modify_closeDperiodType" name="closeDperiodType"/></td>
						<td id="redemptionFee1" align="right" class="table_text"><span class="comboSpan">赎回费率(%)</span></td>
						<td align="left" id="modify_redemptionFee">
						<input id="modify_redemptionFeeInput" class="table_input easyui-validatebox"   name="closeDperiodFee"  data-options="validType:['validDecNum']" /></td>		
					</tr>
			        <tr>
						<td align="right" class="table_text promptIncomeWay"><span class="comboSpan">收益方式</span></td>
						<td class="promptIncomeWay"><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modifyPdCoreInfo_incomeWay" name="incomeWay" ></td>
						<!-- <td class="prompthistoryEarnrate" align="right" class="table_text">
						<span>历史收益(%)</span></td>
				    	<td class="prompthistoryEarnrate" align="left"><input class="table_input easyui-validatebox" name="historyEarnrate" id="modifyPdCoreInfo_historyEarnrate" /></td> -->
						<!-- <td class="prompthistoryEarnratePeriod" align="right" class="table_text">
						<span>历史收益期间</span>
						</td>
				    	<td class="prompthistoryEarnratePeriod" align="left"><input class="table_input easyui-validatebox" name="historyEarnratePeriod" id="modifyPdCoreInfo_historyEarnratePeriod"/></td> -->			       
			            <td id="modify_promptTransferWorkDays" align="right" class="table_text">
						<span >收益分配划款工作日数</span>
						</td>
						<td id="modify_transferWorkDaysTD">
							<input id="modify_transferWorkDays" class="table_input easyui-validatebox"  name="transferWorkDays" data-options="validType:['length[0,4]']"/>
						</td>
						<td align="right" class="table_text cooperationPartner" >合作伙伴</td>
						<td class="cooperationPartner" colspan="1" ><input class="table_input" id="modify_cooperationPartner"  name="cooperationPartner" /></td>
						<!-- <td align="right" class="table_text productFeature" >产品特点</td>
						<td class="productFeature" colspan="1" ><input class="table_input" id="detail_productFeature"  name="productFeature" /></td> -->
			        </tr>			        
			        <tr id="netvaluedisclosureDetail" hidden><!-- 此行只有浮动类才显示 -->
					<td align="right" class="table_text"></span>净值披露频率</td>
					<td><span class="comboSpan"></span><input id="netValueDisclosureinputDetail" class="table_input easyui-combobox" name="netValueDisclosure"  hidden></td>
					<td align="right" class="table_text"></span>开放日规则</td>
					<td><span class="comboSpan"></span><input id="expectOpenDayRulesDetail" class="table_input easyui-combobox" name="expectOpenDayRules"  hidden></td>
					</tr>
					<tr>
					<td align="right" class="table_text productFeature">产品特点</td>
					 	<td align="left" colspan="5" class="productFeature">
							<span class="comboSpan"></span>
							<textArea name="productFeature" id="productFeature" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea></td>
					</tr>
<!-- 					<tr>
						<td align="left" class="table_text">收益说明</td>
						<td align="left" colspan="5" class="table_input">
							<span class="comboSpan"></span>
							<textArea name="description" id="modify_description2" rows="2" cols="80" class="table_textarea"></textArea></td>
					</tr>
							
					<tr>
						<td align="left" class="table_text">资金用途</td>
						<td align="left" colspan="5" class="table_input">
							<span class="comboSpan"></span>
							<textArea name="fundSusing" id="modify_fundSusing" rows="2" cols="80" class="table_textarea"></textArea></td>
					</tr> -->
					<tr>
					<td align="right" class="table_text riskControlMeasure" >风控措施</td>
					 	<td align="left" colspan="5" class="riskControlMeasure" >
							<span class="comboSpan"></span>
							<textArea name="riskControlMeasure"  id="riskControlMeasure" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea>
						</td>
						
						<!-- <td align="right" class="table_text riskControlMeasure">风控措施</td>
						<td class="riskControlMeasure" colspan="1" ><input class="table_input" id="detail_riskControlMeasure" name="riskControlMeasure" /></td> -->
						<!-- <td align="right" class="table_text cooperationPartner" >合作伙伴</td>
						<td class="cooperationPartner" colspan="1" ><input class="table_input" id="detail_cooperationPartner" name="cooperationPartner" /></td> -->
					</tr>
					<!-- <tr>
					<td align="right" class="table_text cooperationPartner" >合作伙伴</td>
					 	<td align="left" colspan="5" class="cooperationPartner" >
							<span class="comboSpan"></span>
							<textArea name="cooperationPartner"  rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea>
						</td>
					</tr> -->
					<tr>
					<td align="right" class="table_text raiseAccount" >募集账户</td>
					 	<td align="left" colspan="5" class="raiseAccount" >
							<span class="comboSpan"></span>
							<textArea name="raiseAccount" id="raiseAccount"  rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea>
						</td>
					</tr>
					<tr>
					<td align="right" class="table_text trusteeshipAccount" >托管账户</td>
					 	<td align="left" colspan="5" class="trusteeshipAccount" >
							<span class="comboSpan"></span>
							<textArea name="trusteeshipAccount" id="trusteeshipAccount"  rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea>
						</td>
						<!-- <td align="right" class="table_text raiseAccount">募集账户</td>
						<td class="raiseAccount" colspan="1" ><input class="table_input" id="detail_raiseAccount"  name="raiseAccount"/></td> -->
						<!-- <td align="right" class="table_text trusteeshipAccount">托管账户</td>
						<td class="trusteeshipAccount" colspan="1" ><input class="table_input" id="detail_trusteeshipAccount"  name="trusteeshipAccount"/></td> -->
					</tr>
					
					<!-- <tr>
					 	<td align="right" class="table_text investDirection" hidden>投资方向</td>
						<td class="investDirection" colspan="3" hidden><input id="investDirection" class="table_input1 easyui-validatebox"  name="investDirection" /></td>
						<td align="right" class="table_text investStrategy" hidden>投资策略</td>
						<td class="investStrategy" colspan="3" hidden><input id="investStrategy" class="table_input1 easyui-validatebox"  name="investStrategy" /></td>
						<td align="right" class="table_text investScope" hidden>投资范围</td>
						<td class="investScope" colspan="3" hidden><input id="investScope" class="table_input1 easyui-validatebox"  name="investScope" /></td>
					</tr> -->
					
					<tr>
					 	<td align="right" class="table_text investDirection" hidden>投资方向</td>
					 	<td align="left" colspan="5" class="investDirection" hidden>
							<span class="comboSpan"></span>
							<textArea name="investDirection" id="investDirection" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea>
						</td>
						<td align="right" class="table_text investStrategy" hidden>投资策略</td>
						<td align="left" colspan="5" class="investStrategy" hidden>
							<span class="comboSpan"></span>
							<textArea name="investStrategy" id="investStrategy" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true"></textArea>
						</td>
						<td align="right" class="table_text investScope" hidden>投资范围</td>
					   <td align="left" colspan="5"  class="investScope" hidden>
							<span class="comboSpan"></span>
							<textArea name="investScope" id="investScope" rows="2" cols="80"  class="table_textarea easyui-validatebox" data-options="required:true"></textArea>
					   </td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- 寿险产品信息 -->
	<div id="insuranceInfo" class="easyui-panel" title="保险产品信息" style="height:auto;"  collapsible="true">
		<form id="insuranceInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table"  id="riskInfo">
					<tr>
						<td align="right" class="table_text">主附险标记</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_prFlag" name="prFlag" data-options="required:true"></td>
						<td align="right" class="table_text">产品特征</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_riskFeatures" name="riskFeatures"></td>
						<td align="right" class="table_text">基本保费(元)</td>
						<td align="left"><input class="table_input easyui-validatebox"  id="modify_basicPrem" name="basicPrem" data-options="validType:['validDecNum']" /></td>
					</tr>
					<tr>
						<td align="right" class="table_text">最小投保年龄(数值)</td>
						<td align="left"><input class="table_input easyui-numberbox" name="minAppAge" id="modify_minAppAge" data-options="min:0,validType:['length[0,3]']"/></td>
						<td align="right" class="table_text">最小投保年龄(数值单位)</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_minAppAgeUnit" name="minAppAgeUnit"  ></td>
					    <td align="right" class="table_text">预定利率(%)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="interestRate" id="modify_interestRate" data-options="validType:['validDecNum']"/></td>
					</tr>
					<tr>
						<td align="right" class="table_text">最大投保年龄(数值)</td>
						<td align="left"><input class="table_input easyui-numberbox" name="maxAppAge" id="modify_maxAppAge" data-options="min:0,validType:['length[0,3]']"/></td>
						<td align="right" class="table_text">最大投保年龄(数值单位)</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="modify_maxAppAgeUnit" name="maxAppAgeUnit"  ></td>
						<td align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="table_text">其他说明</td>
						<td align="left" colspan="5" class="table_input">
							<span class="comboSpan"></span>
							<textArea name="description" id="modify_description" rows="2" cols="80" class="table_textarea"></textArea></td>
					</tr>
				</table>
			</div>
			<div class="top_table">
				<table id="riskInfo2" width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">主附险标记</td>
						<td><span class="comboSpan"></span><input
							class="table_input easyui-combobox" id="modify_prFlag2" name="prFlag"
							data-options="required:true"></td>
					    <td align="right" class="table_text">&nbsp;</td>
						<td><span class="comboSpan"></span>&nbsp;</td>
						 <td align="right" class="table_text">&nbsp;</td>
						<td><span class="comboSpan"></span>&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="table_text">其他说明</td>
						<td align="left" colspan="5" class="table_input"><span
							class="comboSpan"></span> <textArea name="description"
								id="modify_description1" rows="2" cols="50" class="table_textarea"></textArea></td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>

	<!-- 分类信息(浮动/股权类) -->
	<div id="fdiffrentInfo" class="easyui-panel" title="财富费用信息" style="height:auto;"  collapsible="true">
		<form id="fdiffrentInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">认购费比例(%)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="subscriptionFeeRatio" id="modify_subscriptionFeeRatio"  data-options="validType:['validDecNum']"/></td>
						<td align="right" class="table_text">认购费支付时间</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="subscriptionPayDate" id="modify_subscriptionPayDate" data-options="validType:['length[0,10]','validDate']"/></td>
						<td align="right">税费率(%)</td>
						<td align="left"><input class="table_input easyui-validatebox " name="taxFee" id="modify_taxFee1" data-options="validType:['validDecNum']"/></td>
					</tr>
					<tr>
						<td align="right" class="table_text">固定管理费比例(%)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="fixManagementFeeRatio" id="modify_fixManagementFeeRatio" data-options="validType:['validDecNum']" /></td>
						<td align="right" class="table_text">浮动管理费比例(%)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="floatManagementFeeRatio" id="modify_floatManagementFeeRatio" data-options="validType:['validDecNum']"/></td>
						<td align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="table_text">浮动管理费支付时间</td>
						<td align="left" colspan="3" >
							<span class="comboSpan"></span>
							<textArea name="floatManagementFeePayDate" id="modify_floatManagementFeePayDate" rows="2" cols="80" class="table_textarea"></textArea></td>
					</tr>
					<tr>
						<td align="left" class="table_text">固定管理费支付时间</td>
						<td align="left" colspan="3" >
							<span class="comboSpan"></span>
							<textArea name="fixManagementFeePayDate" id="modify_fixManagementFeePayDate" rows="2" cols="80" class="table_textarea"></textArea></td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>

	<!-- 财富类费用比例 -->
	<div id="wealthFeeInfo" class="easyui-panel" title="财富费用信息" style="height:auto;" collapsible="true">
		<table id="wealthRateTable"></table>
		<div id="wealthRateTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="wealthRateTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="wealthRateTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="wealthRateTableLockOneRow()">锁定</a>
		</div>
	</div>
	<!-- 分类信息(固定类) -->
	<div id="gdiffrentInfo" class="easyui-panel" style="height:auto;"  collapsible="true">
		<form id="gdiffrentInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">咨询服务费支付时间</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="serviceFeePayDate" id="modify_serviceFeePayDate" data-options="validType:['length[0,10]','validDate']"/></td>
						<td align="right">税费率(%)</td>
						<td align="left"><input class="table_input easyui-validatebox " name="taxFee" id="modify_taxFee" data-options="validType:['validDecNum']" /></td>
						<td align="right">通道管理费(%)</td>
						<td align="left"><input class="table_input easyui-validatebox " name="channelFee" id="modify_channelFee" data-options="validType:['validDecNum']" /></td>
					</tr>
				</table>
			</div>
		</form>
		<div class="tableOuterDiv"></div>
	</div>
	
	<div id="wealthIncomeDisInfo" class="easyui-panel" title="固定收益类收益分配信息" style="height:auto;"  collapsible="true">
		<table id="wealthIncomeDisInfoTable"></table>
		<div id="wealthIncomeDisInfoTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="wealthIncomeDisInfoTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="wealthIncomeDisInfoTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="wealthIncomeDisInfoTableLockOneRow()">锁定</a>
		</div>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<div id="wealthStockDisInfo" class="easyui-panel" title="股权类收益分配信息" style="height:auto;"  collapsible="true">
		<table id="wealthStockDisInfoTable"></table>
		<div id="wealthStockDisInfoTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="wealthStockDisInfoTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="wealthStockDisInfoTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="wealthStockDisInfoTableLockOneRow()">锁定</a>
		</div>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- 保险类费用比例 -->
	<div id="insuraceFeeInfo" class="easyui-panel" title="保险费用比例信息" style="height:auto;" collapsible="true">
		<table id="insuraceRateTable"></table>
		<div id="insuraceRateTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="insuraceRateTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="insuraceRateTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="insuraceRateTableLockOneRow()">锁定</a>
		</div>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>

	<!-- 录入信息(公共)-->
	<div id="factorInfo" class="easyui-panel" title="交易录入信息" style="height:auto;" collapsible="true">
		<table id="factorInfoTable"></table>
		<div id="factorInfoTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="factorInfoTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="factorInfoTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="factorInfoTableLockOneRow()">锁定</a>
		</div>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<div style="margin-bottom: 3px;">
<!-- 		<a href="#" onclick="submitUpdateProductInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a> -->
		<a href="#" onclick="addFileInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">附件信息查看</a>
	    <!-- <a href="#" onclick="backListProductPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a> -->
	</div>
	
</div>
