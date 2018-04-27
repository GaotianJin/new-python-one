<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/updateCustBelongInfoInit.js"></script>

<div id="tabdiv">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<form id="custBasicInfo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">客户号</td>
						<td>
							<input name="customer_no" id="customer_no" class="table_input" />
							<input name="customerBaseInfoId" id="customerBaseInfo_Id" type="hidden" class="table_input"/>
						</td>
						<td class="table_text" align="right">中文姓名</td>
						<td align="left"><input name="chinese_name" id="chinese_name"  class="table_input" /></td>
						<td class="table_text" align="right">性别</td>
						<td><span class="comboSpan"></span ><input name="sex" id="modifyCustomerBaseInfo_Sex"   class="table_input easyui-combobox1"  /></td>
					</tr>
					<tr>
						<td class="table_text" align="right">证件类型</td>
						<td align="left"><span class="comboSpan"></span ><input name="idtype" id="modifyCustomerBaseInfo_IdType"  class="table_input easyui-combobox1"></td>
						<td class="table_text" align="right">证件号码</td>
						<td align="left"><input class = "table_input" name="idno"   id = "idno" /></td>
						<td class="table_text" align="right">年龄</td>
						<td><input name="age" id="modifyCustomerBaseInfo_age" class="table_input"  /></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">客户级别</td>
						<td align="left"><span class="comboSpan"></span ><input name="custLevelName" class="table_input easyui-combobox1" id = "modifyCustomerBaseInfo_CustLevel" /></td>
						<!-- <td class="table_text" align="right">获客方式</td>
						<td align="left"><span class="comboSpan"></span ><input class="table_input easyui-combobox1" name="custObtainWay"   id = "modifyCustomerBaseInfo_CustObtainWay" /></td> -->
						<td class="table_text" align="right">客户类型</td>
						<td align="left"><span class="comboSpan"></span ><input  name="custType"  class="table_input easyui-combobox1" id = "modifyCustomerBaseInfo_CustType" /></td>
						
					<!-- </tr>
					<tr>
						<td class="table_text" align="right">客户重要性</td>
						<td align="left"><span class="comboSpan"></span ><input  name="custQuality"  class="table_input easyui-combobox1" id = "modifyCustomerBaseInfo_custQuality" /></td>
					</tr> -->
				</table>
				</form>
			</div>
			
			<div id="smsaccordion1" class="easyui-panel" title="调整记录" collapsible="true">
				<table id="custBelongInfoTable"></table>
			</div> 
			
			<div id="smsaccordion2" class="easyui-panel" title="新增调整" collapsible="true">
					<table id="addAgentBelongInfoTable"></table>
			</div> 
			<div>
					<a href="#" onclick="saveUpdateInfo()"   class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>		
					<a href="#" onclick="backListUserPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</div>
		</div>
	
</div>
	