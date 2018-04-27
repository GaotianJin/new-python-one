var pdwealthOpenDayId=null;

$(function() {
    initAllCombobox();
});

/**
 * 批量维护开放日
 */
function addOpenDaysInfo() {
	if (!$("#addOpenDaysInfoForm").form("validate")) {
		$.messager.alert('提示', "请输入必录信息！");
		return false;
	}
	var param = {};
	var netopenInfoJson = formDataToJsonStr($("#addOpenDaysInfoForm").serialize());
	param.netOpenBaseInfo = netopenInfoJson;
	param.pdwealthOpenDayId=pdwealthOpenDayId;
	param.isOrder = $("#isOrder").combobox("getValue");
	param.openDayRules = $("#openDayRules").combobox("getValue");
	// 发送请求进行开放日新增
	$.ajax({
		type : 'post',
		url : contextPath + "/product/addOpenDaysInfo",
		data:'param='+$.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					pdwealthOpenDayId=resultInfo.obj;
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});

}


/**
 * 初始化下拉框信息方法
 * 
 */
function initAllCombobox() {
	$('#openDaySecOpenDate').attr("disabled","true"); 
	$('#openDayRules').attr("disabled","true"); 
	var productIdComobox;
	// 产品初始化 显示为：产品代码-产品名称
	productIdComobox = $("#openDaySecProductId1").combobox({
		valueField : 'code',
		textField : 'codeName'
	});

	// 合作机构
	var agencyComIdCombobox;
	agencyComIdCombobox = $("#openDaySecAgentComId1").combobox(
			{
				url : contextPath + '/codeQuery/agencyQuery',
				valueField : 'code',
				textField : 'codeName',
				onSelect : function() {
					var codeType = agencyComIdCombobox.combobox("getValue");
					var url = contextPath + '/codeQuery/productwealthQuery?codeType='+ codeType;
					$("#openDaySecProductId1").combobox("clear");
					productIdComobox.combobox("reload", url);
				}

			});
	// 开放日规则
	var openDayRules;
	openDayRules = $("#openDayRules").combobox(
			{
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=openDaysRules',
				valueField : 'code',
				textField : 'codeName',
			});
	// 是否指定日期
	var isOrder;
	isOrder = $("#isOrder").combobox(
			{
				url : contextPath + '/codeQuery/tdCodeQuery?codeType=openDaysIsOrder',
				valueField : 'code',
				textField : 'codeName',
				onSelect: function(record){
			    	if(record.code=="1"){
			    		$('#openDaySecOpenDate').removeAttr("disabled");
			    		$('#openDayRules').combobox("disable");
						}
			    	if(record.code=="2"){
						$('#openDayRules').combobox("enable");
						$('#openDaySecOpenDate').attr("disabled","true"); 
			    	}
				}
			});
	//维护止期默认为年末最后一天
	var day = new Date();
	var Year = 0; 
	Year= day.getFullYear();
	var date = Year+"-"+12+"-"+31+" "+17+":"+0+":"+0;
	$('#addOpenDays_defendEndDate').datetimebox({
	    value: date,
	    required: true,
	    showSeconds: true
	});
}

/**
 * 返回
 */
function backLisOpenDayPage(){
	$('#addOpenDays').window('destroy');
	parent.clearForm();
}
