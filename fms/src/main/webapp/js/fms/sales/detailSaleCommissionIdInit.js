var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#detailSaleCommissionId_month").val();
	initDetailSaleCommissionId();
	});

/**
 * 销售佣金列表
 */
var detailSaleCommissionIdTable;
function initDetailSaleCommissionId(){
	var  selectIndex = -1;
	detailSaleCommissionIdTable=$('#detailSaleCommissionIdTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/querySaleCommissionIdList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slySaleCommissionId',	
		    	 title:'销售佣金流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slySaleCommissionId;
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
		    	 field:'orgnizition',	
		    	 title:'机构',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.orgnizition;
		       }
		     },
		     {
		    	 field:'productName',	
		    	 title:'产品名称',
		    	 width:80,
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
		    	 field:'workNumber',	
		    	 title:'工号',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.workNumber;
		       }
		     },
		     {
		    	 field:'agentName',	
		    	 title:'财富顾问',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.agentName;
		       }
		     },
		     {
		    	 field:'departmentLeader',	
		    	 title:'部门负责人',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.departmentLeader;
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
		    	 field:'custType',	
		    	 title:'客户类型',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.custType;
		       }
		     },
		     {
		    	 field:'amount',	
		    	 title:'规模',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.amount;
		       }
		     },
		     {
		    	 field:'closePeriouds',	
		    	 title:'久期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.closePeriouds;
		       }
		     },
		     {
		    	 field:'amountStandard',	
		    	 title:'标规（百万）',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.amountStandard;
		       }
		     },
		     {
		    	 field:'commissionRate',	
		    	 title:'佣金比率（%）',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.commissionRate;
		       }
		     },
		     {
		    	 field:'commissionAmount',	
		    	 title:'佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.commissionAmount;
		       }
		     },
		     {
		    	 field:'managementAllownace',	
		    	 title:'管理津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.managementAllownace;
		       }
		     },
		     {
		    	 field:'serviceAllowance',	
		    	 title:'服务津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.serviceAllowance;
		       }
		     },
		     {
		    	 field:'continueCommission',	
		    	 title:'续佣',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.continueCommission;
		       }
		     },
		     {
		    	 field:'continueManagementAllowance',	
		    	 title:'续管理津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.continueManagementAllowance;
		       }
		     },
		     {
		    	 field:'floatRemarks',	
		    	 title:'浮动',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.floatRemarks;
		       }
		     },
		     {
		    	 field:'otherSalesCommission',	
		    	 title:'其它',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.otherSalesCommission;
		       }
		     },
		     {
		    	 field:'recommendName',	
		    	 title:'推荐人',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.recommendName;
		       }
		     },{
		    	 field:'addBonus',	
		    	 title:'增员奖励',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.addBonus;
		       }
		     },{
		    	 field:'mark',	
		    	 title:'备注',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.mark;
		       }
		     }]],
				onLoadSuccess : function() {
					$('#detailSaleCommissionIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
/*function querySaleCommissionIdList() {
	detailSaleCommissionIdTable.datagrid('options').url = "sales/querySaleCommissionIdList?param="+month;
	var queryParam = $("#detailSaleCommissionIdForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailSaleCommissionIdTable.datagrid('load',{queryParam:queryParam});	
}*/

/**
 * 清空
 */
/*function clearSaleCommissionIdCondition(){
	$("#detailSaleCommissionIdForm").form("clear");
}*/