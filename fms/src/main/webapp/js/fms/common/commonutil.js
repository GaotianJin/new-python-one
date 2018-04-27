//多个table，修改列宽度百分比
$(function(){
	$('.input_table td:nth-child(odd)').css("width","10%");  
	$('.input_table td:nth-child(even)').css("width","23%");
});

function formatString(str){
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
}

/**将获取到的form数据转换成json对象,
 * @author 袁正军
 * 这个方法有些局限性，对于存在特殊字符的转不了，如回车
 * 通过$('#formId').serialize();获取到form中所有的数据，格式是字符串,如下:
 * usercode=001&uwtype=1&uwpopedom=C&upusercode=001&edorpopedom=1
 * &claimpopedom=1&remark=&addpoint=&popuwflag=Y&userstate=0
 */
function formDataToJsonObj(formData){
	try{
		formData = decodeURI(formData);
		formData = dealSpecialCharacter(formData);
		//替换&符号的正则表达式
		var reg_1 = new RegExp("&","g");
		//替换=的正则表达式
		var reg_2 = new RegExp("=","g");
		//第一步，先替换&符号
		formData = formData.replace(reg_1,"','");
		//第二步，先替换=符号
		formData = formData.replace(reg_2,"':'");
		//第三步，拼接成json字符串
		formData = "{'"+formData+"'}";
		//console.info("qqq:==="+formData);
		formData = eval("("+formData+")");
	}catch(e){
		alert("将Form数据转换成json对象出现异常！");
	}
	//return eval("("+formData+")");
	return formData;
}

function dealSpecialCharacter(jsonDataStr){
	//处理数据中的换行符
	jsonDataStr = jsonDataStr.replace("\r\n", "\\r\\n");
	//处理数据中的单引号和双引号
	jsonDataStr = jsonDataStr.replace(new RegExp('(["\"])','g'),"\\\"");
	return jsonDataStr;
}

/**将获取到的form数据转换成json字符串,去除空值
 * @author 袁正军
 * 
 * 通过$('#formId').serialize();获取到form中所有的数据，格式是字符串,如下:
 * usercode=001&uwtype=1&uwpopedom=C&upusercode=001&edorpopedom=1
 * &claimpopedom=1&remark=&addpoint=&popuwflag=Y&userstate=0
 */
