
jQuery(function($) {
	var lastIndex;
	var sex = [ {
		bnfSex : 'M',
		name : '男'
	}, {
		bnfSex : 'F',
		name : '女'
	} ];
	var idpermanent = [ {
		code : '0',
		name : 'N'
	}, {
		code : '1',
		name : 'Y'
	} ];
	var bnfrelation = [ {
		code : '1',
		name : '父母'
	}, {
		code : '2',
		name : '配偶'
	}, {
		code : '3',
		name : '子女'
	},{
		code : '4',
		name : '祖父母、外祖父母'
	},{
		code : '5',
		name : '孙子女、外孙子女'
	},{
		code : '6',
		name : '兄弟姐妹'
	},{
		code : '7',
		name : '监护人'
	},{
		code : '99',
		name : '其他'
	}];
	
	var idtype = [ {
		code : '00',
		name : '身份证'
	}, {
		code : '01',
		name : '军官证'
	}, {
		code : '02',
		name : '士兵证'
	}, {
		code : '03',
		name : '临时身份证'
	}, {
		code : '04',
		name : '港澳居民来往大陆通行证'
	}, {
		code : '05',
		name : '台湾居民来往大陆通行证'
	}, {
		code : '06',
		name : '护照'
	}, {
		code : '99',
		name : '其他'
	} ];

	$.ajax({
		url:'queryPosCom',
//		data:$.toJSON(paramlist),
		type:'POST',
		async:false,
		contentType:'application/json;charset=utf-8', 
		success:function(data) {
			if (data.msg == 'success') {
				// 社保标记
				$('#sbflag')
						.combobox({ data : data.Social_Security_Flag,
									valueField : 'code',
									textField : 'name'
								});
				// 性别
				$('#appsex')
						.combobox({ data : data.Sex,
									valueField : 'code',
									textField : 'name'
								});
				
				$('#inssex')
						.combobox({ data : data.Sex,
									valueField : 'code',
									textField : 'name'
								});
				// 婚姻状况
				$('#appmarital_status')
						.combobox({ data : data.Marital_Status,
									valueField : 'code',
									textField : 'name'
								});
				$('#insmarital_status')
						.combobox({ data : data.Marital_Status,
									valueField : 'code',
									textField : 'name'
								});
				// 证件类型
				$('#appidtype')
						.combobox({ data : data.ID_Type,
									valueField : 'code',
									textField : 'name'
								});
				$('#insidtype')
						.combobox({	data : data.ID_Type,
									valueField : 'code',
									textField : 'name'
								});
				// 收入来源
				$('#appincomesource')
						.combobox({ data : data.Income_Source,
									valueField : 'code',
									textField : 'name'
								});
				$('#insincomesource')
						.combobox({ data : data.Income_Source,
									valueField : 'code',
									textField : 'name'
								});
				// 投被保人关系
				$('#reltoapp')
						.combobox({ data : data.AP_Insured_Relation,
									valueField : 'code',
									textField : 'name'
								});				
				// 国籍
				var appnationindex=0;
				$('#appnationality').combobox({
					data : data.Nationality,
					valueField : 'code',
					textField : 'name'
				});

				// 国籍
				var insnationindex=0;
				$('#insnationality').combobox({
					data : data.Nationality,
					valueField : 'code',
					textField : 'name'
				});
				
				var appoccindex=0;
				$('#appoccupationcode').combobox({
					valueField : 'id',
					textField : 'code',
					onShowPanel:function(){
						if(appoccindex==0){
							$('#appoccupationcode').combobox('loadData',  data.Occupation);
							appoccindex++;
						}
					},
					onSelect : function(red) {
						var code = $('#appoccupationcode').combobox('getValue');
						if(code!=null && code !="" && code !='null'){
							$.post('../occupation/queryOccuName?code=' + code, function(data) {
								if (data.msg == 'success') {
									$('#appoccupationname').val(data.name);
									$('#appoccupationlevel').val(data.level);
								} else {
								}
							});				
						}

					}
				});
				var insoccindex=0;
				$('#insoccupationcode').combobox({
					valueField : 'id',
					textField : 'code',
					disableQueryAll : true,
					onShowPanel:function(){
						if(insoccindex==0){
							$('#insoccupationcode').combobox('loadData',  data.Occupation);
							insoccindex++;
						}
					},
					onSelect : function(red) {	
						var code = $('#insoccupationcode').combobox('getValue');
						$.post('../occupation/queryOccuName?code=' + code, function(data) {
							if (data.msg == 'success') {
								$('#insoccupationname').val(data.name);
								$('#insoccupationlevel').val(data.level);
							} else {
							}
						});
					}
				});
				
				 //省
//				$('#appaddpro').combobox({
//					data : data.Province,
//					valueField : 'code',
//					textField : 'name',
//					onSelect : function(rec) {
//						var proID = $('#appaddpro').combobox('getValue');
//						var url = '../region/queryAllCity?proID=' + proID;
//						var depurl = '../region/queryAllDep?cityID=9999';
//						$('#appaddcity').combobox('clear');
//						$('#appadddep').combobox('clear');
//						$('#appaddcity').combobox('reload', url);
//						$('#appadddep').combobox('reload', depurl);
//					}
//				});
//				
//				// 省
//				$('#insaddpro').combobox({
//					data : data.Province,
//					valueField : 'code',
//					textField : 'name',
//					onSelect : function(rec) {
//						var proID = $('#insaddpro').combobox('getValue');
//						var url = '../region/queryAllCity?proID=' + proID;
//						var depurl = '../region/queryAllDep?cityID=9999';
//						$('#insaddcity').combobox('clear');
//						$('#insadddep').combobox('clear');
//						$('#insaddcity').combobox('reload', url);
//						$('#insadddep').combobox('reload', depurl);
//					}
//				});
//				

			} else {
			}
		}
	});

	 //省
	$('#appaddpro').combobox({
		url : '../region/queryAllPro',
		valueField : 'code',
		textField : 'name',
		onSelect : function(rec) {
			var proID = $('#appaddpro').combobox('getValue');
			var url = '../region/queryAllCity?proID=' + proID;
			var depurl = '../region/queryAllDep?cityID=9999';
			$('#appaddcity').combobox('clear');
			$('#appadddep').combobox('clear');
			$('#appaddcity').combobox('reload', url);
			$('#appadddep').combobox('reload', depurl);
		}
	});
	// 市
	$('#appaddcity').combobox(
			{
				url : '../region/queryAllCity?proID='
						+ $('#appaddpro').combobox('getValue'),
				valueField : 'code',
				textField : 'name',
				onSelect : function(rec) {
					var cityID = $('#appaddcity').combobox('getValue');
					var url = '../region/queryAllDep?cityID=' + cityID;
					$('#appadddep').combobox('clear');
					$('#appadddep').combobox('reload', url);
				}
			});

	// 区
	$('#appadddep').combobox(
			{
				url : '../region/queryAllDep?cityID='
						+ $('#appaddcity').combobox('getValue'),
				valueField : 'code',
				textField : 'name'
			});

	// 省
	$('#insaddpro').combobox({
		url : '../region/queryAllPro',
		valueField : 'code',
		textField : 'name',
		onSelect : function(rec) {
			var proID = $('#insaddpro').combobox('getValue');
			var url = '../region/queryAllCity?proID=' + proID;
			var depurl = '../region/queryAllDep?cityID=9999';
			$('#insaddcity').combobox('clear');
			$('#insadddep').combobox('clear');
			$('#insaddcity').combobox('reload', url);
			$('#insadddep').combobox('reload', depurl);
		}
	});
	// 市
	$('#insaddcity').combobox(
			{
				url : '../region/queryAllCity?proID='
						+ $('#insaddpro').combobox('getValue'),
				valueField : 'code',
				textField : 'name',
				onSelect : function(rec) {
					var cityID = $('#insaddcity').combobox('getValue');
					var url = '../region/queryAllDep?cityID=' + cityID;
					$('#insadddep').combobox('clear');
					$('#insadddep').combobox('reload', url);
				}
			});

	// 区
	$('#insadddep').combobox(
			{
				url : '../region/queryAllDep?cityID='
						+ $('#insaddcity').combobox('getValue'),
				valueField : 'code',
				textField : 'name'
			});

	var flag = $('#flag').val();
	var posId =  $('#posID').val();
	//结案后查询
	if("caseOver"== flag){
		var nextPosId = $('#nextPosId').val();
		if(nextPosId != null  && nextPosId != ""){
			posId = nextPosId;
		}	
	}
	
	$('#bnfTable').datagrid({
		title : '受益人信息', // 标题
		method : 'get',
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		height : 180, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		rownumbers : true, // 显示行号
		url : "queryBnfList?posID=" + posId, // 数据来源

		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
	
				if ($('#bnfflag').val() == "Y") {
					$.messager.alert('提示', '已选择法定受益人，不允许添加！', 'info');
					return;
				}
				$('#bnfTable').datagrid('endEdit', lastIndex);
				$('#bnfTable').datagrid('appendRow', {
					id : '',
					bnfName : '',
					bnfSex : '',
					relaToIns : '',
					bnfIDType : '',
					bnfIDNo : '',
					bnfPermanent : '',
					bnfExpiry : '',
					bnfBirthday : '',
					bnfOrder : '1',
					bnfPercent : ''
				});
				var lastIndex = $('#bnfTable').datagrid('getRows').length - 1;
				$('#bnfTable').datagrid('beginEdit', lastIndex);
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var row = $('#bnfTable').datagrid('getSelected');
				if (row) {
					var index = $('#bnfTable').datagrid('getRowIndex', row);
					$('#bnfTable').datagrid('deleteRow', index);
				}
			}
		}, '-', {
			text : '变更',
			iconCls : 'icon-save',
			handler : function() {
				$('#bnfTable').datagrid('acceptChanges');
			}
		}, '-', {
			text : '<input id="law" name = "law" type="checkbox"/>&nbsp;法定受益人',
			handler : function() {
				if ($('#law').is(":checked")) {
					$('#bnfflag').val('Y');
					var trow = $('#bnfTable').datagrid('getData').total;
					for ( var j = 0; j < trow; j++) {
						$('#bnfTable').datagrid('deleteRow', 0);
					}
				} else {
					$('#bnfflag').val('N');
					$('#bnfTable').datagrid('rejectChanges');
				}
			}
		} ],
		onBeforeLoad : function() {
			$(this).datagrid('rejectChanges');
		},
		onClickRow : function(rowIndex) {
			if (lastIndex != rowIndex) {
				$('#bnfTable').datagrid('endEdit', lastIndex);
				$('#bnfTable').datagrid('beginEdit', rowIndex);
			} else{
				$('#bnfTable').datagrid('endEdit', lastIndex);
				$('#bnfTable').datagrid('beginEdit', rowIndex);
			}
			lastIndex = rowIndex;
		},

		columns : [ [ {
			field : 'ck',
			checkbox : true,
			width : 2
			}, 
			{
			field : 'id',
			title : '受益人ID',
			width : 80,
			sortable : false,
			hidden : true

		}, {
			field : 'bnfName',
			title : '姓名',
			width : 70,
			sortable : false,
			editor : "text"
		}, {
			field : 'bnfSex',
			title : '性别',
			width : 50,
			sortable : false,
			formatter : function(value, row, index) {
				for ( var i = 0; i < sex.length; i++) {
					if (sex[i].bnfSex == row.bnfSex)
						return sex[i].name;
				}
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					valueField : 'bnfSex',
					textField : 'name',
					data : sex,
					required : true
				}
			}
		}, {
			field : 'relaToIns',
			title : '是被保人的',
			width : 80,
			sortable : false,
			formatter : function(value, row, index) {
				for ( var i = 0; i < bnfrelation.length; i++) {
					if (bnfrelation[i].code == row.relaToIns)
						return bnfrelation[i].name;
				}
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					valueField : 'code',
					textField : 'name',
					data : bnfrelation,
					required : true
				}
			}
		}, {
			field : 'bnfIDType',
			title : '证件类型',
			width : 152,
			sortable : false,
			formatter : function(value, row, index) {
				for ( var i = 0; i < idtype.length; i++) {
					if (idtype[i].code == row.bnfIDType)
						return idtype[i].name;
				}
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					valueField : 'code',
					textField : 'name',
					data : idtype,
					required : true
				}
			}
		}, {
			field : 'bnfIDNo',
			title : '证件号码',
			width : 125,
			sortable : false,
			editor : "text"
		}, {
			field : 'bnfPermanent',
			title : '证件有效位长期标记',
			width : 125,
			sortable : false,
			formatter : function(value, row, index) {
				for ( var i = 0; i < idpermanent.length; i++) {
					if (idpermanent[i].code == row.bnfPermanent)
						return idpermanent[i].name;
				}
				return value;
			},
			editor : {
				type : 'checkbox',
				options : {
					on : 'Y',
					off : 'N'
				}
			}
		}, {
			field : 'bnfExpiry',
			title : '证件有效期至',
			width : 100,
			sortable : false,
			editor : "datebox"
		}, {
			field : 'bnfBirthday',
			title : '出生日期',
			width : 95,
			sortable : false,
			editor : "datebox"
		}, {
			field : 'bnfOrder',
			title : '受益顺序',
			width : 70,
			sortable : false
		}, {
			field : 'bnfPercent',
			title : '受益份额%',
			width : 80,
			sortable : false,
			editor : "numberbox"
		} ] ],
		onLoadSuccess : function() {
			$('#bnfTable').datagrid('clearSelections');
		}
	});

	var appExpiryDate = $('#appexpirydate').val();
	if ($('#apppermanent').is(":checked")) {
		$('#appexpirydate').attr("disabled", "disabled");
	}
	$('#apppermanent').click(function() {
		if ($('#apppermanent').is(":checked")) {
			$('#appexpirydate').val('');
			$('#appexpirydate').attr("disabled", "disabled");

		} else {
			$('#appexpirydate').val(appExpiryDate);
			$('#appexpirydate').removeAttr("disabled");

		}
	});

	var insExpiryDate = $('#insexpirydate').val();
	if ($('#inspermanent').is(":checked")) {
		$('#insexpirydate').attr("disabled", "disabled");
	}
	$('#inspermanent').click(function() {
		if ($('#inspermanent').is(":checked")) {
			$('#insexpirydate').val('');
			$('#insexpirydate').attr("disabled", "disabled");

		} else {
			$('#insexpirydate').val(appExpiryDate);
			$('#insexpirydate').removeAttr("disabled");

		}
	});
	// 设置投保人信息是否可编辑
	setAppDisable();
	// 初始化页面空间
	initControl();
	
	initDisplay();
});
// 初始化页面
function initControl() {
}
//提交
function submitTask() {

	if ($('#policyForm').form('validate')) {
		var poslist = [];
		var applist = [];
		var inslist = [];
		var posID = $('#posID').val();
		var policyno = $('#policyno').val();
		var bnfflag = $('#bnfflag').val();
		
		var effectivedate= $('#effencientdate').val();
		if(effectivedate!=null && effectivedate!=""&&effectivedate!='null'){
			if(!isDateTime.call(effectivedate,'yyyy-MM-dd')){
				$.messager.alert('提示', '保全生效日期格式不正确！', 'info');
				return;
			}
		}		
		poslist.push({
			"posID" : posID,
			"policyno" : policyno,
			"bnfflag" : bnfflag,
			"effectivedate" :effectivedate

		});
		var appname = $('#appname').val();
		var appsex = $('#appsex').combobox('getValue');
		var appnationality = $('#appnationality').combobox('getValue');
		var appmarital_status = $('#appmarital_status').combobox('getValue');
		var appbirthday = $('#appbirthday').val();
		var appidtype = $('#appidtype').combobox('getValue');
		var appid = $('#appid').val();
		var appexpirydate = $('#appexpirydate').val();
		var apppermanent = '0';
		if ($('#apppermanent').is(":checked")) {
			apppermanent = '1';
		}
		var appoccupationcode = $('#appoccupationcode').combobox('getValue');
		var appoccupationname = $('#appoccupationname').val();
		var appoccupationlevel = $('#appoccupationlevel').val();
		var appincome = $('#appincome').val();
		var appincomesource = $('#appincomesource').combobox('getValue');
		var appemail = $('#appemail').val();
		var reltoapp = $('#reltoapp').combobox('getValue');
		var appphone = $('#appphone').val();
		var appmobile = $('#appmobile').val();
		var appaddpro = $('#appaddpro').combobox('getValue');
		var appaddcity = $('#appaddcity').combobox('getValue');
		var appadddep = $('#appadddep').combobox('getValue');
		var appaddress = $('#appaddress').val();
		var appzipcode = $('#appzipcode').val();

		// 校验头被保人身份证号码
		if (appidtype == "00" || appidtype == "03") {
			if (!idCard(appid)) {
				$.messager.alert('提示', '投保人身份证号码不合法', 'info');
				return;
			}
		}
		applist.push({
			"appname" : appname,
			"appsex" : appsex,
			"appnationality" : appnationality,
			"appmarital_status" : appmarital_status,
			"appbirthday" : appbirthday,
			"appidtype" : appidtype,
			"appid" : appid,
			"appexpirydate" : appexpirydate,
			"apppermanent" : apppermanent,
			"appoccupationcode" : appoccupationcode,
			"appoccupationname" : appoccupationname,
			"appoccupationlevel" : appoccupationlevel,
			"appincome" : appincome,
			"appincomesource" : appincomesource,
			"appemail" : appemail,
			"reltoapp" : reltoapp,
			"appphone" : appphone,
			"appmobile" : appmobile,
			"appaddpro" : appaddpro,
			"appaddcity" : appaddcity,
			"appadddep" : appadddep,
			"appaddress" : appaddress,
			"appzipcode" : appzipcode
		});

		var insname = $('#insname').val();
		var inssex = $('#inssex').combobox('getValue');
		var insnationality = $('#insnationality').combobox('getValue');
		var insmarital_status = $('#insmarital_status').combobox('getValue');
		var insbirthday = $('#insbirthday').val();
		var insidtype = $('#insidtype').combobox('getValue');
		var insid = $('#insid').val();
		var insexpirydate = $('#insexpirydate').val();
		var inspermanent = '0';
		if ($('#inspermanent').is(":checked")) {
			inspermanent = '1';
		}
		var insoccupationcode = $('#insoccupationcode').combobox('getValue');
		var insoccupationname = $('#insoccupationname').val();
		var insoccupationlevel = $('#insoccupationlevel').val();
		var insincome = $('#insincome').val();
		var insincomesource = $('#insincomesource').combobox('getValue');
		var insemail = $('#insemail').val();
		var sbflag = $('#sbflag').combobox('getValue');
		var insphone = $('#insphone').val();
		var insmobile = $('#insmobile').val();
		var insaddpro = $('#insaddpro').combobox('getValue');
		var insaddcity = $('#insaddcity').combobox('getValue');
		var insadddep = $('#insadddep').combobox('getValue');
		var insaddress = $('#insaddress').val();
		var inszipcode = $('#inszipcode').val();

		// 校验头被保人身份证号码
		if (insidtype == "00" || insidtype == "03") {
			if (!idCard(insid)) {
				$.messager.alert('提示', '被保人身份证号码不合法', 'info');
				return;
			}
		}
		inslist.push({
			"insname" : insname,
			"inssex" : inssex,
			"insnationality" : insnationality,
			"insmarital_status" : insmarital_status,
			"insbirthday" : insbirthday,
			"insidtype" : insidtype,
			"insid" : insid,
			"insexpirydate" : insexpirydate,
			"inspermanent" : inspermanent,
			"insoccupationcode" : insoccupationcode,
			"insoccupationname" : insoccupationname,
			"insoccupationlevel" : insoccupationlevel,
			"insincome" : insincome,
			"insincomesource" : insincomesource,
			"insemail" : insemail,
			"sbflag" : sbflag,
			"insphone" : insphone,
			"insmobile" : insmobile,
			"insaddpro" : insaddpro,
			"insaddcity" : insaddcity,
			"insadddep" : insadddep,
			"insaddress" : insaddress,
			"inszipcode" : inszipcode
		});

		var bnflist = $("#bnfTable").datagrid('getData');
		var jsonlength = bnflist.rows.length;

		var totalPercent = 0;

		for ( var i = 0; i < jsonlength; i++) {
			var idtype = bnflist.rows[i].bnfIDType;
			var id = bnflist.rows[i].bnfIDNo;
			var name = bnflist.rows[i].bnfName;
			if(name==null || name=="" || name =='null'){
				$.messager.alert('提示', '受益人姓名不能为空', 'info');
				return;
			}

			var percent = bnflist.rows[i].bnfPercent;
	
			if(percent==null || (percent=='' &&  percent!='0')){
				$.messager.alert('提示', '受益人份额不能为空', 'info');
				return;
			}
			totalPercent = parseInt(totalPercent) + parseInt(percent);
			
			var bnfExpiry = bnflist.rows[i].bnfExpiry;
			var bnfBirthday = bnflist.rows[i].bnfBirthday;
			
			if(bnfExpiry!=null&&bnfExpiry!=""&&bnfExpiry!='null'){
				if(!isDateTime.call(bnfExpiry,'yyyy-MM-dd')){
					var msg = "受益人" + name + "证件有效期格式不合法!";
					$.messager.alert('提示', msg, 'info');
					return;
				}
			}
			
			if(bnfBirthday!=null&&bnfBirthday!=""&&bnfBirthday!='null'){
				if(!isDateTime.call(bnfBirthday,'yyyy-MM-dd')){
					var msg = "受益人" + name + "出生日期格式不合法!";
					$.messager.alert('提示', msg, 'info');
					return;
				}
			} else{
				var msg = "受益人" + name + "出生日期不能为空!";
				$.messager.alert('提示', msg, 'info');
				return;
			}
			
			if (idtype == "00" || idtype == "03") {
				if (!idCard(id)) {
					var msg = "受益人" + name + "身份证号码不合法!";
					$.messager.alert('提示', msg, 'info');
					return;
				}
			}
		}
		if (jsonlength >0 && totalPercent != 100) {
			$.messager.alert('提示', '受益人分配份额之和应该100%', 'info');
			return;
		}
		if(appbirthday!=null && appbirthday!=""&&appbirthday!='null'){
			if(!isDateTime.call(appbirthday,'yyyy-MM-dd')){
				$.messager.alert('提示', '投保人出生日期格式不正确！', 'info');
				return;
			}
		}

		if(appexpirydate!=null && appexpirydate!=""&&appexpirydate!='null'){
			if(!isDateTime.call(appexpirydate,'yyyy-MM-dd')){
				$.messager.alert('提示', '投保人证件有效期格式不正确！', 'info');
				return;
			}
		}

		if(insbirthday!=null && insbirthday!=""&&insbirthday!='null'){
			if(!isDateTime.call(insbirthday,'yyyy-MM-dd')){
				$.messager.alert('提示', '被保人出生日期格式不正确！', 'info');
				return;
			}
		}
		if(insexpirydate!=null && insexpirydate!=""&&insexpirydate!='null'){
			if(!isDateTime.call(insexpirydate,'yyyy-MM-dd')){
				$.messager.alert('提示', '被保人证件有效期格式不正确！', 'info');
				return;
			}
		}
		var paramlist = [];
		paramlist.push({
			"applist" : applist,
			"inslist" : inslist,
			"bnflist" : bnflist,
			"poslist" : poslist
		});
		sAlert('正在提交数据，请您耐心等候...');
		$.ajax({
			url:'savePolicyInfo',
			data:$.toJSON(paramlist),
			type:'POST',
			async:false,
			contentType:'application/json;charset=utf-8', 
			success:function(data) {
			if (data.msg == 'success') {
				var id = $('#posID').val();
				$.post('posSubmitUrl?id=' + id, function(data) {
					if (data.msg == 'success') {
							cAlert();
							hiddenButton();
							$.messager.alert('提示信息', '保全提交成功！', 'info',function(){
							parent.addtab('我的任务','nb/listTaskUrl');	
							parent.deletetab('保全处理');
						});
					} else {
						cAlert();
						$.messager.alert('提示', data.msg, 'info');
					}
				});
			} 
		}});
	}

}

