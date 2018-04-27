var factorTypeUrl = "";
var productId = null;
var productTypeCode = null;
var productSubTypeCode = null;
var productStatus = null;
var roleId = null;
var fileOperate = null;
/**
 * 初始化列表
 */
jQuery(function($){
	productId = $("#detail_ProductId").val();
	productTypeCode = $("#detail_productTypeCode").val();
	productSubTypeCode = $("#detail_productSubTypeCode").val();
	productStatus = $("#detail_productStatus").val();
	roleId = $("#detail_roleId").val();
	//附件权限控制
	roleControl();
	//初始化产品基本信息
	getProductBasicInfo();
	//初始化产品核心信息
	getProductCoreInfo();
	//初始化其他所有信息
	getProductCoreAllInfo();
	//(固定收益类)费用比例信息表格
	initWealthRateTable();
	//(固定收益类)收益表格
	initWealthIncomeDisInfoTable();
	//录入项表格
	initfactorInfoTable();
	//录入项类型
	if(productTypeCode=='1'){
		modifyfactorType="wealthfactorCode";
		factorTypeUrl = contextPath+ '/codeQuery/tdCodeQuery?codeType='+modifyfactorType;
	}
});

/**
 * 附件权限控制
 */
function roleControl(){
	try{
		if(roleId !=null){
			if(roleId=='16'||roleId=='1'){
				fileOperate = "uploadFile";
				$("#detailProduct_checkFileInfo").hide();
			}else{
				fileOperate = "queryFile";
				$("#detailProduct_loadFileInfo").hide();
			}
		}else{
			$.messager.alert('提示', '获取角色信息失败');
		}
	}catch (e) {
		$.messager.alert('提示', e);
	}
}

/**
 *获取产品基本信息
 */
