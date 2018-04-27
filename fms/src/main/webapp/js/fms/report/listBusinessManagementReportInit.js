jQuery(function($) {
	initListBusinessMangeTable();
	initListBusinessMangeCombobox();
});


var businessManageTable ;
function initListBusinessMangeTable(){
	businessManageTable = $('#listReport_QueryTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : contextPath+"/reports/queryBusinessManageList",
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
					title : '月份',
					width : 100,
					formatter : function(value, row, index) {
						return row.month;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'foundDate',
					title : '成立日期',
					width : 100,
					formatter : function(value, row, index) {
						return row.foundDate;
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
					title : '产品名称',
					width : 100,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'remark',
					title : '产品备注',
					width : 100,
					formatter : function(value, row, index) {
						return row.remark;
					}
				},
				{
					field : 'tradeStatus',
					title : '交易状态',
					width : 100,
					formatter : function(value, row, index) {
						return row.tradeStatus;
					}
				},
				{
					field : 'customerNo',
					title : '客户号',
					width : 100,
					formatter : function(value, row, index) {
						return row.customerNo;
					}
				},{
					field : 'custName',
					title : '客户姓名',
					width : 100,
					formatter : function(value, row, index) {
						return row.custName;
					}
				},{
					field : 'idType',
					title : '证件类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.idType;
					}
				},{
					field : 'idNo',
					title : '证件号码',
					width : 100,
					formatter : function(value, row, index) {
						return row.idNo;
					}
				},
				
				{
					field : 'agentName',
//					title : '财富顾问',
					title : '理财经理',
					width : 100,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},{
					field : 'departmentName',
					title : '所属部门',
					hidden : true,
					formatter : function(value, row, index) {
						return row.departmentName;
					}
				},{
					field : 'comName',
					title : '所属分公司',
					width : 100,
					formatter : function(value, row, index) {
						return row.comName;
					}
				},{
					field : 'custLevel',
					title : '是否新客户',
					/*width : 100,*/
					hidden : true,
					formatter : function(value, row, index) {
						return row.custLevel;
					}
				},{
					field : 'subscriptionFee',
					title : '规模',
					width : 100,
					formatter : function(value, row, index) {
						return row.subscriptionFee;
					}
				},{
					field : 'closedPeriods',
					title : '久期/封闭期',
					width : 100,
					formatter : function(value, row, index) {
						return row.closedPeriods;
					}
				},
				{
					field : 'endDate',
					title : '产品到期时间',
					width : 100,
					formatter : function(value, row, index) {
						return row.endDate;
					}
				},
				{
					field : 'remainSubscription',
					title : '当前产品规模',
					width : 100,
					formatter : function(value, row, index) {
						return row.remainSubscription;
					}
				},
				{
					field : 'subscriptionFeeRatio',
					title : '认购费率',
					/*width : 100,*/
					hidden : true,
					formatter : function(value, row, index) {
						return row.subscriptionFeeRatio;
					}
				},{
					field : 'reductionRatio',
					title : '认购费减免比例',
					/*width : 100,*/
					hidden : true,
					formatter : function(value, row, index) {
						return row.reductionRatio;
					}
				},{
					field : 'isEmployee',
					title : '是否员工认购',
					/*width : 100,*/
					hidden : true,
					formatter : function(value, row, index) {
						return row.isEmployee;
					}
				},{
					field : 'gender',
					title : '性别',
					/*width : 50,*/
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.gender;
					}
				},/*{
					field : 'age',
					title : '年龄',
					width : 50,
					formatter : function(value, row, index) {
						return row.age;
					}
				},*/{
					field : 'bankName',
					title : '开户行',
					/*width : 100,*/
					hidden : true,
					formatter : function(value, row, index) {
						return row.bankName;
					}
				},{
					field : 'bankAccount',
					title : '银行账号',
//					width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.bankAccount;
					}
				},{
					field : 'mobile',
					title : '联系电话',
//					width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.mobile;
					}
				},{
					field : 'email',
					title : 'Email',
//					width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.email;
					}
				},{
					field : 'custAddress',
//					title : '通讯地址',
					hidden : true,
					width : 100,
					formatter : function(value, row, index) {
						return row.custAddress;
					}
				},{
					field : 'redemptionDate',
					title : '赎回时间',
//					width : 100,
					//sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.redemptionDate;
					}
				},{
					field : 'redemptionShares',
					title : '赎回份额',
//					width : 100,
					//sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.redemptionShares;
					}
				},{
					field : 'remianingShares',
					title : '当前产品份额',
//					width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.remianingShares;
					}
				}]],
		onLoadSuccess : function() {
			$('#listReport_QueryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function initListBusinessMangeCombobox(){
	//初始化年度
	$("#lisReport_yearId").combobox({
		url : contextPath + '/reports/yearQuery',
		valueField : 'years',
		textField : 'years'
	});
	//初始化月份
	$("#lisReport_monthId").combobox({
		url : contextPath + '/reports/monthQuery',
		valueField : 'monthes',
		textField : 'monthes'
	});
	//初始化分公司
	$("#lisReport_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	//初始化团队
	$("#lisReport_DepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#lisReport_comId").combobox("getValue");
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
	$("#lisReport_ProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});
}

//导出明细表
function exportDetailList(){
	var queryParam = $('#listReprot_queryConditionForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath+'/reports/businessManagementDetail.xls?queryParam='+encodeURI(queryParam));
}

//表格查询
function queryBusinessManageList() {
	businessManageTable.datagrid('options').url = contextPath+"/reports/queryBusinessManageList";
	var queryParam = $("#listReprot_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	businessManageTable.datagrid('load',{queryParam:queryParam});	
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#listReprot_queryConditionForm').form('clear');
	//queryBusinessManageList();
}