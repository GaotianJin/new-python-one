<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/detailComInit.js"></script>

<div id="tabdiv">
		<div id="detail_comBasicInfoDiv" class="easyui-panel" title="基本信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">机构代码</td>
						<td><input name="comCode" id="detail_comCode" class="table_input" disabled="disabled"/></td>
						<td class="table_text" align="right">机构名称</td>
						<td align="left"><input name="comName" id="detail_comName" class="table_input" disabled="disabled"/></td>
						<td class="table_text" align="right">机构级别</td>
						<td align="left"><span class="comboSpan"/><input class = "table_select easyui-combobox1" name="grade"  id = "detail_grade" disabled="disabled"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">机构状态</td>
						<td align="left"><span class="comboSpan"/><input name="state" id="detail_state" class="table_select easyui-combobox1" disabled="disabled"></td>
						<td class="table_text" align="right">开业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="startDate" id="detail_startDate" disabled="disabled"/></td>
						<td class="table_text" align="right">停业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="endDate" id="detail_endDate" disabled="disabled"></td>
						
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"/><input class = "table_select easyui-combobox1" name="province"  id = "detail_province" disabled="disabled"/></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"/><input class="table_select easyui-combobox1" name="city" id="detail_city" disabled="disabled"/></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"/><input name="country" id="detail_country" class="table_select easyui-combobox1" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right" >详细地址</td>
						<td align="left" colspan='3'><input class = "table_input1" name="street"  id = "detail_street" disabled="disabled"/></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input class = "table_input" name="zipcode"  id = "detail_zipcode" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">机构电话</td>
						<td align="left"><input class = "table_input" name="phone"  id = "detail_phone" disabled="disabled"/></td>
						<td class="table_text" align="right">机构传真</td>
						<td align="left"><input class = "table_input" name="fax"  id = "detail_fax" disabled="disabled"/></td>
						<td class="table_text" align="right">网址</td>
						<td align="left"><input class = "table_input" name="website"  id = "detail_website" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">负责人姓名</td>
						<td align="left"><input class = "table_input" name="managerName"  id = "detail_managerName" disabled="disabled"/></td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left"><input class = "table_input" name="managerMobile"  id = "detail_managerMobile" disabled="disabled"/></td>
						<td class="table_text" align="right"></td>
						<td align="left"></td>
					</tr>
					
				</table>
			</div>
		</div>
		    <div id="detail_comBelongInfoDiv">
			<div id="detail_comBelongInfoDiv1" class="easyui-panel" title="归属信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">上级机构</td>
							<td align="left"><span class="comboSpan" align="left"/><select class = "table_select easyui-combobox1" name="parentComId" id="detail_belongComId" disabled="disabled"/></td>
				 			<td class="table_text" align="right">归属开始日期</td>
							<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="belongStartDate" id="detail_belongStartDate" disabled="disabled"/></td>
							<td class="table_text" align="right">归属结束日期</td>
							<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="belongEndDate" id="detail_belongEndDate" disabled="disabled"></td>
						</tr>
					</table>
				</div>
			</div>
			</div>
			<div>
			<a href="#" onclick="backListComPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
</div>