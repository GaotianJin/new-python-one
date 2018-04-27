jQuery(function($) {
	initAllCombobox(); // 页面控件初始化函数
	initCustProductAmountOrderInfoTable(); // 初始化产品信息列表
	pdOrderAuditGetTotalAmount();
});

function initAllCombobox() {
	$('#pdAmountOrderAuditAgencyComId').combobox({ // “合作机构”控件初始化
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	
	$('#pdAmountOrderAuditProductId').combobox({  // “产品名称”控件初始化
		//url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName',
		
		onShowPanel : function(){
			var agencyComId = $("#pdAmountOrderAuditAgencyComId").combobox("getValue");
			if(agencyComId==null||agencyComId==""||agencyComId==undefined){
				var url = contextPath + '/codeQuery/wealthproductQuery';
			}else{
				var url = contextPath + '/codeQuery/productwealthQuery?codeType='+ agencyComId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});
	
	$('#pdAmountOrderAuditOrderStatus').combobox({  // “预约状态”控件初始化
		    url: 'codeQuery/tdCodeQueryOrderStatus?codeType=custOrderStatus',
			valueField: 'code',
			textField: 'codeName'
		});
	// 分公司：代码-名称
	$("#pdAmountOrderAuditSubCompany").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	 //财富顾问
	$('#pdAmountOrderAuditAgentName').combobox({
		url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var comId = $("#pdAmountOrderAuditSubCompany").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath+'/codeQuery/agentQuery';
			}else{
				var url = contextPath + '/codeQuery/defAgentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload",url);
		}
	});
}

var custProductAmountOrderAuditInfoTableTempVar;
function initCustProductAmountOrderInfoTable(){
	var  selectIndex = -1;
	custProductAmountOrderAuditInfoTableTempVar = $("#custProductAmountOrderAuditInfoTable").datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		idField : 'pdAmountOrderInfoId', // 主键字段
	    url:contextPath+'/productOrder/getAllPdVerificationInfo',
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'tradeInfoId',	
		    	 title:'交易号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.tradeInfoId;
			     }
		    	 
		     },
		     {
		    	 field:'pdAmountOrderInfoId',	
		    	 title:'客户预约流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.pdAmountOrderInfoId;
			     }
		    	 
		     },
		     {
		    	 field:'agencyComId',	
		    	 title:'产品方',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.agencyComId;
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
		    	 title:'产品流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.productId;
		    	 }
		     },
		     {
		    	 field:'productName',	
		    	 title:'产品名称',
		    	 width:200,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
		    		return row.productName;
		    	 }
		     },{
		    	 field:'remark',	
		    	 title:'产品备注',
		    	 width:150,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
		    		return row.remark;
		    	 }
		     },
		     {
		    	 field:'expectOpenDay',	
		    	 title:'开放日',
		    	 width:70,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
		    		return row.expectOpenDay;
		    	 }
		     },
		     { 
		    	 field:'comId',	
		    	 title:'分公司',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.comId;
		    	 }
		     },
		     { 
		    	 field:'comName',	
		    	 title:'分公司',
		    	 width:120,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
		    		return row.comName;
		    	 }
		     },
		     { 
		    	 field:'storeName',	
		    	 title:'网点',
		    	 width:100,
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    		return row.storeName;
		    	 }
		     },
		     {
		    	 field:'agentName',	
		    	 title:'财富顾问',			
		    	 width:70,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
			    		return row.agentName;
			     }
		     },
		     {
		    	 field:'custName',	
		    	 title:'客户姓名',			
		    	 width:70,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
			    		return row.custName;
			     }
		     },
		     {
		    	 field:'orderAmount',		
		    	 title:'预约额度',			
		    	 width:100,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
			    		return row.orderAmount;
			     }
		     },
		     {
		    	 field:'orderDate',		
		    	 title:'预约时间',		
		    	 width:120,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
			    		return row.orderDate;
			     }
		     }
		     ,
		     /*{
					field : 'planTransferDate',
					title : '拟打款时间',
					hidden:true,
					sortable : true,
					width : 100,
					formatter : function(value, row, index) {
						return row.planTransferDate;
					}
				}, */
				{
			    	 field:'earnestAuditDate',		
			    	 title:'定金到账时间',		
			    	 width:120,
			    	 sortable : true,
			    	 formatter : function(value, row, index) {
				    		return row.earnestAuditDate;
				     }
			     },
		     {
		    	 field:'auditDate',		
		    	 title:'全款到账时间',		
		    	 width:120,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
			    		return row.auditDate;
			     }
		     },
		     {
		    	 field:'orderStatus',		
		    	 title:'预约状态',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
			    		return row.orderStatus;
			     }
		     },
		     {
		    	 field:'orderStatusName',		
		    	 title:'预约状态',	
		    	 width:100,
		    	 sortable : true,
		    	 formatter : function(value, row, index) {
			    		return row.orderStatusName;
			     }
		     },
				{
					field : 'productType',
					title : '产品类型',
					hidden : true,
					// sortable : true,
					formatter : function(value, row, index) {
						return row.productType;
					}
				},
				{
					field : 'productSubType',
					title : '产品子类型',
					hidden : true,
					// sortable : true,
					formatter : function(value, row, index) {
						return row.productSubType;
					}
				},{
					field : 'inviteCode',
					title : '邀请码',
					hidden:true,
					sortable : true,
					width : 100,
					formatter : function(value, row, index) {
						return row.inviteCode;
					}
				},{
					field : 'foundDate',
					title : '成立日',
					hidden : true,
					formatter : function(value, row, index) {
						return row.foundDate;
					}
				},
				{
					field : 'investEndDate',
					title : '封账日',
					hidden : true,
					formatter : function(value, row, index) {
						return row.investEndDate;
					}
				},{
					field : 'isDistribute',
					title : '是否已分配额度',
					hidden : true,
					formatter : function(value, row, index) {
						return row.isDistribute;
					}
				},{
					field : 'tradeStaus',
					title : '交易状态',
					hidden : true,
					formatter : function(value, row, index) {
						return row.tradeStaus;
					}
				},
				{
					field : 'Detail',
					title : '预约明细',
					width : 80,
					sortable : true,
					formatter : function(value, row, index) {
						var param = {};
						param.operate = "detailCustOrder";
						param.pdAmountOrderInfoId = row.pdAmountOrderInfoId;
						param.comId = row.comId;
						param.productId = row.productId;
						param.expectOpenDay = $.trim(row.expectOpenDay);
						param.foundDate = $.trim(row.foundDate);
						param.sealingAccDate = $.trim(row.investEndDate);
						param.productType = row.productType;
						param.productSubType = row.productSubType;
						var inviteCode = row.inviteCode;
						// 01-需要邀请码，02-不需要邀请码
						var isInviteCode = "01";
						if (inviteCode == null
								|| inviteCode == ""
								|| inviteCode == undefined) {
							isInviteCode = "02";
						}
						param.isInviteCode = isInviteCode;
						param.isDistribute = row.isDistribute;
						param = $.toJSON(param);
						//alert(param);

						return "<a href='#'  onclick=detailWindow('客户预约明细','"
								+ contextPath
								+ "/productOrder/addProductAmountOrder?param="
								+ param + "')>预约明细查看</a>";
					}
				},]],
		    /*toolbar:[{
						text : '打款审核',
						iconCls : 'icon-tick',
						handler : function() {
							auditPdAmountOrderAudit();
						}
					}, '-',
					{
						text : '撤销申请',
						iconCls : 'icon-undo',
						handler : function() {
							cancelPdAmountOrderAudit();
						}
					}],*/
		    toolbar:"#custProductAmountOrderAuditInfoTable_tb",
			onLoadSuccess : function() {
				$("#custProductAmountOrderAuditInfoTable").datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function clearQueryPdAmountOrderAuditCondition() { // 点击Form中按钮对应函数
	$("#PdAmountOrderAuditQueryConditionForm").form("clear");
}


function queryProductdAmountOrderInfoList(){// 点击“查询”按钮时触发该函数,重新查询数据库并加载数据到"数据表格"
//	custProductAmountOrderAuditInfoTableTempVar.datagrid('options').url = contextPath + "/productOrder/getAllPdVerificationInfo"; // 设置数据表格URL属性
	var queryParam = $('#PdAmountOrderAuditQueryConditionForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	custProductAmountOrderAuditInfoTableTempVar.datagrid('load',{queryParam:queryParam});// 加载数据表格数据
	pdOrderAuditGetTotalAmount();
}
/*
 * 导出信息
 */
function exportProductdAmountOrderInfoList(){
	var queryParam = $('#PdAmountOrderAuditQueryConditionForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
//	queryParam =  $.toJSON(queryParam);
	/*var queryParam={};
	queryParam.custName="周田";
	console.info(queryParam);*/
//	window.open(contextPath+'/productOrder/productOrderDetail.xls);
	window.open(contextPath+'/productOrder/productOrderDetail.xls?queryParam='+queryParam);

	
	
}

/*
 * 修改预约信息
 */

function updatePdAmountOrderAudit(){
		var rows = $("#custProductAmountOrderAuditInfoTable").datagrid("getSelections");
		if (rows.length == 0) { // 用户在数据表格中无选择
			$.messager.alert('提示', "请选择需要修改的交易！");
			return;
		}
		var row = rows[0];
		var orderStatus = row.orderStatus;
		var tradeStaus = row.tradeStaus;
		//存在全款已到账预约记录 仍要修改预约金额
		/*if(tradeStaus != null && tradeStaus != "" && tradeStaus != undefined && (tradeStaus == "02" || tradeStaus == "10")){
			$.messager.alert('提示', "已复核或成立的交易，不能修改！");
			return;
		}
		if(orderStatus != null && orderStatus != "" && orderStatus != undefined && orderStatus == "02"){
			$.messager.alert('提示', "该交易已打款，不能修改！");
			return;
		}*/
		if(orderStatus != null && orderStatus != "" && orderStatus != undefined && orderStatus == "03"){
			$.messager.alert('提示', "该交易已失效，不能修改！");
			return;
		}
		var param = {};
		param.operate = "updateCustOrder";
		param.pdAmountOrderInfoId = row.pdAmountOrderInfoId;
		param.comId = row.comId;
		param.productId = row.productId;
		param.tradeInfoId = row.tradeInfoId;
		param.expectOpenDay = $.trim(row.expectOpenDay);
		param.foundDate = $.trim(row.foundDate);
		param.sealingAccDate = $.trim(row.investEndDate);
		param.productType = row.productType;
		param.productSubType = row.productSubType;
		var inviteCode = row.inviteCode;
		// 01-需要邀请码，02-不需要邀请码
		var isInviteCode = "01";
		if (inviteCode == null
				|| inviteCode == ""
				|| inviteCode == undefined) {
			isInviteCode = "02";
		}
		param.isInviteCode = isInviteCode;
		param.isDistribute = row.isDistribute;
		param = $.toJSON(param);
		detailWindow('修改客户预约信息',contextPath+"/productOrder/addProductAmountOrder?param="+param);
}


/**
 * 产品经理撤销产品预约信息
 **/
function cancelPdAmountOrderAudit(){
	var pdAmountOrderAuditInfoData = $("#custProductAmountOrderAuditInfoTable").datagrid("getSelections");
	if (pdAmountOrderAuditInfoData.length == 0) { // 用户在数据表格中无选择
		$.messager.alert('提示', "请选择需要撤销的交易！");
		return;
	}else{
		   $.messager.confirm('提示', '是否确认撤销该笔交易?', function(r) {
			   if(r){
				   $.ajax({ // ajax请求
					   type:'post',
					   url:'productOrder/cancelPdAmountOrderAudit',
					   data:'param='+encodeURI($.toJSON(pdAmountOrderAuditInfoData)),
					   cache:false,
					   success:function(returnData){
						   try {
							   if(returnData.success){
								   $.messager.alert('提示', returnData.msg);
								   $("#custProductAmountOrderAuditInfoTable").datagrid("reload");
							   }else{
								   $.messager.alert('提示', returnData.msg);
							   }
						   } catch (e) {
							$.messager.alert('提示', e);
						  }
					   }
				   });
			   }
		});
	}
}

/**
 * 产品经理审核产品预约信息
 * 
 **/
function auditPdAmountOrderAudit(){
	var pdAmountOrderAuditInfoData = $("#custProductAmountOrderAuditInfoTable").datagrid("getSelections");
	if (pdAmountOrderAuditInfoData.length == 0) { // 用户在数据表格中无选择
		$.messager.alert('提示', "请选择需要审核的交易！");
		return;
	}
	/*if(!confirm("是否确认审核该笔交易?")){ // 提示用户是否确认审核
		return;
	}*/
	//pdAmountOrderAuditInfoData = pdAmountOrderAuditInfoData[0];
	else{
		$.messager.confirm('提示', "是否确认全款打款审核?", function(r) {
			if(r){
				//pdAmountOrderAuditInfoData = pdAmountOrderAuditInfoData[0];
				
				$.ajax({ // ajax请求
					type:'post',
					url:'productOrder/auditPdAmountOrderAudit',
					data:'param='+encodeURI($.toJSON(pdAmountOrderAuditInfoData)),
					cache:false,
					success:function(returnData){
						try {
							if(returnData.success){
								$.messager.alert('提示', returnData.msg);
								$("#custProductAmountOrderAuditInfoTable").datagrid("reload");
							}else{
								$.messager.alert('提示', returnData.msg);
							}
						} catch (e) {
							$.messager.alert('提示', e);
						}
					}
				});
			}
		});
	}
	
}


/**
 * 产品预约查询获取预约总额
 * 
 * */
function pdOrderAuditGetTotalAmount(){
	var queryParam = $("#PdAmountOrderAuditQueryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'productOrder/getPdOrderTotalAmount',
		data:'queryParam='+queryParam+'&operate=pdOrderAudit',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#auditOrderTotal").html(returnData.obj);
						var auditOrderTotal = document.getElementById('auditOrderTotal');
						//console.info(totalLabel);
						auditOrderTotal.innerHTML = returnData.obj;
					}
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

//查看预约详情窗口
function detailWindow(title, href) {

	$('<div id="addPdAmountOrderWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		width : 1200,
		height : 600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}



function amountPdAmountOrderAudit(){
	amountAudit('批量审核', contextPath + "/productOrder/importPDAmountOrderUrl");
}

function amountAudit(title, href) {
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


/**
 * 定金打款审核
 * 
 **/
function earnestAudit(){
	var pdAmountOrderAuditInfoData = $("#custProductAmountOrderAuditInfoTable").datagrid("getSelections");
	if (pdAmountOrderAuditInfoData.length == 0) { // 用户在数据表格中无选择
		$.messager.alert('提示', "请选择需要审核的交易！");
		return;
	}else{
		$.messager.confirm('提示', "是否确认定金打款审核?", function(r) {
			if(r){
				$.ajax({ // ajax请求
					type:'post',
					url:'productOrder/earnestAudit',
					data:'param='+$.toJSON(pdAmountOrderAuditInfoData),
					cache:false,
					success:function(returnData){
						try {
							if(returnData.success){
								$.messager.alert('提示', returnData.msg);
								$("#custProductAmountOrderAuditInfoTable").datagrid("reload");
							}else{
								$.messager.alert('提示', returnData.msg);
							}
						} catch (e) {
							$.messager.alert('提示', e);
						}
					}
				});
			}
		});
	}
	
}
