jQuery(function($) 
{
	initSearchCombobox();
	initSearchAgentListTable();
	var jsonData = [{},{},{},{},{}];
	searchAgentListTable.datagrid('loadData',jsonData);
});

//初始化查询界面下拉框
function initSearchCombobox(){
	//初始化理财经理
	$("#agent_name").combobox({
		url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name'
	});
	
	//初始化机构
	$("#agent_belong_sub_com").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	//初始化网点
	/*$("#agent_belong_store").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#lisCust_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
}

var searchAgentListTable;
function initSearchAgentListTable(){
	searchAgentListTable = $('#search_agentListTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		//collapsible : true,// 可折叠
		//url : "customer/searchCustomerInfo", // 数据来源
		//sortName : 'id', // 排序的列
		//sortOrder : 'desc', // 倒序
		//remoteSort : true, // 服务器端排序
		idField : 'custBaseInfoId', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'agentId',
					title : '财富顾问流水号',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.custBaseInfoId;
					} // 需要formatter一下才能显示正确的数据
				}, // 显示复选框
				{
					field : 'agentCode',
					title : '财富顾问代码',
					//sortable : true,
					formatter : function(value, row, index) {
						return row.agentCode;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agentName',
					title : '财富顾问姓名',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.agentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'storeName',
					title : '所属网点',
					width : 100,
					hidden:true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.storeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'comName',
					title : '所属分公司',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.comName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'mobilePhone',
					title : '手机号',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.mobile;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'idNo',
					title : '证件号',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.idNo;
					} // 需要formatter一下才能显示正确的数据
				}
				]],
		
		onLoadSuccess : function() {
			$('#search_agentListTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

/**
 * 查找理财经理信息
 * 
 */
function searchAgentomerInfo(){
	searchAgentListTable.datagrid('options').url = "sales/queryAgentList";
	var searchParam = $("#searchAgent_SearchConditionForm").serialize();
	searchParam = formDataToJsonStr(searchParam);
	/*var param = eval("("+searchParam+")");
	var chnName = param.chnName;
	var idNo = param.idNo;
	if((chnName==null||chnName==""||chnName==undefined)&&(idNo==null||idNo==""||idNo==undefined)){
		$.messager.alert('提示', "客户姓名和证件号码必须录入其中一项");
		return;
	}*/
	searchAgentListTable.datagrid('load',{queryParam:searchParam});	
}

/**
 * 返回
 * 
 * */
function backAgentInfoPage(){
	var agentInfoRowData = searchAgentListTable.datagrid('getSelections');
	if(agentInfoRowData==null||agentInfoRowData==""||agentInfoRowData==undefined||agentInfoRowData.length==0){
		$.messager.alert('提示', "请选中一条客户信息再点击确定按钮返回");
		return;
	}else{
		console.info(agentInfoRowData[0])
		searchCustomerInfoBack(agentInfoRowData[0]);
	}
	$("#searchCustWindow").window("destroy");
}
