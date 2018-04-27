<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="../js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../js/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/cis/role/setSystemInit.js"></script>
 <div class="easyui-layout" fit="true"> 
 	<div region="west" split="true"  border="false" style="width: 500%; ">
		<div align="left">
			<table>
				<tr>
					<td>角色：${name}</td>
					<td><input type="hidden" id="roleid" name="roleid" value="${id}"></td>
				</tr>
			</table>
		</div>
		<div align="center">
			<ul id="treeUL" class="ztree"></ul>
		</div>
		<div align="right">
			<a href="#" onclick="addR2S()"
						class="easyui-linkbutton" iconCls="icon-save">保存</a>
		</div>
	</div>
	<div region="center" border="false"> 
		<div>
			<table>
				<tr>
					<td>已有系统：</td>
				</tr>
			</table>
		</div>
		<div align="center">
			<ul id="treeUR" class="ztree"></ul>
		</div>
	</div> 
</div> 
 			 