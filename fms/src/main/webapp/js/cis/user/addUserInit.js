
jQuery(function($) {

	//初始化机构
	$("#adduser_comId").combobox({
		url:contextPath+"/codeQuery/comQuery",
		valueField:'comId',
		textField:'comName',
		onSelect:function(rec){
			var adduser_comId = $("#adduser_comId").combobox('getValue');
			/*var url = contextPath+'/codeQuery/storeQuery?comId='+adduser_comId;
			$("#adduser_storeId").combobox('reload',url);*/
			var url = contextPath+'/codeQuery/departmentQuery?comId='+adduser_comId
			$("#adduser_departmentId").combobox('reload',url);
			
			$.post(contextPath+'/branch/updateComInitUrl?comId=' + rec.comId, function(data) {
				  if(data.defCom.grade=='01')
				  {
					  $('#adduser_departmentId').combobox("disable");
					/*  $('#adduser_storeId').combobox("disable");*/
				  }
				  else
				  {
					  $('#adduser_departmentId').combobox("enable");
					 /* $('#adduser_storeId').combobox("enable");*/
				  }
			 })
		}
	});
		
	//初始化业务部
	$("#adduser_departmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#adduser_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});
	//初始化门店
/*	$("#adduser_storeId").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#adduser_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
		//初始化用户状态
		$("#adduser_state").combobox({
			url:contextPath+'/codeQuery/tdCodeQuery?codeType=userState',
			valueField:'code',
			textField:'codeName',
			value:'01'
		});
		
		
	}
);
// 增加用户
	function addUser() {
		
			if(!$('#addUserForm').form('validate'))
			{
				return;
			}
			if($("#adduser_password").val() != $("#adduser_password1").val())
			{
				$.messager.alert('提示', '两次密码输入不一致，请修改', 'info');
				return;
			}
			var validStartDate = $("#adduser_validstartDate").datebox("getValue");
			var validEndDate = $("#adduser_validendDate").datebox("getValue");
			
			if((validStartDate==null || validStartDate=="" || validStartDate==undefined) && 
					validEndDate!=null && validEndDate!="" && validEndDate!=undefined)
			{
				$.messager.alert('提示', "有效开始日期为空时，有效结束日期必须为空", 'info');
				  return false;
			}
			if(validStartDate!=null && validStartDate!="" && validStartDate!=undefined &&
					validEndDate!=null && validEndDate!="" && validEndDate!=undefined)
			{
				var d1 = new Date(validStartDate.replace(/\-/g, "\/")); 
				var d2 = new Date(validEndDate.replace(/\-/g, "\/")); 
		
				if(d1 >d2)
				{
				  $.messager.alert('提示', "有效结束开始时间不能大于结束时间！", 'info');
				  return false;
				}
			}						
			var param = {};
			var ComBaisicInfoFormDataJson = formDataToJsonStr($("#addUserForm").serialize());
			param.userInfo = ComBaisicInfoFormDataJson;
			$.post("user/saveAdd", 'param='+$.toJSON(param), function(data) {
				$.messager.alert('提示', data.msg, 'info');
				if("true"==data.succ)
				{
					$('#addUserForm').form('clear');
					parent.clearUserForm();
				}
			});	
			
		}
		
	function backListUserPage(){
		$('#userWindow').window('destroy');
		parent.clearUserForm();
	}
	
	