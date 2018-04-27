var json={"total":3,"rows":[{'tradeNo':'1201505220001','custName':'张三','custMobile':'13838380038','money':'1000000.00','agentName':'李世杰','agentMobile':'13567899876','smsContent':'尊敬的张三先生您好！您提交的悟空巨鲸对冲量化一期基金产品1000000.00元认购成功，成立日2015年5月22日，认购份额以份额确认书为准。详询客服热线95059-8或您的财富顾问。','smsStatus':'未发送'},
							{'tradeNo':'1201505220002','custName':'李四','custMobile':'13838389038','money':'2000000.00','agentName':'李世杰','agentMobile':'13567899876','smsContent':'尊敬的李四先生您好！您提交的悟空巨鲸对冲量化一期基金产品2000000.00元认购成功，成立日2015年5月22日，认购份额以份额确认书为准。详询客服热线95059-8或您的财富顾问。','smsStatus':'未发送'},
														{'tradeNo':'1201505220003','custName':'王五','custMobile':'13838388038','money':'2000000.00','agentName':'李世杰','agentMobile':'13567899876','smsContent':'尊敬的王五先生您好！您提交的悟空巨鲸对冲量化一期基金产品2000000.00元认购成功，成立日2015年5月22日，认购份额以份额确认书为准。详询客服热线95059-8或您的财富顾问。','smsStatus':'已发送'}]};
/**
 * 初始化下拉框
 */
var sysSmsBatchId = null;
var smsType = null;
jQuery(function($) {
	sysSmsBatchId = $("#sendSms_sysSmsBatchId").val();
	smsType = $("#sendSms_smsType").val();
	//短信状态
	$('#sendSms_sendStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=smsSendStatus",
		valueField : 'code',
		textField : 'codeName'
	});
	//发送对象类型
	$('#sendSms_sendObjType').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=smsSendObjType",
		valueField : 'code',
		textField : 'codeName'
	});
	initSmsDetailTable();
});	

/**
 * 初始化Table
 */
var smsDetailTable;
function initSmsDetailTable(){
	smsDetailTable = $("#sendSms_smsDetailTable").datagrid({
		title : '短信详细信息列表', // 标题
		method : 'post',
		singleSelect : false, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "sms/getSmsDetailInfo", // 数据来源
		queryParams: {param:$.toJSON({sysSmsBatchId:sysSmsBatchId,smsType:smsType})},
		//sortName : 'id', // 排序的列
		//sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'sysSmsInfoId', // 主键字段
		rownumbers:true,
		nowrap: false,//单元格是否可以换行
		pagination:true,
		pageSize:10,
		pageList:[10,15,20,25,30],
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'sysSmsInfoId',
					title : '短信流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.sysSmsInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'sysSmsBatchId',
					title : '短信批次流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.sysSmsBatchId;
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
					width : 30,
					formatter : function(value, row, index) {
						return row.tradeNo;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'sendObjName',
					title : '发送对象姓名',
					
					width : 25,
					formatter : function(value, row, index) {
						return row.sendObjName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'sendMobile',
					title : '发送对象电话',
					width : 25,
					formatter : function(value, row, index) {
						return row.sendMobile;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'sendObjTypeName',
					title : '发送对象类型',
					width : 25,
					formatter : function(value, row, index) {
						return row.sendObjTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
				/*{
					field : 'money',
					title : '认购/分配金额',
					width : 30,
					formatter : function(value, row, index) {
						return row.money;
					}
				},*/{
					field : 'agentName',
					title : '财富顾问姓名',
					sortable:true,
					width : 25,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},
				{
					field : 'agentMobile',
					title : '财富顾问电话',
					width : 30,
					formatter : function(value, row, index) {
						return row.agentMobile;
					}
				},{
					field : 'sendContent',//textarea
					title : '短信内容',
					width : 80,
					nowrap: false,
					/*editor: {
						type : 'textarea'
					},*/
					formatter : function(value, row, index) {
						return row.sendContent;
					}
				},{
					field : 'sendStatus',
					title : '短信状态',
					hidden : true,
					formatter : function(value, row, index) {
						return row.sendStatus;
					}
				},{
					field : 'sendStatusName',
					title : '短信状态',
					width : 20,
					formatter : function(value, row, index) {
						return row.sendStatusName;
					}
				}]],
		onLoadSuccess : function() {
			$('#sendSms_smsDetailTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			
			//根据第一行数据编号的长度来设置标题
			var rowss=$('#sendSms_smsDetailTable').datagrid('getRows');
			var ColumnOptions=$('#sendSms_smsDetailTable').datagrid("getColumnOption","tradeNo");
			if(rowss[0]!=null&&rowss[0].tradeNo!=undefined){
			if(rowss[0].tradeNo.length<13&&ColumnOptions.title!="客户编号"){
				ColumnOptions.title="客户编号";
				$('#sendSms_smsDetailTable').datagrid();//刷新当前表格，使重命名生效。
			}
			}
		},
		onSortColumn : function(sort, order){
			querySms(sort, order);
		}
	});
}


/**
 * 查询短信
 */
function querySms(sort, order){
	var queryParam = {};
	var queryCondition = formDataToJsonStr($("#sendSms_queryConditionForm").serialize());
	queryParam.queryCondition = queryCondition;
	queryParam.sysSmsBatchId = sysSmsBatchId;
	queryParam.smsType = smsType;
	if(sort!=null&&sort!=undefined){
		queryParam.sort = sort;
	}
	if(order!=null&&order!=undefined){
		queryParam.order = order;
	}
	smsDetailTable.datagrid('reload',{param:$.toJSON(queryParam)});
}
function querySmsByCondition(){
	var queryParam = {};
	var queryCondition = formDataToJsonStr($("#sendSms_queryConditionForm").serialize());
	queryParam.queryCondition = queryCondition;
	queryParam.sysSmsBatchId = sysSmsBatchId;
	queryParam.smsType = smsType;
	smsDetailTable.datagrid('load',{param:$.toJSON(queryParam)});
}

/**
 * 清空短信查询条件
 */
function clearQuerySmsCondition(){
	$("#sendSms_queryConditionForm").form("clear");
}

/**
 * 发送
 */
function sendSms(){
	var param = smsDetailTable.datagrid("getSelections");
	if(param==null||param==undefined||param.length==0){
		$.messager.alert('提示', "请选择要发送的短信！");
		return;
	}
	var count = 0;
	for(var i=0;i<param.length;i++){
		var sendStatus = param[i].sendStatus;
		if(sendStatus=="02"){
			count++;
		}
	}
	if(count>0){
		$.messager.confirm('温馨提示', '您选中的短信中包含已发送的短信，是否继续并再次发送？', function(r){
			if (r){
				confirmSendSms(param);
			}
		});
	}else{
		confirmSendSms(param);
	}
	
}
function confirmSendSms(smsData){
	var data = $.toJSON(smsData);
	data = escape(encodeURIComponent(data));
	$.ajax({
		type:'post',
		url:contextPath+"/sms/sendSms",
		data:'sendSmsListData='+data,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', reData.msg);
					//发送成功后重新加载短信
					querySms();
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 返回
 */
function backSmsExtraction(){
	$("#sendSmsWindow").window('destroy');
	initSmsAuditTable();
};