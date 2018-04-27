jQuery(function($) {
		$('#schedulerTable').datagrid({
			title : '任务列表', //标题
			method : 'post',
			singleSelect : false, //多选
			height : 300, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : contextPath + "/quartz/queryList", // 数据来源
			sortName : 'job_name', //排序的列
			sortOrder : 'asc', //倒序
			remoteSort : true, //服务器端排序
			idField : 'trigger_name', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框
			{
				field : 'job_name',
				title : '任务名称',
				width : 170,
				sortable : true,
				formatter : function(value, row, index) {
					return row.job_name;
				}
			}, {
				field : 'next_fire_time',
				title : '下次执行时间',
				width : 140,
				sortable : true,
				formatter : function(value, row, index) {
					return row.next_fire_time;
				}
			}, {
				field : 'prev_fire_time',
				title : '上次执行时间',
				width : 140,
				sortable : true,
				formatter : function(value, row, index) {
					return row.prev_fire_time;
				}
			}, {
				field : 'trigger_state',
				title : '执行状态',
				width : 70,
				sortable : true,
				formatter : function(value, row, index) {
					return row.trigger_state;
				}
			}, {
				field : 'trigger_type',
				title : '类型',
				width : 70,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.trigger_type;
				}
			}, {
				field : 'start_time',
				title : '开始时间',
				width : 140,
				sortable : true,
				formatter : function(value, row, index) {
					return row.start_time;
				}
			}, {
				field : 'end_time',
				title : '结束时间',
				width : 140,
				sortable : true,
				formatter : function(value, row, index) {
					return row.end_time;
				}
			}, {
				field : 'cron_expression',
				title : 'cron表达式',
				width : 140,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.cron_expression;
				}
			}, {
				field : 'repeat_count',
				title : '计划执行次数',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.repeat_count;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'repeat_internal',
				title : '执行间隔（单位：秒）',
				width : 140,
				sortable : true,
				formatter : function(value, row, index) {
					return row.repeat_internal;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'times_triggered',
				title : '已执行次数',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.times_triggered;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'trigger_name',
				title : '调度名称',
				width : 60,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.trigger_name;
				} //需要formatter一下才能显示正确的数据
			}  ] ],
			toolbar : [{
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					addScheduleWindow();
				}
			}, '-', {
				text : '暂停',
				iconCls : 'icon-edit',
				handler : function() {
					pauseScheduler();
				}
			}, '-', {
				text : '恢复',
				iconCls : 'icon-edit',
				handler : function() {
					resumeScheduler();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					deleteScheduler();
				}
			}, '-'],
			onLoadSuccess : function() {
				$('#schedulerTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	});
	

	function addScheduleWindow(){
		$('<div id="addScheduleWindow"/>').dialog({
			href : contextPath+"/quartz/addSchedulerUrl",
			//type : "post",
			modal : true,
			title : "新增任务配置",
			//fit : true, 
			width:800,
			height:500,
			inline : false,
			minimizable : false,
			onClose : function() {
				$(this).window('destroy');
			}
		});
	}

	
	
	
	//删除
	function deleteScheduler() {
		var rows = $('#schedulerTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择要删除的调度");
			return;
		}else{
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0){
							ps += "?trigger_name=" + escape(n.trigger_name);
						}else{
							ps += "&trigger_name=" + escape(n.trigger_name);
						}
					});
					$.post(contextPath+'/quartz/deleteSchedulerUrl' + ps, function(data) {
						$('#schedulerTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	
	//暂停
	function pauseScheduler() {
		var rows = $('#schedulerTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择要暂停的调度");
			return;
		}else{
			$.messager.confirm('提示', '确定要暂停吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0){
							ps += "?trigger_name=" + escape(n.trigger_name);
						}else{
							ps += "&trigger_name=" + escape(n.trigger_name);
						}
					});
					$.post(contextPath+'/quartz/pauseSchedulerUrl' + ps, function(data) {
						$('#schedulerTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	
	//恢复
	function resumeScheduler() {
		var rows = $('#schedulerTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择要恢复的调度");
			return;
		}else{
			$.messager.confirm('提示', '确定要恢复吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0){
							ps += "?trigger_name=" + escape(n.trigger_name);
						}else{
							ps += "&trigger_name=" + escape(n.trigger_name);
						}
					});
					
					
					$.post(contextPath+'/quartz/resumeSchedulerUrl' + ps, function(data) {
						$('#schedulerTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	
	//表格查询
	function searchScheduler() {
		var params = $('#schedulerTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#schedulerForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#schedulerTable').datagrid('cisreload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearScheduler() {
		$('#schedulerForm').form('clear');
		searchScheduler();
	}