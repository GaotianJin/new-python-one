<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/cis/thirdparty/email/addEmailConfiInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="新增配置" iconCls="icon-ok" collapsible="true">
<form id="addForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">邮箱账户：</td>
<td width="200" align="left"><input id="regname" name="regname" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">邮箱密码：</td>
<td width="200" align="left"><input id="password" name="password" class="table_input"></td>
<td width="125" class="table_text" align="right">编码方式：</td>
<td width="200" align="left" style="border-right:none;"><input id="encoding" name="encoding" class="table_input" value=""></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">二次发送间隔：</td>
<td width="200" align="left"><input id="secondsend" name="secondsend" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">最多发送字数：</td>
<td width="200" align="left"><input id="sendmost" name="sendmost" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">自检时间：</td>
<td width="200" align="left" style="border-right:none;"><input id="selfcheck" name="selfcheck" class="table_input" value=""></td>
</tr>
<tr>
<td width="125" class="table_text" align="right">备注：</td>
<td width="200" align="left"><input id="note" name="note" class="table_input" value=""></td>
<td style="border-right:none;">&nbsp;</td>
<td style="border-right:none;">&nbsp;</td>
<td style="border-right:none;">&nbsp;</td>
<td style="border-right:none;">&nbsp;</td>
</tr>
</table>
<div><a href="#" onclick="addEmail()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增配置</a></div>
</div>
</form>
</div>
</div>	 