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
var productType = null;
// 定义产品子类全局变量
var productSubType = null;
// 上传时需要关联的产品Id全局变量
var productId = null;
//是否重复提交的标志
var sucFlag=null;

var beneficialTypesCombobox;
var expectOpenDayRulesCombobox;
var closeDperiodUnitCombobox;

/**
 * 页面初始化加载
 */
jQuery(function($) {
	productType=$("#addProductCoreInfo_ProductType").val();
	productSubType=$("#addProductCoreInfo_ProductSubType").val();
	productId=$("#addProductCoreInfo_ProductId").val();
	// 加载所有的下拉框
	initAllCombobox();
	//初始文本必录入项
	initAllValidateBox();
	// (财富类)费用比例信息
	initWealthRateTable();
	// (保险类)费用比例信息
	initInsuraceRateTable();
	// 录入信息初始化
	initfactorInfoTable();
	//初始化收益分配信息表格
	initWealthIncomeDisInfoTable();
	//初始化浮动类收益分配信息表格
	initWealthStockDisInfoTable();
	//增加一行收益分配信息
	wealthIncomeDisInfoTableAddOneRow();
	//增加一行浮动类收益分配信息
	wealthStockDisInfoTableAddOneRow();
	// 增加一行财富类费用比例信息表格
	wealthRateTableAddOneRow();
	// 增加一行保险类费用比例信息表格
	insuraceRateTableAddOneRow();
	// 增加一行录入信息表格
//	factorInfoTableAddOneRow();
	//showAndHidePanel();
});

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	// 产品类别
	var wealthCategoryCombobox;
	wealthCategoryCombobox = $("#addProductCoreDef_wealthCategory").combobox({
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	
	  

	// 合作机构
	$("#agencyCode1").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	// 销售状态初始化
	$("#salesStatus1").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		value : '0'
	});

	if(productSubType=='02'){
		// 主附险种标志
		$("#prFlag_2").combobox({
			url : contextPath + '/codeQuery/tdCodeQuery?codeType=prFlag',
			valueField : 'code',
			textField : 'codeName',
			editable:false
		});	
	}
	else{
		// 主附险种标志
		$("#prFlag").combobox({
			url : contextPath + '/codeQuery/tdCodeQuery?codeType=prFlag',
			valueField : 'code',
			textField : 'codeName',
			editable:false
		});	
	}
	
	
	// 财富产品风险等级
	$("#grade").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=level',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		required:true/*,
		value : "01"*/

	});
	
	// 受益权类型
	beneficialTypesCombobox=$("#beneficialTypes").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=beneficialType',
		valueField : 'code',
		textField : 'codeName',
		multiple : true
	});


	// 封闭期间数值单位
  closeDperiodUnitCombobox=$("#closeDperiodUnit").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=cloPeriUnit',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	// 最小投保年龄单位
	$("#minAppAgeUnit").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=insuredDateUnit',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});

	// 最大投保年龄单位
	$("#maxAppAgeUnit").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=insuredDateUnit',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	
	//封闭期类型
	$("#closeDperiodType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=closeDperiodType',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		onSelect:function(){
			var closeDperiodType = $("#closeDperiodType").combobox("getValue");
			if(closeDperiodType=='2'){
				$("#redemptionFee1").hide();
				$("#redemptionFee").hide();
				$("#redemptionFee").val("");
			}else{
				$("#redemptionFee1").show();
				$("#redemptionFee").show();
			}
		}
	});
	
	

	// 产品特征
	var riskFeaturesCombobox=$("#riskFeatures").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=riskFeatures',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		editable:false,
		onSelect:function(){
   var riskFeaturesValue = riskFeaturesCombobox.combobox("getValue");
			if(productType=='2'){
				//个人寿险-且是非传统险 基本保费为比录项
				if(productSubType=='01'&&riskFeaturesValue=='02'){
					$('#basicPrem').numberbox({required:true});	
				}
				else{
					$('#basicPrem').numberbox({required:false});
				}
			}
		}
	});
	//财富产品
	if (productType=='1') {
		if (productSubType=='02') {
			// 收益方式
			$("#addPdCoreInfo_incomeWay").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=incomeWay',
				valueField : 'code',
				textField : 'codeName',
				required:true,
				editable:false
			});
			$('#addPdCoreInfo_historyEarnrate').validatebox({required:false});//固定历史收益率非必填
			$('#modify_closeDperiod').validatebox({required:true});//封闭期非必填
			$('#closeDperiodUnit').combobox({required:true});//封闭期单位非必填
			
		} else {
			if(productSubType=='03'||productSubType=='04'){
			//产品净值披露频率
			$("#netValueDisclosureinput").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=netValueDisclosure',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			//产品开放日规则
			expectOpenDayRulesCombobox=$("#expectOpenDay").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=productOpenDayRules',
				valueField : 'code',
				textField : 'codeName',
				multiple : true
			});
		};
			// 收益方式
			$("#addPdCoreInfo_incomeWay").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=incomeWay',
				valueField : 'code',
				textField : 'codeName',
				editable:false
			});
			
			$('#addPdCoreInfo_historyEarnrate').validatebox({required:false});
			$('#DcloseDperiods').validatebox({required:false});//封闭期非必填
			$('#closeDperiodUnit').combobox({required:false});//封闭期单位非必填
		}
	}
	
	if(productType == "1"){
		loadProductFactor("factorInfoTable","wealthfactorCode");//加载财富产品录入参数信息
	}else{
		loadProductFactor("factorInfoTable", "riskfactorCode");// 加载保险产品录入参数信息
	}
 }



