var CustomInfojson={"total":3,"rows":[{'custBaseInfoId':'1','tradeCode':'1','tradeType':'张三'},{'custBaseInfoId':'2','tradeCode':'2','tradeType':'李四'}]}
var RoleInfojson={"total":3,"rows":[{'role':'1'},{'role':'2'}]}
var riskProductInfojson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var riskProductInfoInputIdJson={"total":3,"rows":[{'inputName':'1'},{'inputName':'2'}]}
var monryProductInfoIdjson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var monryProductInfoInputIdJson={"total":3,"rows":[{'productName':'1'},{'productName':'2'}]}
var attachmentInfoIdJson={"total":3,"rows":[{'attachType':'1'},{'attachType':'2'}]};

var loadFileType = null;
var tradeNo_Input=null;
var investCustomerType = '01';
var custLevel = '02';
jQuery(function($) {
	initTradeInputPage();
});

function initTradeInputPage(){
	//客户信息
	initCustomInfoId();
	//customInfoInputId.datagrid("loadData",CustomInfojson)
	//角色信息
	initRoleInfoId();
	//roleInfoInputId.datagrid("loadData",RoleInfojson);
	initTradeBankInfoInputId("","init");
	initTradeAddressInfoInputId("","init");
	//险种财富信息
	initRiskProInputInfoId("");
	//riskProInputInfoId.datagrid("loadData",riskProductInfojson);
	//险种财富信息
	initRiskProductInfoInputId();
	//riskProInfoObjInputId.datagrid("loadData",riskProductInfoInputIdJson);
	//财富产品信息
	initMonryProductInfoId("");
	//monProInfoInputId.datagrid("loadData",monryProductInfoIdjson);
	//财富产品信息
	initMonryProductInfoInputId();
	//monProInfoObjInputId.datagrid("loadData",monryProductInfoInputIdJson);
	//附件信息
	initAttachmentInfo();
	//attachInfoInputId.datagrid("loadData",attachmentInfoIdJson);
	$('#tradeDate').datebox({required:true});
	//初始化下拉框
	initAllCombobox();
}

