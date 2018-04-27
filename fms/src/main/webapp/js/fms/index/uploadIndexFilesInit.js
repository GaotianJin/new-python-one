
/**
 * 页面加载
 */
jQuery(function($) {
});

/**
 * 上传文件
 */
/*function uploadComNewsInfo(){
	uploadCompanyNewsFile('上传文件', contextPath + "/index/editorCompanyNewsUrl");
}*/

function uploadCompanyNewsFile(){
	$('<div id="editorCompanyNewsDialog"/>')
	.dialog(
			{
				href : contextPath + "/index/companyNewsEditorUrl",
				modal : true,
				fit : true,
				title : '维护公司要闻',
				inline : false,
				minimizable : false,
				onClose : function() {
					$(this).dialog('destroy');
				}
			});
}

function uploadCompanyPolicyFile(){
	$('<div id="editorCompanyPolicyDialog"/>')
	.dialog(
			{
				href : contextPath + "/index/companyPolicyEditorUrl",
				modal : true,
				fit : true,
				title : '维护公司政策',
				inline : false,
				minimizable : false,
				onClose : function() {
					$(this).dialog('destroy');
				}
			});
}

function professionUploadFile(){
	$('<div id="professionEditorFileDialog"/>')
	.dialog(
			{
				href : contextPath + "/index/proNewsEditorUrl",
				modal : true,
				fit : true,
				title : '维护产品报告',
				inline : false,
				minimizable : false,
				onClose : function() {
					$(this).dialog('destroy');
				}
			});
}