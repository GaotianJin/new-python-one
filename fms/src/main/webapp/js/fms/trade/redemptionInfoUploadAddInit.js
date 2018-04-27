var redemptionInfoId=null;
var custAllTradeTableEditRowIndex = null;
var  operate=null;

/**
 * 页面初始化加载
 */
jQuery(function($) {
	redemptionInfoId=$("#redemptionInfoUploadAdd_redemptionInfoId").val();
	operate=$("#redemptionInfoUploadAdd_loadFlag").val();
	//查看赎回明细信息
	if (operate=="detail") {
		initAllComboboxDetail();
		initCustAllTradeTable();
		getRedemptionUploadInfo();
		getActuRedemptionInfo();
		getDetailActuRedemptionInfo();
		$('#saveRedemptionInfoUploadButton').attr('disabled',"true");//设置按钮不能使用
//		$('#queryRedemptionApplicationFormButton').attr('disabled',"true");//设置按钮不能使用
		$('#returnRedemptionUploadInfoListButton').attr('disabled',"true");//设置按钮不能使用
	}
	else{
		//获取赎回Id
		initAllCombobox();
		initAllValidateBox();
		initCustAllTradeTable();
		getRedemptionUploadInfo();
		getActuRedemptionInfo();
		
	}
});

//文本输入必录项校验
function initAllValidateBox(){
	$('#rredemptionInfoUploadAdd_actuallyNetValue').validatebox({required:true});
	$('#redemptionInfoUploadAdd_actuallyRedeemShares').validatebox({required:true});
}


function initAllComboboxDetail() {
	
	//赎回结论
		$("#redemptionInfoUploadAdd_Conclusion").combobox({
			url : contextPath + '/codeQuery/tdCodeQuery?codeType=redemptionAuditConclusion',
			valueField : 'code',
			textField : 'codeName',
			editable:false,
			disable:true
		});
	//客户
		$("#redemptionInfoUploadAdd_custName").combobox({
		url : contextPath + '/redemption/redemtionCustomerQuery',
		valueField : 'code',
		textField : 'codeName',
		editable:false  
		});
		
	//产品名称
		
		$("#redemptionInfoUploadAdd_productName").combobox({
			url : contextPath + '/redemption/redemtionProductQuery',
			valueField : 'code',
			textField : 'codeName',
			editable:false
		});
 }



function initAllCombobox() {
	
	//赎回结论
		$("#redemptionInfoUploadAdd_Conclusion").combobox({
			url : contextPath + '/codeQuery/tdCodeQuery?codeType=redemptionAuditConclusion',
			valueField : 'code',
			textField : 'codeName',
			required:true
		});
	//客户
		$("#redemptionInfoUploadAdd_custName").combobox({
		url : contextPath + '/redemption/redemtionCustomerQuery',
		valueField : 'code',
		textField : 'codeName',
		editable:false  
		});
		
	//产品名称
		
		$("#redemptionInfoUploadAdd_productName").combobox({
			url : contextPath + '/redemption/redemtionProductQuery',
			valueField : 'code',
			textField : 'codeName',
			editable:false
		});
 }


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
					field : 'actualRedemptionShare', 
					title : '实际赎回份额(填写)',
					width : 180,
					editor: {
						type : 'numberbox',
						options:{
							precision:4,
							onChange: function(newValue,oldValue){
								$(this).numberbox("setValue",newValue);
								if(newValue!=null&&newValue!=""&&newValue!=undefined&&custAllTradeTableEditRowIndex!=null){
									var rows = custAllTradeTable.datagrid("getRows");
									var allNewValue=0;
									for ( var int = 0; int < rows.length; int++) {
										
										var writeActuRedemptionShare = 0;
										//如果为这一行
										if(int==custAllTradeTableEditRowIndex){
											
											writeActuRedemptionShare = newValue;
											
										}else{
											
											writeActuRedemptionShare=rows[int].actualRedemptionShare;
										}
										
										//如果不为空，
										if(writeActuRedemptionShare==""||writeActuRedemptionShare==undefined||writeActuRedemptionShare==null){
											
											writeActuRedemptionShare=0;
										}
										allNewValue += parseFloat(writeActuRedemptionShare);
									}
									
									$("#redemptionInfoUploadAdd_actuallyRedeemShares").val(allNewValue);//实际赎回总份额
//									$("#addTradeRedemption_redeemMoney").val(allNewValue*netValue);//实际赎回总金额
									var currentShare = custAllTradeTable.datagrid('getRows')[custAllTradeTableEditRowIndex]['currentShare'];
									var residualShare = currentShare-newValue;
									var residualShareEditor = custAllTradeTable.datagrid('getEditor', {index:custAllTradeTableEditRowIndex,field:'residualShare'});
									$(residualShareEditor.target).numberbox('setValue',residualShare);
								}
							
							}
						}
					},
					
					formatter : function(value, row, index) {
						return row.actualRedemptionShare;
					}
				} , 
				{
					field : 'residualShare',
					title : '剩余份额',
					width : 100,
					editor: {
						type : 'numberbox',
						options:{
							precision:4,
							disabled:true
						}
					},
					formatter : function(value, row, index) {
						return row.residualShare;
					}
				}, 
				{
					field : 'bankName',
					title : '受益账户银行',
					width : 180,
					formatter : function(value, row, index) {
						return row.bankName;
					}
				},
				{
					field : 'bankNo',
					title : '受益账号',
					width : 180,
					formatter : function(value, row, index) {
						return row.bankNo;
					}
				}
				] ],
		onLoadSuccess : function() {
			$('#custAllTradeTable').datagrid('clearSelections'); 
		},
		onClickRow:function(rowIndex){
			custAllTradeTableLockOneRow();
			custAllTradeTableEditOneRow(rowIndex);
		}
	});
}

