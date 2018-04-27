//更新增减项
	function updateAditem() {
/*		if(!$('#updateAditemForm').form('validate')){
			return;
		}*/
		var aditemName=$('#aditemName').val();
		if(aditemName==""||aditemName==null){
			$.messager.alert('提示信息','请输入增减项名称','info');
			return;
		}
		
		var aditemMoney=$('#aditemMoney').val();
		if(aditemMoney==""||aditemMoney==null){
			$.messager.alert('提示信息','请输入金额','info');
			return;
		}
		$.post("updateAditem", $("#updateAditemForm").serializeArray(), function(data) {
			$.messager.alert('更新成功', data.mes, 'info',function(){
				parent.searchAditemInfo();
				parent.deleteAditemTab('更新增减项');
			});
		});
	}
	
	
