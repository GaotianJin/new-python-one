/**
 * 页面初始化加载
 */
var comId;
var productId;
var expectOpenDay;
var operate;
var pdAmountOrderInfoId;
var isInviteCode;
var productType;
var productSubType;
var isDistribute;
var remainAmount;
var tradeInfoId;

jQuery(function($) {
	//全局变量赋值
	comId = $("#addPdAmountOrder_hdcomId").val();
	productId = $("#addPdAmountOrder_hdproductId").val();
	expectOpenDay = $("#addPdAmountOrder_hdexpectOpenDay").val();
	operate = $("#addPdAmountOrder_hdoperate").val();
	pdAmountOrderInfoId = $("#addPdAmountOrder_hdpdAmountOrderInfoId").val();
	productType = $("#addPdAmountOrder_hdproductType").val();
	productSubType = $("#addPdAmountOrder_hdproductSubType").val();
	isDistribute = $("#addPdAmountOrder_hdisDistribute").val();
	remainAmount = $("#addPdAmountOrder_hdremainAmount").val();
	tradeInfoId = $("#addPdAmountOrder_hdTradeInfoId").val();
	$("#addPdAmountOrder_productType").val(productType);
	$("#addPdAmountOrder_productSubType").val(productSubType);
	//固收类期望开放日不要
	if(productType=="1"&&productSubType=="02"){
		$('#addPdAmountOrder_expectOpenDay').attr('disabled',"true");
	}else if(productType=="1"&&(productSubType=="01"||productSubType=="03")){
		//$("#addPdAmountOrder_expectOpenDay").val(expectOpenDay);
	}
	
	
	isInviteCode = $("#addPdAmountOrder_hdisInviteCode").val();
	//不需要邀请码
	/*if(isInviteCode=="02"){
		//$('#addPdAmountOrder_inviteCode').validatebox('disabled'); 
		$('#addPdAmountOrder_inviteCode').attr('disabled',"true");
		$('#addPdCustOrder_getInviteCodeButton').attr('disabled',"true");
	}else{
		getInviteCode();
	}*/
	//初始文本必录入项
	initAllValidateBox();
	//初始化获取产品、理财顾问、机构等相关信息
	getProductAndComInfo();
	//禁止输入信息
	setInputEnable();
	if(operate=="addCustOrder"){
		$("#updatePdCustOrder_submitButton").hide();//更新保存按钮
		/*$("#addPdAmountOrder_isOldCustomer").combobox({required:true});*/
	}
	//查询客户明细
	if(operate=="detailCustOrder"){
		$("#addPdCustOrder_submitButton").hide();//新增保存按钮
		$("#updatePdCustOrder_submitButton").hide();//更新保存按钮
		getCustProductAmountOrderInfo();
	}
	
	//更新
	if(operate=="updateCustOrder"){
		$("#addPdCustOrder_submitButton").hide();//新增保存按钮
		//查询赋值
		getCustProductAmountOrderInfo();
		//置灰按钮，只能修改额度
		$('#addPdAmountOrder_comId').combobox({disabled:true});
		$("#addPdAmountOrder_agentId").combobox({disabled:true}); 
		$('#addPdAmountOrder_mobile').attr('disabled',"true");
		$("#addPdAmountOrder_agenyComId").combobox({disabled:true});
		$('#addPdAmountOrder_productId').combobox({disabled:true});
		$("#addPdAmountOrder_custName").combobox({disabled:true});
		$("#addPdAmountOrder_planTransferDate").datebox({disabled:true});
		$("#addPdAmountOrder_idNo").attr('disabled',"true");
		$("#addPdAmountOrder_custMobile").attr('disabled',"true");
		$("#addPdAmountOrder_sealingAccDate").attr('disabled',"true");
		$("#addPdAmountOrder_custEmail").attr('disabled',"true");
		$("#addPdAmountOrder_foundDate").attr('disabled',"true");
		$("#addPdAmountOrder_productCode").attr('disabled',"true");
		/*$("#addPdAmountOrder_isOldCustomer").combobox({disabled:true});*/
	}
});



