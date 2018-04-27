var productType=null;
var productSubType=null;
var productId=null;
var editorInfoSucFlag=null;//是否重复提交的标志

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
function addProductEditorInfo(){
	var param={};
	var content = UE.getEditor('editor').getContent();
//	param.htmlContext=content;
	param.productId=productId;
	param.editorInfoSucFlag=editorInfoSucFlag;
	//保存基本信息
	var formData=$("#addPdUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath + "/product/addProductUeditorInfoUrl",
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
	$('#addProdutctInfo').window('destroy');
	parent.clearFormInfo();
}