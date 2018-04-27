jQuery(function($) {
	
	initBasicLawVersionForm();
	
	initBasicLawVersionTable();
	
});

//初始化表格信息
function initBasicLawVersionTable()
{
	var  selectIndex = -1;
	$('#list_basicLawVersionTable').datagrid({
		title : '基本法版本信息列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 单选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/basicLaw/queryBasicLawVersionList",
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParam : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'BasicLawId',
					title : '版本ID',
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.basicLawId;
					} 
				},{
					field : 'versionCode',
					title : '版本编号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return  "<a href='#'  onclick=addBasicLawVersion('基本法版本参数明细信息','"+contextPath+"/basicLaw/detailBasicLawVersionUrl')>"+row.versionCode+"</a>";
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'versionName',
					title : '版本名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.versionName;
					}
				},{
					field : 'execState',
					title : '执行状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.execState;
					}
				},{
					field : 'execStartDate',
					title : '执行开始日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.execStartDate;
					}
				},{
					field : 'execEndDate',
					title : '执行结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.execEndDate;
					}
				}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
					addBasicLawVersion('基本法版本信息新增', contextPath+"/basicLaw/addBasicLawVersionUrl");
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {updateBasicLawVersion();}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {deleteBasicLawVersion();}
				}, '-'],
		onLoadSuccess : function() {
			$('#list_basicLawVersionTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
//初始化页面表单信息
function initBasicLawVersionForm()
{
	$('#listv_execState').combobox({
		valueField:'code',
		textField:'codeName',
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=lawExecState'
	  });
}

//更新
function updateBasicLawVersion()
{
	var rows = $('#list_basicLawVersionTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个基本法版本进行修改", 'info');
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个基本版本法修改", 'info');
		return;
	}
	addBasicLawVersion('基本法版本信息更新', contextPath+"/basicLaw/updateBasicLawVersionUrl");
	
}

function addBasicLawVersion(title, href) 
{
	$('<div id="basicLawVersionWindow"/>').dialog({
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
function deleteBasicLawVersion() {
	var rows = $('#list_basicLawVersionTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择要删除的基本法版本", 'info');
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?basicLawId=" + n.basicLawId;
					else
						ps += "&basicLawId=" + n.basicLawId;
				});
				$.post('basicLaw/saveDeleteBasicLawVersionUrl' + ps, function(resultInfo) {
					
					$.messager.alert('提示', resultInfo.msg, 'info');
					$('#list_basicLawVersionTable').datagrid('cisreload');
					
				});
			}
		});
	}
}

// 表格查询
function searchBasicLawVersionInfo()
{
	$('#list_basicLawVersionTable').datagrid('options').url = "basicLaw/queryBasicLawVersionList";
	var queryParam = $("#list_basicLawVersionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	$('#list_basicLawVersionTable').datagrid('load',{queryParam:queryParam});
	
}
// 清空查询条件然后进行查询
function clearBasicLawVersionForm() {
	$('#list_basicLawVersionForm').form('clear');
	searchBasicLawVersionInfo();
}
function clearBasicLawVersionFormList() {
	$('#list_basicLawVersionForm').form('clear');
}