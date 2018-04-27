jQuery(function($) 
{
	//初始化下拉列表
	initAllBasicLawInfo();
});
function initAllBasicLawInfo()
{	
	$("#add_execState").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
		valueField:'code',
		textField:'codeName',
		value:1
	});
}
function addBasicLawVerisonSave()
{
	var param = {};
	//序列化基本法基本信息
	param.basicLawVersionInfo = formDataToJsonStr($("#add_basicLawVersionForm").serialize());
	
	if(!beforeSubmit())
	{
		return;
	}
	$.post("basicLaw/saveAddBasicLawVersionUrl", 'param='+$.toJSON(param), function(data) {
		$.messager.alert('提示', data.msg, 'info');
		if("true"==data.succ)
		{
			$("#add_basicLawVersionForm").form("clear");
			parent.clearForm();
		}
	});
}

function beforeSubmit()
{
	if(!$("#add_basicLawVersionForm").form("validate"))
	{
		return false;
	}

	//基本法执行开始时间和结束时间
	var startDate = $("#add_ExecStartDate").datebox("getValue");
	var endDate = $("#add_ExecEndDate").datebox("getValue");
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