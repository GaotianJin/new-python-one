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
jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initGeneralSalaryTable();
});


function initAllCombobox(){
	$("#salayDetail_year").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: yearData
	});
	$("#salayDetail_endyear").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: yearData
	});
	$("#salayDetail_month").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: monthData
	});
	$("#salayDetail_endmonth").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: monthData
	});
}

/**
 * 初始化综合工资表
 */
var generalSalaryTable;
function initGeneralSalaryTable(){
	generalSalaryTable=
	    $('#generalSalaryTable').datagrid({
		title : '综合工资详情', // 标题
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		rownumbers : true, // 显示行号
		pageSize:1,
		columns : [[
            {
	               field:'slyBaseSalaryId',	
	               title:'基本工资',
	               hidden:true,
	               width:100,
	               formatter : function(value, row, index) {
			         return row.slyBaseSalaryId;
              }
             },
             {
		    	 field:'month',	
		    	 title:'月份',
		    	 //hidden:true,
		    	 width:100,
				 formatter : function(value, row, index) {
				     return row.month;
			   }
		     },
             {
	            field:'agentName',	
	            title:'姓名',
	            width:70,
	            formatter : function(value, row, index) {
	            return row.agentName;
              }
            },
            {
		    	 field:'theroySalary',	
		    	 title:'当月应发',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.theroySalary;
		       }
		     },
		     {
		    	 field:'actualSalary',	
		    	 title:'当月实发',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    	 return row.actualSalary;
		       }
		     },
		     {
		    	 field:'generalBaseSalary',	
		    	 title:'基本工资总额',
		    	 width:70,
		    	 formatter : function(value, row, index) {
		    		 var param = {};
		             param.month = row.month;
			         return "<a href='#'  onclick=addGeneralSalaryTab('基本工资明细信息','"
		        	+contextPath+"/sales/generalSalaryDetailUrl?param="+$.toJSON(param)+"')>"+row.generalBaseSalary;+"</a>";
		       }
		     },
		     {
		    	 field:'commissionAmount',	
		    	 title:'佣金总额',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    		 var param = {};
		             param.month = row.month;
			         return "<a href='#'  onclick=addGeneralSalaryTab('佣金明细信息','"
		        	+contextPath+"/sales/generalCommissionDetailUrl?param="+$.toJSON(param)+"')>"+row.commissionAmount;+"</a>";
		       }
		     },
		     {
		    	 field:'taxFee',	
		    	 title:'扣税金额',
		    	 width:80,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.taxFee;
		       }
		     }]],
				onLoadSuccess : function() {
					$('#generalSalaryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				}
	});
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#salaryDetailForm').form('clear');
}

function querySalaryDetail(){
	generalSalaryTable.datagrid('options').url = "sales/queryGeneralSalary";
	var queryParam = $("#salaryDetailForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	generalSalaryTable.datagrid('load',{queryParam:queryParam});	
}

function addGeneralSalaryTab(title, href) {
	$('<div id="addGeneralSalaryComWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}

	