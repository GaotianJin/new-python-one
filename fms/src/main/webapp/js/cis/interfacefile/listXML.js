jQuery(function($) {
		$('#xmlTable').datagrid({
			title : 'XML列表', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 550, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryXML", //数据来源
			sortName : 'xml.id', //排序的列
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
				field : 'xml.name',
				title : 'XML名称',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.xml.xmlname;
				} //需要formatter一下才能显示正确的数据
			} ] ],
			toolbar : '#tb',
			onLoadSuccess : function() {
				$('#xmlTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	});
	
	//更新
	function updateXML() {
		var rows = $('#xmlTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择一个菜单进行修改");
			return;
		}
		if(rows.length>1){
			alert("只能选择一个菜单修改");
			return;
		}
		var dlist = [];
		var name = "";
		if(null!=rows[0].xml.xmlname){
			name = rows[0].xml.xmlname;
		}
		dlist.push({"name":name,"id":rows[0].xml.id}); 
		parent.addtab('更新XML', 'interfacefile/updateXMLUrl?list='+$.toJSON(dlist));
	}
	//节点设置
	function setNode() {
		var rows = $('#xmlTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择一个XML设置");
			return;
		}
		if(rows.length>1){
			alert("只能选择一个XML设置");
			return;
		}
		var dlist = [];
		var name = "";
		if(null!=rows[0].xml.xmlname){
			name = rows[0].xml.xmlname;
		}
		dlist.push({"name":name,"id":rows[0].xml.id}); 
		parent.addtab('节点设置','interfacefile/setNodeUrl?list='+$.toJSON(dlist));
	}
	//删除
	function deleteXML() {
		var rows = $('#xmlTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择要删除的XML");
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
					$.post('deleteXML' + ps, function(data) {
						$('#xmlTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
	}
	//表格查询
	function searchXML() {
		var params = $('#xmlTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields = $('#xmlForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
			params[field.name] = (field.value); //设置查询参数
		});
		$('#xmlTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearXML() {
		$('#xmlForm').form('clear');
		searchXML();
	}
	function creatXML(){
		var rows = $('#xmlTable').datagrid('getSelections');
		if(rows.length==0){
			alert("请选择要生成的XML");
			return;
		}
		if(rows.length>1){
			alert("只能选择一个XML生成");
			return;
		}
		var id = rows[0].id;
		$.post('creatXML?id='+id, function(data) {
			$('#xmlTable').datagrid('reload');
			$.messager.alert('提示', data.mes, 'info');
		});
	}