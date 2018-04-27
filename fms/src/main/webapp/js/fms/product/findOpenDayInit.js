jQuery(function($) {
	initAllCombobox();
	initopenDayGridId();
});
var openDayGridId;
function initopenDayGridId() {
	openDayGridId = $("#openDayGridId").datagrid({
		title : '开放日信息列表', // 标题
		method : 'post',
		singleSelect : true, // 单选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath + "/product/queryListOpenDayUrl", // 数据来源
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
		}, {
			field : 'wealthOpenDateId',
			title : '开放日Id',
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
			field : 'openDate',
			title : '开放日',
			width : 180
		}]]
	});
}




//查询
function searchOpenDay() {
	openDayGridId.datagrid('options').url = contextPath+"/product/queryListOpenDayUrl";
	var queryparam = $("#queryOpenInfoCondition").serialize();
	queryparam = formDataToJsonStr(queryparam);
	openDayGridId.datagrid('load',{param:queryparam});

}	
//清空
function clearForm(){
	$('#queryOpenInfoCondition').form('clear');
	$('#openDayGridId').datagrid('cisreload');
}



/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {

	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#find_openDayProductId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#find_openDayAgencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'
			});

}
