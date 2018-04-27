var detailAgencyComRelationTableRowIndex=null;
var detailAgencyComId=null;
$(function(){
	//获取从选中的要修改的ID
	var rows = $('#agencyComTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要查看的基金管理人");
		return;
	} 
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			detailAgencyComId = n.agencyComId;
		});
	};
	
	initAllCombobox();
	initdetailAgencyComRelationTable();
	getdetailInitValue();
});
//初始需要修改的数据
function getdetailInitValue(){
	$.ajax({
		type:'post',
		url:contextPath+"/cooperation/queryAgencyComInfo",
		data:{param:detailAgencyComId},
		cache:false,
		success:function(result){
			try {
				if(result.success){
					var resultObj = result.obj;
					//1.合作机构基本信息
					//先加载市和区的下拉列表数据
					var provinceCode = resultObj.agencyComBaseInfo.province;
					var loadCityDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode;
					$("#detailCity").combobox('reload',loadCityDataUrl);
					var cityCode = resultObj.agencyComBaseInfo.city;
					var loadCountryDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode;
					$("#detailCountry").combobox('reload',loadCountryDataUrl);
					setInputValueById("detailAgencyComBasicInfo",resultObj.agencyComBaseInfo)
					//2.合作机构联系人信息赋值
					if(resultObj.agencyComContactsInfo!=null&&resultObj.agencyComContactsInfo!=undefined){
						loadJsonObjData("detailAgencyComRelationTable",resultObj.agencyComContactsInfo);
						detailAgencyComRelationTableRowIndex = null;
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


var detailAgencyComRelationTable;
function initdetailAgencyComRelationTable()
{
	detailAgencyComRelationTable=$("#detailAgencyComRelationTable").datagrid({
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
						var edAgencyLevel = detailAgencyComRelationTable.datagrid('getEditor', {index:detailAgencyComRelationTableRowIndex,field:'agencyLevel'});
						var edAgencyLevelName = $(edAgencyLevel.target).combobox('getText');
						detailAgencyComRelationTable.datagrid('getRows')[detailAgencyComRelationTableRowIndex]['agencyLevelName'] = edAgencyLevelName;
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
						var edPosition = detailAgencyComRelationTable.datagrid('getEditor', {index:detailAgencyComRelationTableRowIndex,field:'position'});
						var edPositionName = $(edPosition.target).combobox('getText');
						detailAgencyComRelationTable.datagrid('getRows')[detailAgencyComRelationTableRowIndex]['positionName'] = edPositionName;
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
						var edProvince = detailAgencyComRelationTable.datagrid('getEditor', {index:detailAgencyComRelationTableRowIndex,field:'province'});
						var edProvinceName = $(edProvince.target).combobox('getText');
						var edProvinceCode = $(edProvince.target).combobox('getValue');
						detailAgencyComRelationTable.datagrid('getRows')[detailAgencyComRelationTableRowIndex]['provinceName'] = edProvinceName;
						var edcity = detailAgencyComRelationTable.datagrid('getEditor', {index:detailAgencyComRelationTableRowIndex,field:'city'});
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
						var edcity = detailAgencyComRelationTable.datagrid('getEditor', {index:detailAgencyComRelationTableRowIndex,field:'city'});
						var edcityName = $(edcity.target).combobox('getText');
						var edcityCode = $(edcity.target).combobox('getValue');
						detailAgencyComRelationTable.datagrid('getRows')[detailAgencyComRelationTableRowIndex]['cityName'] = edcityName;
						
						var edcountry = detailAgencyComRelationTable.datagrid('getEditor', {index:detailAgencyComRelationTableRowIndex,field:'country'});
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

						var edcountry = detailAgencyComRelationTable.datagrid('getEditor', {index:detailAgencyComRelationTableRowIndex,field:'country'});
						var edcountryName = $(edcountry.target).combobox('getText');
						detailAgencyComRelationTable.datagrid('getRows')[detailAgencyComRelationTableRowIndex]['countryName'] = edcountryName;
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
			formatter : function(value, row, index) {
				return row.street;
			}
		}
		,
		{
			field : 'zipCode',
			title : '邮政编码',
			width : 60,
			formatter : function(value, row, index) {
				return row.zipCode;
			}
		}
		,
		{
			field : 'telphone',
			title : '联系电话',
			width : 80,
			formatter : function(value, row, index) {
				return row.telphone;
			}
		}
		,
		{
			field : 'fax',
			title : '传真',
			width : 80,
			formatter : function(value, row, index) {
				return row.fax;
			}
		}
		,
		{
			field : 'mobile',
			title : '手机',
			width : 80,
			formatter : function(value, row, index) {
				return row.mobile;
			}
		},
		{
			field : 'email',
			title : '邮箱',
			width : 80,
			formatter : function(value, row, index) {
				return row.email;
			}
		}
		,
		{
			field : 'webchat',
			title : '微信',
			width : 80,
			formatter : function(value, row, index) {
				return row.webchat;
			}
		}
		
		]],
		onLoadSuccess : function() {
			$('#detailAgencyComRelationTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(index){
			setCityAndCountry(index);
		}
	});
}


//点击一行启动编辑后，给市和区重新赋值
function setCityAndCountry(index){
	//给市和区重新赋值
	var provinceCode = detailAgencyComRelationTable.datagrid('getRows')[index]['province'];
	if(provinceCode!=null&&provinceCode!=''){
		var edCity = detailAgencyComRelationTable.datagrid('getEditor', {index:index,field:'city'});
		//重新加载城市数据
		$(edCity.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode);
		//获取之前存储的城市数据
		var cityCode = detailAgencyComRelationTable.datagrid('getRows')[index]['city'];
		if(cityCode!=null&&cityCode!=''){
			$(edCity.target).combobox('setValue',cityCode);
			//重新加载区县数据
			var edCountry = detailAgencyComRelationTable.datagrid('getEditor', {index:index,field:'country'});
			$(edCountry.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode);
			var countryCode = detailAgencyComRelationTable.datagrid('getRows')[index]['country'];
			if(countryCode!=null&&countryCode!=''){
				$(edCountry.target).combobox('setValue',countryCode);
			}
		}
	}
	//给联系人级别赋值
	var agencyLevelCode = detailAgencyComRelationTable.datagrid('getRows')[index]['agencyLevel'];
	var edAgencyLevel = detailAgencyComRelationTable.datagrid('getEditor', {index:index,field:'agencyLevel'});
	//重新加载城市数据
	$(edAgencyLevel.target).combobox('reload',contextPath+"/codeQuery/codeQuery?codeType='relationLevel'&code="+agencyLevelCode);
	
	
	//给职位重新赋值
	
	
	
}
/********************下拉框初始化*************************************************/
function initAllCombobox()
{	
	//合作机构类型
	$("#detailAgencyType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=agencytype', 
		valueField:'code',
		textField:'codeName'
	});
	
	//合作状态
	$("#detailState").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comState',
		valueField:'code',
		textField:'codeName'
	});
	
	//省
	$("#detailProvince").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#detailProvince").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#detailCity").combobox('reload',url);
		}
	});
	//市
	$("#detailCity").combobox({
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#detailCity").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#detailCountry").combobox('reload',url);
		}
	});
	//区
	$("#detailCountry").combobox({
		valueField:'placeCode',
		textField:'placeName'
	});
}
function backListAgencyComPage  (){
	$('#agencyComWindow').window('destroy');
	parent.clearAgencyCom();
}