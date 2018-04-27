<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/user/setUserInit.js"></script>
<div class="easyui-layout" fit="true">
	<div region="west" split="true" border="false" style="width: 500%;">
		<div align="left">
			<table>
				<tr>
					<td>用户：${name}</td>
					<td><input type="hidden" id="userId" name="userId" value="${id}"></td>
				</tr>
			</table>
		</div>
		<div align="center">
			<ul id="treeUL" class="ztree"></ul>
		</div>
		<div align="right">
			<a href="#" onclick="addU2R()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
			<a href="#" onclick="backListUserPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</div>
	<div region="center" border="false">
		<div>
			<table>
				<tr>
					<td>已存在角色：</td>
				</tr>
			</table>
		</div>
		<div align="center">
			<ul id="treeUR" class="ztree"></ul>
		</div>
	</div>
</div>
