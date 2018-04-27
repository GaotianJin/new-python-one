jQuery(function($) {
	
	$('#messnumber').combobox({
		 url:'queryMessnumberComboxList_info',   
		 valueField:'id',   
		 textField:'messnumber'
	});  
	
	$('#abnonumber').combobox({
		 url:'queryAbnonumberComboxList_info',   
		 valueField:'id',   
		 textField:'abnonumber'
	}); 
	
	 
	$('#infoTable').datagrid({
		title : '提示信息列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryNoticeInfoList", // 数据来源
		sortName : 'tcnoticeinfo.id', // 排序的列
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
					field : 'tcnoticeinfo.messnumber',
					title : '信息编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcnoticeinfo.messnumber;
					}
				},{
					field : 'tcnoticeinfo.abnonumber',
					title : '异常编号',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcnoticeinfo.abnonumber;
					}
				},{
					field : 'tcnoticeinfo.theme',
					title : '信息主题',
					width : 200,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcnoticeinfo.theme;
					}
				},{
					field : 'tcnoticeinfo.infocontent',
					title : '提示内容',
					width : 550,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcnoticeinfo.infocontent;
					}
				}]],
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addInfotab('新增提示信息','addNoticeInfoUrl');
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#infoTable').datagrid('getSelections');
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
							"messnumber":rows[0].tcnoticeinfo.messnumber,
							"abnonumber":rows[0].tcnoticeinfo.abnonumber,
							"theme":rows[0].tcnoticeinfo.theme,
							"infocontent":rows[0].tcnoticeinfo.infocontent,
							"id":rows[0].tcnoticeinfo.id}); 
						addInfotab('更新提示信息','updateNoticeInfoUrl?list='+$.toJSON(dlist));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteInfo();
					}
				}, '-'],
				onLoadSuccess : function() {
					$('#infoTable').datagrid('clearSelections'); 
				}
		
	});
	

	
});
	
function addInfotab(title, href) {
	if (href){  
        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
	}else {  
        var content = '未实现';  
    }  
	if($('#noticeInfotab').tabs('exists', title)) {
		$('#noticeInfotab').tabs('select', title);
        var tab = $('#noticeInfotab').tabs('getSelected'); 
		$('#noticeInfotab').tabs('update', {
			tab: tab,
			options: {
				title: title,
	            content:content, 
				closable : true
			}
		});
	}else {   
		$('#noticeInfotab').tabs('add', {
			title : title,
            content:content, 
			closable : true
		});
	}
}
//删除
function deleteInfo() {
	var rows = $('#infoTable').datagrid('getSelections');
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
				$.post('deleteInfo' + ps, function(data) {
					$('#infoTable').datagrid('cisreload');
					$.messager.alert('提示', data.mes, 'info');
				});
			}
		});
	}
}

	// 表格查询
	function searchInfo() {
		var params = $('#infoTable').datagrid('options').queryParams; // 先取得
		var fields = $('#queryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#infoTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	
	// 清空查询条件
	function clearForm() {
		$('#queryForm').form('clear');
		searchInfo();
	}
	
	function queryMessnumber(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#messnumber').combobox({
			 url:'queryMessnumberComboxList_info',   
			 valueField:'id',   
			 textField:'messnumber'
		});  
	}
	
	function queryabnonumber(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#abnonumber').combobox({
			 url:'queryAbnonumberComboxList_info',   
			 valueField:'id',   
			 textField:'abnonumber'
		});  
	}
