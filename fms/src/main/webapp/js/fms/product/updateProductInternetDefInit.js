var modifyInternetProductType = null;
var modifyInternetProductSubType= null;
var modifyInternetProductId = null;
var internetProductSubTypeCombobox=null;
var internetProductTypeCombobox=null;
var  payWayCombobox=null;
var  promotionWayCombobox=null;
var saleChnnelCombobox=null;

/**
 * 页面初始化加载
 */
jQuery(function($) {
	modifyInternetProductType=$("#updateGDPdInternetInfo_ProductType").val();
	modifyInternetProductSubType=$("#updateGDPdInternetInfo_ProductSubType").val();
	modifyInternetProductId=$("#updateGDPdInternetInfo_ProductId").val();
	initAllCombobox();// 加载所有的下拉框
	getUpdatePDInternetInfo();//获取财富产品互联网信息
	
});

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	
	// 推介方式
	promotionWayCombobox=	$("#updatePdInternet_promotionWay").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=promotionWay',
		valueField : 'code',
		textField : 'codeName',
		required:true
//		multiple : true
/*		,
		editable:false*/
	});
	// 销售方式
	$("#updatePdInternet_saleWay").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=saleWay',
		valueField : 'code',
		textField : 'codeName',
		required:true
/*		,
		editable:false*/
	});
	// 销售渠道
	saleChnnelCombobox=	$("#updatePdInternet_saleChnnel").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=saleChnnel',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		multiple : true
/*		,
		editable:false*/
	});
	// 支付方式
	payWayCombobox=	$("#updatePdInternet_payWay").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=payWay',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		multiple : true
/*		,
		editable:false*/
	});
	
	// 是否显示
	$("#updatePdInternet_isShow").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
/*		,
		editable:false*/
	});
	// 是否热销
	$("#updatePdInternet_isHotSale").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		required:true,
		textField : 'codeName'
	});
	// 是否专享
	$("#updatePdInternet_isExclusive").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		required:true,
		textField : 'codeName'
	});
	
	// 是否显示
	$("#updatePdInternet_isShow").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
/*		,
		editable:false*/
	});
	//是否热销
	$("#updatePdInternet_isHotSale").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
	});
	
	// 首页显示
	$("#updatePdInternet_isShowFirst").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
	});
	
	
	// 网销产品类型
    internetProductTypeCombobox=$("#updatePdInternet_internetProductType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=internetProductType',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		editable:false,
		onSelect:function(){
			var internetProductType = internetProductTypeCombobox.combobox("getValue");
			      //选中保险 
					if(internetProductType=='005'){
						var url=contextPath + '/codeQuery/tdCodeQuery?codeType=internetProductSubType';
						$('#updatePdInternet_internetProductSubType').combobox("enable");
						internetProductSubTypeCombobox.combobox({required:true});
						internetProductSubTypeCombobox.combobox("reload",url); 
					}
					else{
					internetProductSubTypeCombobox.combobox({required:false});
					$('#updatePdInternet_internetProductSubType').combobox('setValues','');  
					$("#updatePdInternet_internetProductSubType").combobox("disable");
					}
				}
	});
	
	// 网销产品子类型
	internetProductSubTypeCombobox= $("#updatePdInternet_internetProductSubType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=internetProductSubType',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	
	/***写了个方法为了拖延时间方法,此种解决方式有点低俗，待更改**/
	if(modifyInternetProductType == "1"){
		loadProductFactor("wealthfactorCode");//加载财富产品录入参数信息
		
	}else{
		loadProductFactor("riskfactorCode");//加载财富产品录入参数信息
	}
	if(modifyInternetProductType == "1"){
		loadProductFactor("wealthfactorCode");//加载财富产品录入参数信息
		
	}else{
		loadProductFactor("riskfactorCode");//加载财富产品录入参数信息
	}
	
 }

