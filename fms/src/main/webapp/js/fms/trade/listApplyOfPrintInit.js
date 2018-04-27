jQuery(function($) {
	$('#applyOfPrintTable').datagrid({
		title : '交易信息公共池列表', // 标题
		method : 'post',
		singleSelect : false, // 单选
		//height : 380, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList : [10,20,30,40],
		pageSize : 10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'tradeNo',
					title : '交易号码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tradeNo;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'tradeType',
					title : '交易类型',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tradeType;
					}
				},{
					field : 'tradeInfoNo',
					title : '合同号',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tradeInfoNo;
					}
				},{
					field : 'tradeDate',
					title : '交易日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tradeDate;
					}
				},{
					field : 'currency',
					title : '币种',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.currency;
					}
				},{
					field : 'agentId',
					title : '财富顾问',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agentId;
					}
				},{
					field : 'companyId',
					title : '所属机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyId;
					}
				},{
					field : 'storeId',
					title : '所属网点',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.storeId;
					}
				},{
					field : 'tradeComId',
					title : '交易机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tradeComId;
					}
				},{
					field : 'tradeStoreId',
					title : '交易网点',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tradeStoreId;
					}
				}]],
		onLoadSuccess : function() {
			$('#applyOfPrintTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	
	$("#printType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency1',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#printcompanyId").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency1',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#printComId").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency1',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#printStoreId").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency1',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#printcurrency").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#printagentId").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency1',
		valueField:'code',
		textField:'codeName'
	});
	
	$("#printstoreId").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency1',
		valueField:'code',
		textField:'codeName'
	});

});	
	// 表格查询
	function searchagent() {
		var params = $('#applyOfPrintTable').datagrid('options').queryParams; // 先取得
		var fields = $('#ApplyOfPrintForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#applyOfPrintTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件然后进行查询
	function clearForm() {
		$('#ApplyOfPrintForm').form('clear');
		searchBuild();
	}