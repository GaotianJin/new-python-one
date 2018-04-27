var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#detailProjectCommissionId_month").val();
	initDetailProjectCommissionId();
	});

/**
 * 项目端佣金列表
 */
var detailProjectCommissionIdTable;
function initDetailProjectCommissionId(){
	var  selectIndex = -1;
	detailProjectCommissionIdTable=$('#detailProjectCommissionIdTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/queryProjectCommissionIdList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slyProjectCommissionId',	
		    	 title:'项目端佣金流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slyProjectCommissionId;
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
		    	 field:'productName',	
		    	 title:'产品名称',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.productName;
		       }
		     },
		     {
		    	 field:'newIncomeAfertTax',	
		    	 title:'公司税后净收入',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.newIncomeAfertTax;
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
		     /*{
		    	 field:'companyFloatIncome',	
		    	 title:'公司收入（浮动）',
		    	 width:80,
		    	 hidden : true,
		    	 formatter : function(value, row, index) {
		    	 return row.companyFloatIncome;
		       }
		     },*/
		     {
		    	 field:'projectImportPerson',	
		    	 title:'项目引进人',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.projectImportPerson;
		       }
		     },
		     {
		    	 field:'issueRateWay',	
		    	 title:'发放比例计算方式',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.issueRateWay;
		       }
		     },
		  
		     {
		    	 field:'issueRate',	
		    	 title:'发放比例',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.issueRate;
		       }
		     },
		     {
		    	 field:'sendCommission',	
		    	 title:'可发佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.sendCommission;
		       }
		     },
		     {
		    	 field:'otherCommission',	
		    	 title:'其它',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.otherCommission;
		       }
		     },
		     {
		    	 field:'remarks',	
		    	 title:'备注',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remarks;
		       }
		     }/*,
		     {
		    	 field:'firstIssueRate',	
		    	 title:'首期发放比例',
		    	 width:80,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.firstIssueRate;
		       }
		     },
		     {
		    	 field:'actualFirstIssueAmount',	
		    	 title:'首期实际可发金额',
		    	 width:80,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.actualFirstIssueAmount;
		       }
		     },
		     {
		    	 field:'secondIssueRate',	
		    	 title:'二期发放比例',
		    	 width:80,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.secondIssueRate;
		       }
		     },
		     {
		    	 field:'actualSecondIssueAmount',	
		    	 title:'二期可发金额',
		    	 width:80,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.actualSecondIssueAmount;
		       }
		     },
		     {
		    	 field:'lastIssueRate',	
		    	 title:'后期发放比例',
		    	 width:80,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.lastIssueRate;
		       }
		     },*/
		     /*{
		    	 field:'actualLastIssueAmount',	
		    	 title:'后期可发金额',
		    	 width:80,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.actualLastIssueAmount;
		       }
		     }*/]],
				onLoadSuccess : function() {
					$('#detailProjectCommissionIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
/*function queryProjectCommissionIdList() {
	detailProjectCommissionIdTable.datagrid('options').url = "sales/queryProjectCommissionIdList?param="+month;
	var queryParam = $("#detailProjectCommissionIdForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailProjectCommissionIdTable.datagrid('load',{queryParam:queryParam});	
}*/

/**
 * 清空
 */
/*function clearProjectCommissionIdCondition(){
	$("#detailProjectCommissionIdForm").form("clear");
}*/