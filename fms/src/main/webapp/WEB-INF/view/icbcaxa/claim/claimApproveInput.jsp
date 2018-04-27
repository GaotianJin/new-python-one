<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/claimApproveInputInit.js"></script>
<form id="claimCheckInputForm">
<div id="buttonDiv" class='buttonDiv' style="z-index: 1; position:fixed;  height: 50px;  background-color: #E0ECF8;  width: 100%;height: 50px;top: 0px;">
   <a href="#" onclick="temporarySave()" id="temporarySaveBtn" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">暂存信息</a>
   <a href="#" onclick="returnToCheck()" id="returnToCheckBtn" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">复核不通过</a>
   <a href="#" onclick="confirmClaim()" id="confirmClaimBtn" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交结案</a>
   <a href="#" onclick="cancelTask()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload" >撤销任务</a>
   <a href="#" onclick="queryPolicyInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">保单查询</a>
   <a href="#" onclick="noteManage()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">照会管理</a>
</div>
<div style="position: absolute;  top: 50px; height: 100%;width:98%" id = "claim_main_div">
<div id="tabdiv">
	 <div id="claimInfo"  class="easyui-panel" fit="true" title="赔案信息" iconCls="icon-edit" collapsible="true" >
		  <div class="top_table">
			   <table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">理赔任务号：</td>
						<td align="left">
						<input type="hidden" id="id" name="id" value="${dbid}">
						  <input id="claimTaskNo" name="claimTaskNo" class="table_input" value="${claimTaskNo}" disabled="disabled">
						</td>
						<td class="table_text" align="right">保单号：</td>	
						  <td align="left">
						  <input id="policyNo" name="policyNo" class="table_input" value="${policyNo}" disabled="disabled">
						</td>
						<td class="table_text" align="right">是否照会：</td>	
						  <td align="left">
						  <input id="isNote" name="isNote" class="table_input" value="${isNote}" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">理赔类型：</td>
						<td align="left">
						 <input id="claimType" name="claimType" type="text" class="table_input"    disabled="disabled"></input>
						</td>
						<td class="table_text" align="right">客户申请日期：</td>	
						  <td align="left">
						  <input id="applyDate" name="applyDate" type="text" class="table_input"  value="${applyDate}"  disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">出险日期：</td>	
						  <td align="left">
						  <input id="inDangerDate" name="inDangerDate"  type="text" class="table_input" value="${inDangerDate}" disabled="disabled"></input> 
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">出险原因：</td>
						<td align="left">
                           <input id="inDangerReason" type="text" class="easyui-validatebox table_input" required="required" value="${inDangerReason}"></input>
						</td>
						<td class="table_text" align="right">疾病代码：</td>	
						  <td align="left">
						  <input id="sicknessCode" type="text" class="easyui-validatebox table_input" required="required" value="${sicknessCode}"></input>
						</td>
						<td class="table_text" align="right">疾病名称：</td>	
						  <td align="left">
						  <input id="sicknessChineseName" type="text" class="easyui-validatebox table_input"  value="${sicknessChineseName}" onclick="querySinknessPage()" disabled="disabled"></input>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">意外类型代码：</td>	
						  <td align="left">
						    <input id="accClassCode" type="text" class="easyui-validatebox table_input" required="required" value="${accClassCode}"></input>
						</td>
						<td class="table_text" align="right">意外身体代码：</td>	
						  <td align="left">
						   <input id="accBodyCode" type="text" class="easyui-validatebox table_input" required="required" value="${accBodyCode}"></input>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">领号人：</td>
						<td align="left">
                           <input id="getTaskOperator" type="text" class="table_input" value="${getTaskOperator}" disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">领号日期：</td>	
						  <td align="left">
		                    <input id="getTaskDate" type="text" class="table_input"  value="${getTaskDate}"  disabled="disabled"></input> 
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">初审人：</td>
						<td align="left">
                           <input id="checkOperator" type="text" class="table_input"  value="${checkOperator}" disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">初审日期：</td>	
						  <td align="left">
		                    <input id="checkDate" type="text" class="table_input"  value="${checkDate}" disabled="disabled"></input> 
						</td>
					</tr>
				    <tr>
						<td class="table_text" align="right">复核人：</td>
						<td align="left">
                           <input id="approveOperator" type="text" class="table_input" disabled="disabled"  value="${approveOperator}"></input> 
						</td>
						<td class="table_text" align="right">复核日期：</td>	
						  <td align="left">
		                    <input id="approveDate" type="text" class="table_input"  disabled="disabled" value="${approveDate}"></input> 
						</td>
					</tr>
				</table>
			</div>
			<a href="#" id="calculateBtn" onclick="calculate()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">计算</a>
			 赔付总金额（元）   <input id="payMoney" type="text" class="table_input" value="${payMoney}" disabled="disabled"></input> 
	</div>
	<div style="margin-top: 3px;" id="claimMoneyInfo">
	      <table id="ClaimMoneyInfoTable"></table>
    </div>
	<div style="margin-top: 3px;" id="crippleInfo">
	      <table id="CrippleInfoTable"></table>
    </div>
    <div style="margin-top: 3px;" id="aditemInfo">
	      <table id="AditemInfoTable"></table>
    </div>
    <div style="margin-top: 3px;" id="payBnfInfo">
	      <table id="PayBnfInfoTable"></table>
    </div>
