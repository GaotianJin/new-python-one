<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script type="text/javascript" language="javascript">
$(function(){ 
    $("body").append("<div id='main_bg'/>"); 
    $("#main_bg").append("<img src='../img/e-cis/index.jpg' id='bigpic'>"); 
    cover(); 
    $(window).resize(function(){ //浏览器窗口变化 
        cover(); 
    }); 
}); 
function cover(){ 
    var win_width = $(window).width(); 
    var win_height = $(window).height(); 
    $("#bigpic").attr({width:win_width,height:win_height}); 
} 
</script>
<style type="text/css">
body,h1,h2,h3,h4,h5,h6,hr,p,blockquote,dl,dd,ul,ol,pre,fieldset,legend,button,input,textarea,th,td,img,div{margin:0;padding:0;}
button,input,select,textarea{font:12px/1.5 Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;}
html{width:100%;height:100%;}
ul,ol{list-style:none;}
a{text-decoration:none;}
#right{position:absolute;width:100%;height:100%;z-index:1;}
</style>
<div id="right">



</div>