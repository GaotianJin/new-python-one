jQuery(function($) {
 //alert("1");
	$('#makedate').datebox({ // 日期选择框
		required : true
	 
	});
	
	//alert("2");
	$('#job_name').combobox({
		required : true,
		url : 'queryJob',
		valueField : 'job_name',
		textField : 'job_name',
		editable : false
	});
	
	//alert("3");
});

function addBaseTask() {
	$.post("saveAddBaseTask", $("#BaseTaskForm").serializeArray(),
			function(data) {
				$.messager.alert('提示', data.mes, 'info');
				parent.clearForm();
			});
}