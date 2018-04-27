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
<script type="text/javascript" src="/EighthCRMItem/js/myjs/easyUIvalidate.js"></script>


<style type="text/css">
input {
	width: 80px;
}
</style>
<script type="text/javascript">
			var url;
			function resultSearchUser(){
				s_Username:$('#s_Username').val("");
				s_Useremail:$('#s_Useremail').val("");
				s_Userphone:$('#s_Userphone').val("");
				$('#dg').datagrid('load',{});
			}
			function searchUser(){
				$('#dg').datagrid('load',{
					s_Username:$('#s_Username').val(),
					s_Useremail:$('#s_Useremail').val(),
					s_Userphone:$('#s_Userphone').val(),
				});	
			}
			//删除用户
			function deleteUser(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length==0){
					$.messager.alert("系统提示","请选择要删除的数据！");
					return;
				}
				var row=selectedRows[0];
				$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
					if(r){
						$.post("/EighthCRMItem/delUsers.eighth",{id:row.userId},function(result){
							if(result.success){
								$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNum+"</font>条数据!");
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示",result.errorMsg);
								return;
							}
						},"json");
					}
				});
			}
			//打开添加窗口
			function openUserAddDialog(){
				$("#fm").form("clear");
				$("#userpwd").show(); 
				$("#UserPASSWORD").attr("data-options","required:true");
				//$("#UserPASSWORD").attr("required",true);
				$('#dlg').dialog('open').dialog("setTitle","添加用户");
				url=("/EighthCRMItem/addUsers.eighth");
			}
			//打开修改窗口
			function openUserModifyDialog(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length!=1){
					$.messager.alert("系统提示","请选择一条要编辑的数据！");
					return;
				}
				var row=selectedRows[0];
				$("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
				$("#UserName").val(row.userName);
				$("#UserEmail").val(row.userEmail);
				$("#UserPhone").val(row.userPhone);
				$("#UserId").val(row.userId);
				$("#UserPASSWORD").val(row.userPASSWORD);
				$("#UserPASSWORD").attr("required",false);
				$("#userpwd").hide(); 
				url="/EighthCRMItem/modUsers.eighth";
			}
			//关闭按钮
			function closeUserDialog(){
				$('#dlg').dialog("close");
				$('#treedlg').dialog("close");
			}
			//保存按钮
			function saveUser(){
				if($("#fm").form("validate")) {
					$.post(url,$("#fm").serialize(),function(result){
						if(result.success){
							$.messager.alert("系统提示",result.message);
							$("#dlg").dialog("close");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示",result.message);
							return;
						}
					});
				}
			}
			//重置密码
			function ResetPwd(index){
				var rows=$("#dg").datagrid('getRows');
				var row=rows[index];
				//alert(row.userId);
				$.post("/EighthCRMItem/ResetUsersPwd.eighth",({id:row.userId}),function(result){
					if(result=="czmmyesdl"){
						$.post('/EighthCRMItem/clearSession.eighth', function(ress) {
							$.messager.confirm("系统提示","重置密码成功,请重新登录！",function(res){
								window.open('/EighthCRMItem/index.jsp','_parent');
							});
						});
					}else if(result=="czmmnodl"){
						$.messager.alert("系统提示","重置密码成功！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","重置密码失败！");
						return;
					}
				});
			}
			function formatterResetPwd(value,row,index){
				return "<a style='cursor:pointer;' onclick='ResetPwd("+index+")'>重置密码</a>";
			}
			//锁定用户
			function LockUser(index){
				var rows=$("#dg").datagrid('getRows');
				var row=rows[index];
				//alert(row.userId);
				$.post("/EighthCRMItem/LockUsers.eighth",({id:row.userId}),function(result){
					if(result){
							$.messager.alert("系统提示","锁定成功");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示","锁定失败");
							return;
						}
				});
			}
			//解锁用户
			function Deblock(index){
				var rows=$("#dg").datagrid('getRows');
				var row=rows[index];
				//alert(row.userId);
				$.post("/EighthCRMItem/DeLockUsers.eighth",({id:row.userId}),function(result){
					if(result){
							$.messager.alert("系统提示","解锁成功");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示","解锁失败");
							return;
						}
				});
			}
			function formatterLock(value,row,index){
				return "<a style='cursor:pointer;' onclick='LockUser("+index+")'>锁定用户</a> <a style='cursor:pointer;' onclick='Deblock("+index+")'>解锁用户</a>";
			}
			//打开设置角色窗口
			function opentreedlg(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length!=1){
					$.messager.alert("系统提示","请选择一条要编辑的数据！");
					return;
				}
				var row=selectedRows[0];
				$('#ysroleDg').datagrid({
					url:"/EighthCRMItem/selectUserRoles.eighth?id="+row.userId
				});
				$('#ksroleDg').datagrid({
					url:"/EighthCRMItem/showAllRole.eighth"
				});
				$("#setrole_window").window("open").window("setTitle","设置"+row.userName+"的角色信息");
			}
			//设置角色
			function addRoleInfo(){
				var selectedRows1=$("#dg").datagrid('getSelections');
				var selectedRows2=$('#ksroleDg').datagrid('getSelections');
				if(selectedRows2.length!=1){
					$.messager.alert("系统提示","请选择要设置的角色名称！");
					return;
				}
				var row1=selectedRows1[0];
				var row2=selectedRows2[0];
				$.post("/EighthCRMItem/insertUserRoles.eighth",({userId:row1.userId,roleId:row2.roleId}),function(result){
					if(result){
							$("#ysroleDg").datagrid("reload");
						}else{
							$.messager.alert("系统提示","错误：角色重复！");
							return;
						}
				});
			}
			//取消角色
			function delRoleInfo(){
				var selectedRows1=$("#dg").datagrid('getSelections');
				var selectedRows2=$('#ysroleDg').datagrid('getSelections');
				if(selectedRows2.length!=1){
					$.messager.alert("系统提示","请选择要取消的角色名称！");
					return;
				}
				var row1=selectedRows1[0];
				var row2=selectedRows2[0];
				$.post("/EighthCRMItem/delUserRoles.eighth",({userId:row1.userId,roleId:row2.roleId}),function(result){
					if(result){
						$("#ysroleDg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","该用户有正在管理的学生，禁止删除角色！");
						return;
					}
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
	    		return number;
    			}
			}
		</script>
		
		<style type="text/css">
			#fm input{
				width: 150px;
			}
			
			#fm table{
				margin: 10px 0px 0px 60px;
			}
		</style>
