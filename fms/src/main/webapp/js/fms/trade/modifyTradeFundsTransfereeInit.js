var tradeInfoId;
var agentId;
var productId;
var tradeType;
var tradeFundsShareChangeId;
var orTradeId;
var tradeStatus;
/**
 * 初始化列表
 */
jQuery(function($){
	//获取页面传值
	tradeInfoId=$("#tradeTransFeree_hOrTradeId").val();
	agentId=$("#tradeTransFeree_horAgentId").val();
	productId=$("#tradeTransFeree_hproductId").val();
	tradeType=$("#tradeTransFeree_htradeType").val();
	orTradeId=$("#tradeTransFeree_horTradeId").val();
	tradeStatus=$("#tradeTransFeree_htradeStatus").val();
	tradeFundsShareChangeId=$("#tradeTransFeree_htradeFundsShareChangeId").val();
	//客户信息列表
//	initCustomInfoInputId();
	initCustomInfoId();
	//交易账户信息列表
	initBankInfoInputPanelId();
	//交易地址信息列表
	initAddressInfoInputPanelId();
	//初始化下拉框
	initAllCombobox();
	//初始化受让产品信息
	initTradeTransFereeProductInfo();
	//初始化受让产品录入项信息
	inittradeTransFactoryInfo("");
	//初始化理财经理、交易金额、产品、机构等信息
	getTradeTransFereeInfo();
	//初始化审核结论
	initTradeCheckConclusion();
	//初始化合同信息
	initContractNum();
	
})
//初始化合同信息
function initContractNum(){
	var productID = $("#tradeTransFeree_hproductId").val();
	$("#tradeInfoNo_Input").combobox({
		url : contextPath + '/codeQuery/contractNumberQuery?productId='+productID,
		valueField : 'id',
		textField : 'name'
	});
	//原值
	$("#tradeInfoNo_Input").combobox("setValue",$("#contractNumbers_Input").val());
}
//初始化下拉框
function initAllCombobox()
{
	//初始化客户下拉框信息
	$("#chCustomers_input").combobox({
		url:contextPath+'/codeQuery/customerQueryByUserId?userId='+userId,
		valueField:'id',
		textField:'codeName',
		onSelect:function(record){
			//初始化客户基本信息
				initCustomInfoByCustInput(record.id);
				//initRoleInfoId();
				//初始化客户银行信息
				initTradeBankInfoInputId(record.id,"getByCustId");
				var tradeType_Input = $("#tradeType_Input").combobox('getValue');
				if(tradeType_Input!=null && tradeType_Input=="1"){
					//初始化客户地址信息
					initTradeAddressInfoInputId(record.id,"getByCustBaseInfoId");
				}
		}
	});
	
	if(tradeStatus==12){
		$("#modifyTransferAuditConclusionId").hide()
	}
	//审核结论
	$("#tradeCheckConclusion_Audit").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=tradeShareChangeConclusion',
		valueField:'code',
		textField:'codeName',
		editable:false
	});
	//初始化交易类型
	$("#tradeType_Input").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType&productType=1',
		valueField:'code',
		textField:'codeName',
		value:"1"
	});
	//初始化交易机构
	$("#tradeComID_Input").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false
	});
	//初始化币种
	$("#currency_Input").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName',
		value:'CNY'
	});
	//初始化产品
	$("#tradeWealthProId").combobox({
		//url:contextPath+'/trade/queryTradeRiskProId?proType=1',
		valueField:'productId',
		textField:'proCodeName'
	});
	//初始现化理财经理
	$("#agentID_Input").combobox({
		valueField:'agentId',
		textField:'agentName'
	});
	//初始化原理财经理
	$("#orAgentId_Input").combobox({
		valueField:'orAgentId',
		textField:'orAgentName'
	});
	//初始化所属机构
	$("#companyID_Input").combobox({
		valueField:'code',
		textField:'codeName'
	});
	//初始化录入日期
	var currentDate = getCurrentDate();
	$("#tradeInputDate_Input").datebox({required:true});
	$("#tradeInputDate_Input").datebox("setValue",currentDate);
}



/**
 * 客户信息
 */
