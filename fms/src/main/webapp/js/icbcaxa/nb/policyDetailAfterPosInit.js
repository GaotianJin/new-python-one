
jQuery(function($) {
	
	//页面下拉录入框初始化
	$.ajax({
		url:'queryPosCom',
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
			} 
		}
	});
	 
	//投保人地址信息 (省市区)
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
	$('#appadddep').combobox(
			{
				url : '../region/queryAllDep?cityID='
						+ $('#appaddcity').combobox('getValue'),
				valueField : 'code',
				textField : 'name'
			});

	//被保人地址信息(省市区)
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

	$('#insadddep').combobox(
			{
				url : '../region/queryAllDep?cityID='
						+ $('#insaddcity').combobox('getValue'),
				valueField : 'code',
				textField : 'name'
			});
	
	//查询受益人信息
	$('#bnfTable').datagrid({
		title : '受益人信息', // 标题
		method : 'get',
		iconCls : 'icon-ok', // 图标
		singleSelect : false, // 多选
		height : 200, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "", // 数据来源

		toolbar : [{
				text : '法定受益人 <input id="law" name = "law" type="checkbox"/>'
		}],
		columns : [ [{
			field : 'bnfName',
			title : '姓名',
			width : 80,
			sortable : false
		}, {
			field : 'bnfSex',
			title : '性别',
			width : 80,
			sortable : false	
		}, {
			field : 'relaToIns',
			title : '与被保险人关系',
			width : 120,
			sortable : false
		}, {
			field : 'bnfIDType',
			title : '证件类型',
			width : 90,
			sortable : false
		}, {
			field : 'bnfIDNo',
			title : '证件号码',
			width : 120,
			sortable : false
		}, {
			field : 'bnfPermanent',
			title : '证件有效期长期标志位',
			width : 150,
			sortable : false
		}, {
			field : 'bnfExpiry',
			title : '证件有效期至',
			width : 110,
			sortable : false
		}, {
			field : 'bnfBirthday',
			title : '出生日期',
			width : 110,
			sortable : false
		}, {
			field : 'bnfOrder',
			title : '受益顺序',
			width : 80,
			sortable : false
		}, {
			field : 'bnfPercent',
			title : '受益份额%',
			width : 80,
			sortable : false
		} ] ],
		onLoadSuccess : function() {
			$('#bnfTable').datagrid('clearSelections');
		}
	});
	//如果存在受益人自动添加
	if(bnfClients[0].length>0){
		showBnfInfo();
	}else{
		$('#law').click();
	}
	
});


function showBnfInfo(){
	
	var bnfs = bnfClients[0];
	var ptbs =  planToBnfs[0];
	
	
	
	for(var i=0; i<bnfs.length; i++){
		var bnf = bnfs[i];
		var ptb;
		for(var j=0 ; j<ptbs.length; j++){
			ptb = ptbs[j];
			if(bnf.id ==  ptb.policyClientId){
				break;
			}
		}
	
		$('#bnfTable').datagrid('appendRow', {
			bnfName:		bnf.clientName,
			bnfSex:			bnf.clientSex=='M'?'男':
				 			bnf.clientSex=='F'?'女':
				 			bnf.clientSex=='U'?'男女':'',
			relaToIns:		ptb.relationToInsured=='2'?'配偶':
							ptb.relationToInsured=='7'?'监护人':
							ptb.relationToInsured=='3'?'子女':
							ptb.relationToInsured=='1'?'父母':
							ptb.relationToInsured=='99'?'其他':
							ptb.relationToInsured=='99'?'其他':
							ptb.relationToInsured=='5'?'孙子女、外孙子女':	
							ptb.relationToInsured=='4'?'祖父母、外祖父母':		
							ptb.relationToInsured=='6'?'兄弟姐妹':	'',
			bnfIDType:		bnf.idType=='00'?'身份证':
							bnf.idType=='01'?'军官证':
							bnf.idType=='02'?'士兵证':
							bnf.idType=='03'?'临时身份证':
							bnf.idType=='04'?'港澳居民来往大陆通行证':
							bnf.idType=='05'?'台湾居民来往大陆通行证':
							bnf.idType=='06'?'护照':
							bnf.idType=='99'?'其他':'',
			bnfIDNo:		bnf.idNo,
			bnfPermanent:	bnf.idPermanentFlag=='0'?'否':
						 	bnf.idPermanentFlag=='N'?'否':
						 	bnf.idPermanentFlag=='1'?'是':
						 	bnf.idPermanentFlag=='Y'?'是':'',
			bnfExpiry:		formatDate(bnf.idExpiryDate),
			bnfBirthday:	formatDate(bnf.clientBirthday),
			bnfOrder:		ptb.beneficiaryOrder,
			bnfPercent:		ptb.beneficiaryPercentage
			});
	}
	
}


function formatDate(date){
	if(date!=null&&date!=''){
		var d = new Date(date);
		return d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate();	
	}
}