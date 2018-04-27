jQuery(function($) {
	$('#posRemarkTable').datagrid({
		title : '保全备注列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 380, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryPosRemark?posID="+$('#posID').val(), // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[
				{
					field : 'id',
					title : 'id',
					width : 100,
					sortable : true,
					hidden :true,
					formatter : function(value, row, index) {
						return row.id;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.status',
					title : '任务状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.status;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'row.operator',
					title : '操作人',
					width : 80,
					sortable : true,
					formatter : function(value, row, index) {
						return row.operator;
					}
				},{
					field : 'row.time',
					title : '操作时间',
					width : 120,
					sortable : true,
					hidden :false,
					formatter : function(value, row, index) {
						return row.time;
					}
				}, {
					field : 'row.remark',
					title : '备注',
					width : 725,
					sortable : true,
					formatter : function(value, row, index) {
						return row.remark;
					}
				}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addremarktab('新增备注', 'addPosRemarkUrl?posID='+$('#posID').val());
					}
				}, '-'],
		onLoadSuccess : function() {
			$('#posRemarkTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
});
	function addremarktab(title, href) {
		if (href){  
	        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
		}else {  
            var content = '未实现';  
        }  
		if($('#remarktab').tabs('exists', title)) {
			$('#remarktab').tabs('select', title);
	        var tab = $('#remarktab').tabs('getSelected'); 
			$('#remarktab').tabs('update', {
				tab: tab,
				options: {
					title: title,
		            content:content, 
					closable : true
				}
			});
		}else {   
			$('#remarktab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
	} 

	
	// 清空查询条件然后进行查询
	function clearForm() {
		$('#posRemarkTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}