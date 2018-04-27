$(function(){
	$("#add_rolePrivilege").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=rolePrivilege',
		valueField:'code',
		textField:'codeName',
		value:'1'
	});
});


function addRole(){
		
	if(!$('#addRoleForm').form('validate'))
	{
		return;
	}
	$.post(contextPath+"/role/saveAdd",$("#addRoleForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info');
			if("true"==data.succ)
			{
				$('#addRoleForm').form("clear");
				parent.clearRole();
			}
		});
}

function backListRolePage(){
	$('#roleWindow').window('destroy');
	parent.clearRole();
}
