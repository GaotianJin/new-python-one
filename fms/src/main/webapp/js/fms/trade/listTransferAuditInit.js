/**
 * 初始化列表
 */
jQuery(function($) {
	initTransferAuditTable();
});

/**
 * 转让审核列表
 */
var transferAuditTable;
function initTransferAuditTable()
{
	var  selectIndex = -1;
	transferAuditTable = $('#transferAuditTable').datagrid({
		fitColumns : true,
		rownumbers : true,
		url: contextPath+"/funds/queryTransferAuditList",
		pagination : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 5, 10, 15, 20, 25, 30 ],
		columns : [ [ 
		 {
			field : 'ck',
			checkbox : true,
			width : 2
		},
		{
			field : 'tradeFundsShareChangeId',
			title : '转让审核Id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeFundsShareChangeId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'originTradeInfoId',
			title : '原交易信息Id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.originTradeInfoId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeInfoId',
			title : '现交易信息Id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeInfoId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeNo',
			title : '交易号码',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeNo;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeType',
			title : '交易类型',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeType;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeInputDate',
			title : '录入日期',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeInputDate;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'companyId',
			title : '所属机构',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.companyId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeComId',
			title : '交易机构',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeComId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeInfoNo',
			title : '合同号',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeInfoNo;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeDate',
			title : '认购日/投保日',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeDate;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'currency',
			title : '币种',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.currency;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'transferCustName',
			title : '转让客户',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.transferCustName;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'custName',
			title : '受让客户',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.custName;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'originAgentId',
			title : '原财富顾问Id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.originAgentId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'originAgentName',
			title : '原财富顾问姓名',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.originAgentName;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'agentId',
			title : '现财富顾问Id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.agentId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'agentName',
			title : '现财富顾问姓名',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.agentName;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'productId',
			title : '产品Id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.productId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'productName',
			title : '产品名称',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productName;
			} // 需要formatter一下才能显示正确的数据
		}, 
		{
			field : 'tradeTotalAsset',
			title : '转让金额',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeTotalAsset;
			} // 需要formatter一下才能显示正确的数据
		}, 
		{
			field : 'changeType',
			title : '是否全部转让',
			width : 120,
			formatter : function(value, row, index) {
				return row.changeType;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'createDate',
			title : '发布日期',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.createDate;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'valueDate',
			title : '成立日期',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.valueDate;
			} // 需要formatter一下才能显示正确的数据
		},{
			field : 'tradeStatus',
			title : '交易状态号',
			width : 120,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeStatus;
		  }
		},
		{
			field : 'tradeStausName',
			title : '交易状态',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeStausName;
		  }
		},
	    ] ],
		toolbar : [ {
						iconCls : 'icon-tick',
						text : '转让审核',
						handler : function(){
							var rows = $('#transferAuditTable').datagrid('getSelections');
							if (rows.length == 0) {
								$.messager.alert('提示', "请选择一条信息进行转让审核");
								return;
							}
							if (rows.length > 1) {
								$.messager.alert('提示', "只能选择一条信息进行转让审核");
								return;
							}
							var tradeStatus = rows[0].tradeStatus;
							if(tradeStatus != 12 && tradeStatus != 14){
								$.messager.alert('提示', "只有交易状态为待审核的才可以进行转让审核操作！");
								return;
							}
							var param={};
							var tradeFundsShareChangeId=rows[0].tradeFundsShareChangeId;
							var originTradeInfoId=rows[0].originTradeInfoId;
							var tradeInfoId=rows[0].tradeInfoId;
							var tradeNo=rows[0].tradeNo;
							var tradeType=rows[0].tradeType;
							var tradeInputDate=rows[0].tradeInputDate;
							var originAgentId=rows[0].originAgentId;
							var companyId=rows[0].companyId;
							var tradeComId=rows[0].tradeComId;
							var agentId=rows[0].agentId;
							var tradeInfoNo=rows[0].tradeInfoNo;
							var tradeDate=rows[0].tradeDate;
							var currency=rows[0].currency;
							var productId=rows[0].productId;
							param.tradeFundsShareChangeId=tradeFundsShareChangeId;
							param.originTradeInfoId=originTradeInfoId;
							param.tradeInfoId=tradeInfoId;
							param.tradeNo=tradeNo;
							param.tradeType=tradeType;
							param.tradeInputDate=tradeInputDate;
							param.originAgentId=originAgentId;
							param.companyId=companyId;
							param.tradeComId=tradeComId;
							param.agentId=agentId;
							param.tradeInfoNo=tradeInfoNo;
							param.tradeDate=tradeDate;
							param.currency=currency;
							param.productId=productId;
							param=$.toJSON(param);
							addTransferAudittab('转让审核', contextPath + "/funds/transferAuditInputUrl?param="+param);
						}
					}],
		onLoadSuccess : function() {
			$('#transferAuditTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

/**
 * 查询转让审核列表信息
 */
function queryTransferAuditList()
{
	transferAuditTable.datagrid('options').url = contextPath+"/funds/queryTransferAuditList";
	var queryParam = $("#transferAuditForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	transferAuditTable.datagrid('load',{queryParam:queryParam});	
}

/**
 * 转让审核tabs
 */
function addTransferAudittab(title, href) {
	$('<div id="TransferAuditInputWindows"/>').dialog({
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

/**
 * 清空查询条件
 */
function clearTransferAudit()
{
	$('#transferAuditForm').form('clear');
	queryTransferAuditList();
}
