jQuery(function($) 
{
	//财富产品薪资参数-奖金比例
	initBasicLawWealthTable();
	//银保奖金比例信息列表
	initBasicLawYBTable();
	//个人寿险奖金比例
	initBasicLawInsTable();
	//考核参数信息
	initBasicLawAssessTable();
	//初始化下拉列表
	initAllBasicLawInfo();
});
function initAllBasicLawInfo()
{	
	
	$("#add_execState").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
		valueField:'code',
		textField:'codeName'
	});
	
	var productIDCombobox;
	productIDCombobox = $("#add_productId").combobox({
		valueField:'code',
		textField:'codeName',
		onSelect : function() 
		{
			clearAllBasicLawProductDataGrid();
		}
	});
	
	$("#add_versionCode").combobox({
		url:contextPath+'/codeQuery/basicLawVersionQuery?execState=1',
		valueField:'basicLawtId',
		textField:'versionName',
		onSelect : function() 
		{
			$.post(contextPath+'/basicLaw/queryBasicLawVersionInfoEntify?basicLawId=' + $("#add_versionCode").combobox("getValue"), function(data)
			{			   
				$("#add_versionName").val(data.basicLaw.versionName);
			    $("#add_execState").combobox('setValue',data.basicLaw.execState);
			    $("#add_execStartDate").datebox('setValue',data.basicLaw.execStartdate);
			    $("#add_execEndDate").datebox('setValue',data.basicLaw.execEnddate);
			});
		}
	});
	
	// 产品子类初始化
	var productSubTypeCombobox;
	productSubTypeCombobox = $("#add_productSubType").combobox({
		valueField:'code',
		textField:'codeName',
		editable:false,
		onSelect : function() 
		{
			clearAllBasicLawProductDataGrid();
			
			
			productSubTypeValue = productSubTypeCombobox.combobox("getValue");
			// 01-个人寿险，02-车险，03-银保
			if(productTypeValue==2)
			{
				if(productSubTypeValue==01)
				{
					$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
					$("#BasicLawYBInfo").hide();// 银行保险薪资参数-奖金比例
					$("#BasicLawInsInfo").show();// 个人寿险薪资参数-奖金比例
					$("#add_productId").combobox({
						required:false,
						url:contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue
					});
					$("#add_productId").combobox("disable");
					
				}else if(productSubTypeValue==02)
				{
					$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
					$("#BasicLawYBInfo").hide();// 银行保险薪资参数-奖金比例
					$("#BasicLawInsInfo").hide();// 个人寿险薪资参数-奖金比例
					$("#add_productId").combobox("enable");
					$("#add_productId").combobox({
						required:true,
						url:contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue
					});
					   
				}else if(productSubTypeValue==03)
				{
					$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
					$("#BasicLawYBInfo").show();// 银行保险薪资参数-奖金比例
					$("#BasicLawInsInfo").hide();// 个人寿险薪资参数-奖金比例
					$("#add_productId").combobox("enable");
					$("#add_productId").combobox({
						required:true,
						url:contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue
					});
				}
				
			}
			else{
				$("#add_productId").combobox("enable");
//				console.info($("#add_productId").combobox("options"));
				productIDCombobox.combobox('clear');
				$("#add_productId").combobox({
					required:true,
					url:contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue
				});
				productSubTypeValue="";
			}
		 }
	});
	
	//产品类别出初始化
	var productTypeCombobox;
	productTypeCombobox=$("#add_productType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType',
		valueField:'code',
		textField:'codeName',
		editable:false,
		onSelect : function() 
		{
			clearAllBasicLawProductDataGrid();
			
			productTypeValue = productTypeCombobox.combobox("getValue");
			// 1-财富，2-保险
			if(productTypeValue=="1"){
				url = contextPath+'/codeQuery/tdCodeQuery?codeType=wealthProSubType';
				$("#basicLawWealthInfo").show();// 财富产品薪资参数-奖金比例
				$("#BasicLawYBInfo").hide();// 银行保险薪资参数-奖金比例
				$("#BasicLawInsInfo").hide();// 个人寿险薪资参数-奖金比例
			}
			else{
				url = contextPath+'/codeQuery/tdCodeQuery?codeType=insProSubType';
				$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
				$("#BasicLawYBInfo").show();// 银行保险薪资参数-奖金比例
				$("#BasicLawInsInfo").show();// 个人寿险薪资参数-奖金比例
				
			}
			
			productSubTypeCombobox.combobox('clear');
			productSubTypeCombobox.combobox("reload",url);
		}
	})
}
//财富产品薪资参数-奖金比例
var BasicLawWealthTable;
function initBasicLawWealthTable()
{
	BasicLawWealthTable = $('#BasicLawWealthTable').datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{field : 'ck',
			         checkbox : true,
			         width : 2
			        },{
		        	field : 'subscriptionFeeLower',
		        	title : '认购金额下限-包含',
		        	width : 100,
		        	editor: {
						type:'numberbox',
						options:{
							min:0,
							precision:2
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.subscriptionFeeLower;
		        	}
		        },{
		        	field : 'subscriptionFeeUpper',
		        	title : '认购金额上限-不包含',
		        	width : 100,
		        	editor: {
		        		type:'numberbox',
						options:{
							min:0,
							precision:2
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.subscriptionFeeUpper;
		        	}
		        },{
					field : 'bonusRate',
					title : '奖金比例%',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							precision:6,
							required:true
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.bonusRate;
					}
				},{
		        	field : 'execStartDate',
		        	title : '执行开始日期',
		        	width : 100,
		        	editor: {
						type:'datebox',
						options:{
							required:true,
							validType: 'validDate'
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.execStartDate;
		        	}
		        },{
		        	field : 'execEndDate',
		        	title : '执行结束日期',
		        	width : 100,
		        	editor: {
						type:'datebox',
						options:{
							validType: "validDate"
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.execEndDate;
		        	}
		        },{
		        	field : 'execState',
		        	title : '执行状态',
		        	width : 100,
		        	editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,
							editable:false, 
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
							onSelect:function(){
								var wbpExecState = BasicLawWealthTable.datagrid('getEditor', {index:BasicLawWealthTableRowIndex,field:'execState'});
								var wbpExecStateName = $(wbpExecState.target).combobox('getText');
								BasicLawWealthTable.datagrid('getRows')[BasicLawWealthTableRowIndex]['execStateName'] = wbpExecStateName;
							}
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.execStateName;
		        	}
		        },{
					field : 'execStateName',
					title : '执行状态名称',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.execStateName;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#BasicLawWealthTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(index){
			BasicLawWealthTableEditOneRow(index);
		}
	});	
}
/** ******************************财富产品薪资参数-奖金比例增/删/编辑************************************ */
var BasicLawWealthTableRowIndex;
//增加一行
function BasicLawWealthTableAddOneRow(){
	BasicLawWealthTableRowIndex = addOneRow(BasicLawWealthTable,BasicLawWealthTableRowIndex);
}
//删除一行
function BasicLawWealthTableRemoveOneRow(){
	removeOneRow(BasicLawWealthTable,BasicLawWealthTableRowIndex);
	BasicLawWealthTableRowIndex= null;
}
//编辑指定行
function BasicLawWealthTableEditOneRow(index){
	if(editOneRow(BasicLawWealthTable,BasicLawWealthTableRowIndex,index)){
		BasicLawWealthTableRowIndex = index;
	}
}
//锁定编辑行
function BasicLawWealthTableLockOneRow(){
	if(lockOneRow(BasicLawWealthTable,BasicLawWealthTableRowIndex)){
		BasicLawWealthTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}


//银保奖金比例信息列表
var BasicLawYBTable;
function initBasicLawYBTable()
{
		BasicLawYBTable = $('#BasicLawYBTable').datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{field : 'ck',
			         checkbox : true,
			         width : 2
			        },{
					field : 'paymentPeriod',
					title : '缴费期限',
					width : 100,
					editor: {
		        		type:'numberbox',
						options:{
							required:true,
							min:1,
							max:100,
							precision:0
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.paymentPeriod;
					}
				},{
					field : 'paymentUnit',
					title : '缴费期限单位',
					width : 100,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,
							editable:false,
							// 查询财富类费用类型
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=paymentPeriod',
							onSelect:function(){
								var paymentUnit = BasicLawYBTable.datagrid('getEditor', {index:BasicLawYBTableRowIndex,field:'execState'});
								var paymentUnitName = $(paymentUnit.target).combobox('getText');
								BasicLawYBTable.datagrid('getRows')[BasicLawYBTableRowIndex]['paymentUnit'] = paymentUnitName;
							}
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.paymentUnit;
					}
				},{
					field : 'policyYear',
					title : '保单年度',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:1,
							max:100
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.policyYear;
					}
				},{
					field : 'bonusRate',
					title : '奖金比例%',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:0,
							precision:6
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.bonusRate;
					}
				},{
					field : 'execStartDate',
					title : '执行开始日期',
					width : 100,
					editor: {
						type:'datebox',
						options:{
							required:true,
							validType: 'validDate'   
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execStartDate;
					}
				},{
					field : 'execEndDate',
					title : '执行结束日期',
					width : 100,
					editor: {
						type:'datebox',
						options:{
							validType: 'validDate'   
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execEndDate;
					}
				},{
					field : 'execState',
					title : '执行状态',
					width : 100,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,
							editable:false,
							// 查询财富类费用类型
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
							onSelect:function(){
								var bbpExecState = BasicLawYBTable.datagrid('getEditor', {index:BasicLawYBTableRowIndex,field:'execState'});
								var bbpExecStateName = $(bbpExecState.target).combobox('getText');
								BasicLawYBTable.datagrid('getRows')[BasicLawYBTableRowIndex]['execStateName'] = bbpExecStateName;
							}
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execStateName;
					}
				},{
					field : 'execStateName',
					title : '执行状态名称',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.execStateName;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#BasicLawYBTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(index){
			BasicLawYBTableEditOneRow(index);
		}
	});	
}

