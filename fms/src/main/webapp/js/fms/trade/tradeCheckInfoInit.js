var CustomInfojson={"total":3,"rows":[{'custBaseInfoId':'1','tradeCode':'1','tradeType':'张三'},{'custBaseInfoId':'2','tradeCode':'2','tradeType':'李四'}]}
var RoleInfojson={"total":3,"rows":[{'role':'1'},{'role':'2'}]}
var riskProductInfojson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var riskProductInfoCheckIdJson={"total":3,"rows":[{'CheckName':'1'},{'CheckName':'2'}]}
var monryProductInfoIdjson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var monryProductInfoCheckIdJson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var attachmentInfoIdJson={"total":3,"rows":[{'attachType':'1'},{'attachType':'2'}]};
var loadFileType = null;
jQuery(function($) {
	//客户信息
	initCustomInfoId();
	//customInfoCheckId.datagrid("loadData",CustomInfojson)
	//角色信息
	initRoleInfoId();
	//roleInfoCheckId.datagrid("loadData",RoleInfojson);
	//险种财富信息
	initRiskProCheckInfoId("");
	//riskProCheckInfoId.datagrid("loadData",riskProductInfojson);
	//险种财富信息
	initRiskProductInfoCheckId("");
	//riskProInfoObjCheckId.datagrid("loadData",riskProductInfoCheckIdJson);
	//财富产品信息
	initMonryProductInfoId("");
	//monProInfoCheckId.datagrid("loadData",monryProductInfoIdjson);
	//财富产品信息
	initMonryProductInfoCheckId("");
	//monProInfoObjCheckId.datagrid("loadData",monryProductInfoCheckIdJson);
	//附件信息
	initAttachmentInfo();
	//attachInfoCheckId.datagrid("loadData",attachmentInfoIdJson);
	$('#tradeDate').datebox({required:true});
	//初始化下拉框
	initAllCombobox();
	//初始化复核结论
	initTradeCheckConclusion();

});


function initTradeCheckConclusion(){
	var tradeId = $("#tradeId_Check_In").val();
	$.ajax({
		type:'post',
		url:'trade/queryTradeConclusion',
		data:'tradeId='+tradeId+'&tradeOperationNode=02',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$("#tradeCheckConclusion").combobox('setValue',returnData.tradeConclusion);
					$("#tradeCheckConclusion").combobox('setText',returnData.tradeConclusionName);
					$("#tradeCheckRemark").val(returnData.tradeRemark);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}



//客户信息
var customInfoCheckId;
function initCustomInfoId()
{
	var tradeId = $("#tradeId_Check_In").val();
	customInfoCheckId=$("#customInfoCheckId").datagrid({
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
	           {field:'ck',checkbox:true}, 
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
	           {field:'chnName',title:'姓名',width:50},
	           {field:'sex',title:'性别',width:50,hidden:true},
	           {field:'sexName',title:'性别',width:50},  
	           {field:'birthday',title:'出生日期',width:50},
	           {field:'idType',title:'证件类型',width:50,hidden:true},   
	           {field:'idTypeName',title:'证件类型',width:50}, 
	           {field:'idNo',title:'证件号码',width:50},
	           /*{field:'mobile',title:'联系电话',width:50},
	           {field:'age',title:'年龄（周岁）',width:50},*/
	           {field:'idValidityDate',title:'证件有效期',width:50}    
		       ]],
      /* toolbar: [{
		   		iconCls: 'icon-addrow',
		   		text : '新增',
		   		handler: function(){
		   			//alert("新增操作后请提交客户信息！");
		   			var param = {};
					param.loadFlag = "addCust";
					param.tradeId = tradeId;
					param.agentId = $("#agentID_Check_In").combobox('getValue');
					param = $.toJSON(param);
					addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
		   		}
		   	},'-',{
		   		iconCls: 'icon-edit',
		   		text : '修改',
		   		handler: function(){
		   			//alert("更新操作后请提交客户信息！");
		   			var rows = customInfoCheckId.datagrid('getSelections');
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
					oneRowData.agentId = $("#agentID_Check_In").combobox('getValue');
					oneRowData = $.toJSON(oneRowData);
					addcustomeWindow('更新客户', contextPath+"/customer/updateCustomerUrl?custBaseInfo="+oneRowData);
		   		}
		   	},'-',{
		   		iconCls: 'icon-delete',
		   		text : '删除',
		   		handler: function(){
		   			delCustomerInfo();
		   		}
		   	}],*/
		 onCheck:function(rowIndex,rowData){
			//alert('未实现的方法');
		 }
		
	});
}

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


function appendTradeCustomerInfo(customerInfo){
	var custinfo = eval("("+decodeURI(customerInfo)+")");
	if(custinfo.custBaseInfoId!=null&&custinfo.custBaseInfoId!=""&&custinfo.custBaseInfoId!=undefined){
		var custBaseInfoId = custinfo.custBaseInfoId;
		var tradeCustInfoId = null;
		var allRows = $("#customInfoCheckId").datagrid('getRows');
		var oldInfoIndex = null;
		if(allRows.length>0){
			for(var i=0;i<allRows.length;i++){
				if(allRows[i].custBaseInfoId==custBaseInfoId){
					oldInfoIndex = parseInt(i);
					tradeCustInfoId = allRows[i].tradeCustInfoId;
				}
			}
		}
		if(oldInfoIndex==null||oldInfoIndex==undefined){
			$("#customInfoCheckId").datagrid('appendRow',custinfo);
		}else{
			custinfo.tradeCustInfoId = tradeCustInfoId;
			$("#customInfoCheckId").datagrid('deleteRow',oldInfoIndex);
			$("#customInfoCheckId").datagrid('insertRow',{index:oldInfoIndex,row:custinfo});
		}
	}
}

