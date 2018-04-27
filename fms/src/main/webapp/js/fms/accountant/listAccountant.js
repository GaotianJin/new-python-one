jQuery(function($) {
	var  selectIndex = -1;
	$('#actTable').datagrid({
		title : '理财师列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/accountant/yList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},  // 显示复选框
				{
					field : 'row.actId',
					title : '理财师ID',
					align : 'center',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.actId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.actName',
					title : '理财师姓名',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.actName;
					}
				},{
					field : 'row.actPhone',
					title : '理财师手机号',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.actPhone;
					}
				},{
					field : 'row.actEmail',
					title : '邮箱',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.actEmail;
					}
				},{
					field : 'row.actYJZB',
					title : '业绩指标',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.state;
					}
				},{
					field : 'row.actWCD',
					title : '完成度',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.state;
					}
				},{
					field : 'row.actKHCount',
					title : '客户数',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.state;
					}
				},
				{
					field : 'aa',
					title : '操作',
					align : 'center',
					width : 350,
					formatter : function(value, row, index) {
						return "<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' iconcls='icon-edit' plain='true' onclick='openEditDialog("
								+ index
								+ ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text'>编辑权重</span><span class='l-btn-icon icon-edit'>&nbsp;</span></span></a>";
					}
				} ] ],
		toolbar : [{
					text : '新增理财师',
					iconCls : 'icon-add',
					handler : function() {
						
					}
				}, '-', {
					text : '导入业绩指标',
					iconCls : 'icon-edit',
					handler : function() {
						
					}
				}, '-', {
					text : '导出业绩模板',
					iconCls : 'icon-remove',
					handler : function() {
						
					}
				}], 
	})
});

function clearActFormList() {
	$('#actForm').form('clear');
}

function searchAct(){
	var aName=$("#actName").val();
	var aPhone=$("#actPhone").val();
}