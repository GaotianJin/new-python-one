jQuery(function($) {
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
					field : 'row.crippleClass',
					title : '伤残大类',
					width : 200,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleClass;
					} 
				}, {
					field : 'row.crippleSubClass',
					title : '伤残子类',
					width : 200,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleSubClass;
					}
				},{
					field : 'row.crippleDesc',
					title : '伤残描述',
					width : 200,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleDesc;
					}
				},{
					field : 'row.crippleGrade',
					title : '伤残等级',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.crippleGrade;
					}
				},{
					field : 'row.authenticationDate',
					title : '鉴定日期',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.authenticationDate;
					}
				}]],
		onLoadSuccess : function() {
			$('#CrippleInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	

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
					field : 'row.aditemName',
					title : '增减项名称',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.aditemName;
					} 
				}, {
					field : 'row.aditemMoney',
					title : '金额（元）',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.aditemMoney;
					}
				}]],
		onLoadSuccess : function() {
			$('#AditemInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
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
					field : 'row.bnfName',
					title : '受益人姓名',
					width : 70,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfName;
					} 
				},	{
					field : 'row.distributeProportion',
					title : '受益比例',
					width : 60,
					sortable : false,
					formatter : function(value, row, index) {
						return row.distributeProportion;
					} 
				}, 	{
					field : 'row.payMoney',
					title : '受益金额',
					width : 70,
					sortable : false,
					formatter : function(value, row, index) {
						return row.payMoney;
					} 
				}, {
					field : 'row.bnfAccountBankIdName',
					title : '受益人账户银行',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfAccountBankIdName;
					} 
				}, {
					field : 'row.payBankProvinceIdName',
					title : '受益人开户行所在省',
					width : 120,
					sortable : false,
					formatter : function(value, row, index) {
						return row.payBankProvinceIdName;
					} 
				},  {
					field : 'row.payBankCityIdName',
					title : '受益人开户行所在市',
					width : 120,
					sortable : false,
					formatter : function(value, row, index) {
						return row.payBankCityIdName;
					} 
				}, {
					field : 'row.bnfAccountName',
					title : '受益人账户名称',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfAccountName;
					} 
				}, {
					field : 'row.bnfAccountNo',
					title : '受益人账号',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfAccountNo;
					} 
				}, {
					field : 'row.bnfCardTypeName',
					title : '受益人证件类型',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfCardTypeName;
					} 
				},  {
					field : 'row.bnfCardNo',
					title : '受益人证件号',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.bnfCardNo;
					}
				},  {
					field : 'row.mobile',
					title : '手机号码',
					width : 80,
					sortable : false,
					formatter : function(value, row, index) {
						return row.mobile;
					}
				}]],
		onLoadSuccess : function() {
			$('#PayBnfInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	var claimType=$('#claimTypeCode').val();
	if(claimType=="2"){
		$('#claimType').val("死亡");
	}else if(claimType=="3"){
		$('#claimType').val("伤残");
	}
	

	
	//初始化页面信息显示
	var mes=$('#mes').val();
	if(mes!=""&&mes!=null){
		$.messager.alert('提示信息',mes);
	}
	
	
	//隐藏退费信息页
	$('#tabdiv3').hide();
	
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
	
	//禁用页面输入框
    $(".table_input").attr("disabled","true");
	$(".table_select").attr("disabled","true");
	$(".easyui-datebox").attr("disabled","true");
	$('#calculateBtn').attr("disabled","true");

	var claimTypeCode=$('#claimTypeCode').val();
	if(claimTypeCode!=""&&claimTypeCode!=null){
		queryClaimMoneyInfo();
	}
	
	var flag = $('#flag').val();
	if(flag == "noOper"){
		$('#buttonDiv').css('display', 'none');
		$('#claim_main_div').css('top','10px');
	}
	
});


//根据选择的理赔类型,显示赔付金额
function queryClaimMoneyInfo(){
	//var wd=$('#claimCheckInputForm').width();
	//var col_size=7;
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
		sortName : 'dbid', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'dbid', // 主键字段
		queryParams : {id:$('#id').val(),claimType:$('#claimTypeCode').val()}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'row.productName',
					title : '产品名称',
					width : 250,
					sortable : false,
					formatter : function(value, row, index) {
						return row.productName;
					} 
				}, {
					field : 'row.vpu',
					title : '产品保额（元）',
					width : 100,
					sortable : false,
					formatter : function(value, row, index) {
						return row.vpu;
					}
				},{
					field : 'row.benefitName',
					title : '责任名称',
					width : 150,
					sortable : false,
					formatter : function(value, row, index) {
						return row.benefitName;
					}
				},{
					field:'row.indemnityProportion',
					title:'赔付比例',
					align:'center',
					width:100,
					formatter : function(value, row, index) {
						return row.indemnityProportion;
					}
				},{
					field : 'row.quondamMoney',
					title : '赔付金额（元）',
					width : 100,
					sortable : false,
              	  	formatter:function(val,row,rowIndex){
              	  	    return row.quondamMoney;
                   }
				},{
					field : 'row.adjustMoney',
					title : '调整后金额（元）',
					width : 100,
					sortable : false,
              	  	formatter:function(val,row,rowIndex){
              	  	    return row.adjustMoney;
                   }
				},{
					field:'row.specialApproveType', 
					title:"特批类型",
					width:60,
              	  	formatter:function(val,row,rowIndex){
              	  	    return row.specialApproveType;
                   }
			     }]],
		onLoadSuccess : function() {
			$('#ClaimMoneyInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
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
			parent.deletetab('理赔复核');
		});
	});	
}



