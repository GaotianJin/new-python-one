var questionAnswerScoreList;
var questionAnswerList;
var answerResultList;
var operate;
var custBaseInfoId;
/**
 * 页面要素初始化
 **/
jQuery(function(){
	operate = $("#modifyCustomerQuestionnaireInfo_loadFlag").val();
	custBaseInfoId = $("#modifyCustomerQuestionnaireInfo_custBaseInfoId").val();
	historyRecordTable();
	initCombobox();
//	alert(operate);
	if(operate=="custDetail"){
	//getCustQuestionnaireInfo();
	//initQuestionAndGetCustQuestionnaireInfo();
		
	
}
	if(operate=="updateCust"||operate=="custDetail"){
		//getCustQuestionnaireInfo();
//		initQuestionAndGetCustQuestionnaireInfo();
		
	}
	if(operate=="custDetail"){
		$("#modifyCustomerQuestionnaireInfo_submitQuestionnaireInfoButton").hide();
		$("#queryQuestionnaireAttachInfoButton").hide();
		$("#uploadQuestionnaireAttachInfoButton").hide();
		$("#modifyCustomerQuestionnaireInfoDiv1").hide();
	}else{
		$("#uploadQuestionnaireAttachInfoButton").show();
		$("#queryQuestionnaireAttachInfoButton").hide();
		initCombobox();
	}
});



function initCombobox(){
	/*$("#modifyCustomerQuestionnaireInfo_SubmitDate").datebox({
		required:true
	});
	*/
	var disable = false;
	if(operate=="custDetail"){
		disable = true;
	}
	
	$("#modifyCustomerQuestionnaireInfo_questionnaireType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    required:true,
	    disabled:disable,
	    url:'codeQuery/tdCodeQuery?codeType=questionnaireType',
	    onSelect:function(record){
	    	initQuestionnaire(record.code);
	    }
	});
}

/**
 * 根据问卷编码获取问卷所有问题
 * 
 * */
