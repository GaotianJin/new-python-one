var json1={"total":3,"rows":[{'productName':'悟空巨鲸对冲量化一期基金','smsType':'产品成立短信','custDetail':'客户明细'},
                            {'productName':'潍坊三河特定多个客户专项资产管理计划(第五期)','smsType':'产品成立短信','custDetail':'客户明细'},
                            {'productName':'泰州通泰应收账款投资集合资金信托计划','smsType':'产品成立短信','custDetail':'客户明细'}]};
var json2={"total":3,"rows":[{'productName':'悟空巨鲸对冲量化一期基金','smsType':'净值公布短信','custDetail':'客户明细'},
                             {'productName':'潍坊三河特定多个客户专项资产管理计划(第五期)','smsType':'净值公布短信','custDetail':'客户明细'},
                             {'productName':'泰州通泰应收账款投资集合资金信托计划','smsType':'净值公布短信','custDetail':'客户明细'}]};
var json3={"total":3,"rows":[{'productName':'悟空巨鲸对冲量化一期基金','smsType':'收益分配短信','custDetail':'客户明细'},
                             {'productName':'潍坊三河特定多个客户专项资产管理计划(第五期)','smsType':'收益分配短信','custDetail':'客户明细'},
                             {'productName':'泰州通泰应收账款投资集合资金信托计划','smsType':'收益分配短信','custDetail':'客户明细'}]};
jQuery(function($) {
	//产品名称
	$('#smsExtraction_productId').combobox({
		url : contextPath+"/codeQuery/wealthproductQuery",
		valueField : 'code',
		textField : 'codeName'
	});
	//短信类型
	$('#smsExtraction_smsType').combobox({
		url : contextPath+"/codeQuery/tdCodeQueryIn?codeType=smsType&codeListStr=['01','02','03']",
		valueField : 'code',
		textField : 'codeName',
		required : true,
		value : '01',
		onSelect : function(record){
			if(record.code=="01"){//产品成立短信
				$("#productFoundSmsTableDiv").css("display", ""); 
				$("#publicNetValueSmsTableDiv").css("display", "none"); 
				$("#incomeDistributeSmsTableDiv").css("display", "none"); 
				initProductFoundSmsTable();
				//productFoundSmsTable.datagrid("loadData",json1);
			}else if(record.code=="02"){//净值公布短信
				$("#productFoundSmsTableDiv").css("display", "none"); 
				$("#publicNetValueSmsTableDiv").css("display", ""); 
				$("#incomeDistributeSmsTableDiv").css("display", "none"); 
				initPublicNetValueSmsTable();
				//publicNetValueSmsTable.datagrid("loadData",json2);
			}else if(record.code=="03"){//收益分配短信
				$("#productFoundSmsTableDiv").css("display", "none"); 
				$("#publicNetValueSmsTableDiv").css("display", "none"); 
				$("#incomeDistributeSmsTableDiv").css("display", ""); 
				initIncomeDistributeSmsTable();
				//incomeDistributeSmsTable.datagrid("loadData",json3);
			}
		}
	});
	//短信状态
	$('#smsExtraction_smsStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=smsDealStatus",
		valueField : 'code',
		textField : 'codeName'
	});
	
	initProductFoundSmsTable();
	//smsExtraction();
	//productFoundSmsTable.datagrid("loadData",json1);
	//initPublicNetValueSmsTable();
	//initIncomeDistributeSmsTable();
});	

