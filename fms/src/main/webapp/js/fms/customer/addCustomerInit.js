var custAddressTableEditIndex = null;
var custAccountTableEditIndex = null;
var custFamilyTableEditIndex = null;
var custHouseTableEditIndex = null;
var custCarTableEditIndex = null;
var operate = null;
var rolePivilege = null;
jQuery(function($) 
{
	//操作
	operate = $("#addCust_loadFlag").val();//loadFlag;
	//权限
	rolePivilege = $("#addCust_rolePivilege").val();
	var tradeId = $("#addCust_tradeId").val();
	if(tradeId!=null&&tradeId!=""&&tradeId!=undefined){
		$("#addCust_searchCustButton").show();
		
	}
	//初始化所有的可编辑表格
	initCustomerAddressInfo();
	initCustAccountInfo();
	initCustFamilyTable();
	initCustHourseTable();
	initCustCarTable();
	//初始化所有下拉框
	initCombobox();
	//初始化兴趣爱好checkbox
	initHobby();
	//初始化收入来源checkbox
	initIncomeSource();
	//初始化资产checkbox
	initAssetInfo();
	//初始化投资构成checkbox
	initInvestInfo();
	
	//客户明细查询
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="custDetail"){
		/*$("#addCust_custManageEnter").css("display", "none");  
		$("#addCust_tradeManageEnter").css("display", "none");*/
		$("#addCust_submitCarInfoButton").hide();
		$("#addCust_searchCustButton").hide();
		$("#addCust_CustBaseInfoButton").css("display", "none");
		$("#addCust_CustContactInfoButton").css("display", "none");
		$("#customerAddressTable_tb").css("display", "none");
		$("#customerAccountTable_tb").css("display", "none");
		$("#customerAccountTableButton").css("display", "none");
		$("#addCust_CustPersonalInfoButton").css("display", "none");
		$("#customerFamilyTable_tb").css("display", "none");
		$("#customerFamilyTableButton").css("display", "none");
		$("#customerHouseTable_tb").css("display", "none");
		$("#customerHouseTableButton").css("display", "none");
		$("#customerCarTable_tb").css("display", "none");	
		//设置点击事件不可用
		custAddressTable.datagrid('options').onClickRow = custDetailOnClickRow;
		custAccountTable.datagrid('options').onClickRow = custDetailOnClickRow;
		custFamilyTable.datagrid('options').onClickRow = custDetailOnClickRow;
		custHouseTable.datagrid('options').onClickRow = custDetailOnClickRow;
		custCarTable.datagrid('options').onClickRow = custDetailOnClickRow;
	}
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="addCust"){
		custAddressTableAddOneRow();
		custAccountTableAddOneRow();
		custFamilyTableAddOneRow();
		custHouseTableAddOneRow();
		custCarTableAddOneRow();
		$("#addCust_custContactInfoTable").hide();//客户联系信息表格隐藏
	}else{
		//财富顾问本人不可以修改客户基本信息
		if(rolePivilege!=null&&rolePivilege!=""&&rolePivilege!=undefined&&rolePivilege=="1"){
			//$("#addCust_CustBaseInfoButton").css("display", "none");
			//$('#addCust_CustBaseInfoSubmitButton').linkbutton('disabled');
			$('#addCust_CustBaseInfoSubmitButton').attr('disabled',"true");
			$('#addCust_Mobile').attr('disabled',"true");
			
		}else
		
		{
			initCustContactInfoTable();
			queryCustContactInfoList();
		}
		//获取客户基本信息
		getCustBaseInfo("01");
		
		//获取客户详细信息
		getCustDetailInfo();
	}
}	
);

function custDetailOnClickRow(){}

//返回
function back(){
	var tradeId = $("#addCust_tradeId").val();
	//返回到交易页面
	if(tradeId!=null&&tradeId!=""&&tradeId!=undefined){
		var agentId = $("#addCust_agentId").val();
		var custBaseInfoId = $("#addCust_CustBaseInfoId").val();
		if(custBaseInfoId==null||custBaseInfoId==""||custBaseInfoId==undefined){
			$.messager.alert('提示', "请录入客户信息或选择已有客户！");
			return;
		}
		var param = {};
		param.agentId = agentId;
		param.custBaseInfoId = custBaseInfoId;
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
						$("#addCust_SexName").val($("#addCust_Sex").combobox("getText"));
						$("#addCust_IdTypeName").val($("#addCust_IdType").combobox("getText"));
						var custBaseInfo = formDataToJsonStr($("#addCust_CustBaseInfoForm").serialize());
						appendTradeCustomerInfo(custBaseInfo);
						$("#addCustomeInputWindow").window('destroy');
					}else{
						$.messager.alert('提示', returnData.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
			}
		
		});
	}else{
		$("#addcustomerWindow").window('destroy');
		if(operate!=null&&operate!=""&&operate!=undefined&&operate!="custDetail"){
			queryCustomerList();
		}
	}
}


/**
 *提交客户基本信息 
 * 
 */
