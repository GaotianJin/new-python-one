var bgObj,msgObj,title,img,txt;

function sAlert(str) {
	
	var msgw,msgh,bordercolor,titleheight,titlecolor;
	msgw = 300;
	msgh = 120;
	bordercolor = "#95B8E7";
	titleheight = 25;
	titlecolor = "#99CCFF";
	
	var sWidth, sHeight;
	sWidth = document.body.offsetWidth;
	sHeight = screen.height;
	/** 背景层 **/
	bgObj = document.createElement("div"); 
	bgObj.setAttribute('id','bgDiv');
	bgObj.style.position = "absolute";
	bgObj.style.top = "0";
	bgObj.style.background = "#777";
	bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75)";
	bgObj.style.opacity = "0.6";
	bgObj.style.left = "0";
	bgObj.style.width = sWidth + "px";
	bgObj.style.height = sHeight + "px";
	bgObj.style.zIndex = "10000";
	document.body.appendChild(bgObj);
	/** 提示框层 **/
	msgObj = document.createElement("div");
	msgObj.setAttribute("id","msgDiv");
	msgObj.setAttribute("align","center");
	msgObj.style.background = "white";
	msgObj.style.border = "1px solid " + bordercolor;
	msgObj.style.position = "absolute";
	msgObj.style.left = "50%";
	msgObj.style.top = "50%";
	msgObj.style.font = "14px/1.6em Verdana,Geneva,Arial,Helvetica,sans-serif";
	msgObj.style.marginLeft = "-225px";
	msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
	msgObj.style.width = msgw + "px";
	msgObj.style.height = msgh + "px";
	msgObj.style.textAlign = "center";
	msgObj.style.lineHeight = "25px";
	msgObj.style.zIndex = "10001";
	/** 标题层 **/
	title = document.createElement("h4");
	title.setAttribute("id","msgTitle");
	title.setAttribute("align","right");
	title.style.margin = "0";
	title.style.padding = "3px";
	title.style.background = bordercolor;
	title.style.filter = "progid:DXImageTransform.Microsoft.Alpha(startX=20,startY=20,finishX=100,finishY=100,style=1,opacity=75,finishOpacity=100)";
	title.style.opacity = "0.75";
	title.style.border = "1px solid " + bordercolor;
	title.style.height = "18px";
	title.style.font = "12px Verdana,Geneva,Arial,Helvetica,sans-serif";
	title.style.color = "white";
	title.style.cursor = "pointer";
	title.innerHTML = "关闭";
	title.onclick = cAlert;
	/** 提示框按钮 **/
	img = document.createElement("img");
	img.setAttribute("type","img");
	img.setAttribute("value","关闭");
	img.style.marginLeft = "0px";
	img.style.marginBottom = "0px";
	img.style.background = "white";
	img.style.color = "white";
	img.src = "../js/easyui/themes/default/images/loading.gif";
	
	document.body.appendChild(msgObj);
	document.getElementById("msgDiv").appendChild(title);
	/** 提示信息 **/
	txt = document.createElement("p");
	txt.style.margin = "1em 0";
	txt.setAttribute("id","msgTxt");
	txt.innerHTML = str;
	
	document.getElementById("msgDiv").appendChild(txt);
	document.getElementById("msgDiv").appendChild(img);
}

function cAlert(){
  document.body.removeChild(bgObj);
  document.getElementById("msgDiv").removeChild(title);
  document.body.removeChild(msgObj);
}