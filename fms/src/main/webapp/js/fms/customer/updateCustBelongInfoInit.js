jQuery(function($){
	// 将所有输入框禁用
	$('#customer_no').attr("disabled", "true");
	$('#chinese_name').attr("disabled", "true");
	$('#idno').attr("disabled", "true");
	$('#modifyCustomerBaseInfo_age').attr("disabled", "true");
	// 初始化所有下拉框
	initCombobox();
	// 初始化客户归属历史调整信息列表
	initAdjustInfoDataGrid();
	// 初始化客户归属调整信息提交列表
	initAdjustAgentInfoDataGrid();
	agentInfoTableAddOneRow();
	//  赋值客户历史归属信息与基本信息给页面
	setCustInfoAndBelongInfoToPage();
});
/**声明变量**/
var flag = true;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**初始化各下拉框**/
function initCombobox(){
	//初始化性别
	$("#modifyCustomerBaseInfo_Sex").combobox({
		url:'codeQuery/tdCodeQuery?codeType=sex',
		valueField:'code',    
	    textField:'codeName',
	    disabled:true
	});
	//初始化客户类型
	$("#modifyCustomerBaseInfo_CustType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=customerType',
	    disabled:true
	});
	//初始化客户重要性
	/*$("#modifyCustomerBaseInfo_custQuality").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=custQuality',
	    disabled:true
	    
	});*/
	//证件类型
	$("#modifyCustomerBaseInfo_IdType").combobox({
		url : contextPath+'/codeQuery/tdCodeQuery?codeType=idType',
		valueField : 'code',
		textField : 'codeName',
		disabled:true
	});
	//获客方式
	/*$("#modifyCustomerBaseInfo_CustObtainWay").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=custObtainWay',
		valueField : 'code',
		textField : 'codeName',
		disabled:true
	});	*/
	//客户级别
	$("#modifyCustomerBaseInfo_CustLevel").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=custLevel',
		valueField : 'code',
		textField : 'codeName',
		disabled:true
	});
}