//复核暂存信息
function temporarySave(){

	if(!$('#claimCheckInputForm').form('validate')){
		return;
	}
	var sCheckOpinion = $('#sCheckOpinion').val();
	if(sCheckOpinion==""||sCheckOpinion==null){
		$.messager.alert('提示信息','请输入复核意见');
		return false;
	}
	
	//sCheckOpinion=decodeURIComponent(sCheckOpinion,true);
	//sCheckOpinion = encodeURI(encodeURI(sCheckOpinion)); 
	
	var dlist = [];
	dlist.push({
				"id" : $('#id').val(),
				"sCheckOpinion":sCheckOpinion
			});
	sAlert('正在提交数据，请您耐心等候...');
	
	$.ajax({
		url:'approveTemporarySave',
		data:$.toJSON(dlist),
		type:'POST',
		contentType:'application/json;charset=utf-8', 
		success:function(data) {
			cAlert();
			$.messager.alert('提示信息', data.mes, 'info');
		}
	});

}

//复核不通过
function returnToCheck(){
	if(!$('#claimCheckInputForm').form('validate')){
		return;
	}
	var dlist = [];
	dlist.push({
				"id" : $('#id').val()
			});
	sAlert('正在提交数据，请您耐心等候...');
	$.post("returnToCheck?list="+ $.toJSON(dlist),  function(data) {
		cAlert();
		$.messager.alert('提示信息', data.mes, 'info',function(){
			parent.addtab('我的任务','nb/listTaskUrl');
			parent.deletetab('理赔复核');
		});
		
	});	
}

//提交结案
function confirmClaim(){
	var sCheckOpinion = $('#sCheckOpinion').val();
	if(sCheckOpinion==""||sCheckOpinion==null){
		$.messager.alert('提示信息','请输入复核意见');
		return false;
	}
	//sCheckOpinion=decodeURIComponent(sCheckOpinion,true);
	//sCheckOpinion = encodeURI(encodeURI(sCheckOpinion)); 
	
	//校验是否有没有解决的照会
    var isNote=$('#isNote').val();
    if(isNote=="是"){
		$.messager.alert('提示信息','有未决照会，不能提交！');
		return false;
    }
    
    
	//校验受益人相关信息
	var  rows=$('#PayBnfInfoTable').datagrid('getRows');
	var  totalDistributeProportion=0;
	var payMoney=$('#payMoney').val();
	var  bnfPayMoney=0;
	if(rows.length>0){
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
	
	
	//校验赔付金额
	var payMoney = $('#payMoney').val();
	if(payMoney<=0){
			$.messager.confirm('提示信息','赔付金额为零，是否继续？',function(r){ 
				if (!r){ 
			    return false;
				} 
				else{
					var dlist = [];
					dlist.push({
								"id" : $('#id').val(),
								"sCheckOpinion":sCheckOpinion

							});
					sAlert('正在提交数据，请您耐心等候...');
					$.ajax({
						url:'confirmClaim',
						data:$.toJSON(dlist),
						type:'POST',
						contentType:'application/json;charset=utf-8', 
						success:function(data) {
							if(data.mes=="isNote"){
								cAlert();
								$.messager.alert('提示信息', '有未决照会，不能提交！', 'info');
							}else{
								cAlert();
								$.messager.alert('提示信息', data.mes, 'info',function(){
									parent.addtab('我的任务','nb/listTaskUrl');
									parent.deletetab('理赔复核');
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
					"sCheckOpinion":sCheckOpinion
				});
		sAlert('正在提交数据，请您耐心等候...');
		$.ajax({
			url:'confirmClaim',
			data:$.toJSON(dlist),
			type:'POST',
			contentType:'application/json;charset=utf-8', 
			success:function(data) {
				if(data.mes=="isNote"){
					cAlert();
					$.messager.alert('提示信息', '有未决照会，不能提交！', 'info');
				}else{
					cAlert();
					$.messager.alert('提示信息', data.mes, 'info',function(){
						parent.addtab('我的任务','nb/listTaskUrl');
						parent.deletetab('理赔复核');
					});	
				}

			}
		});
	
	}

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




