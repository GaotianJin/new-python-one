var certificationEditRowIndex = null;
var agentFamilyInfoTableEditRowIndex = null;
var agentPositionInfoTableEditRowIndex = null;
var agentSalaryAccTableEditRowIndex = null;
/*var agentStoreInfoTableEditRowIndex = null;*/
var agentDepartInfoTableEditRowIndex = null;
var agentNurserInfoTableEditRowIndex = null;
var agentWorkInfoTableEditRowIndex = null;
var agentOtherInfoTableEditRowIndex = null;

var verifyUserCodeFlag = false;
var verifyAgentCodeFlag = false;
var operate = null;
var oldAgentCode = null;
var oldUserCode = null;
jQuery(function($) {
	operate = $("#addAgent_operate").val();
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="updateAgent"){
		verifyUserCodeFlag = true;
		verifyAgentCodeFlag = true;
	}
	
	
	$('#addAgent_birthday').datebox({ // 日期选择框
		required : true
	});
	
	
	$('#addAgent_sex').combobox({
		required : true,
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=sex",
		valueField : 'code',
		textField : 'codeName'
	});
	//证件类型
	$('#addAgent_idType').combobox({
		required : true,
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=idType",
		valueField : 'code',
		textField : 'codeName',
		onSelect:function(record){
			setIdNoAndSexDesabled();
			if(record.code=="1"){
				var idNo = $("#addAgent_idNo").val();
				if(idNo!=null&&idNo!=""&&idNo!=undefined){
					verifyIdNo();
				}
			}
		}
	});
	//民族
	$('#addAgent_nationality').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=ethnicity",
		valueField : 'code',
		textField : 'codeName'
	});

	
	//学历
	$('#addAgent_degree').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=degree",
		valueField : 'code',
		textField : 'codeName'
	});
	//婚姻状况
	$('#addAgent_maritalStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=marriage",
		valueField : 'code',
		textField : 'codeName'
	});
	//政治面貌
	$('#addAgent_political').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=policyType",
		valueField : 'code',
		textField : 'codeName'
	});
	//生育状况，数据待确定
	$('#addAgent_bearStatus').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=bearStatus",
		valueField : 'code',
		textField : 'codeName'
	});
	//省
	$("#addAgent_province").combobox({
		url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName',
		onSelect:function(){
			var upPlaceCode = $("#addAgent_province").combobox("getValue");
			var url = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+upPlaceCode;
			$("#addAgent_city").combobox("reset");
			$("#addAgent_country").combobox("reset");
			$("#addAgent_city").combobox("reload",url);
		}
	});
	$("#addAgent_city").combobox({
		//url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName',
		onSelect:function(){
			var upPlaceCode = $("#addAgent_city").combobox("getValue");
			var url = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+upPlaceCode;
			$("#addAgent_country").combobox("reset");
			$("#addAgent_country").combobox("reload",url);
		}
	});
	$("#addAgent_country").combobox({
		//url : contextPath+"/codeQuery/placeCodeQuery?placeType=01&upPlaceCode=",
		valueField : 'placeCode',
		textField : 'placeName'
	});
	
	//在职状态
	$('#addAgent_workState').combobox({
		required : true,
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=workState",
		valueField : 'code',
		textField : 'codeName'
	});
	
	$('#addAgent_agentType').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=agentType",
		valueField : 'code',
		textField : 'codeName'
	});
	
	//入司日期
	$('#addAgent_joinDate').datebox({
		required : true
	});
	
	//是否试用期
	$('#addAgent_isProbationPeriod').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=isProbationPeriod",
		valueField : 'code',
		textField : 'codeName'
	});
	//户籍性质
	$('#addAgent_householdNature').combobox({
		url : contextPath+"/codeQuery/tdCodeQuery?codeType=rgtType",
		valueField : 'code',
		textField : 'codeName'
	});
	
	initAgentFamilyInfoTable();
	initAgentSalaryAccTable();
	initAgentCertificationTable();
	initAgentPositionInfoTable();
	initAgentNurserInfoTable();
	initAgentWorkInfoTable();
/*	initAgentStoreInfoTable();*/
	initAgentDepartInfoTable();
	initAgentOtherInfoTable();
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="addAgent"){
		agentFamilyInfoTableAddOneRow();
		agentSalaryAccTableAddOneRow();
		agentCertificationTableAddOneRow();
		agentPositionInfoTableAddOneRow();
		agentNurserInfoTableAddOneRow();
		agentWorkInfoTableAddOneRow();
		/*agentStoreInfoTableAddOneRow();*/
		agentDepartInfoTableAddOneRow();
		agentOtherInfoTableAddOneRow();
	}else{
		 //$("#addAgent_userCode").attr({readonly: 'true' }); 
		 queryAgentInfo();
		 
	}
	
	//console.info("agentId:"+agentId+";operate:"+operate);
	//修改时需要查询财富顾问详细信息
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="agentDetail"){
		//$("#addAgent_agentId").val(agentId);
		//queryAgentInfo();
		$('#addAgent_agentCode').attr("disabled",true);
		$("#addAgent_userCode").attr("disabled",true); 
		//$("#addAgent_idNo").attr("disabled",true); 
		$("#agentFamilyInfoTable_tb").css("display", "none");  
		$("#agentSalaryAccTable_tb").css("display", "none"); 
		$("#agentCertificationTable_tb").css("display", "none"); 
		$("#agentPositionInfoTable_tb").css("display", "none"); 
		$("#agentNurserInfoTable_tb").css("display", "none"); 
		$("#agentWorkInfoTable_tb").css("display", "none"); 
		/*$("#agentStoreInfoTable_tb").css("display", "none");*/ 
		$("#agentDepartInfoTable_tb").css("display", "none"); 
		$("#agentOtherInfoTable_tb").css("display", "none"); 
		$("#addAgent_submitButton").hide();
		//设置datagrid点击事件不可用
		agentFamilyInfoTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		agentSalaryAccTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		agentCertificationTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		agentPositionInfoTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		agentNurserInfoTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		agentWorkInfoTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		/*agentStoreInfoTable.datagrid('options').onClickRow = agentDetailOnClickRow;*/
		agentDepartInfoTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		agentOtherInfoTable.datagrid('options').onClickRow = agentDetailOnClickRow;
		
	}else{
		/*initAllValidateBox();*/
	}
	
	/*$("#addAgent_BaseInfoForm").form("validate");*/
	

});	


