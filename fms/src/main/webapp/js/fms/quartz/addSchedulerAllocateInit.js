jQuery(function($) {
 //alert("1");
	$('#startdate').datebox({ // 日期选择框
		required : true
	 
	});
	
//alert("2");
	$('#enddate').datebox({ // 日期选择框
		required : true
	 
	});
	
	$('#jobtname1').combobox({
		 
		url : 'queryJobTnameCombox',
		valueField:'id',
		textField:'jobtname',
		editable : false
	});
	
	$('#jobtname2').combobox({
		 
		url : 'queryJobTnameCombox',
		valueField:'id',
		textField:'jobtname',
		editable : false
	});
});

function addSchedulerAllocate() {
	$.post("saveAddSchedulerAllocate", $("#SchedulerAllocateForm").serializeArray(),
			function(data) {
				$.messager.alert('提示', data.mes, 'info');
				parent.clearForm();
			});
}