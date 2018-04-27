function updateXML(){
		$.post("saveUpdateXML",$("#updateXMLForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info');
		});
		
	}