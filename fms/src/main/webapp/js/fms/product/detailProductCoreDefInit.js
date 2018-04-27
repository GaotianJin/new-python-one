//保险类费用比例全局变量
var insuraceRateTableRowIndex = null;
// 财富类费用比例全局变量
var wealthRateTableRowIndex = null;
// 录入信息全局变量
var factorInfoTableRowIndex = null;
//收益分配配置信息列表全局变量
var wealthIncomeDisInfoTableRowIndex = null;
//浮动类收益分配配置信息列表全局变量
var wealthStockDisInfoTableRowIndex = null;
// 定义产品类型全局变量
var productTypeValue = null;
// 定义产品子类全局变量
var productSubTypeValue = null;
// 上传时需要关联的产品Id全局变量
var productId = null;
//获取产品类型和产品子类
var modifyProductTypeCode=null;
var modifyProductSubTypeCode=null;
var modifyProductId=null;
var modifyProductStatus=null;
//录入项类型
var modifyfactorType=null;
var factorTypeUrl = "";
var beneficialTypesCombobox;
var expectOpenDayRulesCombobox;
var closeDperiodUnitCombobox;
/**
 * 页面初始化加载
 */
jQuery(function($) {
	
	modifyProductTypeCode=$("#modifyProductType").val();
	modifyProductSubTypeCode=$("#modifyProductSubType").val();
	modifyProductId=$("#modifyProductId").val();
	modifyProductStatus=$("#modifyProductStatus").val();
	productSubTypeValue=modifyProductSubTypeCode;
	productTypeValue=modifyProductTypeCode;
	//录入项类型
	if(modifyProductTypeCode=='1'){
		modifyfactorType="wealthfactorCode";
		factorTypeUrl = contextPath+ '/codeQuery/tdCodeQuery?codeType='+modifyfactorType;
		//针对不同的财富产品动态添加输入框”01股权“”02固定“”03浮动““04海外”输入框一定要有id，不然无法初始化值
		if(modifyProductSubTypeCode=="01"){
			$(".investDirection").show();//股权类显示投资方向
			$(".investScope").remove();
			$(".investStrategy").remove();
		}else if(modifyProductSubTypeCode=="02"){
			$(".investScope").show();//固定类显示投资范围
			$(".investDirection").remove();
			$(".investStrategy").remove();
		}else if(modifyProductSubTypeCode=="03"||modifyProductSubTypeCode=="04"){
			$(".investStrategy").show();//浮动类显示投资策略
			$(".investDirection").remove();
			$(".investScope").remove();
		}
	}
	else{
		modifyfactorType="riskfactorCode";
		factorTypeUrl = contextPath+ '/codeQuery/tdCodeQuery?codeType='+modifyfactorType;
	}
	// 加载所有的下拉框
	initAllCombobox();
	// 加载所有的必填项信息
	initAllValidateBox();
	//获取需要修改的数据
	getUpdateInitValue();	
	// (财富类)费用比例信息
	initWealthRateTable();
	// (保险类)费用比例信息
	initInsuraceRateTable();
	//初始化收益分配信息表格
	initWealthIncomeDisInfoTable();
	//浮动类初始化收益分配信息表格
	initWealthStockDisInfoTable();
	// 录入信息初始化
	initfactorInfoTable();
	// 附件信息列表
	initdefFileInfoTable();
	
	//所有金额输入框，输入时转换大写
	$("#updatePdCoreInfo_startInvestMoney," +
			"#updatePdCoreInfo_investLimitMoney," +
			"#updatePdCoreInfo_investIncreaseMoney").bind('input propertychange', function(e){  
	    //将本次修改的值更新到rowData的相应列数据中  
	    //rowData[workRateEditor.field] = $(this).val();
			 var tipsContent = numToUpper($(this).val());
			// alert(tipsContent);
			 $(this).tips({
	             side:1,  //1,2,3,4 分别代表 上右下左
	             msg:tipsContent,//tips的文本内容
	             color:'#FFF',//文字颜色，默认为白色
	             bg:'#FD9720',//背景色，默认为红色
	             time:3,//默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
	             x:0,// 默认为0 横向偏移 正数向右偏移 负数向左偏移
	             y:0 // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
	     });
	}); 
	
	$("#modify_financingScale").bind('input propertychange', function(e){  
	    //将本次修改的值更新到rowData的相应列数据中  
	    //rowData[workRateEditor.field] = $(this).val();
			 var tipsContent = numToUpper($(this).val()*10000);
			// alert(tipsContent);
			 $(this).tips({
	             side:1,  //1,2,3,4 分别代表 上右下左
	             msg:tipsContent,//tips的文本内容
	             color:'#FFF',//文字颜色，默认为白色
	             bg:'#FD9720',//背景色，默认为红色
	             time:3,//默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
	             x:0,// 默认为0 横向偏移 正数向右偏移 负数向左偏移
	             y:0 // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
	     });
	}); 
});

