//每个可编辑表格需要两个全局变量
//这两个全局变量尤为重要，每个可编辑表格都要两个，同一个页面中，有多个可编辑表格的的，不能重复，从此页面弹出的窗口页面中也不能有同名的全局变量
$(function(){
	initAllCombobox();
	
	var rows = $('#list_comTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要更新的机构");
		return;
	} 
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			if (i == 0)
				ps += "?comId=" + n.comId;
			else
				ps += "&comId=" + n.comId;
		});
		$.post(contextPath+'/branch/updateComInitUrl' + ps, function(data) {
			
			   var cityUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+data.defCom.province;
			   $("#update_city").combobox('reload',cityUrl);
			   
			   var countryUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+data.defCom.city;
			   $("#update_country").combobox('reload',countryUrl);
			   setInputValueById("update_comBasicInfoDiv",data.defCom);
			   setInputValueById("update_comBelongInfoDiv",data.defCom);
			   
			   var comGrade = $("#update_grade").combobox("getValue");
				// 01 分公司 02 总公司
				if(comGrade=='01'){
					$("#update_comBelongInfoDiv").hide();//机构归属信息隐藏
					$("#add_belongComId").combobox({required:false});
					$("#add_belongStartDate").datebox({required:false});
				}
				else
				{
					$("#update_comBelongInfoDiv").show();//机构归属信息
					$("#add_belongComId").combobox({required:true});
					$("#add_belongStartDate").datebox({required:true});
				}
		});
	};
});


function initAllCombobox()
{	
	var rows = $('#list_comTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要更新的机构");
		return;
	} 
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			ps = n.comId;
		});
		$("#update_comId").val(ps);
	}
	
	
	
	$("#update_grade").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comGrade',
		valueField:'code',
		textField:'codeName',
		onSelect : function() 
		{
			var comGrade = $("#update_grade").combobox("getValue");
			$("#update_belongComId").combobox("clear");
			$("#update_belongStartDate").datebox("clear");
			$("#update_belongEndDate").datebox("clear");
			// 01 分公司 02 总公司
			if(comGrade=='01'){
				$("#update_comBelongInfoDiv").hide();//机构归属信息隐藏
			}
			else
			{
				$("#update_comBelongInfoDiv").show();//机构归属信息
			}
		}
	});
	
	$("#update_state").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comState',
		valueField:'code',
		textField:'codeName',
		value:'01'
	});
	
	
	//省
	$("#update_province").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#update_province").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#update_city").combobox('reload',url);
		}
	});
	//市
	$("#update_city").combobox({
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#update_city").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#update_country").combobox('reload',url);
		}
	});
	//区
	$("#update_country").combobox({
		valueField:'placeCode',
		textField:'placeName'
	});
	$('#update_belongComId').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
}
/**
 * 新增机构信息，表单和gridTable序列化
 */
function updateComInfo()
{
	var param = {};
	var ComBaisicInfoFormDataJson = formDataToJsonStr($("#update_ComInfoForm").serialize());
	param.comInfo = ComBaisicInfoFormDataJson;
	if(!beforeSubmit())
	{
		return;
	}
	
	$.messager.confirm('提示', '确定要修改吗?', function(result) {
		if (result) {
			$.post("branch/saveUpdateComUrl", 'param='+encodeURI($.toJSON(param)), function(data) {
				$.messager.alert('提示', data.msg, 'info');
				$.messager.progress('close');
				parent.clearForm();
			})
		}
	})
}

function beforeSubmit()
{	
	
	var comGrade = $("#update_grade").combobox("getValue");
	if(comGrade=="01")
	{
		$('#update_belongComId').combobox({required:false});
		$('#update_belongStartDate').combobox({required:false});
	}
	if(comGrade=="02")
	{	
		
		var comId = $('#update_belongComId').combobox("getValue");
		if(comId=="" || comId==null || comId == undefined)
		{
			$('#update_belongComId').combobox({required:true});
		}
		var belongdate = $('#update_belongStartDate').combobox("getValue");
		if(belongdate=="" || belongdate==null || belongdate == undefined)
		{
			$('#update_belongStartDate').datebox({required:true});
		}
	}
	
	//开业时间和停业时间
	var startDate = $("#update_startDate").datebox("getValue");
	var endDate = $("#update_endDate").datebox("getValue");
	
	if(!$("#update_ComInfoForm").form("validate"))
	{
		return false;
	}
	
	
	if(startDate!=null && startDate!="" && startDate!=undefined &&
			endDate!=null && endDate!="" && endDate!=undefined)
	{
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
		var belongStartDate = $("#update_belongStartDate").datebox("getValue");
		var belongEndDate = $("#update_belongEndDate").datebox("getValue");
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
	return true;}

function backListComPage(){
	$('#comWindow').window('destroy');
	parent.clearComForm();
}