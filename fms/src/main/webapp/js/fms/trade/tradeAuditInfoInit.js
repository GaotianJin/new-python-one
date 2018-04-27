var CustomInfojson={"total":3,"rows":[{'custBaseInfoId':'1','tradeCode':'1','tradeType':'张三'},{'custBaseInfoId':'2','tradeCode':'2','tradeType':'李四'}]}
var RoleInfojson={"total":3,"rows":[{'role':'1'},{'role':'2'}]}
var riskProductInfojson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var riskProductInfoAuditIdJson={"total":3,"rows":[{'AuditName':'1'},{'AuditName':'2'}]}
var monryProductInfoIdjson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var monryProductInfoAuditIdJson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var attachmentInfoIdJson={"total":3,"rows":[{'attachType':'1'},{'attachType':'2'}]};
var loadFileType = null;



jQuery(function($) {
	//客户信息
	initCustomInfoId();
	//customInfoAuditId.datagrid("loadData",CustomInfojson)
	//角色信息
	initRoleInfoId();
	//roleInfoAuditId.datagrid("loadData",RoleInfojson);
	//险种财富信息
	initRiskProAuditInfoId("");
	//riskProAuditInfoId.datagrid("loadData",riskProductInfojson);
	//险种财富信息
	initRiskProductInfoAuditId();
	//riskProInfoObjAuditId.datagrid("loadData",riskProductInfoAuditIdJson);
	//财富产品信息
	initMonryProductInfoId("");
	//monProInfoAuditId.datagrid("loadData",monryProductInfoIdjson);
	//财富产品信息
	initMonryProductInfoAuditId("");
	//monProInfoObjAuditId.datagrid("loadData",monryProductInfoAuditIdJson);
	//附件信息
	initAttachmentInfo();
	//attachInfoAuditId.datagrid("loadData",attachmentInfoIdJson);
	$('#tradeDate').datebox({required:true});
	//初始化下拉框
	initAllCombobox();
	//初始化复核结论信息
	//initTradeCheckConclusion();
	});

//客户信息
var customInfoAuditId;
function initCustomInfoId()
{
	var tradeId = $("#tradeId_Audit_In").val();
	customInfoAuditId=$("#customInfoAuditId").datagrid({
		url:'trade/queryCustomInfoInputGrid?tradeId='+tradeId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		columns:[[  
	           //{field:'ck',checkbox:true}, 
	           {field:'custBaseInfoId',title:'客户流水号',width:50,hidden:true}, 
	           {field:'customerNo',
	        	   title:'客户号',
	        	   width:50,
	        	   formatter : function(value, row, index) {
						//return row.customerNo;
						//addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
						var param = {};
						param.loadFlag = "custDetail";
						param.custBaseInfoId = row.custBaseInfoId;
						param = $.toJSON(param);
						return "<a href='#'  onclick=addcustomeWindow('客户明细信息','"+contextPath+"/customer/addCustomerUrl?param="+param+"')>"+row.customerNo+"</a>";
					} // 需要formatter
	           },    
	           {field:'chnName',title:'姓名',width:50},
	           {field:'sex',title:'性别',width:50,hidden:true},
	           {field:'sexName',title:'性别',width:50},  
	           {field:'birthday',title:'出生日期',width:50},
	           {field:'idType',title:'证件类型',width:50,hidden:true},   
	           {field:'idTypeName',title:'证件类型',width:50}, 
	           {field:'idNo',title:'证件号码',width:50},
	          /* {field:'age',title:'年龄（周岁）',width:50},
	           {field:'mobile',title:'手机',width:50},*/
	           {field:'idValidityDate',title:'证件有效期',width:50}
		       ]],
       toolbar: [{
		   		iconCls: 'icon-add',
		   		text : '新增',
		   		handler: function(){
		   			var param = {};
					param.loadFlag = "addCust";
					param.tradeId = tradeId;
					param.agentId = $("#agentID_Audit_In").combobox('getValue');
					param = $.toJSON(param);
					addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
		   		}
		   	},'-',{
		   		iconCls: 'icon-edit',
		   		text : '更新',
		   		handler: function(){
		   			var rows = customInfoInputId.datagrid('getSelections');
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
					oneRowData.tradeId = tradeId;
					oneRowData.agentId = $("#agentID_Audit_In").combobox('getValue');
					oneRowData = $.toJSON(oneRowData);
					
					addcustomeWindow('更新客户', contextPath+"/customer/updateCustomerUrl?custBaseInfo="+oneRowData);
		   		}
		   	},'-',{
		   		iconCls: 'icon-remove',
		   		text : '删除',
		   		handler: function(){
		   			delCustomerInfo();
		   		}
		   	}],
		 onCheck:function(rowIndex,rowData){
			//alert('未实现的方法');
		 }
		
	});
}