function getProductBasicInfo(){
	var param ={};
	param.productId = $("#detail_ProductId").val();
	$.ajax({
		type:'post',
		url:contextPath+'/reports/getUpdateProductBasicInfo',
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if(resultObj.modifyBasicProduct!=null){
						setInputValueById("detailProduct_basicInfoForm",resultObj.modifyBasicProduct);
					}
				}else{
					$.messager.alert('提示', result.msg);
				}
			}catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 *获取产品核心信息
 */
function getProductCoreInfo(){
	var param ={};
	param.productId = $("#detail_ProductId").val();
	$.ajax({
		type:'post',
		url:contextPath+'/reports/getProductCoreInfo',
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if(resultObj.modifyPdWealth!=null&&resultObj.modifyPdWealth!=undefined&&resultObj.modifyPdWealth!=""){
					setInputValueById("wealthProductInfoForm",resultObj.modifyPdWealth);
				}
					if(resultObj.modifyPdWealth.beneficialTypes!=null&&resultObj.modifyPdWealth.beneficialTypes!=undefined&&resultObj.modifyPdWealth.beneficialTypes!=""){
						var a=resultObj.modifyPdWealth.beneficialTypes.substr(1).split(",");
						$('#modify_beneficialTypes').val(a);
					}
				}else{
					$.messager.alert('提示', result.msg);
				}
			}catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 *获取产品核心信息
 */
function getProductCoreAllInfo(){
	$.ajax({
		type:'post',
		url:contextPath+'/product/queryProductCoreInfo',
		data:{param:$("#detail_ProductId").val()},
		cache:false,
		success:function(result){
			try{
				if(result.success){
				var resultObj = result.obj;
				if(productTypeCode == '1'){
					 if(productSubTypeCode=='02'){
						 $('#wealthProductBasicInfo').parent(".panel").css("display","");
	                        $('#wealthProductCoreInfo').parent(".panel").css("display","");
	                        //（浮动股权）财富费用信息
	                        $('#fgTypeInfo').parent(".panel").css("display","none");
	                        //固定财富费用信息Form
	                        $('#gTypeInfo').parent(".panel").css("display","");
	                        $('#wealthFeeInfo').parent(".panel").css("display","");
	                        //固定收益分配
	                        $('#wealthIncomeDisInfo').parent(".panel").css("display","");
	                        $("#modify_promptTransferWorkDays").show();//显示划款工作日
	                        $("#modify_transferWorkDays").show();//显示划款工作日
	                        $("#prompthistoryEarnrate").css("display","none");//隐藏历史收益率
	                        $("#modifyPdCoreInfo_historyEarnrate").css("display","none");//隐藏历史历史收益率
	                        $("#prompthistoryEarnratePeriod").css("display","none");//隐藏历史收益率
	                        $("#modifyPdCoreInfo_historyEarnratePeriod").css("display","none");//隐藏历史历史收益率
	                        $("#closeDperiods_redemptionFee").hide();
	                        if (resultObj.modifyPdWealthChargeRate!=null&&resultObj.modifyPdWealthChargeRate!=undefined&&resultObj.modifyPdWealthChargeRate!="") {
	                            //固定收益分类信息
	                            setInputValueById("gTypeForm",resultObj.modifyPdWealthChargeRate);
	                            if(resultObj.modifyPdWealthChargeRate2!=null && resultObj.modifyPdWealthChargeRate2!=" "){
	                            $("#modify_taxFee").val(resultObj.modifyPdWealthChargeRate2.taxFee);
	                            $("#modify_channelFee").val(resultObj.modifyPdWealthChargeRate2.channelFee);
	                            }
	                        }
	                        if (resultObj.modifyPdWealthFeeRateList!=null&&resultObj.modifyPdWealthFeeRateList!=undefined&&resultObj.modifyPdWealthFeeRateList!="") {
	                            //财富费用比例信息
	                            loadJsonObjData("wealthRateTable",resultObj.modifyPdWealthFeeRateList);
	                        }
	                        // 财富类费用比例全局变量
	                        var wealthRateTableRowIndex = null;
	                        if (resultObj.modifyPdwealthIncomeDisMapList!=null&&resultObj.modifyPdwealthIncomeDisMapList!=undefined&&resultObj.modifyPdwealthIncomeDisMapList!="") {
	                        // 财富固定收益类收益分配信息
	                        loadJsonObjData("wealthIncomeDisInfoTable",resultObj.modifyPdwealthIncomeDisMapList);
	                        }
	                        wealthIncomeDisInfoTableRowIndex = null;
					 }else{
						//赎回费初始化
	                        var closeDperiodType = $("#modify_closeDperiodType").val();
	                        if(closeDperiodType=='2'){
	                            $("#redemptionFee1").hide();
	                            $("#modify_redemptionFee").hide();
	                
	                        }else{
	                            $("#redemptionFee1").show();
	                            $("#modify_redemptionFee").show();
	                        }
	                        $('#wealthProductBasicInfo').parent(".panel").css("display","");
	                        $('#wealthProductCoreInfo').parent(".panel").css("display","");
	                        //（浮动股权）财富费用信息
	                        $('#fgTypeInfo').parent(".panel").css("display","");
	                        //固定财富费用信息Form
	                        $('#gTypeInfo').parent(".panel").css("display","none");
	                        $('#wealthFeeInfo').parent(".panel").css("display","none");
	                        //固定收益分配
	                        $('#wealthIncomeDisInfo').parent(".panel").css("display","none");
	                        $("#modify_promptTransferWorkDays").hide();//隐藏划款工作日
	                        $("#modify_transferWorkDays").hide();//隐藏划款工作日
	                        
	                        $("#promptIncomeWay").css("display","none");//隐藏收益方式
	                        //$('#modifyPdCoreInfo_incomeWay').combobox('destroy');//隐藏收益方式
	                        if (resultObj.modifyPdWealthChargeRate!=null&&resultObj.modifyPdWealthChargeRate!=undefined&&resultObj.modifyPdWealthChargeRate!="") {
	                            //股权浮动分类信息
	                            setInputValueById("fgTypeForm",resultObj.modifyPdWealthChargeRate);
	                            //console.info(resultObj.modifyPdWealthChargeRate2);
	                            if(resultObj.modifyPdWealthChargeRate2!=null && resultObj.modifyPdWealthChargeRate2!=" "){
	                            $("#modify_fixManagementFeeRatio").val(resultObj.modifyPdWealthChargeRate2.fixManagementFeeRatio);
	                            $("#modify_floatManagementFeeRatio").val(resultObj.modifyPdWealthChargeRate2.floatManagementFeeRatio);
	                            $("#modify_taxFee1").val(resultObj.modifyPdWealthChargeRate2.taxFee);}
	                    }
					 }
				}
				 //录入信息
                if(resultObj.modifyPdFactorRateList!=null&&resultObj.modifyPdFactorRateList!=undefined&&resultObj.modifyPdFactorRateList!=""){
                    loadJsonObjData("factorInfoTable",resultObj.modifyPdFactorRateList);
                    factorInfoTableRowIndex=null;
                }
				}else{
					 $.messager.alert('提示', result.msg);
				}
	  }
		catch (e) {
			$.messager.alert('提示', e);
		}
		}
	});
}

/**
 * (固定收益类)费用比例信息可编辑表格
 * 
 */
var wealthRateTable;
function initWealthRateTable() {
	wealthRateTable = $("#wealthRateTable")
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
											validType:['validDecNum']
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
							$('#wealthRateTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						}
//						,
//					 onClickRow:function(index){
//					 wealthRateTableEditOneRow(index);
//					 },
//					 toolbar:"#wealthRateTable_tb"
					});
}
/** ******************************财富类费用比例编辑表格的增/删/编辑************************************ */
//// 增加一行
//function wealthRateTableAddOneRow() {
//	wealthRateTableRowIndex = addOneRow(wealthRateTable,
//			wealthRateTableRowIndex);
//}
//// 删除一行
//function wealthRateTableRemoveOneRow() {
//	removeOneRow(wealthRateTable, wealthRateTableRowIndex);
//	wealthRateTableRowIndex = null;
//}
//// 编辑指定行
//function wealthRateTableEditOneRow(index) {
//	editOneRow(wealthRateTable, wealthRateTableRowIndex, index);
//	wealthRateTableRowIndex = index;
//}
//// 锁定编辑行
//function wealthRateTableLockOneRow() {
//	if(lockOneRow(wealthRateTable, wealthRateTableRowIndex)){
//		wealthRateTableRowIndex = undefined;
//		return true;
//	}
//	else{
//		return false;
//	}
//	
//}

var wealthIncomeDisInfoTable;
function initWealthIncomeDisInfoTable(){
	wealthIncomeDisInfoTable = $("#wealthIncomeDisInfoTable").datagrid({
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
//		,
//		onClickRow : function(index) {
//			wealthIncomeDisInfoTableEditOneRow(index);
//		},
//		toolbar:"#wealthIncomeDisInfoTable_tb"
	});
}

/** ******************************录入信息编辑表格的增/删/编辑************************************ */
////增加一行
//function wealthIncomeDisInfoTableAddOneRow() {
//	wealthIncomeDisInfoTableRowIndex = addOneRow(wealthIncomeDisInfoTable,wealthIncomeDisInfoTableRowIndex);
//}
////删除一行
//function wealthIncomeDisInfoTableRemoveOneRow() {
//	removeOneRow(wealthIncomeDisInfoTable, wealthIncomeDisInfoTableRowIndex);
//	wealthIncomeDisInfoTableRowIndex = null;
//}
////编辑指定行
//function wealthIncomeDisInfoTableEditOneRow(index) {
//	
//	if(editOneRow(wealthIncomeDisInfoTable, wealthIncomeDisInfoTableRowIndex, index))
//	{
//		wealthIncomeDisInfoTableRowIndex = index;
//	}
//}
//
////锁定编辑行
//function wealthIncomeDisInfoTableLockOneRow() {
//	if(lockOneRow(wealthIncomeDisInfoTable, wealthIncomeDisInfoTableRowIndex)){
//		wealthIncomeDisInfoTableRowIndex = undefined;
//		return true;
//	}
//	else{
//		return false;
//	}
//
//}
/******************************************************/

/**
 * 录入信息
 * 
 */
var factorInfoTable;
function initfactorInfoTable() {
	factorInfoTable = $("#factorInfoTable")
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
								},
								{
								
									field : 'factorName',
									title : '录入项名称',
									width : 130,
									sortable : true,
									editor : {
										type : 'combobox',
										options : {
											url:factorTypeUrl,
											required:true,
											valueField : 'code',
											textField : 'name',
											onShowPanel:function(){
												var codeType=null;
												if(productTypeValue=='1'){
													codeType='wealthfactorCode';
												}
												else{
													codeType='riskfactorCode';
												}
												var url = contextPath+ '/codeQuery/tdCodeQuery?codeType='+ codeType;
												var edfactorCode = factorInfoTable.datagrid('getEditor',
														{	index : factorInfoTableRowIndex,
															field : 'factorName'
														});
												
												$(edfactorCode.target).combobox("reload",url);
											},												
											onSelect : function() {
												// 获得这一行的编辑器
												var edfactorName = factorInfoTable.datagrid('getEditor',
																{	index : factorInfoTableRowIndex,
																	field : 'factorName'
																});
												// 获得这行的URL的CodeNAme
												var edfactorTrueName = $(edfactorName.target).combobox('getText');
												// 获取这行的URL的Code
												var edfactorTrueCode = $(edfactorName.target).combobox('getValue');
												// 将CodeName赋给中间传递变量隐藏列
												factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorTrueName'] = edfactorTrueName;
												factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorCode'] = edfactorTrueCode;
												// 级联问题解决，通过factorName级联显示factorValue
												var edfactorValue = factorInfoTable.datagrid('getEditor',{index : factorInfoTableRowIndex,field : 'factorValueCode'});
												var url = contextPath+ '/codeQuery/tdCodeQuery?codeType='+ edfactorTrueCode;
												// 将获得编辑器重新进行加载
												$(edfactorValue.target).combobox("reload", url);
											}
										}

									},
									formatter : function(value, row, index) {
										// 返回隐藏列的factorTrueName
										return row.factorTrueName;
									}
								},
								{
									field : 'factorTrueName',
									title : '录入项真名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.factorTrueName;
									}
								},
								{
									field : 'factorCode',
									title : '录入项名称编码',
									hidden : true,
									formatter : function(value, row, index) {
										return row.factorCode;
									}
								},
								{
									field : 'chooseFlag',
									title : '是否必录',
									width : 130,
									sortable : true,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'code',
											textField : 'codeName',
											required:true,
											url : contextPath
													+ '/codeQuery/tdCodeQuery?codeType=isOrNot',
											onSelect : function() {
												var edChooseFlag = factorInfoTable
														.datagrid(
																'getEditor',
																{
																	index : factorInfoTableRowIndex,
																	field : 'chooseFlag'
																});
												var edChooseFlagName = $(edChooseFlag.target).combobox('getText');
												factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['chooseFlagName'] = edChooseFlagName;
											}

										}
									},
									formatter : function(value, row, index) {
										return row.chooseFlagName;
									}
								},
								{
									field : 'chooseFlagName',
									title : '是否必录名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.chooseFlagName;
									} // 需要formatter一下才能显示正确的数据
								},
								{
									field : 'factorType',
									title : '录入项类型',
									width : 130,
									sortable : true,
									editor : {
										type : 'combobox',
										options : {
											required:true,
											valueField : 'code',
											textField : 'codeName',
											url : contextPath+ '/codeQuery/tdCodeQuery?codeType=factorType',
											onSelect : function() {
												var edFactorType = factorInfoTable.datagrid('getEditor',
																{
																	index : factorInfoTableRowIndex,
																	field : 'factorType'
																});
												var edFactorTypeName = $(edFactorType.target).combobox('getText');
												factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorTypeName'] = edFactorTypeName;
												

												//选择录入项类型下拉值时，进行下拉要素值处理
												var factorCode = factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex].factorCode;
												if(factorCode!=null&&factorCode=='beneficialType'){
													var edFactorName =beneficialTypesCombobox.combobox('getText');
													var edFactorValue='';
													var beneficialTypesValues = beneficialTypesCombobox.combobox('getValues');
													if(beneficialTypesValues!=null&&beneficialTypesValues.length>0){
														for(var i=0;i<beneficialTypesValues.length;i++ ){
							                            	var value=beneficialTypesValues[i];
							                            	if(value!=null&&value!=""&&value!=undefined){
							                            		edFactorValue =edFactorValue+","+value;
							                            		}
							                            	}
													}
													factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValueName'] = edFactorName;
					                                factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValue'] = edFactorValue;
												}
												//封闭期处理
												if(factorCode!=null&&factorCode=='closedPeriods'){
													var edFactorValue='';
													var closedPeriods=$("#modify_closeDperiod").val().split('/');
													var edFactorName=$("#modify_closeDperiod").val();
													if(closedPeriods!=null&&closedPeriods.length>0){
														for(var i=0;i<closedPeriods.length;i++ ){
															var value=closedPeriods[i];
															if(value!=null&&value!=""&&value!=undefined){
																edFactorValue=edFactorValue+','+value;
															}
														}
													}
													factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValueName'] = edFactorName;
					                                factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValue'] = edFactorValue;	
												}
												//封闭期单位处理
												if(factorCode!=null&&factorCode=='closedPeriodUnit'){
													var edFactorName = closeDperiodUnitCombobox.combobox('getText');
													var edFactorValue = ","+closeDperiodUnitCombobox.combobox('getValue');//直接将上面的值赋予
													factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValueName'] = edFactorName;
					                                factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValue'] = edFactorValue;
												}
												
												
												
											}

										}
									},
									formatter : function(value, row, index) {
										return row.factorTypeName;
									}
								},
								{
									field : 'factorTypeName',
									title : '录入项类型名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.factorTypeName;
									}
								},

								{
									field : 'factorValueCode',
									title : '下拉项要素值',
									width : 130,
									sortable : true,
									editor : {
										type : 'combobox',
										options : {
											multiple : true,
											valueField : 'code',
											textField : 'codeName',
											onSelect : function() {
							         var edFactorValueCode = factorInfoTable.datagrid('getEditor',
														{
															index : factorInfoTableRowIndex,
															field : 'factorValueCode'
														});
									var edFactorName = $(edFactorValueCode.target).combobox('getText');
									var edFactorValue = $(edFactorValueCode.target).combobox('getValues');
									var factorValue = '';
									//将factorCode值拼装
									for(var i=0;i<edFactorValue.length;i++ ){
										var value=edFactorValue[i];
										if(value!=null&&value!=""&&value!=undefined){
											factorValue += value+",";
										}
									}
									if(factorValue.length>0&&factorValue.indexOf(",")>=0){
										factorValue = factorValue.substring(0,factorValue.length-1);
									}
									edFactorValue=','+factorValue;
									//获得页面上的值
									if(edFactorName!=null){
										edFactorName=edFactorName.substring(1,edFactorName.length);
									}
                                    factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValueName'] = edFactorName;
                                    factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValue'] = edFactorValue;	
                                    
											}

										}
									},

									formatter : function(value, row, index) {
										return row.factorValueName;
									}
								},
								{
									field : 'factorValueName',
									title : '录入项类型名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.factorValueName;
									}
								},
								{
									field : 'factorValueNameCode',
									title : '录入项类型名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.factorValueNameCode;
									}
								},
								{
									field:'factorValue',
									title : '录入项类型名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.factorValue;
									}
								}
								] ],
						onLoadSuccess : function() {
							$('#factorInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						}
//						,
//						onClickRow : function(index) {
//							factorInfoTableEditOneRow(index);
//						},
//						toolbar:"#factorInfoTable_tb"
					});
}

