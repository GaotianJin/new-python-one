jQuery(function($){
	initAllCombobox();
	listPreCustActivityTable();
})
function initAllCombobox(){
	// 分公司：代码-名称
	$("#listPreCustActivity_comName").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	
 // 门店：代码-名称
   /* $("#listPreCustActivity_storeName").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#listPreCustActivity_comName").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});*/
 // 团队：代码-名称
    $("#listPreCustActivity_departmentName").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#listPreCustActivity_comName").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/departmentQuery';
			}else{
				var url = contextPath + '/codeQuery/defDepartmentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});	
    //财富顾问
	$("#listPreCustActivity_agentId").combobox({
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
			/*var storeId = $("#listPreCustActivity_storeName").combobox("getValue");*/
			var departmentId = $("#listPreCustActivity_departmentName").combobox("getValue");
			/*param.storeId = storeId;*/
			param.departmentId = departmentId;
			if(/*(storeId==null||storeId==""||storeId==undefined)&&(*/departmentId==null||departmentId==""||departmentId==undefined){
				var comId = $("#listPreCustActivity_comName").combobox("getValue");
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
var listPreCustActivityTable;
function listPreCustActivityTable(){
	var selectIndex = -1;
	listPreCustActivityTable = $("#listPreCustActivityTable").datagrid({
		title : '活动量列表',
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath + "/preCustomer/getAllPreCustActivityUrl", // 数据来源
		//sortName : 'id', // 排序的列
		//sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageSize : 10,
		pageList : [ 5, 10, 15, 20, 25, 30 ],
		columns :[[
		           {
		        	   field : 'ck',
		   			checkbox : true
		           },
		           {
			        	  field : 'comName',
			        	  title : '分公司',
			        	  width : 100,
			        	  formatter : function(value, row, index) {
				  				return row.comName;
				  			}
			           },
		           {
		        	  field : 'departmentName',
		        	  title : '团队',
		        	  width : 100,
		        	  formatter : function(value, row, index) {
		  				return row.departmentName;
		  			}
		    
		           },
		           {
			        	  field : 'storeName',
			        	  title : '网点',
			        	  hidden:true,
			        	  width : 100,
			        	  formatter : function(value, row, index) {
				  				return row.storeName;
				  			}
			           },
		           {
			        	  field : 'agentName',
			        	  title : '财富顾问',
			        	  width : 50,
			        	  formatter : function(value, row, index) {
				  				return row.agentName;
				  			}
			           },
		           {
			        	  field : 'preCustNo',
			        	  title : '客户编号',
			        	  width : 100,
			        	  formatter : function(value, row, index) {
				  				return row.preCustNo;
				  			}
			           },
		           {
			        	  field : 'preCustName',
			        	  title : '客户姓名',
			        	  width : 50,
			        	  formatter : function(value, row, index) {
				  				return row.preCustName;
				  			}
			           },
		           {
			        	  field : 'preCustVisitType',
			        	  title : '拜访类型',
			        	  width : 100,
			        	  formatter : function(value, row, index) {
				  				return row.preCustVisitType;
				  			}
			           },
		           {
			        	  field : 'preCustVisitAction',
			        	  title : '拜访动作',
			        	  width : 200,
			        	  formatter : function(value, row, index) {
				  				return row.preCustVisitAction;
				  			}
			           },
			        {
				          field : 'preCustVisitTime',
				          title : '拜访时间',
				          width : 100,
				          formatter : function(value, row, index) {
				  				return row.preCustVisitTime;
				  			}
				       }
		]],
		onLoadSuccess : function() {
			$('#userTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex, rowData){
			if(selectIndex==rowIndex){
				//第一次单击选中,第二次单击取消选中
				$(this).datagrid("unselectRow",rowIndex);
				selectIndex=-1;
			}else{
				selectIndex = rowIndex;
			}
		}
	})
}
//清空列表
function clearPreCustActivityCondition() {
	$("#listPreCustActivityForm").form("clear");
}
//查询列表
function preCustActivityInfoList(){
	//设置Datagrid获取数据的url
	listPreCustActivityTable.datagrid('options').url = contextPath+"/preCustomer/getAllPreCustActivityUrl";
	//获取页面的查询条件
	var queryParam = $("#listPreCustActivityForm").serialize();
	//将请求参数转换成JSON格式字符串
	queryParam = formDataToJsonStr(queryParam);
	//加载datagrid数据
	listPreCustActivityTable.datagrid('load',{queryParam:queryParam});
}
//导出明细表
function exportDetailList(){
	var queryParam = $('#listPreCustActivityForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath+'/preCustomer/preCustActivityDetail.xls?queryParam='+encodeURI(queryParam));
}
//导出活动量管理打分表
function exportActivityManagementList(){
	var queryParam = $('#listPreCustActivityForm').serialize(); // 获取用户表单中的输入信息
	queryParam = formDataToJsonStr(queryParam);// 用户表单中信息转换为JSON格式字符串
	window.open(contextPath+'/preCustomer/preCustActivityManagement.xls?queryParam='+encodeURI(queryParam));
}
//导出汇总表
function exportSummary() {
	
}
	
