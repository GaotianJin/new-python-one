/*var jsonData = [{"productName":'潍坊三河特定多个客户专项资产管理计划(第一期)',"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
	"distributionInterestMoney":"300000.00","distributionPrincipalMoney":"500000.90","principalRate":"1.089","incomeDisState":"待分配","residualShare":"2000000",'redeemShares':'500.00'},
	{"productName":'潍坊三河特定多个客户专项资产管理计划(第二期)',"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
		"distributionInterestMoney":"300000.00","distributionPrincipalMoney":"500000.90","principalRate":"1.089","incomeDisState":"待分配","residualShare":"2000000",'redeemShares':'500.00'},
	{"productName":'潍坊三河特定多个客户专项资产管理计划(第三期)',"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
		"distributionInterestMoney":"300000.00","distributionPrincipalMoney":"500000.90","principalRate":"1.089","incomeDisState":"已分配","residualShare":"2000000",'redeemShares':'500.00'}];
*/

jQuery(function($) {
	initAllCombobx();
	initIncomeDisTributionTable();
	//incomeDisTributionTable.datagrid("loadData",jsonData);
});

var incomeDisTributionTable;
function  initIncomeDisTributionTable (){
	var  selectIndex = -1;
	incomeDisTributionTable=$('#IncomeDisTributionTable').datagrid({
		title : '收益分配计算信息列表', 
		fitColumns:true,
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
		           {field:'pdIncomeDistributeId',title:'收益分配流水号',hidden:true},
		           {field:'productId',title:'产品流水号',hidden:true},
		           {field:'productName',title:'产品名称',width:180},
		           {field:'distributeDate',title:'分配日期',width:100},    
		           {field:'distributeStartDate',title:'分配起期',width:100},
		           {field:'distributeEndDate',title:'分配止期',width:100}, 
		           {field:'distributeTotalInterest',title:'分配利息总金额',width:120},
		           {field:'distributeTotalPrincipal',title:'本金分配总金额',width:120}, 
		           {field:'distributePrincipalRate',title:'本金分配比例',width:120},
		           {field:'distributeStatus',title:'收益分配状态编码',hidden:true},
		           {field:'distributeStatusName',title:'收益分配状态',width:100},
		           {
						field : 'operation',
						title : '操作',
						width : 120,
						formatter : function(value, row, index) {
							return "<a href='#'  onclick=tradeIncomeDistributeExport('"+row.pdIncomeDistributeId+"')>"+row.operation+"</a>";
						}
					}
		       ]],
		   	onLoadSuccess : function() {
		   		$("#IncomeDisTributionTable").datagrid('clearSelections');
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

function initAllCombobx(){
	$("#incomeDistribution_productId").combobox({
		url : contextPath + '/codeQuery/queryAllFixedIncomeProduct?agencyComId='+"",
		valueField : 'code',
		textField : 'codeName'
	});
}

function queryProductIncomeDistributeList() {
	incomeDisTributionTable.datagrid('options').url = "incomeDis/queryProductIncomeDistibuteList";
	var queryParam = $("#incomeDistribulationForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	incomeDisTributionTable.datagrid('load',{queryParam:queryParam});	
}

function clearForm(){
	$('#incomeDistribulationForm').form('clear');
}

/**
 * 通过界面执行收益分配批处理
 * 
 */
function incomeDistribute(){
 $('#incomeDistribute').linkbutton('disable');
	$.ajax({
		type:'post',
		url:contextPath+"/incomeDis/incomeDisBatch",
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', "收益分配完成！");
				}else{
					$.messager.alert('提示', reData.msg);
				}
			}catch (e) {
				$.messager.alert('提示', e);
			}
			 $('#incomeDistribute').linkbutton('enable');}
	});
}

/**
 * 
 *收益分配报表下载 
 */
function tradeIncomeDistributeExport(pdIncomeDistributeId){
	window.open(contextPath+'/incomeDis/productIncomeDisDetail.xls?pdIncomeDistributeId='+pdIncomeDistributeId);
}