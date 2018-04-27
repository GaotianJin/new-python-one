// 页面加载完成
jQuery(function($) {
	
	var businessType = $('#businessType').val();
	if("01" == businessType){
		$('#noteType').removeClass('table_input');
		$('#noteType').addClass('table_select');
		var url = "../note/queryNoteType?businessType="+ businessType;
		$('#noteType').combobox({
			  url: url,
			  valueField:'id',
			  textField:'name'
		});
		$('#noteType').combobox('setValue', '1');  
		
	}else{
		$('#noteType').removeClass('table_select');
		$('#noteType').addClass('table_input');
		$('#noteType').validatebox({ 
			required: true, 
			validType: 'length[0,10]' 
			}); 


	}
	
	searchNote();
});

// 保存照会
function saveNote() {

	if ($('#noteForm').form('validate')) {
		sAlert("the message you want to display");
		var noteContent = $('#noteContent').val();
		if (noteContent.length > 120) {
			error("照会内容长度不能超过120字");
			return;
		}

		$('.disableClass').removeAttr('disabled');
		$.post("saveNote", $("#noteForm").serializeArray(), function(data) {
			$.messager.alert('提示', data.msg, 'info');
			searchNote();
			clearNote();
			$('.disableClass').attr('disabled', 'disabled');
		});
		cAlert();
	}

}

// 解决照会
function closeNote() {

	sAlert("the message you want to display");

	var data = $('#noteTable').datagrid('getSelected');
	if (data == null) {
		error("请选择一条记录！");
		return;
	}

	var noteStatus = data.noteStatus;
	if (noteStatus == "03") {
		error("照会已撤销！");
		return;
	}
	if (noteStatus == "02") {
		error("照会已解决！");
		return;
	}

	$('.disableClass').removeAttr('disabled');
	$('#id').val(data.id);
	$.post("closeNote", $("#noteForm").serializeArray(), function(data) {
		$.messager.alert('提示', data.msg, 'info');
		searchNote();
		clearNote();
		$('.disableClass').attr('disabled', 'disabled');
	});
	cAlert();
}

// 撤销照会
function cancelNote() {

	sAlert("the message you want to display");

	var data = $('#noteTable').datagrid('getSelected');
	if (data == null) {
		error("请选择一条记录！");
		return;
	}

	var noteStatus = data.noteStatus;
	if (noteStatus == "03") {
		error("照会已撤销！");
		return;
	}
	if (noteStatus == "02") {
		error("照会已解决！");
		return;
	}

	$('.disableClass').removeAttr('disabled');
	$('#id').val(data.id);
	$.post("cancelNote", $("#noteForm").serializeArray(), function(data) {
		$.messager.alert('提示', data.msg, 'info');
		searchNote();
		clearNote();
		$('.disableClass').attr('disabled', 'disabled');
	});
	cAlert();

}
// 清空
function clearNote() {
	$('#id').val("");
	$('#noteStatus').val("");
	//$('#noteType').val("");
	$('#noteContent').val("");
}

// 查询
function searchNote() {

	var policyNo = $('#policyNo').val();
	var businessNo = $('#businessNo').val();
	var businessType = $('#businessType').val();

	$('#noteTable').datagrid(
			{
				title : '照会列表', // 标题
				method : 'post',
				iconCls : 'icon-edit', // 图标
				singleSelect : true, // 多选
				height : 373, // 高度
				fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped : true, // 奇偶行颜色不同
				collapsible : true,// 可折叠
				url : "../note/queryList?policyNo=" + policyNo + "&businessNo="
						+ businessNo + "&businessType=" + businessType, // 数据来源
				sortName : 'id', // 排序的列
				sortOrder : 'asc', // 倒序
				remoteSort : true, // 服务器端排序
				idField : 'id', // 主键字段
				queryParams : {}, // 查询条件
				pagination : true, // 显示分页
				rownumbers : true, // 显示行号
				columns : [ [ {
					field : 'ck',
					checkbox : true,
					width : 2
				}, {
					field : 'id',
					title : '照会编号',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						return row.id;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'noteType',
					title : '照会类型',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						if("01" == businessType){
							return row.noteTypeName;
						}else{
							return row.noteType;
						}
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'policyNo',
					title : '保单号',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.policyNo;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'businessType',
					title : '任务类型',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						if ("01" == value) {
							return "保全";
						}
						if ("02" == value) {
							return "理赔";
						}
						// return row.businessType;
					}
				}, {
					field : 'businessNo',
					title : '任务号码',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.businessNo;
					}
				}, {
					field : 'note_Status',
					title : '照会状态',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						if ("01" == row.noteStatus) {
							return "处理中";
						}
						if ("02" == row.noteStatus) {
							return "已解决";
						}
						if ("03" == row.noteStatus) {
							return "已关闭";
						}
						// return row.noteStatus;
					}
				}, {
					field : 'noteSerialNo',
					title : '照会序号',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						return row.noteSerialNo;
					}
				}, {
					field : 'noteContent',
					title : '照会内容',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.noteContent;
					}
				}, {
					field : 'note_Apply_User',
					title : '照会发起人',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.noteApplyUser;
					}
				}, {
					field : 'note_Apply_Date',
					title : '申请日期',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.noteApplyDate;
					}
				}, {
					field : 'note_Close_Date',
					title : '解决日期',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.noteCloseDate;
					}
				}, {
					field : 'note_Cancel_Date',
					title : '撤销日期',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.noteCancelDate;
					}
				} ] ],
				onLoadSuccess : function() {
					$('#noteTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				},
				onSelect : function(index, row) {
					$('#policyNo').val(row.policyNo);
					$('#businessNo').val(row.businessNo);
					$('#id').val(row.id);
					$('#noteStatus').val(row.noteStatus);
					if("01" == businessType){
						$('#noteType').combobox('setValue', row.noteType);  
					}else{
						$('#noteType').val(row.noteType);
					}
					
					$('#noteContent').val(row.noteContent);
					$('#noteApplyUser').val(row.noteApplyUser);
				}
			});
}

// 切换数据类型
function changeType() {
	$('#businessType').val($('#busType').val());
}

// 错误信息处理
function error(info) {
	$.messager.alert('提示', info, 'error');
	cAlert();
}