/**
 * jQuery JSON plugin 2.4.0
 *
 * @author Brantley Harris, 2009-2011
 * @author Timo Tijhof, 2011-2012
 * @source This plugin is heavily influenced by MochiKit's serializeJSON, which is
 *         copyrighted 2005 by Bob Ippolito.
 * @source Brantley Harris wrote this plugin. It is based somewhat on the JSON.org
 *         website's http://www.json.org/json2.js, which proclaims:
 *         "NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.", a sentiment that
 *         I uphold.
 * @license MIT License <http://www.opensource.org/licenses/mit-license.php>
 */
(function ($) {
	'use strict';

	var escape = /["\\\x00-\x1f\x7f-\x9f]/g,
		meta = {
			'\b': '\\b',
			'\t': '\\t',
			'\n': '\\n',
			'\f': '\\f',
			'\r': '\\r',
			'"' : '\\"',
			'\\': '\\\\'
		},
		hasOwn = Object.prototype.hasOwnProperty;

	/**
	 * jQuery.toJSON
	 * Converts the given argument into a JSON representation.
	 *
	 * @param o {Mixed} The json-serializable *thing* to be converted
	 *
	 * If an object has a toJSON prototype, that will be used to get the representation.
	 * Non-integer/string keys are skipped in the object, as are keys that point to a
	 * function.
	 *
	 */
	$.toJSON = typeof JSON === 'object' && JSON.stringify ? JSON.stringify : function (o) {
		if (o === null) {
			return 'null';
		}

		var pairs, k, name, val,
			type = $.type(o);

		if (type === 'undefined') {
			return undefined;
		}

		// Also covers instantiated Number and Boolean objects,
		// which are typeof 'object' but thanks to $.type, we
		// catch them here. I don't know whether it is right
		// or wrong that instantiated primitives are not
		// exported to JSON as an {"object":..}.
		// We choose this path because that's what the browsers did.
		if (type === 'number' || type === 'boolean') {
			return String(o);
		}
		if (type === 'string') {
			return $.quoteString(o);
		}
		if (typeof o.toJSON === 'function') {
			return $.toJSON(o.toJSON());
		}
		if (type === 'date') {
			var month = o.getUTCMonth() + 1,
				day = o.getUTCDate(),
				year = o.getUTCFullYear(),
				hours = o.getUTCHours(),
				minutes = o.getUTCMinutes(),
				seconds = o.getUTCSeconds(),
				milli = o.getUTCMilliseconds();

			if (month < 10) {
				month = '0' + month;
			}
			if (day < 10) {
				day = '0' + day;
			}
			if (hours < 10) {
				hours = '0' + hours;
			}
			if (minutes < 10) {
				minutes = '0' + minutes;
			}
			if (seconds < 10) {
				seconds = '0' + seconds;
			}
			if (milli < 100) {
				milli = '0' + milli;
			}
			if (milli < 10) {
				milli = '0' + milli;
			}
			return '"' + year + '-' + month + '-' + day + 'T' +
				hours + ':' + minutes + ':' + seconds +
				'.' + milli + 'Z"';
		}

		pairs = [];

		if ($.isArray(o)) {
			for (k = 0; k < o.length; k++) {
				pairs.push($.toJSON(o[k]) || 'null');
			}
			return '[' + pairs.join(',') + ']';
		}

		// Any other object (plain object, RegExp, ..)
		// Need to do typeof instead of $.type, because we also
		// want to catch non-plain objects.
		if (typeof o === 'object') {
			for (k in o) {
				// Only include own properties,
				// Filter out inherited prototypes
				if (hasOwn.call(o, k)) {
					// Keys must be numerical or string. Skip others
					type = typeof k;
					if (type === 'number') {
						name = '"' + k + '"';
					} else if (type === 'string') {
						name = $.quoteString(k);
					} else {
						continue;
					}
					type = typeof o[k];

					// Invalid values like these return undefined
					// from toJSON, however those object members
					// shouldn't be included in the JSON string at all.
					if (type !== 'function' && type !== 'undefined') {
						val = $.toJSON(o[k]);
						pairs.push(name + ':' + val);
					}
				}
			}
			return '{' + pairs.join(',') + '}';
		}
	};

	/**
	 * jQuery.evalJSON
	 * Evaluates a given json string.
	 *
	 * @param str {String}
	 */
	$.evalJSON = typeof JSON === 'object' && JSON.parse ? JSON.parse : function (str) {
		/*jshint evil: true */
		return eval('(' + str + ')');
	};

	/**
	 * jQuery.secureEvalJSON
	 * Evals JSON in a way that is *more* secure.
	 *
	 * @param str {String}
	 */
	$.secureEvalJSON = typeof JSON === 'object' && JSON.parse ? JSON.parse : function (str) {
		var filtered =
			str
			.replace(/\\["\\\/bfnrtu]/g, '@')
			.replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
			.replace(/(?:^|:|,)(?:\s*\[)+/g, '');

		if (/^[\],:{}\s]*$/.test(filtered)) {
			/*jshint evil: true */
			return eval('(' + str + ')');
		}
		throw new SyntaxError('Error parsing JSON, source is not valid.');
	};

	/**
	 * jQuery.quoteString
	 * Returns a string-repr of a string, escaping quotes intelligently.
	 * Mostly a support function for toJSON.
	 * Examples:
	 * >>> jQuery.quoteString('apple')
	 * "apple"
	 *
	 * >>> jQuery.quoteString('"Where are we going?", she asked.')
	 * "\"Where are we going?\", she asked."
	 */
	$.quoteString = function (str) {
		if (str.match(escape)) {
			return '"' + str.replace(escape, function (a) {
				var c = meta[a];
				if (typeof c === 'string') {
					return c;
				}
				c = a.charCodeAt();
				return '\\u00' + Math.floor(c / 16).toString(16) + (c % 16).toString(16);
			}) + '"';
		}
		return '"' + str + '"';
	};

/** 

 * 为grid添加自己重新加载方法,解决带条件查询的时候分页栏不能回到首页问题 

 */ 

 $.extend($.fn.datagrid.methods, { 

     cisreload: function(jq, newposition){ 
    	 
          return jq.each(function(){ 

                  //显示第一页数据   
                 $(this).datagrid("options").pageNumber = 1;   
                 //分页栏上跳转到第一页   
                 $(this).datagrid('getPager').pagination({pageNumber: 1}); 
                 $(this).datagrid("reload",newposition); 
          }); 
     } 
 }); 

 /**   
  * @author {CaoGuangHui}   
  */   
 $.extend($.fn.tabs.methods, {    
     /** 
      * 加载iframe内容   
      * @param  {jq Object} jq     [description]   
      * @param  {Object} params    params.which:tab的标题或者index;params.iframe:iframe的相关参数   
      * @return {jq Object}        [description]   
      */   
     loadTabIframe:function(jq,params){    
         return jq.each(function(){    
             var $tab = $(this).tabs('getTab',params.which);    
             if($tab==null) return;    
    
             var $tabBody = $tab.panel('body');    
    
             //销毁已有的iframe    
             var $frame=$('iframe', $tabBody);    
             if($frame.length>0){    
                 try{//跨域会拒绝访问，这里处理掉该异常    
                     $frame[0].contentWindow.document.write('');    
                     $frame[0].contentWindow.close();    
                 }catch(e){    
                     //Do nothing    
                 }    
                 $frame.remove();    
                 if($.browser.msie){    
                     CollectGarbage();    
                 }    
             }    
             $tabBody.html('');    
    
             $tabBody.css({'overflow':'hidden','position':'relative'});    
             var $mask = $('<div style="position:absolute;z-index:2;width:100%;height:100%;background:#ccc;z-index:1000;opacity:0.3;filter:alpha(opacity=30);"><div>').appendTo($tabBody);    
             var $maskMessage = $('<div class="mask-message" style="z-index:3;width:auto;height:16px;line-height:16px;position:absolute;top:50%;left:50%;margin-top:-20px;margin-left:-92px;border:2px solid #d4d4d4;padding: 12px 5px 10px 30px;background: #ffffff url(\'js/easyui/themes/default/images/loading.gif\') no-repeat scroll 5px center;">' + (params.iframe.message || '加载中, 请稍等 ...') + '</div>').appendTo($tabBody);    
             var $containterMask = $('<div style="position:absolute;width:100%;height:100%;z-index:1;background:#fff;"></div>').appendTo($tabBody);    
             var $containter = $('<div style="position:absolute;width:100%;height:100%;z-index:0;"></div>').appendTo($tabBody);    
    
             var iframe = document.createElement("iframe");    
             iframe.src = params.iframe.src;    
             iframe.frameBorder = params.iframe.frameBorder || 0;    
             iframe.height = params.iframe.height || '100%';    
             iframe.width = params.iframe.width || '100%';    
             if (iframe.attachEvent){    
                 iframe.attachEvent("onload", function(){    
                     $([$mask[0],$maskMessage[0]]).fadeOut(params.iframe.delay || 'fast',function(){    
                         $(this).remove();    
                         if($(this).hasClass('mask-message')){    
                             $containterMask.fadeOut(params.iframe.delay || 'fast',function(){    
                                 $(this).remove();    
                             });    
                         }    
                     });    
                 });    
             } else {    
                 iframe.onload = function(){    
                     $([$mask[0],$maskMessage[0]]).fadeOut(params.iframe.delay || 'fast',function(){    
                         $(this).remove();    
                         if($(this).hasClass('mask-message')){    
                             $containterMask.fadeOut(params.iframe.delay || 'fast',function(){    
                                 $(this).remove();    
                             });    
                         }    
                     });    
                 };    
             }    
             $containter[0].appendChild(iframe);    
         });    
     },    
     /** 
      * 增加iframe模式的标签页   
      * @param {[type]} jq     [description]   
      * @param {[type]} params [description]   
      */   
     addIframeTab:function(jq,params){    
         return jq.each(function(){    
             if(params.tab.href){    
                 delete params.tab.href;    
             }    
             $(this).tabs('add',params.tab);    
             $(this).tabs('loadTabIframe',{'which':params.tab.title,'iframe':params.iframe});    
         });    
     },    
     /** 
      * 更新tab的iframe内容   
      * @param  {jq Object} jq     [description]   
      * @param  {Object} params [description]   
      * @return {jq Object}        [description]   
      */   
     updateIframeTab:function(jq,params){    
         return jq.each(function(){    
             params.iframe = params.iframe || {};    
             if(!params.iframe.src){    
                 var $tab = $(this).tabs('getTab',params.which);    
                 if($tab==null) return;    
                 var $tabBody = $tab.panel('body');    
                 var $iframe = $tabBody.find('iframe');    
                 if($iframe.length===0) return;    
                 $.extend(params.iframe,{'src':$iframe.attr('src')});    
             }    
             $(this).tabs('loadTabIframe',params);    
         });    
     }    
 });  
 

 	/**
	 * 以下是日期控件my97的使用
	 */
	$.fn.my97 = function(options, params) {
		if (typeof options == "string") {
			return $.fn.my97.methods[options](this, params);
		}
		options = options || {};
		if (!WdatePicker) {
			alert("未引入My97js包！");
			return;
		}
		return this.each(function() {
			var data = $.data(this, "my97");
			var newOptions;
			if (data) {
				newOptions = $.extend(data.options, options);
				data.opts = newOptions;
			} else {
				newOptions = $.extend({}, $.fn.my97.defaults, $.fn.my97
						.parseOptions(this), options);
				$.data(this, "my97", {
					options : newOptions
				});
			}
			$(this).addClass('Wdate').click(function() {

				WdatePicker(newOptions);
			});
		});
	};

	$.fn.my97.methods = {
		setValue : function(target, params) {
			target.val(params);
		},

		getValue : function(target) {
			return target.val();
		},
		clearValue : function(target) {
			target.val('');
		}
	};

	$.fn.my97.parseOptions = function(target) {
		return $.extend({}, $.parser.parseOptions(target, [ "el", "vel",
				"weekMethod", "lang", "skin", "dateFmt", "realDateFmt",
				"realTimeFmt", "realFullFmt", "minDate", "maxDate",
				"startDate", {
					doubleCalendar : "boolean",
					enableKeyboard : "boolean",
					enableInputMask : "boolean",
					autoUpdateOnChanged : "boolean",
					firstDayOfWeek : "number",
					isShowWeek : "boolean",
					highLineWeekDay : "boolean",
					isShowClear : "boolean",
					isShowToday : "boolean",
					isShowOthers : "boolean",
					readOnly : "boolean",
					errDealMode : "boolean",
					autoPickDate : "boolean",
					qsEnabled : "boolean",
					autoShowQS : "boolean",
					opposite : "boolean"
				}
		]));
	};
	$.fn.my97.defaults = {
		dateFmt : 'yyyy-MM-dd'
	};
	$.parser.plugins.push('my97');
	
	/**
	 *author:yuanzhengjun 2015-03-03 
	 */
	$.extend($.fn.validatebox.defaults.rules, {    
	    validCode: {    
	        validator: function(value,param){  
	        	var reg=/^[a-z0-9A-Z_-]+$/; 
	            return reg.test(value);    
	        },    
	        message: '内容只能输入字母、数字、下划线以及"-"'   
	    },
	    validEn: {    
	        validator: function(value,param){  
	        	var reg=/^[a-zA-Z]+$/; 
	            return reg.test(value);    
	        },    
	        message: '内容只能输入英文字母'   
	    },
		validNum: {    
	        validator: function(value,param){  
	        	var reg=/^[0-9]+$/; 
	            return reg.test(value);    
	        },    
	        message: '内容只能输入数字'   
	    },
	    validDecNum: {  
	        validator: function(value,param){  
	        	var reg=/^[\+\-]?(([1-9]\d*)|\d)(\.\d{1,10})?$/; 
	            return reg.test(value);    
	        },    
	        message: '内容只能输入整数或小数(精度最多为10位)'   
	    },
	    validZip: {    
	        validator: function(value,param){  
	        	var reg=/^\d{6}$/; 
	            return reg.test(value);    
	        },    
	        message: '邮编只能为6位数字'   
	    },
        validTel: {    
	        validator: function(value,param){  
	        	var reg=/^([0-9]{3}-|[0-9]{4}-)?([0-9]{8}|[0-9]{7})?(-[0-9]{1,6})?$/; 
	        	reg = /^(\d{3,4}[ -]{1})?\d{6,8}([ -]{1}\d{1,6})?$/;
	            return reg.test(value);    
	        },    
	        message: "电话号码格式为(区号-电话号码)或不带区号"   
	    },
        validPhone: {    
	        validator: function(value,param){  
	        	var reg=/^(?:13\d|14\d|15\d|17\d|18\d)-?\d{5}(\d{3}|\*{3})$/; 
	            return reg.test(value);    
	        },    
	        message: "请输入正确格式的手机号码"
	    },
        validQQ: {    
	        validator: function(value,param){  
	        	var reg=/^[1-9]\d{4,10}$/; 
	            return reg.test(value);    
	        },    
	        message: "请输入正确格式的QQ号码"   
	    },
	    validDate:{
	    	validator: function(value,param){  
	        	var reg=/^((((19|20)\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\d|30))|(((19|20)\d{2})-(0?[13578]|1[02])-31)|(((19|20)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$/; 
	            var reg1 = /^\d{4}(\-)\d{2}(\-)\d{2}$/;
	            var varifyFlag = false;
	            if(reg.test(value)&&reg1.test(value)){
	            	varifyFlag = true;
	            }
	        	return varifyFlag;    
	        },    
	        message: "请输入YYYY-MM-DD格式的日期,月份与日期为均为两位数值"  
	    	
	    },
		closePeriod : {
			validator : function(value, param) {
				var reg = /[^0-9/]/;
				var varifyFlag = false;
				if(!reg.test(value)){
					varifyFlag = true;
				}
				return varifyFlag;
			},
			message : '封闭期仅可输入数字或/'
		}
	});
	
	$.extend($.fn.datagrid.methods, {
	    addEditor : function(jq, param) {
	        if (param instanceof Array) {
	            $.each(param, function(index, item) {
	                var e = $(jq).datagrid('getColumnOption', item.field);
	                e.editor = item.editor;
	            });
	        } else {
	            var e = $(jq).datagrid('getColumnOption', param.field);
	            e.editor = param.editor;
	        }
	    },
	    removeEditor : function(jq, param) {
	        if (param instanceof Array) {
	            $.each(param, function(index, item) {
	                var e = $(jq).datagrid('getColumnOption', item);
	                e.editor = {};
	            });
	        } else {
	            var e = $(jq).datagrid('getColumnOption', param);
	            e.editor = {};
	        }
	    }
	});
	

	
	/***扩展editors的datetimebox方法*****/ 
	$.extend($.fn.datagrid.defaults.editors, { 
		numberspinner: {         
			init: function(container, options){             
				var input = $('<input type="text">').appendTo(container);             
				return input.numberspinner(options);         
				},         
			destroy: function(target){             
				$(target).numberspinner('destroy');         
				},         
			getValue: function(target){             
				return $(target).numberspinner('getValue');         
				},         
			setValue: function(target, value){             
					$(target).numberspinner('setValue',value);         
					},         
			resize: function(target, width){             
				$(target).numberspinner('resize',width);         
				}     }, 
		datetimebox: {
			//datetimebox就是你要自定义editor的名称        
			init: function(container, options){             
				var editor = $('<input />').appendTo(container);             
				editor.enableEdit = false;             
				editor.datetimebox(options);             
				return editor;         
			},         
			getValue: function(target){         
				var new_str = $(target).datetimebox('getValue').replace(/:/g,'-');         
				new_str = new_str.replace(/ /g,'-');         
				var arr = new_str.split("-");         
				var datum = new Date(Date.UTC(arr[0],arr[1]-1,arr[2],arr[3]-8,arr[4],arr[5]));         
				var timeStamp = datum.getTime(); 
				return new Date(timeStamp).format("yyyy-MM-dd hh:mm:ss");             
				//return timeStamp;        
			},         
			setValue: function(target, value){     
				if(value){
					//$(target).datetimebox('setValue',new Date(value).format("yyyy-MM-dd hh:mm:ss"));
					$(target).datetimebox('setValue',value);
				}         
				else {
					$(target).datetimebox('setValue',new Date().format("yyyy-MM-dd hh:mm:ss"));
				}        
			},         
			resize: function(target, width){            
				$(target).datetimebox('resize',width);                 
			},         
			destroy: function(target){         
				$(target).datetimebox('destroy');         
			}     
		} 
	});
	
	
	// 时间格式化 tianweiqi
	Date.prototype.format = function (format) {
	    /*
	    * eg:format="yyyy-MM-dd hh:mm:ss";
	    */
	    if (!format) {
	        format = "yyyy-MM-dd hh:mm:ss";
	    }

	    var o = {
	        "M+": this.getMonth() + 1, // month
	        "d+": this.getDate(), // day
	        "h+": this.getHours(), // hour
	        "m+": this.getMinutes(), // minute
	        "s+": this.getSeconds(), // second
	        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
	        "S": this.getMilliseconds()
	        // millisecond
	    };

	    if (/(y+)/.test(format)) {
	        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    }

	    for (var k in o) {
	        if (new RegExp("(" + k + ")").test(format)) {
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	        }
	    }
	    return format;
	};
	
	
	//yuanzhengjun 
	/*$.fn.datebox.defaults.parser = function(s){
		s="2015-05-01";
		var t = Date.parse(s);
		return new Date(t);
		if (!isNaN(t)){
			return new Date(t);
		} else {
			alert("日期格式不符合要求");
			//return new Date();
		}
	}*/

	/*$.extend($.fn.combobox.methods, { 
	    selectedIndex: function (jq, index) { 
	        if (!index) { 
	            index = 0; 
	        } 
	        $(jq).combobox({ 
	            onLoadSuccess: function () { 
	                var opt = $(jq).combobox('options'); 
	                var data = $(jq).combobox('getData'); 

	                for (var i = 0; i < data.length; i++) { 
	                    if (i == index) { 
	                        $(jq).combobox('setValue', eval('data[index].' + opt.valueField)); 
	                        break; 
	                    } 
	                } 
	            } 
	        }); 
	    } 
	});*/

	$.fn.tips = function(options){
        var defaults = {
            side:1,
            msg:'',
            color:'#FFF',
            bg:'#F00',
            time:2,
            x:0,
            y:0
        }
        var options = $.extend(defaults, options);
        if (!options.msg||isNaN(options.side)) {
        throw new Error('params error');
    }
    if(!$('#jquery_tips_style').length){
        var style='<style id="jquery_tips_style" type="text/css">';
        style+='.jq_tips_box{padding:10px;position:absolute;overflow:hidden;display:inline;display:none;z-index:10176523;}';
        style+='.jq_tips_arrow{display:block;width:0px;height:0px;position:absolute;}';
        style+='.jq_tips_top{border-left:10px solid transparent;left:20px;bottom:0px;}';
        style+='.jq_tips_left{border-top:10px solid transparent;right:0px;top:18px;}';
        style+='.jq_tips_bottom{border-left:10px solid transparent;left:20px;top:0px;}';
        style+='.jq_tips_right{border-top:10px solid transparent;left:0px;top:18px;}';
        style+='.jq_tips_info{word-wrap: break-word;word-break:normal;border-radius:4px;padding:5px 8px;max-width:500px;overflow:hidden;box-shadow:1px 1px 1px #999;font-size:12px;cursor:pointer;}';
        style+='</style>';
        $(document.body).append(style);
    }
        this.each(function(){
            var element=$(this);
            var element_top=element.offset().top,element_left=element.offset().left,element_height=element.outerHeight(),element_width=element.outerWidth();
            options.side=options.side<1?1:options.side>4?4:Math.round(options.side);
            var sideName=options.side==1?'top':options.side==2?'right':options.side==3?'bottom':options.side==4?'left':'top';
            var tips=$('<div class="jq_tips_box"><i class="jq_tips_'+sideName+'"></i><div class="jq_tips_info">'+options.msg+'</div></div>').appendTo(document.body);
//            var tips=$('<div class="jq_tips_box"><i class="jq_tips_arrow jq_tips_'+sideName+'"></i><div class="jq_tips_info">'+options.msg+'</div></div>').appendTo(document.body);
            tips.find('.jq_tips_arrow').css('border-'+sideName,'10px solid '+options.bg);
            tips.find('.jq_tips_info').css({
                color:options.color,
                backgroundColor:options.bg
            });
            switch(options.side){
                case 1:
                    tips.css({
                        top:element_top-tips.outerHeight()+options.x,
                        left:element_left-10+options.y
                    });
                    break;
                case 2:
                    tips.css({
                        top:element_top-20+options.x,
                        left:element_left+element_width+options.y
                    });
                    break;
                case 3:
                    tips.css({
                        top:element_top+element_height+options.x,
                        left:element_left-10+options.y
                    });
                    break;
                case 4:
                    tips.css({
                        top:element_top-20+options.x,
                        left:element_left-tips.outerWidth()+options.y
                    });
                    break;
                default:
            }
            var closeTime;
            tips.fadeIn('fast').click(function(){
                clearTimeout(closeTime);
                tips.fadeOut('fast',function(){
                    tips.remove();
                })
            })
            if(options.time){
                closeTime=setTimeout(function(){
                    tips.click();
                },options.time*1000);
                tips.hover(function(){
                    clearTimeout(closeTime);
                },function(){
                    closeTime=setTimeout(function(){
                        tips.click();
                    },options.time*1000);
                })
            }
        });
        return this;
    };


	
})(jQuery);

//以下是my97的使用实例

/*<input  class="easyui-my97" type="text"> 
 * <input  class="easyui-my97" dateFmt = 'yyyy-MM-dd HH:mm:ss' type="text"> 
 * 
 * <input  id="my97" type="text" name=""> 
 * $('#my97').my97(options); */




