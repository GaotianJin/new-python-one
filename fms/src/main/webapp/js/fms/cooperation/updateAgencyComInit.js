var modifyAgencyComRelationTableRowIndex=null;
var modifyAgencyComId=null;
$(function(){
	//获取从选中的要修改的ID
	modifyAgencyComId = $("#modifyAgencyComId").val();
	initAllCombobox();
	initModifyAgencyComRelationTable();
	getUpdateInitValue();
});
//初始需要修改的数据
function getUpdateInitValue(){
	$.ajax({
		type:'post',
		url:contextPath+"/cooperation/queryAgencyComInfo",
		data:{param:$('#modifyAgencyComId').val()},
		cache:false,
		success:function(result){
			try {
				if(result.success){
					var resultObj = result.obj;
					//1.合作机构基本信息
					//先加载市和区的下拉列表数据
					var provinceCode = resultObj.agencyComBaseInfo.province;
					var loadCityDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode;
					$("#modifyCity").combobox('reload',loadCityDataUrl);
					var cityCode = resultObj.agencyComBaseInfo.city;
					var loadCountryDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode;
					$("#modifyCountry").combobox('reload',loadCountryDataUrl);
					setInputValueById("updateAgencyComBasicInfo",resultObj.agencyComBaseInfo)
					//2.合作机构联系人信息赋值
					if(resultObj.agencyComContactsInfo!=null&&resultObj.agencyComContactsInfo!=undefined){
						loadJsonObjData("modifyAgencyComRelationTable",resultObj.agencyComContactsInfo);
						modifyAgencyComRelationTableRowIndex = null;
					}
				}else{
					$.messager.alert('提示', result.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


var modifyAgencyComRelationTable;
function initModifyAgencyComRelationTable()
{
	modifyAgencyComRelationTable=$("#modifyAgencyComRelationTable").datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns:[[{
	         field : 'ck',
	         checkbox : true,
	         width : 2
	        }, // 显示复选框
            {
	        field : 'agencyLevel',
			title : '联系人级别',
			width : 80,
			sortable : true,
			editor: {
				type:'combobox',
				options:{
					valueField:'code',
					textField:'codeName',
					url:contextPath+'/codeQuery/tdCodeQuery?codeType=relationLevel',
					onSelect:function(){
						var edAgencyLevel = modifyAgencyComRelationTable.datagrid('getEditor', {index:modifyAgencyComRelationTableRowIndex,field:'agencyLevel'});
						var edAgencyLevelName = $(edAgencyLevel.target).combobox('getText');
						modifyAgencyComRelationTable.datagrid('getRows')[modifyAgencyComRelationTableRowIndex]['agencyLevelName'] = edAgencyLevelName;
					}
				}
			},
			formatter : function(value, row, index) {
				return row.agencyLevelName;
			}
	        
		},
		{
			field : 'agencyLevelName',
			title : '联系人级别名称',
			hidden : true,
			formatter : function(value, row, index) {
				return row.agencyLevelName;
			} 
		},
		{
			field : 'contactsName',
			title : '姓名',
			width : 60,
			sortable : true,
			editor:'text',
			formatter : function(value, row, index) {
				return row.contactsName;
			}
		},
		{
			field : 'position',
			title : '职位',
			width : 80,
			sortable : true,
			editor: {
				type:'combobox',
				options:{
					valueField:'code',
					textField:'codeName',
					url:contextPath+'/codeQuery/tdCodeQuery?codeType=position',
					onSelect:function(){
						var edPosition = modifyAgencyComRelationTable.datagrid('getEditor', {index:modifyAgencyComRelationTableRowIndex,field:'position'});
						var edPositionName = $(edPosition.target).combobox('getText');
						modifyAgencyComRelationTable.datagrid('getRows')[modifyAgencyComRelationTableRowIndex]['positionName'] = edPositionName;
					}
				}
			},
			formatter : function(value, row, index) {
				return row.positionName;
			}
		},
		{
			field : 'positionName',
			title : '职位名称',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.positionName;
			}
		},
		{
			field : 'province',
			title : '省',
			width : 100,
			sortable : true,
			editor: {
				type:'combobox',
				options:{
					valueField:'placeCode',
					textField:'placeName',
					url:contextPath+'/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=',
					onSelect:function(){
						var edProvince = modifyAgencyComRelationTable.datagrid('getEditor', {index:modifyAgencyComRelationTableRowIndex,field:'province'});
						var edProvinceName = $(edProvince.target).combobox('getText');
						var edProvinceCode = $(edProvince.target).combobox('getValue');
						modifyAgencyComRelationTable.datagrid('getRows')[modifyAgencyComRelationTableRowIndex]['provinceName'] = edProvinceName;
						var edcity = modifyAgencyComRelationTable.datagrid('getEditor', {index:modifyAgencyComRelationTableRowIndex,field:'city'});
						var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+edProvinceCode;
						$(edcity.target).combobox("reload",url)
					}
				}
			},
			formatter : function(value, row, index) {
				return row.provinceName;
			}
		},
		{
			field : 'provinceName',
			title : '省名称',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.provinceName;
			}
		},
		{
			field : 'city',
			title : '市',
			width : 100,
			sortable : true,
			editor: {
				type:'combobox',
				options:{
					valueField:'placeCode',
					textField:'placeName',
					onSelect:function(){
						var edcity = modifyAgencyComRelationTable.datagrid('getEditor', {index:modifyAgencyComRelationTableRowIndex,field:'city'});
						var edcityName = $(edcity.target).combobox('getText');
						var edcityCode = $(edcity.target).combobox('getValue');
						modifyAgencyComRelationTable.datagrid('getRows')[modifyAgencyComRelationTableRowIndex]['cityName'] = edcityName;
						
						var edcountry = modifyAgencyComRelationTable.datagrid('getEditor', {index:modifyAgencyComRelationTableRowIndex,field:'country'});
						var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+edcityCode;
						$(edcountry.target).combobox("reload",url)
					}
					
				}
			},
			
			formatter : function(value, row, index) {
				return row.cityName;
			}
		},
		{
			field : 'cityName',
			title : '市编码',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.cityName;
			}
		},
		
		{
			field : 'country',
			title : '区/县',
			width : 100,
			sortable : true,
			editor: {
				type:'combobox',
				options:{
					valueField:'placeCode',
					textField:'placeName',
					onSelect:function(){

						var edcountry = modifyAgencyComRelationTable.datagrid('getEditor', {index:modifyAgencyComRelationTableRowIndex,field:'country'});
						var edcountryName = $(edcountry.target).combobox('getText');
						modifyAgencyComRelationTable.datagrid('getRows')[modifyAgencyComRelationTableRowIndex]['countryName'] = edcountryName;
					}
				}
			},
			formatter : function(value, row, index) {
				return row.countryName;
			}
		},
		{
			field : 'countryName',
			title : '区/县',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.countryName;
			}
		},
		{
			field : 'street',
			title : '街道',
			width : 250,
			sortable : true,
			editor:'text',
			formatter : function(value, row, index) {
				return row.street;
			}
		}
		,
		{
			field : 'zipCode',
			title : '邮政编码',
			width : 60,
			sortable : true,
			editor: {
				type:'validatebox',
				options:{
					validType: 'validZip'
				}
			},
			formatter : function(value, row, index) {
				return row.zipCode;
			}
		}
		,
		{
			field : 'telphone',
			title : '联系电话',
			width : 80,
			sortable : true,
			editor: {
				type:'validatebox',
				options:{
					validType: 'validTel'
				}
			},
			formatter : function(value, row, index) {
				return row.telphone;
			}
		}
		,
		{
			field : 'fax',
			title : '传真',
			width : 80,
			sortable : true,
			editor: {
				type:'validatebox',
				options:{
					validType: 'validTel'
				}
			},
			formatter : function(value, row, index) {
				return row.fax;
			}
		}
		,
		{
			field : 'mobile',
			title : '手机',
			width : 80,
			sortable : true,
			editor:'text',
			editor: {
				type:'validatebox',
				options:{
					validType: 'validPhone'
				}
			},
			formatter : function(value, row, index) {
				return row.mobile;
			}
		},
		{
			field : 'email',
			title : '邮箱',
			width : 80,
			sortable : true,
			editor:'text',
			editor: {
				type:'validatebox',
				options:{
					validType: 'email'
				}
			},
			formatter : function(value, row, index) {
				return row.email;
			}
		}
		,
		{
			field : 'webchat',
			title : '微信',
			width : 80,
			sortable : true,
			editor:'text',
			editor: {
				type:'validatebox',
				options:{
					validType: 'validCode'
				}
			},
			formatter : function(value, row, index) {
				return row.webchat;
			}
		}
		
		]],
		onLoadSuccess : function() {
			$('#modifyAgencyComRelationTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(index){
			modifyAgencyComRelationTableEditOneRow(index);
			setCityAndCountry(index);
		}
	});
}


//点击一行启动编辑后，给市和区重新赋值
function setCityAndCountry(index){
	//给市和区重新赋值
	var provinceCode = modifyAgencyComRelationTable.datagrid('getRows')[index]['province'];
	if(provinceCode!=null&&provinceCode!=''){
		var edCity = modifyAgencyComRelationTable.datagrid('getEditor', {index:index,field:'city'});
		//重新加载城市数据
		$(edCity.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode);
		//获取之前存储的城市数据
		var cityCode = modifyAgencyComRelationTable.datagrid('getRows')[index]['city'];
		if(cityCode!=null&&cityCode!=''){
			$(edCity.target).combobox('setValue',cityCode);
			//重新加载区县数据
			var edCountry = modifyAgencyComRelationTable.datagrid('getEditor', {index:index,field:'country'});
			$(edCountry.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode);
			var countryCode = modifyAgencyComRelationTable.datagrid('getRows')[index]['country'];
			if(countryCode!=null&&countryCode!=''){
				$(edCountry.target).combobox('setValue',countryCode);
			}
		}
	}
	//给联系人级别赋值
	var agencyLevelCode = modifyAgencyComRelationTable.datagrid('getRows')[index]['agencyLevel'];
	var edAgencyLevel = modifyAgencyComRelationTable.datagrid('getEditor', {index:index,field:'agencyLevel'});
	//重新加载城市数据
	$(edAgencyLevel.target).combobox('reload',contextPath+"/codeQuery/codeQuery?codeType='relationLevel'&code="+agencyLevelCode);
	
	
	//给职位重新赋值
	
	
	
}




/**********************可编辑表格增删改查询***********************************************/
//增加一行
function modifyAgencyComRelationTableAddOneRow(){
	modifyAgencyComRelationTableRowIndex = addOneRow(modifyAgencyComRelationTable,modifyAgencyComRelationTableRowIndex);
}
//删除一行
function modifyAgencyComRelationTableRemoveOneRow(){
	removeOneRow(modifyAgencyComRelationTable,modifyAgencyComRelationTableRowIndex);
	modifyAgencyComRelationTableRowIndex= null;
}

//编辑指定行
function modifyAgencyComRelationTableEditOneRow(index){
	if(editOneRow(modifyAgencyComRelationTable,modifyAgencyComRelationTableRowIndex,index)){
		modifyAgencyComRelationTableRowIndex = index;
	}
}
//锁定编辑行
function modifyAgencyComRelationTableLockOneRow(){
	if(lockOneRow(modifyAgencyComRelationTable,modifyAgencyComRelationTableRowIndex)){
		modifyAgencyComRelationTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}


/********************下拉框初始化*************************************************/
function initAllCombobox()
{	
	//合作机构类型
	$("#modifyAgencyType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=agencytype', 
		valueField:'code',
		textField:'codeName'
	});
	
	//合作状态
	$("#modifyState").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comState',
		valueField:'code',
		textField:'codeName'
	});
	
	//省
	$("#modifyProvince").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#modifyProvince").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#modifyCity").combobox('reload',url);
		}
	});
	//市
	$("#modifyCity").combobox({
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#modifyCity").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#modifyCountry").combobox('reload',url);
		}
	});
	//区
	$("#modifyCountry").combobox({
		valueField:'placeCode',
		textField:'placeName'
	});
}

