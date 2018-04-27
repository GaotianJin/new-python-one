jQuery(function($){
	initBasicLawForm();
	initBasiclawTable();
});

//初始化表格信息
function initBasiclawTable()
{
	var  selectIndex = -1;
	$('#list_basicLawProductTable').datagrid({
		title : '基本法信息列表', // 标题
		method : 'post',
//		iconCls : 'icon-ok', // 图标
		singleSelect : true, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/basicLaw/queryBasicLawProductList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList : [5,10,15,20],
		pageSize : 10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},{
					field : 'productId',
					title : '产品Id',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.productId;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'basicLawId',
					title : '基本法版本ID',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.basicLawId;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'productCode',
					title : '产品代码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						if(row.productCode!=null){
							return  "<a href='#'  onclick=addBasicLawProductTab('基本法产品参数明细信息','"+contextPath+"/basicLaw/detailBasicLawProductUrl')>"+row.productCode+"</a>";
						}
						return  "<a href='#'  onclick=addBasicLawProductTab('基本法产品参数明细信息','"+contextPath+"/basicLaw/detailBasicLawProductUrl')>"+"---"+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'productName',
					title : '产品名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						
						if(row.productName!=null){
							return row.productName;
						}
						return "---";
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'productType',
					title : '产品类别',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.productType;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'productTypeCode',
					title : '产品类别Code',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.productTypeCode;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productSubType',
					title : '产品子类',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.productSubType;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productSubTypeCode',
					title : '产品子类Code',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.productSubTypeCode;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'versionCode',
					title : '版本编码',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.versionCode;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'versionName',
					title : '版本名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.versionName;
					}
				},{
					field : 'productExecState',
					title : '版本执行状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.versionExecState;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'versionExecStartDate',
					title : '版本执行开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.versionExecStartdate;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'versionExecEndDate',
					title : '版本执行结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.versionExecEnddate;
					} // 需要formatter一下才能显示正确的数据
				}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
					addBasicLawProductTab('基本法产品参数信息新增', contextPath+"/basicLaw/addBasicLawProductUrl");
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {updateBasicLawProduct();}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {deleteBasicLawProduct();}
				}, '-'],
		onLoadSuccess : function() {
			$('#list_basiclawProductTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
//初始化页面表单信息
function initBasicLawForm()
{	
	//版本执行状态初始化
	$('#listp_versionExecState').combobox({
		valueField:'code',
		textField:'codeName',
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState'
	  });

	
	//产品代码初始化
	var productIDCombobox;
	productIDCombobox = $("#listp_productId").combobox({
		valueField:'code',
		textField:'codeName'
	});
	//产品类别出初始化
	var productTypeCombobox;
	productTypeCombobox=$("#listp_productType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=productType',
		valueField:'code',
		textField:'codeName',
		onSelect : function() 
		{	
			
			productTypeValue = productTypeCombobox.combobox("getValue");
			// 1-财富，2-保险
			if(productTypeValue=="1"){
				url = contextPath+'/codeQuery/tdCodeQuery?codeType=wealthProSubType';
			}
			else{
				url = contextPath+'/codeQuery/tdCodeQuery?codeType=insProSubType';
			}
			productSubTypeCombobox.combobox('clear');
			productSubTypeCombobox.combobox("reload",url);
		}
	});
	// 产品子类初始化
	var productSubTypeCombobox;
	productSubTypeCombobox = $("#listp_productSubType").combobox({
		valueField:'code',
		textField:'codeName',
		onSelect : function()
		{
			productSubTypeValue = productSubTypeCombobox.combobox("getValue");
			productIDCombobox.combobox('clear');
			productIDCombobox.combobox("reload",contextPath+"/codeQuery/productQueryByType?productType="+productTypeValue+"&productSubType="+productSubTypeValue);
		 }
	});
}
//更新
function updateBasicLawProduct()
{
	var rows = $('#list_basicLawProductTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个产品基本法进行修改", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个产品基本法修改", 'info');
		return;
	}
	addBasicLawProductTab('基本法产品参数信息更新', contextPath+"/basicLaw/updateBasicLawProductUrl");
}

function addBasicLawProductTab(title, href) 
{
	$('<div id="basicLawProductWindow"/>').dialog({
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

// 删除
function deleteBasicLawProduct() {
	var rows = $('#list_basicLawProductTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择要删除的基本法", 'info');
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result){
				var dlist = [];
				$.each(rows, function(i, n) {
					dlist.push({"basicLawId":rows[0].basicLawId,"productId":rows[0].productId});
//					console.info(rows[0].productId);
				});
				$.post(contextPath+"/basicLaw/saveDeleteBasicLawProductUrl?list=" +$.toJSON(dlist), function(data) {
					searchBasicLawProduct();
					$.messager.alert('提示', data.msg, 'info');
				});
			}
		});
	}
}

// 表格查询
function searchBasicLawProduct() 
{
	$('#list_basicLawProductTable').datagrid('options').url = "basicLaw/queryBasicLawProductList";
	var queryParam = $("#list_basicLawProductForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	$('#list_basicLawProductTable').datagrid('load',{queryParam:queryParam});
}
// 清空查询条件然后进行查询
function clearBasicLawProductForm() {
	$('#list_basicLawProductForm').form('clear');
	searchBasicLawProduct();
}

//清空查询条件然后进行查询
function clearBasicLawProductFormList() {
	$('#list_basicLawProductForm').form('clear');
}