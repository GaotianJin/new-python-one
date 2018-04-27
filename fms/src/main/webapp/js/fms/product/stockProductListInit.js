jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initStockProductTable();
});
var stockProductTable;
function initStockProductTable() {
	var  selectIndex = -1;
	stockProductTable = $("#stockProductTable")
			.datagrid(
					{
						title : '股权产品列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/queryStockProductList", // 数据来源
						sortName : 'id', // 排序的列
						sortOrder : 'desc', // 倒序
						remoteSort : true, // 服务器端排序
						idField : 'id', // 主键字段
						queryParams : {}, // 查询条件
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageSize : 10,
						pageList : [ 5, 10, 15, 20, 25, 30 ],
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'productId',
							title : '产品Id',
							width : 150,
							hidden : true,
							sortable : true
						}, 
						{
							field : 'agentName',
							title : '基金管理人',
							width : 220,
							sortable : true
						}, {
							field : 'productCode',
							title : '产品代码',
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								var param = {};
								param.productId = row.productId;
								param.productSubTypeCode = row.productSubTypeCode;
								return  "<a href='#' onclick=addStockProducttab('查看产品详情','"+contextPath
									+"/product/searchProductPageUrl?param="+$.toJSON(param)+"')>"+row.productCode+"</a>";
							}
						}, {
							field : 'productName',
							title : '产品名称',
							width : 240,
							sortable : true,
							formatter : function(value, row, index) {
								var param = {};
								param.productId = row.productId;
								param.productSubTypeCode = row.productSubTypeCode;
								return  "<a href='#' onclick=addStockProducttab('查看产品详情','"+contextPath
									+"/product/searchProductPageUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
							}
						}, {
							field : 'salesStatus',
							title : '销售状态',
							width : 100,
							sortable : true
						}, {
							field : 'foundDate',
							title : '成立日期',
							width : 100,
							sortable : true
						}, {
							field : 'closePeriod',
							title : '产品期限',
							width : 100,
							sortable : true
						},
						{
							field : 'remark',
							title : '产品备注',
							width : 180,
							sortable : true
						}, {
							field : 'productManager',
							title : '产品经理',
							width : 180,
							sortable : true
						},
						{
							field : 'productSubTypeCode',
							title : '产品子类Code',
							width : 150,
							hidden : true,
							sortable : true
						} ] ],
								onClickRow: function (rowIndex, rowData) {
						            if(selectIndex==rowIndex){
						            	//第一次单击选中,第二次单击取消选中
						            	$(this).datagrid('unselectRow', rowIndex);
						            	selectIndex=-1;
						            }else{
						            	selectIndex = rowIndex;
						            }
								}
					});
}


// 表格查询
function searchStockProductList() {
	stockProductTable.datagrid('options').url = contextPath+"/product/queryStockProductList";
	var queryparam = $("#stockProductListForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	stockProductTable.datagrid('load',{param:queryparam});
}	
	
// 清空查询条件然后进行查询
function clearStockProductForm() {
	$('#stockProductListForm').form('clear');
	$('#stockProductTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	parent.searchStockProductList();
}
/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#stockPD_productId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#stockPD_comId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'

			});
}

//打开新的页面
function addStockProducttab(title, href) {
	$('<div id="addWindow"/>').window({
		href : href,
		modal : true,
		title : title,
		fit : true,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}

