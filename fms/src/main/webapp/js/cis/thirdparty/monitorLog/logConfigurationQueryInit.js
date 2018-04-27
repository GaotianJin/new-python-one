jQuery(function($) {
	
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
	
	$('#servicecode').combobox({
		 url:'queryServicecodeComboxList_monitor',   
		 valueField:'servicecode',   
		 textField:'servicecode' 
	}); 
	
		$('#monitorTable').datagrid({
			title : '监控配置列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 350, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryMonitorList", //数据来源
			sortName : 'tcmonitor.id', //排序的列
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
				field : 'tcmonitor.id',
				title : '流水号',
				width : 40,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.id;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'tcmonitor.salechnlcode',
				title : '渠道编码',
				width : 70,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.salechnlcode;
				} 
			}, {
				field : 'tcmonitor.transcode',
				title : '交易编码',
				width : 70,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.transcode;
				} 
			}, {
				field : 'tcmonitor.servicecode',
				title : '服务编码',
				width : 70,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.servicecode;
				}
			} , {
				field : 'tcmonitor.confiobject',
				title : '监控点',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.confiobject;
				}
			} , {
				field : 'tcmonitor.ismonitor',
				title : '是否监控',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.ismonitor;
				}
			} , {
				field : 'tcmonitor.smsflag',
				title : '短信通知',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.smsflag;
				}
			} , {
				field : 'tcmonitor.emailflag',
				title : '邮件通知',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.emailflag;
				}
			} , {
				field : 'tcmonitor.smsserver',
				title : '短信服务器编码',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.smsserver;
				}
			} , {
				field : 'tcmonitor.smsreceiver',
				title : '短信接收方编码',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.smsreceiver;
				}
			} , {
				field : 'tcmonitor.emailserver',
				title : '邮件服务器编码',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.emailserver;
				}
			} , {
				field : 'tcmonitor.emalreceiver',
				title : '邮件服务器方编码',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.emalreceiver;
				}
			} , {
				field : 'tcmonitor.maxwaittime',
				title : '允许最大等待时间',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tcmonitor.maxwaittime;
				}
			}] ],
			toolbar : [ {
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					addmonitortab('新增监控配置','addMonitorUrl');
				}
			}, '-', {
				text : '更新',
				iconCls : 'icon-edit',
				handler : function() {
					var rows = $('#monitorTable').datagrid('getSelections');
					if(rows.length==0){
						alert("请选择一个角色进行修改");
						return;
					}
					if(rows.length>1){
						alert("只能选择一个角色修改");
						return;
					}
					var dlist = [];
					dlist.push({
						"salechnlcode":rows[0].tcmonitor.salechnlcode,
						"transcode":rows[0].tcmonitor.transcode,
						"servicecode":rows[0].tcmonitor.servicecode,
						"confiobject":rows[0].tcmonitor.confiobject,
						"ismonitor":rows[0].tcmonitor.ismonitor,
						"smsflag":rows[0].tcmonitor.smsflag,
						"emailflag":rows[0].tcmonitor.emailflag,
						"smsserver":rows[0].tcmonitor.smsserver,
						"emailserver":rows[0].tcmonitor.emailserver,
						"smsreceiver":rows[0].tcmonitor.smsreceiver,
						"emalreceiver":rows[0].tcmonitor.emalreceiver,
						"maxwaittime":rows[0].tcmonitor.maxwaittime,
						"id":rows[0].tcmonitor.id}); 
					addmonitortab('更新监控配置','updateMonitorUrl?list='+$.toJSON(dlist));
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					deleteMonitor();
				}
			}, '-'],
			onLoadSuccess : function() {
				$('#monitorTable').datagrid('clearSelections'); 
			}
		});
	});
	function addmonitortab(title, href) {
		if (href){  
	        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
		}else {  
            var content = '未实现';  
        }  
		if($('#monitortab').tabs('exists', title)) {
			$('#monitortab').tabs('select', title);
	        var tab = $('#monitortab').tabs('getSelected'); 
			$('#monitortab').tabs('update', {
				tab: tab,
				options: {
					title: title,
		            content:content, 
					closable : true
				}
			});
		}else {   
			$('#monitortab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
	}
	//删除
	function deleteMonitor() {
		var rows = $('#monitorTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择要删除的角色");
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
					$.post('deleteMonitor' + ps, function(data) {
						$('#monitorTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	//表格查询
	function searchMonitor() {
		var params = $('#monitorTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#listMonitorForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#monitorTable').datagrid('cisreload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearMonitor() {
		$('#listMonitorForm').form('clear');
		searchMonitor();
	}
	
	
	