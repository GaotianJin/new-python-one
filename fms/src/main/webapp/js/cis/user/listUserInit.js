jQuery(function($) {
	var  selectIndex = -1;
	$('#userTable').datagrid({
		title : '用户列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/user/queryList", // 数据来源
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
					field : 'row.userId',
					title : '用户ID',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.userId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.userCode',
					title : '用户登陆账号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return  "<a href='#'  onclick=addusertab('用户明细信息','"+contextPath+"/user/detailUserUrl')>"+row.userCode+"</a>";
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'row.userName',
					title : '用户名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.userName;
					}
				},{
					field : 'row.comId',
					title : '所属机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.comId;
					}
				},/*{
					field : 'row.storeId',
					title : '所属网点',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.storeId;
					}
				},*/{
					field : 'row.departmentId',
					title : '所属部门',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.departmentId;
					}
				},{
					field : 'row.state',
					title : '用户状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.state;
					}
				},{
					field : 'row.validatestartDate',
					title : '有效开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.validatestartDate;
					}
				}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addusertab('新增用户', contextPath+"/user/addUserUrl");
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#userTable').datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示', '请选择一个用户更新', 'info');
							return;
						}
						if (rows.length > 1) {
							$.messager.alert('提示', '只能选择一个用户更新', 'info');
							return;
						}
						var dlist = [];
						addusertab('更新用户', contextPath+"/user/updateUserUrl");
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteUser();
					}
				}, '-', {
					text : '分配角色',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#userTable').datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示', '请选择一个用户分配', 'info');
							return;
						}
						if (rows.length > 1) {
							$.messager.alert('提示', '只能选择一个用户分配', 'info');
							return;
						}
						var dlist = [];
						dlist.push({
									"name" : rows[0].userName,
									"code" : rows[0].userCode,
									"id" : rows[0].userId
								});
						addusertab('分配角色', contextPath+"/user/setUserUrl?list=" + encodeURI($.toJSON(dlist)));
					}
				},'-',{
					text:'密码重置',
					iconCls:'icon-apply',
					handler:function()
					{
						resetPassWord();
					}
				} ,'-'],
		onLoadSuccess : function() {
			$('#userTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	
	
	
	$('#listuser_comId').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
	
});
	function addusertab(title, href) {
		$('<div id="userWindow"/>').dialog({
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

	// 删除
	function deleteUser() {
		var rows = $('#userTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', '请选择要删除的用户', 'info');
			return;
		} else {
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?id=" + n.userId;
						else
							ps += "&id=" + n.userId;
					});
					$.post('user/deleteUser' + ps, function(data) {
						$.messager.alert('提示', data.mes, 'info');
						clearUserForm();
					});
				}
			});
		}
	}
	//重置密码
	function resetPassWord(){
		var rows = $('#userTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', '请选择要重置密码的用户', 'info');
			return;
		} else {
			$.messager.confirm('提示', '确定要重置密码吗? 密码重置后将默认变为888888！', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?id=" + n.userId;
						else
							ps += "&id=" + n.userId;
					});
					$.post('user/resetPwd' + ps, function(data) {
						$.messager.alert('提示', data.mes, 'info');
						clearUserForm();
					});
				}
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
	function clearUserForm() {
		$('#userForm').form('clear');
		searchUser();
	}
	
	function clearUserFormList() {
		$('#userForm').form('clear');
	}