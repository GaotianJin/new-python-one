<%@page import="com.sinosoft.util.LoginInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>中科软财富综合管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/leftMenu.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myWorkbench.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pageCraniocaudal.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/easyui/themes/blue/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/e-cis.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/json.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/highcharts.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/exporting.js"></script>   --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/processer.js"></script> 
<!-- <link rel="stylesheet" type="text/css" href="../css/leftMenu.css" />
<link rel="stylesheet" type="text/css" href="../css/myWorkbench.css" />
<link rel="stylesheet" type="text/css" href="../css/pageCraniocaudal.css" />
<link rel="stylesheet" type="text/css" href="../css/global.css" />
<link rel="stylesheet" type="text/css" href="../js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="../css/e-cis.css" />
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/processer.js"></script> -->
<script type="text/javascript" >
	contextPath = '<%=request.getContextPath()%>' ;
	userId = '<%=((LoginInfo)request.getAttribute("userSessionInfo")).getUserId() %>';
	</script>
<!-- link rel="shortcut icon" href="<%=request.getContextPath()%>/img/favicon.ico">  
<link rel="Bookmark" href="<%=request.getContextPath()%>/img/favicon.ico">  
 -->

<script type="text/javascript">
jQuery.ajaxSetup({
	cache : false
});//ajax不缓存

jQuery(function($) {
	$
			.ajax({
				async : false,
				cache : false,
				type : "POST",
				dataType : "json",
				url : "welcome/queryMenu",
				error : function() {
					alert("请求异常");
				},
				success : function(data) {
					var htmlTree = "<ul class='menu_main'>";
					var all = data.length;
					var mainMenuList = [];
					var subMenuInfo = {};
					for(var i = 0; i < data.length; i++){
						var node = data[i];
						var pId = node.pId;
						if(pId==null||pId==""){
							mainMenuList.push(node);
						}else{
							if(subMenuInfo[pId]==null||subMenuInfo[pId]==undefined){
								var subMenuList = [];
								subMenuList.push(node);
								subMenuInfo[pId] = subMenuList;
							}else{
								subMenuInfo[pId].push(node);
							}
						}
					}
					for ( var i = 0; i < mainMenuList.length; i++) {
						var mainNode = mainMenuList[i];
						/* alert(mainNode.name); */
						if (mainNode.name == "我的任务" || mainNode.name == "我的菜单") {
							//htmlTree =htmlTree+"<li class='menu_title'><a class='menu_title_link' href='#'onclick=addtab('"+data[i].name+"','"+ data[i].url+"')>"+data[i].name+"</a></li>";
							htmlTree = htmlTree
									+ "<li class='menu_title'><a class='menu_title_link' href='#'>我的菜单</a></li>";
							commonTab();
						}else{

							if(mainNode.click == null||mainNode.click == ""){
								htmlTree = htmlTree
											+ "<li class='menu_item menu_title_link' style='font-weight:bold;' id = menu_item_"+mainNode.id+" > <a id = menu_link_"
											+ mainNode.id
											+ " class='menu_link select menu_folder' href='#' style='background:#93d9f9;' onclick = meunSelect('"
											+ mainNode.name + "','"
											+ mainNode.url + "'," + i + ","
											+ mainMenuList.length + ")>" + mainNode.name
											+ "</a>";
								/* htmlTree = htmlTree
											+ "<div style='display: none;' id = 'submenuDiv"+i+"'><ul class='submenu'>"; */
								//alert("第"+(i+1)+"次生成子菜单");
								/* alert(htmlTree); */
								var subMenuHtml = createSubMenuHtml(mainNode.id,subMenuInfo,i,mainMenuList.length);
								//alert(subMenuHtml);
								htmlTree += subMenuHtml;
								htmlTree += "</li>";
								
							}else{
								htmlTree = htmlTree
								+ "<li class='menu_item' id = menu_item_"+mainNode.id+" ><a class='menu_link' href='#' id = menu_link_"
								+ mainNode.id + " onclick=meunSelect('"
								+ mainNode.name + "','"
								+ mainNode.url + "'," + i + ","
								+ 0 + ")>" + mainNode.name
								+ "</a></li>";
							}
						}
					}
					htmlTree = htmlTree + "</ul>";
					$('#menuDiv').html(htmlTree);
				}
			});
});

/**
 * 打开各TAB页
 */
