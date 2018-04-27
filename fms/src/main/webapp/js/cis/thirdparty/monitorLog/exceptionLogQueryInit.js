jQuery(function($) {
	
	$('#monitortime').datebox({  //日期选择框--
		// required:true   //是否为录项
	});
	
	$('#activesign').datebox({  //日期选择框
		// required:true   //是否为录项
	});
	
	$('#salechnlcode').combobox({
		 url:'querySalechnlcodeComboxList_monitor',   
		 valueField:'salechnlcode',   
		 textField:'salechnlcode'
	});  
	
	$('#transcode').combobox({
		 url:'queryTranscodeComboxList_monitor',   
		 valueField:'transcode',   
		 textField:'transcode'
	}); 
	
	$('#monitorpoint').combobox({
		 url:'querymonitorcodeComboxList_monitorlog',   
		 valueField:'monitorcode',   
		 textField:'monitorcode'
	}); 
	
	$('#scheduler').combobox({			 
		 url:'../ldcode/queryLdCode',   
		 valueField:'Code',   
		 textField:'Codename',
		onBeforeLoad: function(param){
			param.codetype = 'scheduler';
		}
	});
	 
	$('#logTable').datagrid({
		title : '异常日志查询列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryExceptionLog", // 数据来源
		sortName : 'tcmonitorlog.id', // 排序的列
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
				}, // 显示复选框
				 {
					field : 'tcmonitorlog.salechnlcode',
					title : '渠道编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.salechnlcode;
					}
				},{
					field : 'tcmonitorlog.servicecode',
					title : '服务编码',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.servicecode;
					}
				},{
					field : 'tcmonitorlog.scheduler',
					title : '调度服务',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.scheduler;
					}
				},{
					field : 'tcmonitorlog.monitorpoint',
					title : '监控点',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.monitorpoint;
					}
				},{
					field : 'tcmonitorlog.exceptioncode',
					title : '异常代码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.exceptioncode;
					}
				},{
					field : 'tcmonitorlog.exceptioncontent',
					title : '异常内容',
					width : 300,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.exceptioncontent;
					}
				}
//				,{
//					field : 'tcmonitorlog.maketime',
//					title : '异常次数',
//					width : 100,
//					sortable : true,
//					formatter : function(value, row, index) {
//						return row.tcmonitorlog.maketime;
//					}
//				},{
//					field : 'tcmonitorlog.activesign',
//					title : '活跃标志',
//					width : 100,
//					sortable : true,
//					formatter : function(value, row, index) {
//						return row.tcmonitorlog.activesign;
//					}
//				}
				]]
		
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
	
	function querySalechnlcode(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#salechnlcode').combobox({
			 url:'querySalechnlcodeComboxList_exceptionlog',   
			 valueField:'salechnlcode',   
			 textField:'salechnlcode'
		});  
	}
	
	function queryServicecode(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#servicecode').combobox({
			 url:'queryServicecodeComboxList_exceptionlog',   
			 valueField:'servicecode',   
			 textField:'servicecode'
		});  
	}
	
	function queryMonitorpoint(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#monitorpoint').combobox({
			 url:'queryMonitorpointComboxList_exceptionlog',   
			 valueField:'monitorpoint',   
			 textField:'monitorpoint'
		});  
	}
	
	function queryScheduler(){
		$.messager.alert('提示', "哈哈", 'info');
		 $('#scheduler').combobox({
			 url:'querySchedulerComboxList_exceptionlog',   
			 valueField:'scheduler',   
			 textField:'scheduler'
		});  
	}