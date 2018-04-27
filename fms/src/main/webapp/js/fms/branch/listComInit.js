jQuery(function($) {
	var  selectIndex = -1;
	$('#list_comTable').datagrid({
		title : '机构列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/branch/queryComList", // 数据来源
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
					field : 'comID',
					title : '机构ID',
					width : 100,
					hidden:true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.comId;
					} // 需要formatter一下才能显示正确的数据
				}, // 显示复选框
				{
					field : 'comCode',
					title : '机构代码',
					width : 15,
					sortable : true,
					formatter : function(value, row, index) {
						return  "<a href='#'  onclick=addcomtab('机构明细信息','"+contextPath+"/branch/detailComUrl')>"+row.comCode+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'comName',
					title : '机构名称',
					width : 30,
					sortable : true,
					formatter : function(value, row, index) {
						return row.comName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'parentComId',
					title : '上级机构',
					width : 30,
					sortable : true,
					formatter : function(value, row, index) {
						return row.parentComId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'state',
					title : '机构状态',
					width : 11,
					sortable : true,
					formatter : function(value, row, index) {
						return row.state;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'address',
					title : '机构地址',
					width : 80,
					sortable : true,
					formatter : function(value, row, index) {
						return row.address;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'zipcode',
					title : '邮政编码',
					width : 11,
					sortable : true,
					formatter : function(value, row, index) {
						return row.zipcode;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'phone',
					title : '机构电话',
					width : 15,
					sortable : true,
					formatter : function(value, row, index) {
						return row.phone;
					}
				},{
					field : 'managerName',
					title : '负责人姓名',
					width : 13,
					sortable : true,
					formatter : function(value, row, index) {
						return row.managerName;
					}
				},{
					field : 'managerMobile',
					title : '负责人手机',
					width : 15,
					sortable : true,
					formatter : function(value, row, index) {
						return row.managerMobile;
					}
				}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
					addcomtab('机构信息新增', contextPath+"/branch/addComUrl");
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {updateCom();}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {deleteCom();}
				}, '-'],
		onLoadSuccess : function() {
			$('#list_comTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	
	$('#list_comComCode').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
	
});

	function addcomtab(title, href) 
	{
		$('<div id="comWindow"/>').dialog({
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
	function updateCom()
	{
		var rows = $('#list_comTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', "请选择一个机构进行修改", 'info');
			return;
		}
		if (rows.length > 1) {
			$.messager.alert('提示', "只能选择一个机构修改", 'info');
			return;
		}
		var dlist = [];
		dlist.push({
					"comId" : rows[0].comId,
					"comCode" : rows[0].comCode
				});
		addcomtab('机构信息更新', contextPath+"/branch/updateComUrl?list="+ $.toJSON(dlist));
		
	}
	// 删除
	function deleteCom() {
		var rows = $('#list_comTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', "请选择要删除的机构", 'info');
			return;
		} else {
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?id=" + n.comId;
						else
							ps += "&id=" + n.comId;
					});
					$.post('branch/deleteComUrl' + ps, function(data) {
						$('#list_comTable').datagrid('cisreload');
						$.messager.alert('提示', data.msg, 'info');
						
					});
				}
			});
		}
	}
	
	// 表格查询
	function searchCom() {
		var params = $('#list_comTable').datagrid('options').queryParams; // 先取得
		var fields = $('#list_comForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) 
		{
			params[field.name] = (field.value); // 设置查询参数
		});
		$('#list_comTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件然后进行查询
	function clearComForm() {
		$('#list_comForm').form('clear');
		searchCom();
	}
	function clearComFormList() {
		$('#list_comForm').form('clear');
	}