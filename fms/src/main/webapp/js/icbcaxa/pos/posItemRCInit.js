jQuery(function($) {
		$('#productTable').datagrid({
			title : '险种信息', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 220, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryProductList?posID="+$('#posID').val(), //数据来源
			sortName : 'pd.dbid', //排序的列
			sortOrder : 'asc', //正序
			remoteSort : true, //服务器端排序
			idField : 'id', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框
			{
				field : 'row.id',
				title : '险种ID',
				width : 80,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.id;
				} //需要formatter一下才能显示正确的数据
			},{
				field : 'row.productCode',
				title : '险种代码',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.productCode;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'row.productName',
				title : '险种名称',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.productName;
				}
			},{
				field : 'row.sumAssured',
				title : '保额/份数',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.sumAssured;
				}
			},{
				field : 'row.currentModePremium',
				title : '保费',
				width : 50,
				sortable : true,
				formatter : function(value, row, index) {
					return row.currentModePremium;
				}
			},{
				field : 'row.planEffectiveDate',
				title : '生效日期',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.planEffectiveDate;
				}
			},{
				field : 'row.planStatus',
				title : '险种状态',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.planStatus;
				}
			},{
				field : 'row.payToDate',
				title : '保费缴至日',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.payToDate;
				}
			},{
				field : 'row.paidUpDate',
				title : '保费缴清日',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.paidUpDate;
				}
			},{
				field : 'row.socialSecurityFlag',
				title : '社保选项',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.socialSecurityFlag;
				}
			},{
				field : 'row.addPremiumGrade',
				title : '加费级别',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.addPremiumGrade;
				}
			},{
				field : 'row.addPremiumRate',
				title : '加费费率',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.addPremiumRate;
				}
			},{
				field : 'row.planTerminatedDate',
				title : '险种满期日',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.paidUpDate;
				}
			}
			] ],
			onLoadSuccess : function() {
				$('#productTable').datagrid('clearSelections'); 
			}
		});
		
		$('#productTableUpdate').datagrid({
			title : '更改后险种信息', //标题
			method : 'post',
			iconCls : 'icon-edit', //图标
			singleSelect : false, //多选
			height : 220, //高度
			fitColumns : false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, //奇偶行颜色不同
			collapsible : true,//可折叠
			url : "queryProductList?posID="+$('#posID').val(), //数据来源
			sortName : 'pd.dbid', //排序的列
			sortOrder : 'asc', //正序
			remoteSort : true, //服务器端排序
			idField : 'id', //主键字段
			queryParams : {}, //查询条件
			pagination : true, //显示分页
			rownumbers : true, //显示行号
			columns : [ [ {
				field : 'ck',
				checkbox : false,
				width : 2
			}, //显示复选框
			{
				field : 'row.id',
				title : '险种ID',
				width : 80,
				sortable : true,
				hidden:true,
				formatter : function(value, row, index) {
					return row.id;
				} //需要formatter一下才能显示正确的数据
			},{
				field : 'row.productCode',
				title : '险种代码',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.productCode;
				} //需要formatter一下才能显示正确的数据
			}, {
				field : 'row.productName',
				title : '险种名称',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.productName;
				}
			},{
				field : 'row.sumAssured',
				title : '保额/份数',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.sumAssured;
				}
			},{
				field : 'row.currentModePremium',
				title : '保费',
				width : 50,
				sortable : true,
				formatter : function(value, row, index) {
					return row.currentModePremium;
				}
			},{
				field : 'row.planEffectiveDate',
				title : '生效日期',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.planEffectiveDate;
				}
			},{
				field : 'row.planStatus',
				title : '险种状态',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.planStatus;
				}
			},{
				field : 'row.payToDate',
				title : '保费缴至日',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.payToDate;
				}
			},{
				field : 'row.paidUpDate',
				title : '保费缴清日',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.paidUpDate;
				}
			},{
				field : 'row.socialSecurityFlag',
				title : '社保选项',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.socialSecurityFlag;
				}
			},{
				field : 'row.addPremiumGrade',
				title : '加费级别',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.addPremiumGrade;
				}
			},{
				field : 'row.addPremiumRate',
				title : '加费费率',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.addPremiumRate;
				}
			},{
				field : 'row.planTerminatedDate',
				title : '险种满期日',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.paidUpDate;
				}
			},{
				field : 'row.standby',
				title : '退费',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.standby;
				}
			}
			] ],
			onLoadSuccess : function() {
				$('#productTableUpdate').datagrid('clearSelections'); 
			}
		});
		//控制显示
		controllDisplay();
	});

