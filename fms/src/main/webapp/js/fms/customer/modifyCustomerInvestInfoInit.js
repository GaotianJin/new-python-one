var modifyCustomerBaseInfo_Id = null;
var modifyCustInvest_agentId = null;
//var modifyCustInvest_tradeLoadFlag = null;
var customerNo=null;
var operate = null;
jQuery(function($){
	modifyCustomerBaseInfo_Id = $("#modifyCustomerBaseInfo_Id").val();
	modifyCustInvest_agentId = $("#modifyCustInvest_agentId").val();
	operate = $('#modifyCustomerBaseInfo_loadFlag').val();
	customerNo=$("#modifyCustomercustomerNo_Id").val();
	//tradeLoadFlag = $('#modifyCustInvest_tradeLoadFlag').val();
	//modifyCustomerOtherInvestTable();
	modifyCustomerMyInvestTable();
	modifyCustomerMyInvestTable02();
	modifyCustomerMyInvestTable03();
	modifyCustomerMyInvestTable04();
	if( operate!=null&&operate!=""&&operate!=undefined&&operate=="custDetail" ){
		$("#modifyCustomerInvestInfoInit_submitCarInfoButton").hide();
		//$("#modifyCustomerOtherInvestTable_tb").css("display", "none");
		$("#modifyCustomerOtherInvestTableAddOneRowId").hide();
		$("#modifyCustomerOtherInvestTableRemoveOneRowId").hide();
		$("#modifyCustomerOtherInvestTableLockOneRowId").hide();
	}
	/*if( tradeLoadFlag!=null&&tradeLoadFlag!=""&&tradeLoadFlag!=undefined&&tradeLoadFlag=="tradeLoadFlag" ){
		$("#modifyCustomerInvestInfoInit_backButton").hide();
	}*/
	getModifyCustomerInvestInfo();
	//getModifyCustomerInvestAmount();
	getModifyCustomerInvestInfo02();
	getModifyCustomerInvestInfo03();
	getModifyCustomerInvestInfo04();
	getModifyCustomerMyInvestAmount();
	getModifyCustomerMyInvestAmount02();
	getModifyCustomerMyRemainAmount();
	getModifyCustomerMyInvestAmount02();
	getModifyCustomerMyRemainAmount02();
	getModifyCustomerMyInvestShare();
	getModifyCustomerMyInvestShare02();
	getModifyCustomerMyRemainShare();
	getModifyCustomerMyRemainShare02();
})

