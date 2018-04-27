jQuery(function($) {
			$('#workflowTable').datagrid({
				title : '工作流列表', // 标题
				method : 'post',
				iconCls : 'icon-edit', // 图标
				singleSelect : false, // 多选
				height : 350, // 高度
				fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped : true, // 奇偶行颜色不同
				collapsible : true,// 可折叠
				url : "queryList", // 数据来源
				sortName : 'workflow.id', // 排序的列
				sortOrder : 'asc', // 倒序
				remoteSort : true, // 服务器端排序
				idField : 'wid', // 主键字段
				queryParams : {}, // 查询条件
				pagination : true, // 显示分页
				rownumbers : true, // 显示行号
				columns : [[{
							field : 'ck',
							checkbox : true,
							width : 2
						}, // 显示复选框
						{
							field : 'name',
							title : '工作流名称',
							width : 100,
							sortable : true,
							formatter : function(value, row, index) {
								return row.workflow.name;
							} // 需要formatter一下才能显示正确的数据
						}]],
				toolbar : [{
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								parent.addsubtab('新增工作流',
										'workflow/addWorkflowUrl');
							}
						}, '-', {
							text : '更新',
							iconCls : 'icon-edit',
							handler : function() {
								var rows = $('#workflowTable')
										.datagrid('getSelections');
								if (rows.length == 0) {
									alert("请选择一个工作流进行修改");
									return;
								}
								if (rows.length > 1) {
									alert("只能选择一个工作流修改");
									return;
								}
								var dlist = [];
								dlist.push({
											"id" : rows[0].workflow.id,
											"name" : rows[0].workflow.name
										});
								parent.addsubtab('更新工作流',
										'workflow/updateWorkflowUrl?list='
												+ $.toJSON(dlist));
							}
						}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleteWorkflow();
							}
						}, '-'],
				onLoadSuccess : function() {
					$('#workflowTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				}
			});
			$('body', document).live('keydown', function(event) {
				if (event.keyCode == 46) {
					parent.subframe.$('.ui-selected,.click').each(
							function(index, element) {

								if ($(element).is('.node')) {
									parent.subframe.$('body',
											element.ownerDocument).application(
											'executeCommand', 'removeNode', {
												id : element.id,
												offset : {
													left : element.offsetLeft,
													top : element.offsetTop
												},
												text : $(element).text(),
												type : element.classList[1]
											});
								} else if ($(element).is('.transition')) {
									parent.subframe.$('body',
											element.ownerDocument).application(
											'executeCommand',
											'removeTransition', {
												id : element.id,
												fromId : $(element)
														.attr('from'),
												toId : $(element).attr('to')
											});
								}
							})
				}
			});
		});
// 删除
function deleteWorkflow() {
	var rows = $('#workflowTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择要删除的工作流");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
					if (result) {
						var ps = "";
						$.each(rows, function(i, n) {
									if (i == 0) {
										ps += "?wid=" + n.wid;
									} else {
										ps += "&wid=" + n.wid;
									}
								});
						$.post('delWorkflow' + ps, function(data) {
									$('#workflowTable').datagrid('cisreload');
									$.messager.alert('提示', data.mes, 'info');
								});
					}
				});
	}
}
// 表格查询
function searchWorkflow() {
	var params = $('#workflowTable').datagrid('options').queryParams; // 先取得
	// datagrid
	// 的查询参数
	var fields = $('#workflowForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
				params[field.name] = (field.value); // 设置查询参数
			});
	$('#workflowTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}
// 清空查询条件
// 清空查询条件
function clearWorkflow() {
	$('#workflowForm').form('clear');
	searchMenu();
}