function loadProductFactor(factorType){
	$.ajax({
		type : 'post',
		url : contextPath + "/product/queryProductFactor?codeType="+factorType,
		cache : false,
		success : function(resultInfo) {
			try {
				showAndHidePanel();
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}



function showAndHidePanel(){	// 根据产品类型和产品子类加载产品信息页面
	if (modifyInternetProductType == 1){
		if (modifyInternetProductSubType == 01 || modifyInternetProductSubType == 03) {
			$("#updatePdInternet_GDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏固定收益产品说明信息
			$("#updatePdInternet_FDwealthPdDescFormDoiv").parent(".panel").css("display", "");// 展示浮动产品说明信息
			$("#updatePdInternet_riskPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险产品说明信息
			$("#updatePdInternetPdSaleSDescDiv").parent(".panel").css("display", "");// 展示产品营销说明信息
			
			$("#updatePdInternet_FDSEOwealthPdDescFormDoiv").parent(".panel").css("display", "");// 展示浮动SEO区
			$("#updatePdInternet_GDSEOwealthPdDescSEOFormDoiv").parent(".panel").css("display", "none");// 隐藏固定SEO区
			$("#updatePdInternet_riskSEOPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险SEO区
			
			
			
		}else{
			$("#updatePdInternet_GDwealthPdDescFormDoiv").parent(".panel").css("display", "");// 显示固定收益产品说明信息
			$("#updatePdInternet_FDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动产品说明信息
			$("#updatePdInternet_riskPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险产品说明信息
			$("#updatePdInternetPdSaleSDescDiv").parent(".panel").css("display", "");// 展示产品营销说明信息
			
			$("#updatePdInternet_FDSEOwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动SEO区
			$("#updatePdInternet_GDSEOwealthPdDescSEOFormDoiv").parent(".panel").css("display", "");// 展示固定SEO区
			$("#updatePdInternet_riskSEOPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险SEO区
		}
	}else{
		$("#updatePdInternet_GDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏固定收益产品说明信息
		$("#updatePdInternet_FDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动产品说明信息
		$("#updatePdInternet_riskPdDescFormDoiv").parent(".panel").css("display", "");// 隐藏保险产品说明信息
		$("#updatePdInternetPdSaleSDescDiv").parent(".panel").css("display", "");// 展示产品营销说明信息
		
		$("#updatePdInternet_FDSEOwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动SEO区
		$("#updatePdInternet_GDSEOwealthPdDescSEOFormDoiv").parent(".panel").css("display", "none");// 隐藏固定SEO区
		$("#updatePdInternet_riskSEOPdDescFormDoiv").parent(".panel").css("display", "");// 展示保险SEO区
		
	}}



/**
 * 获取互联网财富产品修改信息
 */
function getUpdatePDInternetInfo(){
	var param={};
	param.modifyInternetProductId=modifyInternetProductId;
	param.modifyInternetProductType=modifyInternetProductType;
	param.modifyInternetProductSubType=modifyInternetProductSubType;
	$.ajax({
		type:'post',
		url:contextPath+"/product/getUpdateProductInternetInfo",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(result){
			try {
				if(result.success){
					var resultObj = result.obj;
					//财富产品
					if(modifyInternetProductType=='1'){
						if (modifyInternetProductSubType=='02') {
							setInputValueById("updatePdInternet_GDwealthPdDescForm",resultObj.PDWealthIntroduceInfo);
							setInputValueById("updatePdInternet_GDSeoWealthPdDescForm",resultObj.PDWealthIntroduceInfo);
						}
						else{
							setInputValueById("updatePdInternet_FDwealthPdDescForm",resultObj.PDWealthIntroduceInfo);
							setInputValueById("updatePdInternet_FDSeoWealthPdDescForm",resultObj.PDWealthIntroduceInfo);
						}
						setInputValueById("updatePdInternet_ProductSaleSDescForm",resultObj.pdWealthSalesInfo);
					    if (resultObj.pdWealthSalesInfo != null
										&& resultObj.pdWealthSalesInfo != undefined
										&& resultObj.pdWealthSalesInfo != "") {
					    	
//							alert(resultObj.pdWealthSalesInfo.payWay);
							//处理支付方式
							if(resultObj.pdWealthSalesInfo.payWay!=null&&resultObj.pdWealthSalesInfo.payWay!=undefined&&resultObj.pdWealthSalesInfo.payWay!=""){
								var payWay=resultObj.pdWealthSalesInfo.payWay.split(",");
//								console.info(payWay);
								$('#updatePdInternet_payWay').combobox('setValues', payWay);
							}
//							alert(resultObj.pdWealthSalesInfo.saleChnnel);
							//处理销售渠道
							if(resultObj.pdWealthSalesInfo.saleChnnel!=null&&resultObj.pdWealthSalesInfo.saleChnnel!=undefined&&resultObj.pdWealthSalesInfo.saleChnnel!=""){
								var saleChnnel=resultObj.pdWealthSalesInfo.saleChnnel.split(",");
//								console.info(saleChnnel);
								$('#updatePdInternet_saleChnnel').combobox('setValues', saleChnnel);
							}
//							alert(resultObj.pdWealthSalesInfo.promotionWay);
							//处理推介方式
						/*	if(resultObj.pdWealthSalesInfo.promotionWay!=null&&resultObj.pdWealthSalesInfo.promotionWay!=undefined&&resultObj.pdWealthSalesInfo.promotionWay!=""){
								var promotionWay=resultObj.pdWealthSalesInfo.promotionWay.split(",");
//								console.info(promotionWay);
								$('#updatePdInternet_promotionWay').combobox('setValues', promotionWay);
							}*/
					   }
						
					}
					//保险产品
					else{
						setInputValueById("updatePdInternet_riskProductDescForm",resultObj.PDRiskIntroduceInfo);
						setInputValueById("updatePdInternet_RiskSeoProductDescForm",resultObj.PDRiskIntroduceInfo);
						setInputValueById("updatePdInternet_ProductSaleSDescForm",resultObj.pdRiskSalesInfo);
						
					    if (resultObj.pdRiskSalesInfo != null
								&& resultObj.pdRiskSalesInfo != undefined
								&& resultObj.pdRiskSalesInfo != "") {
			    	
					//处理支付方式
					if(resultObj.pdRiskSalesInfo.payWay!=null&&resultObj.pdRiskSalesInfo.payWay!=undefined&&resultObj.pdRiskSalesInfo.payWay!=""){
						var payWay=resultObj.pdRiskSalesInfo.payWay.split(",");
						$('#updatePdInternet_payWay').combobox('setValues', payWay);
					}
					//处理销售渠道
					if(resultObj.pdRiskSalesInfo.saleChnnel!=null&&resultObj.pdRiskSalesInfo.saleChnnel!=undefined&&resultObj.pdRiskSalesInfo.saleChnnel!=""){
						var saleChnnel=resultObj.pdRiskSalesInfo.saleChnnel.split(",");
						$('#updatePdInternet_saleChnnel').combobox('setValues', saleChnnel);
					}
				/*	//处理推介方式
					if(resultObj.pdRiskSalesInfo.promotionWay!=null&&resultObj.pdRiskSalesInfo.promotionWay!=undefined&&resultObj.pdRiskSalesInfo.promotionWay!=""){
						var promotionWay=resultObj.pdRiskSalesInfo.promotionWay.split(",");
						$('#updatePdInternet_promotionWay').combobox('setValues', promotionWay);
					}*/
			   }
						
						
						
						
					}
				}else{
					$.messager.alert('提示', result.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 保存互联网信息
 */
function  SaveUpdateProductInternetInfo(){
	 //支付方式
	var payWayValues=payWayCombobox.combobox("getValues");
	var payWayValue="";
	for(var i=0;i<payWayValues.length;i++ ){
	var value=payWayValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		payWayValue =value+","+payWayValue;
//		beneficialTypeValue =beneficialTypeValue+","+value;
		}
	}

/*	//推介方式
	var promotionWayValues=promotionWayCombobox.combobox("getValues");
	var promotionWayValue="";
	for(var i=0;i<promotionWayValues.length;i++ ){
	var value=promotionWayValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		promotionWayValue =value+","+promotionWayValue;
		}
	}*/
	//销售渠道
	var saleChnnelValues=saleChnnelCombobox.combobox("getValues");
	var saleChnnelValue="";
	for(var i=0;i<saleChnnelValues.length;i++ ){
	var value=saleChnnelValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		saleChnnelValue =value+","+saleChnnelValue;
		}
	}
	
/*	//推介方式
	var promotionWaysText= $("#updatePdInternet_promotionWay").combobox('getText')
	$("#updatePdInternet_promotionWay").combobox('setValue',promotionWayValue);
	$("#updatePdInternet_promotionWay").combobox('setText',promotionWaysText);*/
	
	//销售渠道
	var saleChnnelText= $("#updatePdInternet_saleChnnel").combobox('getText')
	$("#updatePdInternet_saleChnnel").combobox('setValue',saleChnnelValue);
	$("#updatePdInternet_saleChnnel").combobox('setText',saleChnnelText);
	
	//支付方式
	var payWayText= $("#updatePdInternet_payWay").combobox('getText')
	$("#updatePdInternet_payWay").combobox('setValue',payWayValue);
	$("#updatePdInternet_payWay").combobox('setText',payWayText);
	
	
	var param={};
	if (!$("#updatePdInternet_ProductSaleSDescForm").form("validate")) {
		$.messager.alert('提示',"请输入产品营销信息必录项！");
		return false;
	}
	param.modifyInternetProductId=modifyInternetProductId;
	param.modifyInternetProductType=modifyInternetProductType;
	param.modifyInternetProductSubType=modifyInternetProductSubType;
	if (modifyInternetProductType!=null&&modifyInternetProductSubType!=null&&modifyInternetProductId!=null) {

		  if (modifyInternetProductType == '1') {
			  // 固定类
			  if (modifyInternetProductSubType=='02') {
				  /***********************************************************************************************/
				  //产品特色
				    var productFeaturesExist=$("#updateGDPdInternet_productFeatures").val();
				    if (productFeaturesExist!=null&&productFeaturesExist!=undefined&&productFeaturesExist!="") {
			        if (productFeaturesExist.indexOf("%")>0){
			          $("#updateGDPdInternet_productFeatures").val(productFeaturesExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //产品费用
				    var productCostDescExist=$("#updateGDPdInternet_productCostDesc").val();
				    if (productCostDescExist!=null&&productCostDescExist!=undefined&&productCostDescExist!="") {
			        if (productCostDescExist.indexOf("%")>0){
			          $("#updateGDPdInternet_productCostDesc").val(productCostDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					} 
				  //投资方向
				    var fundSusingDescExist=$("#updateGDPdInternet_fundSusingDesc").val();
				    if (fundSusingDescExist!=null&&fundSusingDescExist!=undefined&&fundSusingDescExist!="") {
			        if (fundSusingDescExist.indexOf("%")>0){
			          $("#updateGDPdInternet_fundSusingDesc").val(fundSusingDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //收益说明
				    var historyEarnRateDescExist=$("#updateGDPdInternet_historyEarnRateDesc").val();
				    if (historyEarnRateDescExist!=null&&historyEarnRateDescExist!=undefined&&historyEarnRateDescExist!="") {
			        if (historyEarnRateDescExist.indexOf("%")>0){
			          $("#updateGDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //增信措施
				    var addPromotionMeasuresExist=$("#updateGDPdInternet_addPromotionMeasures").val();
				    if (addPromotionMeasuresExist!=null&&addPromotionMeasuresExist!=undefined&&addPromotionMeasuresExist!="") {
			        if (addPromotionMeasuresExist.indexOf("%")>0){
			          $("#updateGDPdInternet_addPromotionMeasures").val(addPromotionMeasuresExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //温馨提示
				    var warmWarnDescExist=$("#updateGDPdInternet_warmWarnDesc").val();
				    if (warmWarnDescExist!=null&&warmWarnDescExist!=undefined&&warmWarnDescExist!="") {
			        if (warmWarnDescExist.indexOf("%")>0){
			          $("#updateGDPdInternet_warmWarnDesc").val(warmWarnDescExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
					
			  	  //优惠信息
				    var preferentialDescExist=$("#updateGDPdInternet_preferentialDesc").val();
				    if (preferentialDescExist!=null&&preferentialDescExist!=undefined&&preferentialDescExist!="") {
			        if (preferentialDescExist.indexOf("%")>0){
			          $("#updateGDPdInternet_preferentialDesc").val(preferentialDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  
				  var pdIntenetGDWealthDesc =   formDataToJsonStr($("#updatePdInternet_GDwealthPdDescForm").serialize());
				  param.pdIntenetGDWealthDesc=pdIntenetGDWealthDesc;//固定产品说明信息
				  $("#updateGDPdInternet_productFeatures").val(productFeaturesExist);
				  $("#updateGDPdInternet_productCostDesc").val(productCostDescExist);
				  $("#updateGDPdInternet_fundSusingDesc").val(fundSusingDescExist);
				  $("#updateGDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist);
				  $("#updateGDPdInternet_addPromotionMeasures").val(addPromotionMeasuresExist);
				  $("#updateGDPdInternet_warmWarnDesc").val(warmWarnDescExist);
				  $("#updateGDPdInternet_preferentialDesc").val(preferentialDescExist);
				  /**********************************************************************************/
				  //此种方式获取值为空，所以只能单个值获取,不知到为何是不是加载问题
				  var pdIntenetGDSEOWealthDesc={};
				  pdIntenetGDSEOWealthDesc.productTitle= $("#updateGDPdInternet_productTitle").val();
				  pdIntenetGDSEOWealthDesc.productKeyword= $("#updateGDPdInternet_productKeyword").val();
				  pdIntenetGDSEOWealthDesc.productDesc= $("#updateGDPdInternet_productDesc").val();
				  param.pdIntenetGDSEOWealthDesc=pdIntenetGDSEOWealthDesc;//固定产品SEO说明信息
			  }
			  else{
				  /***********************************************************************************************/
				  //产品特色
				    var productFeaturesExist=$("#updateFDPdInternet_productFeatures").val();
				    if (productFeaturesExist!=null&&productFeaturesExist!=undefined&&productFeaturesExist!="") {
			        if (productFeaturesExist.indexOf("%")>0){
			          $("#updateFDPdInternet_productFeatures").val(productFeaturesExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //产品费用
				    var productCostDescExist=$("#updateFDPdInternet_productCostDesc").val();
				    if (productCostDescExist!=null&&productCostDescExist!=undefined&&productCostDescExist!="") {
			        if (productCostDescExist.indexOf("%")>0){
			          $("#updateFDPdInternet_productCostDesc").val(productCostDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					} 
				  //投资方向
				    var fundSusingDescExist=$("#updateFDPdInternet_fundSusingDesc").val();
				    if (fundSusingDescExist!=null&&fundSusingDescExist!=undefined&&fundSusingDescExist!="") {
			        if (fundSusingDescExist.indexOf("%")>0){
			          $("#updateFDPdInternet_fundSusingDesc").val(fundSusingDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //收益说明
				    var historyEarnRateDescExist=$("#updateFDPdInternet_historyEarnRateDesc").val();
				    if (historyEarnRateDescExist!=null&&historyEarnRateDescExist!=undefined&&historyEarnRateDescExist!="") {
			        if (historyEarnRateDescExist.indexOf("%")>0){
			          $("#updateFDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				    
				  //开放日
				    var openDayDescExist=$("#updateFDPdInternet_openDayDesc").val();
				    if (openDayDescExist!=null&&openDayDescExist!=undefined&&openDayDescExist!="") {
			        if (openDayDescExist.indexOf("%")>0){
			          $("#updateFDPdInternet_openDayDesc").val(openDayDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //管理机构
				    var agncyComNameExist=$("#updateFDPdInternet_agncyComName").val();
				    if (agncyComNameExist!=null&&agncyComNameExist!=undefined&&agncyComNameExist!="") {
			        if (agncyComNameExist.indexOf("%")>0){
			          $("#updateFDPdInternet_agncyComName").val(agncyComNameExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
				   //投顾介绍
				    var agentNameExist=$("#updateFDPdInternet_agentName").val();
				    if (agentNameExist!=null&&agentNameExist!=undefined&&agentNameExist!="") {
			        if (agentNameExist.indexOf("%")>0){
			          $("#updateFDPdInternet_agentName").val(agentNameExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
					//封闭期说明
				    var closedPeriodDescExist=$("#updateFDPdInternet_closedPeriodDesc").val();
				    if (closedPeriodDescExist!=null&&closedPeriodDescExist!=undefined&&closedPeriodDescExist!="") {
			        if (closedPeriodDescExist.indexOf("%")>0){
			          $("#updateFDPdInternet_closedPeriodDesc").val(closedPeriodDescExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
				    
			  	  //温馨提示
				    var warmWarnDescExist=$("#updateFDPdInternet_warmWarnDesc").val();
				    if (warmWarnDescExist!=null&&warmWarnDescExist!=undefined&&warmWarnDescExist!="") {
			        if (warmWarnDescExist.indexOf("%")>0){
			          $("#updateFDPdInternet_warmWarnDesc").val(warmWarnDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
					
			  	  //优惠信息
				    var preferentialDescExist=$("#updateFDPdInternet_preferentialDesc").val();
				    if (preferentialDescExist!=null&&preferentialDescExist!=undefined&&preferentialDescExist!="") {
			        if (preferentialDescExist.indexOf("%")>0){
			          $("#updateFDPdInternet_preferentialDesc").val(preferentialDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
		 
				  var pdIntenetFDWealthDesc = formDataToJsonStr($("#updatePdInternet_FDwealthPdDescForm").serialize());
				  param.pdIntenetFDWealthDesc=pdIntenetFDWealthDesc;//浮动股权产品说明信息
				  
				  $("#updateFDPdInternet_productFeatures").val(productFeaturesExist);
				  $("#updateFDPdInternet_productCostDesc").val(productCostDescExist);
				  $("#updateFDPdInternet_fundSusingDesc").val(fundSusingDescExist);
				  $("#updateFDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist);
				  $("#updateFDPdInternet_openDayDesc").val(openDayDescExist);
				  $("#updateFDPdInternet_agncyComName").val(agncyComNameExist);
				  $("#updateFDPdInternet_agentName").val(agentNameExist);
				  $("#updateFDPdInternet_closedPeriodDesc").val(closedPeriodDescExist);
				  $("#updateFDPdInternet_warmWarnDesc").val(warmWarnDescExist);
				  $("#updateFDPdInternet_preferentialDesc").val(preferentialDescExist);
				 
				  
				  var pdIntenetFDSEOWealthDesc={};
				  pdIntenetFDSEOWealthDesc.productTitle= $("#updateFDPdInternet_productTitle").val();
				  pdIntenetFDSEOWealthDesc.productKeyword= $("#updateFDPdInternet_productKeyword").val();
				  pdIntenetFDSEOWealthDesc.productDesc= $("#updateFDPdInternet_productDesc").val();
				  param.pdIntenetFDSEOWealthDesc=pdIntenetFDSEOWealthDesc;//浮动产品SEO信息
			  }
		} else {
			   /***********************************************************************************/
			    //产品特色
			    var productFeaturesExist=$("#updateRiskPdInternet_productFeatures").val();
			    if (productFeaturesExist!=null&&productFeaturesExist!=undefined&&productFeaturesExist!="") {
		        if (productFeaturesExist.indexOf("%")>0){
		          $("#updateRiskPdInternet_productFeatures").val(productFeaturesExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    //投保须知
			    var insuranceInformationExist=$("#updateRiskPdInternet_insuranceInformation").val();
			    if (insuranceInformationExist!=null&&insuranceInformationExist!=undefined&&insuranceInformationExist!="") {
		        if (insuranceInformationExist.indexOf("%")>0){
		          $("#updateRiskPdInternet_insuranceInformation").val(insuranceInformationExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    //保险期间说明
			    var insuredPeriodDescExist=$("#updateRiskPdInternet_insuredPeriodDesc").val();
			    if (insuredPeriodDescExist!=null&&insuredPeriodDescExist!=undefined&&insuredPeriodDescExist!="") {
		        if (insuredPeriodDescExist.indexOf("%")>0){
		          $("#updateRiskPdInternet_insuredPeriodDesc").val(insuredPeriodDescExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    //理赔说明
			    var claimsIntrouctionExist=$("#updateRiskPdInternet_claimsIntrouction").val();
			    if (claimsIntrouctionExist!=null&&claimsIntrouctionExist!=undefined&&claimsIntrouctionExist!="") {
		        if (claimsIntrouctionExist.indexOf("%")>0){
		          $("#updateRiskPdInternet_claimsIntrouction").val(claimsIntrouctionExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    //温馨提示
			    var warmWarnDescExist=$("#updateRiskPdInternet_warmWarnDesc").val();
			    if (warmWarnDescExist!=null&&warmWarnDescExist!=undefined&&warmWarnDescExist!="") {
		        if (warmWarnDescExist.indexOf("%")>0){
		          $("#updateRiskPdInternet_warmWarnDesc").val(warmWarnDescExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    //优惠信息
			    var preferentialDescExist=$("#updateRiskPdInternet_preferentialDesc").val();
			    if (preferentialDescExist!=null&&preferentialDescExist!=undefined&&preferentialDescExist!="") {
		        if (preferentialDescExist.indexOf("%")>0){
		          $("#updateRiskPdInternet_preferentialDesc").val(preferentialDescExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    
			    
			    var pdIntenetFDRiskDesc = formDataToJsonStr($("#updatePdInternet_riskProductDescForm").serialize());
			 	param.pdIntenetFDRiskDesc=pdIntenetFDRiskDesc;//保险产品说明信息
			 	//序列化完之后，在变回来
			 	 $("#updateRiskPdInternet_productFeatures").val(productFeaturesExist);
			 	 $("#updateRiskPdInternet_insuranceInformation").val(insuranceInformationExist);
			 	 $("#updateRiskPdInternet_insuredPeriodDesc").val(insuredPeriodDescExist);
			 	 $("#updateRiskPdInternet_claimsIntrouction").val(claimsIntrouctionExist);
			 	 $("#updateRiskPdInternet_warmWarnDesc").val(warmWarnDescExist);
			 	 $("#updateRiskPdInternet_preferentialDesc").val(warmWarnDescExist);
			 	
			 	/***********************************************************************************/
			 	//保险产品SEO信息
//				var pdIntenetFDSEORiskDesc={};
				//产品标题
			    var productTitleExist=$("#updateRiskPdInternet_productTitle").val();
			    if (productTitleExist!=null&&productTitleExist!=undefined&&productTitleExist!="") {
		        if (productTitleExist.indexOf("%")>0){
		        $("#updateRiskPdInternet_productTitle").val(productTitleExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    //产品关键字
			    var productKeywordExist=$("#updateRiskPdInternet_productKeyword").val();
			    if (productKeywordExist!=null&&productKeywordExist!=undefined&&productKeywordExist!="") {
		        if (productKeywordExist.indexOf("%")>0){
		        $("#updateRiskPdInternet_productKeyword").val(productKeywordExist.replace(new RegExp("%","gm"),"%25"));
				}
				} 
			    //产品描述
			    var productDescExist=$("#updateRiskPdInternet_productDesc").val();
			    if (productDescExist!=null&&productDescExist!=undefined&&productDescExist!="") {
		        if (productDescExist.indexOf("%")>0){
		        $("#updateRiskPdInternet_productDesc").val(productDescExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
				
				var pdIntenetFDSEORiskDesc=formDataToJsonStr($("#updatePdInternet_RiskSeoProductDescForm").serialize());
				
			/* 	pdIntenetFDSEORiskDesc.productTitle= $("#updateRiskPdInternet_productTitle").val();
			 	pdIntenetFDSEORiskDesc.productKeyword= $("#updateRiskPdInternet_productKeyword").val();
			 	pdIntenetFDSEORiskDesc.productDescribe= $("#updateRiskPdInternet_productDesc").val();
			 	*/	
			 	param.pdIntenetFDSEORiskDesc=pdIntenetFDSEORiskDesc;//保险产品SEO信息
			 	$("#updateRiskPdInternet_productTitle").val(productTitleExist);
			 	$("#updateRiskPdInternet_productKeyword").val(productKeywordExist);
			 	$("#updateRiskPdInternet_productDesc").val(productDescExist);
			 	
		}
		  
		 	var ProductSaleSDesc = formDataToJsonStr($("#updatePdInternet_ProductSaleSDescForm").serialize());
		 	param.ProductSaleSDesc=ProductSaleSDesc;//产品营销信息
	}
	else{
		$.messager.alert('提示', "获取产品类型相关信息失败,无法提交保存！", 'info');
		return;
	}
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/product/saveUpdateProductInternetInfoUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', "保存成功",'info');
					return;
				} else {
					$.messager.alert('提示', resultInfo.msg);
					return;
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}






