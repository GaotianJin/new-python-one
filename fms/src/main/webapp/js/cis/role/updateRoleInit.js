$(function(){
	
	$("#update_rolePrivilege").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=rolePrivilege',
		valueField:'code',
		textField:'codeName'
	});
});

function updateRole(){
	
	if(!$('#updateRoleForm').form('validate'))
	{
		return;
	}
	$.messager.confirm('提示', '确定要修改吗?', function(result) {
		if (result) {
			$.post("role/saveUpdate",$("#updateRoleForm").serializeArray(),function(data){
				$.messager.alert('提示',data.mes,'info');
				parent.clearRole();
			})
		}
	})
}

function backListRolePage(){
	$('#roleWindow').window('destroy');
	parent.clearRole();
}