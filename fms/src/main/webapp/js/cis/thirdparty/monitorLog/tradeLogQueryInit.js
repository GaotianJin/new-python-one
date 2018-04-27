jQuery(function($) {
	
	$('#tempfeeno').datebox({  //日期选择框
		// required:true   //是否为录项
	});
	
	$('#bankacc').datebox({  //日期选择框
		// required:true   //是否为录项
	});
	
	$('#salechnlcode').combobox({
		 url:'querySalechnlcodeComboxList_tradelog',   
		 valueField:'salechnlcode',   
		 textField:'salechnlcode'
	});  
	
	$('#transcode').combobox({
		 url:'querytransidComboxList_tradelog',   
		 valueField:'transcode',   
		 textField:'transcode'
	}); 
	 
	$('#logTable').datagrid({
		title : '交易日志查询列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryTradeLog", // 数据来源
		sortName : 'tctransstatus.id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 3
				},{
					field : 'tctransstatus.salechnlcode',
					title : '渠道编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.salechnlcode;
					}
				}, // 显示复选框
				{
					field : 'tctransstatus.transcode',
					title : '交易编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.transcode;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'tctransstatus.servicecode',
					title : '服务编码',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.servicecode;
					}
				}, {
					field : 'tctransstatus.scheduler',
					title : '调度服务',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.scheduler;
					}
				},{
					field : 'tctransstatus.prtno',
					title : '投保单号',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.prtno;
					}
				}, {
					field : 'tctransstatus.polno',
					title : '保单号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.polno;
					}
				}, {
					field : 'tctransstatus.transstatus',
					title : '是否成功',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.transstatus;
					}
				}, {
					field : 'tctransstatus.transdate',
					title : '交易日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.transdate;
					}
				}, {
					field : 'tctransstatus.transtime',
					title : '交易时间',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tctransstatus.transtime;
					}
				}]]
		
	});
	

	
});
	
	
	// 表格查询
	function searchUser() {
		var params = $('#logTable').datagrid('options').queryParams; // 先取得
		var fields = $('#logQueryForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#logTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件
	function clearForm() {
		$('#logQueryForm').form('clear');
		searchUser();
	}
	
//	function querySalechnlcode(){
//		$.messager.alert('提示', "哈哈", 'info');
//		 $('#salechnlcode').combobox({
//			 url:'querySalechnlcodeComboxList_tradelog',   
//			 valueField:'salechnlcode',   
//			 textField:'salechnlcode'
//		});  
//	}
//	
//	function queryServicecode(){
//		$.messager.alert('提示', "哈哈", 'info');
//		 $('#servicecode').combobox({
//			 url:'queryServicecodeComboxList_tradelog',   
//			 valueField:'servicecode',   
//			 textField:'servicecode'
//		});  
//	}