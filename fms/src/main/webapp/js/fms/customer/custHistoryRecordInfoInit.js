var questionAnswerScoreList;
var questionAnswerList;
var answerResultList;
var operate;
var custBaseInfoId;
var custQuestionnaireId;
/**
 * 页面要素初始化
 **/
jQuery(function(){
	operate = $("#historyRecord_loadFlag").val();
	custBaseInfoId = $("#modifyCustomerQuestionnaireInfo_custBaseInfoId").val();
	custQuestionnaireId=$("#historyRecord_custQuestionnaireId").val();
//	alert(custQuestionnaireId);
	//alert(operate);
	//console.info(operate);
	initCombobox();
	if(operate=="custDetail"){
	//getCustQuestionnaireInfo();
		
		initQuestionAndGetCustQuestionnaire();
}
//	if(operate=="updateCust"||operate=="custDetail"){
//		//getCustQuestionnaireInfo();
//		initQuestionAndGetCustQuestionnaireInfo();
//		
//	}
/*//	if(operate=="custDetail"){
		$("#modifyCustomerQuestionnaireInfo_submitQuestionnaireInfoButton").hide();
		$("#uploadQuestionnaireAttachInfoButton").hide();
		$("#queryQuestionnaireAttachInfoButton").show();
	}else{
		$("#uploadQuestionnaireAttachInfoButton").show();
		$("#queryQuestionnaireAttachInfoButton").hide();
		initQuestionAndGetCustQuestionnaireInfo();
	}*/
});



function initCombobox(){
	/*$("#historyRecord_SubmitDate").datebox({
		required:true
	});
	*/
	var disable = false;
	if(operate=="custDetail"){
		disable = true;
	}
	
	$("#historyRecord_questionnaireType").combobox({
		valueField:'code',    
	    textField:'codeName',  
//	    required:true,
	   // disabled:disable,
	    url:'codeQuery/tdCodeQuery?codeType=questionnaireType',
	    onSelect:function(record){
	    	initQuestionnaires(record.code);
	    }
	});
}

/**
 * 查询历史记录中的详情是进入页面调用此方法
 * */
function initQuestionAndGetCustQuestionnaire(){
	$.ajax({
		type:'post',
		url:'modifyCustomer/getCustHistoryRecordInfo',
		data:'custQuestionnaireId='+custQuestionnaireId,
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
						var custRiskLevelLabel = document.getElementById('riskhistoryRecordLevel');
						custRiskLevelLabel.innerHTML = jsonObj.custRiskLevel;
						initQuestionnaires(questionnaireType);
						setQuestionAnswers(answerResultList);
						//setQuestionAnswer(answerResults);
						setInputValueById("historyRecordDiv1",jsonObj);
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
 * 根据问卷编码获取问卷所有问题
 * 
 * */
function initQuestionnaires(questionnaireType){
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
					showAllQuestions(jsonObj);
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
function showAllQuestions(questionList){
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
	$("#historyRecordTb tr:not(:first)").empty();
	$("#historyRecordTb").append(trHtml);
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
				var allQuestionAndAnswer = $("#allhistoryRecordQuestionAndAnswer").val();
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
				$("#allhistoryRecordQuestionAndAnswer").val($.toJSON(questionAnswerScoreList));
				//calTotalScore(questionAnswerScoreList);
			}
		});
	}
	
}


/**
 * 设置问题答案
 * 
 * */
function setQuestionAnswers(answerResultList){
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



function queryQuestionnaireAttachInfo(){
	var param = {};
	var custQuestionnaireId = $("#historyRecord_custQuestionnaireId").val();
	param.businessNo = custQuestionnaireId
	param.businessType = "07";
	param.flag = "queryFile";
	addFileWindow('文件上传', contextPath+"/fileUpload/fileUploadUrl?param="+$.toJSON(param));
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



