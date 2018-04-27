jQuery(function($) {
	initAllCombobox();
	initListProductDefTable();
});
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#productReport_productId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});
}
// 产品报告信息列表
var listProductReportTable;
function initListProductDefTable() {
	listProductReportTable = $('#listProductReportTable')
			.datagrid(
					{
						title : '产品报告列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/queryListProductReportUrl", // 数据来源
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
						},
						{
							field : 'fileInfoId',
							title : '文件流水号',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.fileInfoId;
							}
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
						}, {
							field : 'productCode',
							title : '产品代码',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productCode;
							}

						}, {
							field : 'productName',
							title : '产品名称',
							width : 450,
							nowrap: false,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productName;
							}
						}, {
							field : 'businessSubType',
							title : '报告类型',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.businessSubType;
							}
						}, 
						 {
							field : 'businessSubTypeCode',
							title : '报告类型编码',
							width : 150,
							hidden:true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.businessSubTypeCode;
							}
						},
						{
							field : 'uploadFileName',
							title : '报告名称',
							width :300,
							nowrap: false,
							sortable : true,
							formatter : function(value, row, index) {
								var param = {};
								param.fileInfoId = row.fileInfoId;
								param = $.toJSON(param);
								return "<a href='#'  onclick=fileDownload('"+param+"')>"+row.uploadFileName+"</a>";
							} // 需要formatter一下才能显示正确的数据
						}] ],
						toolbar : [
								{
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										var param={};
										param.operateFlag="addFile";
										$('<div id="addProdutctReportInfo"/>').dialog(
														{
															href : contextPath+ "/product/addProductReportUrl?param="+param,
															modal : true,
															fit : true,
															title : '产品报告新增',
															inline : false,
															minimizable : false,
															onClose : function() {
																$(this).dialog('destroy');
															}
														});
									}
								},
								'-',
								{
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										deleteProductReport();
									}
								}, '-'],
						onLoadSuccess : function() {
							$('#listProductReportTable').datagrid('clearSelections'); 
						}
					});
}

// 查询
function searchProductReportInfo() {
	listProductReportTable.datagrid('options').url = contextPath+"/product/queryListProductReportUrl";
	var queryparam = $("#productReportForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	listProductReportTable.datagrid('load',{param:queryparam});
	
}

//清空查询条件然后进行查询
function clearSearchPDReportInfo() {
	$('#productReportForm').form('clear');
	searchProductReportInfo();
}

//删除产品报告
function deleteProductReport(){
	var param={};
	var rows = $('#listProductReportTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择一个产品报告信息进行修改");
		return;
	}
	if (rows.length > 1) {
		alert("只能选择一个产品报告信息修改");
		return;
	}
	param.fileInfoId = rows[0].fileInfoId;
	$.ajax({
		type : 'post',
		url : contextPath + "/product/deleteProductReport",
		data : 'param=' +encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', "删除成功",'info');
					searchProductReportInfo();
					return;
				} else {
					$.messager.alert('提示', resultInfo.msg);
					return;
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});	
	
	
	
}

function fileDownload(param) { 
	$.messager.confirm('确认','您确认下载此文件吗？',function(r){    
	    if (r){    
	    	var form = $("<form>");   //定义一个form表单
	    	form.attr('style', 'display:none');   //在form表单中添加查询参数
	    	form.attr('target', '');
	    	form.attr('method', 'post');
	    	form.attr('action', contextPath+"/fileUpload/downloadFile?param="+param);
	    	$('body').append(form);  //将表单放置在web中 
	    	form.submit(); 
	    }
	});
}

function addproductReportTab(title, href) {
	$('<div id="addWindow"/>').dialog({
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
