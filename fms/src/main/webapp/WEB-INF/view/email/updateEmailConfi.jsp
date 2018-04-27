<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/cis/thirdparty/email/updateEmailInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="更新配置" iconCls="icon-ok" collapsible="true">
<form id="updateEmailForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">邮箱服务编码：</td>
<td width="200" align="left"><input id="emailcode" name="emailcode" class="table_input" value="${emailcode}" readonly="readonly"></td>
<td width="125" class="table_text" align="right">邮箱账户：</td>
<td width="200" align="left"><input id="regname" name="regname" class="table_input" value="${regname}"></td>
<td width="125" class="table_text" align="right">邮箱密码：</td>
<td width="200" align="left" style="border-right:none;"><input id="password" name="password" class="table_input" value="${password}"></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">编码方式：</td>
<td width="200" align="left"><input id="secondsend" name="secondsend" class="table_input" value="${secondsend}"></td>
<td width="125" class="table_text" align="right">二次发送间隔：</td>
<td width="200" align="left"><input id="filfields" name="filfields" class="table_input" value="${filfields}"></td>
<td width="125" class="table_text" align="right">最多发送次数：</td>
<td width="200" align="left" style="border-right:none;"><input id="sendmost" name="sendmost" class="table_input" value="${sendmost}"></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">自检时间：</td>
<td width="200" align="left"><input id="selfcheck" name="selfcheck" class="table_input" value="${selfcheck}"></td>
<td width="125" class="table_text" align="right">备注：</td>
<td width="200" align="left"><input id="note" name="note" class="table_input" value="${note}"></td>
<td width="125" style="border-right:none;">&nbsp;</td>
<td width="200" style="border-right:none;">&nbsp;</td>
</tr>
</table>
<input type="hidden" id="id" name="id" value="${id}">
<div><a href="#" onclick="updateEmail()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">修改</a></div>
</div>
</form>
</div>
</div>	