// 保存
function save() {

	if ($('#policyForm').form('validate')) {
		
		var poslist = [];
		var applist = [];
		var inslist = [];
		var posID = $('#posID').val();
		var policyno = $('#policyno').val();
		var bnfflag = $('#bnfflag').val();
		
		var effectivedate= $('#effencientdate').val();
		if(effectivedate!=null && effectivedate!=""&&effectivedate!='null'){
			if(!isDateTime.call(effectivedate,'yyyy-MM-dd')){
				$.messager.alert('提示', '保全生效日期格式不正确！', 'info');
				return;
			}
		}		
		poslist.push({
			"posID" : posID,
			"policyno" : policyno,
			"bnfflag" : bnfflag,
			"effectivedate" :effectivedate

		});
		var appname = $('#appname').val();
		var appsex = $('#appsex').combobox('getValue');
		var appnationality = $('#appnationality').combobox('getValue');
		var appmarital_status = $('#appmarital_status').combobox('getValue');
		var appbirthday = $('#appbirthday').val();
		var appidtype = $('#appidtype').combobox('getValue');
		var appid = $('#appid').val();
		var appexpirydate = $('#appexpirydate').val();
		var apppermanent = '0';
		if ($('#apppermanent').is(":checked")) {
			apppermanent = '1';
		}
		var appoccupationcode = $('#appoccupationcode').combobox('getValue');
		var appoccupationname = $('#appoccupationname').val();
		var appoccupationlevel = $('#appoccupationlevel').val();
		var appincome = $('#appincome').val();
		var appincomesource = $('#appincomesource').combobox('getValue');
		var appemail = $('#appemail').val();
		var reltoapp = $('#reltoapp').combobox('getValue');
		var appphone = $('#appphone').val();
		var appmobile = $('#appmobile').val();
		var appaddpro = $('#appaddpro').combobox('getValue');
		var appaddcity = $('#appaddcity').combobox('getValue');
		var appadddep = $('#appadddep').combobox('getValue');
		var appaddress = $('#appaddress').val();
		var appzipcode = $('#appzipcode').val();

		// 校验头被保人身份证号码
		if (appidtype == "00" || appidtype == "03") {
			if (!idCard(appid)) {
				$.messager.alert('提示', '投保人身份证号码不合法', 'info');
				return;
			}
		}
		applist.push({
			"appname" : appname,
			"appsex" : appsex,
			"appnationality" : appnationality,
			"appmarital_status" : appmarital_status,
			"appbirthday" : appbirthday,
			"appidtype" : appidtype,
			"appid" : appid,
			"appexpirydate" : appexpirydate,
			"apppermanent" : apppermanent,
			"appoccupationcode" : appoccupationcode,
			"appoccupationname" : appoccupationname,
			"appoccupationlevel" : appoccupationlevel,
			"appincome" : appincome,
			"appincomesource" : appincomesource,
			"appemail" : appemail,
			"reltoapp" : reltoapp,
			"appphone" : appphone,
			"appmobile" : appmobile,
			"appaddpro" : appaddpro,
			"appaddcity" : appaddcity,
			"appadddep" : appadddep,
			"appaddress" : appaddress,
			"appzipcode" : appzipcode
		});

		var insname = $('#insname').val();
		var inssex = $('#inssex').combobox('getValue');
		var insnationality = $('#insnationality').combobox('getValue');
		var insmarital_status = $('#insmarital_status').combobox('getValue');
		var insbirthday = $('#insbirthday').val();
		var insidtype = $('#insidtype').combobox('getValue');
		var insid = $('#insid').val();
		var insexpirydate = $('#insexpirydate').val();
		var inspermanent = '0';
		if ($('#inspermanent').is(":checked")) {
			inspermanent = '1';
		}
		var insoccupationcode = $('#insoccupationcode').combobox('getValue');
		var insoccupationname = $('#insoccupationname').val();
		var insoccupationlevel = $('#insoccupationlevel').val();
		var insincome = $('#insincome').val();
		var insincomesource = $('#insincomesource').combobox('getValue');
		var insemail = $('#insemail').val();
		var sbflag = $('#sbflag').combobox('getValue');
		var insphone = $('#insphone').val();
		var insmobile = $('#insmobile').val();
		var insaddpro = $('#insaddpro').combobox('getValue');
		var insaddcity = $('#insaddcity').combobox('getValue');
		var insadddep = $('#insadddep').combobox('getValue');
		var insaddress = $('#insaddress').val();
		var inszipcode = $('#inszipcode').val();

		// 校验头被保人身份证号码
		if (insidtype == "00" || insidtype == "03") {
			if (!idCard(insid)) {
				$.messager.alert('提示', '被保人身份证号码不合法', 'info');
				return;
			}
		}
		inslist.push({
			"insname" : insname,
			"inssex" : inssex,
			"insnationality" : insnationality,
			"insmarital_status" : insmarital_status,
			"insbirthday" : insbirthday,
			"insidtype" : insidtype,
			"insid" : insid,
			"insexpirydate" : insexpirydate,
			"inspermanent" : inspermanent,
			"insoccupationcode" : insoccupationcode,
			"insoccupationname" : insoccupationname,
			"insoccupationlevel" : insoccupationlevel,
			"insincome" : insincome,
			"insincomesource" : insincomesource,
			"insemail" : insemail,
			"sbflag" : sbflag,
			"insphone" : insphone,
			"insmobile" : insmobile,
			"insaddpro" : insaddpro,
			"insaddcity" : insaddcity,
			"insadddep" : insadddep,
			"insaddress" : insaddress,
			"inszipcode" : inszipcode
		});

		var bnflist = $("#bnfTable").datagrid('getData');
		var jsonlength = bnflist.rows.length;

		var totalPercent = 0;

		for ( var i = 0; i < jsonlength; i++) {
			var idtype = bnflist.rows[i].bnfIDType;
			var id = bnflist.rows[i].bnfIDNo;
			var name = bnflist.rows[i].bnfName;
			if(name==null || name=="" || name =='null'){
				$.messager.alert('提示', '受益人姓名不能为空', 'info');
				return;
			}

			var percent = bnflist.rows[i].bnfPercent;
	
			if(percent==null || (percent=='' &&  percent!='0')){
				$.messager.alert('提示', '受益人份额不能为空', 'info');
				return;
			}
			totalPercent = parseInt(totalPercent) + parseInt(percent);
			
			var bnfExpiry = bnflist.rows[i].bnfExpiry;
			var bnfBirthday = bnflist.rows[i].bnfBirthday;
			
			if(bnfExpiry!=null&&bnfExpiry!=""&&bnfExpiry!='null'){
				if(!isDateTime.call(bnfExpiry,'yyyy-MM-dd')){
					var msg = "受益人" + name + "证件有效期格式不合法!";
					$.messager.alert('提示', msg, 'info');
					return;
				}
			}
			
			if(bnfBirthday!=null&&bnfBirthday!=""&&bnfBirthday!='null'){
				if(!isDateTime.call(bnfBirthday,'yyyy-MM-dd')){
					var msg = "受益人" + name + "出生日期格式不合法!";
					$.messager.alert('提示', msg, 'info');
					return;
				}
			} else{
				var msg = "受益人" + name + "出生日期不能为空!";
				$.messager.alert('提示', msg, 'info');
				return;
			}
			
			if (idtype == "00" || idtype == "03") {
				if (!idCard(id)) {
					var msg = "受益人" + name + "身份证号码不合法!";
					$.messager.alert('提示', msg, 'info');
					return;
				}
			}
		}
		if (jsonlength >0 && totalPercent != 100) {
			$.messager.alert('提示', '受益人分配份额之和应该100%', 'info');
			return;
		}
		if(appbirthday!=null && appbirthday!=""&&appbirthday!='null'){
			if(!isDateTime.call(appbirthday,'yyyy-MM-dd')){
				$.messager.alert('提示', '投保人出生日期格式不正确！', 'info');
				return;
			}
		}

		if(appexpirydate!=null && appexpirydate!=""&&appexpirydate!='null'){
			if(!isDateTime.call(appexpirydate,'yyyy-MM-dd')){
				$.messager.alert('提示', '投保人证件有效期格式不正确！', 'info');
				return;
			}
		}

		if(insbirthday!=null && insbirthday!=""&&insbirthday!='null'){
			if(!isDateTime.call(insbirthday,'yyyy-MM-dd')){
				$.messager.alert('提示', '被保人出生日期格式不正确！', 'info');
				return;
			}
		}
		if(insexpirydate!=null && insexpirydate!=""&&insexpirydate!='null'){
			if(!isDateTime.call(insexpirydate,'yyyy-MM-dd')){
				$.messager.alert('提示', '被保人证件有效期格式不正确！', 'info');
				return;
			}
		}
		var paramlist = [];
		paramlist.push({
			"applist" : applist,
			"inslist" : inslist,
			"bnflist" : bnflist,
			"poslist" : poslist
		});
		
		sAlert('正在提交数据，请您耐心等候...');
		$.ajax({
			url:'savePolicyInfo',
			data:$.toJSON(paramlist),
			type:'POST',
//			async:false,
			contentType:'application/json;charset=utf-8', 
			success:function(data) {
			if (data.msg == 'success') {
				cAlert();
				$.messager.alert('提示信息', '修改成功！', 'info',function(){
					parent.addtab('保全处理','pos/getPosPageUrl?id='+$('#posID').val()+'&flag=');				

				});
			} else {
				cAlert();
				$.messager.alert('提示', data.msg, 'info');
			}
			}
		});
	}
}

