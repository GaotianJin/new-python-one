var yearData =[{code: '2015',codeName: '2015'},{code: '2016',codeName: '2016'},{code: '2017',codeName: '2017'},
		       {code: '2018',codeName: '2018'},{code: '2019',codeName: '2019'},{code: '2020',codeName: '2020'},
		       {code: '2021',codeName: '2021'},{code: '2022',codeName: '2022'},{code: '2023',codeName: '2023'},
		       {code: '2024',codeName: '2024'},{code: '2025',codeName: '2025'},{code: '2026',codeName: '2026'},
		       {code: '2027',codeName: '2027'},{code: '2028',codeName: '2028'},{code: '2029',codeName: '2029'},
		       {code: '2030',codeName: '2030'}
		      ];
var monthData =[{code: '01',codeName: '01'},{code: '02',codeName: '02'},{code: '03',codeName: '03'},
		       {code: '04',codeName: '04'},{code: '05',codeName: '05'},{code: '06',codeName: '06'},
		       {code: '07',codeName: '07'},{code: '08',codeName: '08'},{code: '09',codeName: '09'},
		       {code: '10',codeName: '10'},{code: '11',codeName: '11'},{code: '12',codeName: '12'}
		      ]
jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initStockProductIncomeTable();
});

/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	//初始化产品 联动合作机构
	$("#stockProductIncome_ProductId").combobox({
		valueField : 'code',
		textField : 'codeName',
		onShowPanel : function(){
			var agencyComId = $("#stockProductIncome_ComId").combobox("getValue");
			if(agencyComId==null||agencyComId==""||agencyComId==undefined){
				var url = contextPath + '/codeQuery/wealthproductQuery';
			}else{
				var url = contextPath + '/codeQuery/productwealthQuery?codeType='+ agencyComId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});
	//初始化机构
	$("#stockProductIncome_ComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	
	//初始化年月
	$("#stockProductIncome_year").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: yearData
	});
	$("#stockProductIncome_month").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: monthData
	});
}

