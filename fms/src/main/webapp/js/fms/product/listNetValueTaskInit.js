jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initNetValueTaskTable();
});

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#list_net_value_taskProductId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#list_net_value_taskComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'

			});
}

var netValueTaskTable;
function initNetValueTaskTable() {
	var  selectIndex = -1;
	netValueTaskTable = $("#netValueTaskTable")
			.datagrid(
					{
						title : '净值待办任务列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/searchNetValueTaskUrl", // 数据来源
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
							field : 'netValueTaskId',
							title : '净值代办Id',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.netValueTaskId;
							}
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
							field : 'agencyComName',
							title : '基金管理人',
							width : 150,
							sortable : true,
							formatter : function(value, row, index) {
								return row.agencyComName;
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
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productName;
							}
						}, {
							field : 'taskCommission',
							title : '需要维护日期',
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								return row.taskCommission;
							}
						}] ],toolbar:[{
							text : '净值维护',
							iconCls : 'icon-add',
							handler : function() {
								var rows = netValueTaskTable.datagrid('getSelections');
								if(rows.length == 0) {
									$.messager.alert("提示","请选择需要维护的产品!","info");
									return;
								}
								if(rows.length > 1) {
									$.messager.alert("提示","只能选择一条产品进行维护!","info");
									return;
								}
								$('#addnetValueTaskId').val(rows[0].netValueTaskId);
								$('#addAgencyComName').val(rows[0].agencyComName);
								$('#addProductName').val(rows[0].productName)
								$('#addProductId').val(rows[0].productId)
								addproducttab('产品净值维护',contextPath+"/product/addNetValueTaskUrl")
							}
						}, '-',{
							text : '标记为已处理',
							iconCls : 'icon-add',
							handler : function() {
								var param={};
								var rows= $("#netValueTaskTable").datagrid('getChecked');
								if(rows.length==0){
									$.messager.alert('提示', "请选择要变更项");
									return;
								}
								param.netValueTaskId=rows[0].netValueTaskId;
								$.ajax({
									type : 'post',
									url : contextPath+"/product/setNetValueTaskState",
									data:'param=' + $.toJSON(param),
									cache : false,
									success : function(resultInfo) {
										if(resultInfo.success){
											$.messager.alert('提示',resultInfo.msg, 'info');
											//需要清空状态，否则影响下次操作
											$("#netValueTaskTable").datagrid('clearSelections');
											clearValueTaskForm();
										}else{
											$.messager.alert('提示', resultInfo.msg);
										}
									}
								})
							}
						}],
						onLoadSuccess : function() {
							$('#netValueTaskTable').datagrid('clearSelections'); 
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




// 表格查询
function searchNetValueTaskList() {
	
	netValueTaskTable.datagrid('options').url = contextPath+"/product/searchNetValueTaskUrl";
	var queryparam = $("#netValueTaskForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	netValueTaskTable.datagrid('load',{param:queryparam});
}	
	
// 清空查询条件然后进行查询
function clearValueTaskForm() {
	$('#netValueTaskForm').form('clear');
	$('#netValueTaskTable').datagrid('cisreload');
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

