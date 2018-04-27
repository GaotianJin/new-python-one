function updateAdd(){
	$.post("saveUpdate_add",$("#updateAddForm").serializeArray(),function(data){
		$.messager.show({  	
			  title:'提示',  	
			  msg:data.mes,  	
			  timeout:2000,  	
			  showType:'slide'  
			}); 
		parent.clearForm();
	});
	
}