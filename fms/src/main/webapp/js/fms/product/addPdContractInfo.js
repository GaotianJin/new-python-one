jQuery(function($) {
	initAllCombobox();
	//初始化文本必填项
	initAllValidateBox();
});


function  initAllCombobox(){
	// 产品初始化 显示为：产品代码-产品名称
	 $("#addContractInfoProId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});
}


function initAllValidateBox(){
	$('#addContractCode').validatebox({required:true});	
	$('#addContractStartNum').validatebox({required:true});
	$('#addContractEndNum').validatebox({required:true});
}


function savePdContractInfo(){
	//校验必填项
	if (!$("#addContractInfoForm").form("validate")) {
		return false;
	}
	var contractInfoJson = formDataToJsonStr($("#addContractInfoForm").serialize());
	$.ajax({
		type:'post',
		url:contextPath + '/product/addPdContractInfo',
		data:'param='+contractInfoJson,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', reData.msg);
					$("#addPdContract_submitButton").linkbutton('disable');
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function backListContractPage(){
	$('#addWindow').window('destroy');
	parent.clearForm();
}