<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <script type="text/javascript" src="../js/cis/thirdparty/monitorLog/logConfigurationQueryInit.js"></script> 

<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="监控配置" iconCls="icon-ok" collapsible="true">
<form id="listMonitorForm">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">渠道编码：</td>
<td width="200" align="left"><select id="salechnlcode" name="salechnlcode" class="table_select"></select></td>
<td width="125" class="table_text" align="right">交易编码：</td>
<td width="200" align="left"><select id="transcode" name="transcode" class="table_select"></select></td>
<td width="125" class="table_text" align="right">服务编码：</td>
<td width="200" align="left"><select id="servicecode" name="servicecode"  onchange="" class="table_select"></select></td>
</tr>
</table>
<div><a href="#" onclick="clearMonitor()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
<a href="#" onclick="searchMonitor()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a></div>
</div>
</form>
</div>
</div>

<div style="margin-top:2px;" id="tabdiv"><table id="monitorTable"></table></div>
<div id="monitortab" class="easyui-tabs" fit="false" border="ture" plain="true"></div>