//计算
function computeProductDel() {
	var posID = $('#posID').val();
	var ps = '?posID=' + posID;

	var rows = $('#productTable').datagrid('getSelections');
	if (rows.length == 0) {
		alert("请选择要取消的险种");
		return;
	} else {
		$.each(rows, function(i, n) {
			ps += "&pdID=" + n.id;
		});
		sAlert('正在计算，请您耐心等候...');
		$.post('computeProductDel' + ps, function(data) {
			if (data.msg == 'success') {
				cAlert();
				$.messager.alert('提示', '计算成功', 'info');
				$('#productTableUpdate').datagrid('cisreload');
				//控制显示
				controllDisplay();
			} else {
				cAlert();
				$.messager.alert('提示', data.msg, 'info');
			}
		});
	}
}


//控制页面显示
function controllDisplay() {
	var id = $('#posID').val();
	$.post('isComputeUrl?id=' + id, function(data) {
			if (data.sign == 'true') {
				$('#tabdiv2').css('display', '');
//				$('#tabdiv3').css('display','');
				$('#divbankinfo').css('display', '');
			} else {
				$('#tabdiv2').css('display', 'none');
//				$('#tabdiv3').css('display','none');
				$('#divbankinfo').css('display', 'none');
			}	

	});
}


//保存
function save() {
	if($('#posTypeForm').form('validate')){
		var id = $('#posID').val();
		var bankid = $('#bankId').combobox("getValue");
		var accname = $('#accname').val();
		var accno = $('#accno1').val();
		var effencientdate=$('#effencientdate').datebox('getValue');
		var dlist = [];
		dlist.push({
					"id" :id ,
					"bankid" : bankid,
					"accname" : accname,
					"accno":accno,
					"effencientdate":effencientdate
				});
		sAlert('正在提交，请您耐心等候...');
		$.ajax({
			url:'posSaveUrl',
			data:$.toJSON(dlist),
			type:'POST',
			async:false,
			contentType:'application/json;charset=utf-8', 
			success:function(data) {
				cAlert();
				if (data.msg == 'success') {
					$.messager.alert('提示', '保存成功', 'info');
				} else {
					$.messager.alert('提示', data.msg, 'info');
				}
			}
		});
	}else {
		cAlert();
		$.messager.alert('提示', '界面校验未通过，或者尚未进行计算！', 'info');
	}
}

//提交
function submitTask() {
	if($('#posTypeForm').form('validate')){
		var id = $('#posID').val();
		var bankid = $('#bankId').combobox("getValue");
		var accname = $('#accname').val();
		var accno = $('#accno1').val();
		var effencientdate=$('#effencientdate').datebox('getValue');
		var dlist = [];
		dlist.push({
					"id" :id ,
					"bankid" : bankid,
					"accname" : accname,
					"accno":accno,
					"effencientdate":effencientdate
				});
		sAlert('正在提交，请您耐心等候...');

		$.ajax({
			url:'posSaveUrl',
			data:$.toJSON(dlist),
			type:'POST',
			async:false,
			contentType:'application/json;charset=utf-8', 
			success:function(data) {
				cAlert();
				if (data.msg == 'success') {

					var id = $('#posID').val();
					$.post('posSubmitUrl?id=' + id, function(data) {
						if (data.msg == 'success') {
					
							$.messager.alert('提示信息', '处理成功！', 'info',function(){
								parent.addtab('我的任务','nb/listTaskUrl');	
								parent.deletetab('保全处理');
							});
							
						} else {
							$.messager.alert('提示', data.msg, 'info');
						}
					});
				} else {

					$.messager.alert('提示', data.msg, 'info');
				}
			}
		});
	}else {
		$.messager.alert('提示', '界面校验未通过，或者尚未进行计算！', 'info');
	}
}

