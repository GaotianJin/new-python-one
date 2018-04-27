jQuery(function($) {
	initListCustomerTable();
	initListCustomerCombobox();
	/*$("#lisCust_BirthdayBegin").datebox({
		//parser:parserDate
		//formatter:parserDate
		validType:['validDate']
	});*/
	if(1!=userId){
		$("#listCust_button").hide();
		$("#listCustForce_button").hide();
	}
});


var customerTable ;
function initListCustomerTable(){
	var  selectIndex = -1;
	customerTable = $('#listCust_CustomerTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/customer/queryCustomerList",
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {"queryParam":{}}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'custBaseInfoId',
					title : '客户流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.custBaseInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'customerNo',
					title : '客户号',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						//return row.customerNo;
						//addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
						var param = {};
						param.loadFlag = "custDetail";
						param.custBaseInfoId = row.custBaseInfoId;
						param = $.toJSON(param);
						return "<a href='#'  onclick=addcustomeWindow('客户明细信息','"+contextPath+"/customer/modifyCustomerInfoUrl?param="+param+"')>"+row.customerNo+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'chnName',
					title : '中文姓名',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.chnName;
					} // 需要formatter一下才能显示正确的数据
				},
				/*{
					field : 'lastName',
					title : '英文姓',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.lastName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'firstName',
					title : '英文名',
					width : 100,
					formatter : function(value, row, index) {
						return row.firstName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'idType',
					title : '证件类型',
					//width : 100,
					hidden: true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.idType;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'idTypeName',
					title : '证件类型',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.idTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'idNo',
					title : '证件号码',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.idNo;
					} // 需要formatter一下才能显示正确的数据
				},*/
				{
					field : 'sex',
					title : '性别',
					//width : 100,
					hidden: true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.sex;
					}
				},{
					field : 'sexName',
					title : '性别',
					width : 40,
					sortable : true,
					formatter : function(value, row, index) {
						return row.sexName;
					}
				},{
					field : 'birthday',
					title : '出生日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.birthday;
					}
				},{
					field : 'custLevel',
					title : '客户级别',
					//width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.custLevel;
					}
				},{
					field : 'custLevelName',
					title : '客户级别',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.custLevelName;
					}
				},{
					field : 'investCustomerType',
					title : '投资者类型',
					width : 80,
					sortable : true,
					
					formatter : function(value, row, index) {
						return row.investCustomerType;
					}
				},{
					field : 'investCustomerTypeCode',
					title : '投资者类型编码',
					width : 80,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.investCustomerTypeCode;
					}
				},{
					field : 'custType',
					title : '客户类型',
					width : 80,
					sortable : true,
					
					formatter : function(value, row, index) {
						return row.custType;
					}
				},{
					field : 'comId',
					title : '分公司',
					width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.comId;
					}
				},{
					field : 'comName',
					title : '分公司',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.comName;
					}
				},{
					field : 'agentId',
					title : '财富顾问',
					width : 100,
					hidden : true,
					formatter : function(value, row, index) {
						return row.agentId;
					}
				},{
					field : 'agentName',
					title : '财富顾问',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},{
					field : 'investAuditState',
					title : '升级状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.investAuditState;
					}
				},{
					field : 'investAuditStateCode',
					title : '升级状态编码',
					width : 100,
					sortable : true,
					hidden: true,
					formatter : function(value, row, index) {
						return row.investAuditStateCode;
					}
				},{
					field : 'signTimes',
					title : '签单次数',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.signTimes;
					}
				},
		           {field:'action',title:'操作',width:100,sortable : true,formatter:function(value,row,index){
		        		   return "<a class='RcButton'  href='javascript:updateCustInfoButton("+index+");'>更新信息</a>";
		           }},
		           {field:'action1',title:'升级操作',width:100,sortable : true,formatter:function(value,row,index){
		        	   if(row.investCustomerTypeCode == '02' ||row.investAuditStateCode == '01'){
		        		   return '';
		        	   }else{
		        		   return "<a class='RcButton'  href='javascript:custUpgradeButton("+index+");'>客户升级</a>";
		        	   }
	        		   
	           }}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						/*var param = {};
						param.loadFlag = "addCust";
						param = $.toJSON(param);
						addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);*/
						var param = {};
						param.loadFlag = "addCust";
						param = $.toJSON(param);
						addcustomeWindow('新增客户', contextPath+"/customer/modifyCustomerInfoUrl?param="+param);
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = customerTable.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个客户进行修改");
							return;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","只能选择一个客户修改");
							return;
						}
						//console.info(rows);
						var oneRowData = rows[0];
						oneRowData.loadFlag = "updateCust";
						oneRowData = $.toJSON(oneRowData);
						//console.info("=====oneRowData======"+oneRowData);
						//oneRowData = encodeURIComponent(oneRowData);
						/*var dlist = [];
						dlist.push({"CustomerID" : rows[0].id});*/
						//addcustomeWindow('更新客户', contextPath+"/customer/updateCustomerUrl?list="+ $.toJSON(dlist));
						//addcustomeWindow('更新客户', contextPath+"/customer/updateCustomerUrl?custBaseInfo="+oneRowData);
						addcustomeWindow('更新客户', contextPath+"/customer/modifyCustomerInfoUrl?param="+oneRowData);
					}
				},'-',{
					text : '客户升级',
					iconCls : 'icon-edit',
					handler : function(){
						var rows = customerTable.datagrid('getSelections');
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个客户进行升级");
							return;
						}
						var investCustomerType = rows[0].investCustomerTypeCode;
						if (investCustomerType == '02') {
							$.messager.alert("提示","专业投资者无法进行升级！");
							return;
						}
						var investAuditState = rows[0].investAuditStateCode;
						if (investAuditState == '01') {
							$.messager.alert("提示","审核中的客户无法进行操作！");
							return;
						}
						var param = {};
						param.businessNo = rows[0].custBaseInfoId;
						param.businessType = "15";
						param.investAuditStateCode = rows[0].investAuditStateCode;
						param.chnName = rows[0].chnName;
						param.customerNo = rows[0].customerNo;
						param.agentName = rows[0].agentName;
						addFileWindow('投资者升级材料上传', contextPath+"/fileUpload/investCustFileUploadUrl?param="+$.toJSON(param));
					}
				}/*,'-',{
					text : '生成Excel测试',
					iconCls : 'icon-redo',
					handler : function(){
						window.open(contextPath+'/customer/createExcel.xls');
					}
				}
				, '-', {
					text : '上传文件',
					iconCls : 'icon-redo',
					handler : function() {
						var param = {};
						param.businessNo = "201503060001";
						param.businessType = "02";
						param.operate = "queryFile";
						addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteCustomer();
					}
				}, '-'*/],
		onLoadSuccess : function() {
			$('#listCust_CustomerTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			/*把超链接变成按钮样式*/
	   		$('.RcButton').linkbutton({		   		
	   		});  
		}
		,onClickRow: function (rowIndex, rowData) {
            if(selectIndex==rowIndex){
            	//第一次单击选中,第二次单击取消选中
            	$(this).datagrid('unselectRow', rowIndex);
            	selectIndex=-1;
            }else{
            	selectIndex = rowIndex;
            }
		}
	});
}


