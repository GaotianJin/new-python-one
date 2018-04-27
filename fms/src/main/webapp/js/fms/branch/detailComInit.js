jQuery(function($) 
{
	var rows = $('#list_comTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要查看的机构");
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
			   $("#detail_city").combobox('reload',cityUrl);
			   var countryUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+data.defCom.city;
			   $("#detail_country").combobox('reload',countryUrl);
			   setInputValueById("detail_comBasicInfoDiv",data.defCom);
			   if(data.defCom.grade=="01"){
				$("#detail_comBelongInfoDiv").hide(); 
				   
			   }
			   else{
			    $("#detail_comBelongInfoDiv").show();   
			    setInputValueById("detail_comBelongInfoDiv",data.defCom);  
			   }
			   
		});
	};

	initAllCombobox();
});

function initAllCombobox()
{	
	
	$("#detail_type").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=storetype',
		valueField:'code',
		textField:'codeName',
		value:2
	});
	$("#detail_grade").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comGrade',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#detail_state").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comState',
		valueField:'code',
		textField:'codeName'
	});
	
	//省
	$("#detail_province").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#detail_province").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#detail_city").combobox('reload',url);
		}
	});
	//市
	$("#detail_city").combobox({
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#detail_city").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#country").combobox('reload',url);
		}
	});
	//区
	$("#detail_country").combobox({
		valueField:'placeCode',
		textField:'placeName'
	});
	
	//归属机构
	$('#detail_belongComId').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
}

function backListComPage  (){
	$('#comWindow').window('destroy');
	parent.clearComForm();
}