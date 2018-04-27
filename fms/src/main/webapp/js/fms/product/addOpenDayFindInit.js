var pdwealthOpenDayId=null;

$(function() {
	initAllCombobox();
	
	//金额输入框提示金额大写
	$("#financingScale").bind('input propertychange', function(e){  
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
//新增开放日
function addOpenDayInfo() {
	if (!$("#addOpenDayInfoForm").form("validate")) {
		$.messager.alert('提示', "请输入必录信息！");
		return false;
	}
	var param = {};
	var netopenInfoJson = formDataToJsonStr($("#addOpenDayInfoForm").serialize());
	param.netOpenBaseInfo = netopenInfoJson;
	param.pdwealthOpenDayId=pdwealthOpenDayId;
	// 发送请求进行开放日新增
	$.ajax({
		type : 'post',
		url : contextPath + "/product/addOpenDayInfo",
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

}


function backLisOpenDayPage(){
	$('#addOpenDay').window('destroy');
	parent.clearForm();
}
