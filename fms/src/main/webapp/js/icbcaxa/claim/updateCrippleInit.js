jQuery(function($) {
	   
		$('#crippleClass').combobox({
			  url:'../claim/queryCrippleClassList',
			  valueField:'id',
			  textField:'name',
			  onLoadSuccess:function(){
				 if($('#crippleClassName').val()!=null && $('#crippleClassName').val()!=""){
					 var crippleClassCode=$('#crippleClassName').val();
					 crippleClassCode=crippleClassCode.substring(0,crippleClassCode.indexOf("-"));
				     $('#crippleClass').combobox('select',crippleClassCode);
				     $('#crippleClassName').val("");
				 }
			  },
			  onSelect: function(){ 
				  $('#crippleClass').combobox('getText');
				  var crippleCode=$('#crippleClass').combobox('getText');
				  var index=crippleCode.indexOf("-");
				  var crippleCode=crippleCode.substring(0,index);
                  var url = '../claim/queryCrippleSubClassList?crippleCode='+crippleCode;
    			  $('#crippleSubClass').combobox('clear');
    			  $('#crippleDesc').combobox('clear');
				  $('#crippleSubClass').combobox('reload', url); 
				  }

				  
		});
		var tcrippleClass = $('#crippleClass').combobox('getText');
		$('#crippleSubClass').combobox({
			  url:'../claim/queryCrippleSubClassList?crippleCode='+tcrippleClass,
			  valueField:'id',
			  textField:'name',	
			  onLoadSuccess:function(){
				 if($('#crippleSubClassName').val()!=null && $('#crippleSubClassName').val()!=""){
				 var crippleSubClassCode=$('#crippleSubClassName').val();
				 crippleSubClassCode=crippleSubClassCode.substring(0,crippleSubClassCode.indexOf("-"));
				 $('#crippleSubClass').combobox('select',crippleSubClassCode);
				 $('#crippleSubClassName').val("");
				 }
			  },
		      onSelect: function(){ 
				  var crippleCode=$('#crippleSubClass').combobox('getText');
				  var index=crippleCode.indexOf("-");
				  var crippleCode=crippleCode.substring(0,index);
                  var url = '../claim/queryCrippleDescList?crippleCode='+crippleCode;
                  $('#crippleDesc').combobox('clear');
				  $('#crippleDesc').combobox('reload', url); 
				  }
		});
		tcrippleClass = $('#crippleSubClass').combobox('getText');
		$('#crippleDesc').combobox({
			  url:'../claim/queryCrippleDescList?crippleCode='+tcrippleClass,
			  valueField:'id',
			  textField:'name',
			  onLoadSuccess:function(){
				  if($('#crippleDescName').val()!=null && $('#crippleDescName').val()!=""){
					var crippleDescCode=$('#crippleDescName').val();
					crippleDescCode=crippleDescCode.substring(0,crippleDescCode.indexOf("-"));
				    $('#crippleDesc').combobox('select',crippleDescCode);
				    $('#crippleDescName').val("");
				  }
			  },
		      onSelect: function(){ 
				  var crippleCode=$('#crippleDesc').combobox('getText');
				  var index=crippleCode.lastIndexOf("-");
				  var crippleGrade=crippleCode.substring(index+1,crippleCode.length);
				  $('#crippleGrade').val(crippleGrade); 
				  }
		});
		
	});

//更新伤残等级
function updateCripple() {
/*	if(!$('#updateCrippleForm').form('validate')){
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
	$.post("updateCripple?list="+ $.toJSON(dlist),  function(data) {
		$.messager.alert('更新成功', data.mes, 'info',function(){
			parent.searchCrippleInfo();
			parent.deleteCrippleTab('更新伤残等级');
		});
	});	
}
	
	
