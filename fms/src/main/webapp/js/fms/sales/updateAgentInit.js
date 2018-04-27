jQuery(function($) {
	
	//性别
	$('#sex').combobox({
		required : true,
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=sex",
		valueField : 'code',
		textField : 'codeName'
	});
	//证件类型
	$('#idType').combobox({
		required : true,
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=idType",
		valueField : 'code',
		textField : 'codeName'
	});
	//民族
	$('#nationality').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=ethnicity",
		valueField : 'code',
		textField : 'codeName'
	});
	//学历
	$('#degree').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=education",
		valueField : 'code',
		textField : 'codeName'
	});
	//婚姻状况
	$('#maritalStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=marriageStatus",
		valueField : 'code',
		textField : 'codeName'
	});
	//政治面貌
	$('#political').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=policyType",
		valueField : 'code',
		textField : 'codeName'
	});
	//生育状况，数据待确定
	$('#bearStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=bearStatus",
		valueField : 'code',
		textField : 'codeName'
	});
	//与财富顾问关系，数据待确定
	$('#relationToAgent').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=relationToAgent",
		valueField : 'code',
		textField : 'codeName'
	});
	//职业
	$('#occupationCode').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=occupationType",
		valueField : 'code',
		textField : 'codeName'
	});
	//在职状态
	$('#workUpdateState').combobox({
		required : true,
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=workState",
		valueField : 'code',
		textField : 'codeName'
	});
	
	
	$('#agentHomeTable').datagrid({
		title : '家庭信息列表', // 标题
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
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'memberName',
					title : '家庭成员姓名',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'relationToAgent',
					title : '与员工间关系',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.relationToAgent;
					}
				},{
					field : 'age',
					title : '年龄',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.age;
					}
				},{
					field : 'occupationCode',
					title : '职业',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.occupationCode;
					}
				},{
					field : 'incomeValue',
					title : '年收入(万元)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.incomeValue;
					}
				},{
					field : 'mobile',
					title : '手机',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.mobile;
					}
				},{
					field : 'email',
					title : 'E-mail',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.email;
					}
				},{
					field : 'qq',
					title : 'QQ号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.qq;
					}
				},{
					field : 'wechat',
					title : '微信号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.wechat;
					}
				}]],
		onLoadSuccess : function() {
			$('#agentHomeTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	$('#agentCertificationTable').datagrid({
		title : '资格证信息列表', // 标题
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
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'memberName',
					title : '资格证类型',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'relationToAgent',
					title : '资格证代码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.relationToAgent;
					}
				}]],
		onLoadSuccess : function() {
			$('#agentCertificationTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	$('#agentshopTable').datagrid({
		title : '网点信息列表', // 标题
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
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'memberName',
					title : '所属机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'memberName',
					title : '所属部门',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'memberName',
					title : '开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'relationToAgent',
					title : '结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.relationToAgent;
					}
				}]],
		onLoadSuccess : function() {
			$('#agentCertificationTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	$('#agentbusinessTable').datagrid({
		title : '部门信息列表', // 标题
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
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'memberName',
					title : '所属机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'memberName',
					title : '所属部门',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'memberName',
					title : '开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'relationToAgent',
					title : '结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.relationToAgent;
					}
				}]],
		onLoadSuccess : function() {
			$('#agentCertificationTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
});	