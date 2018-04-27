jQuery(function($){
	initManagerTable();
})

var managerInputList;
function initManagerTable(){
	var  selectIndex = -1;
	managerInputList=$('#managerTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/manager/queryManagerList',
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'managerId',	
		    	 title:'内勤人员流水号',
		    	 hidden:true
		     },
		     {
		    	 field:'chnName',	
		    	 title:'姓名',			
		    	 width:10
		     },
		     {
		    	 field:'mobile',		
		    	 title:'手机',			
		    	 width:10
		     }]],
		     toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						var param = {};
						param.operate = "addManager";
						param = $.toJSON(param);
						addManagerTab('新增内勤人员信息', contextPath+"/manager/addManagerUrl?param="+param);
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = managerInputList.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个内勤人员进行修改","info");
							return;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","只能选择一个内勤人员进行修改","info");
							return;
						}
						var managerId = rows[0].managerId;
						var param = {};
						param.operate = "updateManager";
						param.managerId = managerId;
						param = $.toJSON(param);
						addManagerTab('内勤人员信息修改', contextPath+"/manager/addManagerUrl?param="+param);
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var rows = managerInputList.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个内勤人员进行删除","info");
							return;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","只能选择一个内勤人员进行删除","info");
							return;
						}
						$.messager.confirm('提示信息', '您确定要删除内勤人员信息吗？', function(r){
							if (r){
								var managerId = $.toJSON(rows[0].managerId);
								deleteManager(managerId);
							}
						});
					}
				}],
				onLoadSuccess : function() {
					$('#managerTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function queryManagerList(){
	managerInputList.datagrid('options').url = "manager/queryManagerList";
	var queryParam = $("#listManager_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	managerInputList.datagrid('load',{queryParam:queryParam});
}

function addManagerTab(title, href) {
	$('<div id="addManagerWindow"/>').dialog({
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

/**
 *删除内勤人员信息 
 */
function deleteManager(managerId){
	var param = {};
	param.managerId = managerId;
	$.ajax({
		type:'post',
		url:contextPath+"/manager/deleteManager",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', "成功删除内勤人员信息", 'info');
					managerInputList.datagrid('reload');
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function clearQueryManagerCondition(){
	$("#listManager_queryConditionForm").form("clear");
}