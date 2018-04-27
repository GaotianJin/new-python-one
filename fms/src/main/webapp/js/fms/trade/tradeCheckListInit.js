var json={"total":3,"rows":[{'tradeCode':'1'},{'tradeCode':'2'}]}
jQuery(function($) {
	initTradeCheckCommonList();
	initAllCombobox();
	//tradeCheckCommonList.datagrid("loadData",json);
});

function initAllCombobox(){
	$("#tradeCheckProduct").combobox({
		url:contextPath+'/codeQuery/productQueryAll',
		valueField:'code',
		textField:'codeName'
	});
	// 分公司：代码-名称
	$("#tradeCheck_tradeComId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	 //财富顾问
	$("#tradeCheck_agentId").combobox({
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var comId = $("#tradeCheck_tradeComId").combobox("getValue");
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

var tradeCheckCommonList;
function initTradeCheckCommonList()
{	var  selectIndex = -1;
	tradeCheckCommonList=$("#tradeCheckCommonListId").datagrid({
		url:'trade/tradeInputQueryTradeList?tradeStaus=02',
		//title:'交易信息列表',
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		//height:300,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		pageSize:10,
		pageList:[5,10,15,20],
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
		          /* {field:'tradeStoreId',title:'交易网点Id',width:50,hidden:true},
		           {field:'tradeStoreName',title:'交易网点',width:50,hidden:true}, */
		           {field:'tradeTotalAssets',title:'交易总额',width:50},
		           {field:'chnTradeTotalAssets',title:'交易总额',hidden:true},
		           {field:'tradeStaus',title:'交易状态',width:50,hidden:true},
		           {field:'tradeStausName',title:'交易状态',width:50},
		           {field:'custAccInfoId',title:'账户信息流水号',width:50,hidden:true},
		           {field:'custAddressInfoId',title:'地址信息流水号',width:50,hidden:true},
		           {field:'isBack',title:'退回标志',width:50,hidden:true}
		       ]],
       /*onClickCell:function (rowIndex, field, value) {
			if(field=="tradeNo"){
				turnPageCheckListUrl(rowIndex,"query");	
			}
	   	},*/
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


function tradeCheckDetail(rowIndex,type){
	var tradeCheckCommonList = $("#tradeCheckCommonListId").datagrid('getSelected');
	$.ajax({
		type:'post',
		url:'trade/applyTradeCheckInfo',
		data:'tradeInfoId='+tradeCheckCommonList.tradeInfoId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					//tradeInputList.datagrid('loadData',returnData.obj);	
					//$("#tradeId_Input").val(returnData.tradeId_Input);
					turnPageCheckListUrl(rowIndex,type);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function turnPageCheckListUrl(rowIndex,type){
	var tradeCheckListData = $("#tradeCheckCommonListId").datagrid('getRows');
	var tradeCheckListCheckData = $("#tradeCheckCommonListId").datagrid('getSelected');
	$('<div id="tradeInfoCheck"/>').dialog({
			href : contextPath + "/trade/tradeCheckInfo",
			modal : true,
			fit:true,
			title : '交易复核明细',
			inline : false,
			minimizable : false,
			onLoad:function(){
					var totalAssets = 0;
					var chnTradeTotalAssets = 0;
					var custAccInfoId = null;
					var custAddressInfoId = null;
					var tradeInfoId = tradeCheckListCheckData.tradeInfoId;
					
					if(type=="update"){
						$('#tradeInfoForm_check').form('load', tradeCheckListCheckData);
						totalAssets = tradeCheckListCheckData.tradeTotalAssets;
						chnTradeTotalAssets = tradeCheckListCheckData.chnTradeTotalAssets;
						custAccInfoId = tradeCheckListCheckData.custAccInfoId;
						custAddressInfoId = tradeCheckListCheckData.custAddressInfoId;
					}else if(type=="query"){
						totalAssets = tradeCheckListData[rowIndex].tradeTotalAssets;
						chnTradeTotalAssets = tradeCheckListData[rowIndex].chnTradeTotalAssets;
						custAccInfoId = tradeCheckListData[rowIndex].custAccInfoId;
						custAddressInfoId = tradeCheckListData[rowIndex].custAddressInfoId;
						$('#tradeInfoForm_check').form('load', tradeCheckListData[rowIndex]);
						$("#submitCheckTradeInfoButton").hide();
						$("#submitCheckTradeCusInfoButton").hide();
						$("#submitCheckTradeRoleButton").hide();
						$("#submitCheckRiskProButton").hide();
						$("#submitCheckWealthProButton").hide();
						$("#submitTradeCheckButton").hide();
						$("#submitTradeCheckBankInfoButton").hide();
						
						loadFileType = "queryFile";
					}
					var isBack = tradeCheckListCheckData.isBack;
					if(isBack=="Y"){
						$("#tradeCheckAuditConclusionDiv").show();
						initTradeAuditConclusion();
					}else{
						$("#tradeCheckAuditConclusionDiv").hide();
					}
					initCustomInfoId();
					initRoleInfoId();
					initTradeCheckConclusion();
					/*if(custAccInfoId!=null && custAccInfoId!=""){
						initTradeBankInfoCheckId(custAccInfoId,"search");
					}*/
					if(tradeInfoId!=null && tradeInfoId!=""){
						initTradeBankInfoCheckId(tradeInfoId,"query");
					}
					var tradeType_Input = $("#tradeType_Check_In").combobox('getValue');
					if(tradeType_Input=="2")
					{
						$("#tradeInfoNo_Input").validatebox({required:true,validType:'length[0,40]'});
						$("#roleInfoCheckPanelId").show();
						$("#riskProCheckInfoPanelId").show();
						$("#monProInfoCheckPanelId").hide();
						//初始化保险产品信息
						initRiskProCheckInfoId(tradeType_Input);
						$("#riskTotalAssets_Check_In").val(totalAssets);
						$("#chnRiskTotalAssets_Check_In").val(chnTradeTotalAssets);
						$("#addressInfoCheckPanelId").hide();
						
					}else if(tradeType_Input=="1"){
						$("#tradeInfoNo_Input").validatebox({required:false,validType:'length[0,40]'});
						$("#roleInfoCheckPanelId").hide();
						$("#riskProCheckInfoPanelId").hide();
						$("#monProInfoCheckPanelId").show();
						//初始化财富产品信息
						initMonryProductInfoId(tradeType_Input);
						$("#monTotalAssets_Check_In").val(totalAssets);
						$("#chnMonTotalAssets_Check_In").val(chnTradeTotalAssets);
						
						/*if(custAddressInfoId!=null && custAddressInfoId!=""){
							initTradeAddressInfoCheckId(custAddressInfoId,"search");
						}*/
						if(tradeInfoId!=null && tradeInfoId!=""){
							initTradeAddressInfoCheckId(tradeInfoId,"query");
						}
					}
					$("#tradeRiskProtObj_Check_In").combobox({
						url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Check_In").val(),
						valueField:'custID',
						textField:'custName'
					});
					
					if(type=="query"){
						$("#customInfoCheckId").parent().prev("div.datagrid-toolbar").hide();
						$("#roleInfoCheckId").parent().prev("div.datagrid-toolbar").hide();
						$("#riskProCheckInfoId").parent().prev("div.datagrid-toolbar").hide();
						$("#monProInfoCheckId").parent().prev("div.datagrid-toolbar").hide();
					}
					$("#tradeType_Check_In").combobox('disable');
			},
			onClose : function() {
				$(this).dialog('destroy');
			}
	});
}

function clearTradeCheckInfo(){
	$('#tradeCheckCommonListQueryConditionForm').form('clear');
}

function queryTradeCheckInfoList(){
	tradeCheckCommonList.datagrid('load',{queryParam:formDataToJsonStr($("#tradeCheckCommonListQueryConditionForm").serialize())});	
}