function submitCheckTradeCusInfo(){
	$("#submitCheckTradeCusInfoButton").linkbutton("disable");
	var customerData = $("#customInfoCheckId").datagrid('getRows');
	var tradeType_Check = $("#tradeType_Check_In").combobox('getValue');
	var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_check").serialize());
	var tradeId = $("#tradeId_Check_In").val();
	if(tradeType_Check=='1'){
		if(customerData.length>1){
			$.messager.alert('提示',"财富产品只能存在一个客户，请删除多余客户信息！");
			$("#submitCheckTradeCusInfoButton").linkbutton("enable");
			return;
		}
	}
	var param = {};
	param.customerList = customerData;
	param.tradeInfo = tradeBaseInfo;
	$.ajax({
		type:'post',
		url:'trade/saveTradeCusInfo',
		data:'param='+$.toJSON(param),
		//data:'queryParam='+$.toJSON(customerData)+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//tradeInputList.datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					var tradeId = $("#tradeId_Check_In").val();
					var tradeCustInfoUrl ='trade/queryCustomInfoInputGrid?tradeId='+tradeId;
					customInfoCheckId.datagrid('options').url = tradeCustInfoUrl;
					$("#customInfoCheckId").datagrid('load');
					initRoleInfoId();
					//加载银行账户信息
					initTradeBankInfoCheckId(tradeId,"query");
					//财富产品由于需要打印认购申请书，需要有默认地址
					if(tradeType_Check!=null && tradeType_Check=="1"){
						initTradeAddressInfoCheckId(tradeId,"query");
					}
					$("#submitCheckTradeCusInfoButton").linkbutton("enable");
				}else{
					$.messager.alert('提示', returnData.msg);
					$("#submitCheckTradeCusInfoButton").linkbutton("enable");
				}
			} catch (e) {
				$.messager.alert('提示', e);
				$("#submitCheckTradeCusInfoButton").linkbutton("enable");
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
	if(!confirm("是否确认删除选中的客户信息?")){
		return;
	}
	var tradeCustBaseInfo = customerData[0];
	var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_check").serialize());
	var custBaseInfoId = tradeCustBaseInfo.custBaseInfoId;
	var tradeCustInfoId = tradeCustBaseInfo.tradeCustInfoId;
	if(tradeCustInfoId==null||tradeCustInfoId==""||tradeCustInfoId==undefined){
		$("#customInfoCheckId").datagrid('deleteRow',operateCustomerInfoIndex);
		operateCustomerInfoIndex = null;
		return;
	}
	var param = {};
	param.tradeCustInfo = tradeCustBaseInfo;
	param.tradeInfo = tradeBaseInfo;
	
	var tradeType_Check = $("#tradeType_Check_In").combobox('getValue');
	var tradeId = $("#tradeId_Check_In").val();
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
					$("#customInfoCheckId").datagrid('reload');
					//重新加载银行账户信息
					initTradeBankInfoCheckId(tradeId,"query");
					if(tradeType_Check!=null && tradeType_Check=="1"){
						initTradeAddressInfoCheckId(tradeId,"query");
					}else{
						initRoleInfoId();
						initRiskProCheckInfoId("2");
						initRiskProductInfoCheckId();
						$("#riskTotalAssets_Check_In").val("");
						$("#chnRiskTotalAssets_Check_In").val("");
						$("#tradeRiskProtObj_Check_In").combobox("reset");
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

//角色信息
var roleInfoCheckId;
var roleInfoTableEditRowIndex = null;
function initRoleInfoId()
{
	var data = $.toJSON($("#customInfoCheckId").datagrid('getRows'));
	var tradeId = $("#tradeId_Check_In").val();
	roleInfoCheckId=$("#roleInfoCheckId").datagrid({
		url:contextPath+'/trade/queryTradeRoleInfo?tradeId='+tradeId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		 columns:[[ {field:'ck',checkbox:true},
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
									var edRelationToAppnt = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									$(edRelationToAppnt.target).combobox("reset");
									$(edRelationToAppnt.target).combobox("disable");
									var edRelationToInsured = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
									$(edRelationToInsured.target).combobox("reload");
									$(edRelationToInsured.target).combobox("enable");
									roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = null;
								}else if(record.code=='0'){
									var edRelationToInsured = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
									$(edRelationToInsured.target).combobox("reset");
									$(edRelationToInsured.target).combobox("disable");
									var edRelationToAppnt = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									$(edRelationToAppnt.target).combobox("reload");
									$(edRelationToAppnt.target).combobox("enable");
									roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = null;
								}
								var ed = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'roleType'});
								var roleCodeName = $(ed.target).combobox('getText');
								roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['roleTypeName'] = roleCodeName;
								
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
								roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['tradeCustInfoId'] = returnedData.tradeCustInfoId;
								var ed = $('#roleInfoCheckId').datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'customerCode'});
								if(ed!=null){
									$(ed.target).val(returnedData.tradeCustInfoId+"");
									
								}
								if(returnedData.custBaseInfoId!=null){
									//initTradeBankInfoInputId(returnedData.custBaseInfoId,"query");
								}
								
								var ed1 = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'customer'});
								var customerCodeName = $(ed1.target).combobox('getText');
								roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['customerCodeName'] = customerCodeName;
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
									var ed1 = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									var appntRelationCodeName = $(ed1.target).combobox('getText');
									roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = appntRelationCodeName;
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
								var ed1 = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
								var insuredRelationCodeName = $(ed1.target).combobox('getText');
								roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = insuredRelationCodeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.relationToInsuredName;
					}},
					{field:'relationToInsuredName',title:'与被保人关系编码',width:50,hidden:true}
		       ]],
		  toolbar: [{
			   		iconCls: 'icon-addrow',
			   		text : '新增',
			   		handler: function(){
			   			if(roleInfoTableEditRowIndex!=null){
			   				if(!$('#roleInfoCheckId').datagrid('validateRow',roleInfoTableEditRowIndex)){
			   					return;
			   				}
			   			}
			   			var length = $("#roleInfoCheckId").datagrid('getRows').length;
			   			$("#roleInfoCheckId").datagrid("appendRow",{status:'p'});
			   			$("#roleInfoCheckId").datagrid('checkRow',length);
			   			roleInfoTableEditRowIndex = length;
			   			$("#roleInfoCheckId").datagrid('beginEdit',length);
			   		}
			   	},'-',{
			   		iconCls: 'icon-edit',
			   		text : '修改',
			   		handler: function(){
			   			var selected = $('#roleInfoCheckId').datagrid('getSelected');
			   			if(selected){
			   				var index = $('#roleInfoCheckId').datagrid('getRowIndex', selected);
			   				roleInfoTableEditRowIndex = index;
				   			$("#roleInfoCheckId").datagrid("beginEdit",index);
				   			var roleType = selected.roleType;
				   			if(roleType=='1'){
								//initTradeBankInfoInputId("","init");
								var edRelationToAppnt = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
								$(edRelationToAppnt.target).combobox("reset");
								$(edRelationToAppnt.target).combobox("disable");
								var edRelationToInsured = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
								$(edRelationToInsured.target).combobox("reload");
								$(edRelationToInsured.target).combobox("enable");
								roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = null;
							}else if(roleType=='0'){
								var edRelationToInsured = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
								$(edRelationToInsured.target).combobox("reset");
								$(edRelationToInsured.target).combobox("disable");
								var edRelationToAppnt = roleInfoCheckId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
								$(edRelationToAppnt.target).combobox("reload");
								$(edRelationToAppnt.target).combobox("enable");
								roleInfoCheckId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = null;
							}
			   			}
			   		}
			   	},'-',{
			   		iconCls: 'icon-delete',
			   		text : '删除',
			   		handler: function(){
			   			delRoleInfo();
			   		}
			   	}],
			  onSelect:function(rowIndex, rowData){
				    if(roleInfoTableEditRowIndex!=null){
				    	if(!$('#roleInfoCheckId').datagrid('validateRow',roleInfoTableEditRowIndex)){
				    		//$.messager.alert('提示', "请选择或删除待录入角色信息！");
				    		//$("#roleInfoCheckId").datagrid("cancelEdit",roleInfoTableEditRowIndex);
				    		return;
				    	}else{
				    		$("#roleInfoCheckId").datagrid("endEdit",roleInfoTableEditRowIndex);
				    	}
				    }
			   },
			   onCheck:function(rowIndex, rowData){
				   roleInfoTableDeleteRowIndex = rowIndex;
			   }
	});
}


