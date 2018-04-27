var customerTable = null;
jQuery(function($) {
	//当页面加载时调用此方法
	initCustomerBelongTable();
	initAllCombobox();
});



function initCustomerBelongTable(){
	customerTable = $('#listBelong_CustomerTable').datagrid({
		 // 标题
		title : '客户调整信息列表',
		method : 'post',
		// 图标
		iconCls : 'icon-edit', 
		// 多选
		singleSelect : true, 
		// 高度
		//height : 380, 
		// 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例
		fitColumns : true, 
		// 奇偶行颜色不同
		striped : true, 
		// 可折叠
		collapsible : true,
		url : contextPath+"/customer/queryCustomerBelongList",
		// 排序的列
		sortName : 'id', 
		// 倒序
		sortOrder : 'desc', 
		// 服务器端排序
		remoteSort : true, 
		// 主键字段
		idField : 'id', 
		// 查询条件
		queryParams : {}, 
		// 显示分页
		pagination : true, 
		// 显示行号
		rownumbers : true, 
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[
		        //显示复选框
		        {
					field : 'ck',
					checkbox : true,
					width : 2
				}, 
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
					//sortable : true,
					formatter : function(value, row, index) {
						return row.customerNo;
						//addcustomeWindow('新增客户', contextPath+"/customer/addCustomerUrl?param="+param);
						//var param = {};
						//param.loadFlag = "custDetail";
						//param.custBaseInfoId = row.custBaseInfoId;
						//param = $.toJSON(param);
						//return "<a href='#'  onclick=addcustomeWindow('客户明细信息','"+contextPath+"/customer/modifyCustomerInfoUrl?param="+param+"')>"+row.customerNo+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'chnName',
					title : '中文姓名',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.chnName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'custLevel',
					title : '客户级别',
					width : 100,
					//hidden: true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.custLevel;
					}
				},
				/*{
					field : 'custQuality',
					title : '客户重要性',
					width : 100,
					//hidden: true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.custQuality;
					}
				},
				{
					field : 'custObtainWayName',
					title : '获客方式',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.custObtainWayName;
					}
				},*/
				{
					field : 'custType',
					title : '客户类型',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.custType;
					}
				},
				{
					field : 'agentName',
					title : '当前财富顾问',
					width : 100,
					//hidden : true,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},
				{
					field : 'storeName',
					title : '当前网点',
					width : 100,
					hidden:true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.storeName;
					}
				},
				{
					field : 'comName',
					title : '当前分公司',
					width : 80,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.comName;
					}
				},
				{
					field : 'agoAgentName',
					title : '上任财富顾问',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.agoAgentName;
					}
				},
				{
					field : 'custBelongStartDay',
					title : '归属起日',
					width : 100,
					//hidden : true,
					formatter : function(value, row, index) {
						return row.custBelongStartDay;
					}
				},
				{
					field : 'signTimes',
					title : '调整后签单数',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.signTimes;
					}
				}
				]],
		toolbar : 
		           [{
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						var rows = customerTable.datagrid('getSelections');
						//alert(rows[0].custLevel);
						
						if(rows[0].custLevel=="准客户"){
							$.messager.alert("提示","准客户不允许调整","info");
							return;
						}
						if (rows.length == 0) {
							$.messager.alert("提示","请选择一个客户进行更新");
							return;
						}
						if (rows.length > 1) {
							$.messager.alert("提示","只能选择一个客户更新");
							return;
						}
						var custBaseInfoId = rows[0].custBaseInfoId;
						updateCustomeWindow('客户归属详情', contextPath+"/customer/updateCustBelongInfoUrl");
					}
		}],
		onLoadSuccess : function() {
			$('#listBelong_CustomerTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
	  onClickRow: function (rowIndex, rowData) {
		}
	});
}

///////////////////////////////初始化所有的combobox/////////////////////////////////////
function initAllCombobox(){
	//初始化机构
	$("#lisCustm_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});
	//初始化业务部
	$("#lisCustm_DepartmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#lisCustm_comId").combobox("getValue");
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
			var comId = $("#lisCustm_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
	//初始化客户级别别
	$('#lisCustm_custLevel').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=custLevel'
	});

	//初始化理财经理
	$("#lisCustm_AgentId").combobox({
		url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
		/*	var storeId = $("#lisCust_StoreId").combobox("getValue");*/
			var departmentId = $("#lisCustm_DepartmentId").combobox("getValue");
			/*param.storeId = storeId;*/
			param.departmentId = departmentId;
			if(/*(storeId==null||storeId==""||storeId==undefined)&&(*/departmentId==null||departmentId==""||departmentId==undefined){
				var comId = $("#lisCustm_comId").combobox("getValue");
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
	/*$('#lisCustm_custLevel').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=custLevel'
	});*/
	//理财经理状态
	$('#status_id').combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=wealthAgent'
	});
	
}

/////////////////////////////更新客户归属详情///////////////////////////////////////////
function updateCustomeWindow(title, href) 
{
	$('<div id="updateCustomerWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true, 
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
			//queryCustomList();
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
////////////////////////////////根据表单中条件进行查询///////////////////////////////////////
function queryCustomList() {
	// 设置根据过滤条件查询客户归属信息查询时请求URL
	customerTable.datagrid('options').url = contextPath+"/customer/queryCustomerBelongList";
	// 表单中过滤条件序列化
	var queryParam = $("#listCustm_queryConditionForm").serialize();
	// 表单数据转为Json字符串
	queryParam = formDataToJsonStr(queryParam);
	console.info(queryParam);
	// 重新请求
	customerTable.datagrid('load',{queryParam:queryParam});	
}
//////////////////清空查询条件然后进行查询////////////////////////////////////////////////////////////////
function clearForm() {
	$("#listCustm_queryConditionForm").form("clear");
	queryCustomList();
}

////////////////////导出客户归属信息//////////////////////////////////////////////////////////////////////////////
function exportCustomerBelongList(){
	//获取用户表单输入的数据
    var queryParam = $('#listCustm_queryConditionForm').serialize(); 
    //用户表单中信息转换为JSON格式字符串
    queryParam = formDataToJsonStr(queryParam);
    //打开窗口发送请求
    window.open(contextPath+'/customer/customerBelongDetailTable.xls?queryParam='+encodeURI(queryParam));
}