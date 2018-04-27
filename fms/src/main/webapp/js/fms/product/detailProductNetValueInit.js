var productId = $("#ProductId").val();
jQuery(function($) {
	
	getProductNetValue();
	
	
	
});
//初始需要修改的数据
function getProductNetValue(){
	$.ajax({
		type:'post',
		url:contextPath+"/product/getProductNetValue",
		data:{param:productId},
		cache:false,
		success:function(result){
		
				if(result.success){
					var resultObj = result.obj;
					console.info(resultObj);
					//初始化走势图表
					initNetVauleChart(resultObj);
					//初始化table
					initNetValueDetailId();
				}
		}
	});
}


var netValueDetailId;
function initNetValueDetailId() {
	var  selectIndex = -1;
	netValueDetailId = $("#netValueDetailId")
			.datagrid(
					{
						title : '净值信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/searchNetValueUrl", // 数据来源
						sortName : 'id', // 排序的列
						sortOrder : 'desc', // 倒序
						remoteSort : true, // 服务器端排序
						idField : 'id', // 主键字段
						queryParams : {param:productId}, // 查询条件
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageSize : 5,
						pageList : [ 5, 10, 15, 20, 25, 30 ],
						columns : [ [  {
							field : 'productId',
							title : '产品Id',
							width : 150,
							hidden : true,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productId;
							}
						}, {
							field : 'productName',
							title : '产品名称',
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								return row.productName;
							}
						}, {
							field : 'netValue',
							title : '产品净值',
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								return row.netValue;
							}
						}, {
							field : 'publicDate',
							title : '公布日期',
							width : 180,
							sortable : true,
							formatter : function(value, row, index) {
								return row.publicDate;
							}
						}]],
						toolbar : [
									{
										text : '导出',
										iconCls : 'icon-print',
										handler : function() {
											var param = {};
											param.productId = productId;
											param = $.toJSON(param);
											window.open(contextPath+'/product/floatProductNetValue.xls?queryParam='+param);
										}
									}],
						onLoadSuccess : function() {
							$('#netValueDetailId').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
						},
						onClickRow: function (rowIndex, rowData) {
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
function initNetVauleChart(resultObj){
	$('#netValueInfo_DetailFormDiv').highcharts({ 
			title: { text: resultObj.productName, x: -20  }, 
		 subtitle: { text: '近三个月净值走势图', x: -20 }, 
		 credits:{ enabled : false},
		 xAxis: { categories:  resultObj.publicDateList},
		/* yAxis: { title: { text: '净值-netValue' },*/ 
		 yAxis: { title: { text: '净值' },
		 plotLines: [{ value: 0, width: 1, color: '#808080' }] }, 
		 //exporting :{enabled : true,filename : "netValueChart"},
		 /*tooltip: { valueSuffix: '°C' }, */
		 legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 }, 
		 /*series: [{ name: 'netValue', data: resultObj.netValueList}]*/
		 series: [{ name: '净值', data: resultObj.netValueList}]
			 }); 
	}
