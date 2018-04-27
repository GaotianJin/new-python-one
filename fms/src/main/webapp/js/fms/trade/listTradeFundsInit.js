jQuery(function($) {
	initTradeStrus();
	queryTradeFundsList();
	//初始化combobox
	productIdComobox = $("#tradeFunds_productName").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});
});

var tradeFundsTableList;
function initTradeStrus() {
	var  selectIndex = -1;
	tradeFundsTableList = $('#tradeFundsTable').datagrid({
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
			field : 'custName',
			title : '认购人',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.custName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'tradeInfoId',
			title : '交易ID',
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
			field : 'tradeNo',
			title : '交易号',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeNo;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'productName',
			title : '产品',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'productSubType',
			title : '产品子类型',
			width : 100,
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.productSubType;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'tradeTotalAsset',
			title : '交易金额',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeTotalAsset;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'agentName',
			title : '财富顾问',
			width : 100,
			//sortable : true,
			formatter : function(value, row, index) {
				return row.agentName;
			}
		}, {
			field : 'tradeDate',
			title : '产品开放日',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeDate;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'tradeStausName',
			title : '交易状态',
			width : 60,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeStausName;
			}
		} ] ],
		toolbar : [ {
						iconCls : 'icon-add',
						text : '份额转让',
						handler : function(){
							changeStatus("Add");
						}
					}],
		onLoadSuccess : function() {
			$('#tradeFundsTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function queryTradeFundsList() {
	tradeFundsTableList.datagrid('options').url = "funds/queryFundsList";
	var queryParam = $("#TradeFundsForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	tradeFundsTableList.datagrid('load', {
		queryParam : queryParam
	});
}

// 清空查询条件
function clearTradeFunds() {
	$('#TradeFundsForm').form('clear');
	queryTradeFundsList();
}

function addStaustab(title, href) {
	$('<div id="updTradeFundsWindow"/>').dialog({
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
function changeStatus(operate) {
	var rows = $('#tradeFundsTable').datagrid('getSelections');
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
	param.tradeInfoId = tradeInfoId;
	param.tradeType = tradeType;
	param.productSubType = productSubType;
	param.tradeDetailInfo = $.toJSON(encodeURI(rows[0]));
	param = $.toJSON(param);
	addStaustab('基金份额转让', contextPath + "/funds/updateTradeFunds?param="+ param);
}