//险种财富信息
var riskProCheckInfoId;
var riskProCheckInfo = {};
function initRiskProCheckInfoId(proType)
{
	var tradeId = $("#tradeId_Check_In").val();
	riskProCheckInfoId=$("#riskProCheckInfoId").datagrid({
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
		 /*toolbar: [{
		   		iconCls: 'icon-remove',
		   		text : '删除',
		   		handler: function(){
		   			delRiskProInfo();
		   		}
		   	}],*/
		 onCheck:function(rowIndex,rowData)
		 {
			$("#tradeRiskProId_Check_In").combobox("setValue",rowData.proId);
			$("#tradeRiskProId_Check_In").combobox("setText",rowData.proCodeName);
			$("#tradeRiskProtObj_Check_In").combobox("setValue",rowData.insurId);
			$("#tradeRiskProtObj_Check_In").combobox("setText",rowData.insurObj);
			var tradeInfoId = $("#tradeId_Check_In").val();
			initRiskProductInfoCheckId(contextPath
					+'/trade/queryRiskProInfoObjList?tradeInfoId='+tradeInfoId+'&proId='+rowData.proId);
		 }
	});
}


//险种财富信息
var riskProInfoObjCheckId;
function initRiskProductInfoCheckId(urlPath)
{
	riskProInfoObjCheckId=$("#riskProInfoObjCheckId").datagrid({
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
		           {field:'inputValue',title:'录入项值',width:50,editor:'text'},
			       {field:'inputUnit',title:'录入项枚举值',width:50},
			       {field:'factorValueCode',title:'录入项值',width:50,hidden:true},
				   {field:'factorType',title:'要素类型',width:50,hidden:true} 
		       ]],
		 onCheck:function(rowIndex,rowData){
		    if(rowData.factorType=='0'){
		    	if(rowData.chooseFlag=="0"){
		    		$("#riskProInfoObjCheckId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'validatebox',
	                        options : {
	                            required : true
	                        }
	                    }
	                });
		    	}else{
		    		$("#riskProInfoObjCheckId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'text'
	                    }
	                });
		    	}
			}else if(rowData.factorType=='1'){
				if(rowData.chooseFlag=="0"){
					$("#riskProInfoObjCheckId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'combobox',
	                        options : {
	                        	//url:contextPath+'/codeQuery/tdCodeQuery?codeType='+rowData.code,
	                        	url:contextPath+'/trade/queryDefCode?codeType='+rowData.code+'&factorValueCode='+rowData.factorValueCode,
	                        	valueField:'code',
								textField:'codeName',
								editable:false,
								required : true
	                        }
	                    }
	                });
				}else{
					$("#riskProInfoObjCheckId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'combobox',
	                        options : {
	                        	//url:contextPath+'/codeQuery/tdCodeQuery?codeType='+rowData.code,
	                        	url:contextPath+'/trade/queryDefCode?codeType='+rowData.code+'&factorValueCode='+rowData.factorValueCode,
	                        	valueField:'code',
								textField:'codeName',
								editable:false
	                        }
	                    }
	                });
				}
				
			}else if(rowData.factorType=='2'){
				if(rowData.chooseFlag=="0"){
					$("#riskProInfoObjCheckId").datagrid('addEditor', {
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
					$("#riskProInfoObjCheckId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'datebox',
	                        options:{
	                        	validType:["validDate"]
	                        }
	                    }
	                });
				}
				
			}else if(rowData.factorType=='3'){
				if(rowData.chooseFlag=="0"){
					$("#riskProInfoObjCheckId").datagrid('addEditor', {
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
					$("#riskProInfoObjCheckId").datagrid('addEditor', {
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
			$("#riskProInfoObjCheckId").datagrid('beginEdit',rowIndex);
		 }
		
	});
}
//财富产品信息
var  monProInfoCheckId;
function initMonryProductInfoId(proType)
{
	var tradeId = $("#tradeId_Check_In").val();
	monProInfoCheckId=$("#monProInfoCheckId").datagrid({
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
        /*toolbar: [{
	   		iconCls: 'icon-remove',
	   		text : '删除',
	   		handler: function(){
	   			delMonProInfo();
	   			var selected = $('#monProInfoInputId').datagrid('getSelected');
	   			if(selected){
	   				var index = $('#monProInfoInputId').datagrid('getRowIndex', selected);
		   			$("#monProInfoInputId").datagrid("deleteRow",index);
	   			}
	   		}
	   	 }],*/
		 onCheck:function(rowIndex,rowData)
		 {
			$("#tradeWealthProId_Check_In").combobox("setValue",rowData.proId);
			$("#tradeWealthProId_Check_In").combobox("setText",rowData.proCodeName);
			var tradeInfoId = $("#tradeId_Check_In").val();
			initMonryProductInfoCheckId(contextPath
					+'/trade/queryMonryProInfoObjList?tradeInfoId='+tradeInfoId+'&proId='+rowData.proId);
			queryWealthProductDetailInfo(rowData.proId);
		 }
		
	});
}


//财富产品信息  录入项  录入值
var monProInfoObjCheckId;
function initMonryProductInfoCheckId(urlPath)
{
	monProInfoObjCheckId=$("#monProInfoObjCheckId").datagrid({
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
	           {field:'factorValueCode',title:'录入项值',width:50,hidden:true},
		       {field:'factorType',title:'要素类型',width:50,hidden:true}  
		       ]],
		 onCheck:function(rowIndex,rowData)
		 {
			if(rowData.factorType=='0'){
				if(rowData.chooseFlag=="0"){
					$("#monProInfoObjCheckId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'validatebox',
	                        options : {
	                            required : true
	                        }
	                    }
	                });
				}else{
					$("#monProInfoObjCheckId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'text'
	                    }
	                });
				}
				
			}else if(rowData.factorType=='1'){
				var factorCode = rowData.code;
				var productId = $("#tradeWealthProId_Check_In").combobox("getValue");
				var url = contextPath+'/trade/queryDefCode?codeType='+rowData.code+'&factorValueCode='+rowData.factorValueCode;
				if(factorCode=="expectOpenDay"){
					url =  contextPath+'/trade/queryOpenDateList?productId='+productId;
				}
				//产品期限
				if(factorCode=="closedPeriods"){
					url =  contextPath+'/trade/queryClosedPeriodsList?productId='+productId;
				}
				if(rowData.chooseFlag=="0"){
					$("#monProInfoObjCheckId").datagrid('addEditor', {
		                 field : 'inputValue',
		                 editor : {
		                     type : 'combobox',
		                     options : {
		                     	//url:contextPath+'/codeQuery/tdCodeQuery?codeType='+rowData.code,
		                     	url:url,
		                    	valueField:'code',
								textField:'codeName',
								editable:false,
		                        required : true
		                     }
		                 }
					});
				}else{
					$("#monProInfoObjCheckId").datagrid('addEditor', {
		                 field : 'inputValue',
		                 editor : {
		                     type : 'combobox',
		                     options : {
		                     	//url:contextPath+'/codeQuery/tdCodeQuery?codeType='+rowData.code,
		                     	url:url,
		                    	valueField:'code',
		                    	textField:'codeName',
								editable:false
		                     }
		                 }
					});
				}
			}else if(rowData.factorType=='2'){
				if(rowData.chooseFlag=="0"){
					$("#monProInfoObjCheckId").datagrid('addEditor', {
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
					$("#monProInfoObjCheckId").datagrid('addEditor', {
		                 field : 'inputValue',
		                 editor : {
		                     type : 'datebox',
		                     options:{
		                    	 validType:["validDate"]
		                     }
		                 }
		             });
				}
			}else if(rowData.factorType=='3'){
				if(rowData.chooseFlag=="0"){
					$("#monProInfoObjCheckId").datagrid('addEditor', {
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
					$("#monProInfoObjCheckId").datagrid('addEditor', {
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
			$("#monProInfoObjCheckId").datagrid('beginEdit',rowIndex);
		 }
		
	});
}



///	附件信息
var attachInfoCheckId;
function initAttachmentInfo()
{
	attachInfoCheckId=$("#attachInfoCheckId").datagrid({
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
			 $.messager.alert('提示','未实现的方法');
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
function submitCheckTradeInfo(){
	if(!$("#tradeInfoForm_check").form("validate")){
		return;
	}
	var formData = $("#tradeInfoForm_check").serialize();
	formData = formDataToJsonStr(formData);
	$.ajax({
		type:'post',
		url:'trade/saveTradeInfo',
		data:'param='+formData,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//tradeCheckList.datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					$("#tradeId_Check").val(returnData.obj.tradeInfoId);
					$("#tradeType_Check_In").combobox('disable');
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
	$("#submitCheckTradeInfoButton").hide();
	$("#submitCheckTradeCusInfoButton").hide();
	$("#submitTradeCheckBankInfoButton").hide();
	$("#submitTradeCheckAddressInfoButton").hide();
	$("#submitCheckRiskProButton").hide();
	$("#deleteRiskProButton").hide();
	$("#submitCheckWealthProButton").hide();
	$("#deleteWealthProButton").hide();
	$("#tradeType_Check_In").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType',
		valueField:'code',
		textField:'codeName',
		required:true,
		onSelect:function (record){
			if(record.code==2)
			{
				$("#roleInfoCheckPanelId").show();
				$("#riskProCheckInfoPanelId").show();
				$("#monProInfoCheckPanelId").hide();
				$("#addressInfoCheckPanelId").hide();
			}else{
				$("#roleInfoCheckPanelId").hide();
				$("#riskProCheckInfoPanelId").hide();
				$("#monProInfoCheckPanelId").show();
				$("#addressInfoCheckPanelId").show();
			}
			$.getJSON(contextPath+'/trade/getTradeInfoNo?productType='+record.code,
					function(data) {
						$("#tradeNo_Check_In").val(data);
					}); 
		}
	});
	$("#agentID_Check_In").combobox({
		url:contextPath+'/trade/queryTradeAgentId',
		valueField:'agentId',
		textField:'agentCodeName',
		required:true,
		disabled:true,
		onSelect:function (record){
			$("#companyID_Check_In").combobox("setValue",record.comId);
			$("#companyID_Check_In").combobox("setText",record.comCodeName);
			/*$("#storeID_Check_In").combobox("setValue",record.storeId);*/
			/*$("#storeID_Check_In").combobox("setText",record.storeCodeName);*/
		}
	});
	$("#currency_Check_In").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName'
	});
	$("#tradeRiskProId_Check_In").combobox({
		url:contextPath+'/trade/queryTradeRiskProId?proType=2',
		valueField:'proId',
		textField:'pro',
//		required:true,
		onSelect:function(record){
			var tradeInfoId = $("#tradeId_Check_In").val();
			initRiskProductInfoCheckId(contextPath
					+'/trade/queryTradeProductFactor?codeType=riskfactorCode&proId='+record.proId);
		}
	});
	$("#tradeRiskProtObj_Check_In").combobox({
		url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Check_In").val(),
		valueField:'custID',
		textField:'custName',
		required:true,
		onSelect:function(record){
			//$("#tradeWealthProId").combobox('setText',record.proCode);
			//$("#tradeWealthProName").val(record.proName);
			
		}
	});
	$("#tradeWealthProId_Check_In").combobox({
		url:contextPath+'/trade/queryTradeRiskProId?proType=1',
		valueField:'proId',
		textField:'pro',
//		required:true,
		onSelect:function(record){
			//$("#tradeWealthProId").combobox('setText',record.proCode);
			//$("#tradeWealthProName").val(record.proName);
			var tradeInfoId = $("#tradeId_Check_In").val();
			initMonryProductInfoCheckId(contextPath
					+'/trade/queryTradeProductFactor?codeType=wealthfactorCode&proId='+record.proId);
			queryWealthProductDetailInfo(record.proId);
		}
	});
	$("#tradeComID_Check_In").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false/*,
		onSelect:function(data){
			$("#tradeStoreID_Check_In").combobox({
				url:contextPath+'/trade/queryTradeStoreId?comId='+data.comId,
				required:true,
				valueField:'storeId',
				textField:'storeCodeName',
				editable:false
			});
		}*/
	});
	/*$("#tradeStoreID_Check_In").combobox({
		url:contextPath+'/trade/queryTradeStoreId',
		required:true,
		valueField:'storeId',
		textField:'storeCodeName',
		editable:false
		//disabled:true
	});*/
	$("#tradeCheckConclusion").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=tradeCheckConclusion',
		valueField:'code',
		textField:'codeName',
		required:true,
		editable:false
	});
	$("#companyID_Check_In").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false,
		disabled:true
	});
	/*$("#storeID_Check_In").combobox({
		url:contextPath+'/trade/queryTradeStoreId',
		valueField:'storeId',
		textField:'storeCodeName',
		editable:false,
		disabled:true
	});*/
	$("#currency_check").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#tradeInputDate_Check_In").datebox({
		disabled:true
	});
}

