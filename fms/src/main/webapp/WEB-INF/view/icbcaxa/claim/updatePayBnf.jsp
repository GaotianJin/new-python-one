<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/updatePayBnfInit.js"></script>
<div id="tabdiv">
	<form id="updatePayBnfForm">
		<div id="updatePayBnfInfo" class="easyui-panel" fit="true" title="更新受益人" iconCls="icon-edit" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">受益人姓名：</td>
						<td align="left">
						  <input id="bnfName" name="bnfName" class="easyui-validatebox table_input"  value="${bnfName}"><font color="red">*</font>
						</td>
						<td class="table_text" align="right">受益比例：</td>	
						  <td align="left">
						  <input id="distributeProportion" name="distributeProportion" class="easyui-numberbox table_input" 
						  min="0" max="1" precision="2" missingMessage="必须填写0到1之间的两位小数" value="${distributeProportion}"><font color="red">*</font>
						</td>
						<td class="table_text" align="right">受益人账户名称：</td>	
						  <td align="left">
						  <input id="bnfAccountName" name="bnfAccountName" class="easyui-validatebox table_input"  value="${bnfAccountName}"><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">受益人账户银行：</td>
						<td align="left">
						  <input class="easyui-validatebox table_select" name="bnfAccountBankId" id="bnfAccountBankId" value="${bnfAccountBankID}" style="width:154px;"><font color="red">*</font>
						</td>
						<td class="table_text" align="right">受益人开户行所在省：</td>	
						  <td align="left">
						  <select class="easyui-validatebox table_select" name="payBankProvinceId" id="payBankProvinceId"  value="${payBankProvinceID}" style="width:154px;"><font color="red">*</font>
						  </select>
						</td>
						<td class="table_text" align="right">受益人开户行所在市：</td>	
						  <td align="left">
						  <select class="easyui-validatebox table_select" name="payBankCityId" id="payBankCityId"  value="${payBankCityID}" style="width:154px;"><font color="red">*</font>
						  </select>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">受益人账号：</td>
						<td align="left">
						  <input id="bnfAccountNo" name="bnfAccountNo" class="easyui-validatebox table_input" value="${bnfAccountNo}" ><font color="red">*</font>
						</td>
						<td class="table_text" align="right">证件类型：</td>	
						  <td align="left">
						  <select id="bnfCardType" name="bnfCardType"  class="easyui-validatebox table_select"   style="width:154px;">
						      <option value="">--请选择--</option>
			                  <option value="00" <c:if test="${bnfCardType eq '00'}">selected</c:if> >身份证</option>
			                  <option value="01" <c:if test="${bnfCardType eq '01'}">selected</c:if>>军官证</option>
			                  <option value="02" <c:if test="${bnfCardType eq '02'}">selected</c:if>>士兵证</option>
			                  <option value="03" <c:if test="${bnfCardType eq '03'}">selected</c:if>>临时身份证</option>
			                  <option value="04" <c:if test="${bnfCardType eq '04'}">selected</c:if>>港澳居民来往大陆通行证</option>
			                  <option value="05" <c:if test="${bnfCardType eq '05'}">selected</c:if>>台湾居民来往大陆通行证</option>
			                  <option value="06" <c:if test="${bnfCardType eq '06'}">selected</c:if>>护照</option>
			                  <option value="99" <c:if test="${bnfCardType eq '99'}">selected</c:if>>其他</option>
			               </select><font color="red">*</font>
						</td>
						<td class="table_text" align="right">证件号码：</td>	
						  <td align="left">
						  <input id="bnfCardNo" name="bnfCardNo" class="easyui-validatebox table_input" value="${bnfCardNo}" ><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">手机号码：</td>	
						  <td align="left">
						  <input id="mobile" name="mobile" class="easyui-validatebox table_input" value="${mobile}" >
						</td>
					</tr>
				</table>
				<input type="hidden" id="id" name="id" value="${id}">
				<input type="hidden" id="payMoney" name="payMoney" value="${payMoney}">
			    <input type="hidden" id="bnfAccountBankIDName" name="bnfAccountBankIDName" value="${bnfAccountBankID}">
			    <input type="hidden" id="payBankProvinceIdName" name="payBankProvinceIdName" value="${payBankProvinceID}">
			    <input  type="hidden"  id="payBankCityIdName" name="payBankCityIdName" value="${payBankCityID}">
			    
				<div>
                    <a href="#" onclick="updatePayBnf()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">更新</a> 
				</div>
	      </div>
        </div>
	</form>
</div>


