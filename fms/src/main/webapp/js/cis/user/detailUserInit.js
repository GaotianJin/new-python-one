jQuery(function($) {
	
	/*$('#detailuser_storeId').combobox({
		  url:contextPath+"/codeQuery/storeQuery",
		  valueField:'storeId',
		  textField:'storeName'
	});*/
	
	$('#detailuser_state').combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=userState',
		valueField:'code',
		textField:'codeName'
	});
	
	//机构
	$("#detailuser_comId").combobox({
		url:contextPath+"/codeQuery/comQuery",
		valueField:'comId',
		textField:'comName'
	});
	
	//业务部
    $('#detailuser_departmentId').combobox({
          url:contextPath+'/codeQuery/departmentQuery',
          valueField:'departmentId',
          textField:'departmentName'
      });
	
	var rows = $('#userTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要查看的用户");
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
		});
		$.post(contextPath+'/user/updateUserInitUrl' + ps, function(data) {
			 
			/* var url = contextPath+'/codeQuery/storeQuery?comId='+data.defUser.comId;
			  $("#detailuser_storeId").combobox('reload',url);*/
				var url = contextPath+'/codeQuery/departmentQuery?comId='+data.defUser.comId;
			  $("#detailuser_departmentId").combobox('reload',url);
			 setInputValueById("detailUserInfoDiv",data.defUser);
			 
		});
	};	
	$('#companyId').combobox({
		  url:'../company/queryCompanyList',
		  valueField:'id',
		  textField:'name',
		  required:'true'
	});
	
	

});
function backListUserPage(){
	$('#userWindow').window('destroy');
	parent.clearUserForm();
}