var updatePreCustBaseInfoId=null;

jQuery(function($) 
{
	//获取查询页面参数
	updatePreCustBaseInfoId = $("#updatePreCustBaseInfoId").val();
	//初始化下拉列表
	initAllPreCustomerInfo();
	//初始表格
	initpreCustomerVistorTable();
	//初始化需要修改的数据
	updatePreCusomerInfo();

	$("#update_ChinaName").attr("readonly",true);
});
function initAllPreCustomerInfo() {
	//性别
	$("#update_sexual").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=sex',// 通过URL加载远程列表数据。
		valueField : 'code',// 基础数据值名称绑定到该下拉列表框。
		textField : 'codeName',
		editable:false,
		required:true// 基础数据字段名称绑定到该下拉列表框。
	});
	//客户类型
	$("#update_CustomerType").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=customerType',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		required:true
	});
	//客户居住楼盘
	$("#update_CustomerProperty").combobox({
		url : contextPath+'/preCustomer/queryBuildListCode',
		valueField : 'code',
		textField : 'codeName',
		editable:false
	});
	//获取客户方式
	$("#update_getCustomerType").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=preCustObtailWay',
		valueField : 'code',
		textField : 'codeName',
		required:true,
		editable:false
	});	
}

//初始化需要修改的数据
function updatePreCusomerInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/preCustomer/getPreCustomerInfo",
		data:{param:updatePreCustBaseInfoId},
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if (resultObj.preCustomerBaseInfo!=null&&resultObj.preCustomerBaseInfo!=undefined&&resultObj.preCustomerBaseInfo!=""){
						setInputValueById("update_PreCustomerForm",resultObj.preCustomerBaseInfo);
				}
					if(resultObj.preCustomerVistorInfoList!=null&&resultObj.preCustomerVistorInfoList!=undefined&&resultObj.preCustomerVistorInfoList!=""){
						loadJsonObjData("preCustomervistorTable",resultObj.preCustomerVistorInfoList);				
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		
		});
	
}
var preCustomervistorTable; 
function initpreCustomerVistorTable()
{   
	preCustomervistorTable = $('#preCustomervistorTable').datagrid({
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{field : 'ck',
			         checkbox : true,
			         width : 2
			        },
			        {
						field : 'preCustVisitorInfoId',
						title : '拜访Id',
						hidden : true,
						sortable : true,
						formatter : function(value, row, index) {
							return row.preCustVisitorInfoId;
						}
					},
			        {
						field : 'preCustVisitTime',
						title : '拜访时间',
						width : 50,
						sortable : true,
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
							return row.preCustVisitTime;
						}
					},
					
			       {
		        	field : 'preCustVisitType',
		        	title : '拜访类型',
		        	width : 50,
		        	editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,//定义为必填字段
							editable:false,//定义用户是否可以直接输入文本到字段中
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=preCustVisitType',
							onSelect:function(){//在用户选择列表项的时候触发该事件
								//'getEditor'方法获取指定编辑器
								var preCustVisitType = preCustomervistorTable.datagrid('getEditor', {index:preCustomervistorTableRowIndex,field:'preCustVisitType'});
								var preCustVisitTypeName = $(preCustVisitType.target).combobox('getText');
								preCustomervistorTable.datagrid('getRows')[preCustomervistorTableRowIndex]['preCustVisitTypeName'] = preCustVisitTypeName;
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
					} // 需要formatter一下才能显示正确的数据
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
								var preCustVisitActionValue = preCustomervistorTable.datagrid('getEditor', {index:preCustomervistorTableRowIndex,field:'preCustVisitActionValue'});
								var preCustVisitActionName = $(preCustVisitActionValue.target).combobox('getText');
								var preCustVisitActionValues = $(preCustVisitActionValue.target).combobox('getValues');
								var custVisitActionValues = '';
								//将factorCode值拼装
								for(var i=0;i<preCustVisitActionValues.length;i++ ){
									var value=preCustVisitActionValues[i];
									if(value!=null&&value!=""&&value!=undefined){
										custVisitActionValues += value+",";
									}
								}
						/*		if(preCustVisitActionName!=null){
									preCustVisitActionName=preCustVisitActionName.substring(1,preCustVisitActionName.length);
								}*/
								
								preCustomervistorTable.datagrid('getRows')[preCustomervistorTableRowIndex]['preCustVisitActionName'] = preCustVisitActionName;
								preCustomervistorTable.datagrid('getRows')[preCustomervistorTableRowIndex]['preCustVisitAction'] = custVisitActionValues;	
							
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
		        	title : '拜访内容',
		        	width : 200,
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
			$('#preCustomervistorTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow : function(index) {
//			alert(index);
			preCustomervistorTableEditOneRow(index);
			if(preCustomervistorTable.datagrid('getRows')[preCustomervistorTableRowIndex]!=undefined){
				var custVisitActionValues = preCustomervistorTable.datagrid('getRows')[preCustomervistorTableRowIndex]['preCustVisitAction'];	
//				alert(custVisitActionValues);
				var ed = preCustomervistorTable.datagrid('getEditor', {index:preCustomervistorTableRowIndex,field:'preCustVisitActionValue'});
				if(custVisitActionValues!=null&&custVisitActionValues!=""&&custVisitActionValues!=undefined){
					var actionValueArray = custVisitActionValues.split(",");
					$(ed.target).combobox("setValues",actionValueArray);
				}
			}
			
			
		},
		toolbar:"#visitCustomerTable_tb"
	});	
}