var modifyCustomerOtherInvestTable;
function modifyCustomerOtherInvestTable(){
	modifyCustomerOtherInvestTable = $('#modifyCustomerOtherInvestTable').datagrid({
		//title : '其他投资记录',
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
			field : 'ck',
	         checkbox : true,
	         width : 2
		},
		{
			field : 'custHistoryInvestmentId',
			title : '客户其他公司投资记录Id',
			hidden : true,
			formatter : function(value,row,index){
				return row.custHistoryInvestmentId;
			}
		},
		{
        	field : 'investmentType',
        	title : '类型',
        	width : 100,
        	editor: {
				type:'combobox',
				options:{
					valueField:'code',
					textField:'codeName',
					required:true,//定义为必填字段
					editable:false,//定义用户是否可以直接输入文本到字段中
					url:contextPath+'/codeQuery/tdCodeQuery?codeType=investmentType',
					onSelect:function(){//在用户选择列表项的时候触发该事件
						//var investmentType = modifyCustomerOtherInvestTable.datagrid('getEditor', {index:modifyCustomerOtherInvestTableRowIndex,field:'investmentType'});
						//var modifyCustomerOtherInvestTableName = $(modifyCustomerOtherInvestTable.target).combobox('getText');
						var investmentTypeName = $(this).combobox('getText');
						modifyCustomerOtherInvestTable.datagrid('getRows')[modifyCustomerOtherInvestTableRowIndex]['investmentTypeName'] = investmentTypeName;
					}
				}
			},
        	formatter : function(value, row, index) {
        		return row.investmentTypeName;
        	}
        },
        {
			field : 'investmentTypeName',
			title : '类型名称',
			width : 50,
			sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.investmentTypeName;
			} // 需要formatter一下才能显示正确的数据
		},
        {
        	field : 'productName',
        	title : '产品名称',
        	width : 100,
        	editor: {
				type:'text',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.productName;
        	}
        },
        {
        	field : 'agencyName',
        	title : '产品方',
        	width : 100,
        	editor: {
				type:'text',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.agencyName;
        	}
        },
        {
        	field : 'productPurchaseCom',
        	title : '产品购买公司',
        	width : 100,
        	editor: {
				type:'text',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.productPurchaseCom;
        	}
        },
        {
        	field : 'investMentMoney',
        	title : '金额',
        	width : 100,
        	editor: {
				type: 'numberbox',
				options:{
										min: 0,
										precision: 2
									}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investMentMoney;
        	}
        },
        {
        	field : 'productDuration',
        	title : '产品期限',
        	width : 100,
        	editor: {
				type:'text',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.productDuration;
        	}
        }, {
        	field : 'endDate',
        	title : '到期日期',
        	width : 100,
        	editor: {
				type:'datebox',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.endDate;
        	}
        }, {
        	field : 'remark',
        	title : '备注',
        	width : 100,
        	editor: {
				type:'text',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remark;
        	}
        }, {
        	field : 'inputDate',
        	title : '录入日期',
        	width : 100,
        	editor: {
				type:'datetimebox',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.inputDate;
        	}
        }]],
		onLoadSuccess : function() {
			$('#modifyCustomerOtherInvestTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(Index){
			modifyCustomerOtherInvestTableEditOneRow(Index);
		},
		toolbar:"#modifyCustomerOtherInvestTable_tb1"
	})
}
/** ******************************客户历史投资信息增/删/编辑************************************ */
var modifyCustomerOtherInvestTableRowIndex;
//增加一行
function modifyCustomerOtherInvestTableAddOneRow(){
	modifyCustomerOtherInvestTableRowIndex = addOneRow(modifyCustomerOtherInvestTable,modifyCustomerOtherInvestTableRowIndex);
}
//删除一行
function modifyCustomerOtherInvestTableRemoveOneRow(){
	removeOneRow(modifyCustomerOtherInvestTable,modifyCustomerOtherInvestTableRowIndex);
	investSuggestTableRowIndex= null;
}
//编辑指定行
function modifyCustomerOtherInvestTableEditOneRow(index){
	if(editOneRow(modifyCustomerOtherInvestTable,modifyCustomerOtherInvestTableRowIndex,index)){
		modifyCustomerOtherInvestTableRowIndex = index;
	}
}

//锁定编辑行
function modifyCustomerOtherInvestTableLockOneRow(){
	if(lockOneRow(modifyCustomerOtherInvestTable,modifyCustomerOtherInvestTableRowIndex)){
		modifyCustomerOtherInvestTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
var modifyCustomerMyInvestTable;
function modifyCustomerMyInvestTable(){
	modifyCustomerMyInvestTable = $('#modifyCustomerMyInvestTable').datagrid({
		//title : '我司投资记录01',
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
			field : 'ck',
	         checkbox : true,
	         width : 2
		},
		{
        	field : 'investmentType',
        	title : '类型',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investmentType;
        	}
        }, {
			field : 'productId',
			title : '产品Id',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productId;
			}
		},
        {
        	field : 'productName',
        	title : '产品名称',
        	width : 200,
        	sortable : true,
        	formatter : function(value, row, index) {
        		var param = {};
				param.productId = row.productId;
				param.productTypeCode = row.productTypeCode;
				param.productSubTypeCode = row.productSubTypeCode;
				return  "<a href='#' onclick=addproducttab('查看产品详情','"+contextPath
					+"/product/pdSearchInvestMentProductNetValueUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
        	}
        }, {
			field : 'productTypeCode',
			title : '产品类型Code',
			width : 150,
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
		},
		 {
        	field : 'foundDate',
        	title : '成立日期',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.foundDate;
        	}
        },
        {
        	field : 'agencyName',
        	title : '基金管理人',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.agencyName;
        	}
        },
