var month=null;
jQuery(function($) {
	month = $("#generalCommission_month").val();
	//初始化七张表
	initSlyCommissionViewTable()
	initSaleCommissionTable();
	initGuojinCommissionTable();
	initOverseasCommissionTable();
	initReissueTable();
	initWithholdTable();
	initProjectCommissionTable();
});
//初始化销售佣金
var saleCommissionTable;
function initSaleCommissionTable(){
	saleCommissionTable=
	    $('#saleCommissionTable').datagrid({
		title : '销售佣金详情', // 标题
		url:contextPath+'/sales/querySaleCommissionDetail?param='+month,
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		pageSize:5,
		columns : [[
		            {
				    	 field:'slySaleCommissionId',	
				    	 title:'销售佣金流水号',
				    	 hidden:true,
				    	 formatter : function(value, row, index) {
				    	 return row.slySaleCommissionId;
				       }
				     },
				     {
				    	 field:'slySalaryId',	
				    	 title:'工资主表流水号',
				    	 hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.slySalaryId;
				       }
				     },
				     {
				    	 field:'month',	
				    	 title:'月份',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.month;
				       }
				     },
				     {
				    	 field:'channel',	
				    	 title:'结算渠道',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.channel;
				       }
				     },
				     {
				    	 field:'orgnizition',	
				    	 title:'机构',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.orgnizition;
				       }
				     },
				     {
				    	 field:'productName',	
				    	 title:'产品名称',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productName;
				       }
				     },
				     {
				    	 field:'foundDate',	
				    	 title:'成立日期',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.foundDate;
				       }
				     },
				     {
				    	 field:'workNumber',	
				    	 title:'工号',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.workNumber;
				       }
				     },
				     {
				    	 field:'agentName',	
				    	 title:'财富顾问',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.agentName;
				       }
				     },
				     {
				    	 field:'departmentLeader',	
				    	 title:'部门负责人',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.departmentLeader;
				       }
				     },
				     {
				    	 field:'custName',	
				    	 title:'客户姓名',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.custName;
				       }
				     },
				     {
				    	 field:'custType',	
				    	 title:'客户类型',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.custType;
				       }
				     },
				     {
				    	 field:'amount',	
				    	 title:'规模',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.amount;
				       }
				     },
				     {
				    	 field:'closePeriouds',	
				    	 title:'久期',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.closePeriouds;
				       }
				     },
				     {
				    	 field:'amountStandard',	
				    	 title:'标规（百万）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.amountStandard;
				       }
				     },
				     {
				    	 field:'commissionRate',	
				    	 title:'佣金比率（%）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.commissionRate;
				       }
				     },
				     {
				    	 field:'commissionAmount',	
				    	 title:'佣金',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.commissionAmount;
				       }
				     },
				     {
				    	 field:'managementAllownace',	
				    	 title:'管理津贴',
				    	 //hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.managementAllownace;
				       }
				     },
				     {
				    	 field:'serviceAllowance',	
				    	 title:'服务津贴',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.serviceAllowance;
				       }
				     },
				     {
				    	 field:'continueCommission',	
				    	 title:'续佣',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.continueCommission;
				       }
				     },
				     {
				    	 field:'continueManagementAllowance',	
				    	 title:'续管理津贴',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.continueManagementAllowance;
				       }
				     },
				     {
				    	 field:'floatRemarks',	
				    	 title:'浮动',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.floatRemarks;
				       }
				     },
				     {
				    	 field:'otherSalesCommission',	
				    	 title:'其它',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.otherSalesCommission;
				       }
				     },
				     {
				    	 field:'recommendName',	
				    	 title:'推荐人',
				    	 width:80,
				    	 //hidden:true,
				    	 formatter : function(value, row, index) {
				    	 return row.recommendName;
				       }
				     },{
				    	 field:'addBonus',	
				    	 title:'增员奖励',
				    	 //hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.addBonus;
				       }
				     },{
				    	 field:'mark',	
				    	 title:'备注',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.mark;
				       }
				     }]],
				     onLoadSuccess : function(data) {
							$('#saleCommissionTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
							if(data.total==0){
								$("#saleCommissionId").hide();
							}
						},
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

//初始化国金佣金
var guojinCommissionTable;
function initGuojinCommissionTable(){
	guojinCommissionTable=
	    $('#guojinCommissionTable').datagrid({
		title : '国金佣金详情', // 标题
		url:contextPath+'/sales/queryGuojinCommissionDetail?param='+month,
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		pageSize:5,
		columns : [[
		            {
				    	 field:'slyGuojinCommissionId',	
				    	 title:'国金交易佣金流水号',
				    	 hidden:true,
				    	 formatter : function(value, row, index) {
				    	 return row.slyGuojinCommissionId;
				       }
				     },
				     {
				    	 field:'slySalaryId',	
				    	 title:'工资主表流水号',
				    	 hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.slySalaryId;
				       }
				     },
				     {
				    	 field:'month',	
				    	 title:'月份',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.month;
				       }
				     },
				     {
				    	 field:'channel',	
				    	 title:'结算渠道',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.channel;
				       }
				     },
				     {
				    	 field:'workNumber',	
				    	 title:'工号',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.workNumber;
				       }
				     },
				     {
				    	 field:'custRelation',	
				    	 title:'客户关系',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.custRelation;
				       }
				     },
				     {
				    	 field:'custName',	
				    	 title:'客户姓名',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.custName;
				       }
				     },
				     {
				    	 field:'account',	
				    	 title:'资金账户',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.account;
				       }
				     },
				     {
				    	 field:'establishDate',	
				    	 title:'开户日期',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.establishDate;
				       }
				     },
				     {
				    	 field:'marketValueMax',	
				    	 title:'市值峰值',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.marketValueMax;
				       }
				     },
				     {
				    	 field:'personalValueMax',	
				    	 title:'资产规模峰值',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.personalValueMax;
				       }
				     },
				     {
				    	 field:'tradeAmount',	
				    	 title:'成交金额',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.tradeAmount;
				       }
				     },
				     {
				    	 field:'distributeNetValue',	
				    	 title:'分配净佣',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.distributeNetValue;
				       }
				     },
				     {
				    	 field:'remarks',	
				    	 title:'备注',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.remarks;
				       }
				     }
				    ]],
				     onLoadSuccess : function(data) {
							$('#guojinCommissionIdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
							if(data.total==0){
								$("#guojinCommissionId").hide();
							}
						},
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

//初始化海外佣金
var overseasCommissionTable;
function initOverseasCommissionTable(){
	overseasCommissionTable=
	    $('#overseasCommissionTable').datagrid({
		title : '海外佣金详情', // 标题
		url:contextPath+'/sales/queryOverseasCommissionDetail?param='+month,
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		pageSize:5,
		columns : [[
		            {
				    	 field:'slyOverseasCommissionId',	
				    	 title:'海外投资流水号',
				    	 hidden:true,
				    	 formatter : function(value, row, index) {
				    	 return row.slyOverseasCommissionId;
				       }
				     },
				     {
				    	 field:'slySalaryId',	
				    	 title:'工资主表流水号',
				    	 hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.slySalaryId;
				       }
				     },
				     {
				    	 field:'month',	
				    	 title:'月份',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.month;
				       }
				     },
				     {
				    	 field:'channel',	
				    	 title:'结算渠道',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.channel;
				       }
				     },
				     {
				    	 field:'organization',	
				    	 title:'机构',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.organization;
				       }
				     },
				     {
				    	 field:'productSide',	
				    	 title:'产品方',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productSide;
				       }
				     },
				     {
				    	 field:'productPlan',	
				    	 title:'产品计划',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productPlan;
				       }
				     },
				     {
				    	 field:'purchaseDate',	
				    	 title:'购买日期',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.purchaseDate;
				       }
				     },
				     {
				    	 field:'inPay',	
				    	 title:'年缴（USD）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.inPay;
				       }
				     },
				     {
				    	 field:'workNumber',	
				    	 title:'工号',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.workNumber;
				       }
				     },
				     {
				    	 field:'agentName',	
				    	 title:'财富顾问',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.agentName;
				       }
				     },
				     {
				    	 field:'firstCommissionRate',	
				    	 title:'佣金比率（%）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.firstCommissionRate;
				       }
				     },
				     {
				    	 field:'firstCommission',	
				    	 title:'佣金（HKD）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.firstCommission;
				       }
				     },
				     /*{
				    	 field:'settleCommission',	
				    	 title:'本月结算佣金（HKD）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.settleCommission;
				       }
				     },*/
				     {
				    	 field:'insurancePolicyNo',	
				    	 title:'保单号',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.insurancePolicyNo;
				       }
				     },
				     {
				    	 field:'applicantName',	
				    	 title:'持保人姓名',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.applicantName;
				       }
				     },
				     {
				    	 field:'recognizeeName',	
				    	 title:'受保人姓名',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.recognizeeName;
				       }
				     },{
				    	 field:'remark',	
				    	 title:'备注',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.remark;
				     }}]],
				     onLoadSuccess : function(data) {
							$('#overseasCommissionTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
							if(data.total==0){
								$("#overseasCommissionId").hide();
							}
						},
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

//初始化补发利益
var reissueTable;
function initReissueTable(){
	reissueTable=
	    $('#reissueTable').datagrid({
		title : '补发利益详情', // 标题
		url:contextPath+'/sales/queryReissueDetail?param='+month,
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		pageSize:5,
		columns : [[
		            {
				    	 field:'slyReissueId',	
				    	 title:'补发利益流水号',
				    	 hidden:true,
				    	 formatter : function(value, row, index) {
				    	 return row.slyReissueId;
				       }
				     },
				     {
				    	 field:'slySalaryId',	
				    	 title:'工资主表流水号',
				    	 hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.slySalaryId;
				       }
				     },
				     {
				    	 field:'month',	
				    	 title:'月份',
				    	 width:60,
				    	 formatter : function(value, row, index) {
				    	 return row.month;
				       }
				     },
				     {
				    	 field:'channel',	
				    	 title:'结算渠道',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.channel;
				       }
				     },
				     {
				    	 field:'organization',	
				    	 title:'机构',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.organization;
				       }
				     },
				     {
				    	 field:'productName',	
				    	 title:'产品名称',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productName;
				       }
				     },
				     {
				    	 field:'productCreateDate',	
				    	 title:'成立日期',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productCreateDate;
				       }
				     },
				     {
				    	 field:'workNumber',	
				    	 title:'工号',
				    	 width:70,
				    	 formatter : function(value, row, index) {
				    	 return row.workNumber;
				       }
				     },
				     {
				    	 field:'agentName',	
				    	 title:'财富顾问',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.agentName;
				       }
				     },
				     {
				    	 field:'teamDirector',	
				    	 title:'部门负责人',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.teamDirector;
				       }
				     },
				     {
				    	 field:'customerName',	
				    	 title:'客户姓名',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.customerName;
				       }
				     },
				     {
				    	 field:'customerType',	
				    	 title:'客户类型',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.customerType;
				       }
				     },
				     {
				    	 field:'scale',	
				    	 title:'规模',
				    	 width:70,
				    	 formatter : function(value, row, index) {
				    	 return row.scale;
				       }
				     },
				     {
				    	 field:'duration',	
				    	 title:'久期',
				    	 width:70,
				    	 formatter : function(value, row, index) {
				    	 return row.duration;
				       }
				     },
				     {
				    	 field:'gaugeStandard',	
				    	 title:'标规（百万）',
				    	 width:90,
				    	 formatter : function(value, row, index) {
				    	 return row.gaugeStandard;
				       }
				     },
				     {
				    	 field:'commissionRate',	
				    	 title:'佣金比率（%）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.commissionRate;
				       }
				     },
				     {
				    	 field:'reissueCommission',	
				    	 title:'补发佣金',
				    	 width:90,
				    	 formatter : function(value, row, index) {
				    	 return row.reissueCommission;
				       }
				     },
				     {
				    	 field:'reissueAllowance',	
				    	 title:'补发管理津贴',
				    	 width:90,
				    	 formatter : function(value, row, index) {
				    	 return row.reissueAllowance;
				       }
				     },
				     {
				    	 field:'recommoendName',	
				    	 title:'推荐人',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.recommoendName;
				       }
				     },
				     {
				    	 field:'reissueAddedBonus',	
				    	 title:'补发增员奖励',
				    	 width:90,
				    	 formatter : function(value, row, index) {
				    	 return row.reissueAddedBonus;
				       }
				     },{
				    	 field:'remarks',	
				    	 title:'备注',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.remarks;
				       }
				     }]],
				 	onLoadSuccess : function(data) {
						$('#reissueTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						if(data.total==0){
							$("#reissueId").hide();
						}
					},
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

