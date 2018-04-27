<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/nb/policyDetailAfterPosInit.js"></script>
	
<script type="text/javascript">
var plans = ${plans};
var bnfClients = ${bnfClients};
var planToBnfs = ${planToBnfs};
</script>
	
<form id="policyForm">
	<div style="position: absolute; top: 0px; height: 100%; width: 98%"
		id="pos_main_div">
		<div id="tabdiv">
			<div id="taskinfo" class="easyui-panel" title="任务信息"
				iconCls="icon-ok" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="125" class="table_text" align="right">保单号</td>
							<td width="200" align="left"><input name="policyno"
								id="policyno" type="text" class="table_input"
								value="${policyno}" disabled="disabled" style="width: 150px"></td>
							<td width="125" class="table_text" align="right">保全项目</td>
							<td width="200" align="left"><input name="postype"
								id="postype" class="table_input" value="${postypename}"
								disabled="disabled" style="width: 150px"></td>
							<td width="125" class="table_text" align="right">申请日期</td>
							<td width="200" align="left"><input name="applydate"
								id="appdate" class="easyui-validatebox easyui-my97 table_input"
								value="${appdate}" disabled="disabled"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">任务状态</td>
							<td width="200" align="left"><input name="status"
								id="status" class="table_input" value="${status}"
								disabled="disabled" style="width: 150px"></td>
							<td width="125" class="table_text" align="right">当前操作人</td>
							<td width="200" align="left"><input name="operator"
								id="operator" class="table_input" value="${usercode}"
								disabled="disabled"></td>
							<td width="125" class="table_text" align="right">保全生效日期</td>
							<td width="200" align="left"><input name="effectivedate"
								id="effencientdate"
								class="easyui-validatebox easyui-my97 table_input"
								value="${effencientdate}" data-options="required:true"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">保全任务号</td>
							<td width="200" align="left"><input name="posID" id="posID"
								class="table_input" value="${posID}" disabled="disabled"
								style="width: 150px"></td>
							<td class="table_text" align="right">是否照会</td>
							<td align="left"><input id="isNote" name="isNote"
								class="table_input" value="${isNote}" disabled="disabled">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>


		<div style="margin-top: 3px;" id="tabdiv2">
			<div id="appntinfo" class="easyui-panel" fit="true" title="投保人信息"
				iconCls="icon-ok" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="125" class="table_text" align="right">姓名</td>
							<td width="200" align="left"><input name="appname"
								id="appname" class="easyui-validatebox table_input"
								data-options="required:true" value="${appClient.clientName}"
								style="width: 150px"></td>
							<td width="125" class="table_text" align="right">性别</td>
							<td width="200" align="left"><input id="appsex"
								name="appsex" data-options="required:true"
								value="${appClient.clientSex}"></td>
							<td width="125" class="table_text" align="right">国籍</td>
							<td width="200" align="left"><input id="appnationality"
								name="appnationality" value="${appClient.nationality}"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">婚姻状况</td>
							<td width="200" align="left"><input id="appmarital_status"
								name="appmarital_status" value="${appClient.maritalStatus}"></td>
							<td width="125" class="table_text" align="right">出生日期</td>
							<td width="200" align="left"><input name="appbirthday"
								id="appbirthday"
								class="easyui-validatebox easyui-my97 table_input"
								data-options="required:true" value="${appClient.clientBirthday}"></td>
							<td width="125" class="table_text" align="right">证件类型</td>
							<td width="200" align="left"><input id="appidtype"
								name="appidtype" data-options="required:true"
								value="${appClient.idType}"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">证件号码</td>
							<td width="200" align="left"><input name="appid" id="appid"
								class="easyui-validatebox table_input"
								data-options="required:true" value="${appClient.idNo}"
								style="width: 150px"></td>
							<td width="125" class="table_text" align="right">有效期</td>
							<td width="200" align="left"><input name="appexpirydate"
								id="appexpirydate"
								class="easyui-validatebox easyui-my97 table_input"
								value="${appClient.idExpiryDate}"> <br /> <input
								type="checkbox" id="apppermanent"
								${appClient.idPermanentFlag eq 1 ? "checked" : ""}>证件有效期长期标志</td>
							<td width="125" class="table_text" align="right">职业代码</td>
							<td width="200" align="left"><input id="appoccupationcode"
								name="appoccupationcode" value="${appClient.occupationCode}"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">职业名称</td>
							<td width="200" align="left"><input name="appoccupationname"
								id="appoccupationname" class="table_input"
								value="${appoccupationname}" disabled style="width: 150px"></td>
							<td width="125" class="table_text" align="right">职业等级</td>
							<td width="200" align="left"><input
								name="appoccupationlevel" id="appoccupationlevel"
								class="table_input" value="${appClient.occupationType}" disabled
								style="width: 150px"></td>
							<td width="125" class="table_text" align="right">平均年收入</td>
							<td width="200" align="left"><input name="appincome"
								id="appincome" class="table_input" value="${appClient.income}"
								style="width: 150px">万元</td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">收入来源</td>
							<td><input id="appincomesource" name="appincomesource"
								value="${appClient.incomeSource}"></td>
							<td width="125" class="table_text" align="right">Email</td>
							<td width="200" align="left"><input name="appemail"
								id="appemail" value="${appAddress.email1}" class="table_input"
								style="width: 150px"></td>
							<td width="125" class="table_text" align="right">投保人是被保人的</td>
							<td width="200" align="left"><input id="reltoapp"
								name="reltoapp" value="${relationToInsu}"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">联系回访固话</td>
							<td width="200" align="left"><input name="appphone"
								id="appphone" value="${appAddress.phone}" class="table_input"
								style="width: 150px"></td>

							<td width="125" class="table_text" align="right">联系回访手机</td>
							<td width="200" align="left"><input name="appmobile"
								id="appmobile" value="${appAddress.mobile1}" class="table_input"
								style="width: 150px"></td>
							<td width="125" class="table_text" align="right"></td>
							<td width="200" align="left"></td>
						</tr>

						<tr>
							<td width="125" class="table_text" align="right">联系地址</td>
							<td align="left" colspan="5" style="white-space: nowrap"><input
								id="appaddpro" name="appaddpro" value="${appAddress.province}">&nbsp;&nbsp;省<input
								id="appaddcity" name="appaddcity" value="${appAddress.city}">&nbsp;&nbsp;市<input
								id="appadddep" name="appadddep" value="${appAddress.country}">&nbsp;&nbsp;区
								&nbsp; <input name="appaddress" class="table_input"
								id="appaddress" value="${appAddress.address}"
								style="width: 150px;"> &nbsp; 邮编<input name="appzipcode"
								id="appzipcode" class="table_input"
								value="${appAddress.zipCode}" style="width: 80px;"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>


		<div style="margin-top: 3px;" id="tabdiv3">
			<div id="smsaccordion3" class="easyui-panel" fit="true" title="被保人信息"
				iconCls="icon-ok" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="125" class="table_text" align="right">姓名</td>
							<td width="200" align="left"><input name="insname"
								id="insname" class="easyui-validatebox table_input"
								data-options="required:true"
								value="${insuClient.clientName}" style="width: 150px"></td>
							<td width="125" class="table_text" align="right">性别</td>
							<td width="200" align="left"><input id="inssex"
								name="inssex" data-options="required:true"
								value="${insuClient.clientSex}"></td>
							<td width="125" class="table_text" align="right">国籍</td>
							<td width="200" align="left"><input id="insnationality"
								name="insnationality"
								value="${insuClient.nationality}"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">婚姻状况</td>
							<td width="200" align="left"><input id="insmarital_status"
								name="insmarital_status"
								value="${insuClient.maritalStatus}"></td>
							<td width="125" class="table_text" align="right">出生日期</td>
							<td width="200" align="left"><input name="insbirthday"
								id="insbirthday"
								class="easyui-validatebox easyui-my97 table_input"
								data-options="required:true"
								value="${insuClient.clientBirthday}"></td>
							<td width="125" class="table_text" align="right">证件类型</td>
							<td width="200" align="left"><input id="insidtype"
								name="insidtype" class="easyui-validatebox table_input"
								data-options="required:true"
								value="${insuClient.idType}"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">证件号码</td>
							<td width="200" align="left"><input name="insid" id="insid"
								class="easyui-validatebox table_input"
								data-options="required:true"
								value="${insuClient.idNo}" style="width: 150px"></td>
							<td width="125" class="table_text" align="right">有效期</td>
							<td width="200" align="left"><input name="insexpirydate"
								id="insexpirydate"
								class="easyui-validatebox easyui-my97 table_input"
								value="${insuClient.idExpiryDate}"> <br /> <input
								type="checkbox" id="inspermanent"
								${insuClient.idPermanentFlag eq 1 ? "checked" : ""}>证件有效期长期标志</td>
							<td width="125" class="table_text" align="right">职业代码</td>
							<td width="200" align="left"><input id="insoccupationcode"
								name="insoccupationcode"
								value="${insuClient.occupationCode}"></td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">职业名称</td>
							<td width="200" align="left"><input name="insoccupationname"
								id="insoccupationname" class="table_input"
								value="${insoccupationname}" disabled style="width: 150px"></td>
							<td width="125" class="table_text" align="right">职业等级</td>
							<td width="200" align="left"><input
								name="insoccupationlevel" id="insoccupationlevel"
								class="table_input"
								value="${insuClient.occupationType}" disabled
								style="width: 150px"></td>
							<td width="125" class="table_text" align="right">平均年收入</td>
							<td width="200" align="left"><input name="insincome"
								id="insincome" class="table_input"
								value="${insuClient.income}" style="width: 150px">万元</td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">收入来源</td>
							<td><input id="insincomesource" name="insincomesource"
								value="${insuClient.incomeSource}"></td>
							<td width="125" class="table_text" align="right">Email</td>
							<td width="200" align="left"><input name="insemail"
								id="insemail" value="${insuAddress.email1}"
								class="table_input" style="width: 150px"></td>
							<td width="125" class="table_text" align="right">是否有社保标记</td>
							<td width="200" align="left"><input id="sbflag"
								name="sbflag" value="${insuClient.socialSecurityFlag}">
							</td>
						</tr>
						<tr>
							<td width="125" class="table_text" align="right">联系回访固话</td>
							<td width="200" align="left"><input name="insphone"
								id="insphone" value="${insuAddress.phone}"
								class="table_input" style="width: 150px"></td>

							<td width="125" class="table_text" align="right">联系回访手机</td>
							<td width="200" align="left"><input name="insmobile"
								id="insmobile" value="${insuAddress.mobile1}"
								class="table_input" style="width: 150px"></td>
							<td width="125" class="table_text" align="right"></td>
							<td width="200" align="left"></td>
						</tr>

						<tr>
							<td width="125" class="table_text" align="right">联系地址</td>
							<td align="left" colspan="5" style="white-space: nowrap"><input
								id="insaddpro" name="insaddpro"
								value="${insuAddress.province}">&nbsp;&nbsp;省<input
								id="insaddcity" name="insaddcity"
								value="${insuAddress.city}">&nbsp;&nbsp;市<input
								id="insadddep" name="insadddep"
								value="${insuAddress.country}">&nbsp;&nbsp;区
								&nbsp; <input name="insaddress" class="table_input"
								id="insaddress" value="${insuAddress.address}"
								style="width: 150px;"> &nbsp; 邮编<input name="inszipcode"
								id="inszipcode" class="table_input"
								value="${insuAddress.zipCode}" style="width: 80px;">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="margin-top: 3px;" id="benDiv2">
			<table id="bnfTable"></table>
		</div>
		
		
		<%@include file="../pos/disPlayRemark.jsp" %>
		<div id="hiddenDiv">
			<input type="hidden" name="postype" id="postype" value="${postype}" />
			<input type="hidden" name="type" id="type" value="${type}" /> <input
				type="hidden" name="bnfflag" id="bnfflag" value="${law}" />
		</div>
	</div>
</form>