<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/menu/updateMenuInit.js"></script>
<div class="easyui-layout" fit="true">
	<div region="west" split="true" border="false" style="width: 550%;">
		<div>
		
<div style="margin-top:20px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="50" align="right">菜单名称</td>
<td width="200" align="left"><input id="privilegename" name="privilegename"  value="${name}"
class="table_input easyui-validatebox" data-options="required:true"></td>
</tr>
<tr>
<td width="50" align="right">映射路径</td>
<td width="200" align="left"><input id="url" name="url" class="table_input" value="${url}"></td>
<input type="hidden" id="oldname" name="name" value="${name}">
</tr>
<tr>
<td width="50" align="right">系统</td>
<td width="200" align="left"><input id="system" name="system" class="table_input" value="${system}" placeholder="不填默认本系统菜单"></td>
</tr>
</table>
<div>
<a href="#" onclick="saveUpdate()" class="easyui-linkbutton" iconCls="icon-tick" style="margin:10px 0 0 40px;">提交</a>
<a href="#" onclick="backListMenuPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
</div>
</div>
		</div>
	</div>
	<div region="center" border="false">
		<div style="float:center;width:200px;margin-left:8%;">
			<ul id="upmenutree" class="ztree"></ul>
		</div>
	</div>
</div>
