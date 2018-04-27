/**
 * 页面加载
 */
jQuery(function($) {
	initlistSelfIntroductionText();
});

/**
 * 初始化自我介绍信息
 */
function initlistSelfIntroductionText(){
	var param = {};
	param.userId = userId;
		$.ajax({
			type : 'post',
			url : contextPath + "/index/querySelfIntroductionText",
			data : 'param=' +encodeURI($.toJSON(param)),
			cache : false,
			success : function(resultInfo) {
				try {
					if (resultInfo.success) {
					    $('#listSelfIntroduction').val(resultInfo.obj);
					} else {
						$.messager.alert('提示', resultInfo.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
			}
		})
	
};

function updateSelfIntroduction(){
	var param = {};
	param.userId = userId;
	param.selfIntroduction = $('#listSelfIntroduction').val();
	//alert(param.selfIntroduction);
	$.ajax({
		type : 'post',
		url : contextPath + "/index/updateSelfIntroductionText",
		data : 'param=' +$.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg);
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	})

}