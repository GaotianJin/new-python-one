var verifyProtocolCodeFlag = false;
var detailAgencyComId = null;
var detailProtocolType = null;
var detailProtocolId = null;
var returnProtocolId=null;
var protocolTypeValue=null;
jQuery(function($) {
	
	var rows = $('#agencyProtocolTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要查看的合作协议");
		return;
	} 
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			detailAgencyComId = n.agencyComId;
			detailProtocolType = n.protocolType;
			detailProtocolId = n.protocolId;
			returnProtocolId = n.protocolId;
		});
	};
	
	initAllCombox();
	getUpdateInitValue();
})

function getUpdateInitValue() {
	var param = {};
	param.modifyAgencyComId = detailAgencyComId;
	param.modifyProtocolType = detailProtocolType;
	param.modifyProtocolId = detailProtocolId;
//	console.info("判断modifyProtocolType："+modifyProtocolType);
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
					$('#detail_beloneProtocolId').combobox("reload",contextPath + '/codeQuery/protocolQuery');
					if (detailProtocolType == 3) {
						setInputValueById("updateAgencyProtocolForm", resultObj.agencysubProtocol);
					} else {
						setInputValueById("updateAgencyProtocolForm", resultObj.agencyProtocol);
						$('#detail_beloneProtocolId').combobox("disable");
						$("#detail_beloneProtocolId").combobox("setValue","");
					}
					setInputValueById("updateAgencyProtocolForm", resultObj.agencyCom);
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
	protocolTypeCombobox = $('#detail_protocolType').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolType',
		valueField : 'code',
		textField : 'codeName',
		onSelect : function() {
			protocolTypeValue = protocolTypeCombobox.combobox("getValue");
			// 1-框架协议，2-普通协议，3-子协议
			if (protocolTypeValue == 1 || protocolTypeValue == 2) {
				// 框架协议和普通协议时，所属框架协议字段不可编辑
				$("#detail_beloneProtocolId").combobox("disable");
			} else {
				$("#detail_beloneProtocolId").combobox("enable");
			}
		}
	});
	// 合作机构
	$('#detail_agencyComId').combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName'
	});
	// 协议状态
	$('#detail_protocolState').combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=protocolState',
		valueField : 'code',
		textField : 'codeName'
	});
	// 协议开始日期必填
	$('#detail_protocolStartDay').datebox({ // 日期选择框
		required : true
	});

	// 合作机构必填
	$('#detail_agencyComId').combobox({
		required : true
	});
	// 协议名称必填
	$('#detail_protocolName').validatebox({
		required : true
	});
	// 协议名称必填
	$('#detail_protocolCode').validatebox({
		required : true
	});

	// 协议编码必填
	$('#detail_protocolType').combobox({
		required : true
	});

	$('#detail_protocolType').combobox("disable");
	// 所属框架协议
	$('#detail_beloneProtocolId').combobox({
		url : contextPath + '/codeQuery/protocolQuery',
		valueField : 'code',
		textField : 'codeName'
	});

}

// 清空查询条件然后进行查询
function clearForm() {
	$('#addAgencyProtocolForm').form('clear');
	searchBuild();
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
		//console.info(param.businessType);
		param.operate = "queryFile";
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
function backListAgencyProtocolPage  (){
	$('#agencyProtocolWindow').window('destroy');
	parent.clearAgencyProtocol();
}