var custPdAmountOrderInfoTable = null;

jQuery(function($) {
	initAllCombobox();
	initCustPdAmountOrderInfoTable();
	pdOrderQueryGetTotalAmount();
});

function initAllCombobox() {
	$("#pdAmountOrderQuery_productId").combobox(
					{
						// url : contextPath + '/codeQuery/productQueryAll',//
						// 通过URL加载远程列表数据。
						valueField : 'code',// 基础数据值名称绑定到该下拉列表框。
						textField : 'codeName',// 基础数据字段名称绑定到该下拉列表框。

						onShowPanel : function() {
							var agencyComId = $("#pdAmountOrderQuery_agencyComId").combobox("getValue");
							if (agencyComId == null || agencyComId == ""|| agencyComId == undefined) {
								var url = contextPath+ '/codeQuery/wealthproductQuery';
							} else {
								var url = contextPath+ '/codeQuery/productwealthQuery?codeType='+agencyComId;
							}
							$(this).combobox("clear");
							$(this).combobox("reload", url);
						}
					});
	$("#pdAmountOrderQuery_agencyComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	$("#pdAmountOrderQuery_orderStatus").combobox({
		url : 'codeQuery/tdCodeQueryOrderStatus?codeType=custOrderStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	// 分公司：代码-名称
	$("#pdAmountOrderQuery_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	// 网点：代码-名称
	/*$("#pdAmountOrderQuery_storeId").combobox(
			{
				valueField : 'storeId',
				textField : 'storeName',
				onShowPanel : function() {
					var comId = $("#pdAmountOrderQuery_comId").combobox("getValue");
					if (comId == null || comId == "" || comId == undefined) {
						var url = contextPath + '/codeQuery/storeQuery';
					} else {
						var url = contextPath+ '/codeQuery/defStoreQuery?codeType=' + comId;
					}
					$(this).combobox("clear");
					$(this).combobox("reload", url);
				}
			});*/
	// 团队：代码-名称
	$("#pdAmountOrderQuery_departmentId").combobox(
			{
				valueField : 'departmentId',
				textField : 'departmentName',
				onShowPanel : function() {
					var comId = $("#pdAmountOrderQuery_comId").combobox(
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
	/*
	 * $("#pdAmountOrderQuery_AgentId").combobox({ prompt:'输入姓名后自动搜索',
	 * //required:true, mode:'remote',
	 * url:contextPath+'/codeQuery/queryAgentInfo', valueField:'agentId',
	 * textField:'agentName', editable:true, hasDownArrow:false, onBeforeLoad:
	 * function(param){ console.info("---------------------------");
	 * console.info(param); console.info("============================");
	 * if(param == null || param.q == null || param.q.replace(/ /g, '') == ''){
	 * var value = $(this).combobox('getValue'); var text =
	 * $(this).combobox('getText'); //console.info(value+";"+text); if(value){//
	 * 修改的时候才会出现q为空而value不为空 param.id = value; return true; } return false; } }
	 * 
	 * });
	 */
	// 财富顾问
	$("#pdAmountOrderQuery_AgentId")
			.combobox(
					{
						url : contextPath + '/codeQuery/agentQuery',
						valueField : 'id',
						textField : 'name',
						onShowPanel : function() {
							var param = {};
						/*	var storeId = $("#pdAmountOrderQuery_storeId")
									.combobox("getValue");*/
							var departmentId = $(
									"#pdAmountOrderQuery_departmentId")
									.combobox("getValue");
							/*param.storeId = storeId;*/
							param.departmentId = departmentId;
							if (/*(storeId == null || storeId == "" || storeId == undefined)
									&& (*/departmentId == null
											|| departmentId == "" || departmentId == undefined) {
								var comId = $("#pdAmountOrderQuery_comId")
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
}
/**
 * 初始化产品信息列表
 */
function initCustPdAmountOrderInfoTable() {
	var selectIndex = -1;
	custPdAmountOrderInfoTable = $("#custPdAmountOrderInfoTable")
			.datagrid(
					{
						// title : '预约查询列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath
								+ "/productOrder/getAllPdAmountOrderInfo", // 数据来源
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
									field : 'pdAmountOrderInfoId',
									title : '预约ID',
									hidden : true,
									// width : 100
									// sortable : true,
									formatter : function(value, row, index) {
										return row.pdAmountOrderInfoId;
									}
								},
								{
									field : 'productId',
									title : '产品名称',
									width : 250,
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
									width : 150,
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
									field : 'isDistribute',
									title : '是否已分配额度',
									hidden : true,
									formatter : function(value, row, index) {
										return row.isDistribute;
									}
								},
								{
									field : 'Detail',
									title : '预约明细',
									width : 80,
									formatter : function(value, row, index) {
										var param = {};
										param.operate = "detailCustOrder";
										param.pdAmountOrderInfoId = row.pdAmountOrderInfoId;
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
										param.isDistribute = row.isDistribute;
										param = $.toJSON(param);
										//alert(param);

										return "<a href='#'  onclick=detailAmountOrderWindow('客户预约明细','"
												+ contextPath
												+ "/productOrder/addProductAmountOrder?param="
												+ param + "')>预约明细查看</a>";
									}
								}, ] ],
						/*
						 * toolbar : [ { text : '撤销预约', iconCls : 'icon-undo',
						 * handler : function() { //alert("撤销预约中");
						 * deletepdAmountOrderInfo(); } } ],
						 */
						toolbar : "#custPdAmountOrderInfoTable_tb",
						onLoadSuccess : function() {
							$('#custPdAmountOrderInfoTable').datagrid(
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
// 撤销预约
/*function deletepdAmountOrderInfo() {
	var pdAmountOrderQueryData = $("#custPdAmountOrderInfoTable").datagrid(
			"getSelections");
	// var pdAmountOrderAuditInfoData =
	// $("#custProductAmountOrderAuditInfoTable").datagrid("getSelections");
	// 如果用户没有点选
	if (pdAmountOrderQueryData.length == 0) {
		$.messager.alert('提示', "请选择需要撤销的预约！");
		return;
	} else {
		// 提示用户是否确认撤销
		$.messager.confirm('提示', '是否确认撤销该预约?', function(r) {
			if(r){
			pdAmountOrderQueryData = pdAmountOrderQueryData[0];
			// ajax请求
			$.ajax({
					type : 'post',
					url : 'productOrder/deletepdAmountOrderInfoUrl',
					data : 'param='+ encodeURI($.toJSON(pdAmountOrderQueryData)),
					cache : false,
					success : function(returnData){
						try {
							if (returnData.success){
							   $.messager.alert('提示', returnData.msg);
							   $("#custPdAmountOrderInfoTable").datagrid("reload");
							}else{
								$.messager.alert('提示', returnData.msg);
							}
						} catch(e){
								$.messager.alert('提示', e);
							}
					}
				});
			}
		});
	}
}*/
// 清空列表
function clearQueryPdAmountOrderQueryCondition() {
	$("#listPdAmountOrderQuery_queryConditionForm").form("clear");
}

// 查询列表
function queryPdAmountOrderInfoList() {
	// alert("开始查询……");
	// valueInfoGridId.datagrid('options').url = contextPath+"";
	// var queryparam =
	// $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	// queryparam = formDataToJsonStr(queryparam);
	// valueInfoGridId.datagrid('load',{param:queryparam});
	// 设置Datagrid获取数据的url
	custPdAmountOrderInfoTable.datagrid('options').url = contextPath
			+ "/productOrder/getAllPdAmountOrderInfo";
	// 获取页面的查询条件
	var queryParam = $("#listPdAmountOrderQuery_queryConditionForm")
			.serialize();
	// console.info("======queryParam======")
	// console.info(queryParam);
	// 将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	// console.info("======queryParam转换后======")
	// console.info(queryParam);
	// 加载datagrid数据
	custPdAmountOrderInfoTable.datagrid('load', {
		queryParam : queryParam
	});
	// 获取预约总额
	pdOrderQueryGetTotalAmount();
}
// 查看预约详情窗口
function detailAmountOrderWindow(title, href) {

	$('<div id="addPdAmountOrderWindow"/>').dialog({
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
function exportOrderData() {
	var queryParam = $('#listPdAmountOrderQuery_queryConditionForm')
			.serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath
			+ '/productOrder/productOrderQueryDetail.xls?queryParam='
			+ encodeURI(queryParam));
}

/**
 * 产品预约查询获取预约总额
 * 
 */
function pdOrderQueryGetTotalAmount() {
	var queryParam = $("#listPdAmountOrderQuery_queryConditionForm")
			.serialize();
	// 将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type : 'post',
		url : 'productOrder/getPdOrderTotalAmount',
		data : 'queryParam=' + queryParam + '&operate=pdOrderQuery',
		cache : false,
		success : function(returnData) {
			try {
				if (returnData.success) {
					if (returnData.obj != null) {
						// $("#orderTotal").html(returnData.obj);
						// document.getElementById('orderTotal').innerText =
						// returnData.obj;
						var totalLabel = document.getElementById('orderTotal');
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
