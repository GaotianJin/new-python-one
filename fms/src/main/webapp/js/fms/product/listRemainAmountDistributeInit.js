var remainAmountDistributeTable = null;

/**
 * 初始化
 */
jQuery(function($) {
	initAllCombobox();
	initRemainAmountDistributeTable();
	getRemainTotalAmount();
});

/**
 * 下拉框
 */
function initAllCombobox() {
	$("#remainAmountDistribute_productId")
			.combobox(
					{
						url : contextPath + '/codeQuery/productQueryAll',
						// 通过URL加载远程列表数据。
						valueField : 'code',// 基础数据值名称绑定到该下拉列表框。
						textField : 'codeName'// 基础数据字段名称绑定到该下拉列表框。
					});
	$("#remainAmountDistribute_agencyComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	$("#remainAmountDistribute_orderStatus").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=custOrderStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	// 分公司：代码-名称
	$("#remainAmountDistribute_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	// 团队：代码-名称
	$("#remainAmountDistribute_departmentId").combobox(
			{
				valueField : 'departmentId',
				textField : 'departmentName',
				onShowPanel : function() {
					var comId = $("#remainAmountDistribute_comId").combobox(
							"getValue");
					if (comId == null || comId == "" || comId == undefined) {
						var url = contextPath + '/codeQuery/departmentQuery';
					} else {
						var url = contextPath
								+ '/codeQuery/defDepartmentQuery?codeType='
								+ comId;
					}
					$(this).combobox("clear");
					$(this).combobox("reload", url);
				}
			});
	// 财富顾问
	$("#remainAmountDistribute_AgentId")
			.combobox(
					{
						url : contextPath + '/codeQuery/agentQuery',
						valueField : 'id',
						textField : 'name',
						onShowPanel : function() {
							var param = {};
							/*
							 * var storeId = $("#pdAmountOrderQuery_storeId")
							 * .combobox("getValue");
							 */
							var departmentId = $(
									"#remainAmountDistribute_departmentId")
									.combobox("getValue");
							/* param.storeId = storeId; */
							param.departmentId = departmentId;
							if (/*
								 * (storeId == null || storeId == "" || storeId ==
								 * undefined) && (
								 */departmentId == null
									|| departmentId == ""
									|| departmentId == undefined) {
								var comId = $("#remainAmountDistribute_comId")
										.combobox("getValue");
								if (comId == null || comId == ""
										|| comId == undefined) {
									var url = contextPath
											+ '/codeQuery/agentQuery';
								} else {
									var url = contextPath
											+ '/codeQuery/defAgentQuery?codeType='
											+ comId;
								}
							} else {
								var url = contextPath
										+ '/codeQuery/limitAgentInfo?param='
										+ encodeURI($.toJSON(param));
							}
							$(this).combobox("clear");
							$(this).combobox("reload", url);
						}
					});
	$("#remainAmountDistribute_queueIsDistribute").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=queueStatus',
		valueField : 'code',
		textField : 'codeName'
	});
}

/**
 * 初始化队列预约信息列表
 */
