<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="../js/cis/thirdparty/sms/updateSmsInit.js"></script>
	
<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="更新服务器配置" iconCls="icon-ok" collapsible="true">
<form id="updateSmsForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">短信服务编码：</td>
<td width="200" align="left"><input id="smscode" name="smscode" class="table_input" value="${smscode}" readonly="readonly" ></td>
<td width="125" class="table_text" align="right">SMS账户： </td>
<td width="200" align="left"><input id="regname" name="regname" class="table_input" value="${regname}"></td>
<td width="125" class="table_text" align="right">SMS密码：</td>
<td width="200" align="left" style="border-right:none;"><input id="password" name="password" class="table_input" value="${password}"></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">编码方式：</td>
<td width="200" align="left"><input id="encoding" name="encoding" class="table_input" value="${encoding}"></td>
<td width="125" class="table_text" align="right">过滤字段：</td>
<td width="200" align="left"><input id="filfields" name="filfields" class="table_input" value="${filfields}"></td>
<td width="125" class="table_text" align="right">拆分字数：</td>
<td width="200" align="left" style="border-right:none;"><input id="breakupword" name="breakupword" class="table_input" value="${breakupword}"></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">二次发送间隔：</td>
<td width="200" align="left"><input id="secondsend" name="secondsend" class="table_input" value="${secondsend}"></td>
<td width="125" class="table_text" align="right">最多发送字数：</td>
<td width="200" align="left"><input id="sendmost" name="sendmost" class="table_input" value="${sendmost}"></td>
<td width="125" class="table_text" align="right">自检时间：</td>
<td width="200" align="left" style="border-right:none;"><input id="selfcheck" name="selfcheck" class="table_input" value="${selfcheck}"></td>
</tr>
</table>
<input type="hidden" id="id" name="id" value="${id}">
<div><a href="#" onclick="updateSms()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">修改</a></div>
</div>
</form>
</div>
</div>	 