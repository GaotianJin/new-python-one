//理赔领号
function claimRegister() {
	if(!$('#claimRegisterForm').form('validate')){
		return;
	}
	var dlist = [];
	dlist.push({
		        "inDangerDate" : $('#inDangerDate').datebox('getValue'),
				"policyNo":$('#policyNo').val()
			});
	
	$.post("checkRegisterData?list="+ $.toJSON(dlist),  function(data) {
		if(data.mes=="checkPos"){
			$.messager.confirm('提示信息','该保单有正在处理中的保全项目，是否继续？',function(r){ 
				if (r){ 
					sAlert('正在提交数据，请您耐心等候...');
					$.post("claimRegisterSave?list="+ $.toJSON(dlist),  function(data) {
						cAlert();
						$.messager.alert('提示信息', data.mes, 'info',function(){
							parent.addtab('我的任务','nb/listTaskUrl');
							parent.deletetab('理赔领号');
						});
					});	
				} 
				else{
					clearForm();
				}
			});
		}else if(data.mes=="success"){
			sAlert('正在提交数据，请您耐心等候...');
			$.post("claimRegisterSave?list="+ $.toJSON(dlist),  function(data) {
				cAlert();
				$.messager.alert('提示信息', data.mes, 'info',function(){
					parent.addtab('我的任务','nb/listTaskUrl');
					parent.deletetab('理赔领号');
				});
			});	
		}else{
			$.messager.alert('提示信息', data.mes, 'info');
		}

	});	


}
	
//清空页面
function clearForm() {
	$('#claimRegisterForm').form('clear');
}
	