/**
 * 
 * 初始文本格式的必录项
 */
function initAllValidateBox(){
	//$('#addPdAmountOrder_custName').validatebox({required:true});	
	$('#addPdAmountOrder_orderAmount').validatebox({
		required:true
	});
	$('#addPdAmountOrder_orderAmount').bind('input propertychange', function(e){  
         //将本次修改的值更新到rowData的相应列数据中  
         //rowData[workRateEditor.field] = $(this).val();
			 var tipsContent = numToUpper($(this).val());
			// alert(tipsContent);
			 $(this).tips({
                  side:2,  //1,2,3,4 分别代表 上右下左
                  msg:tipsContent,//tips的文本内容
                  color:'#FFF',//文字颜色，默认为白色
                  bg:'#FD9720',//背景色，默认为红色
                  time:3,//默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
                  x:0,// 默认为0 横向偏移 正数向右偏移 负数向左偏移
                  y:0 // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
          });
     }); 
//	$('#addPdAmountOrder_custMobile').validatebox({required:true});
	//客户姓名
	$('#addPdAmountOrder_custName').combobox({
		valueField:'name',    
	    textField:'codeName',  
	    required:true,
	    url:'codeQuery/customerQueryByUserId?userId='+userId,
	    onSelect:function(record){
	    	var param = {};
	    	var custBaseInfoId = record.id;
	    	param.custBaseInfoId = custBaseInfoId;
	    	$.ajax({
	    		type:'post',
	    		url:contextPath+"/productOrder/getCustomerInfo",
	    		data:'param='+$.toJSON(param),
	    		cache:false,
	    		success:function(reData){
	    			if(reData.success){
						var dataObj = reData.obj;
						if(dataObj!=null){
							setInputValueById("addPdAmountOrder_CustomerInfo",dataObj);
						}
	    			}else{
	    				$.messager.alert('提示', reData.msg);
						$("#addPdCustOrder_submitButton").hide();
						}
	    		}
	    	});
	    }/*,
	    onChange : function(newValue, oldValue){
			comboboxOnChange(this,newValue,oldValue);
		}*/
	});
	//分公司
	$("#addPdAmountOrder_comId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//理财经理
	$("#addPdAmountOrder_agentId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//产品方
	$("#addPdAmountOrder_agenyComId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//产品名称
	$("#addPdAmountOrder_productId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//证件类型
	$("#addPdAmountOrder_idType").combobox({
		valueField:'code',    
	    textField:'codeName', 
	    editable:false,
	    //required:true,
	    url:'codeQuery/tdCodeQuery?codeType=idType',
	    onSelect:function(record){
	    	if(record.code=="1"){
				var idNo = $("#addCust_IdNo").val();
				if(idNo!=null&&idNo!=""&&idNo!=undefined){
					verifyIdNo();
				}
			}
	    }
	});
	//开户行
	$("#addPdAmountOrder_bankCode").combobox({
		valueField:'bankId',
		textField:'bankName',
		url:contextPath+"/codeQuery/queryBankInfo"
	});
	
	
	//省
	$("#addPdAmountOrder_province").combobox({
		url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName',
		onSelect:function(){
			var upPlaceCode = $("#addPdAmountOrder_province").combobox("getValue");
			var url = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+upPlaceCode;
			$("#addPdAmountOrder_city").combobox("reset");
			$("#addPdAmountOrder_country").combobox("reset");
			$("#addPdAmountOrder_city").combobox("reload",url);
		}
	});
	$("#addPdAmountOrder_city").combobox({
		//url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName',
		onSelect:function(){
			var upPlaceCode = $("#addPdAmountOrder_city").combobox("getValue");
			var url = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+upPlaceCode;
			$("#addPdAmountOrder_country").combobox("reset");
			$("#addPdAmountOrder_country").combobox("reload",url);
		}
	});
	$("#addPdAmountOrder_country").combobox({
		//url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName'
	});
	
	//是否为老客户
	$("#addPdAmountOrder_isOldCustomer").combobox({
		valueField:'code',    
	    textField:'codeName', 
	    disabled:true,
	    url:'codeQuery/tdCodeQuery?codeType=isOldCustomer'
	});
}

function setInputEnable(){
	$('#addPdAmountOrder_idType').datebox("disable");
	//$('#addPdAmountOrder_idNo').attr('disabled',"true");
	$('#addPdAmountOrder_idValidityDate').attr('disabled',"true");
	//$('#addPdAmountOrder_custEmail').attr('disabled',"true");
}

/**
 * 袁正军 2015-08-13
 * 初始化时获取产品、理财顾问、机构等相关信息
 */
function getProductAndComInfo(){
	var param = {};
	param.comId = comId;
	param.productId =  productId;
	param.expectOpenDay = expectOpenDay;
	param.pdAmountOrderInfoId = pdAmountOrderInfoId;
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/getProductAndComInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var dataObj = reData.obj;
					if(dataObj!=null){
						setInputValueById("productAmountOrderDiv",dataObj);
						//分公司名称
						$("#addPdAmountOrder_comId").combobox("setText",dataObj.comName);
						//理财经理
						$("#addPdAmountOrder_agentId").combobox("setText",dataObj.agentName);
						//产品方
						$("#addPdAmountOrder_agenyComId").combobox("setText",dataObj.agencyComName);
						//产品名称
						$("#addPdAmountOrder_productId").combobox("setText",dataObj.productName);
						//理财经理联系方式
						$("#addPdAmountOrder_mobile").val(dataObj.agentMobile);
					}
				}else{
					//$("#addPdCustOrder_submitButton").attr({"disabled":"disabled"});
					$.messager.alert('提示', reData.msg);
					$("#addPdCustOrder_submitButton").hide();
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}



/**
 * 提交申请
 */
function saveProductAmountOrderInfo(){
	if(!$("#addPdAmountOrder_BaseInfoForm").form("validate")){
		$.messager.alert('提示', "请填写必填项！", 'info');
		return;
	}
	/*var inviteCode = $("#addPdAmountOrder_inviteCode").val();
	if(isInviteCode=="01"){
		if(inviteCode==null||inviteCode==""||inviteCode==undefined){
			$.messager.alert('提示', "请先获取邀请码！", 'info');
			return;
		}
	}*/
	//校验证件号码
	if(!verifyIdNo()){
		return;
	}
	//预约成功不能重复预约
	var pdAmountOrderId = $("#addPdAmountOrder_pdAmountOrderInfoId").val();
	if(pdAmountOrderId!=null&&pdAmountOrderId!=""&&pdAmountOrderId!=undefined){
		$.messager.alert('提示', "该客户已经预约成功，不能重复预约！", 'info');
		return;
	}
	
	//判断是否已经购买物业宝系列产品并提交
	$.messager.confirm('确认',"预约信息提交后不能修改，请仔细检查预约信息，您确认是否提交预约？",function(r){    
	    if (r){ 
	    	isHadBuyWYBProduct();
	    } 
	}); 
}



/**
 * 获取客户预约信息
 * 
 * */
function getCustProductAmountOrderInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/getCustProductAmountOrderInfo",
		data:'pdAmountOrderInfoId='+pdAmountOrderInfoId,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var dataObj = reData.obj;
					if(dataObj!=null){
						if(operate=="detailCustOrder"){
							var provinceCode = dataObj.province;
							var loadCityDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode;
							$("#addPdAmountOrder_city").combobox('reload',loadCityDataUrl);
							var cityCode = dataObj.city;
							var loadCountryDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode;
							$("#addPdAmountOrder_country").combobox('reload',loadCountryDataUrl);
						}
						setInputValueById("addPdAmountOrder_CustomerInfo",dataObj);
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}



/**
 * 
 * 获取邀请码
 * 
 * */
function getInviteCode(){
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/getInviteCode",
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var dataObj = reData.obj;
					if(dataObj!=null){
						$("#addPdAmountOrder_inviteCode").val(dataObj);
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function verifyIdNo(){
	//身份证号码格式校验
	var idType = $("#addPdAmountOrder_idType").combobox("getValue");
	var idNo = $("#addPdAmountOrder_idNo").val();
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		if(!checkIdNo(idNo)){
			//$.messager.alert('提示', "该理财顾问编码已经存在！", 'info');
			return false;
		}
	}
	
	return true;
}

/**
 * 返回
 */
function backListProductInfoPage(){
	$('#addPdAmountOrderWindow').window('destroy');
	if(operate != null && operate != "" && operate != undefined && operate == "addCustOrder"){
		queryProductAmountOrderList();
	}
	if(operate != null && operate != "" && operate != undefined && operate== "detailCustOrder"){
	}
	if(operate != null && operate != "" && operate != undefined && operate == "updateCustOrder"){
		queryProductdAmountOrderInfoList();
	}
}

/**
 * 
 * 判断客户是否购买过物业宝系列产品
 */
function isHadBuyWYBProduct(){
	var custName = $("#addPdAmountOrder_custName").val();
	var custMobile = $("#addPdAmountOrder_custMobile").val();
	var idNo = $("#addPdAmountOrder_idNo").val();
	var idType = $("#addPdAmountOrder_idType").combobox("getValue");
	var param = {};
	param.productId =  productId;
	param.foundDate = expectOpenDay;
	param.custName = custName;
	param.custMobile = custMobile;
	param.idNo = idNo;
	param.idType = idType;
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/isHadBuyWYBProduct",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					if(remainAmount>0){
						submitOrderInfo();
					}else{
						$.messager.confirm('确认',"该产品剩余额度为0！是否加入预约排队队列？",function(r){    
						    if (r){
						    	submitOrderInfo();
						    } 
						}); 
					}
				}else{
					//$.messager.alert('提示', reData.msg);
					$.messager.confirm('确认',reData.msg+",您确认继续预约吗？",function(r){    
					    if (r){ 
					    	var custConfirmOrder = "01";
					    	submitOrderInfo(custConfirmOrder);
					    } 
					}); 
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function submitOrderInfo(custConfirmOrder){
	var param = {};
	var pdAmountOrderInfo = $("#addPdAmountOrder_BaseInfoForm").serialize();
	var productName = $("#addPdAmountOrder_productId").combobox("getText");
	pdAmountOrderInfo = formDataToJsonStr(pdAmountOrderInfo);
	param.pdAmountOrderInfo = pdAmountOrderInfo;
	param.custConfirmOrder = custConfirmOrder;
	param.isInviteCode = isInviteCode;
	param.productName = productName;
	param.isDistribute = isDistribute;
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/saveProductAmountOrderInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					//$.messager.alert('提示', reData.msg);
					$.messager.confirm('提示',reData.msg,function(r){    
					    if (r){ 
					    	$("#addPdCustOrder_submitButton").linkbutton('disable');
							var dataObj = reData.obj;
							//关闭产品预约页面
							backListProductInfoPage();
							if(dataObj!=undefined&&dataObj!=null&&dataObj!=''&&dataObj!='null'){
								setInputValueById("addPdAmountOrder_CustomerInfo",dataObj);
								var tradeInfoId=dataObj.tradeInfoId;
								addTradeInfo("更新交易信息","","update",tradeInfoId);
							}
					    }else{
					    	//关闭产品预约页面
							backListProductInfoPage();
					    } 
					});
					
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

//打开交易更新页面
function addTradeInfo(title,rowIndex,type,tradeInfoId){	
//	$('#addPdAmountOrderWindow').window('destroy');
	
	var param = {};
	param.tradeInfoId=tradeInfoId;
	$('<div id="tradeInfoInput"/>').dialog({
			href : contextPath + "/trade/tradeInputInfo?param="+$.toJSON(param),
			modal : true,
			fit:true,
			title : title,
			inline : false,
			minimizable : false,
			onLoad:function(){ //当加载成功的时候隐藏三个div
				$("#tradeInputCheckConclusionDiv").hide();
				$("#tradeInputAuditConclusionDiv").hide();
				$("#addressInfoInputPanelId").hide();
				if(type=="update" || type=="query"){
//					var tradeInfoId = null;
					if(type=="update"){
						//查询生成交易的基本信息
						getTradeBaseInfo(tradeInfoId);
					}
					
					$("#tradeRiskProtObj").combobox({
						url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Input").val(),
						valueField:'custID',
						textField:'custName'
					});
					$("#tradeType_Input").combobox('disable');
					
					
				}
			},
			//在面板关闭之后触发
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
}

//查询生成交易的基本信息
function getTradeBaseInfo(tradeInfoId){
	var param = {};
	param.tradeInfoId=tradeInfoId;
	$.ajax({
		type:'post',
		url:contextPath+"/trade/getTradeBaseInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try{
				if(reData.success){
					var dataObj=reData.obj;
					setInputValueById("tradeInfoForm_input",dataObj);
					//初始化该客户的基本信息
					initCustomInfoId();
					//初始化角色的基本信息
				    //initRoleInfoId();
					var tradeType_Input = $("#tradeType_Input").combobox('getValue');
					if(dataObj.custAccInfoId!=null && dataObj.custAccInfoId!=""){
						initTradeBankInfoInputId(dataObj.custAccInfoId,"search");
					}else{
						initTradeBankInfoInputId(dataObj.tradeInfoId,"query");
					}
					if(tradeType_Input=="2")
					{
						$("#roleInfoInputPanelId").show();
						$("#riskProInputInfoPanelId").show();
						$("#monProInfoInputPanelId").hide();
						//初始化保险产品信息
						initRiskProInputInfoId(tradeType_Input);
						$("#riskTotalAssets_Input").val(dataObj.totalAssets);
						$("#chnRiskTotalAssets_Input").val(dataObj.chnTradeTotalAssets);
					}else if(tradeType_Input=="1"){
						$("#roleInfoInputPanelId").hide();
						$("#riskProInputInfoPanelId").hide();
						$("#monProInfoInputPanelId").show();
						//初始化财富产品信息
						initMonryProductInfoId(tradeType_Input);
						$("#monTotalAssets_Input").val(dataObj.totalAssets);
						$("#chnMonTotalAssets_Input").val(dataObj.chnTradeTotalAssets);
						
						$("#addressInfoInputPanelId").show();
						if(dataObj.custAddressInfoId!=null && dataObj.custAddressInfoId!=""){
							initTradeAddressInfoInputId(dataObj.custAddressInfoId,"search");
						}else{
							initTradeAddressInfoInputId(tradeInfoId,"query");
					}
					}
					var productId = dataObj.productId;
					$("#tradeInfoNo_Input").combobox({
						url : contextPath + '/codeQuery/contractNumberQuery?productId='+productId,
						valueField : 'id',
						textField : 'name'
					});
					$("#tradeInfoNo_Input").combobox("setValue",$("#contractNum_Input").val());
//					$.messager.alert('提示', reData.msg);
				}else{
					$.messager.alert('提示', reData.msg);
				}
			}catch (e) {
				$.messager.alert('提示', e);
			}
		}
		
	});
		
	
}

/*
 *更新预约信息
 */
function updateProductAmountOrderInfo(){
	if(!$("#addPdAmountOrder_BaseInfoForm").form("validate")){
		return;
	}
	var orderAmount = $('#addPdAmountOrder_orderAmount').val();
	var param = {};
	param.productId =  productId;
	param.tradeInfoId =  tradeInfoId;
	param.pdAmountOrderInfoId =  pdAmountOrderInfoId;
	param.orderAmount =  orderAmount;
	param.isDistribute = isDistribute;
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/updateProductRemainAmount",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try{
				if(reData.success){
					$.messager.alert('提示', reData.msg);
					$("#updatePdCustOrder_submitButton").linkbutton('disable');
				}else{
					$.messager.alert('提示', reData.msg);
				}
			}catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}