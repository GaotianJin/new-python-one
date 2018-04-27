jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initNetValueInfoGridId();
});
var netValueInfoGridId;
function initNetValueInfoGridId() {
	var  selectIndex = -1;
	netValueInfoGridId = $("#netvalueInfoGridId")
			.datagrid(
					{
						title : '净值信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/queryNetValueHistoryUrl", // 数据来源
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
							field : 'agentName',
							title : '基金管理人',
							width : 180
						}, {
							field : 'productCode',
							title : '产品代码',
							width : 180
						}, {
							field : 'productName',
							title : '产品名称',
							width : 180
						},
						{
							field : 'publicDate',
							title : '公布日期',
							width : 180
						}, {
							field : 'netValue',
							title : '净值',
							width : 180
						} , {
							field : 'netValueType',
							title : '净值类型',
							width : 180
						} , {
							field : 'wheatherOpenDay',
							title : '是否开放日',
							width : 180
						} ] ],
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
function searchNetValueList() {
	netValueInfoGridId.datagrid('options').url = contextPath+"/product/queryNetValueHistoryUrl";
	var queryparam = $("#netValuesForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	netValueInfoGridId.datagrid('load',{param:queryparam});
}	
	
// 清空查询条件然后进行查询
function clearValueForm() {
	$('#netValuesForm').form('clear');
	$('#netvalueInfoGridId').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	parent.searchNetValueList();
}
/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#list_net_valueProductId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#list_net_valueComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'

			});
	// 合作机构
	var netValueTypeCombobox;
	netValueTypeCombobox = $("#list_net_valueType").combobox(
			{
				url : contextPath + '/codeQuery//tdCodeQuery?codeType=netValueType',
				valueField : 'code',
				textField : 'codeName',
				editable:false

			});
	
}
