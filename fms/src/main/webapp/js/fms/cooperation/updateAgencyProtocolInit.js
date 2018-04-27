var verifyProtocolCodeFlag = false;
var modifyAgencyComId = null;
var modifyProtocolType = null;
var modifyProtocolId = null;
var returnProtocolId=null;
var protocolTypeValue=null;
jQuery(function($) {
	modifyAgencyComId = $("#modifyAgencyComId").val();
	modifyProtocolType = $("#modifyProtocolType").val();
	modifyProtocolId = $("#modifyProtocolId").val();
	initAllCombox();
	getUpdateInitValue();
})

// 初始需要修改的数据
function getUpdateInitValue() {
	var param = {};
	param.modifyAgencyComId = modifyAgencyComId;
	param.modifyProtocolType = modifyProtocolType;
	param.modifyProtocolId = modifyProtocolId;
	//子协议
	if(modifyProtocolType=='3'){
		midProtocolType='03';
	}
	else{
		midProtocolType='02';
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
					$('#modify_beloneProtocolId').combobox("reload",contextPath + '/codeQuery/protocolQuery');
					if (modifyProtocolType == 3) {
						setInputValueById("updateAgencyProtocolForm", resultObj.agencysubProtocol);
					} else {
						setInputValueById("updateAgencyProtocolForm", resultObj.agencyProtocol);
						$('#modify_beloneProtocolId').combobox("disable");
						$("#modify_beloneProtocolId").combobox("setValue","");
					}
					setInputValueById("updateAgencyProtocolForm", resultObj.agencyCom);
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
function initAllCombox() {
	var protocolTypeCombobox;
	// 合作协议类型
	protocolTypeCombobox = $('#modify_protocolType').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolType',
		valueField : 'code',
		textField : 'codeName',
		onSelect : function() {
			protocolTypeValue = protocolTypeCombobox.combobox("getValue");
			// 1-框架协议，2-普通协议，3-子协议
			if (protocolTypeValue == 1 || protocolTypeValue == 2) {
				// 框架协议和普通协议时，所属框架协议字段不可编辑
				$("#modify_beloneProtocolId").combobox("disable");
			} else {
				$("#modify_beloneProtocolId").combobox("enable");
			}
		}
	});
	// 合作机构
	$('#modify_agencyComId').combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	// 协议状态
	$('#modify_protocolState').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolState',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	// 协议开始日期必填
	$('#modify_protocolStartDay').datebox({ // 日期选择框
		required : true
	});

	// 合作机构必填
	$('#modify_agencyComId').combobox({
		required : true
	});
	// 协议名称必填
	$('#modify_protocolName').validatebox({
		required : true
	});
	// 协议名称必填
	$('#modify_protocolCode').validatebox({
		required : true
	});

	// 协议编码必填
	$('#modify_protocolType').combobox({
		required : true
	});
//	// 协议类型
//	$('#modify_protocolType').combobox({
//		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolType',
//		valueField : 'code',
//		textField : 'codeName'
//	});
	$('#modify_protocolType').combobox("disable");
	// 所属框架协议
	$('#modify_beloneProtocolId').combobox({
		url : contextPath + '/codeQuery/protocolQuery',
		valueField : 'code',
		textField : 'codeName'
	});

}

function checkData() {
	// 1.所有表单非空校验
	if (!$("#updateAgencyProtocolForm").form("validate")
			|| !$("#uploadFileInfo").form("validate")) {
		return false;
	}
	// 2.用户编码与财富顾问编码非重复性校验
//	if (!verifyProtocolCodeFlag) {
//		$.messager.alert('提示', "该协议编码已经存在！", 'info');
//		return false;
//	}
	
	var protocolStartDate = $("#modify_protocolStartDay").datebox("getValue");
	var protocolEndDate = $("#modify_protocolEndDay").datebox("getValue");
	if(protocolStartDate!=null && protocolStartDate!="" && protocolStartDate!=undefined &&
		protocolEndDate!=null && protocolEndDate!="" && protocolEndDate!=undefined)
	{
		var d1 = new Date(protocolStartDate.replace(/\-/g, "\/")); 
		var d2 = new Date(protocolEndDate.replace(/\-/g, "\/")); 

		if(d1 >=d2)
		{
		  $.messager.alert('提示', "协议开始日期不能大于等于结束日期！", 'info');
		  return false; 
		}
	}
	return true;
}

