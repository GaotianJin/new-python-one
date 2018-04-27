var jsonData = [{"tradeNo":"20150150600009","custName":"张三","productName":'潍坊三河特定多个客户专项资产管理计划(第一期)',"expectOpenDay":"2015-03-01",
  "subscriptionMoney":"3000000.00",
	"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
	"distributionInterestMoney":"300000.00","distributionTotalMoney":"5000000.00","distributionPrincipalMoney":"5000.90","principalRate":"1.089"},
	{"tradeNo":"20150150600010","custName":"李四","productName":'潍坊三河特定多个客户专项资产管理计划(第一期)',"expectOpenDay":"2015-05-01",
		"subscriptionMoney":"5000000.00",
		"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
		"distributionInterestMoney":"300000.00","distributionPrincipalMoney":"6009.70","distributionTotalMoney":"5000000.00","principalRate":"1.089"},
	{"tradeNo":"20150150600011","custName":"王五","productName":'潍坊三河特定多个客户专项资产管理计划(第一期)',"expectOpenDay":"2015-04-01",
		"subscriptionMoney":"8000000.00",
	"distributionApplyDate":"2015-05-04","distributionStartDate":"2015-03-01","distributionEndDate":"2015-05-31",
		"distributionInterestMoney":"300000.00","distributionTotalMoney":"5000000.00","distributionPrincipalMoney":"8000.98","principalRate":"1.089"}];



jQuery(function($) {
	
});


function importIncomeDisFile(){
	var incomeFileName = $("#incomeFileName").val();
	if(incomeFileName==null||incomeFileName==""||incomeFileName==undefined){
		$.messager.alert('提示', "请选择需要导入的文件", 'info');
		return;
	}
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/incomeDis/importIncomeDisFile",
		fileElementId:'incomeFileName', 
		dataType:'json',
		success:function(reData){
			reData = $.parseJSON(reData)
			try {
				if(reData.success){
					$.messager.alert('提示', "收益分配文件导入成功！");
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}
