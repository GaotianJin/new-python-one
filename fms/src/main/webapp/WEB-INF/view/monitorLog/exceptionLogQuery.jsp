<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/cis/thirdparty/monitorLog/exceptionLogQueryInit.js"></script>
<script type="text/javascript"></script>

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="异常信息查询" iconCls="icon-ok" collapsible="true">
<form id="logQueryForm">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">监控点：</td>
<td width="200" align="left"><select id="monitorpoint" name="monitorpoint" class="table_select"></select></td>
<td width="125" class="table_text" align="right">调度服务：</td>
<td width="200" align="left"><select id="scheduler" name="scheduler"  class="table_select"></select></td>
<td width="125" class="table_text" align="right">渠道编码：</td>
<td width="200" align="left"><select id="salechnlcode" name="salechnlcode"  class="table_select"></select></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">交易编码：</td>
<td width="200" align="left"><select id="transcode" name="transcode" onblur="" onchange="" class="table_select"></select></td>
<td width="125" class="table_text" align="right">起始时间：</td>
<td width="200" align="left"><input id="monitortime" type="text" class="table_input"></input></td>
<td width="125" class="table_text" align="right">终止时间：</td>
<td width="200" align="left"><input id="activesign" type="text" class="table_input"></input></td>
</tr>

</table>
<div><a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
<a href="#" onclick="searchUser()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a></div>
</div>
</form>
</div>
</div>

<div style="margin-top:10px;" id="tabdiv"><table id="logTable"></table></div>