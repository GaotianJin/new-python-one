<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/cis/thirdparty/sms/addInfoInit.js"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="新增提示信息" iconCls="icon-ok" collapsible="true">
<form id="addInfoForm" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">信息编码：</td>
<td width="200" align="left"><input id="messnumber" name="messnumber" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">异常编号：</td>
<td width="200" align="left"><input id="abnonumber" name="abnonumber" class="table_input" value=""></td>
<td style="border-right:none;">&nbsp;</td>
<td style="border-right:none;">&nbsp;</td>
</tr>

<tr>
<td width="125" class="table_text" align="right">信息主题：</td>
<td width="200" align="left"><input id="theme" name="theme" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">提示信息：</td>
<td width="200" align="left"><input id="infocontent" name="infocontent" class="table_input" value=""></td>
<td style="border-right:none;">&nbsp;</td>
<td style="border-right:none;">&nbsp;</td>
</tr>

</table>
<div><a href="#" onclick="addInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增信息</a></div>
</div>
</form>
</div>
</div>	

	
 			 