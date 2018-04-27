jQuery(function($) 
{
	initSearchCombobox();
	initSearchCustListTable();
	var jsonData = [{},{},{},{},{}];
	searchCustListTable.datagrid('loadData',jsonData);
});

//初始化查询界面下拉框
function initSearchCombobox(){
	$("#search_idtype").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=idType'
	});
}

var searchCustListTable;
function initSearchCustListTable(){
	searchCustListTable = $('#search_custListTable').datagrid({
		//title : '客户列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		//collapsible : true,// 可折叠
		//url : "customer/searchCustomerInfo", // 数据来源
		//sortName : 'id', // 排序的列
		//sortOrder : 'desc', // 倒序
		//remoteSort : true, // 服务器端排序
		idField : 'custBaseInfoId', // 主键字段
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
					field : 'custBaseInfoId',
					title : '客户流水号',
					hidden : true,
					//sortable : true,
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
					//sortable : true,
					formatter : function(value, row, index) {
						return row.firstName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'idType',
					title : '证件类型编码',
					hidden : true,
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
				},{
					field : 'sex',
					title : '性别编码',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.sex;
					}
				},
				{
					field : 'sexName',
					title : '性别',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.sexName;
					}
				},
				{
					field : 'birthday',
					title : '出生日期',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.birthday;
					}
				},
				{
					field : 'age',
					title : '年龄',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.age;
					}
				},
				{
					field : 'idValidityDate',
					title : '证件有效期',
					//hidden : true,
					width : 100,
					formatter : function(value, row, index) {
						return row.idValidityDate;
					}
				},
				{
					field : 'nativePlace',
					title : '国籍',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.nativePlace;
					}
				},
				{
					field : 'drivingLicense',
					title : '是否有驾照',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.drivingLicense;
					}
				},
				{
					field : 'belongAgentName',
					title : '所属财富顾问',
					width : 100,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.belongAgentName;
					}
				}]],
		
		onLoadSuccess : function() {
			$('#search_custListTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
}

/**
 * 查找客户信息
 * 
 */
function searchCustomerInfo(){
	/*var formData = $("#searchCust_SearchConditionForm").serialize();
	//console.info(formData);
	formData = formDataToJsonStr(formData);
	//console.info(formData);*/
	
	 /*var queryParams = searchCustListTable.datagrid('options').queryParams;  

     queryParams.searchParam = formData;  

     searchCustListTable.datagrid('options').queryParams=queryParams;        

     searchCustListTable.datagrid('reload'); 

	*/
	
	/*$.ajax({
		type:'post',
		url:'customer/searchCustomerInfo',
		data:'searchParam='+formData,
		cache:false,
		success:function(returnData){
			//console.info(returnData);
			try {
				searchCustListTable.datagrid("loadData",returnData.rows);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});*/
	
	/*var url = searchCustListTable.datagrid('options').url;
	//console.info("1111111111111111");
	//console.info(url);
	//console.info("2222222222222222222")*/
	//设置查询的URL
	searchCustListTable.datagrid('options').url = "customer/searchCustomerInfo";
	var searchParam = $("#searchCust_SearchConditionForm").serialize();
	searchParam = formDataToJsonStr(searchParam);
	var param = eval("("+searchParam+")");
	////console.info(param);
	var chnName = param.chnName;
	var idNo = param.idNo;
	if((chnName==null||chnName==""||chnName==undefined)&&(idNo==null||idNo==""||idNo==undefined)){
		$.messager.alert('提示', "客户姓名和证件号码必须录入其中一项");
		return;
	}
	searchCustListTable.datagrid('load',{searchParam:searchParam});	
}

/**
 * 返回
 * 
 * */
function backCustomerInfoPage(){
	var custBaseInfoRowData = searchCustListTable.datagrid('getSelections');
	if(custBaseInfoRowData==null||custBaseInfoRowData==""||custBaseInfoRowData==undefined||custBaseInfoRowData.length==0){
		$.messager.alert('提示', "请选中一条客户信息再点击确定按钮返回");
		return;
	}else{
		//console.info(custBaseInfoRowData[0])
		searchCustomerInfoBack(custBaseInfoRowData[0]);
	}
}