function addcustomeWindow(title, href) 
{
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

function appendTradeCustomerInfo(customerInfo){
	$("#customInfoAuditId").datagrid('appendRow',eval("("+decodeURI(customerInfo)+")"));
}

function submitAuditTradeCusInfo(){
	var customerData = $("#customInfoAuditId").datagrid('getRows');
	var tradeId = $("#tradeId_Audit_In").val();
	$.ajax({
		type:'post',
		url:'trade/saveTradeCusInfo',
		data:'queryParam='+$.toJSON(customerData)+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//tradeInputList.datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					$("#customInfoAuditId").datagrid('reload');
					initRoleInfoId();
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function delCustomerInfo(){
	var customerData = $("#customInfoCheckId").datagrid('getSelections');
	if (customerData.length == 0) {
		$.messager.alert('提示',"请选择一个客户进行删除!");
		return;
	}
	if(!confirm("是否确认删除选中的角色信息?")){
		return;
	}
	var custBaseInfoId = customerData[0].custBaseInfoId;
	var tradeId = $("#tradeId_Audit_In").val();
	$.ajax({
		type:'post',
		url:'trade/delTradeCusInfo',
		data:'queryParam='+$.toJSON(customerData)+'&tradeId='+tradeId+'&custBaseInfoId='+custBaseInfoId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//tradeInputList.datagrid('loadData',returnData.obj);	
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

//角色信息
var roleInfoAuditId;
var roleInfoTableEditRowIndex = null;
function initRoleInfoId()
{
	var data = $.toJSON($("#customInfoAuditId").datagrid('getRows'));
	var tradeId = $("#tradeId_Audit_In").val();
	roleInfoAuditId=$("#roleInfoAuditId").datagrid({
		url:contextPath+'/trade/queryTradeRoleInfo?tradeId='+tradeId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		 columns:[[  
		           {field:'tradeCustRoleInfoId',title:'交易客户角色流水号',width:50,hidden:true},
					{field:'tradeCustInfoId',title:'交易客户流水号',width:50,hidden:true},
		           {field:'roleType',title:'角色',width:50,
	        	   editor:{
						type:'combobox',
						options:{
		        	  		url:contextPath+'/codeQuery/tdCodeQuery?codeType=roleType',
							valueField:'code',
							textField:'codeName',
							required:true,
							onSelect:function(record){
								if(record.code=='1'){
									//initTradeBankInfoInputId("","init");
									var edRelationToAppnt = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									$(edRelationToAppnt.target).combobox("reset");
									$(edRelationToAppnt.target).combobox("disable");
									var edRelationToInsured = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
									$(edRelationToInsured.target).combobox("reload");
									$(edRelationToInsured.target).combobox("enable");
									roleInfoAuditId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = null;
								}else if(record.code=='0'){
									var edRelationToInsured = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
									$(edRelationToInsured.target).combobox("reset");
									$(edRelationToInsured.target).combobox("disable");
									var edRelationToAppnt = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									$(edRelationToAppnt.target).combobox("reload");
									$(edRelationToAppnt.target).combobox("enable");
									roleInfoAuditId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = null;
								}
								var ed = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'roleType'});
								var roleCodeName = $(ed.target).combobox('getText');
								roleInfoAuditId.datagrid('getRows')[roleInfoTableEditRowIndex]['roleTypeName'] = roleCodeName;
								
								/*if(record.code=='1'){
									//initTradeBankInfoCheckId("","init");
								}
								var ed = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'role'});
								var roleCodeName = $(ed.target).combobox('getText');
								roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['roleCodeName'] = roleCodeName;*/
							}
							}
						},
					formatter : function(value, row, index) {
						return row.roleTypeName;
					}}, 
					{field:'roleTypeName',title:'角色编码',width:50,hidden:true},
		           {field:'customer',title:'客户',width:50, 
						editor:{
						type:'combobox',
						options:{
		        	  		url:contextPath+'/trade/queryCustomerCombo?tradeId='+tradeId,
							valueField:'customername',
							textField:'customername',
							required:true,
							onSelect:function(returnedData){
								roleInfoAuditId.datagrid('getRows')[roleInfoTableEditRowIndex]['tradeCustInfoId'] = returnedData.tradeCustInfoId;
								var ed = $('#roleInfoCheckId').datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'customerCode'});
								if(ed!=null){
									$(ed.target).val(returnedData.tradeCustInfoId+"");
									
								}
								if(returnedData.custBaseInfoId!=null){
									//initTradeBankInfoInputId(returnedData.custBaseInfoId,"query");
								}
								
								var ed1 = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'customer'});
								var customerCodeName = $(ed1.target).combobox('getText');
								roleInfoAuditId.datagrid('getRows')[roleInfoTableEditRowIndex]['customerCodeName'] = customerCodeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.customerCodeName;
					}},
					{field:'customerCodeName',title:'角色编码',width:50,hidden:true},
					{field:'customerCode',title:'客户流水号',width:50,hidden:true,editor:'text'},
					{field:'relationToAppnt',title:'与投保人关系',width:50, 
						editor:{
							type:'combobox',
							options:{
			        	  		url:contextPath+'/codeQuery/tdCodeQuery?codeType=relation',
								valueField:'code',
								textField:'codeName',
								//required:true,
								onSelect:function(){
									var ed1 = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									var appntRelationCodeName = $(ed1.target).combobox('getText');
									roleInfoAuditId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = appntRelationCodeName;
								}
							}
						},
						formatter : function(value, row, index) {
							return row.relationToAppntName;
						}
					}, 
					{field:'relationToAppntName',title:'与投保人关系编码',width:50,hidden:true},
		            {field:'relationToInsured',title:'与被保人关系',width:50, editor:{
						type:'combobox',
						options:{
		        	  		url:contextPath+'/codeQuery/tdCodeQuery?codeType=relation',
							valueField:'code',
							textField:'codeName',
							//required:true,
							onSelect:function(){
								var ed1 = roleInfoAuditId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
								var insuredRelationCodeName = $(ed1.target).combobox('getText');
								roleInfoAuditId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = insuredRelationCodeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.relationToInsuredName;
					}},
					{field:'relationToInsuredName',title:'与被保人关系编码',width:50,hidden:true}
		       ]],
		  toolbar: [{
			   		iconCls: 'icon-add',
			   		text : '新增',
			   		handler: function(){
			   			if(roleInfoTableEditRowIndex!=null){
			   				if(!$('#roleInfoAuditId').datagrid('validateRow',roleInfoTableEditRowIndex)){
			   					return;
			   				}
			   			}
			   			var length = $("#roleInfoAuditId").datagrid('getRows').length;
			   			$("#roleInfoAuditId").datagrid("appendRow",{
			   				role:'',
			   				roleCode:'',
			   				customer:'',
			   				appntRelation:'',
			   				insuredRelation:'',
			   				insuredRelationCode:''
			   			});
			   			$("#roleInfoAuditId").datagrid('checkRow',length);
			   			roleInfoTableEditRowIndex = length;
			   			$("#roleInfoAuditId").datagrid('beginEdit',length);
			   		}
			   	},'-',{
			   		iconCls: 'icon-edit',
			   		text : '更新',
			   		handler: function(){
			   			var selected = $('#roleInfoAuditId').datagrid('getSelected');
			   			if(selected){
			   				var index = $('#roleInfoAuditId').datagrid('getRowIndex', selected);
			   				roleInfoTableEditRowIndex = index;
				   			$("#roleInfoAuditId").datagrid("beginEdit",index);
			   			}
			   		}
			   	},'-',{
			   		iconCls: 'icon-remove',
			   		text : '删除',
			   		handler: function(){
			   			delRoleInfo();
			   		}
			   	}],
		   	onSelect:function(rowIndex, rowData){
			    if(roleInfoTableEditRowIndex!=null){
			    	if(!$('#roleInfoAuditId').datagrid('validateRow',roleInfoTableEditRowIndex)){
			    		//$.messager.alert('提示', "请选择或删除待录入角色信息！");
			    		//$("#roleInfoAuditId").datagrid("cancelEdit",roleInfoTableEditRowIndex);
			    		return;
			    	}else{
			    		$("#roleInfoAuditId").datagrid("endEdit",roleInfoTableEditRowIndex);
			    	}
			    }
		   }
	});
}


//险种财富信息
var riskProAuditInfoId;
var riskProAuditInfo = {};
function initRiskProAuditInfoId(proType)
{
	var tradeId = $("#tradeId_Audit_In").val();
	riskProAuditInfoId=$("#riskProAuditInfoId").datagrid({
		url:contextPath+'/trade/queryTradeRiskProInfo?tradeId='+tradeId+'&proType='+proType,
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
	           {field:'proCode',title:'产品代码',width:50},    
	           {field:'proName',title:'产品名称',width:50},
	           {field:'insurObj',title:'保障对象',width:50},
	           {field:'insurId',title:'保障对象',width:50,hidden:true},
	           {field:'proId',title:'产品Id',width:50,hidden:true},
	           {field:'proCodeName',title:'产品',width:50,hidden:true}
		       ]],
		 toolbar: [{
		   		iconCls: 'icon-remove',
		   		text : '删除',
		   		handler: function(){
		   			delRiskProInfo();
		   			/*var selected = $('#riskProInputInfoId').datagrid('getSelected');
		   			if(selected){
		   				var index = $('#riskProInputInfoId').datagrid('getRowIndex', selected);
			   			$("#riskProInputInfoId").datagrid("deleteRow",index);
		   			}*/
		   		}
		   	}],
		 onCheck:function(rowIndex,rowData)
		 {
			$("#tradeRiskProId_Audit_In").combobox("setValue",rowData.proId);
			$("#tradeRiskProId_Audit_In").combobox("setText",rowData.proCodeName);
			$("#tradeRiskProtObj_Audit_In").combobox("setValue",rowData.insurId);
			$("#tradeRiskProtObj_Audit_In").combobox("setText",rowData.insurObj);
			var tradeInfoId = $("#tradeId_Audit_In").val();
			initRiskProductInfoAuditId(contextPath
					+'/trade/queryRiskProInfoObjList?tradeInfoId='+tradeInfoId+'&proId='+rowData.proId);
		 }
	});
}


//险种财富信息
var riskProInfoObjAuditId;
function initRiskProductInfoAuditId(urlPath)
{
	riskProInfoObjAuditId=$("#riskProInfoObjAuditId").datagrid({
		//url:contextPath+'/codeQuery/tdCodeQuery?codeType=factorCode',
		url:urlPath,
		fitColumns:true,
		rownumbers:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		 columns:[[  
		           //{field:'ck',checkbox:true},    
		           {field:'code',title:'录入项编码',width:50,hidden : true},
		           {field:'name',title:'录入项名称',width:50},    
		           {field:'inputValue',title:'录入项值',width:50,
		        	   editor:'text'},
			       {field:'inputUnit',title:'录入项枚举值',width:50},
				   {field:'factorType',title:'要素类型',width:50,hidden:true} 
		       ]]/*,
		 onCheck:function(rowIndex,rowData)
		 {
		    if(rowData.factorType=='0'){
				$("#riskProInfoObjAuditId").datagrid('addEditor', {
                    field : 'inputValue',
                    editor : {
                        type : 'text',
                        options : {
                            required : true
                        }
                    }
                });
			}else if(rowData.factorType=='1'){
				$("#riskProInfoObjAuditId").datagrid('addEditor', {
                    field : 'inputValue',
                    editor : {
                        type : 'combobox',
                        options : {
                        	url:contextPath+'/codeQuery/tdCodeQuery?codeType='+rowData.code,
							valueField:'code',
							textField:'codeName',
                            required : true
                        }
                    }
                });
			}else if(rowData.factorType=='2'){
				$("#riskProInfoObjAuditId").datagrid('addEditor', {
                    field : 'inputValue',
                    editor : {
                        type : 'datebox',
                        options : {
                            required : true
                        }
                    }
                });
			}
			$("#riskProInfoObjAuditId").datagrid('beginEdit',rowIndex);
		 }*/
		
	});
}
//财富产品信息
var  monProInfoAuditId;
function initMonryProductInfoId(proType)
{
	var tradeId = $("#tradeId_Audit_In").val();
	monProInfoAuditId=$("#monProInfoAuditId").datagrid({
		url:contextPath+'/trade/queryTradeWealthProInfo?tradeId='+tradeId+'&proType='+proType,
		fitColumns:true,
		rownumbers:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		 columns:[[  
	           {field:'ck',checkbox:true},    
	           {field:'proCode',title:'产品代码',width:50},    
	           {field:'proName',title:'产品名称',width:50},
	           {field:'proId',title:'产品Id',width:50,hidden:true},
	           {field:'proCodeName',title:'产品',width:50,hidden:true}
		       ]],
	    toolbar: [{
	   		iconCls: 'icon-remove',
	   		text : '删除',
	   		handler: function(){
	   			delMonProInfo();
	   		}
	   	 }],
		 onCheck:function(rowIndex,rowData)
		 {
			$("#tradeWealthProId_Audit_In").combobox("setValue",rowData.proId);
			$("#tradeWealthProId_Audit_In").combobox("setText",rowData.proCodeName);
			var tradeInfoId = $("#tradeId_Audit_In").val();
			initMonryProductInfoAuditId(contextPath
					+'/trade/queryMonryProInfoObjList?tradeInfoId='+tradeInfoId+'&proId='+rowData.proId);
			queryWealthProductDetailInfo(rowData.proId);
		 }
	});
}


//财富产品信息  录入项  录入值
var monProInfoObjAuditId;
function initMonryProductInfoAuditId(urlPath)
{
	monProInfoObjAuditId=$("#monProInfoObjAuditId").datagrid({
		url:urlPath,
		fitColumns:true,
		rownumbers:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		 columns:[[  
	           //{field:'ck',checkbox:true},  
	           {field:'code',title:'录入项编码',width:50,hidden : true},
	           {field:'name',title:'录入项名称',width:50},    
	           {field:'inputValue',title:'录入项值',width:50,
	        	   editor:'text'},
		       {field:'inputUnit',title:'录入项枚举值',width:50},
			   {field:'factorType',title:'要素类型',width:50,hidden:true} 
		       ]]/*,
		 onCheck:function(rowIndex,rowData)
		 {
			if(rowData.factorType=='0'){
				$("#monProInfoObjAuditId").datagrid('addEditor', {
                    field : 'inputValue',
                    editor : {
                        type : 'text',
                        options : {
                            required : true
                        }
                    }
                });
			}else if(rowData.factorType=='1'){
				$("#monProInfoObjAuditId").datagrid('addEditor', {
                 field : 'inputValue',
                 editor : {
                     type : 'combobox',
                     options : {
                     	url:contextPath+'/codeQuery/tdCodeQuery?codeType='+rowData.code,
							valueField:'code',
							textField:'codeName',
                         required : true
                     }
                 }
             });
			}else if(rowData.factorType=='2'){
				$("#monProInfoObjAuditId").datagrid('addEditor', {
                 field : 'inputValue',
                 editor : {
                     type : 'datebox',
                     options : {
                         required : true
                     }
                 }
             });
			}
			$("#monProInfoObjAuditId").datagrid('beginEdit',rowIndex);
		 }*/
		
	});
}



///	附件信息
var attachInfoAuditId;
function initAttachmentInfo()
{
	attachInfoAuditId=$("#attachInfoAuditId").datagrid({
		fitColumns:true,
		rownumbers:true,
		//pagination:true,
		singleSelect:true,
		//pageSize:10,
		//pageList:[5,10,15,20,25,30],
		 columns:[[  
	           {field:'ck',checkbox:true},    
	           {field:'attachType',title:'附件类型',width:50},    
	           {field:'attachDesc',title:'附件描述',width:50},
	           {field:'file',title:'协议文件',width:150}   
		       ]],
		 onCheck:function(rowIndex,rowData)
		 {
			alert('未实现的方法');
		 }
	});
}

//客户信息页面
function addCustomerInfo(){
	$('<div id="addCustomerInfo"/>').dialog({
		href : contextPath+"/customer/addCustomerUrl",
		modal : true,
		fit:true,
		title : '客户信息新增',
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}

/**
 * ========================================================
 */

/*
 * 提交交易信息
 */
function submitTradeInfo(){
	if(!$("#tradeInfoForm_Audit").form("validate")){
		return;
	}
	var formData = $("#tradeInfoForm_Audit").serialize();
	formData = formDataToJsonStr(formData);
	$.ajax({
		type:'post',
		url:'trade/saveTradeInfo',
		data:'queryParam='+formData,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//tradeAuditList.datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					$("#tradeId_Audit").val(returnData.tradeId_Audit);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

/*
 * 初始化所有下拉框
 */
function initAllCombobox(){
	$("#tradeType_Audit_In").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType',
		valueField:'code',
		textField:'codeName',
		onSelect:function (record)
		{
			if(record.code==2)
			{
				$("#roleInfoAuditPanelId").show();
				$("#riskProAuditInfoPanelId").show();
				$("#monProInfoAuditPanelId").hide();
			}else{
				$("#roleInfoAuditPanelId").hide();
				$("#riskProAuditInfoPanelId").hide();
				$("#monProInfoAuditPanelId").show();
			}
			$.getJSON(contextPath+'/trade/getTradeInfoNo?productType='+record.code,
					function(data) {
						$("#tradeNo_Audit_In").val(data);
					}); 
		}
	});
	$("#agentID_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeAgentId',
		valueField:'agentId',
		textField:'agentCodeName',
		onSelect:function (record){
			$("#companyID_Audit_In").combobox("setValue",record.comId);
			$("#companyID_Audit_In").combobox("setText",record.comCodeName);
			/*$("#storeID_Audit_In").combobox("setValue",record.storeId);
			$("#storeID_Audit_In").combobox("setText",record.storeCodeName);*/
		}
	});
	$("#currency_Audit_In").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName'
	});
	$("#tradeRiskProId_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeRiskProId?proType=2',
		valueField:'proId',
		textField:'pro',
		onSelect:function(record){
			//$("#tradeRiskProId").combobox('setText',record.proCode);
			//$("#tradeRiskProName").val(record.proName);
		}
	});
	$("#tradeRiskProtObj_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Audit_In").val(),
		valueField:'custID',
		textField:'custName',
		onSelect:function(record){
			//$("#tradeWealthProId").combobox('setText',record.proCode);
			//$("#tradeWealthProName").val(record.proName);
			var tradeInfoId = $("#tradeId_Audit_In").val();
			var proId = $("#tradeRiskProId_Audit_In").combobox('getValue');
			initRiskProductInfoAuditId(contextPath
					+'/trade/queryTradeProductFactor?codeType=riskfactorCode&proId='+proId);
		}
	});
	$("#tradeWealthProId_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeRiskProId?proType=1',
		valueField:'proId',
		textField:'pro',
		onSelect:function(record){
			//$("#tradeWealthProId").combobox('setText',record.proCode);
			//$("#tradeWealthProName").val(record.proName);
			var tradeInfoId = $("#tradeId_Audit_In").val();
			initMonryProductInfoAuditId(contextPath
					+'/trade/queryTradeProductFactor?codeType=wealthfactorCode&proId='+record.proId);
		}
	});
	$("#tradeComID_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false
	});
