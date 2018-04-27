jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initFloatProductInfoGridId();
});
var floatProductInfoGridId;
function initFloatProductInfoGridId() {
	var  selectIndex = -1;
	floatProductInfoGridId = $("#floatProductInfoGridId")
			.datagrid(
					{
						title : '浮动类产品信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/searchFloatProductNetValueUrl", // 数据来源
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
								//param.productName = row.productName;
								param.productSubTypeCode = row.productSubTypeCode;
								return  "<a href='#' onclick=addproducttab('浮动产品净值详情','"+contextPath
									+"/product/detailProductNetValueUrl?param="+$.toJSON(param)+"')>"+row.productCode+"</a>";
							}

						}, {
							field : 'productName',
							title : '产品名称',
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								var param = {};
								param.productId = row.productId;
								param.productTypeCode = row.productTypeCode;
								param.productSubTypeCode = row.productSubTypeCode;
								return  "<a href='#' onclick=addproducttab('浮动产品净值详情','"+contextPath
									+"/product/detailProductNetValueUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
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
							width : 80,
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
							title : '产品状态编码',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.statusCode;
							}
						}] ],
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
function searchFloatProductNetValue() {
	
	floatProductInfoGridId.datagrid('options').url = contextPath+"/product/searchFloatProductNetValueUrl";
	var queryparam = $("#floatProductNetValueForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	floatProductInfoGridId.datagrid('load',{param:queryparam});
}	
	
// 清空查询条件然后进行查询
function clearForm() {
	$('#floatProductNetValueForm').form('clear');
	$('#floatProductInfoGridId').datagrid('cisreload');
	searchFloatProductNetValue();// 设置好查询参数 reload 一下就可以了
}


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
/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#floatProduct_productId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#floatProduct_agencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'

			});
}
