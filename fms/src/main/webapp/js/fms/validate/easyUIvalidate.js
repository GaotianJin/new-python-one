//以下4个的easyUI内部已经实现过了，后边的都是补充的
//email：匹配E-Mail的正则表达式规则。
//url：匹配URL的正则表达式规则。
//length[0,100]：允许在x到x之间个字符。
//remote['http://.../action.do','paramName']：发送ajax请求需要验证的值，当成功时返回true。

$.extend($.fn.validatebox.defaults.rules, {
    rExp: {//自己指定正则表达式,传入时需要转义。例如：validType="rExp['^\\d+$']"
        validator: function (value, param) {
            var reg = new RegExp(param[0]);
            return reg.test(value);
        },
        message: ""
    },
    equaldDate:{
		validator:function (value,param){
		 var start=$(param[0]).datetimebox('getValue');
		 return value>start;
		},
		message:'下次跟踪日期应大于回访日期'
	},
    age: {// 验证年龄
        validator: function (value) {
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
        },
        message: '年龄必须是0到120之间的整数'
    },
    chinesename: {// 验证中文
        validator: function (value) {
            return /^[\u4e00-\u9fa5]{2,8}$/i.test(value);
        },
        message: '请输入2-8个汉字'
    },
    chinese: {// 验证中文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value);
        },
        message: '请输入中文'
    },
    currency: {// 验证货币
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    date: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '清输入合适的日期格式'
    },  
    english: {// 验证英语
        validator: function (value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    equals: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    },    
    faxno: {// 验证传真
        validator: function (value) {
            //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
            return /^((\d2,3 )|(\d{3}\-))?(0\d2,3 |0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '传真号码不正确'
    },

    idcard: {// 验证身份证
        validator: function (value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    year: {// 验证整数 可正负数
        validator: function (value) {        
            return /^(19)|(20)\d{2}$/i.test(value);
        },
        message: '请输入整数'
    },
    integer: {// 验证整数 可正负数
        validator: function (value) {
            //return /^[+]?[1-9]+\d*$/i.test(value);

            return /^[-]?\d*$/i.test(value);
        },
        message: '请输入整数'
    },
    intPlus: {// 验证正整数,包含0。
        validator: function (value) {
            //return /^[+]?[1-9]+\d*$/i.test(value);

            return /^\d*$/i.test(value);
        },
        message: '请输入正整数'
    },
    intSub: {// 验证正整数,包含0。
        validator: function (value) {
            //return /^[+]?[1-9]+\d*$/i.test(value);

            return /^[-]\d+$/i.test(value);
        },
        message: '请输入负整数'
    },
    intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    length: { 
        validator: function (value, param) {
            var len = $.trim(value).length;
            return len >= param[0] && len <= param[1];
        },
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    ip: {// 验证IP地址
        validator: function (value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message: 'IP地址格式不正确'
    },
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '请输入至少{0}个字符.'
    },
    maxNum: {
        validator: function (value) {
            return value < 100;
        },
        message: '输入的值小于100.'
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^1[0-9]{10}$/i.test(value);
        },
        message: '手机号由1开头的11位数字组成'
    },
    msn: {
        validator: function (value) {
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '请输入姓名'
    },
    uname: {// 验证姓名，由2-8个汉字组成
        validator: function (value) {
            return /^[\u4e00-\u9fa5]{2,8}$/i.test(value);
        },
        message: '用户名由2-8个汉字组成'
    },
    upwd: {// 验证密码，密码为6-15位数字和字母组成
        validator: function (value) {
            return /^[a-zA-Z0-9]{6,15}$/i.test(value);
        },
        message: '密码由6-15位数字和字母组成'
    },
    phone: {// 验证电话号码
        validator: function (value) {
            return /^((\d2,3 )|(\d{3}\-))?(0\d2,3 |0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '格式不正确,请使用下面格式:020-88888888'
    },   
    qq: {// 验证QQ,从10000开始
        validator: function (value) {
            return /^[1-9]\d{4,10}$/i.test(value);
        },
        message: 'QQ号码格式不正确'
    },
    weiXin: {// 验证微信号：5位以，只能数字和英文、下划线
        validator: function (value) {
            return /^[a-zA-Z\d_]{5,}$/i.test(value);
        },
        message: '微信号码格式不正确'
    },
    	
    unnormal: {// 验证是否包含空格和非法字符
        validator: function (value) {
            return /.+/i.test(value);
        },
        message: '输入值不能为空和包含其他非法字符'
    },
    username: {// 验证用户名
        validator: function (value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },    
    zip: {// 验证邮政编码
        validator: function (value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    subject: {// 验证课程简称允许字母数字下划线
	    validator: function (value) {
	        return /^[a-zA-Z][a-zA-Z0-9_]{0,15}$/i.test(value);
	    },
	    message: '输入不合法（字母开头，允许1-16字节，允许字母数字下划线）'
    },
    onenum:{
    	validator: function (value) {
    		return /^[0-9]$/i.test(value);
    	},
    	message:'只能输入1个数字'
    }
}); 
