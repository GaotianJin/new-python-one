jQuery(function($) {
			$("#templatetriggerdiv").show();
			$("#simpletriggerdiv").hide();
			$("#crontriggerdiv").hide();
			$('#trigger_type').combobox({
						onSelect : function(rec) {
							if (rec.value == "simpletrigger") {
								$("#simpletriggerdiv").show();
								$("#crontriggerdiv").hide();
								$("#templatetriggerdiv").hide();
							} else if (rec.value == "crontrigger") {
								$("#crontriggerdiv").show();
								$("#simpletriggerdiv").hide();
								$("#templatetriggerdiv").hide();
							} else if (rec.value == "templatetrigger") {
								$("#templatetriggerdiv").show();
								$("#simpletriggerdiv").hide();
								$("#crontriggerdiv").hide();
							}
						},
						editable : false
					});

			$('#template_type').combobox({
						onSelect : function(rec) {
							if (rec.value == "template1") {
								$("#template1div").show();
							} else {
								$("#template1div").hide();
							}
						},
						editable : false
					});
			
			
			
			$('#job_name').combobox({
						required : true,
						url : contextPath + "/quartz/queryJob", // 数据来源
						valueField : 'job_name',
						textField : 'job_name',
						editable : false
					});
			
			$('#executeTime').timespinner({
						required : true,
						showSeconds : true,
						editable : false
					});

			$('#startTime').datetimebox({
						required : true,
						showSeconds : true,
						editable : false
					});
			$('#endTime').datetimebox({
						required : false,
						showSeconds : true,
						editable : false
					});
			$('#repeatCount').numberspinner({
						min : -1,
						editable : true
					});
		});

// 增加调度
function addScheduler() {
	var dlist = [];
	dlist.push({
				"trigger_type" : $("#trigger_type").combobox('getValue'),
				"job_name" : $("#job_name").combobox('getValue'),
				"cron_expression" : $("#cron_expression").val(),
				"startTime" : $("#startTime").datetimebox('getValue'),
				"endTime" : $("#endTime").datetimebox('getValue'),
				"repeatCount" : $("#repeatCount").val(),
				"repeatInterval" : $("#repeatInterval").val(),
				"executeTime" : $("#executeTime").timespinner('getValue'),
				"template_type" : $("#template_type").combobox('getValue'),
				"unit" : $("#unit").combobox('getValue')
			});
	$.post(contextPath + "/quartz/saveAdd?list=" + $.toJSON(dlist), function(data) {
				$.messager.alert('提示', data.mes, 'info');
			});
}