//        {
//        	field : 'productPurchaseCom',
//        	title : '产品购买公司',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return '巨鲸财富';
//        	}
//        },
        {
        	field : 'investMentMoney',
        	title : '金额',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investMentMoney;
        	}
        },
        {
        	field : 'productDuration',
        	title : '产品期限',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.productDuration;
        	}
        }, {
        	field : 'endDate',
        	title : '到期日期',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.endDate;
        	}
        }, {
        	field : 'remark',
        	title : '产品备注',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remark;
        	}
        },/* {
        	field : 'inputDate',
        	title : '录入日期',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.inputDate;
        	}
        },*/ {
        	field : 'remainingStaus',
        	title : '产品状态',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainingStaus;
        	}
        }]],
		toolbar:"#modifyCustomerMyInvestTable_tb"
	})
}

var modifyCustomerMyInvestTable02;
function modifyCustomerMyInvestTable02(){
	modifyCustomerMyInvestTable02 = $('#modifyCustomerMyInvestTable02').datagrid({
		//title : '我司投资记录02',
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
			field : 'ck',
	         checkbox : true,
	         width : 2
		},
		{
        	field : 'investmentType',
        	title : '类型',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investmentType;
        	}
        }, {
			field : 'productId',
			title : '产品Id',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productId;
			}
		},
        {
        	field : 'productName',
        	title : '产品名称',
        	width : 200,
        	sortable : true,
        	formatter : function(value, row, index) {
        		var param = {};
				param.productId = row.productId;
				param.productTypeCode = row.productTypeCode;
				param.productSubTypeCode = row.productSubTypeCode;
				return  "<a href='#' onclick=addproducttab('查看产品详情','"+contextPath
					+"/product/pdSearchInvestMentProductNetValueUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
        	}
        }, {
			field : 'productTypeCode',
			title : '产品类型Code',
			width : 150,
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
		},
		{
        	field : 'foundDate',
        	title : '成立日期',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.foundDate;
        	}
        },
        {
        	field : 'agencyName',
        	title : '基金管理人',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.agencyName;
        	}
        },
//        {
//        	field : 'productPurchaseCom',
//        	title : '产品购买公司',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return '巨鲸财富';
//        	}
//        },
        {
        	field : 'investMentMoney',
        	title : '金额',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investMentMoney;
        	}
        },
        {
        	field : 'productDuration',
        	title : '产品期限',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.productDuration;
        	}
        }, {
        	field : 'endDate',
        	title : '到期日期',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.endDate;
        	}
        }, {
        	field : 'remark',
        	title : '产品备注',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remark;
        	}
        },
        {
        	field : 'expectFeeRate',
        	title : '产品收益率',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.expectFeeRate;
        	}
        },
        /*{
        	field : 'inputDate',
        	title : '录入日期',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.inputDate;
        	}
        },*/ {
        	field : 'remainingStaus',
        	title : '产品状态',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainingStaus;
        	}
        }]]
	})
}
var modifyCustomerMyInvestTable03;
function modifyCustomerMyInvestTable03(){
	modifyCustomerMyInvestTable03 = $('#modifyCustomerMyInvestTable03').datagrid({
		//title : '我司投资记录03',
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
			field : 'ck',
	         checkbox : true,
	         width : 2
		},
		{
        	field : 'investmentType',
        	title : '类型',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investmentType;
        	}
        }, {
			field : 'productId',
			title : '产品Id',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productId;
			}
		},
        {
        	field : 'productName',
        	title : '产品名称',
        	width : 200,
        	sortable : true,
        	formatter : function(value, row, index) {
        		var param = {};
				param.productId = row.productId;
				param.productTypeCode = row.productTypeCode;
				param.productSubTypeCode = row.productSubTypeCode;
				param.customerNo =customerNo;
				/*return  "<a href='#' onclick=addproducttab('查看产品详情','"+contextPath
					+"/product/detailProductDefUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";*/
				return  "<a href='#' onclick=addproducttab('查看产品详情','"+contextPath
				+"/product/pdSearchInvestMentProductNetValueUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
        	}
        }, {
			field : 'productTypeCode',
			title : '产品类型Code',
			width : 150,
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
		},
