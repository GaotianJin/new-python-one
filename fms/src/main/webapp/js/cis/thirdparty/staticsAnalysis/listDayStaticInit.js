jQuery(function($) {
	 
	$('#month').combobox({
		url : '../ldcode/queryLdCode',
		valueField : 'Code',
		textField : 'Codename',
		onBeforeLoad : function(param) {
			param.codetype = 'month';// 参数:CodeType值.
		}
	});
 
	$('#salechnlname').combobox({
		url : 'querySalechnlComboxList',
		valueField : 'id',
		textField : 'salechnlname'

	});
	
});

function selectDayStatic() {
	
	var salechnlname =$('#salechnlname').combobox('getValues');
	
	 
	queryForm.submit();
}

