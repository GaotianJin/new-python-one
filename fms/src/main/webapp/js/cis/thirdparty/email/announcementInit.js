jQuery(function($) {
	
	$('#phone').combobox({
		 url:'queryPhoneComboxList_ann',   
		 valueField:'phone',   
		 textField:'phone'
	})
	
	$('#regname').combobox({
		 required:true,
		 url:'queryEmailComboxList_ann',   
		 valueField:'regname',   
		 textField:'regname'
	}); 
	
//	$('#note').combobox({
//		valueField : 'note',
//		textField : 'note',
//		required : true,
//		panelHeight : 'auto',
//		delay : 500
//	});
//	
//	var opts = $('#note').combobox('options');
//	if (!opts.url) {
//		$('#note').combobox({
//			url : 'queryeamil_em'
//		});
//	}
	
});
	// 表格查询
	function searchEmail() {
		var params = $('#emailTable').datagrid('options').queryParams; // 先取得
		var fields = $('#queryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#emailTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	
	// 清空查询条件
	function clearForm_sms() {
		$('#smsForm').form('clear');
	}
	
	// 清空查询条件
	function clearForm_email() {
		$('#emailForm').form('clear');
	}
	
	function queryPhone(){
		 $('#phone').combobox({
			 url:'queryPhoneComboxList_ann',   
			 valueField:'phone',   
			 textField:'phone'
		});  
	}
	
	function queryRegname(){
		 $('#regname').combobox({
			 url:'queryEmailComboxList_ann',   
			 valueField:'regname',   
			 textField:'regname'
		});  
	}
	
	//email发送
	function send_eamil(){
		if($("#regname").val() == "" || $("#regname").val() == null)
		{
			$.post("send_eamil", $("#emailForm").serializeArray(), function(data) {
				$.messager.show({  	
					  title:'邮件发送提示',  	
					  msg:data.mes,  	
					  timeout:3000,  	
					  showType:'slide'  
					});  
			});
		}else{
//			alert("");
			return false;
		}
	}