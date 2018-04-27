var tradeFundsShareChangeId;
var originTradeInfoId;
var tradeInfoId;
var productId;
var tradeType;
var agentId;
/**
 * 初始化列表
 */
jQuery(function($) {
	//获取页面传值
	originTradeInfoId=$("#transferAuditInput_originTradeInfoId").val();
	tradeInfoId=$("#transferAuditInput_tradeInfoId").val();
	productId=$("#transferAuditInputproductId").val();
	tradeFundsShareChangeId=$("#transferAuditInput_tradeFundsShareChangeId").val();
	tradeType=$("#transferAuditInput_tradeType").val();
	agentId=$("#transferAuditInputagentId").val();
	//客户信息
	initTransferAuditCustInfoTable();
	//交易账户信息
	initTransferAuditTradeBankInfoTable();
	//交易地址信息
	initTransferAuditTradeAddressInfoTable();
	//交易产品信息
	initTransferAuditTradeProInfoTable();
	//下拉框
	initAllCombobox();
	//初始化交易金额、产品、机构等信息
	getTransferAuditInfo();
	});

/**
 * 下拉框
 */
function initAllCombobox(){
	//审核结论
	$("#transferAuditConclusion").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=tradeShareChangeConclusion',
		valueField:'code',
		textField:'codeName',
		editable:false
	});
	//交易类型
	$("#transferAuditInput_tradeType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType&productType=1',
		valueField:'code',
		textField:'codeName',
		value:"1",
		editable:false,
		disabled:true
	});
	//交易机构
	$("#transferAuditInput_tradeComId").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false,
		disabled:true
	});
	//币种
	$("#transferAuditInput_currency").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName',
		value:'CNY',
		editable:false,
		disabled:true
	});
	//现化理财经理
	$("#transferAuditInput_agentId").combobox({
		url : contextPath+"/codeQuery/agentQuery",
		//required : true,
		valueField : 'id',
		textField : 'name',
		editable:false,
		disabled:true
	});
	//原理财经理
	$("#transferAuditInput_originAgentId").combobox({
		url : contextPath+"/codeQuery/agentQuery",
		//required : true,
		valueField : 'id',
		textField : 'name',
		editable:false,
		disabled:true
	});
	//所属机构
	$("#transferAuditInput_companyId").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false,
		disabled:true
	});
	//产品
//	$("#transferAuditInput_productId").combobox({
//		url:contextPath+'/trade/queryTradeRiskProId?proType=1',
//		valueField:'proId',
//		textField:'pro',
//		editable:false,
//		disabled:true
//	});
}

/**
 * 客户信息列表
 */
