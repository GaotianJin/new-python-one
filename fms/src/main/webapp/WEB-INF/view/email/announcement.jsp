<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/cis/thirdparty/email/announcementInit.js"></script>
<script type="text/javascript"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="短信公告" iconCls="icon-ok" collapsible="true">
<form id="smsForm">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">手机号码：</td>
<td width="900" align="left"><select id="phone" name="phone" onblur="queryPhone();" onchange="queryPhone()" style="width: 800px;"></select></br>　多人发送用“；”隔开</td>
<td rowspan="4" style="border-right:none;">&nbsp;</td>
</tr>
<tr>
<td width="125" class="table_text" align="right">内容：</td>
<td width="900" align="left"><textarea id="content_sms" name="content_sms"  rows="10" cols="110" style="width: 795px;margin-left:10px;"></textarea></td>
<td rowspan="4" style="border-right:none;">&nbsp;</td>
</tr>

</table>
<div><a href="#" class="easyui-linkbutton e-cis_button" iconCls="icon-reload" onclick="clearForm_sms()">清空</a>
<a href="#" class="easyui-linkbutton e-cis_button" iconCls="icon-search" onclick="send_sms()">发送</a></div>
</div>
</form>
</div>
</div>


<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="邮件公告" iconCls="icon-ok" collapsible="true">
<form id="emailForm">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">发件人：</td>
<td width="900" align="left"><select id="regname" name="regname" onblur="queryRegname();" onchange="queryRegname()" style="width: 800px;"></select></td>
<td rowspan="4" style="border-right:none;">&nbsp;</td>
</tr>
<tr>
<td width="125" class="table_text" align="right">收件人：</td>
<td width="900" align="left"><input type = "text" id="note" name="note" style="width: 795px;margin-left:10px;"></br>　多人发送用“；”隔开</td>
<td rowspan="4" style="border-right:none;">&nbsp;</td>
</tr>
<tr>
<td width="125" class="table_text" align="right">主题：</td>
<td width="900" align="left"><input type = "text" id="theme" name="theme" style="width:795px;margin-left:10px;"></td>
<td rowspan="4" style="border-right:none;">&nbsp;</td>
</tr>
<tr>
<td width="125" class="table_text" align="right">内容：</td>
<td width="900" align="left"><textarea id="infocontent" name="infocontent"  rows="10" cols="110" style="width: 795px;margin-left:10px;"></textarea></td>
<td rowspan="4" style="border-right:none;">&nbsp;</td>
</tr>

</table>
<div><a href="#" class="easyui-linkbutton e-cis_button" iconCls="icon-reload" onclick="clearForm_email()">清空</a>
<a href="#" class="easyui-linkbutton e-cis_button" iconCls="icon-search" onclick="send_eamil()">发送</a></div>
</div>
</form>
</div>
</div>	
	

