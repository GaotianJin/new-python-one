//每个可编辑表格需要两个全局变量
//这两个全局变量尤为重要，每个可编辑表格都要两个，同一个页面中，有多个可编辑表格的的，不能重复，从此页面弹出的窗口页面中也不能有同名的全局变量

$(function(){	
	$("#add_grade").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comGrade',
		valueField:'code',
		textField:'codeName',
		onSelect : function() 
		{
			var comGrade = $("#add_grade").combobox("getValue");
			$("#add_belongComId").combobox("clear");
			$("#add_belongStartDate").datebox("clear");
			$("#add_belongEndDate").datebox("clear");
			// 01 分公司 02 总公司
			if(comGrade=='01'){
				$("#add_comBelongInfoDiv").hide();//机构归属信息隐藏
				$("#add_belongComId").combobox({required:false});
				$("#add_belongStartDate").datebox({required:false});
			}
			else
			{
				$("#add_comBelongInfoDiv").show();//机构归属信息
				$("#add_belongComId").combobox({required:true});
				$("#add_belongStartDate").datebox({required:true});
			}
		}
		
	});
	
	$("#add_state").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comState',
		valueField:'code',
		textField:'codeName',
		value:'01'
	});
	
	//省
	$("#add_province").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#add_province").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#add_city").combobox('reload',url);
		}
	});
	//市
	$("#add_city").combobox({
		//url:contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode=',
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#add_city").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#add_country").combobox('reload',url);
		}
	});
	//区
	$("#add_country").combobox({
		//url:contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode=''',
		valueField:'placeCode',
		textField:'placeName'
	});
	$('#add_belongComId').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
});

/**
 * 新增机构信息，表单和gridTable序列化
 */
function addComInfo()
{
	var param = {};
	var ComBaisicInfoFormDataJson = formDataToJsonStr($("#add_ComInfoForm").serialize());
	param.comInfo = ComBaisicInfoFormDataJson;
	
//	alert("开始提交");
	if(!beforeSubmit())
	{
		return;
	}
//	data : 'param=' +encodeURI($.toJSON(param)),
	$.post("branch/saveAddComUrl", 'param='+encodeURI($.toJSON(param)), function(data) {
		$.messager.alert('提示', data.msg, 'info');
		if("true"==data.succ)
		{
			$("#add_ComInfoForm").form("clear");
			parent.clearForm();
		}
		parent.clearForm();
	});	
};

function beforeSubmit()
{	
	
	var comGrade = $("#add_grade").combobox("getValue");
	if(comGrade=="01")
	{
		$('#add_belongComId').combobox({required:false});
		$('#add_belongStartDate').combobox({required:false});
	}
	if(comGrade=="02")
	{	
		
		var comId = $('#add_belongComId').combobox("getValue");
		if(comId=="" || comId==null || comId == undefined)
		{
			$('#add_belongComId').combobox({required:true});
		}
		var belongdate = $('#add_belongStartDate').combobox("getValue");
		if(belongdate=="" || belongdate==null || belongdate == undefined)
		{
			$('#add_belongStartDate').datebox({required:true});
		}
	}
	
	//开业时间和停业时间
	var startDate = $("#add_startDate").datebox("getValue");
	var endDate = $("#add_endDate").datebox("getValue");
//	alert("startDate:"+startDate);
//	alert("endDate:"+endDate);
	
//	if((startDate==null || startDate=="" || startDate==undefined) && 
//			endDate!=null && endDate!="" && endDate!=undefined)
//	{
////		$('#add_startDate').datebox({required:true});
//	}
//	else
//	{
////		$('#add_startDate').datebox({required:false});
//	}
	
	
	if(!$("#add_ComInfoForm").form("validate"))
	{
		return false;
	}
	
	
	if(startDate!=null && startDate!="" && startDate!=undefined &&
			endDate!=null && endDate!="" && endDate!=undefined)
	{
//		alert("时间比较出来了");
		var d1 = new Date(startDate.replace(/\-/g, "\/")); 
		var d2 = new Date(endDate.replace(/\-/g, "\/")); 

		if(d1 >d2) 
		{
		  $.messager.alert('提示', "开业时间不能大于停业时间！", 'info');
		  return false; 
		}
	}
	
	// 01 分公司 02 总公司
	if(comGrade=='02')
	{
		var belongStartDate = $("#add_belongStartDate").datebox("getValue");
		var belongEndDate = $("#add_belongEndDate").datebox("getValue");
		if(belongStartDate!=null && belongStartDate!="" && belongStartDate!=undefined &&
			belongEndDate!=null && belongEndDate!="" && belongEndDate!=undefined)
		{
			var d1 = new Date(belongStartDate.replace(/\-/g, "\/")); 
			var d2 = new Date(belongEndDate.replace(/\-/g, "\/")); 
	
			if(d1 >d2)
			{
			  $.messager.alert('提示', "归属开始时间不能大于结束时间！", 'info');
			  return false; 
			}
		}
	}
	return true;
}

function backListComPage(){
	$('#comWindow').window('destroy');
	parent.clearComForm();
}