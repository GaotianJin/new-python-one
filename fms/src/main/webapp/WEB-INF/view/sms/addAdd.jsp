<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/cis/thirdparty/sms/addAddInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="新增个人通讯录" iconCls="icon-ok" collapsible="true">
<form id="addAddForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">姓名：</td>
<td width="200" align="left"><input id="name" name="name" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">手机号码：</td>
<td width="200" align="left"><input id="phone" name="phone" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">邮箱账号：</td>
<td width="200" align="left" style="border-right:none;"><input id="email" name="email" class="table_input" value=""></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">优先级：</td>
<td width="200" align="left"><input id="priority" name="priority" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">备注：</td>
<td width="200" align="left"><input id="note" name="note" class="table_input" value=""></td>
<td style="border-right:none;">&nbsp;</td>
<td style="border-right:none;">&nbsp;</td>
</tr>

</table>
<div><a href="#" onclick="addAdd()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增</a></div>
</div>
</form>
</div>
</div>	