function  formDataToJsonStr(formData){
	try{
		/*//替换&符号的正则表达式
		var reg_1 = new RegExp("&","g");
		//替换=的正则表达式
		var reg_2 = new RegExp("=","g");
		//第一步，先替换&符号
		formData = formData.replace(reg_1,"','");
		//第二步，先替换=符号
		formData = formData.replace(reg_2,"':'");
		//第三步，拼接成json字符串
		formData = "{'"+formData+"'}";*/
		var dataArray = new Array();
		//先对数据进行解码
		formData = decodeURI(formData);
		formData = dealSpecialCharacter(formData);
		dataArray = formData.split("&");
		var reg=/^((((19|20)\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\d|30))|(((19|20)\d{2})-(0?[13578]|1[02])-31)|(((19|20)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$/; 
        //return reg.test(value); 
		var jsonStr = "{";
		for(var i=0;i<dataArray.length;i++){
			var attrAndValue = dataArray[i];
			var index = attrAndValue.indexOf("=");
			var key = attrAndValue.substring(0,index);
			//console.info("f_key"+key);
			var value = $.trim(attrAndValue.substring(index+1));
			if(value!=null&&value!=""&&value!=undefined){//
				/*if(reg.test(value)){
					value = formatDateStr(value);
				}*/
				//jsonStr += "'"+key+"':'"+value+"',"
				jsonStr += '"'+key+'":"'+value+'",'
			}
		}
		if(jsonStr.length>1){
			jsonStr = jsonStr.substring(0,jsonStr.length-1);
		}
		jsonStr += "}";
	}catch(e){
		alert("将Form数据转换成json格式出现异常！");
	}
	return jsonStr;
}

/**
 * @author 袁正军
 * 给指定DIV中的所有input标签赋值，divId：指定的DIV标签，jsonObj是后台查询返回到前台的json对象（不是json字符串），
 * 对于combobox，id必须以Combo结尾，如：currencyCombo,对于datebox，id必须以Datebox结尾，如tradeStartDatebox
 * 对combobox和datebox赋值时，必须先初始化完成后才能赋值，对于datebox的初始化，不能只在页面中对标签引入calss="easyui-datebox",
 * 需要在初始化的js中对其进行初始化，如：$('dateboxId').datebox();
 * jsonObj数据格式如下：
 * "{tradeNo:'LC201502050002',tradeInfoNo:'LC201502050002',tradeType:'02'," 
 *			"currencyCombo:'CNY',tradeStartDatebox:'2015-02-05',
 *			tradeInputListId:[{tradeDate:'2015-02-05',tradeType:'01',tradeInfoNo:'LC201502050001',
 *								tradeNo:'LC201502050001',tradeStoreId:1,tradeComId:1,agentId:1,companyId:1,R_ROWNUM:1,
 *								storeId:1,currency:'CN'}]}"
 * 
 */
function setInputValueById(divId,jsonObj){
	if(jsonObj==null||jsonObj==""||jsonObj==undefined){
		return;
	}
	$("#"+divId+" :input").each(function(){ 
	    var inputId = $(this).attr("id"); 
	    if(inputId!=null&&inputId!=''&&inputId!=undefined){
	    	var value = "";
	    	var classAttr = $(this).attr("class");
	    	//console.info("inputId=="+inputId+"==classAttr==="+classAttr);
	    	if(classAttr!=null&&classAttr!=""&&classAttr!=undefined){
	    		//判断是否为easyui-datebox
		    	var inputName = "";
		    	if(classAttr.indexOf("easyui-datebox")>=0){
		    		inputName = $(this).attr("comboname");
		    		value = jsonObj[inputName];
		    		if(value!=undefined){
		    			$('#'+inputId).datebox('setValue',value);
		    		}
		    	}
		    	//判断是否为easyui-datetimebox
		    	else if(classAttr.indexOf("easyui-datetimebox")>=0){
		    		inputName = $(this).attr("comboname");
		    		value = jsonObj[inputName];
		    		if(value!=undefined){
		    			$('#'+inputId).datetimebox('setValue',value);
		    		}
		    	}
		    	//判断是否为easyui-combobox
		    	else if(classAttr.indexOf("easyui-combobox")>=0){
		    		inputName = $(this).attr("comboname");
		    		value = jsonObj[inputName];
		    		if(value!=undefined){
		    			$('#'+inputId).combobox('setValue',value);
		    		}
		    	}//判断是否为searchbox
		    	else if(classAttr.indexOf("searchbox")>=0){
		    		inputName = $(this).attr("name");
		    		value = jsonObj[inputName];
		    		if(value!=undefined){
		    			$('#'+inputId).searchbox('setValue',value);
		    		}
		    	}
		    	//判断是否为easyui-numberbox
		    	else if(classAttr.indexOf("easyui-numberbox")>=0){
		    		inputName = $(this).attr("numberboxname");
		    		value = jsonObj[inputName];
		    		if(value!=undefined){
		    			$('#'+inputId).numberbox('setValue',value);
		    		}
		    	}
		    	else{
		    		inputName = $(this).attr("name");
		    		value = jsonObj[inputName];
		    		if(value!=undefined){
		    			$('#'+inputId).val(value);
		    		}
		    	}
	    	}
	    }
	});  
}

/**
 * @author 袁正军
 * 给指定DataGrid加载jsonObject中的数据，dataGrid的id和jsonObject中的key需要完全一样；
 * * jsonObj数据格式如下：
 * "{tradeNo:'LC201502050002',tradeInfoNo:'LC201502050002',tradeType:'02'," 
 *			"currencyCombo:'CNY',tradeStartDatebox:'2015-02-05',
 *			tradeInputListId:[{tradeDate:'2015-02-05',tradeType:'01',tradeInfoNo:'LC201502050001',
 *								tradeNo:'LC201502050001',tradeStoreId:1,tradeComId:1,agentId:1,companyId:1,R_ROWNUM:1,
 *								storeId:1,currency:'CN'}]}"
 */
function loadDataFromJsonObj(dataGridId,jsonObj){
	var dataGridData = jsonObj[dataGridId];
	if(dataGridData!=null&&dataGridData!=''&&dataGridData!=undefined){
		$('#'+dataGridId).datagrid('loadData',dataGridData);
	}
}


function loadJsonObjData(dataGridId,jsonObjData){
	if(jsonObjData!=null&&jsonObjData!=''&&jsonObjData!=undefined){
		for(var i=0;i<jsonObjData.length;i++){
			var rowData = jsonObjData[i];
			$('#'+dataGridId).datagrid('appendRow',rowData);
		}
	}
}

/****************可编辑表格需要使用的方法**********************/
//新增一行
function addOneRow(dataGridObj,editRowIndex){
	if(endEditing(dataGridObj,editRowIndex)){
		dataGridObj.datagrid('appendRow',{status:'P'});
		editRowIndex = dataGridObj.datagrid('getRows').length-1;
		dataGridObj.datagrid('selectRow', editRowIndex).datagrid('beginEdit', editRowIndex);	
	}
	return editRowIndex;
}

//判断当前行是否最后一行
function isLastRow(dataGridObj,currentRowIndex){
	var rowsData = dataGridObj.datagrid('getData');
	if(currentRowIndex==(rowsData.total-1)){
		return true;
	}else{
		return false;
	}
}

//删除一行
function removeOneRow(dataGridObj,index){
	if (index == undefined){
		return;
	}
	dataGridObj.datagrid('selectRow', index).datagrid('beginEdit', index).datagrid('deleteRow', index);
	dataGridObj.datagrid('acceptChanges');
}

//结束编辑
function endEditing(dataGridObj,editRowIndex){
	if (editRowIndex == null||editRowIndex == undefined){
		return true;
	}
	if(dataGridObj.datagrid('validateRow', editRowIndex)){
		dataGridObj.datagrid('endEdit', editRowIndex);
		editRowIndex = undefined;
		return true;
	}else{
		return false;
	}
	
}

//编辑一行
function editOneRow(dataGridObj,editRowIndex,index){
	if (editRowIndex != index){
		if (endEditing(dataGridObj,editRowIndex)){
			dataGridObj.datagrid('selectRow', index).datagrid('beginEdit', index);
			return true;
		} else {
			dataGridObj.datagrid('selectRow', editRowIndex);
			return false;
		}
	}
}

//锁定编辑
function lockOneRow(dataGridObj,editRowIndex){
	if (editRowIndex == null||editRowIndex == undefined){
		return true;
	}
	if (endEditing(dataGridObj,editRowIndex)){
		dataGridObj.datagrid('endEdit',editRowIndex);
		return true;
	}else{
		return false;
	}
}
function clearDatagrid(dataGrid)
{
	var item = dataGrid.datagrid('getRows');  
    if (item) {  
        for (var i = item.length - 1; i >= 0; i--) {  
            var index = dataGrid.datagrid('getRowIndex', item[i]);  
            dataGrid.datagrid('deleteRow', index);  
        }  
    }  
}

//清除表格所有行
function clearAllRows(dataGridObj){
	if(dataGridObj!=null&&dataGridObj!=""&&dataGridObj!=undefined){
		dataGridObj.datagrid("loadData",[]);
	}
}


function validateDataGrid(dataGridId){
	var allRows = $('#'+dataGridId).datagrid('getRows');
	var validFlag = true;
	for(var i=0;i<allRows.length;i++){
		if($('#'+dataGridId).datagrid('validateRow', i)){
			$('#'+dataGridId).datagrid('endEdit', i);
		}else{
			validFlag = false;
		}
	}
	return validFlag;
}
/*********************************************************************/

//日期比较，起始日期不能晚于结束日期
function compareDate(startDate, endDate) {
	if(startDate==null||startDate==""||startDate==undefined){
		return true;
	}
	if(endDate==null||endDate==""||endDate==undefined){
		return true;
	}
    var d1 = new Date(startDate.replace(/\-/g, "\/"));
    var d2 = new Date(endDate.replace(/\-/g, "\/"));
    if (d1 > d2) {
        return false;
    } 
    return true;
}

//js获取当前日期
function getCurrentDate() {
	var day = new Date(); 
	var Year = 0; 
	var Month = 0; 
	var Day = 0; 
	var CurrentDate = ""; 
	//初始化时间 
	Year= day.getFullYear();//ie火狐下都可以 
	Month= day.getMonth()+1; 
	Day = day.getDate(); 
	//Hour = day.getHours(); 
	// Minute = day.getMinutes(); 
	// Second = day.getSeconds(); 
	CurrentDate += Year + "-"; 
	 if (Month >= 10 ) 
	 { 
	 CurrentDate += Month + "-"; 
	 } 
	 else 
	 { 
	 CurrentDate += "0" + Month + "-"; 
	 } 
	 if (Day >= 10 ) 
	 { 
	 CurrentDate += Day ; 
	 } 
	 else 
	 { 
	 CurrentDate += "0" + Day ; 
	 } 

	return CurrentDate;
}


function checkIdNo(num){
    num = num.toUpperCase();
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))
    {
    	$.messager.alert('提示','输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
        return false;
    }
    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    //下面分别分析出生日期和校验位
    var len, re;
    len = num.length;
    if (len == 15)
    {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);
 
        //检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay)
        {
        	$.messager.alert('提示','输入的身份证号里出生日期不对！');
            return false;
        }
        else
        {
                //将15位身份证转成18位
                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                for(i = 0; i < 17; i ++)
                {
                    nTemp += num.substr(i, 1) * arrInt[i];
                }
                num += arrCh[nTemp % 11];
                return true;
        }
    }
    if (len == 18)
    {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);
 
        //检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay)
        {
        	$.messager.alert('提示','输入的身份证号里出生日期不对！');
            return false;
        }
    else
    {
        //检验18位身份证的校验码是否正确。
        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
        var valnum;
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
        var nTemp = 0, i;
        for(i = 0; i < 17; i ++)
        {
            nTemp += num.substr(i, 1) * arrInt[i];
        }
        valnum = arrCh[nTemp % 11];
        if (valnum != num.substr(17, 1))
        {
        	$.messager.alert('提示','18位身份证的校验码不正确！应该为：' + valnum);
            return false;
        }
        return true;
    }
    }
    return false;
}