/*var custInfoTransferAuditTable;
function initCustomInfoInputId(){
	custInfoTransferAuditTable=$("#customInfoInputId").datagrid({
		url:'trade/queryCustomInfoInputGrid?tradeId='+tradeInfoId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		columns:[[  
	           {field:'ck',checkbox:true}, 
	           {field:'custBaseInfoId',title:'客户流水号',width:50,hidden:true}, 
	           //客户号做客户明细信息超链接
	           {field:'customerNo',title:'客户号',width:50,
	        	   formatter : function(value, row, index) {
						var param = {};
						param.loadFlag = "custDetail";
						param.custBaseInfoId = row.custBaseInfoId;
						param = $.toJSON(param);
						return "<a href='#'  onclick=addcustomeWindow1('客户明细信息','"+contextPath+"/customer/addCustomerUrl?param="+param+"')>"+row.customerNo+"</a>";
					} // 需要formatter}, 
	           },
	           {field:'chnName',title:'姓名',width:50},
	           {field:'sex',title:'性别',width:50,hidden:true},
	           {field:'sexName',title:'性别',width:50},  
	           {field:'birthday',title:'出生日期',width:50},
	           {field:'idType',title:'证件类型',width:50,hidden:true},   
	           {field:'idTypeName',title:'证件类型',width:50}, 
	           {field:'idNo',title:'证件号码',width:50},
	           {field:'age',title:'年龄（周岁）',width:50},
	           {field:'mobile',title:'手机',width:50},
	           {field:'idValidityDate',title:'证件有效期',width:50}
		       ]],
       toolbar: [{
		   		iconCls: 'icon-add',
		   		text : '新增',
		   		handler: function(){
		   			var param = {};
					param.loadFlag = "addCust";
					param.tradeId = tradeInfoId;
					param.agentId = agentId;
					param = $.toJSON(param);
					addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
		   		}
		   	},'-',{
		   		iconCls: 'icon-edit',
		   		text : '更新',
		   		handler: function(){
		   			var rows = custInfoTransferAuditTable.datagrid('getSelections');
		   			if (rows.length == 0) {
		   				$.messager.alert('提示',"请选择一个客户进行修改");
						return;
					}
					if (rows.length > 1) {
						$.messager.alert('提示',"只能选择一个客户修改");
						return;
					}
					var oneRowData = rows[0];
					oneRowData.loadFlag = "updateCust";
					oneRowData.tradeId = tradeInfoId;
					oneRowData.agentId = agentId;
					oneRowData = $.toJSON(oneRowData);
					addcustomeWindow('更新客户', contextPath+"/customer/updateCustomerUrl?custBaseInfo="+oneRowData);
		   		}
		   	},'-',{
		   		iconCls: 'icon-remove',
		   		text : '删除',
		   		handler: function(){
		   			delTransferAuditCustInfo();
		   		}
		   	}]
	});
}*/
//客户信息
var customInfoInputId;
var operateCustomerInfoIndex = null;
var investCustomerType = '01';
var custLevel = '02';
var custBaseInfoId ;
function initCustomInfoId()
{
	//var tradeId = $("#tradeId_Input").val();
	customInfoInputId=$("#customInfoInputId").datagrid({
		url:'trade/queryCustomInfoInputGrid?tradeId='+tradeInfoId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		columns:[[  
		           {field:'ck',checkbox:true}, 
		           {field:'custBaseInfoId',title:'客户流水号',width:50,hidden:true}, 
		           //客户号做客户明细信息超链接
		           {field:'customerNo',title:'客户号',width:50,
		        	   formatter : function(value, row, index) {
							var param = {};
							param.loadFlag = "custDetail";
							param.custBaseInfoId = row.custBaseInfoId;
							param = $.toJSON(param);
							return "<a href='#'  onclick=addcustomeWindow1('客户明细信息','"+contextPath+"/customer/addCustomerUrl?param="+param+"')>"+row.customerNo+"</a>";
						} // 需要formatter}, 
		           },
		           {field:'chnName',title:'姓名',width:50},
		           {field:'sex',title:'性别',width:50,hidden:true},
		           {field:'sexName',title:'性别',width:50},  
		           {field:'birthday',title:'出生日期',width:50},
		           {field:'idType',title:'证件类型',width:50,hidden:true},   
		           {field:'idTypeName',title:'证件类型',width:50}, 
		           {field:'idNo',title:'证件号码',width:50},
		           {field:'age',title:'年龄（周岁）',width:50},
		           {field:'mobile',title:'手机',width:50},
		           {field:'idValidityDate',title:'证件有效期',width:50}
			       ]],
       toolbar: [/*{
		   		iconCls: 'icon-addrow',
		   		text : '新增',
		   		handler: function(){
		   		//	alert("新增操作后请提交客户信息！");
		   			var tradeId = $("#tradeId_Input").val();
		   			if(tradeId==null||tradeId==""||tradeId==undefined){
		   				$.messager.alert('提示', "请先保存交易基本信息后再增加客户！");
		   				return;
		   			}
		   			
		   			var param = {};
					param.loadFlag = "addCust";
					param.tradeId = $("#tradeId_Input").val();
					param.agentId = $("#agentID_Input").combobox('getValue');
					param = $.toJSON(param);
					addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
		   		}
		   	},'-',*/{
		   		iconCls: 'icon-edit',
		   		text : '修改',
		   		handler: function(){
		   			//alert("更新操作后请提交客户信息！");
		   			var rows = customInfoInputId.datagrid('getSelections');
		   			var rowss = customInfoInputId.datagrid('getRows');
		   			if (rowss.length>1&&rows.length == 0) {
		   				$.messager.alert('提示',"请选择一个客户进行修改");
						return;
					}else if (rowss.length>1&&rows.length > 1) {
						$.messager.alert('提示',"只能选择一个客户修改");
						return;
					}else if (rowss.length>1&&rows.length == 1) {
						var oneRowData = rows[0];		
					}else if(rowss.length==1){
						var oneRowData = rowss[0];
					}		   			
					oneRowData.loadFlag = "updateCust";
					oneRowData.tradeId = $("#tradeId_Input").val();
					oneRowData.agentId = $("#agentID_Input").combobox('getValue');
					oneRowData = $.toJSON(oneRowData);
					addcustomeWindow('更新客户', contextPath+"/customer/updateCustomerUrl?custBaseInfo="+oneRowData);
		   		}
		   	},/*'-',{
		   		iconCls: 'icon-delete',
		   		text : '删除',
		   		handler: function(){
		   			delCustomerInfo();
		   		}
		   	}*/],
		 onCheck:function(rowIndex,rowData){
			//alert('未实现的方法');
			 operateCustomerInfoIndex = rowIndex;
			 
		 }
		
	});
}
function initCustomInfoByCustInput(custId)
{
	customInfoInputId=$("#customInfoInputId").datagrid({
		url:'trade/queryCustomInfoInputGridById?custId='+custId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		columns:[[  
	           {field:'ck',checkbox:true}, 
	           {field:'tradeCustInfoId',title:'交易客户流水号',width:50,hidden:true}, 
	           {field:'custBaseInfoId',title:'客户流水号',width:50,hidden:true}, 
	           {field:'customerNo',title:'客户号',width:50,
	        	   formatter : function(value, row, index) {
						//return row.customerNo;
						//addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
						var param = {};
						param.loadFlag = "custDetail";
						param.custBaseInfoId = row.custBaseInfoId;
						param = $.toJSON(param);
						return "<a href='#'  onclick=addcustomeWindow1('客户明细信息','"+contextPath+"/customer/addCustomerUrl?param="+param+"')>"+row.customerNo+"</a>";
					} // 需要formatter
	        	   }, 
	           //{field:'customerNo',title:'客户号',width:50},    
	           {field:'chnName',title:'姓名',width:50},
	           {field:'sex',title:'性别',width:50,hidden:true},
	           {field:'sexName',title:'性别',width:50},  
	           {field:'birthday',title:'出生日期',width:50},
	           {field:'idType',title:'证件类型',width:50,hidden:true},   
	           {field:'idTypeName',title:'证件类型',width:50}, 
	           {field:'idNo',title:'证件号码',width:50},
	          /* {field:'mobile',title:'联系电话',width:50},
	           {field:'age',title:'年龄（周岁）',width:50},*/
	           {field:'idValidityDate',title:'证件有效期',width:50}   
		       ]],
       toolbar: [/*{
		   		iconCls: 'icon-addrow',
		   		text : '新增',
		   		handler: function(){
		   		//	alert("新增操作后请提交客户信息！");
		   			var tradeId = $("#tradeId_Input").val();
		   			if(tradeId==null||tradeId==""||tradeId==undefined){
		   				$.messager.alert('提示', "请先保存交易基本信息后再增加客户！");
		   				return;
		   			}
		   			
		   			var param = {};
					param.loadFlag = "addCust";
					param.tradeId = $("#tradeId_Input").val();
					param.agentId = $("#agentID_Input").combobox('getValue');
					param = $.toJSON(param);
					addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
		   		}
		   	},'-',*/{
		   		iconCls: 'icon-edit',
		   		text : '修改',
		   		handler: function(){
		   			//alert("更新操作后请提交客户信息！");
		   			var rows = customInfoInputId.datagrid('getSelections');
		   			var rowss = customInfoInputId.datagrid('getRows');
		   			if (rowss.length>1&&rows.length == 0) {
		   				$.messager.alert('提示',"请选择一个客户进行修改");
						return;
					}else if (rowss.length>1&&rows.length > 1) {
						$.messager.alert('提示',"只能选择一个客户修改");
						return;
					}else if (rowss.length>1&&rows.length == 1) {
						var oneRowData = rows[0];		
					}else if(rowss.length==1){
						var oneRowData = rowss[0];
					}		   			
					oneRowData.loadFlag = "updateCust";
					oneRowData.tradeId = $("#tradeId_Input").val();
					oneRowData.agentId = $("#agentID_Input").combobox('getValue');
					oneRowData = $.toJSON(oneRowData);
					addcustomeWindow('更新客户', contextPath+"/customer/updateCustomerUrl?custBaseInfo="+oneRowData);
		   		}
		   	},/*'-',{
		   		iconCls: 'icon-delete',
		   		text : '删除',
		   		handler: function(){
		   			delCustomerInfo();
		   		}
		   	}*/],
		 onCheck:function(rowIndex,rowData){
			//alert('未实现的方法');
			 operateCustomerInfoIndex = rowIndex;
			 
		 }
		
	});
}

