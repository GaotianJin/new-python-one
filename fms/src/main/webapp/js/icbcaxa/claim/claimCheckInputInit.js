jQuery(function($) {
	//datagrid自定义editor继承控件
	$.extend($.fn.datagrid.defaults.editors, {
		combobox: {//为方法取名
	        init: function (container, options) {
	            var editor = $('<input />').appendTo(container);
	            options.editable = false;//设置其不能手动输入
	            editor.combobox(options);
	            return editor;
	        },
	        getValue: function (target) {//取值
	            return $(target).combobox('getValue');
	        },
	        setValue: function (target, value) {//设置值
	            $(target).combobox('select', value);
	        },
	        resize: function (target, width) {
	            $(target).combobox('resize', width);
	        },
	        destroy: function (target) {
	            $(target).combobox('destroy');//销毁生成的panel
	        }
	    }
	});
	
	//重写messager，去掉窗口的"x"按钮  add by xll 2013-12-05
	//$.extend( $.fn.window.defaults,{closable: false});
	
	$('#claimType').combobox({
		onSelect: function(rec){ 
			queryClaimMoneyInfo();
	}
	});
	
	
	
	$('#accClassCode').combobox({
		  url:'../claim/queryAccClassCodeList',
		  valueField:'id',
		  textField:'name'
	});
	
	$('#accBodyCode').combobox({
		  url:'../claim/queryAccBodyCodeList',
		  valueField:'id',
		  textField:'name'
	});
	
	$('#refAccountBankId').combobox({
		  url:'../common/queryBankList',
		  valueField:'id',
		  textField:'name'
	});
	
	//省
	$('#refBankProvinceId').combobox({
		url:'../region/queryAllBankPro',
		valueField:'code', 
		textField:'name',
		onSelect: function(rec){ 
			$('#refBankCityId').combobox('clear'); 
			var proID = $('#refBankProvinceId').combobox('getValue');
			var url = '../region/queryAllBankCity?proID='+proID;
			$('#refBankCityId').combobox('reload', url); 
	}
	});
	
	//市
	$('#refBankCityId').combobox({
		url:'../region/queryAllBankCity?proID='+$('#refBankProvinceId').combobox('getValue'),
		valueField:'code', 
		textField:'name'
	});
	
	
	
	
	//伤残等级信息列表
	$('#CrippleInfoTable').datagrid({
		title : '伤残等级信息', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 200, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryCrippleInfo", // 数据来源
		sortName : 'dbid', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'dbid', // 主键字段
		queryParams : {id:$('#id').val()}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'crippleClass',
					title : '伤残大类',
					width : 200,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleClass;
					} 
				}, {
					field : 'crippleSubClass',
					title : '伤残子类',
					width : 200,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleSubClass;
					}
				},{
					field : 'crippleDesc',
					title : '伤残描述',
					width : 200,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleDesc;
					}
				},{
					field : 'crippleGrade',
					title : '伤残等级',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleGrade;
					}
				},{
					field : 'authenticationDate',
					title : '鉴定日期',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.authenticationDate;
					}
				}]],
				toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						var  id=$('#id').val();
						var dlist = [];
						dlist.push({
									"id":id
								});
						addCrippleTab('新增伤残等级', 'addCrippleUrl?list='+ $.toJSON(dlist));
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#CrippleInfoTable').datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择一个伤残等级进行修改");
							return;
						}
						if (rows.length > 1) {
							alert("只能请选择一个伤残等级修改");
							return;
						}
						var dlist = [];
						dlist.push({
									"id" : rows[0].dbid,
									"crippleClass":rows[0].crippleClass,
									"crippleSubClass":rows[0].crippleSubClass,
									"crippleDesc":rows[0].crippleDesc,
									"crippleGrade":rows[0].crippleGrade,
									"authenticationDate":rows[0].authenticationDate
								});
						addCrippleTab('更新伤残等级', 'updateCrippleUrl?list='+ $.toJSON(dlist));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteCripple();
					}
				}],
		onLoadSuccess : function() {
			$('#CrippleInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
    //增减项等级信息
	$('#AditemInfoTable').datagrid({
		title : '增减信息', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 200, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryAditemInfo", // 数据来源
		sortName : 'dbid', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'dbid', // 主键字段
		queryParams : {id:$('#id').val()}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'aditemName',
					title : '增减项名称',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.aditemName;
					} 
				}, {
					field : 'aditemMoney',
					title : '金额（元）',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.aditemMoney;
					}
				}]],
				toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						var  id=$('#id').val();
						var dlist = [];
						dlist.push({
									"id":id
								});
						addAditemTab('新增增减项', 'addAditemUrl?list='+ $.toJSON(dlist));
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = $('#AditemInfoTable').datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择一个增减项进行修改");
							return;
						}
						if (rows.length > 1) {
							alert("只能选择一个增减项进行修改");
							return;
						}
						var dlist = [];
						dlist.push({
							        "id":rows[0].dbid,
									"aditemName" : rows[0].aditemName,
									"aditemMoney" : rows[0].aditemMoney
								});
						addAditemTab('更新增减项', 'updateAditemUrl?list='+ $.toJSON(dlist));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteAditem();
					}
				}],
		onLoadSuccess : function() {
			$('#AditemInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	//受益人信息列表
	$('#PayBnfInfoTable').datagrid({
		title : '受益人信息', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 200, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryPayBnfInfo", // 数据来源
		sortName : 'dbid', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'dbid', // 主键字段
		queryParams : {id:$('#id').val()}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'bnfName',
					title : '受益人姓名',
					width : 70,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfName;
					} 
				},	{
					field : 'distributeProportion',
					title : '受益比例',
					width : 60,
					sortable : false,
					formatter : function(value, row, index) {
						return row.distributeProportion;
					} 
				}, 	{
					field : 'payMoney',
					title : '受益金额',
					width : 70,
					sortable : false,
					formatter : function(value, row, index) {
						return row.payMoney;
					} 
				}, {
					field : 'bnfAccountBankIdName',
					title : '受益人账户银行',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfAccountBankIdName;
					} 
				}, {
					field : 'payBankProvinceIdName',
					title : '受益人开户行所在省',
					width : 120,
					sortable : false,
					formatter : function(value, row, index) {
						return row.payBankProvinceIdName;
					} 
				},  {
					field : 'payBankCityIdName',
					title : '受益人开户行所在市',
					width : 120,
					sortable : false,
					formatter : function(value, row, index) {
						return row.payBankCityIdName;
					} 
				}, {
					field : 'bnfAccountName',
					title : '受益人账户名称',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfAccountName;
					} 
				}, {
					field : 'bnfAccountNo',
					title : '受益人账号',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfAccountNo;
					} 
				}, {
					field : 'bnfCardTypeName',
					title : '受益人证件类型',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfCardTypeName;
					} 
				},  {
					field : 'bnfCardNo',
					title : '受益人证件号',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfCardNo;
					}
				},	{
					field : 'mobile',
					title : '手机号码',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						return row.mobile;
					}
				}]],
				toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						var payMoney=$('#payMoney').val();
						if(payMoney==""||payMoney==null){
							alert("请先计算赔付金额");
							return false;
						}
						var  id=$('#id').val();
						var dlist = [];
						dlist.push({
							        "id":id,
									"payMoney":payMoney
								});
						addPayBnfTab('新增受益人', 'addPayBnfUrl?list='+ $.toJSON(dlist));
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var payMoney=$('#payMoney').val();
						if(payMoney==""||payMoney==null){
							alert("请先计算赔付金额");
							return false;
						}
						var rows = $('#PayBnfInfoTable').datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择一个受益人进行修改");
							return;
						}
						if (rows.length > 1) {
							alert("只能选择一个受益人进行修改");
							return;
						}

						var dlist = [];
						dlist.push({
									"id" : rows[0].dbid,
									"bnfName":rows[0].bnfName,
									"distributeProportion":rows[0].distributeProportion,
									"payMoney":rows[0].payMoney,
									"bnfAccountBankID":rows[0].bnfAccountBankId,
									"payBankProvinceID":rows[0].payBankProvinceId,
									"payBankCityID":rows[0].payBankCityId,
									"bnfAccountName":rows[0].bnfAccountName,
									"bnfAccountNo":rows[0].bnfAccountNo,
									"bnfCardType":rows[0].bnfCardType,
									"bnfCardNo":rows[0].bnfCardNo,
									"payMoney":payMoney,
									"bnfAccountBankIdName":rows[0].bnfAccountBankIdName,
									"payBankProvinceIdName":rows[0].payBankProvinceIdName,
									"payBankCityIdName":rows[0].payBankCitydName,
									"mobile":rows[0].mobile
								});
						addPayBnfTab('更新受益人', 'updatePayBnfUrl?list='+ $.toJSON(dlist));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deletePayBnf();
					}
				}],
		onLoadSuccess : function() {
			$('#PayBnfInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	//隐藏退费信息页
	$('#tabdiv3').hide();
	
	//初始化页面信息显示
	var mes=$('#mes').val();
	if(mes!=""&&mes!=null){
		$.messager.alert('提示信息',mes);
	}
	
	//根据理赔类型,显示理赔金额信息
	var claimType=$('#claimType').combobox('getValue');
	if(claimType!=""&&claimType!=null){
		queryClaimMoneyInfo();
	}
	
	//根据初审结论或退费金额显示退费tab页
	var refMoney=$('#refMoney').val();
	if(refMoney!=""&&refMoney!=null){
		$('#tabdiv3').show();
		$('#addRefundmentBtn').attr('disabled','true');
	}
	var claimResult=$('#claimResult').val();
	if(claimResult=="05"){
		$('#tabdiv3').show();
		$('#addRefundmentBtn').attr('disabled','true');
	}
	
	//初始化隐藏tab页
	$('#aditemTab').hide();
	$('#crippleTab').hide();
	$('#payBnfTab').hide();

	
	var flag = $('#flag').val();
	if(flag == "noOper"){
		$('#buttonDiv').css('display', 'none');
		$('#claim_main_div').css('top','10px');
	}
	
});