function initRemainAmountDistributeTable() {
	var selectIndex = -1;
	remainAmountDistributeTable = $("#remainAmountTable")
			.datagrid(
					{
						// title : '预约查询列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath
								+ "/productOrder/getRemainAmountOrderInfo", // 数据来源
						// sortName : 'id', // 排序的列
						sortOrder : 'desc', // 倒序
						remoteSort : true, // 服务器端排序
						idField : 'id', // 主键字段
						queryParams : {}, // 查询条件
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageSize : 10,
						pageList : [ 5, 10, 15, 20, 25, 30 ],
						columns : [ [ // Javascript创建数据表格控件
								{
									field : 'ck',
									checkbox : true
								},// 如果为true，则显示复选框。该复选框列固定宽度。
								{
									field : 'agencyComId',
									title : '产品方',
									hidden : true,
									// sortable : true,
									formatter : function(value, row, index) {
										return row.agencyName;
									}
								},
								{
									field : 'pdAmountOrderQueueInfoId',
									title : '队列预约ID',
									hidden : true,
									// width : 100
									// sortable : true,
									formatter : function(value, row, index) {
										return row.pdAmountOrderInfoId;
									}
								},
								{
									field : 'queueNo',
									title : '队列序号',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.queueNo;
									}
								},
								{
									field : 'productId',
									title : '产品名称',
									width : 200,
									sortable : true,
									formatter : function(value, row, index) {
										return row.productName;
									}
								},{
							    	 field:'remark',	
							    	 title:'产品备注',
							    	 width:150,
							    	 formatter : function(value, row, index) {
							    		return row.remark;
							    	 }
							     },
								{
									field : 'productType',
									title : '产品类型',
									hidden : true,
									// sortable : true,
									formatter : function(value, row, index) {
										return row.productType;
									}
								},
								{
									field : 'productSubType',
									title : '产品子类型',
									hidden : true,
									// sortable : true,
									formatter : function(value, row, index) {
										return row.productSubType;
									}
								},
								{
									field : 'comName',
									title : '分公司',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.comName;
									}
								},
								{
									field : 'storeName',
									title : '网点',
									width : 120,
									sortable : true,
									hidden:true,
									formatter : function(value, row, index) {
										return row.storeName;
									}
								},
								{
									field : 'agentName',
									title : '财富顾问',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.agentName;
									}
								},
								{
									field : 'custName',
									title : '客户姓名',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.custName;
									}
								},
								{
									field : 'orderAmount',
									title : '预约额度',
									width : 80,
									sortable : true,
									formatter : function(value, row, index) {
										return row.orderAmount;
									}
								},
								{
									field : 'orderDate',
									title : '预约时间',
									width : 150,
									sortable : true,
									formatter : function(value, row, index) {
										return row.orderDate;
									}
								},
								/*{
									field : 'inviteCode',
									title : '邀请码',
									// hidden:true,
									sortable : true,
									width : 100,
									formatter : function(value, row, index) {
										return row.inviteCode;
									}
								},*/
								{
									field : 'planTransferDate',
									title : '拟打款时间',
									// hidden:true,
									sortable : true,
									width : 100,
									formatter : function(value, row, index) {
										return row.planTransferDate;
									}
								},
								{
									field : 'orderStatus',
									title : '预约状态',
									hidden : true,
									formatter : function(value, row, index) {
										return row.orderStatus;
									}
								},
								{
									field : 'orderStatusName',
									title : '预约状态',
									width : 100,
									sortable : true,
									formatter : function(value, row, index) {
										return row.orderStatusName;
									}
								},
								{
									field : 'comId',
									title : '机构',
									hidden : true,
									formatter : function(value, row, index) {
										return row.comId;
									}
								},
								{
									field : 'expectOpenDay',
									title : '成立日',
									hidden : true,
									formatter : function(value, row, index) {
										return row.expectOpenDay;
									}
								},
								{
									field : 'foundDate',
									title : '成立日',
									hidden : true,
									formatter : function(value, row, index) {
										return row.foundDate;
									}
								},
								{
									field : 'investEndDate',
									title : '封账日',
									hidden : true,
									formatter : function(value, row, index) {
										return row.investEndDate;
									}
								},
								{
									field : 'queueIsDistribute',
									title : '是否已分配额度',
									hidden : true,
									formatter : function(value, row, index) {
										return row.queueIsDistribute;
									}
								},
								{
									field : 'queueIsDistributeName',
									title : '是否已分配额度',
									formatter : function(value, row, index) {
										return row.queueIsDistributeName;
									}
								},
								{
									field : 'Detail',
									title : '预约明细',
									width : 90,
									formatter : function(value, row, index) {
										var param = {};
										param.pdAmountOrderQueueInfoId = row.pdAmountOrderQueueInfoId;
										param.comId = row.comId;
										param.productId = row.productId;
										param.expectOpenDay = $.trim(row.expectOpenDay);
										param.foundDate = $.trim(row.foundDate);
										param.sealingAccDate = $.trim(row.investEndDate);
										param.productType = row.productType;
										param.productSubType = row.productSubType;
										var inviteCode = row.inviteCode;
										// 01-需要邀请码，02-不需要邀请码
										var isInviteCode = "01";
										if (inviteCode == null
												|| inviteCode == ""
												|| inviteCode == undefined) {
											isInviteCode = "02";
										}
										param.isInviteCode = isInviteCode;
										param.queueIsDistribute = row.queueIsDistribute;
										param = $.toJSON(param);
										//alert(param);

										return "<a href='#'  onclick=detailRemainAmountOrderWindow('客户预约明细','"
												+ contextPath
												+ "/productOrder/addProductRemainAmountOrder?param="
												+ param + "')>预约明细查看</a>";
									}
								}, ] ],
						toolbar : "#remainAmountTable_tb",
						onLoadSuccess : function() {
							$('#remainAmountTable').datagrid(
									'clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						},
						onClickRow : function(rowIndex, rowData) {
							if (selectIndex == rowIndex) {
								// 第一次单击选中,第二次单击取消选中
								$(this).datagrid('unselectRow', rowIndex);
								selectIndex = -1;
							} else {
								selectIndex = rowIndex;
							}
						}
					});
}
// 清空列表
function clearQueryRemainAmountDistribute() {
	$("#listRemainAmountDistribute_Form").form("clear");
}

