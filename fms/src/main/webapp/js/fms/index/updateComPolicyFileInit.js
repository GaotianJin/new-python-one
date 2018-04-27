var companyPolicyInfoId = null;
jQuery(function($) {
	companyPolicyInfoId = $("#updComPolicy_companyPolicyInfoId").val();
	getUpdCompanyPolicyContext();
})

/**
 * 获取文本信息的编辑
 */
function  getUpdCompanyPolicyContext(){
	var param={};
	param.companyPolicyInfoId=companyPolicyInfoId;
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/index/getCompanyPolicyContextUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					var resultMap=resultInfo.obj;
					var contextHtml = resultMap.context;
					var title = resultMap.title;
					//赋值
					$("#updComPolicy_title").val(title);
					ueditor.ready(function() {
						ueditor.execCommand('insertHtml', contextHtml);
						editorInfoSucFlag = "sucFlag";
					});
					
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function updateComPolicyEditorInfo(){
	//校验必填项
	if (!$("#updComPolictUeditor").form("validate")) {
		return false;
	}
	var param={};
	var title=$("#updComPolicy_title").val();
//	var content = UE.getEditor('container').getContent();
	param.title=title;
	param.editorInfoSucFlag=editorInfoSucFlag;
	param.companyPolicyInfoId=companyPolicyInfoId;
	console.info(param);
	//保存基本信息
	var formData=$("#updComPolictUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath + "/index/updateComPolicyInfo",
		data : formData+'&param='+$.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					editorInfoSucFlag=resultInfo.obj;
					$.messager.alert('提示', resultInfo.msg);
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


function back(){
	$('#updCompanyPolicyDialog').window('destroy');
	initCompanyPolicyEditorTable();
	initListCompanyPolicyTable();
}