//        {
//        	field : 'agencyName',
//        	title : '产品方',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.agencyName;
//        	}
//        },
//        {
//        	field : 'productPurchaseCom',
//        	title : '产品购买公司',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return '巨鲸财富';
//        	}
//        },
		  {
        	field : 'expectOpenDay',
        	title : '开放日',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.expectOpenDay;
        	}
        },
        {
        	field : 'remainShare',
        	title : '存续份额',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainShare;
        	}
        },
        {
        	field : 'netValue',
        	title : '当前净值',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.netValue;
        	}
        }, 
//        {
//        	field : 'investMentMoney',
//        	title : '金额',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.investMentMoney;
//        	}
//        },
//        {
//        	field : 'productDuration',
//        	title : '产品期限',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.productDuration;
//        	}
//        }, {
//        	field : 'endDate',
//        	title : '到期日期',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.endDate;
//        	}
//        }, {
//        	field : 'remark',
//        	title : '备注',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.remark;
//        	}
//        }, {
//        	field : 'inputDate',
//        	title : '录入日期',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.inputDate;
//        	}
//        }, {
//        	field : 'netValue',
//        	title : '当前净值',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.netValue;
//        	}
//        }, 
//        {
//        	field : 'remainShare',
//        	title : '存续份额',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.remainShare;
//        	}
//        }, 
        {
        	field : 'remainMoney',
        	title : '存续金额',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainMoney;
        	}
        }, {
        	field : 'remainingStaus',
        	title : '产品状态',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainingStaus;
        	}
        }]]
	})
}

/*我的海外产品详情*/
var modifyCustomerMyInvestTable04;
function modifyCustomerMyInvestTable04(){
	modifyCustomerMyInvestTable04 = $('#modifyCustomerMyInvestTable04').datagrid({
		//title : '我司投资记录03',
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
			field : 'ck',
	         checkbox : true,
	         width : 2
		},
		{
        	field : 'investmentType',
        	title : '类型',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investmentType;
        	}
        }, {
			field : 'productId',
			title : '产品Id',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productId;
			}
		},
        {
        	field : 'productName',
        	title : '产品名称',
        	width : 200,
        	sortable : true,
        	formatter : function(value, row, index) {
        		var param = {};
				param.productId = row.productId;
				param.productTypeCode = row.productTypeCode;
				param.productSubTypeCode = row.productSubTypeCode;
				return  "<a href='#' onclick=addproducttab('查看产品详情','"+contextPath
					+"/product/detailProductDefUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
        	}
        }, {
			field : 'productTypeCode',
			title : '产品类型Code',
			width : 150,
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
		},
		{
        	field : 'expectOpenDay',
        	title : '开放日',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.expectOpenDay;
        	}
        },
