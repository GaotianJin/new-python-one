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
		title : '监控日志查询列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryMonitorLog", // 数据来源
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
					field : 'tcmonitorlog.prtno',
					title : '投保单号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.prtno;
					}
				},{
					field : 'tcmonitorlog.polno',
					title : '保单号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.polno;
					}
				},{
					field : 'tcmonitorlog.exceptionsign',
					title : '是否异常',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.exceptionsign;
					}
				},{
					field : 'tcmonitorlog.monitordate',
					title : '监控日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.monitordate;
					}
				},{
					field : 'tcmonitorlog.monitortime',
					title : '监控时间',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tcmonitorlog.monitortime;
					}
				}
//				,{
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
	
	