//点击修改页面的[提交]  进行修改合作机构信息
function commitAgencyCom(){
	
	if(!$("#updateAgencyComBasicInfo").form("validate"))
	{
		return false;
	}
	
	var modifyStartDate = $("#modifyStartDate").datebox("getValue");
	var modifyEndDate = $("#modifyEndDate").datebox("getValue");
	if(modifyStartDate!=null && modifyStartDate!="" && modifyStartDate!=undefined &&
			modifyEndDate!=null && modifyEndDate!="" && modifyEndDate!=undefined)
	{
		var d1 = new Date(modifyStartDate.replace(/\-/g, "\/")); 
		var d2 = new Date(modifyEndDate.replace(/\-/g, "\/")); 

		if(d1 >=d2)
		{
		  $.messager.alert('提示', "合作开始时间不能大于结束时间！", 'info');
		  return false; 
		}
	}
	
	if($("#profile").val().length>=800){  
        $.messager.alert('提示', "公司简介超过字数限制，最大800", 'info');
        return true;
    }  
    if($("#cooperationHistory").val().length>=800){   
    	$.messager.alert('提示', "历史信息超过字数限制，最大800", 'info');
    	return true;
    }
    
	var rows = modifyAgencyComRelationTable.datagrid("getRows");
	if(rows.length==0){
		$.messager.alert('提示', "请添加联系人信息再提交！", 'info');
		return;
	}
	
	if(!modifyAgencyComRelationTableLockOneRow()){
		$.messager.alert('提示', "联系人信息输入有误！", 'info');
		return;
	}
	
	
	
	var param = {};
	//基本信息Form
	var basicInfoJson = formDataToJsonStr($("#updateAgencyComBasicInfo").serialize());
	param.agencyComBaseInfo = basicInfoJson;
	//获取联系人表格信息数据
	var agencyRelationTableData = $("#modifyAgencyComRelationTable").datagrid("getRows");
	param.agencyComRelationTableInfo = $.toJSON(agencyRelationTableData);
	//获取需要修改的合作机构ID
	param.modifyAgencyComId=modifyAgencyComId;
   //发送请求，后台接受数据进行处理
	$.ajax({
		type:'post',
		url:contextPath+"/cooperation/updateAgencyCom",
//		data:'param='+$.toJSON(param),
		data : 'param=' +encodeURI($.toJSON(param)),
		cache:false,
		success:function(resultInfo){
			try {
				if(resultInfo.success){
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
				}
				else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
		});
	
}

//清空信息
function clearForm(){
		$('#updateAgencyComBasicInfo').form('clear');
		$('#updateAgencyComRelationInfo').form('clear');
}
function backListAgencyComPage  (){
	$('#agencyComWindow').window('destroy');
	parent.clearAgencyCom();
}