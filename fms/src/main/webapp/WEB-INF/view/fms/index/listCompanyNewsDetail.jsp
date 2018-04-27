<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/listCompanyNewsDetailInit.js"></script>

<input type="hidden" name="companyNewsInfoId" id="detailUploadFile_companyNewsInfoId" value="${companyNewsInfoId}">

<style>
html *{-moz-user-select: none;}
</style>
<script>
document.onselectstart = function(){return false;}
document.oncontextmenu=function(){return false} 
document.ondragstart=function(){return false} 
</script>
<div id="tabdiv" class="outerPanel" style="width:980px;margin:0 auto;">
				<!-- <div id="newsDetailUploadFile_title" style="height:50px;font-size:30px;font-family:黑体;font-weight:bold;text-align:center;"  ></div> -->
				<div id="newsDetailUploadFile_content" ></div>
</div> 
