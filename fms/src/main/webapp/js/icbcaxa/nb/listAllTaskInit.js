jQuery(function($) {
  
	$('#taskTable').datagrid({
		title : '查询结果', // 标题
		method : 'post',
		iconCls : 'icon-search', // 图标
		singleSelect : true, // 多选
		height : 400, // 高度
		width  : '100%',
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : 'queryTaskList?queryFrom=all&queryFlag=0', // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		columns : [[{
			field : 'ck',
			checkbox : true,
			width : 2
			},
			{
				field : 'row.dbid',
				title : 'id',
				width : 0,
				hidden : true,
				sortable : false,
				formatter : function(value, row, index) {
					//var flag = row.taskStatus;
					//flag = flag=='保全'?1:2;
					//return "<a href='#' onclick='getPageUrl("+flag+","+row.BussNo+")'>"+ row.policyNo+"</a>";
					return row.dbid;
				} // 需要formatter一下才能显示正确的数据
			  },
		            
		             {
					field : 'row.policyNo',
					title : '保单号',
					width : 75,
					sortable : false,
					formatter : function(value, row, index) {
						//var flag = row.taskStatus;
						//flag = flag=='保全'?1:2;
						//return "<a href='#' onclick='getPageUrl("+flag+","+row.BussNo+")'>"+ row.policyNo+"</a>";
						return row.policyNo;
					} // 需要formatter一下才能显示正确的数据
				  },
				  {
						field : 'row.BussNo',
						title : '任务号',
						width : 100,
						sortable : false,
						formatter : function(value, row, index) {
							//var flag = row.taskStatus;
							//flag = flag=='保全'?1:2;
											
							//return "<a href='#' onclick='getTaskUrl("+flag+","+row.BussNo+")'>"+row.BussNo+"</a>";
							return row.BussNo;
						} // 需要formatter一下才能显示正确的数据
				  },
				  {
							field : 'row.saleChannel',
							title : '渠道',
							width : 40,
							sortable : false,
							formatter : function(value, row, index) {
								return row.saleChannel;
								
							} // 需要formatter一下才能显示正确的数据
				  },
				  {
						field : 'row.agentCom',
						title : '机构',
						width : 80,
						sortable : false,
						formatter : function(value, row, index) {
							return row.agentCom;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.agentComName',
						title : '机构名称',
						width : 100,
						sortable : false,
						formatter : function(value, row, index) {
							return row.agentComName;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.createTime',
						title : '提交日期',
						width : 110,
						sortable : false,
						formatter : function(value, row, index) {
							return row.createTime;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.operName',
						title : '操作人',
						width : 70,
						sortable : false,
						formatter : function(value, row, index) {
							return row.operName;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.activityName',
						title : '任务状态',
						width : 60,
						sortable : false,
						formatter : function(value, row, index) {
							return row.activityName;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.agentCode',
						title : '网点',
						width : 50,
						sortable : false,
						formatter : function(value, row, index) {
							//return row.username;
							return row.agentCode;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.agentName',
						title : '网点名称',
						width : 130,
						sortable : false,
						formatter : function(value, row, index) {
							//return row.username;
							return row.agentName;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.taskStatus',
						title : '业务类型',
						width : 70,
						sortable : false,
						formatter : function(value, row, index) {
							//return row.username;
							return row.taskStatus;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.productCode',
						title : '主险编码',
						width : 60,
						sortable : false,
						formatter : function(value, row, index) {
							return row.productCode;
						} // 需要formatter一下才能显示正确的数据
			      },
			      {
						field : 'row.productName',
						title : '主险名称',
						width : 130,
						sortable : false,
						formatter : function(value, row, index) {
							return row.productName;
						} // 需要formatter一下才能显示正确的数据
			      }
				  ]],
		onLoadSuccess : function() {
			$('#taskTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
	$('#companyId').combobox({
		  url:'../company/queryCompanyList2',
		  valueField:'id',
		  textField:'name'
	  });
	$('#mainRiskCode').combobox({
		  url:'../nb/queryMainRistList',
		  valueField:'id',
		  textField:'name'
	  });
})

function cancle(){
	//参数1 DBID， 参数2 flag=0
	var row = $('#taskTable').datagrid('getSelected');
	
	if(row==null){
		$.messager.alert('提示信息','未选择要执行的任务','info');
		return;
	}
	
	//操作理赔撤销
	if(row.taskStatus=='理赔'){
		var dbid = row.dbid;
		var flag = '0';
		var selRow = [];
		selRow.push({
			"id" : dbid ,
			"flag" : flag
		});
		$.post('../claim/cancelTask?list='+$.toJSON(selRow), function(data) {
			$.messager.alert('提示', data.mes, 'info');
			if(data.mes=='操作成功'){
				query();
			}
		});
	}
	//操作保全撤销
	if(row.taskStatus=='保全'){
		var dbid = row.BussNo;
		//var selRow = [];
		//selRow.push({
			//"id" : dbid
		//});
		if(row.activityName=='处理中'){
			$.messager.alert('提示', '此任务正在处理阶段，请到操作页面进行撤销', 'info');
		}else if(row.activityName=='复核中'){
			$.messager.alert('提示', '此任务正在处理阶段，请到操作页面进行撤销', 'info');
		}else{
			$.post('../pos/delTaskUrl?id='+dbid, function(data) {
				if(data.msg=='success'){
					$.messager.alert('提示', '操作成功', 'info');
						query();
				}else{
					$.messager.alert('提示', data.msg, 'info');
				}
							
			});
		}
	}
	
}

function detail(){
	//var row = $('#taskTable').datagrid('getSelected');
	//if(row==null)
		//return;
	//if(row.taskStatus=='保全'){
		//parent.addtab('保全任务','pos/getPosTaskInfo?id='+row.BussNo);
	//}
	//if(row.taskStatus=='理赔'){
		//parent.addtab('理赔任务','claim/getCliamBasicInfo?id='+row.BussNo);
	//}
	
	var row = $('#taskTable').datagrid('getSelected');
	if(row==null){
		$.messager.alert('提示信息','未选择要执行的任务','info');
		return;
	}
	if(row.taskStatus=='保全'){
		if(row.activityName=="处理中"){
			parent.addtab('保全处理','pos/getPosPageUrl?id='+row.BussNo+"&flag=noOper");
		}
		else if(row.activityName=="待复核"||row.activityName=="复核中"){
			parent.addtab('保全复核','pos/getPosPageUrl?id='+row.BussNo+"&flag=noOper");
		}
		else if(row.activityName=="结案"){
			parent.addtab('保全复核','pos/getPosPageUrl?id='+row.BussNo+"&flag=caseOver");
		}
		else if(row.activityName=="已撤销"){
			$.messager.alert('提示信息','该任务已撤销，不允许查看任务详情','info');
		}
	}
	else if(row.taskStatus=='理赔'){
		if(row.activityName=="初审中"||
		   row.activityName=="待初审"){
			parent.addtab('理赔初审',
					'claim/claimCheckOrApproveUrl?id='+row.dbid+"&flag=noOper");
		}
	
		if(row.activityName=="复核中"||
		   row.activityName=="待复核"||
		   row.activityName=="结案"){
			parent.addtab('理赔复核',
					'claim/claimCheckOrApproveUrl?id='+row.dbid+"&flag=noOper");
		}
		
		if(row.activityName=="已撤销")
			$.messager.alert('提示信息','该任务已撤销，不允许查看任务详情','info');
	}
}

function toDo(){
	var row = $('#taskTable').datagrid('getSelected');
	if(row==null){
		$.messager.alert('提示信息','未选择要执行的任务','info');
		return;
	}
	if(row.taskStatus=='保全'){
		parent.addtab('保全任务','pos/getPosPageUrl?id='+row.BussNo+"&flag=Oper");
	}
	if(row.taskStatus=='理赔'){
		if(row.activityName=="初审中"){
			parent.addtab('理赔初审','claim/claimCheckOrApproveUrl?id='+row.dbid+"&flag=Oper");
		}
	
		if(row.activityName=="复核中"){
			parent.addtab('理赔复核','claim/claimCheckOrApproveUrl?id='+row.dbid+"&flag=Oper");
		}
	}
}

//function getTaskUrl(flag,bussNo){
	//if(flag==1){
		//parent.addtab('保全任务','pos/getPosTaskInfo?id='+bussNo);
	//}
//}

//function getPageUrl(flag,bussNo){
	//if(flag==1){
		//parent.addtab('保全处理','pos/getPosPageUrl?id='+bussNo);
	//}
//}

/** 申请保全 */
function applyBQ(){
	parent.addtab('保全处理','pos/dealPosUrl');
}

/** 理赔领号 */
function applyLP(){
	parent.addtab('理赔领号','claim/claimRegisterUrl');
}

/** 理赔初审任务获取 */
function applyTaskLP(){
	var dlist = [];
	dlist.push({
				"operateType" : "check"
			});
	$.post("../claim/checkClaimDataExist?list="+ $.toJSON(dlist),  function(data) {
		if(data.mes=="success"){
			parent.addtab('理赔初审','claim/claimCheckUrl');
		}else{
			$.messager.alert('提示信息','没有理赔初审任务','info');
		}
	});	
}

function uwTaskLP(){
	var dlist = [];
	dlist.push({
				"operateType" : "approve"
			});
	$.post("../claim/checkClaimDataExist?list="+ $.toJSON(dlist),  function(data) {
		if(data.mes=="success"){
			parent.addtab('理赔复核','claim/claimApproveUrl');
		}else{
			$.messager.alert('提示信息','没有理赔复核任务','info');
		}
	});	
}

/** 全选 **/
function BQAllChecked(){
	$("input[name='BQTask']").attr("checked",$("#allCheck1").attr("checked")=='checked'?true:false);
}

function LPAllChecked(){
	$("input[name='LPTask']").attr("checked",$("#allCheck2").attr("checked")=='checked'?true:false);
}

function cancleAllCheck(flag){
	if(flag==1){
		$("#allCheck1").attr("checked",false);
	}
	if(flag==2){
		$("#allCheck2").attr("checked",false);
	}
}


function query(){
	$("input[name='BQTask']").each(function(){
		if(this.checked)
		 $("#BQTaskRs").attr("value",$("#BQTaskRs").val()+','+this.value); 	
	});
	$("input[name='LPTask']").each(function(){
		if(this.checked)
		 $("#LPTaskRs").attr("value",$("#LPTaskRs").val()+','+this.value);  
	});
	var params = $('#taskTable').datagrid('options').queryParams; // 先取得
	var fields = $('#taskForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		params[field.name] = (field.value); // 设置查询参数
	});
	params['queryFlag'] = '1';
	params['queryFrom'] = 'all';
	$('#taskTable').datagrid('options').url = 'queryTaskList?searchParams='+$.toJSON(params);
	$('#taskTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	$("#BQTaskRs").attr('value','');
	$("#LPTaskRs").attr('value','');
}