var roleId = null;

/**
 * 初始化公司政策列表
 */
jQuery(function($) {
	initListCompanyNewsTable();
	roleId = $("#comNews_roleId").val();
	comNewsRoleControl();
})

/**
 * 权限控制
 */
function comNewsRoleControl(){
	try{
		if(roleId !=null){
			if(roleId!='1'){
				$("#comNews_linkbutton").hide();
			}
		}else{
			$.messager.alert('提示', '获取角色信息失败');
		}
	}catch (e) {
		$.messager.alert('提示', e);
	}
}


/**
 * 公司要闻列表
 */
function initListCompanyNewsTable(){
	var  selectIndex = -1;
	customerTable = $('#listCompanyNewsTable').datagrid({
		title : '公司要闻列表', // 标题
		method : 'post',
		//iconCls : 'icon-edit', // 图标
		singleSelect : true, // 多选
		//height : 380, // 高度
		fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/index/queryCompanyNewsList",
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
		idField : 'id', // 主键字段
		queryParams : {"queryParam":{}}, // 查询条件
		pagination : true, // 显示分页
		rownumbers : true, // 显示行号
		pageList:[5,10,15,20],
		pageSize:10,
		columns : [[{
					field : 'ck',
					checkbox : true,
					width : 2
				}, // 显示复选框
				{
					field : 'companyNewsInfoId',
					title : 'id',
					hidden : true,
					width : 1000,
				    formatter : function(value, row, index) {
					return row.companyNewsInfoId;
				    }
				},{
					field : 'title',
					title : '标题',
					//hidden : true,
					width : 1000,
				    formatter : function(value, row, index) {
					var param = {};
					param.companyNewsInfoId = row.companyNewsInfoId;
					return "<a href = '#' onclick=companyNewsInfotab('公司要闻详情','"
					+contextPath+"/index/companyNewsDetailUrl?param="+$.toJSON(param)+"')>"+row.title+"</a>";
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
			$('#listCompanyNewsTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
function uploadComNewsInfo(){
	uploadCompanyNewsFile('上传文件', contextPath + "/index/editorCompanyNewsUrl");
}

function uploadCompanyNewsFile(){
	$('<div id="uploadCompanyNewsDialog"/>')
	.dialog(
			{
				href : contextPath + "/index/addComNewsFileUrl",
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
function companyNewsInfotab(title, href) {
	$('<div id="addCompanyNewsWindow"/>').dialog({
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
