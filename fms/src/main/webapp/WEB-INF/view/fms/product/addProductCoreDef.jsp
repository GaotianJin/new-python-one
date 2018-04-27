<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addProductCoreDefInit.js"></script>



<input type="hidden" name="ProductId" id="addProductCoreInfo_ProductId" value="${productId}">
<input type="hidden" name="ProductType" id="addProductCoreInfo_ProductType" value="${productType}">
<input type="hidden" name="ProductSubType" id="addProductCoreInfo_ProductSubType" value="${productSubType}">

<!--产品信息新增页面 -->
<div id="tabdiv11" class="outerPanel">
	<!-- 财富产品信息 -->
	<div id="wealthProductInfo" class="easyui-panel" title="财富产品信息" style="height:auto;" collapsible="true">
		<form id="wealthProductInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">产品类别</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="addProductCoreDef_wealthCategory" name="wealthCategory" data-options="required:true" ></td>
						<td align="right" class="table_text">成立日</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="foundDate" id="foundDate" data-options="required:true,validType:['length[0,10]','validDate']"/></td>
						<td align="right" class="table_text">投资总额(万元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="financingScale" id="financingScale" data-options="required:true,validType:['validDecNum']" /></td>
					</tr>
					<tr>
						<td align="right" class="table_text">风险等级</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="grade" name="grade" data-options="required:true"></td>
						<td align="right" class="table_text">受益权类型</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="beneficialTypes" name="beneficialTypes" data-options="required:true"></td>
						<td align="right" class="table_text">&nbsp;&nbsp;</td>
						<td>&nbsp;</td>
					</tr>					
					<tr>
						<td align="right" class="table_text">起投金额(元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="startInvestMoney" id="addPdCoreInfo_startInvestMoney" data-options="validType:['validDecNum']" /></td>
						<td align="right" class="table_text">投资限额(元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="investLimitMoney" id="addPdCoreInfo_investLimitMoney" data-options="validType:['validDecNum']" /></td>
					 	<td align="right" class="table_text">递增金额(元)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="investIncreaseMoney" id="addPdCoreInfo_investIncreaseMoney" data-options="validType:['validDecNum']" /></td>
					</tr>
					<tr>
						<!-- <td align="right" class="table_text">小号数量(上限)</td>
						<td align="left"><input class="table_input easyui-numberbox" name="smallBigint" id="smallNumber"  data-options="min:0"/></td> -->
						<td align="right" class="table_text">募集开始日</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="raiseStartDate" id="raiseStartDate" data-options="required:true"/></td>
						<td align="right" class="table_text">募集结束日</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datetimebox" name="raiseEndDate" id="raiseEndDate" data-options="required:true"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right" class="table_text">封闭期</td>
						<td align="left"><input class="table_input easyui-validatebox"  id="modify_closeDperiod" name=closeDperiods data-options="validType:['closePeriod']" />以"/"隔开,如6/9</td>
						<td align="right" class="table_text">封闭期限(数值单位)</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="closeDperiodUnit" name="closeDperiodUnit" data-options="required:true"/></td>
						<td align="right" class="table_text">封闭期特殊说明</td>
						<td><input class="table_input easyui-textbox" id="investPeriodDeclare" name="investPeriodDeclare"/></td>		
					</tr>
					
					<!-- 封闭期新增 -->
					<tr id="closeDperiods_redemptionFee">		
					    <td align="right" class="table_text">封闭期类型</td>
						<td><span class="comboSpan"></span><input class="table_input easyui-combobox" id="closeDperiodType" name="closeDperiodType"/></td>					
						<td align="right" class="table_text"><span class="comboSpan" id="redemptionFee1">赎回费率(%)</span></td>
						<td align="left"><input class="table_input easyui-validatebox"  id="redemptionFee" name="closeDperiodFee"  data-options="validType:['validDecNum']" /></td>						
					<!-- <td align="right" class="table_text cooperationPartner" >合作伙伴</td>
						<td class="cooperationPartner" colspan="1" ><input class="table_input easyui-validatebox"  name="cooperationPartner" /></td> -->
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>					
					<tr>
						<td id="promptIncomeWay" align="right" class="table_text"><span >收益方式</span></td>
						<td id="promptIncomeWayTd"><span class="comboSpan"></span><input class="table_input easyui-combobox1" id="addPdCoreInfo_incomeWay" name="incomeWay" ></td>
						<!-- <td class="table_text addPdCoreInfo_historyEarnrateTd" align="right" >历史收益(%)</td> 
				        <td class="addPdCoreInfo_historyEarnrateTd" align="left"><input class="table_input easyui-validatebox" name="historyEarnrate" id="addPdCoreInfo_historyEarnrate"  data-options="validType:['validDecNum']" /></td> -->
						
						<!-- <td align="right" class="table_text">
						<span id="prompthistoryEarnrate">历史收益(%)</span>
						</td>
				    	<td align="left"><input class="easyui-validatebox " name="historyEarnrate" id="addPdCoreInfo_historyEarnrate" data-options="validType:['validDecNum']"/></td> -->
						
						<!-- <td class="prompthistoryEarnratePeriod" align="right" class="table_text">
						<span>历史收益期间</span>
						</td>
				    	<td class="prompthistoryEarnratePeriod" align="left"><input class="table_input easyui-validatebox" name="historyEarnratePeriod" id="addPdCoreInfo_historyEarnratePeriod"/></td> -->
				    	
				    	<td id="promptTransferWorkDays" align="right" class="table_text"><span>收益分配划款工作日数</span></td>
						<td id="transferWorkDays"><input class="table_input easyui-validatebox"  name="transferWorkDays" data-options="validType:['length[0,4]']"/></td>	
			        <td align="right" class="table_text cooperationPartner" >合作伙伴</td>
						<td class="cooperationPartner" colspan="1" ><input class="table_input easyui-validatebox"  name="cooperationPartner" /></td>
			        </tr>
			        <tr id="netvaluedisclosure" hidden><!-- 此行只有浮动类才显示 -->
					<td align="right" class="table_text"></span>净值披露频率</td>
					<td><span class="comboSpan"></span><input id="netValueDisclosureinput" class="table_input easyui-combobox" name="netValueDisclosure"  hidden></td>
					<td align="right" class="table_text"></span>开放日规则</td>
					<td><span class="comboSpan"></span><input id="expectOpenDay" class="table_input easyui-combobox" name="expectOpenDayRules"  hidden></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					</tr>
<!-- 				<tr>
						<td align="left" class="table_text">收益说明</td>
						<td align="left" colspan="5" class="table_input">
							<span class="comboSpan"></span>
							<textArea name="description" id="description" rows="2" cols="90" class="table_textarea"></textArea></td>
					</tr>
					<tr>
						<td align="left" class="table_text">资金用途</td>
						<td align="left" colspan="5" class="table_input">
							<span class="comboSpan"></span>
							<textArea name="fundSusing" id="fundSusing" rows="2" cols="90" class="table_textarea"></textArea></td>
					</tr> -->
					<tr>
					<td align="right" class="table_text productFeature" >产品特点</td>
					 	<td align="left" colspan="5" class="productFeature" >
							<span class="comboSpan"></span>
							<textArea name="productFeature" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea></td>
						<!-- <td align="right" class="table_text productFeature" >产品特点</td>
						<td class="productFeature" colspan="1" ><input class="table_input easyui-validatebox"  name="productFeature" /></td> -->
						
						<!-- <td align="right" class="table_text riskControlMeasure">风控措施</td>
						<td class="riskControlMeasure" colspan="1" ><input class="table_input easyui-validatebox"  name="riskControlMeasure" /></td> -->
						
						<!-- <td align="right" class="table_text cooperationPartner" >合作伙伴</td>
						<td class="cooperationPartner" colspan="1" ><input class="table_input easyui-validatebox"  name="cooperationPartner" /></td> -->
					</tr>
					<tr>
					<td align="right" class="table_text riskControlMeasure" >风控措施</td>
					 	<td align="left" colspan="5" class="riskControlMeasure" >
							<span class="comboSpan"></span>
							<textArea name="riskControlMeasure" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea></td>
					</tr>
					<!-- <tr>
					<td align="right" class="table_text cooperationPartner" >合作伙伴</td>
					 	<td align="left" colspan="3" class="cooperationPartner" >
							<span class="comboSpan"></span>
							<textArea name="cooperationPartner" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea></td>
					</tr> -->
					<tr>
					<td align="right" class="table_text raiseAccount" >募集账户</td>
					 	<td align="left" colspan="5" class="raiseAccount" >
							<span class="comboSpan"></span>
							<textArea name="raiseAccount" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea></td>
						<!-- <td align="right" class="table_text raiseAccount">募集账户</td>
						<td class="raiseAccount" colspan="1" ><input class="table_input easyui-validatebox"  name="raiseAccount"/></td> -->
						<!-- <td align="right" class="table_text trusteeshipAccount">托管账户</td>
						<td class="trusteeshipAccount" colspan="1" ><input class="table_input easyui-validatebox"  name="trusteeshipAccount"/></td> -->
					</tr>
					<tr>
					<td align="right" class="table_text trusteeshipAccount" >托管账户</td>
					 	<td align="left" colspan="5" class="trusteeshipAccount" >
							<span class="comboSpan"></span>
							<textArea name="trusteeshipAccount" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea></td>
					</tr>
					
					<!-- <tr>
						<td align="right" class="table_text investDirection" hidden>投资方向</td>
						<td class="investDirection" colspan="3" hidden><input class="table_input1 easyui-validatebox"  name="investDirection" data-options="required:true"/></td>
						<td align="right" class="table_text investStrategy" hidden>投资策略</td>
						<td class="investStrategy" colspan="3" hidden><input class="table_input1 easyui-validatebox"  name="investStrategy" data-options="required:true"/></td>
						<td align="right" class="table_text investScope" hidden>投资范围</td>
						<td class="investScope" colspan="3" hidden><input class="table_input1 easyui-validatebox"  name="investScope" data-options="required:true"/></td>
					</tr> -->
					
					<tr>
					 	<td align="right" class="table_text investDirection" hidden>投资方向</td>
					 	<td align="left" colspan="5" class="investDirection" hidden>
							<span class="comboSpan"></span>
							<textArea name="investDirection" id="investDirection" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true" ></textArea></td>
						<td align="right" class="table_text investStrategy" hidden>投资策略</td>
						<td align="left" colspan="5" class="investStrategy" hidden>
							<span class="comboSpan"></span>
							<textArea name="investStrategy" id="investStrategy" rows="2" cols="80" class="table_textarea easyui-validatebox" data-options="required:true"></textArea></td>
						<td align="right" class="table_text investScope" hidden>投资范围</td>
					   <td align="left" colspan="5"  class="investScope" hidden>
							<span class="comboSpan"></span>
							<textArea name="investScope" id="investScope" rows="2" cols="80"  class="table_textarea easyui-validatebox" data-options="required:true"></textArea></td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- 寿险产品信息 -->
	<div id="insuranceInfo" class="easyui-panel" title="保险产品信息" style="height:auto;" collapsible="true">
		<form id="insuranceInfoForm">
			<div class="top_table">
				<table id="riskInfo" width="100%" border="0" cellspacing="0"
					cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">主附险标记</td>
						<td><span class="comboSpan"></span><input
							class="table_input easyui-combobox1" id="prFlag" name="prFlag"
							data-options="required:true"></td>
						<td align="right" class="table_text">产品特征</td>
						<td><span class="comboSpan"></span><input
							class="table_input easyui-combobox1" id="riskFeatures"
							name="riskFeatures"></td>
							<td align="right" class="table_text">基本保费(元)</td>
						<td align="left"><input class="table_input easyui-validatebox"
							name="basicPrem" id="basicPrem"
							data-options="validType:['validDecNum']" /></td>
					</tr>
					<tr>
						<td align="right" class="table_text">最小投保年龄</td>
						<td align="left"><input class="table_input easyui-numberbox"
							name="minAppAge" id="minAppAge"
							data-options="min:0,validType:['length[0,3]']" /></td>
						<td align="right" class="table_text">最小投保年龄单位</td>
						<td><span class="comboSpan"></span><input
							class="table_input easyui-combobox1" id="minAppAgeUnit"
							name="minAppAgeUnit"></td>
		                <td align="right" class="table_text">预定利率(%)</td>
						<td align="left"><input class="table_input easyui-validatebox"
							name="interestRate" id="interestRate"
							data-options="validType:['validDecNum']" /></td>
					</tr>
					<tr>
						<td align="right" class="table_text">最大投保年龄</td>
						<td align="left"><input class="table_input easyui-numberbox"
							name="maxAppAge" id="maxAppAge"
							data-options="min:0,validType:['length[0,3]']" /></td>
						<td align="right" class="table_text">最大投保年龄单位</td>
						<td><span class="comboSpan"></span><input
							class="table_input easyui-combobox1" id="maxAppAgeUnit"
							name="maxAppAgeUnit"></td>
						<td align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="table_text">其他说明</td>
						<td align="left" colspan="5" class="table_input"><span class="comboSpan"></span> 
						<textArea name="description" id="description" rows="2" cols="90" class="table_textarea"></textArea></td>
					</tr>
				</table>
			</div>
			<div class="top_table">
				<table id="riskInfo2" width="100%" border="0" cellspacing="0"
					cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">主附险标记</td>
						<td><span class="comboSpan"></span><input
							class="table_input easyui-combobox1" id="prFlag_2" name="prFlag"
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
								id="description" rows="2" cols="50" class="table_textarea"></textArea></td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	

	<!-- 分类信息(浮动/股权类) -->
	<div id="fdiffrentInfo" class="easyui-panel" title="财富费用信息" style="height:auto;" collapsible="true">
		<form id="fdiffrentInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">认购费比例(%)</td>
						<td align="left"><input class="table_input easyui-validatebox " name="subscriptionFeeRatio" id="subscriptionFeeRatio" data-options="validType:['validDecNum']" /></td>
						<td align="right" class="table_text">认购费支付时间</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="subscriptionPayDate" id="subscriptionPayDate"  data-options="validType:['length[0,10]','validDate']"/></td>
						<td align="right" class="table_text">税费率(%)</td>
						<td align="left"><input class="table_input easyui-validatebox " name="taxFee" id="taxFee" data-options="validType:['validDecNum']" /></td>
					</tr>
					<tr>
						<td align="right" class="table_text">固定管理费比例(%)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="fixManagementFeeRatio" id="fixManagementFeeRatio" data-options="validType:['validDecNum']"/></td>
						<td align="right" class="table_text">浮动管理费比例(%)</td>
						<td align="left"><input class="table_input easyui-validatebox" name="floatManagementFeeRatio" id="floatManagementFeeRatio" data-options="validType:['validDecNum']"/></td>
						<td align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="table_text">浮动管理费支付时间</td>
						<td align="left" colspan="3" >
							<span class="comboSpan"></span>
							<textArea name="floatManagementFeePayDate" id="floatManagementFeePayDate" rows="2" cols="90" class="table_textarea" ></textArea></td>
					</tr>
					<tr>
						<td align="left" class="table_text">固定管理费支付时间</td>
						<td align="left" colspan="3" >
							<span class="comboSpan"></span>
							<textArea name="fixManagementFeePayDate" id="fixManagementFeePayDate" rows="2" cols="90" class="table_textarea" ></textArea></td>
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
	<div id="gdiffrentInfo" class="easyui-panel"  style="height:auto;" collapsible="true">
		<form id="gdiffrentInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">咨询服务费支付时间</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" name="serviceFeePayDate" id="serviceFeePayDate" data-options="validType:['length[0,10]','validDate']"/></td>
						<td align="right" class="table_text">税费率(%)</td>
						<td align="left"><input class="table_input easyui-validatebox " name="taxFee" id="taxFee" data-options="validType:['validDecNum']" /></td>
						<td align="right" class="table_text">通道管理费率(%)</td>
						<td align="left"><input class="table_input easyui-validatebox " name="channelFee" id="channelFee" data-options="validType:['validDecNum']" /></td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<div id="wealthIncomeDisInfo" class="easyui-panel" title="固定收益类收益分配信息" style="height:auto;" collapsible="true">
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
		<a href="#" onclick="submitAddProductCoreInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
		<a href="#" onclick="addFileInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">上传附件</a>
	</div>
</div>
