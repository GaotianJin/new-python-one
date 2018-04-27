<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/updateAgentInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div id="tabdiv">
	<form id="addAgentForm">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">工号</td>
						<td align="left"><input name="agentCode" id="agentCode" class="table_input"></td>
						<td class="table_text" align="right">姓名</td>
						<td align="left"><input name="agentName" id="agentName" class="table_input"></td>
						<td class="table_text" align="right">性别</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="sex" name="sex"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">出生日期</td>
						<td align="left"><span class="comboSpan"></span><input name="birthday" id="birthday" class="easyui-datebox table_input"></td>
						<td class="table_text" align="right">证件类型</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="idType" name="idType"></td>
						<td class="table_text" align="right">证件号码</td>
						<td align="left"><input name="idNo" id="idNo" class="table_input"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">民族</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="nationality" name="nationality"></td>
						<td class="table_text" align="right">籍贯</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="nativeplace" name="nativeplace"></td>
						<td class="table_text" align="right">户口所在地</td>
						<td align="left"><input name="rgtadress" id="rgtadress" class="table_input"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">政治面貌</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="political" name="political"/></td>
						<td class="table_text" align="right">学历</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="degree" name="degree"/></td>
						<td class="table_text" align="right">毕业院校</td>
						<td align="left"><input name="graduate" id="graduate" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">婚姻状况</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="maritalStatus" name="maritalStatus"/></td>
						<td class="table_text" align="right">生育状况</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="bearStatus" name="bearStatus"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="province" name="province"/></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="city" name="city"/></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="country" name="country"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right" >详细地址</td>
						<td colspan="3"><input name="street" id="street" class="table_input1"></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left" ><input name="zipCode" id="zipCode" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">家庭电话</td>
						<td align="left" ><input name="familyPhone" id="familyPhone" class="table_input"></td>
						<td class="table_text" align="right">手机</td>
						<td align="left"><input name="mobile" id="mobile" class="table_input"></td>
						<td class="table_text" align="right">E-mail</td>
						<td align="left"><input name="email" id="email" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">QQ号</td>
						<td align="left"><input name="qq" id="qq" class="table_input"></td>
						<td class="table_text" align="right">微信号</td>
						<td align="left"><input name="wechat" id="wechat" class="table_input"></td>
						<td class="table_text" align="right">用户登录帐号</td>
						<td align="left"><input name="userCode" id="userCode" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">在职状态</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="workUpdateState" name="workState"></td>
						<td class="table_text" align="right">入司日期</td>
						<td align="left"><span class="comboSpan"></span><input name="joinDate" id="joinDate" class="easyui-datebox table_input"></input></td>
						<td class="table_text" align="right">离职日期</td>
						<td align="left"><span class="comboSpan"></span><input name="leaveDate" id="leaveDate" class="easyui-datebox table_input"></input></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
				</div>
			</div>
		</div>
		</form>
	</div>
	<div id ="tabdiv2">	
		<form id="home">
		<div id="#" class="easyui-panel" title="家庭信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">家庭成员姓名</td>
						<td align="left"><input name="memberName" id="memberName" class="table_input"></td>
						<td class="table_text" align="right">与员工关系</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="relationToAgent" name="relationToAgent"/></td>
						<td class="table_text" align="right">年龄</td>
						<td align="left"><input name="age" id="age" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">职业</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="occupationCode" name="occupationCode"/></td>
						<td class="table_text" align="right">年收入(万元)</td>
						<td align="left"><input name="incomeValue" id="incomeValue" class="table_input"></td>
						<td class="table_text" align="right">手机</td>
						<td align="left"><input name="mobile" id="mobile" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">E-mail</td>
						<td align="left"><input name="email" id="email" class="table_input"></td>
						<td class="table_text" align="right">QQ号</td>
						<td align="left"><input name="qq" id="qq" class="table_input"></td>
						<td class="table_text" align="right">微信号</td>
						<td align="left"><input name="wechat" id="wechat" class="table_input"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-no">删除</a>
				</div>
			</div>
		</div>
		</form>
	</div>
		<div style="margin-top: 3px;" id="tabdiv2">
			<table id="agentHomeTable"></table>
		</div>
	<div id="tabdiv3">
		<form id="#">
		<div id="smsaccordion" class="easyui-panel"title="资格证信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">资格证类型</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="certificationType" name="certificationType"/></td>
						<td class="table_text" align="right">资格证代码</td>
						<td align="left"><input name="certificationCode" id="certificationCode" class="table_input"></input></td>
						<td></td>
						<td></td>
					</tr>
				</table>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-no">删除</a>
			</div>
		</div>
		</form>
	</div>
		<div style="margin-top: 3px;" id="tabdiv3">
			<table id="agentCertificationTable"></table>
		</div>
	<div id="tabdiv4">
		<form>
		<div id="#" class="easyui-panel" title="网点信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">所属机构</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="comId" name="comId"/></td>
						<td class="table_text" align="right">所属网点</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="buildingId" name="buildingId"></td>
						<td class="table_text" align="right">开始日期</td>
						<td align="left"><span class="comboSpan"></span><input  name="startDate" id="upBuildStartDate" class="easyui-datebox table_input"></input></td>
					</tr>
					<tr>
						<td class="table_text" align="right">结束日期</td>
						<td align="left"><span class="comboSpan"></span><input  name="endDate"  id = "upBuildEndDate" class="easyui-datebox table_input"></input></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-no">删除</a>
			</div>
		</div>
		</form>
	</div>
	<div style="margin-top: 3px;" id="tabdiv4">
			<table id="agentshopTable"></table>
		</div>
	<div id="tabdiv5">	
		<form>
		<div id="#" class="easyui-panel" title="业务部信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">所属机构</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="comId" name="comId"/></td>
						<td class="table_text" align="right">所属业务部</td>
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="departmentId" name="departmentId"/></td>
						<td class="table_text" align="right">开始日期</td>
						<td align="left"><span class="comboSpan"></span><input  name="startDate" id="upDepartStartDate" class="easyui-datebox table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">结束日期</td>
						<td align="left"><span class="comboSpan"></span><input  name="endDate"  id = "upDepartEndDate" class="easyui-datebox table_input"></input></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
					<a href="#" onclick="fun()" class="easyui-linkbutton e-cis_button" iconCls="icon-no">删除</a>
			</div>
		</div>
		</form>
	</div>
		<div style="margin-top: 3px;" id="tabdiv5">
			<table id="agentbusinessTable"></table>
		</div>