//初始化暂扣利益
var withholdTable;
function initWithholdTable(){
	withholdTable=
	    $('#withholdTable').datagrid({
		title : '暂扣利益详情', // 标题
		url:contextPath+'/sales/queryWithholdDetail?param='+month,
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		pageSize:5,
		columns : [[
		            {
				    	 field:'slyWithholdId',	
				    	 title:'暂扣利益流水号',
				    	 hidden:true,
				    	 formatter : function(value, row, index) {
				    	 return row.slyWithholdId;
				       }
				     },
				     {
				    	 field:'slySalaryId',	
				    	 title:'工资主表流水号',
				    	 hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.slySalaryId;
				       }
				     },
				     {
				    	 field:'month',	
				    	 title:'月份',
				    	 width:60,
				    	 formatter : function(value, row, index) {
				    	 return row.month;
				       }
				     }, 
				     {
					    	field:'channel',	
					    	title:'结算渠道',
					    	width:80,
					    	formatter : function(value, row, index) {
					    	return row.channel;
				    	}
				     },
				     {
				    	 field:'organization',	
				    	 title:'机构',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.organization;
				       }
				     },
				     {
				    	 field:'productName',	
				    	 title:'产品名称',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productName;
				       }
				     },
				     {
				    	 field:'productCreateDate',	
				    	 title:'成立日期',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productCreateDate;
				       }
				     },
				     {
				    	 field:'workNumber',	
				    	 title:'工号',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.workNumber;
				       }
				     },
				     {
				    	 field:'agentName',	
				    	 title:'财富顾问',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.agentName;
				       }
				     },
				     {
				    	 field:'teamDirector',	
				    	 title:'部门负责人',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.teamDirector;
				       }
				     },
				     {
				    	 field:'customerName',	
				    	 title:'客户姓名',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.customerName;
				       }
				     },
				     {
				    	 field:'customerType',	
				    	 title:'客户类型',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.customerType;
				       }
				     },
				     {
				    	 field:'scale',	
				    	 title:'规模',
				    	 width:70,
				    	 formatter : function(value, row, index) {
				    	 return row.scale;
				       }
				     },
				     {
				    	 field:'duration',	
				    	 title:'久期',
				    	 width:70,
				    	 formatter : function(value, row, index) {
				    	 return row.duration;
				       }
				     },
				     {
				    	 field:'gaugeStandard',	
				    	 title:'标规（百万）',
				    	 width:90,
				    	 formatter : function(value, row, index) {
				    	 return row.gaugeStandard;
				       }
				     },
				     {
				    	 field:'commissionRate',	
				    	 title:'佣金比率（%）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.commissionRate;
				       }
				     },
				     {
				    	 field:'withholdCommission',	
				    	 title:'暂扣佣金',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.withholdCommission;
				       }
				     },
				     {
				    	 field:'withholdAllowance',	
				    	 title:'暂扣管理津贴',
				    	 width:90,
				    	 formatter : function(value, row, index) {
				    	 return row.withholdAllowance;
				       }
				     },
				     {
				    	 field:'recommoendName',	
				    	 title:'推荐人',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.recommoendName;
				       }
				     },
				     {
				    	 field:'withholdAddedBonus',	
				    	 title:'暂扣增员奖励',
				    	 width:90,
				    	 formatter : function(value, row, index) {
				    	 return row.withholdAddedBonus;
				       }
				     },{
				    	 field:'remarks',	
				    	 title:'备注',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.remarks;
				       }
				     }]],
				 	onLoadSuccess : function(data) {
						$('#withholdTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						if(data.total==0){
							$("#withholdId").hide();
						}
					},
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