/** ******************************录入信息编辑表格的增/删/编辑************************************ */
//// 增加一行
//function factorInfoTableAddOneRow() {
//	factorInfoTableRowIndex = addOneRow(factorInfoTable,
//			factorInfoTableRowIndex);
//}
//// 删除一行
//function factorInfoTableRemoveOneRow() {
//	removeOneRow(factorInfoTable, factorInfoTableRowIndex);
//	factorInfoTableRowIndex = null;
//}
//// 编辑指定行
//function factorInfoTableEditOneRow(index) {
//	editOneRow(factorInfoTable, factorInfoTableRowIndex, index);
//	factorInfoTableRowIndex = index;
//}
//
////锁定编辑行
//function factorInfoTableLockOneRow() {
//	if(lockOneRow(factorInfoTable, factorInfoTableRowIndex)){
//		factorInfoTableRowIndex = undefined;
//		return true;
//	}
//	else{
//		return false;
//	}
//
//}

/**
 * 上传附件
 */
function loadFileInfo(){
	if (productId != null) {
		var param = {};
		param.businessNo = productId;
		param.businessType = "09";
		param.operate = fileOperate;
		param.roleId = roleId;
		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));
	} else {
		$.messager.alert('提示',"请先添加产品信息，再进行附件上传！");
	}
}
function addFileWindow(title, href) {
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

/**
 * 返回
 */
function backListDetailProduct(){
	$('#addWindow').window('destroy');
//	initProductQueryTable();
}

function productCodeToUpperCase(){
	var productCode = $("#updateProduct_productCode").val();
	if(productCode!=null&&productCode!=""&&productCode!=undefined){
		productCode = productCode.toLocaleUpperCase();
		$("#updateProduct_productCode").val(productCode);
	}
	 
}
