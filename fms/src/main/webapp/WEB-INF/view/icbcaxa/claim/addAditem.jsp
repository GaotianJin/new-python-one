<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/addAditemInit.js"></script>
<div id="tabdiv">
	<form id="addAditemForm">
		<div id="addAditemInfo" class="easyui-panel" fit="true" title="新增增减项" iconCls="icon-edit" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">增减项名称：</td>
						<td align="left">
						  <input id="aditemName" name="aditemName" class="easyui-validatebox table_input" value="" ><font color="red">*</font>
						</td>
						<td class="table_text" align="right">金额：</td>	
						  <td align="left">
						  <input id="aditemMoney" name="aditemMoney" class="easyui-numberbox table_input" 
						  min="-99999999999999" max="99999999999999" percison="2"  missingMessage="金额格式不正确" value=""><font color="red">*</font>
						</td>
					</tr>
				</table>
				<input id="id" type="hidden" name="id" value="${id}">
				<div>
					<a href="#" onclick="addAdditem()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">保存</a>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
				</div>
	      </div>
        </div>
	</form>
</div>


