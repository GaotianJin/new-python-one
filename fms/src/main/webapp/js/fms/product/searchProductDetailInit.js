var productId = null;
var productSubType = null;

jQuery(function($) {
	productId = $("#detail_productId").val();
	productSubType = $("#detail_productSubType").val();
	if(productSubType == '01'){
		$(".hideInfo1").remove();
		$(".hideInfo2").remove();
		$(".investDirection").show();//股权类显示投资方向
		$(".investStrategy").remove();
		$(".investScope").remove();
		$('#incomeDisInfo').hide();
		$('#fixedDiffrentInfo').hide();
		$('#feeInfo').hide();
		$('#diffrentInfo').hide();
		$("#productNetValueInfo").hide();
		initWealthStockDisInfoTable();
		getUpdateInitValue();
	}
	if(productSubType == '02'){
		$(".hideInfo1").remove();
		$(".hideInfo2").remove();
		$(".investScope").show();//固定类显示投资范围
		$(".investDirection").remove();
		$(".investStrategy").remove();
		$(".subscriptionFeeRatio").remove();
		$("#productNetValueInfo").hide();
		$('#diffrentInfo').hide();
		$('#stockDisInfo').hide();
		//初始化收益分配信息表格
		initWealthIncomeDisInfoTable();
		initWealthRateTable();
		getUpdateInitValue();
	}
	if(productSubType == '03' || productSubType == '04'){
		$(".custFloatInvestInfo").show();
		$(".hideInfo3").remove();
		$(".investStrategy").show();//浮动类显示投资策略
		$(".investDirection").remove();
		$(".investScope").remove();
		$('#incomeDisInfo').hide();
		$('#fixedDiffrentInfo').hide();
		$('#feeInfo').hide();
		$('#custInvestInfo').hide();
		$('#diffrentInfo').hide();
		$('#stockDisInfo').hide();
	}
	//获取需要修改的数据
	getProductDetailInitValue();
	if(productSubType == '02' || productSubType == '01'){
	//初始化客户投资记录表格
	initDetialCustInvestInfoTable();
	}else if(productSubType == '03' || productSubType == '04'){
		initDetialFloatCustInvestInfoTable();
	}
});


function getProductDetailInitValue(){
	
	var param = {};
	param.productId = productId;
	param.productSubType = productSubType;
	$.ajax({
		type : 'post',
		url : contextPath+"/product/searchProductDetailUrl",
		data : {param : $.toJSON(param)},
		cache : false,
		success : function (result){
			try{
				if(result.success){
					var resultObj = result.obj;
					setInputValueById("detailProductDetailForm",resultObj);
				}else{
					$.messager.alert('提示', result.msg);
				}
			}catch (e){
				$.messager.alert('提示', e);
			}
		}
	});
}

//产品净值信息查看
function productNetValueInfo(){
	var param = {};
	param.productId = productId;
	param.productSubType = productSubType;
	productNetValueInfoTab('查看产品详情',contextPath + "/product/pdSearchProductNetValueUrl?param="+$.toJSON(param));
}

