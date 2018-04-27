var productAmountOrderInfoTable;

jQuery(function($) {
	initAllCombobox();
	initProductAmountOrderInfoTable();
	queryProductAmountOrderList();
});

function initAllCombobox(){
	$("#pdAmountOrder_productId").combobox({
		//url : contextPath + '/codeQuery/productwealthQuery?codeType='+"",
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
	
	$("#pdAmountOrder_agencyComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
			
		/*onSelect:function(record){
			var productUrl = contextPath + '/codeQuery/productwealthQuery?codeType='+record.code;
			$("#pdAmountOrder_productId").combobox("clear");
			$("#pdAmountOrder_productId").combobox("reload",productUrl);
		}*/
	});
	
	$("#pdAmountOrder_productOrderStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=pdOrderStatus',
		valueField : 'code',
		textField : 'codeName'
	});
}

/**
 * 初始化产品信息列表
 */
function initProductAmountOrderInfoTable(){
	var  selectIndex = -1;
	productAmountOrderInfoTable = $("#productAmountOrderInfoTable").datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		//url:contextPath+'/sales/queryAgentList',
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
		    	 title:'产品名称',
		    	 width:200,
		    	 formatter : function(value, row, index) {
		    		return row.productName;
		    	 }
		     },
		     {
		    	 field:'productName',	
		    	 title:'产品名称',
		    	 hidden:true,
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
		    	 field:'investEndDate',		
		    	 title:'封账日',			
		    	 width:140,
		    	 formatter : function(value, row, index) {
			    		return row.investEndDate;
			     }
		     },{
		    	 field:'expectOpenDay',	
		    	 title:'开放日',			
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.expectOpenDay;
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
		    /* {
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
		     },*/
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
		    	 field:'isDistribute',		
		    	 title:'是否已分配额度',		
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.isDistribute;
			     }
		     }]],
		     toolbar : [{
					text : '预约',
					iconCls : 'icon-add',
					handler : function() {
						var rows = productAmountOrderInfoTable.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert('提示', "请选择一个产品进行预约");
							return;
						}
						var pdOrderStatus = rows[0].pdOrderStatus;
						var pdOrderStatusName = rows[0].pdOrderStatusName;
						if (pdOrderStatus!="02") {
							$.messager.alert('提示', "产品状态为("+pdOrderStatusName+"),不能进行预约！");
							return;
						}
						var param = {};
						param.operate = "addCustOrder";
						param.comId = rows[0].comId;
						param.productId = rows[0].productId;
						param.expectOpenDay = rows[0].expectOpenDay;
						param.foundDate = rows[0].foundDate;
						param.sealingAccDate = rows[0].investEndDate;
						param.isInviteCode = rows[0].isInviteCode;
						param.productType = rows[0].productType;
						param.productSubType = rows[0].productSubType;
						param.isDistribute = rows[0].isDistribute;
						param.remainAmount = rows[0].remainAmount;
						param = $.toJSON(param);
						openAddPdAmountOrderWindow('新增产品预约', contextPath+"/productOrder/addProductAmountOrder?param="+param);
					}
				}],
				onLoadSuccess : function() {
					$('#productAmountOrderInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function openAddPdAmountOrderWindow(title,href){
	$('<div id="addPdAmountOrderWindow"/>').dialog({
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


function clearQueryProductCondition(){
	$("#listProduct_queryConditionForm").form("clear");
}


function queryProductAmountOrderList(){
	//alert("开始查询……");
	//设置Datagrid获取数据的url
	productAmountOrderInfoTable.datagrid('options').url = contextPath+"/productOrder/queryComPdAmountOrderInfoList";
	//获取页面的查询条件
	var queryParam = $("#listProduct_queryConditionForm").serialize();
	//console.info("======queryParam======")
	//console.info(queryParam);
	//将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	//console.info("======queryParam转换后======")
	//console.info(queryParam);
	//加载datagrid数据
	productAmountOrderInfoTable.datagrid('load',{queryParam:queryParam});	
}
