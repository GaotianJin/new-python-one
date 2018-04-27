jQuery(function($) {
	var  selectIndex = -1;
	$('#reftable').datagrid({
		title : '推荐人列表', // 标题
		method : 'post',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/refereeCtrl/getRefereeAll", // 数据来源
		sortName : 'refereeId', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'refereeId', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},  // 显示复选框
				{
					field : 'refereeId',
					title : '推荐人ID',
					align : 'center',
					width : 100,
					sortable : true,
					hidden:true,
					formatter : function(value, row, index) {
						return row.refereeId;
					} // 需要formatter一下才能显示正确的数据,return结果必须为row.属性名。
				},
				{
					field : 'refereeName',
					title : '推荐人',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.refereeName;
					}
				},{
					field : 'departmentId',
					title : '所属部门',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.departmentId;
					}
				},{
					field : 'mobile',
					title : '手机号',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.mobile;
					}
				},{
					field : 'email',
					title : '邮箱',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.email;
					}
				},{
					field : 'refereeCode',
					title : '推荐码',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.refereeCode;
					}
				},{
					field : 'state',
					title : '状态',
					align : 'center',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						if(row.state==0){
							return '申请中';
						}else if(row.state==1){
							return '审核通过';
						}else if(row.state==2){
							return '审批拒绝';
						}else{
							return '冻结';
						}
					}
				},
				{
					field : 'aa',
					title : '操作',
					align : 'center',
					width : 200,
					formatter : function(value, row, index) {
						if(row.state==0){
							return "<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
							+ row.refereeId+ ","+ 1 + ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green	'>审核通过</span></span></a>" +
								"<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
							+ row.refereeId+","+ 2+ ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green'>审核拒绝</span></span></a>";
						}else if(row.state==1){
							return "<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
							+ row.refereeId+","+3+ ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green'>冻结</span></span></a>";
						}else if(row.state==3){
							return "<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
							+ row.refereeId+","+1+ ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green'>解除冻结</span></span></a>";
						}else{
							return "<span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:red'>无法操作</span></span>";
						}
						
					}
				} ] ]
	});
});

function clearRefereeFormList() {
	$('#RefereeForm').form('clear');
	$('#state').combobox("setText","请选择状态").combobox("setValue", "请选择状态");
	
}

function searchReferee(){
	var rstate=$('#state').combobox("getValue");
	if(rstate=="请选择状态"){
		rstate="";
	}
	$('#reftable').datagrid('load',{
		refereeName:$('#refereeName').val(),
		refereeCode:$('#refereeCode').val(),
		state:rstate,
	});	
}

function eventState(id,state){
	$.post(contextPath+"/refereeCtrl/updateRefereeState",{refereeId:id,state:state},function(res){
		if(res.success){
			$.messager.alert("系统提示", res.message);
			$("#reftable").datagrid("reload");
		}else{
			$.messager.alert("系统提示", res.message);
			return;
		}
	});
}

$('#state').combobox({
    url:contextPath+"/refereeCtrl/getRefereeStateforList",
    editable:false,
    value:'请选择状态',
    valueField:'state',
    textField:'state',
});