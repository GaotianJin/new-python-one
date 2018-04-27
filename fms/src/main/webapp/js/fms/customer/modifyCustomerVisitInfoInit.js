var modifyCustVisit_BaseInfoId = null;
var modifyCustVisit_agentId = null;
var modifyCustVisit_tradeLoadFlag = null;
jQuery(function($){
	modifyCustVisit_BaseInfoId = $("#modifyCustVisit_BaseInfoId").val();
	modifyCustVisit_agentId = $("#modifyCustVisit_agentId").val();
	operate = $('#modifyCustomerBaseInfo_loadFlag').val();
	initModifyCustomerVisitTable();
	tradeLoadFlag = $('#modifyCustVisit_tradeLoadFlag').val();
	if( operate!=null&&operate!=""&&operate!=undefined&&operate=="custDetail" ){
		$("#modifyCustomerVisitInfo_submitCarInfoButton").hide();
		$("#modifyCustomerVisitTable_tb").css("display", "none");
	}
	/*if( tradeLoadFlag!=null&&tradeLoadFlag!=""&&tradeLoadFlag!=undefined&&tradeLoadFlag=="tradeLoadFlag" ){
		$("#modifyCustomerVisitInfo_backButton").hide();
	}*/
	
	getModifyCustomerVisitInfo();
})
var modifyCustomerVisitTable; 
function initModifyCustomerVisitTable()
{   
	modifyCustomerVisitTable = $('#modifyCustomerVisitTable').datagrid({
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
		nowrap: false,//单元格是否可以换行
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageSize : 10,
		pageList : [ 5, 10, 15, 20, 25, 30 ],
		columns : [[{field : 'ck',
			         checkbox : true,
			         width : 2
			        },
			        {
						field : 'custVisitorInfoId',
						title : '拜访Id',
						hidden : true,
						sortable : true,
						formatter : function(value, row, index) {
							return row.custVisitorInfoId;
						}
					},
			        {
						field : 'custVisitTime',
						title : '拜访时间',
						width : 150,
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
							return row.custVisitTime;
						}
					},
					
			       {
		        	field : 'custVisitType',
		        	title : '拜访类型',
		        	width : 150,
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
								var custVisitType = modifyCustomerVisitTable.datagrid('getEditor', {index:modifyCustomerVisitTableRowIndex,field:'custVisitType'});
								var custVisitTypeName = $(custVisitType.target).combobox('getText');
								modifyCustomerVisitTable.datagrid('getRows')[modifyCustomerVisitTableRowIndex]['custVisitTypeName'] = custVisitTypeName;
							}
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.custVisitTypeName;
		        	}
		        },
		        {
					field : 'custVisitTypeName',
					title : '准客户拜访类型名称',
					width : 50,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.custVisitTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
		        {
		        	field : 'custVisitActionValue',
		        	title : '准客户拜访动作',
		        	width : 450,
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
								/*var custVisitActionValue = modifyCustomerVisitTable.datagrid('getEditor', {index:modifyCustomerVisitTableRowIndex,field:'custVisitActionValue'});
								var custVisitActionName = $(custVisitActionValue.target).combobox('getText');
								var custVisitActionValues = $(custVisitActionValue.target).combobox('getValues');*/
								var custVisitActionName = $(this).combobox('getText');
								var custVisitActionValues = $(this).combobox('getValues');
								var custVisitActionValue = '';
								//将factorCode值拼装
								for(var i=0;i<custVisitActionValues.length;i++ ){
									var value=custVisitActionValues[i];
									if(value!=null&&value!=""&&value!=undefined){
										custVisitActionValue += value+",";
									}
								}
						/*		if(custVisitActionName!=null){
									custVisitActionName=custVisitActionName.substring(1,custVisitActionName.length);
								}*/
								modifyCustomerVisitTable.datagrid('getRows')[modifyCustomerVisitTableRowIndex]['custVisitActionName'] = custVisitActionName;
								modifyCustomerVisitTable.datagrid('getRows')[modifyCustomerVisitTableRowIndex]['custVisitAction'] = custVisitActionValue;	
							
							}
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.custVisitActionName;
		        	}
			},
				
				{
					field : 'custVisitActionName',
					title : '准客户拜访动作名称',
					width : 50,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.custVisitActionName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
		        	field : 'custVisitContent',
		        	title : '拜访内容',
		        	width : 600,
		        	//nowrap: false,
		        	editor: {
						type:'text',
						options:{
							required:true
						}
					},
		        	sortable : true,
		        	formatter : function(value, row, index) {
		        		return row.custVisitContent;
		        	}
		        },
				{
					field : 'custVisitAction',
					title : '准客户拜访动作名称',
					width : 50,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.custVisitAction;
					} // 需要formatter一下才能显示正确的数据
				},
		        {
					field : 'nextVisitTime',
					title : '下次拜访时间',
					width : 150,
					editor: {
						type:'datetimebox',
						options:{
							required:false,
							editable:false,
							formatter:function(date){
								/*if(date>new Date()){
									return new Date().format("yyyy-MM-dd hh:mm:ss");
								}else{*/
									return new Date(date).format("yyyy-MM-dd hh:mm:ss");
									
								}
							}
						
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.nextVisitTime;
					} // 需要formatter一下才能显示正确的数据
				}
				]],
		onLoadSuccess : function() {
			$('#modifyCustomerVisitTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow : function(index) {
//			alert(index);
			modifyCustomerVisitTableEditOneRow(index);
			if(modifyCustomerVisitTable.datagrid('getRows')[modifyCustomerVisitTableRowIndex]!=undefined){
				var custVisitActionValues = modifyCustomerVisitTable.datagrid('getRows')[modifyCustomerVisitTableRowIndex]['custVisitAction'];	
//				alert(custVisitActionValues);
				var ed = modifyCustomerVisitTable.datagrid('getEditor', {index:modifyCustomerVisitTableRowIndex,field:'custVisitActionValue'});
				if(custVisitActionValues!=null&&custVisitActionValues!=""&&custVisitActionValues!=undefined){
					var actionValueArray = custVisitActionValues.split(",");
					$(ed.target).combobox("setValues",actionValueArray);
				}
			}
			
			
		},
		toolbar:"#modifyCustomerVisitTable_tb"
	});	
}

