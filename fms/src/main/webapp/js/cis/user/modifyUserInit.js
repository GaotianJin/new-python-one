function modifyUser() {
	var password = $("#password").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	if (password == null || password == "") {
		$.messager.alert('提示', "原始密码不能为空!", 'info');
		return false;
	}
	if (password1 == null || password1 == "") {
		$.messager.alert('提示', "新密码不能为空!", 'info');
		return false;
	}
	if (password2 == null || password2 == "") {
		$.messager.alert('提示', "确认新密码不能为空!", 'info');
		return false;
	}
	if (password1 != password2) {
		$.messager.alert('提示', "新密码和确认新密码不匹配!", 'info');
		return false;
	}
	$.post("user/modifySave?password=" + password + "&password1=" + password1,
			function(data) {
				$.messager.alert('提示', data.mes, 'info');
			});
}

function reset() {
	$('#modifyUserForm').form('clear');
}