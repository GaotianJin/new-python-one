jQuery(function($) {
	initAllCombobox();
	initSaleSearchTable();
	
});

function initAllCombobox(){
	// 分公司：代码-名称
	$("#salaryFind_comId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
 // 网点：代码-名称
    $("#salaryFind_storeId").combobox({
		valueField : 'storeId',
		textField : 'storeName',
		onShowPanel : function(){
			var comId = $("#salaryFind_comId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath + '/codeQuery/storeQuery';
			}else{
				var url = contextPath + '/codeQuery/defStoreQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload", url);
		}
	});
 // 团队：代码-名称
    $("#salaryFind_departmentId").combobox({
		valueField : 'departmentId',
		textField : 'departmentName',
		onShowPanel : function(){
			var comId = $("#salaryFind_comId").combobox("getValue");
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
	$("#salaryFind_agentId").combobox({
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
			var storeId = $("#salaryFind_storeId").combobox("getValue");
			var departmentId = $("#salaryFind_departmentId").combobox("getValue");
			param.storeId = storeId;
			param.departmentId = departmentId;
			if((storeId==null||storeId==""||storeId==undefined)&&(departmentId==null||departmentId==""||departmentId==undefined)){
				var comId = $("#salaryFind_comId").combobox("getValue");
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
	//职级
	var storeCombobox=$("#salaryFind_position").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=positionCode',
		valueField:'code',
		textField:'codeName'
	});
	//级别
	var storeCombobox=$("#salaryFind_grade").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=gradeCode',
		valueField:'code',
		textField:'codeName'
	});
	
	
	$("#salaryFind_calYear").datebox({
		editable:false,
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var startDateValue = y;
			return startDateValue;
		}
	});
	
	$("#salaryFind_calMonth").datebox({
		editable:false,
		formatter:function(date){
			var m = date.getMonth()+1;
			if(m<10){
				return '0'+m;
			}
			return m;
		}
	});
}
var saleSearchTable;
function initSaleSearchTable(){
    saleSearchTable=	 
	$('#saleSearchTable').datagrid({
		title : '薪资结算信息', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/sales/queryAgentWageList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'row.comName',
					title : '所属机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.comName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.comId',
					title : '所属机构Id',
					width : 100,
					hidden:true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.comId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.storeName',
					title : '网点',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.storeName;
					}
				},
				{
					field : 'row.storeId',
					title : '网点Id',
					width : 100,
					hidden:true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.storeId;
					}
				},
				{
					field : 'row.departmentName',
					title : '部门',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.departmentName;
					}
				},
				{
					field : 'row.departmentId',
					title : '部门Id',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.departmentId;
					}
				},
				{
					field : 'row.agentName',
					title : '财富顾问',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},
				{
					field : 'row.agentId',
					title : '财富顾问Id',
					width : 100,
					hidden:true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agentId;
					}
				},{
					field : 'row.positionName',
					title : '职级',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.positionName;
					}
				},{
					field : 'row.gradeName',
					title : '级别',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.gradeName;
					}
				},{
					field : 'row.calYear',
					title : '薪资计算年',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.calYear;
					}
				},{
					field : 'row.calMonth',
					title : '薪资计算月',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.calMonth;
					}
				},{
					field : 'row.sumWageValue',
					title : '奖金合计(元)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.sumWageValue;
					}
				}]],
		onLoadSuccess : function() {
			$('#saleSearchTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}
	



// 清空查询条件然后进行查询
function clearAgentWage() {
		$('#salaryFindForm').form('clear');
		$('#saleSearchTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}
function searchAgentWage(){
	saleSearchTable.datagrid('options').url = contextPath+"/sales/queryAgentWageList";
	var queryparam = $("#salaryFindForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	saleSearchTable.datagrid('load',{param:queryparam});
}


function AgentWageExport(){
	
	var calYear = $("#salaryFind_calYear").datebox("getValue");
	var calMonth = $("#salaryFind_calMonth").datebox("getValue");

	if (calYear== null || calYear == undefined||calYear==""){
	alert("请填写需要导出的薪资计算 年/月");
    return;
    }
	if(calMonth== null||calMonth==""||calMonth==undefined) {
	 alert("请填写需要导出的薪资计算年/月");
	 return;
	}
	alert("开始导出薪资");
	var salaryParam = $("#salaryFindForm").serialize();//tradeBalance.xls
	salaryParam = formDataToJsonStr(salaryParam);
	var param = {};
	param.salaryParam = salaryParam;
	window.open(contextPath+'/sales/salaryCalExport.xls?param='+$.toJSON(param));
}