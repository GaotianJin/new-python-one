<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/index/uploadIndexFilesInit.js"></script>

<input type="hidden" name="custBaseInfoId" id="edtiorComPolicyInfo_custBaseInfoId" value="${custBaseInfoId}">

<form id="uploadIndexFilesForm" method="post">
<div class="top_table">
				<div>
					<a href="#" id="comPolicy_linkbutton" onclick="uploadCompanyPolicyFile()"  
					class="easyui-linkbutton e-cis_button" iconCls="icon-reload">维护公司政策</a>
					<a href="#" id="comNews_linkbutton" onclick="uploadCompanyNewsFile()"  
					class="easyui-linkbutton e-cis_button" iconCls="icon-reload">维护公司要闻</a>
					<a href="#" id="proNews_linkbutton" onclick="professionUploadFile()"
					class="easyui-linkbutton e-cis_button" iconCls="icon-reload">维护产品报告</a>
				</div>
			</div>
</form>
