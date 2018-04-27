var companyAmountInfoTableEditIndex = null;
var operate = null;
jQuery(function($) {
	operate = $("#addPdAmountDis_operate").val();
	initAllCombobox();
	initCompanyAmountInfoTable();
	if(operate=="addPdAmountDis"){
		$("#addPdAmountDis_expectOpenDayName").hide(); 
		$("#addPdAmountDis_expectOpenDayText").hide(); 
		$("#addPdAmountDis_foundDateName").hide(); 
		$("#addPdAmountDis_foundDate").hide(); 
	}else if(operate=="modifyPdAmountDis"||operate=="detailPdAmountDis"){
		// 修改额度分配时，合作机构与产品名称不可修改；为防止用户误点，则需将下拉框置灰
		// [合作机构]与[产品名称]下拉框先置灰，再赋值；若先赋值，再置灰则下拉框值丢失
		$("#addPdAmountDis_productId").combobox({
			disabled: true
		});
		$("#addPdAmountDis_agencyComId").combobox({
			disabled: true
		});
		var productType = $("#addPdAmountDis_productType1").val();
		var productSubType = $("#addPdAmountDis_productSubType1").val();
		var productId = $("#addPdAmountDis_productId1").val();
		var agencyComId = $("#addPdAmountDis_agencyComId1").val();
		var expectOpenDay = $("#addPdAmountDis_expectOpenDay1").val();
		$("#addPdAmountDis_productId").combobox("setValue",productId);
		$("#addPdAmountDis_agencyComId").combobox("setValue",agencyComId);
		
		//固定收益类
		if(productType=="1"&&productSubType=="02"){
			$("#addPdAmountDis_foundDateName").show(); 
			$("#addPdAmountDis_foundDate").show();
			$("#addPdAmountDis_expectOpenDayName").hide(); 
			$("#addPdAmountDis_expectOpenDayText").hide(); 
			$("#addPdAmountDis_foundDate").combobox({disabled : true});
			$("#addPdAmountDis_foundDate").combobox("setValue",expectOpenDay);
		}
		//浮动收益类
		else if(productType=="1"&&(productSubType=="01"||productSubType=="03")){
			$("#addPdAmountDis_foundDateName").hide(); 
			$("#addPdAmountDis_foundDate").hide();
			$("#addPdAmountDis_expectOpenDayName").show(); 
			$("#addPdAmountDis_expectOpenDayText").show();
			// "开放日"下拉框禁用
			$("#addPdAmountDis_expectOpenDay").combobox({disabled : true});
			$("#addPdAmountDis_expectOpenDay").combobox("setValue",expectOpenDay);
		}
		getPdAmountDisInfo();
		getProductAmountDisAndOrderInfo();
		if(operate=="detailPdAmountDis"){
			$("#addPdAmountDis_productId").combobox("disable");
			$("#addPdAmountDis_agencyComId").combobox("disable");
			$("#addPdAmountDis_expectOpenDay").combobox("disable");
			$("#addPdAmountDis_submitButton").hide();
			$("#companyAmountInfoTable_tb").css("display", "none");
		}
	}
	
});

function initAllCombobox(){
	$("#addPdAmountDis_productId").combobox({
		url : contextPath + '/codeQuery/productwealthQuery?codeType='+"",
		valueField : 'code',
		textField : 'codeName',
		onSelect:function(record){
			getProductInfo(record.code);
			$("#addPdAmountDis_expectOpenDay").combobox("clear");
				//getAllComInfo();
		}
	});
	
	$("#addPdAmountDis_agencyComId").combobox({
		url : contextPath + '/codeQuery/agencyQuery',
		valueField : 'code',
		textField : 'codeName',
		onSelect:function(record){
			//console.info("record.code=="+record.code);
			var productUrl = contextPath + '/codeQuery/productwealthQuery?codeType='+record.code;
			$("#addPdAmountDis_productId").combobox("clear");
			$("#addPdAmountDis_productId").combobox("reload",productUrl);
		}
	});
	
	$("#addPdAmountDis_expectOpenDay").combobox({
		//url : contextPath + '/codeQuery/agencyQuery',
		//url :  contextPath+'/trade/queryOpenDateList?productId='+productId;
		required : true,
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		onSelect:function(record){
			//console.info("record.code=="+record.code);
			/*var contextPath+'/trade/queryOpenDateList?productId='+productId;
			$("#addPdAmountDis_productId").combobox("clear");
			$("#addPdAmountDis_productId").combobox("reload",productUrl);*/			
			getAllComInfo();
			getProductAmountDisAndOrderInfo();
		}
	});
	
	
}

