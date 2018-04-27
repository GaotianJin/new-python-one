var oldBasicLawVersionExecState;
jQuery(function($) 
{
	//初始化下拉列表
	initAllBasicLawInfo();
	
	var rows = $('#list_basicLawVersionTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个基本法版本进行修改", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个基本版本法修改", 'info');
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
			   setInputValueById("update_basicLawVersionDiv",data.basicLaw);
			   oldBasicLawVersionExecState = data.basicLaw.execState;
		});
	
	}	
});
function initAllBasicLawInfo()
{	
	$("#update_execState").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
		valueField:'code',
		textField:'codeName'
	});	
}
function updateBasicLawVerisonSave()
{
	var param = {};
	//序列化基本法基本信息
	param.basicLawVersionInfo = formDataToJsonStr($("#update_basicLawVersionForm").serialize());
	
	if(!beforeSubmit())
	{
		return;
	}
	
	var newBasicLawVersionExecState = $("#update_execState").combobox("getValue");
	var message = "确定要修改吗?";
	if(oldBasicLawVersionExecState!=newBasicLawVersionExecState && newBasicLawVersionExecState=="0")
	{
		message = "该版本下的所有产品基本法将全部停用，确定修改吗？"
	}
	
	$.messager.confirm('提示', message, function(result) {
		if (result) {
			$.post("basicLaw/saveUpdateBasicLawVersionUrl", 'param='+$.toJSON(param), function(data) {
				$.messager.alert('提示', data.msg, 'info');
				parent.clearForm();
			});
		}
	})
}

function beforeSubmit()
{
	if(!$("#update_basicLawVersionForm").form("validate"))
	{
		return false;
	}
	
	//基本法执行开始时间和结束时间
	var startDate = $("#update_ExecStartDate").datebox("getValue");
	var endDate = $("#update_ExecEndDate").datebox("getValue");
	if((startDate==null || startDate=="" || startDate==undefined) && 
			endDate!=null && endDate!="" && endDate!=undefined)
	{
		$.messager.alert('提示', "执行开始日期为空时，执行结束日期必须为空", 'info');
		return false;
	}
	if(startDate!=null && startDate!="" && startDate!=undefined &&
			endDate!=null && endDate!="" && endDate!=undefined)
	{
		var d1 = new Date(startDate.replace(/\-/g, "\/")); 
		var d2 = new Date(endDate.replace(/\-/g, "\/")); 

		if(d1 >=d2) 
		{
		  $.messager.alert('提示', "执行开始日期不能大于结束日期！", 'info');
		  return false; 
		}
	}
	
	return true;
}

function backListBasicLawVersionPage(){
	$('#basicLawVersionWindow').window('destroy');
	parent.clearBasicLawVersionForm();
}