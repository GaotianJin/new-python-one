jQuery(function($) {
	 
	$('#emailTable').datagrid({
		title : '服务器配置列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryEmailList", // 数据来源
		sortName : 'tcemailconfi.id', // 排序的列
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
					field : 'tcemailconfi.emailcode',
					title : '邮件服务编码',
					width : 70,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.emailcode;
					}
				},{
					field : 'tcemailconfi.regname',
					title : '邮箱账号',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.regname;
					}
				},{
					field : 'tcemailconfi.password',
					title : '邮箱密码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.password;
					}
				},{
					field : 'tcemailconfi.encoding',
					title : '编码方式',
					width : 70,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.encoding;
					}
				},{
					field : 'tcemailconfi.secondsend',
					title : '二次发送间隔(s)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.secondsend;
					}
				},{
					field : 'tcemailconfi.sendmost',
					title : '最多发送次数',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.sendmost;
					}
				},{
					field : 'tcemailconfi.selfcheck',
					title : '自检时间',
					width : 70,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.selfcheck;
					}
				},{
					field : 'tcemailconfi.note',
					title : '备注',
					width : 260,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcemailconfi.note;
					}
				}]],
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addemailtab('新增配置','addEmailUrl');
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#emailTable').datagrid('getSelections');
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
							"emailcode":rows[0].tcemailconfi.emailcode,
							"regname":rows[0].tcemailconfi.regname,
							"password":rows[0].tcemailconfi.password,
							"encoding":rows[0].tcemailconfi.encoding,
							"secondsend":rows[0].tcemailconfi.secondsend,
							"sendmost":rows[0].tcemailconfi.sendmost,
							"selfcheck":rows[0].tcemailconfi.selfcheck,
							"note":rows[0].tcemailconfi.note,
							"id":rows[0].tcemailconfi.id}); 
						addemailtab('更新配置','updateEmailUrl?list='+$.toJSON(dlist));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteEmail();
					}
				}, '-'],
				onLoadSuccess : function() {
					$('#emailTable').datagrid('clearSelections'); 
				}
		
	});
	

	
});
	
function addemailtab(title, href) {
	if (href){  
        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
	}else {  
        var content = '未实现';  
    }  
	if($('#emailtab').tabs('exists', title)) {
		$('#emailtab').tabs('select', title);
        var tab = $('#emailtab').tabs('getSelected'); 
		$('#emailtab').tabs('update', {
			tab: tab,
			options: {
				title: title,
	            content:content, 
				closable : true
			}
		});
	}else {   
		$('#emailtab').tabs('add', {
			title : title,
            content:content, 
			closable : true
		});
	}
}
//删除
function deleteEmail() {
	var rows = $('#emailTable').datagrid('getSelections');
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
				$.post('deleteEmail' + ps, function(data) {
					$('#emailTable').datagrid('cisreload');
					$.messager.alert('提示', data.mes, 'info');
				});
			}
		});
	}
}

	// 表格查询
	function searchEmail() {
		var params = $('#emailTable').datagrid('options').queryParams; // 先取得
		var fields = $('#queryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#emailTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件
	function clearForm() {
		$('#queryForm').form('clear');
		searchEmail();
	}
	
	function querySalechnlcode(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#salechnlcode').combobox({
			 url:'querySalechnlcodeComboxList_email',   
			 valueField:'id',   
			 textField:'salechnlcode'
		});  
	}
	
	function queryTranscode(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#transcode').combobox({
			 url:'queryTranscodeComboxList_email',   
			 valueField:'id',   
			 textField:'transcode'
		});  
	}
