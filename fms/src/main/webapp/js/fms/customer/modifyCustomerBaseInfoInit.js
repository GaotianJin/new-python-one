/*************************************************************************************************************
 * 
 * 			声明	变量
 *************************************************************************************************************/
var modifyCustomerBaseInfo_Id = null;
var modifyCustomerBaseInfo_agentId = null;
var modifyCustomerBaseInfo_rolePivilege = null;
var modifyCustomerBaseInfo_custLevel = null;
var modifyCustomerBaseInfo_tradeId = null;
var operate = null;
var tradeLoadFlag = null;
var loginUserId = null;
/***********************************************************************************************************
 * 
 * 
 * 	页面完全加载后执行的函数
 * 	@author LiuYi
 * 	@
 ************************************************************************************************************/
jQuery( function($) {
	// 初始化页面全局变量============================
	//客户信息流水号
	modifyCustomerBaseInfo_Id = $("#modifyCustomerBaseInfo_Id").val();    
	//理财经理流水号
	modifyCustomerBaseInfo_agentId = $("#modifyCustomerBaseInfo_agentId").val();
	//客户角色
	modifyCustomerBaseInfo_rolePivilege = $("#modifyCustomerBaseInfo_rolePivilege").val();
	//交易流水号
	modifyCustomerBaseInfo_tradeId = $('#modifyCustomerBaseInfo_tradeId').val();
	//操作类型
	operate = $("#modifyCustomerBaseInfo_loadFlag").val();
	//获取登录用户ID
/*	loginUserId = $("#modifyCustomerBaseInfo_loginUserId").val(loginUserId);
	alert(loginUserId);*/
	//调用函数初始化各个控件=========================================
	//初始化所有下拉框
	initCombobox();
	//初始化地址信息表格
	initCustomerAddressInfo();
	//初始化账户信息表格
	initCustAccountInfo(); 
	//初始化客户归属信息
	inItModifyCustomerAscriptionTable();
	// 根据传入参数执行相应操作========================================
	if(operate=="addCust"){
		//客户地址新增一行
		custAddressTableAddOneRow();
		//客户账户新增一行
		custAccountTableAddOneRow();
	}
	//客户明细查询
	if( operate!=null&&operate!=""&&operate!=undefined&&operate=="custDetail" ){
		$("#modifyCustomerBaseInfo_CustBaseInfoSubmitButton").hide();
		$("#modifyCustomerBaseInfo_searchCustButton").hide();
		$('#modifyCustomerBaseInfo_submitCarInfoButton').hide();
		$("#custOperationPrompt").hide();
		$("#modifyCustomerAddressTable_tb").css("display", "none");
		//$("#modifyCustomerAccountTable_tb").css("display", "none");
		$("#custAccountTable_AddOneRow").hide();
		$("#custAccountTable_RemoveOneRow").hide();
		$("#custAccountTable_LockOneRow").hide();
	}
	//交易号不为空,交易功能调用
	if (modifyCustomerBaseInfo_tradeId != null && modifyCustomerBaseInfo_tradeId != "" && modifyCustomerBaseInfo_tradeId != undefined ) {
		// 交易新增客户时，初始化所有必录项
		setCustBaseInfoRequired();
		setIdNoRequired();
		setMobileRequired();
		emailRequired();
		//设置交易操作标记
		tradeLoadFlag = "tradeLoadFlag";
	}
	//客户基本信息流水号不为空，更新客户
	if (modifyCustomerBaseInfo_Id != null && modifyCustomerBaseInfo_Id != "" && modifyCustomerBaseInfo_Id != undefined ) {
		//打开所有tab页
		openAllTabs();
		//加载客户原有数据
		getModifyCustomerBaseInfo(modifyCustomerBaseInfo_Id,modifyCustomerBaseInfo_agentId);
		setCustBaseInfoRequired();
		setIdNoRequired();
		setMobileRequired();
		emailRequired();
	} else {
		// 设置姓名为必录
		setNameRequired();
		setCustBaseInfoRequired();
		setIdNoRequired();
		setMobileRequired();
		emailRequired();
	}
});


/**
 * 交易新增客户时，初始化所有必录项
 * 中文姓名,英文姓,英文名，性别，证件类型，证件号码，出生日期，证件有效期，手机号，邮箱，客户类型，客户类型为必录项
 * */
function setCustBaseInfoRequired(){
	$('#modifyCustomerBaseInfo_LastName').validatebox({    
	    required: true
	});
	$('#modifyCustomerBaseInfo_FirstName').validatebox({    
	    required: true   
	});
	$('#modifyCustomerBaseInfo_Sex').combobox({
		required : true
	});
	$('#modifyCustomerBaseInfo_IdType').combobox({
		required : true,
		value:'1'
	});
	$('#modifyCustomerBaseInfo_Birthday').datebox({    
	    required:true   
	}); 
	
	$('#modifyCustomerBaseInfo_idValidityDate').datebox({    
	    required: true   
	});
	$('#modifyCustomerBaseInfo_CustType').combobox({
		required : true
	});
	$('#modifyCustomerBaseInfo_CustObtainWay').combobox({    
	    required: true
	});
	$('#modifyCustomerBaseInfo_CustType').combobox({
		required : true
	});
}
/***********************************************************************************************************
 * 	设置姓名为必录
 * 	@author
 * 	@
 * ***********************************************************************************************************/
function setNameRequired() {
	$('#modifyCustomerBaseInfo_ChnName').validatebox({
		required:true
	});
}
function setNameNotRequired() {
	$('#modifyCustomerBaseInfo_ChnName').validatebox({
		required:false
	});
}
function setIdNoRequired() {
	$('#modifyCustomerBaseInfo_IdNo').validatebox({    
	    required: true   
	});
}
function setIdNoNotRequired() {
	$('#modifyCustomerBaseInfo_IdNo').validatebox({    
	    required: false   
	});
}
function setMobileRequired() {
	$('#modifyCustomerBaseInfo_Mobile').validatebox({    
	    required: true   
	});
}
function setMobileNotRequired() {
	$('#modifyCustomerBaseInfo_Mobile').validatebox({    
	    required: false   
	});
}
function emailRequired() {
	$('#modifyCustomerBaseInfo_Email').validatebox({    
	    required: true,
	    validType:['email','length[0,100]']
	});
}
function emailNotRequired() {
	$('#modifyCustomerBaseInfo_Email').validatebox({    
	    required: false   
	});
}


/***********************************************************************************************************
 * 	初始化各下拉框
 * 	@author
 * 	@
 * ***********************************************************************************************************/
function initCombobox(){
	/*$('#modifyCustomerBaseInfo_ChnName').validatebox({
		required:true
	});*/
	// 出生日期初始化为datebox
	$("#modifyCustomerBaseInfo_Birthday").datebox({
		//required: true
	});
	//初始化性别
	$("#modifyCustomerBaseInfo_Sex").combobox({
		url:'codeQuery/tdCodeQuery?codeType=sex',
		valueField:'code',    
	    textField:'codeName',
	    editable:false
	    /*required:true,
	    onSelect:function(){
	    	var sexName = $("#addCust_Sex").combobox("getText");
	    	$("#addCust_SexName").val(sexName);
	    }*/
	});
	//初始化客户类型
	$("#modifyCustomerBaseInfo_CustType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=customerType',
	    required: true,
	    editable:false
	});
	//证件类型
		$("#modifyCustomerBaseInfo_IdType").combobox({
			url : contextPath+'/codeQuery/tdCodeQuery?codeType=idType',
			valueField : 'code',
			textField : 'codeName',
			editable: false,
			onSelect: function(record){
						setBirthdayAndSexDisable();
				    	if(record.code=="1"){
							var idNo = $("#modifyCustomerBaseInfo_IdNo").val();
							if(idNo!=null&&idNo!=""&&idNo!=undefined){
								verifyIdNo();
							}
						}
				    	$("#modifyCustomerBaseInfo_IdTypeName").val(record.codeName);
					}
		});
	
	//获客方式
	$("#modifyCustomerBaseInfo_CustObtainWay").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=custObtainWay',
		valueField : 'code',
		textField : 'codeName',
		//required: true,
		editable:false
	});	
	//初始化国籍
	$("#modifyCustomerBaseInfo_NativePlace").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url : 'codeQuery/tdCodeQuery?codeType=nationality',
	    value:156,
	    editable:false
	});
	//客户级别
	$("#modifyCustomerBaseInfo_CustLevel").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=custLevel',
		valueField : 'code',
		textField : 'codeName',
		disabled:true,
		value : '02'
	});
	//客户投资类型
	$("#modifyCustomerBaseInfo_InvestCustomerType").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=investCustomerType',
		valueField : 'code',
		textField : 'codeName',
		required: true/*,
		disabled:true,
		value : '01'*/
	});
    $("#modifyCustomerBaseInfo_idValidityDate").datebox({
	});
    //客户有效期是否长期勾选按钮
    $("input[name='idNoType']").click(function(){
    	switch($("input[name='idNoType']:checked").attr("id")){
    	  case "idNoType0":
    	   $('#modifyCustomerBaseInfo_idValidityDate').datebox('setValue', '3000-12-31');    
    	   break;
    	  case "idNoType1":
    	   $('#modifyCustomerBaseInfo_idValidityDate').datebox('setValue', '');
    	   break;
    	  default:
    	   break;
    	 }
    	 });
    
  //是否国金开户     
	$("#modifyCustomerBaseInfo_isGoldenStateopen").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=isGoldenStateopen',
		valueField : 'code',
		textField : 'codeName',
		//required: true,
		editable:false
	});	
}


