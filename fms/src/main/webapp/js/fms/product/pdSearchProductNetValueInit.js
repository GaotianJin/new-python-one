var productId = $("#pdProductId").val();
var productSubType = $("#pdTypeCode").val();
var type = '01';

jQuery(function($) {
	initAllCombox();
	//产品查询时显示该产品的所有净值走势情况
	getPdSearchNetValue(type);

});

//初始化下拉框
function initAllCombox(){
	var chooseCategory ;
	chooseCategory = $("#chooseCategory").combobox({
		valueField : 'label',
		textField : 'value',
		data: [{
			label: '01',
			value: '最近一个月'
		},{
			label: '02',
			value: '最近半年'
		},{
			label: '03',
			value: '最近一年'
		},{
			label: '04',
			value: '成立以来'
		}],
		onSelect : function(){
			type = chooseCategory.combobox("getValue");
			getPdSearchNetValue(type);
		},
		onLoadSuccess : function(){
			$("#chooseCategory").combobox('setValue', '01');
		}
	});
}


//产品查询的时候显示净值走势图，显示全部
function getPdSearchNetValue(type){
	var param = {};
	param.productId = productId;
	param.productSubType = productSubType;
	param.type = type;
	$.ajax({
		type:'post',
		url:contextPath + "/product/getPdSearchNetValueList",
		data:{param:$.toJSON(param)},
		cache:false,
		success:function(result){
			if(result.success){
				var resultObj = result.obj;
				//初始化走势图表
				initPdSearchNetVauleChart(resultObj);
				//初始化table
				initNetValueDetailId();
			}
		}
	})
}



var pdNetValueDetailId;
function initNetValueDetailId() {
	var param = {};
	param.productId = productId;
	param.productSubType = productSubType;
	var  selectIndex = -1;
	pdNetValueDetailId = $("#pdnetValueDetailId")
			.datagrid(
					{
						title : '净值信息列表', // 标题
						method : 'post',
						singleSelect : true, // 多选
						fitColumns : true, // 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped : true, // 奇偶行颜色不同
						collapsible : true,// 可折叠
						url : contextPath + "/product/pdsearchNetValueUrl?param="+$.toJSON(param), // 数据来源
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
							$('#pdnetValueDetailId').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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



function initPdSearchNetVauleChart(resultObj){
	$('#pdnetValueInfo_DetailFormDiv').highcharts({ 
		 title: { text: resultObj.productName, x: -20  }, 
		 subtitle: { text: '净值走势图', x: -20 }, 
		 credits:{ enabled : false},
		 xAxis: { categories:  resultObj.publicDateList},
		/* yAxis: { title: { text: '净值-netValue' },*/ 
		 yAxis: { title: { text: '净值' }, 
		 plotLines: [{ value: 0, width: 1, color: '#808080' }] }, 
		 //exporting :{enabled : true,filename : "netValueChart"},
		 /*tooltip: { valueSuffix: '°C' }, */
		 legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 }, 
		/* series: [{ name: 'netValue', data: resultObj.netValueList}],*/
		 series: [{ name: '净值', data: resultObj.netValueList}],
	}); 
	}