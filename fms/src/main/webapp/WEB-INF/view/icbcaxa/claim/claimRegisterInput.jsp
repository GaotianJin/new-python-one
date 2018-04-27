<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/claimRegisterInputInit.js"></script>
<div id="tabdiv">
	<form id="claimRegisterForm">
		<div id="claimRegisterInfo" class="easyui-panel" fit="true" title="新增报案" iconCls="icon-edit" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">保单号：</td>
						<td align="left">
						  <input id="policyNo" name="policyNo" class="easyui-validatebox table_input" required="required" value="">
						</td>
						<td class="table_text" align="right">出险日期：</td>	
						  <td align="left">
						  <input id="inDangerDate" name="inDangerDate" class="easyui-datebox" required="required" value="">
						</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="claimRegister()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">领号</a>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
				</div>
	      </div>
        </div>
	</form>
</div>


