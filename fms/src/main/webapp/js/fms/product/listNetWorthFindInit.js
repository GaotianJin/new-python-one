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
						},
						{
							field : 'productId',
							title : '产品流水号',
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
						} ] ],
						toolbar : [
								{
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										$('<div id="addNetValueInfo"/>')
												.dialog(
														{
															href : contextPath+ "/product/addNetWorthUrl",
															modal : true,
															fit : true,
															title : '产品净值新增',
															inline : false,
															minimizable : false,
															onClose : function() {
																$(this).dialog('destroy');
															}
														});
									}
								},
								'-',
								{
									text : '更新',
									iconCls : 'icon-edit',
									handler : function() {
										var rows = $('#valueInfoGridId').datagrid('getSelections');
										if (rows.length == 0) {
											$.messager.alert("提示","请选择一条净值信息进行修改","info");
											return;
										}
										if (rows.length > 1) {
											$.messager.alert("提示","只能选择一条净值信息进行修改","info");
											return;
										}
										$("#modifyRecordNetWorthId").val( rows[0].wealthNetValueId);
										$("#modifyAngecyName").val( rows[0].agentName);
										$("#modifyProductName").val( rows[0].productName);
										addNetValueTab('更新', contextPath+ "/product/updateNetWorthUrl");
									}
								}, '-', {
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										deleteNetWorth();
									}
								}, '-'
								, {
									text : '净值信息同步',
									iconCls : 'icon-reload',
									handler : function() {
										netWorthRelease();
									}
								}, '-' 
								],
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

/**
 * 净值信息同步接口
 */
function netWorthRelease(){
	
	var rows = $('#valueInfoGridId').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要同步的产品净值信息","info");
		return;
	} 
	if (rows.length > 1) {
		$.messager.alert("提示","只能选中一条产品信息进行同步","info");
		return;
	}
	$.ajax({
		type : 'post',
		url : contextPath + "/product/netValueRelease",
		data : 'param=' + rows[0].productId,
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
					return;

				} else {
					$.messager.alert('提示', resultInfo.msg,'info');
					return;
				}
			} catch (e) {
				$.messager.alert('提示', e);
				return;
			}
		}
	});
}


function deleteNetWorth() {
	var rows = $('#valueInfoGridId').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的净值信息","info");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?id=" + n.wealthNetValueId;
					else
						ps += "&id=" + n.wealthNetValueId;
				});
				$.post('product/deleteNetValueUrl' + ps, function(resultInfo) {
					$.messager.alert('提示', resultInfo.msg,"info");
					clearForm();
				});
			}
		});
	}
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
	parent.searchNetValue();
}

function addNetValueTab(title, href) 
{
	$('<div id="addWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true, 
		inline : false,
		minimizable : false,
		onClose : function() {
			$("#modifyRecordNetWorthId").val("");
			$(this).window('destroy');
		}
	});
}
/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#list_net_productId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#list_net_agencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'

			});
}
