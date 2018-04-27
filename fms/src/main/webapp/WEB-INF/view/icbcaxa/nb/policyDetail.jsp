<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/nb/policyDetailInit.js"></script>
<script type="text/javascript">
var agentData = ${AGENTINFO};
var planData = ${PLANINFO};
var bnfData = ${BNFINFO};
var bnfPlanData = ${BNFPLANINFO};
</script>


<div id="tabdiv1" style="width: 98.5%">
	<div id="taskinfo1" class="easyui-panel"  title="合同信息" iconCls="icon-ok" collapsible="true">
		<div class="top_table">
		 
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
			  <tr>
			  	<td  class="table_text">保单号</td>
			  	<td><input name="policyno" id="policyno" type="text" class="table_input" value="${POLICY.policyNo}" disabled="disabled"></td>
			  	<td  class="table_text">投保来源</td>
			  	<td>
			  	 <select class="easyui-combobox" name="policysource" id="policysource" style="width: 155px;" disabled="disabled">
					<option value=${"EYRDO" } selected="selected">${"EYRDO"}</option>
				 </select>
				</td>
			  	<td  class="table_text">投保单递交日期</td>
				<td><input name="applicationSubmitDate" id="applicationSubmitDate" class="easyui-datebox" value="${POLICY.applicationSignDate}" disabled="disabled"></td>
			  </tr>	
			  <tr>
			    <td  class="table_text">投保单签署日期</td>
			    <td><input name="applicationSignDate" id="applicationSignDate" class="easyui-datebox" value="${POLICY.applicationSignDate}" disabled="disabled"></td>
			  	<td  class="table_text">投保单录入日期</td>
			    <td><input name="applicationInputDate" id="applicationInputDate" class="easyui-datebox" value="${POLICY.applicationSignDate}" disabled="disabled"></td>
			    <td  class="table_text">竞赛代码</td>
			    <td><input name="jscode" id="jscode" type="text" class="table_input" value="" disabled="disabled"></td>
			  </tr>
			  <tr>
			    <td  class="table_text">承保分公司</td>
			    <td><input name="issueCompany" id="issueCompany" type="text" class="table_input" value="${ISSUECOMPANY.companyCode}-${ISSUECOMPANY.companyName}" disabled="disabled"></td>
			  	<td  class="table_text">保单状态</td>
			  	<td><input name="policyStatus" id="policyStatus" type="text" class="table_input" value="${POLICY.policyStatus eq '1' ? '承保':POLICY.policyStatus eq '0' ? '投保':POLICY.policyStatus eq '2' ? '失效':POLICY.policyStatus eq '4' ? '终止':''}" disabled="disabled"></td>
			  </tr>
			</table>
		</div>
	</div>
</div>

<div style="margin-top: 3px;" id="benDiv1">
	<table id="agentTable"></table>
</div>

