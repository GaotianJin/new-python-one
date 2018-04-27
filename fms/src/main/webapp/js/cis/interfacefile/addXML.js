function addXML(){
		$.post("saveAddXML",$("#addXMLForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info');
			//parent.clearXML();
		});
	}