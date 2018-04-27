jQuery(function($) {
		$('#nodeTable').datagrid({
			title : '节点列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 350, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryNode?id="+$("#id").val(), //数据来源
			sortName : 'nodeinfo.id', //排序的列
			sortOrder : 'asc', //倒序
			remoteSort : true, //服务器端排序
			idField : 'id', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框
			{
				field : 'nodeinfo.nodename',
				title : '节点名称',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.nodeinfo.nodename;
				} //需要formatter一下才能显示正确的数据
			} ] ],
			toolbar : '#tb',
			onLoadSuccess : function() {
				$('#nodeTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	});
	
	//删除
	function deleteNode() {
		var rows = $('#nodeTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择要删除的节点");
			return;
		}else{
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0){
							ps += "?id=" + n.id;
						}else{
							ps += "&id=" + n.id;
						}
					});
					$.post('deleteNode' + ps, function(data) {
						$('#nodeTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	function updateNode(){
		var rows = $('#nodeTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择一个节点进行修改");
			return;
		}
		if(rows.length>1){
			alert("只能选择一个节点修改");
			return;
		}
		var dlist = [];
		dlist.push({"name":rows[0].nodeinfo.nodename,"xmlid":$("#id").val()}); 
		parent.addtab('更新节点', 'interfacefile/updateNodeUrl?list='+$.toJSON(dlist));
	}
	//表格查询
	function searchNode() {
		var params = $('#nodeTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#nodeForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#nodeTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearNode() {
		$('#nodeForm').form('clear');
		searchNode();
	}