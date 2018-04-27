// 增加备注
function addRemark() {
	if ($('#addRemarkForm').form('validate')) {
		var remark = $('#remark').text();
		if (remark == null || remark == "") {
			$.messager.alert('提示', '备注不能为空', 'info');
			return;
		}
		if (remark.length > 300) {
			$.messager.alert('提示', '备注过长，最多不能超过300字符', 'info');
			return;
		}
		var dlist = [];
		dlist.push({
			"posID" :$('#posID').val() ,
			"remark" : remark
		});
		
		$.ajax({
			url:'addPosRemarkSaveUrl',
			data:$.toJSON(dlist),
			type:'POST',
			async:false,
			contentType:'application/json;charset=utf-8', 
			success:function(data) {
			if (data.msg == 'success') {
				$.messager.alert('提示', '新增成功', 'info');
				parent.clearForm();
				$('#remark').text('');			
			} else {
				$.messager.alert('提示', data.msg, 'info');
			}
			}
		});
	}
}
