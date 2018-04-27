jQuery(function($) {
	initAllCombobox();
	initAgencyList();
	
});

function initAllCombobox() {
	$('#agencyType').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=agencytype',
		valueField : 'code',
		textField : 'codeName'
	});
	$('#agencyCode').combobox({
		
		valueField : 'code',
		textField : 'codeName',
		onShowPanel : function(){
		var	url = contextPath + '/codeQuery/agencyQuery';
		$(this).combobox("reload", url);
		}
	});

}
var agencyInputList;

function initAgencyList() {
	jQuery(function($) {
		var  selectIndex = -1;
		agencyInputList = $('#agencyComTable')
				.datagrid(
						{
							fitColumns : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							url : contextPath+ '/cooperation/queryListAgencyCom',
							pageList : [ 5, 10, 15, 20],
							pageSize : 10,
							columns : [ [ {
								field : 'ck',
								checkbox : true
							}, 
							{
								field : 'agencyComId',
								title : '基金管理人ID',
								width : 10,
								hidden:true,
								sortable : true,
								formatter : function(value, row, index) {
									return row.agencyComId;
								}
							}, 
							{
								field : 'agencyCode',
								title : '基金管理人代码',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									var param = {};
									param.operate = "agencyDetail";
									param.agencyComId = row.agencyComId;
									param = $.toJSON(param);
									return  "<a href='#'  onclick=addAgencyComTab('基金管理人明细信息','"+contextPath+"/cooperation/addAgencyComUrl?param="+param+"')>"+row.agencyCode+"</a>";

								}
							}, {
								field : 'agencyName',
								title : '基金管理人名称',
								width : 10,
								formatter : function(value, row, index) {
									return row.agencyName;
								}
							}, {
								field : 'agencyType',
								title : '基金管理人类型',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.agencyType;
								}
								
							}, {
								field : 'contactsName',
								title : '第一联系人名称',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.contactsName;
								}
							}, {
								field : 'position',
								title : '职位',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.position;
								}
							}, {
								field : 'mobile',
								title : '手机',
								width : 10,
								sortable : true,
								formatter : function(value, row, index) {
									return row.mobile;
								}
							} ] ],
							toolbar : [
									{
										text : '新增',
										iconCls : 'icon-add',
										handler : function() {
											var param = {};
											param.operate = "addAgency";
											param = $.toJSON(param);
											addAgencyComTab(
													'基金管理人信息新增',
													contextPath
															+ '/cooperation/addAgencyComUrl?param='+param);
										}
									},
									'-',
									{
										text : '更新',
										iconCls : 'icon-remove',
										handler : function() {
											var rows = $('#agencyComTable').datagrid('getSelections');
											if (rows.length == 0) {
												$.messager.alert("提示","请选择一条记录进行修改","info");
												return;
											}
											if (rows.length > 1) {
												$.messager.alert("提示","只能选择一条记录修改",'info');
												return;
											}
											var param = {};
											param.operate = "updateAgency";
											param.agencyComId = rows[0].agencyComId;
											param = $.toJSON(param);
											
											addAgencyComTab('更新基金管理人信息',contextPath+ "/cooperation/addAgencyComUrl?param="+param);
										}
									}, '-', {
										text : '删除',
										iconCls : 'icon-edit',
										handler : function() {
											deleteAgencyCom();
										}
									} ],
							onLoadSuccess : function() {
								$('#agencyComTable')
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

function addAgencyComTab(title, href) {
	$('<div id="agencyComWindow"/>').dialog({
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
function deleteAgencyCom() {
	var rows = $('#agencyComTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的菜单","info");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?',function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?agencyComId=" + n.agencyComId;
					else
						ps += "&agencyComId=" + n.agencyComId;
				});
				$.post('cooperation/deleteAgencyCom' + ps,function(result) {
					$.messager.alert('提示', result.msg, 'info');
					searchAgencyCom();
				});
			}
		});
	}
}

// 表格查询
function searchAgencyCom() {
	var params = $('#agencyComTable').datagrid('options').queryParams; // 先取得
	var fields = $('#AgencyComForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		params[field.name] = (field.value); // 设置查询参数
	});
	$('#agencyComTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}
// 清空查询条件然后进行查询
function clearAgencyCom() {
	$('#AgencyComForm').form('clear');
	//searchAgencyCom();
}
//清空查询条件然后进行查询
function clearAgencyComFormList() {
	$('#AgencyComForm').form('clear');
}
