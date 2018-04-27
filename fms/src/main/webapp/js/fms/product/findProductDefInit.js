jQuery(function($) {
	//(财富类)费用比例信息
	$('#wealthRateTable').datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryListWeathRateUrl", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
					field : 'row.companyName',
					title : '费用类型',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				},
				{
					field : 'row.companyName',
					title : '认购金额上限',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '认购金额上限',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '认购金额下限',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '费用率(%)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '客户预期收益率',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#wealthRateTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});	
//(保险类)费用比例信息
	$('#insuraceRateTable').datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryListInsuranceRateUrl", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
					field : 'row.companyName',
					title : '费用类型',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				},
				{
					field : 'row.companyName',
					title : '参数类型',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '参数(数值)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '参数(数值单位)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '保费上限',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '费用率(%)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '执行开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '执行结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				,
				{
					field : 'row.companyName',
					title : '执行状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#insuraceRateTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});	

	
});
	
	function addusertab(title, href) {
		if (href){  
	        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
		}else {  
            var content = '未实现';  
        }  
		if($('#usertab').tabs('exists', title)) {
			$('#usertab').tabs('close', title);
			$('#usertab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
		else{
			$('#usertab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
	}
	// 表格查询
	function searchUser() {
		var params = $('#userTable').datagrid('options').queryParams; // 先取得
		var fields = $('#userForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#userTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件然后进行查询
	function clearForm() {
		$('#userForm').form('clear');
		searchUser();
	}
	