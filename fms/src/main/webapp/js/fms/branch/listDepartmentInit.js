jQuery(function($) {
	initAllCombobox();
	initDepartmentList();
	searchDepartment();
	});

function initAllCombobox(){
	// 分公司：代码-名称
	$("#ywbParentComCode").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
 // 业务部：代码-名称
    $("#ywbdepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#ywbParentComCode").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});	
}

var departmentInputList;
function initDepartmentList(){
	var  selectIndex = -1;
    buildInputList = $('#departmentTable').datagrid({
    title : '部门信息列表', // 标题
    iconCls : 'icon-edit', // 图标
	fitColumns:true, 
	rownumbers:true,
	pagination:true,
	singleSelect:true,
	url:contextPath+'/branch/queryDepartmentList',
	pageSize:10,
	pageList:[5,10,15,20,25,30],
	columns:[[  
		{field:'ck',checkbox:true},
		{field:'departmentId',	title:'部门流水号',	hidden:true},
		{field:'comId',			title:'机构流水号',		hidden:true},
		{field:'departmentCode',title:'部门代码',		width:10,	
			formatter:function(value, row, index){
				var param = {};
				param.operate = "detalDepartment";
				param.departmentId = row.departmentId;
				param = $.toJSON(param);
				return "<a href='#' onclick=addDepartmenttab('部门信息','"+contextPath+"/branch/detailDepartmentUrl?param="+param+"')>"+row.departmentCode+"</a>";}},   
		{field:'departmentName',title:'部门名称',		width:10},
		{field:'comIdName',		title:'上级机构',		width:10},
		{field:'stateName',		title:'部门状态',		width:20}, 
		{field:'managerName',	title:'部门长',		width:20},	
		{field:'managerMobile',	title:'部门长手机号',		width:20},
		{field:'foundDate',		title:'成立日期',		width:20}, 
		{field:'endDate',		title:'结束日期',		hidden:true},    
		{field:'belongStartDate',title:'归属开始日期',	hidden:true},
		{field:'belongEndDate',	title:'归属结束日期',	hidden:true},
		{field:'rcState',		title:'记录状态',		hidden:true},
		{field:'createDate',	title:'创建时间',		hidden:true},
		{field:'modifyDate',	title:'修改时间',		hidden:true},
		{field:'operComId',		title:'操作机构',		hidden:true},
		{field:'createUserId',	title:'创建操作员',		hidden:true},
		{field:'modifyUserId',	title:'修改操作员',		hidden:true}
		]],
	 toolbar: [{
		iconCls: 'icon-add',
		text : '新增',
		handler: function(){
			var param = {};
			param.operate = "addDepartment";
			param = $.toJSON(param);
		   	addDepartmenttab('新增部门信息', contextPath+'/branch/addDepartmentUrl');
		}
		},'-',{
			iconCls: 'icon-edit',
		   	text : '更新',
		   	handler: function(){updateDepartment();}
		 },'-',{
		   	iconCls: 'icon-remove',
		   	text : '删除',
		   	handler: function(){deleteDepartment();}
		 }],
		 onLoadSuccess : function() {
		   	$("#departmentTable").datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function addDepartmenttab(title, href) {
	$('<div id="addDepartmentWindow"/>').dialog({
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

//更新
function updateDepartment(){
	var rows = $('#departmentTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个部门进行修改");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个部门修改");
		return;
	}
	
	var param = {};
	var departmentId = rows[0].departmentId;
	param.operate = "updateDepartment";
	param.departmentId=departmentId;
	param = $.toJSON(param);
	addDepartmenttab('部门信息更新', contextPath+"/branch/updateDepartmentUrl?param="+param);
}

	// 删除
function deleteDepartment() {
	var rows = $('#departmentTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择要删除的部门");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = ""; 
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?departmentId=" + n.departmentId;
					else
						ps += "&departmentId=" + n.departmentId;
				});
				$.post('branch/deleteDepartment' + ps, function(reData){
						if(reData.success){
							$.messager.alert('提示', reData.msg);
							searchDepartment();
						}else{
							$.messager.alert('提示', reData.msg);
						}
				});
			}
		});
	}
}
	
	// 表格查询
	function searchDepartment(){
		var params = $('#departmentTable').datagrid('options').queryParams; // 先取得
		var fields = $('#departmentForm').serializeArray(); // 自动序列化表单元素为JSON对象
		$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
		$('#departmentTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	}
	
	// 清空查询条件然后进行查询
	function clearForm(){
		$('#departmentForm').form('clear');
	}