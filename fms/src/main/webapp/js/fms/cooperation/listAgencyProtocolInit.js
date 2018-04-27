jQuery(function($) {
	initAllCombobox();
	initAgencyProtocolList();
});

function initAllCombobox(){
	//合作协议类型
	$('#list_protocolType').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolType',
		valueField : 'code',
		textField : 'codeName'
	});
	//合作机构
	$('#list_agencyComId').combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	//协议名称
	/*$('#list_protocolCode').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolQuery',
		valueField : 'code',
		textField : 'codeName'
	});*/

}

var agencyProtocolList;

function initAgencyProtocolList() {
	jQuery(function($) {
		var  selectIndex = -1;
		agencyProtocolList = $('#agencyProtocolTable')
				.datagrid(
						{
							//title : '合作协议信息列表', // 标题
							method : 'post',
							singleSelect : true, // 单选
							fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
							striped : false, // 奇偶行颜色不同
							collapsible : true,// 可折叠
							url : contextPath+ '/cooperation/queryListAgencyComProtocol',
							sortName : 'id', // 排序的列
							sortOrder : 'desc', // 倒序
							remoteSort : true, // 服务器端排序
							idField : 'id', // 主键字段
							queryParams : {}, // 查询条件
							pagination : true, // 显示分页
							rownumbers : true, // 显示行号
							pageList : [ 5, 10, 15, 20 ],
							pageSize : 10,
							columns : [ [ {
								field : 'ck',
								checkbox : true,
								width : 2
							}, // 显示复选框

							{
								field : 'agencyComId',
								title : '基金管理人ID',
								width : 10,
								hidden : true,
								sortable : true,
								formatter : function(value, row, index) {
									return row.agencyComId;
								}
							},

							{
								field : 'agencyCode',
								title : '基金管理人代码',
								width : 100,
								sortable : true,
								formatter : function(value, row, index) {
									var param = {};
									param.operate = "protocolDetail";
									param.agencyComId = row.agencyComId;
									param.protocolId = row.protocolId;
									param.protocolType = row.protocolTypeCode;
									param = $.toJSON(param);
									return  "<a href='#'  onclick=addAgencyComProtocolTab('合作协议明细信息','"+contextPath+"/cooperation/addAgencyProtocolUrl?param="+param+"')>"+row.agencyCode+"</a>";

								}
							},

							{
								field : 'agencyName',
								title : '基金管理人名称',
								width : 100,
								sortable : true,
								formatter : function(value, row, index) {
									return row.agencyName;
								}
							}, 
							{
								field : 'protocolId',
								title : '协议流水号',
								width : 100,
								hidden:true,
								sortable : true,
								formatter : function(value, row, index) {
									return row.protocolId;
								}
							},
							{
								field : 'protocolCode',
								title : '协议编码',
								width : 100,
								sortable : true,
								formatter : function(value, row, index) {
									return row.protocolCode;
								}
							},

							{
								field : 'protocolName',
								title : '协议名称',
								width : 200,
								sortable : true,
								formatter : function(value, row, index) {
									return row.protocolName;
								}
							},

							{
								field : 'protocolType',
								title : '协议类型',
								width : 200,
								sortable : true,
								formatter : function(value, row, index) {
									return row.protocolType;
								}
							},
							{
								field : 'protocolTypeCode',
								title : '协议类型',
								width : 100,
								hidden:true,
								sortable : true,
								formatter : function(value, row, index) {
									return row.protocolTypeCode;
								}
							},
							{
								field : 'protocolState',
								title : '协议状态',
								width : 100,
								sortable : true,
								formatter : function(value, row, index) {
									return row.protocolState;
								}
							}] ],
							toolbar : [
									{
										text : '新增',
										iconCls : 'icon-add',
										handler : function() {
											var param = {};
											param.operate = "addProtocol";
											//param.protocolId = row.protocolId;
											//param.protocolType = row.protocolType
											param = $.toJSON(param);
											addAgencyComProtocolTab('协议信息新增',contextPath+ '/cooperation/addAgencyProtocolUrl?param='+param);
										}
									},
									'-',
									{
										text : '更新',
										iconCls : 'icon-remove',
										handler : function() {
											var rows = $('#agencyProtocolTable').datagrid('getSelections');
											if (rows.length == 0) {
												$.messager.alert("提示","请选择一条协议信息进行修改","info");
												return;
											}
											if (rows.length > 1) {
												$.messager.alert("提示","只能选择一条协议信息修改","info");
												return;
											}
											//$("#modifyAgencyComId").val( rows[0].agencyComId);
											//$("#modifyProtocolType").val( rows[0].protocolTypeCode);
											//$("#modifyProtocolId").val( rows[0].protocolId);
											var param = {};
											param.operate = "updateProtocol";
											param.agencyComId = rows[0].agencyComId;
											param.protocolId = rows[0].protocolId;
											param.protocolType = rows[0].protocolTypeCode;
											param = $.toJSON(param);
											addAgencyComProtocolTab('更新基金管理人协议信息',contextPath+ "/cooperation/addAgencyProtocolUrl?param="+param);
										}
									}, '-', {
										text : '删除',
										iconCls : 'icon-edit',
										handler : function() {
											deleteAgencyComProtocol();
										}
									} ],
							onLoadSuccess : function() {
								$('#agencyProtocolTable').datagrid(
										'clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
		$('#agencytype').combobox({
			url : contextPath + '/codeQuery/tdCodeQuery?codeType=agencytype',
			valueField : 'code',
			textField : 'codeName'
		});
	});
}

function queryAgencyProtocolList(){
	agencyProtocolList.datagrid('options').url = contextPath+"/cooperation/queryListAgencyComProtocol";
	var queryParam = $("#listAgencyProtocolForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	queryParam = decodeURI(queryParam);
	agencyProtocolList.datagrid('load',{queryParam:queryParam});	
}

function clearAgencyProtocol(){
	$("#listAgencyProtocolForm").form("clear");
	queryAgencyProtocolList();
}

function clearAgencyProtocolList(){
	$("#listAgencyProtocolForm").form("clear");
}
//删除
function deleteAgencyComProtocol() {
	
	var rows = $('#agencyProtocolTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的协议信息","info");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?',function(result) {
			if (result) {
				var param = {};
				param.rows = $.toJSON(rows);
				// 发送请求，后台接受数据进行处理
				$.ajax({
					type : 'post',
					url : contextPath + "/cooperation/deleteAgencyComProtocol",
					data : 'param=' + $.toJSON(param),
					cache : false,
					success : function(resultInfo) {
						try {
							if (resultInfo.success) {
								$.messager.alert('提示', resultInfo.msg, 'info');
								$.messager.progress('close');
								clearAgencyProtocol();
							} else {
								$.messager.alert('提示', resultInfo.msg,"info");
							}
						} catch (e) {
							$.messager.alert('提示', e.message);
						}
					}
				});
			}
		});
	}
}


function addAgencyProtocltab(title, href) {
	$('<div id="agencyProtocolWindow"/>').dialog({
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

function addAgencyComProtocolTab(title, href) {
	$('<div id="agencyProtocolWindow"/>').dialog({
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

function updateAgencyProtocltab(title, href) {
	if (href) {
		var content = "<iframe scrolling='auto' frameborder='0'  src='" + href
				+ "' style='width:100%;height:100%;'></iframe>";
	} else {
		var content = '未实现';
	}
	if ($('#agencyProtocolTab').tabs('exists', title)) {
		$('#agencyProtocolTab').tabs('close', title);
		$('#agencyProtocolTab').tabs('add', {
			title : title,
			content : content,
			closable : true
		});
	} else {
		$('#agencyProtocolTab').tabs('add', {
			title : title,
			content : content,
			closable : true
		});
	}
}