//初始化暂扣利益
var projectCommissionTable;
function initProjectCommissionTable(){
	projectCommissionTable=
	    $('#projectCommissionTable').datagrid({
		title : '项目端佣金详情', // 标题
		url:contextPath+'/sales/queryProjectCommissionDetail?param='+month,
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		pageSize:5,
		columns : [[
		            {
				    	 field:'slyProjectCommissionId',	
				    	 title:'项目端佣金流水号',
				    	 hidden:true,
				    	 formatter : function(value, row, index) {
				    	 return row.slyProjectCommissionId;
				       }
				     },
				     {
				    	 field:'slySalaryId',	
				    	 title:'工资主表流水号',
				    	 hidden:true,
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.slySalaryId;
				       }
				     },
				     {
				    	 field:'month',	
				    	 title:'月份',
				    	 width:70,
				    	 formatter : function(value, row, index) {
				    	 return row.month;
				       }
				     }, 
				     {
				    	 field:'channel',	
				    	 title:'结算渠道',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.channel;
				       }
				     },
				     {
				    	 field:'productName',	
				    	 title:'产品名称',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.productName;
				       }
				     },
				     {
				    	 field:'newIncomeAfertTax',	
				    	 title:'公司税后净收入',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.newIncomeAfertTax;
				       }
				     },
				     {
				    	 field:'workNumber',	
				    	 title:'工号',
				    	 width:60,
				    	 formatter : function(value, row, index) {
				    	 return row.workNumber;
				       }
				     },
				     /*{
				    	 field:'companyFixIncome',	
				    	 title:'公司收入（固定）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.companyFixIncome;
				       }
				     },
				     {
				    	 field:'companyFloatIncome',	
				    	 title:'公司收入（浮动）',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.companyFloatIncome;
				       }
				     },*/
				     {
				    	 field:'projectImportPerson',	
				    	 title:'项目引进人',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.projectImportPerson;
				       }
				     },
				     {
				    	 field:'issueRateWay',	
				    	 title:'发放比例计算方式',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.issueRateWay;
				       }
				     },
				  
				     {
				    	 field:'issueRate',	
				    	 title:'发放比例',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.issueRate;
				       }
				     },
				     {
				    	 field:'sendCommission',	
				    	 title:'可发佣金',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.sendCommission;
				       }
				     },
				     {
				    	 field:'otherCommission',	
				    	 title:'其它',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.otherCommission;
				       }
				     },
				     {
				    	 field:'remarks',	
				    	 title:'备注',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.remarks;
				       }
				     }
				    /* {
				    	 field:'firstIssueRate',	
				    	 title:'首期发放比例',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.firstIssueRate;
				       }
				     },
				     {
				    	 field:'actualFirstIssueAmount',	
				    	 title:'首期实际可发金额',
				    	 width:100,
				    	 formatter : function(value, row, index) {
				    	 return row.actualFirstIssueAmount;
				       }
				     },
				     {
				    	 field:'secondIssueRate',	
				    	 title:'二期发放比例',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.secondIssueRate;
				       }
				     },
				     {
				    	 field:'actualSecondIssueAmount',	
				    	 title:'二期可发金额',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.actualSecondIssueAmount;
				       }
				     },
				     {
				    	 field:'lastIssueRate',	
				    	 title:'后期发放比例',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.lastIssueRate;
				       }
				     },
				     {
				    	 field:'actualLastIssueAmount',	
				    	 title:'后期可发金额',
				    	 width:80,
				    	 formatter : function(value, row, index) {
				    	 return row.actualLastIssueAmount;
				       }
				     }*/]],
				     onLoadSuccess : function(data) {
							$('#projectCommissionTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
							if(data.total==0){
								$("#projectCommission").hide();
							}
						},
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

//初始化佣金明细
var slyCommissionViewTable;
function initSlyCommissionViewTable(){
	saleCommissionTable=
	    $('#slyCommissionViewTable').datagrid({
		title : '佣金总额明细详情', // 标题
		url:contextPath+'/sales/querydetailsCommission?param='+month,
		method : 'post',
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		collapsible : true,// 可折叠
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		pageSize:5,
		columns : [[
		            /*{field:'ck',checkbox:true},*/
		     {
		    	 field:'slyCommissionId',	
		    	 title:'佣金明细流水号',
		    	 hidden:true,
		    	 formatter : function(value, row, index) {
		    	 return row.slySaleCommissionId;
		       }
		     },
		     {
		    	 field:'slySalaryId',	
		    	 title:'工资主表流水号',
		    	 hidden:true,
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.slySalaryId;
		       }
		     },
		     {
		    	 field:'month',	
		    	 title:'月份',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.month;
		       }
		     },
		     {
		    	 field:'channel',	
		    	 title:'结算渠道',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.channel;
		       }
		     },
		    
		     {
		    	 field:'comName',	
		    	 title:'机构',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.comName;
		       }
		     },
		     {
		    	 field:'workNumber',	
		    	 title:'工号',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.workNumber;
		       }
		     },
		
		     {
		    	 field:'chnName',	
		    	 title:'姓名',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.chnName;
		       }
		     },
		     {
		    	 field:'saleCommission',	
		    	 title:'销售佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.saleCommission;
		       }
		     },
		     {
		    	 field:'managementAllowance',	
		    	 title:'管理津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.managementAllowance;
		       }
		     },
		     {
		    	 field:'addBonus',	
		    	 title:'增员奖励',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.addBonus;
		       }
		     },
		     {
		    	 field:'serviceAllowance',	
		    	 title:'服务津贴',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.serviceAllowance;
		       }
		     },
		     {
		    	 field:'floatIncome',	
		    	 title:'浮动收益',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.floatIncome;
		       }
		     },
		     {
		    	 field:'guojinCommission',	
		    	 title:'国金佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.guojinCommission;
		       }
		     },
		     {
		    	 field:'projectCommission',	
		    	 title:'项目端',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.projectCommission;
		       }
		     },
		     {
		    	 field:'reissueCommision',	
		    	 title:'补发费用',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.reissueCommision;
		       }
		     },
		     {
		    	 field:'withholdCommission',	
		    	 title:'暂扣费用',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.withholdCommission;
		       }
		     },
		     {
		    	 field:'zhanyeCommission',	
		    	 title:'展业费用扣减',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.zhanyeCommission;
		       }
		     },
		     {
		    	 field:'lastMonthRemaining',	
		    	 title:'上月剩余待发',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.lastMonthRemaining;
		       }
		     },
		     {
		    	 field:'otherCommission',	
		    	 title:'其他',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.otherCommission;
		       }
		     },
		     {
		    	 field:'commissionAmount',	
		    	 title:'本月可发合计',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.commissionAmount;
		       }
		     },
		     {
		    	 field:'addToSalary',	
		    	 title:'计入本月',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.addToSalary;
		       }
		     },{
		    	 field:'remainCommission',	
		    	 title:'剩余待发',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remainCommission;
		       }
		     },{
		    	 field:'overseasCommission',	
		    	 title:'海外投资佣金',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.overseasCommission;
		       }
		     },
		     {
		    	 field:'remark',	
		    	 title:'备注',
		    	 width:80,
		    	 formatter : function(value, row, index) {
		    	 return row.remark;
		       }
		     }]],
				     onLoadSuccess : function(data) {
							$('#slyCommissionViewTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
							if(data.total==0){
								$("#slyCommissionViewId").hide();
							}
						},
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

/**
 * 返回
 */
function back(){
	$('#addGeneralSalaryComWindow').window('close');
	initGeneralSalaryTable();
}

	