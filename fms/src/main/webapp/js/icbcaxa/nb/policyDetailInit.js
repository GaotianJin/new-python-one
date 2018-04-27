
jQuery(function($) {
	
	$('#agentTable').datagrid({
		title : '销售人员信息', // 标题
		method : 'get',
		iconCls : 'icon-ok', // 图标
		singleSelect : false, // 多选
		height : 90, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : "", // 数据来源
		width : "100%",
		toolbar : [],
		onBeforeLoad : function() {},
		onClickRow : function(rowIndex) {},
		columns : [[
		            {
		            	field : 'code',
		            	title : '销售人员/机构代码',
		            	width : 150,
		            	sortable : false
		            },
		            {
		            	field : 'name',
		            	title : '销售人员/机构名称',
		            	width : 150,
		            	sortable : false
		            },
		            {
		            	field : 'hander',
		            	title : '代理机构经办人员代码',
		            	width : 150,
		            	sortable : false
		            }
		            ]],
		onLoadSuccess : function() {
			$('#agentTable').datagrid('clearSelections');
		}
	});
	
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
				text : '法定受益人 <input id="law" name = "law" type="checkbox" disabled="disabled"/>'
		}],
		onBeforeLoad : function() {},
		onClickRow : function(rowIndex) {},

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
	
	var sbFlag = planData[0][0].statutoryBeneficiary;
	//if(bnfData!=null&&
	 // bnfData!=''&&
	 // typeof(bnfData)!='undefined')
		loadBnfData(sbFlag);
	if(agentData!=null&&
		agentData!=''&&
		typeof(agentData)!='undefined')
		loadAgentData();
});


function loadAgentData(){	
	$('#agentTable').datagrid('appendRow', {
		code : agentData[0].agentcode,
		name : agentData[0].name,
		hander : ''
	});
}

function loadBnfData(sbFlag){
	if(sbFlag=='Y'){
		$("#law").attr('checked',true);
	}
	if(sbFlag=='N'){
		var bnfList = bnfData[0];
		var bpdList = bnfPlanData[0];
		for(i = 0 ; i < bnfList.length; i++){
			var bnf = bnfList[i];
			    bpd = bpdList[i];
			    
			$('#bnfTable').datagrid('appendRow', {
				bnfName:bnf.clientName,
			bnfSex:bnf.clientSex=='M'?'男':
				 bnf.clientSex=='F'?'女':
				 bnf.clientSex=='U'?'男女':'',
			relaToIns:bpd.relationToInsured=='2'?'配偶':
				bpd.relationToInsured=='7'?'监护人':
				bpd.relationToInsured=='3'?'子女':
				bpd.relationToInsured=='1'?'父母':
				bpd.relationToInsured=='99'?'其他':
				bpd.relationToInsured=='99'?'其他':
				bpd.relationToInsured=='5'?'孙子女、外孙子女':	
				bpd.relationToInsured=='4'?'祖父母、外祖父母':		
				bpd.relationToInsured=='6'?'兄弟姐妹':	'',
			bnfIDType:bnf.idType=='00'?'身份证':
				bnf.idType=='01'?'军官证':
				bnf.idType=='02'?'士兵证':
				bnf.idType=='03'?'临时身份证':
				bnf.idType=='04'?'港澳居民来往大陆通行证':
				bnf.idType=='05'?'台湾居民来往大陆通行证':
				bnf.idType=='06'?'护照':
				bnf.idType=='99'?'其他':'',
			bnfIDNo:bnf.idNo,
			bnfPermanent:bnf.idPermanentFlag=='0'?'否':
						 bnf.idPermanentFlag=='N'?'否':
					     bnf.idPermanentFlag=='1'?'是':
					     bnf.idPermanentFlag=='Y'?'是':'',
			bnfExpiry:formatDate(bnf.idExpiryDate),
			bnfBirthday:formatDate(bnf.clientBirthday),
			bnfOrder:bpd.beneficiaryOrder,
			bnfPercent:bpd.beneficiaryPercentage
			});
		}	
	}
}

function formatDate(date){
	if(date!=null&&date!=''){
		var d = new Date(date);
		return d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate();	
	}
}