/***********************************************************************************************************
 * 	文档页面加载完成后触发该函数
 * 	初始化地址信息数据表格
 * 	@author
 * 	@
 * ***********************************************************************************************************/
var custAddressTable;
function initCustomerAddressInfo(){
	custAddressTable = $('#modifyCustomerAddressTable').datagrid({
		//title : '联系信息', // 标题
		method : 'post',
		////iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		//pageList:[5,10,15,20],
		//pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'custAddressInfoId',
					title : '客户地址流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.custAddressInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'addressType',
					title : '地址类型',
					width : 40,
					//sortable : true,
					editor: {
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							style:{width:'50'},
							url:"codeQuery/tdCodeQuery?codeType=addressType",
							onHidePanel:function(){
								var ed = custAddressTable.datagrid('getEditor', {index:custAddressTableEditIndex,field:'addressType'});
								var addressTypeName = $(ed.target).combobox('getText');
								custAddressTable.datagrid('getRows')[custAddressTableEditIndex]['addressTypeName'] = addressTypeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.addressTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'addressTypeName',
					title : '地址类型名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.addressTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
				
				{
					field : 'province',
					title : '省',
					width : 50,
					//sortable : true,
					editor: {
						type:'combobox',
						options:{
							valueField:'placeCode',
							textField:'placeName',
							required:true,
							url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
							onSelect:function(){
								var edProvince = custAddressTable.datagrid('getEditor', {index:custAddressTableEditIndex,field:'province'});
								var provinceCode = $(edProvince.target).combobox('getValue');
								var provinceName = $(edProvince.target).combobox('getText');
								custAddressTable.datagrid('getRows')[custAddressTableEditIndex]['provinceName'] = provinceName;
								var edCity = custAddressTable.datagrid('getEditor', {index:custAddressTableEditIndex,field:'city'});
								$(edCity.target).combobox('reset');
								$(edCity.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode);
							}
						}
					},
					formatter : function(value, row, index) {
						return row.provinceName;
					}
				},{
					field : 'provinceName',
					title : '省名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.provinceName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'city',
					title : '市',
					width : 60,
					//sortable : true,
					editor: {
						type:'combobox',
						options:{
							valueField:'placeCode',
							textField:'placeName',
							required:true,
							onSelect:function(){
								var edCity = custAddressTable.datagrid('getEditor', {index:custAddressTableEditIndex,field:'city'});
								var cityCode = $(edCity.target).combobox('getValue');
								var cityName = $(edCity.target).combobox('getText');
								custAddressTable.datagrid('getRows')[custAddressTableEditIndex]['cityName'] = cityName;
								var edCountry = custAddressTable.datagrid('getEditor', {index:custAddressTableEditIndex,field:'country'});
								$(edCountry.target).combobox('reset');
								$(edCountry.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode);
							}
							/*url:contextPath+"/codeQuery/placeCodeQuery/placeType=02"*/
						}
					},
					formatter : function(value, row, index) {
						return row.cityName;
					}
				},{
					field : 'cityName',
					title : '市名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.cityName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'country',
					title : '区/县',
					width : 70,
					//sortable : true,
					editor: {
						type:'combobox',
						options:{
							valueField:'placeCode',
							textField:'placeName',
							required:true,
							onSelect:function(){
								var edCountry = custAddressTable.datagrid('getEditor', {index:custAddressTableEditIndex,field:'country'});
								var countryName = $(edCountry.target).combobox('getText');
								custAddressTable.datagrid('getRows')[custAddressTableEditIndex]['countryName'] = countryName;
								/*var edZipCode = custAddressTable.datagrid('getEditor', {index:custAddressTableEditIndex,field:'zipCode'});
								//console.info(edZipCode);
								//console.info($(edZipCode.target));*/
							}
							/*url:contextPath+"/codeQuery/placeCodeQuery/placeType=03"*/
						}
					},
					formatter : function(value, row, index) {
						return row.countryName;
					}
				},{
					field : 'countryName',
					title : '区/县名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.countryName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'street',
					title : '具体街道地址',
					width : 150,
					//sortable : true,
					editor: {
						type:'validatebox',
						options:{
							required:true,
							validType: 'length[0,100]'   
						}
					},
					formatter : function(value, row, index) {
						return row.street;
					}
				},{
					field : 'zipCode',
					title : '邮政编码',
					width : 50,
					//sortable : true,
					editor: {
						type:'validatebox',
						options:{
							validType: 'validZip'   
						}
					},
					formatter : function(value, row, index) {
						return row.zipCode;
					}
				}]],
		onLoadSuccess : function() {
			$('#modifyCustomerAddressTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			custAddressTableeditOneRow(rowIndex);
		},
		toolbar:"#modifyCustomerAddressTable_tb"
	});
}

/***********************************************************************************************************
 * 	
 * 	地址信息数据表格辅助函数
 * 	点击地址信息数据表格新增，删除，锁定时触发
 * 	@author
 * 	@
 * ***********************************************************************************************************/
var custAddressTableEditIndex;
//增加一行
function custAddressTableAddOneRow(){
	custAddressTableEditIndex = addOneRow(custAddressTable,custAddressTableEditIndex);
}
//删除一行
function custAddressTableRemoveOneRow(){
	removeOneRow(custAddressTable,custAddressTableEditIndex);
	custAddressTableEditIndex = null;
}
//编辑指定行
function custAddressTableeditOneRow(index){
	if(editOneRow(custAddressTable,custAddressTableEditIndex,index)){
		custAddressTableEditIndex = index;
	}
}
//锁定编辑行
function custAddressTableLockOneRow(){
	if(lockOneRow(custAddressTable,custAddressTableEditIndex)){
		custAddressTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
}
/***********************************************************************************************************
 * 	文档页面加载完成后触发该函数
 * 	初始化账号信息数据表格
 * 	@author
 * 	@
 * ***********************************************************************************************************/
var custAccountTable;
function initCustAccountInfo(){
	custAccountTable = $('#modifyCustomerAccountTable').datagrid({
		//title : '账户信息', // 标题
		method : 'post',
		////iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		//collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		//pageList:[5,10,15,20],
		//pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'custAccInfoId',
					title : '客户账户信息流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.custAccInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'bankCode',
					title : '开户行',
					width : 30,
					//sortable : true,
					editor: {
						type:'combobox',
						options:{
							required:true,
							valueField:'bankId',
							textField:'bankName',
							url:"codeQuery/queryBankInfo",
							onHidePanel:function(){
								var ed = custAccountTable.datagrid('getEditor', {index:custAccountTableEditIndex,field:'bankCode'});
								var bankName = $(ed.target).combobox('getText');
								custAccountTable.datagrid('getRows')[custAccountTableEditIndex]['bankName'] = bankName;
							},
							onChange: function(newValue, oldValue){
								if(newValue!=null&&newValue!=""&&newValue!=undefined){
									$(this).next().children(":text").blur(function() {
										var ed = custAccountTable.datagrid('getEditor', {index:custAccountTableEditIndex,field:'bankCode'});
										var allData = $(ed.target).combobox('getData');
										var value = $(ed.target).combobox('getValue');
										if(value==null||value==""||value==undefined){
											$(ed.target).combobox('reset');
										}else{
											var count = 0;
											for(var i=0;i<allData.length;i++){
												var rowData = allData[i];
												if(rowData.bankId==value){
													count++;
												}
											}
											if(count==0){
												$(ed.target).combobox('reset');
											}
										}
									});
								}
							}

						}
					},
					formatter : function(value, row, index) {
						return row.bankName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'bankName',
					title : '开户行名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.bankName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'branchBankName',
					title : '支行名称',
					width : 30,
					editor: {
						type:'validatebox',
						options:{
							required:true,
							validType:"length[0,40]"
						}
					},
					formatter : function(value, row, index) {
						return row.branchBankName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'accName',
					title : '开户名',
					width : 30,
					//sortable : true,
					//editor: 'text',
					editor: {
						type:"validatebox",
						options:{
							required:true,
							validType:"length[0,50]"
						}
					},
					formatter : function(value, row, index) {
						return row.accName;
					}
				},{
					field : 'bankAccNo',
					title : '开户账号',
					width : 80,
					//editor: 'text',
					editor: {
						type:"validatebox",
						options:{
							required:true,
							validType:["validNum","length[0,40]"],
							tipPosition:"left"
						}
					},
					formatter : function(value, row, index) {
						return row.bankAccNo;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#custAccountTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			custAccountTableeditOneRow(rowIndex);
		},
		toolbar:"#modifyCustomerAccountTable_tb"
	});
}
/***********************************************************************************************************
 * 	账户信息数据表格辅助函数
 * 	点击账户信息数据表格新增，删除，锁定时触发
 * 	@author
 * 	@
 * ***********************************************************************************************************/
var custAccountTableEditIndex;
//增加一行
function custAccountTableAddOneRow(){
	custAccountTableEditIndex = addOneRow(custAccountTable,custAccountTableEditIndex);
}
//删除一行
function custAccountTableRemoveOneRow(){
	removeOneRow(custAccountTable,custAccountTableEditIndex);
	custAccountTableEditIndex = null;
}
//编辑指定行
function custAccountTableeditOneRow(index){
	if(editOneRow(custAccountTable,custAccountTableEditIndex,index)){
		custAccountTableEditIndex = index;
	}
	
}
//锁定编辑行
function custAccountTableLockOneRow(){
	if(lockOneRow(custAccountTable,custAccountTableEditIndex)){
		custAccountTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
	
}

/**
 * 财富顾问信息
 */
var modifyCustomerAscriptionTable;
function inItModifyCustomerAscriptionTable(){
	modifyCustomerAscriptionTable = $('#modifyCustomerAscriptionTable').datagrid({
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : contextPath + "/productOrder/getAllPdAmountOrderInfo", // 数据来源
		//sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		//pageSize : 10,
		//pageList : [ 5, 10, 15, 20, 25, 30 ],
		columns : [[
		            {
		            	field : 'ck',
		            	checkbox : true
		            },
		            {
		            	field : 'comName',
		            	title : '分公司',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.comName;
		            	}
		            	
		            },
		            {
		            	field : 'departmentName',
		            	title : '部门',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.departmentName;
		            	}
		            	
		            },
		            {
		            	field : 'storeName',
		            	title : '网点',
		            	width : 200,
		            	hidden:true,
		            	formatter : function(value,row,index){
		            		return row.storeName;
		            	}
		            	
		            },
		            {
		            	field : 'agentName',
		            	title : '财富顾问',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.agentName;
		            	}
		            	
		            },
		            {
		            	field : 'agentCode',
		            	title : '工号',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.agentCode;
		            	}
		            	
		            }]]
	});
}
/***********************************************************************************************************
 * 	
 * 	点击客户号旁搜索框是触发
 * 	生成新窗口，用于搜索该理财经理自己名下的客户/准客户
 * 	@author
 * 	@
 * ***********************************************************************************************************/
function openSearchCustWindow(){
	$('<div id="searchCustWindow"/>').dialog({
		href : contextPath+"/customer/searchCustomer",
		//type : "post",
		modal : true,
		title : "客户查询",
		//fit : true, 
		width:800,
		height:500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}


/***********************************************************************************************************
 * 	
 * 	根据客户基本信息流水号和理财经理流水号请求服务器获取该客户相关信息
 * 	将服务器返回的客户相关信息给客户基本信息页面赋值
 * 	@author LiuYi
 * 	@
 * ***********************************************************************************************************/
function getModifyCustomerBaseInfo( custBaseInfoId, agentId ){
	var param = {};
	param.custBaseInfoId = custBaseInfoId;
	param.agentId = agentId;
	$.ajax({
		type:'post', 
		//async: false, // 设置为同步方法
		url:contextPath+"/modifyCustomer/getModifyCustomerBaseInfoUrl",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if (resultObj.custBaseInfoAndContactInfo!=null&&resultObj.custBaseInfoAndContactInfo!=undefined&&resultObj.custBaseInfoAndContactInfo!=""){
						//设置客户级别，02准客户或01签单客户
						//$("#modifyCustomerBaseInfo_CustLevel").combobox('setValue',resultObj.custBaseInfoAndContactInfo.custLevel);
						//modifyCustomerBaseInfo_custLevel = $("#modifyCustomerBaseInfo_CustLevel").combobox('getValue');
						modifyCustomerBaseInfo_custLevel = resultObj.custBaseInfoAndContactInfo.custLevel;
						// 若服务器端客户级别不为01，则默认为准客户
						if (  modifyCustomerBaseInfo_custLevel != "01") {
							$("#modifyCustomerBaseInfo_CustLevel").combobox('setValue','02');
							setCustBaseInfoEnable();
						} else {
							// 身份证号与邮箱与手机号设置为非必录
							setIdNoNotRequired();
							setMobileNotRequired();
							emailNotRequired();
							// 声明设置为非必录
							setNameNotRequired();
							// 设置客户五要素与手机号和邮箱号不可修改和置灰
							setCustBaseInfoDisabled(modifyCustomerBaseInfo_custLevel);
							// 声明变量存储idType
							var idType = resultObj.custBaseInfoAndContactInfo.idType;
							// 设置客户性别和出生日期不可修改和置灰
							setCustSexAndBirthDayDisabled(idType);
						}
						//setIdAndGenderDisabled();
						// 将服务器端数据赋给表单Form
						setInputValueById("modifyCustomerBaseInfoForm",resultObj.custBaseInfoAndContactInfo);
						//$("#modifyCustomerBaseInfo_Birthday").datebox("setValue",resultObj.custBaseInfoAndContactInfo.birthday);
					}
					// SVN以前代码
					/*if(resultObj.custAddressInfoMapList!=null&&resultObj.custAddressInfoMapList!=undefined&&resultObj.custAddressInfoMapList!=""){
						clearAllRows(custAddressTable);
						loadJsonObjData("modifyCustomerAddressTable",resultObj.custAddressInfoMapList);				
					}
					if(resultObj.custAccInfoMapList!=null&&resultObj.custAccInfoMapList!=undefined&&resultObj.custAccInfoMapList!=""){
						clearAllRows(custAccountTable);
						loadJsonObjData("modifyCustomerAccountTable",resultObj.custAccInfoMapList);				
					}
					if(resultObj.custAscriptionMapList!=null&&resultObj.custAscriptionMapList!=undefined&&resultObj.custAscriptionMapList!=""){
						clearAllRows(modifyCustomerAscriptionTable);
						loadJsonObjData("modifyCustomerAscriptionTable",resultObj.custAscriptionMapList);	
					}*/
					// 将服务器端数据赋给页面需先将datagrid以前显示的数据清除
					clearAllRows(custAddressTable);
					clearAllRows(custAccountTable);
					clearAllRows(modifyCustomerAscriptionTable);
					if(resultObj.custAddressInfoMapList!=null&&resultObj.custAddressInfoMapList!=undefined&&resultObj.custAddressInfoMapList!=""){
						loadJsonObjData("modifyCustomerAddressTable",resultObj.custAddressInfoMapList);				
					}
					if(resultObj.custAccInfoMapList!=null&&resultObj.custAccInfoMapList!=undefined&&resultObj.custAccInfoMapList!=""){
						loadJsonObjData("modifyCustomerAccountTable",resultObj.custAccInfoMapList);				
					}
					if(resultObj.custAscriptionMapList!=null&&resultObj.custAscriptionMapList!=undefined&&resultObj.custAscriptionMapList!=""){
						loadJsonObjData("modifyCustomerAscriptionTable",resultObj.custAscriptionMapList);	
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		});
}
/***********************************************************************************************************
 * 	
 * 	点击【提交】按钮触发该事件
 * 	提交客户基本信息
 * 	校验客户信息是否与已存在数据发生冲突，保存成功后才会显示客户拜访信息，客户投资信息，客户个人信息录入页面
 * 	@author LiuYi
 * 	@
 * ***********************************************************************************************************/
function setCustSexAndBirthDayDisabled(idType){
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		$('#modifyCustomerBaseInfo_Birthday').datebox("disable");
		$('#modifyCustomerBaseInfo_Sex').combobox("disable");
	}else{
		$('#modifyCustomerBaseInfo_Birthday').datebox("enable");
		$('#modifyCustomerBaseInfo_Sex').combobox("enable");
	}
}
/***********************************************************************************************************
 * 	
 * 	点击【提交】按钮触发该事件
 * 	提交客户基本信息
 * 	校验客户信息是否与已存在数据发生冲突，保存成功后才会显示客户拜访信息，客户投资信息，客户个人信息录入页面
 * 	@author LiuYi
 * 	@
 * ***********************************************************************************************************/
function setIdAndGenderDisabled(){
	var idType = $('#modifyCustomerBaseInfo_IdType').combobox("getValue");
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		$('#modifyCustomerBaseInfo_Birthday').datebox("disable");
		$('#modifyCustomerBaseInfo_Sex').combobox("disable");
	}else{
		$('#modifyCustomerBaseInfo_Birthday').datebox("enable");
		$('#modifyCustomerBaseInfo_Sex').combobox("enable");
	}
}
/***********************************************************************************************************
 * 	
 * 	点击【提交】按钮触发该事件
 * 	提交客户基本信息
 * 	校验客户信息是否与已存在数据发生冲突，保存成功后才会显示客户拜访信息，客户投资信息，客户个人信息录入页面
 * 	@author LiuYi
 * 	@
 * ***********************************************************************************************************/
function submitCustomerBaseInfo() {
	if(!$("#modifyCustomerBaseInfoForm").form("validate") ) {  // 客户基本信息form表单字段验证
		return;
	}
	if(!verifyCustTradeInfo()) {   // 校验客户交易
		return;
	}
	// 使用EasyUI 提示框
	$.messager.confirm('提示', '请仔细核对客户基本信息,确认无误后点击【确定】按钮', function(r) {
		if (r) {
			//去掉用户输入姓名时无意追加的空格字符
			var name = $.trim($("#modifyCustomerBaseInfo_ChnName").val());  
			$("#modifyCustomerBaseInfo_ChnName").val(name);
			var custBaseInfoId = $('#modifyCustomerBaseInfo_Id').val();
			//获取客户级别
			var custLevel = $("#modifyCustomerBaseInfo_CustLevel").combobox('getValue');
			/**若提交时，该客户的级别为准客户，则需进行几项验证，只有验证通过后才可提交保存*/
			if (custLevel == '02' || custLevel == '02-准客户' || custLevel == "" || custLevel == undefined || custLevel == null )  {
				/*if(!verifyInvestCustInfo()) {   //校验专业投资者是否上传资产证明
					return;
				}*/
				verifyCustomerBaseInfo();
				if(operate=="addCust"){
					$.messager.alert("提醒","请上传客户身份证复印件!",'info');
					var custType = $("#modifyCustomerBaseInfo_InvestCustomerType").combobox('getValue');
					if(custType == "02"){
						$.messager.alert("提醒","请上传有效的资产证明或收入证明!",'info');
					}
				}else{
					verifyInvestCustInfo();
					//校验客户是否上传身份证复印件
					queryCustIdCardUpload();
				}
			}else{
				//校验客户是否上传身份证复印件
				queryCustIdCardUpload();
				//提交客户信息
				submitModifyCustomerBaseInfo();
			}
			
		}
	});
	return;
}

/**
 * 校验客户相关信息
 * */
function verifyCustomerBaseInfo(){
	/****************************验证客户提交的信息是否与自己的客户/准客户发生冲突*********************/
	var verifyOwnCustInfoBakData = checkCollisionWithOwnCust();
	// 若验证客户信息出错，则提示用户并直接放弃本次提交
	if (!verifyOwnCustInfoBakData.success) { 
		$.messager.alert('提示', verifyOwnCustInfoBakData.msg, 'info');
		return;
	}else{
		var resultMap = verifyOwnCustInfoBakData.obj;
		var returnMsg = verifyOwnCustInfoBakData.msg;
		if(resultMap!=null&&resultMap!=""&&resultMap!=undefined){
			var errCode = resultMap.errorCode;
			if(errCode!=null&&errCode!=""&&errCode!=undefined){
				if(errCode=="01"){//未获取到理财经理信息
					$.messager.alert('提示', returnMsg, 'info');
					return;
				} else if(errCode=="02"){//与自己名下客户冲突
					$.messager.confirm('确认对话框', returnMsg, function(r){
						if (r){
							var custBaseInfoObj = resultMap.resultData;
							getModifyCustomerBaseInfo(custBaseInfoObj.custBaseInfoId,custBaseInfoObj.agentId);
							setCustBaseInfoDisabled(custBaseInfoObj.custLevel);
						}
					});
					return;
				} else  if(errCode=="03"){//与他人签单客户冲突
					$.messager.alert('提示', returnMsg, 'info');
					return;
				} else  if(errCode=="04"){//与他人准客户冲突
					$.messager.confirm('确认对话框', returnMsg, function(r){
						if (r){
							//提交客户信息
							submitModifyCustomerBaseInfo();
						}
					});
					return;
				}else if (errCode=="05") { // 手机号与自己的签单客户或准客户冲突
					$.messager.confirm('确认对话框', returnMsg, function(r){
						if (r){
							var custBaseInfoObj = resultMap.resultData;
							getModifyCustomerBaseInfo(custBaseInfoObj.custBaseInfoId,custBaseInfoObj.agentId);
							setCustBaseInfoDisabled(custBaseInfoObj.custLevel);
						}
					});
					return;
				}else if(errCode=="06") { // 手机号与他人签单客户冲突，则给出警告并返回
					$.messager.alert('提示', returnMsg, 'info');
					return;
				} else if(errCode=="07") { // 手机号与他人准客户冲突，则给出提示，用户可继续提交
					$.messager.confirm('确认对话框', returnMsg, function(r){
						if (r){
							//提交客户信息
							submitModifyCustomerBaseInfo();
						}
					});
					return;
				} else if(errCode=="08") { // 身份证号与自己签单客户或准客户冲突，点击【确定】可将已有信息带出，【取消】则放弃提交
					$.messager.confirm('确认对话框', returnMsg, function(r){
						if (r){
							var custBaseInfoObj = resultMap.resultData;
							getModifyCustomerBaseInfo(custBaseInfoObj.custBaseInfoId,custBaseInfoObj.agentId);
							setCustBaseInfoDisabled(custBaseInfoObj.custLevel);
						}
					});
					return;
				} else if(errCode=="09") { // 身份证号与他人签单客户冲突
					$.messager.alert('提示', returnMsg, 'info');
					return;
				} else if(errCode=="10") { // 身份证号与自己准客户冲突
					$.messager.confirm('确认对话框', returnMsg, function(r){
						if (r){
							//提交客户信息
							submitModifyCustomerBaseInfo();
						}
					});
					return;
				} else if(errCode=="11") { // 与自己准客户姓名冲突
					$.messager.confirm('确认对话框', returnMsg,  function(r) {
							if (r) {
								// 用户选择继续且提交客户信息
								submitModifyCustomerBaseInfo();
							}			
					});
					return;
				} else {
					//提交客户信息
					submitModifyCustomerBaseInfo();
				}
			}
		}
	}
}



/**
 * 打开所有tab页
 * */
function openAllTabs(){
	// 获取成功保存的客户/准客户基本信息流水号和客户号
	var param={};
	param.agentId = $("#modifyCustomerBaseInfo_agentId").val();
	param.custBaseInfoId = $("#modifyCustomerBaseInfo_Id").val();
	param.loadFlag = operate;
	// 打开拜访信息，投资信息和个人信息录入页面
	$('#modifyCustomerBaseInfoTabs').tabs({}); 
	//若存在拜访信息和投资信息和个人信息tab,则先删除****/
	//$('#modifyCustomerBaseInfoTabs').tabs('close', '拜访记录');
	$('#modifyCustomerBaseInfoTabs').tabs('close', '投资记录');
	$('#modifyCustomerBaseInfoTabs').tabs('close', '风控问卷');
	//$('#modifyCustomerBaseInfoTabs').tabs('close', '财富信息');
	// 拜访记录tab页
	$('#modifyCustomerBaseInfoTabs').tabs('add',{
		title: '拜访记录',
		selected: false,
		closable:true,
		href : contextPath+"/modifyCustomer/modifyCustomerVisitorInfoUrl?param="+$.toJSON(param)
	});
	$('#modifyCustomerBaseInfoTabs').tabs('add',{
		title: '投资记录',
		selected: false,
		closable:true,
		href : contextPath+"/modifyCustomer/modifyCustomerInvestInfoUrl?param="+$.toJSON(param)
	});
	$('#modifyCustomerBaseInfoTabs').tabs('add',{
		title: '风控问卷',
		selected: false,
		closable:true,
		href : contextPath+"/modifyCustomer/modifyCustomerQuestionnaireInfo?param="+$.toJSON(param)
	});
	/*$('#modifyCustomerBaseInfoTabs').tabs('add',{
		title: '财富信息',
		selected: false,
		closable:true,
		href : contextPath+"/modifyCustomer/modifyCustomerPersonalInfoUrl?param="+$.toJSON(param)
	});*/
}



/***********************************************************************************************************
 * 	
 * 	校验客户的交易信息
 * 	校验客户是否正在进行交易
 * 	@author LiuYi
 * 	@
 * ***********************************************************************************************************/
function verifyCustTradeInfo() {
	var custBaseInfoId = $("#modifyCustomerBaseInfo_Id").val();
	if(custBaseInfoId==null||custBaseInfoId==""||custBaseInfoId==undefined){
		return true;
	}
	var tradeId = $("#modifyCustomerBaseInfo_tradeId").val();
	if(tradeId!=null&&tradeId!=""&&tradeId!=undefined){
		return true;
	}else{
		var verifyPassFlag = false;
		$.ajax({
			type:'post',
			async: false,//设置为同步方法
			url:'customer/verifyCustomerTradeInfo',
			data:'custBaseInfoId='+custBaseInfoId,
			cache:false,
			success:function(resultInfo){
				try {
					if(resultInfo.success){
						verifyPassFlag = true;
					}else{
						$.messager.alert('提示', resultInfo.msg);
						verifyPassFlag = false;
					}
				} catch (e) {
					$.messager.alert('提示', e);
					verifyPassFlag = false;
				}
			}
		});
		return verifyPassFlag;
	}
}
/***********************************************************************************************************
 * 	
 * 	提交客户基本信息 
 * 	用户录入的客户基本信息验证通过后，提交该客户基本信息到服务器
 * 	@author LiuYi
 * 	@
 * ***********************************************************************************************************/
function submitModifyCustomerBaseInfo(){
	var  backData;
	var name = $("#modifyCustomerBaseInfo_ChnName").val();  //去掉用户姓名结尾后无意输入的空格
	name = $.trim(name);
	$("#modifyCustomerBaseInfo_ChnName").val(name);
	setCustBaseInfoEnable();
	var formData = $("#modifyCustomerBaseInfoForm").serialize();  //表单数据序列化
	formData = formDataToJsonStr(formData);
	var param = {};
	param.custLevel = $("#modifyCustomerBaseInfo_CustLevel").combobox('getValue');
	$.ajax({
		type:'post',
		//async: false, // 设置为同步方法
		url:'modifyCustomer/updateModifyCustomerBaseInfoUrl',
		data:'modifyCustomerBaseInfoData='+formData+'&param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(returnData){
			try {
				backData = returnData;
				if(returnData.success){
					// 客户基本信息提交成功后，保存服务器返回的数据
					$("#modifyCustomerBaseInfo_CustomerNo").val( returnData.obj.customerNo );
					$("#modifyCustomerBaseInfo_Id").val( returnData.obj.custBaseInfoId );
					$("#modifyCustomerBaseInfo_agentId").val(returnData.obj.agentId);
					setCustBaseInfoDisabled(returnData.obj.custLevel);
					// 请求服务器获取财富顾问信息
					//debugger;
					modifyCustomerAscriptionTable.datagrid('options').url = "modifyCustomer/searchAgentInfo";
					var searchParam = {};
					searchParam.agentId = returnData.obj.agentId;
					modifyCustomerAscriptionTable.datagrid( 'load', {searchParam:$.toJSON(searchParam)} );	
					//打开其他tab页
					openAllTabs();
					$.messager.alert('提示', returnData.msg);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	return backData;
}


function setCustBaseInfoEnable(){
	//提交之前先让禁用的控件启用，否则提交后其值丢失
	//$('#modifyCustomerBaseInfo_CustLevel').combobox("enable");
	$("#modifyCustomerBaseInfo_ChnName").removeAttr("disabled");
	// 设置姓名为必录
	setNameRequired();
	$('#modifyCustomerBaseInfo_Sex').combobox("enable");
	$('#modifyCustomerBaseInfo_IdType').combobox("enable");
	$("#modifyCustomerBaseInfo_IdNo").removeAttr("disabled");
	$('#modifyCustomerBaseInfo_Birthday').datebox("enable");
	$("#modifyCustomerBaseInfo_Mobile").removeAttr("disabled");
	$("#modifyCustomerBaseInfo_Email").removeAttr("disabled");
}



/***********************************************************************************************************
 * 		
 * 		提交客户联系地址信息和账户信息
 * 		@author LiuYi
 * 		@
 * ***********************************************************************************************************/
function submitCustomerAddressAndAccInfo(){
	modifyCustomerBaseInfo_Id = $('#modifyCustomerBaseInfo_Id').val();
	if ( modifyCustomerBaseInfo_Id == null && modifyCustomerBaseInfo_Id == "" && modifyCustomerBaseInfo_Id == undefined) {
		$.messager.alert('提示', "请先保存客户基本信息!", 'info');
	}
	var param={};
	//判断添加的信息是否有误
	if(!custAddressTableLockOneRow()){
		$.messager.alert('提示', "联系地址信息输入有误！", 'info');
		return;
	}
	if(!custAccountTableLockOneRow()){
		$.messager.alert('提示', "账户信息输入有误！", 'info');
		return;
	}
	
	var custAddressInfo = $("#modifyCustomerAddressTable").datagrid("getRows");
	if(custAddressInfo==null||custAddressInfo==undefined||custAddressInfo.length==0){
		$.messager.alert('提示', "请录入客户地址信息！", 'info');
		return;
	}
	
	/*var custAccountInfo = $("#modifyCustomerAccountTable").datagrid("getRows");
	if(custAccountInfo==null||custAccountInfo==undefined||custAccountInfo.length==0){
		$.messager.alert('提示', "请录入客户账户信息！", 'info');
		return;
	}*/
	
	param.custBaseInfoId = modifyCustomerBaseInfo_Id;
	param.agentId = $('#modifyCustomerBaseInfo_agentId').val();
	param.custLevel = $("#modifyCustomerBaseInfo_CustLevel").combobox('getValue');
	param.custAccountTable = $.toJSON($("#modifyCustomerAccountTable").datagrid("getRows"));
	param.custAddressTable = $.toJSON($("#modifyCustomerAddressTable").datagrid("getRows"));
	//校验客户姓名与账户姓名
	var chnName = $('#modifyCustomerBaseInfo_ChnName').val();
	var custAccountTable = $("#modifyCustomerAccountTable").datagrid("getRows");
	var msg = 0;
	$.each(custAccountTable, function(i, custAccount) {
		var accName = custAccount.accName;
		if(accName != chnName){ 
			return  msg = 1;
		}
	});
	if(msg == 1){
		$.messager.confirm('提示', '客户开户名与客户姓名不一致,是否继续保存操作？', function(r) {
			if(r){
				var data = $.toJSON(param);
				data = escape(encodeURIComponent(data));
				$.ajax({
					type : 'post',
					url : contextPath + "/modifyCustomer/submitCustomerAddressAndAccInfoUrl",
					data : 'param='+data,
					cache : false,
					success : function(resultInfo){
						try {
							if (resultInfo.success) {
								$.messager.alert('提示', resultInfo.msg,"info");
								$('#modifyCustomerBaseInfo_submitCarInfoButton').attr('disabled',"true");
								// 地址信息与账户信息重新赋值
								var resultObj = resultInfo.obj;
								// 若地址信息与账户信息不为空,则重新赋值
								if(resultObj.addressList!=null&&resultObj.addressList!=undefined&&resultObj.addressList!=""){
									// 将服务器端数据赋给页面需先将datagrid以前显示的数据清除
									clearAllRows(custAddressTable);
									loadJsonObjData("modifyCustomerAddressTable",resultObj.addressList);				
								}
								if(resultObj.accList!=null&&resultObj.accList!=undefined&&resultObj.accList!=""){
									// 将服务器端数据赋给页面需先将datagrid以前显示的数据清除
									clearAllRows(custAccountTable);
									loadJsonObjData("modifyCustomerAccountTable",resultObj.accList);				
								}
							} else {
								$.messager.alert('提示', resultInfo.msg);
							}
						} catch (e) {
							$.messager.alert('提示', e);
						}
					}
				});
			}
		});
		return;
	}else{
	
	var data = $.toJSON(param);
	data = escape(encodeURIComponent(data));
	$.ajax({
		type : 'post',
		url : contextPath + "/modifyCustomer/submitCustomerAddressAndAccInfoUrl",
		data : 'param='+data,
		cache : false,
		success : function(resultInfo){
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg,"info");
					$('#modifyCustomerBaseInfo_submitCarInfoButton').attr('disabled',"true");
					// 地址信息与账户信息重新赋值
					var resultObj = resultInfo.obj;
					// 若地址信息与账户信息不为空,则重新赋值
					if(resultObj.addressList!=null&&resultObj.addressList!=undefined&&resultObj.addressList!=""){
						// 将服务器端数据赋给页面需先将datagrid以前显示的数据清除
						clearAllRows(custAddressTable);
						loadJsonObjData("modifyCustomerAddressTable",resultObj.addressList);				
					}
					if(resultObj.accList!=null&&resultObj.accList!=undefined&&resultObj.accList!=""){
						// 将服务器端数据赋给页面需先将datagrid以前显示的数据清除
						clearAllRows(custAccountTable);
						loadJsonObjData("modifyCustomerAccountTable",resultObj.accList);				
					}
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	}
}
/***********************************************************************************************************
 * 
 * 	点击"返回"按钮触发事件函数
 * 	返回时需判断是返回交易页面还是客户信息维护页面
 * 	若返回到交易页面
 * 	@author LiuYi
 * 	@
 * ***********************************************************************************************************/
function modifyCustomerBaseInfoBack() {
	// 返回交易页面时，判断客户基本信息表单必录项是否缺少
	if (modifyCustomerBaseInfo_tradeId != null && modifyCustomerBaseInfo_tradeId != "" && modifyCustomerBaseInfo_tradeId != undefined ) {
		if(!$("#modifyCustomerBaseInfoForm").form("validate") ) {  // 客户基本信息form表单字段验证
			$.messager.alert('提示',"请先输入客户基本信息的必录项并提交保存后再返回");
			return;
		}
	}
	// 返回变量
	var returnDataByServer;
	var returnDateBycheckCustAdressAndAccInfo;
	//返回到交易页面
	if( modifyCustomerBaseInfo_tradeId!=null&&modifyCustomerBaseInfo_tradeId!=""&&modifyCustomerBaseInfo_tradeId!=undefined){
		var agentId = $("#modifyCustomerBaseInfo_agentId").val();
		var custBaseInfoId = $("#modifyCustomerBaseInfo_Id").val();
		if(custBaseInfoId==null||custBaseInfoId==""||custBaseInfoId==undefined){
			$.messager.alert('提示', "请录入客户信息或选择已有客户！");
			return;
		}
		var param = {};
		param.agentId = agentId;
		param.custBaseInfoId = custBaseInfoId;
		param.tradeId = $("#modifyCustomerBaseInfo_tradeId").val();
		//var tradeId = $("#modifyCustomerBaseInfo_tradeId").val();
		returnDataByServer = checkTradeInfoInput(custBaseInfoId);  // 校验准客户是否已经发生交易
		if ( !returnDataByServer.success ) {    // 该准客户已经发生交易，则不能返回交易页面
			$.messager.alert('提示',returnDataByServer.msg);
			return;
		}
		returnDateBycheckCustAdressAndAccInfo = checkCustAdressAndAccInfo(param);//校验客户地址信息和账户信息是否已经录入
		if(!returnDateBycheckCustAdressAndAccInfo.success){
			$.messager.alert('提示',returnDateBycheckCustAdressAndAccInfo.msg);
			return;
		}
		//判断此客户是否为此财富顾问的客户
		$.ajax({
						type:'post',
						url:'customer/getCustByAgentInfo',
						data:'param='+$.toJSON(param),
						cache:false,
						success:function(returnData){
							try {
								var jsonObj = returnData.obj;
								if(returnData.success){
									$("#modifyCustomerBaseInfo_SexName").val( $("#modifyCustomerBaseInfo_Sex").combobox("getText") );
									$("#modifyCustomerBaseInfo_IdTypeName").val( $("#modifyCustomerBaseInfo_IdType").combobox("getText") );
									// 表单序列化之前先将置灰的标签启用
									setCustBaseInfoEnable();
									// 表单序列化及转为json数据
									var custBaseInfo = formDataToJsonStr($("#modifyCustomerBaseInfoForm").serialize());
									// json数据添加到交易页面客户信息数据表格
									appendTradeCustomerInfo(custBaseInfo);
									// 销毁当前窗口
									$("#addCustomeInputWindow").window('destroy');
									//parent.clearForm();
								}else{
									$.messager.alert('提示', returnData.msg);
								}
							} catch (e) {
								$.messager.alert('提示', e);
							}
						}
		});
		//调用交易js中方法 查询并加载客户账户和地址信息
		parent.initTradeBankInfoInputId(custBaseInfoId,"getByCustId");
		parent.initTradeAddressInfoInputId(custBaseInfoId,"getByCustBaseInfoId");
	}
	else
	{
		$("#addcustomerWindow").window('destroy');
		//queryCustomerList();
		//parent.clearForm();
	}
}
/***********************************************************************************************************
 *
 *			查找客户并选中后，返回到该页面
 *			在点击客户号旁搜索按钮时，在弹出的窗口中选择已有客户信息后，会将该客户信息赋给页面
 *			@author YuanZhengJun
 *			@
 * ***********************************************************************************************************/
function searchCustomerInfoBack( custBaseInfo ){
	//var custLevel = $('#modifyCustomerBaseInfo_CustLevel').combobox('getValue');
	//若客户信息不为空，则将其信息更新至页面
	if( custBaseInfo!=null && custBaseInfo!="" && custBaseInfo!=undefined ){ 
		//若存在拜访信息和投资信息和个人信息tab,则先删除
		//$('#modifyCustomerBaseInfoTabs').tabs('close', '拜访记录');  
		//$('#modifyCustomerBaseInfoTabs').tabs('close', '投资记录');
		$('#modifyCustomerBaseInfoTabs').tabs('close', '风控问卷');
		//$('#modifyCustomerBaseInfoTabs').tabs('close', '财富信息');
		
		var custLevel = custBaseInfo.custLevel;
		if( verifyCustBelongAgentInfo(custBaseInfo) ){  
			//将控件以前的状态清空
			/*$('#modifyCustomerBaseInfo_Sex').combobox("enable");  
			$('#modifyCustomerBaseInfo_IdType').combobox("enable");
			$('#modifyCustomerBaseInfo_Birthday').datebox("enable");*/ // 以前版本
							//$('#modifyCustomerBaseInfo_Sex').combobox("disable");
			// Form表单清空
			$("#modifyCustomerBaseInfoForm").form("clear");  	
			//清空账户数据表格与地址数据表格清空与财富顾问归属数据表格清空
			$('#modifyCustomerAddressTable').datagrid('loadData', { total: 0, rows: [] });			
			$('#modifyCustomerAccountTable').datagrid('loadData', { total: 0, rows: [] }); 			
			$('#modifyCustomerAscriptionTable').datagrid('loadData', { total: 0, rows: [] }); 	
			// 调用公共方法将客户基本信息赋给页面
			//setInputValueById("modifyCustomerBaseInfo_top",custBaseInfo); 	 
			// 关闭弹出的模态化窗口
			$('#searchCustWindow').window('close');
			//$('#modifyCustomerBaseInfo_CustLevel').combobox('setValue','02'); 
			// 将签单客户五要素置灰
			//setFiveElementsDisabled( custLevel, custBaseInfo ); 
			// 设置签单客户身份证与性别
			//setIdNoAndSexDesabled(custLevel ); 
			//将客户其他相关信息加载到页面
			var custBaseInfoId = custBaseInfo.custBaseInfoId;
			var agentId = custBaseInfo.agentId;
			// 加载服务器数据到页面
			getModifyCustomerBaseInfo( custBaseInfoId, agentId); 
			/**交易号不为空*/
			if ( modifyCustomerBaseInfo_tradeId != null && modifyCustomerBaseInfo_tradeId != "" 
												&& modifyCustomerBaseInfo_tradeId != undefined ) {
				tradeLoadFlag = "tradeLoadFlag";
			}
			var param={};
			param.custBaseInfoId = custBaseInfoId;
			param.agentId = agentId;
			param.tradeLoadFlag = tradeLoadFlag;
			if (custBaseInfoId != null && custBaseInfoId != "" && custBaseInfoId != undefined    
					&& agentId != null && agentId != "" && agentId != undefined) {    // 如果客户基本信息流水号与财富顾问均不为空，则打开其他三个tab页
				/*$('#modifyCustomerBaseInfoTabs').tabs('add',{
					title: '拜访记录',
					selected: false,
					closable:true,
					href : contextPath+"/modifyCustomer/modifyCustomerVisitorInfoUrl?param="+$.toJSON(param)
				});*/
				/*$('#modifyCustomerBaseInfoTabs').tabs('add',{
					title: '投资记录',
					selected: false,
					closable:true,
					href : contextPath+"/modifyCustomer/modifyCustomerInvestInfoUrl?param="+$.toJSON(param)
				});*/
				$('#modifyCustomerBaseInfoTabs').tabs('add',{
					title: '风控问卷',
					selected: false,
					closable:true,
					href : contextPath+"/modifyCustomer/modifyCustomerQuestionnaireInfo?param="+$.toJSON(param)
				});
				/*$('#modifyCustomerBaseInfoTabs').tabs('add',{
					title: '财富信息',
					selected: false,
					closable:true,
					href : contextPath+"/modifyCustomer/modifyCustomerPersonalInfoUrl?param="+$.toJSON(param)
				});*/
			}
		}
	}
}
/***********************************************************************************************************
 * 
 * 	校验客户理财经理信息
 * 	@author YuanZhengJun
 * 	@
 * ***********************************************************************************************************/
function verifyCustBelongAgentInfo(custBaseInfo){
	var param = {};
	var agentId = $("#modifyCustomerBaseInfo_agentId").val();
	param.custBaseInfo = custBaseInfo;
	param.agentId = agentId;

	var verifyPassFlag = false;
	$.ajax({
		type:'post',
		async: false,//设置为同步方法
		url:'customer/verifyCustBelongAgent',
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(resultInfo){
			try {
				if(resultInfo.success){
					verifyPassFlag = true;
				}else{
					$.messager.alert('提示', resultInfo.msg);
					verifyPassFlag = false;
				}
			} catch (e) {
				$.messager.alert('提示', e);
				verifyPassFlag = false;
			}
		}
	});
	return verifyPassFlag;
}
/***********************************************************************************************************
 * 
 * 	证件类型为身份证时，设置生日和性别
 * 	@author  YuanZhengJun
 * 	@
 * ***********************************************************************************************************/
function setIdNoAndSexDesabled( custLevel ){
	if ( custLevel != null && custLevel != "" && custLevel != undefined && custLevel == "01" ) {
		var idType = $('#modifyCustomerBaseInfo_IdType').combobox("getValue");
		if( idType!=null&&idType!=""&&idType!=undefined&&idType=="1" ){
			$('#modifyCustomerBaseInfo_Birthday').datebox("disable");
			$('#modifyCustomerBaseInfo_Sex').combobox("disable");
		}else{
			$('#modifyCustomerBaseInfo_Birthday').datebox("enable");
			$('#modifyCustomerBaseInfo_Sex').combobox("enable");
		}
	}
}
/***********************************************************************************************************
 * 	
 * 	设置五要素
 * 	@author LiWenTao
 * 	@
 * ***********************************************************************************************************/
function setFiveElementsDisabled( custLevel, custBaseInfo ) {
	if ( custLevel != null && custLevel != "" && custLevel != undefined && custLevel == "01") {
		/*设置客户姓名文本框置灰和只读*/
		$('#modifyCustomerBaseInfo_ChnName').validatebox('disabled',true);
		//$( '#modifyCustomerBaseInfo_ChnName' ).attr( {readonly : 'true'} );
		//$( '#modifyCustomerBaseInfo_ChnName' ).attr( "disabled", "true" );
		$( '#modifyCustomerBaseInfo_IdNo' ).attr( {readonly : 'true'} );
		//$( '#modifyCustomerBaseInfo_IdNo' ).attr( "disabled", "true" );
		//$( '#modifyCustomerBaseInfo_Mobile' ).attr( {readonly : 'true'} ); // 手机号
		//$( '#modifyCustomerBaseInfo_Mobile' ).attr( "disabled", "true" );
		//$( '#modifyCustomerBaseInfo_Email' ).attr( {readonly : 'true'} ); // 邮箱
		//$( '#modifyCustomerBaseInfo_Email' ).attr( "disabled", "true" );
		$('#modifyCustomerBaseInfo_IdType').combobox("setValue",custBaseInfo.idType);
		$('#modifyCustomerBaseInfo_IdType').combobox("disable");
		$('#modifyCustomerBaseInfo_Birthday').datebox("disable");
		$('#modifyCustomerBaseInfo_Sex').combobox("disable");
		/*设置客户级别*/
		$('#modifyCustomerBaseInfo_CustLevel').combobox("setText", "01-签单客户");
		//$("#modifyCustomerBaseInfo_Sex").combobox("setValue",custBaseInfo.sex);
		//$("#modifyCustomerBaseInfo_Birthday").datebox("setValue",custBaseInfo.birthday);
	}
	else 
	{
		/*设置客户级别*/
		$('#modifyCustomerBaseInfo_CustLevel').combobox("setText", "02-准客户");
		/*去掉五要素以前属性*/
		/*$("#modifyCustomerBaseInfo_ChnName").removeAttr("disabled");
		$('#modifyCustomerBaseInfo_Sex').combobox("enable");
		$('#modifyCustomerBaseInfo_IdType').combobox("setValue",custBaseInfo.idType);
		$("#modifyCustomerBaseInfo_IdNo").removeAttr("disabled");
		$('#modifyCustomerBaseInfo_Birthday').datebox("enable");
		$("#modifyCustomerBaseInfo_Mobile").removeAttr("disabled");
		$("#modifyCustomerBaseInfo_Email").removeAttr("disabled");*/
		$("#modifyCustomerBaseInfo_ChnName").removeAttr("disabled");
		$("#modifyCustomerBaseInfo_IdNo").removeAttr("disabled");
		$('#modifyCustomerBaseInfo_Sex').combobox("enable");
		$("#modifyCustomerBaseInfo_Mobile").removeAttr("disabled");
		$("#modifyCustomerBaseInfo_Email").removeAttr("disabled");
		//$('#modifyCustomerBaseInfo_IdType').combobox("setValue",custBaseInfo.idType);
	}
}
/***********************************************************************************************************
 * 
 * 	校验身份证
 * 	@returns {Boolean}
 * ***********************************************************************************************************/
function verifyIdNo(){
	//身份证号码格式校验
	var idType = $("#modifyCustomerBaseInfo_IdType").combobox("getValue");
	var idNo = $("#modifyCustomerBaseInfo_IdNo").val();
	if(idNo!=null&&idNo!=""&&idNo!=undefined&&(idType==null||idType==""||idType==undefined)){
		$("#modifyCustomerBaseInfo_IdType").combobox({required:true});
	}else if(idNo==null||idNo==""||idNo==undefined&&(idType==null||idType==""||idType==undefined)){
		$("#modifyCustomerBaseInfo_IdType").combobox({required:false});
	}
	if( idType !=null && idType!="" && idType!=undefined && idType=="1" ){
		setBirthdayAndSexDisable();
		if(!checkIdNo(idNo)){
			//$.messager.alert('提示', "该财富顾问编码已经存在！", 'info');
			return false;
		}else{
			//设置出生日期和性别
			var birthdayAndSex = getBirthdayAndSex(idNo);
			$("#modifyCustomerBaseInfo_Sex").combobox("setValue",birthdayAndSex.sex);
			$("#modifyCustomerBaseInfo_Birthday").datebox("setValue",birthdayAndSex.birthday);
		}
	}
}
/***********************************************************************************************************
 * 
 * 	用户选择证件类型为身份证时，设置出生日期和性别为置灰状态
 * 	@returns {Boolean}
 * ***********************************************************************************************************/
function setBirthdayAndSexDisable(){
	var idType = $('#modifyCustomerBaseInfo_IdType').combobox("getValue");
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		$('#modifyCustomerBaseInfo_Birthday').datebox("disable");
		$('#modifyCustomerBaseInfo_Sex').combobox("disable");
	}else{
		$('#modifyCustomerBaseInfo_Birthday').datebox("enable");
		$('#modifyCustomerBaseInfo_Sex').combobox("enable");
	}
}
/***********************************************************************************************************
 * 
 * 	提交复核核前校验该客户是否被其他理财经理录入交易
 * 	@returns {Boolean}
 * ***********************************************************************************************************/
function checkTradeInfoInput(custBaseInfoId){
	var backdata;
	$.ajax({
		type : 'post',
		async: false,//设置为同步方法
		url : contextPath + "/modifyCustomer/checkTradeInputUrl",
		data : 'param='+custBaseInfoId,
		cache : false,
		success : function(resultInfo) {
							backdata = resultInfo;
								try {/*
											if (resultInfo.success) {
												//submitTradeInput();
												; //如果校验成功，则可返回
											} else {
												$.messager.alert('提示',resultInfo.msg); //如果校验不成功，则提示
											}*/
										} catch (e) {
											$.messager.alert('提示', e);
											return;
										}
							}
		});
		return backdata;
}
//校验客户地址信息和账户信息是否已经录入
function checkCustAdressAndAccInfo(param){
	var backdata;
	$.ajax({
		type : 'post',
		async: false,//设置为同步方法
		url : contextPath + "/modifyCustomer/checkCustAdressAndAccInfoUrl",
		data : 'param='+$.toJSON(param),
		cache : false,
		success : function(resultInfo) {
							backdata = resultInfo;
								try {
											if (resultInfo.success) {
												 //如果校验成功，则可返回
											} else {
												//$.messager.alert('提示',resultInfo.msg); //如果校验不成功，则提示
											}
										} catch (e) {
											$.messager.alert('提示', e);
											return;
										}
							}
		});
		return backdata;
}
/***********************************************************************************************************
 * 	
 * 	判断页面录入信息是否与自己准客户/客户发生冲突
 * 	主要考虑五个因素
 * 	五要素，手机号，姓名，身份证号，姓名+手机号
 * 	@returns {Boolean}
 * ***********************************************************************************************************/
function  checkCollisionWithOwnCust()  {
	var backData;
	//序列化客户基本信息form中内容
	var custBaseInfoformData = $("#modifyCustomerBaseInfoForm").serialize(); 
	//转为Json格式
	custBaseInfoformData = formDataToJsonStr(custBaseInfoformData);						
	//Json格式字符串通过Ajax请求发送到服务器
	$.ajax({
		type : 'post',
		async : false,//设置为同步方法
		url : 'modifyCustomer/verifyCollisionWithOwnCustUrl',
		data : 'custBaseInfoJson='+custBaseInfoformData+'&agentId='+$("#modifyCustomerBaseInfo_agentId").val(),
		cache : false,
		success : function(returnData){
					try {
						backData = returnData;
					} catch (e) {
						$.messager.alert('提示', e);
					}
				  }
	});
	return backData;
}
/**
 * 设置客户基本信息是否可以修改
 * 
 * */
function setCustBaseInfoDisabled(custLevel){
	if(custLevel=="01"){
		// 姓名不可修改与置灰
		$('#modifyCustomerBaseInfo_ChnName').attr("disabled","disabled"); 
		// 客户证件号码不可修改与置灰
		$( '#modifyCustomerBaseInfo_IdNo' ).attr( "disabled", "true" );
		// 手机号不可修改与置灰
		//$( '#modifyCustomerBaseInfo_Mobile' ).attr( "disabled", "true" );
		// 邮箱不可修改与置灰
		//$( '#modifyCustomerBaseInfo_Email' ).attr( "disabled", "true" );
		// 性别下拉框禁用
		//$('#modifyCustomerBaseInfo_Sex').attr( "disabled", "true" );
		// 出生日期禁用
		//$('#modifyCustomerBaseInfo_Birthday').datebox("disable");
		// 证件类型下拉框禁用
		$('#modifyCustomerBaseInfo_IdType').datebox("disable");
		// 投资者类型下拉框禁用
		$('#modifyCustomerBaseInfo_InvestCustomerType').combobox("disable");
	}else{
		// 姓名可修改
		$('#modifyCustomerBaseInfo_ChnName').removeAttr("readonly");//去除input元素的readonly属性  
		// 证件号码可修改
		$("#modifyCustomerBaseInfo_IdNo").removeAttr("disabled");
		// 手机号可修改
		$("#modifyCustomerBaseInfo_Mobile").removeAttr("disabled");
		// 邮箱可修改
		$("#modifyCustomerBaseInfo_Email").removeAttr("disabled");
		// 性别下拉框启用
		//$('#modifyCustomerBaseInfo_Sex').combobox("enable");
		// 出生日期启用
		//$('#modifyCustomerBaseInfo_Birthday').datebox("enable");
		// 证件类型下拉框启用
		$('#modifyCustomerBaseInfo_IdType').datebox("enable");
	}
	// SVN以前代码
	setIdAndGenderDisabled();
}

//身份证图片上传
function uploadIdcardimageInfo(){
	var param = {};
	param.businessNo = modifyCustomerBaseInfo_Id;
	param.businessType = "10";
	if(param.businessNo=="" || param.businessNo=="" || param.businessNo==undefined){
		$.messager.alert("提示","请先提交客户基本信息!",'info');
	}else{
	addFileWindow('身份证复印件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
	}
}

//银行卡图片上传
function custAccountTableUploadOneRow(){
	var param = {};
	param.businessNo = modifyCustomerBaseInfo_Id;
	param.businessType = "11";
	if(param.businessNo=="" || param.businessNo=="" || param.businessNo==undefined){
		$.messager.alert("提示","请先提交客户基本信息!",'info');
	}else{
	addFileWindow('银行卡复印件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
	}
}
//资产证明上传
function modifyCustomerPropertyProve(){
	var param = {};
	param.businessNo = modifyCustomerBaseInfo_Id;
	param.businessType = "12";
	if(param.businessNo=="" || param.businessNo=="" || param.businessNo==undefined){
		$.messager.alert("提示","请先提交客户基本信息!",'info');
	}else{
	addFileWindow('资产证明复印件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
	}
}

/**
 * 校验专业投资者是否上传资产证明
 * */
function verifyInvestCustInfo(){
	/****************************验证专业投资者是否上传资产证明材料*********************/
	var investCustomerType = $('#modifyCustomerBaseInfo_InvestCustomerType').combobox("getValue");
	if (investCustomerType == '02') { 
		$.ajax({
			type : 'post',
			async: false, // 设置为同步方法
			url : 'modifyCustomer/checkInvestCustInfo',
			data : 'custBaseInfoId='+modifyCustomerBaseInfo_Id,
			cache : false,
			success : function(returnData){
				if(returnData == false){
					$.messager.alert("提醒","请上传有效的资产证明或收入证明!",'info');
				}
			}
		});
	}
}


/**
 * 校验客户是否上传身份证复印件
 * */
function queryCustIdCardUpload(){
	/****************************校验客户是否上传身份证复印件*********************/
		$.ajax({
			type : 'post',
			async: false, // 设置为同步方法
			url : 'modifyCustomer/queryCustIdCardUpload',
			data : 'custBaseInfoId='+modifyCustomerBaseInfo_Id,
			cache : false,
			success : function(returnData){
				if(returnData == false){
					$.messager.alert("提醒","请上传客户身份证复印件",'info');
				}
			}
		});
}