var json={"total":3,"rows":[{'tradeCode':'1'},{'tradeCode':'2'}]}
jQuery(function($) {
	initTradeAuditCommonListId();
	initAllCombobox();
	//tradeAuditCommonListId.datagrid("loadData",json);
});


function initAllCombobox(){
	$("#tradeAuditProduct").combobox({
		url:contextPath+'/codeQuery/productQueryAll',
		valueField:'code',
		textField:'codeName'
	});
	// 分公司：代码-名称
	$("#tradeAudit_tradeComId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	 //财富顾问
	$("#tradeAudit_agentId").combobox({
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var comId = $("#tradeAudit_tradeComId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath+'/codeQuery/agentQuery';
			}else{
				var url = contextPath + '/codeQuery/defAgentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload",url);
		}
	});
}

var tradeAuditCommonListId;
function initTradeAuditCommonListId()
{	var  selectIndex = -1;
	tradeAuditCommonListId=$("#tradeAuditCommonListId").datagrid({
		//title:'交易信息列表',
		url:'trade/tradeInputQueryTradeList?tradeStaus=03',
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		 columns:[[  
		           {field:'ck',checkbox:true},    
		           {field:'tradeInfoId',title:'交易号码',width:50,hidden:true},
		           {field:'tradeNo',title:'交易号码',width:50},    
		           {field:'tradeType',title:'交易类型',width:50,hidden:true},
		           {field:'tradeAppant',title:'认购人',width:50}, 
		           {field:'tradeInfoNo',title:'合同号',width:100}, 
		           {field:'productName',title:'产品名称',width:100}, 
		           {field:'foundDate',title:'成立日/开放日',width:100}, 
		          /* {field:'tradeDate',title:'交易日期',width:50},
		           {field:'tradeInputDate',title:'录入日期',width:50},*/
		           {field:'currency',title:'币种',width:50,hidden:true},    
		           {field:'agentId',title:'财富顾问Id',width:50,hidden:true},
		           {field:'agentName',title:'财富顾问',width:50},
		           {field:'companyId',title:'所属机构',width:50,hidden:true},    
		           {field:'storeId',title:'所属机构',width:50,hidden:true},
		           {field:'tradeComId',title:'交易机构Id',width:50,hidden:true},  
		           {field:'tradeComName',title:'交易机构',width:50},  
		         /*  {field:'tradeStoreId',title:'交易网点Id',width:50,hidden:true},
		           {field:'tradeStoreName',title:'交易网点',width:50,hidden:true}, */
		           {field:'tradeTotalAssets',title:'交易总额',width:50},
		           {field:'chnTradeTotalAssets',title:'交易总额',hidden:true},
		           {field:'tradeStaus',title:'交易状态',width:50,hidden:true},
		           {field:'tradeStausName',title:'交易状态',width:50},
		           {field:'custAccInfoId',title:'账户信息流水号',width:50,hidden:true},
		           {field:'custAddressInfoId',title:'地址信息流水号',width:50,hidden:true},
		           {field:'isBack',title:'退回标志',width:50,hidden:true}
		       ]],
		       onClickCell:function (rowIndex, field, value) {
					/*if(field=="tradeNo"){
						turnPageAuditListUrl(rowIndex,"query");	
					}*/
		       },
			   	/*rowStyler:function(index,row){
					if (row.isBack!=null && row.isBack=='Y'){
						return 'background-color:pink;color:blue;font-weight:bold;';
					}
				},*/
				onClickRow: function (rowIndex, rowData) {
		            if(selectIndex==rowIndex){
		            	//第一次单击选中,第二次单击取消选中
		            	$(this).datagrid('unselectRow', rowIndex);
		            	selectIndex=-1;
		            }else{
		            	selectIndex = rowIndex;
		            }
				}
	});
}

function tradeAuditDetail(rowIndex,type){
	var tradeAuditCommonList = $("#tradeAuditCommonListId").datagrid('getSelected');
	$.ajax({
		type:'post',
		url:'trade/applyTradeAuditInfo',
		data:'tradeInfoId='+tradeAuditCommonList.tradeInfoId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//tradeInputList.datagrid('loadData',returnData.obj);	
					//$("#tradeId_Input").val(returnData.tradeId_Input);
					turnPageAuditListUrl(rowIndex,type);
					//$.messager.alert('提示', returnData.msg);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function turnPageAuditListUrl(rowIndex,type){
	var tradeAuditListData = $("#tradeAuditCommonListId").datagrid('getRows');
	var tradeAuditListCheckData = $("#tradeAuditCommonListId").datagrid('getSelected');
	$('<div id="tradeInfoAudit"/>').dialog({
			href : contextPath + "/trade/tradeAuditInfo",
			modal : true,
			fit:true,
			title : '交易终审明细',
			inline : false,
			minimizable : false,
			onLoad:function(){
					var totalAssets = 0;
					var chnTradeTotalAssets = 0;
					var custAccInfoId = null;
					var custAddressInfoId = null;
					var tradeInfoId = null;
					if(type=="update"){
						$('#tradeInfoForm_Audit').form('load', tradeAuditListCheckData);
						totalAssets = tradeAuditListCheckData.tradeTotalAssets;
						chnTradeTotalAssets = tradeAuditListCheckData.chnTradeTotalAssets;
						custAccInfoId = tradeAuditListCheckData.custAccInfoId;
						custAddressInfoId = tradeAuditListCheckData.custAddressInfoId;
						tradeInfoId = tradeAuditListCheckData.tradeInfoId;
					}else if(type=="query"){
						totalAssets = tradeAuditListData[rowIndex].tradeTotalAssets;
						chnTradeTotalAssets = tradeAuditListData[rowIndex].chnTradeTotalAssets;
						custAccInfoId = tradeAuditListData[rowIndex].custAccInfoId;
						custAddressInfoId = tradeAuditListData[rowIndex].custAddressInfoId;
						tradeInfoId = tradeAuditListData[rowIndex].tradeInfoId;
						$('#tradeInfoForm_Audit').form('load', tradeAuditListData[rowIndex]);
						$("#submitTradeAuditButton").hide();
					}
					
					$("#submitAuditTradeInfoButton").hide();
					$("#submitAuditTradeCusInfoButton").hide();
					$("#submitAuditTradeRoleButton").hide();
					$("#submitAuditRiskProButton").hide();
					$("#submitAuditWealthProButton").hide();
					$("#submitTradeAuditBankInfoButton").hide();
					$("#submitTradeAuditAddressInfoButton").hide();
					loadFileType = "queryFile";
					
					
					initCustomInfoId();
					initRoleInfoId();
					/*if(custAccInfoId!=null && custAccInfoId!=""){
						initTradeBankInfoAuditId(custAccInfoId,"search");
					}*/
					if(tradeInfoId!=null&&tradeInfoId!=""&&tradeInfoId!=undefined){
						initTradeBankInfoAuditId(tradeInfoId,"query");
					}
					
					var tradeType_Input = $("#tradeType_Audit_In").combobox('getValue');
					if(tradeType_Input=="2")
					{
						$("#roleInfoAuditPanelId").show();
						$("#riskProAuditInfoPanelId").show();
						$("#monProInfoAuditPanelId").hide();
						//初始化保险产品信息
						initRiskProAuditInfoId(tradeType_Input);
						$("#riskTotalAssets_Audit_In").val(totalAssets);
						$("#chnRiskTotalAssets_Audit_In").val(chnTradeTotalAssets);
						
						$("#addressInfoAuditPanelId").hide();
					}else if(tradeType_Input=="1"){
						$("#roleInfoAuditPanelId").hide();
						$("#riskProAuditInfoPanelId").hide();
						$("#monProInfoAuditPanelId").show();
						//初始化财富产品信息
						initMonryProductInfoId(tradeType_Input);
						$("#monTotalAssets_Audit_In").val(totalAssets);
						$("#chnMonTotalAssets_Audit_In").val(chnTradeTotalAssets);
						/*if(custAddressInfoId!=null && custAddressInfoId!=""){
							initTradeAddressInfoAuditId(custAddressInfoId,"search");
						}*/
						
						if(tradeInfoId!=null&&tradeInfoId!=""&&tradeInfoId!=undefined){
							initTradeAddressInfoAuditId(tradeInfoId,"query");
						}
					}
					initTradeCheckConclusion();
					$("#tradeRiskProtObj_Audit_In").combobox({
						url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Audit_In").val(),
						valueField:'custID',
						textField:'custName'
					});
					
					$("#customInfoAuditId").parent().prev("div.datagrid-toolbar").hide();
					$("#roleInfoAuditId").parent().prev("div.datagrid-toolbar").hide();
					$("#riskProAuditInfoId").parent().prev("div.datagrid-toolbar").hide();
					$("#monProInfoAuditId").parent().prev("div.datagrid-toolbar").hide();
					$("#tradeType_Audit_In").combobox('disable');
			},
			onClose : function() {
				$(this).dialog('destroy');
			}
	});
}

function clearTradeAuditInfo(){
	$('#tradeAuditCommonListQueryConditionForm').form('clear');
}

function queryTradeAuditInfoList(){
	tradeAuditCommonListId.datagrid('load',{queryParam:formDataToJsonStr($("#tradeAuditCommonListQueryConditionForm").serialize())});	
}