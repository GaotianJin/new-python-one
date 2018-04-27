var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#detailOverseasCommissionId_month").val();
	initDetailOverseasCommissionIdTable();
	});

/**
 * 海外投资列表
 */
var detailOverseasCommissionIdTable;
function initDetailOverseasCommissionIdTable(){
	var  selectIndex = -1;
	detailBaseSalaryTable=$('#detailOverseasCommissionIdTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/queryOverseasCommissionIdList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slyOverseasCommissionId',	
		    	 title:'海外投资流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slyOverseasCommissionId;
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
		    	 field:'organization',	
		    	 title:'机构',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.organization;
		       }
		     },
		     {
		    	 field:'productSide',	
		    	 title:'产品方',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.productSide;
		       }
		     },
		     {
		    	 field:'productPlan',	
		    	 title:'产品计划',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.productPlan;
		       }
		     },
		     {
		    	 field:'purchaseDate',	
		    	 title:'购买日期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.purchaseDate;
		       }
		     },
		     {
		    	 field:'inPay',	
		    	 title:'年缴（USD）',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.inPay;
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
		    	 field:'firstCommissionRate',	
		    	 title:'佣金比率（%）',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.firstCommissionRate;
		       }
		     },
		     {
		    	 field:'firstCommission',	
		    	 title:'佣金（HKD）',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.firstCommission;
		       }
		     },
		  /*   {
		    	 field:'settleCommission',	
		    	 title:'本月结算佣金（HKD）',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.settleCommission;
		       }
		     },*/
		     {
		    	 field:'insurancePolicyNo',	
		    	 title:'保单号',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.insurancePolicyNo;
		       }
		     },
		     {
		    	 field:'applicantName',	
		    	 title:'持保人姓名',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.applicantName;
		       }
		     },
		     {
		    	 field:'recognizeeName',	
		    	 title:'受保人姓名',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.recognizeeName;
		       }
		     },{
		    	 field:'remark',	
		    	 title:'备注',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remark;
		       }
		     }]],
				onLoadSuccess : function() {
					$('#detailOverseasCommissionIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
/*function queryOverseasCommissionIdList() {
	detailOverseasCommissionIdTable.datagrid('options').url = "sales/queryOverseasCommissionIdList?param="+month;
	var queryParam = $("#detailOverseasCommissionIdForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailOverseasCommissionIdTable.datagrid('load',{queryParam:queryParam});	
}*/

/**
 * 清空
 */
/*function clearOverseasCommissionIdCondition(){
	$("#detailOverseasCommissionIdForm").form("clear");
}*/