function submitCheckTradeRole(){
	var formData = $("#roleInfoCheckId").datagrid('getRows');
	var appantCheck = 0;
	for(i=0;i<formData.length;i++){
		if(!$('#roleInfoCheckId').datagrid('validateRow',i)){
			return;
		}
	}
	$("#roleInfoCheckId").datagrid('acceptChanges');
	for(i=0;i<formData.length;i++){
		if(formData[i].roleCode=='1'){
			appantCheck = appantCheck + 1;
		}
	}
	if(appantCheck>1){
		$.messager.alert('提示',"只能选择一个投保人，请将多余投保人删除！");
		return;
	}
	//formData = formDataToJsonStr(formData);
	//formData = $.toJSON(formData);
	var tradeNo_Check = $("#tradeNo_Check_In").val();
	var tradeId_Check = $("#tradeId_Check_In").val();
	
	var param = {};
	param.tradeCustRoleList = formData;
	param.tradeInfoId = tradeId_Check;
	param.tradeNo = tradeNo_Check;
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeRole',
		data:'param='+$.toJSON(param),
		//data:'queryParam='+formData+'&tradeNo='+tradeNo_Check+'&tradeId='+tradeId_Check,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//$("#roleInfoCheckId").datagrid('loadData',returnData.obj);
					$.messager.alert('提示', returnData.msg);
					$("#roleInfoCheckId").datagrid('reload');
					$("#tradeRiskProtObj_Check_In").combobox({
						url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Check_In").val(),
						valueField:'custID',
						textField:'custName',
						onLoadSuccess:function(){
							var allData = $("#tradeRiskProtObj_Check_In").combobox("getData");
							for(var i=0;i<allData.length;i++){
								var riskProtObj = allData[i];
								var roleType = riskProtObj.roleType;
								if(roleType=="0"){
									$("#tradeRiskProtObj_Check_In").combobox("setValue",riskProtObj.custID);
								}
							}
						}/*,
						onSelect:function(record){
							//$("#tradeWealthProId").combobox('setText',record.proCode);
							//$("#tradeWealthProName").val(record.proName);
							var tradeInfoId = $("#tradeId_Check_In").val();
							var proId = $("#tradeRiskProId_Check_In").combobox('getValue');
							initRiskProductInfoCheckId(contextPath
									+'/trade/queryTradeProductFactor?codeType=riskfactorCode&proId='+proId);
						}*/
					});
					var tradeType_Check = $("#tradeType_Check_In").combobox('getValue');
					if(tradeType_Check!=null && tradeType_Check=="2"){
						//alert("tradeInfoId=="+tradeInfoId);
						initTradeBankInfoCheckId(tradeId_Check,"query");
						initRiskProCheckInfoId("2")
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



function submitCheckRiskPro(){
	/*if(!$("#riskProInfoObjCheckId").form("validate")){
		return;
	}*/
	//校验产品表格输入
	if(!validateDataGrid("riskProInfoObjCheckId")){
		$.messager.alert('提示', "产品输入信息有误,请检查产品输入信息");
		return;
	}
	if(!$("#tradeRiskProtObj_Check_In").combobox("isValid")){
		return;
	}
	
	$("#riskProInfoObjCheckId").datagrid('acceptChanges');
	var riskFactorData = $("#riskProInfoObjCheckId").datagrid('getRows');
	if(riskFactorData!=null&&riskFactorData.length>0){
		for(i=0;i<riskFactorData.length;i++){
			var factorCode=riskFactorData[i].code;
			if(factorCode=="premium"){
				var factorCodeValue=riskFactorData[i].inputValue;
				if(factorCodeValue==null||factorCodeValue==undefined||factorCodeValue==""){
					$.messager.alert('提示', "该产品信息含有保费必录项，请输入该产品的保费金额");
					return;
				}
			}
		}
	}
	
	riskFactorData = $.toJSON(riskFactorData);
	var tradeNo_Check = $("#tradeNo_Check_In").val();
	var tradeId_Check = $("#tradeId_Check_In").val();
	var tradeRiskProId = $("#tradeRiskProId_Check_In").combobox('getValue');
	var tradeRiskProtObj = $("#tradeRiskProtObj_Check_In").combobox('getValue');
	
	var params = {};
	params.riskFactorData = riskFactorData;
	params.tradeInfoId = tradeId_Check;
	params.tradeRiskProId = tradeRiskProId;
	params.tradeRiskProtObj = tradeRiskProtObj;
	
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeRiskPro',
		data:'param='+$.toJSON(params),
		//data:'riskFactorData='+riskFactorData+'&tradeId='+tradeId_Check+'&tradeRiskProId='+tradeRiskProId+'&tradeRiskProtObj='+tradeRiskProtObj,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//$("#roleInfoCheckId").datagrid('loadData',returnData.obj);
					$.messager.alert('提示', returnData.msg);
					$("#riskTotalAssets_Check_In").val(returnData.obj.totalAssets);
					$("#chnRiskTotalAssets_Check_In").val(returnData.obj.chnTotalAssets);
					initRiskProCheckInfoId("2");
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


function submitCheckWealthPro(){
	//校验产品表格输入
	if(!validateDataGrid("monProInfoObjCheckId")){
		$.messager.alert('提示', "产品输入信息有误,请检查产品输入信息");
		return;
	}
	$("#monProInfoObjCheckId").datagrid('acceptChanges');
	
	var tradeWealthProId = $("#tradeWealthProId_Check_In").combobox('getValue');
	var wealthProductData = $("#monProInfoCheckId").datagrid("getRows");
	if(wealthProductData!=null&&wealthProductData.length>0){
		var ProId =wealthProductData[0].proId;
		if(ProId==tradeWealthProId){
		}
		else{
			$.messager.alert('提示', "单笔交易只能购买一款财富产品！");
			return;
		}
	}

	var wealthFactorData = $("#monProInfoObjCheckId").datagrid('getRows');
	if(wealthFactorData!=null&&wealthFactorData.length>0){
		for(i=0;i<wealthFactorData.length;i++){
			var factorCode=wealthFactorData[i].code;
			if(factorCode=="subscriptionFee"){
				var factorCodeValue=wealthFactorData[i].inputValue;
				if(factorCodeValue==null||factorCodeValue==undefined||factorCodeValue==""){
					$.messager.alert('提示', "该产品信息含有认购金额必录项，请输入该产品的保费金额");
					return;
				}
			}
		}
		
	}
	wealthFactorData = $.toJSON(wealthFactorData);
	var tradeNo_Check = $("#tradeNo_Check_In").val();
	var tradeId_Check = $("#tradeId_Check_In").val();
	var param = {};
	param.wealthFactorData = wealthFactorData;
	param.tradeWealthProId = tradeWealthProId;
	param.tradeId = tradeId_Check;
	
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeWealthPro',
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					$.messager.alert('提示', returnData.msg);
					$("#monTotalAssets_Check_In").val(returnData.obj.totalAssets);
					$("#chnMonTotalAssets_Check_In").val(returnData.obj.chnTotalAssets);
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
	var tradeInfoId = $("#tradeId_Check_In").val();
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
						$.messager.alert('提示', "当前交易最新状态为("+resultInfo.obj.codeName+"),交易无需复核！");
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
function submitTradeCheck(){
	if(!$("#tradeCheckDiv").form("validate")){
		return;
	}
	/*if(!confirm("交易信息是否复核完毕?")){
		return;
	}*/

	if(!verifyTradeStatusInfo()){
		return;
	}
	$.messager.confirm('提示',"交易信息是否复合完毕？",function(r){
		if(r){
			var tradeType = $("#tradeType_Check_In").combobox("getValue");
			if(tradeType=="1"){
				//判断是否购买过物业宝产品并提交复核信息
				isHadBuyWYBProduct();
			}else{
				submitCheckInfo();
			}
		}else{
			return;
		}
	})
	
	/*var tradeType = $("#tradeType_Check_In").combobox("getValue");
	if(tradeType=="1"){
		//判断是否购买过物业宝产品并提交复核信息
		isHadBuyWYBProduct();
	}else{
		submitCheckInfo();
	}*/
	
}

/**
 * 
 * 判断客户是否购买过物业宝系列产品
 */
function isHadBuyWYBProduct(){
	var customerInfo = customInfoCheckId.datagrid("getRows")[0];
	var custName = customerInfo.chnName;
	var custMobile = customerInfo.mobile;
	var idNo = customerInfo.idNo;
	var idType = customerInfo.idType;
	var param = {};
	param.productId =  $("#monProInfoCheckId").datagrid("getRows")[0].proId;
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
					submitCheckInfo();
				}else{
					//$.messager.alert('提示', reData.msg);
					$.messager.confirm('确认',reData.msg+",您确认继续提交复核结论吗？",function(r){    
					    if (r){    
					    	submitCheckInfo();
					    } 
					}); 
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function submitCheckInfo(){

	var tradeId_Check = $("#tradeId_Check_In").val();
	var tradeType_Check = $("#tradeType_Check_In").combobox('getValue');
	var tradeConclusion = $("#tradeCheckConclusion").combobox('getValue');
	var tradeRemark = $("#tradeCheckRemark").val();
	if(tradeConclusion!=null){
		
	}
	else{
		$.messager.alert('提示', "请填写复核结论！！！");	
		return;
	}
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeInput',
		data:'tradeId='+tradeId_Check+'&tradeType='+tradeType_Check
		      +'&tradeOperationNode=02'+'&tradeConclusion='+tradeConclusion+'&tradeRemark='+tradeRemark,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//$("#roleInfoCheckId").datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', "复核完毕！");
					$("#tradeInfoCheck").dialog('close');
					$("#tradeCheckCommonListId").datagrid('reload');
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
	var deleteRows = $("#roleInfoCheckId").datagrid('getSelected');
	if(deleteRows==null||deleteRows==""||deleteRows==undefined){
		$.messager.alert('提示', "请选择需要删除的角色");
		return;
	}
	var tradeCustRoleInfoId = deleteRows.tradeCustRoleInfoId;
	if(tradeCustRoleInfoId==null||tradeCustRoleInfoId==""||tradeCustRoleInfoId==undefined){
		$('#roleInfoCheckId').datagrid('deleteRow',roleInfoTableEditRowIndex);
		roleInfoTableEditRowIndex = null;
		return;
	}
	if(roleInfoTableEditRowIndex!=null){
		if(!$('#roleInfoCheckId').datagrid('validateRow',roleInfoTableEditRowIndex)){
			return;
		}
	}
	if(!confirm("是否确认删除选中的角色信息?")){
		return;
	}
	var tradeId_Input = $("#tradeId_Check_In").val();
	var tradeCustInfoId = $("#roleInfoCheckId").datagrid('getSelected').customerCode;
	var roleType = $("#roleInfoCheckId").datagrid('getSelected').role;
	
	var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_check").serialize());
	var param = {};
	param.tradeCustRoleInfo = deleteRows;
	param.tradeInfo = tradeBaseInfo;
	
	$.ajax({
		type:'post',
		url:'trade/delRoleInfo',
		//data:'tradeId='+tradeId_Input+'&tradeCustInfoId='+tradeCustInfoId+'&roleType='+roleType,
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					$.messager.alert('提示', returnData.msg);
					$("#roleInfoCheckId").datagrid('reload');
					//重新加载账户信息
					$("#tradeBankInfoCheckId").datagrid('reload');
					//重新加载产品信息
					$("#riskProCheckInfoId").datagrid('reload');
					$("#riskProInfoObjCheckId").datagrid('reload');
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
	var tradeId_Input =  $("#tradeId_Check_In").val();
	var riskTotalAssets_Input = $("#riskTotalAssets_Check_In").val();
	var selected = $('#riskProCheckInfoId').datagrid('getSelected');
	var tradeRiskProductId = selected.proId;
	var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_check").serialize());
	
	var param = {};
	param.productId = tradeRiskProductId;
	param.tradeInfo = tradeBaseInfo;
	param.riskTotalAssets = riskTotalAssets_Input;
	
	$.ajax({
		type:'post',
		url:'trade/delRiskProInfo',
		//data:'tradeId='+tradeId_Input+'&riskTotalAssets='+riskTotalAssets_Input+'&tradeRiskProId='+tradeRiskProId,
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					$.messager.alert('提示', returnData.msg);
					$("#riskProCheckInfoId").datagrid('reload');
					$("#riskProInfoObjCheckId").datagrid('reload');
					$("#riskTotalAssets_Check_In").val(returnData.obj.totalAssets);
					$("#chnRiskTotalAssets_Check_In").val(returnData.obj.chnTotalAssets);
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
	var tradeId_Input =  $("#tradeId_Check_In").val();
	var monTotalAssets_Input = $("#monTotalAssets_Check_In").val();
	var selected = $('#monProInfoCheckId').datagrid('getSelected');
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
					$("#monProInfoCheckId").datagrid('reload');
					$("#monProInfoObjCheckId").datagrid('reload');
					$("#monTotalAssets_Check_In").val(returnData.totalAssets);
					$("#chnMonTotalAssets_Check_In").val(returnData.chnTotalAssets);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function uploadTradeVideo(){
	var param = {};
	param.businessNo = $("#tradeId_Check_In").val();
	param.businessType = "13";
	param.operate = loadFileType;
	addFileWindow('视频上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}
function uploadTradeCheckAttachInfo(){
	var param = {};
	var loadFileType=null;
	param.businessNo = $("#tradeId_Check_In").val();
	param.businessType = "04";
	param.operate = loadFileType;
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}

function delTradeCheckAttachInfo(){
	var param = {};
	param.businessNo = $("#tradeId_Check_In").val();
	param.businessType = "04";
	//param.operate = "queryFile";
	var loadFileType=null;
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

function initTradeBankInfoCheckId(id,type)
{
	var custInfoId = id;
	var tradeType_Check = $("#tradeType_Check_In").combobox('getValue');
	var urlPath = "";
	if(type=="init"){
		
	}else if(type=="query"){
		urlPath = contextPath+'/trade/queryTradeBankInfo?tradeId='+custInfoId+'&tradeType='+tradeType_Check;
	}else if(type=="search"){
		urlPath = contextPath+'/trade/searchTradeBankInfo?custAccInfoId='+custInfoId+'&rcState=E';
	}
	$("#tradeBankInfoCheckId").datagrid({
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
	           {field:'accFlagName',title:'是否交易默认账户',width:50}
		       ]]
	});
}

function submitTradeBankInfoCheckId(){
	var selectRow = $('#tradeBankInfoCheckId').datagrid('getSelections');
	if (selectRow.length == 0) {
		$.messager.alert('提示',"请选择一条账户信息进行保存!");
		return;
	}
	if (selectRow.length > 1) {
		$.messager.alert('提示',"只能选择一条账户信息进行保存!");
		return;
	}
	var tradeId = $("#tradeId_Check_In").val();
	selectRow = $.toJSON(selectRow);
	$.ajax({
		type:'post',
		url:'trade/saveTradeBankInfo',
		data:'rowData='+selectRow+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					initTradeBankInfoCheckId(tradeId,"query");
					$.messager.alert('提示', returnData.msg);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function initTradeAddressInfoCheckId(id,type)
{
	var custInfoId = id;
	var urlPath = "";
	if(type=="init"){
		
	}else if(type=="query"){
		var tradeType_Input = $("#tradeType_Check_In").combobox('getValue');
		var agentId_Input = $("#agentID_Check_In").combobox('getValue');
		urlPath = contextPath+'/trade/queryTradeAddressInfo?tradeId='+custInfoId+'&tradeType='+tradeType_Input+'&agentId='+agentId_Input;
	}else if(type=="search"){
		urlPath = contextPath+'/trade/searchTradeAddressInfo?custAddressInfoId='+custInfoId+'&rcState=E';
	}
	$("#tradeAddressInfoCheckId").datagrid({
		url:urlPath,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		columns:[[  
	           {field:'ck',checkbox:true}, 
	           {field:'tradeCustInfoId',title:'交易客户信息流水号',width:50,hidden:true}, 
	           {field:'custAddressInfoId',title:'客户地址信息流水号',width:50,hidden:true},    
	           {
					field : 'addressType',
					title : '地址类型',
					hidden : true
				},
				{
					field : 'addressTypeName',
					title : '地址类型名称',
					width : 50
				},
				{
					field : 'province',
					title : '省',
					hidden : true
				},{
					field : 'provinceName',
					title : '省名称',
					width : 50
				},{
					field : 'city',
					title : '市',
					hidden : true
				},{
					field : 'cityName',
					title : '市名称',
					width : 60
				},{
					field : 'country',
					title : '区/县',
					hidden : true
				},{
					field : 'countryName',
					title : '区/县名称',
					width : 70
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

function submitTradeAddressInfoCheckId(){
	var selectRow = $('#tradeAddressInfoCheckId').datagrid('getSelections');
	if (selectRow.length == 0) {
		$.messager.alert('提示',"请选择一条地址信息进行保存!");
		return;
	}
	if (selectRow.length > 1) {
		$.messager.alert('提示',"只能选择一条地址信息进行保存!");
		return;
	}
	var tradeId = $("#tradeId_Check_In").val();
	selectRow = $.toJSON(selectRow);
	
	var tradeType_Check = $("#tradeType_Check_In").combobox('getValue');
	$.ajax({
		type:'post',
		url:'trade/saveTradeAddressInfo',
		data:'rowData='+selectRow+'&tradeId='+tradeId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					if(tradeType_Check!=null && tradeType_Check=="1"){
						initTradeAddressInfoCheckId(tradeId,"query");
					}
					$.messager.alert('提示', returnData.msg);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function initTradeAuditConclusion(){
	var tradeId_Check =  $("#tradeId_Check_In").val();
	$.ajax({
		type:'post',
		url:'trade/queryTradeConclusion2',
		data:'tradeId='+tradeId_Check+'&tradeOperationNode=03',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$("#tradeAuditConclusion_Check").combobox('setValue',returnData.tradeConclusion);
					$("#tradeAuditConclusion_Check").combobox('setText',returnData.tradeConclusionName);
					$("#tradeAuditRemark_Check").val(returnData.tradeRemark);
				}else{
					$("#tradeCheckAuditConclusionDiv").hide();
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


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
					setInputValueById("check_monProInfoSelectDiv",dataObj);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function tradeCheckBack(){
	$("#tradeInfoCheck").window("destroy");
}