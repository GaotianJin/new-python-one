var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#detailGuojinCommissionId_month").val();
	initDetailGuojinCommissionIdTable();
	});

/**
 * 国金交易佣金列表
 */
var detailGuojinCommissionIdTable;
function initDetailGuojinCommissionIdTable(){
	var  selectIndex = -1;
	detailGuojinCommissionIdTable=$('#detailGuojinCommissionIdTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/queryGuojinCommissionIdList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slyGuojinCommissionId',	
		    	 title:'国金交易佣金流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slyGuojinCommissionId;
		       }
		     },
		     {
		    	 field:'slySalaryId',	
		    	 title:'工资主表流水号',
		    	 hidden:true,
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.slySalaryId;
		       }
		     },
		     {
		    	 field:'month',	
		    	 title:'月份',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.month;
		       }
		     },
		     {
		    	 field:'channel',	
		    	 title:'结算渠道',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.channel;
		       }
		     },
		     {
		    	 field:'workNumber',	
		    	 title:'工号',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.workNumber;
		       }
		     },
		     {
		    	 field:'custRelation',	
		    	 title:'客户关系',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.custRelation;
		       }
		     },
		     {
		    	 field:'custName',	
		    	 title:'客户姓名',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.custName;
		       }
		     },
		     {
		    	 field:'account',	
		    	 title:'资金账户',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.account;
		       }
		     },
		     {
		    	 field:'establishDate',	
		    	 title:'开户日期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.establishDate;
		       }
		     },
		     {
		    	 field:'marketValueMax',	
		    	 title:'市值峰值',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.marketValueMax;
		       }
		     },
		     {
		    	 field:'personalValueMax',	
		    	 title:'资产规模峰值',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.personalValueMax;
		       }
		     },
		     {
		    	 field:'tradeAmount',	
		    	 title:'成交金额',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.tradeAmount;
		       }
		     },
		     {
		    	 field:'distributeNetValue',	
		    	 title:'分配净佣',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.distributeNetValue;
		       }
		     },
		     {
		    	 field:'remarks',	
		    	 title:'备注',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remarks;
		       }
		     }
		     
		    ]],
				onLoadSuccess : function() {
					$('#detailGuojinCommissionIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
 * 查询
 */
/*function queryGuojinCommissionIdList() {
	detailGuojinCommissionIdTable.datagrid('options').url = "sales/queryGuojinCommissionIdList?param="+month;
	var queryParam = $("#detailGuojinCommissionIdForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailGuojinCommissionIdTable.datagrid('load',{queryParam:queryParam});	
}*/

/**
 * 清空
 */
/*function clearGuojinCommissionIdCondition(){
	$("#detailGuojinCommissionIdForm").form("clear");
}*/