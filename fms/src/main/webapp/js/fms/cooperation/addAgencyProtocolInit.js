var verifyProtocolCodeFlag = false;
//存放返回的协议流水号
var returnProtocolId=null;
//协议类型
var protocolTypeValue=null;
var midprotocolTypeValue=null;
var operate = null;
var fileOperate = null;

jQuery(function($) {
	operate = $("#addAgencyProtocol_operate").val();
	initAllCombox();
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="addProtocol"){
		fileOperate = "uploadFile";
		$("#addAgencyProtocolSearchFileButton").hide();
	}else if(operate!=null&&operate!=""&&operate!=undefined&&operate=="updateProtocol"){
		fileOperate = "uploadFile";
		queryAgencyProtocolInfo();
		$('#add_protocolType').combobox("disable");
		$("#addAgencyProtocolSearchFileButton").hide();
	}else if(operate!=null&&operate!=""&&operate!=undefined&&operate=="protocolDetail"){
		fileOperate = "queryFile";
		queryAgencyProtocolInfo();
		$('#add_agencyComId').combobox("disable");
		$('#add_protocolType').combobox("disable");
		$("#addAgencyProtocolSubmitButton").hide();
		$("#addAgencyProtocolUploadFileButton").hide();
	}
})

function initAllCombox() {
	var protocolTypeCombobox;
	// 合作协议类型
	protocolTypeCombobox = $('#add_protocolType').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolType',
		valueField : 'code',
		textField : 'codeName',
		required : true/*,
		onSelect : function() {
			
			protocolTypeValue = protocolTypeCombobox.combobox("getValue");
			// 1-框架协议，2-普通协议，3-子协议
			if (protocolTypeValue == 1 || protocolTypeValue == 2) {
				// 框架协议和普通协议时，所属框架协议字段不可编辑
				$("#add_beloneProtocolId").combobox("disable");
				$('#add_beloneProtocolId').combobox("reset");
			} else {
				$("#add_beloneProtocolId").combobox("enable");
			}
		}*/
	});
	// 合作机构
	$('#add_agencyComId').combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'/*,
		onSelect:function(){
			var comId = $('#add_agencyComId').combobox("getValue");
			var url = contextPath + '/codeQuery/protocolQuery?comId='+comId;
			$('#add_beloneProtocolId').combobox("reload",url);
		}*/
	});
	// 协议状态
	$('#add_protocolState').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolState',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		value:'0'
	});
	// 协议开始日期必填
	$('#add_protocolStartDay').datebox({ // 日期选择框
		//required : true
	});

	// 协议名称必填
	$('#add_protocolName').validatebox({
		required : true
	});
	/*// 协议编码必填
	$('#add_protocolCode').validatebox({
		required : true
	});*/
	
	// 协议编码必填
	/*$('#add_protocolType').combobox({
		required : true
	});*/
	// 协议类型
	/*$('#add_protocolType').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolType',
		valueField : 'code',
		textField : 'codeName',
		onSelect : function(){
			var comId = $('#add_agencyComId').combobox("getValue");
			var url = contextPath + '/codeQuery/protocolQuery?comId='+comId;
			$('#add_beloneProtocolId').combobox("reload",url);
		}
	});*/
	// 所属框架协议
	$('#add_beloneProtocolId').combobox({
		url : contextPath + '/codeQuery/protocolQuery',
		valueField : 'code',
		textField : 'codeName'
	});

}

function checkData() {
	// 1.所有表单非空校验
	if (!$("#addAgencyProtocolForm").form("validate")
			|| !$("#uploadFileInfo").form("validate")) {
		return false;
	}
	
	var protocolStartDate = $("#add_protocolStartDay").datebox("getValue");
	var protocolEndDate = $("#add_protocolEndDay").datebox("getValue");
	if(!compareDate(protocolStartDate, protocolEndDate)){
		$.messager.alert('提示', "协议开始日期不能大于等于结束日期！", 'info');
		  return false;
	}
	return true;
}

function verifyProtocolCode() {
//console.info("开始校验");
	var protocolCode = $('#add_protocolCode').val();
	var param = {};
	param.protocolCode = protocolCode;
	$.ajax({
		type : 'post',
		url : contextPath + "/cooperation/verifyProtocolCode",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					verifyProtocolCodeFlag = true;
				} else {
					verifyProtocolCodeFlag = false;
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});

}