function commonTab(){

/* $('#tabdiv').tabs('add',{
	title: '公司政策',
	selected: true,
	closable:false,
	href : contextPath+"/index/listCompanyPolicyUrl"
});

$('#tabdiv').tabs('add',{
	title: '公司要闻',
	selected: false,
	closable:false,
	href : contextPath+"/index/listCompanyNewsUrl"
});

$('#tabdiv').tabs('add',{
	title: '产品报告',
	selected: false,
	closable:false,
	href : contextPath+"/index/listProfessionNewsUrl"
});

$('#tabdiv').tabs('add',{
	title: '通讯录',
	selected: false,
	closable:false,
	href : contextPath+"/index/listCompanyRosterUrl"
});

$('#tabdiv').tabs('add',{
	title: '自我介绍',
	selected: false,
	closable:false,
	href : contextPath+"/index/listSelfIntroductionUrl"
}); */
 }

function createSubMenuHtml(pId,subMenuInfo,i,all,count){
	if(count==null||count==undefined){
		count = 0;
	}else{
		count++;
	}
	var subMenuHtml = "<div style='display: none;' id = 'submenuDiv"+i+"'><ul class='submenu'>"; 
	var subMenuList = subMenuInfo[pId];
	if(subMenuList == null ||subMenuList== undefined || subMenuList == "" ){
		return;
	}
	for(var i=0;i<subMenuList.length;i++){
		var subMenu = subMenuList[i];
		var id = subMenu.id;
		if(subMenuInfo[id]==null||subMenuInfo[id]==undefined){
			var liStyle = "";
			if(count==0){
				liStyle = "style='font-weight:bold'";
			}else{
				liStyle = "style='margin-left:4px'";
			} 
			subMenuHtml += "<li class='submenu_item' "+liStyle+" id = sub_menu_item_"+id+" ><a  href='#' id = sub_menu_link_"
						+ id
						+ " onclick=meunSelectOne('"
						+ subMenu.name + "','"
						+ subMenu.url + "',"
						+ id + "," + id + ","
						+ all + ")>"
						+ subMenu.name
						+ "</a></li>";
		}else{
			subMenuHtml += "<li class='submenu_item' style='font-weight:bold' id = sub_menu_item_"+id+" ><a  href='#' id = sub_menu_link_"
						+ id
						+ " onclick=meunSelectOne('"
						+ subMenu.name + "','"
						+ subMenu.url + "',"
						+ id + "," + id + ","
						+ all + ")>"
						+ subMenu.name
						+ "</a>";
			subMenuHtml += createSubMenuHtml(id,subMenuInfo,id,all,count);
			subMenuHtml += "</li>";
		}
	}
	subMenuHtml += "</ul></div>";
	return subMenuHtml;
}


function deletetab(title) {

	if ($('#work').tabs('exists', title)) {
		$('#work').tabs('close', title);
	}
}

function addtab(title, href) {
	if (href == 'cache/reload') {
		$.post(href);
		return;
	}
	if ($('#work').tabs('exists', title)) {
		$('#work').tabs('select', title);
		var tab = $('#work').tabs('getSelected');
		$('#work').tabs('close', title);
		addIframeTab(title, href);
	} else {
		addIframeTab(title, href);
	}
}

function addsubtab(title, href) {
	if ($('#work').tabs('exists', title)) {
		$('#work').tabs('select', title);
		var tab = $('#work').tabs('getSelected');
		$('#work').tabs('close', title);
		addIframeTab(title, href);
	} else {
		addIframeTab(title, href);
	}
}

function addIframeTab(title, href) {
	/* 	$('#work').tabs('addIframeTab', {tab:{
			title : title,
			closable : true
		},
		iframe:{src:href}}); */
	$('#work').tabs('add', {
		title : title,
		href : href,
		closable : true
	});
}

