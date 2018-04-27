<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/cis/thirdparty/sms/updateAddInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="更新个人通讯录" iconCls="icon-ok" collapsible="true">
<form id="updateAddForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">姓名：</td>
<td width="200" align="left"><input id="name" name="name" class="table_input" value="${name}"></td>
<td width="125" class="table_text" align="right">手机号码：</td>
<td width="200" align="left"><input id="phone" name="phone" class="table_input" value="${phone}"></td>
<td width="125" class="table_text" align="right">邮箱账号：</td>
<td width="200" align="left" style="border-right:none;"><input id="email" name="email" class="table_input" value="${email}"></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">优先级：</td>
<td width="200" align="left"><input id="priority" name="priority" class="table_input" value="${priority}"></td>
<td width="125" class="table_text" align="right">备注：</td>
<td width="200" align="left"><input id="note" name="note" class="table_input" value="${note}"></td>
<td style="border-right:none;">&nbsp;</td>
<td style="border-right:none;">&nbsp;</td>
</tr>
</table>
<input type="hidden" id="id" name="id" value="${id}">
<div><a href="#" onclick="updateAdd()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">修改</a></div>
</div>
</form>
</div>
</div>