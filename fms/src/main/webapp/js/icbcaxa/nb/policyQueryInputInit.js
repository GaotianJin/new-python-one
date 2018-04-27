jQuery(function($) {
	$('#mainRiskCode').combobox({
		  url:'../nb/queryMainRistList',
		  valueField:'id',
		  textField:'name'
	  });

	var url="";
	var policyNo=$('#policyNo').val();
	if(policyNo!=null&&policyNo!=""){
		url="queryPolicyInfo?policyNo="+policyNo;
	}else{
       url="queryPolicyInfo";
	}
	
	$('#PolicyQueryTable').datagrid({
		title : '查询结果', //标题
		method : 'post',
		iconCls : 'icon-edit', //图标
		singleSelect : false, //多选
		height : 380, //高度
		fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, //奇偶行颜色不同
		collapsible : true,//可折叠
		url : url, //数据来源
		sortName : '', //排序的列
		sortOrder : 'asc', //倒序
		remoteSort : true, //服务器端排序
		idField : 'dbid', //主键字段
		queryParams : {}, //查询条件
		pagination : true, //显示分页
		rownumbers : true, //显示行号
		columns : [[
				{
					field : 'policyNo',
					title : '保单号',
					width : 80,
					sortable : true,
					formatter : function(value, row, index) {
						return "<a  href='#' onclick=\"javascript:showPolicyDetail('"+row.dbid+"','"+row.policyNo+"','"+row.policyVersionState+"')\">"+row.policyNo+"</a>";

					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'appntName',
					title : '投保人',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.appntName;
					}
				},{
					field : 'insuredName',
					title : '被保人',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.insuredName;
					}
				},{
					field : 'policyVersionDate',
					title : '版本结束日期',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.policyVersionDate;
					}
				},{
					field : 'agentCode',
					title : '网点编码',
					width : 60,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agentCode;
					}
				},{
					field : 'agentName',
					title : '网点名称',
					width : 130,
					sortable : true,
					formatter : function(value, row, index) {
						return row.agentName;
					}
				},{
					field : 'managecomName',
					title : '机构名称',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.managecomName;
					}
				},{
					field : 'policyVersionState',
					title : '保单版本状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.policyVersionState;
					}
				},{
					field : 'policyState',
					title : '保单当前状态',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.policyState;
					}
				},{
					field : 'policyEffectDate',
					title : '保单生效日',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.policyEffectDate;
					}
				},{
					field : 'saleChannel',
					title : '渠道',
					width : 60,
					sortable : true,
					formatter : function(value, row, index) {
						return row.saleChannel;
					}
				},{
					field : 'policyEndDate',
					title : '保单满期日',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.policyEndDate;
					}
				},{
					field : 'mainRiskCodeName',
					title : '主险险种名称',
					width : 300,
					sortable : true,
					formatter : function(value, row, index) {
						return row.mainRiskCodeName;
					}
				}]],
		onLoadSuccess : function(data) {
			if(data.mes!=""&&data.mes!=null){
				$.messager.alert('提示信息', data.mes, 'info')
			}
			$('#PolicyQueryTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
		


});

// 查看保单详细信息 modify by XLL 2013-12-10
function showPolicyDetail(dbid, policyNo, policyVersionState) {

	if (policyVersionState == "null" || policyVersionState == "") {
		parent.addtab('保单详细信息', 'nb/policyDetailUrl?queryNo=' + policyNo
				+ "&queryFlag=C");
	} else {
		parent.addtab('保单详细信息', 'nb/policyDetailUrl?queryNo=' + dbid
				+ "&queryFlag=B");
	}
}

function initPolicyQueryTable(){}

	
// 表格查询
function queryPolicy() {
	var policyVersionDate=$('#policyVersionDate').datebox('getValue');
	if(policyVersionDate!=null&&policyVersionDate!=""){
		var policyNo=$('#policyNo').val();
		if(policyNo==""||policyNo==null){
			$.messager.alert('提示信息', '请输入保单号码', 'info');	
			return false;
		}
	}
	var params = $('#PolicyQueryTable').datagrid('options').queryParams; // 先取得
	var fields = $('#policyQueryInputForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
					params[field.name] = (field.value); // 设置查询参数
				});
	$('#PolicyQueryTable').datagrid('options').url = 'queryPolicyInfo?searchParams='+$.toJSON(params);
	$('#PolicyQueryTable').datagrid('cisreload'); // 设置好查询参数 reload 一下就可以了
	
}

// 清空查询条件然后进行查询
function clearForm() {
		$('#policyQueryInputForm').form('clear');
		queryPolicy();
}
	
