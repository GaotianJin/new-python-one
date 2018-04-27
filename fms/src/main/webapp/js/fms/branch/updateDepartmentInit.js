var operate=null;
jQuery(function($) {	
	initDepartmentList();
	});
	
function initDepartmentList(){
	var rows = $('#departmentTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要修改的部门信息");
		return;
	} else{
		var ps = "";
		$.each(rows, function(i, n) {
			if (i == 0)
				ps += "?departmentId=" + n.departmentId;
		});
		$.post(contextPath+'/branch/updateDepartmentInitUrl' + ps, function(data) {
			setInputValueById("updateDepartment",data.def);
		});
	};
	initAllCombobox();
}


function initAllCombobox(){
	$("#ywbstate").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=state',
		valueField:'code',
		textField:'codeName'
	});
	
	$('#belongComName').combobox({
		 url:contextPath+"/branch/queryComListCode",
		 valueField:'id',
		 textField:'name'
	  });
}


function beforeSubmit(){	
	var startDate = $("#foundDate").datebox("getValue");
	//var endDate = $("#endDate").datebox("getValue");
	//首先判断业务部代码，业务部名称，业务部状态，业务部归属开始时间不能为空
	//判断开始时间和结束时间的关系
	if(startDate==null && startDate=="" && startDate==undefined)
	{
		/*var d1 = new Date(startDate.replace(/\-/g, "\/")); 
		var d2 = new Date(endDate.replace(/\-/g, "\/")); 
		if(d1 >d2) 
		{
		  $.messager.alert('提示', "成立日期不能大于结束日期！", 'info');
		  return false; 
		}*/
		  $.messager.alert('提示', "日期不能为空！", 'info');
		  return false;
			
	}
	
	var belongEndDate = $("#belongEndDate").datebox("getValue");
	var belongStartDate = $("#belongStartDate").datebox("getValue");
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
	
	if(!$("#updateDepartmentForm").form("validate"))
	{
		return false;
	}
	return true;
}

function updateDepartmentInfo(){
	var param = {};
	var DepartmentBaisicInfoFormDataJson = formDataToJsonStr($("#updateDepartmentForm").serialize());
	param.departmentInfo = DepartmentBaisicInfoFormDataJson;
	
	if(!beforeSubmit())
	{
		return;
	}
	$.messager.confirm('提示', '确定要修改吗?', function(result) {
		if(result){
			$.post(contextPath+'/branch/saveUpdateDepartmentUrl', 'param='+$.toJSON(param), function(reData){
				try {
					if(reData.success){
						parent.searchDepartment();
						$.messager.alert('提示', reData.msg);
					}else{
						$.messager.alert('提示', reData.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
			});	
		}
	});
}

function backListupdateDepartmentPage(){
	$('#addDepartmentWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="updateDepartment"){
		searchDepartment();
	}
}