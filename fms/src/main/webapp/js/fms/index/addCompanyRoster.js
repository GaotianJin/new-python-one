/**
 * 初始化信息
 */
var operate = null;
$(function(){
	operate = $("#addCompanyRoster_operate").val();
	initAllCombobox();
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="addComRoster"){
		$("#addCompanyRosterInfo").form("validate");
	}else if(operate!=null&&operate!=""&&operate!=undefined&&operate=="updateComRoster"){
		getCompanyRosterInfo();
		$("#addCompanyRosterInfo").form("validate");
	}
});

/**
 * 下拉框
 */
function initAllCombobox(){
	// 分公司：代码-名称
	$("#addCompanyRoster_filiale").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	
	// 部门：代码-名称
    $("#addCompanyRoster_department").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#addCompanyRoster_filiale").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});	
    
    //岗位
    $('#addCompanyRoster_post').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=positionCode',
		valueField:'code',
		textField:'codeName'
	});
}

/**
 * 新增通讯录信息
 */
function commitCompanyRoster(){
	if(!$("#addCompanyRosterInfo").form("validate")){
		return false;
	}
	var param = {};
	param.operate = operate;
	//基本信息Form
	var basicInfoJson = formDataToJsonStr($("#addCompanyRosterInfo").serialize());
   //发送请求，后台接受数据进行处理
	$.ajax({
		type:'post',
		url:contextPath+"/index/addCompanyRoster",
		data : 'companyRosterInfoData='+basicInfoJson+'&param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(resultInfo){
			try {
				if(resultInfo.success){
					$("#addCompanyRoster_companyRosterInfoId").val(resultInfo.obj.companyRosterInfoId);
					$.messager.alert('提示', resultInfo.msg, 'info');
				}
				else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
		});
	
}

/**
 * 返回主页面
 */
function backListCompanyRoster(){
	$('#addCompanyRosterWindow').window('close');
	$('#companyRosterTable').datagrid('clear');
	initCompanyRosterTable();
}

/**
 * 获取通讯录信息
 */
function getCompanyRosterInfo(){
	var companyRosterInfoId = $("#addCompanyRoster_companyRosterInfoId").val();
	$.ajax({
		type:'post',
		url:contextPath+"/index/getCompanyRosterInfo",
		data:{param:companyRosterInfoId},
		cache:false,
		success:function(resultInfo){
			try {
				if(resultInfo.success){
					var resultObj = resultInfo.obj;
					setInputValueById("addCompanyPolicyDiv",resultObj.companyRosterInfo);
				}
				else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
		});
}
