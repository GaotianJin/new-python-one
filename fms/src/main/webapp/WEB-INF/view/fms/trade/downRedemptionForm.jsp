<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/downRedemptionFormInit.js"></script>
<input type="hidden" name="redemptionInfoId" id="downRedemptionForm_redemptionInfoId" value="${redemptionInfoId}">
<div id="tabdiv" fit="true">
	<table id="redemptionDownloadTable"></table>
</div>
