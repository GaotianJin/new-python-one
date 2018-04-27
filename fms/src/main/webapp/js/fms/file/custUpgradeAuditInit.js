var operate = null;
jQuery(function($) {
	initFileListTable();
	$("#custCheckConclusion").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=custCheckConclusion',
		valueField:'code',
		textField:'codeName',
		required:true,
		editable:false
	});
	operate = $("#operateFlag").val();
	if( operate != null && operate != "" && operate != undefined && operate == "queryFile" ){
		$("#custCheckDiv").css("display","none");
		$("#submitButton").hide();
		$("#deleteFileButton").hide();
	}
});


var fileListTable ;
function initFileListTable(){
	fileListTable = $('#custUpgradeAudit_fileListTable').datagrid({
		title : '文件列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/fileUpload/getFileList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		//idField : 'id', // 主键字段
		queryParams : {"queryParam":$.toJSON(transParam)}, // 查询条件
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
					field : 'businessSubType',
					title : '附件子类型',
					hidden : true,
					formatter : function(value, row, index) {
						return row.businessSubType;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'businessSubTypeName',
					title : '附件子类型',
					width : 80,
					formatter : function(value, row, index) {
						return row.businessSubTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'uploadFileName',
					title : '文件名称',
					width : 120,
					//sortable : true,
					formatter : function(value, row, index) {
						//return row.uploadFileName;
						
						var param = {};
						param.fileInfoId = row.fileInfoId;
						param = $.toJSON(param);
						return "<a href='#'  onclick=fileDownload('"+param+"')>"+row.uploadFileName+"</a>";

						
						
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'fileName',
					title : '在线预览',
					width : 80,
					formatter : function(value, row, index) {
						var fileSavePath = row.fileSavePath;
						var fileName = row.fileName;
						var photo = fileSavePath+fileName;
						var path = 'http://localhost:8888'+fileSavePath.substring(9)+fileName;
						return "<a href= "+path+" target='_blank'>"+"预览"+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'fileDescribe',
					title : '文件描述',
					width : 140,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.fileDescribe;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#custUpgradeAudit_fileListTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

function queryFileList(transParam) {
	fileListTable.datagrid('options').url = contextPath+"/fileUpload/getFileList";
	var queryParam = null ;
	if(transParam!=null&&transParam!=""){
		queryParam.businessType = $("#investCustUploadFile_businessType").val();
		queryParam.roleId = transParam.roleId;
		queryParam = $.toJSON(transParam);
	}else{
		queryParam = $("#investCustUploadFile_fileForm").serialize();
		queryParam = formDataToJsonStr(queryParam);
	}
	var param = eval("("+queryParam+")");
	var businessNo = param.businessNo;
	if(businessNo==null||businessNo==""||businessNo==undefined){
		return;
	}
	var businessType = param.businessType;
	if(businessType==null||businessType==""||businessType==undefined){
		return;
	}
	fileListTable.datagrid('load',{queryParam:queryParam});	
}

// 清空查询条件然后进行查询
function clearForm() {
	$('#investCustUploadFile_uploadFileName').val("");
	$('#investCustUploadFile_fileDescribe').val("");
	$('#investCustUploadFile_businessSubType').combobox("clear");
	
}


function backParentPage(){
	$('#addFileWindow').window('destroy');
	parent.queryCustomerAuditList();
}


function investCustAudit(){
	var custBaseInfoId=$('#custUpgradeAudit_businessNo').val();
	var custCheckConclusion = $("#custCheckConclusion").combobox('getValue');
	var custCheckRemark = $("#custCheckRemark").val();
	if(custCheckConclusion==null || custCheckConclusion == "" ||custCheckConclusion == undefined){
		$.messager.alert('提示', "请填写复核结论！！！");	
		return;
	}
	
	$.ajax({
		type:'post',
		url:'customer/saveCustUpgradeAudit',
		data:'custBaseInfoId='+custBaseInfoId+'&custCheckConclusion='+custCheckConclusion
		      +'&custOperationNode=01'+'&custCheckRemark='+custCheckRemark,
		cache:false,
		success:function(returnData){
				if(returnData.success=="true"){
					$.messager.alert('提示', "复核完毕！");
				}else{
					$.messager.alert('提示', returnData.msg);
				}
		}
	
	});
}