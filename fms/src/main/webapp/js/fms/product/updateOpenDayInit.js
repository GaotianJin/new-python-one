var modifywealthOpenDateId=null;
var modifyAngecyName=null;
var modifyProductName=null;

$(function(){
	$("#ThirdInvestStartDate").datetimebox({required:true});
	$("#ThirdInvestEndDate").datetimebox({required:true});
	modifywealthOpenDateId = $("#modifywealthOpenDateId").val();
	modifyAngecyName= $("#modifyAngecyName").val();
	modifyProductName= $("#modifyProductName").val();
	getUpdateInitValue();
	//金额输入框提示金额大写
	$("#updfinancingScale").bind('input propertychange', function(e){  
	    //将本次修改的值更新到rowData的相应列数据中  
	    //rowData[workRateEditor.field] = $(this).val();
			 var tipsContent = numToUpper($(this).val()*10000);
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
function getUpdateInitValue(){
	$.ajax({
		type : 'post',
		url : contextPath+"/product/updateOpenDayInitUrl",
		data:{param:$('#modifywealthOpenDateId').val()},
		cache : false,
		success : function(resultInfo) {
			setInputValueById("updateOpenDayDiv",resultInfo);
	        $("#ThirdagencyComId").val(modifyAngecyName);
	        $("#ThirdproductId").val(resultInfo.productId);
	        $("#ThirdproductName").val(modifyProductName);
		}
	});
}



function updateOpenDayInfo(){
	if(!$("#openDayInfoForm").form("validate")){
		$.messager.alert('提示', "请填写必录项！");
		return;
	}
	var param = {};
	var openDayInfoJson = formDataToJsonStr($("#openDayInfoForm").serialize());
	param.openDayBaseInfo = openDayInfoJson;
    param.modifywealthOpenDateId=modifywealthOpenDateId;
	$.ajax({
		type : 'post',
		url : contextPath + "/product/saveUpdateOpenDayUrl",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
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


function backLisOpenDayPage(){
	$('#addWindow').window('destroy');
	parent.clearForm();
}








