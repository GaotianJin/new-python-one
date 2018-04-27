jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initSalaryCalTable();
});


function initAllCombobox(){
	var wageStateComobox;
	wageStateComobox = $("#salaycal_wageState").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=wageState',
		valueField : 'code',
		textField : 'codeName'
	});
	
	
	$("#salaycal_calYear").datebox({
		editable:false,
		formatter:function(date){
			var y = date.getFullYear();
			var startDateValue = y;
			return  startDateValue+"";
		}
	});
	
	$("#salaycal_calMonth").datebox({
		editable:false,
		formatter:function(date){
			var m = date.getMonth()+1;
			if(m<10){
				return '0'+m;
			}
			return m+"";
		}
	});
	
	
	
}
var salaryCalTable;
function initSalaryCalTable(){
	salaryCalTable=
	    $('#salaryCalTable').datagrid({
		title : '薪资结算信息', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/sales/querySalaryCalList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'row.calYear',
					title : '薪资计算年',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.calYear;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'row.calMonth',
					title : '薪资计算月',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.calMonth;
					}
				},
				{
					field : 'row.wageSumMoney',
					title : '总金额(元)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.wageSumMoney;
					}
				},
				{
					field : 'row.state',
					title : '结算状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.state;
					}
				},
				{
					field : 'row.stateCode',
					title : '结算状态',
					width : 100,
					hidden:true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.stateCode;
					}
				},
				
				{
					field : 'row.settleDate',
					title : '结算日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.settleDate;
					}
				},
				{
					field : 'row.forwardDate',
					title : '结转日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.forwardDate;
					}
				}]]
	});
}
//薪资结转按钮
function salaryForward(){
	var rows = $('#salaryCalTable').datagrid('getSelections');
	
	if(rows.length==0){
		$.messager.alert('提示',"请选中一条记录进行薪资结转", 'info');
		return;
	}
	
   if(rows.length>1){
	  $.messager.alert('提示',"只能选中一条记录进行薪资结转",'info');
	  return;
   }
   var calYear= rows[0].calYear;
   var calMonth=rows[0].calMonth;
   var param={};
	param.calYear=calYear;
	param.calMonth=calMonth;
	$.ajax({
		type : 'post',
		url : contextPath + "/sales/salaryForward",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
					clearForm();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

//薪资结算按钮
function salaryCal(){
	var calYear=$('#salaycal_calYear').datebox("getValue");
	var calMonth=$('#salaycal_calMonth').datebox("getValue");
	
	var param={};
	param.calYear=calYear;
	param.calMonth=calMonth;
	$.ajax({
		type : 'post',
		url : contextPath + "/sales/salaryCal",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
					clearForm();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}



// 清空查询条件然后进行查询
function clearForm() {
	$('#salesAccForm').form('clear');
	$('#salaryCalTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}

// 根据查询条件进行查询
function searchSalary(){
	salaryCalTable.datagrid('options').url = contextPath+"/sales/querySalaryCalList";
	var queryparam = $("#salesAccForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	salaryCalTable.datagrid('load',{param:queryparam});
}

	