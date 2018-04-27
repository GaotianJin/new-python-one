jQuery(function($) {
	initListSaleProductsTable();
	initListSaleProductsCombobox();
});


var SaleProductsTable ;
function initListSaleProductsTable(){
	SaleProductsTable = $('#listSale_QueryTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : contextPath+"/reports/querySaleProductsList",
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {"queryParam":{}}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[
		        {
				    field : 'ck',
				   	checkbox : true
		        },
				{
					field : 'month',
					title : '月度',
					width : 100,
					formatter : function(value, row, index) {
						return row.month;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productOpenDate',
					title : '产品开放日',
					width : 100,
					formatter : function(value, row, index) {
						return row.productOpenDate;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'custType',
					title : '客户类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.custType;
					}
				},
				{
					field : 'custNo',
					title : '客户号',
					width : 100,
					formatter : function(value, row, index) {
						return row.custNo;
					}
				},{
					field : 'custName',
					title : '客户姓名',
					width : 100,
					formatter : function(value, row, index) {
						return row.custName;
					}
				},{
					field : 'custLevel',
					title : '是否新客户',
					width : 100,
					formatter : function(value, row, index) {
						return row.custLevel;
					}
				},{
					field : 'agentName',
					title : '现财富顾问',
					width : 100,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},{
					field : 'departmentName',
					title : '部门',
					width : 100,
					formatter : function(value, row, index) {
						return row.departmentName;
					}
				},{
					field : 'comName',
					title : '现归属部门',
					width : 100,
					formatter : function(value, row, index) {
						return row.comName;
					}
				},
				{
					field : 'productSubType',
					title : '产品所属板块',
					width : 100,
					formatter : function(value, row, index) {
						return row.productSubType;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productName',
					title : '存续产品',
					width : 100,
					formatter : function(value, row, index) {
						return row.productName;
					}
				},{
					field : 'closedPeriods',
					title : '久期/封闭期',
					width : 100,
					formatter : function(value, row, index) {
						return row.closedPeriods;
					}
				},{
					field : 'endDate',
					title : '预计到期时间',
					width : 40,
					formatter : function(value, row, index) {
						return row.endDate;
					}
				},{
					field : 'subscriptionFee',
					title : '净认/申购金额',
					width : 100,
					formatter : function(value, row, index) {
						return row.subscriptionFee;
					}
				},{
					field : 'subscriptionFeeRatio',
					title : '认购费率',
					width : 100,
					formatter : function(value, row, index) {
						return row.subscriptionFeeRatio;
					}
				},{
					field : 'reductionRatio',
					title : '减免比例',
					width : 100,
					formatter : function(value, row, index) {
						return row.reductionRatio;
					}
				},{
					field : 'actualSubscriptionFee',
					title : '认购费',
					width : 100,
					formatter : function(value, row, index) {
						return row.actualSubscriptionFee;
					}
				},{
					field : 'subscriptionType',
					title : '认购类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.subscriptionType;
					}
				},{
					field : 'addScale',
					title : '新增标准规模',
					width : 100,
					formatter : function(value, row, index) {
						return row.addScale;
					}
				},{
					field : 'clearType',
					title : '清算类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.clearType;
					}
				},{
					field : 'clearedScale',
					title : '已清算规模',
					width : 100,
					formatter : function(value, row, index) {
						return row.clearedScale;
					}
				},{
					field : 'continuedScale',
					title : '存续规模',
					width : 100,
					formatter : function(value, row, index) {
						return row.continuedScale;
					}
				},{
					field : 'originAgentName',
					title : '原财富顾问',
					width : 100,
					formatter : function(value, row, index) {
						return row.originAgentName;
					}
				},{
					field : 'originComName',
					title : '原部门',
					width : 100,
					formatter : function(value, row, index) {
						return row.originComName;
					}
				},{
					field : 'originDepartmentName',
					title : '原部门',
					width : 100,
					formatter : function(value, row, index) {
						return row.originDepartmentName;
					}
				},{
					field : 'originExpectFeeRate',
					title : '原定产品收益率',
					width : 100,
					formatter : function(value, row, index) {
						return row.originExpectFeeRate;
					}
				},{
					field : 'remark',
					title : '产品备注',
					width : 100,
					formatter : function(value, row, index) {
						return row.remark;
					}
				}]],
		onLoadSuccess : function() {
			$('#listSale_QueryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function initListSaleProductsCombobox(){
	//初始化年度
	$("#listSale_yearId").combobox({
		url : contextPath + '/reports/yearQuery',
		valueField : 'years',
		textField : 'years'
	});
	//初始化月份
	$("#listSale_monthId").combobox({
		url : contextPath + '/reports/monthQuery',
		valueField : 'monthes',
		textField : 'monthes'
	});
	//初始化分公司
	$("#listSale_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	//初始化团队
	$("#listSale_DepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#listSale_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});
	
	// 产品初始化 显示为：产品代码-产品名称
	$("#lisSale_ProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});
}

//导出明细表
function exportDetailList(){
	var queryParam = $('#listSale_queryConditionForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath+'/reports/saleProductsDetail.xls?queryParam='+encodeURI(queryParam));
}

//表格查询
function querySaleProductsList() {
	SaleProductsTable.datagrid('options').url = contextPath+"/reports/querySaleProductsList";
	var queryParam = $("#listSale_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	SaleProductsTable.datagrid('load',{queryParam:queryParam});	
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#listSale_queryConditionForm').form('clear');
	querySaleProductsList();
}