var custAllTradeTableEditRowIndex = null;
var custAllTradeTable;
var netValue=null;
var publicDay=null;
var custNo=null;
var productId=null;
var redemptionInfoId=null;
var operate=null;

/**
 * 页面初始化加载
 */
jQuery(function($) {
	operate = $("#addTradeRedemption_loadFlag").val();//loadFlag;
	redemptionInfoId = $("#addTradeRedemption_redemptionInfoId").val();
	initAllCombobox();
	initCustAllTradeTable();
	if (operate=="updateTradeRedemption") {
		getTradeRedemptionInfo();
		$('#clearTradeInfoByCustandProButton').attr('disabled',"true");//设置按钮不能使用
		$('#queryTradeInfoByCustandProButton').attr('disabled',"true");//设置按钮不能使用
		custNo=$("#addTradeRedemption_custNo").val();
	}
});


//修改时候页面初始获取值
function getTradeRedemptionInfo() {
	if (redemptionInfoId != null || redemptionInfoId != undefined|| redemptionInfoId != "") {
		var params = {};
		params.redemptionInfoId = redemptionInfoId;
		params.operate = operate;
		$.ajax({
			type:'post',
			url:'redemption/getTradeRedemptionInfo',
			data:'queryParams='+$.toJSON(params),
			cache:false,
			success:function(returnData){
				try {
					var jsonObj = returnData.obj;
					productId=jsonObj.redemptionInfo.pdProductId;
					if(returnData.success){
						//重新加载赎回对应开放日的下拉框
						getOpenDayByProductId(productId);
						netValue=jsonObj.redemptionInfo.referenceNetValue;
						
						//赎回参考信息赋值
						setInputValueById("redemptionRefenceInfoForm",jsonObj.redemptionInfo);
						//赎回参考信息赋值
						setInputValueById("redemptionRefenceInfoForm",jsonObj.redemptionInfoNetInfo[0]);
						//赎回交易信息列表赋值
						if(jsonObj.redemptionInfoList!=null&&jsonObj.redemptionInfoList!=undefined){
							clearAllRows(custAllTradeTable);
							loadJsonObjData("custAllTradeTable",jsonObj.redemptionInfoList);
						}
						//赎回客户产品信息赋值
						setInputValueById("redemptionInfoQeuryForm",jsonObj.redemptionCustProInfo[0]);
						//赋值赎回总份额和总金额
						setInputValueById("redemptionTradeInfoForm",jsonObj.redemptionInfo);
					}else{
						$.messager.alert('提示', returnData.msg);
					}
				} catch (e) {
					$.messager.alert('提示', e);
				}
			}
		});
	} else {
		$.messager.alert('提示',"查询赎回信息时获取赎回流水号失败！");
		return;
	}

}


