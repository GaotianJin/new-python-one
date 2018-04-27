/**
 * 使用说明
 * 引入该js后,页面中加入如下标签即可运行:
 * <span id="currentdate"></span>
 * @authority ms
 */
$(function(){
	setInterval(setTime,200);//时间刷新频率 200毫秒
});

function setTime(){
		console.log("设置时间 => "+getNowFormatDate());
		$('#currentdate').text(getNowFormatDate());
}

/**
 * 获取当前的日期时间
 * 格式“yyyy-MM-dd HH:MM:SS”
 * @return “yyyy-MM-dd HH:MM:SS” 
 */
function getNowFormatDate() {
    var currdate = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var year = currdate.getFullYear();
    var month =currdate.getMonth() + 1;
    var date = currdate.getDate();
    var hour =currdate.getHours();
    var minute = currdate.getMinutes();
    var second = currdate.getSeconds();
    var milli = currdate.getMilliseconds();
    var currentdate = year + seperator1 + fmt(month) + seperator1 + fmt(date)
            + " " + fmt(hour) + seperator2 + fmt(minute)+ seperator2 + fmt(second);
            //+ " " +fmtmilli(milli);
    return currentdate;
}
/**
 * 日期格式化为标准字符
 * @param {Object} number
 * @return {TypeName} 
 */
function fmt(number){
		if (number >= 1 && number <= 9) {
        	return "0" + number;
    	}else{
    		return number
    	}
}

function fmtmilli(number){
		if (number >= 1 && number <= 9) {
        	return "00" + number;
    	}else if(number >= 10 && number <= 99) {
    		return "0" + number;
    	}else{
    		return number;
    	}
}