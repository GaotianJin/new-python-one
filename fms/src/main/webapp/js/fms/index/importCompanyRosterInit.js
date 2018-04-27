/**
 * 批量导入
 */
function importRosterFile(){
	var rosterFileName = $("#rosterFileName").val();
	if(rosterFileName==null||rosterFileName==""||rosterFileName==undefined){
		$.messager.alert('提示', "请选择需要导入的文件", 'info');
		return;
	}
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/index/importRosterDisFile",
		fileElementId:'rosterFileName', 
		dataType:'json',
		success:function(reData){
			reData = $.parseJSON(reData);
			try {
				if(reData.success){
					$.messager.alert('提示', "通讯录文件导入成功！");
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
 * 返回主页面
 */
function back(){
	$('#addCompanyRosterWindow').window('close');
	$('#companyRosterTable').datagrid('clear');
	initCompanyRosterTable();
}
