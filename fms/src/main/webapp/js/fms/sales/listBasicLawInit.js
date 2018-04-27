jQuery(function($) {
	$('#basiclawTable').datagrid({
		title : '基本法信息列表', // 标题
		method : 'post',
		iconCls : 'icon-ok', // 图标
		singleSelect : false, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null, // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList : [10,20,30,40],
		pageSize : 10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'buildingcode',
					title : '版本编号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.buildingcode;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'buildingname',
					title : '版本名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.buildingname;
					}
				},{
					field : 'buildingType',
					title : '执行状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.buildingType;
					}
				},{
					field : 'estatetype',
					title : '执行开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.estatetype;
					}
				},{
					field : 'num',
					title : '执行结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.num;
					}
				}]],
				toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						$('<div id="addBaseLawInfo"/>').dialog({
				   			href :contextPath+"/sales/addBasicLawUrl",
				   			modal : true,
				   			fit:true,
				   			title : '新增基本法信息',
				   			inline : false,
				   			minimizable : false,
				   			onClose : function() {
				   				$(this).dialog('destroy');
				   			}
				   		});
//						addbasiclawtab('新增基本法信息', 'addBasicLawUrl');
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#buildTable').datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择一个用户进行修改");
							return;
						}
						if (rows.length > 1) {
							alert("只能选择一个用户修改");
							return;
						}
						var dlist = [];
						dlist.push({
									"name" : rows[0].username,
									"code" : rows[0].usercode,
									"id" : rows[0].id,
									"companyId_read" : rows[0].companyId,
									"companyName_read" : rows[0].companyName
								});
						addusertab('更新用户', 'updateBuildUrl?list='+ $.toJSON(dlist));
					}
				}, '-'],
		onLoadSuccess : function() {
			$('#buildTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	$('#buildingtype').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#companyId').combobox('getData');
			  if(data.length>0){
				  $('#companyId').combobox('select',data[0].id);
			  }
		  }
	  });
	$('#estatetype').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  onLoadSuccess:function(){
			  var data = $('#companyId').combobox('getData');
			  if(data.length>0){
				  $('#companyId').combobox('select',data[0].id);
			  }
		  }
	  });
	
});


	function addbasiclawtab(title, href) {
		if (href){  
	        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
		}else {  
            var content = '未实现';  
        }  
		if($('#basiclawtab').tabs('exists', title)) {
			$('#basiclawtab').tabs('close', title);
			$('#basiclawtab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
		else{
			$('#basiclawtab').tabs('add', {
				title : title,
	            content:content, 
				closable : true
			});
		}
	} 
	
	// 表格查询
	function searchBuild() {
		var params = $('#buildTable').datagrid('options').queryParams; // 先取得
		var fields = $('#buildForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#buildTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件然后进行查询
	function clearForm() {
		$('#buildForm').form('clear');
		searchBuild();
	}