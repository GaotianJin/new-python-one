jQuery(function($) {
	//alert(2222);
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

function selectCoverAnalysis() {
	
	var salechnlname =$('#salechnlname').combobox('getValues');
	 
	var month=$('#month').combobox('getValues');
	
	 

	queryForm.submit();
	
}
