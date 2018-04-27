jQuery(function($) {
		$('#queryWorkflowTable').datagrid({
			title : '工作流明细列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 350, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryWorkflow?key="+$("#key").val(), //数据来源
			remoteSort : true, //服务器端排序
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框
			{
				field : 'key',
				title : '业务号',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row[0];
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'state',
				title : '状态',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row[1];
				}
			} ] ],
			toolbar : [ {
				text : '展现',
				iconCls : 'icon-ok',
				handler : function() {
					showWorkflow();
				}
			}, '-' ],
			onLoadSuccess : function() {
				$('#queryWorkflowTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	});
	//表格查询
	function searchWorkflow() {
		var params = $('#queryWorkflowTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#queryWorkflowForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#queryWorkflowTable').datagrid('cisreload'); //设置好查询参数 reload 一下就可以了
	}
	
	//清空查询条件
	function clearWorkflow() {
		$('#queryWorkflowForm').form('clear');
		searchWorkflow();
	}
	function showWorkflow() {
		var rows = $('#queryWorkflowTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择要展示的工作流");
			return;
		}else if(rows.length>1){
			alert("只能选择一个工作流展示");
			return;
		}else{
			var key = rows[0][0];
			parent.addtab('工作流展示', 'workflow/showWorkflowDemoUrl?key='+key);
		}
	}