var productFoundSmsTable;
function initProductFoundSmsTable(){
	productFoundSmsTable = $("#productFoundSmsTable").datagrid({
		title : '产品成立短信列表', // 标题
		method : 'post',
		singleSelect : true, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[10,15,20,25,30],
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框sysSmsBatchId
				{
					field : 'sysSmsBatchId',
					title : '短信批次流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.sysSmsBatchId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productId',
					title : '产品名称',
					hidden:true,
					formatter : function(value, row, index) {
						return row.productId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productName',
					title : '产品名称',
					width : 200,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'expectOpenDay',
					title : '期望开放日',
					width : 100,
					formatter : function(value, row, index) {
						return row.expectOpenDay;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'smsType',
					title : '短信类型',
					hidden:true,
					formatter : function(value, row, index) {
						return row.smsType;
					}
				},{
					field : 'smsTypeName',
					title : '短信类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsTypeName;
					}
				},{
					field : 'smsStatus',
					title : '短信状态',
					hidden:true,
					formatter : function(value, row, index) {
						return row.smsStatus;
					}
				},{
					field : 'smsStatusName',
					title : '短信状态',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsStatusName;
					}
				},
				{
					field : 'shoudSendCount',
					title : '应发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.shoudSendCount;
					}
				},{
					field : 'actuSendCount',
					title : '实发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.actuSendCount;
					}
				},{
					field : 'custDetail',
					title : '详情',
					width : 100,
					formatter : function(value, row, index) {
						var param = {};
						param.smsType = row.smsType;
						param.sysSmsBatchId=row.sysSmsBatchId;
						return "<a href='#'  onclick=openSendSmsWindow('产品成立短信发送','"+contextPath+"/sms/sendSmsDetail?param="+$.toJSON(param)+"')>"+row.custDetail+"</a>";
					}
				}]],
		onLoadSuccess : function() {
			$('#productFoundSmsTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

var publicNetValueSmsTable;
function initPublicNetValueSmsTable(){
	publicNetValueSmsTable = $("#publicNetValueSmsTable").datagrid({
		title : '净值公布短信列表', // 标题
		method : 'post',
		singleSelect : true, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[10,15,20,25,30],
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'sysSmsBatchId',
					title : '短信批次流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.sysSmsBatchId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productId',
					title : '产品名称',
					hidden:true,
					formatter : function(value, row, index) {
						return row.productId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productName',
					title : '产品名称',
					width : 200,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'netValue',
					title : '净值',
					width : 100,
					formatter : function(value, row, index) {
						return row.netValue;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'publicDate',
					title : '净值公布日期',
					width : 100,
					formatter : function(value, row, index) {
						return row.publicDate;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'smsType',
					title : '短信类型',
					hidden : true,
					formatter : function(value, row, index) {
						return row.smsType;
					}
				},{
					field : 'smsTypeName',
					title : '短信类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsTypeName;
					}
				},{
					field : 'smsStatus',
					title : '短信状态',
					hidden : true,
					formatter : function(value, row, index) {
						return row.smsStatus;
					}
				},{
					field : 'smsStatusName',
					title : '短信状态',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsStatusName;
					}
				},
				{
					field : 'shoudSendCount',
					title : '应发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.shoudSendCount;
					}
				},{
					field : 'actuSendCount',
					title : '实发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.actuSendCount;
					}
				},{
					field : 'custDetail',
					title : '详情',
					width : 100,
					formatter : function(value, row, index) {
						var param = {};
						param.smsType = row.smsType;
						param.sysSmsBatchId=row.sysSmsBatchId;
						return "<a href='#'  onclick=openSendSmsWindow('净值公布短信发送','"+contextPath+"/sms/sendSmsDetail?param="+$.toJSON(param)+"')>"+row.custDetail+"</a>";
					}
				}]],
		onLoadSuccess : function() {
			$('#publicNetValueSmsTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

var incomeDistributeSmsTable;
function initIncomeDistributeSmsTable(){
	incomeDistributeSmsTable = $("#incomeDistributeSmsTable").datagrid({
		title : '收益分配短信列表', // 标题
		method : 'post',
		singleSelect : true, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[10,15,20,25,30],
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'sysSmsBatchId',
					title : '短信批次流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.sysSmsBatchId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productId',
					title : '产品名称',
					hidden:true,
					formatter : function(value, row, index) {
						return row.productId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productName',
					title : '产品名称',
					width : 200,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'incomeDistributeDate',
					title : '收益分配日期',
					width : 100,
					formatter : function(value, row, index) {
						return row.incomeDistributeDate;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'smsType',
					title : '短信类型',
					hidden : true,
					formatter : function(value, row, index) {
						return row.smsType;
					}
				},{
					field : 'smsTypeName',
					title : '短信类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsTypeName;
					}
				},{
					field : 'smsStatus',
					title : '短信状态',
					hidden : true,
					formatter : function(value, row, index) {
						return row.smsStatus;
					}
				},{
					field : 'smsStatusName',
					title : '短信状态',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsStatusName;
					}
				},
				{
					field : 'shoudSendCount',
					title : '应发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.shoudSendCount;
					}
				},{
					field : 'actuSendCount',
					title : '实发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.actuSendCount;
					}
				},{
					field : 'custDetail',
					title : '详情',
					width : 100,
					formatter : function(value, row, index) {
						var param = {};
						param.smsType = row.smsType;
						param.sysSmsBatchId=row.sysSmsBatchId;
						return "<a href='#'  onclick=openSendSmsWindow('收益分配短信发送','"+contextPath+"/sms/sendSmsDetail?param="+$.toJSON(param)+"')>"+row.custDetail+"</a>";
					}
				}]],
		onLoadSuccess : function() {
			$('#incomeDistributeSmsTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

/**
 * 短信提取
 */
function smsExtraction(){
	if(!$("#smsExtraction_queryConditionForm").form("validate")){
		$.messager.alert('提示', "请选择必须的短信提取条件");
		return;
	}
	var smsType = $("#smsExtraction_smsType").combobox("getValue");
	var queryParam = $("#smsExtraction_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	//console.info(productFoundSmsTable);
	//01-产品成立短信，03-净值公布短信，03-收益分配短信
	if(smsType=="01"){
		productFoundSmsTable.datagrid('options').url = "sms/getSmsBatchInfo";
		productFoundSmsTable.datagrid('load',{param:queryParam});
	}else if(smsType=="02"){
		publicNetValueSmsTable.datagrid('options').url = "sms/getSmsBatchInfo";
		publicNetValueSmsTable.datagrid('load',{param:queryParam});
	}else if(smsType=="03"){
		incomeDistributeSmsTable.datagrid('options').url = "sms/getSmsBatchInfo";
		incomeDistributeSmsTable.datagrid('load',{param:queryParam});
	}else{
		//默认为产品成立短信
		productFoundSmsTable.datagrid('options').url = "sms/getSmsBatchInfo";
		productFoundSmsTable.datagrid('load',{param:queryParam});
	}
}

/**
 * 清空短信提取条件
 */
function clearSmsExtractionCondition(){
	$("#smsExtraction_queryConditionForm").form("clear");
}

function createSms(){
	$.messager.progress({
		title:'温馨提示',
		msg:'正在生成短信信息，请稍后...'
	});
	$.ajax({
		type:'post',
		url:contextPath+"/sms/createSms",
		//data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				$.messager.progress('close');
				if(reData.success){
					//var reDataObj = reData.obj;
					//smsDetailTable.datagrid("loadData",reDataObj);
					$.messager.alert('提示', reData.msg);
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.progress('close');
				$.messager.alert('提示', e);
			}
		}
	});
}


/**
 * 短信发送详细窗口
 */
function openSendSmsWindow(title, href){
	$('<div id="sendSmsWindow"/>').dialog({
		href : href,
		//type : "post",
		modal : true,
		title : title,
		//fit : true, 
		width:1200,
		height:600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}