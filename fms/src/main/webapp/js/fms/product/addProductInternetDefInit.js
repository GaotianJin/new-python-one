var productTypeValue = null;
var productSubTypeValue = null;
var productId = null;
var productInternetInfoId=null;//互联网产品信息流水号
var internetInfoSucFlag=null;//是否重复提交的标志
var internetProductSubTypeCombobox=null;
var internetProductTypeCombobox=null;
var  payWayCombobox=null;
var  promotionWayCombobox=null;
var saleChnnelCombobox=null;

/**
 * 页面初始化加载
 */
jQuery(function($) {
	productTypeValue=$("#addGDPdInternetInfo_ProductType").val();
	productSubTypeValue=$("#addGDPdInternetInfo_ProductSubType").val();
	productId=$("#addGDPdInternetInfo_ProductId").val();
	initAllCombobox();// 加载所有的下拉框
});

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	
	// 推介方式
	promotionWayCombobox=$("#addPdInternet_promotionWay").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=promotionWay',
		valueField : 'code',
		textField : 'codeName',
//		multiple : true,
		required:true
/*		,
		editable:false*/
	});
	// 销售方式
  $("#addPdInternet_saleWay").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=saleWay',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		editable:false
	});
	// 销售渠道
  saleChnnelCombobox=$("#addPdInternet_saleChnnel").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=saleChnnel',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		multiple : true
/*		,
		editable:false*/
	});
	// 支付方式
  payWayCombobox=$("#addPdInternet_payWay").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=payWay',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		multiple : true
/*		,
		editable:false*/
	});
	// 是否显示
	$("#addPdInternet_isShow").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