/*	$("#tradeStoreID_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeStoreId',
		valueField:'storeId',
		textField:'storeCodeName',
		editable:false
	});*/
	$("#tradeAuditConclusion").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=tradeAuditConclusion',
		valueField:'code',
		textField:'codeName',
		editable:false
	});
	$("#companyID_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false,
		disabled:true
	});
	/*$("#storeID_Audit_In").combobox({
		url:contextPath+'/trade/queryTradeStoreId',
		valueField:'storeId',
		textField:'storeCodeName',
		editable:false,
		disabled:true
	});*/
	
	$("#currency_Audit").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#tradeInputDate_Audit_In").datebox({
		disabled:true
	});
}

function submitAuditTradeRole(){
	var formData = $("#roleInfoAuditId").datagrid('getRows');
	for(i=0;i<formData.length;i++){
		if(!$('#roleInfoAuditId').datagrid('validateRow',i)){
			return;
		}
	}
	$("#roleInfoAuditId").datagrid('acceptChanges');
	//formData = formDataToJsonStr(formData);
	formData = $.toJSON(formData);
	var tradeNo_Audit = $("#tradeNo_Audit_In").val();
	var tradeId_Audit = $("#tradeId_Audit_In").val();
	$.ajax({
		type:'post',
		url:'trade/saveTradeRole',
		data:'queryParam='+formData+'&tradeNo='+tradeNo_Audit+'&tradeId='+tradeId_Audit,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//$("#roleInfoAuditId").datagrid('loadData',returnData.obj);
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



function submitAuditTradeRole(){
	$("#riskProInfoObjAuditId").datagrid('acceptChanges');
	var riskFactorData = $("#riskProInfoObjAuditId").datagrid('getRows');
	riskFactorData = $.toJSON(riskFactorData);
	var tradeNo_Audit = $("#tradeNo_Audit_In").val();
	var tradeId_Audit = $("#tradeId_Audit_In").val();
	var tradeRiskProId = $("#tradeRiskProId_Audit_In").combobox('getValue');
	var tradeRiskProtObj = $("#tradeRiskProtObj_Audit_In").combobox('getValue');
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeRiskPro',
		data:'riskFactorData='+riskFactorData+'&tradeId='+tradeId_Audit+'&tradeRiskProId='+tradeRiskProId+'&tradeRiskProtObj='+tradeRiskProtObj,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//$("#roleInfoAuditId").datagrid('loadData',returnData.obj);
					$.messager.alert('提示', returnData.msg);
					$("#riskTotalAssets_Audit_In").val(returnData.totalAssets);
					initRiskProAuditInfoId("2");
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


function submitAuditWealthPro(){
	$("#monProInfoObjAuditId").datagrid('acceptChanges');
	var wealthFactorData = $("#monProInfoObjAuditId").datagrid('getRows');
	wealthFactorData = $.toJSON(wealthFactorData);
	var tradeNo_Audit = $("#tradeNo_Audit_In").val();
	var tradeId_Audit = $("#tradeId_Audit_In").val();
	var tradeWealthProId = $("#tradeWealthProId_Audit_In").combobox('getValue');
	$.ajax({
		type:'post',
		url:'trade/saveTradeWealthPro',
		data:'wealthFactorData='+wealthFactorData+'&tradeWealthProId='+tradeWealthProId+'&tradeId='+tradeId_Audit,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//$("#roleInfoAuditId").datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					$("#monTotalAssets_Audit_In").val(returnData.totalAssets);
					//$("#monProInfoAuditId").datagrid('reload');
					initMonryProductInfoId("1");
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

//校验交易状态信息
function verifyTradeStatusInfo(){
	var tradeInfoId = $("#tradeId_Audit_In").val();
	var verifyPassFlag = false;
	$.ajax({
		type:'post',
		async: false,//设置为同步方法
		url:'trade/getTradeStatusInfo',
		data:'tradeInfoId='+tradeInfoId,
		cache:false,
		success:function(resultInfo){
			try {
				if(resultInfo.success){
					var tradeStatus = resultInfo.obj.code;
					if(tradeStatus!=null&&tradeStatus!=""&&tradeStatus!=undefined&&tradeStatus=="09"){
						$.messager.alert('提示', "当前交易最新状态为("+resultInfo.obj.codeName+"),交易无需审核！");
						verifyPassFlag = false;
					}else{
						verifyPassFlag = true;
					}
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

/*
 * 提交复核
 */
function submitTradeAudit(){
	if(!$("#tradeInfoForm_Audit").form("validate")){
		return;
	}

	/*if(!confirm("交易信息是否录入完毕?")){
		return;
	}*/
	
	if(!verifyTradeStatusInfo()){
		return;
	}
	
	$.messager.confirm('提示',"交易信息是否录入完毕?",function(r){
		if(r){
			var tradeType = $("#tradeType_Audit_In").combobox("getValue");
			if(tradeType=="1"){
				//判断物业宝产品的购买次数并提交审核结论
				isHadBuyWYBProduct();
			}else{
				submitAuditInfo();
			}
		}else{
			return;
		}
	});
	
	
	
	
}

function isHadBuyWYBProduct(){
	var customerInfo = customInfoAuditId.datagrid("getRows")[0];
	var custName = customerInfo.chnName;
	var custMobile = customerInfo.mobile;
	var idNo = customerInfo.idNo;
	var idType = customerInfo.idType;
	var param = {};
	param.productId =  $("#monProInfoAuditId").datagrid("getRows")[0].proId;;
	//param.foundDate = expectOpenDay;
	param.custName = custName;
	param.custMobile = custMobile;
	param.idNo = idNo;
	param.idType = idType;
	$.ajax({
		type:'post',
		url:contextPath+"/productOrder/isHadBuyWYBProduct",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					submitAuditInfo();
				}else{
					//$.messager.alert('提示', reData.msg);
					$.messager.confirm('确认',reData.msg+",您确认继续提交审核结论吗？",function(r){    
					    if (r){    
					    	submitAuditInfo();
					    } 
					}); 
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function submitAuditInfo(){
	var tradeId_Audit = $("#tradeId_Audit_In").val();
	var tradeType_Audit = $("#tradeType_Audit_In").val();
	var tradeConclusion = $("#tradeAuditConclusion").combobox('getValue');
	var tradeRemark = $("#tradeAuditRemark").val();
	
//	alert(tradeConclusion);
	if(tradeConclusion==null||tradeConclusion==""||tradeConclusion==undefined){
		$.messager.alert('提示', "请给出审核结论！！！");	
		return;
	}
	
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeInput',
		data:'tradeId='+tradeId_Audit+'&tradeType='+tradeType_Audit
			+'&tradeOperationNode=03'+'&tradeConclusion='+tradeConclusion+'&tradeRemark='+tradeRemark,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//$("#roleInfoAuditId").datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					$("#tradeInfoAudit").dialog('close');
					$("#tradeAuditCommonListId").datagrid('reload');
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function delRoleInfo(){
	if(roleInfoTableEditRowIndex!=null){
		if(!$('#roleInfoInputId').datagrid('validateRow',roleInfoTableEditRowIndex)){
			return;
		}
	}
	if(!confirm("是否确认删除选中的角色信息?")){
		return;
	}
	var tradeId_Input = $("#tradeId_Audit_In").val();
	var tradeCustInfoId = $("#roleInfoAuditId").datagrid('getSelected').customerCode;
	var roleType = $("#roleInfoInputId").datagrid('getSelected').role;
	$.ajax({
		type:'post',
		url:'trade/delRoleInfo',
		data:'tradeId='+tradeId_Input+'&tradeCustInfoId='+tradeCustInfoId+'&roleType='+roleType,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$.messager.alert('提示', returnData.msg);
					$("#roleInfoAuditId").datagrid('reload');
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function delRiskProInfo(){
	if(!confirm("是否确认删除选中的保险产品信息?")){
		return;
	}
	var tradeId_Input =  $("#tradeId_Audit_In").val();
	var riskTotalAssets_Input = $("#riskTotalAssets_Audit_In").val();
	var selected = $('#riskProAuditInfoId').datagrid('getSelected');
	var tradeRiskProId = selected.proId;
	$.ajax({
		type:'post',
		url:'trade/delRiskProInfo',
		data:'tradeId='+tradeId_Input+'&riskTotalAssets='+riskTotalAssets_Input+'&tradeRiskProId='+tradeRiskProId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$.messager.alert('提示', returnData.msg);
					$("#riskProAuditInfoId").datagrid('reload');
					$("#riskProInfoObjAuditId").datagrid('reload');
					$("#riskTotalAssets_Audit_In").val(returnData.totalAssets);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function delMonProInfo(){
	if(!confirm("是否确认删除选中的财富产品信息?")){
		return;
	}
	var tradeId_Input =  $("#tradeId_Audit_In").val();
	var monTotalAssets_Input = $("#monTotalAssets_Audit_In").val();
	var selected = $('#monProInfoAuditId').datagrid('getSelected');
	var tradeWealthProId = selected.proId;
	$.ajax({
		type:'post',
		url:'trade/delMonProInfo',
		data:'tradeId='+tradeId_Input+'&monTotalAssets='+monTotalAssets_Input+'&tradeWealthProId='+tradeWealthProId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$.messager.alert('提示', returnData.msg);
					$("#monProInfoAuditId").datagrid('reload');
					$("#monProInfoObjAuditId").datagrid('reload');
					$("#monTotalAssets_Audit_In").val(returnData.totalAssets);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function initTradeCheckConclusion(){
	var tradeId_Input =  $("#tradeId_Audit_In").val();
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
	
	$.ajax({
		type:'post',
		url:'trade/queryTradeConclusion2',
		data:'tradeId='+tradeId_Input+'&tradeOperationNode=03',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$("#tradeAuditConclusion").combobox('setValue',returnData.tradeConclusion);
					$("#tradeAuditConclusion").combobox('setText',returnData.tradeConclusionName);
					$("#tradeAuditRemark").val(returnData.tradeRemark);
				}else{
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}
function uploadTradeVideo(){
	var param = {};
	param.businessNo = $("#tradeId_Audit_In").val();
	param.businessType = "13";
	param.operate = loadFileType;
	addFileWindow('视频上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}
function uploadTradeAuditAttachInfo(){
	var param = {};
	var loadFileType = null;
	param.businessNo = $("#tradeId_Audit_In").val();
	param.businessType = "04";
	param.operate = loadFileType;
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}

function delTradeAuditAttachInfo(){
	var param = {};
	param.businessNo = $("#tradeId_Audit_In").val();
	param.businessType = "04";
	//param.operate = "queryFile";
	param.operate = loadFileType;
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}

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

function initTradeBankInfoAuditId(id,type)
{
	var custInfoId = id;
	var tradeType_Audit = $("#tradeType_Audit_In").combobox('getValue');
	var urlPath = "";
	if(type=="init"){
		
	}else if(type=="query"){
		urlPath = contextPath+'/trade/queryTradeBankInfo?tradeId='+custInfoId+'&tradeType='+tradeType_Audit;
	}else if(type=="search"){
		urlPath = contextPath+'/trade/searchTradeBankInfo?custAccInfoId='+custInfoId+'&rcState=E';
	}
	$("#tradeBankInfoAuditId").datagrid({
		url:urlPath,
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

function submitTradeBankInfoAuditId(){
	var selectRow = $('#tradeBankInfoAuditId').datagrid('getSelections');
	var tradeId = $("#tradeId_Audit_In").val();
	selectRow = $.toJSON(selectRow);
	$.ajax({
		type:'post',
		url:'trade/saveTradeBankInfo',
		data:'rowData='+selectRow+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$.messager.alert('提示', returnData.msg);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function initTradeAddressInfoAuditId(id,type)
{
	var custInfoId = id;
	var urlPath = "";
	if(type=="init"){
		
	}else if(type=="query"){
		var tradeType_Input = $("#tradeType_Audit_In").combobox('getValue');
		var agentId_Input = $("#agentID_Audit_In").combobox('getValue');
		urlPath = contextPath+'/trade/queryTradeAddressInfo?tradeId='+custInfoId+'&tradeType='+tradeType_Input+'&agentId='+agentId_Input;
	}else if(type=="search"){
		urlPath = contextPath+'/trade/searchTradeAddressInfo?custAddressInfoId='+custInfoId+'&rcState=E';
	}
	$("#tradeAddressInfoAuditId").datagrid({
		url:urlPath,
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

function submitTradeAddressInfoAuditId(){
	var selectRow = $('#tradeAddressInfoAuditId').datagrid('getSelections');
	if (selectRow.length == 0) {
		$.messager.alert('提示',"请选择一条地址信息进行保存!");
		return;
	}
	if (selectRow.length > 1) {
		$.messager.alert('提示',"只能选择一条地址信息进行保存!");
		return;
	}
	var tradeId = $("#tradeId_Audit_In").val();
	selectRow = $.toJSON(selectRow);
	$.ajax({
		type:'post',
		url:'trade/saveTradeAddressInfo',
		data:'rowData='+selectRow+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$.messager.alert('提示', returnData.msg);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function addcustomeWindow(title, href) 
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

function queryWealthProductDetailInfo(productId){
	$.ajax({
		type:'post',
		url:'trade/queryWealthProductDetailInfo',
		data:'productId='+productId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					var dataObj = returnData.obj;
					setInputValueById("audit_monProInfoSelectDiv",dataObj);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function tradeAuditBack(){
	$("#tradeInfoAudit").window("destroy");
}