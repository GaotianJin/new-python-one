<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="../js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../js/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/cis/quartz/updateSchedulerInit.js"></script>
<div class="easyui-layout" fit="true">
	<div region="west" split="true" border="false" style="width: 550%;">
		<div>
			<table width="100%">
				<tr>
					<td>调度名称：<input id="privilegename" name="privilegename"
						style="width: 100" value="${name}"></td>
					<td>映射路径：<input id="url" name="url" style="width: 150"
						value="${url}"></td>
					<input type="hidden" id="oldname" name="name" value="${name}">
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统：<input id="system" name="system" style="width: 150"
						value="${system}" placeholder="不填默认本系统调度"></td>
				</tr>
				<tr>
					<td><a href="#" onclick="saveUpdate()"
						class="easyui-linkbutton" iconCls="icon-add">修改</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div region="center" border="false">
		<div style="float:center;width:200px;margin-left:8%;">
			<ul id="upschedulertree" class="ztree"></ul>
		</div>
	</div>
</div>
