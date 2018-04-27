var jsonData = [{"tradeNo":"20150150600009","custName":"张三","productName":'潍坊三河特定多个客户专项资产管理计划(第一期)',"expectOpenDay":"2015-03-01",
  "subscriptionMoney":"3000000.00",
	"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
	"distributionInterestMoney":"300000.00","distributionPrincipalMoney":"5000.90","principalRate":"1.089"},
	{"tradeNo":"20150150600010","custName":"李四","productName":'潍坊三河特定多个客户专项资产管理计划(第一期)',"expectOpenDay":"2015-05-01",
		"subscriptionMoney":"5000000.00",
		"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
		"distributionInterestMoney":"300000.00","distributionPrincipalMoney":"6009.70","principalRate":"1.089"},
	{"tradeNo":"20150150600011","custName":"王五","productName":'潍坊三河特定多个客户专项资产管理计划(第一期)',"expectOpenDay":"2015-04-01",
		"subscriptionMoney":"8000000.00",
	"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
		"distributionInterestMoney":"300000.00","distributionPrincipalMoney":"8000.98","principalRate":"1.089"}];


jQuery(function($) {
	initAllCombobox();
	initIncomeDisDetailTable();
	//incomeDisDetailTable.datagrid("loadData",jsonData);
});

var incomeDisDetailTable;
function initIncomeDisDetailTable(){
	var  selectIndex = -1;
	incomeDisDetailTable=$('#incomeDisDetailTable').datagrid({
		 title : '收益分配明细列表',
			fitColumns:true,
			rownumbers:true,
			pagination:true,
			checkOnSelect:true,
			selectOnCheck:true,
			singleSelect:true,
			nowrap: false,
			pageSize:10,
			pageList:[5,10,15,20,25,30],
			 columns:[[  
			           {field:'ck',checkbox:true},   
			           {field:'tradeNo',title:'交易号码',width:90},
			           {field:'custName',title:'客户名称',width:70,sortable:true},
			           {field:'productName',title:'产品名称',width:180,nowrap: false},
			           {field:'foundDate',title:'产品成立日',width:70},
			           {field:'actuSubscriptionAmount',title:'认购金额',width:70},
			           {field:'distributeInterest',title:'分配利息金额',width:90},
			           {field:'distributePrincipal',title:'本金分配金额',width:90}, 
			           {field:'distributePrincipalRate',title:'本金分配比例(%)',width:90},
			           {field:'tradeDistributeTotal',title:'分配总金额',width:90},
			           {field:'distributeDate',title:'分配日期',width:70},    
			           {field:'distributeStartDate',title:'分配起期',width:70},
			           {field:'distributeEndDate',title:'分配止期',width:70},
			           {field:'agencyDisPrincipal',title:'产品方分配本金金额',width:70},
			           {field:'agencyDisInterest',title:'产品方分配利息金额',width:70}/*,    
			           {field:'confirmDisPrincipal',title:'确认分配本金金额',width:70},
			           {field:'confirmDisInterest',title:'确认分配利息金额',width:70}*/
			       ]],
			   	onLoadSuccess : function() {
			   		$("#incomeDisDetailTable").datagrid('clearSelections');
			   	},
			   	onSortColumn : function(){
			   		queryProductIncomeDistributeDetailList();
				},
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

/**
 * 
 * 初始化所有的下拉框
 */
function initAllCombobox(){
	//初始化产品方下拉框
	$("#incomeDisDetail_agencyComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName',
		onSelect : function(record){
			$("#incomeDisDetail_productId").combobox('reset');
			var reloadUrl = contextPath + '/codeQuery/queryAllFixedIncomeProduct?agencyComId='+record.code;
			$("#incomeDisDetail_productId").combobox('reload',reloadUrl);
		}
	});
	
	//初始化产品下拉框
	$("#incomeDisDetail_productId").combobox({
		//url : contextPath + '/codeQuery/queryAllFixedIncomeProduct?agencyComId='+"",
		valueField : 'code',
		textField : 'codeName',
		onShowPanel : function(){
			var agencyComId = $("#incomeDisDetail_agencyComId").combobox("getValue");
			if(agencyComId==null||agencyComId==""||agencyComId==undefined){
				var reloadUrl = contextPath + '/codeQuery/queryAllFixedIncomeProduct?agencyComId='+"";
				$("#incomeDisDetail_productId").combobox('reload',reloadUrl);
			}
		}
	});
}


function queryProductIncomeDistributeDetailList() {
	incomeDisDetailTable.datagrid('options').url = "incomeDis/queryProductIncomeDistibuteDetailList";
	var queryParam = $("#IncomeDisDetailQueryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	incomeDisDetailTable.datagrid('load',{queryParam:queryParam});	
}

function clearForm(){
	$("#IncomeDisDetailQueryConditionForm").form("clear");
}