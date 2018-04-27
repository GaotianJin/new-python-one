jQuery(function($) {
	initAllCombobox();
	initAssessCalTable();
});

function initAllCombobox(){
	$("#assesscal_Unit").combobox({
		url : contextPath + '/codeQuery/tdCodeQuery?codeType=assessUnit',
		valueField : 'code',
		textField : 'codeName',
		editable:false,
		required:true,
		value : 'S'
	});	
	
	$("#assesscal_year").datebox({
		editable:false,
		required:true,
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			var startDateValue = y;
			return startDateValue;
		}
	});
	
	$("#assesscal_Month").datebox({
		editable:false,
		required:true,
		formatter:function(date){
			var m = date.getMonth()+1;
			return m;
		}
	});
	

}

var assessCalTable;
function initAssessCalTable(){
	assessCalTable=
	    $('#assessCalTable').datagrid({
		title : '考核明细信息', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/sales/queryAssessCalList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
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
					field : 'row.assessInfoId',
					title : '考核信息Id',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.assessInfoId;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'row.assessYear',
					title : '考核年份',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.assessYear;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'row.assessMonth',
					title : '考核月份',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.assessMonth;
					}
				},
				 {
					field : 'row.assessUnit',
					title : '考核单位',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.assessUnit;
					}
				},
				 {
					field : 'row.assessUnitName',
					title : '考核单位',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.assessUnitName;
					}
				},
				{
					field : 'row.assessSumAssets',
					title : '考核总资产(元)',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.assessSumAssets;
					}
				},
				{
					field : 'row.assessStatus',
					title : '考核状态',
					width : 100,
					hidden:true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.assessStatus;
					}
				},
				{
					field : 'row.assessStatusName',
					title : '考核状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.assessStatusName;
					}
				},
				
				{
					field : 'row.assessDate',
					title : '预警计算日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.assessDate;
					}
				},
				{
					field : 'row.forwardDate',
					title : '考核结转日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.forwardDate;
					}
				}]]
	});
}

//考核预警按钮
function assessCal(){
	// 考核起始日期和结算日期
	if (!$("#assessAccForm").form("validate")) {
		return false;
	}
	var assessCalYear=$('#assesscal_year').datebox("getValue");
	var assessCalMonth=$('#assesscal_Month').datebox("getValue");
	var assessUnit=$('#assesscal_Unit').combobox("getValue");
	if (assessUnit=='S') {
		if (assessCalMonth!='6'&&assessCalMonth!='12') {
			alert("此为半年度考核，考核月份只能为6月/12月");
			return;
		}
		
	}
	
	
	var param={};
	param.assessCalYear=assessCalYear;
	param.assessCalMonth=assessCalMonth;
	param.assessUnit=assessUnit;
	$.ajax({
		type : 'post',
		url : contextPath + "/sales/assessCal",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
					clearForm();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}


//考核结转按钮
function assessmentForward(){  
	var rows = $('#assessCalTable').datagrid('getSelections');
	
	if(rows.length==0){
		$.messager.alert('提示',"请选中一条记录进行考核信息结转", 'info');
		return;
	}
	
   if(rows.length>1){
	  $.messager.alert('提示',"只能选中一条记录进行考核信息结转",'info');
	  return;
   }
   
   var assessYear= rows[0].assessYear;
   var assessMonth=rows[0].assessMonth;
   var assessUnit=rows[0].assessUnit;
   var param={};
	param.assessYear=assessYear;
	param.assessMonth=assessMonth;
	param.assessUnit=assessUnit;
	$.ajax({
		type : 'post',
		url : contextPath + "/sales/assessForward",
		data : 'param=' + $.toJSON(param),
		cache : false,
		success : function(resultInfo) {
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg, 'info');
					$.messager.progress('close');
					clearForm();
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}





// 清空查询条件然后进行查询
function clearForm() {
	$('#assessAccForm').form('clear');
	$('#assessCalTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
}

// 根据查询条件进行查询
function searchAssess(){
	assessCalTable.datagrid('options').url = contextPath+"/sales/queryAssessCalList";
	var queryparam = $("#assessAccForm").serialize();
	queryparam = formDataToJsonStr(queryparam);
	assessCalTable.datagrid('load',{param:queryparam});
}

	