/*		,
		editable:false*/
	});
	// 网销产品类型
	 internetProductTypeCombobox= $("#addPdInternet_internetProductType").combobox({
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
					$('#addPdInternet_internetProductSubType').combobox('setValues','');  
					$("#addPdInternet_internetProductSubType").combobox("disable");
					}
				}
	});
	
	// 网销产品子类型
	internetProductSubTypeCombobox= $("#addPdInternet_internetProductSubType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=internetProductSubType',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		editable:false
	});
	
	//是否热销
	$("#addPdInternet_isHotSale").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
	});
	
	//是否专享
	$("#addPdInternet_isExclusive").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
	});
	// 首页显示
	$("#addPdInternet_isShowFirst").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=defaultFlag',
		valueField : 'code',
		textField : 'codeName',
		required:true
	});
	
	/***写了个方法为了拖延时间方法,此种解决方式有点低俗，待更改**/
	if(productTypeValue == "1"){
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

function showAndHidePanel(){
	// 根据产品类型和产品子类加载产品信息页面
	if (productTypeValue == 1){
		if (productSubTypeValue == 01 || productSubTypeValue == 03) {
			$("#addPdInternet_GDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏固定收益产品说明信息
			$("#addPdInternet_FDwealthPdDescFormDoiv").parent(".panel").css("display", "");// 展示浮动产品说明信息
			$("#addPdInternet_riskPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险产品说明信息
			$("#addPdInternetPdSaleSDescDiv").parent(".panel").css("display", "");// 展示产品营销说明信息
			
			$("#addPdInternet_FDSEOwealthPdDescFormDoiv").parent(".panel").css("display", "");// 展示浮动SEO区
			$("#addPdInternet_GDSEOwealthPdDescSEOFormDoiv").parent(".panel").css("display", "none");// 隐藏固定SEO区
			$("#addPdInternet_riskSEOPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险SEO区
			
			
		}else{
			$("#addPdInternet_GDwealthPdDescFormDoiv").parent(".panel").css("display", "");// 显示固定收益产品说明信息
			$("#addPdInternet_FDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动产品说明信息
			$("#addPdInternet_riskPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险产品说明信息
			$("#addPdInternetPdSaleSDescDiv").parent(".panel").css("display", "");// 展示产品营销说明信息
			
			$("#addPdInternet_FDSEOwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动SEO区
			$("#addPdInternet_GDSEOwealthPdDescSEOFormDoiv").parent(".panel").css("display", "");// 展示固定SEO区
			$("#addPdInternet_riskSEOPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏保险SEO区
		}
	}else{
		$("#addPdInternet_GDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏固定收益产品说明信息
		$("#addPdInternet_FDwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动产品说明信息
		$("#addPdInternet_riskPdDescFormDoiv").parent(".panel").css("display", "");// 隐藏保险产品说明信息
		$("#addPdInternetPdSaleSDescDiv").parent(".panel").css("display", "");// 展示产品营销说明信息
		
		$("#addPdInternet_FDSEOwealthPdDescFormDoiv").parent(".panel").css("display", "none");// 隐藏浮动SEO区
		$("#addPdInternet_GDSEOwealthPdDescSEOFormDoiv").parent(".panel").css("display", "none");// 隐藏固定SEO区
		$("#addPdInternet_riskSEOPdDescFormDoiv").parent(".panel").css("display", "");// 展示保险SEO区
	}
}



/**
 * 保存互联网信息
 */
function  submitProductInternetInfo(){
	
/*	var str=$("#addGDPdInternet_productFeatures").text();
	$("#addGDPdInternet_productFeatures").val(str);*/
	
   //支付方式
	var payWayValues=payWayCombobox.combobox("getValues");
	var payWayValue="";
	for(var i=0;i<payWayValues.length;i++ ){
	var value=payWayValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		payWayValue =value+","+payWayValue;
		}
	}
	//销售渠道
	var saleChnnelValues=saleChnnelCombobox.combobox("getValues");
	var saleChnnelValue="";
	for(var i=0;i<saleChnnelValues.length;i++ ){
	var value=saleChnnelValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		saleChnnelValue =value+","+saleChnnelValue;
		}
	}
	
 
	
	//销售渠道
	var saleChnnelText= $("#addPdInternet_saleChnnel").combobox('getText')
	$("#addPdInternet_saleChnnel").combobox('setValue',saleChnnelValue);
	$("#addPdInternet_saleChnnel").combobox('setText',saleChnnelText);
	
	//支付方式
	var payWayText= $("#addPdInternet_payWay").combobox('getText')
	$("#addPdInternet_payWay").combobox('setValue',payWayValue);
	$("#addPdInternet_payWay").combobox('setText',payWayText);
	
	if (!$("#addPdInternet_ProductSaleSDescForm").form("validate")) {
		$.messager.alert('提示',"请输入产品营销信息必录项！");
		return false;
	}
	var param={};
	param.productId=productId;
	param.productType=productTypeValue;
	param.productSubType=productSubTypeValue;
	param.internetInfoSucFlag=internetInfoSucFlag;
	
	if (productTypeValue!=null&&productSubTypeValue!=null&&productId!=null) {
		    
		 	var ProductSaleSDesc = formDataToJsonStr($("#addPdInternet_ProductSaleSDescForm").serialize());
		 	param.ProductSaleSDesc=ProductSaleSDesc;//产品营销信息
		  if (productTypeValue == '1') {
			  // 固定类
			  if (productSubTypeValue=='02') {
				  /***********************************************************************************************/
				  //产品特色
				    var productFeaturesExist=$("#addGDPdInternet_productFeatures").val();
//				    alert(productFeaturesExist);
				    if (productFeaturesExist!=null&&productFeaturesExist!=undefined&&productFeaturesExist!="") {
				    	
			        if (productFeaturesExist.indexOf("%")>0){
			          $("#addGDPdInternet_productFeatures").val(productFeaturesExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //产品费用
				    var productCostDescExist=$("#addGDPdInternet_productCostDesc").val();
				    if (productCostDescExist!=null&&productCostDescExist!=undefined&&productCostDescExist!="") {
			        if (productCostDescExist.indexOf("%")>0){
			          $("#addGDPdInternet_productCostDesc").val(productCostDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					} 
				  //投资方向
				    var fundSusingDescExist=$("#addGDPdInternet_fundSusingDesc").val();
				    if (fundSusingDescExist!=null&&fundSusingDescExist!=undefined&&fundSusingDescExist!="") {
			        if (fundSusingDescExist.indexOf("%")>0){
			          $("#addGDPdInternet_fundSusingDesc").val(fundSusingDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //收益说明
				    var historyEarnRateDescExist=$("#addGDPdInternet_historyEarnRateDesc").val();
				    if (historyEarnRateDescExist!=null&&historyEarnRateDescExist!=undefined&&historyEarnRateDescExist!="") {
			        if (historyEarnRateDescExist.indexOf("%")>0){
			          $("#addGDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //增信措施
				    var addPromotionMeasuresExist=$("#addGDPdInternet_addPromotionMeasures").val();
				    if (addPromotionMeasuresExist!=null&&addPromotionMeasuresExist!=undefined&&addPromotionMeasuresExist!="") {
			        if (addPromotionMeasuresExist.indexOf("%")>0){
			          $("#addGDPdInternet_addPromotionMeasures").val(addPromotionMeasuresExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //温馨提示
				    var warmWarnDescExist=$("#addGDPdInternet_warmWarnDesc").val();
				    if (warmWarnDescExist!=null&&warmWarnDescExist!=undefined&&warmWarnDescExist!="") {
			        if (warmWarnDescExist.indexOf("%")>0){
			          $("#addGDPdInternet_warmWarnDesc").val(warmWarnDescExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
					
			  	  //优惠信息
				    var preferentialDescExist=$("#addGDPdInternet_preferentialDesc").val();
				    if (preferentialDescExist!=null&&preferentialDescExist!=undefined&&preferentialDescExist!="") {
			        if (preferentialDescExist.indexOf("%")>0){
			          $("#addGDPdInternet_preferentialDesc").val(preferentialDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  
				  var pdIntenetGDWealthDesc = formDataToJsonStr($("#addPdInternet_GDwealthPdDescForm").serialize());
				  param.pdIntenetGDWealthDesc=pdIntenetGDWealthDesc;//固定产品说明信息
//				  alert(productFeaturesExist);
				  $("#addGDPdInternet_productFeatures").val(productFeaturesExist);
				  $("#addGDPdInternet_productCostDesc").val(productCostDescExist);
				  $("#addGDPdInternet_fundSusingDesc").val(fundSusingDescExist);
				  $("#addGDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist);
				  $("#addGDPdInternet_addPromotionMeasures").val(addPromotionMeasuresExist);
				  $("#addGDPdInternet_warmWarnDesc").val(warmWarnDescExist);
				  $("#addGDPdInternet_preferentialDesc").val(preferentialDescExist);
				  /****************************************************************************/
				//产品标题
				    var productTitleExist=$("#addGDPdInternet_productTitle").val();
				    if (productTitleExist!=null&&productTitleExist!=undefined&&productTitleExist!="") {
			        if (productTitleExist.indexOf("%")>0){
			        $("#addGDPdInternet_productTitle").val(productTitleExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				    //产品关键字
				    var productKeywordExist=$("#addGDPdInternet_productKeyword").val();
				    if (productKeywordExist!=null&&productKeywordExist!=undefined&&productKeywordExist!="") {
			        if (productKeywordExist.indexOf("%")>0){
			        $("#addGDPdInternet_productKeyword").val(productKeywordExist.replace(new RegExp("%","gm"),"%25"));
					}
					} 
				    //产品描述
				    var productDescExist=$("#addGDPdInternet_productDesc").val();
				    if (productDescExist!=null&&productDescExist!=undefined&&productDescExist!="") {
			        if (productDescExist.indexOf("%")>0){
			        $("#addGDPdInternet_productDesc").val(productDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  
				  var pdIntenetGDSEOWealthDesc = formDataToJsonStr($("#addPdInternet_GDSEOwealthPdDescForm").serialize());
				  param.pdIntenetGDSEOWealthDesc=pdIntenetGDSEOWealthDesc;//固定产品SEO说明信息
				  
				 	$("#addGDPdInternet_productTitle").val(productTitleExist);
				 	$("#addGDPdInternet_productKeyword").val(productKeywordExist);
				 	$("#addGDPdInternet_productDesc").val(productDescExist);
			  }
			  else{
				  //产品特色
				    var productFeaturesExist=$("#addFDPdInternet_productFeatures").val();
				    if (productFeaturesExist!=null&&productFeaturesExist!=undefined&&productFeaturesExist!="") {
			        if (productFeaturesExist.indexOf("%")>0){
			          $("#addFDPdInternet_productFeatures").val(productFeaturesExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //产品费用
				    var productCostDescExist=$("#addFDPdInternet_productCostDesc").val();
				    if (productCostDescExist!=null&&productCostDescExist!=undefined&&productCostDescExist!="") {
			        if (productCostDescExist.indexOf("%")>0){
			          $("#addFDPdInternet_productCostDesc").val(productCostDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					} 
				  //投资方向
				    var fundSusingDescExist=$("#addFDPdInternet_fundSusingDesc").val();
				    if (fundSusingDescExist!=null&&fundSusingDescExist!=undefined&&fundSusingDescExist!="") {
			        if (fundSusingDescExist.indexOf("%")>0){
			          $("#addFDPdInternet_fundSusingDesc").val(fundSusingDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //收益说明
				    var historyEarnRateDescExist=$("#addFDPdInternet_historyEarnRateDesc").val();
				    if (historyEarnRateDescExist!=null&&historyEarnRateDescExist!=undefined&&historyEarnRateDescExist!="") {
			        if (historyEarnRateDescExist.indexOf("%")>0){
			          $("#addFDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				    
				  //开放日
				    var openDayDescExist=$("#addFDPdInternet_openDayDesc").val();
				    if (openDayDescExist!=null&&openDayDescExist!=undefined&&openDayDescExist!="") {
			        if (openDayDescExist.indexOf("%")>0){
			          $("#addFDPdInternet_openDayDesc").val(openDayDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  //管理机构
				    var agncyComNameExist=$("#addFDPdInternet_agncyComName").val();
				    if (agncyComNameExist!=null&&agncyComNameExist!=undefined&&agncyComNameExist!="") {
			        if (agncyComNameExist.indexOf("%")>0){
			          $("#addFDPdInternet_agncyComName").val(agncyComNameExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
				   //投顾介绍
				    var agentNameExist=$("#addFDPdInternet_agentName").val();
				    if (agentNameExist!=null&&agentNameExist!=undefined&&agentNameExist!="") {
			        if (agentNameExist.indexOf("%")>0){
			          $("#addFDPdInternet_agentName").val(agentNameExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
					//封闭期说明
				    var closedPeriodDescExist=$("#addFDPdInternet_closedPeriodDesc").val();
				    if (closedPeriodDescExist!=null&&closedPeriodDescExist!=undefined&&closedPeriodDescExist!="") {
			        if (closedPeriodDescExist.indexOf("%")>0){
			          $("#addFDPdInternet_closedPeriodDesc").val(closedPeriodDescExist.replace(new RegExp("%","gm"),"%25"));
					}
				    }
				    
			  	  //温馨提示
				    var warmWarnDescExist=$("#addFDPdInternet_warmWarnDesc").val();
				    if (warmWarnDescExist!=null&&warmWarnDescExist!=undefined&&warmWarnDescExist!="") {
			        if (warmWarnDescExist.indexOf("%")>0){
			          $("#addFDPdInternet_warmWarnDesc").val(warmWarnDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
					
			  	  //优惠信息
				    var preferentialDescExist=$("#addFDPdInternet_preferentialDesc").val();
//				    alert(preferentialDescExist);
				    if (preferentialDescExist!=null&&preferentialDescExist!=undefined&&preferentialDescExist!="") {
			        if (preferentialDescExist.indexOf("%")>0){
			          $("#addFDPdInternet_preferentialDesc").val(preferentialDescExist.replace(new RegExp("%","gm"),"%25"));
//			          alert(preferentialDescExist.replace(new RegExp("%","gm"),"%25"));
					}
					}
				  
				  var pdIntenetFDWealthDesc = formDataToJsonStr($("#addPdInternet_FDwealthPdDescForm").serialize());
				  param.pdIntenetFDWealthDesc=pdIntenetFDWealthDesc;//浮动股权产品说明信息
				 
				  $("#addFDPdInternet_productFeatures").val(productFeaturesExist);
				  $("#addFDPdInternet_productCostDesc").val(productCostDescExist);
				  $("#addFDPdInternet_fundSusingDesc").val(fundSusingDescExist);
				  $("#addFDPdInternet_historyEarnRateDesc").val(historyEarnRateDescExist);
				  $("#addFDPdInternet_openDayDesc").val(openDayDescExist);
				  $("#addFDPdInternet_agncyComName").val(agncyComNameExist);
				  $("#addFDPdInternet_agentName").val(agentNameExist);
				  $("#addFDPdInternet_closedPeriodDesc").val(closedPeriodDescExist);
				  $("#addFDPdInternet_warmWarnDesc").val(warmWarnDescExist);
				  $("#addFDPdInternet_preferentialDesc").val(preferentialDescExist);
				  
				  /****************************************************************************/
					//产品标题
					    var productTitleExist=$("#addFDPdInternet_productTitle").val();
					    if (productTitleExist!=null&&productTitleExist!=undefined&&productTitleExist!="") {
				        if (productTitleExist.indexOf("%")>0){
				        $("#addFDPdInternet_productTitle").val(productTitleExist.replace(new RegExp("%","gm"),"%25"));
						}
						}
					    //产品关键字
					    var productKeywordExist=$("#addFDPdInternet_productKeyword").val();
					    if (productKeywordExist!=null&&productKeywordExist!=undefined&&productKeywordExist!="") {
				        if (productKeywordExist.indexOf("%")>0){
				        $("#addFDPdInternet_productKeyword").val(productKeywordExist.replace(new RegExp("%","gm"),"%25"));
						}
						} 
					    //产品描述
					    var productDescExist=$("#addFDPdInternet_productDesc").val();
					    if (productDescExist!=null&&productDescExist!=undefined&&productDescExist!="") {
				        if (productDescExist.indexOf("%")>0){
				        $("#addFDPdInternet_productDesc").val(productDescExist.replace(new RegExp("%","gm"),"%25"));
						}
						} 
				  
				  var pdIntenetFDSEOWealthDesc = formDataToJsonStr($("#addPdInternet_FDSEOwealthPdDescForm").serialize());
				  param.pdIntenetFDSEOWealthDesc=pdIntenetFDSEOWealthDesc;//浮动产品SEO信息
				  
				 	$("#addFDPdInternet_productTitle").val(productTitleExist);
				 	$("#addFDPdInternet_productKeyword").val(productKeywordExist);
				 	$("#addFDPdInternet_productDesc").val(productDescExist);
				
			  }
		} else {
			
			/***********************************************************************************/
		    //产品特色
		    var productFeaturesExist=$("#addRiskPdInternet_productFeatures").val();
		    if (productFeaturesExist!=null&&productFeaturesExist!=undefined&&productFeaturesExist!="") {
	        if (productFeaturesExist.indexOf("%")>0){
	          $("#addRiskPdInternet_productFeatures").val(productFeaturesExist.replace(new RegExp("%","gm"),"%25"));
			}
			}
		    //投保须知
		    var insuranceInformationExist=$("#addRiskPdInternet_insuranceInformation").val();
		    if (insuranceInformationExist!=null&&insuranceInformationExist!=undefined&&insuranceInformationExist!="") {
	        if (insuranceInformationExist.indexOf("%")>0){
	          $("#addRiskPdInternet_insuranceInformation").val(insuranceInformationExist.replace(new RegExp("%","gm"),"%25"));
			}
			}
		    //保险期间说明
		    var insuredPeriodDescExist=$("#addRiskPdInternet_insuredPeriodDesc").val();
		    if (insuredPeriodDescExist!=null&&insuredPeriodDescExist!=undefined&&insuredPeriodDescExist!="") {
	        if (insuredPeriodDescExist.indexOf("%")>0){
	          $("#addRiskPdInternet_insuredPeriodDesc").val(insuredPeriodDescExist.replace(new RegExp("%","gm"),"%25"));
			}
			}
		    //理赔说明
		    var claimsIntrouctionExist=$("#addRiskPdInternet_claimsIntrouction").val();
		    if (claimsIntrouctionExist!=null&&claimsIntrouctionExist!=undefined&&claimsIntrouctionExist!="") {
	        if (claimsIntrouctionExist.indexOf("%")>0){
	          $("#addRiskPdInternet_claimsIntrouction").val(claimsIntrouctionExist.replace(new RegExp("%","gm"),"%25"));
			}
			}
		    //温馨提示
		    var warmWarnDescExist=$("#addRiskPdInternet_warmWarnDesc").val();
		    if (warmWarnDescExist!=null&&warmWarnDescExist!=undefined&&warmWarnDescExist!="") {
	        if (warmWarnDescExist.indexOf("%")>0){
	          $("#addRiskPdInternet_warmWarnDesc").val(warmWarnDescExist.replace(new RegExp("%","gm"),"%25"));
			}
			}
		    //优惠信息
		    var preferentialDescExist=$("#addRiskPdInternet_preferentialDesc").val();
		    if (preferentialDescExist!=null&&preferentialDescExist!=undefined&&preferentialDescExist!="") {
	        if (preferentialDescExist.indexOf("%")>0){
	          $("#addRiskPdInternet_preferentialDesc").val(preferentialDescExist.replace(new RegExp("%","gm"),"%25"));
			}
			}
			    
			var pdIntenetFDRiskDesc = formDataToJsonStr($("#addPdInternet_riskProductDescForm").serialize());
			param.pdIntenetFDRiskDesc=pdIntenetFDRiskDesc;//保险产品说明信息
			//序列化完之后，在变回来
		 	 $("#addRiskPdInternet_productFeatures").val(productFeaturesExist);
		 	 $("#addRiskPdInternet_insuranceInformation").val(insuranceInformationExist);
		 	 $("#addRiskPdInternet_insuredPeriodDesc").val(insuredPeriodDescExist);
		 	 $("#addRiskPdInternet_claimsIntrouction").val(claimsIntrouctionExist);
		 	 $("#addRiskPdInternet_warmWarnDesc").val(warmWarnDescExist);
		 	 $("#addRiskPdInternet_preferentialDesc").val(warmWarnDescExist);
		 	/***********************************************************************************/
			 	//保险产品SEO信息
//				var pdIntenetFDSEORiskDesc={};
				//产品标题
			    var productTitleExist=$("#addRiskPdInternet_productTitle").val();
			    if (productTitleExist!=null&&productTitleExist!=undefined&&productTitleExist!="") {
		        if (productTitleExist.indexOf("%")>0){
		        $("#addRiskPdInternet_productTitle").val(productTitleExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			    //产品关键字
			    var productKeywordExist=$("#addRiskPdInternet_productKeyword").val();
			    if (productKeywordExist!=null&&productKeywordExist!=undefined&&productKeywordExist!="") {
		        if (productKeywordExist.indexOf("%")>0){
		        $("#addRiskPdInternet_productKeyword").val(productKeywordExist.replace(new RegExp("%","gm"),"%25"));
				}
				} 
			    //产品描述
			    var productDescExist=$("#addRiskPdInternet_productDesc").val();
			    if (productDescExist!=null&&productDescExist!=undefined&&productDescExist!="") {
		        if (productDescExist.indexOf("%")>0){
		        $("#addRiskPdInternet_productDesc").val(productDescExist.replace(new RegExp("%","gm"),"%25"));
				}
				}
			 
			 	//保险产品SEO信息
			    var pdIntenetFDSEORiskDesc = formDataToJsonStr($("#addPdInternet_riskSEOProductDescForm").serialize());
			    param.pdIntenetFDSEORiskDesc=pdIntenetFDSEORiskDesc;//保险产品SEO信息
			    
				$("#addRiskPdInternet_productTitle").val(productTitleExist);
			 	$("#addRiskPdInternet_productKeyword").val(productKeywordExist);
			 	$("#addRiskPdInternet_productDesc").val(productDescExist);
		}
	}
	else{
		$.messager.alert('提示', "获取产品类型相关信息失败,无法提交保存！", 'info');
		return;
	}
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/product/submitProductInternetInfoUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					internetInfoSucFlag=resultInfo.obj;
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


