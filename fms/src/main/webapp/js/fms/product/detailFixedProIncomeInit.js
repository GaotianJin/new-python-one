var productId=null;
var expectFeeRate=null;
var distributeDate=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	productId = $("#detailFixedProIncome_productId").val();
	expectFeeRate = $("#detailFixedProIncome_expectFeeRate").val();
	distributeDate = $("#detailFixedProIncome_distributeDate").val();
	initDetailFixedProIncomeTable();
	});

/**
 * 固收产品投后详单列表
 */
var detailFixedProIncomeTable;
function initDetailFixedProIncomeTable(){
	var  selectIndex = -1;
	detailFixedProIncomeTable=$('#detailFixedProIncomeTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/product/queryDetailFixedProIncomeList',
		queryParams : {param:$.toJSON({productId:productId,expectFeeRate:expectFeeRate,distributeDate:distributeDate})}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'productName',	
		    	 title:'产品名称',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.productName;
		       }
		     },
		     {
		    	 field:'foundDate',	
		    	 title:'成立日期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.foundDate;
		       }
		     },
		     {
		    	 field:'endDate',	
		    	 title:'到期时间',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.endDate;
		       }
		     },
		     {
		    	 field:'chnName',	
		    	 title:'客户姓名',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.chnName;
		       }
		     },
		     {
		    	 field:'subscriptionFee',	
		    	 title:'认购规模',
		    	 width:60,
		    	 formatter : function(value, row, index) {
		    	 return row.subscriptionFee;
		       }
		     },
		     {
		    	 field:'expectFeeRate',	
		    	 title:'收益率',
		    	 width:90,
		    	 formatter : function(value, row, index) {
		    	 return row.expectFeeRate;
		       }
		     },
		     {
		    	 field:'lastPayDate',	
		    	 title:'上一次付息时间',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lastPayDate;
		       }
		     },
		     {
		    	 field:'lastPayAmount',	
		    	 title:'付息金额',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lastPayAmount;
		       }
		     },
		     {
		    	 field:'lastPayPeriod',	
		    	 title:'付息久期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lastPayPeriod;
		       }
		     },
		     {
		    	 field:'nextPayDate',	
		    	 title:'最近一次付息时间',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.nextPayDate;
		       }
		     },
		     {
		    	 field:'nextPayAmount',	
		    	 title:'付息金额',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.nextPayAmount;
		       }
		     },
		     {
		    	 field:'nextPayPeriod',	
		    	 title:'付息久期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.nextPayPeriod;
		       }
		     },
		     {
		    	 field:'remainSubscription',	
		    	 title:'剩余本金规模',
		    	 width:60,
		    	 formatter : function(value, row, index) {
		    	 return row.remainSubscription;
		       }
		     },
		     {
		    	 field:'lastRemainingMoney',	
		    	 title:'分配后本金规模',
		    	 width:60,
		    	 formatter : function(value, row, index) {
		    	 return row.lastRemainingMoney;
		       }
		     }]],
				onLoadSuccess : function() {
					$('#detailFixedProIncomeTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
/*
*//**
 * 查询
 *//*
function queryBaseSalaryList() {
	detailBaseSalaryTable.datagrid('options').url = "sales/queryBaseSalaryList?param="+month;
	var queryParam = $("#detailBaseSalaryForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailBaseSalaryTable.datagrid('load',{queryParam:queryParam});	
}

*//**
 * 清空
 *//*
function clearBaseSalaryCondition(){
	$("#detailBaseSalaryForm").form("clear");
}*/

/**
 * 返回
 */
function back(){
	$('#addWindow').window('close');
}
