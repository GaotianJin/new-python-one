var productType=null;
var productSubType=null;
var productId=null;
var editorInfoSucFlag=null;//是否重复提交的标志

/**
 * 页面加载
 */
jQuery(function($) {
	productType=$("#updateProductEdtiorInfo_ProductType").val();
	productSubType=$("#updateProductEdtiorInfo_ProductSubType").val();
	productId=$("#updateProductEdtiorInfo_ProductId").val();
	//获取符文本的信息显示在编辑框
	getProductEditorContext();
});

/**
 * 获取文本信息的编辑
 */
function  getProductEditorContext(){
	var param={};
	param.productId=productId
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/product/getProductEditorContextUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					var contextHtml=resultInfo.obj;
	/*				ueditor.addListener("ready", function () {  
						alert(contextHtml);
						// editor准备好之后才可以使用        ueditor.setContent('abc');});
						ueditor.execCommand('insertHtml', contextHtml);
						editorInfoSucFlag = "sucFlag";
					});*/
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


/**
 * 提交修改产品附文本编辑信息
 */
function updateProductEditorInfo(){
	
	
	/*$("#updatePdUeditor").live('submit',function(){ 
		var url = contextPath + "/product/addProductUeditorInfoUrl1";
		var formData=$("#updatePdUeditor").serialize();    
		$.post(url,formData,function(data){	
			alert("11111");   
		})
	});*/
	
	
	
	var param={};
	//var content = UE.getEditor('container').getContent();
	//param.htmlContext=content;
	param.productId=productId;
	param.editorInfoSucFlag=editorInfoSucFlag;
//	console.info(param);
	//保存基本信息
	var formData=$("#updatePdUeditor").serialize();
	$.ajax({
		type : 'post',
		url : contextPath + "/product/addProductUeditorInfoUrl",
		data : formData+"&param="+$.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					editorInfoSucFlag=resultInfo.obj;
					$.messager.alert('提示', resultInfo.msg);
				} else {
					$.messager.alert('提示', resultInfo.msg);
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