//        {
//        	field : 'agencyName',
//        	title : '产品方',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.agencyName;
//        	}
//        },
//        {
//        	field : 'productPurchaseCom',
//        	title : '产品购买公司',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return '巨鲸财富';
//        	}
//        },
//        {
//        	field : 'investMentMoney',
//        	title : '金额',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.investMentMoney;
//        	}
//        },
//        {
//        	field : 'productDuration',
//        	title : '产品期限',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.productDuration;
//        	}
//        }, {
//        	field : 'endDate',
//        	title : '到期日期',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.endDate;
//        	}
//        }, {
//        	field : 'remark',
//        	title : '备注',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.remark;
//        	}
//        }, {
//        	field : 'inputDate',
//        	title : '录入日期',
//        	width : 100,
//        	sortable : true,
//        	formatter : function(value, row, index) {
//        		return row.inputDate;
//        	}
//        }, 
        {
        	field : 'remainShare',
        	title : '存续份额',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainShare;
        	}
        }, 
        {
        	field : 'netValue',
        	title : '当前净值',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.netValue;
        	}
        }, {
        	field : 'remainMoney',
        	title : '存续金额',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainMoney;
        	}
        }, {
        	field : 'remainingStaus',
        	title : '产品状态',
        	width : 100,
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.remainingStaus;
        	}
        }]]
	})
}


