jQuery(function($) {
		$('#AppRuleTable').datagrid({
			title : '规则列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 400, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryList", //数据来源
			sortName : 'lmcalmode.id', //排序的列
			sortOrder : 'asc', //倒序
			remoteSort : true, //服务器端排序
			idField : 'lid', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框
			{
				field : 'id',
				title : '序号',
				width : 50,
				sortable : true,
//				hidden : true,
				formatter : function(value, row, index) {
					return row.lmcalmode.id;
				} //需要formatter一下才能显示正确的数据
			},  {
				field : 'calcode',
				title : '规则编码',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.lmcalmode.calcode;
				}
			},{
				field : 'riskcode',
				title : '产品编码',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.lmcalmode.riskcode;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'type',
				title : '规则类型',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.lmcalmode.type;
				} //需要formatter一下才能显示正确的数据
			},{
				field : 'calsql',
				title : '规则内容',
				width : 300,
				sortable : true,
				formatter : function(value, row, index) {
					return row.lmcalmode.calsql;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'remark',
				title : '算法备注',
				width : 400,
				sortable : true,
				formatter : function(value, row, index) {
					return row.lmcalmode.remark;
				} //需要formatter一下才能显示正确的数据
			},
			] ],
			toolbar : '#tb',
			onLoadSuccess : function() {
				$('#AppRuleTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	});
	function addAppRuletab(title, href) {
		if (href){  
	        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
		}else {  
            var content = '未实现';  
        }  
		if($('#AppRuletab').tabs('exists', title)) {
			$('#AppRuletab').tabs('select', title);
	        var tab = $('#AppRuletab').tabs('getSelected'); 
			$('#AppRuletab').tabs('update', {
				tab: tab,
				options: {
					title: title,
		            content:content, 
					closable : true
				}
			});
		}else {   
			$('#AppRuletab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
	}
	//新增
	function addAppRule(){
		addAppRuletab('新增规则', 'addAppRuleUrl');
	}
	//更新
	function updateAppRule() {
		var rows = $('#AppRuleTable').datagrid('getSelections');
					if(rows.length==0){
						alert("请选择一个规则进行修改");
						return;
					}
					if(rows.length>1){
						alert("只能选择一个规则修改");
						return;
					}
					
					var dlist = [];
					dlist.push({
						"calcode" : rows[0].lmcalmode.calcode,
						"riskcode" : rows[0].lmcalmode.riskcode,
						"type" : rows[0].lmcalmode.type,
						"calsql" : escape(rows[0].lmcalmode.calsql),
						"remark" : rows[0].lmcalmode.remark,
						"id" : rows[0].lmcalmode.id
					});
			addAppRuletab('更新规则', 'updateAppRuleUrl?list='+ $.toJSON(dlist));
	}
	//删除
	function deleteAppRule() {
		var rows = $('#AppRuleTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择要删除的规则");
			return;
		}else{
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0){
							ps += "?lid=" + n.lid;
						}else{
							ps += "&lid=" + n.lid;
						}
					});
					$.post('deleteAppRule' + ps, function(data) {
						$('#AppRuleTable').datagrid('cisreload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	//表格查询
	function searchAppRule() {
		var params = $('#AppRuleTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#AppRuleForm').serializeArray(); //自动序列化表单元素为JSON对象

		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#AppRuleTable').datagrid('cisreload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearAppRule() {
		$('#AppRuleForm').form('clear');
		searchAppRule();
	}