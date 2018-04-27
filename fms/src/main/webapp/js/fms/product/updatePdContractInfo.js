jQuery(function($) {
	var productId = $("#updatePdContractInfo_productId").val();
	initAllCombobox();
	//初始化文本必填项
	initAllValidateBox();
	//查询信息
	getUpdatePDContractInfo();
});


function  initAllCombobox(){
	// 产品初始化 显示为：产品代码-产品名称
	 $("#updateContractInfoProId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName',
		disabled:true
	});
}


function initAllValidateBox(){
	//$('#updateContractCode').attr("disabled","disabled"); 
	$('#updateContractStartNum').attr("disabled","disabled"); 
	$('#updateContractCode').validatebox({required:true});
	$('#updateContractEndNum').validatebox({required:true});
}

/**
 * 获取修改的产品合同信息
 */
function getUpdatePDContractInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/product/getUpdatePDContractInfo",
		data:{param:$("#updatePdContractInfo_productId").val()},
		cache:false,
		success:function(result){
			try {
				if(result.success){
					var resultObj = result.obj;
					setInputValueById("updateContractInfoForm",resultObj.productContractInfo);
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
 * 提交产品基本信息
 */
function updatePdContractInfo(){
	
	//校验必填项
	if (!$("#updateProduct_basicInfoForm").form("validate")) {
		return false;
	}
	//productCodeToUpperCase();
	var param={};
	var basicInfoJson = formDataToJsonStr($("#updateContractInfoForm").serialize());
	param.pdContractInfo = basicInfoJson;
	param.productId = $("#updatePdContractInfo_productId").val();
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/product/updatePdContractInfo",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo){
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg,"info");
					$('#updatePdContract_submitButton').linkbutton('disable');
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function backContractPage(){
	$('#addWindow').window('destroy');
	parent.clearForm();
}