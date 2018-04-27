<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/addCrippleInit.js"></script>

<div id="tabdiv">
	<form id="addCrippleForm">
		<div id="addCrippleInfo" class="easyui-panel" fit="true" title="新增伤残项" iconCls="icon-edit" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">伤残大类：</td>
						<td align="left">
						  <select class="easyui-validatebox table_select" name="crippleClass" id="crippleClass"  >
						  </select><font color="red">*</font>
						</td>
						<td class="table_text" align="right">伤残子类：</td>	
						  <td align="left">
						  <select class="easyui-validatebox table_select" name="crippleSubClass" id="crippleSubClass" >
						  </select><font color="red">*</font>
						</td>
						<td class="table_text" align="right">伤残描述：</td>	
						  <td align="left">
						  <select class="easyui-validatebox table_select" name="crippleDesc" id="crippleDesc"  >
						  </select><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">伤残等级：</td>
						<td align="left">
                             <input id="crippleGrade" name="crippleGrade" class="easyui-validatebox table_input"   disabled="disabled" style="width:145px;"><font color="red">*</font>
						</td>
						<td class="table_text" align="right">鉴定日期：</td>	
						  <td align="left">
						  <input id="authenticationDate" type="text" class="easyui-datebox"  style="width:150px;"></input><font color="red">*</font> 
						</td>
				</table>
				<input id="id" type="hidden" name="id" value="${id}">
				<div>
					<a href="#" onclick="addCripple()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增</a>
					<a href="#" onclick="clearCripple()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
				</div>
	      </div>
        </div>
	</form>
</div>