<div id="tabdiv2" style="width: 98.5%; margin-top: 3px;">
	<div id="taskinfo2" class="easyui-panel" title="投保人信息" iconCls="icon-ok" collapsible="true">
		<div class="top_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			  	<td  class="table_text">姓名</td>
			  	<td><input name="appntName" id="appntName" type="text" class="table_input" value="${APPNT.clientName}" disabled="disabled"></td>
				<td  class="table_text">性别</td>
				<td>
				 <select class="easyui-combobox" name="appntSex" id="appntSex" style="width: 155px;" disabled="disabled">
					<option value='${APPNT.clientSex}' selected="selected">${APPNT.clientSex eq 'M' ? '男':APPNT.clientSex eq 'F' ? '女':APPNT.clientSex eq 'U' ? '男女':''}</option>
				 </select>
				</td>
			  	<td  class="table_text">国籍</td>
			  	<td>
				 <select class="easyui-combobox" name="appntNationality" id="appntNationality" style="width: 155px;" disabled="disabled">
					<option value='${APPNTNATIONALITY.id}' selected="selected">${APPNTNATIONALITY.chineseShortName}</option>
				 </select>
				</td>
			  </tr>
			  <tr>
			  <td  class="table_text">婚姻状况</td>
				<td>
				 <select class="easyui-combobox" name="appntMarry" id="appntMarry" style="width: 155px;" disabled="disabled">
					<option value="${APPNT.maritalStatus }" selected="selected">${APPNT.maritalStatus eq 'S' ? '未婚':APPNT.maritalStatus eq 'M' ? '已婚':APPNT.maritalStatus eq 'D' ? '离异':APPNT.maritalStatus eq 'W' ? '鳏寡':''}</option>
				 </select>
				</td>
			  	<td  class="table_text">出生日期</td>
			  	<td><input name="appntBirthday" id="appntBirthday" class="easyui-datebox" value="${APPNT.clientBirthday}" disabled="disabled"></td>
			  	<td  class="table_text">单位名称/就读学校</td>
			  	<td><input name="appntDWORSCH" id="appntDWORSCH" type="text" class="table_input" value="" disabled="disabled"></td>
			  </tr>
			  <tr>
			  	<td  class="table_text">证件类型</td>
			  	<td>
				 <select class="easyui-combobox" name="appntIDType" id="appntIDType" style="width: 155px;" disabled="disabled">
					<option value="${APPNT.idType }" selected="selected">${APPNT.idType eq '00' ? '身份证' : APPNT.idType eq '06' ? '护照': APPNT.idType eq '01' ? '军官证': APPNT.idType eq '02' ? '士兵证': APPNT.idType eq '04' ? '港澳居民来往大陆通行证': APPNT.idType eq '03' ? '临时身份证': APPNT.idType eq '05' ? '台湾居民来往大陆通行证': APPNT.idType eq '99' ? '其他':''}</option>
				 </select>
				</td>
			  	<td  class="table_text">证件号码</td>
				<td><input name="appntIDNo" id="appntIDNo" type="text" class="table_input" value="${APPNT.idNo}" disabled="disabled"></td>
			  	<td  class="table_text">证件有效期至</td>
			  	<td>
			  		<input name="appntIdExpiryDate" id="appntIdExpiryDate" class="easyui-datebox" value="${APPNT.idExpiryDate}" disabled="disabled">
			  		<br/><input type="checkbox" id="appntIdPermanentFlag" name="appntIdPermanentFlag" ${APPNT.idPermanentFlag eq '1'? 'checked': '' } disabled="disabled">证件有效期长期标志位
			  	</td>
			  </tr>
			  <tr>
			  	<td  class="table_text">职业代码</td>
				<td>
				 <select class="easyui-combobox" name="appntOccupationCode" id="appntOccupationCode" style="width: 155px;" disabled="disabled">
					<option value="${APPNTOCCUPATION.id }" selected="selected">${APPNTOCCUPATION.occupationCode }</option>
				 </select>
				</td>
			  	<td  class="table_text">职业名称</td>
			  	<td><input name="appntOccupationName" id="appntOccupationName" type="text" class="table_input" value="${APPNTOCCUPATION.occupationName}" disabled="disabled"></td>
			  	<td  class="table_text">职业等级</td>
			  	<td><input name="appntOccupationClass" id="appntOccupationClass" type="text" class="table_input" value="${APPNT.occupationType}" disabled="disabled"></td>
			  </tr>
			  <tr>
			  	<td  class="table_text">平均年收入</td>
			  	<td><input name="appntIncome" id="appntIncome" type="text" class="table_input" value="${APPNT.income}" disabled="disabled">万元</td>
			  	<td  class="table_text">收入来源</td>
			  	<td>
			  	<select class="easyui-combobox" name="appntIncomeSource" id="appntIncomeSource" style="width: 155px;" disabled="disabled">
					<option value="${APPNT.incomeSource }" selected="selected">${APPNT.incomeSource eq '00' ? '工资': APPNT.incomeSource eq '01' ? '奖金/分红': APPNT.incomeSource eq '02' ? '遗产': APPNT.incomeSource eq '03' ? '房租': APPNT.incomeSource eq '04' ? '股票/债券': APPNT.incomeSource eq '05' ? '其他':''}</option>
				 </select>
			    </td> 
			    <td  class="table_text">联系回访手机</td>
			    <td><input name="appntMobile" id="appntMobile" type="text" class="table_input" value="${APPNTADDRESS.mobile1 }" disabled="disabled"></td>
			 </tr>
			 <tr>
			 	<td  class="table_text">联系回访固话</td>
			    <td><input name="appntPhone" id="appntPhone" type="text" class="table_input" value="${APPNTADDRESS.phone }" disabled="disabled"></td>
			    <td  class="table_text">电子邮箱</td>
			    <td><input name="appntEmail" id="appntEmail" type="text" class="table_input" value="${APPNTADDRESS.email1 }" disabled="disabled"></td>
			    <td  class="table_text">投被保人关系</td>
			    <td>
			      <select class="easyui-combobox" name="appntToInsuredRelation" id="appntToInsuredRelation" disabled="disabled" style="width: 155px;">
					<option value="${PLAN[0].relationToApplication }" selected="selected">${PLAN[0].relationToApplication eq '0' ? '本人' : PLAN[0].relationToApplication eq '2' ? '配偶' : PLAN[0].relationToApplication eq '3' ? '子女' : PLAN[0].relationToApplication eq '1' ? '父母' : PLAN[0].relationToApplication eq '99' ? '其他' : PLAN[0].relationToApplication eq '4' ? '祖父母、外祖父母': PLAN[0].relationToApplication eq '7' ? '监护人' : PLAN[0].relationToApplication eq '6' ? '兄弟姐妹' : PLAN[0].relationToApplication eq '5' ? '孙子女、外孙子女' : ''}</option>
				 </select>
			    </td>
			 </tr>
			 <tr>
			 	<td  class="table_text">联系地址</td>
			 	<td colspan="6">
			 	  <select class="easyui-combobox" name="appntProvince" id="appntProvince" disabled="disabled" style="width: 120px;">
					<option value="${APPNTADDRESSPRO.regionCode }" selected="selected">${APPNTADDRESSPRO.regionName }</option>
				 </select>&nbsp;省
				 <select class="easyui-combobox" name="appntCity" id="appntCity" disabled="disabled" style="width: 120px;">
					<option value="${APPNTADDRESSCIT.regionCode }" selected="selected">${APPNTADDRESSCIT.regionName }</option>
				 </select>&nbsp;市
				 <select class="easyui-combobox" name="appntCountry" id="appntCountry" disabled="disabled" style="width: 120px;">
					<option value="${APPNTADDRESSCOU.regionCode }" selected="selected">${APPNTADDRESSCOU.regionName }</option>
				 </select>&nbsp;区
				 
				 <input name="appntAddress" id="appntAddress" type="text" class="table_input" value="${APPNTADDRESS.address }" disabled="disabled" 
				 style="width: 300px; margin-top: 10px;" >
				 
				 &nbsp;邮政编码<input name="appntZipCode" id="appntZipCode" type="text" class="table_input" value="${APPNTADDRESS.zipCode }" disabled="disabled" 
				 style="width: 50px; margin-top: 10px;" >
			 	</td>
			 </tr>
			</table>
		</div>
	</div>
