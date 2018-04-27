var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#detailsCommissionOverviewId_month").val();
	initDetailSaleCommissionId();
	});

/**
 * 销售佣金列表
 */
var detailsCommissionOverviewIdTable;
function initDetailSaleCommissionId(){
	var  selectIndex = -1;
	detailsCommissionOverviewIdTable=$('#detailsCommissionOverviewIdTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/querydetailsCommissionIdList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slyCommissionId',	
		    	 title:'佣金明细流水号',
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
		    	 field:'comName',	
		    	 title:'机构',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.comName;
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
		    	 field:'chnName',	
		    	 title:'姓名',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.chnName;
		       }
		     },
		     {
		    	 field:'saleCommission',	
		    	 title:'销售佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.saleCommission;
		       }
		     },
		     {
		    	 field:'managementAllowance',	
		    	 title:'管理津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.managementAllowance;
		       }
		     },
		     {
		    	 field:'addBonus',	
		    	 title:'增员奖励',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.addBonus;
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
		    	 field:'floatIncome',	
		    	 title:'浮动收益',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.floatIncome;
		       }
		     },
		     {
		    	 field:'guojinCommission',	
		    	 title:'国金佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.guojinCommission;
		       }
		     },
		     {
		    	 field:'projectCommission',	
		    	 title:'项目端',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.projectCommission;
		       }
		     },
		     {
		    	 field:'reissueCommision',	
		    	 title:'补发费用',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.reissueCommision;
		       }
		     },
		     {
		    	 field:'withholdCommission',	
		    	 title:'暂扣费用',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.withholdCommission;
		       }
		     },
		     {
		    	 field:'zhanyeCommission',	
		    	 title:'展业费用扣减',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.zhanyeCommission;
		       }
		     },
		     {
		    	 field:'lastMonthRemaining',	
		    	 title:'上月剩余待发',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lastMonthRemaining;
		       }
		     },
		     {
		    	 field:'otherCommission',	
		    	 title:'其他',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.otherCommission;
		       }
		     },
		     {
		    	 field:'commissionAmount',	
		    	 title:'本月可发合计',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.commissionAmount;
		       }
		     },
		     {
		    	 field:'addToSalary',	
		    	 title:'计入本月',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.addToSalary;
		       }
		     },{
		    	 field:'remainCommission',	
		    	 title:'剩余待发',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remainCommission;
		       }
		     },{
		    	 field:'overseasCommission',	
		    	 title:'海外投资佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.overseasCommission;
		       }
		     },
		     {
		    	 field:'remark',	
		    	 title:'备注',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remark;
		       }
		     }
		     ]],
				onLoadSuccess : function() {
					$('#detailsCommissionOverviewIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	detailsCommissionOverviewIdTable.datagrid('options').url = "sales/querySaleCommissionIdList?param="+month;
	var queryParam = $("#detailSaleCommissionIdForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailsCommissionOverviewIdTable.datagrid('load',{queryParam:queryParam});	
}*/

/**
 * 清空
 */
/*function clearSaleCommissionIdCondition(){
	$("#detailSaleCommissionIdForm").form("clear");
}*/