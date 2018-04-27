jQuery(function($) {
	
	//机构
	$("#updateuser_comId").combobox({
		url:contextPath+"/codeQuery/comQuery",
		valueField:'comId',
		textField:'comName',
		onSelect:function(rec){
			var adduser_comId = $("#updateuser_comId").combobox('getValue');
			/*var url = contextPath+'/codeQuery/storeQuery?comId='+adduser_comId;
			$("#updateuser_storeId").combobox('reload',url);*/
			var url = contextPath+'/codeQuery/departmentQuery?comId='+adduser_comId
			$("#updateuser_departmentId").combobox('reload',url);
			$("#updateuser_departmentId").combobox('reset');
			/*$("#updateuser_storeId").combobox('reset');*/
			
			$.post(contextPath+'/branch/updateComInitUrl?comId=' + rec.comId, function(data) {
				  if(data.defCom.grade=='01')
				  {
					  $('#updateuser_departmentId').combobox("disable");
					/*  $('#updateuser_storeId').combobox("disable");*/
				  }
				  else
				  {
					  $('#updateuser_departmentId').combobox("enable");
					  /*$('#updateuser_storeId').combobox("enable");*/
				  }
			 })
			
		}
	});
	//初始化业务部
	$("#updateuser_departmentId").combobox({
		url:contextPath + '/codeQuery/departmentQuery',
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#updateuser_comId").combobox("getValue");
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
	/*$("#updateuser_storeId").combobox({
		url:contextPath + '/codeQuery/storeQuery',
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#updateuser_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
	//用户状态
	$('#updateuser_state').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=userState',
		valueField:'code',
		textField:'codeName',
		value:'01'
	});
	
	var rows = $('#userTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要更新的用户");
		return;
	} 
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			if (i == 0)
				ps += "?userId=" + n.userId;
			else
				ps += "&userId=" + n.userId;
			$("#updateuser_userId").val(n.userId);
		});
		$.post(contextPath+'/user/updateUserInitUrl' + ps, function(data) {
			 
			$.post(contextPath+'/branch/updateComInitUrl?comId=' + data.defUser.comId, function(data) {
				  if(data.defCom.grade=='01')
				  {
					  $('#updateuser_departmentId').combobox("disable");
					 /* $('#updateuser_storeId').combobox("disable");*/
				  }
				  else
				  {
					  $('#updateuser_departmentId').combobox("enable");
					/*  $('#updateuser_storeId').combobox("enable");*/
					/*  var url = contextPath+'/codeQuery/storeQuery?comId='+data.defUser.comId;
					  $("#updateuser_storeId").combobox('reload',url);*/
						var url = contextPath+'/codeQuery/departmentQuery?comId='+data.defUser.comId;
					  $("#updateuser_departmentId").combobox('reload',url);
				  }
			 })
			
			 setInputValueById("updateUserInfoDiv",data.defUser);
			 
		});
	};	
	
/*	$('#companyId').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  required:'true', 
		  onLoadSuccess:function(){
			  var data = $('#companyId').combobox('getData');
			  if(data.length>0){
				  $('#companyId').combobox('select',data[0].id);
			  }
		  }
	});
	
	*/

});

//更新用户
function updateUser(){
	
		if(!$('#updateUserForm').form('validate'))
		{
			return;
		}
		
		var validStartDate = $("#updateuser_validstartDate").datebox("getValue");
		var validEndDate = $("#updateuser_validendDate").datebox("getValue");
		
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
		var UserBaisicInfoFormDataJson = formDataToJsonStr($("#updateUserForm").serialize());
		param.userInfo = UserBaisicInfoFormDataJson;
		
		$.messager.confirm('提示', '确定要修改吗?', function(result) {
			if (result) {
				$.post("user/saveUpdate", 'param='+$.toJSON(param), function(data) {
					$.messager.alert('提示', data.msg, 'info');
					if("true"==data.succ)
					{
						parent.clearUserForm();
					}
				})
			}
		})
	}

function backListUserPage(){
	$('#userWindow').window('destroy');
	parent.clearUserForm();
}