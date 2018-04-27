//每个可编辑表格需要两个全局变量
//这两个全局变量尤为重要，每个可编辑表格都要两个，同一个页面中，有多个可编辑表格的的，不能重复，从此页面弹出的窗口页面中也不能有同名的全局变量
var storeLeaseMoneyInfoTableEditIndex = null ;
$(function(){
	initAllStorebobox();
	
	initStoreLeaseMoneyInfoTable();
	
	var rows = $('#list_storeTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		$.messager.alert('提示',"请选择要更新的网点");
		return;
	} 
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			if (i == 0)
				ps += "?storeId=" + n.storeId;
			else
				ps += "&storeId=" + n.storeId;
		});
		$.post(contextPath+'/branch/updateStoreInitUrl' + ps, function(data) {
			
			   var cityUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+data.defStore.province;
			   $("#update_city").combobox('reload',cityUrl);
			   var countryUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+data.defStore.city;
			   $("#update_country").combobox('reload',countryUrl);
			   setInputValueById("update_storeBasicInfoDiv",data.defStore);
			   setInputValueById("update_storeBelongInfoDiv",data.storeBelongComInfo);
			   if(data.storeLeaseMoneyList!=null&&data.storeLeaseMoneyList!=undefined){
					clearAllRows(storeLeaseMoneyInfoTable);
					loadJsonObjData("storeLeaseMoneyInfoTable",data.storeLeaseMoneyList);
					storeLeaseMoneyInfoTableEditIndex = null;
				}
		});
	};
	
});



function initAllStorebobox()
{	
	var rows = $('#list_storeTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		$.messager.alert('提示',"请选择要更新的网点");
		return;
	} 
	else 
	{
		var ps = "";
		$.each(rows, function(i, n) {
			ps = n.storeId;
		});
		$("#update_storeId").val(ps);
	}
	
	
	$('#update_buildingId').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name',
		  required:true
	  });
	
	$("#update_type").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=storeType',
		valueField:'code',
		textField:'codeName',
		value:'01',
		required:true
	});
	
	$("#update_state").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=storeState',
		valueField:'code',
		textField:'codeName',
		value:'01',
		required:true
	});
	
	//省
	$("#update_province").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#update_province").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#update_city").combobox('reload',url);
		}
	});
	//市
	$("#update_city").combobox({
		//url:contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode=',
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#update_city").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#update_country").combobox('reload',url);
		}
	});
	//区
	$("#update_country").combobox({
		//url:contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode=''',
		valueField:'placeCode',
		textField:'placeName'
	});
	
	$('#update_buildingId').combobox({
        url:contextPath+'/branch/queryBuildListCode',
        valueField:'id',
        textField:'name'
    });
	$('#update_comId').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name',
		  required:true
	  })
	  
	  $('#update_contractCondition').combobox({
	        url:contextPath+'/codeQuery/tdCodeQuery?codeType=contractStatus',
	        valueField:'code',
			textField:'codeName'
	  });
}