function setAppDisable() {
	// 设置投保人信息不能修改
	if ($('#reltoapp').combobox('getValue') == "0") {
		$('#appname').attr("disabled", "disabled");
		$('#appsex').combobox('disable');
		$('#appnationality').combobox('disable');
		$('#appmarital_status').combobox('disable');
		$('#appbirthday').attr("disabled", "disabled");
		$('#appidtype').combobox('disable');
		$('#appid').attr("disabled", "disabled");
		$('#appexpirydate').attr("disabled", "disabled");
		$('#apppermanent').attr("disabled", "disabled");
		$('#appoccupationcode').combobox('disable');
		$('#appoccupationname').attr("disabled", "disabled");
		$('#appoccupationlevel').attr("disabled", "disabled");
		$('#appincome').attr("disabled", "disabled");
		$('#appincomesource').combobox('disable');
		$('#appphone').attr("disabled", "disabled");
		$('#appmobile').attr("disabled", "disabled");
		$('#appemail').attr("disabled", "disabled");
		$('#appaddress').attr("disabled", "disabled");
		$('#appzipcode').attr("disabled", "disabled");
		$('#appaddpro').combobox('disable');
		$('#appaddcity').combobox('disable');
		$('#appadddep').combobox('disable');
	}

}

function initDisplay(){
//	$('#appname').attr("disabled", "disabled");
//	$('#appsex').combobox('disable');
//	$('#appnationality').combobox('disable');
//	$('#appmarital_status').combobox('disable');
//	$('#appbirthday').attr("disabled", "disabled");
//	$('#appidtype').combobox('disable');
//	$('#appid').attr("disabled", "disabled");
//	$('#appexpirydate').attr("disabled", "disabled");
//	$('#apppermanent').attr("disabled", "disabled");
//	$('#appoccupationcode').combobox('disable');
//	$('#appoccupationname').attr("disabled", "disabled");
//	$('#appoccupationlevel').attr("disabled", "disabled");
//	$('#appincome').attr("disabled", "disabled");
//	$('#appincomesource').combobox('disable');
//	$('#appphone').attr("disabled", "disabled");
//	$('#appmobile').attr("disabled", "disabled");
//	$('#appemail').attr("disabled", "disabled");
//	$('#appaddress').attr("disabled", "disabled");
//	$('#appzipcode').attr("disabled", "disabled");
//	$('#appaddpro').combobox('disable');
//	$('#appaddcity').combobox('disable');
//	$('#appadddep').combobox('disable');
//	$('#reltoapp').combobox('disable');
//	$('#effencientdate').attr("disabled", "disabled");
//	
//	$('#insname').attr("disabled", "disabled");
//	$('#inssex').combobox('disable');
//	$('#insnationality').combobox('disable');
//	$('#insmarital_status').combobox('disable');
//	$('#insbirthday').attr("disabled", "disabled");
//	$('#insidtype').combobox('disable');
//	$('#insid').attr("disabled", "disabled");
//	$('#insexpirydate').attr("disabled", "disabled");
//	$('#inspermanent').attr("disabled", "disabled");
//	$('#insoccupationcode').combobox('disable');
//	$('#insoccupationname').attr("disabled", "disabled");
//	$('#insoccupationlevel').attr("disabled", "disabled");
//	$('#insincome').attr("disabled", "disabled");
//	$('#insincomesource').combobox('disable');
//	$('#insphone').attr("disabled", "disabled");
//	$('#insmobile').attr("disabled", "disabled");
//	$('#insemail').attr("disabled", "disabled");
//	$('#insaddress').attr("disabled", "disabled");
//	$('#inszipcode').attr("disabled", "disabled");
//	$('#insaddpro').combobox('disable');
//	$('#insaddcity').combobox('disable');
//	$('#insadddep').combobox('disable');
//	$('#sbflag').combobox('disable');
//
//	$.extend($.fn.datagrid.defaults.editors, { 
//		text: { 
//		destroy: function(target){ 
//		return $(target).val();
//		}, 
//		} 
//		}); 


}

