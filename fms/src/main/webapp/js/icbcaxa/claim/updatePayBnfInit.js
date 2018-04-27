jQuery(function($) {
		$('#bnfAccountBankId').combobox({
			  url:'../common/queryBankList',
			  valueField:'id',
			  textField:'name',
			  onLoadSuccess:function(){
			     $('#bnfAccountBankId').combobox('select',$('#bnfAccountBankIDName').val());
			  }
		});
		
		//省
		$('#payBankProvinceId').combobox({
			url:'../region/queryAllBankPro',
			valueField:'code', 
			textField:'name',
			onLoadSuccess:function(){
				if($('#payBankProvinceIdName').val()!=null && $('#payBankProvinceIdName').val()!=""){
			      $('#payBankProvinceId').combobox('select',$('#payBankProvinceIdName').val());
			      $('#payBankProvinceIdName').val("");
				 }
			 },
			onSelect: function(rec){ 
				var proID = $('#payBankProvinceId').combobox('getValue');
				var url = '../region/queryAllBankCity?proID='+proID;
				 $('#payBankCityId').combobox('clear');
				$('#payBankCityId').combobox('reload', url); 
				

		}
		});
		
		//市
		$('#payBankCityId').combobox({
			url : '../region/queryAllBankCity?proID='
				+ $('#payBankProvinceId').combobox('getValue'),
			valueField:'code', 
			textField:'name',
			onLoadSuccess:function(){
				 if($('#payBankCityIdName').val()!=null && $('#payBankCityIdName').val()!=""){
					 $('#payBankCityId').combobox('select',$('#payBankCityIdName').val()); 
					 $('#payBankCityIdName').val("");
				 }
			      
			 }
		});
		
		
		
		
		
		
	});


//更新受益人
function updatePayBnf() {
/*	if(!$('#updatePayBnfForm').form('validate')){
		return;
	}*/
	var bnfName=$('#bnfName').val();
	if(bnfName==""||bnfName==null){
		$.messager.alert('提示信息','请输入受益人姓名','info');
		return;
	}
	
	var distributeProportion=$('#distributeProportion').val();
	if(distributeProportion==""||distributeProportion==null){
		$.messager.alert('提示信息','请输入受益比例','info');
		return;
	}
	
	var bnfAccountName=$('#bnfAccountName').val();
	if(bnfAccountName==""||bnfAccountName==null){
		$.messager.alert('提示信息','请输入受益人账户名称','info');
		return;
	}
	
	var bnfAccountBankId=$('#bnfAccountBankId').combobox('getValue');
	if(bnfAccountBankId==""||bnfAccountBankId==null){
		$.messager.alert('提示信息','请选择受益人账户银行','info');
		return;
	}
	var payBankProvinceId=$('#payBankProvinceId').combobox('getValue');
	if(payBankProvinceId==""||payBankProvinceId==null){
		$.messager.alert('提示信息','请选择受益人开户行所在省','info');
		return;
	}
	var payBankCityId=$('#payBankCityId').combobox('getValue');
	if(payBankCityId==""||payBankCityId==null){
		$.messager.alert('提示信息','请选择受益人开户行所在市','info');
		return;
	}
	
	var bnfAccountNo=$('#bnfAccountNo').val();
	if(bnfAccountNo==""||bnfAccountNo==null){
		$.messager.alert('提示信息','请输入受益人账号','info');
		return;
	}
	
	var bnfCardType=$('#bnfCardType').val();
	if(bnfCardType==""||bnfCardType==null){
		$.messager.alert('提示信息','请选择证件类型','info');
		return;
	}
	var bnfCardNo = $('#bnfCardNo').val();
	if(bnfCardNo==""||bnfCardNo==null){
		$.messager.alert('提示信息','请输入证件号码','info');
		return;
	}
	
	
	if (bnfCardType == "00" || bnfCardType == "03") {
		if (!idCard(bnfCardNo)) {
			$.messager.alert('提示', '身份证号码不合法', 'info');
			return;
		}
	}
	
	var mobile = $('#mobile').val();
	if(mobile !="" && mobile != null){
		
		 var reg = new RegExp("^[0-9]*$");
		 if(!reg.test(mobile)){
			 alert("手机号码请输入数字！");
			 return;
		 }
		 if(mobile.length > 20){
			 alert("手机号码长度不能超过20！");
			 return;
		 }
	}
	
	$.post("updatePayBnf", $("#updatePayBnfForm").serializeArray(), function(data) {
		$.messager.alert('更新成功', data.mes, 'info',function(){
			parent.searchPayBnfInfo();
			parent.deletePayBnfTab('更新受益人');
		});
	});
}
	
	