function initAllCombobox() {
	var addTradeRedemptionProductNameCombobox;
    //修改的时候下拉框不能编辑
	if (operate == "updateTradeRedemption") {
		// 客户姓名
		$("#addTradeRedemption_custName").combobox({
			url : contextPath + '/redemption/redemtionCustomerQuery',
			valueField : 'code',
			textField : 'codeName',
			disabled:true  
			
		});

		// 赎回产品信息
		$("#addTradeRedemption_productName").combobox({
			url : contextPath + '/redemption/redemtionProductQuery',
			valueField : 'code',
			textField : 'codeName',
			disabled:true
		});

	} else {
		// 客户姓名
		$("#addTradeRedemption_custName").combobox({
			url : contextPath + '/redemption/redemtionCustomerQuery',
			valueField : 'code',
			textField : 'codeName',
			onSelect:function(){
				$("#addTradeRedemption_productName").combobox('setValue',null);
				var custNo=$("#addTradeRedemption_custName").combobox('getValue');
				//根据客户查找相应的产品
				var url = contextPath+ '/redemption/redemtionProductQueryByCustNo?custNo='+ custNo;
				addTradeRedemptionProductNameCombobox.combobox("reload", url);
				//根据客户查找对应的证件号码
				$.ajax({
					type:'post',
					url:'redemption/queryCustomerInfo',
					data:'&custNo='+custNo,
					cache:false,
					success:function(returnData){
						try {
							if(returnData.success){
								//先清空
								$("#addTradeRedemption_idNo").val();
								var idNo = returnData.obj;
//								console.info(idNo);
								$("#addTradeRedemption_idNo").val(idNo);
							}else{
								$.messager.alert('提示', returnData.msg);
							}
						} catch (e) {
							$.messager.alert('提示', e);
						}
					}
				});
				
			}
		
		});

		// 赎回产品信息
		addTradeRedemptionProductNameCombobox=$("#addTradeRedemption_productName").combobox({
			url : contextPath + '/redemption/redemtionProductQuery',
			valueField : 'code',
			textField : 'codeName'
		});

	}
}

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
					title : '<font color="red">赎回份额(填写)</font>',
					width : 100,
					editor: {
						type : 'numberbox',
						options:{
							precision:4,
							onChange: function(newValue,oldValue){
								$(this).numberbox("setValue",newValue);
								if (netValue==null||netValue==""&&netValue==undefined) {
									$.messager.alert('提示',"请先选择赎回对应开放日获得参考净值");
									return;
								}
								else{
									if(newValue!=null&&newValue!=""&&newValue!=undefined&&custAllTradeTableEditRowIndex!=null){
										var rows = custAllTradeTable.datagrid("getRows");
										var allNewValue=0;
										for ( var int = 0; int < rows.length; int++) {
											
											var writeRedemptionShare = 0;
											//如果为这一行
											if(int==custAllTradeTableEditRowIndex){
												
												writeRedemptionShare = newValue;
												
											}else{
												
												writeRedemptionShare=rows[int].redemptionShare;
											}
											
											//如果不为空，
											if(writeRedemptionShare==""||writeRedemptionShare==undefined||writeRedemptionShare==null){
												
												writeRedemptionShare=0;
											}
											allNewValue += parseFloat(writeRedemptionShare);
										}
										$("#addTradeRedemption_redeemShares").val(allNewValue);//赎回总份额
										$("#addTradeRedemption_redeemMoney").val(netValue*10000*allNewValue/10000);//赎回总金额
										var currentShare = custAllTradeTable.datagrid('getRows')[custAllTradeTableEditRowIndex]['currentShare'];
										var residualShare = currentShare-newValue;
										var residualShareEditor = custAllTradeTable.datagrid('getEditor', {index:custAllTradeTableEditRowIndex,field:'residualShare'});
										$(residualShareEditor.target).numberbox('setValue',residualShare);
									}
								}
							}
						}
					},
					formatter : function(value, row, index) {
						return row.redemptionShare;
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
		},
		onClickRow:function(rowIndex){
			custAllTradeTableLockOneRow();
			custAllTradeTableEditOneRow(rowIndex);
			//编辑框绑定input事件
			var editors = $('#custAllTradeTable').datagrid('getEditors', rowIndex);
			var redemptionShareEditor = editors[0];
			redemptionShareEditor.target.bind('input propertychange', function(e){  
               //将本次修改的值更新到rowData的相应列数据中  
               //rowData[workRateEditor.field] = $(this).val();
				 var tipsContent = numToUpper($(this).val());
				 //alert($(this).val());
				 $(this).tips({
                       side:1,  //1,2,3,4 分别代表 上右下左
                       msg:tipsContent,//tips的文本内容
                       color:'#FFF',//文字颜色，默认为白色
                       bg:'#FD9720',//背景色，默认为红色
                       time:3,//默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
                       x:0,// 默认为0 横向偏移 正数向右偏移 负数向左偏移
                       y:0 // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
               });
           }); 
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

//根据客户号和产品名称查询相关的交易信息
function  queryTradeInfoByCustandPro(){
	  custNo = $("#addTradeRedemption_custName").combobox("getValue");
	  productId = $("#addTradeRedemption_productName").combobox("getValue");
	if (custNo == null || custNo == undefined || custNo=="") {
		$.messager.alert('提示',"请选择需要赎回的客户姓名和产品名称！");
		return;
	}
	if (productId == null || productId == undefined || productId=="") {
		$.messager.alert('提示',"请选择需要赎回的客户姓名和产品名称！");
		return;
	}
	
	//根据产品Id加载开放日
	getOpenDayByProductId(productId);
	
	//查询赎回交易信息列表信息
	$.ajax({
		type:'post',
		url:'redemption/queryTradeInfoByCustandPro',
		data:'productId='+productId+'&custNo='+custNo,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					clearAllRows(custAllTradeTable);
					var dataObj = returnData.obj;
					loadJsonObjData("custAllTradeTable",dataObj);
					
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function getOpenDayByProductId(productId){
	//点击【查询】后加载选择产品的所有开放日信息
	$("#addTradeRedemption_expectOpenDay").combobox({
		url : contextPath + '/redemption/redemtionExpectOpenDayQuery?productId='+productId,
		valueField : 'code',
		textField : 'codeName',
		onSelect:function(record){
			var openDay=record.code;
			//点击所选的开放日后查询相应的开放日净值信息
			$.ajax({
				type:'post',
				url:'redemption/queryNetValueByOpenDay',
				data:'openDay='+openDay+'&productId='+productId,
				cache:false,
				success:function(returnData){
					try {
						if (returnData.success) {
							netValue = returnData.obj[0].netValue;
							publicDay= returnData.obj[0].publicDay;
							$("#addTradeRedemption_netValue").val(netValue);
							$("#addTradeRedemption_publicDay").val(publicDay);
							
						}
						else{
							$.messager.alert('提示', returnData.msg);
							return;
						}
					} catch (e) {
						$.messager.alert('提示', e);
					}
				}
			});
		}
	});
	
	
}


//校验赎回
function checkData(){
	//如果有多笔交易，先赎回第一笔交易的份额
	var allData = custAllTradeTable.datagrid("getRows");
	var sumInnerRedemptionShare = 0;
	if(allData.length>1){
		for(var i=0;i<allData.length;i++){
			var rowData = allData[i];
			//当前份额
			var currentShare = rowData.currentShare;
			//赎回份额
			var redemptionShare = rowData.redemptionShare;
			//剩余份额，计算得到
			var residualShare = currentShare - redemptionShare;
			if(currentShare<=0){
				continue;
			}
			if(residualShare>0){
				sumInnerRedemptionShare = 0;
				for(var j=i+1;j<allData.length;j++){
					var innerRowData = allData[j];
					var innerRedemptionShare = innerRowData.redemptionShare;
					sumInnerRedemptionShare += innerRedemptionShare;
				}
				if(sumInnerRedemptionShare>0){
					break;
				}
			}
			
		}
		if(sumInnerRedemptionShare>0){
			$.messager.alert('提示', "请按认购先后顺序依次申请赎回！", 'info');
			return false;
		}
	}
	return true;
}





//保存赎回申请的信息
function saveRedemptionTradeInfo() {
	if(!custAllTradeTableLockOneRow()){
		return;
	}
	// 校验赎回参考信息必填项
	if (!$("#redemptionRefenceInfoForm").form("validate")) {
		$.messager.alert('提示', "请输入赎回参考信息的必填项！", 'info');
		return;
	}
	// 校验是否有赎回交易信息
	var rows = custAllTradeTable.datagrid("getRows");
	if (rows.length == 0) {
		$.messager.alert('提示', "没有赎回交易信息记录，无法进行保存", 'info');
		return;
	} else {
		for ( var int = 0; int < rows.length; int++) {
			// 交易号
			var tradeNo = rows[int].tradeNo;
			// 赎回份额
			var redeemShares = rows[int].redemptionShare;
			// 可赎回份额
			var redeemableShare = rows[int].redeemableShare;
			// 判断赎回份额是否填写，如填写是否大于剩余份额
			if (redeemShares == null || redeemShares == undefined|| redeemShares == "") {
				$.messager.alert('提示', "请输入"+tradeNo+"该笔交易的赎回份额,如不赎回请填写：0", 'info');
				return;
			}
			else {
		         if (redeemShares > redeemableShare) {
					$.messager.alert('提示', tradeNo+ "：该笔交易的本次赎回份额大于可赎回份额,请重新填写", 'info');
					return;
				}
			}
		}
	}
	
	//校验数据
	if(!checkData()){
		return;
	}
	
	var param={};
	
	// 赎回参考信息
	var redemptionRefenceInfoJson = formDataToJsonStr($("#redemptionRefenceInfoForm").serialize());
	param.redemptionRefenceInfo = redemptionRefenceInfoJson;
	// 赎回交易信息
	var redemptionTradeInfoJson = formDataToJsonStr($("#redemptionTradeInfoForm").serialize());
	param.redemptionTradeInfo = redemptionTradeInfoJson;
	
	var custAllTradeTableData = $("#custAllTradeTable").datagrid("getRows");
	param.custAllTradeTableInfo = $.toJSON(custAllTradeTableData);
	
	param.operateFlag=operate;
	
	param.productId=productId;
	
	param.custNo=custNo;
	
	param.redemptionInfoId=redemptionInfoId;
	
	//保存赎回相关的信息
	$.ajax({
		type:'post',
		url:'redemption/saveRedemptionTradeInfo',
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					redemptionInfoId=returnData.obj;
					$.messager.alert('提示', returnData.msg);
					//将新增信息全部清空
//					clearDatagrid(custAllTradeTable);
//					$("#redemptionInfoQeuryForm").form("clear");
//					$("#redemptionRefenceInfoForm").form("clear");
//					$("#redemptionTradeInfoForm").form("clear");
					
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


//下载赎回单
function downloadApplicationForm(){
	if (redemptionInfoId==null||redemptionInfoId==undefined||redemptionInfoId=="") {
		$.messager.alert('提示',"请先保存赎回信息，在生成赎回申请单",'info')
		return;
	}
	else{
		$.messager.confirm('提示', '确定要生成赎回申请单吗?', function(result) {
			if (result) {
					$.messager.progress({
						title:'温馨提示',
						msg:'正在生成并下载PDF文件，请稍后...'
					});
					$.post('redemption/printApplicationForm?redemptionInfoId=' + redemptionInfoId, function(reData){
							if(reData.success){
								var param={};
								param.redemptionInfoId=redemptionInfoId;
								$.messager.progress('close');
								addDownloadWindow('赎回申请单下载', contextPath+"/redemption/downApplicationForm?param="+$.toJSON(param));	
							}else{
								$.messager.progress('close');
								$.messager.alert('提示', reData.msg);
							}
					});
			}
		});
	}
}

//下载赎回单
//function downloadApplicationForm(){
//	$.messager.alert('提示',"赎回申请书下周一上线，敬请期待",'info')
//	return;
//}

//提交确认
function commitRedemptionCheck(){
if (redemptionInfoId==null||redemptionInfoId==undefined||redemptionInfoId=="") {
	$.messager.alert('提示',"赎回申请信息未保存，请先保存赎回申请信息！",'info')
	return;
}

$.ajax({
		type:'post',
		url:'redemption/commitRedemptionCheck',
		data:'redemptionInfoId='+redemptionInfoId,
		cache:false,
		success:function(returnData){
			try {
				if(returnData.success){
					redemptionInfoId=returnData.obj;
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

//清空查询条件
function clearTradeInfoByCustandPro() {
	$('#redemptionInfoQeuryForm').form('clear');
}

//返回
function returnRedemptionList(){
	$('#tradeRedemptionWindow').window('destroy');
}

//上传影像件
function uploadApplicationForm() {
	if (redemptionInfoId != null) {
		var param = {};
		param.businessNo = redemptionInfoId;
		//06-赎回申请单影像件
		param.businessType = "06";
		addFileWindow('文件上传', contextPath + "/fileUpload/fileUploadUrl?param="+ $.toJSON(param));

	} else {
		$.messager.alert('提示',"请先添保存赎回信息，再进行影像件上传！");
		return;
	}

}


//赎回申请单下载窗口
function addDownloadWindow(title, href) 
{
	$('<div id="addPrintWindow"/>').dialog({
	href : href,
	modal : true,
	title : title,
	width:800,
	height:500,
	inline : false,
	minimizable : false,
	onClose : function() {
	$(this).window('destroy');
	}
	});
}


//上传影像件窗口
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