/**
 * 
 * 显示或隐藏面板
 * */
function showAndHidePanel(){
	//根据产品类型和产品子类加载产品信息页面
	  if (productType == "1") 
	  {
	  	// 当产品子类为股权和浮动和海外时，动态隐藏固定收益类页面信息部分
		if (productSubType == "01"|| productSubType == "03"||productSubType=='04'){
			$('#wealthProductInfo').parent(".panel").css("display","");//展示财富产品信息
			$('#fdiffrentInfo').parent(".panel").css("display","");//展示浮动股权信息
			$('#gdiffrentInfo').parent(".panel").css("display","none");//隐藏固定收益分配信息
			$('#wealthFeeInfo').parent(".panel").css("display","none");//隐藏固定收益预期收益率表格
			$('#wealthIncomeDisInfo').parent(".panel").css("display","none");//隐藏固定收益分配信息表格
			$("#promptTransferWorkDays").css("display","none");//隐藏收益划款工作日信息
			$("#transferWorkDays").css("display","none");//隐藏收益划款工作日信息
			$("#promptIncomeWay").remove();//隐藏收益方式
			$("#promptIncomeWayTd").remove();//移除收益方式输入框单元格
			$('#addPdCoreInfo_incomeWay').combobox('destroy');//隐藏收益方式
			$('#insuranceInfo').parent(".panel").css("display","none");//隐藏保险产品信息
			$('#insuraceFeeInfo').parent(".panel").css("display","none");//隐藏保险费用比例信息
			// 动态加载产品类别下拉框值
			if (productSubType == "01") {
				var url = contextPath+ '/codeQuery/tdCodeQuery?codeType=gqWealthCategory';
				$("#addProductCoreDef_wealthCategory").combobox("reload", url);
				$(".investDirection").show();//股权类显示投资方向
				$(".investStrategy").remove();
				$(".investScope").remove();
				$('#wealthStockDisInfo').parent(".panel").css("display","");//显示浮动类收益分配信息表格
			} else {
				var url = contextPath+ '/codeQuery/tdCodeQuery?codeType=fdWealthCategory';
				$("#addProductCoreDef_wealthCategory").combobox("reload", url);
				$("#netvaluedisclosure").show();//浮动收益类显示净值披露频率、产品开放日规则
				$(".investStrategy").show();//浮动类显示投资策略
				$(".investDirection").remove();
				$(".investScope").remove();
				$('#wealthStockDisInfo').parent(".panel").css("display","none");//隐藏浮动类收益分配信息表格
			}
		} 
		else {
			$(".investScope").show();//固定类显示投资范围
			$(".investDirection").remove();
			$(".investStrategy").remove();
			// 产品子类为固定类，动态隐藏固定类页面信息部分
			$('#wealthProductInfo').parent(".panel").css("display","");//展示财富产品信息
			$('#fdiffrentInfo').parent(".panel").css("display","none");//隐藏浮动股权信息
			$('#gdiffrentInfo').parent(".panel").css("display","");//显示固定收益分配信息
			$('#wealthFeeInfo').parent(".panel").css("display","");//显示固定收益预期收益率表格
			$('#wealthIncomeDisInfo').parent(".panel").css("display","");//隐藏固定收益分配信息表格
			$('#wealthStockDisInfo').parent(".panel").css("display","none");//隐藏浮动类收益分配信息表格
			$("#promptTransferWorkDays").css("display","");//显示收益划款工作日信息
			$("#transferWorkDays").css("display","");//显示收益划款工作日信息
			$('#insuranceInfo').parent(".panel").css("display","none");//隐藏保险产品信息
			$('#insuraceFeeInfo').parent(".panel").css("display","none");//隐藏保险费用比例信息
			
			$("#prompthistoryEarnrate").css("display","none");//隐藏历史收益率
			$("#addPdCoreInfo_historyEarnrate").css("display","none");//隐藏历史历史收益率
			$("#prompthistoryEarnratePeriod").css("display","none");//隐藏历史收益率
			$("#addPdCoreInfo_historyEarnratePeriod").css("display","none");//隐藏历史历史收益率
			var url = contextPath+ '/codeQuery/tdCodeQuery?codeType=gdWealthCategory';
			$("#addProductCoreDef_wealthCategory").combobox("reload",url);
			
			$("#closeDperiods_redemptionFee").hide();
		}
		
	} else {
		// 产品子类为01-个人寿险 03-银保，动态显示页面信息部分 
		if (productSubType == "01" || productSubType == "03") {
			$('#wealthProductInfo').parent(".panel").css("display","none");//展示财富产品信息
			$('#fdiffrentInfo').parent(".panel").css("display","none");//隐藏浮动股权信息
			$('#gdiffrentInfo').parent(".panel").css("display","none");//显示固定收益分配信息
			$('#wealthFeeInfo').parent(".panel").css("display","none");//显示固定收益预期收益率表格
			$('#wealthIncomeDisInfo').parent(".panel").css("display","none");//隐藏固定收益分配信息表格
			$("#promptTransferWorkDays").css("display","none");//显示收益划款工作日信息
			$("#transferWorkDays").css("display","none");//显示收益划款工作日信息
			$("#riskInfo2").css("display", "none");
			$("#riskInfo").css("display", "");
			$('#insuraceFeeInfo').parent(".panel").css("display","");//隐藏保险费用比例信息
		}
		// 02-车险
		if (productSubType == "02") {
			$('#wealthProductInfo').parent(".panel").css("display","none");//展示财富产品信息
			$('#fdiffrentInfo').parent(".panel").css("display","none");//隐藏浮动股权信息
			$('#gdiffrentInfo').parent(".panel").css("display","none");//显示固定收益分配信息
			$('#wealthFeeInfo').parent(".panel").css("display","none");//显示固定收益预期收益率表格
			$('#wealthIncomeDisInfo').parent(".panel").css("display","none");//隐藏固定收益分配信息表格
			$("#promptTransferWorkDays").css("display","none");//显示收益划款工作日信息
			$("#transferWorkDays").css("display","none");//显示收益划款工作日信息
			$("#riskInfo2").css("display", "");
			$("#riskInfo").css("display", "none");
			$('#insuraceFeeInfo').parent(".panel").css("display","");//隐藏保险费用比例信息
			$("#prFlag_2").combobox({
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=prFlag',
				valueField : 'code',
				textField : 'codeName',
				editable : false
			});
		}
		
	}
}

