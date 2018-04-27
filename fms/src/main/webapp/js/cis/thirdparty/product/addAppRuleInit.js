// 增加规则
function addAppRule(){
		$.post("saveAdd", $("#addAppRuleForm").serializeArray(), function(data) {
			parent.clearAppRule();
			$.messager.alert('提示', data.mes, 'info');
		});
	}