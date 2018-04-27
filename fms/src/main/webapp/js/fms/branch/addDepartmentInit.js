var operate=null;
var addDepartmentId=null;
jQuery(function($) {
	//上级机构类型
	$('#addParentComCode').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
	//业务部类型
	$('#addstate').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=departmentState',
		valueField:'code',
		textField:'codeName',
		value:'1'
	  });
});

function beforeSubmit(){
	
	var startDate = $("#addfoundDate").datebox("getValue");
	var endDate = $("#addendDate").datebox("getValue");
	//首先判断业务部代码，业务部名称，业务部状态，业务部归属开始时间不能为空
	//判断开始时间和结束时间的关系
	if(startDate!=null && startDate!="" && startDate!=undefined &&
			endDate!=null && endDate!="" && endDate!=undefined)
	{
		var d1 = new Date(startDate.replace(/\-/g, "\/")); 
		var d2 = new Date(endDate.replace(/\-/g, "\/")); 
		if(d1 >d2) 
		{
		  $.messager.alert('提示', "成立日期不能大于结束日期！", 'info');
		  return false; 
		}
	}
	
	
	var belongEndDate = $("#addbelongEndDate").datebox("getValue");
	var belongStartDate = $("#addbelongStartDate").datebox("getValue");
	if(belongEndDate!=null && belongEndDate!="" && belongEndDate!=undefined)
	{
		var d1 = new Date(belongStartDate.replace(/\-/g, "\/")); 
		var d2 = new Date(belongEndDate.replace(/\-/g, "\/")); 
		if(d1 >d2) 
		{
		  $.messager.alert('提示', "归属开始时间不能大于结束时间！", 'info');
		  return false; 
		}
	}
	
	if(!$("#addDepartmentForm").form("validate"))
	{
		return false;
	}
	return true;
}

function addDepartment(){
	var param={};
	
	if(!beforeSubmit()){
		return;
	}
	
	param.comInfo = formDataToJsonStr($("#addDepartmentForm").serialize());
	param.addDepartmentId=addDepartmentId;
	$.ajax({
		type:'post',
		url:'branch/addDepartment',
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
				if(reData.success){
					parent.searchDepartment();
					$.messager.alert('提示', reData.msg);
					$.messager.progress('close');
					addDepartmentId=reData.obj;
				}else{
					$.messager.alert('提示', reData.msg);
				}
			}
	});
}

function backListaddDepartmentPage(){
	$('#addDepartmentWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="addDepartment"){
		searchDepartment();
	}
}