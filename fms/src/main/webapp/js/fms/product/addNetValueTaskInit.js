// 新增完成之后，返回的净值信息流水号变量
var pdwealthNetValueId = null;
var addnetValueTaskId = null;
var addAngecyComName = null;
var addProductName = null;
var addProductId = null;
$(function(){
	initAllCombobox();
	addnetValueTaskId = $('#addnetValueTaskId').val();
	addAngecyComName = $('#addAgencyComName').val();
	addProductName = $('#addProductName').val();
	addProductId = $('#addProductId').val();
})
$('#netValueTaskAgentComId').val(addAngecyComName);
$('#netValueTaskProductId').val(addProductName);

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	// 净值类型
	$("#netValueTaskType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueType',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 是否生成预估短信
	$("#addNetValueTask_isOrder").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=addNetValueIsOrder',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 是否生特殊短信
	$("#addNetValueTask_specialType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueSpecialType',
		valueField : 'code',
		textField : 'codeName'
	});
    $('#netValueTask').validatebox({required:true});
	
	$("#addNetValueTaskInfoForm").form("validate");
}

//提交
function submit() {
	//产品基本信息
	if(!$("#addNetValueTaskInfoForm").form("validate")) {
		$.messager.alert("提示","请输入必录项！","info");
		return false;
	}
	var param = {};
	var netValueInfoJson = formDataToJsonStr($("#addNetValueTaskInfoForm").serialize());
	param.netValueBaseInfo = netValueInfoJson;
	param.pdwealthNetValueId = pdwealthNetValueId;
	param.netValueTaskId = addnetValueTaskId;
	param.productId = addProductId;
	$.ajax({
		type : 'post',
		url : contextPath + "/product/addNetValueTaskAndFileInfoUrl",
		data : "param="+$.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if(resultInfo.success) {
					pdwealthNetValueId=resultInfo.obj;
					$.messager.alert("提示",resultInfo.msg,"info");
					$.messager.progress('close');
				}else {
					$.messager.alert("提示",resultInfo.msg)
				}
			} catch(e) {
				$.messager.alert("提示",e)
			}
			$('#submitButton').linkbutton('disable');
		}
	})
	
}
function backNetValueTaskPage(){
	$('#addWindow').window('destroy');
	parent.clearValueTaskForm();
}