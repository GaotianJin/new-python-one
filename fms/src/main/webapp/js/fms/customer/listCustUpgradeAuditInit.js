jQuery(function($) {
	initListAuditCustomerTable();
	initListAuditCustomerCombobox();
});


var customerAuditTable ;
function initListAuditCustomerTable(){
	var  selectIndex = -1;
	customerAuditTable = $('#listCustUpgradeAudit_CustomerTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/customer/queryCustUpgradeAuditList",
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
						var param = {};
						param.businessNo = row.custBaseInfoId;
						param.businessType = "15";
						param.operateFlag = "queryFile";
						return "<a href='#'  onclick=addcustomeWindow('客户审核信息','"+contextPath+"/fileUpload/custUpgradeAuditUrl?param="+$.toJSON(param)+"')>"+row.customerNo+"</a>";
					} 
				},
				{
					field : 'chnName',
					title : '中文姓名',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						var param = {};
						param.businessNo = row.custBaseInfoId;
						param.businessType = "15";
						param.operateFlag = "queryFile";
						return "<a href='#'  onclick=addcustomeWindow('客户审核信息','"+contextPath+"/fileUpload/custUpgradeAuditUrl?param="+$.toJSON(param)+"')>"+row.chnName+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'sex',
					title : '性别',
					hidden: true,
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
				}/*,
		           {field:'action',title:'操作',width:100,sortable : true,formatter:function(value,row,index){
		        		   return "<a class='RcButton'  href='javascript:updateCustInfoButton("+index+");'>更新信息</a>";
		           }}*/]],
		toolbar : [{
					text : '升级审核',
					iconCls : 'icon-add',
					handler : function() {
						var rows = customerAuditTable.datagrid('getSelections');
			   			if (rows.length == 0) {
			   				$.messager.alert('提示',"请选择一个客户进行升级审核！");
							return;
						}
			   			if (rows[0].investAuditStateCode == '03') {
			   				$.messager.alert('提示',"审核通过的客户不可继续审核！");
							return;
						}
			   			var param = {};
						param.businessNo = rows[0].custBaseInfoId;
						param.businessType = "15";
						addFileWindow('客户升级审核', contextPath+"/fileUpload/custUpgradeAuditUrl?param="+$.toJSON(param));
					}
				}],
		onLoadSuccess : function() {
			$('#listCustUpgradeAudit_CustomerTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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


function initListAuditCustomerCombobox(){
	//初始化理财经理
	$("#listCustUpgradeAudit_AgentId").combobox({
		url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var param={};
			/*var storeId = $("#lisCust_StoreId").combobox("getValue");*/
			var departmentId = $("#listCustUpgradeAudit_DepartmentId").combobox("getValue");
			/*param.storeId = storeId;*/
			param.departmentId = departmentId;
			if(/*(storeId==null||storeId==""||storeId==undefined)&&*/departmentId==null||departmentId==""||departmentId==undefined){
				var comId = $("#listCustUpgradeAudit_comId").combobox("getValue");
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

function addcustomeWindow(title, href) 
{
	$('<div id="addFileWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		fit : true, 
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
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
function queryCustomerAuditList() {
	customerAuditTable.datagrid('options').url = contextPath+"/customer/queryCustUpgradeAuditList";
	var queryParam = $("#listCustUpgradeAudit_queryConditionForm").serialize();
	queryParam = formDataToJsonStr(queryParam);
	customerAuditTable.datagrid('load',{queryParam:queryParam});	
}


// 清空查询条件然后进行查询
function clearAuditForm() {
	$('#listCustUpgradeAudit_queryConditionForm').form('clear');
	queryCustomerAuditList();
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