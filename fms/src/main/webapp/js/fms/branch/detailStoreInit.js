//每个可编辑表格需要两个全局变量
//这两个全局变量尤为重要，每个可编辑表格都要两个，同一个页面中，有多个可编辑表格的的，不能重复，从此页面弹出的窗口页面中也不能有同名的全局变量
$(function()
{	
	initAllStorebobox();
	
	initStoreLeaseMoneyInfoTable();
	
	var rows = $('#list_storeTable').datagrid('getSelections');
	if (rows.length == 0) 
	{
		alert("请选择要更新的网点");
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
			   $("#detail_city").combobox('reload',cityUrl);
			   
			   var countryUrl = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+data.defStore.city;
			   $("#detail_country").combobox('reload',countryUrl);
			   setInputValueById("detail_storeBasicInfoDiv",data.defStore);
			   setInputValueById("detail_storeBelongInfoDiv",data.defStore);
			   setInputValueById("detail_storeBelongInfoDiv",data.storeBelongComInfo);
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

	$('#detail_buildingId').combobox({
		  url:contextPath+"/branch/queryBuildListCode",
		  valueField:'id',
		  textField:'name'
	  });
	
	$("#detail_type").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=storeType',
		valueField:'code',
		textField:'codeName',
		value:'01'
	});
	
	$("#detail_state").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=storeState',
		valueField:'code',
		textField:'codeName',
		value:'01'
	});
	
	//省
	$("#detail_province").combobox({
		url:contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var provinceCode = $("#detail_province").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode='+provinceCode;
			$("#detail_city").combobox('reload',url);
		}
	});
	//市
	$("#detail_city").combobox({
		//url:contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode=',
		valueField:'placeCode',
		textField:'placeName',
		onSelect:function(){
			var cityCode = $("#detail_city").combobox('getValue');
			var url = contextPath+'/codeQuery/placeCodeQuery?placeType=03&upPlaceCode='+cityCode;
			$("#detail_country").combobox('reload',url);
		}
	});
	//区
	$("#detail_country").combobox({
		//url:contextPath+'/codeQuery/placeCodeQuery?placeType=02&upPlaceCode=''',
		valueField:'placeCode',
		textField:'placeName'
	});
	//归属机构
	$('#detail_comId').combobox({
		  url:contextPath+"/branch/queryComListCode",
		  valueField:'id',
		  textField:'name'
	  });
	
	 $('#detail_contractCondition').combobox({
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
		}
	});
}

//查看附件
function queryStoreAttachInfo(){
	var storeId = $("#detail_storeId").val();
	var param = {};
	param.businessNo = storeId;
	param.businessType = "05";
	param.operate = "queryFile";
	addFileWindow('附件查看', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
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
	var storeId = $("#detail_storeId").val();
	if(storeId==null||storeId==""||storeId==undefined){
		$.messager.alert('提示', "未获取到网点信息！");
		return;
	}
	var param = {};
	param.storeId = storeId;
	param.operate = "detailStore";
	$('<div id="uploadStoreImageWindow"/>').dialog({
		href : contextPath+"/branch/uploadStoreImage?param="+$.toJSON(param),
		modal : true,
		title : "网点照片查看",
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