function verifyProtocolCode() {
	var protocolCode = $('#modify_protocolCode').val();
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

// 点击[提交]修改合作机构协议信息
function updateAgencyProtocol() {
	// 校验提交数据的合法性
	if (!checkData()) {
		return;
	}
	var param = {};
	// 协议基本信息Form
	var basicProtocolInfoJson = formDataToJsonStr($("#updateAgencyProtocolForm").serialize());
	param.basicProtocolInfo = basicProtocolInfoJson;
	param.modifyAgencyComId = modifyAgencyComId;
	param.modifyProtocolType = modifyProtocolType;
	param.modifyProtocolId = modifyProtocolId;
	
	$('#updateAgencyProtocolSubmitButton').linkbutton('disable');
	// 发送请求，后台接受数据进行处理
	$.ajax({
		type : 'post',
		url : contextPath + "/cooperation/updateAgencyComProtocol",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
					clearForm();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
			$('#updateAgencyProtocolSubmitButton').linkbutton('enable');
		}
	});

}

// 清空查询条件然后进行查询
function clearForm() {
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
		param.businessType=midProtocolType;
		
		param.operate = "uploadFile";
		addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
	}
	else{
		alert("请先新增协议信息，再进行附件上传!");
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

function initAddAgencyProtocolTable() {
	jQuery(function($) {
		$('#addAgencyProtocolTable').datagrid({
			title : '协议信息列表', // 标题
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			// url : contextPath+ '/cooperation/queryListAgencyCom',
			pageSize : 10,
			pageList : [ 5, 10, 15, 20, 25, 30 ],
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, // 显示复选框
			{
				field : 'agencyName',
				title : '基金管理人',
				width : 180,
				sortable : true,
				formatter : function(value, row, index) {
					return row.agencyName;
				} // 需要formatter一下才能显示正确的数据
			}, {
				field : 'protocolCode',
				title : '协议编码',
				width : 180,
				sortable : true,
				formatter : function(value, row, index) {
					return row.protocolCode;
				}
			}, {
				field : 'protocolName',
				title : '协议名称',
				width : 180,
				sortable : true,
				formatter : function(value, row, index) {
					return row.protocolName;
				}
			}, {
				field : 'protocolType',
				title : '协议类型',
				width : 180,
				sortable : true,
				formatter : function(value, row, index) {
					return row.protocolType;
				}
			}, {
				field : 'protocolState',
				title : '协议状态',
				width : 180,
				sortable : true,
				formatter : function(value, row, index) {
					return row.protocolState;
				}
			}, {
				field : 'protocolStartDay',
				title : '协议开始日期',
				width : 150,
				sortable : true,
				formatter : function(value, row, index) {
					return row.protocolStartDay;
				}
			}, {
				field : 'protocolEndDay',
				title : '协议结束日期',
				width : 150,
				sortable : true,
				formatter : function(value, row, index) {
					return row.protocolEndDay;
				}
			}, {
				field : 'rate',
				title : '协议文件',
				width : 150,
				sortable : true,
				formatter : function(value, row, index) {
					return row.rate;
				}
			} ] ],
			onLoadSuccess : function() {
				$('#addAgencyProtocolTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});

		$('#agencyName').combobox({
			url : '../company/queryCompanyList',
			valueField : 'id',
			textField : 'name'
		});

		$('#protocolName_').combobox({
			url : '../company/queryCompanyList',
			valueField : 'id',
			textField : 'name'
		});

		$('#protocolType').combobox({
			url : '../company/queryCompanyList',
			valueField : 'id',
			textField : 'name'
		});

		$('#protocolState').combobox({
			url : '../company/queryCompanyList',
			valueField : 'id',
			textField : 'name'
		});
	});

}
function backListAgencyProtocolPage(){
	$('#agencyProtocolWindow').window('destroy');
	parent.clearAgencyProtocol();
}