jQuery(function($) {
	initTradeDownloadTable();
});

var tradeDownloadTable ;
function initTradeDownloadTable(){
	var role = $('#tradeInputListId').datagrid('getSelections');
	
	var param = {};
	param.tradeNo = role[0].tradeNo;
	param.doucmentType = '01';
	param = $.toJSON(param);
	
	
	tradeDownloadTable = $('#tradeDownloadTable').datagrid({
		//title : '认购书列表', // 标题
		method : 'post',
		singleSelect : true, // 多选
		fitColumns : false, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped : true, // 奇偶行颜色不同
		collapsible : true,// 可折叠
		url : contextPath+"/trade/printNum?param="+param, // 数据来源
		sortName : 'id', // 排序的列
		sortOrder : 'desc', // 倒序
		remoteSort : true, // 服务器端排序
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
					field : 'businessType',
					title : '交易类型',
					width : 120,
					formatter : function(value, row, index) {
						return row.businessType;
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'fileSavePath',
					title : '交易路径',
					width : 130,
					hidden:true,
					formatter : function(value, row, index) {
						return row.fileSavePath;
					} // 需要formatter一下才能显示正确的数据
				},{
					field : 'printFileName',
					title : '认购书名称',
					width : 280,
					formatter : function(value, row, index) {
						var param = {};
						param.printInfoId = row.printInfoId;
						param = $.toJSON(param);
						return "<a href='#'  onclick=printDownload('"+param+"')>"+row.printFileName+"</a>";
					} // 需要formatter一下才能显示正确的数据
				},
				{
					field : 'printTime',
					title : '认购书打印时间',
					width : 150,
					formatter : function(value, row, index) {
						return row.printTime;
					} 
				}]]
	});
}


function printDownload(param) { 
	$.messager.confirm('确认','您确认下载此文件吗？',function(r){    
	    if (r){    
	    	var form = $("<form>");   //定义一个form表单
	    	form.attr('style', 'display:none');   //在form表单中添加查询参数
	    	form.attr('target', '');
	    	form.attr('method', 'post');
	    	form.attr('action', contextPath+"/trade/downloadPrint?param="+param);
	    	$('body').append(form);  //将表单放置在web中 
	    	form.submit(); 
	    }
	});
}
