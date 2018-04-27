<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/pos/posAppInit.js"></script>

<form id="posForm">

<div><a href="#" onclick="policyQuery()" class="easyui-linkbutton e-cis_button" iconCls="icon-search" >保单查询</a>

</div>
<div id="tabdiv" >
<div id="smsaccordion" class="easyui-panel" fit="true" title="保全处理" iconCls="icon-ok" collapsible="true">
<div class="top_table">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="125" class="table_text" align="right" />保单号</td>
<td width="200" align="left"><input name="policyno" id="policyno"  type="text" class="easyui-validatebox table_input" data-options="required:true"></td>
<td width="125" class="table_text" align="right">保全操作人</td>
<td width="200" align="left"><input name="operator" id="operator" class="table_input" value="${userCode}" disabled="disabled"></td>
</tr>

<tr>
<td width="125" class="table_text" align="right">保全申请日期</td>
<td width="200" align="left"><input name="appdate" id="appdate" type="text" class="easyui-validatebox easyui-my97 table_input"   data-options="required:true"></td>
<!-- 
<td width="125" class="table_text" align="right">保全生效日期</td>
<td width="200" align="left"><input name="username" class="easyui-datebox"></td>
 -->
<td width="125" class="table_text" align="right">保全项目</td>
<td width="200" align="left">
<select id="postype" class="easyui-combobox" name="postype" style="width:150px;" data-options="required:true">  
<option value="DC">变更地址、邮编</option> 
<option value="CC">变更客户联系电话及邮件地址</option> 
<option value="BC">变更受益人</option>
<option value="AC">变更投保人（信息）</option>
<option value="AE">变更投保人（原投保人身故）</option>
<option value="IC">变更被保险人信息（姓名、身份证号码）</option>
<option value="PA">保单补发</option>
<option value="SC">变更签名</option>
<option value="IE">变更被保险人生日、性别</option>
<option value="OC">被保人变更职业/职业级别</option>
<option value="PT">退保</option>
<option value="PC">取消保单</option>
<option value="RC">取消附加险</option>
<option value="DA">降低保额</option>
</select> 
</td>
</tr>
</table>
</div>
<div><a href="#" onclick="confirm()" class="easyui-linkbutton e-cis_button" iconCls="icon-ok">确认</a>
</div>
</div>
</div>	
</form>

