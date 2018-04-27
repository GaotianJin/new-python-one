<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addProductAmountOrderInit.js"></script>

<input type="hidden" name="pdAmountOrderInfoId" id="addPdAmountOrder_hdpdAmountOrderInfoId" value="${pdAmountOrderInfoId}">
<input type="hidden" name="tradeInfoId" id="addPdAmountOrder_hdTradeInfoId" value="${tradeInfoId}">
<input type="hidden" name="operate" id="addPdAmountOrder_hdoperate" value="${operate}">
<input type="hidden" name="comId" id="addPdAmountOrder_hdcomId" value="${comId}">
<input type="hidden" name="productId" id="addPdAmountOrder_hdproductId" value="${productId}">
<input type="hidden" name="expectOpenDay" id="addPdAmountOrder_hdexpectOpenDay" value="${expectOpenDay}">
<input type="hidden" name="foundDate" id="addPdAmountOrder_hdfoundDate" value="${foundDate}">
<input type="hidden" name="sealingAccDate" id="addPdAmountOrder_hdsealingAccDate" value="${sealingAccDate}">
<input type="hidden" name="isInviteCode" id="addPdAmountOrder_hdisInviteCode" value="${isInviteCode}">
<input type="hidden" name="productType" id="addPdAmountOrder_hdproductType" value="${productType}">
<input type="hidden" name="productSubType" id="addPdAmountOrder_hdproductSubType" value="${productSubType}">
<input type="hidden" name="isDistribute" id="addPdAmountOrder_hdisDistribute" value="${isDistribute}">
<input type="hidden" name="remainAmount" id="addPdAmountOrder_hdremainAmount" value="${remainAmount}">
<div id="productAmountOrderDiv">
	<form id="addPdAmountOrder_BaseInfoForm">
		<div id="smsaccordion1" class="easyui-panel" title="机构信息" collapsible="true">
			<div id="addPdAmountOrder_ComInfo" class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">分公司</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="comId" id="addPdAmountOrder_comId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">财富顾问</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agentId" id="addPdAmountOrder_agentId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">联系方式</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agentMobile" id="addPdAmountOrder_mobile" class="table_input easyui-combobox1">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="smsaccordion2" class="easyui-panel" title="产品信息" collapsible="true">
			<div id="addPdAmountOrder_ProductInfo" class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">产品方</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agenyComId" id="addPdAmountOrder_agenyComId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">产品编码</td>
						<td align="left">
							<input class="table_input"  id="addPdAmountOrder_productCode" name="productCode" readonly="true">
							<input type="hidden"  id="addPdAmountOrder_productType" name="productType">
							<input type="hidden"  id="addPdAmountOrder_productSubType" name="productSubType">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="addPdAmountOrder_productId" class="table_input easyui-combobox1"/>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">成立日</td>
						<td align="left">
							<input class="table_input"  id="addPdAmountOrder_foundDate" name="foundDate" readonly="true">
						</td>
						<td class="table_text" align="right">开放日</td>
						<td align="left">
							<input class="table_input"  id="addPdAmountOrder_expectOpenDay" name="expectOpenDay" readonly="true">
						</td>
						<td class="table_text" align="right">封账日</td>
						<td align="left">
							<input name="sealingAccDate" id="addPdAmountOrder_sealingAccDate" class="table_input" readonly="true"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="smsaccordion3" class="easyui-panel" title="客户信息" collapsible="true">
			<div id="addPdAmountOrder_CustomerInfo" class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input type="hidden" name="pdAmountOrderInfoId" id="addPdAmountOrder_pdAmountOrderInfoId" class="input_hidden">
							<input type="hidden" name="custBaseInfoId" id="custBaseInfoId" class="input_hidden">
							<input class="table_input easyui-combobox"  id="addPdAmountOrder_custName" name="custName" data-options="required:true"/>
						</td>
						<td class="table_text" align="right">预约额度(元)</td>
						<td align="left">
							<input class="table_input easyui-numberbox"  id="addPdAmountOrder_orderAmount" name="orderAmount" data-options="required:true"/>
						</td>
						<td class="table_text" align="right">拟打款时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="addPdAmountOrder_planTransferDate" name="planTransferDate" data-options="required:true"/>
						</td>
						<!-- <td class="table_text" align="right">邀请码</td>
						<td align="left">
							<input name="inviteCode" id="addPdAmountOrder_inviteCode" class="table_input" readonly="true" >
							<a href="javascript:getInviteCode()" id="addPdCustOrder_getInviteCodeButton" class="easyui-linkbutton" data-options="iconCls:'icon-apply',plain:true">获取邀请码</a>
						</td> -->
					</tr>
					<tr>
						<td class="table_text" align="right">证件类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="addPdAmountOrder_idType" name="idType" readonly="true"/>
						</td>
						<td class="table_text" align="right">证件号码</td>
						<td align="left">
							<input class="table_input easyui-validatebox"  id="addPdAmountOrder_idNo" name="idNo"  readonly="true"/>
						</td>
						<td class="table_text" align="right">证件有效期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="addPdAmountOrder_idValidityDate" name="idValidityDate" readonly="true"/>
						</td>
					</tr>
					<!-- <tr>
						<td class="table_text" align="right">开户行</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox"  id="addPdAmountOrder_bankCode" name="bankCode"/>
						</td>
						<td class="table_text" align="right">开户名</td>
						<td align="left">
							<input class="table_input easyui-validatebox"  id="addPdAmountOrder_accName" name="accName"/>
						</td>
						<td class="table_text" align="right">收益账号</td>
						<td align="left">
							<input class="table_input easyui-validatebox"  id="addPdAmountOrder_accNo" name="accNo"/>
						</td>
					</tr> -->
					
					<tr>
						<!-- <td class="table_text" align="right">联系电话</td>
						<td align="left" >
							<input name="mobile" id="addPdAmountOrder_custMobile" class="table_input easyui-validatebox"
								 readonly="true"></td> -->
						<td class="table_text" align="right">邮箱</td>
						<td align="left">
							<input name="email" id="addPdAmountOrder_custEmail" class="table_input easyui-validatebox"
								 readonly="true"></td>
						<td class="table_text" align="right">是否为新客户</td>
							<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="addPdAmountOrder_isOldCustomer" name="isOldCustomer" 
							/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div style="margin-bottom: 3px;">
			<a href="#" id="addPdCustOrder_submitButton" onclick="saveProductAmountOrderInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交申请</a>
			<a href="#" id="updatePdCustOrder_submitButton" onclick="updateProductAmountOrderInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
			<a href="#" onclick="backListProductInfoPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>