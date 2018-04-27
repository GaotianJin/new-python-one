jQuery(function($) {
	initAllCombobox();
	initTradeStrus();
	queryTradeStrusList();
});

function initAllCombobox() {
	$('#stausAgencyComId').combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	$('#stausProductId').combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});

	$('#stausComId').combobox({
		url : contextPath + "/branch/queryComListCode",
		valueField : 'id',
		textField : 'name'
	});
	
	$('#stausTradeStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQueryIn?codeType=tradeStatus&codeListStr=['04','05','06','07','08','09','10']",
		valueField : 'code',
		textField : 'codeName'
	});
}

var tradeStausTableList;
function initTradeStrus() {
	var  selectIndex = -1;
	tradeStausTableList = $('#tradeStausTable').datagrid({
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 5, 10, 15, 20, 25, 30 ],
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			width : 2
		}, // 显示复选框
		{
			field : 'tradeInfoId',
			title : '交易编号',
			width : 100,
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeInfoId;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'tradeType',
			title : '交易类型',
			width : 100,
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeType;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'agencyComName',
			title : '基金管理人',
			width : 100,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.agencyComName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'productName',
			title : '产品',
			width : 100,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.productName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'comName',
			title : '管理机构',
			width : 80,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.comName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'tradeNo',
			title : '交易号码',
			width : 110,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeNo;
			}
		}, {
			field : 'tradeInfoNo',
			title : '合同号',
			width : 110,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeInfoNo;
			}
		}, {
			field : 'tradeAppant',
			title : '认购人',
			width : 80,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeAppant;
			}
		}, {
			field : 'custBaseInfoId',
			title : '客户号',
			width : 80,
			hidden : true,
			formatter : function(value, row, index) {
				return row.custBaseInfoId;
			}
		}, {
			field : 'investCustomerType',
			title : '投资人类型',
			width : 80,
			hidden : true,
			formatter : function(value, row, index) {
				return row.investCustomerType;
			}
		}, /*{
			field : 'tradeDate',
			title : '交易日期',
			width : 80,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeDate;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'tradeInputDate',
			title : '录入日期',
			width : 80,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeInputDate;
			}
		}, */{
			field : 'agentName',
			title : '财富顾问',
			width : 80,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.agentName;
			}
		}, {
			field : 'tradeComName',
			title : '交易机构',
			width : 80,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeComName;
			}
		}, {
			field : 'tradeStoreName',
			title : '交易网点',
			width : 80,
			hidden:true,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeStoreName;
			}
		}, {
			field : 'tradeTotalAssets',
			title : '交易总额',
			width : 60,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeTotalAssets;
			}
		},{
			field : 'actuTradeTotalAssets',
			title : '实际交易总额',
			width : 80,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.actuTradeTotalAssets;
			}
		}, {
			field : 'tradeStausName',
			title : '交易状态',
			width : 60,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.tradeStausName;
			}
		} ] ],
		toolbar : [ {
						iconCls : 'icon-add',
						text : '交易状态维护',
						handler : function(){
							changeStatus("Add");
						}
					},'-',{
						iconCls : 'icon-edit',
						text : '交易状态修改',
						handler : function() {
							changeStatus("Modify");
						}
					},'-',{
						iconCls : 'icon-reload',
						text : '浮动类产品份额维护',
						handler : function() {
							addCopies('浮动产品份额导入', contextPath + "/tradeS/importFloatCopiesFileUrl");
						}
					} ],
		onLoadSuccess : function() {
			$('#tradeStausTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function queryTradeStrusList() {
	tradeStausTableList.datagrid('options').url = "tradeS/tradeStrus";
	var queryParam = $("#TradeStausForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	tradeStausTableList.datagrid('load', {
		queryParam : queryParam
	});
}

// 清空查询条件然后进行查询
function clearTradeStaus() {
	$('#TradeStausForm').form('clear');
}

function addStaustab(title, href) {
	$('<div id="updTradeStatusWindow"/>').dialog({
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

function addCopies(title, href) {
	$('<div id="addCopiesWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		//fit : true,
		inline : false,
		minimizable : false,
		width: 500,    
		height: 200, 
		onClose : function() {
			$(this).window('destroy');
		}
	});
}
function changeStatus(operate) {
	var rows = $('#tradeStausTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一条交易信息进行修改");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一条交易信息修改");
		return;
	}
	var param = {};
	var tradeInfoId = rows[0].tradeInfoId;
	var tradeType = rows[0].tradeType;
	var productSubType = rows[0].productSubType;
	var custBaseInfoId = rows[0].custBaseInfoId;
	var investCustomerType = rows[0].investCustomerType;
	param.tradeInfoId = tradeInfoId;
	param.tradeType = tradeType;
	param.operate = operate;
	param.productSubType = productSubType;
	param.custBaseInfoId = custBaseInfoId;
	param.investCustomerType = investCustomerType;
	param.tradeDetailInfo = $.toJSON(encodeURI(rows[0]));
	param = $.toJSON(param);
	addStaustab('交易状态信息维护', contextPath + "/tradeS/updateTradeStatus?param="+ param);
}

