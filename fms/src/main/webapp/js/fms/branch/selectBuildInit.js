jQuery(function($) {
	$('#buildingType').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#buildingType').combobox('getData');
			  if(data.length>0){
				  $('#buildingType').combobox('select',data[0].id);
			  }
		  }
	  });
	
	$('#province').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#province').combobox('getData');
			  if(data.length>0){
				  $('#province').combobox('select',data[0].id);
			  }
		  }
	  });
	
	$('#city').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#city').combobox('getData');
			  if(data.length>0){
				  $('#city').combobox('select',data[0].id);
			  }
		  }
	  });
	
	$('#country').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#country').combobox('getData');
			  if(data.length>0){
				  $('#country').combobox('select',data[0].id);
			  }
		  }
	  });
	
	$('#estateType').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#estateType').combobox('getData');
			  if(data.length>0){
				  $('#estateType').combobox('select',data[0].id);
			  }
		  }
	  });
});