function updateMonitor(){
		$.post("saveUpdate",$("#updateMonitorForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info');
			parent.clearMonitor();
		});
		
	}