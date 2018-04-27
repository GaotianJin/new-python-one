<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/updateAditemInit.js"></script>

<div id="tabdiv">
	<form id="updateAditemForm">
		<div id="updateAditemInfo" class="easyui-panel" fit="true" title="更新增减项" iconCls="icon-edit" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">增减项名称：</td>
						<td align="left">
						  <input id="aditemName" name="aditemName" class="easyui-validatebox table_input" value="${aditemName}" ><font color="red">*</font>
						</td>
						<td class="table_text" align="right">金额：</td>	
						  <td align="left">
						  <input id="aditemMoney" name="aditemMoney" class="easyui-numberbox table_input" 
						  min="-99999999999999" max="99999999999999" percison="2"  missingMessage="金额格式不正确" value="${aditemMoney}"><font color="red">*</font>
						</td>
					</tr>
				</table>
				<input type="hidden" id="id" name="id" value="${id}">
				<div>
					<a href="#" onclick="updateAditem()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">更新</a>
				</div>
	      </div>
        </div>
	</form>
</div>


