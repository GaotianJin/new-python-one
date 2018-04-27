jQuery(function($) {
	initAllCombobox();
	initListFloatProductTable();
});


//初始化下拉框
function initAllCombobox(){
	var floatProductIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	floatProductIdComobox = $("#listFloatProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构初始化
	var floatAgencyComIdCombobox;
	floatAgencyComIdCombobox = $("#listFloatAgencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'
			});
}

// 浮动产品设置页面信息列表初始化
var listFloatProductTable;
function initListFloatProductTable() {
	var selectIndex = -1;
	listFloatProductTable = $("#listFloatProductTable")
			.datagrid(
					{
						title : '浮动产品信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/queryListFloatProductUrl", // 数据来源
						sortName : 'id', // 排序的列
						sortOrder : 'desc', // 倒序
						remoteSort : true, // 服务器端排序
						idField : 'id', // 主键字段
						queryParams : {}, // 查询条件
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageSize : 10,
						pageList : [ 5, 10, 15, 20, 25, 30 ],
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'productId',
									title : '产品Id',
									width : 150,
									hidden : true,
									sortable : true,
									formatter : function(value, row, index) {
										return row.productId;
									}
								},{
									field : 'agencyComId',
									title : '基金管理人',
									width : 150,
									sortable : true,
									formatter : function(value, row, index) {
										return row.agencyComId;
									}
								},{
									field : 'productCode',
									title : '产品代码',
									width : 150,
									sortable : true,
									formatter : function(value, row, index) {
										var param = {};
										param.productId = row.productId;
										param.productSubTypeCode = row.productSubTypeCode;
										return  "<a href='#' onclick=addfloatProducttab('查看产品详情','"+contextPath
											+"/product/searchProductPageUrl?param="+$.toJSON(param)+"')>"+row.productCode+"</a>";
									}
								},
								{
									field : 'productName',
									title : '产品名称',
									width : 150,
									sortable : true,
									formatter : function(value, row, index) {
										var param = {};
										param.productId = row.productId;
										param.productSubTypeCode = row.productSubTypeCode;
										return  "<a href='#' onclick=addfloatProducttab('查看产品详情','"+contextPath
											+"/product/searchProductPageUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
									}
								}, {
									field : 'saleStatus',
									title : '销售状态',
									width : 150,
									sortable : true,
									formatter : function(value, row, index) {
										return row.saleStatus;
									}
								},{
									field : 'openDate',
									title : '最近开放日',
									width : 150,
									sortable : true,
									formatter : function(value, row, index) {
										return row.openDate;
									}
								},/*{
									field : 'remark',
									title : '产品备注',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.remark;
									}
								},*/ /*{
									field : 'productManager',
									title : '产品经理',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.productManager;
									}
								}*/
								{
									field : 'remainShare',
									title : '存续份额（万份）',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.remainShare;
									}
								}
								,{
									field : 'remainScale',
									title : '存续金额（万元）',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.remainScale;
									}
								}
								,{
									field : 'productTypeCode',
									title : '产品类型Code',
									width : 150,
									hidden : true,
									sortable : true,
									formatter : function(value, row, index) {
										return row.productTypeCode;
									}
								},{
									field : 'productSubTypeCode',
									title : '产品子类Code',
									width : 150,
									hidden : true,
									sortable : true,
									formatter : function(value, row, index) {
										return row.productSubTypeCode;
									}
								}] ],
						onLoadSuccess : function() {
							$('#listFloatProductTable').datagrid('clearSelections');
						},
						onClickRow : function(rowIndex, rowData) {
							if (selectIndex == rowIndex) {
								//第一次单击选中,第二次单击取消选中
								$(this).datagrid('unselectRow', rowIndex);
								selectIndex = -1;
							} else {
								selectIndex = rowIndex;
							}
						}
					});
}
//清空
function clearSearchInfo() {
	$('#floatProductForm').form('clear');
	listFloatProductTable.datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	parent.searchFloatProductInfo();
}

//查询
function searchFloatProductInfo() {
	listFloatProductTable.datagrid('options').url = contextPath+"/product/queryListFloatProductUrl";
	var queryparam = $("#floatProductForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	listFloatProductTable.datagrid('load',{param:queryparam});
}

//打开新的页面
function addfloatProducttab(title, href) {
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