var investAuditStateCode;
jQuery(function($) {
	initAllCombobox();
	initFileListTable();
	queryCustAndAgentInfo();
	investAuditStateCode = $("#investCustUploadFile_investAuditStateCode").val();
	//若为退回状态  则展示退回div信息 
	if(investAuditStateCode == "02"){
		$("#custUpgradeAuditConclusionDiv").show();
		initCustAuditConclusion();
	}else{
		$("#custUpgradeAuditConclusionDiv").hide();
	}
});


var fileListTable ;
function initFileListTable(){
	fileListTable = $('#investCustUploadFile_fileListTable').datagrid({
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
			$('#investCustUploadFile_fileListTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

function fileUpload(){
	if(!$("#investCustUploadFile_fileForm").form("validate") ) {  // 客户基本信息form表单字段验证
		return;
	}
	var allFiles = fileListTable.datagrid("getRows");
	var businessNo = $("#investCustUploadFile_businessNo").val();
	var businessType = $("#investCustUploadFile_businessType").val();
	var fileDescribe = $("#investCustUploadFile_fileDescribe").val();	
	var uploadFileName = $("#investCustUploadFile_uploadFileName").val();
	var businessSubType =$("#investCustUploadFile_businessSubType").combobox("getValue");
	
	if(uploadFileName==null||uploadFileName==""||uploadFileName==undefined){
		$.messager.alert('提示', "请选择需要上传的附件", 'info');
		return;
	}
	//判断此文件是否已经上传
	if(allFiles.length>0){
		for(var i=0;i<allFiles.length;i++){
			var row = allFiles[i];
			if(row.uploadFileName==uploadFileName){
				$.messager.alert('提示', "此附件已经上传", 'info');
				return;
			}
		}
	}
	var param = {};
	param.businessNo = businessNo;
	param.businessType = businessType;
	param.fileDescribe = fileDescribe;
	param.businessSubType=businessSubType;
	param.type="01";
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/fileUpload/uploadFile?param="+$.toJSON(param),
		fileElementId:'investCustUploadFile_uploadFileName', 
		dataType:'json',
		success:function(reData,status){
			reData = $.parseJSON(reData)
			try {
				if(reData.success){
					$.messager.alert('提示', "文件上传成功");
					clearForm();
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
		$.messager.alert("提示","请选择一个附件进行删除",'info');
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

function initAllCombobox(){
	var param={};
	param.codeType='businessSubType';
	param.businessType=$('#investCustUploadFile_businessType').val();
	$('#investCustUploadFile_businessSubType').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:contextPath +'/codeQuery/codeQueryByParam?param='+$.toJSON(param),
	    editable : false
	});
	$('#investCustUploadFile_businessSubType').combobox({
		required : true
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
}

function investCustAudit(){
	var custBaseInfoId=$('#investCustUploadFile_businessNo').val();
	$.ajax({
		type:'post',
		url:contextPath+"/customer/investCustAudit",
		data:'custBaseInfoId='+custBaseInfoId,
		cache:false,
		success:function(reData){
				if(reData.success){
					$.messager.alert('提示', reData.msg, 'info');
					$('#addFileWindow').window('destroy');
					parent.queryCustomerList();
				}else{
					$.messager.alert('提示', reData.msg);
				}
		}
	});
}

function initCustAuditConclusion(){
	var custBaseInfoId =  $("#investCustUploadFile_businessNo").val();
	$.ajax({
		type:'post',
		url:'customer/queryCustConclusion',
		data:'custBaseInfoId='+custBaseInfoId+'&custOperationNode=01',
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success=="true"){
					$("#custUpgradeAuditConclusion_Input").combobox('setValue',returnData.custConclusion);
					$("#custUpgradeAuditConclusion_Input").combobox('setText',returnData.custConclusionName);
					$("#custUpgradeAuditRemark_Input").val(returnData.custRemark);
				}else{
					
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

function queryCustAndAgentInfo(){
	var custBaseInfoId =  $("#investCustUploadFile_businessNo").val();
	$.ajax({
		type:'post',
		url:'customer/queryCustAndAgentInfo',
		data:'custBaseInfoId='+custBaseInfoId,
		cache:false,
		success:function(returnData){
			$("#custUpgradeBaseInfo_chnName").val(returnData.chnName);
			$("#custUpgradeBaseInfo_agentName").val(returnData.agentName);
		}
	
	});
}