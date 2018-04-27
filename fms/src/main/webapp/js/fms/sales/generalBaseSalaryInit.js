var month=null;

/**
 * 初始化列表
 */
jQuery(function($) {
	month = $("#generalBaseSalary_month").val();
	initGeneralBaseSalaryTable();
	});

/**
 * 基本工资列表
 */
var generalBaseSalaryTable;
function initGeneralBaseSalaryTable(){
	var  selectIndex = -1;
	generalBaseSalaryTable=$('#generalBaseSalaryTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/queryGeneralBaseSalaryList?param='+month,
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slyBaseSalaryId',	
		    	 title:'基本工资流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slyBaseSalaryId;
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
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.month;
		       }
		     },
		     {
		    	 field:'department',	
		    	 title:'部门',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.department;
		       }
		     },
		     {
		    	 field:'post',	
		    	 title:'岗位',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.post;
		       }
		     },
		     {
		    	 field:'workNumber',	
		    	 title:'工号',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.workNumber;
		       }
		     },
		     {
		    	 field:'chnName',	
		    	 title:'姓名',
		    	 width:60,
		    	 formatter : function(value, row, index) {
		    	 return row.chnName;
		       }
		     },
		     {
		    	 field:'baseSalary',	
		    	 title:'月基本工资',
		    	 width:90,
		    	 formatter : function(value, row, index) {
		    	 return row.baseSalary;
		       }
		     },
		     {
		    	 field:'bussinessBonus',	
		    	 title:'业务提奖',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.bussinessBonus;
		       }
		     },
		     {
		    	 field:'lunchAllownace',	
		    	 title:'午餐补贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lunchAllownace;
		       }
		     },
		     {
		    	 field:'hihgTepFee',	
		    	 title:'高温费',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.hihgTepFee;
		       }
		     },
		     {
		    	 field:'absenceFee',	
		    	 title:'缺勤扣款',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.absenceFee;
		       }
		     },
		     {
		    	 field:'other',	
		    	 title:'其他扣款',
		    	 width:60,
		    	 formatter : function(value, row, index) {
		    	 return row.other;
		       }
		     },
		     {
		    	 field:'salaryOfTheory',	
		    	 title:'应发工资',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.salaryOfTheory;
		       }
		     },
		     {
		    	 field:'pension',	
		    	 title:'扣养老金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.pension;
		       }
		     },
		     {
		    	 field:'medicare',	
		    	 title:'扣医疗金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.medicare;
		       }
		     },
		     {
		    	 field:'unemployment',	
		    	 title:'扣失业金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.unemployment;
		       }
		     },{
		    	 field:'accumulationFund',	
		    	 title:'扣公积金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.accumulationFund;
		       }
		     },{
		    	 field:'taxFee',	
		    	 title:'应纳税额',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.taxFee;
		       }
		     },{
		    	 field:'personalTax',	
		    	 title:'月工资个税',
		    	 width:90,
		    	 formatter : function(value, row, index) {
		    	 return row.personalTax;
		       }
		     },{
		    	 field:'actualSalary',	
		    	 title:'月工资实发',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.actualSalary;
		       }
		     }]],
				onLoadSuccess : function() {
					$('#generalBaseSalaryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	$('#addGeneralSalaryComWindow').window('close');
	initGeneralSalaryTable();
}