/**
 * 
 * 初始文本格式的必录项
 */
/*function initAllValidateBox(){
	$('#modify_closeDperiod').validatebox({required:true});
}*/
//初始需要修改的数据
function getUpdateInitValue(){
	$.ajax({
		type:'post',
		url:contextPath+"/product/queryProductCoreInfo",
		data:{param:$('#modifyProductId').val()},
		cache:false,
		success:function(result){
			try {
				if(result.success){
					var resultObj = result.obj;
					//1-财富产品，2-保险产品
					if(modifyProductTypeCode=='1'){
						if (resultObj.modifyPdWealth!=null&&resultObj.modifyPdWealth!=undefined&&resultObj.modifyPdWealth!="") {
						//财富产品信息
						setInputValueById("wealthProductInfoForm",resultObj.modifyPdWealth);
						var historyEarn=resultObj.modifyPdWealth.historyEarnrate;
						$("#modifyPdCoreInfo_historyEarnrate").val(historyEarn);
						if(resultObj.modifyPdWealth.beneficialTypes!=null&&resultObj.modifyPdWealth.beneficialTypes!=undefined&&resultObj.modifyPdWealth.beneficialTypes!=""){
							var a=resultObj.modifyPdWealth.beneficialTypes.substr(1).split(",");
							$('#modify_beneficialTypes').combobox('setValues', a);
						}
						if(resultObj.modifyPdWealth.expectOpenDayRules!=null&&resultObj.modifyPdWealth.expectOpenDayRules!=undefined&&resultObj.modifyPdWealth.expectOpenDayRules!=""){
							var a=resultObj.modifyPdWealth.expectOpenDayRules.substr(1).split(",");
							$('#expectOpenDayRulesDetail').combobox('setValues', a);
						}
						}
						//01-股权,02-固定收益类，03-浮动收益，04-海外
						if(modifyProductSubTypeCode=="02"){
							//$("#wealthProductInfo").show();// 财富类产品信息
							$('#wealthProductInfo').parent(".panel").css("display","");
							//$("#fdiffrentInfo").hide();// 分类信息(浮动/股权类)
							$('#fdiffrentInfo').parent(".panel").css("display","none");
							//$("#gdiffrentInfo").show();// 分类信息(固定类)
							$('#gdiffrentInfo').parent(".panel").css("display","");
							//$("#wealthFeeInfo").show();// 财富类费用比例信息
							$('#wealthFeeInfo').parent(".panel").css("display","");
							//$("#insuranceInfo").hide();// 保险产品信息
							$('#insuranceInfo').parent(".panel").css("display","none");
							//$("#insuranceInfo").panel("close");
							//$("#insuraceFeeInfo").hide();// 保险产品费用比例信息
							$('#insuraceFeeInfo').parent(".panel").css("display","none");
							$("#wealthIncomeDisInfo").parent(".panel").css("display","");
							$("#wealthStockDisInfo").parent(".panel").css("display","none");
							$("#modify_promptTransferWorkDays").show();//显示划款工作日
							$("#modify_transferWorkDays").show();//显示划款工作日
							$("#modify_transferWorkDaysTD").show();
							$("#prompthistoryEarnrate").css("display","none");//隐藏历史收益率
							$("#modifyPdCoreInfo_historyEarnrate").css("display","none");//隐藏历史历史收益率
							$("#prompthistoryEarnratePeriod").css("display","none");//隐藏历史收益率
							$("#modifyPdCoreInfo_historyEarnratePeriod").css("display","none");//隐藏历史历史收益率
							$("#closeDperiods_redemptionFee").hide();
						
							if (resultObj.modifyPdWealthChargeRate!=null&&resultObj.modifyPdWealthChargeRate!=undefined&&resultObj.modifyPdWealthChargeRate!="") {
								//固定收益分类信息
								setInputValueById("gdiffrentInfoForm",resultObj.modifyPdWealthChargeRate);
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
						}
						else{
							if(modifyProductSubTypeCode=="03"||modifyProductSubTypeCode=="04"){
								$("#netvaluedisclosureDetail").show();//浮动收益类显示净值披露频率、产品开放日规则
								$("#wealthStockDisInfo").parent(".panel").css("display","none");
							}
							if(modifyProductSubTypeCode=="01"){
								$("#wealthStockDisInfo").parent(".panel").css("display","");
								if (resultObj.modifyPdwealthStockDisMapList!=null&&resultObj.modifyPdwealthStockDisMapList!=undefined&&resultObj.modifyPdwealthStockDisMapList!="") {
									// 财富股权类收益类收益分配信息
									loadJsonObjData("wealthStockDisInfoTable",resultObj.modifyPdwealthStockDisMapList);
									}
									wealthStockDisInfoTableRowIndex = null;
							}
							//赎回费初始化
							var closeDperiodType = $("#modify_closeDperiodType").combobox("getValue");
							if(closeDperiodType=='2'){
								$("#redemptionFee1").hide();
								$("#modify_redemptionFee").hide();
					
							}else{
								$("#redemptionFee1").show();
								$("#modify_redemptionFee").show();
							}
							
							//$("#wealthProductInfo").show();// 财富类产品信息
							$("#wealthProductInfo").parent(".panel").css("display","");
							//$("#fdiffrentInfo").show();// 分类信息(浮动/股权类)
							$("#fdiffrentInfo").parent(".panel").css("display","");
							//$("#gdiffrentInfo").hide();// 分类信息(固定类)
							$("#gdiffrentInfo").parent(".panel").css("display","none");
							//$("#wealthFeeInfo").hide();// 财富类费用比例信息
							$("#wealthFeeInfo").parent(".panel").css("display","none");
							//$("#insuranceInfo").hide();// 保险产品信息
							$("#insuranceInfo").parent(".panel").css("display","none");
							//$("#insuranceInfo").panel("close");
							//$("#insuraceFeeInfo").hide();// 保险产品费用比例信息
							$("#insuraceFeeInfo").parent(".panel").css("display","none");
							//$("#wealthIncomeDisInfo").css("display","none");
							$("#wealthIncomeDisInfo").parent(".panel").css("display","none");
							$("#modify_promptTransferWorkDays").hide();//隐藏划款工作日
							$("#modify_transferWorkDays").hide();//隐藏划款工作日
							$("#modify_transferWorkDaysTD").hide();
							$(".promptIncomeWay").remove();//隐藏收益方式
							$('#modifyPdCoreInfo_incomeWay').combobox('destroy');//隐藏收益方式
							
							if (resultObj.modifyPdWealthChargeRate!=null&&resultObj.modifyPdWealthChargeRate!=undefined&&resultObj.modifyPdWealthChargeRate!="") {
							//股权浮动分类信息
							setInputValueById("fdiffrentInfoForm",resultObj.modifyPdWealthChargeRate);
							//console.info(resultObj.modifyPdWealthChargeRate2);
							if(resultObj.modifyPdWealthChargeRate2!=null && resultObj.modifyPdWealthChargeRate2!=" "){
							$("#modify_fixManagementFeeRatio").val(resultObj.modifyPdWealthChargeRate2.fixManagementFeeRatio);
							$("#modify_floatManagementFeeRatio").val(resultObj.modifyPdWealthChargeRate2.floatManagementFeeRatio);
							$("#modify_taxFee1").val(resultObj.modifyPdWealthChargeRate2.taxFee);}
							}
						}
					}
					else{
						if (resultObj.modifyPdRisk!=null&&resultObj.modifyPdRisk!=undefined&&resultObj.modifyPdRisk!="") {
						if(modifyProductSubTypeCode=="02"){
							setInputValueById("riskInfo2",resultObj.modifyPdRisk);
						}
						else{
							setInputValueById("riskInfo",resultObj.modifyPdRisk);
						}	
						}
						//$("#wealthProductInfo").hide();// 财富类产品信息
						$("#wealthProductInfo").parent(".panel").css("display","none");
						//$("#fdiffrentInfo").hide();// 分类信息(浮动/股权类)
						$("#fdiffrentInfo").parent(".panel").css("display","none");
						//$("#gdiffrentInfo").hide();// 分类信息(固定类)
						$("#gdiffrentInfo").parent(".panel").css("display","none");
						//$("#wealthFeeInfo").hide();// 财富类费用比例信息
						$("#wealthFeeInfo").parent(".panel").css("display","none");
						//$("#wealthIncomeDisInfo").css("display","none");
						$("#wealthIncomeDisInfo").parent(".panel").css("display","none");
						
						/*$("#wealthProductInfo").panel("close");// 财富类产品信息
						$("#fdiffrentInfo").panel("close");// 分类信息(浮动/股权类)
						$("#gdiffrentInfo").panel("close");// 分类信息(固定类)
						$("#wealthFeeInfo").panel("close");// 财富类费用比例信息
						$("#wealthIncomeDisInfo").panel("close");*/
						
						
						if (resultObj.modifyPdRiskFeeRateList!=null&&resultObj.modifyPdRiskFeeRateList!=undefined&&resultObj.modifyPdRiskFeeRateList!="") {
							//保险费用比例
							loadJsonObjData("insuraceRateTable",resultObj.modifyPdRiskFeeRateList);	
						}
						//保险类费用比例全局变量
						var insuraceRateTableRowIndex = null;
					}
					 //录入信息
					if(resultObj.modifyPdFactorRateList!=null&&resultObj.modifyPdFactorRateList!=undefined&&resultObj.modifyPdFactorRateList!=""){
						loadJsonObjData("factorInfoTable",resultObj.modifyPdFactorRateList);
						factorInfoTableRowIndex=null;
					}
					//最后修改的产品ID给ProductId 允许不修改产品信息，就直接对附件信息进行更改
					  productId=modifyProductId;
					  
				}else{
					$.messager.alert('提示', result.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {

	// 合作机构
	$("#modify_agencyCode").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	// 销售状态初始化
	$("#modify_salesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName',
		editable:false
		
	});
	
	// 财富产品风险等级
	$("#modify_grade").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=level',
		valueField : 'code',
		textField : 'codeName',
		editable:false

	});
	// 封闭期间数值单位
	closeDperiodUnitCombobox=	$("#modify_closeDperiodUnit").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=cloPeriUnit',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	
	
	// 保障对象
	$("#modify_insurObj").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=insurObj',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	// 最小投保年龄单位
	$("#modify_minAppAgeUnit").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=insuredDateUnit',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});

	// 最大投保年龄单位
	$("#modify_maxAppAgeUnit").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=insuredDateUnit',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});

	
	// 受益权类型
	beneficialTypesCombobox=  $("#modify_beneficialTypes").combobox({
			url : contextPath + '/codeQuery/tdCodeQuery?codeType=beneficialType',
			valueField : 'code',
			textField : 'codeName',
			multiple : true
		});
	
	
	//封闭期类型
	$("#modify_closeDperiodType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=closeDperiodType',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		onSelect:function(){
			var closeDperiodType = $("#modify_closeDperiodType").combobox("getValue");
			if(closeDperiodType=='2'){
				$("#redemptionFee1").hide();
				$("#modify_redemptionFee").hide();
			}else{
				$("#redemptionFee1").show();
				$("#modify_redemptionFee").show();
			}
		}
	});

	// 产品特征
	var riskFeaturesCombobox= $('#modify_riskFeatures').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=riskFeatures',
		required:true,
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		onSelect:function(){
			var riskFeaturesValue = riskFeaturesCombobox.combobox("getValue");
			//保险产品
			if(modifyProductTypeCode=='2'){
				//个人寿险 非传统型
				if(modifyProductSubTypeCode=='01'&&riskFeaturesValue=='02'){
					$('#modify_basicPrem').numberbox({required:true});
				}
				else{
					$('#modify_basicPrem').numberbox({required:false});
				}
				
			}
			
		}
	});
	
	$("#modify_isInviteCode").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isInviteCode',
		valueField : 'code',
		textField : 'codeName'
	});
	
	
	if(modifyProductTypeCode=='1'){
		//股权
		if(modifyProductSubTypeCode=='01'){
			// 产品类别
			var wealthCategoryCombobox;
			wealthCategoryCombobox = $("#modify_wealthCategory").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=gqWealthCategory',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			
		}
		//固定
		if(modifyProductSubTypeCode=='02'){
			// 产品类别
			var wealthCategoryCombobox;
			wealthCategoryCombobox = $("#modify_wealthCategory").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=gdWealthCategory',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			
		}
		//浮动
		if(modifyProductSubTypeCode=='03'||modifyProductSubTypeCode=="04"){
			// 产品类别
			var wealthCategoryCombobox;
			wealthCategoryCombobox = $("#modify_wealthCategory").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=fdWealthCategory',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			//产品净值披露频率
			$("#netValueDisclosureinputDetail").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueDisclosure',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			//产品开放日规则
			expectOpenDayRulesCombobox = $("#expectOpenDayRulesDetail").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=productOpenDayRules',
				valueField : 'code',
				textField : 'codeName',
				editable:false,
				multiple : true
			});
			
		}
		
	}	
 	else {

		if (modifyProductSubTypeCode == '02') {
			$("#riskInfo2").show();// 保险产品费用比例信息
			$("#riskInfo").hide();
			// 主附险种标志
			$("#modify_prFlag2").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=prFlag',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			
		}
		else{
			$("#riskInfo").show();// 保险产品费用比例信息
			$("#riskInfo2").hide();	
			// 主附险种标志
			$("#modify_prFlag").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=prFlag',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
		}
	}
	//财富产品
	if (modifyProductTypeCode=='1') {
		if (modifyProductSubTypeCode=='02') {
			// 收益方式
			$("#modifyPdCoreInfo_incomeWay").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=incomeWay',
				valueField : 'code',
				textField : 'codeName',
				required:true,
				editable:false
			});
			//$('#modifyPdCoreInfo_historyEarnrate').validatebox({required:true});	
		} else {
			// 收益方式
			$("#modifyPdCoreInfo_incomeWay").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=incomeWay',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			//$('#modifyPdCoreInfo_historyEarnrate').validatebox({required:false});
		}
	}
	

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
								},{
									field : 'expectedFeeRateState',
									title : '超额收益或浮动收益',
									width : 100,
									sortable : true,
									editor: {
										type:'text'
									},
									formatter : function(value, row, index) {
										return row.expectedFeeRateState;
									}
								}] ],
						onLoadSuccess : function() {
							$('#wealthRateTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						},
					 onClickRow:function(index){
					 wealthRateTableEditOneRow(index);
					 },
					 toolbar:"#wealthRateTable_tb"
					});
}
/** ******************************财富类费用比例编辑表格的增/删/编辑************************************ */
// 增加一行
function wealthRateTableAddOneRow() {
	wealthRateTableRowIndex = addOneRow(wealthRateTable,
			wealthRateTableRowIndex);
}
// 删除一行
function wealthRateTableRemoveOneRow() {
	removeOneRow(wealthRateTable, wealthRateTableRowIndex);
	wealthRateTableRowIndex = null;
}
// 编辑指定行
function wealthRateTableEditOneRow(index) {
	editOneRow(wealthRateTable, wealthRateTableRowIndex, index);
	wealthRateTableRowIndex = index;
}
// 锁定编辑行
function wealthRateTableLockOneRow() {
	if(lockOneRow(wealthRateTable, wealthRateTableRowIndex)){
		wealthRateTableRowIndex = undefined;
		return true;
	}
	else{
		return false;
	}
	
}




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
		},
		onClickRow : function(index) {
			wealthIncomeDisInfoTableEditOneRow(index);
		},
		toolbar:"#wealthIncomeDisInfoTable_tb"
	});
}

