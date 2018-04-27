<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/addComInit.js"></script>

<div id="tabdiv">
	<form id="add_ComInfoForm">
		<div id="add_comBasicInfoDiv" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">机构代码</td>
						<td><input name="comCode" id="add_comCode" class="table_input easyui-validatebox" 
						data-options="required:true,validType:['length[0,20]','validCode']"/></td>
						<td class="table_text" align="right">机构名称</td>
						<td align="left"><input name="comName" id="add_comName" class="table_input easyui-validatebox" 
						data-options="required:true,validType:['length[0,100]']"/></td>
						<td class="table_text" align="right">机构级别</td>
						<td align="left"><span class="comboSpan"/><select class = "table_select easyui-combobox1  easyui-validatebox" 
						data-options="required:true" name="grade"  id="add_grade"  data-options="required:true"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">机构状态</td>
						<td align="left"><span class="comboSpan"/><select name="state" id="add_state" class="table_select 
						easyui-combobox1  easyui-validatebox " data-options="required:true"></td>
						<td class="table_text" align="right">开业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox " 
						name="startDate" id="add_startDate" data-options="required:true,validType:['length[0,10]','validDate']"/></td>
						<td class="table_text" align="right">停业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input " name="endDate" id="add_endDate" data-options="validType:['length[0,10]','validDate']"></td>
						
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"/><select class = "table_select easyui-combobox" name="province"  id="add_province" /></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"/><select class="table_select easyui-combobox" name="city" id="add_city"/></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"/><select name="country" id="add_country" class="table_select easyui-combobox"/></td>
					</tr>
					
					 <tr>
						<td class="table_text" align="right" >详细地址</td>
						<td align="left" colspan='3'><input class = "table_input1" name="street"  id = "street" /></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input name="zipcode"  id = "zipcode"  class="table_input easyui-validatebox"
						data-options="validType:['validZip']"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">机构电话</td>
						<td align="left"><input name="phone"  id = "phone" class="table_input easyui-validatebox"  
						data-options="validType:['length[0,20]','validTel']"/></td>
						<td class="table_text" align="right">机构传真</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="fax"  id = "fax" 
						 data-options="validType:['length[0,20]','validTel']"/></td>
						<td class="table_text" align="right">网址</td>
						<td align="left"><input class = "table_input easyui-validatebox"  name="website" id="add_website" 
						data-options="validType:['url']"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">负责人姓名</td>
						<td align="left"><input class = "table_input" name="managerName"  id="add_managerName" /></td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="managerMobile"  id="add_managerMobile" 
						data-options="validType:['length[0,20]','validPhone']"/></td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					
				</table>
			</div>
		</div>
		<div id="add_comBelongInfoDiv">
		<div id="add_comBelongInfoDiv1" class="easyui-panel" title="归属信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">上级机构</td>
						<td align="left"><span class="comboSpan" align="left"/><select name="parentComId" id="add_belongComId" class="table_select easyui-combobox easyui-validatebox" /></td>
			 			<td class="table_text" align="right">归属开始日期</td>
						<td align="left"><span class="comboSpan"/><select class="easyui-datebox table_input easyui-validatebox" name="belongStartDate" id="add_belongStartDate"
						data-options="validType:['length[0,10]','validDate']"></td>
						<td class="table_text" align="right">归属结束日期</td>
						<td align="left"><span class="comboSpan"/><select class="easyui-datebox table_input easyui-validatebox" name="belongEndDate" id="add_belongEndDate"
						data-options="validType:['length[0,10]','validDate']"></td>
					</tr>
				</table>
			</div>
		</div>
		</div>
		</form>		
 </div>
<div>
	<a href="#" onclick="addComInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
	<a href="#" onclick="backListComPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	
</div>

<script>
$("#add_ComInfoForm").form("validate");
</script>