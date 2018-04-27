var productId=null;
var productTypeCombobox=null;
var productSubTypeCombobox=null;


/**
 * 椤甸潰鍔犺浇
 */
jQuery(function($) {
	// 鍔犺浇鎵�鏈夌殑涓嬫媺妗�
	initAllCombobox();
	//鍒濆鍖栨枃鏈繀濉」
	initAllValidateBox();
});

/**
 * 鍒濆鍖栦笅鎷夋
 */
function  initAllCombobox(){
	// 浜у搧绫诲瀷鍒濆鍖�
	productTypeCombobox = $("#addProduct_productType").combobox(
					{
						url : contextPath+ '/codeQuery/tdCodeQuery?codeType=productType',
						valueField : 'code',
						textField : 'codeName',
						editable : false,
						onSelect : function() {
							var codeType;
							productTypeValue = productTypeCombobox.combobox("getValue");
							if (productTypeValue == 1) {
								codeType = 'wealthProSubType';
							} else {
								codeType = 'insProSubType';
							}
							var url = contextPath+ '/codeQuery/tdCodeQuery?codeType='+ codeType;
							productSubTypeCombobox.combobox("reload", url);
						}
					});
	// 浜у搧瀛愮被鍒濆鍖�

	productSubTypeCombobox = $("#addProduct_productSubType").combobox(
			{
			  valueField : 'code',
			  textField : 'codeName'
			});
	
	// 鍚堜綔鏈烘瀯
	$("#addProduct_agencyCode").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	// 閿�鍞姸鎬佸垵濮嬪寲
	$("#addProduct_salesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		value : '0'
	});
	//閭�璇风爜
	$("#addProduct_isInviteCode").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isInviteCode',
		valueField : 'code',
		textField : 'codeName'
	});
	
	$("#isOrder").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isOrder',
		valueField : 'code',
		textField : 'codeName',
		required:true
	
	});
 }

/**
 * 
 * 鍒濆鏂囨湰鏍煎紡鐨勫繀褰曢」
 */
function initAllValidateBox(){
	$('#addProduct_productCode').validatebox({required:true});	
	$('#addProduct_productName').validatebox({required:true});
}

/**
 * 鎻愪氦浜у搧鍩烘湰淇℃伅
 */
function addProductBasicInfo(){
	//鏍￠獙蹇呭～椤�
	if (!$("#addProduct_basicInfoForm").form("validate")) {
		return false;
	}
	var param={};
	var basicInfoJson = formDataToJsonStr($("#addProduct_basicInfoForm").serialize());
	param.productBaseInfo = basicInfoJson;
	param.productId=productId;
	//淇濆瓨鍩烘湰淇℃伅
	$.ajax({
		type : 'post',
		url : contextPath + "/product/addProductBasicInfoUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
				    $("#addProduct_productType").combobox("disable");
					$("#addProduct_productSubType").combobox("disable");
					if (productId==null||productId==undefined||productId=="") {
						productId=resultInfo.obj;
						addProductTabs();
					}
					else{
						productId=resultInfo.obj;
						$.messager.alert('鎻愮ず', resultInfo.msg,"info");
					}
				} else {
					$.messager.alert('鎻愮ず', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('鎻愮ず', e);
			}
		}
	});
}

/**
 * 鎵撳紑浜у搧鍚凾AB椤�
 */
function addProductTabs(){
	//鑾峰彇浜у搧绫诲瀷鍜屽瓙绫讳俊鎭�
	var param={};
	param.productType=productTypeCombobox.combobox("getValue");
	param.productSubType=productSubTypeCombobox.combobox("getValue");
	param.productId=productId;

	$('#addProductTabs').tabs('add',{
		title: '鏍稿績浜у搧淇℃伅',
		selected: true,
		closable:true,
		href : contextPath+"/product/addProductCoreInfoUrl?param="+$.toJSON(param)
	});
	
	$('#addProductTabs').tabs('add',{
		title: '缃戦攢浜у搧淇℃伅',
		selected: false,
		closable:true,
		href : contextPath+"/product/addProductInternetInfoUrl?param="+$.toJSON(param)
	});
	
	$('#addProductTabs').tabs('add',{
		title: '闄勬枃鏈紪杈戝尯',
		selected: false,
		closable:true,
		href : contextPath+"/product/addProductEditorUrl?param="+$.toJSON(param)
     });
	
	

}
/**
 * 杩斿洖鎸夐挳
 */
function backListProductPage(){
	$('#addProdutctInfo').window('destroy');
	parent.clearFormInfo();
}
/**
 * 浜у搧缂栫爜杞崲澶у啓
 */
function productCodeToUpperCase(){
	var productCode = $("#addProduct_productCode").val();
	if(productCode!=null&&productCode!=""&&productCode!=undefined){
		productCode = productCode.toLocaleUpperCase();
		$("#addProduct_productCode").val(productCode);
	}
}