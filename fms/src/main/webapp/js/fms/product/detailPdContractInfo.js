jQuery(function($) {
	initdetailAllCombobox();
	initContractDetailInfoTable();
	searchDetailContractInfo();
});

function initdetailAllCombobox() {
	//合同状态
	 $("#detailpdContractStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isUseContract',
		valueField : 'code',
		textField : 'codeName'
	});
}

//初始化table
var detailPdContractInfoTable;
function initContractDetailInfoTable() {
	var  selectIndex = -1;
	detailPdContractInfoTable = $('#detailPdContractInfo').datagrid({	
		title : '合同信息列表', // 标题
		method : 'post',
		singleSelect : false, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
//		url : contextPath + "/product/queryDetailContractInfo?param="+$.toJSON(productId), // 数据来源
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
		},{
			field : 'contractId',
			title : '合同Id',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.contractId;
			}
		},{
			field : 'tradeInfoId',
			title : '交易流水号',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeInfoId;
			}
		},{
			field : 'contractNumber',
			title : '合同号',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.contractNumber;
			}
		},{
			field : 'tradeNo',
			title : '交易号',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.tradeNo;
			}
		},{
			field : 'agentName',
			title : '理财顾问',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.agentName;
			}
		},{
			field : 'comName',
			title : '所属机构',
			width : 100,
			sortable : true,
			formatter : function(value, row, index) {
				return row.comName;
			}
		},{
			field : 'contractStatusCode',
			title : '合同状态Code',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.contractStatusCode;
			}
		},
		{
			field : 'contractStatus',
			title : '合同状态',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.contractStatus;
			}
		}]],
        onLoadSuccess : function() {
			$('#detailPdContractInfo').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	})
}

//清空查询条件然后进行查询
function clearDetailForm() {
//	$('#detailContractInfoFrom').form('clear');
//	$('#detailpdContractStatus').val('');
	$("#detailpdContractStatus").combobox("setValue",'');
}
//查询
function searchDetailContractInfo() {
	detailPdContractInfoTable.datagrid('options').url = contextPath+"/product/queryDetailContractInfo";
	var queryparam = $("#detailContractInfoFrom").serialize();
	queryparam = formDataToJsonStr(queryparam);
	detailPdContractInfoTable.datagrid('load',{param:queryparam});
}
function exportDetailPdContractInfo(){
	var queryParam = $("#detailContractInfoFrom").serialize();// 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath
			+ '/product/contractInfoDetail.xls?queryParam='
			+ encodeURI(queryParam));
}