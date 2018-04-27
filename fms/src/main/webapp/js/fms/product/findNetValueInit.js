jQuery(function($) {
	// 初始化下来框信息
	initAllCombobox();
	initvalueInfoGridId();
});
var valueInfoGridId;
function initvalueInfoGridId() {
	var  selectIndex = -1;
	valueInfoGridId = $("#valueInfoGridId")
			.datagrid(
					{
						title : '净值信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/queryListNetValueUrl", // 数据来源
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
							field : 'wealthNetValueId',
							title : '净值Id',
							hidden: true,
							width : 180
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
						}, {
							field : 'publicDate',
							title : '公布日期',
							width : 180
						}, {
							field : 'netValue',
							title : '净值',
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
function searchNetValue() {
	
	valueInfoGridId.datagrid('options').url = contextPath+"/product/queryListNetValueUrl";
	var queryparam = $("#netValueForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	valueInfoGridId.datagrid('load',{param:queryparam});
}	
	
// 清空查询条件然后进行查询
function clearForm() {
	$('#netValueForm').form('clear');
	$('#valueInfoGridId').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}


/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#find_productId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#find_agencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'

			});
}
