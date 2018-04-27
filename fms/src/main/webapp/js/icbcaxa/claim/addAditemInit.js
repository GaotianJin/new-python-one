//新增增减项
function addAdditem() {
/*	if(!$('#addAditemForm').form('validate')){
		return;
	}*/
	
	var aditemName=$('#aditemName').val();
	if(aditemName==""||aditemName==null){
		$.messager.alert('提示信息','请输入增减项名称','info');
		return;
	}
	
	var aditemMoney=$('#aditemMoney').val();
	if(aditemMoney==""||aditemMoney==null){
		$.messager.alert('提示信息','请输入金额','info');
		return;
	}
	
	
	
	$.post("saveAditem", $("#addAditemForm").serializeArray(), function(data) {
		$.messager.alert('保存成功', data.mes, 'info',function(){
			parent.searchAditemInfo();
			parent.deleteAditemTab('新增增减项');
		});
	});
	
	
	
}
	
//清空页面
function clearForm() {
	var id=$('#id').val();
	$('#addAditemForm').form('clear');
	  $('#id').val(id);
}
	
