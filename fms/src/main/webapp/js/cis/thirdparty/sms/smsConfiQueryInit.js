jQuery(function($) {
	$('#smsTable').datagrid({
		title : '服务器配置列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "querySmsList", // 数据来源
		sortName : 'tcsmsconfi.id', // 排序的列
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
					field : 'tcsmsconfi.smscode',
					title : '短信服务编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.smscode;
					}
				},
				{
					field : 'tcsmsconfi.regname',
					title : 'SMS账户',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.regname;
					}
				},{
					field : 'tcsmsconfi.password',
					title : 'SMS密码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.password;
					}
				},{
					field : 'tcsmsconfi.encoding',
					title : '编码方式',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.encoding;
					}
				},{
					field : 'tcsmsconfi.filfields',
					title : '过滤字段',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.filfields;
					}
				},{
					field : 'tcsmsconfi.breakupword',
					title : '拆分字数',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.breakupword;
					}
				},{
					field : 'tcsmsconfi.secondsend',
					title : '二次发送间隔(s)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.secondsend;
					}
				},{
					field : 'tcsmsconfi.sendmost',
					title : '最多发送次数',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.sendmost;
					}
				},{
					field : 'tcsmsconfi.selfcheck',
					title : '自检时间',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcsmsconfi.selfcheck;
					}
				}]],
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addsmstab('新增服务器配置','addSmsUrl');
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#smsTable').datagrid('getSelections');
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
							"smscode":rows[0].tcsmsconfi.smscode,
							"regname":rows[0].tcsmsconfi.regname,
							"password":rows[0].tcsmsconfi.password,
							"encoding":rows[0].tcsmsconfi.encoding,
							"filfields":rows[0].tcsmsconfi.filfields,
							"breakupword":rows[0].tcsmsconfi.breakupword,
							"secondsend":rows[0].tcsmsconfi.secondsend,
							"sendmost":rows[0].tcsmsconfi.sendmost,
							"selfcheck":rows[0].tcsmsconfi.selfcheck,
							"id":rows[0].tcsmsconfi.id}); 
						addsmstab('更新服务器配置','updateSmsUrl?list='+$.toJSON(dlist));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteSms();
					}
				}, '-'],
				onLoadSuccess : function() {
					$('#smsTable').datagrid('clearSelections'); 
				}
		
	});
	

	
});
	
function addsmstab(title, href) {
	if (href){  
        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
	}else {  
        var content = '未实现';  
    }  
	if($('#smstab').tabs('exists', title)) {
		$('#smstab').tabs('select', title);
        var tab = $('#smstab').tabs('getSelected'); 
		$('#smstab').tabs('update', {
			tab: tab,
			options: {
				title: title,
	            content:content, 
				closable : true
			}
		});
	}else {   
		$('#smstab').tabs('add', {
			title : title,
            content:content, 
			closable : true
		});
	}
}
//删除
function deleteSms() {
	var rows = $('#smsTable').datagrid('getSelections');
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
				$.post('deleteSms' + ps, function(data) {
					$('#smsTable').datagrid('cisreload');
					$.messager.alert('提示', data.mes, 'info');
				});
			}
		});
	}
}

	// 表格查询
	function searchSms() {
		var params = $('#smsTable').datagrid('options').queryParams; // 先取得
		var fields = $('#queryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#smsTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件
	function clearForm() {
		$('#queryForm').form('clear');
		searchSms();
	}
	
	function querySalechnlcode(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#salechnlcode').combobox({
			 url:'querySalechnlcodeComboxList_sms',   
			 valueField:'id',   
			 textField:'salechnlcode'
		});  
	}
	
	function queryServicecode(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#servicecode').combobox({
			 url:'queryServicecodeComboxList_sms',   
			 valueField:'id',   
			 textField:'servicecode'
		});  
	}
