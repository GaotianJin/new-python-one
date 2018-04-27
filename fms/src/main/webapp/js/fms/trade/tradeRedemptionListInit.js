/*
 * 页面元素初始化
 * 
 */
jQuery(function($) {
	initAllCombobox();//初始化下拉框
	initTradeRedemptionList();
	});

function initAllCombobox(){
	//产品名称
	$("#tradeRedemptionList_productName").combobox({
		url : contextPath + '/redemption/redemtionProductQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	
	// 客户姓名
	$("#tradeRedemptionList_custName").combobox({
		url : contextPath + '/redemption/redemtionCustomerQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	//赎回状态
	$("#tradeRedemptionList_redemptionStatus").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=redemptionStatus',
		valueField : 'code',
		textField : 'codeName'
	});

	
}

/*
 * 初始化赎回页面信息列表
 * 
 */
var tradeRedemptionList;
function initTradeRedemptionList()
{	var  selectIndex = -1;
	tradeRedemptionList=$("#tradeRedemptionListId").datagrid({
		fitColumns:true,
		url : contextPath + "/redemption/queryListTradeRedemption", // 数据来源
		rownumbers:true,
		pagination:true,
		checkOnSelect:true,
		selectOnCheck:true,
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
				    title:'预约赎回份额',
				    width:130,
				    formatter : function(value, row, index) {
		        		return row.redemptionTotalShare;
						}
				    },
				    {field:'actualRedemptionShare',
					    title:'实际赎回份额',
					    width:130,
					    formatter : function(value, row, index) {
			        	return row.actualRedemptionShare;
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
		   		text : '预约赎回',
		   		handler: function(){
		   			addTradeRedemption();
		   		}
		   	},'-',{
		   		iconCls: 'icon-edit',
		   		text : '赎回修改',
		   		handler: function(){
		   			updateTradeRedemption();
		   		}
		   	},'-',{
		   		iconCls: 'icon-undo',
		   		text : '赎回撤销',
		   		handler: function(){
		   			delTradeRedemption();
		   		}
		   	}],
		   	onLoadSuccess : function() {
		   		$("#tradeRedemptionList").datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
 * 清空查询条件
 */
function clearTradeRedemptionCondition(){
	$('#tradeRedemptionQueryForm').form('clear');
}


/*
 * 查询赎回
 */
function queryTradeRedemptionInfoList() {
	tradeRedemptionList.datagrid('options').url = contextPath+"/redemption/queryListTradeRedemption";
	var queryparam = $("#tradeRedemptionQueryForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	tradeRedemptionList.datagrid('load',{param:queryparam});
}


/*
 *预约赎回 
 */
function addTradeRedemption(){
	var title = "预约赎回";
	var param = {};
	param.loadFlag = "addTradeRedemption";
	param = $.toJSON(param);
	var href = contextPath+"/redemption/tradeRedemptionAddUrl?param="+param;
	openTradeRedemptionWindow(title,href);
}

/*
 *赎回修改 
 */
function updateTradeRedemption(){
	var title = "赎回修改";
	var rows = $('#tradeRedemptionListId').datagrid('getSelections');
	if (rows.length<=0) {
		$.messager.alert('提示', "请选择一条记录进行赎回修改", 'info');
	   	return;
	}
	
    var oneRowData=rows[0];
	var redemptionInfoId = rows[0].redemptionInfoId;
	var redemptionStatusCode=rows[0].redemptionStatusCode;
	oneRowData.loadFlag = "updateTradeRedemption";
	var oneRowData = $.toJSON(oneRowData);
	
	if (redemptionStatusCode=='02') {
		$.messager.alert('提示', "该赎回记录为复核中，无法进行修改", 'info')
	   	return;
	}
	if (redemptionStatusCode=='03') {
		$.messager.alert('提示', "该赎回记录为确认中，无法进行修改", 'info')
	   	return;
	}
	if (redemptionStatusCode=='04') {
		$.messager.alert('提示', "该记录为已赎回，无法进行修改", 'info')
	   	return;
	}
	if (redemptionStatusCode=='05') {
		$.messager.alert('提示', "该记录为撤销赎回，无法进行修改", 'info')
	   	return;
	}
	
	
	if (redemptionInfoId!=null) {
		var href = contextPath+"/redemption/tradeRedemptionAddUrl?param="+oneRowData;
		openTradeRedemptionWindow(title,href);
		
	}
	else 
	{
		$.messager.alert('提示', "请选择一条记录进行赎回修改", 'info');
	   	return;
	}
}

/*
 * 赎回撤销
 */
function delTradeRedemption() {
	var rows = $('#tradeRedemptionListId').datagrid('getSelections');
	if (rows.length<=0) {
		$.messager.alert('提示', "请选择一条记录进行赎回撤销", 'info');
	   	return;
	}
	
	var redemptionInfoId = rows[0].redemptionInfoId;
	var redemptionStatus = rows[0].redemptionStatusCode;
		if (redemptionInfoId == null || redemptionInfoId == undefined|| redemptionInfoId == "") {
			$.messager.alert('提示', "请选择一条记录", 'info')
			return;
		
		}
		if (redemptionStatus == "05") {
			$.messager.alert('提示', "该记录已经处于赎回撤销", 'info')
			return;
		}
		if (redemptionStatus == "04") {
			$.messager.alert('提示', "该记录已赎回成功，无法进行赎回撤销", 'info')
			return;
		}
		// 赎回撤销
		$.ajax({
			type : 'post',
			url : 'redemption/delTradeRedemption',
			data : 'redemptionInfoId=' + redemptionInfoId,
			cache : false,
			success : function(returnData) {
				try {
					if (returnData.success) {
						redemptionInfoId = returnData.obj;
						$.messager.alert('提示', returnData.msg);
					} else {
						$.messager.alert('提示', returnData.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
			}
		});
}

//打开赎回预约窗口
function openTradeRedemptionWindow(title, href) {
	$('<div id="tradeRedemptionWindow"/>').dialog({
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

//查看赎回详情窗口
function detailRedemptionWindow(title, href){
	$('<div id="detailRedemptionWindow"/>').dialog({
		href : href,
		//type : "post",
		modal : true,
		title : title,
		width:1200,
		height:600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}