//客户信息
var customInfoInputId;
var operateCustomerInfoIndex = null;
var custBaseInfoId ;
function initCustomInfoId()
{
	var tradeId = $("#tradeId_Input").val();
	customInfoInputId=$("#customInfoInputId").datagrid({
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
	           {field:'tradeCustInfoId',title:'交易客户流水号',width:50,hidden:true}, 
	           {field:'custBaseInfoId',title:'客户流水号',width:50,hidden:true,
	        	   formatter : function(value, row, index) {
	        		   custBaseInfoId = row.custBaseInfoId;
	        	}}, 
	           {field:'investCustomerType',title:'客户投资类型',width:50,hidden:true,
	        	   formatter : function(value, row, index) {
	        		   investCustomerType = row.investCustomerType;
	        	}
	           },
	           {field:'custLevel',title:'客户类型',width:50,hidden:true,
	        	   formatter : function(value, row, index) {
	        		   custLevel = row.custLevel;
	        	}
	           },
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
	           /*{field:'mobile',title:'联系电话',width:50},
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
	           {field:'investCustomerType',title:'投资者类型',width:50,hidden:true},
	           {field:'custLevel',title:'客户级别',width:50,hidden:true},
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




//角色信息
var roleInfoInputId;
var roleInfoTableEditRowIndex = null;
var roleInfoTableDeleteRowIndex = null;
function initRoleInfoId()
{
	var data = $.toJSON($("#customInfoInputId").datagrid('getRows'));
	var tradeId = $("#tradeId_Input").val();
	roleInfoInputId=$("#roleInfoInputId").datagrid({
		url:contextPath+'/trade/queryTradeRoleInfo?tradeId='+tradeId,
		fitColumns:true,
		rownumbers:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		 columns:[[  
		           {field:'ck',checkbox:true}, 
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
									var edRelatinToAppnt = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									$(edRelatinToAppnt.target).combobox("reset");
									$(edRelatinToAppnt.target).combobox("disable");
									var edRelatinToInsured = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
									$(edRelatinToInsured.target).combobox("reload");
									$(edRelatinToInsured.target).combobox("enable");
									roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = null;
								}else if(record.code=='0'){
									var edRelatinToInsured = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
									$(edRelatinToInsured.target).combobox("reset");
									$(edRelatinToInsured.target).combobox("disable");
									var edRelatinToAppnt = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
									$(edRelatinToAppnt.target).combobox("reload");
									$(edRelatinToAppnt.target).combobox("enable");
									roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = null;
								}
								var ed = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'roleType'});
								var roleCodeName = $(ed.target).combobox('getText');
								roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['roleTypeName'] = roleCodeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.roleTypeName;
					}}, 
					{field:'roleTypeName',title:'角色编码名称',width:50,hidden:true},
		           {field:'customer',title:'客户',width:50, 
						editor:{
						type:'combobox',
						options:{
		        	  		url:contextPath+'/trade/queryCustomerCombo?tradeId='+tradeId,
							valueField:'customername',
							textField:'customername',
							required:true,
							onSelect:function(returnedData){
								roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['tradeCustInfoId'] = returnedData.tradeCustInfoId;
								var ed = $('#roleInfoInputId').datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'customerCode'});
								if(ed!=null){
									$(ed.target).val(returnedData.tradeCustInfoId+"");
									
								}
								if(returnedData.custBaseInfoId!=null){
									//initTradeBankInfoInputId(returnedData.custBaseInfoId,"query");
								}
								
								var ed1 = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'customer'});
								var customerCodeName = $(ed1.target).combobox('getText');
								roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['customerCodeName'] = customerCodeName;
							
							}
						}
					},
					formatter : function(value, row, index) {
						return row.customerCodeName;
					}},
					{field:'customerCodeName',title:'角色编码',width:50,hidden:true},
					{field:'customerCode',title:'客户流水号',width:50,editor:'text',hidden:true},
		           {field:'relationToAppnt',title:'与投保人关系',width:50, editor:{
						type:'combobox',
						options:{
		        	  		url:contextPath+'/codeQuery/tdCodeQuery?codeType=relation',
							valueField:'code',
							textField:'codeName',
							//required:true,
							onSelect:function(){
								var ed1 = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
								var appntRelationCodeName = $(ed1.target).combobox('getText');
								roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = appntRelationCodeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.relationToAppntName;
					}}, 
					{field:'relationToAppntName',title:'与投保人关系编码',width:50,hidden:true},
		            {field:'relationToInsured',title:'与被保人关系',width:50, editor:{
						type:'combobox',
						options:{
		        	  		url:contextPath+'/codeQuery/tdCodeQuery?codeType=relation',
							valueField:'code',
							textField:'codeName',
							//required:true,
							onSelect:function(){
								var ed1 = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
								var insuredRelationCodeName = $(ed1.target).combobox('getText');
								roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = insuredRelationCodeName;
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
			   				if(!$('#roleInfoInputId').datagrid('validateRow',roleInfoTableEditRowIndex)){
			   					return;
			   				}
			   			}
			   			var length = $("#roleInfoInputId").datagrid('getRows').length;
			   			$("#roleInfoInputId").datagrid("appendRow",{
			   				role:'',
			   				roleCode:'',
			   				customer:'',
			   				appntRelation:'',
			   				insuredRelation:'',
			   				insuredRelationCode:''
			   			});
			   			$("#roleInfoInputId").datagrid('checkRow',length);
			   			roleInfoTableEditRowIndex = length;
			   			$("#roleInfoInputId").datagrid('beginEdit',length);
			   		}
			   	},'-',{
			   		iconCls: 'icon-edit',
			   		text : '修改',
			   		handler: function(){
			   			var selected = $('#roleInfoInputId').datagrid('getSelected');
			   			if(selected){
			   				var index = $('#roleInfoInputId').datagrid('getRowIndex', selected);
			   				roleInfoTableEditRowIndex = index;
				   			$("#roleInfoInputId").datagrid("beginEdit",index);
				   			var roleType = selected.roleType;
				   			if(roleType=='1'){
								//initTradeBankInfoInputId("","init");
								var edRelatinToAppnt = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
								$(edRelatinToAppnt.target).combobox("reset");
								$(edRelatinToAppnt.target).combobox("disable");
								var edRelatinToInsured = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
								$(edRelatinToInsured.target).combobox("reload");
								$(edRelatinToInsured.target).combobox("enable");
								roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToAppntName'] = null;
							}else if(roleType=='0'){
								var edRelatinToInsured = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToInsured'});
								$(edRelatinToInsured.target).combobox("reset");
								$(edRelatinToInsured.target).combobox("disable");
								var edRelatinToAppnt = roleInfoInputId.datagrid('getEditor', {index:roleInfoTableEditRowIndex,field:'relationToAppnt'});
								$(edRelatinToAppnt.target).combobox("reload");
								$(edRelatinToAppnt.target).combobox("enable");
								roleInfoInputId.datagrid('getRows')[roleInfoTableEditRowIndex]['relationToInsuredName'] = null;
							}
			   			}
			   		}
			   	},'-',{
			   		iconCls: 'icon-delete',
			   		text : '删除',
			   		handler: function(){
			   			delRoleInfo();
			   			/*var selected = $('#roleInfoInputId').datagrid('getSelected');
			   			if(selected){
			   				var index = $('#roleInfoInputId').datagrid('getRowIndex', selected);
				   			$("#roleInfoInputId").datagrid("deleteRow",index);
			   			}*/
			   		}
			   	}],
			onSelect:function(rowIndex, rowData){
				    if(roleInfoTableEditRowIndex!=null){
				    	if(!$('#roleInfoInputId').datagrid('validateRow',roleInfoTableEditRowIndex)){
				    		return;
				    	}else{
				    		$("#roleInfoInputId").datagrid("endEdit",roleInfoTableEditRowIndex);
				    	}
				    }
			   },
		   onCheck:function(rowIndex, rowData){
			   roleInfoTableDeleteRowIndex = rowIndex;
		   }
	});
}