//客户投资信息赋值
function getModifyCustomerInvestInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/modifyCustomer/getModifyCustomerInvestInfoUrl",
		data:{param:modifyCustomerBaseInfo_Id},
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					/*if(resultObj.customerOtherInvestInfoMapList!=null&&resultObj.customerOtherInvestInfoMapList!=undefined&&resultObj.customerOtherInvestInfoMapList!=""){
						loadJsonObjData("modifyCustomerOtherInvestTable",resultObj.customerOtherInvestInfoMapList);				
					}*/
					if(resultObj.customerMyInvestInfoMapList!=null&&resultObj.customerMyInvestInfoMapList!=undefined&&resultObj.customerMyInvestInfoMapList!=""){
						loadJsonObjData("modifyCustomerMyInvestTable",resultObj.customerMyInvestInfoMapList);				
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		
		});
	
}
//客户投资信息赋值
function getModifyCustomerInvestInfo02(){
	$.ajax({
		type:'post',
		url:contextPath+"/modifyCustomer/getModifyCustomerInvestInfo02Url",
		data:{param:modifyCustomerBaseInfo_Id},
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if(resultObj.customerMyInvestInfoMapList02!=null&&resultObj.customerMyInvestInfoMapList02!=undefined&&resultObj.customerMyInvestInfoMapList02!=""){
						loadJsonObjData("modifyCustomerMyInvestTable02",resultObj.customerMyInvestInfoMapList02);				
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		
		});
	
}
//客户投资信息赋值
function getModifyCustomerInvestInfo03(){
	$.ajax({
		type:'post',
		url:contextPath+"/modifyCustomer/getModifyCustomerInvestInfo03Url",
		data:{param:modifyCustomerBaseInfo_Id},
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if(resultObj.customerMyInvestInfoMapList03!=null&&resultObj.customerMyInvestInfoMapList03!=undefined&&resultObj.customerMyInvestInfoMapList03!=""){
						loadJsonObjData("modifyCustomerMyInvestTable03",resultObj.customerMyInvestInfoMapList03);				
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		
		});
	
}

//客户海外投资信息赋值
function getModifyCustomerInvestInfo04(){
	$.ajax({
		type:'post',
		url:contextPath+"/modifyCustomer/getModifyCustomerInvestInfo04Url",
		data:{param:modifyCustomerBaseInfo_Id},
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if(resultObj.customerMyInvestInfoMapList04!=null&&resultObj.customerMyInvestInfoMapList04!=undefined&&resultObj.customerMyInvestInfoMapList04!=""){
						loadJsonObjData("modifyCustomerMyInvestTable04",resultObj.customerMyInvestInfoMapList04);				
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		
		});
	
}

//提交客户其他投资信息
function submitAllCustomerInvestInfo(){
	var param={};
	//判断添加的信息是否有误
	if(!modifyCustomerOtherInvestTableLockOneRow()){
		$.messager.alert('提示', "客户拜访信息输入有误！", 'info');
		return;
	}
	param.custInvestTable = $.toJSON($("#modifyCustomerOtherInvestTable").datagrid("getRows"));
	param.custBaseInfoId = modifyCustomerBaseInfo_Id;
	param.agentId = modifyCustInvest_agentId;
	var data = $.toJSON(param);
	data = escape(encodeURIComponent(data));
	$.ajax({
		type : 'post',
		url : contextPath + "/modifyCustomer/submitAllCustomerInvestInfoUrl",
		data : 'param='+data,
		cache : false,
		success : function(resultInfo){
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg,"info");
					getModifyCustomerInvestAmount();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}
/**
 * 获取其他公司投资总额
 * 
 * */
/*function getModifyCustomerInvestAmount(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerInvestAmountUrl',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var totalLabel = document.getElementById('investTotalAmount');
						//console.info(totalLabel);
						totalLabel.innerHTML = returnData.obj;  
					}
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}*/
/**
 * 获取我们公司投资总额
 * 
 * */
function getModifyCustomerMyInvestAmount(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyInvestAmountUrl',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var totalLabel = document.getElementById('myInvestTotalAmount');
						//console.info(totalLabel);
						totalLabel.innerHTML = returnData.obj;  
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
/**
 * 获取我们公司投资总额
 * 
 * */
function getModifyCustomerMyInvestAmount02(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyInvestAmount02Url',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var totalLabel = document.getElementById('myInvestTotalAmount02');
						//console.info(totalLabel);
						totalLabel.innerHTML = returnData.obj;  
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
/**
 * 获取我们公司投资份额
 * 
 * */
function getModifyCustomerMyInvestShare(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyInvestShareUrl',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var totalLabel = document.getElementById('myInvestTotalShare');
						//console.info(totalLabel);
						totalLabel.innerHTML = returnData.obj;  
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

/**
 * 获取海外投资份额
 * 
 * */
function getModifyCustomerMyInvestShare02(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyInvestShareUrl02',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var totalLabel = document.getElementById('myInvestTotalShare02');
						//console.info(totalLabel);
						totalLabel.innerHTML = returnData.obj;  
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



/**
 * 获取我们公司存续金额
 * 
 * */
function getModifyCustomerMyRemainAmount(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyRemainAmountUrl',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var remainLabel = document.getElementById('myInvestRemainAmount');
						//console.info(totalLabel);
						remainLabel.innerHTML = returnData.obj;  
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
/**
 * 获取我们公司存续金额
 * 
 * */
function getModifyCustomerMyRemainAmount02(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyRemainAmount02Url',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var remainLabel = document.getElementById('myInvestRemainAmount02');
						//console.info(totalLabel);
						remainLabel.innerHTML = returnData.obj;  
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
/**
 * 获取我们公司存续份额
 * 
 * */
function getModifyCustomerMyRemainShare(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyRemainShareUrl',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var remainLabel = document.getElementById('myInvestRemainShare');
						//console.info(totalLabel);
						remainLabel.innerHTML = returnData.obj;  
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

/**
 * 获取我们公司海外存续份额
 * 
 * */
function getModifyCustomerMyRemainShare02(){
	//var queryParam = $("#listPdAmountOrderQuery_queryConditionForm").serialize();
	//将请求参数转换成JSON格式字符串
	//queryParam = formDataToJsonStr(queryParam);
	$.ajax({
		type:'post',
		url:'modifyCustomer/getModifyCustomerMyRemainShareUrl02',
		data:'param='+modifyCustomerBaseInfo_Id,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					if(returnData.obj!=null){
						//$("#orderTotal").html(returnData.obj);
						//document.getElementById('orderTotal').innerText = returnData.obj;
						var remainLabel = document.getElementById('myInvestRemainShare02');
						//console.info(totalLabel);
						remainLabel.innerHTML = returnData.obj;  
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


//点击产品显示明细
function addproducttab(title, href) {
	$('<div id="addWindow"/>').window({
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
/*******************点击"返回"按钮触发事件函数************************************/
function modifyCustomerInvestInfoInitBack(){
	$('#addcustomerWindow').window('destroy');
	//parent.clearPreCustomerBasicInfoForm();
	clearForm();
	queryCustomerList();
}
