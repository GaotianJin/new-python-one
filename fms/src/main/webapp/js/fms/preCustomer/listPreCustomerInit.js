jQuery(function($) {
	initAllCombobox();
	//初始准客户表格
	initListPreCustomerTable();

});
function initAllCombobox(){
	// 分公司：代码-名称
	$("#listPreCust_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
		/*onSelect : function(rec) {
			var url1 = contextPath + '/codeQuery/defStoreQuery?codeType='+ rec.comId;
			$("#listPreCust_storeId").combobox("clear");
			$("#listPreCust_storeId").combobox("reload", url1);
			var url2 = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ rec.comId;
			$("#listPreCust_departmentId").combobox("clear");
			$("#listPreCust_departmentId").combobox("reload", url2);
			var url3 = contextPath + '/codeQuery/defAgentQuery?codeType='+ rec.comId;
			$("#listPreCust_agentId").combobox("clear");
			$("#listPreCust_agentId").combobox("reload", url3);
		}*/
	});	
	// 网点：代码-名称
    $("#listPreCust_storeId").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#listPreCust_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
		/*onSelect : function (){
			var param={};
			var storeId = $("#listPreCust_storeId").combobox("getValue");
			var departmentId = $("#listPreCust_departmentId").combobox("getValue");
			param.storeId = storeId;
			param.departmentId = departmentId;
			var url = contextPath + '/codeQuery/limitAgentInfo?param='+ encodeURI($.toJSON(param));
			$("#listPreCust_agentId").combobox("clear");
			$("#listPreCust_agentId").combobox("reload",url);
		}*/
	});
	// 团队：代码-名称
    $("#listPreCust_departmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#listPreCust_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
		/*onSelect : function (){
			var param={};
			var storeId = $("#listPreCust_storeId").combobox("getValue");
			var departmentId = $("#listPreCust_departmentId").combobox("getValue");
			param.storeId = storeId;
			param.departmentId = departmentId;
			var url = contextPath + '/codeQuery/limitAgentInfo?param='+ encodeURI($.toJSON(param));
			$("#listPreCust_agentId").combobox("clear");
			$("#listPreCust_agentId").combobox("reload",url);
		}*/
	});	
    $("#listPreCust_agentId").combobox({
		/*prompt:'输入姓名后自动搜索',
		//required:true,
		mode:'remote',
		url:contextPath+'/codeQuery/queryAgentInfo',
		valueField:'agentId',
		textField:'agentCodeAndName',
		editable:true,
		hasDownArrow:false,
		onBeforeLoad: function(param){
			console.info("---------------------------");
			console.info(param);
			console.info("============================");
			if(param == null || param.q == null || param.q.replace(/ /g, '') == ''){
				var value = $(this).combobox('getValue');
				var text = $(this).combobox('getText');
				//console.info(value+";"+text);
				if(value){// 修改的时候才会出现q为空而value不为空
					param.id = value;
					return true;
				}
				return false;
			}
		}*/
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
			var storeId = $("#listPreCust_storeId").combobox("getValue");
			var departmentId = $("#listPreCust_departmentId").combobox("getValue");
			param.storeId = storeId;
			param.departmentId = departmentId;
			if((storeId==null||storeId==""||storeId==undefined)&&(departmentId==null||departmentId==""||departmentId==undefined)){
				var comId = $("#listPreCust_comId").combobox("getValue");
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
}

var preCustomerTable ;
function initListPreCustomerTable(){
	var  selectIndex = -1;
	preCustomerTable = $('#listPreCust_preCustomerTable').datagrid({
		title : '准客户信息列表', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/preCustomer/queryPreCustomerListUrl",
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
				},  
				{
					field : 'preCustBaseInfoId',
					title : '客户流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.preCustBaseInfoId;
					} 
				},
				{
					field : 'preCustNo',
					title : '准客户编码',
					width : 100,
					formatter : function(value, row, index) {
						var param = {};
						param.preCustBaseInfoId = row.preCustBaseInfoId;// 将准客户基本信息流水号添加到param对象
						// 如果准客户姓名preCustName不为undefined,则添加到param对象
						if (row.preCustName != undefined) {
							var preCustNameAfterEncode = encodeURI(row.preCustName);// 将准客户姓名preCustName使用encodeURI函数编码
							param.preCustName = preCustNameAfterEncode;
						}
						// 如果准客户手机号preCustMobile不为undefined,则添加到param对象
						if (row.preCustMobile != undefined) {
							param.preCustMobile = row.preCustMobile;
						}
						// 如果准客户楼号preCustBuildingNo不为undefined,则添加到param对象
						if (row.preCustBuildingNo != undefined) {
							param.preCustBuildingNo = encodeURI(row.preCustBuildingNo);
						}
						var data = $.toJSON(param); // param对象转为JSON格式字符串
						data = data.replace(/%/g, "%25");  
						data = data.replace(/\&/g, "%26");  
						data = data.replace(/\+/g, "%2B");
						data = data.replace(/#/g, "%23");
						data = encodeURI(data);
						return "<a href='#'  onclick=addcustomeWindow('准客户明细信息','"+contextPath+"/preCustomer/detailPreCustomerUrl?param="+data+"')>"+row.preCustNo+"</a>";
					} 
				},
				{
					field : 'preCustName',
					title : '准客户姓名',
					width : 100,
					formatter : function(value, row, index) {
						return row.preCustName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'preCustMobile',
					title : '手机号',
					width : 100,
					formatter : function(value, row, index) {
						return row.preCustMobile;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'preCustType',
					title : '客户类型',
					width : 100,
					formatter : function(value, row, index) {
						return row.preCustType;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'preCustTypeCode',
					title : '客户类型编码',
					width : 100,
					hidden:true,
					formatter : function(value, row, index) {
						return row.preCustTypeCode;
					} // 需要formatter一下才能显示正确的数据
				},
//				{
//					field : 'preCustResidentialBuilding',
//					title : '客户楼盘',
//					width : 100,
//					formatter : function(value, row, index) {
//						return row.preCustResidentialBuilding;
//					} // 需要formatter一下才能显示正确的数据
//				},
//				{
//					field : 'preCustResidentialBuildingCode',
//					title : '客户楼盘编码',
//					hidden:true,
//					width : 100,
//					formatter : function(value, row, index) {
//						return row.preCustResidentialBuildingCode;
//					} // 需要formatter一下才能显示正确的数据
//				},
				
//				{
//					field : 'preCustBuildingNo',
//					title : '楼盘号',
//					hidden:true,
//					width : 100,
//					formatter : function(value, row, index) {
//						return row.preCustBuildingNo;
//					} // 需要formatter一下才能显示正确的数据
//				},
				
				{
					field : 'preCustObtainWay',
					title : '获客方式',
					width : 100,
					formatter : function(value, row, index) {
						return row.preCustObtainWay;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'preCustObtainWayCode',
					title : '获客方式编码',
					width : 100,
					hidden:true,
					formatter : function(value, row, index) {
						return row.preCustObtainWayCode;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agentId',
					title : '代理人编码Id',
					width : 100,
					hidden:true,
					formatter : function(value, row, index) {
						return row.agentId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agentName',
					title : '财富顾问',
					width : 100,
					formatter : function(value, row, index) {
						return row.agentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'lastPreCustVisitTime',
					title : '最近拜访时间',
					width : 100,
					formatter : function(value, row, index) {
						return row.lastPreCustVisitTime;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'visitTimes',
					title : '拜访次数',
					width : 100,
					formatter : function(value, row, index) {
						return row.visitTimes;
					} // 需要formatter一下才能显示正确的数据
				}]],
		toolbar : [{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addPreCustomer();
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						updateCustomer();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteCustomer();
					}
				}],
		onLoadSuccess : function() {
			$('#listPreCust_preCustomerTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow: function (rowIndex, rowData) {
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

//新增准客户
function addPreCustomer(){
	$.ajax({
		type : 'post',
		url : contextPath + "/preCustomer/addVerifyPreAgentUrl",
		cache : false,
		success : function(resultInfo) {
			try {
				//如果校验成功，则打开修改准客户信息页面
				if (resultInfo.success) {
					addcustomeWindow('新增准客户', contextPath+"/preCustomer/addPreCustomerBaicInfoUrl");
				} else {
					$.messager.alert('提示', resultInfo.msg);
					return;
				}
			} catch (e) {
				$.messager.alert('提示', e);
				return;
			}
		}
	});
	
	
//	addcustomeWindow('新增准客户', contextPath+"/preCustomer/addPreCustomerBaicInfoUrl");
}

//更新准客户
function updateCustomer(){
	var rows = $('#listPreCust_preCustomerTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert("提示","请选择一条准客户信息进行更新");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert("提示","只能选中一条准客户信息进行更新");
		return;
	}
	var param={};
	param.modifyPreCustBaseInfoId=rows[0].preCustBaseInfoId;
	param.modifyAgentId=rows[0].agentId;
	$.ajax({
		type : 'post',
		url : contextPath + "/preCustomer/updateVerifyPreCustomerUrl",
		data : 'param='+encodeURI($.toJSON(param)),
		cache : false,
		success : function(resultInfo) {
			try {
				//如果校验成功，则打开修改准客户信息页面
				if (resultInfo.success) {
					addcustomeWindow('更新准客户', contextPath+"/preCustomer/updatePreCustomerUrl?param="+encodeURI($.toJSON(param)));
				} else {
					$.messager.alert('提示', resultInfo.msg);
					return;
				}
			} catch (e) {
				$.messager.alert('提示', e);
				return;
			}
		}
	});
	
}

//删除准客户
function deleteCustomer(){
	var rows = $('#listPreCust_preCustomerTable').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择要删除准客户基本信息", 'info');
		return;
	} 
	else 
	{
		$.messager.confirm('提示', '确定要删除吗?', function(result){
			if (result){
				var dlist = [];
				$.each(rows, function(i, n) {
					dlist.push({"preCustBaseInfoId":rows[0].preCustBaseInfoId,"agentId":rows[0].agentId});
				});
				$.post(contextPath+"/preCustomer/deletePreCustomerBasicInfoUrl?list=" +$.toJSON(dlist), function(data) {
					//searchBasicLawProduct();
					$.messager.alert('提示', data.msg, 'info');
					// 重新加载数据到datagrid控件
					preCustomerTable.datagrid('load', {});
				});
			}
		});
	}
}



function initListCustomerCombobox(){
	$('#lisCust_IdType').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=idType'
	});
	
	$('#lisCust_Sex').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=sex'
	});
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
			queryPreCustomerList();
		}
	});
}


 
// 表格查询
function queryPreCustomerList() {
	preCustomerTable.datagrid('options').url = contextPath+"/preCustomer/queryPreCustomerListUrl";
	var queryParam = $("#listPreCust_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
//	console.info(queryParam);
	preCustomerTable.datagrid('load',{param:queryParam});	
}


// 清空查询条件然后进行查询
function clearPreCustomer() {
	$('#listPreCust_queryConditionForm').form('clear');	
}