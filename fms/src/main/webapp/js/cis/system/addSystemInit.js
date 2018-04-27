// 增加用户
	function addSystem() {
		$.post("saveAdd", $("#addSystemForm").serializeArray(), function(data) {
			$.messager.alert('提示', data.mes, 'info');
			parent.clearForm();
		});
	}