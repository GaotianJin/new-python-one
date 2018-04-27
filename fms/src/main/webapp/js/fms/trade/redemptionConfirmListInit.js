
jQuery(function($) {
	initAllCombobox();//初始化下拉框
	initTradeRedemptionConfrimList();
	});

function initAllCombobox(){
	//产品名称
	$("#redemptionConfirmList_productName").combobox({
		url : contextPath + '/redemption/redemtionProductQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 客户姓名
	$("#redemptionConfirmList_custName").combobox({
		url : contextPath + '/redemption/redemtionCustomerQuery',
		valueField : 'code',
		textField : 'codeName'
	});

}

/*
 * 初始化赎回页面信息列表
 * 
 */
var tradeRedemptionConfrimList;
function initTradeRedemptionConfrimList()
{	var  selectIndex = -1;
	tradeRedemptionConfrimList=$("#tradeRedemptionConfirmListId").datagrid({
		fitColumns:true,
		url : contextPath + "/redemption/queryRedemptionConfirmList", // 数据来源
		rownumbers:true,
		pagination:true,
		checkOnSelect:true,
		selectOnCheck:true,
		nowrap: false,
		singleSelect:true,
		nowrap: false,
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		 columns:[[  
		           {field:'ck',checkbox:true},   
		           {field:'redemptionInfoId',
		        	title:'赎回信息Id',
		        	width:30,
		        	hidden:true,
		        	sortable:true,
		        	formatter : function(value, row, index) {
		        		return row.redemptionInfoId;
						}
		        	},
		           {field:'productName',
		        	title:'产品名称',
		        	width:250,
		        	formatter : function(value, row, index) {
		        		return row.productName;
						}
		        	},
		           {field:'productId',
		        	title:'产品ID',
		        	width:250,
		        	hidden:true,
		        	formatter : function(value, row, index) {
		        		return row.productId;
						}
		        	},
		           {field:'agencyName',
				    title:'产品方名称',
				    width:200,
				    nowrap: false,
					formatter : function(value, row, index) {
		        		return row.agencyName;
					  }
				    },    
		           {field:'custName',
				    title:'客户名称',
				    width:80,
				    formatter : function(value, row, index) {
		        		return row.custName;
						}
				    },
		           {field:'custNo',
				    title:'客户号',
				    width:80,
				    hidden:true,
				    formatter : function(value, row, index) {
		        		return row.custNo;
						}
				    }, 
		           {field:'applyDate',
				    title:'赎回申请日期',
				    width:100,
				    formatter : function(value, row, index) {
		        		return row.applyDate;
						}
				    },
		           {field:'openDay',
				    title:'赎回开放日',
				    width:100,
				    formatter : function(value, row, index) {
		        		return row.openDay;
						}
				    },
		           {field:'redemptionTotalShare',
				    title:'本次赎回份额',
				    width:130,
				    formatter : function(value, row, index) {
		        		return row.redemptionTotalShare;
						}
				    },
		           {field:'remainingTotalShare',
				    title:'剩余份额',
				    width:130,
				    formatter : function(value, row, index) {
		        		return row.remainingTotalShare;
						}
				    }, 
		           {field:'referenceNetValue',
				    title:'参考净值',
				    width:130,
				    formatter : function(value, row, index) {
		        		return row.referenceNetValue;
						}
				    },
		           {field:'redemptionStatus',
				    title:'赎回状态',
				    width:100,
				    formatter : function(value, row, index) {
		        		return row.redemptionStatus;
						}
				    },
				    {field:'redemptionStatusCode',
					    title:'赎回状态',
					    width:100,
					    hidden:true,
					    formatter : function(value, row, index) {
			        		return row.redemptionStatusCode;
							}
					    },
					{field:'agentName',
						    title:'财富顾问',
						    width:100,
						    formatter : function(value, row, index) {
				        		return row.agentName;
								}
						}, 
					{field:'agentId',
						    title:'财富顾问Id',
						    width:100,
						    hidden:true,
						    formatter : function(value, row, index) {
				        		return row.agentName;
								}
					},
					  {field:'isback',
					    title:'退回标志',
					    width:100,
					    hidden:true,
					    formatter : function(value, row, index) {
			        		return row.isback;
							}
					    },
//		           {
//						field : 'Detail',
//						title : '详情',
//						width : 80,
//						formatter : function(value, row, index) {
//							return "<a href='#'  onclick=detailRedemptionWindow('赎回信息明细','"+contextPath+"/redemption/tradeRedemptionAddUrl?tradeId="+row.tradeId+"')>赎回明细查看</a>";
//						}
//					},
		       ]],
       toolbar: [{
		   		iconCls: 'icon-add',
		   		text : '赎回确认',
		   		handler: function(){
		   			addRedemptionInfoConfirm();
		   		}
		   	}],
		   	onLoadSuccess : function() {
		   		$("#tradeRedemptionConfrimList").datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		   	},
		   	/*rowStyler:function(index,row){
   				if (row.isback!=null && row.isback=='Y'){
   					return 'background-color:pink;color:blue;font-weight:bold;';
   				}
   			},*/
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


/*
 *赎回确认申请
 */
function addRedemptionInfoConfirm(){
	var rows = $('#tradeRedemptionConfirmListId').datagrid('getSelections');
	if (rows.length<=0) {
		$.messager.alert('提示', "请选中一条记录", 'info')
	   	return;
	}
	
    var oneRowData=rows[0];
	var redemptionInfoId = rows[0].redemptionInfoId;
	var redemptionStatusCode=rows[0].redemptionStatusCode;
	var oneRowData = $.toJSON(oneRowData);
	
	var title = "赎回确认";
	var href = contextPath+"/redemption/redemptionConfirmAddUrl?param="+oneRowData;
	openRedemptionConfirmWindow(title,href);
}


function openRedemptionConfirmWindow(title, href) {
	$('<div id="redemptionConfirmWindow"/>').dialog({
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

function detailRedemptionWindow(title, href){
	$('<div id="detailRedemptionWindow"/>').dialog({
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

/*
 * 清空查询条件
 */
function clearRedemptionConfirmInfo(){
	$('#tradeRedemptionConfirmListForm').form('clear');
}


/*
 * 查询赎回
 */
function queryRedemptionConfirmInfoList() {
	tradeRedemptionConfrimList.datagrid('options').url = contextPath+"/redemption/queryRedemptionConfirmList";
	var queryparam = $("#tradeRedemptionConfirmListForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	tradeRedemptionConfrimList.datagrid('load',{param:queryparam});
}


