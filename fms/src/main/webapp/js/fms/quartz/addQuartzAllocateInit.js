jQuery(function($) {
 
	$('#startdate').datebox({ // 日期选择框
		required : true
	 
	});
	
//alert("2");
	$('#enddate').datebox({ // 日期选择框
		required : true
	 
	});
	
	$('#schedulername1').combobox({
		 
		url : 'queryJobTnameCombox',
		valueField:'id',
		textField:'schedulername',
		editable : false,
		onSelect : function() {
			queryQuartz();
		}
	});
	
	$('#schedulername2').combobox({
		 
		url : 'queryJobTnameCombox',
		valueField:'id',
		textField:'schedulername',
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
//下拉查询
function queryQuartz() {
	alert("2323");
	$.post("queryQuartzByJobname", $("#SchedulerAllocateForm").serializeArray(), function(
			data) {

		$('#transname').attr("value", data.transname);
	});
}