/** ******************************准客户拜访信息增/删/编辑************************************ */
var modifyCustomerVisitTableRowIndex;
//增加一行
function modifyCustomerVisitTableAddOneRow(){
	modifyCustomerVisitTableRowIndex = addOneRow(modifyCustomerVisitTable,modifyCustomerVisitTableRowIndex);
}
//删除一行
function modifyCustomerVisitTableRemoveOneRow(){
	removeOneRow(modifyCustomerVisitTable,modifyCustomerVisitTableRowIndex);
	modifyCustomerVisitTableRowIndex= null;
}
//编辑指定行
function modifyCustomerVisitTableEditOneRow(index){
	if(editOneRow(modifyCustomerVisitTable,modifyCustomerVisitTableRowIndex,index)){
		modifyCustomerVisitTableRowIndex = index;
	}
}

//锁定编辑行
function modifyCustomerVisitTableLockOneRow(){
	if(lockOneRow(modifyCustomerVisitTable,modifyCustomerVisitTableRowIndex)){
		modifyCustomerVisitTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
//客户拜访记录赋值
function getModifyCustomerVisitInfo(){
	$.ajax({
		type:'post',
		url:contextPath+"/modifyCustomer/getModifyCustomerVisitInfoUrl",
		data:{param:modifyCustVisit_BaseInfoId},
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if(resultObj.custVisitInfoMapList!=null&&resultObj.custVisitInfoMapList!=undefined&&resultObj.custVisitInfoMapList!=""){
						loadJsonObjData("modifyCustomerVisitTable",resultObj.custVisitInfoMapList);				
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		
		});
	
}
/**
 * 提交客户拜访信息
 */
function submitAllCustomerVisitInfo(){
	var param={};
	//判断添加的信息是否有误
	if(!modifyCustomerVisitTableLockOneRow()){
		$.messager.alert('提示', "客户拜访信息输入有误！", 'info');
		return;
	}
	param.custVisitTable = $.toJSON($("#modifyCustomerVisitTable").datagrid("getRows"));
	param.custBaseInfoId = modifyCustVisit_BaseInfoId;
	param.agentId = modifyCustVisit_agentId;
	var data = $.toJSON(param);
	data = escape(encodeURIComponent(data)); 
	$.ajax({
		type : 'post',
		url : contextPath + "/modifyCustomer/submitAllCustomerVisitInfoUrl",
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
/*******************点击"返回"按钮触发事件函数************************************/
function modifyCustomerVisitInfoBack(){
	$('#addcustomerWindow').window('destroy');
	//parent.clearcustomerBasicInfoForm();
	clearForm();
	queryCustomerList();
}