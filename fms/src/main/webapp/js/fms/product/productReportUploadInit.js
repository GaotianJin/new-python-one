var operate = null;
var transParam={};
jQuery(function($) {
	initcombobox();
	initFileListTable();
});


function initcombobox(){
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#uploadFile_productId").combobox({
		url : contextPath + '/product/productQueryAllRelease',
		valueField : 'code',
		textField : 'codeName',
		onSelect:function(){
			queryFileList();
		}
	});
	var businessSubTypeComobox;
	// 产品初始化 显示为：产品代码-产品名称
	businessSubTypeComobox = $("#uploadFile_businessSubType").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=businessSubType',
		valueField : 'code',
		textField : 'codeName',
		value:104,
		editable : false
	});
}

var fileListTable ;
function initFileListTable(){
	fileListTable = $('#uploadFile_fileListTable').datagrid({
		title : '文件列表', // 标题
		method : 'post',
		singleSelect : false, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'fileInfoId',
					title : '附件关联流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.fileInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'businessNo',
					title : '附件关联流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.businessNo;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'businessType',
					title : '附件类型',
					hidden : true,
					formatter : function(value, row, index) {
						return row.businessType;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'businessTypeName',
					title : '附件类型',
					width : 80,
					formatter : function(value, row, index) {
						return row.businessTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'uploadFileName',
					title : '文件名称',
					width : 120,
					formatter : function(value, row, index) {
						var param = {};
						param.fileInfoId = row.fileInfoId;
						param = $.toJSON(param);
						return "<a href='#'  onclick=fileDownload('"+param+"')>"+row.uploadFileName+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'fileDescribe',
					title : '文件描述',
					width : 140,
					formatter : function(value, row, index) {
						return row.fileDescribe;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#uploadFile_fileListTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function fileUpload(){
	var allFiles = fileListTable.datagrid("getRows");
	var businessNo =$("#uploadFile_productId").combobox("getValue");
	var businessSubType =$("#uploadFile_businessSubType").combobox("getValue");
	var fileDescribe = $("#uploadFile_fileDescribe").val();	
	var uploadFileName = $("#uploadFile_uploadFileName").val();
	//校验必填项

	if(uploadFileName==null||uploadFileName==""||uploadFileName==undefined){
		$.messager.alert('提示', "请选择需要上传的附件", 'info');
		return;
	}
	var param = {};
	param.businessNo = businessNo;
	param.businessType = "01";
	param.fileDescribe = fileDescribe;
	param.businessSubType=businessSubType;
	param.uploadFileName=uploadFileName;
	param.type="01";
	param.flag="check";
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/fileUpload/uploadFile?param="+$.toJSON(param),
		fileElementId:'uploadFile_uploadFileName', 
		dataType:'json',
		success:function(reData,status){
			reData = $.parseJSON(reData)
			try {
				if(reData.success){
					$.messager.alert('提示', "文件上传成功");
					clearForm();
					queryFileList();
				    $("#uploadFile_productId").combobox("disable");
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function fileDownload(param) { 
	$.messager.confirm('确认','您确认下载此文件吗？',function(r){    
	    if (r){    
	    	var form = $("<form>");   //定义一个form表单
	    	form.attr('style', 'display:none');   //在form表单中添加查询参数
	    	form.attr('target', '');
	    	form.attr('method', 'post');
	    	form.attr('action', contextPath+"/fileUpload/downloadFile?param="+param);
	    	$('body').append(form);  //将表单放置在web中 
	    	form.submit(); 
	    }
	});
}


function fileDelete(){
	var rows = fileListTable.datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择一个附件进行删除");
		return;
	}
	$.ajax({
		type:'post',
		url:contextPath+"/fileUpload/deleteFile",
		data:'param='+$.toJSON(rows),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', reData.msg, 'info');
					 queryFileList();
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}


function queryFileList() {
	fileListTable.datagrid('options').url = contextPath+"/fileUpload/getFileList";
	queryParam = $("#uploadFile_fileForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	fileListTable.datagrid('load',{queryParam:queryParam});	
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#uploadFile_uploadFileName').val("");
	$('#uploadFile_fileDescribe').val("");
}


function backParentPage(){
	$('#addProdutctReportInfo').window('destroy');
}