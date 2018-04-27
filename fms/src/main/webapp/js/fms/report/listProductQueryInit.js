/**
 * 初始化列表
 */
jQuery(function($){
	initProductQueryTable();
	initAllCombox();
});

/**
 * 下拉框
 */
function initAllCombox(){
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#ProductQuery_ProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构初始化
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#ProductQuery_AgencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'
			});

	// 产品状态初始化
	$("#ProductQuery_ProductStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=productStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品子类
	var productSubTypeCombobox;
	productSubTypeCombobox = $("#ProductQuery_ProductSubType").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品类型初始化
	var productTypeCombobox;
	productTypeCombobox = $("#ProductQuery_ProductType").combobox(
			{
				url : contextPath
						+ '/codeQuery/tdCodeQuery?codeType=productType',
				valueField : 'code',
				textField : 'codeName',
				onSelect : function() {
					var value = productTypeCombobox.combobox("getValue");
					// 1-财富，2-保险
					var codeType;
					if (value == 1) {
						codeType = 'wealthProSubType';
					} else {
						codeType = 'insProSubType';
					}
					var url = contextPath + '/codeQuery/tdCodeQuery?codeType='
							+ codeType;
					productSubTypeCombobox.combobox("reload", url);
				}

			});

	// 销售状态初始化
	$("#ProductQuery_SalesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName'
	});
}

/**
 * 产品查询列表
 */
var listProductQueryTable;
function initProductQueryTable(){
	var  selectIndex = -1;
	listProductQueryTable = $('#listProductQueryTable')
			.datagrid(
					{
						title : '产品信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/reports/queryProductListUrl", // 数据来源
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
							sortable : true,
							formatter : function(value, row, index) {
								return row.productId;
							}
						}, {
							field : 'agencyComId',
							title : '基金管理人',
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								return row.agencyComId;
							}
						}, {
							field : 'productCode',
							title : '产品代码',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								var param = {};
								param.productId = row.productId;
								param.productTypeCode = row.productTypeCode;
								param.productSubTypeCode = row.productSubTypeCode;
								param.productStatus = row.productStatus;
								return "<a href = '#' onclick=addproducttab('查看产品详情','"
								+contextPath+"/reports/fixedDetailUrl?param="+$.toJSON(param)+"')>"+row.productCode+"</a>";
							}

						}, {
							field : 'productName',
							title : '产品名称',
							width : 200,
							sortable : true,
							formatter : function(value, row, index) {
								var param = {};
								param.productId = row.productId;
								param.productTypeCode = row.productTypeCode;
								param.productSubTypeCode = row.productSubTypeCode;
								param.productStatus = row.productStatus;
								return "<a href = '#' onclick=addproducttab('查看产品详情','"
								+contextPath+"/reports/fixedDetailUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
							}

						}, {
							field : 'productType',
							title : '产品类型',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productType;
							}
						}, {
							field : 'productTypeCode',
							title : '产品类型Code',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productTypeCode;
							}
						}, {
							field : 'productSubType',
							title : '产品子类',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productSubType;
							}
						}, {
							field : 'productSubTypeCode',
							title : '产品子类Code',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productSubTypeCode;
							}
						}, {
							field : 'salesStatus',
							title : '销售状态',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.salesStatus;
							}
						}, {
							field : 'status',
							title : '产品状态',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.status;
							}
						}, {
							field : 'statusCode',
							title : '产品状态',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.statusCode;
							}
						}] ],
						onLoadSuccess : function() {
							$('#listProductQueryTable').datagrid(
									'clearSelections'); 
						},
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

/**
 * 清空查询条件
 */
function clearSearchInfo() {
	$('#productQueryForm').form('clear');
	searchProductInfo();
}

/**
 * 查询
 */
function searchProductInfo() {
	listProductQueryTable.datagrid('options').url = contextPath+"/product/queryListProductUrl";
	var queryparam = $("#productQueryForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	listProductQueryTable.datagrid('load',{param:queryparam});
	
}

/**
 * 产品详情Window
 * @param title
 * @param href
 */
function addproducttab(title, href) {
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