var stockProductIncomeTable;
function initStockProductIncomeTable() {
	var  selectIndex = -1;
	stockProductIncomeTable = $("#stockProductIncomeTable")
			.datagrid(
					{
						title : '股权产品清单', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/getStockProductIncomeUrl", // 数据来源
						sortName : 'id', // 排序的列
						sortOrder : 'desc', // 倒序
						remoteSort : true, // 服务器端排序
						idField : 'id', // 主键字段
						queryParams : {}, // 查询条件
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageSize : 10,
						pageList : [ 5, 10, 15, 20, 25, 30 ],
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, 
						 {
							field : 'productId',
							title : '产品Id',
							hidden : true,
							width : 180
						}, 
						 {
							field : 'productCode',
							title : '产品代码',
							width : 180
						}, {
							field : 'productTypeCode',
							title : '产品类型Code',
							width : 100,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productTypeCode;
							}
						}, {
							field : 'productSubTypeCode',
							title : '产品子类Code',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productSubTypeCode;
							}
						},  {
							field : 'productName',
							title : '产品名称',
							width : 360,
							formatter : function(value, row, index) {
								var param = {};
								param.productId = row.productId;
								param.distributeDate = row.distributeDate;
								return  "<a href='#' onclick=addproducttab('查看详情','"+contextPath
									+"/product/detailStockProIncomeUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
							}
						},
						{
							field : 'foundDate',
							title : '成立日期',
							width : 180
						}, {
							field : 'endDate',
							title : '到期时间',
							width : 180
						} , {
							field : 'financingScale',
							title : '成立规模',
							width : 180
						} ,  {
							field : 'distributeDate',
							title : '预计付息时间',
							width : 180
						} , {
							field : 'distributeAmount',
							title : '付息金额',
							width : 180
						}]],
						toolbar:[{
							text : '导出总表',
							iconCls : 'icon-redo',
							handler : function() {
								var queryParam = {};
								var comId = $("#stockProductIncome_ComId").combobox("getValue");
								var productId = $("#stockProductIncome_ProductId").combobox("getValue");
								queryParam.comId = comId;
								queryParam.productId = productId;
								var incomeYear = $("#stockProductIncome_year").combobox("getValue");
					             var incomeMonth = $("#stockProductIncome_month").combobox("getValue");
					             var incomeDate = incomeYear + '-' + incomeMonth;
				            	 queryParam.month = incomeDate;
					             if(incomeYear==""||incomeMonth==""){
					            	   var incomeDate = "";
						            	 queryParam.month = incomeDate;
					             }
						        window.open(contextPath
								+ '/product/stockProductIncomeDetail.xls?queryParam='
								+ $.toJSON(queryParam));
							}
						}, '-',
						{ 
							text : '下载详情', 
							iconCls : 'icon-redo',
							handler : function() { 
								var rows = $('#stockProductIncomeTable').datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一条信息进行数据下载","info");
							return;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","只能选选择一条信息进行数据下载","info");
							return;
						}
								var queryParam = {};
								queryParam.productId = rows[0].productId;
								queryParam.distributeDate = rows[0].distributeDate;
								queryParam = $.toJSON(queryParam)
								window.open(contextPath+ '/product/stockProductDetail.xls?queryParam='+encodeURI(queryParam));
						    } 
						}, '-',
						{ 
							text : '生成分配短信', 
							iconCls : 'icon-add',
							handler : function() { 
								var rows = $('#stockProductIncomeTable').datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一条信息进行短信生成","info");
							return;
						}
								var queryParam = {};
								queryParam.productId = rows[0].productId;
								queryParam.distributeDate = rows[0].distributeDate;
								$.ajax({ 
									// ajax请求
									type:'post',
									url:'product/createStockDisSms',
									data:'param='+ $.toJSON(queryParam),
									cache:false,
									success:function(returnData){
											if(returnData.success){
												$.messager.alert('提示', returnData.msg);
											}else{
												$.messager.alert('提示', returnData.msg);
											}
									}
								});
						    } 
						}, '-',
						{ 
							text : '生成分配到账短信', 
							iconCls : 'icon-add',
							handler : function() { 
								var rows = $('#stockProductIncomeTable').datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一条信息进行短信生成","info");
							return;
						}
								var queryParam = {};
								queryParam.productId = rows[0].productId;
								queryParam.distributeDate = rows[0].distributeDate;
								$.ajax({ 
									// ajax请求
									type:'post',
									url:'product/createStockToAccSms',
									data:'param='+ $.toJSON(queryParam),
									cache:false,
									success:function(returnData){
											if(returnData.success){
												$.messager.alert('提示', returnData.msg);
											}else{
												$.messager.alert('提示', returnData.msg);
											}
									}
								});
						    } 
						}],
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


// 表格查询
function searchStockProductIncomeList() {
	stockProductIncomeTable.datagrid('options').url = contextPath+"/product/getStockProductIncomeUrl";
	var queryParam = {};
	var comId = $("#stockProductIncome_ComId").combobox("getValue");
	var productId = $("#stockProductIncome_ProductId").combobox("getValue");
	queryParam.comId = comId;
	queryParam.productId = productId;
	var incomeYear = $("#stockProductIncome_year").combobox("getValue");
     var incomeMonth = $("#stockProductIncome_month").combobox("getValue");
     var incomeDate = incomeYear + '-' + incomeMonth;
	 queryParam.month = incomeDate;
     if(incomeYear==""||incomeMonth==""){
    	   var incomeDate = "";
        	 queryParam.month = incomeDate;
     }
     queryParam = $.toJSON(queryParam);
	stockProductIncomeTable.datagrid('load',{param:queryParam});
}	
	
// 清空查询条件然后进行查询
function clearStockProductIncomeForm() {
	$('#stockProductIncomeForm').form('clear');
	$('#stockProductIncomeTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	parent.searchProductIncomeList();
}

function addproducttab(title, href) {
	$('<div id="addWindow"/>').window({
		href : href,
		modal : true,
		title : title,
		width : 1200,
		height : 500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}

