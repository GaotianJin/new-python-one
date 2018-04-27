var tradeType = null;
var tradeInfoId = null;
var riskTradeInfoTableEditIndex = null;
var operate = null;
var netValue=null;
var productSubType = null;
var custBaseInfoId = null;
var investCustomerType = null;
jQuery(function($) {
	tradeType = $("#tradeType").val();
	tradeInfoId = $("#tradeInfoId").val();
	operate = $("#operate").val();
	productSubType = $("#productSubType").val();
	custBaseInfoId = $("#custBaseInfoId").val();
	investCustomerType = $("#investCustomerType").val();
	/*$( '#wealth_subscriptionCopies' ).attr( "disabled", "true" );
	$("#wealth_subscriptionCopies").attr( {readonly : 'true'} );*/
	//获取交易详细信息
	getTradeDetailInfo();
	/*var tradeDetailInfo = $("#tradeDetailInfo").val();
	console.info("tradeDetailInfo==="+tradeDetailInfo);
	setInputValueById("tradeDetailInfoDiv",eval("("+tradeDetailInfo+")"));*/
	//console.info("tradeInfoId==="+tradeInfoId);
	if(tradeType=="1"){
		if(productSubType =='02'){
			$("#expectOpenDayName").hide(); 
		}else{
			$("#foundDateName").hide();
		}
		if(productSubType == 01||productSubType ==02){
			$("#wealth_tradeStatus").combobox({
				url : contextPath+"/codeQuery/tdCodeQueryIn?codeType=tradeStatus&codeListStr=['09','10']",
				required : true,
				valueField : 'code',
				textField : 'codeName'
			});
			$("#riskTradeStatus").css("display", "none"); 
			$("#wealthTradeStatus").css("display", ""); 
			$("#updTradeStatus_netValue").hide();
		}
		else {
			$("#wealth_tradeStatus").combobox({
			url : contextPath+"/codeQuery/tdCodeQueryIn?codeType=tradeStatus&codeListStr=['09','10']",
			required : true,
			valueField : 'code',
			textField : 'codeName'
		});
		$("#riskTradeStatus").css("display", "none"); 
		$("#wealthTradeStatus").css("display", ""); 
		}
	}else{
		$("#risk_tradeStatus").combobox({
			url : contextPath+"/codeQuery/tdCodeQueryIn?codeType=tradeStatus&codeListStr=['05','06','07','08']",
			required : true,
			valueField : 'code',
			textField : 'codeName'
		});
		$("#riskTradeStatus").css("display", ""); 
		$("#wealthTradeStatus").css("display", "none"); 
		$("#expectOpenDayName").hide(); 
		$("#updTradeStatus_expectOpenDay").hide(); 
		initRiskTradeInfoTable();
		getRiskTradeInfo();
	}
	
	if(operate=="Modify"&&tradeType!="2"){
		getLastTradeStatusInfo();
	}
	
	$("#wealth_actuSubscriptionAmount").bind('input propertychange', function(e){  
        //将本次修改的值更新到rowData的相应列数据中  
        //rowData[workRateEditor.field] = $(this).val();
			 var tipsContent = numToUpper($(this).val());
			// alert(tipsContent);
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
});
//离交事件，计算份额
function getSubscriptionCopies(){
	var amount = $("#wealth_actuSubscriptionAmount").val();
	netValue = $("#updTradeStatus_netValue").val();
	if(netValue == null||netValue==undefined||netValue==""){
		$("#wealth_subscriptionCopies").val(null);
	}
	else{
		$("#wealth_subscriptionCopies").val((amount/netValue).toFixed(2));
		}
};

var riskTradeInfoTable;
function initRiskTradeInfoTable(){
	riskTradeInfoTable = $("#riskTradeInfoTable").datagrid({
		//title : '交易产品信息', // 标题
		method : 'post',
		////iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		//pageList:[5,10,15,20],
		//pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'tradeStatusInfoId',
					title : '交易状态流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.tradeStatusInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'tradeInfoId',
					title : '交易流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.tradeInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'tradeNo',
					title : '交易号码',
					width : 100,
					formatter : function(value, row, index) {
						return row.tradeNo;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productId',
					title : '产品流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.productId;
					} // 需要formatter一下才能显示正确的数据
				},
				
				{
					field : 'productName',
					title : '产品名称',
					width : 100,
					formatter : function(value, row, index) {
						return row.productName;
					}
				},{
					field : 'policyNo',
					title : '保单号',
					width : 100,
					editor:	{
						type:'validatebox',
						options:{
							validType:'length[0,40]'
						}
					},
					formatter : function(value, row, index) {
						return row.policyNo;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'tradeStatus',
					title : '交易状态',
					width : 100,
					//sortable : true,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							url:contextPath+"/codeQuery/tdCodeQueryIn?codeType=tradeStatus&codeListStr=['05','06','07','08']",
							required:true,
							onSelect:function(){
								var tradeStatusName = $(this).combobox('getText');
								riskTradeInfoTable.datagrid('getRows')[riskTradeInfoTableEditIndex]['tradeStatusName'] = tradeStatusName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.tradeStatusName;
					}
				},{
					field : 'tradeStatusName',
					title : '交易状态名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.tradeStatusName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'statusDate',
					title : '状态日期',
					width : 100,
					editor: {
						type:'datebox',
						options:{
							required:true,
							validType:'validDate'
						}
					},
					formatter : function(value, row, index) {
						return row.statusDate;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'actuPremium',
					title : '实收保费',
					width : 100,
					//sortable : true,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:0,    
						    precision:2 
						}
					},
					formatter : function(value, row, index) {
						return row.actuPremium;
					}
				}]],
		onLoadSuccess : function() {
			$('#riskTradeInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			riskTradeInfoTableEditIndex = rowIndex;
			riskTradeInfoTable.datagrid('selectRow', rowIndex).datagrid('beginEdit', rowIndex);
		}
	});
}

function getRiskTradeInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/tradeS/getRiskTradeInfo",
		data:'tradeInfoId='+tradeInfoId+'&operate='+operate,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					loadJsonObjData("riskTradeInfoTable",reData.obj);
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function submitTradeStatus(){
	
	if(!$("#TradeStausWealthForm").form("validate")){
		$.messager.alert('提示', "请检查页面必录项是否全部录入正确");
		return;
	}
	
	var formData = null;
	if(tradeType=="1"){
		formData = formDataToJsonStr($("#TradeStausWealthForm").serialize());
	}else{
		//formData = formDataToJsonStr($("#TradeStausInsuranceForm").serialize());
		riskTradeInfoTable.datagrid('acceptChanges');
		formData = riskTradeInfoTable.datagrid("getRows");
	}
	var param = {};
	param.tradeType = tradeType;
	param.tradeInfoId = tradeInfoId;
	param.tradeStatusInfo = formData;
	param.custBaseInfoId = custBaseInfoId;
	param.investCustomerType = investCustomerType;
	$.ajax({
		type:'post',
		url:contextPath+"/tradeS/saveTradeStatus",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					//$.messager.alert('提示', reData.msg);
					if(tradeType=="1"){
						$("#wealth_tradeStatusInfoId").val(reData.obj.tradeStatusInfoId);
					}else{
						//$("#risk_tradeStatusInfoId").val(reData.obj.tradeStatusInfoId);
						clearAllRows(riskTradeInfoTable);
						loadJsonObjData("riskTradeInfoTable",reData.obj);
					}
					$.messager.confirm('确认对话框', reData.msg+"，是否关闭此窗口？", function(r){
						if (r){
							$('#updTradeStatusWindow').window('destroy');
							queryTradeStrusList();
						}
					});
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}



function getLastTradeStatusInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/tradeS/getLastTradeStatusInfo",
		data:'tradeInfoId='+tradeInfoId,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var reDataObj = reData.obj;
					if(tradeType=="1"){
						setInputValueById("wealthTradeStatus",reDataObj);
					}else{
						setInputValueById("riskTradeStatus",reDataObj);
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
	
}


function getTradeDetailInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/tradeS/getTradeDetailInfo",
		data:'tradeInfoId='+tradeInfoId,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var reDataObj = reData.obj;
					setInputValueById("tradeDetailInfoDiv",reDataObj);
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function backListTradeInfo(){
	$('#updTradeStatusWindow').window('destroy');
}


function uploadTradeAttachInfo(){
	var loadFileType = null;
	var param = {};
	param.businessNo = $("#tradeInfoId").val();
	param.businessType = "04";
	param.operate = loadFileType;
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
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
