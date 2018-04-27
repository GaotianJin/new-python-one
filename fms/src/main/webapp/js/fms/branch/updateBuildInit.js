jQuery(function($){	
	initAllCombobox();
	
	var rows = $('#buildTable').datagrid('getSelections');
	var ps = "";
	$.each(rows, function(i, n) {
		if (i == 0)
			ps += "?buildingId=" + n.buildingId;
	});
	
	$.post(contextPath+'/branch/updateBuildInitUrl' + ps, function(data) {
		var cityUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+data.def.province;
		$("#city").combobox('reload',cityUrl);
		var countryUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+data.def.city;
		$("#country").combobox('reload',countryUrl);
		
		setInputValueById("build",data.def);
	});
	
});


function initAllCombobox(){
	//楼盘类型
	$('#updBuildingType').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=buildingtype',
		valueField:'code',
		textField:'codeName'
	  });
	
	//物业类型
	$('#updEstateType').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=estatetype',
		valueField:'code',
		textField:'codeName'
	  });
	//接管状态
	$('#updControlState').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=controlState',
		valueField:'code',
		textField:'codeName'
	  });
	//省
	$("#province").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#province").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#city").combobox('reload',url);
		}
	});
	//市
	$("#city").combobox({
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#city").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#country").combobox('reload',url);
		}
	});
	//区
	$("#country").combobox({
		valueField:'placeCode',
		textField:'placeName'
	});
	
	$("#updBuildingServiceStore").combobox({
		valueField:'id',
		textField:'name',
		url:contextPath+"/codeQuery/storeQueryOnly"
	})
	
	$('#updIsCooperation').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=isCooperation',
		valueField:'code',
		textField:'codeName'
	  });
}

function beforeSubmit()
{
	if(!$("#updateBuildForm").form("validate"))
	{
		return false;
	}
	return true;
}
/**
 * 修改楼盘信息，表单和gridTable序列化
 */
function updateBuildInfo(){
	var param = {};
	var BuildBaisicInfoFormDataJson = formDataToJsonStr($("#updateBuildForm").serialize());
	param.buildingInfo = BuildBaisicInfoFormDataJson;
	if(!beforeSubmit())
	{
		return;
	}
	
	$.messager.confirm('提示', '确定要修改吗?', function(result) {
	
	if(result){
		$.post(contextPath+'/branch/saveUpdateBuildUrl', 'param='+encodeURI($.toJSON(param)), function(reData){
			try {
				if(reData.success){
					parent.queryBuildList();
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

function backListupdateBuildPage(){
	$('#addBuildWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="updateBuild"){
		queryBuildList();
	}
}