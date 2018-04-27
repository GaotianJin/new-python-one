jQuery(function($) {
	initAllCombobox();
	initBuildList();
	queryBuildList();
	});

function initAllCombobox(){
	//楼盘类型
	$('#listBuildingType').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=buildingtype&code=1',
		valueField:'code',
		textField:'codeName'
	  });
	
	//物业类型 
	$('#listEstatetype').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=estatetype',
		valueField:'code',
		textField:'codeName'
	  });
}
var buildInputList;
function initBuildList(){
    buildInputList = $('#buildTable').datagrid({
	fitColumns:true, 
	rownumbers:true,
	pagination:true,
	singleSelect:true,
	pageSize:10,
	pageList:[5,10,15,20,25,30],
	columns:[[  
		{field:'ck',checkbox:true},
		{field:'buildingId',	title:'楼盘id',	hidden:true},
		{field:'buildingCode',	title:'楼盘代码',	width:15,	
			formatter:function(value, row, index){
				var param = {};
				param.operate = "detalBuild";
				param.buildingId = row.buildingId;
				param = $.toJSON(param);
				return "<a href='#'  onclick=addbuildtab('楼盘信息','"+contextPath+"/branch/detailBuildUrl?param="+param+"')>"+row.buildingCode+"</a>";}},    
		{field:'buildingName',	title:'楼盘名称',	width:15},
		{field:'buildingTypeName',title:'楼盘类型',width:15}, 
		{field:'estateTypeName',title:'物业类型',	width:20},
		{field:'stayNum',		title:'户数',		width:15},    
		{field:'stayRate',		title:'入住率(%)',	width:15},	
		{field:'avgPrice',		title:'房屋均价(元/㎡)',width:20},
		{field:'address',		title:'楼盘地址',	width:50},
		{field:'zipCode',		title:'邮政编码',	width:20}
		]],
	 toolbar: [{
		iconCls: 'icon-add',
		text : '新增',
		handler: function(){
			var param = {};
			param.operate = "addBuild";
			param = $.toJSON(param);
		   	addbuildtab('新增楼盘信息', contextPath+"/branch/addBuildUrl?param="+param);
		}
		},'-',{
			iconCls: 'icon-edit',
		   	text : '更新',
		   	handler: function(){
		   		updateBuild();}
		 },'-',{
		   	iconCls: 'icon-remove',
		   	text : '删除',
		   	handler: function(){deleteBuild();}
		 }],
		 onLoadSuccess : function() {
		   	$("#buildTable").datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
    });
}

//楼盘添加
function addbuildtab(title, href) {
	$('<div id="addBuildWindow"/>').dialog({
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
function updateBuild(){
	var rows = $('#buildTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择一个楼盘进行修改");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一个楼盘修改");
		return;
	}
	var param = {};
	var buildingId = rows[0].buildingId;
	param.operate = "updateBuild";
	param.buildingId=buildingId;
	param = $.toJSON(param);
	addbuildtab('楼盘信息更新', contextPath+"/branch/updateBuildUrl?param="+param);
}

// 删除
function deleteBuild() {
	var rows = $('#buildTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择要删除的楼盘");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = ""; 
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?buildingId=" + n.buildingId;
					else
						ps += "&buildingId=" + n.buildingId;
				});
				$.post('branch/delBuild' + ps, function(reData){
						if(reData.success){
							queryBuildList();
							$.messager.alert('提示', reData.msg);
						}else{
							$.messager.alert('提示', reData.msg);
						}
				});
			}
		});
	}
}


function queryBuildList(){
	buildInputList.datagrid('options').url = "branch/queryBuildListUrl";
	var queryParam = $("#buildForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	buildInputList.datagrid('load',{queryParam:queryParam});
}

// 清空查询条件然后进行查询
function clearForm(){
	$('#buildForm').form('clear');
}