function getBirthdayAndSex(val) {
	var birthdayAndSex = {};
	var birthdayValue;
	if (15 == val.length) { // 15位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7);
		if (parseInt(birthdayValue) < 10) {
			birthdayValue = '20' + birthdayValue;
		} else {
			birthdayValue = '19' + birthdayValue;
		}
		birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9)
				+ '-' + val.charAt(10) + val.charAt(11);
		if (parseInt(val.charAt(14) / 2) * 2 != val.charAt(14)){
			birthdayAndSex.sex = 'M';//男
		}else{
			birthdayAndSex.sex = 'F';//女
		}
		birthdayAndSex.birthday = birthdayValue;
	}
	if (18 == val.length) { // 18位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8)
				+ val.charAt(9) + '-' + val.charAt(10) + val.charAt(11) + '-'
				+ val.charAt(12) + val.charAt(13);
		if (parseInt(val.charAt(16) / 2) * 2 != val.charAt(16)){
			birthdayAndSex.sex = 'M';//男
		}
		else{
			birthdayAndSex.sex = 'F';//女
		}
		birthdayAndSex.birthday = birthdayValue;
	}
	return birthdayAndSex;
} 


function formatDateBoxValue(divId){
	$("#"+divId+" :input").each(function(){ 
	    var inputId = $(this).attr("id"); 
	    if(inputId!=null&&inputId!=''&&inputId!=undefined){
	    	var value = "";
	    	var classAttr = $(this).attr("class");
	    	//console.info("inputId=="+inputId+"==classAttr==="+classAttr);
	    	if(classAttr!=null&&classAttr!=""&&classAttr!=undefined){
	    		//判断是否为easyui-datebox
		    	var inputName = "";
		    	if(classAttr.indexOf("easyui-datebox")>=0){
		    		value = $(this).datebox("getValue");
		    		if(value!=undefined){
		    			$(this).datebox("setValue");
		    			$('#'+inputId).datebox('setValue',value);
		    		}
		    	}
		    	
	    	}
	    }
	});  
}

