jQuery(function($) {
	$('#TradeBalanceImportTable').datagrid({
		title : '结算信息', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
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
					field : 'row.username',
					title : '管理机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.username;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.username',
					title : '网点',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.username;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.username',
					title : '部门',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.username;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.username',
					title : '财富顾问',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.username;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.username',
					title : '基金管理人',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.username;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.username',
					title : '产品',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.username;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'row.usercode',
					title : '交易号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.usercode;
					}
				},{
					field : 'row.companyName',
					title : '费用金额',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}]],
		onLoadSuccess : function() {
			$('#TradeBalanceTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	$('#companyId').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#companyId').combobox('getData');
			  if(data.length>0){
				  $('#companyId').combobox('select',data[0].id);
			  }
		  }
	  });
	
});
	// 表格查询
	function searchUser() {
		var params = $('#customerTable').datagrid('options').queryParams; // 先取得
		var fields = $('#customerForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#customerTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件然后进行查询
	function clearForm() {
		$('#customerForm').form('clear');
		searchcustomer();
	}