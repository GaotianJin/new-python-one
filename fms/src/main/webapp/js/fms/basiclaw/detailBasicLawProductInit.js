jQuery(function($) 
{
	//初始化下拉列表
	initAllBasicLawInfo();
	//财富产品薪资参数-奖金比例
	initBasicLawWealthTable();
	//银保奖金比例信息列表
	initBasicLawYBTable();
	//个人寿险奖金比例
	initBasicLawInsTable();
	//考核参数信息
	initBasicLawAssessTable();
	
});
function initAllBasicLawInfo()
{	
	//版本执行状态初始化
	$("#detail_execState").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState',
		valueField:'code',
		textField:'codeName'
	});
	
	//版本编号初始化
	$("#detail_versionCode").combobox({
		url:contextPath+'/codeQuery/basicLawVersionQuery?execState=01',
		valueField:'basicLawtId',
		textField:'versionName'
	});
	
	//产品类别出初始化
	$("#detail_productType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType',
		valueField:'code',
		textField:'codeName'	
	});
	
	$("#detail_productSubType").combobox({
		valueField:'code',
		textField:'codeName'
	});
	
	$("#detail_productId").combobox({
		valueField:'code',
		textField:'codeName'
	});	
	
	
	var rows = $('#list_basicLawProductTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个产品基本法版本更新", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个产品基本法更新", 'info');
		return;
	}
	else 
	{
		var dlist = [];
		$.each(rows, function(i, n) {
			dlist.push({"basicLawId":rows[0].basicLawId,
				         "productId":rows[0].productId,
				         "productTypeCode":rows[0].productTypeCode,
				         "productSubTypeCode":rows[0].productSubTypeCode
				         }); 
		});
		$.post(contextPath+'/basicLaw/queryBasicLawProductVersionInfo?list='+$.toJSON(dlist), function(data) {
			   var productTypeValue = data.pdProduct.productType;
			   var productSubTypeValue = data.pdProduct.productSubType;
			   //1 财富产品 2 保险产品
			   if(productTypeValue=="1")
			   {
					$("#basicLawWealthInfo").show();// 财富产品薪资参数-奖金比例
					$("#BasicLawYBInfo").hide();// 银行保险薪资参数-奖金比例
					$("#BasicLawInsInfo").hide();// 个人寿险薪资参数-奖金比例
					$("#detail_productSubType").combobox("reload",contextPath+'/codeQuery/tdCodeQuery?codeType=wealthProSubType');
					$('#BasicLawWealthTable').datagrid('loadData',data.basicLawWealthList);
			   }
			   else
			   {
					$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
					$("#BasicLawYBInfo").show();// 银行保险薪资参数-奖金比例
					$("#BasicLawInsInfo").show();// 个人寿险薪资参数-奖金比例
					$("#detail_productSubType").combobox("reload",contextPath+'/codeQuery/tdCodeQuery?codeType=insProSubType');
				    if(productSubTypeValue==01) //个人寿险
					{
						$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
						$("#BasicLawYBInfo").hide();// 银行保险薪资参数-奖金比例
						$("#BasicLawInsInfo").show();// 个人寿险薪资参数-奖金比例
						$("#detail_productId").combobox("reload",contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue);
						$('#BasicLawInsTable').datagrid('loadData',data.basicLawInsList);
						   
					}else if(productSubTypeValue==02)//车险
					{
						$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
						$("#BasicLawYBInfo").hide();// 银行保险薪资参数-奖金比例
						$("#detail_productId").combobox("reload",contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue);
						$("#BasicLawInsInfo").hide();// 个人寿险薪资参数-奖金比例
   
					}else if(productSubTypeValue==03) //银行保险
					{
						$("#basicLawWealthInfo").hide();// 财富产品薪资参数-奖金比例
						$("#BasicLawYBInfo").show();// 银行保险薪资参数-奖金比例
						$("#BasicLawInsInfo").hide();// 个人寿险薪资参数-奖金比例
						$("#detail_productId").combobox("reload",contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue);
						$('#BasicLawYBTable').datagrid('loadData',data.basicLawYBList);

					}
			   }
			   $("#detail_productId").combobox("reload",contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue);
			   setInputValueById("detail_basiclawProductDiv",data.basicLaw);
			   setInputValueById("detail_basiclawProductDiv",data.pdProduct);
			   $('#BasicLawAssessTable').datagrid('loadData',data.basicLawAssessList);
		});
	};
	
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
			        },
			        {
			        	field : 'subscriptionFeeLower',
			        	title : '认购金额下限(包含)',
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
			        },
			        {
		        	field : 'subscriptionFeeUpper',
		        	title : '认购金额上限(不包含)',
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
					title : '奖金比例',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							max:1,
							precision:2
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
							// 查询财富类费用类型
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
		}
	});	
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
					title : '奖金比例',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							max:1,
							precision:2
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
		}
	});	
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
			        },
			        {
						field : 'premiumLower',
						title : '保费下限(包含)',
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
					},
			        {
					field : 'premiumUpper',
					title : '保费上限(不包含)',
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
					title : '奖金比例',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							min:0,
							max:1,
							precision:2
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
		}
	});	
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
							valueField:'code',
							textField:'codeName',
							// 查询财富类费用类型
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=assessParam',
							onSelect:function(){
								var param = BasicLawAssessTable.datagrid('getEditor', {index:BasicLawAssessTableRowIndex,field:'execState'});
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
				},
				{
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
				},
				{
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
							required:true,
							editable:false,
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
					title : '参数数值单位名称',
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
							min:0,
							max:1,
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
		}
	});	
}

function backListBasicLawProductPage(){
	$('#basicLawProductWindow').window('destroy');
	parent.clearBasicLawProductForm();
}