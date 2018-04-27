jQuery(function($) 
{
	//初始化下拉列表
	initAllBasicLawInfo();
	
	var rows = $('#list_basicLawVersionTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个基本法版本查看", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个基本版本法查看", 'info');
		return;
	}
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			if (i == 0)
				ps += "?basicLawId=" + n.basicLawId;
			else
				ps += "&basicLawId=" + n.basicLawId;
			$("#update_basicLawId").val(n.basicLawId);
		});
		
		
		$.post(contextPath+'/basicLaw/queryBasicLawVersionInfoEntify' + ps, function(data) {
			   setInputValueById("detail_basicLawVersionDiv",data.basicLaw);
		});
	};
	
	
});
function initAllBasicLawInfo()
{	
	$("#update_execState").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
		valueField:'code',
		textField:'codeName'
	});	
}

function backListBasicLawVersionPage(){
	$('#basicLawVersionWindow').window('destroy');
	parent.clearBasicLawVersionForm();
}