</head>
<body>
	<table id="dg" title="用户显示" class="easyui-datagrid"
		style="width:auto;height:auto" fitColumns="true" rownumbers="true"
		singleSelect="true" pagination="true" pageSize="20" fit="true"
		url="/EighthCRMItem/ShowUsers.eighth" toolbar="#tb">
		<thead>
			<tr>
				<th data-options="field:'userId'" width="5" hidden="true">用户编号</th>
				<th data-options="field:'userName'" width="10">用户名</th>
				<th data-options="field:'userEmail'" width="10">邮箱</th>
				<th data-options="field:'userPhone'" width="10">手机号</th>
				<th data-options="field:'userIsLockout'" width="10">是否锁定</th>
				<th data-options="field:'userCreateTime'" width="10" formatter="formatterDate">账户创建时间</th>
				<th data-options="field:'userLastLoginTime'" width="10" formatter="formatterDate">最后登录时间</th>
				<th data-options="field:'xx',formatter:formatterResetPwd" width="10">操作</th>
				<th data-options="field:'xxx',formatter:formatterLock" width="10">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:openUserAddDialog()" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" plain="true">添加</a> <a
				href="javascript:openUserModifyDialog()" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'" plain="true">修改</a> <a
				href="javascript:deleteUser()" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'" plain="true">删除</a> <a
				href="javascript:opentreedlg()" class="easyui-linkbutton"
				data-options="iconCls:'icon-man'" plain="true">设置角色</a>
		</div>
		<div>
			<form id="search" method="post">
				&nbsp;用户名称:&nbsp;<input type="text" name="s_Username"
					id="s_Username" size="10" /> 
				&nbsp;Email:&nbsp;<input type="text" name="s_Useremail"
					id="s_Useremail" size="10" /> 
				&nbsp;手机号:&nbsp;<input type="text" name="s_Userphone"
					id="s_Userphone" size="10" /> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchUser()">搜索</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="resultSearchUser()">重置</a>
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
					<td>用户ID：</td>
					<td><input type="text" name="userId" id="UserId"
						class="easyui-validatebox">&nbsp;&nbsp;</td>
					<td style="display: none"></td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="userName" id="UserName"
						class="easyui-validatebox" data-options="required:true,validType:'uname'">&nbsp;&nbsp;</td>
				</tr>
				<tr id="userpwd">
					<td>密码：</td>
					<td><input type="password" name="userPASSWORD" id="UserPASSWORD"
						class="easyui-validatebox" data-options="required:true,validType:'upwd'">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="userEmail" id="UserEmail"
						class="easyui-validatebox" data-options="required:true,validType:'email'" >&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>手机号码：</td>
					<td><input type="text" name="userPhone" id="UserPhone"
						class="easyui-validatebox" data-options="required:true,validType:'mobile'" >&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="setrole_window" class="easyui-window"
		style="width:800px;height:450px;padding:20px 10px 0;" closed="true">
		<div id="szrole" class="easyui-layout" fit="true">
			<div data-options="region:'west'" style="width:340px">
				<table id="ksroleDg" class="easyui-datagrid" title="系统所有角色" fitColumns="true"
				pagination="true" pageSize="20"
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
			<div data-options="region:'east'" style="width:340px">
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