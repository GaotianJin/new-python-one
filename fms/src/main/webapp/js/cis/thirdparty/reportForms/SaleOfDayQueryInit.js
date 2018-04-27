jQuery(function($) {
	
	$('#makeDate').datebox({  //日期选择框
		// required:true   //是否为录项
	});
	
	$('#salechnlcode').combobox({
		 url:'querySalechnlcodeComboxList',   
		 valueField:'salechnlcode',   
		 textField:'salechnlcode'
	});
	
	$('#riskcode').combobox({
		 url:'queryRiskcodeComboxList',   
		 valueField:'riskcode',   
		 textField:'riskcode'
	});

});

function extract()
{
	
		var url='./saleOfDay.xls';  
window.location.href = url;   
	 
}

