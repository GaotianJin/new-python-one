jQuery(function($) {
	initContractAllCombobox();
	initContractInfoTable();
});

function initContractAllCombobox() {
	// 产品初始化 显示为：产品代码-产品名称
	 $("#contractInfoProId").combobox({
		url : contextPath + '/codeQuery/wealthproductQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	 
/*	//合同号
	 $("#pdContractNumber").combobox({
		url : contextPath + '/codeQuery/contractNumberQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	//合同状态
	 $("#pdContractStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isUseContract',
		valueField : 'code',
		textField : 'codeName'
	});*/
}

//产品设置页面信息列表初始化
var lisPdContractInfoTable;
function initContractInfoTable() {
	var  selectIndex = -1;
	lisPdContractInfoTable = $('#lisPdContractInfo').datagrid({	
		title : '合同信息列表', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath + "/product/queryListContractInfo", // 数据来源
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
			field : 'productId',
			title : '产品Id',
			width : 150,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productId;
			}
		}, {
			field : 'productCode',
			title : '产品代码',
			width : 140,
//			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productCode;
			}
		},
		{
			field : 'productName',
			title : '产品名称',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				var param = {};
				param.contractCode = row.contractCode;
				param.productId = row.productId;
				return  "<a href='#' onclick=addContractTab('查看产品合同详情','"+contextPath
					+"/product/detailPdContractUrl?param="+$.toJSON(param)+"')>"+row.productName+"</a>";
			}
		},{
			field : 'contractCode',
			title : '合同号',
			width : 100,
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.contractCode;
			}
		}, {
			field : 'contractStatusCode',
			title : '合同状态',
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
			hidden : true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.contractStatus;
			}
		},{
				field : 'productType',
				title : '产品类型',
				width : 150,
				sortable : true,
				formatter : function(value, row, index) {
					return row.productType;
			}
		},{
			field : 'productSubType',
			title : '产品子类型',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productSubType;
		}
	}]],
		toolbar : [
					{
						text : '新增',
						iconCls : 'icon-add',
						handler : function() {
							addContractTab('新增产品合同信息',contextPath + "/product/addPdContractInfoUrl");
						}
					}, '-' , {
						text : '更新',
						iconCls : 'icon-edit',
						handler : function() {
							var rows = $('#lisPdContractInfo').datagrid('getSelections');
							if(rows.length == 0){
								$.messager.alert("提示","请选择一个合同信息进行修改","info");
								return;
							}
							var param = {};
							param.productId = rows[0].productId;
							param = $.toJSON(param);
							addContractTab('更新产品合同信息',contextPath+ "/product/updatePdContractInfoUrl?param="+param);
						}
						
					}, '-'  , {
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {
							var rows = $('#lisPdContractInfo').datagrid('getSelections');
							if (rows.length == 0) {
								$.messager.alert("提示","请选择要删除的产品","info");
								return;
							} 
							
							$.messager.confirm('提示', '确定要删除吗?', function(r) {
								if (r){
									$.ajax({
										type:'post',
										url:contextPath+"/product/deleteContract",
										data:'param='+$.toJSON(rows),
										cache:false,
										success:function(reData){
											try {
												if(reData.success){
													$.messager.alert('提示', "成功删除", 'info');
													lisPdContractInfoTable.datagrid('reload');
												}else{
													$.messager.alert('提示', reData.msg);
												}
											} catch (e) {
												$.messager.alert('提示', e);
											}
										}
									});
								}
							});
						}
						
					}],
        onLoadSuccess : function() {
			$('#lisPdContractInfo').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
function clearForm() {
	$('#queryContractInfoCondition').form('clear');
}
//查询
function searchContractInfo() {
	lisPdContractInfoTable.datagrid('options').url = contextPath+"/product/queryListContractInfo";
	var queryparam = $("#queryContractInfoCondition").serialize();
	queryparam = formDataToJsonStr(queryparam);
	lisPdContractInfoTable.datagrid('load',{param:queryparam});
}

//打开新的窗口
function addContractTab(title, href) {
	$('<div id="addWindow"/>').dialog({
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
