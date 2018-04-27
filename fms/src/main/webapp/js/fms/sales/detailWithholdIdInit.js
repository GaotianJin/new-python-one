var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#detailWithholdId_month").val();
	initDetailWithholdIdTable();
	});

/**
 * 暂扣利益列表
 */
var detailWithholdIdTable;
function initDetailWithholdIdTable(){
	var  selectIndex = -1;
	detailWithholdIdTable=$('#detailWithholdIdTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/queryWithholdIdList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slyWithholdId',	
		    	 title:'暂扣利益流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slyWithholdId;
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
		    	 title:'部门负责人',
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
		    	 field:'withholdCommission',	
		    	 title:'暂扣佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.withholdCommission;
		       }
		     },
		     {
		    	 field:'withholdAllowance',	
		    	 title:'暂扣管理津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.withholdAllowance;
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
		    	 field:'withholdAddedBonus',	
		    	 title:'暂扣增员奖励',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.withholdAddedBonus;
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
					$('#detailWithholdIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
/*function queryWithholdIdList() {
	detailWithholdIdTable.datagrid('options').url = "sales/queryWithholdIdList?param="+month;
	var queryParam = $("#detailWithholdIdForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	detailWithholdIdTable.datagrid('load',{queryParam:queryParam});	
}*/

/**
 * 清空
 */
/*function clearWithholdIdCondition(){
	$("#detailWithholdIdForm").form("clear");
}*/