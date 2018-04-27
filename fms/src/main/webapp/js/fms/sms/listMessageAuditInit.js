var json1={"total":3,"rows":[{'productName':'悟空巨鲸对冲量化一期基金','smsType':'产品成立短信','custDetail':'客户明细'},
                            {'productName':'潍坊三河特定多个客户专项资产管理计划(第五期)','smsType':'产品成立短信','custDetail':'客户明细'},
                            {'productName':'泰州通泰应收账款投资集合资金信托计划','smsType':'产品成立短信','custDetail':'客户明细'}]};
var json2={"total":3,"rows":[{'productName':'悟空巨鲸对冲量化一期基金','smsType':'净值公布短信','custDetail':'客户明细'},
                             {'productName':'潍坊三河特定多个客户专项资产管理计划(第五期)','smsType':'净值公布短信','custDetail':'客户明细'},
                             {'productName':'泰州通泰应收账款投资集合资金信托计划','smsType':'净值公布短信','custDetail':'客户明细'}]};
var json3={"total":3,"rows":[{'productName':'悟空巨鲸对冲量化一期基金','smsTypeName':'产品成立短信','custDetail':'客户明细'},
                             {'productName':'潍坊三河特定多个客户专项资产管理计划(第五期)','smsTypeName':'净值公布短信','custDetail':'客户明细'},
                             {'productName':'泰州通泰应收账款投资集合资金信托计划','smsTypeName':'收益分配短信','custDetail':'客户明细'}]};
jQuery(function($) {
	//产品名称
	$('#smsAudit_productId').combobox({
		url : contextPath+"/codeQuery/wealthproductQuery",
		valueField : 'code',
		textField : 'codeName'
	});
	//短信类型
	$('#smsAudit_smsType').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=smsType",
		valueField : 'code',
		textField : 'codeName'
	});
	//短信处理状态
	$('#smsAudit_smsStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=smsDealStatus",
		valueField : 'code',
		textField : 'codeName'
	});
	initSmsAuditTable();
});	

var smsAuditTable;
function initSmsAuditTable(){
	smsAuditTable = $("#smsAuditTable").datagrid({
		title : '短信列表', // 标题
		method : 'post',
		singleSelect : false, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//data : json3,
		url : "sms/getSmsAuditInfo", // 数据来源
		param : {},
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'sysSmsBatchId', // 主键字段
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[10,15,20,25,30],
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框sysSmsBatchId
				{
					field : 'sysSmsBatchId',
					title : '短信批次流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.sysSmsBatchId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productId',
					title : '产品名称',
					hidden:true,
					formatter : function(value, row, index) {
						return row.productId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productName',
					title : '产品名称',
					width : 200,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},
				 {
					field : 'smsType',
					title : '短信类型',
					hidden:true,
					formatter : function(value, row, index) {
						return row.smsType;
					}
				},{
					field : 'smsTypeName',
					title : '短信类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsTypeName;
					}
				},{
					field : 'smsStatus',
					title : '短信状态',
					hidden:true,
					formatter : function(value, row, index) {
						return row.smsStatus;
					}
				},{
					field : 'smsStatusName',
					title : '短信状态',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsStatusName;
					}
				},
				{
					field : 'shoudSendCount',
					title : '应发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.shoudSendCount;
					}
				},{
					field : 'actuSendCount',
					title : '实发数量',
					width : 100,
					formatter : function(value, row, index) {
						return row.actuSendCount;
					}
				},{
					field : 'smsCreateTime',
					title : '短信创建时间',
					width : 100,
					formatter : function(value, row, index) {
						return row.smsCreateTime;
					}
				},{
					field : 'custDetail',
					title : '详情',
					width : 100,
					formatter : function(value, row, index) {
						var param = {};
						param.smsType = row.smsType;
						param.sysSmsBatchId=row.sysSmsBatchId;
						return "<a href='#'  onclick=openSendSmsWindow('短信发送','"+contextPath+"/sms/sendSmsDetail?param="+$.toJSON(param)+"')>"+row.custDetail+"</a>";
					}
				}]],
		onLoadSuccess : function() {
			$('#smsAuditTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

/**
 * 查询
 */
function smsQuery(){
	var smsType = $("#smsAudit_smsType").combobox("getValue");
	var queryParam = $("#smsAudit_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	smsAuditTable.datagrid('options').url = "sms/getSmsAuditInfo";
	smsAuditTable.datagrid('load',{param:queryParam});
}

/**
 * 清空短信提取条件
 */
function clearSmsAuditCondition(){
	$("#smsAudit_queryConditionForm").form("clear");
}


/**
 * 短信发送详细窗口
 */
function openSendSmsWindow(title, href){
	$('<div id="sendSmsWindow"/>').dialog({
		href : href,
		//type : "post",
		modal : true,
		title : title,
		//fit : true, 
		width:1200,
		height:600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}	
/**
 * 删除
 */
function smsDelete(){
	var rows =  $("#smsAuditTable").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择一个信息进行删除","info");
		return;
	}
	/*if (rows.length > 1) {
		$.messager.alert("提示","只能选择一个信息进行删除","info");
		return;
	}*/
	$.messager.confirm('提示信息', '您确定要删除此信息吗？', function(r){
		if (r){
//			var sysSmsBatchId = $.toJSON(rows[0].sysSmsBatchId);
//			var param = {};
//			param.sysSmsBatchId = sysSmsBatchId;
			$.ajax({
				type:'post',
				url:contextPath+"/sms/deleteSms",
				data:'param='+$.toJSON(rows),
				cache:false,
				success:function(reData){
					try {
						if(reData.success){
							$.messager.alert('提示', "成功删除信息", 'info');
							smsAuditTable.datagrid('reload');
						}else{
							$.messager.alert('提示', reData.msg);
						}
					} catch (e) {
						$.messager.alert('提示', e);
					}
				}
			});
		}
	});
}
//手动生成收益分配短信
function smsIncomeDis(){
	$.ajax({
		type:'post',
		url:contextPath+"/incomeDis/incomeDisSmsBatch",
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', "短信生成完成！");
				}else{
					$.messager.alert('提示', reData.msg);
				}
			}catch (e) {
				$.messager.alert('提示', e);
			}
			 }
	});
}