function formatDateStr(dateStr){
	var dateArray= new Array(); //定义一数组 
	dateArray=dateStr.split("-");
	var year = dateArray[0];
	var month = dateArray[1];
	var day = dateArray[2];
	if(month.length<2){
		month = "0" + month;
	}
	if(day.length<2){
		day = "0" + day;
	}
	return year+"-"+month+"-"+day;
}

function getMonthLastDay(year,month){
	if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
		return "31";
	}else if(month=="4"||month=="6"||month=="9"||month=="11"){
		return "30";
	}else if(month=="2"){
		if(year%4==0){
			return "29";
		}else{
			return "28";
		}
	}
}



Array.prototype.remove=function(index) 
{ 
    if(isNaN(index)||index>this.length){
    	return false;
    } 
    for(var i=0,n=0;i<this.length;i++) 
    { 
        if(this[i]!=this[index]) 
        { 
            this[n++]=this[i] 
        } 
    } 
    if(this.length>0){
    	this.length -=1 ; 
    }    
} 


function numToUpper(num){
	var strOutput = "";  
	  var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';  
	  num += "00";  
	  var intPos = num.indexOf('.');  
	  if (intPos >= 0){
		  num = num.substring(0, intPos) + num.substr(intPos + 1, 2); 
	  }  
	  strUnit = strUnit.substr(strUnit.length - num.length);  
	  for (var i=0; i < num.length; i++){  
		  strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);  
	  }
	  return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");  
}



