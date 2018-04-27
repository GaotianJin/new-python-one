var operate = null;
var addBuildingId=null;
jQuery(function($) {
	
	//楼盘类型
	$('#addBuildingType').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=buildingtype',
		valueField:'code',
		textField:'codeName',
		required:true,
		value:'1'
	  });
	
	//物业类型
	$('#addEstateType').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=estatetype',
		valueField:'code',
		textField:'codeName',
		required:true,
		value:'1'
	  });
	
	//接管状态
	$('#addControlState').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=controlState',
		valueField:'code',
		textField:'codeName',
		value:'01'
	  });
	//省
	$("#addProvince").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#addProvince").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#addCity").combobox('reload',url);
		}
	});
	//市
	$("#addCity").combobox({
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#addCity").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#addCountry").combobox('reload',url);
		}
	});
	//区
	$("#addCountry").combobox({
		valueField:'placeCode',
		textField:'placeName'
	});
	$("#addBuildingServiceStore").combobox({
		valueField:'id',
		textField:'name',
		url:contextPath+"/codeQuery/storeQueryOnly"
	})
	
	$('#addIsCooperation').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=isCooperation',
		valueField:'code',
		textField:'codeName'
	  });
	
	
});

function beforeSubmit(){
	if(!$("#addBuildForm").form("validate"))
	{
		return false;
	}
	return true;
}

function addBuild(){
	
	if(!beforeSubmit()){
		return;
	}
	
	var param={};
	var addBuildFormDataJson = eval("("+formDataToJsonStr($("#addBuildForm").serialize())+")");
	param.comInfo=addBuildFormDataJson;
	param.addBuildingId=addBuildingId;
	
	
	
	$.ajax({
		type:'post',
		url:'branch/addBuild',
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
			if(reData.success){
				parent.queryBuildList();
				$.messager.alert('提示', reData.msg);
				$.messager.progress('close');
				addBuildingId=reData.obj;
			}else{
				$.messager.alert('提示', reData.msg);
			}
		}
	});
}

function backListaddBuildPage(){
	$('#addBuildWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="addBuild"){
		queryBuildList();
	}
}