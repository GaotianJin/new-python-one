jQuery(function($) {
	$('#tradeTable').datagrid({
		title : '交易查询列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryTrade", // 数据来源
		sortName : 'tctransstatus.id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
				field : 'tctransstatus.bankbranch',
				title : '渠道',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tctransstatus.bankbranch;
				}
			},	{
					field : 'tctransstatus.reportno',
					title : '交易名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.reportno;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'tctransstatus.transcode',
					title : '交易编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.transcode;
					}
				}, {
					field : 'tctransstatus.transdate',
					title : '交易日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.transdate;
					}
				}, {
					field : 'tctransstatus.transtime',
					title : '交易时间',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.transtime;
					}
				}
//				,{
//					field : 'trade.tradesum',
//					title : '次数统计',
//					width : 100,
//					sortable : true,
//					formatter : function(value, row, index) {
//						return row.trade.tradesum;
//					}
//				}
				]]
		
	});
	

	
});
	
	
	// 表格查询
	function searchUser() {
		var params = $('#tradeTable').datagrid('options').queryParams; // 先取得
		var fields = $('#tradeQueryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#tradeTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件
	function clearForm() {
		$('#tradeQueryForm').form('clear');
		searchUser();
	}