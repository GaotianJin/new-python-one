jQuery(function($) {
	$('#logTable').datagrid({
		title : '保单查询列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryLccont", // 数据来源
		//sortName : 'lccont.id', // 排序的列
		//sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		//idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'row.salechnlname',
					title : '交易渠道',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						//alert(row.salechnlname);
						return row.salechnlname;
					}
				},
				{
					field : 'row.contno',
					title : '保单号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.contno;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'row.appntno',
					title : '投保单号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.appntno;
					}
				}, {
					field : 'row.appntname',
					title : '投保人姓名',
					width : 70,
					sortable : true,
					formatter : function(value, row, index) {
						return row.appntname;
					}
				}, {
					field : 'row.insurdname',
					title : '被保人姓名',
					width : 70,
					sortable : true,
					formatter : function(value, row, index) {
						return row.insurdname;
					}
				}, {
					field : 'row.riskcode',
					title : '险种编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.riskcode;
					}
				}, {
					field : 'row.amnt',
					title : '保额',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.amnt;
					}
				}, {
					field : 'row.prem',
					title : '保费',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.prem;
					}
				}, {
					field : 'row.state',
					title : '保单状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.state;
					}
				}, {
					field : 'row.cvalidate',
					title : '保单生效日',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.cvalidate;
					}
				}
				, {
					field : 'lccont.money',
					title : '币种',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.money;
					}
				}]]
		
	});
	

	
});
	
	
	// 表格查询
	function searchUser() {
		var params = $('#logTable').datagrid('options').queryParams; // 先取得
		var fields = $('#logQueryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#logTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件
	function clearForm() {
		$('#logQueryForm').form('clear');
		searchUser();
	}