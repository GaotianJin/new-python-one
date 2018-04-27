jQuery(function($) {
	var mes=$('#mes').val();
	var operateType=$('#operateType').val();
	if(mes!=null&&mes!=""){
		$.messager.alert('提示信息',mes,'info',function(){
			parent.addtab('我的任务','nb/listTaskUrl');
			if(operateType=="check"){
				parent.deletetab('理赔初审');	
			}else if(operateType=="approve"){
				parent.deletetab('理赔复核');
			}

		})
	}
});
	
	
