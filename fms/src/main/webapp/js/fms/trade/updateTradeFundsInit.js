var tradeType = null;
var tradeInfoId = null;
var productSubType = null;
jQuery(function($) {
	tradeType = $("#tradeType").val();
	tradeInfoId = $("#tradeInfoId").val();
	productSubType = $("#productSubType").val();
	getTradeDetailInfo();
	initAllCombobox();
	/*if(tradeType=="1"){
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
	}
	
	if(operate=="Modify"&&tradeType!="2"){
		getLastTradeStatusInfo();
	}*/
	
	$("#fundsShare_transferAsset").bind('input propertychange', function(e){  
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


function initAllCombobox(){
	$("#fundsShare_transferAll").combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=changeType",
		required : true,
		valueField : 'code',
		textField : 'codeName',
		onSelect:function(record){
		    	var changeType = record.code;
		    	if(changeType == '02'){
		    		$.ajax({
		    			type:'post',
		    			url:contextPath+"/funds/getTradeTotalAssets",
		    			data:'tradeInfoId='+tradeInfoId,
		    			cache:false,
		    			success:function(reData){
		    				try {
		    					if(reData.success){
		    						var reDataObj = reData.obj;
		    						setInputValueById("fundsShareChangeDiv",reDataObj);
		    					}else{
		    						$.messager.alert('提示', reData.msg);
		    					}
		    				} catch (e) {
		    					$.messager.alert('提示', e);
		    				}
		    			}
		    		});
		    		
		    	}
		    }
	});
	$("#fundsShare_isOrder").combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=agentIsOrder",
		required : true,
		valueField : 'code',
		textField : 'codeName'
	});
	
	$("#fundsShare_agentId").combobox({
		url : contextPath+"/codeQuery/agentAllQuery",
		//required : true,
		valueField : 'id',
		textField : 'name'
	});
};
function submitTradeFunds(){
	
	if(!$("#FundsShareChangeForm").form("validate")){
		$.messager.alert('提示', "请检查页面必录项是否全部录入正确");
		return;
	}
	
	var formData = formDataToJsonStr($("#FundsShareChangeForm").serialize());
	var param = {};
	param.tradeType = tradeType;
	param.tradeInfoId = tradeInfoId;
	param.productSubType = productSubType;
	param.fundsShareChangeInfo = formData;
	$.ajax({
		type:'post',
		url:contextPath+"/funds/saveFundsShareChangeInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', reData.msg);
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
		url:contextPath+"/funds/getTradeDetailInfo",
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
	$('#updTradeFundsWindow').window('destroy');
	initTradeStrus();
}
