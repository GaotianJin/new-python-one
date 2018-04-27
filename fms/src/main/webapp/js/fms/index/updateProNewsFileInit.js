var professionNewsInfoId = null;
jQuery(function($) {
	professionNewsInfoId = $("#updProNews_professionNewsInfoId").val();
	getUpdProNewsContext();
})

/**
 * 获取文本信息的编辑
 */
function  getUpdProNewsContext(){
	var param={};
	param.professionNewsInfoId=professionNewsInfoId;
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/index/getProfessionNewsContextUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					var resultMap=resultInfo.obj;
					var contextHtml = resultMap.context;
					var title = resultMap.title;
					//赋值
					$("#updProNews_title").val(title);
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

function updateProNewsEditorInfo(){
	//校验必填项
	if (!$("#updProNewsUeditor").form("validate")) {
		return false;
	}
	var param={};
	var title=$("#updProNews_title").val();
//	var content = UE.getEditor('container').getContent();
	param.title=title;
	param.editorInfoSucFlag=editorInfoSucFlag;
	param.professionNewsInfoId=professionNewsInfoId;
	console.info(param);
	//保存基本信息
	var formData=$("#updProNewsUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath + "/index/updateProNewsInfo",
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
	$('#updProNewsDialog').window('destroy');
	initProNewsEditorTable();
	initListProfessionNewsTable();
}
