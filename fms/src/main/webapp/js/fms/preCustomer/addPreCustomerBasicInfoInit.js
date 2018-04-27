/***************浏览器载入该JS文件时执行的函数*********************************/
jQuery(function($){
	//初始化准客户基本信息Form中输入框
	initPreCustomerBasiciInfo();
	//初始化数据表格DataGrid
	initPreCustomerVisitInfoTable();
});


/*************初始化"准客户基本信息Form"多个选择下拉框*************************************/
function initPreCustomerBasiciInfo() {
	//准客户性别下拉框
	$("#sex_select").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=sex',
		valueField:'code', //code值为"性别代码"
		textField:'codeName', //codeName值为"性别代码-性别名称"
		required:true,
		editable:false
	});
	//准客户类型下拉框
	$("#custType_select").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=customerType',
		valueField:'code', //code值为"准客户类型代码"
		textField:'codeName', //codeName值为"准客户类型代码-准客户类型名"
		required:true,
		editable:false
	});
	//准客户居住楼盘下拉框
	$("#resiBuilding_select").combobox({
		url:contextPath+'/preCustomer/queryBuildListCode',
		valueField:'code', //code值为"准客户居住楼盘代码"
		textField:'codeName',
		editable:false//codeName值为"准客户居住楼盘代码-准客户居住楼盘名称"
	});
	//准客户获客方式下拉框
	$("#preCustTypeObtain_select").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=preCustObtailWay',
		valueField:'code', //code值为"准客户获客方式代码"
		textField:'codeName', //codeName值为"准客户获客方式代码-准客户获客方式名称"
		required:true,
		editable:false
	});
}

/***********************初始化准客户拜访信息数据表格**************************************/
var preCustVisitActionValues=null;

var preCustomerVisitInfoTable;
function initPreCustomerVisitInfoTable()
{
	preCustomerVisitInfoTable = $('#preCustomerVisitInfoTable').datagrid({
		method : 'post',		// POST请求
		iconCls : 'icon-edit',  // 图标
		singleSelect : true,    // 多选
		//height : 380,         // 高度
		fitColumns : true,      // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true,         // 奇偶行颜色不同
		collapsible : true,     // 可折叠
		url : null,				// URL为空
		sortName : 'id',        // 排序的列
		sortOrder : 'desc',     // 倒序
		remoteSort : true,      // 服务器端排序
		idField : 'id',         // 主键字段
		queryParams : {},       // 查询条件
		columns : [[
		            {
		            	field : 'ck',
		            	checkbox : true,
		            	width : 2
			        },
			        {
			        	field : 'preCustVisitTime',
			        	title : '准客户拜访时间',
			        	width : 50,
			        	editor: {
			        		type:'datetimebox',
			    			options:{
								required:true,
								editable:false,
								formatter:function(date){
									if(date>new Date()){
										return new Date().format("yyyy-MM-dd hh:mm:ss");
									}else{
										return new Date(date).format("yyyy-MM-dd hh:mm:ss");
									}
								}
							}	
			        	},
			        	
			        	formatter : function(value, row, index) {
			        		return  row.preCustVisitTime;
			        	}
			        },
			        {
			        	field : 'preCustVisitType',
			        	title : '准客户拜访类型',
			        	width : 50,
			        	editor: {
							type:'combobox',
							options:{
								valueField:'code',
								textField:'codeName',
								required:true,
								editable:false, 
								url:contextPath+'/codeQuery/tdCodeQuery?codeType=preCustVisitType',
								onSelect:function(){
									var preCustVisitType = preCustomerVisitInfoTable.datagrid('getEditor', {index:preCustomerVisitInfoTableRowIndex,field:'preCustVisitType'});
									var preCustVisitTypeName = $(preCustVisitType.target).combobox('getText');
									preCustomerVisitInfoTable.datagrid('getRows')[preCustomerVisitInfoTableRowIndex]['preCustVisitTypeName'] = preCustVisitTypeName;
								}
							}
						},
			        	sortable : true,
			        	formatter : function(value, row, index) {
			        		return row.preCustVisitTypeName;
			        	}
			        },
			        {
						field : 'preCustVisitTypeName',
						title : '准客户拜访类型名称',
						width : 50,
						sortable : true,
						hidden : true,
						formatter : function(value, row, index) {
							return row.preCustVisitTypeName;
						} 
					},
			        {
			        	field : 'preCustVisitActionValue',
			        	title : '准客户拜访动作',
			        	width : 80,
			        	editor:  {
							type:'combobox',
							options:{
								valueField:'code',
								textField:'codeName',
								required:true,
								multiple : true,
								editable:false, 
								url:contextPath+'/codeQuery/tdCodeQuery?codeType=preCustVisitAction',
								onSelect:function(){
									var preCustVisitActionValue = preCustomerVisitInfoTable.datagrid('getEditor', {index:preCustomerVisitInfoTableRowIndex,field:'preCustVisitActionValue'});
									var preCustVisitActionName = $(preCustVisitActionValue.target).combobox('getText');
									preCustVisitActionValues = $(preCustVisitActionValue.target).combobox('getValues');
									var custVisitActionValues = '';
									//将factorCode值拼装
									for(var i=0;i<preCustVisitActionValues.length;i++ ){
										var value=preCustVisitActionValues[i];
										if(value!=null&&value!=""&&value!=undefined){
											custVisitActionValues += value+",";
										}
									}
									/*if(preCustVisitActionName!=null){
										preCustVisitActionName=preCustVisitActionName.substring(1,preCustVisitActionName.length);
									}*/
									preCustomerVisitInfoTable.datagrid('getRows')[preCustomerVisitInfoTableRowIndex]['preCustVisitActionName'] = preCustVisitActionName;
									preCustomerVisitInfoTable.datagrid('getRows')[preCustomerVisitInfoTableRowIndex]['preCustVisitAction'] = custVisitActionValues;	
								}
							}
						},
			        	sortable : true,
			        	formatter : function(value, row, index) {
			        		return row.preCustVisitActionName;
			        	}
				},
				{
					field : 'preCustVisitActionName',
					title : '准客户拜访动作名称',
					width : 50,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.preCustVisitActionName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
		        	field : 'preCustVisitContent',
		        	title : '准客户拜访内容',
		        	width : 100,
		        	editor: {
						type:'text',
						options:{
							required:true
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.preCustVisitContent;
		        	}
		        },
				{
					field : 'preCustVisitAction',
					title : '准客户拜访动作名称',
					width : 50,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.preCustVisitAction;
					} // 需要formatter一下才能显示正确的数据
				}
		        ]],
				onLoadSuccess : function() {
					// 一定要加上这一句，要不然DATAGRID会记住之前的选择状态，删除时会出问题
					$('#preCustomerVisitInfoTable').datagrid('clearSelections'); 
				},
				onClickRow:function(index){
					preCustomerVisitInfoTableEditOneRow(index);
					if(preCustomerVisitInfoTable.datagrid('getRows')[preCustomerVisitInfoTableRowIndex]!=undefined){
						var custVisitActionValues = preCustomerVisitInfoTable.datagrid('getRows')[preCustomerVisitInfoTableRowIndex]['preCustVisitAction'];	
						var ed = preCustomerVisitInfoTable.datagrid('getEditor', {index:preCustomerVisitInfoTableRowIndex,field:'preCustVisitActionValue'});
						if(custVisitActionValues!=null&&custVisitActionValues!=""&&custVisitActionValues!=undefined){
							var actionValueArray = custVisitActionValues.split(",");
							$(ed.target).combobox("setValues",actionValueArray);
						}
					}
				},
				toolbar:"#preCustVisitInfo"
	});	
}


