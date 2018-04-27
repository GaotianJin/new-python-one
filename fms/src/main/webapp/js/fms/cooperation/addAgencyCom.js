var addAgencyComRelationTableRowIndex=null;
var operate = null;
$(function(){
	operate = $("#addAgency_operate").val();
	initAllValidateBox();
	initAllCombobox();
	initaddAgencyComRelationTable();
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="addAgency"){
		addAgencyComRelationTableAddOneRow();
		$("#addAgencyComBaiscInfo").form("validate");
	}else if(operate!=null&&operate!=""&&operate!=undefined&&operate=="updateAgency"){
		getAgencyComInfo();
		$("#addAgencyComBaiscInfo").form("validate");
	}else if(operate!=null&&operate!=""&&operate!=undefined&&operate=="agencyDetail"){
		$("#relationTable_tb").css("display", "none"); 
		$("#addAgency_submitButton").hide();
		addAgencyComRelationTable.datagrid('options').onClickRow = agencyComContactsOnClickRow;
		getAgencyComInfo();
	}
});

function initAllValidateBox(){
	$("#addAgencyCode").validatebox({required:true});
	$("#addAgencyName").validatebox({required:true});
}

function initAllCombobox()
{	
	//合作机构类型
	$("#addAgencyType").combobox({
		required:true,
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=agencytype', 
		valueField:'code',
		textField:'codeName'
	});
	//合作状态
	$("#addState").combobox({
		required:true,
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=comState',
		valueField:'code',
		textField:'codeName'
	});
	
	//省
	$("#addProvince").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#addProvince").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#addCity").combobox("reset");
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
			$("#addCountry").combobox("reset");
			$("#addCountry").combobox('reload',url);
		}
	});
	//区
	$("#addCountry").combobox({
		valueField:'placeCode',
		textField:'placeName'
	});
}


function agencyComContactsOnClickRow(){}

