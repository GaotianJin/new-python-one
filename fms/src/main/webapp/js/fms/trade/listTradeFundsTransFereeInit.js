jQuery(function($) {
	
	//初始化列表信息
	initTradeFundsTranFereeTable();
});
//初始化列表信息
var tradeFundsTranFereeTable;
function initTradeFundsTranFereeTable()
{
	var  selectIndex = -1;
	tradeFundsTranFereeTable = $('#tradeFundsTranFereeTable').datagrid({
		fitColumns : true,
		rownumbers : true,
		url: "funds/queryFundsTranFereeList",
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
			title : '转让信息Id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeFundsShareChangeId;
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
		},{
			field : 'custBaseInfoId',
			title : '客户ID',
			width : 100,
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.custBaseInfoId;
			} // 需要formatter一下才能显示正确的数据
		}, 
		{
			field : 'orTradeNo',
			title : '原交易号',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.orTradeNo;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'orTradeId',
			title : '原交易id',
			width : 120,
			sortable : true,
			hidden:true,
			formatter : function(value, row, index) {
				return row.orTradeId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'tradeInfoId',
			title : '现交易id',
			width : 120,
			sortable : true,
			hidden:true,
			formatter : function(value, row, index) {
				return row.tradeInfoId;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'productName',
			title : '产品',
			width : 120,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productName;
			} // 需要formatter一下才能显示正确的数据
		}, 
		{
			field : 'productId',
			title : '产品id',
			width : 100,
			hidden : true,
			formatter : function(value, row, index) {
				return row.productId;
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
			field : 'orAgentName',
			title : '原财富顾问',
			width : 100,
			formatter : function(value, row, index) {
				return row.orAgentName;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'agentName',
			title : '现财富顾问',
			width : 100,
			formatter : function(value, row, index) {
				return row.agentName;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'orAgentId',
			title : '原财富顾问id',
			width : 100,
			hidden:true,
			formatter : function(value, row, index) {
				return row.orAgentId;
			} // 需要formatter一下才能显示正确的数据
		},{
			field : 'agentId',
			title : '现财富顾问id',
			width : 100,
			hidden:true,
			formatter : function(value, row, index) {
				return row.agentId;
			} // 需要formatter一下才能显示正确的数据
		},{
			field : 'tradeStatus',
			title : '交易状态号',
			width : 60,
			hidden : true,
			formatter : function(value, row, index) {
				return row.tradeStatus;
		  }
		},
		{
			field : 'tradeStausName',
			title : '交易状态',
			width : 60,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeStausName;
		  }
		},
	    ] ],
		toolbar : [ {
						iconCls : 'icon-add',
						text : '份额受让',
						handler : function(){
							var rows = $('#tradeFundsTranFereeTable').datagrid('getSelections');
							if (rows.length == 0) {
								$.messager.alert('提示', "请选择一条转让信息进行基金份额受让处理");
								return;
							}
							if (rows.length > 1) {
								$.messager.alert('提示', "只能选择一条转让信息进行基金份额受让处理");
								return;
							}
							var tradeStatus = rows[0].tradeStatus;
							if(tradeStatus != 11){
								$.messager.alert('提示', "只有交易状态为转让中才可以进行操作！");
								return;
							}
							var param = {};
							var orAgentId = rows[0].orAgentId;
							var orTradeId=rows[0].orTradeId;
							var tradeFundsShareChangeId = rows[0].tradeFundsShareChangeId;
							var productId = rows[0].productId;
							param.orAgentId = orAgentId;
							param.tradeFundsShareChangeId = tradeFundsShareChangeId;
							param.productId = productId;
							param.orTradeId = orTradeId;
							param.tradeStatus=tradeStatus;
							param.transFereeDetailInfo = $.toJSON(encodeURI(rows[0]));
							param = $.toJSON(param);
							addTransFereetab('基金份额受让', contextPath + "/funds/transFereeInput?param="+ param);
						}
					},{
						iconCls:'icon-edit',
						text:'修改',
					    handler:function(){
					    	var rows=$("#tradeFundsTranFereeTable").datagrid('getSelections');
					    	if (rows.length == 0) {
								$.messager.alert('提示', "请选择一条转让信息进行基金份额受让处理");
								return;
							}
							if (rows.length > 1) {
								$.messager.alert('提示', "只能选择一条转让信息进行基金份额受让处理");
								return;
							}
							var tradeStatus = rows[0].tradeStatus;
							//tradeStatus : 12  待审核
							if(tradeStatus != 14){
									$.messager.alert('提示', "不可修改！只有审核退回记录才可以进行修改！");
									return;
							}
							var param = {};
							var orTradeId=rows[0].orTradeId;
							var agentId = rows[0].agentId;
							var tradeInfoId=rows[0].tradeInfoId;
							var tradeFundsShareChangeId = rows[0].tradeFundsShareChangeId;
							var productId = rows[0].productId;
							var tradeType=rows[0].tradeType;
							param.agentId = agentId;
							param.tradeFundsShareChangeId = tradeFundsShareChangeId;
							param.productId = productId;
							param.tradeInfoId = tradeInfoId;
							param.tradeType=tradeType;
							param.orTradeId = orTradeId;
							param.tradeStatus=tradeStatus;
							param.transFereeDetailInfo =$.toJSON(encodeURI(rows[0]));
							param = $.toJSON(param);
							updateTransfereeWindow('修改份额受让', contextPath+"/funds/modifyTradeFundsTransfereeUrl?param="+param)
					    }
					},{
						iconCls:'icon-undo',
						text:'撤销',
					    handler:function(){
					    	var rows=$("#tradeFundsTranFereeTable").datagrid('getSelections');
					    	if (rows.length == 0) {
								$.messager.alert('提示', "请选择一条信息进行撤销处理");
								return;
							}
							var tradeStatus = rows[0].tradeStatus;
							if(tradeStatus == 13){
									$.messager.alert('提示', "已转让的交易不可撤销！");
									return;
							}
							var param = {};
							var orTradeId=rows[0].orTradeId;
							var agentId = rows[0].agentId;
							var tradeInfoId=rows[0].tradeInfoId;
							var tradeFundsShareChangeId = rows[0].tradeFundsShareChangeId;
							param.agentId = agentId;
							param.tradeFundsShareChangeId = tradeFundsShareChangeId;
							param.tradeInfoId = tradeInfoId;
							param.orTradeId = orTradeId;
							param.tradeStatus=tradeStatus;
							param.transFereeDetailInfo =$.toJSON(encodeURI(rows[0]));
							param = $.toJSON(param);
							$.ajax({
								type:'post',
								url:'funds/deleteTradeFundsShareRecord',
								data:'param='+param,
								cache:false,
								success:function(returnData){
									try {
										if(returnData.success=="true"){
											queryTradeFundsTranFereeList();
											$.messager.alert('提示', returnData.msg);
										}else{
											$.messager.alert('提示', returnData.msg);
										}
									} catch (e) {
										$.messager.alert('提示', e);
									}
								}
							
							});
					    }
					}],
		onLoadSuccess : function() {
			$('#tradeFundsTranFereeTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

//修改份额受让信息窗口
function updateTransfereeWindow(title, href) 
{
	$('<div id="updateTransfereeWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true, 
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
			queryTradeFundsTranFereeList();
		}
	});
}

//查询客户转让信息
function queryTradeFundsTranFereeList()
{
	tradeFundsTranFereeTable.datagrid('options').url = "funds/queryFundsTranFereeList";
	var queryParam = $("#TradeFundsTranFereeForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	tradeFundsTranFereeTable.datagrid('load', {
		queryParam : queryParam
	});
}
//显示基金份额转让详情tabs
function addTransFereetab(title, href) {
	$('<div id="transFereeInputWindows"/>').dialog({
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
/**********************************************************************************************/
//清空查询条件
function clearTradeFundsTranFeree()
{
	$('#TradeFundsTranFereeForm').form('clear');
	queryTradeFundsTranFereeList();
}
