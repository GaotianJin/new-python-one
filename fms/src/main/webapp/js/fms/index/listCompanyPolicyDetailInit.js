var companyPolicyInfoId = null;
jQuery(function($) {
	companyPolicyInfoId = $("#detailUploadFile_companyPolicyInfoId").val();
	getCompanyPolicyContext();
})

/**
 * 获取文本信息的编辑
 */
function  getCompanyPolicyContext(){
	var param={};
	param.companyPolicyInfoId=companyPolicyInfoId;
	//保存基本信息
	$.ajax({
		type : 'post',
		url : contextPath + "/index/getCompanyPolicyContextUrl",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					var resultMap=resultInfo.obj;
					var contextHtml = resultMap.context;
					var title = resultMap.title;
					//赋值
					$("#policyDetailUploadFile_title").html(title);
					$("#policyDetailUploadFile_content").html(contextHtml);
					
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}

