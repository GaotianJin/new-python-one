jQuery(function($) {
		var  selectIndex = -1;
		$('#menuTable').datagrid({
			title : '菜单列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : true, //多选
			//height : 550, //高度
			fitColumns : true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : contextPath+"/menu/queryList", //数据来源
			remoteSort : true, //服务器端排序
			idField : 'pid', //主键字段
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
				field : 'privilegeId',
				title : '菜单ID',
				width : 100,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.privilegeId;
				}
			}, {
				field : 'privilegeCode',
				title : '菜单编码',
				width : 70,
				sortable : true,
				formatter : function(value, row, index) {
					return row.privilegeCode;
				}
			},{
				field : 'privilegeName',
				title : '菜单名称',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.privilegeName;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'url',
				title : '路径',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.url;
				}
			} ] ],
			toolbar : [{
						text : '新增',
						iconCls : 'icon-add',
						handler : function() {
						addMenutab('菜单新增', contextPath+"/menu/addMenuUrl");
						}
					}, '-', {
						text : '更新',
						iconCls : 'icon-edit',
						handler : function() {updateMenu();}
					}, '-', {
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {deleteMenu();}
					}, '-'],
			onLoadSuccess : function() {
				$('#menuTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	function addMenutab(title,path) 
	{
		$('<div id="menuWindow"/>').dialog({
			href : path,
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
	function updateMenu() {
		var rows = $('#menuTable').datagrid('getSelections');
					if(rows.length==0){
						$.messager.alert("提示","请选择一个菜单进行修改");
						return;
					}
					if(rows.length>1){
						$.messager.alert("提示","只能选择一个菜单修改");
						return;
					}
					var dlist = [];
					var url = "";
					if(null!=rows[0].url){
						url = rows[0].url;
					}
					var system = "";
					if(null!=rows[0].method){
						system = rows[0].method;
					}
					dlist.push({"name":rows[0].privilegeName,"url":url,"system":system}); 
					addMenutab('更新菜单',contextPath+'/menu/updateMenuUrl?list='+encodeURI($.toJSON(dlist)))
	}
	//删除
	function deleteMenu() {
		var rows = $('#menuTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert("提示","请选择要删除的菜单");
			return;
		}else{
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0){
							ps += "?pid=" + n.privilegeId;
						}else{
							ps += "&pid=" + n.privilegeId;
						}
					});
					$.post(contextPath+'/menu/deleteMenu' + ps, function(data) {
						$('#menuTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	//表格查询
	function searchMenu() {
		var params = $('#menuTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#menuForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#menuTable').datagrid('cisreload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearMenu() {
		$('#menuForm').form('clear');
		searchMenu();
	}
	function clearMenuFormList() {
		$('#menuForm').form('clear');
	}