//名称 链接 节点序号 总菜单数
function meunSelect(name, url, id, all) {
	/* 20180121-liubeibei点击总菜单，子菜单链接颜色默认为黑色 */
	$("#submenuDiv"+id).find('a').css('color', '#666');
	//显示隐藏子菜单
	var obj_div = '#submenuDiv' + id;
	if ($(obj_div).length > 0) {
		var display = $(obj_div).css('display');
		if (display == "none") {
			$(obj_div).css('display', '');
			$('#menu_link_' + id).css('background','url(img/icon_01.png) 126px center no-repeat');
		} else {
			$(obj_div).css('display', 'none');
			$('#menu_link_' + id).css('background','url(img/icon_02.png) 130px center no-repeat');
		}
		for ( var i = 0; i < all; i++) {
			obj_div = '#submenuDiv' + i;
			if (i != id) {
				if ($(obj_div).length > 0) {
					$(obj_div).css('display', 'none');
					$('#menu_link_' + id).css('background','url(img/icon_01.png) 130px center no-repeat');
				}
			}
		}
	}

	//链接颜色改变
	var obj_a = '#menu_link_' + id;
	if ($(obj_a).length > 0) {
		$(obj_a).css('color', '');
	}
	for ( var i = 0; i < all; i++) {
		if (i != id) {
			obj_a = '#menu_link_' + i;
			if ($(obj_a).length > 0) {
				$(obj_a).css('color', '#666');
			}
		}
		var sub_obj_a = '#sub_menu_link_' + i;
		if ($(sub_obj_a).length > 0) {
			$(sub_obj_a).css('color', '#666');//将颜色#999换成了#666
		}
	}
	if (url != null && url != "" && url != "null") {
		addtab(name, url);
	}
}
//名称 链接 节点序号 父菜单序号 总菜单数
function meunSelectOne(name, url, id, all) {
	//显示隐藏子菜单
	var obj_div = '#submenuDiv' + id;
	if ($(obj_div).length > 0) {
		var display = $(obj_div).css('display');
	}
	if (display == "none") {
		$(obj_div).css('display', '');
		$('#sub_menu_link_' + id).css('background',
				'url(img/icon_01.png) 116px center no-repeat');
		/* alert(obj_div); */
	} else {
		$(obj_div).css('display', 'none');
		$('#sub_menu_link_' + id).css('background',
				'url(img/icon_02.png) 123px center no-repeat');
		
	}
	//链接颜色改变
	var sub_obj_a = '#sub_menu_link_' + id;
	if ($(sub_obj_a).length > 0) {
		/* 20180121-liubeibei 解决连续点击菜单时，链接颜色不更换问题 */
		$(sub_obj_a).parent().siblings().children('a').css('color', '#666');
		$(sub_obj_a).css('color', '#56aeeb');
		
	}
	var obj_a = '#menu_link_' + id;
	if ($(obj_a).length > 0) {
		/* alert(2222); */
		$(obj_a).css('color', '#2b8ed3');
	}
	for ( var i = 0; i < all; i++) {
		if (i != id) {
			obj_a = '#menu_link_' + i;
			if ($(obj_a).length > 0) {
				/* alert(obj_div); */
				$(obj_a).css('color', '#666');
				/* alert(obj_div); */
			}
		}
		
		var sub_obj_a = '#sub_menu_link_' + i;
		if ($(sub_obj_a).length > 0) {
			$(sub_obj_a).css('color', '#666');//将颜色#999换成了#666 
		}
	}
	if (url != null && url != "" && url != "null") {
		addtab(name, url);
	}
	$(obj_div).parent().siblings().find("div").hide();
	$(obj_div).parent().siblings().find("a").attr('style','background:url("img/icon_02.png") no-repeat scroll 123px center !important;');
} 


</script>
</head>
<!-- easyui-layout 可分上下左右中五部分，中间的是必须的，支持href，这样就可以不用iframe了 -->
<body class="easyui-layout" id="mainBody">
	<!-- 上-->
	<div data-options="region:'north',border:false" class="header">
		<div class="background">
			<img class="logo" src="images/logo_02.png">
			<div style="margin-right: 50px; text-align: right;">
				<div class="header_right">
					<div class="button_box">
		            	<div class="button">
		                	<img src="img/amend.png" />
		                    <a href="#" onclick="addtab('密码修改','user/modifyUserUrl')">修改密码</a>
		                </div>
		                <div class="button">
		                	<img src="img/exit.png" />
		                    <a href="#" onclick="addtab('重新登陆','welcome/reloadUrl')">注销登录</a>
		                </div>
		            </div>
		           <div>
			           <div class="button"  style="margin-left: 30px;">
			           		<p><span style="margin-left: 20px;">登录用户：${_user}&nbsp;&nbsp;工号：${_agentCode}</span></p>
			           </div>
		           </div>
	            </div>
				<!--  <span>登录区站：${_company} | 登录用户：${_user} | 工号：${_agentCode} |<a href="#"
					onclick=" addtab('密码修改', 'user/modifyUserUrl')">密码修改</a> | <a
					href="#" onclick=" addtab('重新登陆', 'welcome/reloadUrl')">注销</a>
				</span>
				-->
			</div>
		</div>
	</div>
	<!-- 左height: 100%; -->
	<div region="west" split="false" border="false" title=""
		style="width: 210px; border: 1px solid #d2e0e6; background: #f0f6fa;">
		<div class="personal_menu" id="menuDiv"
			style="height: 100%; overflow: auto"></div>
	</div>

	<!-- 中 -->
	<div region="center" border="false" split="false" >
		<div class="easyui-tabs" id='work' fit='true' border='false'
			plain='true'>
			<!-- <div id="tabdiv" class="easyui-tabs" title="首页信息"> -->
		</div>
</div>
			</div>
	</div>
	<!-- 下 -->
<div region="south" split="false" border="false" class="footer" style="height:44px;line-height:44px;padding-top:5px;">
			<p>地址：上海市江场三路56号8楼802室</p>
</div> 
	
	
</body>

</html>
