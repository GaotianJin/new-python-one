<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/report/fixedDetailInit.js"></script>
<input type="hidden" name="productId" id="detail_ProductId" value="${productId}">
<input type="hidden" name="productTypeCode" id="detail_productTypeCode" value="${productTypeCode}">
<input type="hidden" name="productSubTypeCode" id="detail_productSubTypeCode" value="${productSubTypeCode}">
<input type="hidden" name="productStatus" id="detail_productStatus" value="${productStatus}">
<input type="hidden" name="roleId" id="detail_roleId" value="${roleId}">


<div id="tabdiv11" class="outerPanel">
<div id="wealthProductBasicInfo" class="easyui-panel"  title="产品基本信息"  style="height:auto;" collapsible="true">
			<form id="detailProduct_basicInfoForm">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">基金管理人</td>
							<td align="left">
								<input class="table_input4" id="updateProduct_agencyCode" name="agencyComId" data-options="required:true" disabled="disabled"/></td>
							<td class="table_text" align="right">产品代码</td>
							<td align="left">
								<input class="table_input4 easyui-validatebox" name="productCode" id="updateProduct_productCode" data-options="validType:['length[0,20]','validCode']" Onchange="productCodeToUpperCase()" disabled="disabled"/>
							</td>
							<td class="table_text" align="right">产品名称</td>
							<td align="left">
								<input class="table_input4" name="productName" id="updateProduct_productName" data-options="required:true,validType:['length[0,100]']" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">产品简称</td>
							<td align="left">
								<input class="table_input4 easyui-validatebox" name="productShortName" id="updateProduct_productShortName" data-options="validType:['length[0,100]']" disabled="disabled"/>
							</td>
							<td class="table_text" align="right">产品类型</td>
							<td align="left">
								<input class="table_input4" name="productType" id="updateProduct_productType" data-options="required:true" disabled="disabled"/>
							</td>
							<td class="table_text" align="right">产品子类型</td>
							<td align="left">
								<input class="table_input4" id="updateProduct_productSubType" name="productSubType" data-options="required:true" disabled="disabled"/>
							</td>
						</tr>
						<tr>
						    <td class="table_text" align="right">外部引入</td>
							<td align="left">
								<input class="table_input4" name="introduceDate" id="updateProduct_introduceDate" data-options="required:true,validType:['length[0,10]','validDate']" disabled="disabled"/>
							</td>
							<td class="table_text" align="right">销售状态</td>
							<td align="left">
								<input class="table_input4" id="updateProduct_salesStatus" name="salesStatus" data-options="required:true" disabled="disabled"/>
							</td>
		                    <td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="table_text" align="right">财富顾问预约</td>
		                    <td align="left">
								<input class="table_input4" id="updateProduct_isOrder" name="isOrder" disabled="disabled"/>
							</td>
						    <td class="table_text" align="right">是否生成邀请码</td>
		                    <td align="left">
								<input class="table_input4" id="updateProduct_isInviteCode" name="isInviteCode" disabled="disabled"/>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</form>
			<!-- 撑开5个像素的距离 -->
		<div class="tableOuterDiv"></div>
		</div>
		
		<div id="wealthProductCoreInfo" class="easyui-panel" title="财富产品信息" style="height:auto;" collapsible="true">
		<form id="wealthProductInfoForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">产品类别</td>
						<td  align="left">
						<input class="table_input4" id="modify_wealthCategory" name="wealthCategory" data-options="required:true" disabled="disabled"/></td>
						<td align="right" class="table_text">成立日</td>
						<td align="left">
						<input class="table_input4" name="foundDate" id="modify_foundDate" data-options="required:true" disabled="disabled"/></td>
						<td align="right" class="table_text">投资总额(万元)</td>
						<td align="left">
						<input class="table_input4 easyui-validatebox" name="financingScale" id="modify_financingScale" data-options="validType:['validDecNum']" disabled="disabled"/></td>
					</tr>
					<tr>
						<td align="right" class="table_text">风险等级</td>
						<td align="left">
						<input class="table_input4" id="modify_grade" name="grade" disabled="disabled"/></td>
						<td align="right" class="table_text">受益权类型</td>
						<td align="left">
						<input class="table_input4" id="modify_beneficialTypes" name="beneficialTypes" data-options="required:true" disabled="disabled"/></td>
					 	<td align="right" class="table_text">&nbsp;</td>
						<td><span class="comboSpan"></span>&nbsp;</td>
					</tr>
					 <tr>
						<td align="right" class="table_text">起投金额(元)</td>
						<td align="left"><input class="table_input4 easyui-validatebox" name="startInvestMoney" id="updatePdCoreInfo_startInvestMoney" data-options="validType:['validDecNum']" disabled="disabled"/></td>
						<td align="right" class="table_text">投资限额(元)</td>
						<td align="left"><input class="table_input4 easyui-validatebox" name="investLimitMoney" id="updatePdCoreInfo_investLimitMoney" data-options="validType:['validDecNum']" disabled="disabled"/></td>
					 	<td align="right" class="table_text">递增金额(元)</td>
						<td align="left"><input class="table_input4 easyui-validatebox" name="investIncreaseMoney" id="updatePdCoreInfo_investIncreaseMoney" data-options="validType:['validDecNum']" disabled="disabled"/></td>
					</tr>
					<tr>
						<td align="right" class="table_text">小号数量(上限)</td>
						<td align="left"><input class="table_input4 easyui-numberbox" name="smallNumber" id="modify_smallNumber" data-options="min:0" disabled="disabled"/></td>
						<td align="right" class="table_text">募集开始日</td>
						<td align="left"><input class="table_input4" name="raiseStartDate" id="modify_raiseStartDate" data-options="required:true" disabled="disabled"/></td>
						<td align="right" class="table_text">募集结束日</td>
						<td align="left"><input class="table_input4" name="raiseEndDate" id="modify_raiseEndDate" data-options="required:true" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td align="right" class="table_text">封闭期(数值)</td>
						<td align="left"><input class="table_input4"  id="modify_closeDperiod" name=closeDperiods  data-options="required:true,min:0" disabled="disabled"/></td>
						<td align="right" class="table_text">封闭期限(数值单位)</td>
						<td align="left"><input class="table_input4" id="modify_closeDperiodUnit" name="closeDperiodUnit" disabled="disabled"/></td>
						<td align="right" class="table_text">
						 	<span id="modify_promptTransferWorkDays">收益分配划款工作日数</span>
						</td>
						<td>
							<input class="table_input4 easyui-validatebox" id="modify_transferWorkDays" name="transferWorkDays" data-options="validType:['length[0,4]']" disabled="disabled"/>
						</td>
					</tr>
					
					
					<tr id="closeDperiods_redemptionFee">
					
					<td align="right" class="table_text">封闭期类型</td>
						<td align="left"><input class="table_input4" id="modify_closeDperiodType" name="closeDperiodType" disabled="disabled"/></td>
						<td align="right" class="table_text"><span class="comboSpan" id="redemptionFee1">赎回费率(%)</span></td>
						<td align="left">
						<input class="table_input4 easyui-validatebox"  id="modify_redemptionFee" name="closeDperiodFee"  data-options="validType:['validDecNum']" disabled="disabled"/></td>
						<td align="right" class="table_text">&nbsp;</td>
						<td><span class="comboSpan"></span>&nbsp;</td>
					</tr>
					
					
			        <tr>
						<td align="right" class="table_text"><span id="promptIncomeWay">收益方式</span></td>
						<td align="left"><input class="table_input4" id="modifyPdCoreInfo_incomeWay" name="incomeWay" disabled="disabled"/></td>
						<td align="right" class="table_text">
						<span id="prompthistoryEarnrate">历史收益(%)</span>
						</td>
				    	<td align="left"><input class="table_input4 easyui-validatebox" name="historyEarnrate" id="modifyPdCoreInfo_historyEarnrate" disabled="disabled"/></td>
							<td align="right" class="table_text">
						<span id="prompthistoryEarnratePeriod">历史收益期间</span>
						</td>
				    	<td align="left"><input class="table_input4 easyui-validatebox" name="historyEarnratePeriod" id="modifyPdCoreInfo_historyEarnratePeriod" disabled="disabled"/></td>
			        </tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的距离 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- 分类信息(浮动/股权类) -->
	<div id="fgTypeInfo" class="easyui-panel" title="财富费用信息" style="height:auto;"  collapsible="true">
		<form id="fgTypeForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">认购费比例(%)</td>
						<td align="left"><input class="table_input4 easyui-validatebox" name="subscriptionFeeRatio" id="modify_subscriptionFeeRatio"  data-options="validType:['validDecNum']" disabled="disabled"/></td>
						<td align="right" class="table_text">认购费支付时间</td>
						<td align="left"><input class="table_input4" name="subscriptionPayDate" id="modify_subscriptionPayDate" data-options="validType:['length[0,10]','validDate']" disabled="disabled"/></td>
						<td align="right">税费率(%)</td>
						<td align="left"><input class="table_input4 easyui-validatebox " name="taxFee" id="modify_taxFee1" data-options="validType:['validDecNum']" disabled="disabled"/></td>
					</tr>
					<tr>
						<td align="right" class="table_text">固定管理费比例(%)</td>
						<td align="left"><input class="table_input4 easyui-validatebox" name="fixManagementFeeRatio" id="modify_fixManagementFeeRatio" data-options="validType:['validDecNum']" disabled="disabled"/></td>
						<td align="right" class="table_text">浮动管理费比例(%)</td>
						<td align="left"><input class="table_input4 easyui-validatebox" name="floatManagementFeeRatio" id="modify_floatManagementFeeRatio" data-options="validType:['validDecNum']" disabled="disabled"/></td>
						<td align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="table_text">浮动管理费支付时间</td>
						<td align="left" colspan="5" >
							<span class="comboSpan"></span>
							<textArea name="floatManagementFeePayDate" id="modify_floatManagementFeePayDate" rows="2" cols="80" class="table_textarea" disabled="disabled"/></textArea></td>
					</tr>
					<tr>
						<td align="left" class="table_text">固定管理费支付时间</td>
						<td align="left" colspan="5" >
							<span class="comboSpan"></span>
							<textArea name="fixManagementFeePayDate" id="modify_fixManagementFeePayDate" rows="2" cols="80" class="table_textarea" disabled="disabled"/></textArea></td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- 财富类费用比例(固定类) -->
	<div id="wealthFeeInfo" class="easyui-panel" title="财富费用信息" style="height:auto;" collapsible="true">
		<table id="wealthRateTable"></table>
		<!-- <div id="wealthRateTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="wealthRateTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="wealthRateTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="wealthRateTableLockOneRow()">锁定</a>
		</div> -->
	</div>
	<!-- 分类信息(固定类) -->
	<div id="gTypeInfo" class="easyui-panel" style="height:auto;"  collapsible="true">
		<form id="gTypeForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td align="right" class="table_text">咨询服务费支付时间</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input4" name="serviceFeePayDate" id="modify_serviceFeePayDate" data-options="validType:['length[0,10]','validDate']" disabled="disabled"/></td>
						<td align="right">税费率(%)</td>
						<td align="left"><input class="table_input4 easyui-validatebox " name="taxFee" id="modify_taxFee" data-options="validType:['validDecNum']" disabled="disabled"/></td>
						<td align="right">通道管理费(%)</td>
						<td align="left"><input class="table_input4 easyui-validatebox " name="channelFee" id="modify_channelFee" data-options="validType:['validDecNum']" disabled="disabled"/></td>
					</tr>
				</table>
			</div>
		</form>
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- 固定类 -->
	<div id="wealthIncomeDisInfo" class="easyui-panel" title="固定收益类收益分配信息" style="height:auto;"  collapsible="true">
		<table id="wealthIncomeDisInfoTable"></table>
		<!-- <div id="wealthIncomeDisInfoTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="wealthIncomeDisInfoTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="wealthIncomeDisInfoTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="wealthIncomeDisInfoTableLockOneRow()">锁定</a>
		</div> -->
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- 录入信息(公共)-->
	<div id="factorInfo" class="easyui-panel" title="交易录入信息" style="height:auto;" collapsible="true">
		<table id="factorInfoTable"></table>
		<!-- <div id="factorInfoTable_tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="factorInfoTableAddOneRow()">新增</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="factorInfoTableRemoveOneRow()">删除</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="factorInfoTableLockOneRow()">锁定</a>
		</div> -->
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<div style="margin-bottom: 3px;">
		<a href="#" id="detailProduct_loadFileInfo" onclick="loadFileInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">上传附件</a>
		<a href="#" id="detailProduct_checkFileInfo" onclick="loadFileInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">附件信息查看</a>
	    <a href="#" onclick="backListDetailProduct()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	</div>
</div>