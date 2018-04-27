jQuery(function($){
	initAllCombobox();
	
	
	var rows = $('#buildTable').datagrid('getSelections');
	{var ps = "";
	$.each(rows, function(i, n) {
		if (i == 0)
			ps += "?buildingId=" + n.buildingId;
	});
	$.post(contextPath+'/branch/updateBuildInitUrl' + ps, function(data) {
		
		var cityUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+data.def.province;
		$("#city").combobox('reload',cityUrl);
		var countryUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+data.def.city;
		$("#country").combobox('reload',countryUrl);
		
		setInputValueById("detBuild",data.def);
	});
	}
	
	
});

function initAllCombobox(){
	
	$("#detBuildingType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=buildingtype',
		valueField:'code',
		textField:'codeName'
	});
	
	
	$("#detEstateType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=estatetype',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#detControlState").combobox({
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
	
	$("#detailBuildingServiceStore").combobox({
		valueField:'id',
		textField:'name',
		url:contextPath+"/codeQuery/storeQueryOnly"
	});
	
	$('#detailIsCooperation').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=isCooperation',
		valueField:'code',
		textField:'codeName'
	  });
}

function backListdetailBuildPage(){
	$('#addBuildWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="detalBuild"){
		queryBuildList();
	}
}