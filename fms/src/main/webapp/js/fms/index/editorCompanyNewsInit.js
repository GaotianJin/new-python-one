var custBaseInfoId=null;
var editorInfoSucFlag=null;//是否重复提交的标志

/**
 * 页面加载
 */
jQuery(function($) {
	custBaseInfoId=$("#edtiorComPolicyInfo_custBaseInfoId").val();
	//获取符文本的信息显示在编辑框
	getComPolicyEditorContext();
});

/**
 * 获取文本信息的编辑
 */
function  getComPolicyEditorContext(){
	var param={};
	param.custBaseInfoId=custBaseInfoId
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/index/getComPolicyEditorContextUrl",
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
 * 返回按钮
 */
function backListProductPage(){
	$('#addProdutctInfo').window('destroy');
	parent.clearFormInfo();
}
