function StushowPies(){
  		$.post("/EighthCRMItem/RoleStuPic.eighth",function(ress){
  			showPie(ress.str,ress.list);	
  		});
  	}
  	
  	function showPie(data,data1){
  		var myChart = echarts.init(document.getElementById("echart"));
	option = {
    title : {
        text: '咨询师所属学生数量比重图',
        subtext: '实时数据',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: data
    },
    series : [
        {
            name: '咨询师：学生数量',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:data1,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
	};
	myChart.setOption(option);
  	}