/////////////////////////////////////////两个数据网格datagrid/////////////////////////////////////////////////////////////////
/**初始化显示客户历史调整信息列表**/
var customerBelongTable;
function initAdjustInfoDataGrid(){
	// 获取客户归属页面中客户基本信息流水号
	var rows = $('#listBelong_CustomerTable').datagrid('getSelections');
	// 获取不到合法的客户基本信息流水号
	if(rows == null || rows == undefined) {
		$.messager.alert('提示', "客户基本信息流水号未获取到，请通知IT！", 'info');
	}
	// 获取到合法客户基本流水号
	var param = {};
	param.custBaseInfoId = rows[0].custBaseInfoId;
	param = encodeURI($.toJSON(param));
	// 初始化table为数据网格datagrid
	customerBelongTable = $('#custBelongInfoTable').datagrid({
			method : 'post',
			iconCls : 'icon-edit', // 图标
			singleSelect : true, // 多选
			fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, // 奇偶行颜色不同
			collapsible : true,// 可折叠
			url : contextPath+"/customer/queryCustHistoryBelongURL",
			sortName : 'id', // 排序的列
			sortOrder : 'desc', // 倒序
			remoteSort : true, // 服务器端排序
			idField : 'id', // 主键字段
			queryParams : {"queryParam":param}, // 查询条件
			pagination : true, // 显示分页
			rownumbers : true, // 显示行号
			pageList:[5,10,15,20],
			pageSize:5,
			columns : [[{
						field : 'ck',
						checkbox : true,
						width : 2
					}, // 显示复选框
					{
						field : 'custBaseInfoId',
						title : '客户流水号',
						hidden : true,
						formatter : function(value, row, index) {
							return row.custBaseInfoId;
						} 
					},
					{
						field : 'agentId',
						title : '财富顾问流水号',
						hidden : true,
						formatter : function(value, row, index) {
							return row.agentId;
						},
						editor: {
							type:'combobox',
							options:{
								required: true,    
								editable: false,
							    hasDownArrow: false
							}
						}
					},
					{
						field : 'agentCode',
						title : '财富顾问代码',
						width : 100,
						formatter : function(value, row, index) {
							return row.agentCode;
						},
						editor: {
							type:'combobox',
							options:{
								required: true,    
								editable: false,
							    hasDownArrow: false
							}
						}
					},
					{
						field : 'agentName',
						title : '财富顾问姓名',
						width : 100,
						formatter : function(value, row, index) {
							return row.agentName;
						},
						editor: {
							type:'combobox',
							options:{
								required: true,    
								editable: false,
							    hasDownArrow: false
							}
						}
					},
					{
						field : 'storeName',
						title : '所属网点',
						width : 100,
						hidden:true,
						formatter : function(value, row, index) {
							return row.storeName;
						}
					},
					{
						field : 'comName',
						title : '所属分公司',
						width : 100,
						formatter : function(value, row, index) {
							return row.comName;
						}
					},{
						field : 'belongStartDate',
						title : '归属起日',
						width : 100,
						formatter : function(value, row, index) {
							return row.belongStartDate;
						}
					},{
						field : 'belongEndDate',
						title : '归属止日',
						width : 100,
						formatter : function(value, row, index) {
							return row.belongEndDate;
						}
					},{
						field : 'serverDay',
						title : '服务时长(天)',
						width : 100,
						formatter : function(value, row, index) {
							return row.serverDay;
						}
					},{
						field : 'signNumber',
						title : '签单次数',
						width : 100,
						formatter : function(value, row, index) {
							return row.signNumber;
						}
					},{
						field : 'operator',
						title : '操作人',
						width : 80,
						formatter : function(value, row, index) {
							return row.operator;
						}
					},{
						field : 'adjustDate',
						title : '调整时间',
						width : 100,
						formatter : function(value, row, index) {
							return row.adjustDate;
						}
					},{
						field : 'adjustCause',
						title : '调整原因',
						width : 100,
						formatter : function(value, row, index) {
							return row.adjustCause;
						}
					},{
						field : 'attachment',
						title : '附件',
						width : 100,
						formatter : function(value, row, index) {
							var s =  '<a href="#" onclick="uploadCustAdjustFile()" >调整凭证</a>';
							return s;
						}
					}
					]],
			onLoadSuccess : function() {
				// 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('#custBelongInfoTable').datagrid('clearSelections'); 
			},
			onClickRow: function() {
				$('#custBelongInfoTable').datagrid('clearSelections');
			}
		});
}

