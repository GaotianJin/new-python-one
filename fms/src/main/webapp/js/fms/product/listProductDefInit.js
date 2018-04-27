jQuery(function($) {
	initAllCombobox();
	initListProductDefTable();
});
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#cpszProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构初始化
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#cpszAgencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'
			});

	// 产品状态初始化
	$("#cpszProductStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=productStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品子类
	var productSubTypeCombobox;
	productSubTypeCombobox = $("#cpszProductSubType").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品类型初始化
	var productTypeCombobox;
	productTypeCombobox = $("#cpszProductType").combobox(
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
	$("#cpszSalesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName'
	});

}
// 产品设置页面信息列表初始化
var listProductDefTable;
function initListProductDefTable() {
	var  selectIndex = -1;
	listProductDefTable = $('#listProductDefTable')
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
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										$('<div id="addProdutctInfo"/>')
												.dialog(
														{
															href : contextPath
																	+ "/product/addProductDefUrl",
															modal : true,
															fit : true,
															title : '产品信息新增',
															inline : false,
															minimizable : false,
															onClose : function() {
																$(this)
																		.dialog(
																				'destroy');
															}
														});
									}
								},
								'-',
								{
									text : '更新',
									iconCls : 'icon-edit',
									handler : function() {
										var rows = $('#listProductDefTable')
												.datagrid('getSelections');
										if (rows.length == 0) {
											$.messager.alert("提示","请选择一个产品信息进行修改","info");
											return;
										}
										if (rows.length > 1) {
											$.messager.alert("提示","只能选择一个产品信息修改","info");
											return;
										}
										if(rows[0].statusCode=="2"&&userId!=1){
											$.messager.alert("提示","抱歉,发布后只有管理员才有权限修改!","info");
											return;
										}
										var param = {};
										param.productId = rows[0].productId;
										param.productTypeCode = rows[0].productTypeCode;
										param.productSubTypeCode = rows[0].productSubTypeCode;
										param.productStatus=rows[0].statusCode;
										addproducttab('产品信息修改',contextPath+ '/product/updateProductDefUrl?param='+$.toJSON(param));
									}
								},
								'-',
								{
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										var rows = $('#listProductDefTable').datagrid('getSelections');
										if (rows.length == 0) {
											$.messager.alert("提示","请选择一个产品信息进行删除","info");
											return;
										}
										if(rows[0].statusCode=="2"){
											$.messager.alert("提示","已审核发布的产品不可以删除!","info");
											return;
										}
										$.ajax({
											type : 'post',
											url : contextPath + "/product/productDelete",
											data : 'productId=' + rows[0].productId,
											cache : false,
											success : function(resultInfo) {
													if (resultInfo.success) {
														$.messager.alert('提示', resultInfo.msg, 'info');
														$('#listProductDefTable').datagrid('reload');   
													} else {
														$.messager.alert('提示', resultInfo.msg);
														return;
													}
											}
										});
									}
								}, '-', {
									id:'submitAudit',
									text : '提交审核',
									iconCls : 'icon-add',
									handler : function() {
										submitAudit();
									}
								}, '-'
								
								, {
									text : '信息同步',
									iconCls : 'icon-reload',
									handler : function() {
										internetRelease();
									}
								}, '-'
								
								],
						onLoadSuccess : function() {
							$('#listProductDefTable').datagrid(
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


//信息同步至互联网端接口
function internetRelease() {
	{
		var rows = $('#listProductDefTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert("提示","请选择一个产品进行信息同步至互联网","info");
			return;
		}
		if (rows.length > 1) {
			$.messager.alert("提示","只能选中一条产品信息进行同步","info");
			return;
		}
		if(rows[0].statusCode!=2){
			$.messager.alert("提示","该产品尚未发布，信息暂不能同步至互联网","info");
			return;
		}
		
		$.ajax({
			type : 'post',
			url : contextPath + "/product/productInternetRelease",
			data : 'param=' + rows[0].productId,
			cache : false,
			success : function(resultInfo) {
				try {
					if (resultInfo.success) {
						$.messager.alert('提示', resultInfo.msg, 'info');
						$.messager.progress('close');
						return;

					} else {
						$.messager.alert('提示', resultInfo.msg);
						return;
					}
				} catch (e) {
					$.messager.alert('提示', e);
					return;
				}
			}
		});

	}

}


//提交审核
function submitAudit() {
	{
		var rows = $('#listProductDefTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert("提示","请选择一个产品进行提交","info");
			return;
		}
		if (rows.length > 1) {
			$.messager.alert("提示","只能提交一个产品","info");
			return;
		}
		if(rows[0].statusCode==2){
			$.messager.alert("提示","该产品已发布，不能再进行提交审核","info");
			return;
		}
		$('#submitAudit').linkbutton('disable');
		$.ajax({
			type : 'post',
			url : contextPath + "/product/productSubmitAudit",
			data : 'param=' + rows[0].productId,
			cache : false,
			success : function(resultInfo) {
				try {
					if (resultInfo.success) {
						$.messager.alert('提示', resultInfo.msg, 'info');
						$.messager.progress('close');
						$('#listProductDefTable').datagrid('reload');            
					} else {
						$.messager.alert('提示', resultInfo.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
				$('#submitAudit').linkbutton('enable');
			}
		});

	}

}

// 查询
function searchProductInfo() {
	
	listProductDefTable.datagrid('options').url = contextPath+"/product/queryListProductUrl";
	var queryparam = $("#productForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	listProductDefTable.datagrid('load',{param:queryparam});
	
}

//清空查询条件然后进行查询
function clearSearchInfo() {
	$('#productForm').form('clear');
//	searchProductInfo();
}


function clearFormInfo() {
	$('#productForm').form('clear');
	searchProductInfo();
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