function initListCustomerCombobox(){
	/*$('#lisCust_IdType').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=idType'
	});
	
	$('#lisCust_Sex').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=sex'
	});*/
	//初始化机构
	$("#lisCust_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	//初始化团队
	$("#lisCust_DepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#lisCust_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});
	//初始化网点
	/*$("#lisCust_StoreId").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#lisCust_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
	
	//初始化理财经理
	$("#lisCust_AgentId").combobox({
		url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
			/*var storeId = $("#lisCust_StoreId").combobox("getValue");*/
			var departmentId = $("#lisCust_DepartmentId").combobox("getValue");
			/*param.storeId = storeId;*/
			param.departmentId = departmentId;
			if(/*(storeId==null||storeId==""||storeId==undefined)&&*/departmentId==null||departmentId==""||departmentId==undefined){
				var comId = $("#lisCust_comId").combobox("getValue");
				if(comId==null||comId==""||comId==undefined){
					var url = contextPath+'/codeQuery/agentQuery';
				}else{
					var url = contextPath + '/codeQuery/defAgentQuery?codeType='+ comId;
				}
			}else{
				var url = contextPath + '/codeQuery/limitAgentInfo?param='+ encodeURI($.toJSON(param));
			}
			$(this).combobox("clear");
			$(this).combobox("reload",url);
		}
	});
	//客户级别
	$('#lisCust_custLevel').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=custLevel'
	});
	//获客方式
	/*$('#lisCust_custObtainWay').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=custObtainWay'
	});*/
	//客户类型
	$('#lisCust_custType').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=customerType'
	});
	// 初始化客户重要性
	/*$('#cust_Quality').combobox({
		valueField: 'code',
		textField: 'codeName',
		url: 'codeQuery/tdCodeQuery?codeType=custQuality'
	});*/
	
	//是否国金开户
	$('#ListCust_isGoldenStateopen').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=isGoldenStateopen'
	});
	//出生月份
	$('#ListCust_birthMonth').combobox({
		valueField: 'code',
		textField: 'codeName',
		url: 'codeQuery/tdCodeQuery?codeType=birthMonth'
	})
	//投资者类型
	$('#lisCust_InvestCustomerType').combobox({
		valueField: 'code',
		textField: 'codeName',
		url: 'codeQuery/tdCodeQuery?codeType=investCustomerType'
	})
}

