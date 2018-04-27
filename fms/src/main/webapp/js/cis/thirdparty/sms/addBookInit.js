jQuery(function($) {
	
	$('#smsaccordion').panel('open');
	
	$('#name').combobox({
		 url:'queryNameComboxList',   
		 valueField:'name',   
		 textField:'name'
	});  
	
	 
	$('#addBookTable').datagrid({
		title : '通讯录个人列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryAddBookList", // 数据来源
		sortName : 'tcaddbook.id', // 排序的列
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
					field : 'tcaddbook.name',
					title : '姓名',
					width : 70,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcaddbook.name;
					}
				},{
					field : 'tcaddbook.phone',
					title : '手机号码',
					width : 120,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcaddbook.phone;
					}
				},{
					field : 'tcaddbook.email',
					title : '邮箱',
					width : 200,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcaddbook.email;
					}
				},{
					field : 'tcaddbook.priority',
					title : '优先级',
					width : 50,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcaddbook.priority;
					}
				},{
					field : 'tcaddbook.note',
					title : '备注',
					width : 400,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcaddbook.note;
					}
				}]],
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addaddtab('新增个人通讯录','addAddBookUrl');
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#addBookTable').datagrid('getSelections');
						if(rows.length==0){
							alert("请选择一条记录进行修改");
							return;
						}
						if(rows.length>1){
							alert("只能选择一条记录修改");
							return;
						}
						var dlist = [];
						dlist.push({
							"name":rows[0].tcaddbook.name,
							"phone":rows[0].tcaddbook.phone,
							"email":rows[0].tcaddbook.email,
							"priority":rows[0].tcaddbook.priority,
							"note":rows[0].tcaddbook.note,
							"id":rows[0].tcaddbook.id}); 
						addaddtab('更新个人通讯录','updateAddUrl?list='+$.toJSON(dlist));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteAdd();
					}
				}, '-'],
				onLoadSuccess : function() {
					$('#addBookTable').datagrid('clearSelections'); 
				}
		
	});
	

	
});
	
function addaddtab(title, href) {
	if (href){  
        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
	}else {  
        var content = '未实现';  
    }  
	if($('#addBooktab').tabs('exists', title)) {
		$('#addBooktab').tabs('select', title);
        var tab = $('#addBooktab').tabs('getSelected'); 
		$('#addBooktab').tabs('update', {
			tab: tab,
			options: {
				title: title,
	            content:content, 
				closable : true
			}
		});
	}else {   
		$('#addBooktab').tabs('add', {
			title : title,
            content:content, 
			closable : true
		});
	}
}
//删除
function deleteAdd() {
	var rows = $('#addBookTable').datagrid('getSelections');
	if(rows.length==0){
		alert("请选择要删除的记录");
		return;
	}else{
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?id=" + n.id;
					else
						ps += "&id=" + n.id;
				});
				$.post('deleteAdd' + ps, function(data) {
					$('#addBookTable').datagrid('cisreload');
					$.messager.alert('提示', data.mes, 'info');
				});
			}
		});
	}
}

	// 表格查询
	function searchAdd() {
		var params = $('#addBookTable').datagrid('options').queryParams; // 先取得
		var fields = $('#queryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#addBookTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件
	function clearForm() {
		$('#queryForm').form('clear');
		searchAdd();
	}
	
	