/** ******************************录入信息编辑表格的增/删/编辑************************************ */
//增加一行
function wealthIncomeDisInfoTableAddOneRow() {
	wealthIncomeDisInfoTableRowIndex = addOneRow(wealthIncomeDisInfoTable,wealthIncomeDisInfoTableRowIndex);
}
//删除一行
function wealthIncomeDisInfoTableRemoveOneRow() {
	removeOneRow(wealthIncomeDisInfoTable, wealthIncomeDisInfoTableRowIndex);
	wealthIncomeDisInfoTableRowIndex = null;
}
//编辑指定行
function wealthIncomeDisInfoTableEditOneRow(index) {
	
	if(editOneRow(wealthIncomeDisInfoTable, wealthIncomeDisInfoTableRowIndex, index))
	{
		wealthIncomeDisInfoTableRowIndex = index;
	}
}

//锁定编辑行
function wealthIncomeDisInfoTableLockOneRow() {
	if(lockOneRow(wealthIncomeDisInfoTable, wealthIncomeDisInfoTableRowIndex)){
		wealthIncomeDisInfoTableRowIndex = undefined;
		return true;
	}
	else{
		return false;
	}

}
/******************************************************/







/**
 * (保险类)费用比例信息
 * 
 */
var insuraceRateTable;
function initInsuraceRateTable() {
	insuraceRateTable = $("#insuraceRateTable")
			.datagrid(
					{
						method : 'post',
						singleSelect : false, // 多选
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
									field : 'riskFeeRateId',
									title : '费用比例流水号',
									hidden : true,
									formatter : function(value, row, index) {
										return row.riskFeeRateId;
									}
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
											// 查询保险类费用类型
											url : contextPath+ '/codeQuery/tdCodeQuery?codeType=insuredFeeType',
											onSelect : function() {
												var edFeeType = insuraceRateTable.datagrid(
																'getEditor',
																{
																 index : insuraceRateTableRowIndex,
																 field : 'feeType'
																});
												var edFeeTypeName = $(edFeeType.target).combobox('getText');
												insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['feeTypeName'] = edFeeTypeName;
											}
										}
									},
									formatter : function(value, row, index) {
										return row.feeTypeName;
									}
								},
								{
									field : 'feeTypeName',
									title : '费用类型名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.feeTypeName;
									}
								},
								{
									field : 'paramType',
									title : '保单年度',
									width : 100,
									editor : 'text',
									formatter : function(value, row, index) {
										return row.paramType;
									}
								},
								{
									field : 'paramValue',
									title : '交费期间',
									width : 100,
									sortable : true,
									editor : 'text',
									formatter : function(value, row, index) {
										return row.paramValue;
									}
								},
								{
									field : 'paramUnit',
									title : '交费期间(单位)',
									width : 100,
									sortable : true,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'code',
											textField : 'codeName',
											url : contextPath+ '/codeQuery/tdCodeQuery?codeType=payPeriodUnit',
											onSelect : function() {
												var edParamUnit = insuraceRateTable.datagrid('getEditor',
																{
															      index : insuraceRateTableRowIndex,
																  field : 'paramUnit'
																});
												var edParamUnitName = $(edParamUnit.target).combobox('getText');
												insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['paramUnitName'] = edParamUnitName;
											}
										}
									},
									formatter : function(value, row, index) {
										return row.paramUnitName;
									}
								},
								{
									field : 'paramUnitName',
									title : '交费期间(单位)名称',
									width : 100,
									hidden:true,
									formatter : function(value, row, index) {
										return row.paramUnitName;
									}
								},
								{
									field : 'premLower',
									title : '保费下限(元)-开区间',
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
										return row.premLower;
									}
								},
								{
									field : 'premUpper',
									title : '保费上限(元)-闭区间',
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
										return row.premUpper;
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
								},
								{
									field : 'execStartDate',
									title : '执行开始日期',
									width : 100,
									sortable : true,
								 	editor: {
										type:'datebox',
										options:{
											required:true,
											validType: 'validDate'
										}
									},
									formatter : function(value, row, index) {
										return row.execStartDate;
									}
								},
								{
									field : 'execEndDate',
									title : '执行结束日期',
									width : 100,
									sortable : true,
									editor: {
										type:'datebox',
										options:{
											validType: 'validDate'
										}
									},
									formatter : function(value, row, index) {
										return row.execEndDate;
									}
								},
								{
									field : 'execState',
									title : '执行状态',
									width : 100,
									sortable : true,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'code',
											textField : 'codeName',
											required:true,
											editable:false,
											url : contextPath+ '/codeQuery/tdCodeQuery?codeType=execState',
											onSelect : function() {
												var edExecState = insuraceRateTable.datagrid(
																'getEditor',
																{index : insuraceRateTableRowIndex,
																 field : 'execState'
																});
												var edExecStateName = $(edExecState.target).combobox('getText');
												insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['execStateName'] = edExecStateName;
											}
										}
									},
									formatter : function(value, row, index) {
										return row.execStateName;
									}
								}, {
									field : 'execStateName',
									title : '执行状态名称',
									hidden : true,
									formatter : function(value, row, index) {
										return row.execStateName;
									}
								} ] ],
						onLoadSuccess : function() {
							$('#insuraceRateTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						},
						onClickRow : function(index) {
							insuraceRateTableEditOneRow(index);
						},
						toolbar:"#insuraceRateTable_tb"
					});
}
/** ******************************保险类费用比例编辑表格的增/删/编辑************************************ */
// 费用增加一行
function insuraceRateTableAddOneRow() {
	insuraceRateTableRowIndex = addOneRow(insuraceRateTable,
			insuraceRateTableRowIndex);
}

