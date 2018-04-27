var operate = null;
jQuery(function($) {
	operate = $("#addManager_operate").val();
	managerId = $("#addManager_managerId").val();
	if(operate=="updateManager"){
		queryManagerInfo();
	}

});	

function setIdNoAndSexDesabled(){
	var idType = $('#addAgent_idType').combobox("getValue");
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		$('#addAgent_birthday').datebox("disable");
		$('#addAgent_sex').combobox("disable");
	}else{
		$('#addAgent_birthday').datebox("enable");
		$('#addAgent_sex').combobox("enable");
	}
}

//查询内勤人员信息 并赋值
function queryManagerInfo(){
	var param = {};
	param.managerId = managerId;
	$.ajax({
		type:'post',
		url:contextPath+"/manager/queryManagerInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var reDataObj = reData.obj;
					//setInputValueById("addAgent_agentBaseInfo",reDataObj.agentBaseInfo);
					$("#addManager_chnName").val(reDataObj.chnName);
					$("#addManager_mobile").val(reDataObj.mobile);

				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

//提交
function submit(){
	var param = {};
	var managerInfoJsonStr = formDataToJsonStr($("#addManagerInfoForm").serialize());
	param.managerInfoJsonStr = managerInfoJsonStr;
	param.operate = operate;
	param.managerId = managerId;
	$.ajax({
		type:'post',
		url:contextPath+"/manager/submitManager",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
				if(reData.success){
					$.messager.alert('提示', "提交成功", 'info');
				}else{
					$.messager.alert('提示', reData.msg);
				}
		}
	});
}

function backListAgentPage(){
	$('#addManagerWindow').window('destroy');
	parent.queryManagerList();
}