function agentDetailOnClickRow(){}

function initAllValidateBox(){
	$('#addAgent_agentCode').validatebox({required:true});	
	$('#addAgent_agentName').validatebox({required:true});
	$('#addAgent_idNo').validatebox({required:true});
	$('#addAgent_userCode').validatebox({required:true});
	$('#addAgent_agentMobile').validatebox({required:true});
	/*$("#addAgent_BaseInfoForm").form("validate");
	$("#addAgent_agentPositionForm").form("validate");
	$("#addAgent_agentStoreForm").form("validate");
	$("#addAgent_agentDepartForm").form("validate");*/
}


/**
 * 查询财富顾问详细信息
 */
function queryAgentInfo(){
	var param = {};
	//Agent基本信息
	var agentBaseInfoJsonStr = formDataToJsonStr($("#addAgent_BaseInfoForm").serialize());
	////console.info("=============agentBaseInfoJsonStr===========");
	////console.info(agentBaseInfoJsonStr);
	param.agentBaseInfo = agentBaseInfoJsonStr;
	$.ajax({
		type:'post',
		url:contextPath+"/sales/queryAgentInfo",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
		////console.info(reData);
			try {
				if(reData.success){
					var reDataObj = reData.obj;
					//1.财富顾问基本信息赋值
					//先加载市和区的下拉列表数据
					var provinceCode = reDataObj.agentBaseInfo.province;
					var loadCityDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=02&upPlaceCode="+provinceCode;
					$("#addAgent_city").combobox('reload',loadCityDataUrl);
					var cityCode = reDataObj.agentBaseInfo.city;
					var loadCountryDataUrl = contextPath+"/codeQuery/placeCodeQuery?placeType=03&upPlaceCode="+cityCode;
					$("#addAgent_country").combobox('reload',loadCountryDataUrl);
					//是身份证的话设置出生日期和性别不可编辑
					if(reDataObj.agentBaseInfo.idtype=="1"){
						$('#addAgent_birthday').datebox("disable");
						$('#addAgent_sex').combobox("disable");
					}
					setInputValueById("addAgent_agentBaseInfo",reDataObj.agentBaseInfo);
					$("#addAgent_agentImage").val(reDataObj.agentBaseInfo.agentImage);

					//2.财富顾问家庭成员信息赋值
					if(reDataObj.agentFamilyInfo!=null&&reDataObj.agentFamilyInfo!=undefined){
						clearAllRows(agentFamilyInfoTable);
						loadJsonObjData("agentFamilyInfoTable",reDataObj.agentFamilyInfo);
						agentFamilyInfoTableEditRowIndex = null;
					}
					//3.财富顾问证书信息赋值
					if(reDataObj.agentCertificationInfo!=null&&reDataObj.agentCertificationInfo!=undefined){
						clearAllRows(agentCertificationTable);
						loadJsonObjData("agentCertificationTable",reDataObj.agentCertificationInfo);
						certificationEditRowIndex = null;
					}
					
					//4.财富顾问职级信息赋值
					if(reDataObj.agentPositionInfo!=null&&reDataObj.agentPositionInfo!=undefined){
						clearAllRows(agentPositionInfoTable);
						loadJsonObjData("agentPositionInfoTable",reDataObj.agentPositionInfo);
						agentPositionInfoTableEditRowIndex = null;
					}
					//4.1财富顾问培育信息赋值
					if(reDataObj.agentNurserInfo!=null&&reDataObj.agentNurserInfo!=undefined){
						clearAllRows(agentNurserInfoTable);
						loadJsonObjData("agentNurserInfoTable",reDataObj.agentNurserInfo);
						agentNurserInfoTableEditRowIndex = null;
					}
					
					//4.2财富顾问从业经历信息赋值
					if(reDataObj.agentWorkInfo!=null&&reDataObj.agentWorkInfo!=undefined){
						clearAllRows(agentWorkInfoTable);
						loadJsonObjData("agentWorkInfoTable",reDataObj.agentWorkInfo);
						agentWorkInfoTableEditRowIndex = null;
					}
					//setInputValueById("addAgent_agentPositionInfo",reDataObj.agentPositionInfo);
					//5.财富顾问网点所属信息赋值
					/*var storeUrl = contextPath+'/codeQuery/storeQuery?comId='+reDataObj.agentStoreInfo.comId;
					$('#addAgent_storeId').combobox('reload',storeUrl);
					setInputValueById("addAgent_agentStoreInfo",reDataObj.agentStoreInfo);*/
					/*if(reDataObj.agentStoreInfo!=null&&reDataObj.agentStoreInfo!=undefined){
						clearAllRows(agentStoreInfoTable);
						loadJsonObjData("agentStoreInfoTable",reDataObj.agentStoreInfo);
						agentStoreInfoTableEditRowIndex = null;
					}*/
					//6.财富顾问所属团队信息赋值
					/*var departUrl = contextPath+'/codeQuery/departmentQuery?comId='+reDataObj.agentDepartmentInfo.comId;
					$('#addAgent_departId').combobox('reload',departUrl);
					setInputValueById("addAgent_agentDepartmentInfo",reDataObj.agentDepartmentInfo);*/
					if(reDataObj.agentDepartmentInfo!=null&&reDataObj.agentDepartmentInfo!=undefined){
						clearAllRows(agentDepartInfoTable);
						loadJsonObjData("agentDepartInfoTable",reDataObj.agentDepartmentInfo);
						agentDepartInfoTableEditRowIndex = null;
					}
					//7.员工其他信息赋值
					if(reDataObj.agentOtherInfo!=null&&reDataObj.agentOtherInfo!=undefined){
						clearAllRows(agentOtherInfoTable);
						loadJsonObjData("agentOtherInfoTable",reDataObj.agentOtherInfo);
						agentOtherInfoTableEditRowIndex = null;
					}
					//8.员工工资卡信息赋值
					if(reDataObj.agentSalaryAccInfo!=null&&reDataObj.agentSalaryAccInfo!=undefined){
						clearAllRows(agentSalaryAccTable);
						loadJsonObjData("agentSalaryAccTable",reDataObj.agentSalaryAccInfo);
						agentSalaryAccTableEditRowIndex = null;
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

var agentFamilyInfoTable;
function initAgentFamilyInfoTable(){
	agentFamilyInfoTable = $('#agentFamilyInfoTable').datagrid({
		title : '家庭信息列表', // 标题
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
					field : 'familyId',
					title : '家庭成员流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.familyId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'memberName',
					title : '家庭成员姓名',
					width : 100,
					//sortable : true,
					//editor: 'text',
					editor:{
						type:"validatebox",
						options:{
							required:true
						}
					},
					formatter : function(value, row, index) {
						return row.memberName;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'relationToAgent',
					title : '与员工间关系',
					width : 100,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							url:"codeQuery/tdCodeQuery?codeType=relationToAgent",
							onHidePanel:function(){
								var ed = agentFamilyInfoTable.datagrid('getEditor', {index:agentFamilyInfoTableEditRowIndex,field:'relationToAgent'});
								var relationToAgentName = $(ed.target).combobox('getText');
								agentFamilyInfoTable.datagrid('getRows')[agentFamilyInfoTableEditRowIndex]['relationToAgentName'] = relationToAgentName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.relationToAgentName;
					}
				},{
					field : 'relationToAgentName',
					title : '与员工间关系',
					hidden: true,
					formatter : function(value, row, index) {
						return row.relationToAgentName;
					}
				},
				{
					field : 'age',
					title : '年龄',
					width : 100,
					//editor: 'text',
					editor: {
						type:'validatebox',
						options:{
							validType: 'validNum'   
						}
					},
					formatter : function(value, row, index) {
						return row.age;
					}
				},{
					field : 'occupationCode',
					title : '职业',
					width : 100,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							url:"codeQuery/tdCodeQuery?codeType=occupation",
							onHidePanel:function(){
								var ed = agentFamilyInfoTable.datagrid('getEditor', {index:agentFamilyInfoTableEditRowIndex,field:'occupationCode'});
								var occupationName = $(ed.target).combobox('getText');
								agentFamilyInfoTable.datagrid('getRows')[agentFamilyInfoTableEditRowIndex]['occupationName'] = occupationName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.occupationName;
					}
				},{
					field : 'occupationName',
					title : '职业',
					hidden: true,
					formatter : function(value, row, index) {
						return row.occupationName;
					}
				},
				{
					field : 'incomeValue',
					title : '年收入(万元)',
					width : 100,
					hidden : true,
					//editor: 'text',
					editor: {
						type:'validatebox',
						options:{
							validType: 'validNum'   
						}
					},
					formatter : function(value, row, index) {
						return row.incomeValue;
					}
				},
				{
					field : 'mobile',
					title : '手机',
					width : 100,
					//editor: 'text',
					editor: {
						type:'validatebox',
						options:{
							validType: 'validPhone'   
						}
					},
					formatter : function(value, row, index) {
						return row.mobile;
					}
				},{
					field : 'email',
					title : 'E-mail',
					width : 100,
					//editor: 'text',
					editor: {
						type:'validatebox',
						options:{
							validType: 'email'   
						}
					},
					formatter : function(value, row, index) {
						return row.email;
					}
				},
				{
					field : 'qq',
					title : 'QQ号',
					width : 100,
					hidden : true,
					//editor: 'text',
					editor: {
						type:'validatebox',
						options:{
							validType: 'validQQ'   
						}
					},
					formatter : function(value, row, index) {
						return row.qq;
					}
				},
				{
					field : 'wechat',
					title : '微信号',
					width : 100,
					hidden : true,
					//editor: 'text',
					editor: {
						type:'validatebox',
						options:{
							validType: 'validCode'   
						}
					},
					formatter : function(value, row, index) {
						return row.wechat;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#agentFamilyInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentFamilyInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#agentFamilyInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function agentFamilyInfoTableAddOneRow(){
	////console.info(agentFamilyInfoTable.toolbar);
	agentFamilyInfoTableEditRowIndex = addOneRow(agentFamilyInfoTable,agentFamilyInfoTableEditRowIndex);
}
//删除一行
function agentFamilyInfoTableRemoveOneRow(){
	removeOneRow(agentFamilyInfoTable,agentFamilyInfoTableEditRowIndex);
	agentFamilyInfoTableEditRowIndex = null;
}
//编辑指定行
function agentFamilyInfoTableeditOneRow(index){
	if(editOneRow(agentFamilyInfoTable,agentFamilyInfoTableEditRowIndex,index)){
		agentFamilyInfoTableEditRowIndex = index;
	}
}
//锁定编辑行
function agentFamilyInfoTableLockOneRow(){
	if(lockOneRow(agentFamilyInfoTable,agentFamilyInfoTableEditRowIndex)){
		agentFamilyInfoTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}

/*********************************************************************/


var agentSalaryAccTable;
function initAgentSalaryAccTable(){
	agentSalaryAccTable = $('#agentSalaryAccTable').datagrid({
		title : '工资卡信息列表', // 标题
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
					field : 'agentAccInfoId',
					title : '员工工资卡流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.familyId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'bankCode',
					title : '开户行',
					width : 30,
					//sortable : true,
					editor: {
						type:'combobox',
						options:{
							required:true,
							valueField:'bankId',
							textField:'bankName',
							url:"codeQuery/queryBankInfo",
							onHidePanel:function(){
								var ed = agentSalaryAccTable.datagrid('getEditor', {index:agentSalaryAccTableEditRowIndex,field:'bankCode'});
								var bankName = $(ed.target).combobox('getText');
								agentSalaryAccTable.datagrid('getRows')[agentSalaryAccTableEditRowIndex]['bankName'] = bankName;
							},
							onChange: function(newValue, oldValue){
								if(newValue!=null&&newValue!=""&&newValue!=undefined){
									$(this).next().children(":text").blur(function() {
										var ed = agentSalaryAccTable.datagrid('getEditor', {index:agentSalaryAccTableEditRowIndex,field:'bankCode'});
										var allData = $(ed.target).combobox('getData');
										var value = $(ed.target).combobox('getValue');
										if(value==null||value==""||value==undefined){
											$(ed.target).combobox('reset');
										}else{
											var count = 0;
											for(var i=0;i<allData.length;i++){
												var rowData = allData[i];
												if(rowData.bankId==value){
													count++;
												}
											}
											if(count==0){
												$(ed.target).combobox('reset');
											}
										}
									});
								}
							}

						}
					},
					formatter : function(value, row, index) {
						return row.bankName;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'bankName',
					title : '开户行名称',
					hidden : true,
					formatter : function(value, row, index) {
						return row.bankName;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'branchBankName',
					title : '分行名称',
					width : 30,
					editor: {
						type:'validatebox',
						options:{
							required:true,
							validType:"length[0,40]"
						}
					},
					formatter : function(value, row, index) {
						return row.branchBankName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'accName',
					title : '开户名',
					width : 30,
					//sortable : true,
					//editor: 'text',
					editor: {
						type:"validatebox",
						options:{
							required:true,
							validType:"length[0,50]"
						}
					},
					formatter : function(value, row, index) {
						return row.accName;
					}
				},{
					field : 'bankAccNo',
					title : '开户账号',
					width : 80,
					//editor: 'text',
					editor: {
						type:"validatebox",
						options:{
							required:true,
							validType:["validNum","length[0,40]"],
							tipPosition:"left"
						}
					},
					formatter : function(value, row, index) {
						return row.bankAccNo;
					} // 需要formatter一下才能显示正确的数据
				}]],
		onLoadSuccess : function() {
			$('#agentSalaryAccTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentSalaryAccTableeditOneRow(rowIndex);
		},
		toolbar:"#agentSalaryAccTable_tb"
	});
}
/*********************************************************************/
//增加一行
function agentSalaryAccTableAddOneRow(){
	////console.info(agentFamilyInfoTable.toolbar);
	agentSalaryAccTableEditRowIndex = addOneRow(agentSalaryAccTable,agentSalaryAccTableEditRowIndex);
}
//删除一行
function agentSalaryAccTableRemoveOneRow(){
	removeOneRow(agentSalaryAccTable,agentSalaryAccTableEditRowIndex);
	agentSalaryAccTableEditRowIndex = null;
}
//编辑指定行
function agentSalaryAccTableeditOneRow(index){
	if(editOneRow(agentSalaryAccTable,agentSalaryAccTableEditRowIndex,index)){
		agentSalaryAccTableEditRowIndex = index;
	}
}
//锁定编辑行
function agentSalaryAccTableLockOneRow(){
	if(lockOneRow(agentSalaryAccTable,agentSalaryAccTableEditRowIndex)){
		agentSalaryAccTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}

/*********************************************************************/
var agentCertificationTable;
function initAgentCertificationTable(){
	agentCertificationTable=$('#agentCertificationTable').datagrid({
		title : '资格证信息列表', // 标题
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
					field : 'certificationId',
					title : '资格证流水号',
					hidden:true,
					formatter : function(value, row, index) {
						return row.certificationId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'certificationType',
					title : '资格证类型',
					width : 100,
					editor:{
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url:"codeQuery/tdCodeQuery?codeType=certificationType",
							onHidePanel:function(){
								var ed = agentCertificationTable.datagrid('getEditor', {index:certificationEditRowIndex,field:'certificationType'});
								var certificationName = $(ed.target).combobox('getText');
								////console.info("certificationName==="+certificationName);
								var certificationType = $(ed.target).combobox('getValue');
								//console.info("certificationType=="+certificationType);
								agentCertificationTable.datagrid('getRows')[certificationEditRowIndex]['certificationName'] = certificationName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.certificationName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'certificationName',
					title : '资格证类型',
					hidden: true,
					formatter : function(value, row, index) {
						return row.certificationName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'certificationCode',
					title : '资格证代码',
					width : 100,
					editor: 'text',
					sortable : true,
					formatter : function(value, row, index) {
						return row.certificationCode;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#agentCertificationTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentCertificationTableeditOneRow(rowIndex);
		},
		toolbar:"#agentCertificationTable_tb"
	});
}


/*********************************************************************/
//增加一行
function agentCertificationTableAddOneRow(){
	certificationEditRowIndex = addOneRow(agentCertificationTable,certificationEditRowIndex);
}
//删除一行
function agentCertificationTableRemoveOneRow(){
	removeOneRow(agentCertificationTable,certificationEditRowIndex);
	certificationEditRowIndex = null;
}
//编辑指定行
function agentCertificationTableeditOneRow(index){
	editOneRow(agentCertificationTable,certificationEditRowIndex,index);
	certificationEditRowIndex = index;
}
//锁定编辑行
function agentCertificationTableLockOneRow(){
	if(lockOneRow(agentCertificationTable,certificationEditRowIndex)){
		certificationEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
/*********************************************************************/

var agentPositionInfoTable;
function initAgentPositionInfoTable(){
	agentPositionInfoTable = $('#agentPositionInfoTable').datagrid({
		title : '职位信息列表', // 标题
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
					field : 'blpositionId',
					title : '归属流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.blpositionId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'positionCode',
					title : '职位',
					width: 100,
					editor:{
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url : contextPath+"/codeQuery/tdCodeQuery?codeType=positionCode",
							onHidePanel:function(){
								//var ed = agentPositionInfoTable.datagrid('getEditor', {index:agentPositionInfoTableEditRowIndex,field:'positionCode'});
								var positionCodeName = $(this).combobox('getText');
								var positionCode =  $(this).combobox('getValue');
								agentPositionInfoTable.datagrid('getRows')[agentPositionInfoTableEditRowIndex]['positionCodeName'] = positionCodeName;
								var edGradeCode = agentPositionInfoTable.datagrid('getEditor', {index:agentPositionInfoTableEditRowIndex,field:'gradeCode'});
								$(edGradeCode.target).combobox("enable");
							}
						}
					},
					formatter : function(value, row, index) {
						return row.positionCodeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'positionCodeName',
					title : '职位名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.positionCodeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'gradeCode',
					title : '级别',
					width: 100,
					editor:{
						type:'combobox',
						options:{
							//required:true,
							valueField:'code',
							textField:'codeName',
							url : contextPath+"/codeQuery/tdCodeQuery?codeType=gradeCode",
							onHidePanel:function(){
								//var ed = agentPositionInfoTable.datagrid('getEditor', {index:agentPositionInfoTableEditRowIndex,field:'gradeCode'});
								var gradeCodeName = $(this).combobox('getText');
								agentPositionInfoTable.datagrid('getRows')[agentPositionInfoTableEditRowIndex]['gradeCodeName'] = gradeCodeName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.gradeCodeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'gradeCodeName',
					title : '级别名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.gradeCodeName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'startDate',
					title : '开始日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							required:true,
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.startDate;
					}
				},
				{
					field : 'endDate',
					title : '结束日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.endDate;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#agentPositionInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentPositionInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#agentPositionInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function agentPositionInfoTableAddOneRow(){
	agentPositionInfoTableEditRowIndex = addOneRow(agentPositionInfoTable,agentPositionInfoTableEditRowIndex);
}
//删除一行
function agentPositionInfoTableRemoveOneRow(){
	removeOneRow(agentPositionInfoTable,agentPositionInfoTableEditRowIndex);
	agentPositionInfoTableEditRowIndex = null;
}
//编辑指定行
function agentPositionInfoTableeditOneRow(index){
	if(editOneRow(agentPositionInfoTable,agentPositionInfoTableEditRowIndex,index)){
		agentPositionInfoTableEditRowIndex = index;
	}
}
//锁定编辑行
function agentPositionInfoTableLockOneRow(){
	if(lockOneRow(agentPositionInfoTable,agentPositionInfoTableEditRowIndex)){
		agentPositionInfoTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
/*********************************************************************/



var agentNurserInfoTable;
function initAgentNurserInfoTable(){
	agentNurserInfoTable = $('#agentNurserInfoTable').datagrid({
		title : '培育信息列表', // 标题
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
					field : 'agentNurserInfoId',
					title : '培育信息流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.agentNurserInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'nurserAgentId',
					title : '培育人',
					width: 100,
					editor:{
						type:'combobox',
						options:{
							required:true,
							url:contextPath+'/codeQuery/queryAllAgent?agentName='+"",
							valueField:'agentId',
							textField:'agentName',
							editable:true,
							onHidePanel:function(){
								var nurserAgentName = $(this).combobox('getText');
								agentNurserInfoTable.datagrid('getRows')[agentNurserInfoTableEditRowIndex]['nurserAgentName'] = nurserAgentName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.nurserAgentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'nurserAgentCode',
					title : '培育人代码',
					hidden: true,
					formatter : function(value, row, index) {
						return row.nurserAgentCode;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'nurserAgentName',
					title : '培育人姓名',
					hidden: true,
					formatter : function(value, row, index) {
						return row.nurserAgentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'startDate',
					title : '开始日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							required:true,
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.startDate;
					}
				},
				{
					field : 'endDate',
					title : '结束日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.endDate;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#agentNurserInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentNurserInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#agentNurserInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function agentNurserInfoTableAddOneRow(){
	agentNurserInfoTableEditRowIndex = addOneRow(agentNurserInfoTable,agentNurserInfoTableEditRowIndex);
}
//删除一行
function agentNurserInfoTableRemoveOneRow(){
	removeOneRow(agentNurserInfoTable,agentNurserInfoTableEditRowIndex);
	agentNurserInfoTableEditRowIndex = null;
}
//编辑指定行
function agentNurserInfoTableeditOneRow(index){
	if(editOneRow(agentNurserInfoTable,agentNurserInfoTableEditRowIndex,index)){
		agentNurserInfoTableEditRowIndex = index;
	}
}
//锁定编辑行
function agentNurserInfoTableLockOneRow(){
	if(lockOneRow(agentNurserInfoTable,agentNurserInfoTableEditRowIndex)){
		agentNurserInfoTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
/*********************************************************************/



var agentWorkInfoTable;
function initAgentWorkInfoTable(){
	agentWorkInfoTable = $('#agentWorkInfoTable').datagrid({
		title : '从业信息列表', // 标题
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
					field : 'agentWorkInfoId',
					title : '从业信息流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.agentWorkInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'occupation',
					title : '行业',
					width: 100,
					editor:{
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url : contextPath+"/codeQuery/tdCodeQuery?codeType=agentOccupation",
							onHidePanel:function(){
								var subOccupationEd = agentWorkInfoTable.datagrid('getEditor', {index:agentWorkInfoTableEditRowIndex,field:'subOccupation'});
								var agentOccupationName = $(this).combobox('getText');
								var agentOccupationCode =  $(this).combobox('getValue');
								agentWorkInfoTable.datagrid('getRows')[agentWorkInfoTableEditRowIndex]['occupationName'] = agentOccupationName;
								var subOccupationUrl = "";
								//金融类
								if(agentOccupationCode=="01"){
									subOccupationUrl = contextPath+"/codeQuery/tdCodeQueryIn?codeType=agentSubOccupation&codeListStr=['01','02','03','04','05','09']";
								}
								//零售类
								else if(agentOccupationCode=="02"){
									subOccupationUrl = contextPath+"/codeQuery/tdCodeQueryIn?codeType=agentSubOccupation&codeListStr=['06','07','08','09']";
								}
								//其他类
								else{
									subOccupationUrl = contextPath+"/codeQuery/tdCodeQueryIn?codeType=agentSubOccupation&codeListStr=['09']";
								}
								$(subOccupationEd.target).combobox('reload',subOccupationUrl);
							}
						}
					},
					formatter : function(value, row, index) {
						return row.occupationName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'occupationName',
					title : '行业名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.occupationName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'subOccupation',
					title : '行业小类',
					width : 100,
					editor:{
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url : contextPath+"/codeQuery/tdCodeQuery?codeType=agentSubOccupation",
							onHidePanel:function(){
								var agentSubOccupationName = $(this).combobox('getText');
								var agentSubOccupationCode =  $(this).combobox('getValue');
								agentWorkInfoTable.datagrid('getRows')[agentWorkInfoTableEditRowIndex]['subOccupationName'] = agentSubOccupationName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.subOccupationName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'subOccupationName',
					title : '行业小类名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.subOccupationName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'duties',
					title : '职务',
					width : 100,
					editor:{
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url : contextPath+"/codeQuery/tdCodeQuery?codeType=agentDuties",
							onHidePanel:function(){
								var agentDutiesName = $(this).combobox('getText');
								agentWorkInfoTable.datagrid('getRows')[agentWorkInfoTableEditRowIndex]['dutiesName'] = agentDutiesName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.dutiesName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'dutiesName',
					title : '职务名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.dutiesName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'rank',
					title : '职位',
					width : 100,
					editor:{
						type:'combobox',
						options:{
							required:true,
							valueField:'code',
							textField:'codeName',
							url : contextPath+"/codeQuery/tdCodeQuery?codeType=agentRank",
							onHidePanel:function(){
								var agentRankName = $(this).combobox('getText');
								agentWorkInfoTable.datagrid('getRows')[agentWorkInfoTableEditRowIndex]['rankName'] = agentRankName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.rankName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'rankName',
					title : '职级名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.rankName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'startDate',
					title : '开始日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							required:true,
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.startDate;
					}
				},
				{
					field : 'endDate',
					title : '结束日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.endDate;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#agentWorkInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentWorkInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#agentWorkInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function agentWorkInfoTableAddOneRow(){
	agentWorkInfoTableEditRowIndex = addOneRow(agentWorkInfoTable,agentWorkInfoTableEditRowIndex);
}
//删除一行
function agentWorkInfoTableRemoveOneRow(){
	removeOneRow(agentWorkInfoTable,agentWorkInfoTableEditRowIndex);
	agentWorkInfoTableEditRowIndex = null;
}
//编辑指定行
function agentWorkInfoTableeditOneRow(index){
	if(editOneRow(agentWorkInfoTable,agentWorkInfoTableEditRowIndex,index)){
		agentWorkInfoTableEditRowIndex = index;
	}
}
//锁定编辑行
function agentWorkInfoTableLockOneRow(){
	if(lockOneRow(agentWorkInfoTable,agentWorkInfoTableEditRowIndex)){
		agentWorkInfoTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
/*********************************************************************/

var agentDepartInfoTable;
function initAgentDepartInfoTable(){
	agentDepartInfoTable = $('#agentDepartInfoTable').datagrid({
		title : '部门信息列表', // 标题
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
					field : 'bldepartmentId',
					title : '归属部门流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.blpositionId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'comId',
					title : '所属机构',
					width: 100,
					editor:{
						type:'combobox',
						options:{
							required : true,
							url : contextPath+'/codeQuery/comQuery',
							valueField:'comId',
							textField:'comName',
							onHidePanel:function(){
								var ed = agentDepartInfoTable.datagrid('getEditor', {index:agentDepartInfoTableEditRowIndex,field:'comId'});
								var comName = $(ed.target).combobox('getText');
								var comId = $(ed.target).combobox('getValue');
								agentDepartInfoTable.datagrid('getRows')[agentDepartInfoTableEditRowIndex]['comName'] = comName;
								var url = contextPath+'/codeQuery/departmentQueryByComId?comId='+comId;
								var departEditor = agentDepartInfoTable.datagrid('getEditor', {index:agentDepartInfoTableEditRowIndex,field:'departmentId'});
								$(departEditor.target).combobox('reload',url);
								$(departEditor.target).combobox('reset');
							}
						}
					},
					formatter : function(value, row, index) {
						return row.comName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'comName',
					title : '所属机构名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.comName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'departmentId',
					title : '部门名称',
					width: 100,
					editor:{
						type:'combobox',
						options:{
//							required : true,
							url:contextPath+'/codeQuery/departmentQuery',
							valueField:'departmentId',
							textField:'departmentName',
							onHidePanel:function(){
								var ed = agentDepartInfoTable.datagrid('getEditor', {index:agentDepartInfoTableEditRowIndex,field:'departmentId'});
								var departmentName = $(ed.target).combobox('getText');
								agentDepartInfoTable.datagrid('getRows')[agentDepartInfoTableEditRowIndex]['departmentName'] = departmentName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.departmentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'departmentName',
					title : '所属部门',
					hidden: true,
					formatter : function(value, row, index) {
						return row.departmentName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'isLeader',
					title : '是否负责人',
					width: 100,
					editor:{
						type:'combobox',
						options:{
							required : true,
							url:contextPath+'/codeQuery/tdCodeQuery?codeType=isLeader',
							valueField:'code',
							textField:'codeName',
							onHidePanel:function(){
								var ed = agentDepartInfoTable.datagrid('getEditor', {index:agentDepartInfoTableEditRowIndex,field:'isLeader'});
								var isLeaderName = $(ed.target).combobox('getText');
								agentDepartInfoTable.datagrid('getRows')[agentDepartInfoTableEditRowIndex]['isLeaderName'] = isLeaderName;
							}
						}
					},
					formatter : function(value, row, index) {
						return row.isLeaderName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'isLeaderName',
					title : '是否负责人名称',
					hidden: true,
					formatter : function(value, row, index) {
						return row.isLeaderName;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'startDate',
					title : '开始日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							required:true,
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.startDate;
					}
				},
				{
					field : 'endDate',
					title : '结束日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.endDate;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#agentDepartInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentDepartInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#agentDepartInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function agentDepartInfoTableAddOneRow(){
	agentDepartInfoTableEditRowIndex = addOneRow(agentDepartInfoTable,agentDepartInfoTableEditRowIndex);
}
//删除一行
function agentDepartInfoTableRemoveOneRow(){
	removeOneRow(agentDepartInfoTable,agentDepartInfoTableEditRowIndex);
	agentDepartInfoTableEditRowIndex = null;
}
//编辑指定行
function agentDepartInfoTableeditOneRow(index){
	if(editOneRow(agentDepartInfoTable,agentDepartInfoTableEditRowIndex,index)){
		agentDepartInfoTableEditRowIndex = index;
	}
}
//锁定编辑行
function agentDepartInfoTableLockOneRow(){
	if(lockOneRow(agentDepartInfoTable,agentDepartInfoTableEditRowIndex)){
		agentDepartInfoTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}


/*********************************************************************/

var agentOtherInfoTable;
function initAgentOtherInfoTable(){
	agentOtherInfoTable = $('#agentOtherInfoTable').datagrid({
		title : '其他信息列表', // 标题
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
					field : 'otherInfoId',
					title : '其他信息流水号',
					hidden : true,
					formatter : function(value, row, index) {
						return row.otherInfoId;
					} 
				},
				{
					field : 'contractStartDate',
					title : '合同生效日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.contractStartDate;
					}
				},
				{
					field : 'contractEndDate',
					title : '合同到期日期',
					width : 100,
					editor:{
						type:'datebox',
						options:{
							validType:['validDate']
						}
					},
					formatter : function(value, row, index) {
						return row.contractEndDate;
					}
				},
				{
					field : 'halfyearTestRank',
					title : '年中绩效考核排名',
					width : 100,
					editor: 'text',
					sortable : true,
					formatter : function(value, row, index) {
						return row.halfyearTestRank;
					}
				},
				{
					field : 'finalTestRank',
					title : '年末绩效考核排名',
					width : 100,
					editor: 'text',
					sortable : true,
					formatter : function(value, row, index) {
						return row.finalTestRank;
					}
				}
				]],
		onLoadSuccess : function() {
			$('#agentOtherInfoTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			agentOtherInfoTableeditOneRow(rowIndex);
		},
		toolbar:"#agentOtherInfoTable_tb"
	});
}
/*********************************************************************/
//增加一行
function agentOtherInfoTableAddOneRow(){
	agentOtherInfoTableEditRowIndex = addOneRow(agentOtherInfoTable,agentOtherInfoTableEditRowIndex);
}
//删除一行
function agentOtherInfoTableRemoveOneRow(){
	removeOneRow(agentOtherInfoTable,agentOtherInfoTableEditRowIndex);
	agentOtherInfoTableEditRowIndex = null;
}
//编辑指定行
function agentOtherInfoTableeditOneRow(index){
	if(editOneRow(agentOtherInfoTable,agentOtherInfoTableEditRowIndex,index)){
		agentOtherInfoTableEditRowIndex = index;
	}
}
//锁定编辑行
function agentOtherInfoTableLockOneRow(){
	if(lockOneRow(agentOtherInfoTable,agentOtherInfoTableEditRowIndex)){
		agentOtherInfoTableEditRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
/*********************************************************************/
function verifyUserCode(){
	//校验通过后再做唯一性校验
	if(!($("#addAgent_userCode").validatebox('isValid'))){
		return;
	}
	//查看时用户登陆账号只读，不用做唯一性校验
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="agentDetail"){
		verifyUserCodeFlag = true;
		return;
	}
	var userId = $('#addAgent_userId').val();
	var userCode = $('#addAgent_userCode').val();
	//如果只是失去焦点，用户编码没变的话，就不去做唯一性校验
	/*if(oldUserCode==userCode){
		return;
	}else{
		oldUserCode = userCode;
	}*/
	var param = {};
	param.userId = userId;
	param.userCode = userCode;
	param.operate = operate;
	$.ajax({
		type:'post',
		url:contextPath+"/sales/verifyUserCode",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
		//console.info(reData);
			try {
				if(reData.success){
					verifyUserCodeFlag = true;
					
				}else{
					verifyUserCodeFlag = false;
				
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function verifyAgentCode(){
	//校验通过后再做唯一性校验
	if(!($("#addAgent_agentCode").validatebox('isValid'))){
		return;
	}
	//修改和查看时用户登陆账号只读，不用做唯一性校验
	if(operate!=null&&operate!=""&&operate!=undefined&&operate=="agentDetail"){
		verifyAgentCodeFlag = true;
		return;
	}
	var agentId = $('#addAgent_agentId').val();
	var agentCode = $('#addAgent_agentCode').val();
	//如果只是失去焦点，财富顾问编码没变的话，就不去做唯一性校验
	if(oldAgentCode==agentCode){
		return;
	}else{
		oldAgentCode = agentCode;
	}
	var param = {};
	param.agentId = agentId;
	param.agentCode = agentCode;
	param.operate = operate;
	$.ajax({
		type:'post',
		url:contextPath+"/sales/verifyAgentCode",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
		//console.info(reData);
			try {
				if(reData.success){
					verifyAgentCodeFlag = true;
				}else{
					verifyAgentCodeFlag = false;
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


function verifyIdNo(){
	//身份证号码格式校验
	var idType = $("#addAgent_idType").combobox("getValue");
	var idNo = $("#addAgent_idNo").val();
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		if(!checkIdNo(idNo)){
			//$.messager.alert('提示', "该财富顾问编码已经存在！", 'info');
			return false;
		}else{
			//设置出生日期和性别
			var birthdayAndSex = getBirthdayAndSex(idNo);
			$("#addAgent_sex").combobox("setValue",birthdayAndSex.sex);
			$("#addAgent_birthday").datebox("setValue",birthdayAndSex.birthday);
		}
	}
}

function setIdNoAndSexDesabled(){
	var idType = $('#addAgent_idType').combobox("getValue");
	if(idType!=null&&idType!=""&&idType!=undefined&&idType=="1"){
		$('#addAgent_birthday').datebox("disable");
		$('#addAgent_sex').combobox("disable");
	}else{
		$('#addAgent_birthday').datebox("enable");
		$('#addAgent_sex').combobox("enable");
	}
}

function checkData(){
	//1.所有表单非空校验
	if(!$("#addAgent_BaseInfoForm").form("validate")||!$("#addAgent_agentPositionForm").form("validate")
			||!$("#addAgent_agentStoreForm").form("validate")||!$("#addAgent_agentDepartForm").form("validate")){
		return false;
	}
	//2.用户编码与财富顾问编码非重复性校验
	//verifyUserCode();
	if(!verifyUserCodeFlag){
		//$.messager.alert('提示', "该用户编码已经存在！", 'info');
		return false;
	}
	//verifyAgentCode();
	if(!verifyAgentCodeFlag){
		$.messager.alert('提示', "该工号已经存在！", 'info');
		return false;
	}
	
	if(!agentFamilyInfoTableLockOneRow()){
		$.messager.alert('提示', "员工家庭成员信息输入有误！", 'info');
		return false;
	}
	if(!agentSalaryAccTableLockOneRow()){
		$.messager.alert('提示', "员工工资卡信息输入有误！", 'info');
		return false;
	}
	if(!agentCertificationTableLockOneRow()){
		$.messager.alert('提示', "员工证书信息输入有误！", 'info');
		return false;
	}
	if(!agentPositionInfoTableLockOneRow()){
		$.messager.alert('提示', "员工职级信息输入有误！", 'info');
		return false;
	}
	if(!agentNurserInfoTableLockOneRow()){
		$.messager.alert('提示', "员工培育信息输入有误！", 'info');
		return false;
	}	
	if(!agentWorkInfoTableLockOneRow()){
		$.messager.alert('提示', "员工从业经历信息输入有误！", 'info');
		return false;
	}	
	if(!agentOtherInfoTableLockOneRow()){
		$.messager.alert('提示', "员工其他信息输入有误！", 'info');
		return false;
	}	
	/*if(!agentStoreInfoTableLockOneRow()){
		$.messager.alert('提示', "员工所属网点信息输入有误！", 'info');
		return false;
	}	*/
	if(!agentDepartInfoTableLockOneRow()){
		$.messager.alert('提示', "员工所属部门信息输入有误！", 'info');
		return false;
	}	
	
	//校验职级信息的起始日期与结束日期
	var positionInfo = $("#agentPositionInfoTable").datagrid("getRows");
	for(var i=0;i<positionInfo.length;i++){
		var positionStartDate = positionInfo[i].startDate;
		var positionEndDate = positionInfo[i].endDate;
		if(!compareDate(positionStartDate, positionEndDate)){
			$.messager.alert('提示', "职级信息列表第"+(i+1)+"行日期输入有误，结束日期不能早于开始日期！", 'info');
			return false;
		}
	}
	
	//校验培育关系信息的起始日期与结束日期
	var nurserInfo = $("#agentNurserInfoTable").datagrid("getRows");
	for(var i=0;i<nurserInfo.length;i++){
		var nurserStartDate = nurserInfo[i].startDate;
		var nurserEndDate = nurserInfo[i].endDate;
		if(!compareDate(nurserStartDate, nurserEndDate)){
			$.messager.alert('提示', "培育信息列表第"+(i+1)+"行日期输入有误，结束日期不能早于开始日期！", 'info');
			return false;
		}
	}
	
	//校验从业经历信息的起始日期与结束日期
	var workInfo = $("#agentWorkInfoTable").datagrid("getRows");
	for(var i=0;i<workInfo.length;i++){
		var workStartDate = workInfo[i].startDate;
		var workEndDate = workInfo[i].endDate;
		if(!compareDate(workStartDate, workEndDate)){
			$.messager.alert('提示', "从业信息列表第"+(i+1)+"行日期输入有误，结束日期不能早于开始日期！", 'info');
			return false;
		}
	}
	
	//校验网点信息列表的起始日期与结束日期
/*	var agentStoreInfo = $("#agentStoreInfoTable").datagrid("getRows");
	for(var i=0;i<agentStoreInfo.length;i++){
		var storeStartDate = agentStoreInfo[i].startDate;
		var storeEndDate = agentStoreInfo[i].endDate;
		if(!compareDate(storeStartDate, storeEndDate)){
			$.messager.alert('提示', "网点信息列表第"+(i+1)+"行日期输入有误，结束日期不能早于开始日期！", 'info');
			return false;
		}
	}*/
	
	//校验部门信息列表的起始日期与结束日期
	var agentDepartInfo = $("#agentDepartInfoTable").datagrid("getRows");
	for(var i=0;i<agentDepartInfo.length;i++){
		var departStartDate = agentDepartInfo[i].startDate;
		var departEndDate = agentDepartInfo[i].endDate;
		if(!compareDate(departStartDate, departEndDate)){
			$.messager.alert('提示', "部门信息列表第"+(i+1)+"行日期输入有误，结束日期不能早于开始日期！", 'info');
			return false;
		}
	}
	return true;
}

//提交
function submit(){
	//校验提交数据的合法性
	if(!checkData()){
		return;
	}
	//封装数据，准备提交
	var param = {};
	//Agent基本信息
	var agentBaseInfoJsonStr = formDataToJsonStr($("#addAgent_BaseInfoForm").serialize());
	param.agentBaseInfo = agentBaseInfoJsonStr;
	//家庭信息
	var agentFamilyData = $("#agentFamilyInfoTable").datagrid("getRows");
	param.agentFamilyInfoList = agentFamilyData;
	//工资卡信息
	var agentSalaryAccData = $("#agentSalaryAccTable").datagrid("getRows");
	param.agentSalaryAccInfoList = agentSalaryAccData;
	//资格证信息
	var agentCertificationData = $("#agentCertificationTable").datagrid("getRows");
	param.agentCertificationInfoList = agentCertificationData;
	//职级信息
	var agentPositionInfoData = $("#agentPositionInfoTable").datagrid("getRows");
	param.agentPositionInfoList = agentPositionInfoData;
	//培育信息
	var agentNurserInfoData = $("#agentNurserInfoTable").datagrid("getRows");
	param.agentNurserInfoList = agentNurserInfoData;
	//从业经历信息
	var agentWorkInfoData = $("#agentWorkInfoTable").datagrid("getRows");
	param.agentWorkInfoList = agentWorkInfoData;
	//网点信息
	/*var agentStoreInfoData = $("#agentStoreInfoTable").datagrid("getRows");
	if(agentStoreInfoData.length==0){
		$.messager.alert('提示', "请填写员工所属网点信息");
		return;
	}
	param.agentStoreInfoList = agentStoreInfoData;*/
	//部门信息
	var agentDepartInfoData = $("#agentDepartInfoTable").datagrid("getRows");
	if(agentDepartInfoData.length==0){
		$.messager.alert('提示', "请填写员工所属部门信息");
		return;
	}
	param.agentDepartInfoList = agentDepartInfoData;
	
	//其他信息
	var agentOtherInfoData = $("#agentOtherInfoTable").datagrid("getRows");
	param.agentOtherInfoList = agentOtherInfoData;
	param.operate = operate;
	$.ajax({
		type:'post',
		url:contextPath+"/sales/submitAgent",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
		//console.info(reData);
			try {
				//var result = $.parseJSON(reData);
				if(reData.success){
					$.messager.alert('提示', "提交成功", 'info');
					$("#addAgent_agentId").val(reData.obj.agentId);
					$("#addAgent_userId").val(reData.obj.userId);
					queryAgentInfo();
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function clearAllData(){
	$("#addAgent_BaseInfoForm").form("clear");
	$("#addAgent_agentPositionForm").form("clear");
	$("#addAgent_agentStoreForm").form("clear");
	$("#addAgent_agentDepartForm").form("clear");
	clearAllRows(agentFamilyInfoTable);
	clearAllRows(agentSalaryAccTable);
	clearAllRows(agentCertificationTable);
}

function backListAgentPage(){
	$('#addAgentWindow').window('destroy');
	if(operate!=null&&operate!=""&&operate!=undefined&&operate!="agentDetail"){
		queryAgentList() ;
	}
}



function openUploadImageWindow(){
	var agentId = $("#addAgent_agentId").val();
	var agentImage = $("#addAgent_agentImage").val();
	if(agentId==null||agentId==""||agentId==undefined){
		$.messager.alert('提示', "未获取到财富顾问信息！");
		return;
	}
	var param = {};
	param.agentId = agentId;
	param.agentImage = agentImage;
	param.operate = operate;
	
	$('<div id="openUploadImageWindow"/>').dialog({
		href : contextPath + "/sales/uploadAgentPhoto?param="+$.toJSON(param),
		modal : true,
		title : "财富顾问照片上传",
		//fit : true,
		width:600,
		height:400,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
			if(operate!="agentDetail"){
				queryAgentInfo();
			}
		}
	});
}
