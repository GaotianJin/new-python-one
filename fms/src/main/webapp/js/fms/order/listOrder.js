//打开页面时执行的方法
jQuery(function($) {
	initOrderAll();
	initAllListOrderCombobox();
});

//加载datagrid
function initOrderAll() {
	var  selectIndex = -1;
	$('#OrderTable').datagrid({
		//title : '订单信息查询', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/ordersCtrl/getOrdersAllByPage", // 数据来源
		sortName : 'oId', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'oId', // 主键字段
		queryParams : {}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			width : 2
		}, // 显示复选框
		{
			field : 'orderId',
			title : '订单编号',
			width : 100,
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.orderId;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'productId',
			title : '商品编号',
			width : 100,
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.productId;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'productName',
			title : '商品名称',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.productName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'olientId',
			title : '客户编号',
			width : 100,
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.olientId;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'chnName',
			title : '客户姓名',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.chnName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'saleConsultantId',
			title : '所属理财师编号',
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.saleConsultantId;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'agentName',
			title : '所属理财师姓名',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.agentName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'saleConsultantId',
			title : '交易理财师编号',
			//sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.saleConsultantId;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'agentName',
			title : '交易理财师姓名',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.agentName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'strategyName',
			title : '销售策略',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.strategyName;
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'orderStatus',
			title : '订单状态',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.orderStatus;
			}
		}, {
			field : 'amount',
			title : '人民币金额',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.amount;
			}
		}, {
			field : 'saleAmount',
			title : '营销业绩',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.saleAmount;
			}
		}, {
			field : 'discountAmount',
			title : '折标业绩',
			formatter : function(value, row, index) {
				return row.discountAmount;
			}
		}, {
			field : 'preorderCreatetime',
			title : '预约日期',
			formatter : function(value, row, index) {
				return formatDateTime(row.preorderCreatetime);
			}
		}, {
			field : 'preorderCompletetime',
			title : '预约确认日期',
			//sortable : true,
			formatter : function(value, row, index) {
				return formatDateTime(row.preorderCompletetime);
			} // 需要formatter一下才能显示正确的数据
		}, {
			field : 'payTime',
			title : '划款日期',
			//sortable : true,
			formatter : function(value, row, index) {
				return formatDateTime(row.payTime);
			}
		}, {
			field : 'contactStatus',
			title : '合同签署状态',
			//sortable : true,
			formatter : function(value, row, index) {
				return row.contactStatus;
			}
		},{
			field : 'tradeStausName',
			title : '操作',
			//sortable : true,
			formatter : function(value, row, index) {
				return "<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
				+ row.refereeId+ ","+ 1 + ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green	'>提交订单</span></span></a>" +
					"<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
				+ row.refereeId+","+ 2+ ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green'>份额通过</span></span></a>" +
						"<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
				+ row.refereeId+ ","+ 1 + ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green	'>份额拒绝</span></span></a>" +
						"<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
				+ row.refereeId+ ","+ 1 + ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green	'>支付完成</span></span></a>" +
						"<a href='javascript:void(0)' class='easyui-linkbutton l-btn l-btn-small l-btn-plain' plain='true' onclick='eventState("
				+ row.refereeId+ ","+ 1 + ")'><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text' style='color:green;margin-right:30px	'>合同已收</span></span></a>";
			}
		} ] ],
		toolbar : [ {
						iconCls : 'icon-add',
						text : '新增预约',
						handler : function() {
							openOrderAddDialog();
						}
					}],
		onLoadSuccess : function() {
			$('#OrderTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow: function (rowIndex, rowData) {
            if(selectIndex==rowIndex){
            	//第一次单击选中,第二次单击取消选中
            	$(this).datagrid('unselectRow', rowIndex);
            	selectIndex=-1;
            }else{
            	alert(rowData.orderId);
            	selectIndex = rowIndex;
            }
		}
	});
}

//模糊查询
function queryOrderList() {
	$('#OrderTable').datagrid('load', {
		productName:$("#o_productName").val(),
		strategyName:$("#strategyName").val(),
		chnName:$("#o_chnName").val(),
		agentName:$("#agentName").val(),
		orderStatus:$("#o_orderStatus").val(),
		contactStatus:$("#contactStatus").val(),
		beginPreorderCreatetime:$('#beginPreorderCreatetime').datebox("getValue"),
		endPreorderCreatetime:$('#endPreorderCreatetime').datebox("getValue"),
		beginPreorderCompletetime:$('#beginPreorderCompletetime').datebox("getValue"),
		endPreorderCompletetime:$('#endPreorderCompletetime').datebox("getValue"),
		beginPayTime:$('#beginPayTime').datebox("getValue"),
		endPayTime:$('#endPayTime').datebox("getValue")
	});
}

// 清空查询条件
function clearOrderForm() {
	$('#OrderForm').form('clear');//清空表单
}
//时间戳转日期
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
};  

//打开添加窗口
function openOrderAddDialog(){
	$("#AddOrderForm").form("clear");
	$("#orderStatus").val("未提交");
	//$('#orderStatus').prop('disabled', true);
	//$('#orderStatus').prop('required', true);
	$("#orderStatus").attr("readonly", "readonly");
	$('#OrderDlg').dialog('open').dialog("setTitle","订单预约");
	url=("/fms/ordersCtrl/insertOrders");
}

//关闭按钮
function closeOrderDialog(){
	$('#OrderDlg').dialog("close");
}

function initAllListOrderCombobox() {
	$('#productId').combobox({
		url : contextPath + '/productOrder/getProductAllForSelect',
		editable:false,
		required:true,
		valueField : 'productId',
		textField : 'productName'
	});
	$('#olientId').combobox({
		url : contextPath + '/custBaseInfoCtrl/getCustAllForSelect',
		editable:false,
		required:true,
		valueField : 'custBaseInfoId',
		textField : 'chnName'
	});
	$('#o_orderStatus').combobox({
		url : contextPath + '/ordersCtrl/getStateGroupByOrdersState',
		editable:false,
		valueField : 'orderStatus',
		textField : 'orderStatus'
	});
	$('#contactStatus').combobox({
		url : contextPath + '/ordersCtrl/getStateGroupByContactStatus',
		editable:false,
		valueField : 'contactStatus',
		textField : 'contactStatus'
	});
}

function saveOrder(){
	if($('#AddOrderForm').form("validate")) {
		$.post(url,$('#AddOrderForm').serialize(),function(result){
			if(result.success){
				$.messager.alert("系统提示",result.msg);
				$('#OrderDlg').dialog("close");
				$("#OrderTable").datagrid("reload");
			}else{
				$.messager.alert("系统提示",result.msg);
				return;
			}
		});
	}
}