var companyAmountInfoTable;
function initCompanyAmountInfoTable(){
	companyAmountInfoTable = $('#companyAmountInfoTable').datagrid({
		title : '分公司额度设置', // 标题
		method : 'post',
		singleSelect : true, // 单选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : false, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'pdAmountDisInfoId',
					title : '产品额度分配流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.pdAmountDisInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				/*{
					field : 'agencyComId',
					title : '产品方',
					width : 100,
					editor:{
						type:"combobox",
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url : contextPath + '/codeQuery/agencyQuery',
							onSelect:function(record){
								companyAmountInfoTable.datagrid('getRows')[companyAmountInfoTableEditIndex]['agencyComName'] = record.codeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.agencyComName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'agencyComName',
					title : '产品方',
					hidden:true,
					formatter : function(value, row, index) {
						return row.agencyComName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productId',
					title : '产品名称',
					width : 100,
					//sortable : true,
					//editor: 'text',
					editor:{
						type:"combobox",
						options:{
							required:true,
							url : contextPath + '/codeQuery/productwealthQuery?codeType='+"",
							valueField : 'code',
							textField : 'codeName',
							onSelect:function(record){
								companyAmountInfoTable.datagrid('getRows')[companyAmountInfoTableEditIndex]['productName'] = record.codeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'productName',
					title : '产品名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.productName;
					} // 需要formatter一下才能显示正确的数据
				}, */
				{
					field : 'comId',
					title : '分公司',
					width: 100,
					editor:{
						type:"combobox",
						options:{
							required:true,
							url : contextPath + '/codeQuery/comQuery',
							valueField : 'comId',
							textField : 'comName',
							onSelect:function(record){
								companyAmountInfoTable.datagrid('getRows')[companyAmountInfoTableEditIndex]['comName'] = record.comName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.comName;
					}
				},
				{
					field : 'comName',
					title : '分公司',
					hidden: true,
					formatter : function(value, row, index) {
						return row.comName;
					}
				},
				{
					field : 'amount',
					title : '额度(元)',
					width : 100,
					//editor: 'text',
					editor: {
						type:'numberbox',
						options:{
							 required:true,
							 min:0,    
							 precision:2,
							 onChange: function(newValue,oldValue){
									$(this).numberbox("setValue",newValue);
									if(newValue!=null&&newValue!=""&&newValue!=undefined&&companyAmountInfoTableEditIndex!=null){
										var rows = companyAmountInfoTable.datagrid("getRows");
										var allNewValue=0;
										for ( var int = 0; int < rows.length; int++) {
											var amountValue = 0;
											//如果为这一行
											if(int==companyAmountInfoTableEditIndex){
												amountValue = newValue;
											}else{
												amountValue=rows[int].amount;
											}
											//如果不为空，
											if(amountValue==""||amountValue==undefined||amountValue==null){
												amountValue=0;
											}
											allNewValue += parseFloat(amountValue);
										}
										$("#disTotalAmount").val(allNewValue);//产品额度分配总额
										var orderTotalAmount = $("#orderTotalAmount").val();//产品已预约总额
										if(orderTotalAmount==null||orderTotalAmount==""||orderTotalAmount==undefined){
											orderTotalAmount = 0;
										}
										$("#remainTotalAmount").val(allNewValue-orderTotalAmount);//剩余额度总和
									}
								}
						}
					},
					formatter : function(value, row, index) {
						return row.amount;
					}
				},
				{
					field : 'remainAmount',
					title : '剩余额度(元)',
					width : 100,
					formatter : function(value, row, index) {
						return row.remainAmount;
					}
				},
				{
					field : 'orderTotalAmount',
					title : '已预约总额(元)',
					width : 100,
					//hidden : true,
					formatter : function(value, row, index) {
						return row.orderTotalAmount;
					}
				}]],
		onLoadSuccess : function() {
			$('#companyAmountInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			companyAmountInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#companyAmountInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function companyAmountInfoTableAddOneRow(){
	////console.info(companyAmountInfoTable.toolbar);
	companyAmountInfoTableEditIndex = addOneRow(companyAmountInfoTable,companyAmountInfoTableEditIndex);
}
//删除一行
function companyAmountInfoTableRemoveOneRow(){
	removeOneRow(companyAmountInfoTable,companyAmountInfoTableEditIndex);
	companyAmountInfoTableEditIndex = null;
}
//编辑指定行
function companyAmountInfoTableeditOneRow(index){
	if(editOneRow(companyAmountInfoTable,companyAmountInfoTableEditIndex,index)){
		companyAmountInfoTableEditIndex = index;
	}
}
//锁定编辑行
function companyAmountInfoTableLockOneRow(){
	if(lockOneRow(companyAmountInfoTable,companyAmountInfoTableEditIndex)){
		companyAmountInfoTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
}

/*********************************************************************/


/**
 * 获取所有分公司信息，初始化到表格 
 * 
 */
function getAllComInfo(){
	var param = {};
	var productId = $("#addPdAmountDis_productId").combobox("getValue");
	if(productId==null||productId==""||productId==undefined){
		$.messager.alert('提示', "未获取到产品信息");
		return;
	}
	param.productId = productId;
	$.ajax({
		type:'post',
		url:contextPath+"/product/getAllComInfo",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var jsonObj = reData.obj;
					if(jsonObj!=null&&jsonObj!=""&&jsonObj!=undefined){
						clearAllRows(companyAmountInfoTable);
						loadJsonObjData("companyAmountInfoTable",jsonObj);
						companyAmountInfoTableEditIndex = null;
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 获取产品信息
 * 
 */
function getProductInfo(productId){
	$.ajax({
		type:'post',
		url:contextPath+"/product/getProductInfo",
		data:'productId='+productId,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var productInfo = reData.obj.productInfo;
					var pdWealthInfo = reData.obj.pdWealthInfo;
					if(productInfo!=null){
						var productType = productInfo.productType;
						var productSubType = productInfo.productSubType;
						var foundDate = pdWealthInfo.foundDate;
						if(productType=='1'&&(productSubType=='01'||productSubType=='02')){
							document.getElementById("addPdAmountDis_expectOpenDayText").value="";
							//浮动收益类和股权类隐藏
							$("#addPdAmountDis_expectOpenDayName").hide(); 
							$("#addPdAmountDis_expectOpenDayText").hide(); 
							//固定收益类显示
							$("#addPdAmountDis_foundDateName").show(); 
							$("#addPdAmountDis_foundDate").show(); 
							$("#addPdAmountDis_foundDate").val(foundDate);
							$('#addPdAmountDis_foundDate').attr('readonly',"true");
							getAllComInfo();
							getProductAmountDisAndOrderInfo();
						}else if(productType=='1'&&productSubType=='03'){
							//固定收益类信息隐藏
							document.getElementById("addPdAmountDis_foundDate").value="";
							$("#addPdAmountDis_foundDateName").hide();
							$("#addPdAmountDis_foundDate").hide(); 
							//浮动收益类和股权类显示
							$("#addPdAmountDis_expectOpenDayName").show(); 
							$("#addPdAmountDis_expectOpenDayText").show();
							var openDateUrl = contextPath+'/trade/queryOpenDateList?productId='+productId;
							$("#addPdAmountDis_expectOpenDay").combobox("reload",openDateUrl);
						}
					}else{
						$.messager.alert('提示', "未获取到产品相关信息");
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


/**
 * 校验数据
 * 
 */
function checkData(){
	var companyAmountInfoTableInfoRows = companyAmountInfoTable.datagrid("getRows");
	if(companyAmountInfoTableInfoRows==null||companyAmountInfoTableInfoRows.length==0){
		$.messager.alert('提示', "请填写分公司额度分配信息！", 'info');
		return false;
	}
	if(companyAmountInfoTableInfoRows.length>0){
		//地址信息验证
		if(!companyAmountInfoTableLockOneRow()){
			$.messager.alert('提示', "分公司额度分配信息输入有误！", 'info');
			return false;
		}
	}
	//循环判断额度是否未填写
	var flag = true;
	var amountSignTag = true; // 额度分配值是否为小于等于0的值
	for(var i=0;i<companyAmountInfoTableInfoRows.length;i++){
		var oneRowData = companyAmountInfoTableInfoRows[i];
		var amount = oneRowData.amount;
		if(amount==null||amount==""||amount==undefined){
			flag = false;
			} else if ( amount <= 0) {
			amountSignTag = false;
		}
	}
	if(!flag){
		$.messager.alert('提示', "请填写分公司分配的额度信息！", 'info');
		return false;
	}
	if(!amountSignTag) {
		$.messager.alert('提示',"请填写大于0的额度信息!", 'info');
		return false;
	}
	//校验同一个分公司是否有重复的记录
	var repeatCount = 0;
	//校验修改时是否存在产品分配额度小于已预约额度的记录
	var amountLessThanOrderTotalAmountCount = 0;
	var comName = "";
	for(var i=0;i<companyAmountInfoTableInfoRows.length;i++){
		var rowData = companyAmountInfoTableInfoRows[i];
		var comId = rowData.comId;
		for(var j=i+1;j<companyAmountInfoTableInfoRows.length;j++){
			var innerRowData = companyAmountInfoTableInfoRows[j];
			var innerComId = innerRowData.comId;
			if(comId==innerComId){
				repeatCount++;
				comName = innerRowData.comName;
			}
		}
	}
	if(repeatCount>0){
		$.messager.alert('提示', comName+"额度重复分配，请删除重复记录！", 'info');
		return false;
	}
	//校验修改时是否存在产品分配额度小于已预约额度的记录
	var amountLessThanOrderTotalAmountCount = 0;
	var comName1 = "";
	for(var i=0;i<companyAmountInfoTableInfoRows.length;i++){
		var rowData = companyAmountInfoTableInfoRows[i];
		var amount = rowData.amount;
		var orderTotalAmount = rowData.orderTotalAmount;
		if(parseInt(amount)<parseInt(orderTotalAmount)){
			amountLessThanOrderTotalAmountCount++;
			comName1 = rowData.comName;
		}
	}
	if(amountLessThanOrderTotalAmountCount>0){
		$.messager.alert('提示', comName1+"额度少于已预约额度，请重新设置额度！", 'info');
		return false;
	}
	//校验产品分配总额大于产品融资规模的情况
	//融资规模
	var financingScale = $("#financingScale").val();
	//分配总额
	var disTotalAmount = $("#disTotalAmount").val();
	if(financingScale==null||financingScale==""||financingScale==undefined){
		financingScale = 0;
	}
	if(disTotalAmount==null||disTotalAmount==""||disTotalAmount==undefined){
		disTotalAmount = 0;
	}
	if(parseInt(disTotalAmount)>parseInt(financingScale)){
		$.messager.alert('提示', "分配总额已超过融资规模，请重新调整额度分配！", 'info');
		return false;
	}
	return true;
}


function saveProductAmountDisInfo(){
	if(!checkData()){
		return;
	}
	//获取form数据
	var formData = $("#addProductAmountDisInfoForm").serialize();
	formData = formDataToJsonStr(formData);
	//提交参数
	var param = {};
	var companyAmountInfoTableInfoRows = companyAmountInfoTable.datagrid("getRows");
	param.pdAmountDisInfo = $.toJSON(companyAmountInfoTableInfoRows);
	param.productInfo =  formData;
	param.operate = operate;
	$('#addPdAmountDis_submitButton').linkbutton('disable');
	$.ajax({
		type:'post',
		url:contextPath+"/product/saveProductAmountDisInfo",
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', "保存成功");
					getPdAmountDisInfo();
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
			$('#addPdAmountDis_submitButton').linkbutton('enable');


		}
	});
}


function getPdAmountDisInfo(){

	var formData = $("#addProductAmountDisInfoForm").serialize();;
	formData = formDataToJsonStr(formData);
	
	$.ajax({
		type:'post',
		url:contextPath+"/product/getPdAmountDisInfo",
		data:'param='+formData,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var jsonObj = reData.obj;
					if(jsonObj!=null&&jsonObj!=""&&jsonObj!=undefined){
						clearAllRows(companyAmountInfoTable);
						loadJsonObjData("companyAmountInfoTable",jsonObj);
						companyAmountInfoTableEditIndex = null;
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function getProductAmountDisAndOrderInfo(){
	var formData = $("#addProductAmountDisInfoForm").serialize();
	formData = formDataToJsonStr(formData);
	$.ajax({
		type:'post',
		url:contextPath+"/product/getProductAmountDisAndOrderInfo",
		data:'param='+formData,
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var jsonObj = reData.obj;
					if(jsonObj!=null&&jsonObj!=""&&jsonObj!=undefined){
						setInputValueById("pdAmountTotalInfo",jsonObj);
					}
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function backProductDisListPage(){
	$('#productAmountDisWindow').window('destroy');
	/*if(operate!=null&&operate!=""&&operate!=undefined&&operate!="agentDetail"){
		queryAgentList() ;
	}*/
}