//查看附件信息
function lookFileInfo() {
	var param = {};
	param.businessNo = productId;
	param.businessType = "01";
	param.flag = "queryFile";
	$('<div id="addFileWindow"/>').dialog({
		href :  contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param),
		modal : true,
		title : '文件上传',
		width : 800,
		height : 500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}


//打开新的页面
function productNetValueInfoTab(title, href) {
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


var detialWealthIncomeDisInfoTable;
function initWealthIncomeDisInfoTable(){
	detialWealthIncomeDisInfoTable = $("#detialWealthIncomeDisInfoTable").datagrid({
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [ [
				{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'pdWealthIncomeDistributeId',
					title : '财富产品收益分配参数流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.pdWealthIncomeDistributeId;
					}
				},
				{
					field : 'pdWealthId',
					title : '财富产品流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.pdWealthId;
					}
				},
				{
					field : 'distributeDate',
					title : '分配日期',
					width : 100,
					editor: {
						type : 'datebox',
						options : {
							required:true,
							validType:'validDate'
						}
					},
					formatter : function(value, row, index) {
						return row.distributeDate;
					}
				},
				{
					field : 'distributeStartDate',
					title : '分配起期(闭区间)',
					width : 100,
					editor: {
						type : 'datebox',
						options : {
							required:true,
							validType:'validDate'
						}
					},
					formatter : function(value, row, index) {
						return row.distributeStartDate;
					}
				},
				{
					field:'distributeEndDate',
					title : '分配止期(闭区间)',
					width : 100,
					editor: {
						type : 'datebox',
						options : {
							required:true,
							validType:'validDate'
						}
					},
					formatter : function(value, row, index) {
						return row.distributeEndDate;
					}
				},
				{
					field:'principalDistributeRate',
					title : '本金分配比例（%）',
					width : 100,
					editor: {
						type : 'numberbox',
						options : {
							min:0,  
							max:100,
						    precision:2, 
						    tipPosition:'left',
							validType:'validDecNum'
						}
					},
					formatter : function(value, row, index) {
						return row.principalDistributeRate;
					}
				}
				] ],
		onLoadSuccess : function() {
			$('#wealthIncomeDisInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}
/**
 * (固定收益类)费用比例信息可编辑表格
 * 
 */
var fixedWealthRateTable;
function initWealthRateTable() {
	fixedWealthRateTable = $("#fixedWealthRateTable")
			.datagrid(
					{
						method : 'post',
						iconCls : 'icon-edit', // 图标
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						sortName : 'id', // 排序的列
						sortOrder : 'desc', // 倒序
						remoteSort : true, // 服务器端排序
						idField : 'id', // 主键字段
						queryParams : {}, // 查询条件
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
									width : 2
								}, // 显示复选框
								 {
									field : 'weathFeeRateId',
									title : '费用比例流水号',
									hidden : true,
									formatter : function(value, row, index) {
										return row.weathFeeRateId;
									} // 需要formatter一下才能显示正确的数据
								},
								{
									field : 'feeType',
									title : '费用类型',
									width : 100,
									sortable : true,
									editor : {
										type : 'combobox',
										options : {
											required:true,
											valueField : 'code',
											textField : 'codeName',
											// 查询财富类费用类型
											url : contextPath
													+ '/codeQuery/tdCodeQuery?codeType=wealthFeeType',
											onSelect : function() {
												var edFeeType = wealthRateTable
														.datagrid(
																'getEditor',
																{
																	index : wealthRateTableRowIndex,
																	field : 'feeType'
																});
												var edFeeTypeName = $(
														edFeeType.target)
														.combobox('getText');
												wealthRateTable
														.datagrid('getRows')[wealthRateTableRowIndex]['feeTypeName'] = edFeeTypeName;
											}
										}
									},
									formatter : function(value, row, index) {
										return row.feeTypeName;
									}

								}, {
									field : 'feeTypeName',
									title : '费用类型名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.feeTypeName;
									} // 需要formatter一下才能显示正确的数据
								},
								 {
									field : 'subscriptionFeeLower',
									title : '认购金额下限(元)-闭区间',
									width : 100,
									sortable : true,
									editor: {
										type:"numberbox",
										options:{
											min:0,
											precision:2
										
										}
									},
									formatter : function(value, row, index) {
										return row.subscriptionFeeLower;
									}
								}, 
								{
									field : 'subscriptionFeeUpper',
									title : '认购金额上限(元)-开区间',
									width : 100,
									sortable : true,
									editor: {
										type:"numberbox",
										options:{
											min:0,
											precision:2
										
										}
									},
									formatter : function(value, row, index) {
										return row.subscriptionFeeUpper;
									}
								},
								{
									field : 'beneficialType',
									title : '受益权类型',
									width : 100,
									sortable : true,
									editor: {
										type : 'combobox',
										options : {
										required : true ,
										valueField : 'code',
										textField : 'codeName',
										url : contextPath+ '/codeQuery/tdCodeQuery?codeType=beneficialType',// 查询财富类费用类型
										onSelect : function() {
										   var edBeneficialType = wealthRateTable.datagrid('getEditor',{index : wealthRateTableRowIndex,field : 'beneficialType'});
										   var edBeneficialTypeName = $(edBeneficialType.target).combobox('getText');
										   wealthRateTable.datagrid('getRows')[wealthRateTableRowIndex]['beneficialTypeName'] = edBeneficialTypeName;
										}
									}
									},
									formatter : function(value, row, index) {
										return row.beneficialTypeName;
								   }
								},
								{
									field : 'beneficialTypeName',
									title : '受益权类型名称',
									hidden : true,
									formatter : function(value, row, index) {
									return row.beneficialTypeName;
									} 
								}, 
								{
									field : 'closeDperiod',
									title : '产品期间',
									width : 100,
									sortable : true,
									editor: {
										type:"numberbox",
										options:{
											min:0
										}
									},
									formatter : function(value, row, index) {
										return row.closeDperiod;
								   }
								},
								{
									field : 'closeDperiodunit',
									title : '产品期间单位',
									width : 100,
									sortable : true,
									editor: {
										type : 'combobox',
										options : {
										valueField : 'code',
										textField : 'codeName',
										url : contextPath+ '/codeQuery/tdCodeQuery?codeType=closedPeriodUnit',// 查询财富类费用类型
										onSelect : function() {
										   var edCloseDperiodunit = wealthRateTable.datagrid('getEditor',{index : wealthRateTableRowIndex,field : 'closeDperiodunit'});
										   var edCloseDperiodunitName = $(edCloseDperiodunit.target).combobox('getText');
										   wealthRateTable.datagrid('getRows')[wealthRateTableRowIndex]['closeDperiodunitName'] = edCloseDperiodunitName;
										}
										
									 }
									},
									formatter : function(value, row, index) {
										return row.closeDperiodunitName;
								   }
								},
								{
									field : 'closeDperiodunitName',
									title : '产品期间单位名称',
									hidden : true,
									formatter : function(value, row, index) {
									return row.closeDperiodunitName;
									} 
								}, 
								
								{
									field : 'feeRate',
									title : '费用率(%)',
									width : 100,
									sortable : true,
									editor: {
										type:'validatebox',
										options:{
											validType:['validDecNum'],
											required:true
										}
									},
									formatter : function(value, row, index) {
										return row.feeRate;
									}
								},  {
									field : 'expectedFeeRate',
									title : '客户预期收益率(%)',
									width : 100,
									sortable : true,
									editor: {
										type:'validatebox',
										options:{
											validType:['validDecNum'],
											required:true
										}
									},
									formatter : function(value, row, index) {
										return row.expectedFeeRate;
									}
								},
								 {
									field : 'addExpectedFeeRate',
									title : '附加预期收益率(%)',
									width : 100,
									sortable : true,
									editor: {
										type:'validatebox',
										options:{
											validType:['validDecNum']
										}
									},
									formatter : function(value, row, index) {
										return row.addExpectedFeeRate;
									}
								}] ],
						onLoadSuccess : function() {
							$('#fixedWealthRateTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						}
					});
}

//初始需要修改的数据
function getUpdateInitValue(){
	$.ajax({
		type:'post',
		url:contextPath+"/product/queryProductCoreInfo",
		data:{param:$('#detail_productId').val()},
		cache:false,
		success:function(result){
				if(result.success){
					var resultObj = result.obj;
					if (resultObj.modifyPdWealthChargeRate!=null&&resultObj.modifyPdWealthChargeRate!=undefined&&resultObj.modifyPdWealthChargeRate!="") {
						//固定收益分类信息
						setInputValueById("fixedDiffrentInfoForm",resultObj.modifyPdWealthChargeRate);
						if(resultObj.modifyPdWealthChargeRate2!=null && resultObj.modifyPdWealthChargeRate2!=" "){
						$("#productDetail_taxFee").val(resultObj.modifyPdWealthChargeRate2.taxFee);
						$("#productDetail_channelFee").val(resultObj.modifyPdWealthChargeRate2.channelFee);}
					}
					if (resultObj.modifyPdWealthFeeRateList!=null&&resultObj.modifyPdWealthFeeRateList!=undefined&&resultObj.modifyPdWealthFeeRateList!="") {
						//财富费用比例信息
						loadJsonObjData("fixedWealthRateTable",resultObj.modifyPdWealthFeeRateList);
					}
					if (resultObj.modifyPdwealthIncomeDisMapList!=null&&resultObj.modifyPdwealthIncomeDisMapList!=undefined&&resultObj.modifyPdwealthIncomeDisMapList!="") {
					// 财富固定收益类收益分配信息
					loadJsonObjData("detialWealthIncomeDisInfoTable",resultObj.modifyPdwealthIncomeDisMapList);
					}
					if (resultObj.modifyPdwealthStockDisMapList!=null&&resultObj.modifyPdwealthStockDisMapList!=undefined&&resultObj.modifyPdwealthStockDisMapList!="") {
						// 财富股权类收益分配信息
						loadJsonObjData("detialWealthStockDisInfoTable",resultObj.modifyPdwealthStockDisMapList);
						}
					}else{
						$.messager.alert('提示', result.msg);
					}
				}
	});
}

var detialWealthStockDisInfoTable;
function initWealthStockDisInfoTable(){
	detialWealthStockDisInfoTable = $("#detialWealthStockDisInfoTable").datagrid({
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [ [
				{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'pdWealthStockDisId',
					title : '财富产品收益分配参数流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.pdWealthStockDisId;
					}
				},
				{
					field : 'pdWealthId',
					title : '财富产品流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.pdWealthId;
					}
				},
				{
					field : 'distributeDate',
					title : '分配日期',
					width : 100,
					editor: {
						type : 'datebox',
						options : {
							required:true,
							validType:'validDate'
						}
					},
					formatter : function(value, row, index) {
						return row.distributeDate;
					}
				},
				{
					field:'distributeMoney',
					title : '每百万分配金额（元）',
					width : 100,
					editor: {
						type : 'numberbox',
						options : {
							required:true,
							min:0,  
						    precision:2, 
						    tipPosition:'left',
							validType:'validDecNum'
						}
					},
					formatter : function(value, row, index) {
						return row.distributeMoney;
					}
				}
				] ],
		onLoadSuccess : function() {
			$('#wealthStockDisInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

var detialCustInvestInfoTable;
function initDetialCustInvestInfoTable(){
	detialCustInvestInfoTable = $("#detialCustInvestInfoTable").datagrid({
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		url : contextPath+"/product/queryCustInvestList",
		queryParams : {"productId":productId}, // 查询条件
		pagination : true, // 显示分页
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [ [
				{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{field : 'tradeInfoId',title : '交易号',hidden : true,formatter : function(value, row, index) {
						return row.tradeInfoId;}
				},
				{
					field : 'custName',
					title : '客户姓名',
					width : 100,
					formatter : function(value, row, index) {
						return row.custName;
					}
				},{
					field : 'tradeTotalAssets',
					title : '投资金额',
					width : 100,
					formatter : function(value, row, index) {
						return row.tradeTotalAssets;
					}
				}]],
				toolbar:[{
					text : '导出总表',
					iconCls : 'icon-redo',
					handler : function() {
						var queryParam = {};
						queryParam.productId = productId;
				        window.open(contextPath
						+ '/product/detialCustInvestInfo.xls?queryParam='
						+ $.toJSON(queryParam));
					}
				}],
		onLoadSuccess : function() {
			$('#detialCustInvestInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}
var detialFloatCustInvestInfoTable;
function initDetialFloatCustInvestInfoTable(){
	detialFloatCustInvestInfoTable = $("#detialFloatCustInvestInfoTable").datagrid({
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		url : contextPath+"/product/queryCustFloatInvestList",
		queryParams : {"productId":productId}, // 查询条件
		pagination : true, // 显示分页
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [ [
				{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{field : 'tradeInfoId',title : '交易号',hidden : true,formatter : function(value, row, index) {
						return row.tradeInfoId;}
				},
				{
					field : 'custName',
					title : '客户姓名',
					width : 100,
					formatter : function(value, row, index) {
						return row.custName;
					}
				},
				{
					field : 'remainScale',
					title : '存续份额(万份)',
					width : 100,
					formatter : function(value, row, index) {
						return row.remainScale;
					}
				}/*{
					field : 'tradeTotalAssets',
					title : '存续份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.tradeTotalAssets;
					}
				}*/]],
				toolbar:[{
					text : '导出总表',
					iconCls : 'icon-redo',
					handler : function() {
						var queryParam = {};
						queryParam.productId = productId;
				        window.open(contextPath
						+ '/product/detialCustInvestInfo.xls?queryParam='
						+ $.toJSON(queryParam));
					}
				}],
		onLoadSuccess : function() {
			$('#detialFloatCustInvestInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}