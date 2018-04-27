var json={"total":3,"rows":[{'tradeCode':'1'},{'tradeCode':'2'}]}
var isBack = null;
/*
 * 页面元素初始化
 * 
 */
jQuery(function($) {
	initAllCombobox();//初始化下拉框
	initTradeInputList();//
	//tradeInputList.datagrid("loadData",json);//
	});
/*点击datagrid表格中提交复核按钮触发的函数*/
var submitReCheckButton=function(index){
	$("#tradeInputListId").datagrid('selectRow',index);
	var tradeInputListCheckData = $("#tradeInputListId").datagrid('getSelected');
	isBack = tradeInputListCheckData.isBack;
	addTradeInfo("更新交易信息","","update");	
}
function initAllCombobox(){
	$("#currency").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=currency',
		valueField:'code',
		textField:'codeName'
	});
	$("#tradeInputProduct").combobox({
		url:contextPath+'/codeQuery/productQueryAll',
		valueField:'code',
		textField:'codeName'
	});
	$("#tradeStaus").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=tradeStatus',
		valueField:'code',
		textField:'codeName'
	});
	// 分公司：代码-名称
	$("#tradeInput_tradeComId").combobox({
		url : contextPath + '/codeQuery/comQuery',
		valueField : 'comId',
		textField : 'comName'
	});	
	 //财富顾问
	$("#tradeInput_agentId").combobox({
    	url:contextPath+'/codeQuery/agentQuery',
		valueField:'id',
		textField:'name',
		onShowPanel : function(){
			var comId = $("#tradeInput_tradeComId").combobox("getValue");
			if(comId==null||comId==""||comId==undefined){
				var url = contextPath+'/codeQuery/agentQuery';
			}else{
				var url = contextPath + '/codeQuery/defAgentQuery?codeType='+ comId;
			}
			$(this).combobox("clear");
			$(this).combobox("reload",url);
		}
	});
	// 是否出具确认书
	$("#tradeInput_isPrintTradeConfirmPDF").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=isPrintTradeConfirmPDF',
		valueField : 'code',
		textField : 'codeName'
	});	
}
/*
 * 初始化交易信息列表
 * 
 */
