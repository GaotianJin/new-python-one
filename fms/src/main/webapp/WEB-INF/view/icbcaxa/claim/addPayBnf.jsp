<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/addPayBnfInit.js"></script>
<div id="tabdiv">
	<form id="addPayBnfForm">
		<div id="addPayBnfInfo" class="easyui-panel" fit="true" title="新增受益人" iconCls="icon-edit" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">受益人姓名：</td>
						<td align="left">
						  <input id="bnfName" name="bnfName" class="easyui-validatebox table_input"   value=""><font color="red">*</font>
						</td>
						<td class="table_text" align="right">受益比例：</td>	
						  <td align="left">
						  <input id="distributeProportion" name="distributeProportion" class="easyui-numberbox table_input" 
						  min="0" max="1" precision="2"  missingMessage="必须填写0到1之间的两位小数" value=""><font color="red">*</font>
						</td>
						<td class="table_text" align="right">受益人账户名称：</td>	
						  <td align="left">
						  <input id="bnfAccountName" name="bnfAccountName" class="easyui-validatebox table_input"   value=""><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">受益人账户银行：</td>
						<td align="left">
						  <select class="easyui-validatebox table_select" name="bnfAccountBankId" id="bnfAccountBankId" style="width:154px;">
						  </select><font color="red">*</font>
						</td>
						<td class="table_text" align="right">受益人开户行所在省：</td>	
						  <td align="left">
						  <select class="easyui-validatebox table_select" name="payBankProvinceId" id="payBankProvinceId"  style="width:154px;">
						  </select><font color="red">*</font>
						</td>
						<td class="table_text" align="right">受益人开户行所在市：</td>	
						  <td align="left">
						  <select class="easyui-validatebox table_select" name="payBankCityId" id="payBankCityId"  style="width:154px;">
						  </select><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">受益人账号：</td>
						<td align="left">
						  <input id="bnfAccountNo" name="bnfAccountNo" class="easyui-validatebox table_input" value="" ><font color="red">*</font>
						</td>
						<td class="table_text" align="right">证件类型：</td>	
						  <td align="left">
						  <select id="bnfCardType" name="bnfCardType"  class="easyui-validatebox table_select"  value="" style="width:154px;">
						      <option value="">--请选择--</option>
			                  <option value="00">身份证</option>
			                  <option value="01">军官证</option>
			                  <option value="02">士兵证</option>
			                  <option value="03">临时身份证</option>
			                  <option value="04">港澳居民来往大陆通行证</option>
			                  <option value="05">台湾居民来往大陆通行证</option>
			                  <option value="06">护照</option>
			                  <option value="99">其他</option>
			               </select><font color="red">*</font>
						</td>
						<td class="table_text" align="right">证件号码：</td>	
						  <td align="left">
						  <input id="bnfCardNo" name="bnfCardNo" class="easyui-validatebox table_input" value="" ><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">手机号码：</td>	
						  <td align="left">
						  <input id="mobile" name="mobile" class="easyui-validatebox table_input" value="" >
						</td>
					</tr>
				</table>
				<input id="id" type="hidden" name="id" value="${id}">
				<input type="hidden" id="payMoney" name="payMoney" value="${payMoney}">
				<div>
					<a href="#" onclick="addPayBnf()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">保存</a>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
				</div>
	      </div>
        </div>
	</form>
</div>