function test(){
	var formData = "usercode=001&uwtype=1&uwpopedom=C&upusercode=001&edorpopedom=1&claimpopedom=1&remark=&addpoint=&popuwflag=Y&userstate=0";
	var jsonObject = formDataToJsonObj(formData);
//	console.info(jsonObject);
	return;
	var dataArray = new Array();
	dataArray = formData.split("&");
//	console.info("--------------1111-------------");
//	console.info(dataArray);
//	console.info("----------------2222-----------");
	var jsonStr = "{";
	for(var i=0;i<dataArray.length;i++){
		var attrAndValue = dataArray[i];
		var index = attrAndValue.indexOf("=");
//		console.info(attrAndValue);
		var key = attrAndValue.substring(0,index);
//		console.info("key==="+key);
		var value = attrAndValue.substring(index+1);
//		console.info("value==="+value);
		if(value!=null&&value!=undefined){//&&value!=""
			jsonStr += key+":'"+value+"',"
		}
	}
	jsonStr = jsonStr.substring(0,jsonStr.length-1);
	jsonStr += "}";
//	console.info("jsonStr=="+jsonStr);
	var jsonObj = {};
	jsonObj.userCode = "001";
	jsonObj.userName = "002";
//	console.info(jsonObj);
//	console.info(JSON.stringify(jsonObj));
	//{usercode:'001',uwtype:'1',uwpopedom:'C',upusercode:'001',edorpopedom:'1',claimpopedom:'1',remark:'',addpoint:'',popuwflag:'Y',userstate:'0'}
	
//	console.info("kkkkkkkkkkkkkkkk");
	var str = '{"userCode":"001","userName":"002"}';
//	console.info(eval("("+str+")"));
//	console.info(eval("("+jsonStr+")"));
}

function comboboxOnChange(obj,newValue,oldValue){
	if(newValue!=null&&newValue!=""&&newValue!=undefined){
		$(obj).next().children(":text").blur(function() {
			var allData = $(obj).combobox('getData');
			var value = $(obj).combobox('getValue');
			if(value==null||value==""||value==undefined){
				$(obj).combobox('reset');
			}else{
				var count = 0;
				for(var i=0;i<allData.length;i++){
					var rowData = allData[i];
					if(rowData.code==value){
						count++;
					}
				}
				if(count==0){
					$(obj).combobox('reset');
				}
			}
		});
	}
}
