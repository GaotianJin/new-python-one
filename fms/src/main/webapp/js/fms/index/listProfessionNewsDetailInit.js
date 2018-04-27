var professionNewsInfoId = null;
jQuery(function($) {
	professionNewsInfoId = $("#detailUploadFile_professionNewsInfoId").val();
	getProfessionNewsContext();
})

/**
 * 获取文本信息的编辑
 */
function  getProfessionNewsContext(){
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
					$("#detailProfessionUploadFile_title").html(title);
					$("#detailProfessionUploadFile_content").html(contextHtml);
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}

