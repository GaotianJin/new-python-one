jQuery(function($) {
	$('#SinknessInfoTable').datagrid({
		title : '疾病信息', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 200, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : '', // 数据来源
		//sortName : 'dbid', // 排序的列
		//sortOrder : 'desc', // 倒序
		//remoteSort : false, // 服务器端排序
		idField : 'dbid', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : false, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'row.sicknessCode',
					title : '疾病代码',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessCode;
					} 
				}, {
					field : 'row.sicknessEnglishName',
					title : '疾病英文名称',
					width : 300,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessEnglishName;
					}
				},{
					field : 'row.sicknessChineseName',
					title : '疾病中文名称',
					width : 300,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessChineseName;
					}
				},{
					field : 'row.sicknessDescribe',
					title : '疾病描述',
					width : 300,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessDescribe;
					}
				}]],
		onLoadSuccess : function() {
			$('#SinknessInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	});

function querySinkness(){
	$('#SinknessInfoTable').datagrid({
		title : '疾病信息', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		height : 350, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : 'querySicknessList', // 数据来源
		sortName : 'dbid', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'dbid', // 主键字段
		resizable:true,
		queryParams : {sinknessCode:$('#sinknessCode').val(),sinknessName:$('#sinknessName').val()}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : false, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'row.sicknessCode',
					title : '疾病代码',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessCode;
					} 
				}, {
					field : 'row.sicknessEnglishName',
					title : '疾病英文名称',
					width : 240,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessEnglishName;
					}
				},{
					field : 'row.sicknessChineseName',
					title : '疾病中文名称',
					width : 240,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessChineseName;
					}
				},{
					field : 'row.sicknessDescribe',
					title : '疾病描述',
					width : 446,
					sortable : false,
					formatter : function(value, row, index) {
						return row.sicknessDescribe;
					}
				}]],
		onLoadSuccess : function() {
			$('#SinknessInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function getResult(){
	var  rows=$('#SinknessInfoTable').datagrid('getSelections');
	if(rows.length<=0){
		$.messager.alert('提示信息','请选择一条记录','info');
		return;
	}
	window.opener.document.getElementById("sicknessCode").value=rows[0].sicknessCode;
	window.opener.document.getElementById("sicknessChineseName").value=rows[0].sicknessChineseName;
    window.close();
	
}
