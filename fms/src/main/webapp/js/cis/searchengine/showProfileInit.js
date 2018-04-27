jQuery(function($) {
            //var id = getParamter('id');
			//$.post('queryProfile?id='+id, function(data) {
			//	$.messager.alert('提示', data, 'info');
			//});
		});
		
var PARAMTER_VALUE = null;    
function getParamter(paramName) {    
    if(!PARAMTER_VALUE) {   //第一次初始化    
        PARAMTER_VALUE = new Array();    
        var paramStr = location.search.substring(1);    
        var paramArr = paramStr.split("&");    
        var len = paramArr.length;    
        var tempArr;    
        for(var i = 0; i < len; i++) {    
            tempArr = paramArr[i].split("=");    
            PARAMTER_VALUE[tempArr[0]] = tempArr[1];    
        }    
    }    
    var paramValue = PARAMTER_VALUE[paramName];    
    if(paramValue) {    
        return paramValue;    
    }    
} 