//伤残等级tap页展示
function addCrippleTab(title, href) {
	$('#crippleTab').show();
	if (href){  
        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
	}else {  
        var content = '未实现';  
    }  
	if($('#crippleTab').tabs('exists', title)) {
		$('#crippleTab').tabs('select', title);
        var tab = $('#crippleTab').tabs('getSelected'); 
		$('#crippleTab').tabs('update', {
			tab: tab,
			options: {
				title: title,
	            content:content, 
				closable : true
			}
		});
	}else {   
		$('#crippleTab').tabs('add', {
			title : title,
            content:content, 
			closable : true
		});
	}
} 

function deleteCrippleTab(title)
{
	$('#crippleTab').hide();
	if($('#crippleTab').tabs('exists', title))
	{
		$('#crippleTab').tabs('select', title)
		var tab = $('#crippleTab').tabs('getSelected'); 
		$('#crippleTab').tabs('close', title);
	}
}

//根据初审结论显示退费信息录入页面
function onSelectChange(){
	var claimResult=$('#claimResult').val();
	if(claimResult=="05"){
		$('#tabdiv3').show();
	}else{
		$('#tabdiv3').hide();
	}
}

//增减项tap页展示
function addAditemTab(title, href) {
	$('#aditemTab').show();
	if (href){  
        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
	}else {  
        var content = '未实现';  
    }  
	if($('#aditemTab').tabs('exists', title)) {
		$('#aditemTab').tabs('select', title);
        var tab = $('#aditemTab').tabs('getSelected'); 
		$('#aditemTab').tabs('update', {
			tab: tab,
			options: {
				title: title,
	            content:content, 
				closable : true
			}
		});
	}else {   
		$('#aditemTab').tabs('add', {
			title : title,
            content:content, 
			closable : true
		});
	}
} 
function deleteAditemTab(title)
{
	$('#aditemTab').hide();
	if($('#aditemTab').tabs('exists', title))
	{
		$('#aditemTab').tabs('select', title)
		var tab = $('#aditemTab').tabs('getSelected'); 
		$('#aditemTab').tabs('close', title);
	}
}


