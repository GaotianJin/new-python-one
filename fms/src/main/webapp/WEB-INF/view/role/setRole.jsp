<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cis/role/setRoleInit.js"></script>
 <div class="easyui-layout" fit="true"> 
 	<div id="scrolldiv" region="west" split="true"  border="false" style="width: 500%;overflow:scroll; ">
		<div class="hb"  align="left">
			<a href="#" onclick="addR2P()"
						class="easyui-linkbutton" iconCls="icon-tick">保存</a>
		<a href="#" onclick="backListRolePage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
						
		</div>
		
		<div align="center">
		<div align="center" >
			<table>
				<tr>
					<td>角色：${name}</td>
					<td><input type="hidden" id="roleid" name="roleid" value="${id}"></td>
				</tr>
			</table>
		</div>
			<ul id="treeRL" class="ztree"></ul>
		</div>
	</div>
	<div region="center" border="false" style="width: 500%;overflow:scroll; "> 
		<div align="center">
			<table>
				<tr>
					<td>已存在菜单：</td>
				</tr>
			</table>
		</div>
		<div align="center">
			<ul id="treeRR" class="ztree"></ul>
		</div>
		
		
	</div> 
</div> 
 			 