jQuery(function($) {
		$('#crippleClass').combobox({
			  url:'../claim/queryCrippleClassList',
			  valueField:'id',
			  textField:'name',
			  onSelect: function(){ 
				  $('#crippleSubClass').combobox('clear'); 
				  $('#crippleDesc').combobox('clear');
				  var crippleCode=$('#crippleClass').combobox('getValue');
                  var url = '../claim/queryCrippleSubClassList?crippleCode='+crippleCode;
				  $('#crippleSubClass').combobox('reload', url); 
				  }

				  
		});
		
		$('#crippleSubClass').combobox({
			  url:'../claim/queryCrippleSubClassList',
			  valueField:'id',
			  textField:'name',			  
		      onSelect: function(){ 
		    	  $('#crippleDesc').combobox('clear'); 
				  var crippleCode=$('#crippleSubClass').combobox('getValue');
                  var url = '../claim/queryCrippleDescList?crippleCode='+crippleCode;
				  $('#crippleDesc').combobox('reload', url); 

				  }
		});
		
		$('#crippleDesc').combobox({
			  url:'../claim/queryCrippleDescList',
			  valueField:'id',
			  textField:'name',
		      onSelect: function(){ 
				  var crippleCode=$('#crippleDesc').combobox('getText');
				  var index=crippleCode.lastIndexOf("-");
				  var crippleGrade=crippleCode.substring(index+1,crippleCode.length);
				  $('#crippleGrade').val(crippleGrade); 
				  }
		});
		
		
	});


function addCripple() {
/*	if(!$('#addCrippleForm').form('validate')){
		return;
	}*/
	var crippleClass=$('#crippleClass').combobox('getValue');
	if(crippleClass==""||crippleClass==null){
		$.messager.alert('提示信息','请选择伤残大类','info');
		return;
	}
	var crippleSubClass=$('#crippleSubClass').combobox('getValue');
	if(crippleSubClass==""||crippleSubClass==null){
		$.messager.alert('提示信息','请选择伤残子类','info');
		return;
	}
	var crippleDesc=$('#crippleDesc').combobox('getValue');
	if(crippleDesc==""||crippleDesc==null){
		$.messager.alert('提示信息','请选择伤残描述','info');
		return;
	}
	var authenticationDate=$('#authenticationDate').datebox('getValue');
	if(authenticationDate==""||authenticationDate==null){
		$.messager.alert('提示信息','请选择鉴定日期','info');
		return;
	}
	
	var crippleCode=$('#crippleDesc').combobox('getValue');
	var dlist = [];
	dlist.push({
				"id" : $('#id').val(),
				"crippleDesc":crippleCode,
				"authenticationDate" : $('#authenticationDate').datebox('getValue')

			});
	$.post("saveCripple?list="+ $.toJSON(dlist),  function(data) {
		$.messager.alert('保存成功', data.mes, 'info',function(){
			parent.searchCrippleInfo();
			parent.deleteCrippleTab('新增伤残等级');
		});
	});	
}
	
function clearCripple(){
	var id=$('#id').val();
	$('#addCrippleForm').form('clear');
    $('#id').val(id);
}
	
