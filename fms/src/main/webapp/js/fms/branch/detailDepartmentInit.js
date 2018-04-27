jQuery(function($) 
{
	initDepartmentTable();
	initDepartmentList();
});


function initDepartmentList(){
	var rows = $('#departmentTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		$.messager.alert("请选择要查询的部门信息");
		return;
	} else{
		var ps = "";
		$.each(rows, function(i, n) {
			if (i == 0)
				ps += "?departmentId=" + n.departmentId;
		});
		$.post(contextPath+'/branch/updateDepartmentInitUrl' + ps, function(data) {
			setInputValueById("department",data.def);
			$('#departmentBelongInfoTable').datagrid('loadData',data.departmentBelongTraceList);
		});
	};
	initAllCombobox();
}


function initAllCombobox(){
	$("#ywbstate").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=state',
		valueField:'code',
		textField:'codeName'
	});

}

function initDepartmentTable(){
	$('#departmentBelongInfoTable').datagrid({
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		sortName : 'id', // 排序的列
		idField : 'id', // 主键字段
		rownumbers : true, // 显示行号
		border : true,
		columns : [[
				{
					field : 'belongComName',
					title : '上级机构',
					width : 30,
					sortable : true,
					formatter : function(value, row, index) {
						return row.belongComName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'startDate',
					title : '归属开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.startDate;
					}
				},{
					field : 'endDate',
					title : '归属结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.endDate;
					}
				}]],
		onLoadSuccess : function() {
			$('#departmentBelongInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function backListdetailDepartmentPage(){
	$('#addDepartmentWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="detalDepartment"){
		searchDepartment();
	}
}