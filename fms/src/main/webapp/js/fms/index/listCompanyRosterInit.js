var roleId = null;

/**
 * 初始化通讯录列表
 */
jQuery(function($) {
	initCompanyRosterTable();
	roleId = $("#comRoster_roleId").val();
});

/**
 * 通讯录列表
 */
var comRosterList
function initCompanyRosterTable() {
	jQuery(function($) {
		var  selectIndex = -1;
		comRosterList = $('#companyRosterTable')
				.datagrid(
						{
							fitColumns : true,
							rownumbers : true,
							queryParams : {}, // 查询条件
							pagination : true,
							singleSelect : true,
							//url : contextPath+ '/index/queryListComRoster',
							pageList : [ 5, 10, 15, 20],
							pageSize : 10,
							columns : [ [ {
								field : 'ck',
								checkbox : true
							}, 
							{
								field : 'agentID',
								title : '员工ID',
								width : 10,
								hidden:true,
								sortable : true,
								formatter : function(value, row, index) {
									return row.agentID;
								}
							}, 
							{
								field : 'chnName',
								title : '姓名',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return  row.chnName;

								}
							}, {
								field : 'comID',
								title : '所属机构',
								width : 10,
								sortable : true,
								hidden : true,
								formatter : function(value, row, index) {
									return row.comID;
								}
							}, {
								field : 'comName',
								title : '所属机构',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.comName;
								}
							}, {
								field : 'positionCode',
								title : '职位',
								width : 10,
								sortable : true,
								hidden : true,
								formatter : function(value, row, index) {
									return row.positionCode;
								}
							} , {
								field : 'positionName',
								title : '职位',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.positionName;
								}
							} ,{
								field : 'mobile',
								title : '手机',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.mobile;
								}
								
							}, {
								field : 'officeTel',
								title : '公司固话',
								width : 10,
								formatter : function(value, row, index) {
									return row.officeTel;
								}
							},  {
								field : 'email',
								title : 'E-Mail',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.email;
								}
							}/*, {
								field : 'fax',
								title : '传真',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.fax;
								}
							}, {
								field : 'filiale',
								title : '所属分公司',
								width : 10,
								hidden : true,
								sortable : true,
								formatter : function(value, row, index) {
									return row.filiale;
								}
							}, {
								field : 'filialeCode',
								title : '所属分公司',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.filialeCode;
								}
							}*/] ],
							/*toolbar : [
									{
										id : 'add',
										text : '新增',
										iconCls : 'icon-add',
										handler : function() {
											var param = {};
											param.operate = "addComRoster";
											param = $.toJSON(param);
											addCompanyRosterTab(
													'通讯录信息新增',
													contextPath
															+ '/index/addCompanyRosterUrl?param='+param);
										}
									},
									'-',
									{
										id : 'update',
										text : '更新',
										iconCls : 'icon-edit',
										handler : function() {
											var rows = $('#companyRosterTable').datagrid('getSelections');
											if (rows.length == 0) {
												$.messager.alert("提示","请选择一条记录进行修改","info");
												return;
											}
											if (rows.length > 1) {
												$.messager.alert("提示","只能选择一条记录修改",'info');
												return;
											}
											var param = {};
											param.operate = "updateComRoster";
											param.companyRosterInfoId = rows[0].companyRosterInfoId;
											param = $.toJSON(param);
											
											addCompanyRosterTab('更新通讯录信息',contextPath+ "/index/addCompanyRosterUrl?param="+param);
										}
									}, '-', {
										id : 'remove',
										text : '删除',
										iconCls : 'icon-remove',
										handler : function() {
											deleteCompanyRoster();
										}
									} , '-', {
										id : 'import',
										text : '导入',
										iconCls : 'icon-redo',
										handler : function() {
											addCompanyRosterTab(
													'通讯录批量导入',
													contextPath+ '/index/importCompanyRosterUrl');
											}
									} ],*/
							onLoadSuccess : function() {
								$('#companyRosterTable')
										.datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	});
}

/**
 * 跳转窗口
 * @param title
 * @param href
 */
function addCompanyRosterTab(title, href) {
	$('<div id="addCompanyRosterWindow"/>').dialog({
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
 * 删除
 */
function deleteCompanyRoster() {
	var rows = $('#companyRosterTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的菜单","info");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?',function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?companyRosterInfoId=" + n.companyRosterInfoId;
					else
						ps += "&companyRosterInfoId=" + n.companyRosterInfoId;
				});
				$.post('index/deleteCompanyRoster' + ps,function(result) {
					$.messager.alert('提示', result.msg, 'info');
					searchCompanyRoster();
				});
			}
		});
	}
}

/**
 * 表格查询
 */
function searchCompanyRoster() {
	var queryparam = $("#CompanyRosterForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	if(queryparam!=null&&queryparam!=undefined&&queryparam!="{}"){
		comRosterList.datagrid('options').url = contextPath+"/index/queryListComRoster";
		comRosterList.datagrid('load',{param:queryparam});
	}else{
		comRosterList.datagrid('options').url = contextPath+"/index/queryListComRoster";
		comRosterList.datagrid('load',{param:queryparam});
	}
}

/**
 * 清空查询条件
 */
function clearCompanyRosterFormList() {
	$('#CompanyRosterForm').form('clear');
	searchCompanyRoster();
}