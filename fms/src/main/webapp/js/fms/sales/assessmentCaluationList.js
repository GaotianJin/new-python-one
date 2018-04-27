jQuery(function($) {
	
	$('#Startdate').datebox({ // 日期选择框
	});
	$('#Enddate').datebox({ // 日期选择框
	});

	//所属机构
	var comCombobox=$("#assessCal_comId").combobox({
		url:contextPath+'/branch/queryComListCode',
		valueField:'id',
		textField:'name'
	});
	
	//网点
	var storeCombobox=$("#assessCal_storeId").combobox({
		url:contextPath+'/codeQuery/storeQueryOnly',
		valueField:'id',
		textField:'name'
	});
	//团队
	var storeCombobox=$("#assessCal_departmentId").combobox({
		url:contextPath+'/branch/queryDepartmentListCode',
		valueField:'id',
		textField:'name'
	});
	
	
	//财富顾问
	var storeCombobox=$("#assessCal_agentId").combobox({
		url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name'
	});
	//职级
	var storeCombobox=$("#assessCal_positionCode").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=positionCode',
		valueField:'code',
		textField:'codeName'
	});
	//级别
	var storeCombobox=$("#assessCal_gradeCode").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=gradeCode',
		valueField:'code',
		textField:'codeName'
	});

});

function assessExport(){
	alert("考核导出");
	var assessParam = $("#assessForm").serialize();
	assessParam = formDataToJsonStr(assessParam);
	var param = {};
	param.assessParam = assessParam;
	window.open(contextPath+'/sales/assessCalExport.xls?param='+$.toJSON(param));
}