var tradeInputList;
function initTradeInputList()
{	var  selectIndex = -1;
    var  showRcButton=false; //定义该行是否显示提交复核按钮
	/*var formData = $("#tradeInputQueryTradeListForm").serialize();
	formData = formDataToJsonStr(formData);*/
	tradeInputList=$("#tradeInputListId").datagrid({
		//title:'交易信息列表',
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		checkOnSelect:true,
		selectOnCheck:true,
		singleSelect:true,
		url:'trade/tradeInputQueryTradeList?tradeStaus=01',
		//queryParams:{queryParam:formDataToJsonStr($("#tradeInputQueryTradeListForm").serialize())},
		pageSize:10,
		pageList:[5,10,15,20,25,30],
		 columns:[[  
		           {field:'ck',checkbox:true},    
		           {field:'printBuyTimes',title:'认购申请书打印次数',width:50,hidden:true},
		           {field:'printConfirmTimes',title:'认购确认书打印次数',width:50,hidden:true},
		           {field:'tradeInfoId',title:'交易号码',width:50,hidden:true},
		           {field:'tradeNo',title:'交易号码',width:60,
		        	   formatter:function(value, row, index){
		        		   return "<a href='#'  onclick=addTradeInfo('交易信息详情','"+index+"','query')>"+row.tradeNo+"</a>";   
		        	   }
		           },    
		           {field:'tradeType',title:'交易类型',width:50,hidden:true},
		           {field:'tradeAppant',title:'认购人',width:50}, 
		           {field:'tradeInfoNo',title:'合同号',width:60},
		           {field:'productName',title:'产品名称',width:100}, 
		           {field:'productId',title:'产品Id',width:100,hidden:true}, 
		           {field:'salesStatus',title:'产品状态',width:50},
		           {field:'foundDate',title:'成立日/开放日',width:60},
		          /* {field:'tradeDate',title:'交易日期',width:50},
		           {field:'tradeInputDate',title:'录入日期',width:50},*/
		           {field:'currency',title:'币种',width:50,hidden:true},    
		           {field:'agentId',title:'财富顾问Id',width:50,hidden:true},
		           {field:'agentName',title:'财富顾问',width:50},
		           {field:'companyId',title:'所属机构',width:50,hidden:true},    
		           {field:'storeId',title:'所属机构',width:50,hidden:true},
		           {field:'tradeComId',title:'交易机构Id',width:50,hidden:true},  
		           {field:'tradeComName',title:'交易机构',width:50},  
		          /* {field:'tradeStoreId',title:'交易网点Id',width:50,hidden:true},
		           {field:'tradeStoreName',title:'交易网点',width:50,hidden:true}, */ 
		           {field:'tradeTotalAssets',title:'交易总额',width:50},
		           {field:'chnTradeTotalAssets',title:'交易总额',hidden:true},
		           {field:'tradeStaus',title:'交易状态Code',width:50,hidden:true,formatter:function(value,row,index){
		        	    /*如果交易状态为01或者15本行就显示提交复核按钮*/
		        	   if(value=='01'||value=='15'){
		        		   showRcButton=true;
		        	   }else{
		        		   showRcButton=false;
		        	   }
		        	   return value;
		           }},
		           {field:'tradeStausName',title:'交易状态',width:50},
		           {field:'isPrintTradeConfirmPDF',title:'是否出具确认书',width:60},
		           {field:'action',title:'操作',width:60,formatter:function(value,row,index){
		        	   /*是否显示提交复核按钮*/
		        	   if(showRcButton){
		        		   return "<a class='RcButton'  href='javascript:submitReCheckButton("+index+");'>提交复核</a>";
		        	   }else{
		        		   return '';
		        	   }

		           }},
		           {field:'custAccInfoId',title:'账户信息流水号',width:50,hidden:true},
		           {field:'custAddressInfoId',title:'地址信息流水号',width:50,hidden:true},
		           {field:'isBack',title:'退回标志',width:50,hidden:true}
		           
		       ]],
       toolbar: [{
		   		iconCls: 'icon-add',
		   		text : '新增',
		   		handler: function(){
		   			addTradeInfo("新增交易信息","","insert");
		   		}
		   	},'-',{
		   		iconCls: 'icon-edit',
		   		text : '更新',
		   		handler: function(){
		   			var tradeInputListCheckData = $("#tradeInputListId").datagrid('getSelected');
		   			if(tradeInputListCheckData==null){
		   				$.messager.alert("提示","请选择一条交易信息","info");
		   				return;
		   			}
		   			if(tradeInputListCheckData.tradeStaus=="01"||tradeInputListCheckData.tradeStaus=="15"){
		   				isBack = tradeInputListCheckData.isBack;
		   				addTradeInfo("更新交易信息","","update");	
		   			}else{
		   				$.messager.alert('提示', "请选择交易状态为录入、退回的交易信息！");
		   			}
		   		}
		   	},'-',{
		   		iconCls: 'icon-remove',
		   		text : '删除',
		   		handler: function(){
		   			delTradeInfo();
		   		}
		   	},'-',{
		   		iconCls: 'icon-undo',
		   		text : '撤销',
		   		handler: function(){
		   			cancelTradeInfo();
		   		}
		   	},'-',{
		   		iconCls: 'icon-print',
		   		text : '生成认购书',
		   		handler: function(){
		   			tradePrint();
		   		}
		   	},'-',{
		   		iconCls: 'icon-save',
		   		text : '认购书下载',
		   		handler: function(){
		   			tradeDownload();
		   		}
		   	},'-',{
		   		iconCls: 'icon-print',
		   		text : '生成确认书',
		   		handler: function(){
		   			tradeConfirmPrint();
		   		}
		   	},'-',{
		   		iconCls: 'icon-save',
		   		text : '确认书下载',
		   		handler: function(){
		   			tradeConfirmDownload();
		   		}
		   	}],
		   	/*onClickCell:function (rowIndex, field, value) {
		   		
		   		if(field=="tradeNo"){
					
					addTradeInfo(rowIndex,"query");	
				}
		   	},*/
		   	onLoadSuccess : function() {
		   		$("#tradeInputListId").datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		   		/*把超链接变成按钮样式*/
		   		$('.RcButton').linkbutton({		   		
		   		});  


		   	},
		   
		   	/*rowStyler:function(index,row){
   				if (row.isBack!=null && row.isBack=='Y'){
   					return 'background-color:pink;color:blue;font-weight:bold;';
   				}
   			},*/
			onClickRow: function (rowIndex, rowData) {
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

/*
 * 交易信息新增
 * 
 */
function addTradeInfo(title,rowIndex,type){
	var tradeInputListData = $("#tradeInputListId").datagrid('getRows');
	var tradeInputListCheckData = $("#tradeInputListId").datagrid('getSelected');
	var param = {};
	if(type != "insert"){
		param.contractNum = tradeInputListCheckData.tradeInfoNo;
	}else{
		param.contractNum = "";
	}
	$('<div id="tradeInfoInput"/>').dialog({
			href : contextPath + "/trade/tradeInputInfo?param="+$.toJSON(param),
			modal : true,
			fit:true,
			title : title,
			inline : false,
			minimizable : false,
			onLoad:function(){ //当加载成功的时候隐藏三个div
				$("#tradeInputCheckConclusionDiv").hide();
				$("#tradeInputAuditConclusionDiv").hide();
				$("#addressInfoInputPanelId").hide();
				if(type=="update" || type=="query"){
					var totalAssets = 0;
					var chnTradeTotalAssets = 0;
					var custAccInfoId = null;
					var custAddressInfoId = null;
					var tradeInfoId = null;
					if(type=="update"){
						$('#tradeInfoForm_input').form('load', tradeInputListCheckData);
						totalAssets = tradeInputListCheckData.tradeTotalAssets;
						chnTradeTotalAssets = tradeInputListCheckData.chnTradeTotalAssets;
						custAccInfoId = tradeInputListCheckData.custAccInfoId;
						custAddressInfoId = tradeInputListCheckData.custAddressInfoId;
						tradeInfoId = tradeInputListCheckData.tradeInfoId;
						if(isBack=="Y"){
							$("#tradeInputCheckConclusionDiv").show();
							initTradeCheckConclusion();
						}
					}else if(type=="query"){
						totalAssets = tradeInputListData[rowIndex].tradeTotalAssets;
						chnTradeTotalAssets = tradeInputListData[rowIndex].chnTradeTotalAssets;
						custAccInfoId = tradeInputListData[rowIndex].custAccInfoId;
						custAddressInfoId = tradeInputListData[rowIndex].custAddressInfoId;
						tradeInfoId = tradeInputListData[rowIndex].tradeInfoId;
						//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型
						$('#tradeInfoForm_input').form('load', tradeInputListData[rowIndex]);
						$("#submitTradeInfoButton").hide();
						$("#submitTradeCusInfoButton").hide();
						$("#submitTradeRoleButton").hide();
						$("#submitRiskProButton").hide();
						$("#deleteRiskProButton").hide();
						$("#submitWealthProButton").hide();
						$("#submitTradeInputButton").hide();
						$("#submitTradeCustomerInfoButton").hide();
						$('#chooseCust_input').combobox('disable');						
						$("#submitTradeInputBankInfoButton").hide();
						$("#custOperationPrompt").hide();
						$("#deleteWealthProButton").hide();
						$("#submitTradeInputAddressInfoButton").hide();
						$("#riskProInputSelectId").hide();
						$("#monProInfoSelectDiv").hide();
						loadFileType = "queryFile";
						
						var tradeStatus = tradeInputListData[rowIndex].tradeStaus;
						if(tradeStatus!=null && tradeStatus=="04"){
							$("#tradeInputCheckConclusionDiv").show();
							$("#tradeInputAuditConclusionDiv").show();
							initTradeCheckConclusion();
							initTradeAuditConclusion();
						}
					}
					
					initCustomInfoId();
					initRoleInfoId();
					if(custAccInfoId!=null && custAccInfoId!=""){
						initTradeBankInfoInputId(custAccInfoId,"search");
					}else{
						initTradeBankInfoInputId(tradeInfoId,"query");
					}
					var tradeType_Input = $("#tradeType_Input").combobox('getValue');
					if(tradeType_Input=="2")
					{
						$("#roleInfoInputPanelId").show();
						$("#riskProInputInfoPanelId").show();
						$("#monProInfoInputPanelId").hide();
						//初始化保险产品信息
						initRiskProInputInfoId(tradeType_Input);
						$("#riskTotalAssets_Input").val(totalAssets);
						$("#chnRiskTotalAssets_Input").val(chnTradeTotalAssets);
					}else if(tradeType_Input=="1"){
						$("#roleInfoInputPanelId").hide();
						$("#riskProInputInfoPanelId").hide();
						$("#monProInfoInputPanelId").show();
						//初始化财富产品信息
						initMonryProductInfoId(tradeType_Input);
						$("#monTotalAssets_Input").val(totalAssets);
						$("#chnMonTotalAssets_Input").val(chnTradeTotalAssets);
						
						$("#addressInfoInputPanelId").show();
						if(custAddressInfoId!=null && custAddressInfoId!=""){
							initTradeAddressInfoInputId(custAddressInfoId,"search");
						}else{
							initTradeAddressInfoInputId(tradeInfoId,"query");
						}
					}
					$("#tradeRiskProtObj").combobox({
						url:contextPath+'/trade/queryTradeRiskProtObj?tradeInfoID='+$("#tradeId_Input").val(),
						valueField:'custID',
						textField:'custName'
					});
					
					if(type=="query"){
						$("#customInfoInputId").parent().prev("div.datagrid-toolbar").hide();
						$("#roleInfoInputId").parent().prev("div.datagrid-toolbar").hide();
						$("#riskProInputInfoId").parent().prev("div.datagrid-toolbar").hide();
						$("#monProInfoInputId").parent().prev("div.datagrid-toolbar").hide();
					}
					
					$("#tradeType_Input").combobox('disable');
					
				}else if(type=="insert"){
					//agentID_Input
					//companyID_Input
					//storeID_Input
					$.ajax({
						type:'post',
						url:contextPath+'/trade/queryTradeAgentId?isAdd=true',
						data:'isAdd=true',
						success:function(returnData){
							$("#agentID_Input").combobox("setValue",returnData[0].agentId);
							$("#agentID_Input").combobox("setText",returnData[0].agentCodeName);
							$("#companyID_Input").combobox("setValue",returnData[0].comId);
							$("#companyID_Input").combobox("setText",returnData[0].comCodeName);
							$("#storeID_Input").combobox("setValue",returnData[0].storeId);
							$("#storeID_Input").combobox("setText",returnData[0].storeCodeName);
							$("#tradeComID_Input").combobox("setValue",returnData[0].comId);
							$("#tradeComID_Input").combobox("setText",returnData[0].comCodeName);
							$("#tradeStoreID_Input").combobox("setValue",returnData[0].storeId);
							$("#tradeStoreID_Input").combobox("setText",returnData[0].storeCodeName);
							
						}
					
					});
					$.messager.alert('提示', '1、认购产品请从产品预约菜单中进行预约。	'+'&nbsp; 2、已预约产品已自动生成交易,请返回并选择相应交易提交复核。');
				}
			},
			//在面板关闭之后触发
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
}

//查询交易信息列表
function queryTradeInfoList(){
	/*var formData = $("#tradeInputQueryTradeListForm").serialize();
	formData = formDataToJsonStr(formData);
	console.info(formData);
	$.ajax({
		type:'post',
		url:'trade/tradeInputQueryTradeList',
		data:'queryParam='+formData,
		cache:false,
		success:function(returnData){
			console.info(returnData);
			try {
				//var result = $.parseJSON(returnData);
				//console.info(result);
				if(returnData.success){
					tradeInputList.datagrid('loadData',returnData.obj);				
				}else{
					$.messager.alert('提示', returnData.msg);
				}
				//$.messager.alert('提示', result.msg);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});*/
	//console.info(tradeInputList.pageNumber);
	//console.info(tradeInputList.datagrid('getPage'));
	//tradeInputList.datagrid();
	tradeInputList.datagrid('load',{queryParam:formDataToJsonStr($("#tradeInputQueryTradeListForm").serialize())});	
}

/*
 * 清空查询条件
 */
function clearTradeInfo(){
	$('#tradeInputQueryTradeListForm').form('clear');
	//initTradeInputList();
}

function delTradeInfo(){
	var tradeInfoData = $("#tradeInputListId").datagrid("getSelections");
	if (tradeInfoData.length == 0) {
		$.messager.alert('提示', "请选择需要删除交易！","info");
		return;
	}
	/*if(!confirm("是否确认删除交易信息?")){
		return;
	}*/
	$.messager.confirm('提示',"是否确认删除交易信息?",function(r){
		if(r){
			if(tradeInfoData[0].tradeStaus!="09"){
				$.messager.alert('提示', "只能删除交易状态为撤销的交易！");
				return;
			}
			$.messager.progress({
				title:'温馨提示',
				msg:'正在删除交易信息，请稍后...'
			});
			tradeInfoData = $.toJSON(tradeInfoData);
			
			$.ajax({
				type:'post',
				url:'trade/delTradeInfo',
				data:'tradeInfoData='+tradeInfoData,
				cache:false,
				success:function(returnData){
					try {
						if(returnData.success=="true"){
							$.messager.progress('close');
							$.messager.alert('提示', returnData.msg);
							$("#tradeInputListId").datagrid("reload");
						}else{
							$.messager.progress('close');
							$.messager.alert('提示', returnData.msg);
						}
					} catch (e) {
						$.messager.progress('close');
						$.messager.alert('提示', e);
					}
				}
			
			});
		}else{
			return;
		}
	});
	
}


/*
 * 交易认购书打印
 */
function tradePrint(){
	var rows = $('#tradeInputListId').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择需要生成认购申请书的交易！");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一条交易进行打印！");
		return;
	}
	var oneRowData = rows[0];
	var code =oneRowData.tradeStaus;
	//console.info(typeof code);
	if(code!="04"& code!="10"){
		$.messager.alert('提示', "请选择状态为审核通过或成立的交易！");
		$("#tradeInputListId").datagrid("reload");
		return;	
	}
	$.messager.confirm('提示', '确定要生成认购申请书吗?', function(result) {
		if (result) {
			var ps = ""; 
			var isPrint = false;
			$.each(rows, function(i, n) {
				if (i == 0){
					if(n.tradeType!=1){
						$.messager.alert('提示', "此交易非财富类交易，不能生成认购申请书！");
						isPrint = false;
					}else{
						isPrint = true;
					ps += "?tradeInfoId=" + n.tradeInfoId;}
					}
			});
			if(isPrint){
				$.messager.progress({
					title:'温馨提示',
					msg:'正在生成PDF文件，请稍后...'
				});
				$.post('trade/tradePrintList' + ps, function(reData){
						if(reData.success){
							$.messager.progress('close');
							$.messager.alert('提示', reData.msg);
							$("#tradeInputListId").datagrid("reload");
						}else{
							$.messager.progress('close');
							$.messager.alert('提示', reData.msg);
						}
				});
			}
			
		}
	});
}
/*
 * 交易确认书打印
 */
function tradeConfirmPrint(){
	var rows = $('#tradeInputListId').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择需要生成认购确认书的交易！");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一条交易进行打印！");
		return;
	}
	var oneRowData = rows[0];
	var code =oneRowData.tradeStaus;
	//console.info(typeof code);
	if( code!="10"){
		$.messager.alert('提示', "请选择状态为成立的交易！");
		$("#tradeInputListId").datagrid("reload");
		return;	
	}
	$.messager.confirm('提示', '确定要生成认购确认书吗?', function(result) {
		if (result) {
			var param = {}; 
			var isPrint = false;
			$.each(rows, function(i, n) {
				if (i == 0){
					if(n.tradeType!=1){
						$.messager.alert('提示', "此交易非财富类交易，不能生成认购确认书！");
						isPrint = false;
					}else{
						isPrint = true;
					param.tradeInfoId = n.tradeInfoId;
					param.productName = n.productName;}
					}
			});
			if(isPrint){
				$.messager.progress({
					title:'温馨提示',
					msg:'正在生成PDF文件，请稍后...'
				});
				$.ajax({
					type:'post', 
					url:contextPath+"/trade/tradeConfirmPrintList",
					data:'param='+$.toJSON(param),
					cache:false,
					success:function(reData){
						if(reData.success){
							$.messager.progress('close');
							$.messager.alert('提示', reData.msg);
							$("#tradeInputListId").datagrid("reload");
						}else{
							$.messager.progress('close');
							$.messager.alert('提示', reData.msg);
						}
					}
				});
				/*$.post('trade/tradeConfirmPrintList' +"?param="+ param, function(reData){
						if(reData.success){
							$.messager.progress('close');
							$.messager.alert('提示', reData.msg);
							$("#tradeInputListId").datagrid("reload");
						}else{
							$.messager.progress('close');
							$.messager.alert('提示', reData.msg);
						}
				});*/
			}
			
		}
	});
}
/*
 *认购书下载 
 */
function tradeDownload(){
	var rows = $('#tradeInputListId').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择交易进行认购申请书下载！");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一条交易进行下载！");
		return;
	}
	var param = {};
	var tradeNo = rows[0].tradeNo;
	param.tradeNo=tradeNo;
	param = $.toJSON(param);
	
	if(rows[0].tradeType!=1){
		$.messager.alert('提示', "此交易非财富类交易无认购申请书！");
	}else if(rows[0].printBuyTimes==0){
		$.messager.alert('提示', "此交易认购申请书尚未生成，请先生成认购申请书！");
	}else{
	addDownloadWindow('认购申请书下载', contextPath+"/trade/printDown?param="+param);
	}
}
/*
 *确认书下载 
 */
