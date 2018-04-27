var custAllTradeTableIndex = null;
var redemptionInfoId=null;
/**
 * 页面初始化加载
 */
jQuery(function($) {
	redemptionInfoId=$("#redemptionConfirmAdd_redemptionInfoId").val();
	initAllCombobox();
	initCustAllTradeTable();
	getRedemptionConfirmInfo();
	
});


function initAllCombobox() {
//赎回结论
	
	$("#redemptionConfirmAdd_Conclusion").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=redemptionCheckConclusion',
		valueField : 'code',
		textField : 'codeName',
		required:true
	});
	
//客户
	$("#redemptionConfirmAdd_custName").combobox({
	url : contextPath + '/redemption/redemtionCustomerQuery',
	valueField : 'code',
	textField : 'codeName',
	editable:false  
	});
	
//产品名称
	$("#redemptionConfirmAdd_productName").combobox({
		url : contextPath + '/redemption/redemtionProductQuery',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});

 }
 	
/**
 * 赎回确认交易列表
 */
var custAllTradeTable;
function initCustAllTradeTable() {
	custAllTradeTable = $("#custAllTradeTable").datagrid({
		method : 'post',
		singleSelect : true, 
		fitColumns : true, 
		striped : true, 
		collapsible : true,
		remoteSort : true,
		idField : 'id',
		queryParams : {}, 
		columns : [ [
				{
					field : 'ck',
					checkbox : true,
					width : 2
				}, 
				{
					field : 'tradeInfoId',
					title : '交易流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.tradeInfoId;
					}
				}, 
				{
					field : 'tradeNo',
					title : '交易号',
					width : 100,
					formatter : function(value, row, index) {
						return row.tradeNo;
					}
				}, 
				{
					field : 'expectOpenDay',
					title : '成立日/开放日',
					width : 100,
					formatter : function(value, row, index) {
					return row.expectOpenDay;
					} 
				}, 
				{
					field : 'benefitType',
					title : '受益类型',
					width : 100,
					formatter : function(value, row, index) {
					return row.benefitType;
					} 
				}, 
				{
					field : 'subscriptShare',
					title : '初始份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.subscriptShare;
				   }
				},
				{
					field : 'aleadyRedemptionShare',
					title : '已赎回份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.aleadyRedemptionShare;
				   }
				},
				{
					field : 'currentShare',
					title : '当前份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.currentShare;
					}
				}, 
				{
					field : 'redeemableShare',
					title : '可赎回份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.redeemableShare;
					}
				}, 
				{
					field : 'redemptionShare', 
					title : '赎回份额(本次)',
					width : 100,
					formatter : function(value, row, index) {
						return row.redemptionShare;
					}
				} , 
				{
					field : 'residualShare',
					title : '剩余份额',
					width : 100,
					formatter : function(value, row, index) {
						return row.residualShare;
					}
				}, 
				{
					field : 'bankName',
					title : '受益账户银行',
					width : 200,
					formatter : function(value, row, index) {
						return row.bankName;
					}
				},
				{
					field : 'bankNo',
					title : '受益账号',
					width : 200,
					formatter : function(value, row, index) {
						return row.bankNo;
					}
				}
				] ],
		onLoadSuccess : function() {
			$('#custAllTradeTable').datagrid('clearSelections'); 
		}
	});
}


//获取赎回交易信息数据
function getRedemptionConfirmInfo(){
	if (redemptionInfoId != null || redemptionInfoId != undefined|| redemptionInfoId != "") {
		var params = {};
		params.redemptionInfoId = redemptionInfoId;
		$.ajax({
			type:'post',
			url:'redemption/getRedemptionConfirmInfo',
			data:'queryParams='+$.toJSON(params),
			cache:false,
			success:function(returnData){
				try {
					var jsonObj = returnData.obj;
					if(returnData.success){
						//赎回参考信息赋值
						setInputValueById("redemptionConfirmRefenceInfoForm",jsonObj.redemptionInfo);
						//赎回参考信息赋值
						setInputValueById("redemptionConfirmRefenceInfoForm",jsonObj.redemptionInfoNetInfo[0]);
						//赎回交易信息列表赋值
						if(jsonObj.redemptionInfoList!=null&&jsonObj.redemptionInfoList!=undefined){
							clearAllRows(custAllTradeTable);
							loadJsonObjData("custAllTradeTable",jsonObj.redemptionInfoList);
						}
						//赎回客户产品信息赋值
						setInputValueById("redemptionConfirmQueryForm",jsonObj.redemptionCustProInfo[0]);
						//赋值赎回总份额和总金额
						setInputValueById("redemptionConfirmTradeInfoForm",jsonObj.redemptionInfo);
					}else{
						$.messager.alert('提示', returnData.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
			}
		});
	} else {
		alert("查询赎回信息时获取赎回流水号失败！");
		return;
	}
};

//赎回确认动作
function redemptionConfirmAddInfo(){
 
	// 校验赎回结论是否填写
	if (!$("#redemptionConfirmCheckInfoForm").form("validate")) {
		$.messager.alert('提示', "请输入赎回结论！", 'info');
		return;
	}
	if (redemptionInfoId==null||redemptionInfoId==undefined||redemptionInfoId=="") {
		$.messager.alert('提示', "赎回流水号为空，提交赎回确认信息失败", 'info');
		return;
	}
	
	var param={};
	// 赎回结论信息
	var redemptionConfirmCheckInfoJson = formDataToJsonStr($("#redemptionConfirmCheckInfoForm").serialize());
	param.redemptionConfirmCheckInfo = redemptionConfirmCheckInfoJson;
	param.redemptionInfoId=redemptionInfoId;
	param.redemptionOperationId = $("#redemptionOperationId").val();
	//保存赎回相关的信息
	$.ajax({
		type:'post',
		url:'redemption/saveRedemptionConfirmCheckInfo',
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					//redemptionInfoId=returnData.obj;
					if(returnData.obj!=null){
						$("#redemptionOperationId").val(returnData.obj);
					}
					$.messager.alert('提示', returnData.msg);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});

	
	
	
	
	
}

////查看影像件
//function queryRedemptionApplicationForm(){
//
//	if (redemptionInfoId != null) {
//		var param = {};
//		param.businessNo = redemptionInfoId;
//		param.operate = "queryFile";
//		//产品
//		param.businessType = "06";
//		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));
//	} else {
//		alert("请先添保存赎回信息，再进行影像件上传！");
//	}
//}


//上传影像件
function queryRedemptionApplicationForm() {
	if (redemptionInfoId != null) {
		var param = {};
		param.businessNo = redemptionInfoId;
		//06-赎回申请单影像件
		param.businessType = "06";
		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));

	} else {
		alert("请先添保存赎回信息，再进行影像件上传！");
		return;
	}

}
function addFileWindow(title, href) {
	$('<div id="addFileWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		width : 1000,
		height : 600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}
//返回
function returnRedemptionConfirmList() {
	$('#redemptionConfirmWindow').window('destroy');
}