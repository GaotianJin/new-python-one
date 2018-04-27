var jsonData = [{"tradeId":'1',"tradeNo":"201501010001","expectOpenDay":"2015-01-01","shareTypeName":"认购","startShare":"2000000","currentShare":"2000000","redeemableShare":"2000000","residualShare":"2000000",'redeemShares':'500.00','beneficialAccName':'中国银行','beneficialAccNo':'622230980009900'},
                {"tradeId":'2',"tradeNo":"201501010004","expectOpenDay":"2015-01-04","shareTypeName":"认购","startShare":"3000000","currentShare":"3000000","redeemableShare":"3000000","residualShare":"3000000",'redeemShares':'0.00','beneficialAccName':'中国银行','beneficialAccNo':'622230980009900'}];

var custAllTradeTableIndex = null;
/**
 * 页面初始化加载
 */
jQuery(function($) {
	initAllCombobox();
	initCustAllTradeTable();
	custAllTradeTable.datagrid("loadData",jsonData);
});


function initAllCombobox() {
	
	$("#redemptionConfirmAdd_custName").val('张三');
	$("#redemptionConfirmAdd_agentName").val('CF001');
	$("#redemptionConfirmAdd_tradeNo").val('0000000990002033');
	$("#redemptionConfirmAdd_agencyName").val('平安财富公司');
	$("#redemptionConfirmAdd_agencyCode").val('PA001');
	$("#redemptionConfirmAdd_productName").val('潍坊三河特定多个客户专项资产管理计划(第一期)');
	$("#redemptionConfirmAdd_beneficialTypeName").val('未分类');
	$('#redemptionConfirmAdd_redemptionType').combobox(); 
	$('#redemptionConfirmAdd_redemptionType').combobox('setValue', '部分赎回');
	$('#redemptionConfirmAdd_expectOpenDay').combobox(); 
	$('#redemptionConfirmAdd_expectOpenDay').combobox('setValue', '2015-05-24');
	
	$("#redemptionConfirmAdd_netValue").val('1.090');
	$("#redemptionConfirmAdd_applyDate").datebox();
	$("#redemptionConfirmAdd_applyDate").datebox('setValue', '2014-09-12');
	
	$("#redemptionConfirmAdd_redeemMoney").val('5000009.00');
	$("#redemptionConfirmAdd_redeemShares").val('500.00');
	
	// 合作机构
	/*$("#agencyCode1").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	// 销售状态初始化
	$("#salesStatus1").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		value : '0'
	});*/
 }

/**
 * (固定收益类)费用比例信息可编辑表格
 * 
 */
var custAllTradeTable;
function initCustAllTradeTable() {
	custAllTradeTable = $("#custAllTradeTable").datagrid({
		method : 'post',
		singleSelect : false, 
		fitColumns : true, 
		striped : true, 
		collapsible : true,
		remoteSort : true,
		idField : 'id',
		queryParams : {}, 
		columns : [ [
				{
					field : 'ck',
					checkbox : true,
					width : 2
				}, 
				{
					field : 'tradeId',
					title : '交易流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.tradeId;
					}
				}, 
				{
					field : 'tradeNo',
					title : '交易号',
					width : 100,
					formatter : function(value, row, index) {
						return row.tradeNo;
					}
				}, 
				{
					field : 'expectOpenDay',
					title : '成立日/开放日',
					width : 100,
					formatter : function(value, row, index) {
					return row.expectOpenDay;
					} 
				}, 
				{
					field : 'shareType',
					title : '份额类型',
					hidden : true,
					formatter : function(value, row, index) {
						return row.shareType;
					}
				}, 
				{
					field : 'shareTypeName',
					title : '份额类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.shareTypeName;
					}
				}, 
				{
					field : 'startShare',
					title : '初始份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.startShare;
				   }
				},
				{
					field : 'currentShare',
					title : '当前份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.currentShare;
					}
				}, 
				{
					field : 'redeemableShare',
					title : '可赎回份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.redeemableShare;
					}
				}, 
				{
					field : 'redeemShares',
					title : '本次赎回份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.redeemShares;
					}
				} , 
				{
					field : 'residualShare',
					title : '剩余份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.residualShare;
					}
				}, 
				{
					field : 'beneficialAccName',
					title : '受益账户银行',
					width : 200,
					formatter : function(value, row, index) {
						return row.beneficialAccName;
					}
				},
				{
					field : 'beneficialAccNo',
					title : '受益账号',
					width : 200,
					formatter : function(value, row, index) {
						return row.beneficialAccNo;
					}
				}] ],
		onLoadSuccess : function() {
			$('#custAllTradeTable').datagrid('clearSelections'); 
		},
		onClickRow:function(rowIndex){
			custAllTradeTableIndex = rowIndex;
			$("#custAllTradeTable").datagrid('selectRow', rowIndex).datagrid('beginEdit', rowIndex);
		}
	});
}
