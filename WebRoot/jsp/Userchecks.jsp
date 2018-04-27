<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<!--
	    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
		-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>


<style type="text/css">
input {
	width: 80px;
}
</style>
<script type="text/javascript">
			var url;
			function resultSearchUser(){
				$('#UserName').textbox("clear");
				$('#checkInTime').combo("setValue", "").combo("setText", "");
				$('#CheckOutTime').combo("setValue", "").combo("setText", "");
				$("#checkState").combobox("setText","").combobox("setValue", "");
				$('#dg').datagrid('load',{});
			}
			function searchUser(){
				$('#dg').datagrid('load',{
					UserName:$('#UserName').val(),
					checkInTime:$('#checkInTime').datebox('getValue'),
					CheckOutTime:$('#CheckOutTime').datebox('getValue'),
					checkState:$("#checkState").combobox('getValue'),
				});
			}
			//绑定数据到tree，防止undfind
			$(function(){
				$("#treegrid").tree({
					formatter:function(tree){
						return tree.menu_name;
					}
				});
			});
			//日期格式转换
			function  formatterDate(value){
				if(value==null){
					return null;
				}else{
					var seperator1 = "-";
    				var seperator2 = ":";
					var date = new Date(value);
					var y=date.getFullYear();
					var m=date.getMonth()+1;
					var d=date.getDate();
					var h=date.getHours();
					var M=date.getMinutes();
					var s=date.getSeconds();
					return y+seperator1+fmt(m)+seperator1+fmt(d)+" "+fmt(h)+seperator2+fmt(M)+seperator2+fmt(s);
					}
				}
			function fmt(number){
				if (number >= 0 && number <= 9) {
	        	return "0" + number;
	    		}else{
	    		return number
    			}
			}
		</script>
</head>
<body>
	<table id="dg" title="员工签到信息" class="easyui-datagrid"
		style="width:auto;height:auto" fitColumns="true" rownumbers="true"
		singleSelect="true" pagination="true" pageSize="20" fit="true"
		url="/EighthCRMItem/getUserChecksAll.eighth" toolbar="#tb">
		<thead>
			<tr>
				<th data-options="field:'userId'" width="5" hidden="true">用户id</th>
				<th data-options="field:'userName'" width="10">用户名</th>
				<th data-options="field:'checkState'" width="10">签到状态</th>
				<th data-options="field:'checkInTime'" width="10" formatter="formatterDate">签到时间</th>
				<th data-options="field:'checkOutTime'" width="10" formatter="formatterDate">签退时间</th>
				</tr>
		</thead>
	</table>
	<div id="tb">
		<div style="margin-top: 20px">
			<form id="search" method="post">
				&nbsp;用户名:&nbsp;<input class="easyui-textbox" name="UserName"
					id="UserName" size="10" /> 
				&nbsp;签到时间:&nbsp;<input class="easyui-datebox" name="checkInTime"
					id="checkInTime" style="width: 100px;" /> 
				&nbsp;-&nbsp;<input class="easyui-datebox" name="CheckOutTime"
					id="CheckOutTime" style="width: 100px;" /> 
				&nbsp;签到状态:&nbsp;<select class="easyui-combobox" panelHeight="auto" id="checkState" name="checkState">
					<option value="">--请选择--</option>
					<option value="已签到">已签到</option>
					<option value="已签退">已签退</option>
					<option value="迟到">迟到</option>
					<option value="早退">早退</option>
				</select> 
				<a class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="searchUser()">搜索</a>
				<a class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="resultSearchUser()">重置</a>
			</form>
		</div>
		<br />
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:250px;padding:20px 10px 0;" closed="true"
		buttons="#dlgbuttons">
		<form id="fm" method="post">
			<table>
				<tr hidden=“true”>
					<td>角色编码：</td>
					<td><input type="text" name="userId" id="UserId"
						class="easyui-validatebox">&nbsp;&nbsp;</td>
					<td style="display: none"></td>
				</tr>
				<tr>
					<td>角色名：</td>
					<td><input type="text" name="userName" id="UserName"
						class="easyui-validatebox" required="true" onblur="isuName(this.value)">&nbsp;&nbsp;</td>
					<td style="color: red;display: none" id="UserNameexec">用户名长度为2-8个汉字</td>
				</tr>
				<tr id="userpwd">
					<td>密码：</td>
					<td><input type="password" name="userPASSWORD" id="UserPASSWORD"
						class="easyui-validatebox" onblur="isPWD(this.value)">&nbsp;&nbsp;</td>
					<td style="color: red;display: none" id="UserPASSWORDexec">密码为6-15位数字和字母组成</td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="userEmail" id="UserEmail"
						class="easyui-validatebox" required="true" validType="email" >&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>手机号码：</td>
					<td><input type="text" name="userPhone" id="UserPhone"
						class="easyui-validatebox" required="true" onblur="isMobil(this.value)">&nbsp;&nbsp;</td>
					<td style="color: red;display: none" id="userPhoneexec">手机号为1开头的11位数字</td><!-- ^1[0-9]{10}$ -->
				</tr>
			</table>
		</form>
	</div>
	<div id="setrole_window" class="easyui-window"
		style="width:600px;height:450px;padding:20px 10px 0;" closed="true">
		<div id="szrole" class="easyui-layout" fit="true">
			<div data-options="region:'west'" style="width:240px">
				<table id="ksroleDg" class="easyui-datagrid" title="系统所有角色" fitColumns="true"
					rownumbers="true" singleSelect="true" fit="true">
					<thead>
						<tr>
							<th data-options="field:'roleName'" width="10">角色名</th>
						</tr>
					</thead>
				</table>
			</div>
			<div data-options="region:'center'" style="width:40px">
				<div style="margin-top:100px;margin-left:2px" align="center">
					<a class="easyui-linkbutton" onclick="addRoleInfo()">>></a>
				</div>
				<div style="margin-top:140px;margin-left:2px" align="center">
					<a class="easyui-linkbutton" onclick="delRoleInfo()"><<</a>
				</div>
			</div>
			<div data-options="region:'east'" style="width:240px">
				<table id="ysroleDg" class="easyui-datagrid" title="当前用户已设角色"
					fitColumns="true" rownumbers="true" singleSelect="true" fit="true">
					<thead>
						<tr>
							<th data-options="field:'roleName'" width="10">角色名</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<div id="dlgbuttons">
		<a href="javascript:saveUser()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeUserDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>