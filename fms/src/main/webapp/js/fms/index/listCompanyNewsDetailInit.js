var companyNewsInfoId = null;
jQuery(function($) {
	companyNewsInfoId = $("#detailUploadFile_companyNewsInfoId").val();
	getCompanyNewsContext();
	console.info(companyNewsInfoId);
})

/**
 * 获取文本信息的编辑
 */
function  getCompanyNewsContext(){
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
					$("#newsDetailUploadFile_title").html(title);
					$("#newsDetailUploadFile_content").html(contextHtml);
					
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}