</div>

<c:forEach var="insured" begin="0" varStatus="status" items="${INSURED }">
<c:set value="${status.index }" var="i_x"></c:set>
<div id="tabdiv_insured${i_x}" style="margin-top: 3px;">
	<div id="taskinfo_insured${i_x}" class="easyui-panel" fit="true" title="被保人信息" iconCls="icon-ok" collapsible="true">
		<div class="top_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td  class="table_text">姓名</td>
			    <td><input name="insuredName" id="insuredName" type="text" class="table_input" value="${insured.clientName}" disabled="disabled"></td>
			  	<td  class="table_text">性别</td>
				<td>
				 <select class="easyui-combobox" name="insuredSex" id="insuredSex" style="width: 155px;" disabled="disabled">
					<option value='${APPNT.clientSex}' selected="selected">${insured.clientSex eq 'M' ? '男': insured.clientSex eq 'F' ? '女': insured.clientSex eq 'U' ? '男女':''}</option>
				 </select>
				</td>
				<td  class="table_text">国籍</td>
			  	<td>
				 <select class="easyui-combobox" name="insuredNationality" id="insuredNationality" style="width: 155px;" disabled="disabled">
					<option value='${INSUREDNATIONALITY[i_x].id}' selected="selected">${INSUREDNATIONALITY[i_x].chineseShortName}</option>
				 </select>
				</td>
			  </tr>
			  <tr>
			  	<td  class="table_text">婚姻状况</td>
				<td>
				 <select class="easyui-combobox" name="insuredMarry" id="insuredMarry" style="width: 155px;" disabled="disabled">
					<option value="${insured.maritalStatus }" selected="selected">${insured.maritalStatus eq 'S' ? '未婚': insured.maritalStatus eq 'M' ? '已婚': insured.maritalStatus eq 'D' ? '离异': insured.maritalStatus eq 'W' ? '鳏寡':''}</option>
				 </select>
				</td>
				<td  class="table_text">出生日期</td>
			  	<td><input name="insuredBirthday" id="insuredBirthday" class="easyui-datebox" value="${insured.clientBirthday}" disabled="disabled"></td>
			  	<td  class="table_text">单位名称/就读学校</td>
			  	<td><input name="insuredDWORSCH" id="insuredDWORSCH" type="text" class="table_input" value="" disabled="disabled"></td>
			  </tr>
			  <tr>
			  <td  class="table_text">证件类型</td>
			  	<td>
				 <select class="easyui-combobox" name="insuredIDType" id="insuredIDType" style="width: 155px;" disabled="disabled">
					<option value="${insured.idType }" selected="selected">${insured.idType eq '00' ? '身份证' :  insured.idType eq '06' ? '护照': insured.idType eq '01' ? '军官证': insured.idType eq '02' ? '士兵证': insured.idType eq '04' ? '港澳居民来往大陆通行证': insured.idType eq '03' ? '临时身份证': insured.idType eq '05' ? '台湾居民来往大陆通行证': insured.idType eq '99' ? '其他':''}</option>
				 </select>
				</td>
				<td  class="table_text">证件号码</td>
				<td><input name="insuredIDNo" id="insuredIDNo" type="text" class="table_input" value="${insured.idNo}" disabled="disabled"></td>
			  	<td  class="table_text">证件有效期至</td>
			  	<td>
			  		<input name="insuredIdExpiryDate" id="insuredIdExpiryDate" class="easyui-datebox" value="${insured.idExpiryDate}" disabled="disabled">
			  		<br/><input type="checkbox" id="insuredIdPermanentFlag" name="insuredIdPermanentFlag" ${insured.idPermanentFlag eq '1'? 'checked': '' } disabled="disabled">证件有效期长期标志位
			  	</td>
			  </tr>
			  
			  <tr>
			  <td  class="table_text">平均年收入</td>
			  <td><input name="insuredIncome" id="insuredIncome" type="text" class="table_input" value="${insured.income}" disabled="disabled">万元</td>
			  <td  class="table_text">收入来源</td>
			  	<td>
			  	<select class="easyui-combobox" name="insuredIncomeSource" id="insuredIncomeSource" style="width: 155px;" disabled="disabled">
					<option value="${insured.incomeSource }" selected="selected">${insured.incomeSource eq '00' ? '工资': insured.incomeSource eq '01' ? '奖金/分红': insured.incomeSource eq '02' ? '遗产': insured.incomeSource eq '03' ? '房租': insured.incomeSource eq '04' ? '股票/债券': insured.incomeSource eq '05' ? '其他':''}</option>
				 </select>
			    </td>
			   <td  class="table_text">是否有社保标记</td>
			   <td>
				 <select class="easyui-combobox" name="insuredScocialFlag" id="insuredScocialFlag" style="width: 155px;" disabled="disabled">
					<option value="${insured.socialSecurityFlag}" selected="selected">${insured.socialSecurityFlag eq '0' ? '没有' : insured.socialSecurityFlag eq '1' ? '有' : insured.socialSecurityFlag eq null ? '没有' : ''}</option>
				 </select>
				</td> 
			  </tr>
			  <tr>
			  	<td  class="table_text">联系回访手机</td>
			    <td><input name="insuredMobile" id="insuredMobile" type="text" class="table_input" value="${INSUREDADDRESS[i_x].mobile1 }" disabled="disabled"></td>
			  	<td  class="table_text">联系回访固话</td>
			    <td><input name="insuredPhone" id="insuredPhone" type="text" class="table_input" value="${INSUREDADDRESS[i_x].phone }" disabled="disabled"></td>
			  	 <td  class="table_text">电子邮箱</td>
			    <td><input name="insuredEmail" id="insuredEmail" type="text" class="table_input" value="${INSUREDADDRESS[i_x].email1 }" disabled="disabled"></td>
			  </tr>
			  <tr>
			  	<td  class="table_text">联系地址</td>
			 	<td colspan="6">
			 	  <select class="easyui-combobox" name="insuredProvince" id="insuredProvince" disabled="disabled" style="width: 120px;">
					<option value="${INSUREDADDRESSPRO[i_x].regionCode }" selected="selected">${INSUREDADDRESSPRO[i_x].regionName }</option>
				 </select>&nbsp;省
				 <select class="easyui-combobox" name="insuredCity" id="insuredCity" disabled="disabled" style="width: 120px;">
					<option value="${INSUREDADDRESSCIT[i_x].regionCode }" selected="selected">${INSUREDADDRESSCIT[i_x].regionName }</option>
				 </select>&nbsp;市
				 <select class="easyui-combobox" name="insuredCountry" id="insuredCountry" disabled="disabled" style="width: 120px;">
					<option value="${INSUREDADDRESSCOU[i_x].regionCode }" selected="selected">${INSUREDADDRESSCOU[i_x].regionName }</option>
				 </select>&nbsp;区
				 
				 <input name="insuredAddress" id="insuredAddress" type="text" class="table_input" value="${INSUREDADDRESS[i_x].address }" disabled="disabled" 
				 style="width: 300px; margin-top: 10px;" >
				 
				  &nbsp;邮政编码<input name="insuredZipCode" id="insuredZipCode" type="text" class="table_input" value="${INSUREDADDRESS[i_x].zipCode }" disabled="disabled" 
				 style="width: 50px; margin-top: 10px;" >
			 	</td>
			  </tr>
			</table>
		</div>
	</div>
