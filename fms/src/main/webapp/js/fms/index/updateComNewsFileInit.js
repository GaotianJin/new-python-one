var companyNewsInfoId = null;
jQuery(function($) {
	companyNewsInfoId = $("#updComNews_companyNewsInfoId").val();
	getUpdCompanyNewsContext();
})

/**
 * 获取文本信息的编辑
 */
function  getUpdCompanyNewsContext(){
	var param={};
	param.companyNewsInfoId=companyNewsInfoId;
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/index/getCompanyNewsContextUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					var resultMap=resultInfo.obj;
					var contextHtml = resultMap.context;
					var title = resultMap.title;
					//赋值
					$("#updComNews__title").val(title);
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

function updateComNewsEditorInfo(){
	//校验必填项
	if (!$("#updComNewsUeditor").form("validate")) {
		return false;
	}
	var param={};
	var title=$("#updComNews__title").val();
//	var content = UE.getEditor('container').getContent();
	param.title=title;
	param.editorInfoSucFlag=editorInfoSucFlag;
	param.companyNewsInfoId=companyNewsInfoId;
	console.info(param);
	//保存基本信息
	var formData=$("#updComNewsUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath + "/index/updateComNewsInfo",
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


function backList(){
	$('#updCompanyNewsDialog').window('destroy');
	initCompanyNewsEditorTable();
	initListCompanyNewsTable();
}