var custInfoTransferAuditTable;
function initTransferAuditCustInfoTable()
{
	custInfoTransferAuditTable=$("#transferAuditInput_custInfoId").datagrid({
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
	var customerData = $("#transferAuditInput_custInfoId").datagrid('getSelections');
	if (customerData.length == 0) {
		$.messager.alert('提示',"请选择一个客户进行删除!");
		return;
	}
	if(!confirm("是否确认删除选中的客户信息?")){
		return;
	}
	var tradeCustBaseInfo = customerData[0];
	var tradeBaseInfo = formDataToJsonStr($("#transferAuditInputForm").serialize());
	var custBaseInfoId = tradeCustBaseInfo.custBaseInfoId;
	var tradeCustInfoId = tradeCustBaseInfo.tradeCustInfoId;
	if(tradeCustInfoId==null||tradeCustInfoId==""||tradeCustInfoId==undefined){
		$("#transferAuditInput_custInfoId").datagrid('deleteRow',operateCustomerInfoIndex);
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
					$("#transferAuditInput_custInfoId").datagrid('reload');
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
function initTransferAuditTradeBankInfoTable()
{
	tradeBankInfoTransferAuditTable=$("#transferAuditInput_tradeBankInfoId").datagrid({
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
function initTransferAuditTradeAddressInfoTable()
{
	var tradeAddressTransferAuditTable;
	tradeAddressTransferAuditTable=$("#transferAuditInput_tradeAddressInfoId").datagrid({
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

/**
 * 交易产品信息列表
 */
var transferAuditProInfoTable;
function initTransferAuditTradeProInfoTable(){

	transferAuditProInfoTable=$("#transferAuditProInfoTable").datagrid({
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
	           {field:'proCodeName',title:'产品',width:50,hidden:true},
	           getTradeAuditPdDetailInfo(productId),
	           initTransferAuditFactoryInfo(contextPath
						+'/trade/queryTransFerMoneyProInfoObjList?tradeInfoId='+originTradeInfoId+'&proId='+productId),
		       ]]
//		 onCheck:function(rowIndex,rowData)
//		 {
//			 
//			$("#transferAuditInput_productId").combobox("setValue",rowData.productId);
//			$("#transferAuditInput_productId").combobox("setText",rowData.proCodeName);
//			getTradeAuditPdDetailInfo(rowData.productId);
//			initTransferAuditFactoryInfo(contextPath
//					+'/trade/queryTransFerMoneyProInfoObjList?tradeInfoId='+originTradeInfoId+'&proId='+rowData.productId);
//		 }
		
	});
}


//产品信息
function getTradeAuditPdDetailInfo(productId){
	$.ajax({
		type:'post',
		url:'trade/queryWealthProductDetailInfo',
		data:'productId='+productId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					var dataObj = returnData.obj;
					setInputValueById("transferAuditProInfoDiv",dataObj);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

//产品录入项信息
var transferAuditProInfoInputTable;
function initTransferAuditFactoryInfo(urlPath)
{
	transferAuditProInfoInputTable=$("#transferAuditProInfoInputTable").datagrid({
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
					 $("#transferAuditProInfoInputTable").datagrid('addEditor', {
		                    field : 'inputValue',
		                    editor : {
		                        type : 'text',
		                        options : {
		                            required : true
		                        }
		                    }
		                });
				 }else{
					 $("#transferAuditProInfoInputTable").datagrid('addEditor', {
		                    field : 'inputValue',
		                    editor : {
		                        type : 'text'
		                    }
		                });
				 }
				
			}else if(rowData.factorType=='1'){
				var factorCode = rowData.code;
				var productId = productId;
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
						$("#transferAuditProInfoInputTable").datagrid('addEditor', {
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
					$("#transferAuditProInfoInputTable").datagrid('addEditor', {
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
					$("#transferAuditProInfoInputTable").datagrid('addEditor', {
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
					$("#transferAuditProInfoInputTable").datagrid('addEditor', {
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
						$("#transferAuditProInfoInputTable").datagrid('addEditor', {
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
						$("#transferAuditProInfoInputTable").datagrid('addEditor', {
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
			$("#transferAuditProInfoInputTable").datagrid('beginEdit',rowIndex);
			//=========================================
			 var paramCode = rowData.code;
			 if(paramCode=="subscriptionFee"){
				 var rowIndex = $('#transferAuditProInfoInputTable').datagrid('getRowIndex', rowData);  
				 var editors = $('#transferAuditProInfoInputTable').datagrid('getEditors', rowIndex);
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

//初始化交易金额、产品、机构等信息
function getTransferAuditInfo(){
	var param = {};
	param.productId =  productId;
	param.tradeFundsShareChangeId = tradeFundsShareChangeId;
	$.ajax({
		type:'post',
		url:contextPath+"/funds/getTransferAuditProInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var dataObj = reData.obj;
					if(dataObj!=null){
						//交易金额
						$("#monTotalAssets_Input").val(dataObj.transferAsset);
						//交易金额大写
						$("#chnMonTotalAssets_Input").val(dataObj.chnMonTotalAssets);
				 clearAllRows(transferAuditProInfoTable);
				if(dataObj.productMapList!=null&&dataObj.productMapList!=undefined&&dataObj.productMapList!=""){
					loadJsonObjData("transferAuditProInfoTable",dataObj.productMapList);				
				}
					}
				}
				else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	}); 
}


/**
 * 上传附件
 */
function uploadTransferAuditInput(){
	var param = {};
	var loadFileType=null;
	param.businessNo = tradeInfoId;
	param.businessType = "04";
	param.operate = loadFileType;
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}

//上传附件窗口
function addFileWindow(title, href) 
{
	$('<div id="addFileWindow"/>').dialog({
	href : href,
	modal : true,
	title : title,
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

/**
 * 提交审核结论
 */
function submitTransferAuditInput(){
	var tradeId_Check = tradeInfoId;
	var tradeType_Check = tradeType;
	var tradeFundsShareChangeId_Check = tradeFundsShareChangeId;
	var tradeConclusion = $("#transferAuditConclusion").combobox('getValue');
	var tradeRemark = $("#transferAuditRemark").val();
	if(tradeConclusion == null){
		$.messager.alert('提示', "请填写审核结论！！！");	
		return;
	}
	$.ajax({
		type:'post',
		url:'funds/saveTradeAudit',
		data:'tradeId='+tradeId_Check+'&tradeType='+tradeType_Check
		      +'&tradeOperationNode=02'+'&tradeConclusion='+tradeConclusion+'&tradeRemark='+tradeRemark
		      +'&tradeFundsShareChangeId='+tradeFundsShareChangeId_Check+'&productId='+productId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//$("#roleInfoCheckId").datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', "审核完毕！");
					$("#TransferAuditInputWindows").dialog('close');
					$("#transferAuditTable").datagrid('reload');
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
 * 返回
 */
function transferAuditInputBack(){
	$("#TransferAuditInputWindows").window("destroy");
	initTransferAuditTable();
}