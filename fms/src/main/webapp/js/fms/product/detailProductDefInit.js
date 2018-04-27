var modifyBasicProductId=null;
var modifyBasicProductType=null;
var modifyBasicProductSubType=null;
var modifyBasicProductStatus=null;

/**
 * 椤甸潰鍔犺浇
 */
jQuery(function($) {
	//$("#updateProductTabs").tabs({border:true});
	//鑾峰彇淇敼棰濅骇鍝佷俊鎭�
	modifyBasicProductType=$("#modifyBasicProductType").val();
	modifyBasicProductSubType=$("#modifyBasicProductSubType").val();
	modifyBasicProductId=$("#modifyBasicProductId").val();
	modifyBasicProductStatus=$("#modifyBasicProductStatus").val();
	// 鍔犺浇鎵�鏈夌殑涓嬫媺妗�
	initAllCombobox();
	//鍒濆鍖栨枃鏈繀濉」
	initAllValidateBox();
	//鑾峰彇淇敼鐨勪骇鍝佸熀鏈俊鎭�
	getUpdateProductBasicInfo();
	
});



/**
 * 鍒濆鍖栦笅鎷夋
 */
function  initAllCombobox(){
	// 浜у搧绫诲瀷鍒濆鍖�
	var productTypeCombobox;
	productTypeCombobox = $("#updateProduct_productType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=productType',
		valueField : 'code',
		textField : 'codeName'
	});

	// 浜у搧瀛愮被鍒濆鍖�
	if (modifyBasicProductType == '1') {
		var productSubTypeCombobox;
		productSubTypeCombobox = $("#updateProduct_productSubType").combobox({
		url : contextPath+ '/codeQuery/tdCodeQuery?codeType=wealthProSubType',
		valueField : 'code',
		textField : 'codeName'
	});
	} 
	else 
	{
		var productSubTypeCombobox;
		productSubTypeCombobox = $("#updateProduct_productSubType").combobox({
		url : contextPath+'/codeQuery/tdCodeQuery?codeType=insProSubType',
		valueField : 'code',
		textField : 'codeName'
	});
    }
	
	// 鍚堜綔鏈烘瀯
	$("#updateProduct_agencyCode").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 閿�鍞姸鎬佸垵濮嬪寲
	$("#updateProduct_salesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		value : '0'
	});
	//閭�璇风爜
	$("#updateProduct_isInviteCode").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isInviteCode',
		valueField : 'code',
		textField : 'codeName'
	});
	
	$("#updateProduct_isOrder").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isOrder',
		valueField : 'code',
		textField : 'codeName'
	});
}

/**
 * 
 * 鍒濆鏂囨湰鏍煎紡鐨勫繀褰曢」
 */
function initAllValidateBox(){
	$('#updateProduct_productCode').validatebox({required:true});	
	$('#updateProduct_productName').validatebox({required:true});
}


/**
 * 鑾峰彇淇敼鐨勪骇鍝佷俊鎭�
 */
function getUpdateProductBasicInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/product/getUpdateProductBasicInfo",
		data:{param:$('#modifyBasicProductId').val()},
		cache:false,
		success:function(result){
			try {
				if(result.success){
					var resultObj = result.obj;
					if(resultObj.modifyBasicProduct!=null){
						setInputValueById("updateProduct_basicInfoForm",resultObj.modifyBasicProduct);
					}
					if(resultObj.modifyStatus!=null){
						//宸插彂甯冪殑浜у搧 杩涜鏇存敼鏃讹紝涓嶅厑璁哥紪杈戜骇鍝佷唬鐮佸拰浜у搧鍚嶇О锛岄槻姝㈠噣鍊奸偅鍎夸笉鍖归厤
						if(resultObj.modifyStatus=='2'){
							 $("#updateProduct_productCode").attr('required',false);
							 $("#updateProduct_productCode").attr("readonly",true);
							 $("#updateProduct_productName").attr('required',false);
							 $("#updateProduct_productName").attr("readonly",true);
						}
					}
					//鎵撳紑鍏朵粬淇敼tab椤�
					updateProductTabs();
				}else{
					$.messager.alert('鎻愮ず', result.msg);
				}
			} catch (e) {
				$.messager.alert('鎻愮ず', e);
			}
		}
	});
}

/**
 * 鎻愪氦浜у搧鍩烘湰淇℃伅
 */
function updateProductBasicInfo(){
	
	//鏍￠獙蹇呭～椤�
	if (!$("#updateProduct_basicInfoForm").form("validate")) {
		return false;
	}
	productCodeToUpperCase();
	var param={};
	var basicInfoJson = formDataToJsonStr($("#updateProduct_basicInfoForm").serialize());
	param.productBaseInfo = basicInfoJson;
	param.modifyBasicProductId=modifyBasicProductId;
	param.modifyBasicProductType=modifyBasicProductType;
	param.modifyBasicProductSubType=modifyBasicProductSubType;
	param.modifyBasicProductStatus=modifyBasicProductStatus;
	//淇濆瓨鍩烘湰淇℃伅
	$.ajax({
		type : 'post',
		url : contextPath + "/product/updateProductBasicInfoUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo){
			try {
				if (resultInfo.success) {
					$.messager.alert('鎻愮ず', resultInfo.msg,"info");
				} else {
					$.messager.alert('鎻愮ず', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('鎻愮ず', e);
			}
		}
	});
}


function productCodeToUpperCase(){
	var productCode = $("#modify_productCode").val();
	if(productCode!=null&&productCode!=""&&productCode!=undefined){
		productCode = productCode.toLocaleUpperCase();
		$("#modify_productCode").val(productCode);
	}
	 
}

/**
 * 鎵撳紑浜у搧鍚凾AB椤�
 */
function updateProductTabs(){
	//鑾峰彇浜у搧绫诲瀷鍜屽瓙绫讳俊鎭�
	var param={};
	param.updateTabProductType=modifyBasicProductType;
	param.updateTabProductSubType=modifyBasicProductSubType;
	param.updateTabProductId=modifyBasicProductId;
	param.updateTabProductStatus=modifyBasicProductStatus;
	
	$('#updateProductTabs').tabs('add',{
		title: '鏍稿績浜у搧淇℃伅',
		selected: false,
		closable:true,
		href : contextPath+"/product/detailProductCoreInfoUrl?param="+$.toJSON(param)
	});
	
	$('#updateProductTabs').tabs('add',{
		title: '缃戦攢浜у搧淇℃伅',
		selected: false,
		closable:true,
		href : contextPath+"/product/detailProductInternetInfoUrl?param="+$.toJSON(param)
	});
	
	$('#updateProductTabs').tabs('add',{
		title: '闄勬枃鏈紪杈戝尯',
		selected: false,
		closable:true,
		href : contextPath+"/product/detailProductEditorUrl?param="+$.toJSON(param)
	});
	
}



/**
 * 杩斿洖鎸夐挳
 */
function backListProductPage(){
	$('#addWindow').window('destroy');
	parent.clearFormInfo();
}
/**
 * 浜у搧缂栫爜杞崲澶у啓
 */
function productCodeToUpperCase(){
	var productCode = $("#updateProduct_productCode").val();
	if(productCode!=null&&productCode!=""&&productCode!=undefined){
		productCode = productCode.toLocaleUpperCase();
		$("#updateProduct_productCode").val(productCode);
	}
}