//险种财富信息
var riskProInputInfoId;
var riskProCheckInfo = {};
var riskProInputInfoId_index = null;
function initRiskProInputInfoId(proType)
{
	var tradeId = $("#tradeId_Input").val();
	riskProInputInfoId=$("#riskProInputInfoId").datagrid({
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
		       ///toolbar:"#riskProTradeTotalDiv",
		       /*toolbar: [{
			   		iconCls: 'icon-remove',
			   		text : '删除',
			   		handler: function(){
			   			delRiskProInfo();
			   			var selected = $('#riskProInputInfoId').datagrid('getSelected');
			   			if(selected){
			   				var index = $('#riskProInputInfoId').datagrid('getRowIndex', selected);
				   			$("#riskProInputInfoId").datagrid("deleteRow",index);
			   			}
			   		}
			   	}],*/
		 onCheck:function(rowIndex,rowData)
		 {
			$("#tradeRiskProId").combobox("setValue",rowData.proId);
			$("#tradeRiskProId").combobox("setText",rowData.proCodeName);
			$("#tradeRiskProtObj").combobox("setValue",rowData.insurId);
			$("#tradeRiskProtObj").combobox("setText",rowData.insurObj);
			var tradeInfoId = $("#tradeId_Input").val();
			initRiskProductInfoInputId(contextPath
					+'/trade/queryRiskProInfoObjList?tradeInfoId='+tradeInfoId+'&proId='+rowData.proId);
		 }
	});
}


