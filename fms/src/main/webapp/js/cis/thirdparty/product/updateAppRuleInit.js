//更新规则
function updateAppRule(){
		$.post("saveUpdate",$("#updateAppRuleForm").serializeArray(),function(data){
			parent.clearAppRule();
			$.messager.alert('提示',data.mes,'info');
		});
	}

