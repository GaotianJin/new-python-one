function showStuAddress(){
	$.post("/EighthCRMItem/StuAddress.eighth",function(res){
		showProvince(res);
	});
}

function showProvince(data) {
	var myChart = echarts.init(document.getElementById("echart1"));
	var uploadedDataURL = "/EighthCRMItem/data/data-1483664927969-r1dX0P2Sl.json";
    var name = 'hn';

    // myChart.showLoading();

    $.get(uploadedDataURL, function(geoJson) {

        // myChart.hideLoading();

        echarts.registerMap(name, geoJson);

        myChart.setOption(option = {
           
            title: {
                text: "河南省各地市学生数量分布图",
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            visualMap: {
                min: 0,
                max: 100,
                left: 'left',
                top: 'bottom',
                text: ['多', '少'], // 文本，默认为数值文本
                calculable: true
            },
             toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                        }
            },
            series: [{
                type: 'map',
                mapType: name,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        textStyle: {
                            color: '#fff'
                        }
                    }
                },
                itemStyle: {

                    normal: {
                        borderColor: '#389BB7',
                        areaColor: '#fff',
                    },
                    emphasis: {
                        areaColor: 'blue',
                        borderWidth: 0
                    }
                },
                animation: false,
                
            data:data
                    
            }],
            
        });
    });
}