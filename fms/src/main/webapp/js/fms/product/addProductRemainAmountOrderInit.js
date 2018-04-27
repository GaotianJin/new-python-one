var comId;
var productId;
var expectOpenDay;
var pdAmountOrderQueueInfoId;
var isInviteCode;
var productType;
var productSubType;
var queueIsDistribute;
var remainAmount;

/**
 * 页面初始化加载
 */
jQuery(function($) {
	//全局变量赋值
	comId = $("#addRemainAmountOrder_rdcomId").val();
	productId = $("#addRemainAmountOrder_rdproductId").val();
	expectOpenDay = $("#addRemainAmountOrder_rdexpectOpenDay").val();
	pdAmountOrderQueueInfoId = $("#addRemainAmountOrder_rdpdAmountOrderQueueInfoId").val();
	productType = $("#addRemainAmountOrder_rdproductType").val();
	productSubType = $("#addRemainAmountOrder_rdproductSubType").val();
	queueIsDistribute = $("#addRemainAmountOrder_rdqueueIsDistribute").val();
	remainAmount = $("#addRemainAmountOrder_rdremainAmount").val();
	//固收类期望开放日不要
	if(productType=="1"&&productSubType=="02"){
		$('#addRemainAmountOrder_expectOpenDay').attr('disabled',"true");
	}else if(productType=="1"&&(productSubType=="01"||productSubType=="03")){
		//$("#addPdAmountOrder_expectOpenDay").val(expectOpenDay);
	}
	isInviteCode = $("#addRemainAmountOrder_isInviteCode").val();
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
	//初始化获取产品、财富顾问、机构等相关信息
	getQueueProductAndComInfo();
	getCustRemainAmountOrderInfo();
});

/**
 * 
 * 初始文本格式的必录项
 */
function initAllValidateBox(){
	$('#addRemainAmountOrder_custName').validatebox({required:true});	
	$('#addRemainAmountOrder_orderAmount').validatebox({
		required:true
	});
	$('#addRemainAmountOrder_orderAmount').bind('input propertychange', function(e){  
         //将本次修改的值更新到rowData的相应列数据中  
         //rowData[workRateEditor.field] = $(this).val();
			 var tipsContent = numToUpper($(this).val());
			// alert(tipsContent);
			 $(this).tips({
                  side:1,  //1,2,3,4 分别代表 上右下左
                  msg:tipsContent,//tips的文本内容
                  color:'#FFF',//文字颜色，默认为白色
                  bg:'#FD9720',//背景色，默认为红色
                  time:3,//默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
                  x:0,// 默认为0 横向偏移 正数向右偏移 负数向左偏移
                  y:0 // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
          });
     }); 
	$('#addRemainAmountOrder_custMobile').validatebox({required:true});
	//分公司
	$("#addRemainAmountOrder_comId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//理财经理
	$("#addRemainAmountOrder_agentId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//产品方
	$("#addRemainAmountOrder_agenyComId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//产品名称
	$("#addRemainAmountOrder_productId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	//证件类型
	$("#addRemainAmountOrder_idType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    //required:true,
	    url:'codeQuery/tdCodeQuery?codeType=idType',
	    onSelect:function(record){
	    	if(record.code=="1"){
				var idNo = $("#addRemainAmountOrder_idNo").val();
				if(idNo!=null&&idNo!=""&&idNo!=undefined){
					verifyIdNo();
				}
			}
	    }
	});
	//开户行
	$("#addRemainAmountOrder_bankCode").combobox({
		valueField:'bankId',
		textField:'bankName',
		url:contextPath+"/codeQuery/queryBankInfo"
	});
		
	//省
	$("#addRemainAmountOrder_province").combobox({
		url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName',
		onSelect:function(){
			var upPlaceCode = $("#addRemainAmountOrder_province").combobox("getValue");
			var url = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+upPlaceCode;
			$("#addRemainAmountOrder_city").combobox("reset");
			$("#addRemainAmountOrder_country").combobox("reset");
			$("#addRemainAmountOrder_city").combobox("reload",url);
		}
	});
	$("#addRemainAmountOrder_city").combobox({
		//url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName',
		onSelect:function(){
			var upPlaceCode = $("#addRemainAmountOrder_city").combobox("getValue");
			var url = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+upPlaceCode;
			$("#addRemainAmountOrder_country").combobox("reset");
			$("#addRemainAmountOrder_country").combobox("reload",url);
		}
	});
	$("#addRemainAmountOrder_country").combobox({
		//url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName'
	});
	
	//是否为老客户
	$("#addRemainAmountOrder_isOldCustomer").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    required:true,
	    url:'codeQuery/tdCodeQuery?codeType=isOldCustomer'
	});
}



/**
 * 
 * 初始化时获取产品、财富顾问、机构等相关信息
 */
function getQueueProductAndComInfo(){
	var param = {};
	param.comId = comId;
	param.productId =  productId;
	param.expectOpenDay = expectOpenDay;
	param.pdAmountOrderQueueInfoId = pdAmountOrderQueueInfoId;
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/getQueueProductAndComInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var dataObj = reData.obj;
					if(dataObj!=null){
						setInputValueById("productRemainAmountOrderDiv",dataObj);
						//分公司名称
						$("#addRemainAmountOrder_comId").combobox("setText",dataObj.comName);
						//理财经理
						$("#addRemainAmountOrder_agentId").combobox("setText",dataObj.agentName);
						//产品方
						$("#addRemainAmountOrder_agenyComId").combobox("setText",dataObj.agencyComName);
						//产品名称
						$("#addRemainAmountOrder_productId").combobox("setText",dataObj.productName);
						//理财经理联系方式
						$("#addRemainAmountOrder_mobile").val(dataObj.agentMobile);
					}
				}else{
					//$("#addPdCustOrder_submitButton").attr({"disabled":"disabled"});
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 获取队列客户额度预约信息
 * 
 * */
function getCustRemainAmountOrderInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/getCustRemainAmountOrderInfo",
		data:'pdAmountOrderQueueInfoId='+pdAmountOrderQueueInfoId,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var dataObj = reData.obj;
					if(dataObj!=null){
							var provinceCode = dataObj.province;
							var loadCityDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode;
							$("#addRemainAmountOrder_city").combobox('reload',loadCityDataUrl);
							var cityCode = dataObj.city;
							var loadCountryDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode;
							$("#addRemainAmountOrder_country").combobox('reload',loadCountryDataUrl);
						setInputValueById("addRemainAmountOrder_CustomerInfo",dataObj);
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
/*function getInviteCode(){
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
}*/

/**
 * 身份证号验证
 */
function verifyIdNo(){
	//身份证号码格式校验
	var idType = $("#addRemainAmountOrder_idType").combobox("getValue");
	var idNo = $("#addRemainAmountOrder_idNo").val();
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		if(!checkIdNo(idNo)){
			//$.messager.alert('提示', "该财富顾问编码已经存在！", 'info');
			return false;
		}
	}
	return true;
}

/**
 * 返回
 */
function backListPage(){
	$('#addPdRemainAmountOrderWindow').window('destroy');
	initRemainAmountDistributeTable
}