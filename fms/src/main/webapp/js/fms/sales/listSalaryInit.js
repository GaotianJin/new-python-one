var yearData =[{code: '2015',codeName: '2015'},{code: '2016',codeName: '2016'},{code: '2017',codeName: '2017'},
		       {code: '2018',codeName: '2018'},{code: '2019',codeName: '2019'},{code: '2020',codeName: '2020'},
		       {code: '2021',codeName: '2021'},{code: '2022',codeName: '2022'},{code: '2023',codeName: '2023'},
		       {code: '2024',codeName: '2024'},{code: '2025',codeName: '2025'},{code: '2026',codeName: '2026'},
		       {code: '2027',codeName: '2027'},{code: '2028',codeName: '2028'},{code: '2029',codeName: '2029'},
		       {code: '2030',codeName: '2030'}
		      ];
var monthData =[{code: '01',codeName: '01'},{code: '02',codeName: '02'},{code: '03',codeName: '03'},
		       {code: '04',codeName: '04'},{code: '05',codeName: '05'},{code: '06',codeName: '06'},
		       {code: '07',codeName: '07'},{code: '08',codeName: '08'},{code: '09',codeName: '09'},
		       {code: '10',codeName: '10'},{code: '11',codeName: '11'},{code: '12',codeName: '12'}
		      ]
/**
 * 初始化列表
 */
jQuery(function($) {
	initSalaryTable();
	initDatebox();
});

/**
 * 初始化日期
 */
function initDatebox(){
	$("#listSalary_year").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: yearData
	});
	$("#listSalary_month").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: monthData
	});
}

/**
 * 薪资列表
 */
var salaryTable;
function initSalaryTable(){
	var  selectIndex = -1;
	salaryTable = $('#salaryTable').datagrid({
		method : 'post',
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/querySalaryList',
		sortName : 'id', // 排序的列
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'slySalaryId',	
		    	 title:'薪资主表流水号',
		    	 hidden:true,
				 formatter : function(value, row, index) {
				     return row.slySalaryId;
			   }
		     },
		     {
		    	 field:'month',	
		    	 title:'月份',
		    	 width:100,
				 formatter : function(value, row, index) {
				     return row.month;
			   }
		     },
		     {
		    	 field:'slyBaseSalaryId',	
		    	 title:'基本工资',
		    	 width:100,
				 formatter : function(value, row, index) {
					    var param = {};
					    param.month = row.month;
						return "<a href='#'  onclick=addSalaryTab('基本工资明细信息','"
						+contextPath+"/sales/baseSalaryDetailUrl?param="+$.toJSON(param)+"')>"+'基本工资明细';+"</a>";
			   }
		     }
	]],
		     toolbar : [{
					id : 'import',
					text : '导入',
					iconCls : 'icon-redo',
					handler : function() {
						addSalaryTab(
								'薪资批量导入',
								contextPath+ '/sales/importSalaryUrl');
						}
				} ],
				onLoadSuccess : function() {
					$('#salaryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
 * 跳转窗口
 * @param title
 * @param href
 */
function addSalaryTab(title, href) {
	$('<div id="addSalaryWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		width : 1200,
		height : 600,
		inline : false,
		minimizable : true,
		onClose : function() {
			$(this).window('destroy');
		}
	});
} 

/**
 * 查询
 */
function querySalaryList() {
	salaryTable.datagrid('options').url = "sales/querySalaryList";
	var salaryYear = $("#listSalary_year").combobox("getValue");
	var salaryMonth = $("#listSalary_month").combobox("getValue");
	var salaryDate = salaryYear + '-' + salaryMonth;
	var queryParam = "month="+salaryDate;
	queryParam = formDataToJsonStr(queryParam);
	salaryTable.datagrid('load',{queryParam:queryParam});	
}

/**
 * 清空
 */
function clearSalaryCondition(){
	$("#listSalaryForm").form("clear");
}


