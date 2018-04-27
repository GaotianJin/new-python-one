<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/cis/thirdparty/monitorLog/addMonitorInit.js"></script>


<div id="tabdiv">
<div id="smsaccordion" class="easyui-panel" fit="true" title="新增监控配置" iconCls="icon-ok" collapsible="true">
<form id="addMonitorForm" action="listWebCharUrl" method="post">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr>
<td width="125" class="table_text" align="right">渠道编码：</td>
<td width="200" align="left"><input id="salechnlcode" name="salechnlcode" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">渠道编码：</td>
<td width="200" align="left"><input id="transcode" name="transcode" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">服务编码： </td>
<td width="200" align="left"><input id="servicecode" name="servicecode" class="table_input" value=""></td>

</tr>

<tr>
<td width="125" class="table_text" align="right">配置对象：</td>
<td width="200" align="left" style="border-right:none;"><input id="confiobject" name="confiobject" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">是否监控：</td>
<td width="200" align="left"><input id="smsflag" name="smsflag" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">短信通知：</td>
<td width="200" align="left"><input id="servicecode" name="servicecode" class="table_input" value=""></td>

</tr>

<tr>
<td width="125" class="table_text" align="right">邮件通知：</td>
<td width="200" align="left" style="border-right:none;"><input id="emailflag" name="emailflag" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">短信服务器编码：</td>
<td width="200" align="left"><input id="smsserver" name="smsserver" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">短信接收方编码：</td>
<td width="200" align="left"><input id="smsreceiver" name="smsreceiver" class="table_input" value=""></td>

</tr>

<tr>
<td width="125" class="table_text" align="right">邮件服务器编码：</td>
<td width="200" align="left" style="border-right:none;"><input id="emailserver" name="emailserver" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">邮件服务器方编码：</td>
<td width="200" align="left"><input id="emalreceiver" name="emalreceiver" class="table_input" value=""></td>
<td width="125" class="table_text" align="right">允许最大等待时间：</td>
<td width="200" align="left"><input id="maxwaittime" name="maxwaittime" class="table_input" value=""></td>
</tr>

</table>
<div><a href="#" onclick="addMonitor()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增配置</a></div>
</div>
</form>
</div>
</div>	 