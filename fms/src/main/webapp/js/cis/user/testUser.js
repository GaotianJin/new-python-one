var editRowIndex;
$(function() {
	$('#userTable').datagrid({
		title : '用户列表', // 标题
		//method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : auto, // 高度
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		//url : "queryList", // 数据来源
		sortName : 'id', // 排序的列
		//sortOrder : 'desc', // 倒序
		//remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		//queryParams : {}, // 查询条件
		//pagination : true, // 显示分页
		//rownumbers : true, // 显示行号
		
		
		//fit : true,
		//fitColumns : true,
	border : false,
		//rownumbers : true,
		
		onDblClickRow: function(rowIndex){
			onClickGridRow(rowIndex);
		},
		
		//pageList:[5,10,15,20],
		//pageSize:5,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'row.username',
					title : '名字',
					width : 100,
					editor:'text',
					sortable : true,
					formatter : function(value, row, index) {
						return row.username;
					} // 需要formatter一下才能显示正确的数据
				}, {
					field : 'row.usercode',
					title : '编码',
					width : 100,
					editor:'text',
					sortable : true,
					formatter : function(value, row, index) {
						return row.usercode;
					}
				},{
					field : 'row.companyName',
					title : '管理机构',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						return row.companyName;
					}
				},{
					field : 'row.companyId',
					title : '管理机构ID',
					width : 100,
					sortable : true,
					hidden :true,
					formatter : function(value, row, index) {
						return row.companyId;
					}
				},{
					field : 'action',
					title : '操作',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, index) {
							alert("wwwwwwwwwwwww:==="+index);
							/*return formatString('<img onclick="onClickGridRow('+index+');" src="${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png"/>&nbsp;&nbsp;'
											   +'<img onclick="removeOneRow('+index+');" src="${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png"/>&nbsp;&nbsp;'
											   +'<img onclick="lockOneRow();" src="${pageContext.request.contextPath}/style/images/extjs_icons/lock/lock_edit.png"/>');
							*/
							//return $.formatString('<img onclick="lockOneRow();" src="/js/img/extjs_icons/lock/lock_edit.png"/>');
							return eval('<img onclick="lockOneRow();" src="../js/img/extjs_icons/lock/lock_edit.png"/>');
					}
				}
				
				
				]]//,
		//toolbar: '#tb'//,
		
		//onLoadSuccess : function() {
		//	$('#userTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		//}
	});	
});	



	//datagrid增加一行数据
	function addOneRow(){
		if(endEditing()){
			$('#userTable').datagrid('appendRow',{status:'P'});
			editRowIndex = $('#userTable').datagrid('getRows').length-1;
			$('#userTable').datagrid('selectRow', editRowIndex).datagrid('beginEdit', editRowIndex);	
		}
	}


	//核保机构删除一行数据
	function removeOneRow(index){
		if (index == undefined){
			return;
		}
		$('#LDUWManageComGrid').datagrid('cancelEdit', index).datagrid('deleteRow', index);
	}


	//结束编辑
	function endEditing(){
		/*if (editIndex_mngComGrid == undefined){
			return true;
		}
		if ($('#LDUWManageComGrid').datagrid('validateRow', editIndex_mngComGrid)){
			var ed = $('#LDUWManageComGrid').datagrid('getEditor', {index:editIndex_mngComGrid,field:'UWManageCom'});
			var value = $(ed.target).combobox('getValue');
			var text = $(ed.target).combobox('getText');
			var managecomname = text.substring(text.indexOf('-')+1);
			$(ed.target).combobox('setText',value);
			var ed1 = $('#LDUWManageComGrid').datagrid('getEditor', {index:editIndex_mngComGrid,field:'UWManageComName'}); 
			$(ed1.target).val(managecomname);
			$('#LDUWManageComGrid').datagrid('acceptChanges');
			$('#LDUWManageComGrid').datagrid('endEdit', editIndex_mngComGrid);
			editIndex_mngComGrid = undefined;
			return true;
		} 
		else {
			return false;
		}*/
		return true;
	}

	//当双击datagrid的行时启动此行的编辑功能
	function onClickGridRow(index){
		if (editIndex_mngComGrid != index){
			if (endEditing()){
				$('#LDUWManageComGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
				editIndex_mngComGrid = index;//showPanel
				
			} else {
				$('#LDUWManageComGrid').datagrid('selectRow', editIndex_mngComGrid);
			}
		}
	}


	//锁定编辑
	function lockOneRow(){
		if (endEditing()){
			$('#LDUWManageComGrid').datagrid('acceptChanges');
		}
	}

	/*function r_click_Input(rowIndex){
		removeRowIndex = rowIndex;
	}*/

	//查询返回时调用此方法
	function afterQuery(selectedRowData){
		//console.info("-------------------------");
		//console.info(selectedRowData);
		$('#queryWindow').window('close');
		var UWType = selectedRowData.uwtype;
		var UserCode = selectedRowData.usercode;
		if(UWType="个险"){
			UWType = "1";
		}
		else{
			UWType = "2";
		}
		queryLdUWUserInfo(UWType,UserCode);
		queyUWManageComInfo(UserCode);
		
		return;
	}


	//查询核保员信息
	function queryLdUWUserInfo(UWType,UserCode){
		//console.info(UWType);
		//console.info(UserCode);
		var UWPopedom = null;
		$.ajax({
			type : 'post',
			url : '${pageContext.request.contextPath}/oLDUWUserController/queryLdUWUserByPK.action',
			data : {'uwtype':UWType,'usercode':UserCode},
			cache : false,
			success : function(result){
				result = $.parseJSON(result);
				//console.info(result);
				//console.info(result.uwpopedom);
				UWPopedom = result.uwpopedom;
				queryMaxAmntInfo(UWType,UWPopedom);
				$('#ldUWUser').form('load',result);
			}
		});
		
	}

	//查询核保机构信息
	function queyUWManageComInfo(UserCode){
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/oLDUWUserController/queryUWManageComInfo.action',
			data:{'usercode':UserCode},
			cache:false,
			success:function(reData){
				//console.info('queyUWManageComInfo=====');
				//console.info(reData);
				var result = $.parseJSON(reData);
				//console.info(result);
				$('#LDUWManageComGrid').datagrid('loadData',result);
			}
		});
	}


	//清空数据
	function clearFormData(){
		$('#ldUWUser').form('clear');
		//initUWMaxAmountGrid();
		$('#UWMaxAmountGrid').datagrid('loadData',{total:0,rows:[]});
		//initLDUWManageComGrid();
		$('#LDUWManageComGrid').datagrid('loadData',{total:0,rows:[]});
	}
	