// 点击[提交]新增合作机构协议信息
function addAgencyProtocol() {
	// 校验提交数据的合法性
	if (!checkData()) {
		return;
	}
	var param = {};
	var protocolId = $("#addAgencyProtocolId").val();
	// 协议基本信息Form
	var basicProtocolInfoJson = formDataToJsonStr($("#addAgencyProtocolForm").serialize());
	param.addAgencyProtocolId = protocolId;
	param.basicProtocolInfo = basicProtocolInfoJson;
	//console.info("basicProtocolInfo:" + basicProtocolInfoJson);
	// 上传文件基本信息Form
	var uploadFileInfoJson = formDataToJsonStr($("#uploadFileInfo").serialize());
	param.basicUploadFileInfo = uploadFileInfoJson;
	// 获取协议类型
	var addProtocolType = $("#add_protocolType").val();
	param.protocolType = addProtocolType;
	
	$('#addAgencyProtocolSubmitButton').linkbutton('disable');
	// 发送请求，后台接受数据进行处理
	$.ajax({
		type : 'post',
		url : contextPath + "/cooperation/addAgencyComProtocol",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					returnProtocolId=resultInfo.obj;
					$("#addAgencyProtocolId").val(resultInfo.obj);
					//提交成功后协议类型不让改
					$('#add_protocolType').combobox("disable");
					//queryAgencyProtocolInfo();
					//子协议
					if(protocolTypeValue=='3'){
						midprotocolTypeValue='03';
					}
					else{
						midprotocolTypeValue='02';
					}
					//console.info("返回的协议流水号："+returnProtocolId);
					$.messager.alert('提示', resultInfo.msg, 'info');
					//clearForm();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
			$('#addAgencyProtocolSubmitButton').linkbutton('enable');

		}
	});

}

// 清空查询条件然后进行查询
function clearForm() {
	$('#addAgencyProtocolForm').form('clear');
}

/** ***************************************************** */

function addAgencyProtocltab(title, href) {
	$('<div id="addWindow"/>').dialog({
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


function AgencyProtocolFileUpload()
{
	//先添加协议信息后上传
	if(returnProtocolId!=null){
		var param = {};
		//获得协议流水号
		param.businessNo = returnProtocolId;
		param.businessType=midprotocolTypeValue;
		param.operate = fileOperate;
		addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
	}
	else{
		$.messager.alert('提示',"请先新增协议信息，再进行附件上传!");
	}
}
function addFileWindow(title, href) 
{
	$('<div id="addFileWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		//fit : true, 
		width:800,
		height:500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}


function queryAgencyProtocolInfo(){
	var param = {};
	var modifyProtocolId = $("#addAgencyProtocolId").val();
	var addAgencyProtocolType = $("#addAgencyProtocolType").val();
	var addAgencyProtocolComId = null;
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="addProtocol"){
		addAgencyProtocolType = $('#add_protocolType').combobox("getValue");
		addAgencyProtocolComId = $("#add_agencyComId").combobox("getValue");
	}else{
		addAgencyProtocolComId = $("#addAgencyProtocolComId").val();
	}
	param.modifyAgencyComId = addAgencyProtocolComId;
	param.modifyProtocolType = addAgencyProtocolType;
	param.modifyProtocolId = modifyProtocolId;
	//子协议
	if(addAgencyProtocolType=='3'){
		midprotocolTypeValue='03';
	}
	else{
		midprotocolTypeValue='02';
	}
	$.ajax({
		type : 'post',
		url : contextPath + "/cooperation/queryAgencyComProtocolInfo",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(result) {
			try {
				if (result.success) {
					var resultObj = result.obj;
					// 1.合作机构协议基本信息
					//$('#modify_beloneProtocolId').combobox("reload",contextPath + '/codeQuery/protocolQuery');
					
						setInputValueById("addAgencyProtocolDiv", resultObj.agencyProtocol);
						$('#add_beloneProtocolId').combobox("disable");
						$("#add_beloneProtocolId").combobox("reset");
						$('#add_protocolStartDay').datebox("setValue",resultObj.agencyProtocol.protocolStartDate);
						$('#add_protocolEndDay').datebox("setValue",resultObj.agencyProtocol.protocolEndDate);
					
					setInputValueById("addAgencyProtocolDiv", resultObj.agencyCom);
					$("#addAgencyProtocolComId").val(resultObj.agencyCom.agencyComId);
					returnProtocolId=modifyProtocolId;
				} else {
					$.messager.alert('提示', result.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function backListAgencyProtocolPage(){
	$('#agencyProtocolWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="protocolDetail"){
		
	}else{
		queryAgencyProtocolList();
	}
	
	//parent.clearAgencyProtocol();
}