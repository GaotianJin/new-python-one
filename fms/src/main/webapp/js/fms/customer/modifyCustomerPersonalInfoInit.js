var modifyCustomerPersonalInfoinvestTableRowIndex = null;
var modifyCustomerBaseInfo_Id = null;
var modifyCustPersonal_agentId = null;
//var modifyCustPersonal_tradeLoadFlag = null;
var operate = null;
jQuery(function($){
	modifyCustomerBaseInfo_Id = $("#modifyCustomerBaseInfo_Id").val();
	modifyCustPersonal_agentId = $("#modifyCustPersonal_agentId").val();
	operate = $('#modifyCustomerBaseInfo_loadFlag').val();
	//tradeLoadFlag = $("#modifyCustPersonal_tradeLoadFlag").val();
	initCombobox();
	initHobby();
	modifyCustomerPersonalInfoinvestTable();
	modifyCustomerPersonalInfoFamilyTable();
	modifyCustomerPersonalInfoHouseTable();
	modifyCustomerPersonalInfoCarTable();
	
	if( operate!=null&&operate!=""&&operate!=undefined&&operate=="custDetail" ){
		$("#modifyCustomerPersonalInfo_submitCarInfoButton").hide();
		$("#modifyCustomerPersonalInfoinvestTable_tb").css("display", "none");
		$("#modifyCustomerPersonalInfoFamilyTable_tb").css("display", "none");
		$("#modifyCustomerPersonalInfoHouseTable_tb").css("display", "none");
		$("#modifyCustomerPersonalInfoCarTable_tb").css("display", "none");
	}
	/*if( tradeLoadFlag!=null&&tradeLoadFlag!=""&&tradeLoadFlag!=undefined&&tradeLoadFlag=="tradeLoadFlag" ){
		$("#modifyCustomerPersonalInfo_backButton").hide();
	}*/
	
	//赋值
	var param = {};
	getModifyCustomerPersonalInfo();
})
//初始化下拉框
function initCombobox(){
	//客户可投资预估
	$("#modifyCustomerPersonalInfo_CustAssetEstimate").combobox({
		url:'codeQuery/tdCodeQuery?codeType=custAssetEstimate',
		valueField:'code',    
	    textField:'codeName'
	});
	//客户优质度
	$("#modifyCustomerPersonalInfo_CustQuality").combobox({
		url:'codeQuery/tdCodeQuery?codeType=custQuality',
		valueField:'code',    
	    textField:'codeName'
	});
	//初始化婚姻状况
	$("#modifyCustomerPersonalInfo_Marriage").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=marriage'
	});
	//初始化教育水平
	$("#modifyCustomerPersonalInfo_Degree").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=degree'
	});
	//初始化职业
	$("#modifyCustomerPersonalInfo_OccupationCode").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=occupation'
	});
	//初始化人生阶段
	$("#modifyCustomerPersonalInfo_LifeStage").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=lifeStage'
	});
	//初始化行业
	$("#modifyCustomerPersonalInfo_WorkType").combobox({
		valueField:'code',    
	    textField:'codeName',  
	    url:'codeQuery/tdCodeQuery?codeType=workType'
	});
}
//兴趣爱好
function initHobby(){
	$.ajax({
		type:'post',
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=customerHobby',
		//data:'queryParam='+formData,
		cache:false,
		success:function(returnData){
			try {
				var size = returnData.length;
				var hobbyHtml = '<span class="comboSpan"></span>';
				for(var i=0;i<size;i++){
					var jsonObj = returnData[i];
					var code = jsonObj.code;
					var name = jsonObj.name;
					hobbyHtml += '<input type="checkbox" name="hobbyCode" id="addCust_HobbyCode" value="'+code
						+'"/><span class="commonSpan">'+name+'&nbsp;</span>';
				}
				//hobbyHtml.appendTo($("#hobbyCheckBox"));
				$("#hobbyCheckBox").append(hobbyHtml);
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	
	});  
}
var modifyCustomerPersonalInfoinvestTable;
function modifyCustomerPersonalInfoinvestTable(){
	modifyCustomerPersonalInfoinvestTable = $('#modifyCustomerPersonalInfoinvestTable').datagrid({
		method : 'post',
		nowrap: false,		//单元格是否可以换行
		iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : null,
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		nowrap: false,//单元格是否可以换行
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		columns : [[{
			 field : 'ck',
	         checkbox : true,
	         width : 2
		},
		{
			field : 'visitDate',
			title : '记录时间',
			width : 100,
			//sortable : true,
        	editor: {
        		type:'datetimebox',
    			options:{
					required:true,
					editable:false,
					formatter:function(date){
						if(date>new Date()){
							return new Date().format("yyyy-MM-dd hh:mm:ss");
						}else{
							return new Date(date).format("yyyy-MM-dd hh:mm:ss");
						}
					}
				}	
        	},
        	formatter : function(value, row, index) {
				//return new Date(row.visitDate).format("yyyy-MM-dd hh:mm:ss");
        		//return "2015-12-08 15:00:00";
        		return row.visitDate;
			}
		},
		{
			  field : 'historyInvestProjectValue',
		      title : '历史投资项目',
		      width : 100,
		      sortable : true,
		      editor : {
		        type:'combobox',
		        options:{
		          valueField:'code',
		          textField:'codeName',
		          required:true,
		          multiple : true,
		          editable:false, 
		          url:contextPath+'/codeQuery/tdCodeQuery?codeType=investProject',
		          onSelect:function(){
//		            var historyInvestProjectValue = modifyCustomerPersonalInfoinvestTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoinvestTableRowIndex,field:'historyInvestProject'});
//		            var historyInvestProjectName = $(historyInvestProjectValue.target).combobox('getText');
//		            var historyInvestProjectValues = $(historyInvestProjectValue.target).combobox('getValues');
		            
		        	var historyInvestProjectName = $(this).combobox('getText');
		        	var historyInvestProjectValues = $(this).combobox('getValues');  
		            var historyInvestProjectValue = '';
		            //将factorCode值拼装
		            for(var i=0;i<historyInvestProjectValues.length;i++ ){
		              var value=historyInvestProjectValues[i];
		              if(value!=null&&value!=""&&value!=undefined){
		                historyInvestProjectValue += value+",";
		              }
		            }
		            if(historyInvestProjectName!=null){
		              historyInvestProjectName=historyInvestProjectName.substring(1,historyInvestProjectName.length);
		            }
		            modifyCustomerPersonalInfoinvestTable.datagrid('getRows')[modifyCustomerPersonalInfoinvestTableRowIndex]['historyInvestProjectName'] = historyInvestProjectName;
		            modifyCustomerPersonalInfoinvestTable.datagrid('getRows')[modifyCustomerPersonalInfoinvestTableRowIndex]['historyInvestProject'] = historyInvestProjectValue;  
		          
		          }
		        }
		      },
		            formatter : function(value, row, index) {
		              return row.historyInvestProjectName;
		            }
		},
			{
				field : 'historyInvestProjectName',
				title : '历史投资项目名称',
				sortable : true,
				hidden : true,
				formatter : function(value, row, index) {
					return row.historyInvestProjectName;
				} // 需要formatter一下才能显示正确的数据
			},
		 {
        	field : 'investProjectValue',
        	title : '目前关注的投资项目',
        	width : 100,
        	editor:  {
				type:'combobox',
				options:{
					valueField:'code',
					textField:'codeName',
					required:true,
					multiple : true,
					editable:false, 
					url:contextPath+'/codeQuery/tdCodeQuery?codeType=investProject',
					onSelect:function(){
						//var investProjectValue = modifyCustomerPersonalInfoinvestTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoinvestTableRowIndex,field:'investProject'});
//						var investProjectName = $(investProjectValue.target).combobox('getText');
//						var investProjectValues = $(investProjectValue.target).combobox('getValues');
						var investProjectName = $(this).combobox('getText');
						var investProjectValues = $(this).combobox('getValues');
						
						var investProjectValue = '';
						//将factorCode值拼装
						for(var i=0;i<investProjectValues.length;i++ ){
							var value=investProjectValues[i];
							if(value!=null&&value!=""&&value!=undefined){
								investProjectValue += value+",";
							}
						}
						if(investProjectName!=null){
							investProjectName=investProjectName.substring(1,investProjectName.length);
						}
						
						modifyCustomerPersonalInfoinvestTable.datagrid('getRows')[modifyCustomerPersonalInfoinvestTableRowIndex]['investProjectName'] = investProjectName;
						modifyCustomerPersonalInfoinvestTable.datagrid('getRows')[modifyCustomerPersonalInfoinvestTableRowIndex]['investProject'] = investProjectValue;	
					
					}
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investProjectName;
        	}
	},
		{
			field : 'investProjectName',
			title : '目前关注的投资项目名称',
			sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.investProjectName;
			} // 需要formatter一下才能显示正确的数据
		},
		{
        	field : 'investSuggest',
        	title : '客户投资建议或者期望',
        	width : 150,
        	sortable : true,
        	editor: {
				type:'text',
				options:{
					required:true
				}
			},
        	sortable : true,
        	formatter : function(value, row, index) {
        		return row.investSuggest;
        	}
        },
        {
			field : 'historyInvestProject',
			title : '历史投资项目',
			width : 50,
			sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.historyInvestProject;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'investProject',
			title : '目前关注项目',
			width : 50,
			sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.investProject;
			} // 需要formatter一下才能显示正确的数据
		},
		{
			field : 'custInvestSuggestId',
			title : '客户投资意向和建议Id',
			width : 50,
			sortable : true,
			hidden : true,
			formatter : function(value, row, index) {
				return row.custInvestSuggestId;
			} // 需要formatter一下才能显示正确的数据
		}
		]],
		onLoadSuccess : function() {
			$('#modifyCustomerPersonalInfoinvestTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow : function(index) {
			modifyCustomerPersonalInfoinvestTableEditOneRow(index);
			if(modifyCustomerPersonalInfoinvestTable.datagrid('getRows')[modifyCustomerPersonalInfoinvestTableRowIndex]!=undefined){
				var investProjectValues = modifyCustomerPersonalInfoinvestTable.datagrid('getRows')[modifyCustomerPersonalInfoinvestTableRowIndex]['investProject'];
				var edInvestProjectValue = modifyCustomerPersonalInfoinvestTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoinvestTableRowIndex,field:'investProjectValue'});
				if(investProjectValues!=null&&investProjectValues!=""&&investProjectValues!=undefined){
					var investProjectArray = investProjectValues.split(",");
					$(edInvestProjectValue.target).combobox("setValues",investProjectArray);
				}
				var histiryInvestProjectValues = modifyCustomerPersonalInfoinvestTable.datagrid('getRows')[modifyCustomerPersonalInfoinvestTableRowIndex]['historyInvestProject'];
				var edHistoryInvestProjectValue = modifyCustomerPersonalInfoinvestTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoinvestTableRowIndex,field:'historyInvestProjectValue'});
				if(histiryInvestProjectValues!=null&&histiryInvestProjectValues!=""&&histiryInvestProjectValues!=undefined){
					var investProjectArray = investProjectValues.split(",");
					$(edHistoryInvestProjectValue.target).combobox("setValues",investProjectArray);
				}
			}
		},
		toolbar:"#modifyCustomerPersonalInfoinvestTable_tb"
	})
}