//编辑指定行
function custAllTradeTableEditOneRow(index){
	
	if(editOneRow(custAllTradeTable,custAllTradeTableEditRowIndex,index)){
		custAllTradeTableEditRowIndex = index;
	}
}
//锁定编辑行
function custAllTradeTableLockOneRow(){
	if(lockOneRow(custAllTradeTable,custAllTradeTableEditRowIndex)){
		custAllTradeTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}


//获取赎回确认信息
function getRedemptionUploadInfo(){
	if (redemptionInfoId != null || redemptionInfoId != undefined|| redemptionInfoId != "") {
		var params = {};
		params.redemptionInfoId = redemptionInfoId;
		$.ajax({
			type:'post',
			url:'redemption/getRedemptionUploadInfo',
			data:'queryParams='+$.toJSON(params),
			cache:false,
			success:function(returnData){
				try {
					var jsonObj = returnData.obj;
					if(returnData.success){
						//赎回参考信息赋值
						setInputValueById("redemptionInfoUploadReferenceForm",jsonObj.redemptionInfo);
						
						//赎回参考信息赋值
						setInputValueById("redemptionInfoUploadReferenceForm",jsonObj.redemptionInfoNetInfo[0]);
						//赎回交易信息列表赋值
						if(jsonObj.redemptionInfoList!=null&&jsonObj.redemptionInfoList!=undefined){
							clearAllRows(custAllTradeTable);
							loadJsonObjData("custAllTradeTable",jsonObj.redemptionInfoList);
						}
						//赎回客户产品信息赋值
						setInputValueById("redemptionInfoUploadQueryForm",jsonObj.redemptionCustProInfo[0]);
						//本次赎回总份额和总金额赋值
						setInputValueById("redemptionUploadTradeInfoForm",jsonObj.redemptionInfo);
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


/**
 * 获取实际赎回信息
 */
function getActuRedemptionInfo(){
	$.ajax({
		type:'post',
		url:'redemption/getActuRedemptionInfo',
		data:'redemptionInfoId='+redemptionInfoId,
		cache:false,
		success:function(returnData){
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					//赎回参考信息赋值
					if(jsonObj!=null&&jsonObj!=undefined){
						
						setInputValueById("redemptionUploadActuInfoDiv",jsonObj);
					}
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 明细查看页面获取实际参数信息
 */
function getDetailActuRedemptionInfo(){
	$.ajax({
		type:'post',
		url:'redemption/getDetailActuRedemptionInfo',
		data:'redemptionInfoId='+redemptionInfoId,
		cache:false,
		success:function(returnData){
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					//赎回参考信息赋值
					if(jsonObj!=null&&jsonObj!=undefined){
						setInputValueById("redemptionUploadConclusionInfoForm",jsonObj.redemptionDetailUploadConclusion[0]);//获取赎回确认结论信息
						setInputValueById("redemptionUploadActuallyInfoForm",jsonObj.redemptionDetailActuPrem[0]);//获取赎回参数实际信息
						if(jsonObj.redemptionDetailUploadConclusion[0]!=null&&jsonObj.redemptionDetailUploadConclusion[0]!=undefined){
							$("#redemptionInfoUploadAdd_remark").val(jsonObj.redemptionDetailUploadConclusion[0].remark);
						}
					}
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


//保存赎回明细上传信息
function saveRedemptionInfoUpload(){
	if(!custAllTradeTableLockOneRow()){
		return;
	}
	
	//校验实际赎回交易份额有没有填写
	var rows = custAllTradeTable.datagrid("getRows");
	for ( var int = 0; int < rows.length; int++) {
		// 交易号
		var tradeNo = rows[int].tradeNo;
		// 赎回份额
		var actualRedemptionShare = rows[int].actualRedemptionShare;
		// 可赎回份额
		var redeemableShare = rows[int].redeemableShare;
		// 判断赎回份额是否填写，如填写是否大于剩余份额
		if (actualRedemptionShare == null || actualRedemptionShare == undefined|| actualRedemptionShare == "") {
			$.messager.alert('提示', "请输入"+tradeNo+"该笔交易的实际赎回份额,如不赎回请填写：0", 'info');
			return;
		}
		else {
	         if (actualRedemptionShare > redeemableShare) {
				$.messager.alert('提示', tradeNo+ "：该笔交易的本次赎回份额大于可赎回份额,请重新填写", 'info');
				return;
			}
		}
	}
	
	//校验必填项是否均填写
	if (!$("#redemptionUploadActuallyInfoForm").form("validate")) {
		$.messager.alert('提示', "请输入赎回实际参数信息必填项！", 'info');
		return;
	}
	
	if (!$("#redemptionUploadConclusionInfoForm").form("validate")) {
		$.messager.alert('提示', "请输入赎回确认信息结论！", 'info');
		return;
	}
	
	var param={};
	
	// 赎回结论信息
	var redemptionUploadConclusionInfoJson = formDataToJsonStr($("#redemptionUploadConclusionInfoForm").serialize());
	param.redemptionUploadConclusionInfo = redemptionUploadConclusionInfoJson;
	// 赎回实际参数信息
	var redemptionUploadActuallyInfoJson = formDataToJsonStr($("#redemptionUploadActuallyInfoForm").serialize());
	param.redemptionUploadActuallyInfo = redemptionUploadActuallyInfoJson;
	//获取交易赎回信息列表
	var custAllTradeTableData = $("#custAllTradeTable").datagrid("getRows");
	param.custAllTradeTableInfo = $.toJSON(custAllTradeTableData);
	
	param.redemptionInfoId=redemptionInfoId;
	param.redemptionOperationId = $("#redemptionOperationId").val();
	//保存赎回相关的信息
	
	$.messager.confirm('提示',"赎回信息是否录入完毕？",function(r){
		if(r){
			$.ajax({
				type:'post',
				url:'redemption/saveRedemptionUploadInfo',
				data:'param='+encodeURI($.toJSON(param)),
				cache:false,
				success:function(returnData){
					try {
						if(returnData.success){
							if(returnData.obj!=null){
								$("#redemptionOperationId").val(returnData.obj);
							}
							$.messager.alert('提示', returnData.msg);
							$("#redemptionUploadInfoWindow").dialog('close');
							$("#redemptionUploadInfoListId").datagrid('reload');
						}else{
							$.messager.alert('提示', returnData.msg);
						}
					} catch (e) {
						$.messager.alert('提示', e);
					}
				}
			});
		}else{
			return;
		}
	});
	
	
	
	
	
}

//返回
function returnRedemptionUploadInfoList() {
	$('#redemptionUploadInfoWindow').window('destroy');
}

//查看影像件
function queryRedemptionApplicationForm(){

	if (redemptionInfoId != null) {
		var param = {};
		param.businessNo = redemptionInfoId;
		param.operate = "queryFile";
		//产品
		param.businessType = "06";
		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));
	} else {
		alert("请先添保存赎回信息，再进行影像件上传！");
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

//计算实际赎回总金额
function calculateActuallyTotalMoney(){
	var actuRedemptionNetValue=$("#rredemptionInfoUploadAdd_actuallyNetValue").val();
	var actuRedeemShares=$("#redemptionInfoUploadAdd_actuallyRedeemShares").val();
	actuRedeemShares = actuRedeemShares.replace(/,/g, "");
	if (actuRedemptionNetValue!=null&&actuRedemptionNetValue!=undefined&&actuRedemptionNetValue!=""&&actuRedeemShares!=""&&actuRedeemShares!=undefined&&actuRedeemShares!=null) {
		var actuRedemptionAllMoney=parseFloat(actuRedemptionNetValue)*10000*parseFloat(actuRedeemShares)/10000;
		$("#redemptionInfoUploadAdd_actuallyRedeemMoney").val(actuRedemptionAllMoney);
	}
	
}