/**
 * 客户明细信息窗口
 * @param title
 * @param href
 */
function addcustomeWindow1(title, href) 
{
	$('<div id="addcustomerWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true, 
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}

/**
 * 新增客户窗口
 * @param title
 * @param href
 */
function addcustomeWindow(title, href) {
	$('<div id="addCustomeInputWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true, 
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}

/**
 * 删除客户信息
 */
function delTransferAuditCustInfo(){
	var customerData = $("#customInfoInputId").datagrid('getSelections');
	if (customerData.length == 0) {
		$.messager.alert('提示',"请选择一个客户进行删除!");
		return;
	}
	if(!confirm("是否确认删除选中的客户信息?")){
		return;
	}
	var tradeCustBaseInfo = customerData[0];
	var tradeBaseInfo = formDataToJsonStr($("#tradeTransFereeInfoForm_input").serialize());
	var custBaseInfoId = tradeCustBaseInfo.custBaseInfoId;
	var tradeCustInfoId = tradeCustBaseInfo.tradeCustInfoId;
	if(tradeCustInfoId==null||tradeCustInfoId==""||tradeCustInfoId==undefined){
		$("#customInfoInputId").datagrid('deleteRow',operateCustomerInfoIndex);
		operateCustomerInfoIndex = null;
		return;
	}
	var param = {};
	param.tradeCustInfo = tradeCustBaseInfo;
	param.tradeInfo = tradeBaseInfo;
	
	var tradeType_Check = tradeType;
	var tradeId = tradeInfoId;
	$.ajax({
		type:'post',
		url:'trade/delTradeCusInfo',
		//data:'queryParam='+$.toJSON(customerData)+'&tradeId='+tradeId+'&custBaseInfoId='+custBaseInfoId,
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//tradeInputList.datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					$("#customInfoInputId").datagrid('reload');
					//重新加载银行账户信息
					initTradeBankInfoCheckId(tradeId,"query");
					if(tradeType_Check!=null && tradeType_Check=="1"){
						initTradeAddressInfoCheckId(tradeId,"query");
					}else{
						initRoleInfoId();
						initRiskProCheckInfoId("2");
						initRiskProductInfoCheckId();
						$("#monTotalAssets_Input").val("");
						$("#chnMonTotalAssets_Input").val("");
//						$("#tradeRiskProtObj_Check_In").combobox("reset");
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
 * 交易账户列表信息
 */
var tradeBankInfoTransferAuditTable;
function initBankInfoInputPanelId()
{
	tradeBankInfoTransferAuditTable=$("#tradeBankInfoInputId").datagrid({
		url:contextPath+'/trade/queryTradeBankInfo?tradeId='+tradeInfoId+'&tradeType='+tradeType,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		columns:[[  
	           //{field:'ck',checkbox:true},    
	           {field:'tradeCustInfoId',title:'交易客户流水号',width:50,hidden:true},
	           {field:'custAccInfoId',title:'客户账户信息流水号',width:50,hidden:true},    
	           {field:'bankCode',title:'开户行',width:50,hidden:true},
	           {field:'bankName',title:'开户行名称',width:50},
	           {field:'branchBankName',title:'支行名称',width:50},
	           {field:'accName',title:'开户名',width:50},
	           {field:'bankAccNo',title:'开户账号',width:50},
	           {field:'accFlagName',title : '是否交易默认账户',width : 50}
		       ]]
	});
}

/**
 * 交易地址信息列表
 */
function initAddressInfoInputPanelId()
{
	var tradeAddressTransferAuditTable;
	tradeAddressTransferAuditTable=$("#tradeAddressInfoInputId").datagrid({
		url:contextPath+'/trade/queryTradeAddressInfo?tradeId='+tradeInfoId+'&tradeType='+tradeType+'&agentId='+agentId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		columns:[[  
	           //{field:'ck',checkbox:true}, 
				{field:'tradeCustInfoId',title:'交易客户信息流水号',width:50,hidden:true}, 
		           {field:'custAddressInfoId',title:'客户地址信息流水号',width:50,hidden:true},    
		           {
						field : 'addressType',
						title : '地址类型',
						width : 50,
						hidden : true
					},
					{
						field : 'addressTypeName',
						width : 50,
						title : '地址类型名称'
					},
					{
						field : 'province',
						title : '省',
						width : 50,
						hidden : true
					},{
						field : 'provinceName',
						width : 50,
						title : '省名称'
					},{
						field : 'city',
						title : '市',
						width : 60,
						hidden : true
					},{
						field : 'cityName',
						width : 60,
						title : '市名称'
					},{
						field : 'country',
						title : '区/县',
						width : 70,
						hidden : true
					},{
						field : 'countryName',
						width : 70,
						title : '区/县名称'
					},{
						field : 'street',
						title : '详细地址',
						width : 100
					},{
						field : 'zipCode',
						title : '邮政编码',
						width : 50
					},{
						field : 'addressFlagName',
						title : '是否交易默认地址',
						width : 40
					}
		       ]]
	});
}

//提交受让交易信息
function submitTradeTreansFereeInfo()
{
	if(!$("#tradeTransFereeInfoForm_input").form("validate")){
		return;
	}
	var formData = $("#tradeTransFereeInfoForm_input").serialize();
	var tradeInPutDate=$("#tradeInputDate_Input").val();
	formData = formDataToJsonStr(formData);
	$.ajax({
		type:'post',
		url:'funds/saveTradeInfo',
		data:'param='+formData,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					$.messager.alert('提示', returnData.msg);
					$("#tradeId_Input").val(returnData.obj.tradeInfoId);
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
 * 初始化受让产品信息
 */
var  monProInfoInputId;
function initTradeTransFereeProductInfo()
{
	monProInfoInputId=$("#monProInfoInputId").datagrid({
		//url:contextPath+'/trade/queryTradeWealthProInfo?productId='+productId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		 columns:[[  
	           {field:'ck',checkbox:true},    
	           {field:'productCode',title:'产品代码',width:50},    
	           {field:'productName',title:'产品名称',width:50},
	           {field:'productId',title:'产品Id',width:50,hidden:true},
	           {field:'proCodeName',title:'产品',width:50,hidden:true}
		       ]],
		 onCheck:function(rowIndex,rowData)
		 {
			 
			$("#tradeWealthProId").combobox("setValue",rowData.productId);
			$("#tradeWealthProId").combobox("setText",rowData.proCodeName);
			getTradePdDetailInfo(rowData.productId);
			inittradeTransFactoryInfo(contextPath
					+'/trade/queryTransFerMoneyProInfoObjList?tradeInfoId='+orTradeId+'&proId='+rowData.productId);
		 }
		
	});
}

/**
 * 受让产品录入项信息
 */
var monProInfoObjInputId;
function inittradeTransFactoryInfo(urlPath)
{
	monProInfoObjInputId=$("#monProInfoObjInputId").datagrid({
		url:urlPath,
		fitColumns:true,
		rownumbers:true,
		singleSelect:true,
		 columns:[[  
	           {field:'code',title:'录入项编码',width:50,hidden : true},
	           {field:'name',title:'录入项名称',width:50},    
	           {field:'inputValue',title:'录入项值',width:50,
	        	   editor:'text'},
	           {field:'inputUnit',title:'录入项枚举值',width:50},
	           {field:'factorValueCode',title:'录入项值',width:50,hidden:true},
	           {field:'factorType',title:'要素类型',width:50,hidden:true} 
		       ]],
		 onCheck:function(rowIndex,rowData)
		 {
			 if(rowData.factorType=='0'){
				 if(rowData.chooseFlag=='0'){
					 $("#monProInfoObjInputId").datagrid('addEditor', {
		                    field : 'inputValue',
		                    editor : {
		                        type : 'text',
		                        options : {
		                            required : true
		                        }
		                    }
		                });
				 }else{
					 $("#monProInfoObjInputId").datagrid('addEditor', {
		                    field : 'inputValue',
		                    editor : {
		                        type : 'text'
		                    }
		                });
				 }
				
			}else if(rowData.factorType=='1'){
				var factorCode = rowData.code;
				var productId = $("#tradeWealthProId").combobox("getValue");
				var url = contextPath+'/trade/queryDefCode?codeType='+rowData.code+'&factorValueCode='+rowData.factorValueCode;
				//期望开放日
				if(factorCode=="expectOpenDay"){
					url =  contextPath+'/trade/queryOpenDateList?productId='+productId;
				}
				//产品期限
				if(factorCode=="closedPeriods"){
					url =  contextPath+'/trade/queryClosedPeriodsList?productId='+productId;
				}
				if(rowData.chooseFlag=='0'){
						$("#monProInfoObjInputId").datagrid('addEditor', {
		                    field : 'inputValue',
		                    editor : {
		                        type : 'combobox',
		                        options : {
		                        	url:url,
									valueField:'code',
									textField:'codeName',
									editable:false,
		                            required : true
		                        }
		                    }
		                });	
					
				}else{
					$("#monProInfoObjInputId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'combobox',
	                        options : {
	                        	url:contextPath+'/trade/queryDefCode?codeType='+rowData.code+'&factorValueCode='+rowData.factorValueCode,
								valueField:'code',
								textField:'codeName',
								editable:false
	                        }
	                    }
	                });
				}
				
			}else if(rowData.factorType=='2'){
				if(rowData.chooseFlag=='0'){
					$("#monProInfoObjInputId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'datebox',
	                        options : {
	                            required : true,
	                            validType:["validDate"]
	                        }
	                    }
	                });
				}else{
					$("#monProInfoObjInputId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'datebox',
	                        options : {
	                            validType:["validDate"]
	                        }
	                    }
	                });
				}
				
			}else if(rowData.factorType=='3'){
				var factorCode = rowData.code;
					if(rowData.chooseFlag=='0'){
						$("#monProInfoObjInputId").datagrid('addEditor', {
		                    field : 'inputValue',
		                    editor : {
		                        type : 'numberbox',
		                        options : {
		                            required : true,
		                            precision:2
		                        }
		                    }
		                });
					}else{
						$("#monProInfoObjInputId").datagrid('addEditor', {
		                    field : 'inputValue',
		                    editor : {
		                        type : 'numberbox',
		                        options : {
		                            precision:2
		                        }
		                    }
		                });
					}
			}
			$("#monProInfoObjInputId").datagrid('beginEdit',rowIndex);
			//=========================================
			 var paramCode = rowData.code;
			 if(paramCode=="subscriptionFee"){
				 var rowIndex = $('#monProInfoObjInputId').datagrid('getRowIndex', rowData);  
				 var editors = $('#monProInfoObjInputId').datagrid('getEditors', rowIndex);
				 var subscriptionFeeEditor = editors[0];
				 subscriptionFeeEditor.target.bind('input propertychange', function(e){  
                 //将本次修改的值更新到rowData的相应列数据中  
					 var tipsContent = numToUpper($(this).val());
					 $(this).tips({
	                        side:1,  //1,2,3,4 分别代表 上右下左
	                        msg:tipsContent,//tips的文本内容
	                        color:'#FFF',//文字颜色，默认为白色
	                        bg:'#FD9720',//背景色，默认为红色
	                        time:3,//默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
	                        x:0,// 默认为0 横向偏移 正数向右偏移 负数向左偏移
	                        y:0 // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
	                });
             }); 
			 }
			//=============================================
		 }
		
	});
}

/**
 * 初始化理财经理、交易金额、产品、机构等信息
 */
function getTradeTransFereeInfo()
{
	var param = {};
	param.orTradeId = orTradeId;
	param.productId =  productId;
	param.tradeFundsShareChangeId = tradeFundsShareChangeId;
	param.tradeInfoId=tradeInfoId;
	param.tradeStatus=tradeStatus;
	$.ajax({
		type:'post',
		url:contextPath+"/funds/getTradeTransFereeInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var dataObj = reData.obj;
					if(dataObj!=null){
						//现理财经理
						//$("#agentName_Input").val(dataObj.agentName);
						$("#agentID_Input").combobox("setValue",dataObj.agentId);
						$("#agentID_Input").combobox("setText",dataObj.agentName);
						//原理财经理
						//$("#orAgentName_Input").val(dataObj.orAgentName);
						$("#orAgentId_Input").combobox("setValue",dataObj.orAgentId);
						$("#orAgentId_Input").combobox("setText",dataObj.orAgentName);
						//现理财经理所属机构
						$("#companyID_Input").combobox("setValue",dataObj.comId);
						$("#companyID_Input").combobox("setText",dataObj.comName);
						//交易机构
						$("#tradeComID_Input").combobox("setValue",dataObj.tradeComId);
						$("#tradeDate_Input").datebox("setValue",dataObj.tradeDate);
						$("#tradeInfoNo_Input").val(dataObj.tradeInfoNo);
						$("#tradeNo_Input").val(dataObj.tradeNo);
						//交易金额
						$("#monTotalAssets_Input").val(dataObj.transferAsset);
						//交易金额大写
						$("#chnMonTotalAssets_Input").val(dataObj.chnMonTotalAssets);
				 clearAllRows(monProInfoInputId);
				if(dataObj.productMapList!=null&&dataObj.productMapList!=undefined&&dataObj.productMapList!=""){
					loadJsonObjData("monProInfoInputId",dataObj.productMapList);				
				}
					}
				}else{
					$.messager.alert('提示', reData.msg);
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

//查询产品信息
function getTradePdDetailInfo(productId){
		$.ajax({
			type:'post',
			url:'trade/queryWealthProductDetailInfo',
			data:'productId='+productId,
			cache:false,
			success:function(returnData){
				try {
					if(returnData.success){
						var dataObj = returnData.obj;
						setInputValueById("monProInfoSelectDiv",dataObj);
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
 * 初始化审核结论
 */
function initTradeCheckConclusion(){
	var tradeId_Input =  tradeInfoId;
	$.ajax({
		type:'post',
		url:'trade/queryTradeConclusion',
		data:'tradeId='+tradeId_Input+'&tradeOperationNode=02',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$("#tradeCheckConclusion_Audit").combobox('setValue',returnData.tradeConclusion);
					$("#tradeCheckConclusion_Audit").combobox('setText',returnData.tradeConclusionName);
					$("#tradeCheckRemark_Audit").val(returnData.tradeRemark);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}
//初始化客户银行信息
function initTradeBankInfoInputId(id,type)
{
	var custInfoId = id;
	var urlPath = "";
	if(type=="init"){
		
	}else if(type=="query"){
		var tradeType_Input = $("#tradeType_Input").combobox('getValue');
		urlPath = contextPath+'/trade/queryTradeBankInfo?tradeId='+custInfoId+'&tradeType='+tradeType_Input;
	}else if(type=="search"){
		urlPath = contextPath+'/trade/searchTradeBankInfo?custAccInfoId='+custInfoId+'&rcState=E';
	}else if(type=="getByCustId"){
		urlPath = contextPath+'/trade/getAllTBankInfoByCustId?custBaseInfoId='+custInfoId
	}
	$("#tradeBankInfoInputId").datagrid({
		url:urlPath,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		columns:[[  
	           {field:'ck',checkbox:true},
	           {field:'tradeCustInfoId',title:'交易客户流水号',width:50,hidden:true},
	           {field:'custAccInfoId',title:'客户账户信息流水号',width:50,hidden:true},    
	           {field:'bankCode',title:'开户行',width:50,hidden:true},
	           {field:'bankName',title:'开户行名称',width:50},
	           {field:'branchBankName',title:'支行名称',width:50},
	           {field:'accName',title:'开户名',width:50},
	           {field:'bankAccNo',title:'开户账号',width:50},
	           {field:'accFlagName',title : '是否交易默认账户',width : 50}
		       ]]
	});
}
//初始化客户地址信息
function initTradeAddressInfoInputId(id,type)
{
	var custInfoId = id;
	var urlPath = "";
	if(type=="init"){
		
	}else if(type=="query"){
		var tradeType_Input = $("#tradeType_Input").combobox('getValue');
		var agentId_Input = $("#agentID_Input").combobox('getValue');
		urlPath = contextPath+'/trade/queryTradeAddressInfo?tradeId='+custInfoId+'&tradeType='+tradeType_Input+'&agentId='+agentId_Input;
	}else if(type=="search"){
		urlPath = contextPath+'/trade/searchTradeAddressInfo?custAddressInfoId='+custInfoId+'&rcState=E';
	}else if(type=="getByCustBaseInfoId"){
		urlPath = contextPath+'/trade/getTradeAddressInfo?custBaseInfoId='+custInfoId+'&rcState=E';
	}
	$("#tradeAddressInfoInputId").datagrid({
		url:urlPath,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		columns:[[  
	           {field:'ck',checkbox:true},
	           {field:'tradeCustInfoId',title:'交易客户流水号',width:50,hidden:true}, 
	           {field:'custAddressInfoId',title:'客户地址信息流水号',width:50,hidden:true},    
	           {
					field : 'addressType',
					title : '地址类型',
					width : 50,
					hidden : true
				},
				{
					field : 'addressTypeName',
					width : 50,
					title : '地址类型名称'
				},
				{
					field : 'province',
					title : '省',
					width : 50,
					hidden : true
				},{
					field : 'provinceName',
					width : 50,
					title : '省名称'
				},{
					field : 'city',
					title : '市',
					width : 60,
					hidden : true
				},{
					field : 'cityName',
					width : 60,
					title : '市名称'
				},{
					field : 'country',
					title : '区/县',
					width : 70,
					hidden : true
				},{
					field : 'countryName',
					width : 70,
					title : '区/县名称'
				},{
					field : 'street',
					title : '详细地址',
					width : 100
				},{
					field : 'zipCode',
					title : '邮政编码',
					width : 50
				},{
					field : 'addressFlagName',
					title : '是否交易默认地址',
					width : 40
				}
		       ]]
	});
}
function submitTradeCustomerInfo(){
	$("#submitTradeCustomerInfoButton").linkbutton("disable");
	submitTradeCusInfo();
}
function submitTradeCusInfo(){

	//$("#submitTradeCusInfoButton").linkbutton("disable");
	var customerData = $("#customInfoInputId").datagrid('getRows');
	var tradeId =$("#tradeId_Input").val();
	var tradeBaseInfo = formDataToJsonStr($("#tradeTransFereeInfoForm_input").serialize());
	var tradeType_Input = $("#tradeType_Input").combobox('getValue');
	if(tradeType_Input=='1'){
		if(customerData.length>1){
			$.messager.alert('提示',"财富产品只能存在一个客户，请删除多余客户信息！");
			$("#submitTradeCustomerInfoButton").linkbutton("enable");
			return;
		}
		if(customerData.length==0){
			$.messager.alert('提示',"请先选择客户！");
			return;
		}
	}
	if(tradeId==null||tradeId==""||tradeId==undefined){
		$.messager.alert('提示', "请先保存受让交易基本信息");
		$("#submitTradeCustomerInfoButton").linkbutton("enable");
		return;
	}
	var param = {};
	param.customerList = customerData;
	param.tradeInfo = tradeBaseInfo;
	$.ajax({
		type:'post',
		url:'trade/saveTradeCusInfo',
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					var tradeCustInfoId = returnData.obj.tradeCustInfoId;
					submitTradeBankInfoInputId(tradeCustInfoId);//基本信息保存成功后保存账户信息
					//tradeInputList.datagrid('loadData',returnData.obj);	
					/*$.messager.alert('提示', returnData.msg);
					var tradeId = $("#tradeId_Input").val();
					var tradeCustInfoUrl ='trade/queryCustomInfoInputGrid?tradeId='+tradeId;
					customInfoInputId.datagrid('options').url = tradeCustInfoUrl;
					$("#customInfoInputId").datagrid('load');
					initRoleInfoId();
					initTradeBankInfoInputId(tradeId,"query");
					if(tradeType_Input!=null && tradeType_Input=="1"){
						initTradeAddressInfoInputId(tradeId,"query");
					}*/
					$("#submitTradeCustomerInfoButton").linkbutton("enable");					
				}else{
					$("#submitTradeCustomerInfoButton").linkbutton("enable");
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
				$("#submitTradeCustomerInfoButton").linkbutton("enable");
			}
		}
	
	});
}
function submitTradeBankInfoInputId(tradeCustInfoId){
	var selectRow = $('#tradeBankInfoInputId').datagrid('getSelections');
	
	var selectRows = $('#tradeBankInfoInputId').datagrid('getRows');
	if(selectRows.length==1){
		selectRows[0].tradeCustInfoId=tradeCustInfoId;
		selectRow = $.toJSON(selectRows);
	}
	else if(selectRows.length>1&&selectRow.length == 0){
		$.messager.alert('提示',"请选择一条账户信息进行保存!");
		$("#submitTradeCustomerInfoButton").linkbutton("enable");
		return;
	}
	else if (selectRows.length>1&&selectRow.length>1) {
		$.messager.alert('提示',"只能选择一条账户信息进行保存!");
		$("#submitTradeCustomerInfoButton").linkbutton("enable");
		return;
	}else{
		selectRow[0].tradeCustInfoId=tradeCustInfoId;
		selectRow = $.toJSON(selectRow);
	}
	var tradeId = $("#tradeId_Input").val();
	console.info("交易银行"+selectRow);
	$.ajax({
		type:'post',
		url:'trade/saveTradeBankInfo',
		data:'rowData='+selectRow+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					initTradeBankInfoInputId(tradeId,"query");
					submitTradeAddressInfoInputId(tradeCustInfoId);//账户信息保存成功后保存地址信息
					/*$.messager.alert('提示', returnData.msg);*/
				}else{
					$("#submitTradeCustomerInfoButton").linkbutton("enable");
				}
			} catch (e) {
				$.messager.alert('提示', e);
				$("#submitTradeCustomerInfoButton").linkbutton("enable");
			}
		}
	});
}
function submitTradeAddressInfoInputId(tradeCustInfoId){
	var selectRow = $('#tradeAddressInfoInputId').datagrid('getSelections');
	var selectRows=$('#tradeAddressInfoInputId').datagrid('getRows');
	if(selectRows.length==1){
		selectRows[0].tradeCustInfoId=tradeCustInfoId;
		selectRow = $.toJSON(selectRows);
	}else if(selectRows.length>1&&selectRow.length == 0){
		$.messager.alert('提示',"请选择一条地址信息进行保存!");
		$("#submitTradeCustomerInfoButton").linkbutton("enable");
		return;
	}else if(selectRows.length>1&&selectRow.length > 1){
		$.messager.alert('提示',"只能选择一条地址信息进行保存!");
		$("#submitTradeCustomerInfoButton").linkbutton("enable");
		return;
	}else{
		selectRow[0].tradeCustInfoId=tradeCustInfoId;
		selectRow = $.toJSON(selectRow);
	}
	var tradeId = $("#tradeId_Input").val();
	var tradeType_Input = $("#tradeType_Input").combobox('getValue');
	console.info("交易地址"+selectRow);
	$.ajax({
		type:'post',
		url:'trade/saveTradeAddressInfo',
		data:'rowData='+selectRow+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					if(tradeType_Input!=null && tradeType_Input=="1"){
						initTradeAddressInfoInputId(tradeId,"query");
					}
					$.messager.alert('提示', '交易客户信息保存成功');
					$("#submitTradeCustomerInfoButton").linkbutton("enable");
				}else{
					$("#submitTradeCustomerInfoButton").linkbutton("enable");
				}
			} catch (e) {
				$.messager.alert('提示', e);
				$("#submitTradeCustomerInfoButton").linkbutton("enable");
			}
		}
	});
}
/*
 * 提交受让修改
 */
function updateTradeTransFeree(){
	if(!$("#tradeTransFereeInfoForm_input").form("validate")){
		return;
	}
	$.messager.confirm('提示',"是否完成信息修改?",function(r){
		if(r){
			var tradeId_Input = orTradeId;
			var tradeInfoId_Input = tradeInfoId;
			var tradeType_Input = $("#tradeType_Input").combobox("getValue");
			$.ajax({
				type:'post',
				url:'funds/saveTradeInput',
				data:'tradeId='+tradeId_Input+'&tradeType='+tradeType_Input+'&tradeInfoId='+tradeInfoId_Input,
				cache:false,
				success:function(returnData){
					try {
						if(returnData.success=="true"){
							$.messager.alert('提示', returnData.msg);
							$("#transFereeInputWindows").dialog('close');
							$("#tradeFundsTranFereeTable").datagrid('reload');
						}else{
							$.messager.alert('提示', returnData.msg);
						}
					} catch (e) {
						$.messager.alert('提示', e);
					}
				}
			
			});
		}else{
			return;
		}
	})
	
}
/**
 * 返回
 */
function tradeTransFereeInputBack(){
	$("#updateTransfereeWindow").window("destroy");
	initTradeFundsTranFereeTable();
}