//受益人tap页展示
function addPayBnfTab(title, href) {
	$('#payBnfTab').show();
	if (href){  
        var content = "<iframe scrolling='auto' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";  
	}else {  
        var content = '未实现';  
    }  
	if($('#payBnfTab').tabs('exists', title)) {
		$('#payBnfTab').tabs('select', title);
        var tab = $('#payBnfTab').tabs('getSelected'); 
		$('#payBnfTab').tabs('update', {
			tab: tab,
			options: {
				title: title,
	            content:content, 
				closable : true
			}
		});
	}else {   
		$('#payBnfTab').tabs('add', {
			title : title,
            content:content, 
			closable : true
		});
	}
} 
function deletePayBnfTab(title)
{
	$('#payBnfTab').hide();
	if($('#payBnfTab').tabs('exists', title))
	{
		$('#payBnfTab').tabs('select', title)
		var tab = $('#payBnfTab').tabs('getSelected'); 
		$('#payBnfTab').tabs('close', title);
	}
}




//删除伤残等级
function deleteCripple() {
	var rows = $('#CrippleInfoTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择要删除的伤残等级");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?id=" + n.dbid;
					else
						ps += "&id=" + n.dbid;
				});
				$.post('deleteCripple' + ps, function(data) {
					$.messager.alert('提示', data.mes, 'info',function(){
						searchCrippleInfo();
					});
				});
			}
		});
	}
}

//删除增减项
function deleteAditem() {
	var rows = $('#AditemInfoTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择要删除的增减项");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?id=" + n.dbid;
					else
						ps += "&id=" + n.dbid;
				});
				$.post('deleteAditem' + ps, function(data) {
					$.messager.alert('提示', data.mes, 'info',function(){
						searchAditemInfo();	
					});
				});
			}
		});
	}
}

//删除受益人
function deletePayBnf() {
	var rows = $('#PayBnfInfoTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择要删除的受益人");
		return;
	} else {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += "?id=" + n.dbid;
					else
						ps += "&id=" + n.dbid;
				});
				$.post('deletePayBnf' + ps, function(data) {
					$.messager.alert('提示', data.mes, 'info',function(){
						searchPayBnfInfo();	
					});
				});
			}
		});
	}
}