</div>
<div id="tabdiv2">
	 <div id="checkresult"  class="easyui-panel"  title="初审结论" iconCls="icon-edit" collapsible="true" >
		  <div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">初审结论：</td>
						<td align="left">
						   <select id="claimResult" name="claimResult"  class="table_select" onchange="onSelectChange()">
						      <option value="">--请选择--</option>
			                  <option value="01" <c:if test="${claimResult eq '01'}">selected</c:if>>正常赔付</option>
			                  <option value="02" <c:if test="${claimResult eq '02'}">selected</c:if>>协议赔付</option>
			                  <option value="03" <c:if test="${claimResult eq '03'}">selected</c:if>>通融赔付</option>
			                  <option value="04" <c:if test="${claimResult eq '04'}">selected</c:if>>单纯拒赔</option>
			                  <option value="05" <c:if test="${claimResult eq '05'}">selected</c:if>>拒赔且解约退保费</option>
			                  <option value="06" <c:if test="${claimResult eq '06'}">selected</c:if>>拒赔且解约不退费</option>
			               </select>
						</td>
						<td class="table_text" align="right">初审状态：</td>	
						  <td align="left">
						   <select id="fcheckState" name="fcheckState"  class="table_select">
						      <option value="">--请选择--</option>
			                  <option value="01" <c:if test="${fcheckState eq '01'}">selected</c:if>>初审中</option>
			                  <option value="02" <c:if test="${fcheckState eq '02'}">selected</c:if>>调查</option>
			                  <option value="03" <c:if test="${fcheckState eq '03'}">selected</c:if>>面谈</option>
			               </select>
						</td>
						<td class="table_text" align="right">保单有效性：</td>	
						  <td align="left">
						   <select id="policyStateResult" name="policyStateResult"  class="table_select">
						      <option value="">--请选择--</option>
			                  <option value="01" <c:if test="${policyStateResult eq '01'}">selected</c:if>>有效</option>
			                  <option value="02" <c:if test="${policyStateResult eq '02'}">selected</c:if>>终止</option>
			               </select>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">初审意见：</td>
						<td align="left" colspan="4">
							<textarea cols=90 rows=2 name="fcheckOpinion" id="fcheckOpinion" 
							class="easyui-validatebox" disabled="true">${fcheckOpinion}</textarea>
						</td>
					</tr>
				</table>
		   </div>
		</div>
</div>
<div id="tabdiv3">
	 <div id="refundmentInfo"  class="easyui-panel"  title="退费信息" iconCls="icon-edit" collapsible="true" >
		  <div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">退费账户姓名：</td>
						<td align="left">
						  <input id="refAccountName" name="refAccountName" class="easyui-validatebox table_input" value="${refAccountName}"  disabled="true">
						</td>
						<td class="table_text" align="right">退费账户：</td>
						<td align="left">
						  <input id="refAccountNo" name="refAccountNo" class="easyui-validatebox table_input" value="${refAccountNo}"  disabled="true">
						</td>
						<td class="table_text" align="right">退费金额：</td>
						<td align="left">
						<input id="refMoney" name="refMoney" class="easyui-numberbox table_input" 
						  min="0" max="99999999999999" percison="2" missingMessage="金额格式不正确" value="${refMoney}" disabled="true">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">退费账户银行：</td>
						<td align="left">
					      <input class="easyui-validatebox table_select" name="refAccountBankId" id="refAccountBankId"  value="${refAccountBankIdName}" disabled="true">
						</td>
						<td class="table_text" align="right">退费开户行所在省 ：</td>
						<td align="left">
						  <input id="refBankProvinceId" name="refBankProvinceId" class="easyui-validatebox table_select" value="${refBankProvinceIdName}" disabled="true">
						</td>
						<td class="table_text" align="right">退费开户行所在市：</td>
						<td align="left">
						  <input id="refBankCityId" name="refBankCityId" class="easyui-validatebox table_select" value="${refBankCityIdName}" disabled="true">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">证件类型：</td>
						<td align="left">
						  <select id="refCardType" name="refCardType"  class="easyui-validatebox table_select" disabled="true">
						      <option value="">--请选择--</option>
			                  <option value="00" <c:if test="${refCardType eq '00'}">selected</c:if>>身份证</option>
			                  <option value="01" <c:if test="${refCardType eq '01'}">selected</c:if>>军官证</option>
			                  <option value="02" <c:if test="${refCardType eq '02'}">selected</c:if>>士兵证</option>
			                  <option value="03" <c:if test="${refCardType eq '03'}">selected</c:if>>临时身份证</option>
			                  <option value="04" <c:if test="${refCardType eq '04'}">selected</c:if>>港澳居民来往大陆通行证</option>
			                  <option value="05" <c:if test="${refCardType eq '05'}">selected</c:if>>台湾居民来往大陆通行证</option>
			                  <option value="06" <c:if test="${refCardType eq '06'}">selected</c:if>>护照</option>
			                  <option value="99" <c:if test="${refCardType eq '99'}">selected</c:if>>其他</option>
			               </select>
						</td>
						<td class="table_text" align="right">证件号码 ：</td>
						<td align="left">
						  <input id="refCardNo" name="refCardNo" class="easyui-validatebox table_input" value="${refCardNo}"  disabled="true">
						</td>
					</tr>
				</table>
		   </div>
		</div>
</div>
<div id="tabdiv4">
	 <div id="approveResult"  class="easyui-panel" fit="true" title="复核结论" iconCls="icon-edit" collapsible="true" >
		  <div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">复核意见：</td>
						<td align="left" colspan="4">
							<textarea cols=90 rows=2 name="sCheckOpinion" id="sCheckOpinion" 
							class="" >${sCheckOpinion}</textarea>
						</td>
						<td></td><td></td><td></td><td></td>
					</tr>
				</table>
		   </div>
		</div>
</div>
</div>
<input type="hidden" id="mes" name="mes" value="${mes}">
<input type="hidden" id="claimState" name="claimState" value="${claimState}">
<input type="hidden" id="claimTypeCode" name="claimTypeCode" value="${claimType}">
<input type="hidden" id="flag" name="flag" value="${flag}">

</form>