/***********************准客户拜访信息：增加/删除/锁定对应函数*****************************/
var preCustomerVisitInfoTableRowIndex;
//准客户拜访信息增加一行
function preCustomerVisitInfoTableAddOneRow(){
	preCustomerVisitInfoTableRowIndex = addOneRow(preCustomerVisitInfoTable,preCustomerVisitInfoTableRowIndex);
}
//准客户拜访信息删除一行
function preCustomerVisitInfoTableRemoveOneRow(){
	removeOneRow(preCustomerVisitInfoTable,preCustomerVisitInfoTableRowIndex);
	preCustomerVisitInfoTableRowIndex= null;
}
//编辑指定准客户拜访信息行
function preCustomerVisitInfoTableEditOneRow(index){

	if(editOneRow(preCustomerVisitInfoTable,preCustomerVisitInfoTableRowIndex,index)){
		preCustomerVisitInfoTableRowIndex = index;
	}
}
//锁定编辑行
function preCustomerVisitInfoTableLockOneRow(){
	if(lockOneRow(preCustomerVisitInfoTable,preCustomerVisitInfoTableRowIndex)){
		preCustomerVisitInfoTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}

/***********************************************************************
 				使用AJAX"提交"表单和数据表格中数据到服务端
 * *******************************************************************/
function addPreCustomerBasicInfoAndVistInfoSave() {
	//首先判断Form中数据合法性
	if(!beforeSubmit())
	{
		return;
	}
	var rows = preCustomerVisitInfoTable.datagrid("getRows");
	//判断添加的信息是否有误
	if(!preCustomerVisitInfoTableLockOneRow()){
		$.messager.alert('提示', "拜访信息输入有误！", 'info');
		return;
	}
	// 声明变量
	var param = {};
	var formdataAferSerialize = $("#preCustomerBasicInfoForm").serialize();
	var formDataJsonStr = formDataToJsonStr(formdataAferSerialize);
	param.preCustomerBasicInfo = formDataJsonStr;
	var datagridInfo = $("#preCustomerVisitInfoTable").datagrid("getRows");
	// 提交之前进行编码
	param.preCustomerVisitInfo = datagridInfo;
	var data = $.toJSON(param);
	data = escape(encodeURIComponent(data));
	// 发送异步请求AJAX
	$.ajax({
		type : 'post',
		url : contextPath + "/preCustomer/saveAddPreCustomerBasicInfoUrl",
		data : 'param='+data,
		cache : false,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		success : function(resultInfo){
			try {
				if (resultInfo.succ) {
					$.messager.alert('提示', resultInfo.msg,"info");
					$("#preCustomerBasicInfoForm").form("clear");
					clearDatagrid(preCustomerVisitInfoTable);
					
				} else {
					$.messager.alert('提示', resultInfo.msg);
					return;
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}

/*********************提交之前判断表单中必录项是否已输入****************************/
function beforeSubmit()
{
	if(!$("#preCustomerBasicInfoForm").form("validate"))
	{
		return false;
	}
	return true;
}

/*******************点击"返回"按钮触发事件函数************************************/
function backPreCustomerBasicInfoPage(){
	$('#addcustomerWindow').window('destroy');
	parent.clearPreCustomerBasicInfoForm();
}

/****清空准用户基本信息查询页面:listPreCustomer.jsp中的Form表单查询条件然后进行查询**/
function clearPreCustomerBasicInfoForm() {
	$('#listPreCust_queryConditionForm').form('clear');
	queryPreCustomerList();
}