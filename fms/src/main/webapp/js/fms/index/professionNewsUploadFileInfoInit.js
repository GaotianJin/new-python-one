var editorInfoSucFlag=null;//是否重复提交的标志
var title = null;

/**
 * 提交通讯录附文本编辑信息
 */
function saveFileUploadInfo(){
	//校验必填项
	if (!$("#proNewsFileUeditor").form("validate")) {
		return false;
	}
	var param={};
	title=$("#proNewsUploadFile_title").val();
	var content = UE.getEditor('editor').getContent();
	param.title=title;
	param.editorInfoSucFlag=editorInfoSucFlag;
	console.info(param);
	//保存基本信息
	var formData=$("#proNewsFileUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath +"/index/professionNewsUploadFileInfo",
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


/**
 * 返回按钮
 */
function backListPage(){
	$('#uploadProNewsDialog').window('destroy');
	initProNewsEditorTable();
	initListProfessionNewsTable();
}