/** ******************************准客户拜访信息增/删/编辑************************************ */
var preCustomervistorTableRowIndex;
//增加一行
function preCustomervistorTableAddOneRow(){
	preCustomervistorTableRowIndex = addOneRow(preCustomervistorTable,preCustomervistorTableRowIndex);
}
//删除一行
function preCustomervistorTableRemoveOneRow(){
	removeOneRow(preCustomervistorTable,preCustomervistorTableRowIndex);
	preCustomervistorTableRowIndex= null;
}
//编辑指定行
function preCustomervistorTableEditOneRow(index){
	if(editOneRow(preCustomervistorTable,preCustomervistorTableRowIndex,index)){
		preCustomervistorTableRowIndex = index;
	}
}

//锁定编辑行
function preCustomervistorTableLockOneRow(){
	if(lockOneRow(preCustomervistorTable,preCustomervistorTableRowIndex)){
		preCustomervistorTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}

/**
 * 返回按钮
 */
/*function backUpdatePreCusomerPage(){
	$('#addcustomerWindow').window('destroy');
	parent.clearForm();
	queryPreCustomerList();
}
*/


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

/*****************************提交之前判断表单中必录项是否已输入********************************************/
function beforeSubmit()
{
	if(!$("#update_PreCustomerForm").form("validate"))
	{
		return false;
	}
	return true;
}

/**
 * 提交准客户基本信息和拜访信息
 */
function submitPreCusomerInfo(){
	var param={};
	if(!beforeSubmit())
	{
		return;
	}
	var rows = preCustomervistorTable.datagrid("getRows");
	//判断添加的信息是否有误
	if(!preCustomervistorTableLockOneRow()){
		$.messager.alert('提示', "拜访信息输入有误！", 'info');
		return;
	}
	var preCustInfoJson = formDataToJsonStr($("#update_PreCustomerForm").serialize());
	param.preCustBaseInfo = preCustInfoJson;
	param.updatePreCustBaseInfoId=updatePreCustBaseInfoId;
	param.preCustVistorInfo = $.toJSON($("#preCustomervistorTable").datagrid("getRows"));
	
	var data = $.toJSON(param);
	data = data.replace(/%/g, "%25");  
	data = data.replace(/\&/g, "%26");  
	data = data.replace(/\+/g, "%2B"); 
	data = encodeURI(data);
	
	
	
	$.ajax({
		type : 'post',
		url : contextPath + "/preCustomer/keepPreCustomerUrl",
		data : 'param='+data,
		cache : false,
		success : function(resultInfo){
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg,"info");
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}



