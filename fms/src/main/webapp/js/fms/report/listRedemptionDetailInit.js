jQuery(function($) {
	initListRedemptionDetailTable();
	initListRedemptionDetailCombobox();
});


var redemptionDetailTable ;
function initListRedemptionDetailTable(){
	redemptionDetailTable = $('#listRedemption_QueryTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/reports/queryRedemptionDetailList",
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
					field : 'productName',
					title : '产品名称',
					width : 100,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'foundDate',
					title : '成立日期',
					width : 100,
					formatter : function(value, row, index) {
						return row.foundDate;
					}
				},{
					field : 'agentName',
					title : '财富顾问',
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
					field : 'subscriptionFee',
					title : '规模',
					width : 100,
					formatter : function(value, row, index) {
						return row.subscriptionFee;
					}
				},{
					field : 'closedPeriods',
					title : '久期',
					width : 100,
					formatter : function(value, row, index) {
						return row.closedPeriods;
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
				},{
					field : 'bankName',
					title : '开户行',
					width : 100,
					formatter : function(value, row, index) {
						return row.bankName;
					}
				},{
					field : 'bankAccount',
					title : '银行账号',
					width : 100,
					formatter : function(value, row, index) {
						return row.bankAccount;
					}
				},{
					field : 'mobile',
					title : '联系电话',
					width : 100,
					formatter : function(value, row, index) {
						return row.mobile;
					}
				},{
					field : 'custAddress',
					title : '通讯地址',
					width : 100,
					formatter : function(value, row, index) {
						return row.custAddress;
					}
				},{
					field : 'redemptionDate',
					title : '赎回/到期时间',
					width : 100,
					formatter : function(value, row, index) {
						return row.redemptionDate;
					}
				},{
					field : 'redemptionShares',
					title : '赎回/到期份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.redemptionShares;
					}
				},{
					field : 'remainSubscription',
					title : '当前产品规模',
					width : 100,
					formatter : function(value, row, index) {
						return row.remainSubscription;
					}
				},{
					field : 'remianingShares',
					title : '当前产品份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.remianingShares;
					}
				}]],
		onLoadSuccess : function() {
			$('#listRedemption_QueryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function initListRedemptionDetailCombobox(){
	//初始化年度
	$("#listRedemption_yearId").combobox({
		url : contextPath + '/reports/yearQuery',
		valueField : 'years',
		textField : 'years'
	});
	//初始化月份
	$("#listRedemption_monthId").combobox({
		url : contextPath + '/reports/monthQuery',
		valueField : 'monthes',
		textField : 'monthes'
	});
	//初始化分公司
	$("#listRedemption_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	//初始化团队
	$("#listRedemption_DepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#listRedemption_comId").combobox("getValue");
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
	$("#lisRedemption_ProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});
}

//导出明细表
function exportDetailList(){
	var queryParam = $('#listRedemption_queryConditionForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath+'/reports/redemptionDetail.xls?queryParam='+encodeURI(queryParam));
}

//表格查询
function queryRedemptionDetailList() {
	redemptionDetailTable.datagrid('options').url = contextPath+"/reports/queryRedemptionDetailList";
	var queryParam = $("#listRedemption_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	redemptionDetailTable.datagrid('load',{queryParam:queryParam});	
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#listRedemption_queryConditionForm').form('clear');
	queryRedemptionDetailList();
}