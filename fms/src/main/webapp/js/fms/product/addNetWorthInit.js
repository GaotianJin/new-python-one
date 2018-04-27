// 新增完成之后，返回的净值信息流水号变量
var pdwealthNetValueId = null;

$(function() {
	initAllCombobox();
});

//提交
function submit() {
	// 产品基本信息
	if (!$("#netValueInfoForm").form("validate")) {
		alert("请输入必录信息！");
		return false;
	}
	$('#submitButton').linkbutton('disable');
	var param = {};
	var netValueInfoJson = formDataToJsonStr($("#netValueInfoForm").serialize());
	param.netValueBaseInfo = netValueInfoJson;
	param.pdwealthNetValueId=pdwealthNetValueId;
	
	$.ajax({
		type : 'post',
		url : contextPath + "/product/addNetValueAndFileInfoUrl",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					pdwealthNetValueId=resultInfo.obj;
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
				} else {
					$.messager.alert('提示', resultInfo.msg);
					$('#submitButton').linkbutton('enable');
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
			//$('#submitButton').linkbutton('disable');
		}
	});
	
}

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {

	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#jzSecondProductId").combobox({
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#jzSecondAgentComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName',
				onSelect : function() {
					var codeType = agencyComIdCombobox.combobox("getValue");
					var url = contextPath + '/codeQuery/productwealthQuery?codeType='+ codeType;
					$("#jzSecondProductId").combobox("clear");
					productIdComobox.combobox("reload", url);
				}

			});

	// 净值类型
	$("#jzSecondNetValueType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueType',
		valueField : 'code',
		textField : 'codeName'
	});
	
	//附件类型
	$("#jzAttachType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=businessType',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 是否生成预估短信
	$("#addNetValue_isOrder").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=addNetValueIsOrder',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 是否生特殊短信
	$("#addNetValue_specialType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueSpecialType',
		valueField : 'code',
		textField : 'codeName'
	});
    $('#jzSecondNetValue').validatebox({required:true});
	
	$("#netValueInfoForm").form("validate");
}
function backLisNetValuePage(){
	$('#addNetValueInfo').window('destroy');
	parent.clearForm();
}


