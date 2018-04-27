jQuery(function($) {
	initAllCombobox();
	initListPdfmTable();
});
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#pdfmProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构初始化
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#pdfmAgencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'
			});

	// 产品状态初始化
	$("#pdfmProductStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=productStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品子类
	var productSubTypeCombobox;
	productSubTypeCombobox = $("#pdfmProductSubType").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品类型初始化
	var productTypeCombobox;
	productTypeCombobox = $("#pdfmProductType").combobox(
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
	$("#pdfmSalesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName'
	});

}
// 产品设置页面信息列表初始化
var initListPdfmTable;
function initListPdfmTable() {
	var  selectIndex = -1;
	initListPdfmTable = $('#listPdfmAppendixTable')
			.datagrid(
					{
						title : '产品信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/queryListProductUrl", // 数据来源
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
							width : 150,
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
								return  "<a href='#' onclick=addproducttab('查看产品详情','"+contextPath
									+"/product/detailProductDefUrl?param="+$.toJSON(param)+"')>"+row.productCode+"</a>";
							}

						}, {
							field : 'productName',
							title : '产品名称',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								var param = {};
								param.productId = row.productId;
								param.productTypeCode = row.productTypeCode;
								param.productSubTypeCode = row.productSubTypeCode;
								return  "<a href='#' onclick=addproducttab('查看产品详情','"+contextPath
									+"/product/detailProductDefUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
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
							width : 100,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productTypeCode;
							}
						}, {
							field : 'productSubType',
							title : '产品子类',
							width : 100,
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
							width : 80,
							sortable : true,
							formatter : function(value, row, index) {
								return row.salesStatus;
							}
						}, {
							field : 'status',
							title : '产品状态',
							width : 100,
							sortable : true,
							formatter : function(value, row, index) {
								return row.status;
							}
						}, {
							field : 'statusCode',
							title : '产品状态编码',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.statusCode;
							}
						}, {
							field : 'remark',
							title : '备注',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.remark;
							}
						}, {
							field : 'userCode',
							title : '操作员',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.userCode;
							}
						}, {
							field : 'productManager',
							title : '产品经理',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productManager;
							}
						}  ] ],
						toolbar : [
								{
									text : '附件上传',
									iconCls : 'icon-add',
									handler : function() {
										var rows = $('#listPdfmAppendixTable').datagrid('getSelections');
										if (rows.length == 0) {
											$.messager.alert("提示","请选择一个产品上传附件","info");
											return;
										}
										var productId = rows[0].productId;
										var param = {};
										param.businessNo = productId;
										param.businessType = "14";
										param.flag = "addFile";
										addPdfmFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));
									}
								},'-'],
						onLoadSuccess : function() {
							$('#listPdfmAppendixTable').datagrid('clearSelections'); 
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

// 查询
function PdfmsearchproductInfo() {
	
	initListPdfmTable.datagrid('options').url = contextPath+"/product/queryListProductUrl";
	var queryparam = $("#listPdfmForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	initListPdfmTable.datagrid('load',{param:queryparam});
	
}

//清空查询条件然后进行查询
function PdfmclearSearchInfo() {
	$('#listPdfmForm').form('clear');
	PdfmsearchproductInfo();
}

//上传文件
function addPdfmFileWindow(title, href) {
	$('<div id="addFileWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		width : 800,
		height : 500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}