function initQuestionnaire(questionnaireType){
	if(questionnaireType == "01"){
		questionnaireType = "03";
	}else{
		questionnaireType = "04";
	}
	$.ajax({
		type:'post',
		async: false, // 设置为同步方法
		url:'modifyCustomer/getQuestionnaireQuestion',
		data:'questionnaireType='+questionnaireType,
		cache:false,
		success:function(returnData){
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					showAllQuestion(jsonObj);
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}


/**
 * 显示所有问题
 * */
function showAllQuestion(questionList){
	var trHtml = "";
	//循环处理每一个问题
	for(var i=0;i<questionList.length;i++){
		var questionAndAnswerList = questionList[i];
		//问题
		var question = questionAndAnswerList.question;
		//问题答案集合
		var questionAnswerList = questionAndAnswerList.questionAnswerList;
		//问题编码
		var questionCode = question.questionCode;
		//问题序号
		var questionNo = question.questionNo;
		//问题内容
		var questionContent = question.questionContent;
		//问题标题行
		var questionTrHtml = '<tr><td class="table_text" align="right">'+questionNo
							+'<input type="hidden" id="'+questionCode+'No" value="'+questionNo+'"></td>'
							+'<td align="left" colspan=5><span class="comboSpan"></span>'+questionContent
							+'<input type="hidden" name="questionCode'+questionNo+'" value="'+questionCode+'"></td></tr>';
		
		//问题答案行
		var questionAnswerListTrHtml = '<tr><td class="table_text" align="right"></td>'
							+'<td align="left" colspan=5><span class="comboSpan"></span>'
							+'<input id="'+questionCode+'" class="easyui-combobox table_input" data-options=\''
							+'required:true,width:400,valueField:"answerCode",textField:"answerContent",data:'+$.toJSON(questionAnswerList)+'\'/>'
							+'</td></tr>';
		trHtml += questionTrHtml+questionAnswerListTrHtml;
	}
	$("#questionnaireTb tr:not(:first)").empty();
	$("#questionnaireTb").append(trHtml);
	//初始化下拉框组件
	questionAnswerScoreList = new Array(questionList.length);
	questionAnswerList = new Array(questionList.length);
	var disable = false;
	if(operate=="custDetail"){
		disable = true;
	}
	for(var i=0;i<questionList.length;i++){
		var questionAndAnswerList = questionList[i];
		var question = questionAndAnswerList.question;
		$("#"+question.questionCode).combobox({
			editable:false,
			disabled:disable,
			onSelect:function(record){
				var allQuestionAndAnswer = $("#allQuestionAndAnswer").val();
				if(allQuestionAndAnswer!=null&&allQuestionAndAnswer!=""&&allQuestionAndAnswer!=undefined){
					questionAnswerScoreList = eval('('+allQuestionAndAnswer+')');
				}
				var questionAnswerAndScore = {};
				var questionAnswer = {};
				var questionCode = record.questionCode;
				var answerCode = record.answerCode;
				var answerScore = record.answerScore;
				//var questionNo = question.questionNo;
				questionAnswerAndScore.questionCode = questionCode;
				questionAnswerAndScore.answerCode = answerCode;
				questionAnswerAndScore.answerScore = answerScore;
				questionAnswer.questionCode = questionCode;
				questionAnswer.answerCode = answerCode;
				var questionNo = $("#"+questionCode+"No").val();
				questionAnswerScoreList[parseInt(questionNo)-1] = questionAnswerAndScore;
				questionAnswerList[parseInt(questionNo)-1] = questionAnswer;
				//console.info(questionAnswerScoreList);
				$("#allQuestionAndAnswer").val($.toJSON(questionAnswerScoreList));
				//calTotalScore(questionAnswerScoreList);
			}
		});
	}
	
	
}

/**
 * 计算总得分
 * */
function calTotalScore(questionList){
	var score = 0;
	for(var i=0;i<questionList.length;i++){
		var question = questionList[i];
		if(question!=null&&question!=undefined){
			score += question.answerScore;
		}
	}
	$("#modifyCustomerQuestionnaireInfo_score").val(score);
}


/**
 * 保存客户所选择的的问卷信息
 * */
function submitModifyCustomerQuestionnaireInfo(){
	if(!$("#modifyCustomerQuestionnaireWealthInfoForm").form("validate")){
		$.messager.alert('提示', "请填写相关必录项！");
		return;
	}
	//获取所有问题答案
	var answerResults = $("#allQuestionAndAnswer").val();
	//console.info(answerResults);
	calTotalScore(eval('(' + answerResults + ')'));
	var submitDate = $("#modifyCustomerQuestionnaireInfo_SubmitDate").datebox("getValue");
	var score = $("#modifyCustomerQuestionnaireInfo_score").val();
	var questionnaireType = $("#modifyCustomerQuestionnaireInfo_questionnaireType").combobox("getValue");
	if(questionnaireType == "01"){
		questionnaireType = "03";
	}else{
		questionnaireType = "04";
	}
	var custBaseInfoId = $("#modifyCustomerQuestionnaireInfo_custBaseInfoId").val();
	var agentId = $("#modifyCustomerQuestionnaireInfo_agentId").val();
	var custQuestionnaireId = $("#modifyCustomerQuestionnaireInfo_custQuestionnaireId").val();
	
	
	var param = {};
	param.answerResults = answerResults;
	param.submitDate = submitDate;
	param.score = score;
	param.questionnaireType = questionnaireType;
	param.custBaseInfoId = custBaseInfoId;
	param.agentId = agentId;
	param.custQuestionnaireId = custQuestionnaireId;
	
	//保存问卷数据
	$.ajax({
		type:'post',
		url:'modifyCustomer/saveQuestionnaireQuestionInfo',
		data:'param='+$.toJSON(param),
		cache:false,
		success:function(returnData){
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					$.messager.alert('提示', returnData.msg);
					if(jsonObj!=null&&jsonObj!=undefined){
						$("#modifyCustomerQuestionnaireInfo_custQuestionnaireId").val(jsonObj.custQuestionnaireId);
						var custRiskLevelLabel = document.getElementById('riskLevel');
						custRiskLevelLabel.innerHTML = jsonObj.custRiskLevel;
						$('#modifyCustomerQuestionnaireInfo_submitQuestionnaireInfoButton').linkbutton('disable');
					}
					//getCustQuestionnaireInfo();
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
	
}

/**
 * 查询客户风控问卷信息
 * */
function getCustQuestionnaireInfo(){
	var custBaseInfoId = $("#modifyCustomerQuestionnaireInfo_custBaseInfoId").val();
	$.ajax({
		type:'post',
		url:'modifyCustomer/getCustQuestionnaireInfo',
		data:'custBaseInfoId='+custBaseInfoId,
		cache:false,
		success:function(returnData){
			try {
				var jsonObj = returnData.obj;
				if(returnData.success){
					if(jsonObj!=null&&jsonObj!=undefined){
						setQuestionAnswer(jsonObj.answerResults);
						setInputValueById("modifyCustomerQuestionnaireInfoDiv1",jsonObj);
					}
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

/**
 * 修改和查询是进入页面调用此方法
 * */
function initQuestionAndGetCustQuestionnaireInfo(){
	var custBaseInfoId = $("#modifyCustomerQuestionnaireInfo_custBaseInfoId").val();
	$.ajax({
		type:'post',
		url:'modifyCustomer/getCustQuestionnaireInfo',
		data:'custBaseInfoId='+custBaseInfoId,
		cache:false,
		success:function(returnData){
			try {
				var jsonObj = returnData.obj;
				//console.info(jsonObj);
				if(returnData.success){
					if(jsonObj!=null&&jsonObj!=undefined){
						var questionnaireType = jsonObj.questionnaireType;
						answerResultList = jsonObj.answerResults;
						//设置风险等级
						var custRiskLevelLabel = document.getElementById('riskLevel');
						custRiskLevelLabel.innerHTML = jsonObj.custRiskLevel;
						initQuestionnaire(questionnaireType);
						setQuestionAnswer(answerResultList);
						//setQuestionAnswer(answerResults);
						setInputValueById("modifyCustomerQuestionnaireInfoDiv1",jsonObj);
					}
				}else{
					$.messager.alert('提示', returnData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});
}

/**
 * 设置问题答案
 * 
 * */
function setQuestionAnswer(answerResultList){
	answerResultList = eval('(' + answerResultList + ')');
	for(var i=0;i<answerResultList.length;i++){
		var questionAndAnswer = answerResultList[i];
		if(questionAndAnswer!=null&&questionAndAnswer!=undefined){
			var questionCode = questionAndAnswer.questionCode;
			var answerCode = questionAndAnswer.answerCode;
			$("#"+questionCode).combobox("setValue",answerCode);
		}
	}
}



function uploadQuestionnaireAttachInfo(){
	var param = {};
	var custQuestionnaireId = $("#modifyCustomerQuestionnaireInfo_custQuestionnaireId").val();
	if(custQuestionnaireId==null||custQuestionnaireId==""||custQuestionnaireId==undefined){
		$.messager.alert('提示', "请先保存客户风控问卷信息！");
		return;
	}
	param.businessNo = custQuestionnaireId
	param.businessType = "07";
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
}

/**
 *风控问卷历史记录查询
 */
var historyRecordTable;
var custBaseInfoId = $("#modifyCustomerQuestionnaireInfo_custBaseInfoId").val();
function historyRecordTable(){
	historyRecordTable = $('#historyRecordTable').datagrid({
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/modifyCustomer/getHistoryRecord",
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {"custBaseInfoId":custBaseInfoId}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:5,
		
		columns : [[
		            {
		            	field : 'ck',
		            	checkbox : true
		            },
		            {
		            	field : 'custQuestionnaireId',
		            	title : '风控流水号',
		            	hidden:true,
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.custQuestionnaireId;
		            	}
		            	
		            },
		            {
		            	field : 'custQuestionnaireState',
		            	title : '问卷类型',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.custQuestionnaireState;
		            	}
		            	
		            },
		            {
		            	field : 'submitDate',
		            	title : '调查日期',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		var param = {};
							param.loadFlag = "custDetail";
//							param.custBaseInfoId = custBaseInfoId;
							param.custQuestionnaireId =row.custQuestionnaireId;
							param = $.toJSON(param);
							return "<a href='#'  onclick=openHistoryRecordCustWindow('历史记录','"+contextPath+"/modifyCustomer/custHistoryRecordInfoUrl?param="+param+"')>"+row.submitDate+"</a>";
//		            		return row.submitDate;
		            	}
		            	
		            },
		            {
		            	field : 'score',
		            	title : '得分',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.score;
		            	}
		            	
		            },
		            {
		            	field : 'custRiskLevel',
		            	title : '风险等级',
		            	width : 200,
		            	formatter : function(value,row,index){
		            		return row.custRiskLevel;
		            	}
		            	
		            }
		            ]]
	});
}

function openHistoryRecordCustWindow(title, href){
	$('<div id="historyRecordCustWindow"/>').dialog({
		href :href,
		//type : "post",
		modal : true,
		title : title,
		//fit : true, 
		width:1000,
		height:600,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}

function addFileWindow(title, href) {
	$('<div id="addFileWindow"/>').dialog({
		href : href,
		modal : true,
		title : title,
		// fit : true,
		width : 800,
		height : 500,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}



