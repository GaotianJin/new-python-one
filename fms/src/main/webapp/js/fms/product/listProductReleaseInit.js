jQuery(function($) {
	initAllCombobox();
	initproInfoListGridId();
});
//初始化下拉框
function initAllCombobox() {
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#productReleaseProductId").combobox({
		url : contextPath + '/codeQuery/productQueryAll',
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构初始化
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#productReleaseAgencyComId").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName'
			});

	// 产品状态初始化
	$("#productReleaseSalesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=productStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品子类
	var productSubTypeCombobox;
	productSubTypeCombobox = $("#productReleaseProductSubType").combobox({
		valueField : 'code',
		textField : 'codeName'
	});
	// 产品类型初始化
	var productTypeCombobox;
	productTypeCombobox = $("#productReleaseProductType").combobox(
			{
				url : contextPath+ '/codeQuery/tdCodeQuery?codeType=productType',
				valueField : 'code',
				textField : 'codeName',
				onSelect : function() {
					var value = productTypeCombobox.combobox("getValue");
					// 1-财富，2-保险
					var codeType;
					if (value == 1) {
						codeType = 'wealthProSubType';
					} else {
						codeType = 'insProSubType';
					}
					var url = contextPath + '/codeQuery/tdCodeQuery?codeType='
							+ codeType;
					productSubTypeCombobox.combobox("reload", url);
				}

			});

	// 销售状态初始化
	$("#productReleaseSalesStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=salesStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	
	//产品状态
	$("#productReleaseProductStatus").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=productStatus',
		valueField : 'code',
		textField : 'codeName'
	});
	
}
var proInfoListGridId;
function initproInfoListGridId() {
	var  selectIndex = -1;
	proInfoListGridId = $("#proReleaseListGrid").datagrid({
		title : '产品信息列表', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath + "/product/queryListReleaseProductUrl", // 数据来源
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
			field : 'productId',
			title : '产品Id',
			width : 150,
			hidden:true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productId;
			}
		},
		{
			field : 'agencyComId',
			title : '基金管理人',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.agencyComId;
			}
		}, {
			field : 'productCode',
			title : '产品代码',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				
				var param = {};
				param.productId = row.productId;
				param.productTypeCode = row.productTypeCode;
				param.productSubTypeCode = row.productSubTypeCode;
				return  "<a href='#' onclick=addproducttab('产品明细信息','"+contextPath
				+"/product/detailProductDefUrl?param="+$.toJSON(param)+"')>"+row.productCode+"</a>";
			}

		}, {
			field : 'productName',
			title : '产品名称',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productName;
			}

		}, {
			field : 'productType',
			title : '产品类型',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productType;
			}
		}, 
		 {
			field : 'productTypeCode',
			title : '产品类型',
			width : 150,
			sortable : true,
			hidden:true,
			formatter : function(value, row, index) {
				return row.productTypeCode;
			}
		}, 
		{
			field : 'productSubType',
			title : '产品子类',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productSubType;
			}
		}, 
		{
			field : 'productSubTypeCode',
			title : '产品子类',
			hidden:true,
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.productSubTypeCode;
			}
		}, 
		{
			field : 'salesStatus',
			title : '销售状态',
			width : 80,
			sortable : true,
			formatter : function(value, row, index) {
				return row.salesStatus;
			}
		}, {
			field : 'status',
			title : '产品状态',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.status;
			}
		},
		{
			field : 'statusCode',
			title : '产品状态编码',
			width : 150,
			hidden:true,
			sortable : true,
			formatter : function(value, row, index) {
				return row.statusCode;
			}
		},
		{
			field : 'userCode',
			title : '操作员',
			width : 150,
			sortable : true,
			formatter : function(value, row, index) {
				return row.userCode;
			}
		}
		] ],
		toolbar : [
					{
						text : '修改申请',
						iconCls : 'icon-edit',
						handler : function() {
							modifyApply();
						}
						
					}, '-', {
						id: 'release',
						text : '发布',
						iconCls : 'icon-tick',
						handler : function() {
							release();
						}
					}, '-' ],
		onLoadSuccess : function() {
			$('#listProductDefTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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



//修改申请
function modifyApply(){
	{
		var rows = $('#proReleaseListGrid').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert("提示","请选择一个产品进行修改申请","info");
			return;
		}
		if (rows.length > 1) {
			$.messager.alert("提示","只能选择一个产品进行修改申请","info");
			return;
		}
		if(rows[0].statusCode==2){
			$.messager.alert("提示","该产品已发布，不能进行修改申请","info");
			return;
		}
		$.ajax({
			type : 'post',
			url : contextPath + "/product/productModifyApply",
			data : 'param=' + rows[0].productId,
			cache : false,
			success : function(resultInfo) {
				try {
					if (resultInfo.success) {
						$.messager.alert('提示', resultInfo.msg, 'info');
						$.messager.progress('close');
						clearFormInfo();
						$('#proReleaseListGrid').datagrid('cisreload');
						
					} else {
						$.messager.alert('提示', resultInfo.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
			}
		});
		
		
	}
	
	
}


//发布
function release(){
	{
		var param={};
		var rows = $('#proReleaseListGrid').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert("提示","请选择一个产品进行发布","info");
			return;
		}
		if (rows.length > 1) {
			$.messager.alert("提示","只能选择一个产品进行发布","info");
			return;
		}
/*		if(rows[0].statusCode==2){
			alert("该产品已发布，不能进行重新发布");
			return;
		}*/
		param.productId=rows[0].productId;
		param.productStatus=rows[0].statusCode;
		$('#release' ).linkbutton('disable');
		$.ajax({
			type : 'post',
			url : contextPath + "/product/productRelease",
			data : 'param=' +encodeURI($.toJSON(param)),
			cache : false,
			success : function(resultInfo) {
				try {
					if (resultInfo.success) {
						$.messager.alert('提示', resultInfo.msg, 'info');
						$.messager.progress('close');
						//clearFormInfo();
						//$('#proReleaseListGrid').datagrid('cisreload');
						$('#proReleaseListGrid').datagrid('reload');
						
					} else {
						$.messager.alert('提示', resultInfo.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
				$('#release' ).linkbutton('enable');
			}
		});
		
	}
}

//清空查询条件然后进行查询
function clearFormInfo() {
	$('#productReleaseForm').form('clear');
}
//查询
function searchProductReleaseInfo() {
	
	proInfoListGridId.datagrid('options').url = contextPath+"/product/queryListReleaseProductUrl";
	var queryparam = $("#productReleaseForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	proInfoListGridId.datagrid('load',{param:queryparam});
}
function addproducttab(title, href) {
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