var storeLeaseMoneyInfoTable;
function initStoreLeaseMoneyInfoTable(){
	storeLeaseMoneyInfoTable = $("#storeLeaseMoneyInfoTable").datagrid({
		method : 'post',
		////iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		//collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		//pageList:[5,10,15,20],
		//pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'storeLeaseInfoId',
					title : '网点租金信息流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.storeLeaseInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'leaseYear',
					title : '租赁年度（第n年）',
					width : 100,
					//sortable : true,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:0,
							max:100
						}
					},
					formatter : function(value, row, index) {
						return row.leaseYear;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'leaseMoney',
					title : '年度租金（元）',
					width : 100,
					editor: {
						type:'numberbox',
						options:{
							required:true,
							min:0,
							precision:2   
						}
					},
					formatter : function(value, row, index) {
						return row.leaseMoney;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'payStatusRemark',
					title : '付款情况备注',
					width : 300,
					editor: {
						type:'validatebox',
						options:{
							validType:"length[0,200]"
						}
					},
					formatter : function(value, row, index) {
						return row.payStatusRemark;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#storeLeaseMoneyInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			storeLeaseMoneyInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#storeLeaseMoneyInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function storeLeaseMoneyInfoTableAddOneRow(){
	storeLeaseMoneyInfoTableEditIndex = addOneRow(storeLeaseMoneyInfoTable,storeLeaseMoneyInfoTableEditIndex);
}
//删除一行
function storeLeaseMoneyInfoTableRemoveOneRow(){
	removeOneRow(storeLeaseMoneyInfoTable,storeLeaseMoneyInfoTableEditIndex);
	storeLeaseMoneyInfoTableEditIndex = null;
}
//编辑指定行
function storeLeaseMoneyInfoTableeditOneRow(index){
	if(editOneRow(storeLeaseMoneyInfoTable,storeLeaseMoneyInfoTableEditIndex,index)){
		storeLeaseMoneyInfoTableEditIndex = index;
	}
	
}
//锁定编辑行
function storeLeaseMoneyInfoTableLockOneRow(){
	if(lockOneRow(storeLeaseMoneyInfoTable,storeLeaseMoneyInfoTableEditIndex)){
		storeLeaseMoneyInfoTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
	
}
/*******************************************************************/


/**
 * 更新机构信息，表单和gridTable序列化
 */
function updateStoreInfo()
{
	var param = {};
	var StoreBaisicInfoFormDataJson = formDataToJsonStr($("#update_StoreInfoForm").serialize());
	//网点租金信息列表
	var storeLeaseMoneyInfoRows = storeLeaseMoneyInfoTable.datagrid("getRows");
	//网点归属机构信息
	var storeBelongComInfo =  formDataToJsonStr($("#update_StoreBelongComInfoForm").serialize());  
	
	param.storeInfo = StoreBaisicInfoFormDataJson;
	param.storeLeaseMoneyInfo = storeLeaseMoneyInfoRows;
	param.storeBelongComInfo = storeBelongComInfo;
	
	
	if(!beforeSubmit())
	{
		return;
	}
	$('#updateStoreInfo').linkbutton('disable');
	$.ajax({
		type:'post',
		url:'branch/saveAddStoreUrl',
		//data:'customerInfo='+$.toJSON(customerInfo),
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(resultInfo){
			try {
				var jsonObj = resultInfo.obj;
				if(resultInfo.success){
					//$.messager.alert('提示', resultInfo.msg);
					$.messager.confirm('提示',  '确定要修改吗?', function(result){
						if (result){
						    // 退出操作;
							$('#storeWindow').window('destroy');
							parent.clearStoreForm();
						}
					});
					setInputValueById("update_storeBasicInfoDiv",jsonObj.storeInfo);
					setInputValueById("update_storeBelongInfoDiv",jsonObj.storeBelongComInfo);
					//给网点租金列表信息赋值
					if(jsonObj.storeLeaseMoneyList!=null&&jsonObj.storeLeaseMoneyList!=undefined){
						clearAllRows(storeLeaseMoneyInfoTable);
						loadJsonObjData("storeLeaseMoneyInfoTable",jsonObj.storeLeaseMoneyList);
						storeLeaseMoneyInfoTableEditIndex = null;
					}
					/*result.put("storeInfo", defStoreSchema);
					result.put("storeBelongComInfo", storeBelongComTrace);
					result.put("storeLeaseMoneyList", storeLeaseList);*/
					//getCustDetailInfo();
				}else{
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
			$('#updateStoreInfo').linkbutton('enable');
		}
	
	});
	
	/*$.messager.confirm('提示', '确定要修改吗?', function(result) {
		if (result) {
			$.post("branch/saveUpdateStoreUrl", 'param='+encodeURI($.toJSON(param)), function(data) {
				$.messager.alert('提示', data.msg, 'info');
				$.messager.progress('close');
				parent.clearForm();
			})
		}
	})*/
}

function beforeSubmit()
{
	//开业时间和停业时间
	var startDate = $("#update_startDate").datebox("getValue");
	var endDate = $("#update_endDate").datebox("getValue");
//	if((startDate==null || startDate=="" || startDate==undefined) && 
//			endDate!=null && endDate!="" && endDate!=undefined)
//	{
//		$('#update_startDate').datebox({required:true});
//	}	
//	else
//	{
//		$('#update_startDate').datebox({required:false});
//	}
	
	if(!$("#update_StoreInfoForm").form("validate"))
	{
		return false;
	}
	
	if(startDate!=null && startDate!="" && startDate!=undefined &&
			endDate!=null && endDate!="" && endDate!=undefined)
	{
		var d1 = new Date(startDate.replace(/\-/g, "\/")); 
		var d2 = new Date(endDate.replace(/\-/g, "\/")); 

		if(d1 >d2) 
		{
		  $.messager.alert('提示', "开业时间不能大于停业时间！", 'info');
		  return false; 
		}
	}
	
	var belongEndDate = $("#update_belongEndDate").datebox("getValue");
	if(belongEndDate!=null && belongEndDate!="" && belongEndDate!=undefined)
	{
		var belongStartDate = $("#update_belongStartDate").datebox("getValue");
		var d1 = new Date(belongStartDate.replace(/\-/g, "\/")); 
		var d2 = new Date(belongEndDate.replace(/\-/g, "\/")); 

		if(d1 >d2) 
		{
		  $.messager.alert('提示', "归属开始时间不能大于结束时间！", 'info');
		  return false; 
		}
	}
	
	var storeLeaseMoneyInfoRows = storeLeaseMoneyInfoTable.datagrid("getRows");
	if(storeLeaseMoneyInfoRows.length>0){
		//网点租金信息验证
		if(!storeLeaseMoneyInfoTableLockOneRow()){
			$.messager.alert('提示', "网点租金信息输入有误！", 'info');
			return false;
		}
	}
	
	return true;
}


//上传附件
function uploadStoreAttachInfo(){
	var storeId = $("#update_storeId").val();
	if(storeId==null||storeId==""||storeId==undefined){
		$.messager.alert('提示', "请先保存网点信息！", 'info');
		return;
	}
	var param = {};
	param.businessNo = storeId;
	param.businessType = "05";
	param.operate = "uploadFile";
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
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

function openUploadStoreImageWindow(){
	var storeId = $("#update_storeId").val();
	if(storeId==null||storeId==""||storeId==undefined){
		$.messager.alert('提示', "未获取到网点信息！");
		return;
	}
	var param = {};
	param.storeId = storeId;
	param.operate = "updStore";
	$('<div id="uploadStoreImageWindow"/>').dialog({
		href : contextPath+"/branch/uploadStoreImage?param="+$.toJSON(param),
		modal : true,
		title : "网点照片上传",
		width:700,
		height:550,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}

function backListStorePage(){
	$('#storeWindow').window('destroy');
	parent.clearStoreForm();
}