//查询伤残等级信息
function searchCrippleInfo() {
	var params = $('#CrippleInfoTable').datagrid('options').queryParams; // 先取得
	var fields = $('#claimCheckInputForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
				params[field.name] = (field.value); // 设置查询参数
			});
	$('#CrippleInfoTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}
//查询增减项信息
function searchAditemInfo() {
	var params = $('#AditemInfoTable').datagrid('options').queryParams; // 先取得
	var fields = $('#claimCheckInputForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
				params[field.name] = (field.value); // 设置查询参数
			});
	$('#AditemInfoTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}
//查询受益人信息
function searchPayBnfInfo() {
	var params = $('#PayBnfInfoTable').datagrid('options').queryParams; // 先取得
	var fields = $('#claimCheckInputForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
				params[field.name] = (field.value); // 设置查询参数
			});
	$('#PayBnfInfoTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}
//增加退费信息
function addRefundment(){
	var refAccountName = $('#refAccountName').val();
	if(refAccountName==""||refAccountName==null){
		$.messager.alert('提示信息','请输入退费账户姓名');
		return false;
	}
	var refAccountNo = $('#refAccountNo').val();
	if(refAccountNo==""||refAccountNo==null){
		$.messager.alert('提示信息','请输入退费账户');
		return false;
	}
	var refMoney = $('#refMoney').val();
	if(refMoney==""||refMoney==null){
		$.messager.alert('提示信息','请输入退费金额');
		return false;
	}
	
	var refAccountBankId = $('#refAccountBankId').combobox('getValue');
	if(refAccountBankId==""||refAccountBankId==null){
		$.messager.alert('提示信息','请输入退费账户银行');
		return false;
	}
	
	var refBankProvinceId = $('#refBankProvinceId').combobox('getValue');
	if(refBankProvinceId==""||refBankProvinceId==null){
		$.messager.alert('提示信息','请输入退费开户行所在省 ');
		return false;
	}
	var refBankCityId = $('#refBankCityId').combobox('getValue');
	if(refBankCityId==""||refBankCityId==null){
		$.messager.alert('提示信息','请输入退费开户行所在市 ');
		return false;
	}
	var refCardType = $('#refCardType').val();
	if(refCardType==""||refCardType==null){
		$.messager.alert('提示信息','请输入证件类型 ');
		return false;
	}
	var refCardNo = $('#refCardNo').val();
	if(refCardNo==""||refCardNo==null){
		$.messager.alert('提示信息','请输入证件号码 ');
		return false;
	}
	
	$('#refAccountName').validatebox({ required: true}); 
	$('#refAccountNo').validatebox({ required: true}); 
	$('#refMoney').validatebox({ required: true}); 
	$('#refAccountBankId').validatebox({ required: true}); 
	$('#refBankProvinceId').validatebox({ required: true}); 
	$('#refBankCityId').validatebox({ required: true}); 
	$('#refCardType').validatebox({ required: true}); 
	$('#refCardNo').validatebox({ required: true}); 
	
	var payMoney=$('#payMoney').val();
	if(parseFloat(payMoney)!=parseFloat(refMoney)){
		$.messager.alert('提示信息','退费金额与赔付金额不一致 ');
		return false;
	}
	$.post("saveRefundment", $("#claimCheckInputForm").serializeArray(), function(data) {
		$.messager.alert('提示', data.mes, 'info',function(){
			$('#addRefundmentBtn').attr('disabled','true');
		});
	});
}

function clearRefundmentInfo(){
	$('#refAccountName').val("");
	$('#refAccountNo').val("");
	$('#refMoney').val("");
	$('#refAccountBankId').combobox('setValue','');
	$('#refBankProvinceId').combobox('setValue','');
	$('#refBankCityId').combobox('setValue','');
	$('#refCardType').val("");
	$('#refCardNo').val("");
	
}

//暂存信息
function temporarySave(){
	var claimType=$('#claimType').combobox('getValue');
	if(claimType==""||claimType==null){
		$.messager.alert('提示信息', '请选择理赔类型', 'info');
		return false;
	}
	var inDangerReason=$('#inDangerReason').val();
	if(inDangerReason==""||inDangerReason==null){
		$.messager.alert('提示信息', '请输入出险原因', 'info');
		return false;
	}
	var sicknessCode=$('#sicknessCode').val();
	if(sicknessCode==""||sicknessCode==null){
		$.messager.alert('提示信息', '请输入疾病代码', 'info');
		return false;	
	}
	var accClassCode=$('#accClassCode').combobox('getValue');
	if(accClassCode==""||accClassCode==null){
		$.messager.alert('提示信息', '请输入意外类型代码', 'info');
		return false;		
	}
	var accBodyCode=$('#accBodyCode').combobox('getValue');
	if(accBodyCode==""||accBodyCode==null){
		$.messager.alert('提示信息', '请输入意外身体代码', 'info');
		return false;		
	}
	
    
	
	var dlist = [];
	var  rows=$('#ClaimMoneyInfoTable').datagrid('getRows');
	var  pdBenifitId;
	var  indemnityProportion=0;
	var  quondamMoney=0;  
	var  specialApproveType;  
	var  adjustMoney=0;  
	if(rows.length>0){
		var index=$('#ClaimMoneyInfoTable').datagrid('getRowIndex',rows[0]);
	    pdBenifitId=rows[0].benefitId;
	    indemnityProportion= $("#indemnityProportion"+index).val();
		quondamMoney=$("#quondamMoney"+index).val();
		$('#ClaimMoneyInfoTable').datagrid('endEdit',index);
		specialApproveType=rows[0].specialApproveType;  
		adjustMoney=$("#adjustMoney"+index).val(); 
	}
	

	var inDangerReason =$('#inDangerReason').val();
	//inDangerReason=decodeURIComponent(inDangerReason,true);
	//inDangerReason = encodeURI(encodeURI(inDangerReason)); 
	var fcheckOpinion =$('#fcheckOpinion').val();
	//fcheckOpinion=decodeURIComponent(fcheckOpinion,true);
	//fcheckOpinion = encodeURI(encodeURI(fcheckOpinion)); 
	
	
	dlist.push({
		"id" : $('#id').val(),
		"claimTaskNo":$('#claimTaskNo').val(),
		"policyNo":$('#policyNo').val(),
		"claimType" : $('#claimType').combobox('getValue'),
		"applyDate" : $('#applyDate').val(),
		"inDangerDate" : $('#inDangerDate').val(),
		"inDangerReason" : inDangerReason,
		"sicknessCode": $('#sicknessCode').val(),
		"accClassCode": $('#accClassCode').combobox('getValue'),
		"accBodyCode":$('#accBodyCode').combobox('getValue'),
		"claimResult":$('#claimResult').val(),
		"fcheckState":$('#fcheckState').val(),
		"policyStateResult" : $('#policyStateResult').val(),
		"fcheckOpinion": fcheckOpinion,
		"pdBenifitId":pdBenifitId,
		"indemnityProportion":escape(indemnityProportion),
		"quondamMoney":quondamMoney,
		"specialApproveType":specialApproveType,
		"adjustMoney":adjustMoney,
		"payMoney":$('#payMoney').val()
			});
	sAlert('正在提交数据，请您耐心等候...');
	
	$.ajax({
		url:'temporarySave',
		data:$.toJSON(dlist),
		type:'POST',
		contentType:'application/json;charset=utf-8', 
		success:function(data) {
			cAlert();
			$.messager.alert('提示信息', data.mes, 'info');
		}
	});
	
	
/*	$.post("temporarySave?list="+ $.toJSON(dlist),  function(data) {
		cAlert();
		$.messager.alert('提示信息', data.mes, 'info');
	});	*/

}

//根据选择的理赔类型,显示赔付金额
function queryClaimMoneyInfo(){
	
	$('#claimMoneyInfo').empty();
	$('#claimMoneyInfo').append(' <table id="ClaimMoneyInfoTable"></table>');
	
	var claimLoadFlag = 0;//加载时需要将数据库中的值赋值到datagrid中，但赋值后可编辑，编辑结束后，datagrid会自动调用formatter，如果不处理，系统会将数据库值又置回到datagrid，为避免此情况，加一个flag做控制
	var lastIndex=0;
	var wd=$('#claimCheckInputForm').width();
	var col_size=7;
	$('#ClaimMoneyInfoTable').datagrid({
		title : '赔案信息', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : false, // 多选
		height : 150, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "queryClaimMoneyInfo", // 数据来源
		idField : 'dbid', // 主键字段
		queryParams : {id:$('#id').val(),claimType:$('#claimType').combobox('getValue')}, // 查询条件
		onBeforeLoad : function() {
			$(this).datagrid('rejectChanges');
		},
		onSelect : function(rowIndex) {
			$('#ClaimMoneyInfoTable').datagrid('endEdit', rowIndex);
			$('#ClaimMoneyInfoTable').datagrid('beginEdit', rowIndex);
		},
		onUnselect : function(rowIndex) {
			$('#ClaimMoneyInfoTable').datagrid('endEdit', rowIndex);
		},
		columns : [[{
			field : 'ck',
			checkbox : true,
			width : 2
		}, // 显示复选框
		{
			field : 'productName',
			title : '产品名称',
			width : wd*(1/(col_size+1)),
			sortable : false,
			formatter : function(value, row, index) {
				return row.productName;
			} 
		}, {
			field : 'vpu',
			title : '产品保额（元）',
			width : wd*(1/(col_size+1)),
			sortable : false,
			formatter : function(value, row, index) {
				return row.vpu;
			}
		},{
			field : 'benefitName',
			title : '责任名称',
			width : wd*(1/(col_size+1)),
			sortable : false,
			formatter : function(value, row, index) {
				return row.benefitName;
			}
		},{
			field:'indemnityProportion',
			title:'赔付比例',
			align:'center',
			width:wd*(1/(col_size+1)),
      	  	formatter:function(val,row,rowIndex){
      	  	    var claimType=$('#claimType').combobox('getValue');
      	  		if(claimType=="2"){
	      	  		if(claimLoadFlag<2){
		      	  		return "<input id='indemnityProportion"+rowIndex+"' value='"+row.indemnityProportion+"' name='indemnityProportion' disabled='true' size='10' class=\"{required:true,messages:{required:' 请输入赔付比例'}}\""
		      	  		+"onChange=\"return calculateQuondamMoney('"+row.vpu+"','"+rowIndex+"')\"/>%";
	      	  		}else{
		      	  		return "<input id='indemnityProportion"+rowIndex+"' value='"+$("#indemnityProportion"+rowIndex).val()+"' name='indemnityProportion' disabled='true' size='10' class=\"{required:true,messages:{required:' 请输入赔付比例'}}\""
		      	  		+"onChange=\"return calculateQuondamMoney('"+row.vpu+"','"+rowIndex+"')\"/>%";
	      	  		}
      	  		}else{
	      	  		if(claimLoadFlag<2){
		      	  		return "<input id='indemnityProportion"+rowIndex+"' value='"+row.indemnityProportion+"' name='indemnityProportion' size='10' class=\"{required:true,messages:{required:' 请输入赔付比例'}}\""
	       			 +"onChange=\"return calculateQuondamMoney('"+row.vpu+"','"+rowIndex+"')\"/>%";
	      	  		}else{
		      	  		return "<input id='indemnityProportion"+rowIndex+"' value='"+$("#indemnityProportion"+rowIndex).val()+"' name='indemnityProportion' size='10' class=\"{required:true,messages:{required:' 请输入赔付比例'}}\""
	       			 +"onChange=\"return calculateQuondamMoney('"+row.vpu+"','"+rowIndex+"')\"/>%";
	      	  		}
      	  		}

           }

		},{
			field : 'quondamMoney',
			title : '赔付金额（元）',
			width : wd*(1/(col_size+1)),
			sortable : false,
      	  	formatter:function(val,row,rowIndex){
	      	  	if(claimLoadFlag<2){
	  	  			return "<input id='quondamMoney"+rowIndex+"' value='"+row.quondamMoney+"' name='quondamMoney' size='10' disabled='true'/>";
	  	  		}else{
	  	  			return "<input id='quondamMoney"+rowIndex+"' value='"+$("#quondamMoney"+rowIndex).val()+"' name='quondamMoney' size='10' disabled='true'/>";
	  	  		}
           }

		},{
			field : 'adjustMoney',
			title : '调整后金额（元）',
			width : wd*(1/(col_size+1)),
			sortable : false,
      	  	formatter:function(val,row,rowIndex){
	      	  	if(claimLoadFlag<2){
	  	  			return "<input id='adjustMoney"+rowIndex+"' value='"+row.adjustMoney+"' name='adjustMoney' size='10' />";
	  	  		}else{
	  	  			return "<input id='adjustMoney"+rowIndex+"' value='"+$("#adjustMoney"+rowIndex).val()+"' name='adjustMoney' size='10' />";
	  	  		}
      	  	}

		},{
			field:'specialApproveType', 
			title:"特批类型",
			width:100,
			editor:{type:'combobox',options:{data:[
                                                   {"value":"","text":"--请选择--"},
	    	               				    	   {"value":"ex-gratia","text":"ex-gratia"},
	    	               				    	   {"value":"technical","text":"technical"}
	    	               				    	  ], 
	    	               				     "panelHeight":"auto"
		    	               				 }
			    } ,onSelect:function(){
        		 var text=$(this).combobox('getValue');
	             $(this).combobox('setText',text);
                 }

	
	     }]],

		onLoadSuccess : function() {
			claimLoadFlag = claimLoadFlag+1;//datagrid加载时会在初始和加载完后都调用formatter，只有当claimLoadFlag为2时才表明加载结束
			$('#ClaimMoneyInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
}

//根据输入的赔付比例计算原始赔付金额
function calculateQuondamMoney(vpu,index){
  var indemnityProportion = $("#indemnityProportion"+index).val();
  if(indemnityProportion==""||indemnityProportion==null){
		$.messager.alert('提示信息','赔付比例不能为空','info');
		return false; 
  }
  var quondamMoney = parseFloat(vpu)*parseFloat(indemnityProportion)/100;
  $("#quondamMoney"+index).val(quondamMoney);
  $("#adjustMoney"+index).val(quondamMoney);
} 

//计算最终赔付金额(调整金额与增减项的和)
function calculate(){
	var claimType=$('#claimType').combobox('getValue');
	if(claimType==""){
		$.messager.alert('提示信息','请先选择理赔类型','info');
		return false;
	}
	$('#ClaimMoneyInfoTable').datagrid('acceptChanges');
	$('#ClaimMoneyInfoTable').datagrid('endEdit',0);
	var adjustMoney=0;
	var  rows=$('#ClaimMoneyInfoTable').datagrid('getRows');
	for(var k=0;k<rows.length;k++){
		var index=$('#ClaimMoneyInfoTable').datagrid('getRowIndex',rows[k]);
		var indemnityProportion = $("#indemnityProportion"+index).val();
		if(indemnityProportion==""||indemnityProportion==null||indemnityProportion<0){
			$.messager.alert('提示信息','赔付比例不能为空');
			return false;
		}
		var money = $("#adjustMoney"+index).val();
		if(money==""||money==null){
			$.messager.alert('提示信息','调整金额不能为空');
			return false;
		}
		adjustMoney = parseFloat(adjustMoney)+parseFloat(money);
	}
	var aditemMoney=0;
	var  rows=$('#AditemInfoTable').datagrid('getRows');
	for(var k=0;k<rows.length;k++){
		var index=$('#AditemInfoTable').datagrid('getRowIndex',rows[k]);
		aditemMoney = parseFloat(aditemMoney)+parseFloat(rows[k].aditemMoney);
	}
	var payMoney=0;
	payMoney=parseFloat(adjustMoney)+parseFloat(aditemMoney);
	$('#payMoney').val(payMoney.toFixed(2));
}
//提交复核
function saveToApproval(){
	var claimType=$('#claimType').combobox('getValue');
	if(claimType==""||claimType==null){
		$.messager.alert('提示信息', '请选择理赔类型', 'info');
		return false;
	}
	var inDangerReason=$('#inDangerReason').val();
	if(inDangerReason==""||inDangerReason==null){
		$.messager.alert('提示信息', '请输入出险原因', 'info');
		return false;
	}
	var sicknessCode=$('#sicknessCode').val();
	if(sicknessCode==""||sicknessCode==null){
		$.messager.alert('提示信息', '请输入疾病代码', 'info');
		return false;	
	}
	var accClassCode=$('#accClassCode').combobox('getValue');
	if(accClassCode==""||accClassCode==null){
		$.messager.alert('提示信息', '请输入意外类型代码', 'info');
		return false;		
	}
	var accBodyCode=$('#accBodyCode').combobox('getValue');
	if(accBodyCode==""||accBodyCode==null){
		$.messager.alert('提示信息', '请输入意外身体代码', 'info');
		return false;		
	}
	
	var claimResult = $('#claimResult').val();
	if(claimResult==""||claimResult==null){
		$.messager.alert('提示信息','请选择初审结论');
		return false;
	}
	var fcheckState = $('#fcheckState').val();
	if(fcheckState==""||fcheckState==null){
		$.messager.alert('提示信息','请选择初审状态');
		return false;
	}
	var policyStateResult = $('#policyStateResult').val();
	if(policyStateResult==""||policyStateResult==null){
		$.messager.alert('提示信息','请选择保单有效性');
		return false;
	}
	
/*	var fcheckOpinion =$('#fcheckOpinion').val();
	if(fcheckOpinion==""||fcheckOpinion==null){
		$.messager.alert('提示信息','请输入初审意见');
		return false;
	}*/
	
	if(claimResult=="05"){
		var refAccountName = $('#refAccountName').val();
		if(refAccountName==""||refAccountName==null){
			$.messager.alert('提示信息','请输入退费账户姓名');
			return false;
		}
		var refAccountNo = $('#refAccountNo').val();
		if(refAccountNo==""||refAccountNo==null){
			$.messager.alert('提示信息','请输入退费账户');
			return false;
		}
		var refMoney = $('#refMoney').val();
		if(refMoney==""||refMoney==null){
			$.messager.alert('提示信息','请输入退费金额');
			return false;
		}
		
		var refAccountBankId = $('#refAccountBankId').combobox('getValue');
		if(refAccountBankId==""||refAccountBankId==null){
			$.messager.alert('提示信息','请输入退费账户银行');
			return false;
		}
		
		var refBankProvinceId = $('#refBankProvinceId').combobox('getValue');
		if(refBankProvinceId==""||refBankProvinceId==null){
			$.messager.alert('提示信息','请输入退费开户行所在省 ');
			return false;
		}
		var refBankCityId = $('#refBankCityId').combobox('getValue');
		if(refBankCityId==""||refBankCityId==null){
			$.messager.alert('提示信息','请输入退费开户行所在市 ');
			return false;
		}
		var refCardType = $('#refCardType').val();
		if(refCardType==""||refCardType==null){
			$.messager.alert('提示信息','请输入证件类型 ');
			return false;
		}
		var refCardNo = $('#refCardNo').val();
		if(refCardNo==""||refCardNo==null){
			$.messager.alert('提示信息','请输入证件号码 ');
			return false;
		}

	}
	

	//校验赔付金额
	var payMoney = $('#payMoney').val();
	if(payMoney==""||payMoney==null){
		$.messager.alert('提示信息','请计算赔付金额');
		return false;
	}
	
	$('#ClaimMoneyInfoTable').datagrid('acceptChanges');
	$('#ClaimMoneyInfoTable').datagrid('endEdit',0);
	var adjustMoney=0;
	var  rows=$('#ClaimMoneyInfoTable').datagrid('getRows');
	for(var k=0;k<rows.length;k++){
		var index=$('#ClaimMoneyInfoTable').datagrid('getRowIndex',rows[k]);
		var indemnityProportion = $("#indemnityProportion"+index).val();
		if(indemnityProportion==""||indemnityProportion==null||indemnityProportion<0){
			$.messager.alert('提示信息','赔付比例不能为空');
			return false;
		}
		var money = $("#adjustMoney"+index).val();
		if(money==""||money==null){
			$.messager.alert('提示信息','调整金额不能为空');
			return false;
		}
		adjustMoney = parseFloat(adjustMoney)+parseFloat(money);
	}
	var aditemMoney=0;
	var  rows=$('#AditemInfoTable').datagrid('getRows');
	for(var k=0;k<rows.length;k++){
		var index=$('#AditemInfoTable').datagrid('getRowIndex',rows[k]);
		aditemMoney = parseFloat(aditemMoney)+parseFloat(rows[k].aditemMoney);
	}
	var calPayMoney=0;
	calPayMoney=parseFloat(adjustMoney)+parseFloat(aditemMoney);
	if(parseFloat(calPayMoney)!=parseFloat(payMoney)){
		$.messager.alert('提示信息','赔付总金额与调整金额,增减项金额之和不一致');
		return false;
	}
	
	//校验是否有没有解决的照会
    var isNote=$('#isNote').val();
    //if(isNote=="是"){
		//$.messager.alert('提示信息','有未决照会，不能提交！');
		//return false;
    //}
	
	//校验受益人相关信息
	var  rows=$('#PayBnfInfoTable').datagrid('getRows');
	var  totalDistributeProportion=0;
	var payMoney=$('#payMoney').val();
	var  bnfPayMoney=0;
	if(rows.length<=0){
		$.messager.alert('提示信息','请输入受益人');
		return false;
	}else{
		for(var k=0;k<rows.length;k++){
			totalDistributeProportion = parseFloat(totalDistributeProportion)+parseFloat(rows[k].distributeProportion);
			bnfPayMoney=parseFloat(bnfPayMoney)+parseFloat(rows[k].payMoney)
		}
		if(totalDistributeProportion>1){
		  $.messager.alert('提示信息','受益份额之和必须为100%');	
		  return false;
		}
		if(payMoney!=bnfPayMoney){
			 $.messager.alert('提示信息','理赔金额与受益金额不一致');	
			 return false;	
		}
	}
	
	var  rows=$('#ClaimMoneyInfoTable').datagrid('getRows');
	var  pdBenifitId;
	var  indemnityProportion=0;
	var  quondamMoney=0;  
	var  specialApproveType;  
	adjustMoney=0;  
	
	if(rows.length>0){
		var index=$('#ClaimMoneyInfoTable').datagrid('getRowIndex',rows[0]);
		pdBenifitId=rows[0].benefitId;
	    indemnityProportion= $("#indemnityProportion"+index).val();
		quondamMoney=$("#quondamMoney"+index).val();
		specialApproveType=rows[0].specialApproveType;
		adjustMoney=$("#adjustMoney"+index).val(); 
	    
	}
	
	if(adjustMoney==""||adjustMoney==null){
		 $.messager.alert('提示信息','调整金额不能为空');	
		 return false;	
	}
	if(indemnityProportion==""||indemnityProportion==null){
		 $.messager.alert('提示信息','赔付比例不能为空');	
		 return false;		
	}

	var inDangerReason =$('#inDangerReason').val();
	//inDangerReason=decodeURIComponent(inDangerReason,true);
	//inDangerReason = encodeURI(encodeURI(inDangerReason)); 
	var fcheckOpinion =$('#fcheckOpinion').val();
	//fcheckOpinion=decodeURIComponent(fcheckOpinion,true);
	//fcheckOpinion = encodeURI(encodeURI(fcheckOpinion)); 

	//校验赔付金额
	var payMoney = $('#payMoney').val();
	if(payMoney==""||payMoney==null){
		$.messager.alert('提示信息','请计算赔付金额');
		return false;
	}else{
		if(payMoney<=0){
			$.messager.confirm('提示信息','赔付金额为零，是否继续？',function(r){ 
				if (!r){ 
			    return false;
				} 
				else{
					var dlist = [];
					dlist.push({
								"id" : $('#id').val(),
								"claimTaskNo":$('#claimTaskNo').val(),
								"policyNo":$('#policyNo').val(),
								"claimType" : $('#claimType').combobox('getValue'),
								"applyDate" : $('#applyDate').val(),
								"inDangerDate" : $('#inDangerDate').val(),
								"inDangerReason" : inDangerReason,
								"sicknessCode": $('#sicknessCode').val(),
								"accClassCode": $('#accClassCode').combobox('getValue'),
								"accBodyCode":$('#accBodyCode').combobox('getValue'),
								"claimResult":$('#claimResult').val(),
								"fcheckState":$('#fcheckState').val(),
								"policyStateResult" : $('#policyStateResult').val(),
								"fcheckOpinion": fcheckOpinion,
								"pdBenifitId":pdBenifitId,
								"indemnityProportion":escape(indemnityProportion),
								"quondamMoney":quondamMoney,
								"specialApproveType":specialApproveType,
								"adjustMoney":adjustMoney,
								"payMoney":$('#payMoney').val()
							});
					sAlert('正在提交数据，请您耐心等候...');
					$.ajax({
						url:'saveToApproval',
						data:$.toJSON(dlist),
						type:'POST',
						contentType:'application/json;charset=utf-8', 
						success:function(data) {
							if(data.mes=="isNote"){
								cAlert();
								$.messager.alert('提示信息', '有未决照会，不能提交！', 'info');
							}
							else{
								cAlert();
							$.messager.alert('提示信息', data.mes, 'info',function(){
								parent.addtab('我的任务','nb/listTaskUrl');
								parent.deletetab('理赔初审');
							});
							}
						}
					});
					
				  }
				});	
		}else{
			var dlist = [];
			dlist.push({
						"id" : $('#id').val(),
						"claimTaskNo":$('#claimTaskNo').val(),
						"policyNo":$('#policyNo').val(),
						"claimType" : $('#claimType').combobox('getValue'),
						"applyDate" : $('#applyDate').val(),
						"inDangerDate" : $('#inDangerDate').val(),
						"inDangerReason" : inDangerReason,
						"sicknessCode": $('#sicknessCode').val(),
						"accClassCode": $('#accClassCode').combobox('getValue'),
						"accBodyCode":$('#accBodyCode').combobox('getValue'),
						"claimResult":$('#claimResult').val(),
						"fcheckState":$('#fcheckState').val(),
						"policyStateResult" : $('#policyStateResult').val(),
						"fcheckOpinion": fcheckOpinion,
						"pdBenifitId":pdBenifitId,
						"indemnityProportion":escape(indemnityProportion),
						"quondamMoney":quondamMoney,
						"specialApproveType":specialApproveType,
						"adjustMoney":adjustMoney,
						"payMoney":$('#payMoney').val()
					});
			sAlert('正在提交数据，请您耐心等候...');
			$.ajax({
				url:'saveToApproval',
				data:$.toJSON(dlist),
				type:'POST',
				contentType:'application/json;charset=utf-8', 
				success:function(data) {
					if(data.mes=="isNote"){
						cAlert();
						$.messager.alert('提示信息', '有未决照会，不能提交！', 'info');
					}
					else{
						cAlert();
					$.messager.alert('提示信息', data.mes, 'info',function(){
						parent.addtab('我的任务','nb/listTaskUrl');
						parent.deletetab('理赔初审');
					});
					}
				}
			});
		}
	}
	

}

//疾病代码查询页面
function querySinknessPage() {
//parent.addtab('疾病代码','claim/querySinknessPageUrl');
	
window.open("querySinknessPageUrl","_blank",'height=520, width=950, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no') //这句要写成一行;
}





//撤销任务
function cancelTask(){
	var dlist = [];
	dlist.push({
				"id" : $('#id').val(),
				"flag":"1"
			});
	sAlert('正在提交数据，请您耐心等候...');
	$.post("cancelTask?list="+ $.toJSON(dlist),  function(data) {
		cAlert();
		$.messager.alert('提示信息', data.mes, 'info',function(){
			parent.addtab('我的任务','nb/listTaskUrl');
			parent.deletetab('理赔初审');
		});
	});	
}


//保单查询
function queryPolicyInfo(){
	var policyNo=$('#policyNo').val();
	parent.addtab('保单查询','nb/policyQueryUrl?policyNo='+policyNo);	
}


//照会管理
function noteManage(){
	var policyNo = $('#policyNo').val();
	var dlist = [];
	dlist.push({
				"policyNo":policyNo,
				"businessNo":$('#id').val(),
				"businessType":"02"
			});
	parent.addtab('照会管理','note/listNote?list='+$.toJSON(dlist));
}




