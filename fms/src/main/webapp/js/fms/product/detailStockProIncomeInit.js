var productId=null;
var distributeDate=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	productId = $("#detailStockProIncome_productId").val();
	distributeDate = $("#detailStockProIncome_distributeDate").val();
	initDetailStockProIncomeTable();
	});

/**
 * 股权产品投后详单列表
 */
var detailStockProIncomeTable;
function initDetailStockProIncomeTable(){
	var  selectIndex = -1;
	detailStockProIncomeTable=$('#detailStockProIncomeTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/product/queryDetailStockProIncomeList',
		queryParams : {param:$.toJSON({productId:productId,distributeDate:distributeDate})}, // 查询条件
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
		    	 field:'lastPayDate',	
		    	 title:'上一次分配时间',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lastPayDate;
		       }
		     },
		     {
		    	 field:'lastPayAmount',	
		    	 title:'分配金额',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lastPayAmount;
		       }
		     },
		     {
		    	 field:'nextPayDate',	
		    	 title:'最近一次分配时间',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.nextPayDate;
		       }
		     },
		     {
		    	 field:'nextPayAmount',	
		    	 title:'分配金额',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.nextPayAmount;
		       }
		     }]],
				onLoadSuccess : function() {
					$('#detailStockProIncomeTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
 * 返回
 */
function back(){
	$('#addWindow').window('close');
}
