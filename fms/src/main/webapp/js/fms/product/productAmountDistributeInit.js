jQuery(function($) {
	initAllCombobox();
	initProductAmountDisInfoTable();
});

function initAllCombobox(){
	$("#productAmountDis_productId").combobox({
		url : contextPath + '/codeQuery/productwealthQuery?codeType='+"",
		valueField : 'code',
		textField : 'codeName'
	});
	
	$("#productAmountDis_agencyComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName',
		onSelect : function(record){
			var productUrl = contextPath + '/codeQuery/productwealthQuery?codeType='+record.code;
			$("#productAmountDis_productId").combobox("clear");
			$("#productAmountDis_productId").combobox("reload",productUrl);
		}
	});
}

var productAmountDisInfoTable;
function initProductAmountDisInfoTable(){
	var  selectIndex = -1;
	productAmountDisInfoTable = $('#productAmountDisInfoTable').datagrid({
		title : '产品信息列表', // 标题
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		//url:contextPath+'/sales/queryAgentList',
		url:contextPath+"/product/getAllPdAmountDisInfo",
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'agencyComId',	
		    	 title:'产品方',
		    	 width:200,
		    	 //hidden:true
		    	 formatter : function(value, row, index) {
			    		return row.agencyName;
			     }
		    	 
		     },
		     {
		    	 field:'agencyName',	
		    	 title:'产品方',
		    	 hidden:true,//如果为true则隐藏列
		    	 //hidden:true
		    	 formatter : function(value, row, index) {
			    		return row.agencyName;
			     }
		    	 
		     },
		     {
		    	 field:'productId',	
		    	 title:'产品名称',
		    	 width:200,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.productId;
		    	 }
		     },
		     {
		    	 field:'productName',	
		    	 title:'产品名称',
		    	 formatter : function(value, row, index) {
		    		return row.productName;
		    	 }
		     },
		     {
		    	 field:'productType',	
		    	 title:'产品类别',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.productType;
		    	 }
		     },
		     {
		    	 field:'productSubType',	
		    	 title:'产品子类',
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
		    	 width:100,
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
		    	 field:'financingSacle',		
		    	 title:'融资规模',		
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.financingSacle;
			     }
		     },
		     {
		    	 field:'remainAmount',		
		    	 title:'剩余额度',		
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.remainAmount;
			     }
		     },
		    /* {
		    	 field:'isInviteCode',		
		    	 title:'是否生成邀请码',		
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.isInviteCode;
			     }
		     },*/
		     {
		    	 field:'pdOrderStatus',		
		    	 title:'状态',		
		    	 width:100,
		    	 formatter : function(value, row, index) {
			    		return row.pdOrderStatus;
			     }
		     },
		     {
					field : 'Detail',
					title : '分配明细',
					width : 80,
					formatter : function(value, row, index) {
						var param={};
						param.operate="detailPdAmountDis";
						
						param.productId = row.productId;
						param.agencyComId = row.agencyComId;
						param.expectOpenDay = row.expectOpenDay;
						param.productType = row.productType;
						param.productSubType = row.productSubType;
						
						param = $.toJSON(param);
						return "<a href='#'  onclick=openProductAmountDisWindow('额度分配明细','"+contextPath+"/product/addProductAmountDis?param="+param+"')>分配明细查看</a>";

					}
				},
		     ]],
		     toolbar : [{
					text : '额度分配',
					iconCls : 'icon-add',
					handler : function() {
						var param = {};
						param.operate = "addPdAmountDis";
						param = $.toJSON(param);
						openProductAmountDisWindow('新增产品额度分配', contextPath+"/product/addProductAmountDis?param="+param);
					}
				}, '-', {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = productAmountDisInfoTable.datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择一条记录进行修改");
							return;
						}
						var productId = rows[0].productId;
						var agencyComId = rows[0].agencyComId;
						var expectOpenDay = rows[0].expectOpenDay;
						var productType = rows[0].productType;
						var productSubType = rows[0].productSubType;
						var param = {};
						param.operate = "modifyPdAmountDis";
						param.productId = productId;
						param.agencyComId = agencyComId;
						param.expectOpenDay = expectOpenDay;
						param.productType = productType;
						param.productSubType = productSubType;
						param = $.toJSON(param);
					
						openProductAmountDisWindow('产品额度分配修改', contextPath+"/product/addProductAmountDis?param="+param);
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var rows = productAmountDisInfoTable.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个产品额度分配信息进行删除");
							return;
						}
						$.messager.confirm('提示信息', '您确定要删产品额度分配信息吗？', function(r){
							if (r){
								var param = {};
								param.productId = rows[0].productId;
								param.expectOpenDay = rows[0].expectOpenDay;
								deleteProductAmountDisInfo(param);
							}
						});
					}
				}],
			onLoadSuccess : function() {
				$('#companyAmountInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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



function openProductAmountDisWindow(title,href){
	$('<div id="productAmountDisWindow"/>').dialog({
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
 * 删除产品额度分配信息
 * 
 */
function deleteProductAmountDisInfo(param){
	$.ajax({
		type : 'post',
		url : contextPath + "/product/deleteProductAmountDisInfo",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
				if (resultInfo.success) {
					$.messager.alert('提示', '删除成功！', 'info');
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
		}
	});
}


function clearQueryCondition(){
	$("#productAmountDisInfo_queryConditionForm").form("clear");
}


function queryProductAmountDisInfoList(){
	//alert("开始查询……");
	//设置Datagrid获取数据的url
	productAmountDisInfoTable.datagrid('options').url = contextPath+"/product/getAllPdAmountDisInfo";
	//获取页面的查询条件
	var queryParam = $("#productAmountDisInfo_queryConditionForm").serialize();
	//console.info("======queryParam======")
	//console.info(queryParam);
	//将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	//console.info("======queryParam转换后======")
	//console.info(queryParam);
	//加载datagrid数据
	productAmountDisInfoTable.datagrid('load',{queryParam:queryParam});	
}

//查看分配详情窗口
function detailDistributeWindow(title, href){
	$('<div id="detailDistributeWindow"/>').dialog({
		href : href,
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
