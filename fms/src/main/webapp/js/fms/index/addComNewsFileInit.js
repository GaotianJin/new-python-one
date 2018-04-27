var editorInfoSucFlag=null;//是否重复提交的标志
var title = null;

/**
 * 页面加载
 */
jQuery(function($) {
});

/**
 * 提交产品附文本编辑信息
 */
function addComNewsInfo(){
	//校验必填项
	if (!$("#addComNewsUeditor").form("validate")) {
		return false;
	}
	var param={};
	title=$("#addComNewsFile_title").val();
	var content = UE.getEditor('editor').getContent();
	param.title=title;
	param.editorInfoSucFlag=editorInfoSucFlag;
	console.info(param);
	//保存基本信息
	var formData=$("#addComNewsUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath + "/index/addComNewsInfo",
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
function backListComNews(){
	$('#uploadCompanyNewsDialog').window('destroy');
	initCompanyNewsEditorTable();
	initListCompanyNewsTable();
}