function addcustomeWindow(title, href) 
{
	$('<div id="addcustomerWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true, 
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
			queryCustomerList();
		}
	});
}

function addFileWindow(title, href) 
{
	$('<div id="addFileWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		//fit : true, 
		width:800,
		height:500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}
// 表格查询
function queryCustomerList() {
	/*var params = $('#listCust_CustomerTable').datagrid('options').queryParams; // 先取得
	var fields = $('#listCust_queryConditionForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
				params[field.name] = field.value; // 设置查询参数
			});
	$('#listCust_CustomerTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了*/
	customerTable.datagrid('options').url = contextPath+"/customer/queryCustomerList";
	var queryParam = $("#listCust_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	/*var param = eval("("+queryParam+")");
	//console.info(param);
	var chnName = param.chnName;
	var idNo = param.idNo;
	if((chnName==null||chnName==""||chnName==undefined)&&(idNo==null||idNo==""||idNo==undefined)){
		$.messager.alert('提示', "客户姓名和证件号码必须录入其中一项");
		return;
	}*/
	customerTable.datagrid('load',{queryParam:queryParam});	


}


// 清空查询条件然后进行查询
function clearForm() {
	$('#listCust_queryConditionForm').form('clear');
	queryCustomerList();
}

/**
 * 数据导出
 */
function exportCustBaseInfo() {
	var queryParam = $("#listCust_queryConditionForm").serialize();// 获取用户表单中的输入信息
		queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
		window.open(contextPath
				+ '/customer/custBaseInfoDetail.xls?queryParam='
				+ encodeURI(queryParam));
	}

/**
 * 强制客户升级
 */
function custForceUpdate() {
	var rows = customerTable.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择一个客户进行强制升级");
		return;
	}
	var investCustomerTypeCode =rows[0].investCustomerTypeCode;
	if(investCustomerTypeCode=="02"){
		$.messager.alert('提示',"该客户已经是专业投资者，无法再次升级");
		return ;
	}
	var param = {};
	param.custBaseInfoId = rows[0].custBaseInfoId;
	$.messager.confirm('提示', '确认强制升级？', function(r) {
		if (r) {
			$.ajax({
				type:'post',
				url:contextPath+"/customer/custForceUpdate",
				data:'param='+$.toJSON(param),
				cache:false,
				success:function(reData){
						if(reData.success){
							$.messager.alert('提示', "强制升级成功", 'info');
							initListCustomerTable();
						}else{
							$.messager.alert('提示', reData.msg);
						}
				}
			});
		}
	});
	
}
	

/**
 * 数据导出
 */
function updateCustInvestGrade() {
	var param = {};
	$.ajax({
		type:'post',
		url:contextPath+"/customer/updateCustInvestGrade",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
				if(reData.success){
					$.messager.alert('提示', "成功生成客户投资等级", 'info');
				}else{
					$.messager.alert('提示', reData.msg);
				}
		}
	});
	}

/////////////////
/*点击datagrid表格中提交复核按钮触发的函数*/
var updateCustInfoButton=function(index){
	$("#listCust_CustomerTable").datagrid('selectRow',index);
	var oneRowData = $("#listCust_CustomerTable").datagrid('getSelected');
	oneRowData.loadFlag = "updateCust";
	oneRowData = $.toJSON(oneRowData);
	addcustomeWindow('更新客户', contextPath+"/customer/modifyCustomerInfoUrl?param="+oneRowData);
}

var custUpgradeButton=function(index){
	$("#listCust_CustomerTable").datagrid('selectRow',index);
	var oneRowData = $("#listCust_CustomerTable").datagrid('getSelected');
	var param = {};
	param.businessNo = oneRowData.custBaseInfoId;
	param.businessType = "15";
	param.investAuditStateCode = oneRowData.investAuditStateCode;
	param.chnName = oneRowData.chnName;
	param.customerNo = oneRowData.customerNo;
	param.agentName = oneRowData.agentName;
	addFileWindow('投资者升级材料上传', contextPath+"/fileUpload/investCustFileUploadUrl?param="+$.toJSON(param));
}