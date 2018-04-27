jQuery(function($) {
	initAllCombobox();
	initAgentList();
	});

function initAllCombobox(){
	// 分公司：代码-名称
	$("#comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	
 // 网点：代码-名称
   /* $("#storeId").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
 // 团队：代码-名称
    $("#departmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});	
    //财富顾问
	$("#agentId").combobox({
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
			/*var storeId = $("#storeId").combobox("getValue");*/
			var departmentId = $("#departmentId").combobox("getValue");
			/*param.storeId = storeId;*/
			param.departmentId = departmentId;
			if(/*(storeId==null||storeId==""||storeId==undefined)&&(*/departmentId==null||departmentId==""||departmentId==undefined){
				var comId = $("#comId").combobox("getValue");
				if(comId==null||comId==""||comId==undefined){
					var url = contextPath+'/codeQuery/agentQuery';
				}else{
					var url = contextPath + '/codeQuery/defAgentQuery?codeType='+ comId;
				}
			}else{
				var url = contextPath + '/codeQuery/limitAgentInfo?param='+ encodeURI($.toJSON(param));
			}
			$(this).combobox("clear");
			$(this).combobox("reload",url);
		}
	});
	$('#workState').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=workState',
		valueField:'code',
		textField:'codeName'
	});
	
	$('#positionCode').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=positionCode',
		valueField:'code',
		textField:'codeName'
	});
	
	$('#gradeCode').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=gradeCode',
		valueField:'code',
		textField:'codeName'
	});
}


var agentInputList;
function initAgentList(){
	var  selectIndex = -1;
	agentInputList=$('#agentTable').datagrid({
		fitColumns:true, 
		rownumbers:true,
		pagination:true,
		singleSelect:true,
		url:contextPath+'/sales/queryAgentList',
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		columns:[[  
		     {field:'ck',checkbox:true},
		     {
		    	 field:'agentId',	
		    	 title:'财富顾问流水号',
		    	 hidden:true
		     },
		     {
		    	 field:'agentCode',	
		    	 title:'工号',
		    	 width:10,
		    	 formatter : function(value, row, index) {
		    		var param = {};
					param.operate = "agentDetail";
					param.agentId = row.agentId;
					param = $.toJSON(param);
					return "<a href='#'  onclick=addAgenttab('员工信息明细','"+contextPath+"/sales/addAgentUrl?param="+param+"')>"+row.agentCode+"</a>";
					//addAgenttab('财富顾问信息修改', contextPath+"/sales/addAgentUrl?param="+param);
		    	 }
		     },
		     {
		    	 field:'agentName',	
		    	 title:'姓名',			
		    	 width:10
		     },
		     {
		    	 field:'mobile',		
		    	 title:'手机',			
		    	 width:10
		     },
		     {
		    	 field:'comId',		
		    	 title:'所属机构',		
		    	 hidden:true
		     },
		     {
		    	 field:'comName',		
		    	 title:'所属机构',		
		    	 width:10
		     },
		     {
		    	 field:'storeId',		
		    	 title:'所属网点',		
		    	 hidden:true
		     },
		     {
		    	 field:'storeName',		
		    	 title:'所属网点',
		    	 hidden:true,
		    	 width:10
		     },
		     {
		    	 field:'departmentId',		
		    	 title:'所属部门',		
		    	 hidden:true
		     },
		     {
		    	 field:'departmentName',		
		    	 title:'所属部门',		
		    	 width:10
		     },
		     {
		    	 field:'workState',	
		    	 title:'在职状态',		
		    	 hidden:true
		     },
		     {
		    	 field:'workStateName',	
		    	 title:'在职状态',		
		    	 width:10
		     },
		     {
		    	 field:'joinDate',		
		    	 title:'入司日期',		
		    	 width:10
		     },
		     {
		    	 field:'positionCode',	
		    	 title:'职级',			
		    	 hidden:true
		     },
		     {
		    	 field:'positionCodeName',	
		    	 title:'职位',			
		    	 width:10
		     },
		     {
		    	 field:'gradeCode',	
		    	 title:'级别',			
		    	 hidden:true
		     },
		     {
		    	 field:'gradeCodeName',	
		    	 title:'级别',			
		    	 width:10
		     }]],
		     toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						//alert("add");
						var param = {};
						param.operate = "addAgent";
						param = $.toJSON(param);
						addAgenttab('员工信息新增', contextPath+"/sales/addAgentUrl?param="+param);
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = agentInputList.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个员工进行修改","info");
							return;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","只能选择一个员工进行修改","info");
							return;
						}
						//console.info(rows);
						var agentId = rows[0].agentId;
						var param = {};
						param.operate = "updateAgent";
						param.agentId = agentId;
						param = $.toJSON(param);
						//console.info("=====agentId======"+agentId);
						addAgenttab('员工信息修改', contextPath+"/sales/addAgentUrl?param="+param);
						//addAgenttab('财富顾问信息修改', contextPath+"/sales/updateAgentUrl?operate='updateAgent'&agentId="+agentId);
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var rows = agentInputList.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个员工进行删除","info");
							return;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","只能选择一个员工进行删除","info");
							return;
						}
						$.messager.confirm('提示信息', '您确定要删除员工信息吗？', function(r){
							if (r){
								//console.info(rows);
								var agentId = $.toJSON(rows[0].agentId);
								deleteAgent(agentId);
							}
						});
					}
				}, '-', {
					text : '导出基本信息',
					iconCls : 'icon-redo',
					handler : function() {
						$.messager.confirm('提示信息', '您确定要导出员工基本信息吗？', function(r){
							if (r){
								exportAgentsInfo();
							}
						});
					}
				}],
				onLoadSuccess : function() {
					$('#agentTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function queryAgentList() {
	agentInputList.datagrid('options').url = "sales/queryAgentList";
	var queryParam = $("#listAgent_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	agentInputList.datagrid('load',{queryParam:queryParam});	
}

function addAgenttab(title, href) {
		$('<div id="addAgentWindow"/>').dialog({
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
 *删除财富顾问信息 
 */
function deleteAgent(agentId){
	var param = {};
	param.agentId = agentId;
	$.ajax({
		type:'post',
		url:contextPath+"/sales/deleteAgent",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
		//console.info(reData);
			try {
				//var result = $.parseJSON(reData);
				if(reData.success){
					$.messager.alert('提示', "成功删除员工信息", 'info');
					agentInputList.datagrid('reload');
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
	};

function clearQueryAgentCondition(){
	$('#joinDateBegin').datebox('setValue', formatterDate(new Date()));
	$('#joinDateEnd').datebox('setValue', formatterDate(new Date()));
	$("#listAgent_queryConditionForm").form("clear");
	//$("#joinDateBegin").datebox();
	
	
}


//导出员工基本信息
function exportAgentsInfo(){
//	var queryParam = {};
	var queryParam = $("#listAgent_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
//	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath+'/sales/exportAgentInfo.xls?queryParam='+queryParam);
}