function submitCustBaseInfo(){
	//客户基本信息form表单验证
	if(!$("#addCust_CustBaseInfoForm").form("validate")){
		return;
	}
	
	if(!verifyCustTradeInfo()){
		//$.messager.alert('提示', "当前客户有正在处理中的交易，不能修改客户相关信息！", 'info');
		return;
	}
	if(!confirm("请仔细核对客户基本信息，确认无误后点击【确定】按钮")){
		return;
	}
	var formData = $("#addCust_CustBaseInfoForm").serialize();
	formData = formDataToJsonStr(formData);
	$.ajax({
		type:'post',
		url:'customer/saveCustBaseInfo',
		data:'custBaseInfoData='+formData+'&agentId='+$("#addCust_agentId").val(),
		cache:false,
		success:function(returnData){
			//console.info(returnData);
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$("#addCust_CustomerNo").val(jsonObj.customerNo);
					$("#addCust_CustBaseInfoId").val(jsonObj.custBaseInfoId);
					//获取客户详细信息
					getCustDetailInfo();
					$.messager.alert('提示', returnData.msg);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

/**
 *提交客户联系信息和地址信息 
 * 
 */
function submitCustContactAndAddressInfo(){
	//联系信息form表单验证
	if(!$("#addCust_CustContactInfoForm").form("validate")){
		return;
	}
	var rows = custAddressTable.datagrid("getRows");
	if(rows.length>0){
		//地址信息验证
		if(!custAddressTableLockOneRow()){
			$.messager.alert('提示', "客户地址信息输入有误！", 'info');
			return;
		}
	}
	
	if(!verifyCustTradeInfo()){
		//$.messager.alert('提示', "当前客户有正在处理中的交易，不能修改客户相关信息！", 'info');
		return;
	}
	
	
	var custBaseInfoformData = $("#addCust_CustBaseInfoForm").serialize();
	//console.info($("#addCust_CustBaseInfoId").val())
	custBaseInfoformData = formDataToJsonStr(custBaseInfoformData);
	//console.info(custBaseInfoformData);
	//获取客户客户个人联系信息
	var contactFormData = $("#addCust_CustContactInfoForm").serialize();
	////console.info(contactFormData);
	contactFormData = formDataToJsonStr(contactFormData);
	////console.info(contactFormData);
	//从datagrid中获取客户地址信息
	var addressData = custAddressTable.datagrid('getData');
	/*//console.info(addressData);
	//console.info(addressData.rows);
	//console.info($.toJSON(addressData.rows));*/
	var customerNo = $("#addCust_CustomerNo").val();
	var agentId = $("#addCust_agentId").val();
	var param = {};
	param.custContactInfo = contactFormData;
	param.custAddressList = $.toJSON(addressData.rows)
	param.operate = operate;
	param.agentId = agentId;
	param.custBaseInfo = custBaseInfoformData;
	$.ajax({
		type:'post',
		url:'customer/saveCustContactAndAddressInfo',
		data:'custContactAndAddressInfo='+$.toJSON(param),
		cache:false,
		success:function(resultInfo){
			//console.info(resultInfo);
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$.messager.alert('提示', resultInfo.msg);
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


var custContactInfoTable;
function initCustContactInfoTable(){
	custContactInfoTable = $('#addCust_custContactInfoTable').datagrid({
		title : '客户联系信息列表', // 标题
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
					field : 'custBaseInfoId',
					title : '客户流水号',
					hidden: true,
					formatter : function(value, row, index) {
						return row.custBaseInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'custCantactInfoId',
					title : '客户联系方式流水号',
					hidden: true,
					formatter : function(value, row, index) {
						return row.custCantactInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agentId',
					title : '财富顾问流水号',
					hidden: true,
					formatter : function(value, row, index) {
						return row.agentId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agentName',
					title : '财富顾问',
					width : 40,
					formatter : function(value, row, index) {
						return row.agentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'phone',
					title : '固定电话',
					width : 40,
					formatter : function(value, row, index) {
						return row.phone;
					} // 需要formatter一下才能显示正确的数据
				},
				
				{
					field : 'mobile',
					title : '手机',
					width : 40,
					formatter : function(value, row, index) {
						return row.mobile;
					}
				},{
					field : 'email',
					title : 'E-MAIL',
					width : 40,
					formatter : function(value, row, index) {
						return row.email;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'qq',
					title : 'QQ',
					width : 40,
					formatter : function(value, row, index) {
						return row.qq;
					}
				},{
					field : 'wechat',
					title : '微信',
					width : 40,
					formatter : function(value, row, index) {
						return row.wechat;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#addCust_custContactInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onCheck:function(rowIndex,rowData){
			//console.info(rowIndex);
			//console.info(rowData);
			$("#addCust_agentId").val(rowData.agentId);
			queryCustContactDetailInfo(rowData);
			/*custAddressTableeditOneRow(rowIndex);
			setCityAndCountry(rowIndex);*/
		}
	});
}

function queryCustContactInfoList(){
	var custBaseInfoId = $("#addCust_CustBaseInfoId").val()
	var param = {};
	param.custBaseInfoId = custBaseInfoId;
	$.ajax({
		type:'post',
		url:'customer/queryCustContactInfoList',
		data:'queryParam='+$.toJSON(param),
		cache:false,
		success:function(resultInfo){
			//console.info(resultInfo);
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					//$.messager.alert('提示', resultInfo.msg);
					custContactInfoTable.datagrid("loadData",jsonObj);
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function queryCustContactDetailInfo(param){
	$.ajax({
		type:'post',
		url:'customer/queryCustContactDetailInfo',
		data:'queryParam='+$.toJSON(param),
		cache:false,
		success:function(resultInfo){
			//console.info(resultInfo);
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//给客户联系信息赋值
					$("#addCust_CustContactInfoForm").form("clear");
					setInputValueById("addCust_CustContactInfo",jsonObj.custContactInfo);
					if(jsonObj.custContactInfo!=null){
						$("#addCust_agentId").val(jsonObj.custContactInfo.agentId);
					}
					//给客户联系地址信息赋值
					if(jsonObj.custAddressInfoList!=null&&jsonObj.custAddressInfoList!=undefined){
						clearAllRows(custAddressTable);
						loadJsonObjData("customerAddressTable",jsonObj.custAddressInfoList);
						custAddressTableEditIndex = null;
					}
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

var custAddressTable;
function initCustomerAddressInfo(){
	custAddressTable = $('#customerAddressTable').datagrid({
		title : '联系地址信息', // 标题
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
			$('#customerAddressTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			custAddressTableeditOneRow(rowIndex);
			setCityAndCountry(rowIndex);
		},
		toolbar:"#customerAddressTable_tb"
	});
}
//点击一行启动编辑后，给市和区重新赋值
function setCityAndCountry(index){
	var provinceCode = custAddressTable.datagrid('getRows')[index]['province'];
	//console.info("provinceCode===="+provinceCode);
	if(provinceCode!=null&&provinceCode!=''){
		var edCity = custAddressTable.datagrid('getEditor', {index:index,field:'city'});
		//重新加载城市数据
		$(edCity.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode);
		//获取之前存储的城市数据
		var cityCode = custAddressTable.datagrid('getRows')[index]['city'];
		if(cityCode!=null&&cityCode!=''){
			$(edCity.target).combobox('setValue',cityCode);
			//重新加载区县数据
			var edCountry = custAddressTable.datagrid('getEditor', {index:index,field:'country'});
			$(edCountry.target).combobox('reload',contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode);
			var countryCode = custAddressTable.datagrid('getRows')[index]['country'];
			if(countryCode!=null&&countryCode!=''){
				$(edCountry.target).combobox('setValue',countryCode);
			}
		}
	}
}
/*********************************************************************/
//增加一行
function custAddressTableAddOneRow(){
	//console.info(custAddressTable.toolbar);
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

/*********************************************************************/

var custAccountTable;
function initCustAccountInfo(){
	custAccountTable = $('#customerAccountTable').datagrid({
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
		toolbar:"#customerAccountTable_tb"
	});
}


/*********************************************************************/
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

/*********************************************************************/
/**
 *提交客户账户信息 
 * 
 */
function submitCustAccInfo(){
	var rows = custAccountTable.datagrid("getRows");
	if(rows.length==0){
		$.messager.alert('提示', "请添加客户账户信息后再提交！", 'info');
		return;
	}
	
	if(!custAccountTableLockOneRow()){
		$.messager.alert('提示', "客户账户信息输入有误！", 'info');
		return;
	}
	
	if(!verifyCustTradeInfo()){
		//$.messager.alert('提示', "当前客户有正在处理中的交易，不能修改客户相关信息！", 'info');
		return;
	}
	
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	//datagrid中获取客户账户信息
	var custAccData = custAccountTable.datagrid('getData');
	/*//console.info(addressData);
	//console.info(addressData.rows);
	//console.info($.toJSON(addressData.rows));*/
	var agentId = $("#addCust_agentId").val();
	var param = {};
	param.custBaseInfo = custBaseInfoFormData;
	param.agentId = agentId;
	param.custAccInfo = $.toJSON(custAccData.rows)
	param.operate = operate;
	$.ajax({
		type:'post',
		url:'customer/saveCustAccInfo',
		data:'custAccInfoParam='+$.toJSON(param),
		cache:false,
		success:function(resultInfo){
			//console.info(resultInfo);
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$.messager.alert('提示', resultInfo.msg);
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


var custFamilyTable;
function initCustFamilyTable(){
	custFamilyTable = $('#customerFamilyTable').datagrid({
		//	title : '家庭信息', // 标题
			method : 'post',
			//iconCls : 'icon-edit', // 图标
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
						field : 'memberName',
						title : '家庭成员姓名',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								required:true,
								validType:'length[0,20]'
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.memberName;
						} // 需要formatter一下才能显示正确的数据
					},{
						field : 'relationToCust',
						title : '与客户关系',
						width : 70,
						editor: {
							type:'combobox',
							options:{
								valueField:'code',
								textField:'codeName',
								url:"codeQuery/tdCodeQuery?codeType=relationToCust",
								onHidePanel:function(){
									var ed = custFamilyTable.datagrid('getEditor', {index:custFamilyTableEditIndex,field:'relationToCust'});
									var relationToCustName = $(ed.target).combobox('getText');
									custFamilyTable.datagrid('getRows')[custFamilyTableEditIndex]['relationToCustName'] = relationToCustName;
								}
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.relationToCustName;
						}
					},{
						field : 'relationToCustName',
						title : '与客户关系名称',
						hidden : true,
						//sortable : true,
						formatter : function(value, row, index) {
							return row.relationToCustName;
						}
					},{
						field : 'age',
						title : '年龄（周岁）',
						width : 70,
						//editor: 'text',
						editor: {
							type:"numberbox",
							options:{
								min:0,
								max:100
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.age;
						}
					},{
						field : 'occupationCode',
						title : '职业',
						width : 70,
						editor: {
							type:'combobox',
							options:{
								valueField:'code',
								textField:'codeName',
								url:"codeQuery/tdCodeQuery?codeType=occupation",
								onHidePanel:function(){
									var ed = custFamilyTable.datagrid('getEditor', {index:custFamilyTableEditIndex,field:'occupationCode'});
									var occupationName = $(ed.target).combobox('getText');
									custFamilyTable.datagrid('getRows')[custFamilyTableEditIndex]['occupationName'] = occupationName;
								}
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.occupationName;
						}
					},{
						field : 'occupationName',
						title : '职业名称',
						hidden : true,
						//sortable : true,
						formatter : function(value, row, index) {
							return row.occupationName;
						}
					},{
						field : 'annualIncome',
						title : '年收入（万元）',
						width : 70,
						//editor: 'text',
						//sortable : true,
						editor: {
							type:"numberbox",
							options:{
								min:0,
								precision:2
							}
						},
						formatter : function(value, row, index) {
							return row.annualIncome;
						}
					},{
						field : 'mobile',
						title : '手机号',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								validType:"validPhone"
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.mobile;
						}
					},{
						field : 'qq',
						title : 'QQ号',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								validType:"validQQ"
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.qq;
						}
					},{
						field : 'wechat',
						title : '微信号',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								validType:"validCode"
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.wechat;
						}
					}]],
			onLoadSuccess : function() {
				$('#customerFamilyTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			},
			onClickRow:function(rowIndex){
				custFamilyTableeditOneRow(rowIndex);
			},
			toolbar:"#customerFamilyTable_tb"
	});
}

/*********************************************************************/
//增加一行
function custFamilyTableAddOneRow(){
	custFamilyTableEditIndex = addOneRow(custFamilyTable,custFamilyTableEditIndex);
}
//删除一行
function custFamilyTableRemoveOneRow(){
	removeOneRow(custFamilyTable,custFamilyTableEditIndex);
	custFamilyTableEditIndex = null;
}
//编辑指定行
function custFamilyTableeditOneRow(index){
	if(editOneRow(custFamilyTable,custFamilyTableEditIndex,index)){
		custFamilyTableEditIndex = index;
	}
}
//锁定编辑行
function custFamilyTableLockOneRow(){
	if(lockOneRow(custFamilyTable,custFamilyTableEditIndex)){
		custFamilyTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}

}
/*********************************************************************/

/**
 * 提交客户家庭信息
 */
function submitCustFamilyInfo(){
	var rows = custFamilyTable.datagrid("getRows");
	if(rows.length==0){
		$.messager.alert('提示', "请添加客户家庭成员信息后再提交！", 'info');
		return;
	}
	//数据校验
	if(!custFamilyTableLockOneRow()){
		$.messager.alert('提示', "客户家庭成员信息输入有误！", 'info');
		return;
	}
	
	if(!verifyCustTradeInfo()){
		//$.messager.alert('提示', "当前客户有正在处理中的交易，不能修改客户相关信息！", 'info');
		return;
	}
	
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	//datagrid中获取客户账户信息
	var custFamilyData = custFamilyTable.datagrid('getData');
	/*//console.info(addressData);
	//console.info(addressData.rows);
	//console.info($.toJSON(addressData.rows));*/
	var agentId = $("#addCust_agentId").val();
	var params = {};
	params.custBaseInfo = custBaseInfoFormData;
	params.agentId = agentId;
	params.familyInfoList = $.toJSON(custFamilyData.rows)
	params.operate = operate;
	$.ajax({
		type:'post',
		url:'customer/saveCustFamilyInfo',
		data:'custFamilyInfoData='+$.toJSON(params),
		cache:false,
		success:function(resultInfo){
			//console.info(resultInfo);
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$.messager.alert('提示', resultInfo.msg);
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


var custHouseTable;
function initCustHourseTable(){
	custHouseTable = $('#customerHouseTable').datagrid({
		//title : '联系信息地址', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
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
					field : 'housePropertyCode',
					title : '房产证编号',
					width : 140,
					editor: {
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,40]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyCode;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'housePropertyValue',
					title : '房产价值',
					width : 140,
					//editor: 'text',
					editor: {
						type:"numberbox",
						options:{
							min:0,
							precision:2
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyValue;
					}
				},{
					field : 'housePropertyState',
					title : '房产状况',
					width : 140,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							url:"codeQuery/tdCodeQuery?codeType=houseType",
							onHidePanel:function(){
								var ed = custHouseTable.datagrid('getEditor', {index:custHouseTableEditIndex,field:'housePropertyState'});
								var housePropertyStateName = $(ed.target).combobox('getText');
								custHouseTable.datagrid('getRows')[custHouseTableEditIndex]['housePropertyStateName'] = housePropertyStateName;
							}
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyStateName;
					}
				},{
					field : 'housePropertyStateName',
					title : '房产状况名称',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyStateName;
					}
				}]],
		onLoadSuccess : function() {
			$('#customerHourseTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			custHouseTableeditOneRow(rowIndex);
		},
		toolbar:"#customerHouseTable_tb"
	});
}
/*********************************************************************/
//增加一行
function custHouseTableAddOneRow(){
	custHouseTableEditIndex = addOneRow(custHouseTable,custHouseTableEditIndex);
}
//删除一行
function custHouseTableRemoveOneRow(){
	removeOneRow(custHouseTable,custHouseTableEditIndex);
	custHouseTableEditIndex = null;
}
//编辑指定行
function custHouseTableeditOneRow(index){
	if(editOneRow(custHouseTable,custHouseTableEditIndex,index)){
		custHouseTableEditIndex = index;
	}
}
//锁定编辑行
function custHouseTableLockOneRow(){
	if(lockOneRow(custHouseTable,custHouseTableEditIndex)){
		custHouseTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
}
/*********************************************************************/

/**
 * 提交客户房产信息
 */
function submitCustHouseInfo(){
	var rows = custHouseTable.datagrid("getRows");
	if(rows.length==0){
		$.messager.alert('提示', "请添加客户房产信息后再提交！", 'info');
		return;
	}
	if(!custHouseTableLockOneRow()){
		$.messager.alert('提示', "客户房产信息输入有误！", 'info');
		return;
	}
	
	if(!verifyCustTradeInfo()){
		//$.messager.alert('提示', "当前客户有正在处理中的交易，不能修改客户相关信息！", 'info');
		return;
	}
	
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	//datagrid中获取客户账户信息
	var custHouseData = custHouseTable.datagrid('getData');
	/*//console.info(addressData);
	//console.info(addressData.rows);
	//console.info($.toJSON(addressData.rows));*/
	var agentId = $("#addCust_agentId").val();
	var params = {};
	params.agentId = agentId;
	params.custBaseInfo = custBaseInfoFormData;
	params.houseInfoList = $.toJSON(custHouseData.rows)
	params.operate = operate;
	$.ajax({
		type:'post',
		url:'customer/saveCustHouseInfo',
		data:'custHouseInfoData='+$.toJSON(params),
		cache:false,
		success:function(resultInfo){
			//console.info(resultInfo);
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$.messager.alert('提示', resultInfo.msg);
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


var custCarTable;
function initCustCarTable(){
	custCarTable = $('#customerCarTable').datagrid({
		//title : '联系信息地址', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
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
					field : 'carCode',
					title : '车辆编号',
					width : 70,
					//editor: 'text',
					editor:{
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,10]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carCode;
					} // 需要formatter一下才能显示正确的数据
				},
/*				{
					field : 'carType',
					title : '车辆种类',
					width : 70,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							//url:"codeQuery/tdCodeQuery?codeType=addressType",
							onHidePanel:function(){
								var ed = custCarTable.datagrid('getEditor', {index:custCarTableEditIndex,field:'carType'});
								var carTypeName = $(ed.target).combobox('getText');
								custCarTable.datagrid('getRows')[custCarTableEditIndex]['carTypeName'] = carTypeName;
							}
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carTypeName;
					}
				},
				{
					field : 'carTypeName',
					title : '车辆种类名称',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carTypeName;
					}
				},*/
				{
					field : 'carBrand',
					title : '车辆品牌',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							validType:['length[0,10]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carBrand;
					}
				},{
					field : 'engineNo',
					title : '发动机号',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							validType:['length[0,50]']
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.engineNo;
					} 
				},{
					field : 'carFrameNo',
					title : '车架号',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,50]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carFrameNo;
					}
				},{
					field : 'licensePlateNo',
					title : '车牌号',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,10]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.licensePlateNo;
					}
				},{
					field : 'rePrice',
					title : '车辆重置价（万）',
					width : 70,
					editor: {
						type:"numberbox",
						options:{
							required:true,
							min:0,
							precision:2
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.rePrice;
					}
				}]],
		onLoadSuccess : function() {
			$('#customerCarTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			custCarTableeditOneRow(rowIndex);
		},
		toolbar: '#customerCarTable_tb'
	});
}
/*********************************************************************/
//增加一行
function custCarTableAddOneRow(){
	custCarTableEditIndex = addOneRow(custCarTable,custCarTableEditIndex);
	//custCarTableBindAddRowEvent();
}
//删除一行
function custCarTableRemoveOneRow(){
	removeOneRow(custCarTable,custCarTableEditIndex);
	custCarTableEditIndex = null;
}
//编辑指定行
function custCarTableeditOneRow(index){
	if(editOneRow(custCarTable,custCarTableEditIndex,index)){
		custCarTableEditIndex = index;
	}
	
	//custCarTableBindAddRowEvent();
}
//锁定编辑行
function custCarTableLockOneRow(){
	if(lockOneRow(custCarTable,custCarTableEditIndex)){
		custCarTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
	
}
//绑定新增一行事件
/*function custCarTableBindAddRowEvent(){
	var edRePrice = custCarTable.datagrid('getEditor', {index:custCarTableEditIndex,field:'rePrice'});
	$(edRePrice.target).focus(function(){
		if(isLastRow(custCarTable,custCarTableEditIndex)){
			custCarTableAddOneRow();
		}
	});
}*/
/*********************************************************************/

/**
 * 提交客户车辆信息
 */
function submitCustCarInfo(){
	var rows = custCarTable.datagrid("getRows");
	if(rows.length==0){
		$.messager.alert('提示', "请添加客户车辆信息后再提交！", 'info');
		return;
	}
	//客户车辆信息校验
	if(!custCarTableLockOneRow()){
		$.messager.alert('提示', "客户车辆信息输入有误！", 'info');
		return;
	}
	
	if(!verifyCustTradeInfo()){
		//$.messager.alert('提示', "当前客户有正在处理中的交易，不能修改客户相关信息！", 'info');
		return;
	}
	
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	//datagrid中获取客户账户信息
	var custCarData = custCarTable.datagrid('getData');
	/*//console.info(addressData);
	//console.info(addressData.rows);
	//console.info($.toJSON(addressData.rows));*/
	var agentId = $("#addCust_agentId").val();
	var params = {};
	params.agentId = agentId;
	params.custBaseInfo = custBaseInfoFormData;
	params.carInfoList = $.toJSON(custCarData.rows)
	params.operate = operate;
	$.ajax({
		type:'post',
		url:'customer/saveCustCarInfo',
		data:'custCarInfoData='+$.toJSON(params),
		cache:false,
		success:function(resultInfo){
			//console.info(resultInfo);
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$.messager.alert('提示', resultInfo.msg);
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


//兴趣爱好
function initHobby(){
	$.ajax({
		type:'post',
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=customerHobby',
		//data:'queryParam='+formData,
		cache:false,
		success:function(returnData){
			////console.info(returnData);
			try {
				////console.info();
				////console.info(8%6);
				var size = returnData.length;
				var hobbyHtml = '<span class="comboSpan"></span>';
				for(var i=0;i<size;i++){
					var jsonObj = returnData[i];
					var code = jsonObj.code;
					var name = jsonObj.name;
					hobbyHtml += '<input type="checkbox" name="hobbyCode" id="addCust_HobbyCode" value="'+code
						+'"/><span class="commonSpan">'+name+'&nbsp;</span>';
				}
				//hobbyHtml.appendTo($("#hobbyCheckBox"));
				$("#hobbyCheckBox").append(hobbyHtml);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});  
}

/**
 *提交客户个人信息 
 * 
 */
function submitCustPersonalInfo(){
	if(!$("#addCust_CustPersonalInfoForm").form("validate")){
		return;
	}

	if(!verifyCustTradeInfo()){
		//$.messager.alert('提示', "当前客户有正在处理中的交易，不能修改客户相关信息！", 'info');
		return;
	}
	
	//获取客户基本信息
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	//获取客户个人信息
	var custPersonalInfoFormData = $("#addCust_CustPersonalInfoForm").serialize();
	//var obj = formDataToJsonObj(custPersonalInfoFormData);
	//console.info(obj);
	//console.info(eval("("+obj+")"));
	var custOthInfoFormData = $("#addCust_custAssetInfoForm").serialize();
	custPersonalInfoFormData = custPersonalInfoFormData+"&"+custOthInfoFormData;
	custPersonalInfoFormData = formDataToJsonStr(custPersonalInfoFormData);
	//获取客户爱好信息
	var hobbyList = [];
	$('input[name="hobbyCode"]:checked').each(function(){ 
		var hobby={};
		hobby.hobbyCode = $(this).val();
		hobbyList.push(hobby);    
	});   
	hobbyList = $.toJSON(hobbyList);
	//获取客户收入来源信息
	var incomeList = [];
	var reg = /^([1-9]\d?(\.{1}\d+)?|100)$/ ;
	//reg = /^[1-9]\d{1,}[\.\d*]?$/
	var verifyIncome = true;
	var verifyIncomeMsg = null;
	var verifyIncomePercentSum = 0;
	$('input[name="incomeCode"]:checked').each(function(){ 
		var income={};
		var incomeCode = $(this).val();
		var incomePercent = $("#addCust_IncomePercent"+incomeCode).val();
		//判断了勾选收入来源未填写收入百分比的项
		if(incomePercent==null||incomePercent==""||incomePercent==undefined){
			verifyIncomeMsg = "有选择了收入来源，未填写收入比例的选项";
			verifyIncome = false;
		}else{
			//判断了勾选收入来源收入百分比不为0-100的数字
			if(!reg.test(incomePercent)){
				verifyIncome = false;
				verifyIncomeMsg = "收入来源百分比只能录入0—100的数字";
			}else{
				verifyIncomePercentSum += parseFloat(incomePercent);
			}
		}
		
		income.incomeCode = incomeCode;
		income.percent = incomePercent;
		incomeList.push(income);    
	});   
	if(!verifyIncome){
		$.messager.alert('提示', verifyIncomeMsg);
		return;
	}
	if(verifyIncomePercentSum>100){
		$.messager.alert('提示', "收入来源百分比之和大于100,请调整收入来源百分比");
		return;
	}
	////console.info(incomeList);
	incomeList = $.toJSON(incomeList);
	//获取资产构成信息
	var verifyAsset = true;
	var verifyAssetMsg = null;
	var verifyAssetPercentSum = 0;
	var assetList = [];
	$('input[name="assetCode"]:checked').each(function(){ 
		var asset={};
		var assetCode = $(this).val();
		var assetPercent = $("#addCust_AssetPercent"+assetCode).val();
		if(assetPercent==null||assetPercent==""||assetPercent==undefined){
			verifyAssetMsg = "有选择了资产构成，未填写构成比例的选项";
			verifyAsset = false;
		}else{
			if(!reg.test(assetPercent)){
				verifyAsset = false;
				verifyAssetMsg = "资产构成百分比只能录入0—100的数字";
			}else{
				verifyAssetPercentSum += parseFloat(assetPercent);
			}
		}
		
		asset.assetCode = assetCode;
		asset.percent = assetPercent;
		assetList.push(asset);    
	});  
	if(!verifyAsset){
		$.messager.alert('提示', verifyAssetMsg);
		return;
	}
	if(verifyAssetPercentSum>100){
		$.messager.alert('提示', "资产构成百分比之和大于100,请调整资产构成百分比");
		return;
	}
	
	assetList = $.toJSON(assetList);
	//获取投资金融产品类型
	var investList = [];
	$('input[name="investCode"]:checked').each(function(){ 
		var invest={};
		var investCode = $(this).val();
		invest.investCode = investCode;
		investList.push(invest);    
	});  
	investList = $.toJSON(investList);
	
	var agentId = $("#addCust_agentId").val();
	//封装参数
	var params = {};
	//1.
	params.agentId = agentId;
	params.custBaseInfo = custBaseInfoFormData;
	params.custPersonalInfo = custPersonalInfoFormData;
	params.hobbyInfo = hobbyList;
	//3.客户收入来源信息
	params.incomeList = incomeList;
	//4.客户资产构成信息
	params.assetList = assetList;
	//5.客户投资金融产品类型信息
	params.investList = investList;
	params.operate = operate;
	
	
	$.ajax({
		type:'post',
		url:'customer/saveCustPersonalInfo',
		data:'custPersonalInfoData='+$.toJSON(params),
		cache:false,
		success:function(returnData){
			//console.info(returnData);
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$.messager.alert('提示', returnData.msg);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


//收入来源
function initIncomeSource(){
	$.ajax({
		type:'post',
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=incomeSource',
		//data:'queryParam='+formData,
		cache:false,
		success:function(returnData){
			////console.info(returnData);
			try {
				var size = returnData.length;
				var incomeSourceHtml = '<span class="comboSpan"></span>';
				for(var i=0;i<size;i++){
					var jsonObj = returnData[i];
					var code = jsonObj.code;
					var name = jsonObj.name;
					incomeSourceHtml += '<input type="checkbox" name="incomeCode" id="addCust_IncomeCode" value="'+code+'"/>'+name
									 +'<input name="incomePercent'+code+'" id="addCust_IncomePercent'+code
									 +'" class="table_input3" >（%）&nbsp;';
					//easyui-numberbox" data-options="min:0,precision:2"
				}
				 $("#incomeSource").append(incomeSourceHtml);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});  
	
}
//资产构成
function initAssetInfo(){
	$.ajax({
		type:'post',
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=assetComposition',
		//data:'queryParam='+formData,
		cache:false,
		success:function(returnData){
			////console.info(returnData);
			try {
				var size = returnData.length;
				var assetHtml = '<span class="comboSpan"></span>';
				for(var i=0;i<size;i++){
					var jsonObj = returnData[i];
					var code = jsonObj.code;
					var name = jsonObj.name;
					assetHtml += '<input type="checkbox" name="assetCode" id="addCust_assetCode" value="'+code+'"/>'+name
							  +'<input name="percent'+code+'" id="addCust_AssetPercent'+code+'" class="table_input3 easyui-numberbox" data-options="min:0,precision:2"/>（%）&nbsp;';
				}
				/*var cellCount = 3;
				var rowCount = Math.ceil(size/cellCount); 
				var table=$('<table width="100%" border="0" cellspacing="0" cellpadding="0">');
				table.appendTo($("#assetCodeCheckBox"));
				for(var i=0;i<rowCount;i++){
				    var tr=$("<tr></tr>");
				    tr.appendTo(table);
				    for(var j=0;j<cellCount;j++)
				    {
				    	var index = (i*cellCount)+j;
				    	var input = "";
				    	if(j==0){
				    		input = '<span class="comboSpan"></span>';
				    	}
				    	if(index<size){
				    		var jsonObj = returnData[index];
							var code = jsonObj.code;
							var name = jsonObj.name;
					    	input += '<input type="checkbox" name="assetComposition" id="assetComposition" value="'+code+'"/>'+name
					    			+'<input name="investscale" id="investscale" class="table_input3 easyui-numberbox" data-options="min:0,precision:2"/>%';
				    	}else{
				    		input = "&nbsp;";
				    	}
				    	var td=$("<td>"+input+"</td>");
				       	td.appendTo(tr);
				    }
				 }*/
				 //trend.appendTo(table);
				 //$("#assetCodeCheckBox").append("</table>");
				 $("#assetCodeCheckBox").append(assetHtml);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});  
}

//投资金融产品类型
function initInvestInfo(){
	$.ajax({
		type:'post',
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=investComposition',
		//data:'queryParam='+formData,
		cache:false,
		success:function(returnData){
			////console.info(returnData);
			try {
				////console.info();
				////console.info(8%6);
				var size = returnData.length;
				var investHtml = '<span class="comboSpan"></span>';
				for(var i=0;i<size;i++){
					var jsonObj = returnData[i];
					var code = jsonObj.code;
					var name = jsonObj.name;
					investHtml += '<input type="checkbox" name="investCode" id="investCode" value="'+code+'"/>'+name+'&nbsp;';
				}
				//hobbyHtml.appendTo($("#hobbyCheckBox"));
				$("#investCheckbox").append(investHtml);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});  
}



/**
 *提交客户个人财富状况信息 
 * 
 */
function submitCustWealthInfo(){
	alert("1111111111111");
	//获取客户基本信息
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	//获取客户其它信息
	var custPersonalInfoFormData = $("#addCust_CustPersonalInfoForm").serialize();
	var custOthInfoFormData = $("#addCust_custAssetInfoForm").serialize();
	custOthInfoFormData = custOthInfoFormData+"&"+custPersonalInfoFormData;
	custOthInfoFormData = formDataToJsonStr(custOthInfoFormData);
	////console.info(custAssetInfoFormData);
	//获取客户收入来源信息
	var reg = /^[1-9]\d{2,}[\.]?\d*$/ ;
	var incomeList = [];
	$('input[name="incomeCode"]:checked').each(function(){ 
		var income={};
		var incomeCode = $(this).val();
		var incomePercent = $("#addCust_IncomePercent"+incomeCode).val();
		alert(incomePercent);
		if(incomePercent=="cs"){
			alert(reg.test(incomePercent));
			//console.info(reg.test(incomePercent));
			if(!reg.test(incomePercent)){
				$.messager.alert('提示', "收入来源百分比只能录入0—100的数字");
				return false;
			}
		}
		
		income.incomeCode = incomeCode;
		income.percent = incomePercent;
		incomeList.push(income);    
	});   
	alert(incomeList.length);
	////console.info(incomeList);
	incomeList = $.toJSON(incomeList);
	//获取资产构成信息
	var assetList = [];
	$('input[name="assetCode"]:checked').each(function(){ 
		var asset={};
		var assetCode = $(this).val();
		var assetPercent = $("#addCust_AssetPercent"+assetCode).val();
		asset.assetCode = assetCode;
		asset.percent = assetPercent;
		assetList.push(asset);    
	});  
	assetList = $.toJSON(assetList);
	//获取投资金融产品类型
	var investList = [];
	$('input[name="investCode"]:checked').each(function(){ 
		var invest={};
		var investCode = $(this).val();
		invest.investCode = investCode;
		investList.push(invest);    
	});  
	investList = $.toJSON(investList);
	//封装参数
	var params = {};
	//1.客户财富状况的文本信息
	params.custBaseInfo = custBaseInfoFormData;
	//2.客户财富状况的文本信息
	params.custOthInfo = custOthInfoFormData;
	//3.客户收入来源信息
	params.incomeList = incomeList;
	//4.客户资产构成信息
	params.assetList = assetList;
	//5.客户投资金融产品类型信息
	params.investList = investList;
	params.operate = operate;
	
	$.ajax({
		type:'post',
		url:'customer/saveCustWealthInfo',
		data:'custWealthInfoData='+$.toJSON(params),
		cache:false,
		success:function(returnData){
			//console.info(returnData);
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					$.messager.alert('提示', returnData.msg);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


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


//初始化combobox
function initCombobox(){
	/*$("#addCust_CustomerNo").searchbox({
		searcher:function(value,name){ //customer/searchCustomer
			//alert("111111") ;
			$('<div id="searchCustWindow"/>').dialog({
				href : contextPath+"/customer/searchCustomer",
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
		}, 
		//menu:'#mm', 
		//prompt:'Please Input Value' 
		readOnly:true
	});*/
	$("#addCust_Birthday").datebox({
		required:true
	});
	//证件有效期
	$("#addCust_idValidityDate").datebox({
		required:true
	});
	//初始化性别
	$("#addCust_Sex").combobox({
		url:'codeQuery/tdCodeQuery?codeType=sex',
		valueField:'code',    
	    textField:'codeName',
	    required:true,
	    onSelect:function(){
	    	var sexName = $("#addCust_Sex").combobox("getText");
	    	$("#addCust_SexName").val(sexName);
	    }
	});
	//初始化证件类型
	$("#addCust_IdType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    required:true,
	    url:'codeQuery/tdCodeQuery?codeType=idType',
	    onSelect:function(record){
	    	setIdNoAndSexDesabled();
	    	if(record.code=="1"){
				var idNo = $("#addCust_IdNo").val();
				if(idNo!=null&&idNo!=""&&idNo!=undefined){
					verifyIdNo();
				}
			}
	    	var idTypeName = $("#addCust_IdType").combobox("getText");
	    	$("#addCust_IdTypeName").val(idTypeName);
	    }
	});
	//初始化国籍
	$("#addCust_NativePlace").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    required:true,
	    url:'codeQuery/tdCodeQuery?codeType=nationality',
	    value:156
	});
	//初始化是否有驾照
	$("#addCust_DrivingLicense").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=isDrivingLicense'
	});
	//初始化客户类型
	$("#addCust_CustType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    required:true,
	    url:'codeQuery/tdCodeQuery?codeType=customerType'
	});
	//初始化人生阶段
	$("#addCust_LifeStage").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=lifeStage'
	});
	//初始化婚姻状况
	$("#addCust_Marriage").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=marriage'
	});
	//初始化教育水平
	$("#addCust_Degree").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=degree'
	});
	//初始化行业
	$("#addCust_WorkType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=workType'
	});
	//初始化职业
	$("#addCust_OccupationCode").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=occupation'
	});
	//初始化客户居住楼盘
	$("#addCust_BuildingName").combobox({
		url:contextPath+'/branch/queryBuildListCode',
        valueField:'id',
        required:true,
        textField:'name'
	});
}


function setIdNoAndSexDesabled(){
	var idType = $('#addCust_IdType').combobox("getValue");
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		$('#addCust_Birthday').datebox("disable");
		$('#addCust_Sex').combobox("disable");
	}else{
		$('#addCust_Birthday').datebox("enable");
		$('#addCust_Sex').combobox("enable");
	}
}



/**
 * 查找客户并选中后，返回到该页面
 * */
function searchCustomerInfoBack(custBaseInfo){
	if(custBaseInfo!=null&&custBaseInfo!=""&&custBaseInfo!=undefined){
		if(verifyCustBelongAgentInfo(custBaseInfo)){
			$("#addCust_CustBaseInfoForm").form("clear");
			setInputValueById("addCust_CustBaseInfo",custBaseInfo);
			$('#searchCustWindow').window('close');
			setIdNoAndSexDesabled();
			$("#addCust_Sex").combobox("setValue",custBaseInfo.sex);
			$("#addCust_Birthday").datebox("setValue",custBaseInfo.birthday);
			//console.info(custBaseInfo);
			//console.info($("#addCust_Sex").combobox("getValue"));
			getCustDetailInfo();
		}
	}
}



//校验客户的交易信息
function verifyCustBelongAgentInfo(custBaseInfo){
	var param = {};
	var agentId = $("#addCust_agentId").val();
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



/**
 * 获取客户基本信息 
 * 
 **/
function getCustBaseInfo(getType){
	//根据客户流水号查询客户
	if(getType!=null&&getType!=""&&getType!=undefined&&getType=="01"){
		var custBaseInfoId = $("#addCust_CustBaseInfoId").val();
		if(custBaseInfoId==null||custBaseInfoId==""||custBaseInfoId==undefined){
			return;
		}
	}

	//获取客户基本信息
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	var params = {};
	params.custBaseInfo = custBaseInfoFormData;
	params.getType = getType;
	$.ajax({
		type:'post',
		url:'customer/getCustBaseInfo',
		data:'queryParams='+$.toJSON(params),
		cache:false,
		success:function(returnData){
			//console.info(returnData);
			try {
				var custBaseInfoObj = returnData.obj;
				if(returnData.success){
					//console.info(custBaseInfoObj);
					//给客户联系信息赋值
					setInputValueById("addCust_CustBaseInfo",custBaseInfoObj);
					setIdNoAndSexDesabled();
					$("#addCust_Sex").combobox("setValue",custBaseInfoObj.sex);
					$("#addCust_Birthday").datebox("setValue",custBaseInfoObj.birthday);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

/**
 * 获取客户详细信息 
 * 
 **/
function getCustDetailInfo(){
	var custBaseInfoId = $("#addCust_CustBaseInfoId").val();
	if(custBaseInfoId==null||custBaseInfoId==""||custBaseInfoId==undefined){
		return;
	}
	//获取客户基本信息
	var custBaseInfoFormData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoFormData = formDataToJsonStr(custBaseInfoFormData);
	var params = {};
	params.custBaseInfo = custBaseInfoFormData;
	params.operate = operate;
	$.ajax({
		type:'post',
		url:'customer/getCustDetailInfo',
		data:'queryParams='+$.toJSON(params),
		cache:false,
		success:function(returnData){
			//console.info(returnData);
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					//$("#addCust_CustomerNo").searchbox("setValue",jsonObj.customerNo);
					//console.info(jsonObj);
					//给客户联系信息赋值
					setInputValueById("addCust_CustContactInfo",jsonObj.custContactInfo);
					if(jsonObj.custContactInfo!=null){
						$("#addCust_agentId").val(jsonObj.custContactInfo.agentId);
					}
					//给客户联系地址信息赋值
					if(jsonObj.custAddressInfoList!=null&&jsonObj.custAddressInfoList!=undefined){
						clearAllRows(custAddressTable);
						loadJsonObjData("customerAddressTable",jsonObj.custAddressInfoList);
						custAddressTableEditIndex = null;
					}
					//给客户账户信息赋值
					if(jsonObj.custAccInfoList!=null&&jsonObj.custAccInfoList!=undefined){
						clearAllRows(custAccountTable);
						loadJsonObjData("customerAccountTable",jsonObj.custAccInfoList);
						custAccountTableEditIndex = null;
					}
					//给客户个人信息赋值
					setInputValueById("addCust_CustPersonalInfo",jsonObj.custPersonalInfo);
					//给个人财富状况赋值
					setInputValueById("addCust_custAssetInfo",jsonObj.custPersonalInfo);
					//......
					setCustHobbyInfo(jsonObj.custHobbyInfoList);
					setCustIncomeInfo(jsonObj.custIncomeInfoList);
					setCustAssetInfo(jsonObj.custAssetInfoList);
					setCustInvestInfo(jsonObj.custInvestInfoList)
					//给客户家庭信息赋值
					if(jsonObj.custFamilyInfoList!=null&&jsonObj.custFamilyInfoList!=undefined){
						clearAllRows(custFamilyTable);
						loadJsonObjData("customerFamilyTable",jsonObj.custFamilyInfoList);
						custFamilyTableEditIndex = null;
					}
					//给客户房产信息赋值
					if(jsonObj.custHouseInfoList!=null&&jsonObj.custHouseInfoList!=undefined){
						clearAllRows(custHouseTable);
						loadJsonObjData("customerHouseTable",jsonObj.custHouseInfoList);
						custHouseTableEditIndex = null;
					}
					//给客户车辆信息赋值
					if(jsonObj.custCarInfoList!=null&&jsonObj.custCarInfoList!=undefined){
						clearAllRows(custCarTable);
						loadJsonObjData("customerCarTable",jsonObj.custCarInfoList);
						custCarTableEditIndex = null;
					}
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}
/**
 *设置用户联系信息 
 **/
function setCustHobbyInfo(custHobbyInfoList){
	if(custHobbyInfoList!=null&&custHobbyInfoList!=''&&custHobbyInfoList!=undefined){
		$('input[name="hobbyCode"]').each(function(){ 
			var chkValue = $(this).val();
			for(var i=0;i<custHobbyInfoList.length;i++){
				var hobbyInfo = custHobbyInfoList[i];
				var hobbyCode = hobbyInfo.hobbyCode;
				if(chkValue==hobbyCode){
					$(this).attr("checked",'true');
				}
			}
		});  
	}
}
/**
 *设置用户收入来源信息 
 **/
function setCustIncomeInfo(custIncomeInfoList){
	if(custIncomeInfoList!=null&&custIncomeInfoList!=''&&custIncomeInfoList!=undefined){
		$('input[name="incomeCode"]').each(function(){ 
			var chkValue = $(this).val();
			for(var i=0;i<custIncomeInfoList.length;i++){
				var incomeInfo = custIncomeInfoList[i];
				var incomeCode = incomeInfo.incomeCode;
				if(chkValue==incomeCode){
					$(this).attr("checked",'true');
					var percent = incomeInfo.percent;
					$("#addCust_IncomePercent"+incomeCode).val(percent);
				}
			}
		});  
	}
}

/**
 *设置用户资产构成信息 
 **/
function setCustAssetInfo(custAssetInfoList){
	if(custAssetInfoList!=null&&custAssetInfoList!=''&&custAssetInfoList!=undefined){
		$('input[name="assetCode"]').each(function(){ 
			var chkValue = $(this).val();
			for(var i=0;i<custAssetInfoList.length;i++){
				var assetInfo = custAssetInfoList[i];
				var assetCode = assetInfo.assetCode;
				if(chkValue==assetCode){
					$(this).attr("checked",'true');
					var percent = assetInfo.percent;
					$("#addCust_AssetPercent"+assetCode).val(percent);
				}
			}
		});  
	}
}

/**
 *设置用户投资偏好信息 
 **/
function setCustInvestInfo(custInvestInfoList){
	if(custInvestInfoList!=null&&custInvestInfoList!=''&&custInvestInfoList!=undefined){
		$('input[name="investCode"]').each(function(){ 
			var chkValue = $(this).val();
			for(var i=0;i<custInvestInfoList.length;i++){
				var investInfo = custInvestInfoList[i];
				var investCode = investInfo.investCode;
				if(chkValue==investCode){
					$(this).attr("checked",'true');
				}
			}
		});  
	}
}


//校验客户的交易信息
function verifyCustTradeInfo(){
	var custBaseInfoId = $("#addCust_CustBaseInfoId").val();
	if(custBaseInfoId==null||custBaseInfoId==""||custBaseInfoId==undefined){
		return true;
	}
	var tradeId = $("#addCust_tradeId").val();
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


function verifyIdNo(){
	//身份证号码格式校验
	var idType = $("#addCust_IdType").combobox("getValue");
	var idNo = $("#addCust_IdNo").val();
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		if(!checkIdNo(idNo)){
			//$.messager.alert('提示', "该财富顾问编码已经存在！", 'info');
			return false;
		}else{
			//设置出生日期和性别
			var birthdayAndSex = getBirthdayAndSex(idNo);
			$("#addCust_Sex").combobox("setValue",birthdayAndSex.sex);
			$("#addCust_Birthday").datebox("setValue",birthdayAndSex.birthday);
		}
	}
}



function verifyCustAllInfo(){
	var custBaseInfoId=$("#addCust_CustBaseInfoId").val();
	if(custBaseInfoId==null||custBaseInfoId=="" ||custBaseInfoId==undefined){
		$.messager.alert('提示', "请先保存客户基本信息！", 'info');
		return false;
	}
	
	//校验当前客户是否有正在处理中的交易
	if(!verifyCustTradeInfo()){
		return false;
	}
	//联系信息form表单验证
	if(!$("#addCust_CustContactInfoForm").form("validate")){
		return false;
	}
	//联系地址信息校验
	var custAddressInfoRows = custAddressTable.datagrid("getRows");
	if(custAddressInfoRows.length>0){
		//地址信息验证
		if(!custAddressTableLockOneRow()){
			$.messager.alert('提示', "客户地址信息输入有误！", 'info');
			return false;
		}
	}
	
	var custAccInfoRows = custAccountTable.datagrid("getRows");
	if(custAccInfoRows.length>0){
		if(!custAccountTableLockOneRow()){
			$.messager.alert('提示', "客户账户信息输入有误！", 'info');
			return false;
		}
	}
	
	//客户个人其它信息表单校验
	if(!$("#addCust_CustPersonalInfoForm").form("validate")){
		return false;
	}
	//客户家庭成员信息表格数据校验
	var custFamilyInfoRows = custFamilyTable.datagrid("getRows");
	if(custFamilyInfoRows.length>0){
		//数据校验
		if(!custFamilyTableLockOneRow()){
			$.messager.alert('提示', "客户家庭成员信息输入有误！", 'info');
			return false;
		}
	}
	//校验客户房产信息
	var custHouseInfoRows = custHouseTable.datagrid("getRows");
	if(custHouseInfoRows.length>0){
		if(!custHouseTableLockOneRow()){
			$.messager.alert('提示', "客户房产信息输入有误！", 'info');
			return false;
		}
	}
	//校验客户车辆信息
	var custCarInfoRows = custCarTable.datagrid("getRows");
	if(custCarInfoRows.length>0){
		if(!custCarTableLockOneRow()){
			$.messager.alert('提示', "客户车辆信息输入有误！", 'info');
			return false;
		}
	}
	return true;
}



function saveCustAllInfo(){
	//校验提交信息
	if(!verifyCustAllInfo()){
		return;
	}
	//客户信息
	var customerInfo = {};
	//1.获取客户基本信息
	var custBaseInfoformData = $("#addCust_CustBaseInfoForm").serialize();
	custBaseInfoformData = formDataToJsonStr(custBaseInfoformData);
	customerInfo.custBaseInfo = custBaseInfoformData;
	//2.获取客户客户个人联系信息
	var contactFormData = $("#addCust_CustContactInfoForm").serialize();
	contactFormData = formDataToJsonStr(contactFormData);
	customerInfo.custContactInfo = contactFormData;
	//3.从datagrid中获取客户地址信息
	var addressData = custAddressTable.datagrid('getData');
	customerInfo.custAddressList = $.toJSON(addressData.rows)
	//4.获取客户账户信息
	var custAccData = custAccountTable.datagrid('getData');
	customerInfo.custAccInfo = $.toJSON(custAccData.rows)
	//5.获取客户个人信息
	var custPersonalInfoFormData = $("#addCust_CustPersonalInfoForm").serialize();
	var custOthInfoFormData = $("#addCust_custAssetInfoForm").serialize();
	custPersonalInfoFormData = custPersonalInfoFormData+"&"+custOthInfoFormData;
	custPersonalInfoFormData = formDataToJsonStr(custPersonalInfoFormData);
	customerInfo.custPersonalInfo = custPersonalInfoFormData;
	//6.获取客户爱好信息
	var hobbyList = [];
	$('input[name="hobbyCode"]:checked').each(function(){ 
		var hobby={};
		hobby.hobbyCode = $(this).val();
		hobbyList.push(hobby);    
	});   
	hobbyList = $.toJSON(hobbyList);
	customerInfo.hobbyInfo = hobbyList;
	//7.获取客户收入来源信息
	var incomeList = [];
	var reg = /^([1-9]\d?(\.{1}\d+)?|100)$/ ;
	var verifyIncome = true;
	var verifyIncomeMsg = null;
	var verifyIncomePercentSum = 0;
	$('input[name="incomeCode"]:checked').each(function(){ 
		var income={};
		var incomeCode = $(this).val();
		var incomePercent = $("#addCust_IncomePercent"+incomeCode).val();
		//判断了勾选收入来源未填写收入百分比的项
		if(incomePercent==null||incomePercent==""||incomePercent==undefined){
			verifyIncomeMsg = "有选择了收入来源，未填写收入比例的选项";
			verifyIncome = false;
		}else{
			//判断了勾选收入来源收入百分比不为0-100的数字
			if(!reg.test(incomePercent)){
				verifyIncome = false;
				verifyIncomeMsg = "收入来源百分比只能录入0—100的数字";
			}else{
				verifyIncomePercentSum += parseFloat(incomePercent);
			}
		}
		
		income.incomeCode = incomeCode;
		income.percent = incomePercent;
		incomeList.push(income);    
	});   
	if(!verifyIncome){
		$.messager.alert('提示', verifyIncomeMsg);
		return;
	}
	if(verifyIncomePercentSum>100){
		$.messager.alert('提示', "收入来源百分比之和大于100,请调整收入来源百分比");
		return;
	}
	incomeList = $.toJSON(incomeList);
	customerInfo.incomeList = incomeList;
	//8.获取资产构成信息
	var verifyAsset = true;
	var verifyAssetMsg = null;
	var verifyAssetPercentSum = 0;
	var assetList = [];
	$('input[name="assetCode"]:checked').each(function(){ 
		var asset={};
		var assetCode = $(this).val();
		var assetPercent = $("#addCust_AssetPercent"+assetCode).val();
		if(assetPercent==null||assetPercent==""||assetPercent==undefined){
			verifyAssetMsg = "有选择了资产构成，未填写构成比例的选项";
			verifyAsset = false;
		}else{
			if(!reg.test(assetPercent)){
				verifyAsset = false;
				verifyAssetMsg = "资产构成百分比只能录入0—100的数字";
			}else{
				verifyAssetPercentSum += parseFloat(assetPercent);
			}
		}
		
		asset.assetCode = assetCode;
		asset.percent = assetPercent;
		assetList.push(asset);    
	});  
	if(!verifyAsset){
		$.messager.alert('提示', verifyAssetMsg);
		return;
	}
	if(verifyAssetPercentSum>100){
		$.messager.alert('提示', "资产构成百分比之和大于100,请调整资产构成百分比");
		return;
	}
	assetList = $.toJSON(assetList);
	customerInfo.assetList = assetList;
	//9.获取投资金融产品类型
	var investList = [];
	$('input[name="investCode"]:checked').each(function(){ 
		var invest={};
		var investCode = $(this).val();
		invest.investCode = investCode;
		investList.push(invest);    
	});  
	investList = $.toJSON(investList);
	customerInfo.investList = investList;
	//10.获取客户家庭成员信息
	var custFamilyData = custFamilyTable.datagrid('getData');
	customerInfo.familyInfoList = $.toJSON(custFamilyData.rows)
	//11.获取客户房产信息
	var custHouseData = custHouseTable.datagrid('getData');
	customerInfo.houseInfoList = $.toJSON(custHouseData.rows)
	//12.获取客户车辆信息
	var custCarData = custCarTable.datagrid('getData');
	customerInfo.carInfoList = $.toJSON(custCarData.rows)
	//财富顾问编码
	var agentId = $("#addCust_agentId").val();
	customerInfo.operate = operate;
	customerInfo.agentId = agentId;
	//提交到后台
	$('#addCust_submitCarInfoButton').linkbutton('disable');
	$.ajax({
		type:'post',
		url:'customer/saveCustAllInfo',
		//data:'customerInfo='+$.toJSON(customerInfo),
		data:'customerInfo='+encodeURI($.toJSON(customerInfo)),
		cache:false,
		success:function(resultInfo){
			$('#addCust_submitCarInfoButton').linkbutton('enable');
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					$.messager.alert('提示', resultInfo.msg);
					getCustDetailInfo();
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


