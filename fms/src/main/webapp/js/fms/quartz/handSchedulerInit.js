function dealTaskNFO() {
	sAlert('正在提交数据，请您耐心等候...');
	$.post('handDealTaskNFOUrl', function(data) {
		if (data.msg == 'success') {
			cAlert();
			$.messager.alert('提示', '操作成功', 'info');
		} else {
			cAlert();
			$.messager.alert('提示', data.msg, 'info');
		}
	});
}