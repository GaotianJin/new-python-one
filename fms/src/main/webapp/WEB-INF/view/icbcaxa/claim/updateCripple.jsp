<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/updateCrippleInit.js"></script>

<div id="tabdiv">
	<form id="updateCrippleForm">
		<div id="updateCrippleInfo" class="easyui-panel" fit="true" title="更新伤残项" iconCls="icon-edit" collapsible="true">
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
						  <select class="easyui-validatebox table_select" name="crippleDesc" id="crippleDesc" >
						  </select><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">伤残等级：</td>
						<td align="left">
                             <input id="crippleGrade" name="crippleGrade" class="table_input" value="${crippleGrade}" style="width:145px;"><font color="red">*</font>
						</td>
						<td class="table_text" align="right">鉴定日期：</td>	
						  <td align="left">
						  <input id="authenticationDate" type="text" class="easyui-datebox"  value="${authenticationDate}" style="width:150px;"></input><font color="red">*</font> 
						</td>
				</table>
				<input type="hidden" id="id" name="id" value="${id}">
				<input type="hidden"  id="crippleClassName" name="crippleClassName" value="${crippleClass}">
				<input type="hidden"  id="crippleSubClassName" name="crippleSubClassName" value="${crippleSubClass}">
				<input type="hidden"  id="crippleDescName" name="crippleDescName"  value="${crippleDesc}">
				<div>
					<a href="#" onclick="updateCripple()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">更新</a>
				</div>
	      </div>
        </div>
	</form>
</div>