/**
 * 
 * 初始文本格式的必录项
 */
function initAllValidateBox(){
	$('#financingScale').validatebox({required:true});
//	$('#DcloseDperiods').validatebox({required:true});
	$('#subscriptionFeeRatio').validatebox({required:true});
	
	//所有金额输入框，输入时转换大写
	$("#addPdCoreInfo_startInvestMoney," +
			"#addPdCoreInfo_investLimitMoney," +
			"#addPdCoreInfo_investIncreaseMoney").bind('input propertychange', function(e){  
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

	$("#financingScale").bind('input propertychange', function(e){  
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
}



/**
 * (固定收益类)费用比例信息可编辑表格
 * 
 */
var wealthRateTable;
function initWealthRateTable() {
	wealthRateTable = $("#wealthRateTable").datagrid(
					{
						method : 'post',
						iconCls : 'icon-edit',
						singleSelect : true, 
						fitColumns : true, 
						striped : true, 
						collapsible : true,
						sortName : 'id', 
						sortOrder : 'desc', 
						remoteSort : true,
						idField : 'id',
						queryParams : {}, 
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
									width : 2
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
										url : contextPath+ '/codeQuery/tdCodeQuery?codeType=wealthFeeType',// 查询财富类费用类型
										onSelect : function() {
										   var edFeeType = wealthRateTable.datagrid('getEditor',{index : wealthRateTableRowIndex,field : 'feeType'});
										   var edFeeTypeName = $(edFeeType.target).combobox('getText');
										   wealthRateTable.datagrid('getRows')[wealthRateTableRowIndex]['feeTypeName'] = edFeeTypeName;
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
									required : true ,
									editor: {
										type : 'combobox',
										options : {
										valueField : 'code',
										textField : 'codeName',
										required : true ,
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
											required:true,
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
										required:true,
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
											min:0,
											precision:2,
											required:true
										}
									},
									formatter : function(value, row, index) {
										return row.feeRate;
									}
								}, 
								{
									field : 'expectedFeeRate',
									title : '客户预期收益率(%)',
									width : 100,
									sortable : true,
									editor: {
										type:'validatebox',
										options:{
											validType:['validDecNum'],
											min:0,
											precision:2,
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
											validType:['validDecNum'],
											min:0,
											precision:2
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
							$('#wealthRateTable').datagrid('clearSelections'); 
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
	if(editOneRow(wealthRateTable, wealthRateTableRowIndex, index))
	{
		wealthRateTableRowIndex = index;
	}
	
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

/**
 * (保险类)费用比例信息
 * 
 */
var insuraceRateTable;
function initInsuraceRateTable() {
	insuraceRateTable = $("#insuraceRateTable").datagrid({
           method : 'post',
           singleSelect : false, 
           fitColumns : true, 
           striped : true, 
           collapsible : true,
		   sortName : 'id',
		   sortOrder : 'desc',
           remoteSort : true, 
           idField : 'id', 
           queryParams : {},
		   columns : [ [
			           {
                         field : 'ck',
	                     checkbox : true,
                         width : 2
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
								url : contextPath+ '/codeQuery/tdCodeQuery?codeType=insuredFeeType',	// 查询保险类费用类型
								onSelect : function() {
								           var edFeeType = insuraceRateTable.datagrid('getEditor',{index : insuraceRateTableRowIndex,field : 'feeType'});
								           var edFeeTypeName = $(edFeeType.target).combobox('getText');
										   insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['feeTypeName'] = edFeeTypeName;
								                    }
  /*                     ,
								onLoadSuccess:function(){
									var allData = $(this).combobox("getData");
									if(allData!=null&&allData.length>0){
										for(var i=0;i<allData.length;i++){
											if(allData[i].code=="01"){
												$(this).combobox("setValue",allData[i].code);
												insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['feeTypeName'] = allData[i].codeName;
											}
										}
										var introduceDate = $("#introduceDate").datebox("getValue");
										var edExecStartDate = insuraceRateTable.datagrid('getEditor',{index : insuraceRateTableRowIndex,field : 'execStartDate'});
										$("#edExecStartDate.target").datebox('setValue',introduceDate);
									}
									
								}*/
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
											var edParamUnit = insuraceRateTable.datagrid('getEditor',{index : insuraceRateTableRowIndex, field : 'paramUnit'});
												var edParamUnitName = $(edParamUnit.target).combobox('getText');
												insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['paramUnitName'] = edParamUnitName;
											}
/*						,
											onLoadSuccess:function(){
												var allData = $(this).combobox("getData");
												if(allData!=null&&allData.length>0){
													for(var i=0;i<allData.length;i++){
														if(allData[i].code=="Y"){
															$(this).combobox("setValue",allData[i].code);
															insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['paramUnitName'] = allData[i].codeName;
														}
													}
												}
												
											}	*/
											
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
			/*					,
											onLoadSuccess:function(){
												var allData = $(this).combobox("getData");
												if(allData!=null&&allData.length>0){
													for(var i=0;i<allData.length;i++){
														if(allData[i].code=="1"){
															$(this).combobox("setValue",allData[i].code);
															insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['execStateName'] = allData[i].codeName;
														}
													}
												}
												
											}	*/
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
	insuraceRateTableRowIndex = addOneRow(insuraceRateTable,insuraceRateTableRowIndex);
	//setFeeTypeValue();
}

// 删除一行
function insuraceRateTableRemoveOneRow() {
	removeOneRow(insuraceRateTable, insuraceRateTableRowIndex);
	insuraceRateTableRowIndex = null;
}
// 编辑指定行
function insuraceRateTableEditOneRow(index) {
	if(editOneRow(insuraceRateTable, insuraceRateTableRowIndex, index))
	{
		insuraceRateTableRowIndex = index;
	}
}
// 锁定编辑行
function insuraceRateTableLockOneRow() {
	if(lockOneRow(insuraceRateTable, insuraceRateTableRowIndex)){
		insuraceRateTableRowIndex = undefined;
		return true;
	}
	else{
		return false;
	}

}

function setFeeTypeValue(){
	
	var edFeeType = insuraceRateTable.datagrid('getEditor',{index : insuraceRateTableRowIndex,field: 'feeType'});
	var allData = $(edFeeType.target).combobox('getData');
	for(var i=0;i<allData.length;i++){
		if(allData[i].code=="01"){
			$(edFeeType.target).combobox("setValue",allDataLoad[i].code);
			insuraceRateTable.datagrid('getRows')[insuraceRateTableRowIndex]['feeTypeName'] = allData[i].codeName;
		}
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
											required:true,
											valueField : 'code',
											textField : 'name',
											onShowPanel:function(){
												var codeType=null;
												if(productType=='1'){
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
												
												//获得是否必录行的编辑器
												var edchooseFlag = factorInfoTable.datagrid('getEditor',
														{	index : factorInfoTableRowIndex,
															field : 'chooseFlag'
														});
												
												var allData = $(edchooseFlag.target).combobox("getData");
												for(var i=0;i<allData.length;i++){
													if(allData[i].code=="0"){
														$(edchooseFlag.target).combobox("setValue",allData[i].code);
														factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['chooseFlagName'] = allData[i].codeName;
													}
												}
												
												
												
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
											value:0,
											url : contextPath+ '/codeQuery/tdCodeQuery?codeType=isOrNot',
											onSelect : function() {
												var edChooseFlag = factorInfoTable
														.datagrid(
																'getEditor',
																{
																	index : factorInfoTableRowIndex,
																	field : 'chooseFlag'
																});
												var edChooseFlagName = $(
														edChooseFlag.target)
														.combobox('getText');
												factorInfoTable
														.datagrid('getRows')[factorInfoTableRowIndex]['chooseFlagName'] = edChooseFlagName;
											},
											onLoadSuccess:function(){
												var allData = $(this).combobox("getData");
												for(var i=0;i<allData.length;i++){
													if(allData[i].code=="0"){
														$(this).combobox("setValue",allData[i].code);
														factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['chooseFlagName'] = allData[i].codeName;
													}
												}
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
												var edFactorType = factorInfoTable.datagrid('getEditor',{index : factorInfoTableRowIndex,field : 'factorType'});
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
											/*		alert(edFactorName);
													alert(edFactorValue);*/
													factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValueName'] = edFactorName;
					                                factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex]['factorValue'] = edFactorValue;
												}
												//封闭期处理
												if(factorCode!=null&&factorCode=='closedPeriods'){
													var edFactorValue='';
													var closedPeriods=$("#DcloseDperiods").val().split('/');
													var edFactorName=$("#DcloseDperiods").val();
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
											onShowPanel:function(row){
												var factorCode = factorInfoTable.datagrid('getRows')[factorInfoTableRowIndex].factorCode;
												var url = contextPath+ '/codeQuery/tdCodeQuery?codeType='+ factorCode;
												$(this).combobox("reload", url);
											},
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
//									alert(edFactorValue);
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


function ceshi(){
	var param={};
	var factorInfoTableData = $("#factorInfoTable").datagrid("getRows");
	param.factorInfoTableInfo = $.toJSON (factorInfoTableData);
	
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
	
	if(editOneRow(factorInfoTable, factorInfoTableRowIndex, index))
	{
		factorInfoTableRowIndex = index;
	}
}

// 锁定编辑行
function factorInfoTableLockOneRow() {
	if(lockOneRow(factorInfoTable, factorInfoTableRowIndex)){
		factorInfoTableRowIndex = undefined;
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
//点击[提交]产品信息
function submitAddProductCoreInfo() {
	//处理受益权类型存储后台格式为,001,002
	var beneficialTypesValues=beneficialTypesCombobox.combobox("getValues");
	var beneficialTypeValue="";
	for(var i=0;i<beneficialTypesValues.length;i++ ){
	var value=beneficialTypesValues[i];
	if(value!=null&&value!=""&&value!=undefined){
		beneficialTypeValue =beneficialTypeValue+","+value;
		}
	}
	var beneficialTypesText= $("#beneficialTypes").combobox('getText');
	$("#beneficialTypes").combobox('setValue',beneficialTypeValue);
	$("#beneficialTypes").combobox('setText',beneficialTypesText);
	
	if (productSubType == "03" || productSubType == "04") {
		//处理开放日规则存储后台格式为,01,02
		var expectOpenDayRulesValues=expectOpenDayRulesCombobox.combobox("getValues");
		var expectOpenDayRulesValue="";
		for(var i=0;i<expectOpenDayRulesValues.length;i++ ){
		var value=expectOpenDayRulesValues[i];
		if(value!=null&&value!=""&&value!=undefined){
			expectOpenDayRulesValue =expectOpenDayRulesValue+","+value;
			}
		}
		var expectOpenDayRulesText= $("#expectOpenDay").combobox('getText');
		$("#expectOpenDay").combobox('setValue',expectOpenDayRulesValue);
		$("#expectOpenDay").combobox('setText',expectOpenDayRulesText);
	}
	// 校验页面数据
	if(!checkData()){
		return;
	}
	var param = {};
    param.existProductId=productId;
    param.existProductType=productType;
    param.existProductSubType=productSubType;
    param.existSucFlag=sucFlag;
	/*// 产品基本信息Form
	var basicInfoJson = formDataToJsonStr($("#basicInfoForm").serialize());
	param.productBaseInfo = basicInfoJson;*/
    var wealthProductBaseInfo;
	//2-保险产品,1-财富产品
	if (productType == 2) {
		//获取保险产品基本信息
		var insuranceInfoJson = formDataToJsonStr($("#insuranceInfoForm").serialize());
		param.insuranceInfo = insuranceInfoJson;
		//获取保险产品的费用比例信息的表格信息
		var insuraceRateTableData = $("#insuraceRateTable").datagrid("getRows");
		param.insuraceRateTableInfo = $.toJSON(insuraceRateTableData);
		
	} else {
		//HSH 置空费率
		var closeDperiodType = $("#closeDperiodType").combobox("getValue");
		if(closeDperiodType=='2'){
			$("#redemptionFee").val("");		
		}
		
		//获取财富产品的基本信息
		/*var wealthProductInfoJson = formDataToJsonStr($("#wealthProductInfoForm").serialize());
		param.wealthProductInfo = wealthProductInfoJson;*/
		wealthProductBaseInfo = $("#wealthProductInfoForm").serialize();
		//02-固定收益类 01/03-产品子类为浮动/股权类的产品
		if (productSubType == 02) {
			//固定分类信息
			/*var gdiffrentInfoJson = formDataToJsonStr($("#gdiffrentInfoForm").serialize());
			param.gdiffrentInfo = gdiffrentInfoJson;*/
			var gdiffrentInfoForm = $("#gdiffrentInfoForm").serialize();
			wealthProductBaseInfo += "&"+gdiffrentInfoForm;
			//固定费用比例
			var wealthRateTableData = $("#wealthRateTable").datagrid("getRows");
			/*param.wealthRateTableInfo = $.toJSON(wealthRateTableData);*/
			wealthProductBaseInfo += "&wealthRateTableInfo="+escape(encodeURIComponent($.toJSON(wealthRateTableData)));
			//固定收益类收益分配信息
			var wealthIncomeDisInfoTableData = $("#wealthIncomeDisInfoTable").datagrid("getRows");
			/*param.wealthIncomeDisInfoData = $.toJSON(wealthIncomeDisInfoTableData);*/
			wealthProductBaseInfo += "&wealthIncomeDisInfoData="+$.toJSON(wealthIncomeDisInfoTableData);
		} else {
			if (productSubType == 01) {
				//股权类收益类收益分配信息
				var wealthStockDisInfoTableData = $("#wealthStockDisInfoTable").datagrid("getRows");
				//param.wealthStockDisInfoData = $.toJSON(wealthStockDisInfoTableData);
				wealthProductBaseInfo += "&wealthStockDisInfoData="+$.toJSON(wealthStockDisInfoTableData);
			}
			
			//浮动/股权分类信息
			/*var fdiffrentInfoJson = formDataToJsonStr($("#fdiffrentInfoForm").serialize());
			param.fdiffrentInfo = fdiffrentInfoJson;*/
			wealthProductBaseInfo += "&"+$("#fdiffrentInfoForm").serialize();
		}

	}
	//获取录入信息的表格信息
	//param.factorInfoTableInfo = $.toJSON($("#factorInfoTable").datagrid("getRows")); 
	wealthProductBaseInfo += "&factorInfoTableInfo="+escape(encodeURIComponent($.toJSON($("#factorInfoTable").datagrid("getRows"))));;
	//发送请求，后台接受数据进行处理
	$.ajax({
		type : 'post',
		url : contextPath + "/product/submitProductCoreInfoUrl",
		data : wealthProductBaseInfo +"&param="+$.toJSON(param),
//		data : 'param=' +$.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					sucFlag=resultInfo.obj;
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
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


function checkData(){
	// 录入项信息
	if (!factorInfoTableLockOneRow()) {
		$.messager.alert('提示',"请输入录入信息必录项！");
		return false;
	}
	// 1-财富类产品
	if (productType == '1') {
		// 财富基本信息为空
		if (!$("#wealthProductInfoForm").form("validate")) {
			$.messager.alert('提示',"请输入财富基本信息必录项！");
			return false;
		}
		// 02-固定收益类
		if (productSubType == '02') {
			// 固定收益类分类信息
			if (!wealthIncomeDisInfoTableLockOneRow()) {
				$.messager.alert('提示',"请输入固定收益类分配信息必录项！");
				return false;
			}
			// 财富费用比例信息表
			if (!wealthRateTableLockOneRow()) {
				$.messager.alert('提示',"请输入财富费用比例信息必录项！");
				return false;
			}
		} else {
			if (productSubType == '01') {
				if (!wealthStockDisInfoTableLockOneRow()) {
					$.messager.alert('提示',"请输入股权收益类分配信息必录项！");
					return false;
				}
			}
			// 股权浮动分类信息
			if (!$("#fdiffrentInfoForm").form("validate")) {
				$.messager.alert('提示',"请输入股权浮动分类信息必录项！");
				return false;
			}
		}

	} else {
		// 2-保险产品 基本信息
		if(productSubType == '01'||productSubType == '03'){
			if (!$("#riskInfo").form("validate")) {
				$.messager.alert('提示',"请输入保险产品-个人寿险/银行保险基本信息必录项！");
				return false;
			}
			
		}
        if(productType == '02'){
			
			if (!$("#riskInfo2").form("validate")) {
				$.messager.alert('提示',"请输入保险产品-车险基本信息必录项！");
				return false;
			}
		}
		
		// 保险费用比例信息
		if (!insuraceRateTableLockOneRow()) {
			$.messager.alert('提示',"请输入保险费用比例必录项！");
			return false;
		}
	}
	
	var raiseStartDate = $("#raiseStartDate").datebox("getValue");
	var raiseEndDate = $("#raiseEndDate").datebox("getValue");
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


//返回按钮
function backListProductPage(){
	$('#addProdutctInfo').window('destroy');
	parent.clearFormInfo();
	$('#listProductDefTable').datagrid('reload');
}


//比较日期
function checkExecDate(BasicLawTableParam,message){
	var rows = BasicLawTableParam.datagrid('getRows');
	for ( var row = 0; row <rows.length; row++) {
		
		if(!compareDate(rows[row].execStartDate,rows[row].execEndDate))
		{
			$.messager.alert('提示', message+",第"+(row+1)+"行的执行开始日期不能大于执行结束日期", 'info');
			return false;
		}
	}
	return true;
}


function loadProductFactor(dataGridId,factorType){
	$.ajax({
		type : 'post',
		url : contextPath + "/product/queryProductFactor?codeType="+factorType,
		cache : false,
		success : function(resultInfo) {
			try {
				loadJsonObjData(dataGridId,resultInfo);
				showAndHidePanel();
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}

/**
 * 附件上传
 * 
 **/
function addFileInfo() {
	if (productId != null) {
		var param = {};
		param.businessNo = productId;
		param.businessType = "01";
		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));

	} else {
		$.messager.alert('提示',"请先添加产品信息，再进行附件上传！");
	}

}

function addFileWindow(title, href) 
{
	$('<div id="addFileWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		//fit : true, 
		width:800,
		height:500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
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