// 查询列表
function queryRemainAmountDistributeList() {
	// 设置Datagrid获取数据的url
	remainAmountDistributeTable.datagrid('options').url = contextPath
			+ "/productOrder/getRemainAmountOrderInfo";
	// 获取页面的查询条件
	var queryParam = $("#listRemainAmountDistribute_Form")
			.serialize();
	// 将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	// 加载datagrid数据
	remainAmountDistributeTable.datagrid('load', {
		queryParam : queryParam
	});
	// 获取预约总额
	getRemainTotalAmount();
}
// 查看预约详情窗口
function detailRemainAmountOrderWindow(title, href) {

	$('<div id="addPdRemainAmountOrderWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		width : 1200,
		height : 600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}

/**
 * 数据导出
 */
function exportRemainAmountData() {
	var queryParam = $('#listRemainAmountDistribute_Form')
			.serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath
			+ '/productOrder/productOrderQueueDetail.xls?queryParam='
			+ encodeURI(queryParam));
}
/**
 * 产品队列预约查询获取预约总额
 * 
 */
function getRemainTotalAmount() {
	var queryParam = $("#listRemainAmountDistribute_Form")
			.serialize();
	// 将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type : 'post',
		url : 'productOrder/getRemainTotalAmount',
		data : 'queryParam=' + queryParam + '&operate=pdOrderQuery',
		cache : false,
		success : function(returnData) {
			try {
				if (returnData.success) {
					if (returnData.obj != null) {
						var totalLabel = document.getElementById("remainAmount_OrderTotal");
						totalLabel.innerHTML = returnData.obj;
					}
				} else {
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 *队列客户剩余额度分配校验
 **/
function remainAmountDistribute(){
	var rows = remainAmountDistributeTable.datagrid('getSelections');
	
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个产品进行预约");
		return;
	};
	var pdAmountOrderQueueInfoId = rows[0].pdAmountOrderQueueInfoId;
	//客户预约额度
	var orderAmount = rows[0].orderAmount;
	//是否已分配
	var queueIsDistribute = rows[0].queueIsDistribute;
	//产品成立日
	var foundDate = rows[0].foundDate;
	//可预约额度
	var remainAmount = document.getElementById("remainAmount_OrderTotal").innerHTML;
	if(queueIsDistribute=='已分配'){
		$.messager.alert('提示', '该客户已分配完毕！');
	}else{
		if(orderAmount > remainAmount){
			$.messager.confirm('确认',"该客户预约额度超出可预约额度！是否将剩余额度全部分配给该客户？",function(r){    
			    if (r){
			    	if(remainAmount <= 0){
			    		$.messager.alert('提示', '可预约额度为零，您不能预约！');
			    	}else{
			    		submitQueueDistribute(pdAmountOrderQueueInfoId,remainAmount,foundDate);
			    	}
			    } 
			}); 
		}else if(orderAmount == remainAmount){
			$.messager.confirm('确认',"该客户预约额度与可预约额度相等！是否分配额度？",function(r){    
			    if (r){
			    	submitQueueDistribute(pdAmountOrderQueueInfoId,orderAmount,foundDate);
			    } 
			}); 
		}else if(orderAmount < remainAmount){
			$.messager.confirm('确认',"可预约额度充足！是否分配额度？",function(r){    
			    if (r){
			    	submitQueueDistribute(pdAmountOrderQueueInfoId,orderAmount,foundDate);
			    } 
			}); 
		}
	}
}

/**
 * 队列客户剩余额度分配
 **/
function submitQueueDistribute(pdAmountOrderQueueInfoId,amount,foundDate){
	var param = {};
	param.pdAmountOrderQueueInfoId = pdAmountOrderQueueInfoId;
	param.amount = amount;
	param.foundDate = foundDate;
	$.ajax({
		type : 'post',
		url : 'productOrder/submitQueueDistribute',
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(returnData) {
			try {
					$.messager.alert('提示', returnData.msg);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}