var preCustBaseInfoId=null;
var preCustName = undefined;
var preCustMobile = undefined;
var preCustBuildingNo=null;

/**************************************************************************
 					函数执行区
***************************************************************************/
jQuery(function($) 
{
	//获取查询页面参数
	preCustBaseInfoId = $("#detailPreCustBaseInfoId").val();
	preCustName = $("#detailPreCusName").val();
	preCustMobile = $("#detailPreCustMobile").val();
	preCustBuildingNo = $("#detailPreCustBuildingNo").val();
	//console.info("页面传入的楼号："+preCustBuildingNo);
	//初始化四个下拉列表框
	initAllPreCustomerInfo();
	
	//初始化DataGrid（数据表格）
	initpreCustomerVistorTable();
	
	//初始化需要修改的数据
	updatePreCusomerInfo();
});

/*****************************************************************************************
									函数定义区
******************************************************************************************/
/******************************定义初始化下拉列表框函数************************************/
function initAllPreCustomerInfo(){
	//性别
	$("#detail_sexual").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=sex',// 通过URL加载远程列表数据。
		valueField : 'code',// 基础数据值名称绑定到该下拉列表框。
		textField : 'codeName'// 基础数据字段名称绑定到该下拉列表框。
	});
	//客户类型
	$("#detail_CustomerType").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=customerType',
		valueField : 'code',
		textField : 'codeName'
	});
	//客户居住楼盘
	$("#detail_CustomerProperty").combobox({
		url : contextPath+'/preCustomer/queryBuildListCode',
		valueField : 'code',
		textField : 'codeName'
	});
	//获取客户方式
	$("#detail_getCustomerType").combobox({
		url : 'codeQuery/tdCodeQuery?codeType=preCustObtailWay',
		valueField : 'code',
		textField : 'codeName'
	});	
}

/*******************定义初始化DataGrid（数据表格）函数************************/
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
						width : 100,
						sortable : true,
						editor: {
			        		type:'datebox',
			        		options:{
			        			required:true,
			        			validType:'validDate'
			        		}
			        	},
						formatter : function(value, row, index) {
							return row.preCustVisitTime;
						}
					},
					
			       {
		        	field : 'preCustVisitType',
		        	title : '拜访类型',
		        	width : 100,
		        	editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,
							editable:false, 
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=preCustVisitType',
							onSelect:function(){
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
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.preCustVisitTypeName;
					} // 需要formatter一下才能显示正确的数据
				},
				
		        {
					field : 'preCustVisitAction',
					title : '拜访动作',
					width : 100,
					editor:  {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							required:true,
							editable:false, 
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=preCustVisitAction',
							onSelect:function(){
								var preCustVisitAction = preCustomervistorTable.datagrid('getEditor', {index:preCustomervistorTableRowIndex,field:'preCustVisitAction'});
								var preCustVisitActionName = $(preCustVisitAction.target).combobox('getText');
								preCustomervistorTable.datagrid('getRows')[preCustomervistorTableRowIndex]['preCustVisitActionName'] = preCustVisitActionName;
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
					width : 100,
					sortable : true,
					hidden : true,
					formatter : function(value, row, index) {
						return row.preCustVisitActionName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
		        	field : 'preCustVisitContent',
		        	title : '拜访内容',
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
		        }
				]],
		onLoadSuccess : function() {
			$('#preCustomervistorTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}/*,
		onClickRow : function(index) {
			preCustomervistorTableEditOneRow(index);
		},*/
	});	
}

/*************************定义初始化需要修改数据函数*********************/
function updatePreCusomerInfo() {
	$.ajax({
		type:'post',
		url:contextPath+"/preCustomer/getPreCustomerInfo",
		data:{param:preCustBaseInfoId},
		cache:false,
		success:function(result){
//			console.info(result);
			try{
				if(result.success){
					var resultObj = result.obj;
					if (resultObj.preCustomerBaseInfo!=null && resultObj.preCustomerBaseInfo != undefined && resultObj.preCustomerBaseInfo !="") {
						// 设置服务器端获取的客户姓名值为页面传入值
						resultObj.preCustomerBaseInfo.preCustName = preCustName;
						// 设置"准客户手机号"下拉列表框值为页面传入值
						resultObj.preCustomerBaseInfo.preCustMobile = preCustMobile;
						// 将楼盘号赋值给页面
						resultObj.preCustomerBaseInfo.preCustBuildingNo=preCustBuildingNo;
						
						// 将所有值赋给表单中各个标签中以供显示
						setInputValueById("detail_PreCustomerForm",resultObj.preCustomerBaseInfo);
					}
					if(resultObj.preCustomerVistorInfoList!=null&&resultObj.preCustomerVistorInfoList!=undefined&&resultObj.preCustomerVistorInfoList!="") {
//						console.info(resultObj.preCustomerVistorInfoList);
						loadJsonObjData("preCustomervistorTable",resultObj.preCustomerVistorInfoList);				
					}
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		})
}

function backListPage(){
	$('#addcustomerWindow').window('destroy');
}

