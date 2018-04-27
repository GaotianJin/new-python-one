/**
 * 初始化列表
 */
jQuery(function($) {
	initProNewsEditorTable();
});

/**
 * 行业新闻列表
 */
var proNewsEditorTable;
function initProNewsEditorTable() {
		var  selectIndex = -1;
		proNewsEditorTable = $('#proNewsEditorTable')
				.datagrid(
						{
							method : 'post',
							//iconCls : 'icon-edit', // 图标
							singleSelect : true, // 多选
							//height : 380, // 高度
							fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
							striped : true, // 奇偶行颜色不同
							collapsible : true,// 可折叠
							url : contextPath+"/index/queryProfessionNewsList",
							sortName : 'id', // 排序的列
							sortOrder : 'desc', // 倒序
							remoteSort : true, // 服务器端排序
							idField : 'id', // 主键字段
							queryParams : {"queryParam":{}}, // 查询条件
							pagination : true, // 显示分页
							rownumbers : true, // 显示行号
							pageList:[5,10,15,20],
							pageSize:10,
							columns : [[
							            {
		                                    field : 'ck',
		                                    checkbox : true,
		                                    width : 2
	                                    }, // 显示复选框
									{
										field : 'professionNewsInfoId',
										title : 'id',
										hidden : true,
										width : 1000,
									    formatter : function(value, row, index) {
										return row.professionNewsInfoId;
									    }
									},{
										field : 'title',
										title : '标题',
										//hidden : true,
										width : 1000,
									    formatter : function(value, row, index) {
										var param = {};
										param.professionNewsInfoId = row.professionNewsInfoId;
										return "<a href = '#' onclick=proNewsEditorTab('产品报告详情','"
										+contextPath+"/index/listProfessionNewsDetailUrl?param="+$.toJSON(param)+"')>"+row.title+"</a>";
									    }
									},{
										field : 'modifyDate',
										title : '上传时间',
										width : 1000,
										height : 100,
									    formatter : function(value, row, index) {
										return row.modifyDate;
									    }
									}]],
									toolbar : [
												{
													text : '上传',
													iconCls : 'icon-add',
													handler : function() {
														$('<div id="uploadProNewsDialog"/>')
														.dialog(
																{
																	href : contextPath + "/index/proNewsUploadFileUrl",
																	modal : true,
																	fit : true,
																	title : '文件上传',
																	inline : false,
																	minimizable : false,
																	onClose : function() {
																		$(this).dialog('destroy');
																		initProNewsEditorTable();
																		initListProfessionNewsTable();
																	}
																});
												}
												},
												'-',
												{
													text : '更新',
													iconCls : 'icon-remove',
													handler : function() {
														var rows = $('#proNewsEditorTable').datagrid('getSelections');
														if (rows.length == 0) {
															$.messager.alert("提示","请选择一条记录进行修改","info");
															return;
														}
														if (rows.length > 1) {
															$.messager.alert("提示","只能选择一条记录修改",'info');
															return;
														}
														var param = {};
														param.professionNewsInfoId = rows[0].professionNewsInfoId;
														param = $.toJSON(param);
														$('<div id="updProNewsDialog"/>')
														.dialog(
																{
																	href : contextPath + "/index/updateProNewsFileUrl?param="+param,
																	modal : true,
																	fit : true,
																	title : '更新文件',
																	inline : false,
																	minimizable : false,
																	onClose : function() {
																		$(this).dialog('destroy');
																		initProNewsEditorTable();
																		initListProfessionNewsTable();
																	}
																});
													}
												}, '-', {
													text : '删除',
													iconCls : 'icon-edit',
													handler : function() {
														deleteProNews();
													}
												} ],
							onLoadSuccess : function() {
								$('#professionNewsInfoId').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
							}
							,onClickRow: function (rowIndex, rowData) {
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

/**
 * 跳转窗口
 * @param title
 * @param href
 */
function proNewsEditorTab(title, href) {
	$('<div id="proNewsEditorWindow"/>').dialog({
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
function deleteProNews() {
	var rows = $('#proNewsEditorTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的信息","info");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?',function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?professionNewsInfoId=" + n.professionNewsInfoId;
					else
						ps += "&professionNewsInfoId=" + n.professionNewsInfoId;
				});
				$.post('index/deleteProNews' + ps,function(result) {
					$.messager.alert('提示', result.msg, 'info');
					initProNewsEditorTable();
				});
			}
		});
	}
}

/**
 * 返回按钮
 */
function backList(){
	$('#professionEditorFileDialog').window('destroy');
	parent.clearFormInfo();
}
