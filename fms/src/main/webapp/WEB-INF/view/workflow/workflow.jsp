<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>工作流设计器</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<script type="text/javascript" src="../js/ccui/scripts/lib/excanvas.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/lib/json2.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/lib/jquery.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/lib/colorpicker.js"></script>
		<script type="text/javascript" src="../js/bootstrap/js/bootstrap.min.js"></script>		
		
		<script type="text/javascript" src="../js/jquery-ui/ui/jquery-ui.js"></script>
		
		<script type="text/javascript" src="../js/ccui/scripts/ccui.widget.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/ccui.toolbar.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/ccui.application.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/ccui.menu.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/ccui.palette.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/ccui.fieldEditor.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/ccui.propertyTable.js"></script>
		<script type="text/javascript" src="../js/ccui/scripts/ccui.workflow.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.workflow.css"></link>
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.application.css"></link>
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.palette.css"></link>
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.toolbar.css"></link>
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.menu.css"></link>
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.layout.css"></link>
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.tree-new.css"></link>
		<link rel="stylesheet" type="text/css" href="../js/ccui/styles/ccui/ccui.propertyTable.css"></link>
		
    <link rel="stylesheet" href="../js/jquery-ui-bootstrap/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../js/jquery-ui-bootstrap/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="../js/jquery-ui-bootstrap/assets/css/docs.css">
    <link rel="stylesheet" href="../js/jquery-ui-bootstrap/assets/js/google-code-prettify/prettify.css">
    
		<link rel="stylesheet" type="text/css" href="../js/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.9.2.custom.css" />

		<style>
			body{
				font-size:10pt;
				font-family:宋体;
			}
		</style>
		<script>
			$(function(){
				var type = "${type}";
				if(type=="add"){
					var oHead = document.getElementsByTagName('HEAD').item(0); 
				    var oScript= document.createElement("script"); 
				    oScript.type = "text/javascript"; 
				    oScript.src="../js/ccui/workflow.js"; 
				    oHead.appendChild( oScript);
				}
				if(type=="update"){
					$("#processid").attr('value',"${id}");
					$("#processname").attr('value',"${name}");
					$.post("getHtml?id="+"${id}", function(data) {
						$("#editor-workflow").append(data.mes);
						var oHead = document.getElementsByTagName('HEAD').item(0); 
					    var oScript= document.createElement("script"); 
					    oScript.type = "text/javascript"; 
					    oScript.src="../js/ccui/workflow.js"; 
					    oHead.appendChild( oScript);
					});
				}
				
				
			})
		</script>
	</head>
	  
	<body class="flora" style="padding:0px;margin:0px;overflow:hidden;">
  		<div id="application-layout">
	  		<div class="ccui-cover-split"></div>
	    	<div class="pane north">
	    	  
	  			<div id="application-toolbar">
	  			<button id="undo" command="undo" class="btn toolbarItem undo disabled" ><i class="icon-chevron-left"></i> 撤销</button>
	  			<button id="redo" command="redo" class="btn toolbarItem redo disabled" ><i class="icon-chevron-right"></i> 重做</button>
	  			<button id="save" action="save" class="btn toolbarItem save disabled" ><i class="icon-ok"></i> 保存</button>
	  			<button id="palette" action="showPalette" class="btn toolbarItem new" ><i class="icon-picture"></i> 显示画板</button>
	  			<button id="help" action="help" class="btn toolbarItem help" ><i class="icon-info-sign"></i> 注意</button>
		  		</div>
	  		</div>
	  		<div class="pane west">
	  			<div id="tree-workflow-element">
			    	<ul>
				  		<li id="root_workspace" class="treeNode root"><span><a>工作区间</a></span>
				  			<ul>
				  				<li class="treeNode expandable expanded" id="node"><span><a href="#">节点</a></span></li>
				  				<li class="treeNode expandable expanded" id="transition"><span><a href="#">连接</a></span></li>
				  			</ul>
				  		</li>
				  	</ul>
	    		</div>
	  		</div>
	  		<div class="pane center" >
	  			<div id="editor-workflow" class="palette-drop">
	  				<div class="bg"></div>
				  	<div class="select-dragging"></div>
				  
					
	  			</div>
	  		</div>
	  		<div class="pane east">
	  			<div id="application-propertyTable">
	  				<ul>
	  					<li class="propertyGroup expanded">属性
	  						<ul>
		  						<li editor="input" id="text" class="property">名称</li>
	  						</ul>
	  					</li>
	  					<li class="propertyGroup">节点属性
	  						<ul>
		  						<li id="id" class="property">id</li>
		  						<li id="nodeType" class="property">节点类型</li>
		  						<li editor="input" id="top" class="property">上边距</li>
		  						<li editor="input" id="left" class="property">左边距</li>
	  						</ul>
	  					</li>
	  				</ul>
	  			</div>
	  		</div>
	  		<div id="dialog-form" title="是否保存" >
				<div id="message"  style="display: none">
     				<p>
         				<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
         				请输入名称</p>
 				</div>
				<form>
				<fieldset>
					<label for="name">Name</label>
					<input type="text" name="name" id="processname" class="text ui-widget-content ui-corner-all" />
					
				</fieldset>
				
				</form>
			</div>
			<input type="hidden" id="processid" value="" />
  		</div>
  		
  		
  		<div id="dialog-help">
  			连线：点击节点，按住shift，再点击其他节点，则增加两点间的连线。
  			</br>
  			修改节点属性：双击节点，或者单击属性编辑器中的属性值，都可以修改属性。
  		</div>
  		
  		<div id="application-palette">
  			<div class="dragHandle move"><span class="move">画板</span></div>
  			<ul>
  				<li class=""><span><a></a></span>
  					<ul>
  						<li id="Select" class="paletteItem Select toggle"><span><a>选择</a></span></li>
  						<li id="Marquee" class="paletteItem Marquee toggle"><span><a>框选</a></span></li>
  						<li id="transition" class="paletteItem transition toggle"><span><a>转换</a></span></li>
  						<li id="start"  class="paletteItem start"><span><a>开始</a></span></li>
  						<li id="state" class="paletteItem state"><span><a>状态</a></span></li>
  						<li id="task" class="paletteItem task"><span><a>任务</a></span></li>
  						<li id="decision" class="paletteItem decision"><span><a>判断</a></span></li>
  						<li id="fork" class="paletteItem fork"><span><a>分支</a></span></li>
  						<li id="join" class="paletteItem join"><span><a>合并</a></span></li>
  						<li id="end" class="paletteItem end"><span><a>结束</a></span></li>
  					</ul>
  				</li>
  			</ul>
  		</div>
	</body>
</html>