var modifyRecordNetWorthId=null;
var modifyAngecyName=null;
var modifyProductName=null;
var modifyProductId=null;

$(function(){
	initAllCombobox();
	modifyRecordNetWorthId = $("#modifyRecordNetWorthId").val();
	modifyAngecyName= $("#modifyAngecyName").val();
	modifyProductName= $("#modifyProductName").val();
	getUpdateInitValue();
});
function getUpdateInitValue(){
	$.ajax({
		type : 'post',
		url : contextPath+"/product/updateNetValueInitUrl",
		data:{param:$('#modifyRecordNetWorthId').val()},
		cache : false,
		success : function(resultInfo) {
		setInputValueById("updateNetValueDiv",resultInfo);
	   $("#ThirdagencyComId").val(modifyAngecyName);
	   $("#ThirdproductId").val(modifyProductName);
		}
	});
}

function updateNetValueInfo(){
	if (!$("#updateNetValueInfoForm").form("validate")) {
		alert("请输入必录信息！");
		return false;
	}
	
	
	var param = {};
	var netValueInfoJson = formDataToJsonStr($("#updateNetValueInfoForm").serialize());
	param.netValueBaseInfo = netValueInfoJson;
    param.modifyRecordNetWorthId=modifyRecordNetWorthId;
    param.modifyProductId=modifyProductId;
	$.ajax({
		type : 'post',
		url : contextPath + "/product/saveUpdateNetValueUrl",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示',resultInfo.msg, 'info');
					$.messager.progress('close');
					
					clearForm();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}


function initAllCombobox()
{
	$("#thirdnetType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueType',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 是否生成预估短信
	$("#updateNetValue_isOrder").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=addNetValueIsOrder',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 是否生特殊短信
	$("#updateNetValue_specialType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueSpecialType',
		valueField : 'code',
		textField : 'codeName'
	});
}



function backLisNetValuePage(){
	$('#addWindow').window('destroy');
	parent.clearForm();
}


