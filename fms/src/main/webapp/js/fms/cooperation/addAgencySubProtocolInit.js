jQuery(function($) {
	$('#addAgencySubProtocolTable').datagrid({
		title : '协议信息列表', // 标题
		method : 'post',
		singleSelect : false, // 单选
		//height : 380, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList : [5,10,15,20],
		pageSize : 5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'agencyName',
					title : '协议编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agencyName;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'protocolName',
					title : '协议名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.protocolName;
					}
				},{
					field : 'protocolType',
					title : '协议类型',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.protocolType;
					}
				},
				{
					field : 'protocolType',
					title : '协议状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.protocolType;
					}
				},{
					field : 'protocolType',
					title : '所属框架协议',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.protocolType;
					}
				},{
					field : 'protocolType',
					title : '产品',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.protocolType;
					}
				},{
					field : 'protocolStartDay',
					title : '协议开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.protocolStartDay;
					}
				},{
					field : 'rate',
					title : '协议文件',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.rate;
					}
				}]],
		onLoadSuccess : function() {
			$('#addAgencySubProtocolTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	$('#subprotocolName').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
	  });
	$('#subprotocolType').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
	  });
	$('#subprotocolState').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
	  });
	$('#subkuangjia').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
	  });
	$('#subproduct').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
	  });
	
});
	// 表格查询
	function searchBuild() {
		var params = $('#addAgencySubProtocolTable').datagrid('options').queryParams; // 先取得
		var fields = $('#agencySubProtocoltab').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#addAgencySubProtocolTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	// 清空查询条件然后进行查询
	function clearForm() {
		$('#addAgencyProtocolForm').form('clear');
		searchBuild();
	}