// 删除一行
function insuraceRateTableRemoveOneRow() {
	removeOneRow(insuraceRateTable, insuraceRateTableRowIndex);
	insuraceRateTableRowIndex = null;
}
// 编辑指定行
function insuraceRateTableEditOneRow(index) {
	editOneRow(insuraceRateTable, insuraceRateTableRowIndex, index);
	insuraceRateTableRowIndex = index;
}
//锁定
function insuraceRateTableLockOneRow() {
	if(lockOneRow(insuraceRateTable, insuraceRateTableRowIndex)){
		insuraceRateTableRowIndex = undefined;
		return true;
	}
	else{
		return false;
	}

}
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
						},
						onClickRow : function(index) {
							factorInfoTableEditOneRow(index);
						},
						toolbar:"#factorInfoTable_tb"
					});
}

/** ******************************录入信息编辑表格的增/删/编辑************************************ */
// 增加一行
function factorInfoTableAddOneRow() {
	factorInfoTableRowIndex = addOneRow(factorInfoTable,
			factorInfoTableRowIndex);
}
// 删除一行
function factorInfoTableRemoveOneRow() {
	removeOneRow(factorInfoTable, factorInfoTableRowIndex);
	factorInfoTableRowIndex = null;
}
// 编辑指定行
function factorInfoTableEditOneRow(index) {
	editOneRow(factorInfoTable, factorInfoTableRowIndex, index);
	factorInfoTableRowIndex = index;
}

