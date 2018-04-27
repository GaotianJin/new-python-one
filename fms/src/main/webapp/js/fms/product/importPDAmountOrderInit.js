
jQuery(function($) {
	
});


function importPDAmountOrderAuditFile(){
	var pdAmountOrderAuditFile = $("#pdAmountOrderAuditFile").val();
	if(pdAmountOrderAuditFile==null||pdAmountOrderAuditFile==""||pdAmountOrderAuditFile==undefined){
		$.messager.alert('提示', "请选择需要导入的文件", 'info');
		return;
	}
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/productOrder/importPDAmountOrderAuditFile",
		fileElementId:'pdAmountOrderAuditFile', 
		dataType:'json',
		success:function(reData){
			//alert("reData=="+reData);
			reData = $.parseJSON(reData)
			try {
				if(reData.success){
					$.messager.alert('提示', "产品预约审核批量维护成功！"); 
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}