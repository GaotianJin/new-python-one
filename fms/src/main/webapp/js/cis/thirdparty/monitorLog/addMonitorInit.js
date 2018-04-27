function addMonitor(){
		$.post("saveAdd",$("#addMonitorForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info');
			parent.clearMonitor();
		});
	}