/**初始化客户历史归属调整信息**/
var initAdjustAgentInfoDataGrid;
function initAdjustAgentInfoDataGrid() {
	initAdjustAgentInfoDataGrid = $('#addAgentBelongInfoTable').datagrid({
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
			queryParams : {"queryParam":{}}, // 查询条件
			rownumbers : true, // 显示行号
			columns : [[{
						field : 'ck',
						checkbox : true,
						width : 2
					}, // 显示复选框
					{
						field : 'custBaseInfoId',
						title : '客户流水号',
						hidden : true,
						formatter : function(value, row, index) {
							return row.custBaseInfoId;
						} 
					},
					{
						field : 'agentId',
						title : '财富顾问流水号',
						hidden : true,
						formatter : function(value, row, index) {
							return row.agentId;
						},
						editor: {
									type:'combobox',
									options:{
												required: true,    
												editable: false,
											    hasDownArrow: false
											}
								}
					},
					{
						field : 'agentCode',
						title : '财富顾问代码',
						width : 100,
						editor: {
									type:'combobox',
									options:{
										required: true,    
										editable: false,
									    hasDownArrow: false
									}
								}
					},
					{
						field : 'agentName',
						title : '财富顾问姓名',
						width : 80,
						formatter:function(value, row, index){
							return row.agentName;
						},
						editor: {
									type:'combobox',
									options:{
										required: true,    
										editable: false,
									    hasDownArrow: false
									}
								}
					},
					{
						field : 'img',
						title : '',
						width : 20,
						formatter : function(value, row, index) {
							return "<a href='javascript:void(0)'  onclick='openSearchCustWindow()'><img src='img/search.png'/></a>";
						}
					},
					/*{
						field : 'storeName',
						title : '所属门店',
						width : 80,
						hidden:true,
						formatter : function(value, row, index) {
							return row.storeName;
						},
						editor: {
							type:'combobox',
							options:{
								required: true,    
								editable: false,
							    hasDownArrow: false
							}
						}
					},*/
					{
						field : 'comName',
						title : '所属分公司',
						width : 80,
						formatter : function(value, row, index) {
							return row.comName;
						},
						editor: {
							type:'combobox',
							options:{
								required: true,    
								editable: false,
							    hasDownArrow: false 
							}
						}
					},{
						field : 'belongStartDate',
						title : '归属起日',
						width : 100,
						formatter : function(value, row, index) {
							return row.belongStartDate;
						},
						editor: {
							type:'datebox',
							options:{
								required: true  
							}
						}
					},{
						field : 'belongEndDate',
						title : '归属止日',
						width : 100,
						formatter : function(value, row, index) {
							return '至今';
						}
					},{
						field : 'adjustCause',
						title : '调整原因',
						width : 100,
						formatter : function(value, row, index) {
							return row.adjustCause;
						},
						editor: {
							type:'text',
							options:{
								required: true   
							}
						}
					}/*,{
						field : 'attachment',
						title : '附件',
						width : 100,
						formatter : function(value, rowData, rowIndex) {
									var s =  "<a href='#' onclick='uploadCustAdjustFile()' >调整凭证</a>";
									return s;
						}
					}*/
					]],
			toolbar : [{
						text : '锁定',
						iconCls : 'icon-lockedit',
						handler : function() {
							agentInfoTableLockOneRow();
						}
					}],
			onLoadSuccess : function() {
				// 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('#addAgentBelongInfoTable').datagrid('clearSelections'); 
			},
			onClickRow: function (rowIndex, rowData) {
	            agentInfoTableEditOneRow(rowIndex);
			}
		});
}
var agentAdjustTableEditIndex;
//增加一行
function agentInfoTableAddOneRow(){
	agentAdjustTableEditIndex = addOneRow(initAdjustAgentInfoDataGrid,agentAdjustTableEditIndex);
}
//删除一行
function agentInfoTableRemoveOneRow(){
	removeOneRow(initAdjustAgentInfoDataGrid,agentAdjustTableEditIndex);
	agentAdjustTableEditIndex = null;
}
//编辑指定行
function agentInfoTableEditOneRow(index){
	if(editOneRow(initAdjustAgentInfoDataGrid,agentAdjustTableEditIndex,index)){
		agentAdjustTableEditIndex = index;
	}
}
//锁定编辑行
function agentInfoTableLockOneRow(){
	if(lockOneRow(initAdjustAgentInfoDataGrid,agentAdjustTableEditIndex)){
		agentAdjustTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**将客户基本相关信息与客户历史归属信息赋值给页面**/
function setCustInfoAndBelongInfoToPage() {
	// 获取客户归属页面中客户基本信息流水号
	var rows = $('#listBelong_CustomerTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		$.messager.alert("请选择将要进行调整的客户");
		return;
	} 
	// 将待调整的客户基本信息流水号通过Ajax请求发送的服务器进行查询
	var param = rows[0].custBaseInfoId;
	$.ajax({
		type:'post', 
		url:contextPath+"/customer/getCustBelongInoUrl",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(result){
				try {
					if(result.success){
						var resultObj = result.obj;
						if (resultObj.custInfo!=null&&resultObj.custInfo!=undefined&&resultObj.custInfo!=""){					
							// 将客户基本相关信息Map赋给客户基本信息表单
							setInputValueById("custBasicInfo",resultObj.custInfo);
						}
					}
				}catch(e){
					$.messager.alert('提示', e);
				}
			}
		});
}



function backListUserPage(){
	$("#updateCustomerWindow").window("destroy");
}

/**弹出新窗口查询理财经理信息**/
function openSearchCustWindow(){
	$('<div id="searchCustWindow"/>').dialog({
		href : contextPath+"/sales/searchAgent",
		//type : "post",
		modal : true,
		title : "财富顾问信息查询",
		//fit : true, 
		width:1000,
		height:600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}


/**查找客户并选中后，返回到该页面在点击客户号旁搜索按钮时，在弹出的窗口中选择已有客户信息后，会将该客户信息赋给页面**/
function searchCustomerInfoBack(agentBaseInfo){
	var agentIdCol = initAdjustAgentInfoDataGrid.datagrid('getEditor', {index:agentAdjustTableEditIndex,field:'agentId'});
	$(agentIdCol.target).combobox('setValue',agentBaseInfo.agentId);
	// 数据表格列：理财经理代码赋值
	var agentCodeCol = initAdjustAgentInfoDataGrid.datagrid('getEditor', {index:agentAdjustTableEditIndex,field:'agentCode'});
	$(agentCodeCol.target).combobox('setValue',agentBaseInfo.agentCode);
	// 数据表格列：理财经理姓名赋值
	var agentNameCol = initAdjustAgentInfoDataGrid.datagrid('getEditor', {index:agentAdjustTableEditIndex,field:'agentName'});
	$(agentNameCol.target).combobox('setValue',agentBaseInfo.agentName);
	// 数据表格列：所属门店赋值
	/*var belongStoreCol = initAdjustAgentInfoDataGrid.datagrid('getEditor', {index:agentAdjustTableEditIndex,field:'storeName'});
	$(belongStoreCol.target).combobox('setValue',agentBaseInfo.storeName);*/
	// 数据表格列：所属分公司赋值
	var belongSubComCol = initAdjustAgentInfoDataGrid.datagrid('getEditor', {index:agentAdjustTableEditIndex,field:'comName'});
	$(belongSubComCol.target).combobox('setValue',agentBaseInfo.comName);
}
/**保存客户归属信息**/
function saveUpdateInfo(){
	agentInfoTableLockOneRow();
	if(!flag) {
		return;
	}
	flag = false;
	// 数据表格序列化
	var param = {};
	var custInfo = {};
	//判断添加的信息是否有误
	if(!agentInfoTableLockOneRow()){
		$.messager.alert('提示', "客户归属信息输入有误！", 'info');
		return;
	}
	// 获取客户归属页面中客户基本信息流水号
	var rows = $('#listBelong_CustomerTable').datagrid('getSelections');
	param.custBelongInfo = $.toJSON($("#addAgentBelongInfoTable").datagrid("getRows"));
	param.custBaseInfoId = rows[0].custBaseInfoId;
	var data = $.toJSON(param);
	data = escape(encodeURIComponent(data));
	custInfo.custBaseInfoId = rows[0].custBaseInfoId;
	custInfo = encodeURI($.toJSON(custInfo));
	$.ajax({
		type : 'post',
		url : contextPath + "/customer/saveUpadateAgentInfo",
		data : 'param='+data,
		cache : false,
		success : function(resultInfo){
					flag = true;
					try {
						if (resultInfo.success) {
							$.messager.alert('提示', resultInfo.msg,"info");
							//setCustInfoAndBelongInfoToPage();
							clearAllRows(initAdjustAgentInfoDataGrid);
							agentInfoTableAddOneRow();
							customerBelongTable.datagrid('load',{queryParam:custInfo});
						} else {
							$.messager.alert('提示', resultInfo.msg);
						}
					} catch (e) {
						$.messager.alert('提示', e);
					}
				}
	});
}
/**客户调整凭证上传**/
function uploadCustAdjustFile() {
	var rows = $('#custBelongInfoTable').datagrid('getSelections');
	// 判断是否选定一行
	if(rows == null || rows == undefined || rows == '') {
		$.messager.alert('提示', "为确保正确查看与提交附件，请先选定第一列的复选框后再点击上传", 'info');
		return;
	} 
	else 
	{
		var agentId = rows[0].agentId;
	}
	// 若客户基本信息流水号不为空
	if (agentId != null) {
		var param = {};
		param.businessNo = agentId;
		param.businessType = "08";
		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));
	} 
	else 
	{
		$.messager.alert('提示', "请先保存该财富顾问信息后再进行上传！", 'info');
	}
}