function tradeConfirmDownload(){
	var rows = $('#tradeInputListId').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', "请选择交易进行认购确认书下载！");
		return;
	}
	if (rows.length > 1) {
		$.messager.alert('提示', "只能选择一条交易进行下载！");
		return;
	}
	var param = {};
	var tradeNo = rows[0].tradeNo;
	param.tradeNo=tradeNo;
	param = $.toJSON(param);
	
	if(rows[0].tradeType!=1){
		$.messager.alert('提示', "此交易非财富类交易无认购确认书！");
	}else if(rows[0].printConfirmTimes==0){
		$.messager.alert('提示', "此交易认购确认书尚未生成，请先生成认购确认书！");
	}else{
	addDownloadWindow('认购确认书下载', contextPath+"/trade/printConfirmDown?param="+param);
	}
}
/**
 * 理财经理交易撤销，只能撤销状态为01-录入中，02-复核中，03-审核中的交易
 **/
function cancelTradeInfo(){
	var tradeInfoData = $("#tradeInputListId").datagrid("getSelections");
	if (tradeInfoData.length == 0) {
		$.messager.alert('提示', "请选择需要撤销的交易！");
		return;
	}
	/*if(!confirm("是否确认撤销该笔交易?")){
		return;
	}*/
	$.messager.confirm('提示', '是否确认撤销该笔交易?', function(result) {
		if(result){
			tradeInfoData = $.toJSON(tradeInfoData[0]);
			$.ajax({
				type:'post',
				url:'trade/cancelTradeInfo',
				data:'tradeInfoParam='+tradeInfoData,
				cache:false,
				success:function(returnData){
					try {
						if(returnData.success){
							$.messager.alert('提示', returnData.msg);
							$("#tradeInputListId").datagrid("reload");
						}else{
							$.messager.alert('提示', returnData.msg);
						}
					} catch (e) {
						$.messager.alert('提示', e);
					}
				}
			
			});
		}else{
			return;
		}
	})
	
}

function addDownloadWindow(title, href) 
{
	$('<div id="addPrintWindow"/>').dialog({
	href : href,
	modal : true,
	title : title,
	width:800,
	height:500,
	inline : false,
	minimizable : false,
	onClose : function() {
	$(this).window('destroy');
		}
	});
}