/** ******************************客户投资意向和建议信息增/删/编辑************************************ */

//增加一行
function modifyCustomerPersonalInfoinvestTableAddOneRow(){
	modifyCustomerPersonalInfoinvestTableRowIndex = addOneRow(modifyCustomerPersonalInfoinvestTable,modifyCustomerPersonalInfoinvestTableRowIndex);
}
//删除一行
function modifyCustomerPersonalInfoinvestTableRemoveOneRow(){
	removeOneRow(modifyCustomerPersonalInfoinvestTable,modifyCustomerPersonalInfoinvestTableRowIndex);
	modifyCustomerPersonalInfoinvestTableRowIndex= null;
}
//编辑指定行
function modifyCustomerPersonalInfoinvestTableEditOneRow(index){
	if(editOneRow(modifyCustomerPersonalInfoinvestTable,modifyCustomerPersonalInfoinvestTableRowIndex,index)){
		modifyCustomerPersonalInfoinvestTableRowIndex = index;
	}
}

//锁定编辑行
function modifyCustomerPersonalInfoinvestTableLockOneRow(){
	if(lockOneRow(modifyCustomerPersonalInfoinvestTable,modifyCustomerPersonalInfoinvestTableRowIndex)){
		modifyCustomerPersonalInfoinvestTableRowIndex = undefined;
		return true;
	}else{
		return false;
	}
}
var modifyCustomerPersonalInfoFamilyTable;
function modifyCustomerPersonalInfoFamilyTable(){
	modifyCustomerPersonalInfoFamilyTable = $('#modifyCustomerPersonalInfoFamilyTable').datagrid({
		//	title : '家庭信息', // 标题
			method : 'post',
			//iconCls : 'icon-edit', // 图标
			singleSelect : true, // 多选
			//height : 380, // 高度
			fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped : true, // 奇偶行颜色不同
			collapsible : true,// 可折叠
			//url : "queryList", // 数据来源
			sortName : 'id', // 排序的列
			sortOrder : 'desc', // 倒序
			remoteSort : true, // 服务器端排序
			idField : 'id', // 主键字段
			queryParams : {}, // 查询条件
			//pagination : true, // 显示分页
			rownumbers : true, // 显示行号
			//pageList:[5,10,15,20],
			//pageSize:5,
			columns : [[{
						field : 'ck',
						checkbox : true,
						width : 2
					},
					{
						field : 'memberName',
						title : '家庭成员姓名',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								required:true,
								validType:'length[0,20]'
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.memberName;
						} // 需要formatter一下才能显示正确的数据
					},{
						field : 'relationToCust',
						title : '与客户关系',
						width : 70,
						editor: {
							type:'combobox',
							options:{
								valueField:'code',
								textField:'codeName',
								url:"codeQuery/tdCodeQuery?codeType=relationToCust",
								onHidePanel:function(){
									var ed = modifyCustomerPersonalInfoFamilyTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoFamilyTableEditIndex,field:'relationToCust'});
									var relationToCustName = $(ed.target).combobox('getText');
									modifyCustomerPersonalInfoFamilyTable.datagrid('getRows')[modifyCustomerPersonalInfoFamilyTableEditIndex]['relationToCustName'] = relationToCustName;
								}
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.relationToCustName;
						}
					},{
						field : 'relationToCustName',
						title : '与客户关系名称',
						hidden : true,
						//sortable : true,
						formatter : function(value, row, index) {
							return row.relationToCustName;
						}
					},{
						field : 'age',
						title : '年龄（周岁）',
						width : 70,
						//editor: 'text',
						editor: {
							type:"numberbox",
							options:{
								min:0,
								max:100
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.age;
						}
					},{
						field : 'occupationCode',
						title : '职业',
						width : 70,
						editor: {
							type:'combobox',
							options:{
								valueField:'code',
								textField:'codeName',
								url:"codeQuery/tdCodeQuery?codeType=occupation",
								onHidePanel:function(){
									var ed = modifyCustomerPersonalInfoFamilyTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoFamilyTableEditIndex,field:'occupationCode'});
									var occupationName = $(ed.target).combobox('getText');
									modifyCustomerPersonalInfoFamilyTable.datagrid('getRows')[modifyCustomerPersonalInfoFamilyTableEditIndex]['occupationName'] = occupationName;
								}
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.occupationName;
						}
					},{
						field : 'occupationName',
						title : '职业名称',
						hidden : true,
						//sortable : true,
						formatter : function(value, row, index) {
							return row.occupationName;
						}
					},{
						field : 'annualIncome',
						title : '年收入（万元）',
						width : 70,
						//editor: 'text',
						//sortable : true,
						editor: {
							type:"numberbox",
							options:{
								min:0,
								precision:2
							}
						},
						formatter : function(value, row, index) {
							return row.annualIncome;
						}
					},{
						field : 'mobile',
						title : '手机号',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								validType:"validPhone"
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.mobile;
						}
					},{
						field : 'qq',
						title : 'QQ号',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								validType:"validQQ"
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.qq;
						}
					},{
						field : 'wechat',
						title : '微信号',
						width : 70,
						//editor: 'text',
						editor: {
							type:"validatebox",
							options:{
								validType:"validCode"
							}
						},
						//sortable : true,
						formatter : function(value, row, index) {
							return row.wechat;
						}
					}]],
			onLoadSuccess : function() {
				$('#modifyCustomerPersonalInfoFamilyTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			},
			onClickRow:function(rowIndex){
				modifyCustomerPersonalInfoFamilyTableeditOneRow(rowIndex);
			},
			toolbar:"#modifyCustomerPersonalInfoFamilyTable_tb"
	});
}

