jQuery(function($) {
		var  selectIndex = -1;
		$("#rolePrivilege").combobox({
			url:contextPath+'/codeQuery/tdCodeQuery?codeType=rolePrivilege',
			valueField:'code',
			textField:'codeName'
		});
	
	
		$('#roleTable').datagrid({
			title : '角色列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : true, //多选
			//height : 380, //高度
			fitColumns : true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : contextPath+"/role/queryList", //数据来源
			sortName : 'role.id', //排序的列
			sortOrder : 'asc', //正序
			remoteSort : true, //服务器端排序
			idField : 'rid', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			pageList:[5,10,15,20],
			pageSize:10,
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框
			{
				field : 'roleId',
				title : '角色ID',
				width : 100,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.roleId;
				} //需要formatter一下才能显示正确的数据
			}, //显示复选框
			{
				field : 'roleName',
				title : '角色名称',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.roleName;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'roleCode',
				title : '角色编码',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.roleCode;
				}
			}, {
				field : 'rolePrivilegeId',
				title : '角色查询权限Id',
				width : 100,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.rolePrivilegeId;
				}
			}, {
				field : 'rolePrivilege',
				title : '角色查询权限',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.rolePrivilege;
				}
			} ] ],
			toolbar : [ {
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					addroletab('新增角色',contextPath+"/role/addRoleUrl");
				}
			}, '-', {
				text : '更新',
				iconCls : 'icon-edit',
				handler : function() {
					var rows = $('#roleTable').datagrid('getSelections');
					if(rows.length==0){
						$.messager.alert("提示","请选择一个角色进行修改");
						return;
					}
					if(rows.length>1){
						$.messager.alert("提示","只能选择一个角色修改");
						return;
					}
					var dlist = [];
					dlist.push({"roleName":rows[0].roleName,"roleCode":rows[0].roleCode,"roleId":rows[0].roleId,"rolePrivilegeId":rows[0].rolePrivilegeId}); 
					addroletab('更新角色',contextPath+"/role/updateRoleUrl?list="+encodeURI($.toJSON(dlist)));
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					deleteRole();
				}
			}, '-', {
				text : '分配菜单',
				iconCls : 'icon-edit',
				handler : function() {
					var rows = $('#roleTable').datagrid('getSelections');
					if(rows.length==0){
						$.messager.alert("提示","请选择一个角色分配");
						return;
					}
					if(rows.length>1){
						$.messager.alert("提示","只能选择一个角色分配");
						return;
					}
					var dlist = [];
					dlist.push({"name":rows[0].roleName,"code":rows[0].roleCode,"id":rows[0].roleId}); 
					addroletab('分配菜单',contextPath+"/role/setRoleUrl?list="+encodeURI($.toJSON(dlist)));
				}
			}, '-'],
			onLoadSuccess : function() {
				$('#roleTable').datagrid('clearSelections'); 
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
	function addroletab(title, href) {
		$('<div id="roleWindow"/>').dialog({
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
	//删除
	function deleteRole() {
		var rows = $('#roleTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert("提示","请选择要删除的角色");
			return;
		}else{
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?rid=" + n.roleId;
						else
							ps += "&rid=" + n.roleId;
					});
					$.post(contextPath+"/role/deleteRole" + ps, function(data) {
						$('#roleTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	//表格查询
	function searchRole() {
		var params = $('#roleTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#listRoleForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#roleTable').datagrid('cisreload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearRole() {
		$('#listRoleForm').form('clear');
		searchRole();
	}
	function clearRoleFormList() {
		$('#listRoleForm').form('clear');
	}