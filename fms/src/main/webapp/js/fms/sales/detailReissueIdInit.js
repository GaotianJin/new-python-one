var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#detailReissueId_month").val();
	initDetailReissueIdTable();
	});

/**
 * 补发利益列表
 */
var detailReissueIdTable;
function initDetailReissueIdTable(){
	var  selectIndex = -1;
	detailReissueIdTable=$('#detailReissueIdTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/queryReissueIdList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slyReissueId',	
		    	 title:'补发利益流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slyReissueId;
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
		    	 field:'productName',	
		    	 title:'产品名称',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.productName;
		       }
		     },
		     {
		    	 field:'productCreateDate',	
		    	 title:'成立日期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.productCreateDate;
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
		    	 field:'teamDirector',	
		    	 title:'部门总监',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.teamDirector;
		       }
		     },
		     {
		    	 field:'customerName',	
		    	 title:'客户姓名',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.customerName;
		       }
		     },
		     {
		    	 field:'customerType',	
		    	 title:'客户类型',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.customerType;
		       }
		     },
		     {
		    	 field:'scale',	
		    	 title:'规模',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.scale;
		       }
		     },
		     {
		    	 field:'duration',	
		    	 title:'久期',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.duration;
		       }
		     },
		     {
		    	 field:'gaugeStandard',	
		    	 title:'标规（百万）',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.gaugeStandard;
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
		    	 field:'reissueCommission',	
		    	 title:'补发佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.reissueCommission;
		       }
		     },
		     {
		    	 field:'reissueAllowance',	
		    	 title:'补发管理津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.reissueAllowance;
		       }
		     },
		     {
		    	 field:'recommoendName',	
		    	 title:'推荐人',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.recommoendName;
		       }
		     },
		     {
		    	 field:'reissueAddedBonus',	
		    	 title:'补发增员奖励',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.reissueAddedBonus;
		       }
		     },{
		    	 field:'remarks',	
		    	 title:'备注',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remarks;
		       }
		     }]],
				onLoadSuccess : function() {
					$('#detailReissueIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
/*function queryReissueIdList() {
	detailReissueIdTable.datagrid('options').url = "sales/queryReissueIdList?param="+month;
	var queryParam = $("#detailReissueIdForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailReissueIdTable.datagrid('load',{queryParam:queryParam});	
}*/

/**
 * 清空
 */
/*function clearReissueIdCondition(){
	$("#detailReissueIdForm").form("clear");
}*/