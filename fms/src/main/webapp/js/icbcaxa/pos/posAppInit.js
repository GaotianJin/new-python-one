function confirm() 
{
	if($('#posForm').form('validate'))
	{	
		var postype = $('#postype').combobox('getValue');
		var postypename = $('#postype').combobox('getText');
		var appdate = $('#appdate').val();
		var dlist = [];	
		var elist = [];
		dlist.push({
					"policyno" :$('#policyno').val() ,
					"appdate" : appdate,
					"postype" : postype,
					"postypename":postypename
				});
		if(!isDateTime.call(appdate,'yyyy-MM-dd')){
			$.messager.alert('提示', '申请日期格式不正确！', 'info');
			return;
		}
		
		var postFlag = false;	
		
		sAlert('正在提交数据，请您耐心等候...');
		$.post('checkClaimStatus?policyno='+$('#policyno').val(), function(data) {
			if(data.result=='1'){
				cAlert();
				$.messager.confirm('Confirm', '该保单有正在进行的理赔任务，是否继续当前保全操作？', function(r) {
					if (r) {
						sAlert('正在提交数据，请您耐心等候...');
						$.post('posApplyUrl?list='+ $.toJSON(dlist), function(data) {
						if(data.msg=='success'){
							cAlert();
							parent.addtab('保全处理','pos/getPosPageUrl?id='+data.posID+'&flag=');
							parent.deletetab('保全申请');
						}else{
							cAlert();
							$.messager.alert('提示', data.msg, 'info');
						}
					});}});
			} else{
				$.post('posApplyUrl?list='+ $.toJSON(dlist), function(data) {
					if(data.msg=='success'){
						cAlert();
						parent.addtab('保全处理','pos/getPosPageUrl?id='+data.posID+'&flag=');
						parent.deletetab('保全申请');
					}else{
						cAlert();
						$.messager.alert('提示', data.msg, 'info');
					}
				});}
			
		});
	}
}
function policyQuery(){
	var policyNo = $.trim($('#policyno').val());
	if(policyNo!=null && policyNo!="" &&policyNo!="null"){
		$.post('../nb/policyQuery?policyNo='+policyNo,function(data){
			if(data.msg =='successed'){
				parent.addtab('保单查询','nb/policyDetailUrl?queryNo='+policyNo+'&queryFlag=C');
			} else {
				$.messager.alert('提示', data.msg, 'info');

			}
		});	
	} else{
		parent.addtab('保单查询','nb/policyQueryUrl');
	}
}
