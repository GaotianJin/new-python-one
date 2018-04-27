function updateSystem(){
		$.post("saveUpdate",$("#updateSystemForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info');
			parent.clearForm();
		});
		
	}
//测试
//测试2
