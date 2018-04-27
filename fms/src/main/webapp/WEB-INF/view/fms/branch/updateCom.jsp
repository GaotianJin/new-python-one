<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/updateComInit.js"></script>

<div id="tabdiv">
	<form id="update_ComInfoForm">
		<div id="update_comBasicInfoDiv" class="easyui-panel" title="基本信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					
					<tr>
						<td class="table_text" align="right">机构代码</td>
						<td><input name="comCode" id="update_comCode" class="table_input easyui-validatebox" 
						data-options="required:true,validType:['length[0,20]','validCode']"/></td>
						<td class="table_text" align="right">机构名称</td>
						<td align="left"><input name="comName" id="update_comName" class="table_input easyui-validatebox" 
						data-options="required:true,validType:['length[0,100]']"/></td>
						<td class="table_text" align="right">机构级别</td>
						<td align="left"><span class="comboSpan"/><select class = "table_select easyui-combobox easyui-validatebox" 
						data-options="required:true" name="grade"  id="update_grade" /></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">机构状态</td>
						<td align="left"><span class="comboSpan"/><select name="state" id="update_state" class="table_select easyui-combobox 
						easyui-validatebox" data-options="required:true"></td>
						<td class="table_text" align="right">开业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input"  name="startDate" id="update_startDate" 
						data-options="required:true,validType:['length[0,10]','validDate']"/></td>
						<td class="table_text" align="right">停业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input"  name="endDate" id="update_endDate"
						data-options="validType:['length[0,10]','validDate']"/></td>
						
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"/><select class = "table_select easyui-combobox" name="province"  id = "update_province" /></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"/><select class="table_select easyui-combobox" name="city" id="update_city"/></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"/><select name="country" id="update_country" class="table_select easyui-combobox"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right" >详细地址</td>
						<td align="left" colspan='3'><input class = "table_input1" name="street"  id = "update_street" /></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="zipcode"  id = "update_zipcode" 
						data-options="validType:['validZip']"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">机构电话</td>
						<td align="left"><input name="phone"  id = "update_phone" class = "table_input easyui-validatebox"  
						data-options="validType:['length[0,20]','validTel']"/></td>
						<td class="table_text" align="right">机构传真</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="fax"  id = "update_fax"
						data-options="validType:['length[0,20]','validTel']" /></td>
						<td class="table_text" align="right">网址</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="website" data-options="validType:['url']"
						 id = "update_website" /></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">负责人姓名</td>
						<td align="left"><input class = "table_input" name="managerName"  id = "update_managerName" /></td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="managerMobile"  id = "update_managerMobile" 
						data-options="validType:['length[0,20]','validPhone']"/></td>
						<td class="table_text" align="right"></td>
						<td align="left"></td>
					</tr>
					
				</table>
			</div>
		</div>
		  
		<input type="hidden" name="comId" id="update_comId">
		<div id="update_comBelongInfoDiv">
		<div id="update_comBelongInfoDiv1" class="easyui-panel" title="归属信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">上级机构</td>
						<td align="left"><span class="comboSpan" align="left"/><select class = "table_select easyui-combobox easyui-validatebox" name="parentComId" id="update_belongComId"/></td>
			 			<td class="table_text" align="right">归属开始日期</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="belongStartDate" id="update_belongStartDate" 
						data-options="validType:['length[0,10]','validDate']"/></td>
						<td class="table_text" align="right">归属结束日期</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="belongEndDate" id="update_belongEndDate"
						data-options="validType:['length[0,10]','validDate']"></td>
					</tr>
				</table>
			</div>
		</div>
		</div>
		</form>		
		
		<div>
			<a href="#" onclick="updateComInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			<a href="#" onclick="backListComPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	
</div>