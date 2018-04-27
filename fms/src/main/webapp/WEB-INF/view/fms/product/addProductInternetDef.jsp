<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addProductInternetDefInit.js"></script>
<input type="hidden" name="ProductId" id="addGDPdInternetInfo_ProductId" value="${productId}">
<input type="hidden" name="ProductType" id="addGDPdInternetInfo_ProductType" value="${productType}">
<input type="hidden" name="ProductSubType" id="addGDPdInternetInfo_ProductSubType" value="${productSubType}">
<!--网销信息新增页面 -->
<div id="tabdiv11" class="outerPanel">
	<!-- 固定财富产品信息 -->
	<div id="addPdInternet_GDwealthPdDescFormDoiv" class="easyui-panel" title="财富产品说明信息" style="height:auto;" collapsible="true">
		<form id="addPdInternet_GDwealthPdDescForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right" class="table_text">产品特色</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productFeatures" id="addGDPdInternet_productFeatures" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">以中文；表示换行</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">产品费用</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productCostDesc" id="addGDPdInternet_productCostDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户产品额外收费信息，以中文；表示换行</td>
						</tr> 
						<tr>
						    <td align="right" class="table_text">投资方向</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="fundSusingDesc" id="addGDPdInternet_fundSusingDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户产品投资方向或资金用途</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">收益说明</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="historyEarnRateDesc" id="addGDPdInternet_historyEarnRateDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">&nbsp;</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">增信措施</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="addPromotionMeasures" id="addGDPdInternet_addPromotionMeasures" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户该产品投资风险和安全评估措施</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">温馨提示</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="warmWarnDesc" id="addGDPdInternet_warmWarnDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户其他应知信息，以中文；表示换行，以1、2、3、标明序号，非必填项</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">优惠信息</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="preferentialDesc" id="addGDPdInternet_preferentialDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户优惠信息，非必填项</td>
						</tr>
						
				</table>
			  <br>
			</div>
		</form>
		<!--撑开5个像素的间隔-->
		<div class="tableOuterDiv"></div>
	</div> 
    <!-- 浮动财富产品信息 -->
	<div id="addPdInternet_FDwealthPdDescFormDoiv" class="easyui-panel" title="财富产品说明信息" style="height:auto;" collapsible="true">
		<form id="addPdInternet_FDwealthPdDescForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
						    <td align="right" class="table_text">产品特色</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productFeatures" id="addFDPdInternet_productFeatures" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">以中文；表示换行</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">收益说明</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="historyEarnRateDesc" id="addFDPdInternet_historyEarnRateDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">&nbsp;</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">产品费用</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productCostDesc" id="addFDPdInternet_productCostDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户产品额外收费信息，以中文；表示换行</td>
						</tr>
						<tr>
						   	<td align="right" class="table_text">投资方向</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="fundSusingDesc" id="addFDPdInternet_fundSusingDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户产品投资方向或资金用途</td>
							
						</tr>
					    <tr>
						   	<td align="right" class="table_text">开放日</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="opendayDesc" id="addFDPdInternet_openDayDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户开放日大概时间</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">管理机构</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="agncyComName" id="addFDPdInternet_agncyComName" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户管理人</td>
						</tr>
						<tr>
						   	<td align="right" class="table_text">投顾介绍</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="agentName" id="addFDPdInternet_agentName" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户管理机构情况及投资理念，以中文；表示换行</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">封闭期说明</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="closedPeriodDesc" id="addFDPdInternet_closedPeriodDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">&nbsp;</td>
						</tr>
						<tr>
						    <td align="right" class="table_text">温馨提示</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="warmWarnDesc" id="addFDPdInternet_warmWarnDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户其他应知信息，以中文；表示换行，以1、2、3、标明序号，非必填项</td>
						<tr>
						<tr>
						    <td align="right" class="table_text">优惠信息</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="preferentialDesc" id="addFDPdInternet_preferentialDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户优惠信息，非必填项</td>
						</tr>
				</table>
			  <br>
			</div>
		</form>
		<!--撑开5个像素的间隔-->
		<div class="tableOuterDiv"></div>
	</div> 
	
    <!-- 保险产品说明信息 -->
	<div id="addPdInternet_riskPdDescFormDoiv" class="easyui-panel" title="保险产品说明信息" style="height:auto;" collapsible="true">
		<form id="addPdInternet_riskProductDescForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
							<td align="right" class="table_text">产品特色</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productFeatures" id="addRiskPdInternet_productFeatures" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">网页/APP重要展示信息，以中文；表示换行</td>
					</tr>
					<tr>
							<td align="right" class="table_text">投保须知</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="insuranceInformation" id="addRiskPdInternet_insuranceInformation" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户投保须知，以中文；表示换行，以1、2、3、标明序号，非必填项</td>
					</tr>
					<tr>
							<td align="right" class="table_text">保险期间说明</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="insuredPeriodDesc" id="addRiskPdInternet_insuredPeriodDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right"></td>
					</tr>
					
					<tr>
							<td align="right" class="table_text">理赔说明</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="claimsIntrouction" id="addRiskPdInternet_claimsIntrouction" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户理赔说明，以中文；表示换行，以1、2、3、标明序号，非必填项</td>
					</tr>
					<tr>
						    <td align="right" class="table_text">温馨提示</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="warmWarnDesc" id="addRiskPdInternet_warmWarnDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户其他应知信息，以中文；表示换行，以1、2、3、标明序号，非必填项</td>
					</tr>
					<tr>
						    <td align="right" class="table_text">优惠信息</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="preferentialDesc" id="addRiskPdInternet_preferentialDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">告知客户优惠信息，非必填项</td>
					</tr>
				</table>
			  <br>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	<!-- SEO区GD产品信息 -->
	<div id="addPdInternet_GDSEOwealthPdDescSEOFormDoiv" class="easyui-panel" title="SEO信息" style="height:auto;" collapsible="true">
		<form id="addPdInternet_GDSEOwealthPdDescForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					    <tr>
							<td align="right" class="table_text">产品标题</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productTitle" id="addGDPdInternet_productTitle" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
							
						</tr>
						<tr>
							<td align="right" class="table_text">产品关键字</td>
							<td colspan="4" style="width:60%" ><span class="comboSpan"></span>
							<textArea name="productKeyword" id="addGDPdInternet_productKeyword" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
						</tr>
						<tr>
						
							<td align="right" class="table_text">产品描述</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productDesc" id="addGDPdInternet_productDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
						</tr>
				</table>
			  <br>
			</div>
		</form>
		<!--撑开5个像素的间隔-->
		<div class="tableOuterDiv"></div>
	</div> 
	
	
	<!-- 浮动财SEO富产品信息 -->
	<div id="addPdInternet_FDSEOwealthPdDescFormDoiv" class="easyui-panel" title="SEO信息" style="height:auto;" collapsible="true">
		<form id="addPdInternet_FDSEOwealthPdDescForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					    <tr>
							<td align="right" class="table_text">产品标题</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productTitle" id="addFDPdInternet_productTitle" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
							
						</tr>
						<tr>
							<td align="right" class="table_text">产品关键字</td>
							<td colspan="4" style="width:60%" ><span class="comboSpan"></span>
							<textArea name="productKeyword" id="addFDPdInternet_productKeyword" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
						</tr>
						<tr>
						
							<td align="right" class="table_text">产品描述</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productDesc" id="addFDPdInternet_productDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
						</tr>
				</table>
			  <br>
			</div>
		</form>
		<!--撑开5个像素的间隔-->
		<div class="tableOuterDiv"></div>
	</div> 
	
	<!-- 保险产品说明信息 -->
	<div id="addPdInternet_riskSEOPdDescFormDoiv" class="easyui-panel" title="SEO信息" style="height:auto;" collapsible="true">
		<form id="addPdInternet_riskSEOProductDescForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
					        <td align="right" class="table_text">产品标题</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productTitle" id="addRiskPdInternet_productTitle" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
					</tr>
				    <tr>
				    		<td align="right" class="table_text">产品关键字</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productKeyword" id="addRiskPdInternet_productKeyword" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
					</tr>
					<tr>
					    	<td align="right" class="table_text">产品描述</td>
							<td colspan="4" style="width:60%"><span class="comboSpan"></span>
							<textArea name="productDescribe" id="addRiskPdInternet_productDesc" rows="2" cols="80" class="table_TextArea"></textArea></td>
							<td class="table_text_right">产品页面推广信息，由网站运营提供</td>
					</tr>
				</table>
			  <br>
			</div>
		</form>
		<!-- 撑开5个像素的间隔 -->
		<div class="tableOuterDiv"></div>
	</div>
	
	
    <!-- 产品营销信息 -->
	<div id="addPdInternetPdSaleSDescDiv" class="easyui-panel" title="产品营销信息" style="height:auto;" collapsible="true">
		<form id="addPdInternet_ProductSaleSDescForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					     <tr>
							<td align="right" class="table_text">推介方式</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_promotionWay" name="promotionWay"></td>
							<td align="right" class="table_text">销售方式</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_saleWay" name="saleWay"></td>
							<td align="right" class="table_text">销售平台</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_saleChnnel" name="saleChnnel"></td>
						</tr>
						<tr>
							<td align="right" class="table_text">网销产品类型</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_internetProductType" name="internetProductType"></td>
							<td align="right" class="table_text">产品类型子类</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_internetProductSubType" name="internetProductsubtype"></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right" class="table_text">支付方式</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_payWay" name="payWay"></td>
							<td align="right" class="table_text">是否热销</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_isHotSale" name="isHotSale"></td>
							<td align="right" class="table_text">业主专享</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_isExclusive" name="isExclusive"></td>
						</tr>
					    <tr>
					    	<td align="right" class="table_text">网页显示</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_isShow" name="isShow" ></td>
							<td align="right" class="table_text">首页显示</td>
							<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addPdInternet_isShowFirst" name="isShowFirst" ></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
				</table>
			  <br>
			</div>
		</form>
	</div>
	
	<div style="margin-bottom: 3px;">
		<a href="#" onclick="submitProductInternetInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
	</div>
</div>