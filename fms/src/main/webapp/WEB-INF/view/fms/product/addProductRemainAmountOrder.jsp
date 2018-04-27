<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addProductRemainAmountOrderInit.js"></script>

<input type="hidden" name="pdAmountOrderInfoId" id="addRemainAmountOrder_rdpdAmountOrderQueueInfoId" value="${pdAmountOrderQueueInfoId}">
<input type="hidden" name="comId" id="addRemainAmountOrder_rdcomId" value="${comId}">
<input type="hidden" name="productId" id="addRemainAmountOrder_rdproductId" value="${productId}">
<input type="hidden" name="expectOpenDay" id="addRemainAmountOrder_rdexpectOpenDay" value="${expectOpenDay}">
<input type="hidden" name="foundDate" id="addRemainAmountOrder_rdfoundDate" value="${foundDate}">
<input type="hidden" name="sealingAccDate" id="addRemainAmountOrder_rdsealingAccDate" value="${sealingAccDate}">
<input type="hidden" name="isInviteCode" id="addRemainAmountOrder_rdisInviteCode" value="${isInviteCode}">
<input type="hidden" name="productType" id="addRemainAmountOrder_rdproductType" value="${productType}">
<input type="hidden" name="productSubType" id="addRemainAmountOrder_rdproductSubType" value="${productSubType}">
<input type="hidden" name="isDistribute" id="addRemainAmountOrder_rdqueueIsDistribute" value="${queueIsDistribute}">
<input type="hidden" name="isDistribute" id="addRemainAmountOrder_rdremainAmount" value="${remainAmount}">

<div id="productRemainAmountOrderDiv">
	<form id="addRemainAmountOrder_BaseInfoForm">
		<div id="smsaccordion1" class="easyui-panel" title="机构信息" collapsible="true">
			<div id="addRemainAmountOrder_ComInfo" class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">分公司</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="comId" id="addRemainAmountOrder_comId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">财富顾问</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agentId" id="addRemainAmountOrder_agentId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">联系方式</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agentMobile" id="addRemainAmountOrder_mobile" class="table_input easyui-combobox1">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="smsaccordion2" class="easyui-panel" title="产品信息" collapsible="true">
			<div id="addRemainAmountOrder_ProductInfo" class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">产品方</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agenyComId" id="addRemainAmountOrder_agenyComId" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">产品编码</td>
						<td align="left">
							<input class="table_input"  id="addRemainAmountOrder_productCode" name="productCode" readonly="true">
							<input type="hidden"  id="addRemainAmountOrder_productType" name="productType">
							<input type="hidden"  id="addRemainAmountOrder_productSubType" name="productSubType">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="addRemainAmountOrder_productId" class="table_input easyui-combobox1"/>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">成立日</td>
						<td align="left">
							<input class="table_input"  id="addRemainAmountOrder_foundDate" name="foundDate" readonly="true">
						</td>
						<td class="table_text" align="right">开放日</td>
						<td align="left">
							<input class="table_input"  id="addRemainAmountOrder_expectOpenDay" name="expectOpenDay" readonly="true">
						</td>
						<td class="table_text" align="right">封账日</td>
						<td align="left">
							<input name="sealingAccDate" id="addRemainAmountOrder_sealingAccDate" class="table_input" readonly="true"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="smsaccordion3" class="easyui-panel" title="客户信息" collapsible="true">
			<div id="addRemainAmountOrder_CustomerInfo" class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left">
							<input type="hidden" name="pdAmountOrderInfoId" id="addRemainAmountOrder_pdAmountOrderInfoId" class="input_hidden">
							<input class="table_input easyui-validatebox"  id="addRemainAmountOrder_custName" name="custName" data-options="required:true"/>
						</td>
						<td class="table_text" align="right">预约额度(元)</td>
						<td align="left">
							<input class="table_input easyui-numberbox"  id="addRemainAmountOrder_orderAmount" name="orderAmount" data-options="required:true"/>
						</td>
						<td class="table_text" align="right">拟打款时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="addRemainAmountOrder_planTransferDate" name="planTransferDate" data-options="required:true"/>
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
							<input class="table_input easyui-combobox"  id="addRemainAmountOrder_idType" name="idType"/>
						</td>
						<td class="table_text" align="right">证件号码</td>
						<td align="left">
							<input class="table_input easyui-validatebox"  id="addRemainAmountOrder_idNo" name="idNo" onBlur="verifyIdNo()"/>
						</td>
						<td class="table_text" align="right">证件有效期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-datebox"  id="addRemainAmountOrder_idValidityDate" name="idValidityDate"/>
						</td>
					</tr>
					<!-- <tr>
						<td class="table_text" align="right">开户行</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox"  id="addRemainAmountOrder_bankCode" name="bankCode"/>
						</td>
						<td class="table_text" align="right">开户名</td>
						<td align="left">
							<input class="table_input easyui-validatebox"  id="addRemainAmountOrder_accName" name="accName"/>
						</td>
						<td class="table_text" align="right">收益账号</td>
						<td align="left">
							<input class="table_input easyui-validatebox"  id="addRemainAmountOrder_accNo" name="accNo"/>
						</td>
					</tr> -->
					
					<tr>
						<!-- <td class="table_text" align="right">联系电话</td>
						<td align="left" >
							<input name="mobile" id="addRemainAmountOrder_custMobile" class="table_input easyui-validatebox"
								data-options="validType:['length[0,20]','validPhone'],required:true"></td> -->
						<td class="table_text" align="right">邮箱</td>
						<td align="left">
							<input name="email" id="addRemainAmountOrder_custEmail" class="table_input easyui-validatebox"
								data-options="validType:['email','length[0,50]']"></td>
						<td class="table_text" align="right">是否为新客户</td>
							<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="addRemainAmountOrder_isOldCustomer" name="isOldCustomer" 
							data-options="required:true"/>
						</td>
					</tr>
					<!-- <tr>
						<td class="table_text" align="right">省</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="addRemainAmountOrder_province" name="province"/>
						</td>
						<td class="table_text" align="right">市</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="addRemainAmountOrder_city" name="city"/>
						</td>
						<td class="table_text" align="right">区/县</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1"  id="addRemainAmountOrder_country" name="country"/>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right" >详细地址</td>
						<td colspan="3">
							<input name="address" id="addRemainAmountOrder_address" class="table_input1 easyui-validatebox"
								data-options="validType:['length[0,100]']"></td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left" >&nbsp;</td>
					</tr> -->
				</table>
			</div>
		</div>
		<div style="margin-bottom: 3px;">
			<a href="#" onclick="backListPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>