var roleId = null;

/**
 * 初始化行业新闻列表
 */
jQuery(function($) {
	initListProfessionNewsTable();
	roleId = $("#proNews_roleId").val();
	proNewsRoleControl();
})

/**
 * 权限控制
 */
function proNewsRoleControl(){
	try{
		if(roleId !=null){
			if(roleId!='1'){
				$("#proNews_linkbutton").hide();
			}
		}else{
			$.messager.alert('提示', '获取角色信息失败');
		}
	}catch (e) {
		$.messager.alert('提示', e);
	}
}

/**
 * 产品报告列表
 */
function initListProfessionNewsTable(){
	var  selectIndex = -1;
	    $('#listProfessionNewsTable').datagrid({
		title : '产品报告列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/index/queryProfessionNewsList",
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {"queryParam":{}}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[
				{
					field : 'professionNewsInfoId',
					title : 'id',
					hidden : true,
					width : 1000,
				    formatter : function(value, row, index) {
					return row.professionNewsInfoId;
				    }
				},{
					field : 'title',
					title : '标题',
					//hidden : true,
					width : 1000,
				    formatter : function(value, row, index) {
					var param = {};
					param.professionNewsInfoId = row.professionNewsInfoId;
					return "<a href = '#' onclick=detailNewstab('产品报告详情','"
					+contextPath+"/index/listProfessionNewsDetailUrl?param="+$.toJSON(param)+"')>"+row.title+"</a>";
				    }
				},{
					field : 'modifyDate',
					title : '上传时间',
					width : 1000,
					height : 100,
				    formatter : function(value, row, index) {
					return row.modifyDate;
				    }
				}]],
		onLoadSuccess : function() {
			$('#listProfessionNewsTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
		,onClickRow: function (rowIndex, rowData) {
            if(selectIndex==rowIndex){
            	//第一次单击选中,第二次单击取消选中
            	$(this).datagrid('unselectRow', rowIndex);
            	selectIndex=-1;
            }else{
            	selectIndex = rowIndex;
            }
		}
	});
	
}

/**
 * 上传文件
 */
function professionUploadFile(){
	$('<div id="professionUploadFileDialog"/>')
	.dialog(
			{
				href : contextPath + "/index/proNewsUploadFileUrl",
				modal : true,
				fit : true,
				title : '文件上传',
				inline : false,
				minimizable : false,
				onClose : function() {
					$(this).dialog('destroy');
				}
			});
}

/**
 * 行业新闻详情跳转窗口
 * @param title
 * @param href
 */
function detailNewstab(title, href) {
	$('<div id="addNewsWindow"/>').window({
		href : href,
		modal : true,
		title : title,
		fit : true,
		inline : false,
		minimizable : false,
		onClose : function() {
			$(this).window('destroy');
		}
	});
}