/*********************************************************************/
var modifyCustomerPersonalInfoFamilyTableEditIndex;
//增加一行
function modifyCustomerPersonalInfoFamilyTableAddOneRow(){
	
	modifyCustomerPersonalInfoFamilyTableEditIndex = addOneRow(modifyCustomerPersonalInfoFamilyTable,modifyCustomerPersonalInfoFamilyTableEditIndex);
}
//删除一行
function modifyCustomerPersonalInfoFamilyTableRemoveOneRow(){
	removeOneRow(modifyCustomerPersonalInfoFamilyTable,modifyCustomerPersonalInfoFamilyTableEditIndex);
	modifyCustomerPersonalInfoFamilyTableEditIndex = null;
}
//编辑指定行
function modifyCustomerPersonalInfoFamilyTableeditOneRow(index){
	if(editOneRow(modifyCustomerPersonalInfoFamilyTable,modifyCustomerPersonalInfoFamilyTableEditIndex,index)){
		modifyCustomerPersonalInfoFamilyTableEditIndex = index;
	}
}
//锁定编辑行
function modifyCustomerPersonalInfoFamilyTableLockOneRow(){
	if(lockOneRow(modifyCustomerPersonalInfoFamilyTable,modifyCustomerPersonalInfoFamilyTableEditIndex)){
		modifyCustomerPersonalInfoFamilyTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}

}
var modifyCustomerPersonalInfoHouseTable;
function modifyCustomerPersonalInfoHouseTable(){
	modifyCustomerPersonalInfoHouseTable = $('#modifyCustomerPersonalInfoHouseTable').datagrid({
		//title : '联系信息地址', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		//pageList:[5,10,15,20],
		//pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'housePropertyCode',
					title : '房产证编号',
					width : 140,
					editor: {
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,40]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyCode;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'housePropertyValue',
					title : '房产价值',
					width : 140,
					//editor: 'text',
					editor: {
						type:"numberbox",
						options:{
							min:0,
							precision:2
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyValue;
					}
				},{
					field : 'housePropertyState',
					title : '房产状况',
					width : 140,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							url:"codeQuery/tdCodeQuery?codeType=houseType",
							onHidePanel:function(){
								var ed = modifyCustomerPersonalInfoHouseTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoHouseTableEditIndex,field:'housePropertyState'});
								var housePropertyStateName = $(ed.target).combobox('getText');
								modifyCustomerPersonalInfoHouseTable.datagrid('getRows')[modifyCustomerPersonalInfoHouseTableEditIndex]['housePropertyStateName'] = housePropertyStateName;
							}
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyStateName;
					}
				},{
					field : 'housePropertyStateName',
					title : '房产状况名称',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.housePropertyStateName;
					}
				}]],
		onLoadSuccess : function() {
			$('#modifyCustomerPersonalInfoHouseTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			modifyCustomerPersonalInfoHouseTableeditOneRow(rowIndex);
		},
		toolbar:"#modifyCustomerPersonalInfoHouseTable_tb"
	});
}
/*********************************************************************/
var modifyCustomerPersonalInfoHouseTableEditIndex;
//增加一行
function modifyCustomerPersonalInfoHouseTableAddOneRow(){
	modifyCustomerPersonalInfoHouseTableEditIndex = addOneRow(modifyCustomerPersonalInfoHouseTable,modifyCustomerPersonalInfoHouseTableEditIndex);
}
//删除一行
function modifyCustomerPersonalInfoHouseTableRemoveOneRow(){
	removeOneRow(modifyCustomerPersonalInfoHouseTable,modifyCustomerPersonalInfoHouseTableEditIndex);
	modifyCustomerPersonalInfoHouseTableEditIndex = null;
}
//编辑指定行
function modifyCustomerPersonalInfoHouseTableeditOneRow(index){
	if(editOneRow(modifyCustomerPersonalInfoHouseTable,modifyCustomerPersonalInfoHouseTableEditIndex,index)){
		modifyCustomerPersonalInfoHouseTableEditIndex = index;
	}
}
//锁定编辑行
function modifyCustomerPersonalInfoHouseTableLockOneRow(){
	if(lockOneRow(modifyCustomerPersonalInfoHouseTable,modifyCustomerPersonalInfoHouseTableEditIndex)){
		modifyCustomerPersonalInfoHouseTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
}

var modifyCustomerPersonalInfoCarTable;
function modifyCustomerPersonalInfoCarTable(){
	modifyCustomerPersonalInfoCarTable = $('#modifyCustomerPersonalInfoCarTable').datagrid({
		//title : '联系信息地址', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		//pageList:[5,10,15,20],
		//pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				},
				{
					field : 'carCode',
					title : '车辆编号',
					width : 70,
					//editor: 'text',
					editor:{
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,10]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carCode;
					} // 需要formatter一下才能显示正确的数据
				},
/*				{
					field : 'carType',
					title : '车辆种类',
					width : 70,
					editor: {
						type:'combobox',
						options:{
							valueField:'code',
							textField:'codeName',
							//url:"codeQuery/tdCodeQuery?codeType=addressType",
							onHidePanel:function(){
								var ed = modifyCustomerPersonalInfoCarTable.datagrid('getEditor', {index:modifyCustomerPersonalInfoCarTableEditIndex,field:'carType'});
								var carTypeName = $(ed.target).combobox('getText');
								modifyCustomerPersonalInfoCarTable.datagrid('getRows')[modifyCustomerPersonalInfoCarTableEditIndex]['carTypeName'] = carTypeName;
							}
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carTypeName;
					}
				},
				{
					field : 'carTypeName',
					title : '车辆种类名称',
					hidden : true,
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carTypeName;
					}
				},*/
				{
					field : 'carBrand',
					title : '车辆品牌',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							validType:['length[0,10]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carBrand;
					}
				},{
					field : 'engineNo',
					title : '发动机号',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							validType:['length[0,50]']
						}
					},
					sortable : true,
					formatter : function(value, row, index) {
						return row.engineNo;
					} 
				},{
					field : 'carFrameNo',
					title : '车架号',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,50]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.carFrameNo;
					}
				},{
					field : 'licensePlateNo',
					title : '车牌号',
					width : 70,
					editor:{
						type:"validatebox",
						options:{
							required:true,
							validType:['length[0,10]']
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.licensePlateNo;
					}
				},{
					field : 'rePrice',
					title : '车辆重置价（万）',
					width : 70,
					editor: {
						type:"numberbox",
						options:{
							required:true,
							min:0,
							precision:2
						}
					},
					//sortable : true,
					formatter : function(value, row, index) {
						return row.rePrice;
					}
				}]],
		onLoadSuccess : function() {
			$('#modifyCustomerPersonalInfoCarTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onClickRow:function(rowIndex){
			modifyCustomerPersonalInfoCarTableeditOneRow(rowIndex);
		},
		toolbar: '#modifyCustomerPersonalInfoCarTable_tb'
	});
}
/*********************************************************************/
var modifyCustomerPersonalInfoCarTableEditIndex;
//增加一行
function modifyCustomerPersonalInfoCarTableAddOneRow(){
	modifyCustomerPersonalInfoCarTableEditIndex = addOneRow(modifyCustomerPersonalInfoCarTable,modifyCustomerPersonalInfoCarTableEditIndex);
	//modifyCustomerPersonalInfoCarTableBindAddRowEvent();
}
//删除一行
function modifyCustomerPersonalInfoCarTableRemoveOneRow(){
	removeOneRow(modifyCustomerPersonalInfoCarTable,modifyCustomerPersonalInfoCarTableEditIndex);
	modifyCustomerPersonalInfoCarTableEditIndex = null;
}
//编辑指定行
function modifyCustomerPersonalInfoCarTableeditOneRow(index){
	if(editOneRow(modifyCustomerPersonalInfoCarTable,modifyCustomerPersonalInfoCarTableEditIndex,index)){
		modifyCustomerPersonalInfoCarTableEditIndex = index;
	}
	
	//modifyCustomerPersonalInfoCarTableBindAddRowEvent();
}
//锁定编辑行
function modifyCustomerPersonalInfoCarTableLockOneRow(){
	if(lockOneRow(modifyCustomerPersonalInfoCarTable,modifyCustomerPersonalInfoCarTableEditIndex)){
		modifyCustomerPersonalInfoCarTableEditIndex = undefined;
		return true;
	}else{
		return false;
	}
	
}
//给客户个人信息赋值
function getModifyCustomerPersonalInfo(){
	$.ajax({
		type:'post',      
		url:contextPath+"/modifyCustomer/getModifyCustomerPersonalInfoUrl",
		data:{param:modifyCustomerBaseInfo_Id},
		cache:false,
		success:function(result){
			try{
				if(result.success){
					var resultObj = result.obj;
					if (resultObj.custOthInfoMap!=null&&resultObj.custOthInfoMap!=undefined&&resultObj.custOthInfoMap!=""){
						setInputValueById("modifyCustomerPersonalWealthInfoForm",resultObj.custOthInfoMap);
				    }
					if (resultObj.custOthInfoMap!=null&&resultObj.custOthInfoMap!=undefined&&resultObj.custOthInfoMap!=""){
						setInputValueById("modifyCustomerPersonalInfoForm",resultObj.custOthInfoMap);
				    }
					if(resultObj.custInvestmentSuggestList!=null&&resultObj.custInvestmentSuggestList!=undefined&&resultObj.custInvestmentSuggestList!=""){
						loadJsonObjData("modifyCustomerPersonalInfoinvestTable",resultObj.custInvestmentSuggestList);				
					}
					if(resultObj.custFamilyInfoMapList!=null&&resultObj.custFamilyInfoMapList!=undefined&&resultObj.custFamilyInfoMapList!=""){
						loadJsonObjData("modifyCustomerPersonalInfoFamilyTable",resultObj.custFamilyInfoMapList);				
					}
					if(resultObj.custHouseInfoMapList!=null&&resultObj.custHouseInfoMapList!=undefined&&resultObj.custHouseInfoMapList!=""){
						loadJsonObjData("modifyCustomerPersonalInfoHouseTable",resultObj.custHouseInfoMapList);	
					}
					if(resultObj.custCarInfoMapList!=null&&resultObj.custCarInfoMapList!=undefined&&resultObj.custCarInfoMapList!=""){
						loadJsonObjData("modifyCustomerPersonalInfoCarTable",resultObj.custCarInfoMapList);	
					}
					if(resultObj.custHobbyInfoList!=null&&resultObj.custHobbyInfoList!=undefined&&resultObj.custHobbyInfoList!=""){
						setCustHobbyInfo(resultObj.custHobbyInfoList);
					}
				}else{
					$.messager.alert('提示',result.msg);
				}
			}catch(e){
				$.messager.alert('提示', e);
			}
			}
		
		});
}
//获取客户兴趣爱好信息
function setCustHobbyInfo(custHobbyInfoList){
	if(custHobbyInfoList!=null&&custHobbyInfoList!=''&&custHobbyInfoList!=undefined){
		$('input[name="hobbyCode"]').each(function(){ 
			var chkValue = $(this).val();
			for(var i=0;i<custHobbyInfoList.length;i++){
				var hobbyInfo = custHobbyInfoList[i];
				var hobbyCode = hobbyInfo.hobbyCode;
				if(chkValue==hobbyCode){
					$(this).attr("checked",'true');
				}
			}
		});  
	}
}
//提交客户个人信息
function submitModifyCustomerPersonalInfo(){
	var param={};
	//判断添加的信息是否有误
	if(!modifyCustomerPersonalInfoinvestTableLockOneRow()){
		$.messager.alert('提示', "客户拜访信息输入有误！", 'info');
		return;
	}
	if(!modifyCustomerPersonalInfoFamilyTableLockOneRow()){
		$.messager.alert('提示', "客户家庭信息输入有误！", 'info');
		return;
	}
	if(!modifyCustomerPersonalInfoHouseTableLockOneRow()){
		$.messager.alert('提示', "客户房产信息输入有误！", 'info');
		return;
	}
	if(!modifyCustomerPersonalInfoCarTableLockOneRow()){
		$.messager.alert('提示', "客户车辆信息输入有误！", 'info');
		return;
	}
	//获取客户爱好信息
	var hobbyList = [];
	$('input[name="hobbyCode"]:checked').each(function(){ 
		var hobby={};
		hobby.hobbyCode = $(this).val();
		hobbyList.push(hobby);    
	});   
	param.custPersonalInfoinvestTable = $.toJSON($("#modifyCustomerPersonalInfoinvestTable").datagrid("getRows"));
	param.custPersonalInfoFamilyTable = $.toJSON($("#modifyCustomerPersonalInfoFamilyTable").datagrid("getRows"));
	param.custPersonalInfoHouseTable = $.toJSON($("#modifyCustomerPersonalInfoHouseTable").datagrid("getRows"));
	param.custPersonalInfoCarTable = $.toJSON($("#modifyCustomerPersonalInfoCarTable").datagrid("getRows"));
	var customerPersonalWealthInfo = $("#modifyCustomerPersonalWealthInfoForm").serialize();
	param.customerPersonalWealthInfo = formDataToJsonStr(customerPersonalWealthInfo);
	var customerPersonalInfo = $("#modifyCustomerPersonalInfoForm").serialize();
	param.customerPersonalInfo = formDataToJsonStr(customerPersonalInfo);
	param.custHobbyList = hobbyList;
	param.custBaseInfoId = modifyCustomerBaseInfo_Id;
	param.agentId = modifyCustPersonal_agentId;
	var data = $.toJSON(param);
	data = escape(encodeURIComponent(data));
	$.ajax({
		type : 'post',
		url : contextPath + "/modifyCustomer/submitModifyCustomerPersonalInfoUrl",
		data : 'param='+data,
		cache : false,
		success : function(resultInfo){
			try {
				if (resultInfo.success) {
					$.messager.alert('提示', resultInfo.msg,"info");
				} else {
					$.messager.alert('提示', resultInfo.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}
/*******************点击"返回"按钮触发事件函数************************************/
function modifyCustomerPersonalInfo_backButton(){
	$('#addcustomerWindow').window('destroy');
	//parent.clearPreCustomerBasicInfoForm();
	clearForm();
	queryCustomerList();
}

