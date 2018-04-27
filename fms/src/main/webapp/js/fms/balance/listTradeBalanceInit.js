jQuery(function($) {
	//初始化所有下拉框
	initAllCombobox();
	//初始化表格
	initTradeBalanceTable();
});


var tradeBalanceTable;
function initTradeBalanceTable(){
	var  selectIndex = -1;
	tradeBalanceTable = $('#TradeBalanceTable').datagrid({
		title : '结算交易信息', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'comName',
					title : '管理机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.comName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'storeName',
					title : '网点',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.storeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'departmentName',
					title : '部门',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.departmentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agentName',
					title : '财富顾问',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agencyComName',
					title : '基金管理人',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agencyComName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productName',
					title : '产品',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'tradeNo',
					title : '交易号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.tradeNo;
					}
				},{
					field : 'feeTotal',
					title : '交易金额',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.feeTotal;
					}
				}]],
		onLoadSuccess : function() {
			$('#TradeBalanceTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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



function initAllCombobox(){
	// 分公司：代码-名称
	$("#tradeComId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	
 // 网点：代码-名称
/*    $("#tradeStoreId").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#tradeComId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
 // 团队：代码-名称
    $("#tradeDepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#tradeComId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});	
    //财富顾问
	$("#tradeAgentId").combobox({
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
		/*	var storeId = $("#tradeStoreId").combobox("getValue");*/
			var departmentId = $("#tradeDepartmentId").combobox("getValue");
			/*param.storeId = storeId;*/
			param.departmentId = departmentId;
			if(/*(storeId==null||storeId==""||storeId==undefined)&&(*/departmentId==null||departmentId==""||departmentId==undefined){
				var comId = $("#tradeComId").combobox("getValue");
				if(comId==null||comId==""||comId==undefined){
					var url = contextPath+'/codeQuery/agentQuery';
				}else{
					var url = contextPath + '/codeQuery/defAgentQuery?codeType='+ comId;
				}
			}else{
				var url = contextPath + '/codeQuery/limitAgentInfo?param='+ encodeURI($.toJSON(param));
			}
			$(this).combobox("clear");
			$(this).combobox("reload",url);
		}
	});
	//合作机构
	$('#agencyComId').combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	//产品
	$('#productId').combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	 });
	//产品类型
	$('#productType').combobox({
		url : contextPath+ '/codeQuery/tdCodeQuery?codeType=productType',
		valueField : 'code',
		textField : 'codeName',
		required : true,
		onChange:function(){
			var productType = $(this).combobox("getValue");
			var productSubType = $('#productSubType').combobox("getValue");
			var subType = null;
			var required = true;
			var disabled = true;
			if(productType=="1"){
				subType = "wealthProSubType";
				required = true;
				disabled = false;
			}else{
				subType = "insProSubType";
				required = false;
				disabled = true;
			}
			var url = contextPath+ '/codeQuery/tdCodeQuery?codeType='+ subType;
			$('#productSubType').combobox({
				url : url,
				valueField : 'code',
				textField : 'codeName',
				required :required,
				disabled :disabled
			});
			
			var productUrl = contextPath + '/codeQuery/productQueryByType?productType='+productType+'&productSubType='+productSubType;
			$('#productId').combobox({
				url : productUrl,
				//productQueryByType
				valueField : 'code',
				textField : 'codeName'
			 });
		}
	 });
	//产品子类
	$('#productSubType').combobox({
		//url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName',
		onChange:function(){
			var productType = $('#productType').combobox("getValue");
			var productSubType = $(this).combobox("getValue");;
			var productUrl = contextPath + '/codeQuery/productQueryByType?productType='+productType+'&productSubType='+productSubType;
			$('#productId').combobox({
				url : productUrl,
				//productQueryByType
				valueField : 'code',
				textField : 'codeName'
			});
		}
	 });
	
	$("#startDate").datebox({
		editable:false,
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var startDateValue = y+"-"+m+"-"+"01";
			return formatDateStr(startDateValue);
		}
	});
	
	$("#endDate").datebox({
		editable:false,
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var currentMonthLastDay = getMonthLastDay(y,m);
			var endDateValue = y+"-"+m+"-"+currentMonthLastDay;
			return formatDateStr(endDateValue);
		}
	});

}

function queryBalanceTradeInfoList(){
	if(!$("#TradeBalanceQueryConditionForm").form("validate")){
		$.messager.alert('提示', "请填写必录的查询条件");
		return;
	}
	var startDate = $("#startDate").datebox("getValue");
	var endDate = $("#endDate").datebox("getValue");
	if(!compareDate(startDate,endDate)){
		$.messager.alert('提示', "统计起期不能晚于统计止期");
		return;
	}
	
	tradeBalanceTable.datagrid('options').url = "balance/queryTradeBalanceList";
	var queryParam = $("#TradeBalanceQueryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	tradeBalanceTable.datagrid('load',{queryParam:queryParam});
}


function tradeBalanceAndExport(){
	if(!$("#TradeBalanceQueryConditionForm").form("validate")){
		$.messager.alert('提示', "请填写必录的导出条件");
		return;
	}
	var startDate = $("#startDate").datebox("getValue");
	var endDate = $("#endDate").datebox("getValue");
	if(!compareDate(startDate,endDate)){
		$.messager.alert('提示', "统计起期不能晚于统计止期");
		return;
	}
	
	var balanceParam = $("#TradeBalanceQueryConditionForm").serialize();//tradeBalance.xls
	balanceParam = formDataToJsonStr(balanceParam);
	//console.info($.toJSON(balanceParam).length);
	var param = {};
	param.balanceParam = balanceParam;
	window.open(contextPath+'/balance/tradeBalance.xls?param='+$.toJSON(param));
	
	/*$.ajax({
		type:'post',
		url:contextPath+"/balance/tradeBalanceAndExport",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});*/
}

function excelTest(){
	window.open(contextPath+'/viewController/exportExcel.xls?param=1');
	
	
	/*$.ajax({
		type:'post',
		url:contextPath+"/viewController/viewExcel",
		//data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				window.open('测试');
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});*/
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#TradeBalanceQueryConditionForm').form('clear');
}