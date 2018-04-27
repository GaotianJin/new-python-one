jQuery(function($) {
			$('#searchresultTable').datagrid({
				title : '微博列表', // 标题
				method : 'post',
				iconCls : 'icon-edit', // 图标
				singleSelect : false, // 多选
				height : 550, // 高度
				fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped : true, // 奇偶行颜色不同
				collapsible : true,// 可折叠
				url : "queryList", // 数据来源
				sortName : 'user.Name', // 排序的列
				sortOrder : 'asc', // 倒序
				remoteSort : true, // 服务器端排序
				idField : 'pid', // 主键字段
				queryParams : {}, // 查询条件
				pagination : true, // 显示分页
				rownumbers : true, // 显示行号
				columns : [[{
							field : 'ck',
							checkbox : true,
							width : 2
						}, // 显示复选框
						{
							field : 'id',
							title : '微博ID',
							width : 100,
							sortable : true,
							formatter : function(value, row, index) {
								return "<a href='javacript:;' onclick='showProfile("
										+ row.user.id
										+ ");'>"
										+ row.user.id
										+ "</a>";
							} // 需要formatter一下才能显示正确的数据
						}, {
							field : 'name',
							title : '微博名称',
							width : 120,
							sortable : true,
							formatter : function(value, row, index) {
								return row.user.name;
							}
						}, {
							field : 'text',
							title : '微博',
							width : 1500,
							sortable : true,
							formatter : function(value, row, index) {
								return row.status.text;
							}
						}]],
				toolbar : '#tb',
				onLoadSuccess : function() {
					$('#searchresultTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				}
			});
		});

function showProfile(id) {
	$('#dd').dialog({
				title : '个人资料',
				width : 600,
				height : 400,
				closed : false,
				cache : true,
				modal : true
			});
	$('#dialogIframe')[0].src = "showProfileUrl?id="+id+"";
}

// 表格查询
function searchResult() {
	var params = $('#searchresultTable').datagrid('options').queryParams; // 先取得
	// datagrid
	// 的查询参数
	var fields = $('#searchresultForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
				params[field.name] = (field.value); // 设置查询参数
			});
	$('#searchresultTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}
// 清空查询条件
function clearResult() {
	$('#searchresultForm').form('clear');
	searchResult();
}

function addFriends() {
	$.post('addFriends', function(data) {
				$('#searchresultTable').datagrid('cisreload');
				$.messager.alert('提示', data.mes, 'info');
			});
}

function searchWeibo() {
	$.post('searchWeibo', function(data) {
				$('#searchresultTable').datagrid('cisreload');
				$.messager.alert('提示', data.mes, 'info');
			});
}

function searchKeyword() {
	var keyword = $("[name='keyword']").val();
	$.post('searchKeyword?keyword=' + keyword, function(data) {
				$('#searchresultTable').datagrid('cisreload');
				$.messager.alert('提示', data.mes, 'info');
			});
}

function analysis() {
	var params = $('#searchresultTable').datagrid('options').queryParams; // 先取得
	// datagrid
	// 的查询参数
	var fields = $('#searchresultForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
				params[field.name] = '薛蛮子'; // 设置查询参数ZiJun_Jiang
			});
	$('#searchresultTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}

function directmessage() {
	var rows = $('#searchresultTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择要发送私信的微博ID");
		return;
	} else {
		$.messager.confirm('提示', '确定要发送私信吗?', function(result) {
					if (result) {
						var ps = "";
						$.each(rows, function(i, n) {
									if (i == 0) {
										ps += "?pid=" + n.pid;
									} else {
										ps += "&pid=" + n.pid;
									}
								});
						$.post('directmessage' + ps, function(data) {
									$('#searchresultTable').datagrid('cisreload');
									$.messager.alert('提示', data.mes, 'info');
								});
					}
				});
	}

}

function comment() {
	var rows = $('#searchresultTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择要发送评论的微博ID");
		return;
	} else {
		$.messager.confirm('提示', '确定要发送评论吗?', function(result) {
					if (result) {
						var ps = "";
						$.each(rows, function(i, n) {
									if (i == 0) {
										ps += "?pid=" + n.status.id;
									} else {
										ps += "&pid=" + n.status.id;
									}
								});
						$.post('comment' + ps, function(data) {
									$('#searchresultTable').datagrid('cisreload');
									$.messager.alert('提示', data.mes, 'info');
								});
					}
				});
	}

}