//锁定编辑行
function factorInfoTableLockOneRow() {
	if(lockOneRow(factorInfoTable, factorInfoTableRowIndex)){
		factorInfoTableRowIndex = undefined;
		return true;
	}
	else{
		return false;
	}

}


/**
 * 附件信息列表
 */
var defFileInfoTable;
function initdefFileInfoTable() {
	defFileInfoTable = $("#defFileInfoTable").datagrid({

		title : '附件信息列表', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath + "/product/listQueryFileUrl", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			width : 2
		}, {
			field : 'businessType',
			title : '附件类型',
			width : 130,
			sortable : true,
			formatter : function(value, row, index) {
				return row.businessType;
			}
		}, {
			field : 'fileSavePath',
			title : '附件路径',
			width : 130,
			sortable : true,
			formatter : function(value, row, index) {
				return row.fileSavePath;
			}
		}, {
			field : 'fileDescribe',
			title : '附件描述',
			width : 130,
			sortable : true,
			formatter : function(value, row, index) {
				return row.fileDescribe;
			}
		} ] ],
		onLoadSuccess : function() {
			$('#defFileInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onCheck : function() {
			$('#defFileInfoTable').datagrid('getSelections');
		}
	});
}

//点击[提交]产品信息
function submitUpdateProductInfo() {
	//产品编码转换成大写
	if(!checkData()){
		return;
	}
	var param = {};
	//拿到中间变量
	param.modifyProductId= modifyProductId;
	param.modifyProductType=modifyProductTypeCode;
    param.modifyProductSubType=modifyProductSubTypeCode;
    param.modifyProductStatus=modifyProductStatus;
    
	//校验日期
	if(modifyProductTypeCode=='2'){
		if(!checkExecDate(insuraceRateTable,"保险费用比例信息"))
		{
			return;
		}
	}
    
	
	//处理受益权类型存储后台格式为,001,002
	var beneficialTypesValues=beneficialTypesCombobox.combobox("getValues");
	var beneficialTypeValue="";
	for(var i=0;i<beneficialTypesValues.length;i++ ){
	var value=beneficialTypesValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		beneficialTypeValue =beneficialTypeValue+","+value;
		}
	}
	var beneficialTypesText= $("#modify_beneficialTypes").combobox('getText');
	$("#modify_beneficialTypes").combobox('setValue',beneficialTypeValue);
	$("#modify_beneficialTypes").combobox('setText',beneficialTypesText);
	
	//处理开放日规则存储后台格式为,01,02
	var expectOpenDayRulesValues=expectOpenDayRulesCombobox.combobox("getValues");
	var expectOpenDayRulesValue="";
	for(var i=0;i<expectOpenDayRulesValues.length;i++ ){
	var value=expectOpenDayRulesValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		expectOpenDayRulesValue =expectOpenDayRulesValue+","+value;
		}
	}
	var expectOpenDayRulesText= $("#expectOpenDayRulesDetail").combobox('getText');
	$("#expectOpenDayRulesDetail").combobox('setValue',expectOpenDayRulesValue);
	$("#expectOpenDayRulesDetail").combobox('setText',expectOpenDayRulesText);
	
	var basicInfoJson = formDataToJsonStr($("#basicInfoForm").serialize());
	param.productBaseInfo = basicInfoJson;
	if (productTypeValue == 2) {
		//获取保险产品基本信息
		var insuranceInfoJson = formDataToJsonStr($("#insuranceInfoForm").serialize());
		param.insuranceInfo = insuranceInfoJson;
		//获取保险产品的费用比例信息的表格信息
		var insuraceRateTableData = $("#insuraceRateTable").datagrid("getRows");
		param.insuraceRateTableInfo = $.toJSON(insuraceRateTableData);
	} else {
		//获取财富产品的基本信息
		var wealthProductInfoJson = formDataToJsonStr($("#wealthProductInfoForm").serialize());
		param.wealthProductInfo = wealthProductInfoJson;
		//02-固定收益类 /01股权类的产品/03-产品子类为浮动/
		if (productSubTypeValue == 02) {
			//固定分类信息
			var gdiffrentInfoJson = formDataToJsonStr($("#gdiffrentInfoForm").serialize());
			param.gdiffrentInfo = gdiffrentInfoJson;
			//固定费用比例
			var wealthRateTableData = $("#wealthRateTable").datagrid("getRows");
			param.wealthRateTableInfo = $.toJSON(wealthRateTableData);
			//固定收益类收益分配信息
			var wealthIncomeDisInfoTableData = $("#wealthIncomeDisInfoTable").datagrid("getRows");
			param.wealthIncomeDisInfoData = $.toJSON(wealthIncomeDisInfoTableData);
		} else {
		if (productSubTypeValue == 01) {
				//股权类收益类收益分配信息
				var wealthStockDisInfoTableData = $("#wealthStockDisInfoTable").datagrid("getRows");
				param.wealthStockDisInfoData = $.toJSON(wealthStockDisInfoTableData);
			}
			//浮动/股权分类信息
			var fdiffrentInfoJson = formDataToJsonStr($("#fdiffrentInfoForm").serialize());
			param.fdiffrentInfo = fdiffrentInfoJson;
		}

	}
	var factorInfoTableData = $("#factorInfoTable").datagrid("getRows");
	param.factorInfoTableInfo = $.toJSON(factorInfoTableData);
	$.ajax({
		type : 'post',
		url : contextPath + "/product/submitUpdateProductCoreInfoUrl",
//		data : 'param=' +encodeURI($.toJSON(param)),
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					productId=resultInfo.obj;
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


//比较日期
function checkExecDate(BasicLawTableParam,message){
	var rows = BasicLawTableParam.datagrid('getRows');
	for ( var row = 0; row < rows.length; row++) {
		
		if(!compareDate(rows[row].execStartDate,rows[row].execEndDate))
		{
			$.messager.alert('提示', message+",第"+(row+1)+"行的执行开始日期不能大于执行结束日期", 'info');
			return false;
		}
	}
	return true;
}


function addFileInfo() {
	if (productId != null) {
		var param = {};
		param.businessNo = productId;
		param.businessType = "01";
		param.flag = "queryFile";
		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));

	} else {
		alert("请先添加产品信息，再进行附件上传！");
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
function backListProductPage(){
	$('#addWindow').window('destroy');
	parent.clearFormInfo();
}

function checkData(){
	if (!factorInfoTableLockOneRow()) {
		alert("请输入录入信息必录项！");
		return false;
	}
	if (productTypeValue == '1') {
		if (!$("#wealthProductInfoForm").form("validate")) {
			alert("请输入财富基本信息必录项！");
			return false;
		}
		if (productSubTypeValue == '02') {
			if (!$("#gdiffrentInfoForm").form("validate")) {
				alert("请输入固定收益类分类信息必录项！");
				return false;
			}
			if (!wealthRateTableLockOneRow()) {
				alert("请输入财富费用比例信息必录项！");
				return false;
			}
		} else {
			if (productSubTypeValue == '01') {
				if (!wealthStockDisInfoTableLockOneRow()) {
					$.messager.alert('提示',"请输入股权收益类分配信息必录项！");
					return false;
				}
			}
			if (!$("#fdiffrentInfoForm").form("validate")) {
				alert("请输入股权浮动分类信息必录项！");
				return false;
			}
		}

	} else {
		// 2-保险产品 基本信息
		if(productSubTypeValue == '01'||productSubTypeValue == '03'){
			if (!$("#riskInfo").form("validate")) {
				alert("请输入保险产品-个人寿险/银行保险基本信息必录项！");
				return false;
			}
			
		}
        if(productTypeValue == '02'){
			
			if (!$("#riskInfo2").form("validate")) {
				alert("请输入保险产品-车险基本信息必录项！");
				return false;
			}
			
		}
		
		// 保险费用比例信息
		if (!insuraceRateTableLockOneRow()) {
			alert("请输入保险费用比例必录项！");
			return false;
		}
	}
	
	var raiseStartDate = $("#modify_raiseStartDate").datebox("getValue");
	var raiseEndDate = $("#modify_raiseEndDate").datebox("getValue");
	if(raiseStartDate!=null && raiseStartDate!="" && raiseStartDate!=undefined &&
			raiseEndDate!=null && raiseEndDate!="" && raiseEndDate!=undefined)
	{
		var d1 = new Date(raiseStartDate.replace(/\-/g, "\/")); 
		var d2 = new Date(raiseEndDate.replace(/\-/g, "\/")); 

		if(d1 >=d2)
		{
		  $.messager.alert('提示', "募集开始日期不能大于等于募集结束日期！", 'info');
		  return false; 
		}
	}
	
     return true;
}

var wealthStockDisInfoTable;
function initWealthStockDisInfoTable(){
	wealthStockDisInfoTable = $("#wealthStockDisInfoTable").datagrid({
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
		},
		onClickRow : function(index) {
			wealthStockDisInfoTableEditOneRow(index);
		},
		toolbar:"#wealthStockDisInfoTable_tb"
	});
}

/** ******************************录入信息编辑表格的增/删/编辑************************************ */
//增加一行
function wealthStockDisInfoTableAddOneRow() {
	wealthStockDisInfoTableRowIndex = addOneRow(wealthStockDisInfoTable,wealthStockDisInfoTableRowIndex);
}
//删除一行
function wealthStockDisInfoTableRemoveOneRow() {
	removeOneRow(wealthStockDisInfoTable, wealthStockDisInfoTableRowIndex);
	wealthStockDisInfoTableRowIndex = null;
}
//编辑指定行
function wealthStockDisInfoTableEditOneRow(index) {
	
	if(editOneRow(wealthStockDisInfoTable, wealthStockDisInfoTableRowIndex, index))
	{
		wealthStockDisInfoTableRowIndex = index;
	}
}

//锁定编辑行
function wealthStockDisInfoTableLockOneRow() {
	if(lockOneRow(wealthStockDisInfoTable, wealthStockDisInfoTableRowIndex)){
		wealthStockDisInfoTableRowIndex = undefined;
		return true;
	}
	else{
		return false;
	}

}



