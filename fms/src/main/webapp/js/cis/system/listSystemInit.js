jQuery(function($) {
		$('#systemTable').datagrid({
			title : '应用列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 550, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryList", //数据来源
			sortName : 'privilege.id', //排序的列
			sortOrder : 'asc', //倒序
			remoteSort : true, //服务器端排序
			idField : 'pid', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框
			{
				field : 'privilegename',
				title : '应用名称',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.privilege.privilegename;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'privilegecode',
				title : '应用编码',
				width : 70,
				sortable : true,
				formatter : function(value, row, index) {
					return row.privilege.privilegecode;
				}
			} ] ],
			toolbar : '#tb',
			onLoadSuccess : function() {
				$('#systemTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	});
	function addSystemtab(title, href) {
		if (href){  
	        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
		}else {  
            var content = '未实现';  
        }  
		if($('#systemtab').tabs('exists', title)) {
			$('#systemtab').tabs('select', title);
	        var tab = $('#systemtab').tabs('getSelected'); 
			$('#systemtab').tabs('update', {
				tab: tab,
				options: {
					title: title,
		            content:content, 
					closable : true
				}
			});
		}else {   
			$('#systemtab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
	}
	//更新
	function updateSystem() {
		var rows = $('#systemTable').datagrid('getSelections');
					if(rows.length==0){
						alert("请选择一个应用进行修改");
						return;
					}
					if(rows.length>1){
						alert("只能选择一个应用修改");
						return;
					}
					var dlist = [];
					dlist.push({
									"name" : rows[0].privilege.privilegename,
									"code" : rows[0].privilege.privilegecode,
									"id" : rows[0].privilege.id
								});
					parent.addtab('更新应用', 'system/updateSystemUrl?list='+$.toJSON(dlist));
	}
	//删除
	function deleteSystem() {
		var rows = $('#systemTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择要删除的应用");
			return;
		}else{
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0){
							ps += "?pid=" + n.pid;
						}else{
							ps += "&pid=" + n.pid;
						}
					});
					$.post('deleteSystem' + ps, function(data) {
						$('#systemTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	//表格查询
	function searchSystem() {
		var params = $('#systemTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#systemForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#systemTable').datagrid('cisreload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearSystem() {
		$('#systemForm').form('clear');
		searchSystem();
	}