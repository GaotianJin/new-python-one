var agentImageUrl = "";
var custOperate = "";
jQuery(function($) {
	//$("#title2").parent(".panel").css("display", "none");
	agentImageUrl = $("#uploadImage_agentImage").val();
	if(agentImageUrl!=null&&agentImageUrl!=""&&agentImageUrl!=undefined){
		$("#uploadImage_agentImageUrl").attr('src',agentImageUrl); 
	}
	
	custOperate = $("#uploadImage_agentImageOperate").val();
	
	if(custOperate!=null&&custOperate!=""&&custOperate!=undefined&&custOperate=="agentDetail"){
		$("#uploadFile_fileFormTableDiv").css("display","none");
	}
});

function uploadAgentImage(operate){
	var agentId = $("#uploadImage_agentId").val();
	var agentImage = $("#uploadFile_agentImage").val();
	if(agentImage==null||agentImage==""||agentImage==undefined){
		$.messager.alert('提示', "请选择需要上传的照片", 'info');
		return;
	}
	//判断此文件是否已经上传
	var param = {};
	param.agentId = agentId;
	param.fileType = "03";
	param.operate = operate;
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/sales/uploadAgentImage?param="+$.toJSON(param),
		fileElementId:'uploadFile_agentImage', 
		dataType:'json',
		success:function(reData,status){
			reData = $.parseJSON(reData)
			try {
				if(reData.success){
					//clearForm();
					//此处刷新img标签
					//alert(reData.obj);
					if(reData.obj!=null&&reData.obj!=""&&reData.obj!=undefined){
						$("#uploadImage_agentImageUrl").attr('src',reData.obj); 
					}
					if(operate!=null&&operate!=""&&operate!=undefined&&operate=="upload"){
						$.messager.alert('提示', "上传成功！");
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 清空上传form
 * 
 **/
function clearForm(){
	$("#uploadFile_agentImage").val();
}