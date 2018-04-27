var productType=null;
var productSubType=null;
var productId=null;
var editorInfoSucFlag=null;//是否重复提交的标志
var title = null;

/**
 * 页面加载
 */
jQuery(function($) {
	productType=$("#addProductEditorInfo_ProductType").val();
	productSubType=$("#addProductEditorInfo_ProductSubType").val();
	productId=$("#addProductEditorInfo_ProductId").val();
	
});

/**
 * 提交产品附文本编辑信息
 */
function addFileUploadInfo(){
	//校验必填项
	if (!$("#addFileUeditor").form("validate")) {
		return false;
	}
	var param={};
	title=$("#addUploadFile_title").val();
	var content = UE.getEditor('editor').getContent();
	param.title=title;
	param.editorInfoSucFlag=editorInfoSucFlag;
	console.info(param);
	//保存基本信息
	var formData=$("#addFileUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath + "/index/addUploadFileInfo",
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
function backListProductPage(){
	$('#uploadCompanyPolicyDialog').window('destroy');
	initCompanyPolicyEditorTable();
	initListCompanyPolicyTable();
}