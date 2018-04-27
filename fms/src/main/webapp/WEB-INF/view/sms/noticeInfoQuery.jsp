<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/cis/thirdparty/sms/noticeInfoQueryInit.js"></script>
<script type="text/javascript"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="提示信息查询" iconCls="icon-ok" collapsible="true">
<form id="queryForm">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">信息编码：</td>
<td width="200" align="left"><select id="messnumber" name="messnumber" onblur="queryMessnumber();" onchange="queryMessnumber" class="table_select"></select></td>
<td width="125" class="table_text" align="right">异常编号：</td>
<td width="200" align="left"><select id="abnonumber" name="abnonumber" onblur="queryabnonumber();" onchange="queryabnonumber()" class="table_select"></select></td>
<td rowspan="2" style="border-right:none;">&nbsp;</td>
</tr>

</table>
<div><a href="#" class="easyui-linkbutton e-cis_button" iconCls="icon-reload" onclick="clearForm()">清空</a>
<a href="#" class="easyui-linkbutton e-cis_button" iconCls="icon-search" onclick="searchInfo()">查询</a></div>
</div>
</form>
</div>
</div>

<div style="margin-top:10px;" id="tabdiv"><table id="infoTable"></table> </div>
<div id="noticeInfotab" class="easyui-tabs" fit="false" border="ture" plain="true"></div>