//险种财富信息
var riskProInfoObjInputId;
function initRiskProductInfoInputId(urlPath)
{
	riskProInfoObjInputId=$("#riskProInfoObjInputId").datagrid({
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
		        	   editor : {
	                        type : 'text',
	                        options : {
	                            required : true
	                        }
	                    }},
		           {field:'inputUnit',title:'录入项枚举值',width:50},
		           {field:'factorValueCode',title:'录入项值',width:50,hidden:true},
			       {field:'factorType',title:'要素类型',width:50,hidden:true}
		       ]],
		 onCheck:function(rowIndex,rowData)
		 {
		    if(rowData.factorType=='0'){
		    	if(rowData.chooseFlag=="0"){
		    		$("#riskProInfoObjInputId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'text',
	                        options : {
	                            required : true
	                        }
	                    }
	                });
		    	}else{
		    		$("#riskProInfoObjInputId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'text'
	                    }
	                });
		    	}
				
			}else if(rowData.factorType=='1'){
				var factorCode = rowData.code;
				if(rowData.chooseFlag=="0"){
					$("#riskProInfoObjInputId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'combobox',
	                        options : {
	                        	url:contextPath+'/trade/queryDefCode?codeType='+rowData.code+'&factorValueCode='+rowData.factorValueCode,
								valueField:'code',
								textField:'codeName',
								editable:false,
	                            required : true,
	                            onHidePanel:function(){
	                            	if(factorCode=="insurdPeriodUnit"){
	                            		var value=$(this).combobox("getValue");
	                            		if(value=="E"){
	                            			$(this).combobox("setValue","A");
	                            			$.messager.alert('提示', "保终身请填写为105周岁");
	                            		}
	                            	}
	                            	 
	                            }
	                        }
	                    }
	                });
				}else{
					$("#riskProInfoObjInputId").datagrid('addEditor', {
	                    field : 'inputValue',
	                    editor : {
	                        type : 'combobox',
	                        options : {
	                        	url:contextPath+'/trade/queryDefCode?codeType='+rowData.code+'&factorValueCode='+rowData.factorValueCode,
								valueField:'code',
								textField:'codeName',
								editable:false,
	                            onHidePanel:function(){
	                            	if(factorCode=="insurdPeriodUnit"){
	                            		var value=$(this).combobox("getValue");
	                            		if(value=="E"){
	                            			$(this).combobox("setValue","A");
	                            			$.messager.alert('提示', "保终身请填写为105周岁");
	                            		}
	                            	}
	                            }
	                        }
	                    }
	                });
				}
				
			}else if(rowData.factorType=='2'){
				if(rowData.chooseFlag=="0"){
					$("#riskProInfoObjInputId").datagrid('addEditor', {
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
					$("#riskProInfoObjInputId").datagrid('addEditor', {
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
					$("#riskProInfoObjInputId").datagrid('addEditor', {
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
					$("#riskProInfoObjInputId").datagrid('addEditor', {
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
			$("#riskProInfoObjInputId").datagrid('beginEdit',rowIndex);
		 }
		
	});
}
//财富产品信息
var  monProInfoInputId;
function initMonryProductInfoId(proType)
{
	var tradeId = $("#tradeId_Input").val();
	monProInfoInputId=$("#monProInfoInputId").datagrid({
		url:contextPath+'/trade/queryTradeWealthProInfo?tradeId='+tradeId+'&proType='+proType,
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
	/* onCheck:function(rowIndex,rowData)
		 {
			$("#tradeWealthProId").combobox("setValue",rowData.proId);
			$("#tradeWealthProId").combobox("setText",rowData.proCodeName);
			queryWealthProductDetailInfo(rowData.proId);
			var tradeInfoId = $("#tradeId_Input").val();
			initMonryProductInfoInputId(contextPath
					+'/trade/queryMonryProInfoObjList?tradeInfoId='+tradeInfoId+'&proId='+rowData.proId);
		 }*/
		       
	 //预约和交易关联后，更新时直接显示产品信息         
		onLoadSuccess : function() {
			//默认选中第一行
			var rowData = $('#monProInfoInputId').datagrid('getData').rows[0];
			if(rowData==undefined||rowData==null){//如果没有录产品信息就停止
				return;
			}
			$("#tradeWealthProId").combobox("setValue",rowData.proId);
			$("#tradeWealthProId").combobox("setText",rowData.proCodeName);
			queryWealthProductDetailInfo(rowData.proId);
			var tradeInfoId = $("#tradeId_Input").val();
			initMonryProductInfoInputId(contextPath
					+'/trade/queryMonryProInfoObjList?tradeInfoId='+tradeInfoId+'&proId='+rowData.proId);
	   	}
	});
}


//财富产品信息  录入项  录入值
var monProInfoObjInputId;
function initMonryProductInfoInputId(urlPath)
{
	monProInfoObjInputId=$("#monProInfoObjInputId").datagrid({
		//url:contextPath+'/codeQuery/tdCodeQuery?codeType=wealthfactorCode',
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
	        	   editor : {
                       type : 'text',
                       options : {
                           required : true
                       }
                   }},
	           {field:'inputUnit',title:'录入项枚举值',width:50},
	           {field:'factorValueCode',title:'录入项值',width:50,hidden:true},
	           {field:'factorType',title:'要素类型',width:50,hidden:true}
		       ]],
		/* onCheck:function(rowIndex,rowData)
		 {
			 //console.info(rowData);
			 
			//$("#monProInfoObjInputId").datagrid('beginEdit',rowIndex);
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

				// alert(editors instanceof Array);
				 var subscriptionFeeEditor = editors[0];
				 subscriptionFeeEditor.target.bind('input propertychange', function(e){  
                   //将本次修改的值更新到rowData的相应列数据中  
                   //rowData[workRateEditor.field] = $(this).val();
					 var tipsContent = numToUpper($(this).val());
					 //alert(tipsContent);
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
		 },*/
		 onLoadSuccess:function(){
			 var objRowData=$("#monProInfoObjInputId").datagrid('getRows');
			 for(var i=0;i<objRowData.length;i++){
			  var rowData=objRowData[i];
			  var rowIndex=i;
				//console.info(rowData); 
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

					// alert(editors instanceof Array);
					 var subscriptionFeeEditor = editors[0];
					 subscriptionFeeEditor.target.bind('input propertychange', function(e){  
	                   //将本次修改的值更新到rowData的相应列数据中  
	                   //rowData[workRateEditor.field] = $(this).val();
						 var tipsContent = numToUpper($(this).val());
						 //alert(tipsContent);
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
			 
			
		 }
	});
}

///	附件信息
var attachInfoInputId;
function initAttachmentInfo()
{
	attachInfoInputId=$("#attachInfoInputId").datagrid({
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
	var custinfo = eval("("+decodeURI(customerInfo)+")");
	if(custinfo.custBaseInfoId!=null&&custinfo.custBaseInfoId!=""&&custinfo.custBaseInfoId!=undefined){
		var custBaseInfoId = custinfo.custBaseInfoId;
		var tradeCustInfoId = null;
		var allRows = $("#customInfoInputId").datagrid('getRows');
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
			$("#customInfoInputId").datagrid('appendRow',custinfo);
		}else{
			custinfo.tradeCustInfoId = tradeCustInfoId;
			$("#customInfoInputId").datagrid('deleteRow',oldInfoIndex);
			$("#customInfoInputId").datagrid('insertRow',{index:oldInfoIndex,row:custinfo});
		}
	}
}

function submitTradeCusInfo(){

	//$("#submitTradeCusInfoButton").linkbutton("disable");
	var customerData = $("#customInfoInputId").datagrid('getRows');
	var tradeId = $("#tradeId_Input").val();
	var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_input").serialize());
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
		$.messager.alert('提示', "请先保存交易基本信息");
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
					investCustomerType = customerData[0].investCustomerType;
					custLevel = customerData[0].custLevel;
					custBaseInfoId = customerData[0].custBaseInfoId;
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

function delCustomerInfo(){
	var customerData = $("#customInfoInputId").datagrid('getSelections');
	if (customerData.length == 0) {
		$.messager.alert('提示',"请选择一个客户进行删除!");
		return;
	}
	/*if(!confirm("是否确认删除选中的客户信息?")){
		return;
	}*/
	$.messager.confirm('提示',"是否确认删除选中的客户信息?",function(r){
		if(r){
			var tradeCustBaseInfo = customerData[0];
			var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_input").serialize());
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
			
			var tradeId = $("#tradeId_Input").val();
			var tradeType_Input = $("#tradeType_Input").combobox('getValue');
			
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
							initTradeBankInfoInputId(tradeId,"query");
							if(tradeType_Input!=null && tradeType_Input=="1"){
								initTradeAddressInfoInputId(tradeId,"query");
							}else{
								initRoleInfoId();
								initRiskProInputInfoId("2");
								initRiskProductInfoInputId();
								$("#riskTotalAssets_Input").val("");
								$("#chnRiskTotalAssets_Input").val("");
								$("#tradeRiskProtObj").combobox("reset");
							}
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
	});
	
}

/**
 * ========================================================
 */

/*
 * 提交交易信息
 */
function submitTradeInfo(){
	var agentID_Input=$("#agentID_Input").val();
	if(!$("#tradeInfoForm_input").form("validate")){
		return;
	}
	
	var formData = $("#tradeInfoForm_input").serialize();
	formData = formDataToJsonStr(formData);
	$.ajax({
		type:'post',
		url:'trade/saveTradeInfo',
		data:'param='+formData,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//tradeInputList.datagrid('loadData',returnData.obj);	
					$.messager.alert('提示', returnData.msg);
					$("#tradeId_Input").val(returnData.obj.tradeInfoId);
					$("#tradeType_Input").combobox('disable');
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
function initAllCombobox(){//xtso
	$("#tradeType_Input").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType',
		valueField:'code',
		textField:'codeName',
		onSelect:function (record)
		{
			if(record.code==2)				
			{   $(".wealthHidden").show();
			    $("#submitTradeCustomerInfoButton").hide();
				$("#roleInfoInputPanelId").show();
				$("#riskProInputInfoPanelId").show();
				$("#monProInfoInputPanelId").hide();
				$("#addressInfoInputPanelId").hide();
				$("#tradeInfoNo_Input").validatebox({required:false,validType:'length[0,40]'});
				
			}else{
				$(".wealthHidden").hide();
				$("#submitTradeCustomerInfoButton").show();
				$("#roleInfoInputPanelId").hide();
				$("#riskProInputInfoPanelId").hide();
				$("#monProInfoInputPanelId").show();
				$("#addressInfoInputPanelId").show();
				$("#tradeInfoNo_Input").validatebox({required:false,validType:'length[0,40]'});
			}
			$.getJSON(contextPath+'/trade/getTradeInfoNo?productType='+record.code,
					function(data) {
						$("#tradeNo_Input").val(data);
					}); 
		}
	});
	$("#agentID_Input").combobox({
		url:contextPath+'/trade/queryTradeAgentId',
		valueField:'agentId',
		textField:'agentCodeName',
		disabled:true,
		onSelect:function (record){
			$("#companyID_Input").combobox("setValue",record.comId);
			$("#companyID_Input").combobox("setText",record.comCodeName);
			/*$("#storeID_Input").combobox("setValue",record.storeId);
			$("#storeID_Input").combobox("setText",record.storeCodeName);*/
		}
	});
	$("#currency_Input").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName',
		value:'CNY'
	});
	$("#tradeRiskProId").combobox({
		url:contextPath+'/trade/queryTradeRiskProId?proType=2',
		valueField:'proId',
		textField:'pro',
		onSelect:function(record){
			var tradeInfoId = $("#tradeId_Input").val();
			initRiskProductInfoInputId(contextPath
					+'/trade/queryTradeProductFactor?codeType=riskfactorCode&proId='+record.proId);
		}
	});
	$("#tradeRiskProtObj").combobox({
		url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Input").val(),
		valueField:'custID',
		textField:'custName',
		onSelect:function(record){
			//$("#tradeWealthProId").combobox('setText',record.proCode);
			//$("#tradeWealthProName").val(record.proName);
			
		}
	});
	$("#tradeWealthProId").combobox({
		url:contextPath+'/trade/queryTradeRiskProId?proType=1',
		valueField:'proId',
		textField:'pro',	
		onSelect:function(record){
			//$("#tradeWealthProId").combobox('setText',record.proCode);
			//$("#tradeWealthProName").val(record.proName);
			var tradeInfoId = $("#tradeId_Input").val();
			initMonryProductInfoInputId(contextPath
					+'/trade/queryTradeProductFactor?codeType=wealthfactorCode&proId='+record.proId);
			queryWealthProductDetailInfo(record.proId);
			//更新时根据产品信息初始化合同号
			var productId = record.proId;
			$("#tradeInfoNo_Input").combobox({
				url : contextPath + '/codeQuery/contractNumberQuery?productId='+productId,
				valueField : 'id',
				textField : 'name'
			});
			$("#tradeInfoNo_Input").combobox("setValue",$("#contractNum_Input").val());
		},
		onLoadSuccess : function(){
			var productID = $("#productId_Input").val();
			$("#tradeInfoNo_Input").combobox({
				url : contextPath + '/codeQuery/contractNumberQuery?productId='+productID,
				valueField : 'id',
				textField : 'name'
			});
			//原值
			$("#tradeInfoNo_Input").combobox("setValue",$("#contractNum_Input").val());
		}
	});
	$("#tradeComID_Input").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false/*,
		onSelect:function(data){
			$("#tradeStoreID_Input").combobox({
				url:contextPath+'/trade/queryTradeStoreId?comId='+data.comId,
				required:true,
				valueField:'storeId',
				textField:'storeCodeName',
				editable:false
			});
		}*/
	});
	/*$("#tradeStoreID_Input").combobox({
		url:contextPath+'/trade/queryTradeStoreId',
		required:true,
		valueField:'storeId',
		textField:'storeCodeName',
		editable:false
	});*/
	$("#companyID_Input").combobox({
		url:contextPath+'/trade/queryTradeComId',
		valueField:'comId',
		textField:'comCodeName',
		editable:false,
		disabled:true
	});
/*	$("#storeID_Input").combobox({
		url:contextPath+'/trade/queryTradeStoreId',
		valueField:'storeId',
		textField:'storeCodeName',
		editable:false,
		disabled:true
	});*/
	$("#chooseCust_input").combobox({
		url:contextPath+'/codeQuery/customerQueryByUserId?userId='+userId,
		valueField:'id',
		textField:'codeName',
		onSelect:function(record){
			tradeNo_Input=$("#tradeNo_Input").val();
			if(tradeNo_Input!=null&&tradeNo_Input!=""&&tradeNo_Input!=undefined){
				initCustomInfoByCustInput(record.id);
				initRoleInfoId();
				initTradeBankInfoInputId(record.id,"getByCustId");
				var tradeType_Input = $("#tradeType_Input").combobox('getValue');
				if(tradeType_Input!=null && tradeType_Input=="1"){
					initTradeAddressInfoInputId(record.id,"getByCustBaseInfoId");
				}
			}else{
				$.messager.alert('提示', "请先保存交易基本信息!");
				$("#chooseCust_input").combobox('clear');
			}
			
		}
	});
	
	var currentDate = getCurrentDate();
	$("#tradeInputDate_Input").datebox({required:true});
	$("#tradeInputDate_Input").datebox("setValue",currentDate);	
}

function submitTradeRole(){
	var formData = $("#roleInfoInputId").datagrid('getRows');
	var appant = 0;
	for(i=0;i<formData.length;i++){
		if(!$('#roleInfoInputId').datagrid('validateRow',i)){
			return;
		}
	}
	
	$("#roleInfoInputId").datagrid('acceptChanges');
	for(i=0;i<formData.length;i++){
		if(formData[i].role=='1'){
			appant = appant + 1;
		}
	}
	if(appant>1){
		alert("只能选择一个投保人，请将多余投保人删除！");
		return;
	}
	//formData = formDataToJsonStr(formData);
	//formData = $.toJSON(formData);
	var tradeNo = $("#tradeNo_Input").val();
	var tradeInfoId = $("#tradeId_Input").val();
	//alert("tradeInfoId=="+tradeInfoId);
	var param = {};
	param.tradeCustRoleList = formData;
	param.tradeInfoId = tradeInfoId;
	param.tradeNo = tradeNo;
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeRole',
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//$("#roleInfoInputId").datagrid('loadData',returnData.obj);
					$.messager.alert('提示', returnData.msg);
					$("#roleInfoInputId").datagrid('reload');
					$("#tradeRiskProtObj").combobox({
						url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Input").val(),
						valueField:'custID',
						textField:'custName',
						onLoadSuccess:function(){
							var allData = $("#tradeRiskProtObj").combobox("getData");
							for(var i=0;i<allData.length;i++){
								var riskProtObj = allData[i];
								var roleType = riskProtObj.roleType;
								if(roleType=="0"){
									$("#tradeRiskProtObj").combobox("setValue",riskProtObj.custID);
								}
							}
						}/*,
						onSelect:function(record){
							//$("#tradeWealthProId").combobox('setText',record.proCode);
							//$("#tradeWealthProName").val(record.proName);
							var tradeInfoId = $("#tradeId_Input").val();
							var proId = $("#tradeRiskProId").combobox('getValue');
							initRiskProductInfoInputId(contextPath
									+'/trade/queryTradeProductFactor?codeType=riskfactorCode&proId='+proId);
						}*/
					});
					var tradeType_Input = $("#tradeType_Input").combobox('getValue');
					if(tradeType_Input!=null && tradeType_Input=="2"){
						//alert("tradeInfoId=="+tradeInfoId);
						initTradeBankInfoInputId(tradeInfoId,"query");
						initRiskProInputInfoId("2")
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



function submitRiskPro(){
	if(!$("#riskProInputSelectId").form("validate")){
		return;
	}
	//校验产品表格输入
	if(!validateDataGrid("riskProInfoObjInputId")){
		$.messager.alert('提示', "产品输入信息有误,请检查产品输入信息");
		return;
	}
	
	$("#riskProInfoObjInputId").datagrid('acceptChanges');
	var riskFactorData = $("#riskProInfoObjInputId").datagrid('getRows');
	
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
	var tradeNo_Input = $("#tradeNo_Input").val();
	var tradeId_Input = $("#tradeId_Input").val();
	var tradeRiskProId = $("#tradeRiskProId").combobox('getValue');
	var tradeRiskProtObj = $("#tradeRiskProtObj").combobox('getValue');
	var params = {};
	params.riskFactorData = riskFactorData;
	params.tradeInfoId = tradeId_Input;
	params.tradeRiskProId = tradeRiskProId;
	params.tradeRiskProtObj = tradeRiskProtObj;
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeRiskPro',
		data:'param='+$.toJSON(params),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					$.messager.alert('提示', returnData.msg);
					$("#riskTotalAssets_Input").val(returnData.obj.totalAssets);
					$("#chnRiskTotalAssets_Input").val(returnData.obj.chnTotalAssets);
					initRiskProInputInfoId("2");
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


function submitWealthPro(){
	//校验产品表格输入
	if(!validateDataGrid("monProInfoObjInputId")){
		$.messager.alert('提示', "产品输入信息有误,请检查产品输入信息");
		return;
	}
	$("#monProInfoObjInputId").datagrid('acceptChanges');
	var wealthProductData = $("#monProInfoInputId").datagrid("getRows");
	var tradeWealthProId = $("#tradeWealthProId").combobox('getValue');
	
	
	if(wealthProductData!=null&&wealthProductData.length>0){
		var ProId =wealthProductData[0].proId;
		if(ProId==tradeWealthProId){
		}
		else{
			$.messager.alert('提示', "单笔交易只能购买一款财富产品！");
			return;
		}
	}
	
	var wealthFactorData = $("#monProInfoObjInputId").datagrid('getRows');
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
			/*if(factorCode=="beneficialType"){
				var factorCodeBeneficialValue=wealthFactorData[i].inputValue;
				if(factorCodeBeneficialValue==null||factorCodeBeneficialValue==undefined||factorCodeBeneficialValue==""){
					$.messager.alert('提示', "该产品信息含有受益权类型必录项，请输入该产品的受益权类型");
					return;
				}
			}*/
		}
		
	}
	
	wealthFactorData = $.toJSON(wealthFactorData);
	var tradeNo_Input = $("#tradeNo_Input").val();
	var tradeId_Input = $("#tradeId_Input").val();
	
	var param = {};
	param.wealthFactorData = wealthFactorData;
	param.tradeWealthProId = tradeWealthProId;
	param.tradeId = tradeId_Input;
	
	
	$.ajax({
		type:'post',
		url:'trade/saveTradeWealthPro',
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					$.messager.alert('提示', returnData.msg);
					$("#monTotalAssets_Input").val(returnData.obj.totalAssets);
					$("#chnMonTotalAssets_Input").val(returnData.obj.chnTotalAssets);
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

/*
 * 提交复核
 */
function submitTradeInput(){
	if(!$("#tradeInfoForm_input").form("validate")){
		$.messager.alert('提示', "交易基本信息输入有误，请检查交易基本信息");
		return;
	}
	if(!validateDataGrid("monProInfoObjInputId")){
		$.messager.alert('提示', "产品录入要素不完整！请补全产品必录要素！");
		return;
	}
	var tradeInfoId = $("#tradeId_Input").val();
	//首先判断客户是否上传身份证复印件
	$.ajax({
		type:'post',
		url :'trade/checkCustIDFile',
		data:'tradeInfoId='+ tradeInfoId,
		cache:false,
		success:function(data){
			if(data=="0"){
				$.messager.alert('提示', "您还未上传客户身份证复印件！无法继续提交复核！请在客户维护中上传客户身份证复印件！");
			}else{
				//判断是否是专业投资者 01-普通投资者 02-专业投资者
					if(investCustomerType == '02'){
						//若该专业投资者为准客户 则需要校验该客户是否上传资产证明
						if(custLevel == "02"){
							if(!verifyInvestCustInfo()) {   //校验专业投资者是否上传资产证明
								return;
								}
						}
						var tradeId_Input = $("#tradeId_Input").val();
						var tradeType_Input = $("#tradeType_Input").combobox("getValue");
						$.ajax({
							type:'post',
							url:'trade/saveTradeInput',
							data:'tradeId='+tradeId_Input+'&tradeType='+tradeType_Input+'&tradeOperationNode=01',
							cache:false,
							success:function(returnData){
								try {
									if(returnData.success=="true"){
										$.messager.alert('提示', returnData.msg);
										$("#tradeInfoInput").dialog('close');
										$("#tradeInputListId").datagrid('reload');
									}else{
										$.messager.alert('提示', returnData.msg);
									}
								} catch (e) {
									$.messager.alert('提示', e);
								}
							}
						
						});
					}else{
						//判断基金风险等级与客户风险等级是否匹配
						$.ajax({
							type:'post',
							url :'trade/checkRiskLevel',
							data:'tradeInfoId='+ tradeInfoId,
							cache:false,
							success:function(data){
								if(data.success==false){
									$.messager.alert('提示', '该客户风险等级不适配，请重新填写问卷或升级为专业投资者!');
									return;
								}else{
									//判断是否上传签约视频
									$.ajax({
										type:'post',
										url :'trade/checkUpFile',
										data:'tradeInfoId='+ tradeInfoId,
										cache:false,
										success:function(data){
											if(data=="0"){
												$.messager.alert('提示', "该客户为普通投资者，请上传签约视频！");
											}else{
												var tradeId_Input = $("#tradeId_Input").val();
												var tradeType_Input = $("#tradeType_Input").combobox("getValue");
												$.ajax({
													type:'post',
													url:'trade/saveTradeInput',
													data:'tradeId='+tradeId_Input+'&tradeType='+tradeType_Input+'&tradeOperationNode=01',
													cache:false,
													success:function(returnData){
														try {
															if(returnData.success=="true"){
																$.messager.alert('提示', returnData.msg);
																$("#tradeInfoInput").dialog('close');
																$("#tradeInputListId").datagrid('reload');
															}else{
																$.messager.alert('提示', returnData.msg);
															}
														} catch (e) {
															$.messager.alert('提示', e);
														}
													}
												
												});
											}
										}
									});
								}
							}
						})
					}
			}
		}
	});
	
}


function delRoleInfo(){
	var deleteRows = $("#roleInfoInputId").datagrid('getSelected');
	if(deleteRows==null||deleteRows==""||deleteRows==undefined){
		$.messager.alert('提示', "请选择需要删除的角色");
		return;
	}
	var tradeCustRoleInfoId = deleteRows.tradeCustRoleInfoId;
	if(tradeCustRoleInfoId==null||tradeCustRoleInfoId==""||tradeCustRoleInfoId==undefined){
		$('#roleInfoInputId').datagrid('deleteRow',roleInfoTableDeleteRowIndex);
		roleInfoTableDeleteRowIndex = null;
		return;
	}
	if(roleInfoTableEditRowIndex!=null){
		if(!$('#roleInfoInputId').datagrid('validateRow',roleInfoTableEditRowIndex)){
			return;
		}
	}
	$('#roleInfoInputId').datagrid('acceptChanges');
	/*if(!confirm("是否确认删除选中的角色信息?")){
		return;
	}*/
	$.messager.confirm('提示',"是否确认删除选中的角色信息?",function(r){
		if(r){
			var tradeId_Input = $("#tradeId_Input").val();
			var tradeCustInfoId = $("#roleInfoInputId").datagrid('getSelected').customerCode;
			var roleType = $("#roleInfoInputId").datagrid('getSelected').role;
			
			var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_input").serialize());
			
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
							//重新加载角色信息
							$("#roleInfoInputId").datagrid('reload');
							//重新加载账户信息
							//重新加载账户信息
							$("#tradeBankInfoInputId").datagrid('reload');
							//重新加载产品信息
							$("#riskProInputInfoId").datagrid('reload');
							$("#riskProInfoObjInputId").datagrid('reload');
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
	});
	
}

function delRiskProInfo(){
	var selected = $('#riskProInputInfoId').datagrid('getSelected');
	if(selected==null||selected==""||selected==undefined){
		$.messager.alert('提示', "请先选择需要删除的产品");
		return;
	}
	if(!confirm("是否确认删除选中的保险产品信息?")){
		return;
	}
	var tradeId_Input =  $("#tradeId_Input").val();
	var tradeBaseInfo = formDataToJsonStr($("#tradeInfoForm_input").serialize());
	var riskTotalAssets_Input = $("#riskTotalAssets_Input").val();
	//var selected = $('#riskProInputInfoId').datagrid('getSelected');
	var tradeRiskProductId = selected.proId;
	
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
					$("#riskProInputInfoId").datagrid('reload');
					$("#riskProInfoObjInputId").datagrid('reload');
					//$("#riskTotalAssets_Input").val(returnData.obj);
					$("#riskTotalAssets_Input").val(returnData.obj.totalAssets);
					$("#chnRiskTotalAssets_Input").val(returnData.obj.chnTotalAssets);
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
	var selected = $('#monProInfoInputId').datagrid('getSelected');
	if(selected==null||selected==""||selected==undefined){
		$.messager.alert('提示', "请选择需要删除的产品");
		return;
	}
	/*if(!confirm("是否确认删除选中的财富产品信息?")){
		return;
	}*/
	$.messager.confirm('提示',"是否确认删除选中的财富产品信息?",function(r){
		if(r){
			var tradeId_Input =  $("#tradeId_Input").val();
			var monTotalAssets_Input = $("#monTotalAssets_Input").val();
			
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
							$("#monProInfoInputId").datagrid('reload');
							$("#monProInfoObjInputId").datagrid('reload');
							$("#monTotalAssets_Input").val(returnData.totalAssets);
							$("#chnMonTotalAssets_Input").val(returnData.chnTotalAssets);
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

function initTradeCheckConclusion(){
	var tradeId_Input =  $("#tradeId_Input").val();
	$.ajax({
		type:'post',
		url:'trade/queryTradeConclusion',
		data:'tradeId='+tradeId_Input+'&tradeOperationNode=02',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$("#tradeCheckConclusion_Input").combobox('setValue',returnData.tradeConclusion);
					$("#tradeCheckConclusion_Input").combobox('setText',returnData.tradeConclusionName);
					$("#tradeCheckRemark_Input").val(returnData.tradeRemark);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function initTradeAuditConclusion(){
	var tradeId_Input =  $("#tradeId_Input").val();
	$.ajax({
		type:'post',
		url:'trade/queryTradeConclusion',
		data:'tradeId='+tradeId_Input+'&tradeOperationNode=03',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$("#tradeAuditConclusion_Input").combobox('setValue',returnData.tradeConclusion);
					$("#tradeAuditConclusion_Input").combobox('setText',returnData.tradeConclusionName);
					$("#tradeAuditRemark_Input").val(returnData.tradeRemark);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function uploadTradeInputAttachInfo(){
	var param = {};
	param.businessNo = $("#tradeId_Input").val();
	param.businessType = "04";
	param.operate = loadFileType;
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}

function uploadTradeVideo(){
	var param = {};
	param.businessNo = $("#tradeId_Input").val();
	param.businessType = "13";
	param.operate = loadFileType;
	addFileWindow('视频上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}

function delTradeInputAttachInfo(){
	var param = {};
	param.businessNo = $("#tradeId_Input").val();
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

function tradeInputBack(){
	$("#tradeInfoInput").window("destroy");
}

function submitTradeCustomerInfo(){
	$("#submitTradeCustomerInfoButton").linkbutton("disable");
	submitTradeCusInfo();
}

/**
 * 校验专业投资者是否上传资产证明
 * */
function verifyInvestCustInfo(){
	/****************************验证专业投资者是否上传资产证明材料*********************/
	var successFlag = true;
		$.ajax({
			type : 'post',
			async: false, // 设置为同步方法
			url : 'modifyCustomer/checkInvestCustInfo',
			data : 'custBaseInfoId='+custBaseInfoId,
			cache : false,
			success : function(returnData){
				if(returnData == false){
					$.messager.alert("提醒","请上传有效的资产证明或收入证明!",'info');
					successFlag = false;
				}
			}
		});
	return successFlag;
}