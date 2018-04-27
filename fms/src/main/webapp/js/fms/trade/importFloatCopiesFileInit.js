
jQuery(function($) {
	
});


function importFloatCopiesFile(){
	var floatCopiesFile = $("#floatCopiesFile").val();
	console.info(floatCopiesFile);
	if(floatCopiesFile==null||floatCopiesFile==""||floatCopiesFile==undefined){
		$.messager.alert('提示', "请选择需要导入的文件", 'info');
		return;
	}
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/tradeS/importFloatCopiesFile",
		fileElementId:'floatCopiesFile', 
		dataType:'json',
		success:function(reData){
			reData = $.parseJSON(reData)
			try {
				if(reData.success){
					$.messager.alert('提示', "浮动产品份额导入成功！"); 
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}