</div>
</c:forEach>

<div style="margin-top: 3px;" id="benDiv2">
	<table id="bnfTable"></table>
</div>

<c:forEach var="plan" begin="0" varStatus="status" items="${PLAN }">
<c:set value="${status.index }" var="i_x"></c:set>
<div id="tabdiv_plan${i_x}" style="margin-top: 3px;">
	<div id="taskinfo_plan${i_x }" class="easyui-panel" fit="true" title="${i_x eq 0 ? '主险信息' : '附加险信息'}" iconCls="icon-ok" collapsible="true">
		<div class="top_table">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
		 	<tr>
		 	 <td  class="table_text">险种代码</td>
			  	<td colspan="5">
				 <select class="easyui-combobox" name="riskCode" id="riskCode" style="width: 155px;" disabled="disabled">
					<option value='${PRODUCT[i_x].id}' selected="selected">${PRODUCT[i_x].productCode}</option>
				 </select>
				</td>
		 	</tr>
		 	<tr>
		 		<td  class="table_text">险种名称</td>
		 		<td><input name="riskName" id="riskName" type="text" class="table_input" value="${PRODUCT[i_x].productName}" disabled="disabled"></td>
		 		<td  class="table_text">交费年期</td>
		 		<td><input name="paidupyear" id="paidupyear" type="text" class="table_input" value="" disabled="disabled"></td>
		 		<td  class="table_text">交费年期单位</td>
		 		<td><input name="paidupyear" id="paidupyear" type="text" class="table_input" value="" disabled="disabled"></td>
		 	</tr>
		 	<tr>
		 		<td  class="table_text">保险年期</td>
		 		<td><input name="insuYear" id="insuYear" type="text" class="table_input" value="${plan.insuYear }" disabled="disabled"></td>
		 		<td  class="table_text">保险年期单位</td>
		 		<td><input name="insuYearFlag" id="insuYearFlag" type="text" class="table_input" value="${plan.insuYearFlag eq 'D' ? '日': plan.insuYearFlag eq 'M' ? '月': plan.insuYearFlag eq 'Q' ? '季': plan.insuYearFlag eq 'S' ? '半年': plan.insuYearFlag eq 'Y' ? '年':''}" disabled="disabled"></td>
				
		 		<td  class="table_text">自动续保标记</td>
				<td>
				 <select class="easyui-combobox" name="autoRenewFlag" id="autoRenewFlag" style="width: 155px;" disabled="disabled">
					<option value='${plan.autoRenewFlag}' selected="selected">${plan.autoRenewFlag eq '0' ? '不自动续保' : plan.autoRenewFlag eq '1' ? '自动续保':''}</option>
				 </select>
				</td>
		 	</tr>
		 	<tr>
		 		<td  class="table_text">保费</td>
		 		<td><input name="currentModePremium" id="currentModePremium" type="text" class="table_input" value="${plan.currentModePremium}" disabled="disabled">元</td>
		 		<td  class="table_text">保额</td>
				<td><input name="sumAssured" id="sumAssured" type="text" class="table_input" value="${plan.sumAssured}" disabled="disabled">元</td>
		 		<td  class="table_text">份数</td>
		 		<td><input name="unit" id="unit" type="text" class="table_input" value="${plan.unit}" disabled="disabled">份</td>
		 	</tr>
		 	<tr>
		 	    <td  class="table_text">红利领取方式</td>
		 	    <td>
				 <select class="easyui-combobox" name="dividendOption" id="dividendOption" style="width: 155px;" disabled="disabled">
					<option value='${plan.dividendOption }' selected="selected">${plan.dividendOption eq '1' ? '现金给付': plan.dividendOption eq '2' ? '支付保险费': plan.dividendOption eq '3' ? '累积生息': plan.dividendOption eq '5' ? '直接投资':''}</option>
				 </select>
				</td>
		 		<td  class="table_text">生存金领取方式</td>
		 	    <td>
				 <select class="easyui-combobox" name="endowmentOption" id="endowmentOption" style="width: 155px;" disabled="disabled">
					<option value='${plan.endowmentOption }' selected="selected">${plan.endowmentOption eq '1' ? '现金给付': plan.endowmentOption eq '2' ? '支付保险费': plan.endowmentOption eq '3' ? '累积生息': plan.endowmentOption eq '5' ? '直接投资':''}</option>
				</select>
				</td>
		 		<td  class="table_text">保费逾期自动选择</td>
		 		<td>
				 <select class="easyui-combobox" name="nfoOption" id="nfoOption" style="width: 155px;" disabled="disabled">
					<option value='${plan.nfoOption }' selected="selected">${plan.nfoOption eq 'A' ? '自动垫交': plan.nfoOption eq 'L' ? '终止合同': plan.nfoOption eq 'X' ? '不适用':''}</option>
				 </select>
				</td>
		 	</tr>
		 	<tr>
		 		<td  class="table_text">缴费频率</td>
		 		<td>
		 			<select class="easyui-combobox" name="nfoOption" id="nfoOption" style="width: 155px;" disabled="disabled">
					<option value='${1}' selected="selected">${'趸交'}</option>
				 </select>
		 		</td>
		 		<td  class="table_text">险种状态</td>
		 		<td>
		 			<input name="riskStatus" id="riskStatus" type="text" class="table_input" value="${plan.planStatus eq '4' ? '终止' : plan.planStatus eq '2' ? '失效' : plan.planStatus eq '1' ? '承保' : plan.planStatus eq '0' ? '投保' : ''  }" disabled="disabled">
		 		</td>
		 	</tr>
		 </table>
		</div>
	</div>
</div>		
</c:forEach>
