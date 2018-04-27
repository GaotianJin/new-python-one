<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/selectAgentInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div id="tabdiv">
	<form id="addAgentForm">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">财富顾问代码</td>
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
						<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="workState" name="workState"></td>
						<td class="table_text" align="right">入司日期</td>
						<td align="left"><span class="comboSpan"></span><input name="joinDate" id="joinDate" class="easyui-datebox table_input"></input></td>
						<td class="table_text" align="right">离职日期</td>
						<td align="left"><span class="comboSpan"></span><input name="leaveDate" id="leaveDate" class="easyui-datebox table_input"></input></td>
					</tr>
				</table>
			</div>
		</div>
		</form>
	</div>
<!-- 家庭信息 -->
		<div style="margin-top: 3px;" id="tabdiv2">
			<table id="agentHomeTable"></table>
		</div>
<!-- 资格证信息 -->
		<div style="margin-top: 3px;" id="tabdiv3">
			<table id="agentCertificationTable"></table>
		</div>
<!-- 职级信息 -->
	<div style="margin-top: 3px;" id="tabdiv4">
			<table id="agentjobTable"></table>
		</div>
<!-- 网点信息 -->
		<div style="margin-top: 3px;" id="tabdiv5">
			<table id="agentshopTable"></table>
		</div>
<!-- 业务部信息 -->
		<div style="margin-top: 3px;" id="tabdiv6">
			<table id="agentbusinessTable"></table>
		</div>