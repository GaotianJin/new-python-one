var hotProductOrderInfoTable;

jQuery(function($) {
	initCombobox();
	initHotProductOrderInfoTable();
	queryHotProductOrderList();
});


function initCombobox(){
	
	//产品名称
	$("#hotPdOrder_productId").combobox({
		valueField : 'code',
		textField : 'codeName',
		onShowPanel : function(){
			var agencyComId = $("#pdAmountOrder_agencyComId").combobox("getValue");
			if(agencyComId==null||agencyComId==""||agencyComId==undefined){
				var url = contextPath + '/codeQuery/wealthproductQuery';
			}else{
				var url = contextPath + '/codeQuery/productwealthQuery?codeType='+ agencyComId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});
	
	//合作机构
	$("#hotPdOrder_agencyComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
			
		/*onSelect:function(record){
			var productUrl = contextPath + '/codeQuery/productwealthQuery?codeType='+record.code;
			$("#pdAmountOrder_productId").combobox("clear");
			$("#pdAmountOrder_productId").combobox("reload",productUrl);
		}*/
	});
	
	//产品状态
	$("#hotPdOrder_productOrderStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=pdOrderStatus',
		valueField : 'code',
		textField : 'codeName'
	});
}


/**
 * 初始化产品信息列表
 */
function initHotProductOrderInfoTable(){
	var  selectIndex = -1;
	hotProductOrderInfoTable = $("#hotProductOrderInfoTable").datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
//		url : contextPath+"/productOrder/queryHotPdOrderInfoList",
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'agencyComId',	
		    	 title:'产品方',
		    	 width:200,
		    	 formatter : function(value, row, index) {
			    		return row.agencyComName;
			     }
		    	 
		     },
		     {
		    	 field:'agencyComName',	
		    	 title:'产品方',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.agencyComName;
			     }
		    	 
		     },
		     {
		    	 field:'productId',	
		    	 title:'产品Id',
		    	 width:200,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.productId;
		    	 }
		     },
		     {
		    	 field:'productName',	
		    	 title:'产品名称',
//		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.productName;
		    	 }
		     },
		     {
		    	 field:'remark',	
		    	 title:'产品备注',
		    	 width:150,
		    	 formatter : function(value, row, index) {
		    		return row.remark;
		    	 }
		     },
		     {
		    	 field:'productType',	
		    	 title:'产品类型',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.productType;
		    	 }
		     },
		     {
		    	 field:'productSubType',	
		    	 title:'产品子类型',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.productSubType;
		    	 }
		     },
		     {
		    	 field:'expectOpenDay',	
		    	 title:'开放日',			
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.expectOpenDay;
			     }
		     },
		     {
		    	 field:'investEndDate',		
		    	 title:'封账日',			
		    	 width:140,
		    	 formatter : function(value, row, index) {
			    		return row.investEndDate;
			     }
		     },
		     {
		    	 field:'foundDate',		
		    	 title:'成立日',		
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.foundDate;
			     }
		     },
		     {
		    	 field:'remainAmount',		
		    	 title:'剩余额度(元)',		
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.remainAmount;
			     }
		     },
		     {
		    	 field:'isInviteCode',		
		    	 title:'邀请码',		
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.isInviteCode;
			     }
		     },
		     {
		    	 field:'isInviteCodeName',		
		    	 title:'邀请码',		
		    	 //hidden:true,
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.isInviteCodeName;
			     }
		     },
		     {
		    	 field:'pdOrderStatus',		
		    	 title:'状态',		
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.pdOrderStatus;
			     }
		     },
		     {
		    	 field:'pdOrderStatusName',		
		    	 title:'状态',		
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.pdOrderStatusName;
			     }
		     },
		     {
		    	 field:'isHotCode',	
		    	 title:'是否是热门产品code',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.isHotCode;
			     }
		    	 
		     },
		     {
		    	 field:'isHot',	
		    	 title:'是否是热门产品',
		    	 width:200,
		    	 formatter : function(value, row, index) {
			    		return row.isHot;
			     }
		     },
		     {
		    	 field:'isDistribute',		
		    	 title:'是否已分配额度',		
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.isDistribute;
			     }
		     }]],
		     toolbar : [{
		    	 	text : '新增热门产品',
					iconCls : 'icon-add',
					handler : function() {
						var rows = hotProductOrderInfoTable.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示', "请选择一个产品进行操作!");
							return;
						}
						var param = {};
						var productId = rows[0].productId;
						param.productId = productId;
						$.ajax({
							type:'post',
							url:contextPath+"/productOrder/addHotProductInfo",
							data:'param='+encodeURI($.toJSON(param)),
							cache:false,
							success:function(reData){
								try {
									if(reData.success){
										$.messager.alert('提示', reData.msg);
										queryHotProductOrderList();
									}else{
										$.messager.alert('提示', reData.msg);
									}
								}catch (e) {
									$.messager.alert('提示', e);
								}
					 }});
				   }
				},
				{
		    	 	text : '取消热门产品',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = hotProductOrderInfoTable.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示', "请选择一个产品进行操作!");
							return;
						}
						var param = {};
						var productId = rows[0].productId;
						param.productId = productId;
						$.ajax({
							type:'post',
							url:contextPath+"/productOrder/cancelHotProductInfo",
							data:'param='+encodeURI($.toJSON(param)),
							cache:false,
							success:function(reData){
								try {
									if(reData.success){
										$.messager.alert('提示', reData.msg);
										queryHotProductOrderList();
									}else{
										$.messager.alert('提示', reData.msg);
									}
								}catch (e) {
									$.messager.alert('提示', e);
								}
					 }});
				   }
				}],
				onLoadSuccess : function() {
					$('#hotProductOrderInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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



function openHotPdOrderWindow(title,href){
	$('<div id="addHotPdOrderWindow"/>').dialog({
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


//清空
function clearQueryHotProductCondition(){
	$("#listHotProduct_queryConditionForm").form("clear");
}


//查询
function queryHotProductOrderList(){
	//设置Datagrid获取数据的url
	hotProductOrderInfoTable.datagrid('options').url = contextPath+"/productOrder/queryHotPdOrderInfoList";
	//获取页面的查询条件
	var queryParam = $("#listHotProduct_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	//加载datagrid数据
	hotProductOrderInfoTable.datagrid('load',{queryParam:queryParam});	
}