$(function() {
	$('#reltoapp').combobox({
		onSelect : function() {
			if ($('#reltoapp').combobox('getValue') == '0') {
				$('#appname').attr("disabled", "disabled");
				$('#appsex').combobox('disable');
				$('#appnationality').combobox('disable');
				$('#appmarital_status').combobox('disable');
				$('#appbirthday').attr("disabled", "disabled");
				$('#appidtype').combobox('disable');
				$('#appid').attr("disabled", "disabled");
				$('#appexpirydate').attr("disabled", "disabled");
				$('#apppermanent').attr("disabled", "disabled");
				$('#appoccupationcode').combobox('disable');
				$('#appincome').attr("disabled", "disabled");
				$('#appincomesource').combobox('disable');
				$('#appphone').attr("disabled", "disabled");
				$('#appmobile').attr("disabled", "disabled");
				$('#appemail').attr("disabled", "disabled");
				$('#appaddress').attr("disabled", "disabled");
				$('#appzipcode').attr("disabled", "disabled");
				$('#appaddpro').combobox('disable');
				$('#appaddcity').combobox('disable');
				$('#appadddep').combobox('disable');
			} else {
				$('#appname').removeAttr("disabled");
				$('#appsex').combobox('enable');
				$('#appnationality').combobox('enable');
				$('#appmarital_status').combobox('enable');
				$('#appbirthday').removeAttr("disabled");
				$('#appidtype').combobox('enable');
				$('#appid').removeAttr("disabled");
				$('#appexpirydate').removeAttr("disabled");
				$('#apppermanent').removeAttr("disabled");
				$('#appoccupationcode').combobox('enable');
				$('#appincome').removeAttr("disabled");
				$('#appincomesource').combobox('enable');
				$('#appphone').removeAttr("disabled");
				$('#appmobile').removeAttr("disabled");
				$('#appemail').removeAttr("disabled");
				$('#appaddress').removeAttr("disabled");
				$('#appzipcode').removeAttr("disabled");
				$('#appaddpro').combobox('enable');
				$('#appaddcity').combobox('enable');
				$('#appadddep').combobox('enable');
				if ($('#apppermanent').is(":checked")) {
					$('#appexpirydate').attr("disabled", "disabled");
				}
			}
		}
	});

	if ("Y" == $('#bnfflag').val()) {
		$('#law').click();
	}
	
});
