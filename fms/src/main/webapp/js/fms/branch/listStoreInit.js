jQuery(function($) {
	$('#list_storeComCode').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
	/* $('#list_buildingId').combobox({
         url:contextPath+'/branch/queryBuildListCode',
         valueField:'id',
         textField:'name'
     });*/
	
	$("#list_type").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=storeType',
		valueField:'code',
		textField:'codeName'
		//value:'01'
	});
	var  selectIndex = -1;
	$('#list_storeTable').datagrid({
		title : '网点列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/branch/queryStoreList", // 数据来源
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
				}, // 显示复选框
				{
					field : 'storeID',
					title : '网点ID',
					width : 100,
					hidden:true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.storeId;
					} // 需要formatter一下才能显示正确的数据
				}, // 显示复选框
				{
					field : 'storeCode',
					title : '网点代码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return  "<a href='#'  onclick=addStoretab('网点明细信息','"+contextPath+"/branch/detailStoreUrl')>"+row.storeCode+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'storeName',
					title : '网点名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.storeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'parentComId',
					title : '上级机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.parentComId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'storeState',
					title : '网点状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.storeState;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'storeType',
					title : '网点类型',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.storeType;
					} // 需要formatter一下才能显示正确的数据
				},/*{
					field : 'row.buildingName',
					title : '所在楼盘',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.buildingName;
					}
				},*/{
					field : 'phone',
					title : '网点电话',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.phone;
					}
				},{
					field : 'managerName',
					title : '负责人姓名',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.managerName;
					}
				},{
					field : 'managerMobile',
					title : '负责人手机',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.managerMobile;
					}
				}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addStoretab('网点信息新增', contextPath+"/branch/addStoreUrl");
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {updateStore();}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {deleteStore();}
				}, '-'],
		onLoadSuccess : function() {
			$('#list_storeTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow: function (rowIndex, rowData) {
            if(selectIndex==rowIndex){
            	//第一次单击选中,第二次单击取消选中
            	$(this).datagrid('unselectRow', rowIndex);
            	selectIndex=-1;
            }else{
            	selectIndex = rowIndex;
            }
		}
	});
	
});

	function addStoretab(title, href) 
	{
		$('<div id="storeWindow"/>').dialog({
			href : href,
			modal : true,
			title : title,
			fit : true, 
			inline : false,
			minimizable : false,
			onClose : function() {
				$(this).window('destroy');
			}
		});
	}
	//更新
	function updateStore()
	{
		var rows = $('#list_storeTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', "请选择一个网点进行修改", 'info');
			return;
		}
		if (rows.length > 1) {
			$.messager.alert('提示', "只能选择一个网点修改", 'info');
			return;
		}
		var dlist = [];
		dlist.push({
					"storeId" : rows[0].comId,
					"storeCode" : rows[0].comCode
				});
		addStoretab('网点信息更新', contextPath+"/branch/updateStoreUrl?list="+ $.toJSON(dlist));
		
	}
	// 删除
	function deleteStore() {
		var rows = $('#list_storeTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', "请选择要删除的网点", 'info');
			return;
		} else {
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?id=" + n.storeId;
						else
							ps += "&id=" + n.storeId;
					});
					$.post('branch/deleteStoreUrl' + ps, function(data) {
						clearStoreForm();
						$.messager.alert('提示', data.msg, 'info');
					});
				}
			});
		}
	}
	
	// 表格查询
	function searchStore() {
		var params = $('#list_storeTable').datagrid('options').queryParams; // 先取得
		var fields = $('#list_storeForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) 
		{
			params[field.name] = (field.value); // 设置查询参数
		});
		$('#list_storeTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件然后进行查询
	function clearStoreForm() {
		$('#list_storeForm').form('clear');
		searchStore();
	}
	// 清空查询条件然后进行查询
	function clearStoreFormList() {
		$('#list_storeForm').form('clear');
	}
	
	