var addAgencyComRelationTable;
function initaddAgencyComRelationTable()
{
	addAgencyComRelationTable=$("#addAgencyComRelationTable").datagrid({
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
						field : 'agencyContactsId',
						title : '基金管理人联系人流水号',
						hidden : true,
						formatter : function(value, row, index) {
							return row.agencyContactsId;
						} 
				},
		          {
			        field : 'agencyLevel',
					title : '联系人级别',
					width : 80,
					editor: {
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=relationLevel',
							onSelect:function(){
								var edAgencyLevel = addAgencyComRelationTable.datagrid('getEditor', {index:addAgencyComRelationTableRowIndex,field:'agencyLevel'});
								var edAgencyLevelName = $(edAgencyLevel.target).combobox('getText');
								addAgencyComRelationTable.datagrid('getRows')[addAgencyComRelationTableRowIndex]['agencyLevelName'] = edAgencyLevelName;
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
					width : 80,
					editor:{
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,20]']
						}
					},
					formatter : function(value, row, index) {
						return row.contactsName;
					}
				},
				{
					field : 'position',
					title : '职位',
					width : 80,
					editor: {
						type:'validatebox',
						options:{
							validType:['length[0,100]']
						}
					},
					formatter : function(value, row, index) {
						return row.position;
					}
				},
				{
					field : 'zipCode',
					title : '邮政编码',
					width : 80,
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
					field : 'tel',
					title : '联系电话',
					width : 80,
					editor: {
						type:'validatebox',
						options:{
							validType: 'validTel'
						}
					},
					formatter : function(value, row, index) {
						return row.tel;
					}
				}
				,
				{
					field : 'fax',
					title : '传真',
					width : 80,
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
				,
				{
					field : 'remark',
					title : '备注',
					width : 120,
					editor: {
						type:'validatebox',
						options:{
							validType: ['length[0,100]']
						}
					},
					formatter : function(value, row, index) {
						return row.remark;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#addAgencyComRelationTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(index){
			addAgencyComRelationTableEditOneRow(index);
		},
		toolbar:"#relationTable_tb"
	});
}

/**********************可编辑表格增删改查询***********************************************/
//增加一行
function addAgencyComRelationTableAddOneRow(){
	addAgencyComRelationTableRowIndex = addOneRow(addAgencyComRelationTable,addAgencyComRelationTableRowIndex);
}
//删除一行
function addAgencyComRelationTableRemoveOneRow(){
	removeOneRow(addAgencyComRelationTable,addAgencyComRelationTableRowIndex);
	addAgencyComRelationTableRowIndex= null;
}
//编辑指定行
function addAgencyComRelationTableEditOneRow(index){
	if(editOneRow(addAgencyComRelationTable,addAgencyComRelationTableRowIndex,index)){
		addAgencyComRelationTableRowIndex = index;
	}
}
//锁定编辑行
function addAgencyComRelationTableLockOneRow(){
	if(lockOneRow(addAgencyComRelationTable,addAgencyComRelationTableRowIndex)){
		addAgencyComRelationTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}

/********************下拉框初始化*************************************************/


//点击[提交]新增合作机构信息
function commitAgencyCom(){
	if(!$("#addAgencyComBaiscInfo").form("validate")){
		return false;
	}
	
	var addStartDate = $("#addStartDate").datebox("getValue");
	var addEndDate = $("#addEndDate").datebox("getValue");
	if(!compareDate(addStartDate,addEndDate)){
		$.messager.alert('提示', "合作开始时间不能大于结束时间！", 'info');
		  return false; 
	}
	
	if($("#profile").val().length>=800){  
        $.messager.alert('提示', "公司简介超过字数限制，最大800", 'info');
        return true;
    }
    if($("#cooperationHistory").val().length>=800){   
    	$.messager.alert('提示', "历史信息超过字数限制，最大800", 'info');
    	return true;
    }
    
	var rows = addAgencyComRelationTable.datagrid("getRows");
	if(rows.length==0){
		$.messager.alert('提示', "请添加联系人信息再提交！", 'info');
		return;
	}
	
	if(!addAgencyComRelationTableLockOneRow()){
		$.messager.alert('提示', "联系人信息输入有误！", 'info');
		return;
	}
	
	
	var param = {};
	param.operate = operate;
	//基本信息Form
	var basicInfoJson = formDataToJsonStr($("#addAgencyComBaiscInfo").serialize());
	param.agencyComBaseInfo = basicInfoJson;
	//获取联系人表格信息数据
	var agencyRelationTableData = $("#addAgencyComRelationTable").datagrid("getRows");
	param.agencyComRelationTableInfo = $.toJSON(agencyRelationTableData);

   //发送请求，后台接受数据进行处理
	$.ajax({
		type:'post',
		url:contextPath+"/cooperation/addAgencyCom",
//		data:'param='+$.toJSON(param),
		data : 'param=' +encodeURI($.toJSON(param)),
		cache:false,
		success:function(resultInfo){
			try {
				if(resultInfo.success){
					$("#addAgencyComId").val(resultInfo.obj.agencyComId);
					getAgencyComInfo();
					$.messager.alert('提示', resultInfo.msg, 'info');
					//clearForm();
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
	$('#addAgencyComBaiscInfo').form('clear');
	$('#addAgencyComRelationInfo').form('clear');
}

function backListAgencyComPage(){
	$('#agencyComWindow').window('close');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="agencyDetail"){
		searchAgencyCom();
	}
}

function getAgencyComInfo(){
	var agencyComId = $("#addAgencyComId").val();
	$.ajax({
		type:'post',
		url:contextPath+"/cooperation/queryAgencyComInfo",
		data:{param:agencyComId},
		cache:false,
		success:function(resultInfo){
			try {
				if(resultInfo.success){
					var resultObj = resultInfo.obj;
					//1.合作机构基本信息
					//先加载市和区的下拉列表数据
					var provinceCode = resultObj.agencyComBaseInfo.province;
					var loadCityDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode;
					$("#addCity").combobox('reload',loadCityDataUrl);
					var cityCode = resultObj.agencyComBaseInfo.city;
					var loadCountryDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode;
					$("#addCountry").combobox('reload',loadCountryDataUrl);
					setInputValueById("addAgencyComBaseDiv",resultObj.agencyComBaseInfo)
					//2.合作机构联系人信息赋值
					if(resultObj.agencyComContactsInfo!=null&&resultObj.agencyComContactsInfo!=undefined){
						clearAllRows(addAgencyComRelationTable);
						loadJsonObjData("addAgencyComRelationTable",resultObj.agencyComContactsInfo);
						addAgencyComRelationTableRowIndex = null;
					}
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
