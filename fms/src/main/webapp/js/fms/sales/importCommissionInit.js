var yearData =[{code: '2015',codeName: '2015'},{code: '2016',codeName: '2016'},{code: '2017',codeName: '2017'},
		       {code: '2018',codeName: '2018'},{code: '2019',codeName: '2019'},{code: '2020',codeName: '2020'},
		       {code: '2021',codeName: '2021'},{code: '2022',codeName: '2022'},{code: '2023',codeName: '2023'},
		       {code: '2024',codeName: '2024'},{code: '2025',codeName: '2025'},{code: '2026',codeName: '2026'},
		       {code: '2027',codeName: '2027'},{code: '2028',codeName: '2028'},{code: '2029',codeName: '2029'},
		       {code: '2030',codeName: '2030'}
		      ];
var monthData =[{code: '01',codeName: '01'},{code: '02',codeName: '02'},{code: '03',codeName: '03'},
		       {code: '04',codeName: '04'},{code: '05',codeName: '05'},{code: '06',codeName: '06'},
		       {code: '07',codeName: '07'},{code: '08',codeName: '08'},{code: '09',codeName: '09'},
		       {code: '10',codeName: '10'},{code: '11',codeName: '11'},{code: '12',codeName: '12'}
		      ];
/**
 * 初始化列表
 */
jQuery(function($){
	initCombobox();
});

/**
 * 下拉框
 */
function initCombobox(){
	//文件类型
	$("#commission_salaryFileType").combobox({
		url:contextPath+'/codeQuery/tdCodeQuery?codeType=commissionType',
		valueField:'code',
		textField:'codeName'
	});	
	$("#commission_salaryFileYear").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: yearData
	});
	$("#commission_salaryFileMonth").combobox({
		valueField:'code',
		textField:'codeName',
		width:100,
		data: monthData
	});
}

/**
 * 批量导入
 */
function importCommissionFile(){
	//校验必填项
	if (!$("#commissionImportForm").form("validate")) {
		return false;
	}
	var commissionFileType = $("#commission_salaryFileType").combobox("getValue");
	var salaryFileName = $("#commission_salaryFileName").val();
	var commissionFileYear = $("#commission_salaryFileYear").combobox("getValue");
	var commissionFileMonth = $("#commission_salaryFileMonth").combobox("getValue");
	var commissionFileDate = commissionFileYear+'-'+commissionFileMonth;
	var param = {};
	param.salaryFileType =commissionFileType;
	param.salaryFileDate = commissionFileDate;
	if(salaryFileName==null||salaryFileName==""||salaryFileName==undefined){
		$.messager.alert('提示', "请选择需要导入的文件", 'info');
		return;
	};
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/sales/importSalaryFile?param="+$.toJSON(param),
		fileElementId:'commission_salaryFileName',
		dataType:'json',
		success:function(reData){
			reData = $.parseJSON(reData);
			try {
				if(reData.success){
					$.messager.alert('提示', "薪资文件导入成功！");
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

/**
 * 返回主页面
 */
function backPage(){
	$('#addCommissionWindow').window('close');
	initCommissionTable();
}