var BasicLawYBTableRowIndex;
/** ******************************银行保险薪资参数-奖金比例增/删/编辑************************************ */
//增加一行
function BasicLawYBTableAddOneRow(){
	BasicLawYBTableRowIndex = addOneRow(BasicLawYBTable,BasicLawYBTableRowIndex);
}
//删除一行
function BasicLawYBTableRemoveOneRow(){
	removeOneRow(BasicLawYBTable,BasicLawYBTableRowIndex);
	BasicLawYBTableRowIndex= null;
}
//编辑指定行
function BasicLawYBTableEditOneRow(index){
	if(editOneRow(BasicLawYBTable,BasicLawYBTableRowIndex,index)){
		BasicLawYBTableRowIndex = index;
	}
}
//锁定编辑行
function BasicLawYBTableLockOneRow(){
	if(lockOneRow(BasicLawYBTable,BasicLawYBTableRowIndex)){
		BasicLawYBTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}


//个人寿险薪资参数-奖金比例
var BasicLawInsTable;
function initBasicLawInsTable()
{
	BasicLawInsTable = $('#BasicLawInsTable').datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{field : 'ck',
			         checkbox : true,
			         width : 2
			        },{
					field : 'premiumLower',
					title : '保费下限-闭区间',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							precision:2
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.premiumLower;
					}
				},{
					field : 'premiumUpper',
					title : '保费上限-开区间',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							precision:2
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.premiumUpper;
					}
				},{
					field : 'policyYear',
					title : '保单年度',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:0,
							max:100
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.policyYear;
					}
				},{
					field : 'bonusRate',
					title : '奖金比例%',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:0,
							precision:6
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.bonusRate;
					}
				},{
					field : 'execStartDate',
					title : '执行开始日期',
					width : 100,
					editor: {
						type:'datebox',
						options:{
							required:true,
							validType: 'validDate'   
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execStartDate;
					}
				},{
					field : 'execEndDate',
					title : '执行结束日期',
					width : 100,
					editor: {
						type:'datebox',
						options:{
							validType: 'validDate'   
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execEndDate;
					}
				},{
					field : 'execState',
					title : '执行状态',
					width : 100,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,
							editable:false,
							// 查询财富类费用类型
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
							onSelect:function(){
								var libpExecState = BasicLawInsTable.datagrid('getEditor', {index:BasicLawInsTableRowIndex,field:'execState'});
								var libpExecStateName = $(libpExecState.target).combobox('getText');
								BasicLawInsTable.datagrid('getRows')[BasicLawInsTableRowIndex]['execStateName'] = libpExecStateName;
							}
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execStateName;
					}
				},{
					field : 'execStateName',
					title : '执行状态名称',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.execStateName;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#BasicLawInsTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(index){
			BasicLawInsTableEditOneRow(index);
		}
	});	
}
/** *****************************个人寿险薪资参数-奖金比例增/删/编辑************************************ */
var BasicLawInsTableRowIndex;
//增加一行
function BasicLawInsTableAddOneRow(){
	BasicLawInsTableRowIndex = addOneRow(BasicLawInsTable,BasicLawInsTableRowIndex);
}
//删除一行
function BasicLawInsTableRemoveOneRow(){
	removeOneRow(BasicLawInsTable,BasicLawInsTableRowIndex);
	BasicLawInsTableRowIndex= null;
}
//编辑指定行
function BasicLawInsTableEditOneRow(index){
	if(editOneRow(BasicLawInsTable,BasicLawInsTableRowIndex,index)){
		BasicLawInsTableRowIndex = index;
	}
}
//锁定编辑行
function BasicLawInsTableLockOneRow(){
	if(lockOneRow(BasicLawInsTable,BasicLawInsTableRowIndex)){
		BasicLawInsTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}

//考核参数信息
var BasicLawAssessTable;
function initBasicLawAssessTable()
{
		BasicLawAssessTable = $('#BasicLawAssessTable').datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{field : 'ck',
			         checkbox : true,
			         width : 2
			        },{
					field : 'param',
					title : '参数类型',
					width : 100,
					editor: {
						type:'combobox',
						options:{
							required:true,
							editable:false,
							valueField:'code',
							textField:'codeName',
							// 查询财富类费用类型
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=assessParam',
							onSelect:function(){
								var param = BasicLawAssessTable.datagrid('getEditor', {index:BasicLawAssessTableRowIndex,field:'param'});
								var paramName = $(param.target).combobox('getText');
								BasicLawAssessTable.datagrid('getRows')[BasicLawAssessTableRowIndex]['paramName'] = paramName;
							}
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.paramName;
					}
				},
				{
					field : 'paramName',
					title : '参数类型名称',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.paramName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'paramLower',
					title : '参数数值下限(包含)',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							precision:2
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.paramLower;
					}
				},{
					field : 'paramUpper',
					title : '参数数值上限(不包含)',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							precision:2
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.paramUpper;
					}
				},{
					field : 'paramUnit',
					title : '参数数值单位',
					width : 100,
					sortable : true,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							// 查询财富类费用类型
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=payPeriodUnit',
							onSelect:function(){
								var paramUnit = BasicLawAssessTable.datagrid('getEditor', {index:BasicLawAssessTableRowIndex,field:'paramUnit'});
								var paramUnitName = $(paramUnit.target).combobox('getText');
								BasicLawAssessTable.datagrid('getRows')[BasicLawAssessTableRowIndex]['paramUnitName'] = paramUnitName;
								
							}
						}
					},
					formatter : function(value, row, index) {
						return row.paramUnitName;
					}
				},
				{
					field : 'paramUnitName',
					title : '参数单位名称',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.paramUnitName;
					} 
				},
				{
					field : 'durationRate',
					title : '久期系数',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:0,
							precision:2
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.durationRate;
					}
				},{
					field : 'execStartDate',
					title : '执行开始日期',
					width : 100,
					editor: {
						type:'datebox',
						options:{
							required:true,
							validType: 'validDate'   
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execStartDate;
					}
				},{
					field : 'execEndDate',
					title : '执行结束日期',
					width : 100,
					editor: {
						type:'datebox',
						options:{
							validType: 'validDate'   
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execEndDate;
					}
				},{
					field : 'execState',
					title : '执行状态',
					width : 100,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,
							editable:false,
							// 查询财富类费用类型
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
							onSelect:function(){
								var assessExecState = BasicLawAssessTable.datagrid('getEditor', {index:BasicLawAssessTableRowIndex,field:'execState'});
								var assessExecStateName = $(assessExecState.target).combobox('getText');
								BasicLawAssessTable.datagrid('getRows')[BasicLawAssessTableRowIndex]['execStateName'] = assessExecStateName;
							}
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.execStateName;
					}
				},{
					field : 'execStateName',
					title : '执行状态名称',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.execStateName;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#BasicLawAssessTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(index){
			BasicLawAssessTableEditOneRow(index);
		}
	});	
}
/** ******************************财富产品薪资参数-奖金比例增/删/编辑************************************ */
var BasicLawAssessTableRowIndex;
//增加一行
function BasicLawAssessTableAddOneRow(){
	BasicLawAssessTableRowIndex = addOneRow(BasicLawAssessTable,BasicLawAssessTableRowIndex);
}
//删除一行
function BasicLawAssessTableRemoveOneRow(){
	removeOneRow(BasicLawAssessTable,BasicLawAssessTableRowIndex);
	BasicLawAssessTableRowIndex= null;
}
//编辑指定行
function BasicLawAssessTableEditOneRow(index){
	if(editOneRow(BasicLawAssessTable,BasicLawAssessTableRowIndex,index)){
		BasicLawAssessTableRowIndex = index;
	}
}
//锁定编辑行
function BasicLawAssessTableLockOneRow(){
	if(lockOneRow(BasicLawAssessTable,BasicLawAssessTableRowIndex)){
		BasicLawAssessTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}

function addBasicLawSave()
{
	var param = {};
	if(!beforeSubmit())
	{
		return;
	}
	
	//获取产品类别，产品子类，产品ID值
	var productType = $("#add_productType").combobox("getValue");
	var productSubType = $("#add_productSubType").combobox("getValue");
	var productId = $("#add_productId").combobox("getValue");
	
	
	//财富产品表格序列化
	if(productType==1)
	{
		var rows = BasicLawWealthTable.datagrid("getRows");
		if(rows.length==0){
			$.messager.alert('提示', "请添加财富产品薪资参数再提交！", 'info');
			return;
		}
		
		if(!BasicLawWealthTableLockOneRow()){
			$.messager.alert('提示', "财富产品薪资参数输入有误！", 'info');
			return;
		}
		if(!checkExecDate(BasicLawWealthTable,"财富产品薪资参数"))
		{
			return;
		}
		//财富产品薪资参数-奖金比例
		param.basicLawWealthInfo = $("#BasicLawWealthTable").datagrid("getRows");
	}
	//保险产品表格序列化
	if(productType==2)
	{
		
		//个人寿险薪资参数-奖金比例
		if(productSubType==01)
		{
			var rows = BasicLawInsTable.datagrid("getRows");
			if(rows.length==0){
				$.messager.alert('提示', "请添加个人寿险薪资参数再提交！", 'info');
				return;
			}
			
			if(!BasicLawInsTableLockOneRow()){
				$.messager.alert('提示', "个人寿险薪资参数输入有误！", 'info');
				return;
			}
			
			if(!checkExecDate(BasicLawInsTable,"个人寿险薪资参数"))
			{
				return;
			}
			param.basicLawInsInfo = $("#BasicLawInsTable").datagrid("getRows");
		}
		//银行保险薪资参数-奖金比例
		if(productSubType==03)
		{
			
			var rows = BasicLawYBTable.datagrid("getRows");
			if(rows.length==0){
				$.messager.alert('提示', "请添加银行保险薪资参数再提交！", 'info');
				return;
			}
			
			if(!BasicLawYBTableLockOneRow()){
				$.messager.alert('提示', "产品银行保险薪资参数输入有误！", 'info');
				return;
			}
			
			if(!checkExecDate(BasicLawYBTable,"银行保险薪资参数"))
			{
				return;
			}
			param.basicLawYBInfo = $("#BasicLawYBTable").datagrid("getRows");
		}
	}
	
	var rows = BasicLawAssessTable.datagrid("getRows");
	if(rows.length==0){
		$.messager.alert('提示', "请添加考核参数信息再提交！", 'info');
		return;
	}
	
	if(!BasicLawAssessTableLockOneRow()){
		$.messager.alert('提示', "考核参数信息输入有误！", 'info');
		return;
	}
	if(!checkExecDate(BasicLawAssessTable,"考核参数信息"))
	{
		return;
	}
	
	//奖金比例表格序列化
	param.basicLawAssessInfo = $("#BasicLawAssessTable").datagrid("getRows");
	//序列化基本法基本信息
	param.basicLawFormInfo = formDataToJsonStr($("#add_basiclawProductForm").serialize());

	$.post("basicLaw/saveAddBasicLawProductUrl", 'param='+$.toJSON(param), function(data) {
		$.messager.alert('提示', data.msg, 'info');
		if("true"==data.succ)
		{
			$("#add_basiclawProductForm").form("clear");
			clearAllBasicLawProductDataGrid();
		}
	});
}


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

function clearAllBasicLawProductDataGrid()
{	
	clearDatagrid(BasicLawWealthTable);
	clearDatagrid(BasicLawYBTable);
	clearDatagrid(BasicLawInsTable);
	clearDatagrid(BasicLawAssessTable);
}
function beforeSubmit()
{
	if(!$("#add_basiclawProductForm").form("validate"))
	{
		return false;
	}
	return true;
}
function backListBasicLawProductPage(){
	$('#basicLawProductWindow').window('destroy');
	parent.clearBasicLawProductForm();
}
