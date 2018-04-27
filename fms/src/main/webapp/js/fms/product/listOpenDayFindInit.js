jQuery(function($) {
	initAllCombobox();
	initopenDayGridId();
});

var openDayGridId;
function initopenDayGridId() {
	var  selectIndex = -1;
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
		},
		{
			field : 'productId',
			title : '产品流水号',
			width : 180
		},
		{
			field : 'openDate',
			title : '开放日',
			width : 180
		}]],
		toolbar : [
					{
						text : '新增',
						iconCls : 'icon-add',
						handler : function() {
							addOpenDayValueUrl();
						}
					},
					'-',
					{	text : '批量维护',
						iconCls : 'icon-add',
						handler : function() {
							addOpenDaysValueUrl();
						}
					},
					'-',
					{
						text : '更新',
						iconCls : 'icon-edit',
						handler : function() {
							var rows = $('#openDayGridId').datagrid('getSelections');
							if (rows.length == 0) {
								$.messager.alert("提示","请选择一条开放日信息进行修改","info");
								return;
							}
							if (rows.length > 1) {
								$.messager.alert("提示","只能选择一条开放日信息进行修改","info");
								return;
							}
							$("#modifywealthOpenDateId").val( rows[0].wealthOpenDateId);
							$("#modifyAngecyName").val( rows[0].agentName);
							$("#modifyProductName").val( rows[0].productName);
							addOpenDayTab('更新', contextPath+ "/product/updateOpenDayUrl");
						}
					}, '-', {
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {
							deleteOpenDay();
						}
					}, '-'
					, {
						text : '开放日信息同步',
						iconCls : 'icon-reload',
						handler : function() {
							openDayRelease();
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
 * 开放日信息同步接口
 */
function openDayRelease(){
	var rows = $('#openDayGridId').datagrid('getSelections');
	if (rows.length == 0){
		$.messager.alert("提示","请选择要同步的产品开放日信息","info");
		return;
	} 
	if (rows.length > 1) {
		$.messager.alert("提示","只能选中一条产品的开放日信息进行同步","info");
		return;
	}
	$.ajax({
		type : 'post',
		url : contextPath + "/product/openDayRelease",
		data : 'param=' + rows[0].productId,
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
					return;

				} else {
					$.messager.alert('提示', resultInfo.msg);
					return;
				}
			} catch (e) {
				$.messager.alert('提示', e);
				return;
			}
		}
	});
}


//开放日
function addOpenDayValueUrl() {
	$('<div id="addOpenDay"/>').dialog({
		href : contextPath + "/product/addOpenDayFindUrl",
		modal : true,
		fit : true,
		title : '新增开放日',
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}
//开放日批量维护
function addOpenDaysValueUrl() {
	$('<div id="addOpenDays"/>').dialog({
		href : contextPath + "/product/addOpenDaysFindUrl",
		modal : true,
		fit : true,
		title : '新增开放日',
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}

//删除
function deleteOpenDay(){
	var rows = $('#openDayGridId').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的开放日信息","info");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?id=" + n.wealthOpenDateId;
					else
						ps += "&id=" + n.wealthOpenDateId;
				});
				$.post('product/deleteOpenDayInfoUrl' + ps, function(resultInfo) {
					$.messager.alert('提示', resultInfo.msg);
					clearForm();
				});
			}
		});
	}
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

function addOpenDayTab(title, href) 
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
	productIdComobox = $("#openDayProductId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#openDayAgencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'
			});

}
