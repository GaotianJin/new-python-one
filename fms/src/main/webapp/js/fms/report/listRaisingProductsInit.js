jQuery(function($) {
	initListRaisingProductsTable();
	initListRaisingProductsCombobox();
});


var raisingProductsTable ;
function initListRaisingProductsTable(){
	raisingProductsTable = $('#listProducts_QueryTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/reports/queryRaisingProductsList",
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
					field : 'serialNo',
					title : '序号',
					//width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.serialNo;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'date',
					title : '时间',
					width : 100,
					formatter : function(value, row, index) {
						return row.date;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productSubType',
					title : '产品类别',
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
					field : 'custName',
					title : '姓名',
					width : 100,
					formatter : function(value, row, index) {
						return row.custName;
					}
				},{
					field : 'agentName',
					title : '财富顾问',
					width : 100,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},
				{
					field : 'subscriptionFee',
					title : '金额',
					width : 100,
					formatter : function(value, row, index) {
						return row.subscriptionFee;
					}
				},{
					field : 'comName',
					title : '所属分公司',
					hidden : true,
					formatter : function(value, row, index) {
						return row.comName;
					}
				},{
					field : 'departmentName',
					title : '所属部门',
					hidden : true,
					formatter : function(value, row, index) {
						return row.departmentName;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#listReport_QueryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function initListRaisingProductsCombobox(){
	//初始化分公司
	$("#listProducts_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	//初始化团队
	$("#listProducts_DepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#listProducts_comId").combobox("getValue");
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
	$("#lisProducts_ProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});
}

//导出明细表
function exportDetailList(){
	var queryParam = $('#listProducts_queryConditionForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath+'/reports/raisingProductsDetail.xls?queryParam='+encodeURI(queryParam));
}

//表格查询
function queryRaisingProductsList() {
	raisingProductsTable.datagrid('options').url = contextPath+"/reports/queryRaisingProductsList";
	var queryParam = $("#listProducts_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	raisingProductsTable.datagrid('load',{queryParam:queryParam});	
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#listProducts_queryConditionForm').form('clear');
	queryRaisingProductsList();
}