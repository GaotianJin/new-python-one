<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/addCustomerInit.js"></script>
<input type="hidden" name="loadFlag" id="addCust_loadFlag" value="${loadFlag}">
<input type="hidden" name="tradeId" id="addCust_tradeId" value="${tradeId}">
<input type="hidden" name="rolePivilege" id="addCust_rolePivilege" value="${rolePivilege}">
<input type="hidden" name="agentId" id="addCust_agentId" value="${agentId}">
<div id="tabdiv">
	<div id="addCust_CustBaseInfo">
		<form id="addCust_CustBaseInfoForm">
			<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">客户号</td>
							<td>
								<!-- <span class="comboSpan"></span>
								<input name="customerNo" id="addCust_CustomerNo" class="table_input"/> -->
								<input type="hidden" name="custBaseInfoId" id="addCust_CustBaseInfoId" class="inpuntHidden"value="${custBaseInfoId}"/>
								<input name="customerNo" id="addCust_CustomerNo" class="table_input" readonly="true" />
								<span id="addCust_searchCustButton">
									<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="openSearchCustWindow()"></a>
								</span>
							</td>
							<td class="table_text" align="right">中文姓名</td>
							<td align="left">
								<input name="chnName" id="addCust_ChnName" class="table_input easyui-validatebox" 
									data-options="required:true,validType:['length[0,50]']"/></td>
							<td class="table_text" align="right">英文姓</td>
							<td align="left">
								<input class = "table_input easyui-validatebox" name="lastName"  id = "addCust_LastName" 
									data-options="required:true,validType:['length[0,50]','validEn']"/></td>
						</tr>
						<tr>
							<td class="table_text" align="right">英文名</td>
							<td>
								<input name="firstName" id="addCust_FirstName" class="table_input easyui-validatebox"
									data-options="required:true,validType:['length[0,50]','validEn']"></td>
							<td class="table_text" align="right">性别</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="sex" id="addCust_Sex" class="table_input easyui-combobox1"/>
								<input type="hidden" name="sexName" id="addCust_SexName" class="inpuntHidden"/>
							</td>
							<td class="table_text" align="right">证件类型</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="idType" id="addCust_IdType" class="table_input easyui-combobox1">
								<input type="hidden" name="idTypeName" id="addCust_IdTypeName" class="inpuntHidden"/>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">证件号码</td>
							<td align="left">
								<input class = "table_input easyui-validatebox" name="idNo"  id = "addCust_IdNo" 
									data-options="required:true,validType:['length[0,20]']" onBlur="verifyIdNo()"/></td>
							<td class="table_text" align="right">出生日期</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input type="hidden" name="age" id="addCust_CustAge" class="inpuntHidden"/>
								<input class="easyui-datebox table_input" name="birthday" id="addCust_Birthday" 
									data-options="editable:false,validType:['validDate']"/>
							</td>
							<td class="table_text" align="right">证件有效期</td>
							<td>
								<span class="comboSpan"></span>
								<input name="idValidityDate"  id = "addCust_idValidityDate" class="table_input easyui-datebox"/>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">国籍</td>
							<td>
								<span class="comboSpan"></span>
								<input name="nativePlace"  id = "addCust_NativePlace" class="table_input easyui-combobox1"/>
							</td>
							<td class="table_text" align="right">是否有驾照</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="drivingLicense"  id = "addCust_DrivingLicense" class="table_input easyui-combobox1"/>
							</td>
							<td class="table_text" align="right">&nbsp;</td>
							<td align="left">&nbsp;</td>
						</tr>
					</table>
					<div id='addCust_CustBaseInfoButton'>
						<a href="#" id='addCust_CustBaseInfoSubmitButton' onclick="submitCustBaseInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
						<!-- <a href="#" onclick="getCustDetailInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">获取详细信息</a> -->
					</div>
					<div class="tableOuterDiv"></div>
				</div>
			</div>
		</form>
	</div>
	
	<div id="addCust_CustContactInfo">
		<form id="addCust_CustContactInfoForm">
			<div id="smsaccordion" class="easyui-panel" title="联系信息" collapsible="true">
				<div class="top_table">
					<table id="addCust_custContactInfoTable"></table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td  class="table_text" align="right" >固定电话</td>
							<td align="left">
								<input name="phone" id="addCust_Phone" class="table_input easyui-validatebox"
									data-options="validType:['length[0,20]','validTel']"/></td>
							<td  class="table_text" align="right" >手机</td>
							<td align="left">
								<input name="mobile" id="addCust_Mobile" class="table_input easyui-validatebox"
									data-options="required:true,validType:['length[0,20]','validPhone']"/></td>
							<td  class="table_text" align="right" >E-Mail</td>
							<td align="left">
								<input class = "table_input easyui-validatebox" name="email"  id = "addCust_Email" 
									data-options="required:true,validType:['length[0,50]','email']"/></td>
						</tr>
						<tr>
							<td  class="table_text" align="right" >QQ号</td>
							<td align="left">
								<input name="qq" id="addCust_Qq" class="table_input easyui-validatebox"
									data-options="validType:['length[0,20]','validQQ']"></td>
							<td  class="table_text" align="right" >微信号</td>
							<td align="left">
								<input name="wechat" id="addCust_Webchat" class="table_input easyui-validatebox"
									data-options="validType:['length[0,20]','validCode']"/></td>
							<td  align="right"></td>
							<td align="left"></td>
						</tr>
					</table>
					<div class="tableOuterDiv"></div>
					<table id="customerAddressTable"></table>
					<div id="customerAddressTable_tb" style="height:auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="custAddressTableAddOneRow()">新增</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="custAddressTableRemoveOneRow()">删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="custAddressTableLockOneRow()">锁定</a>
					</div>
					<!-- <div style="margin-bottom: 3px;" id='addCust_CustContactInfoButton'>
						<a href="#" onclick="submitCustContactAndAddressInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
					</div> -->
				</div>
			</div>
		</form>
	</div>
	<div class="tableOuterDiv"></div>
	<div id="smsaccordion" class="easyui-panel" title="账户信息" collapsible="true">
		<table id="customerAccountTable"></table>
		<div id="customerAccountTable_tb" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="custAccountTableAddOneRow()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="custAccountTableRemoveOneRow()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="custAccountTableLockOneRow()">锁定</a>
		</div>
		<!-- <div style="margin-bottom: 3px;" id="customerAccountTableButton">
			<a href="#" onclick="submitCustAccInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
		</div> -->
	</div> 
	<div class="tableOuterDiv"></div>
	<form id="addCust_CustPersonalInfoForm">
	<div id="addCust_CustPersonalInfo">
			<div id="smsaccordion" class="easyui-panel" title="个人信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right" >客户类型</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="custType" id="addCust_CustType" class="table_input easyui-combobox1"></td>
							<!-- <td class="table_text" align="right" >客户居住楼盘</td>
							<td align="left">
								<span class="comboSpan"></span> 
								<input name="buildingName" id="addCust_BuildingName" class="table_input  easyui-combobox1"></td> -->
							<td class="table_text" align="right" >人生阶段</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="lifeStage"  id="addCust_LifeStage" class="table_input easyui-combobox1"/>
							</td>
						
							<td class="table_text" align="right" >婚姻状况</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="marriage" id="addCust_Marriage" class="table_input easyui-combobox1"/>
							</td>
							</tr>
						<tr>
							<td class="table_text" align="right" >教育水平</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="degree" id="addCust_Degree" class="table_input easyui-combobox1"/>
							</td>
							
							<td class="table_text" align="right" >身高(cm)</td>
							<td align="left">
								<input name="height" id="addCust_Height" class="table_input easyui-numberbox"
									data-options="min:0,precision:2"/></td>
						
							<td class="table_text"  align="right" >体重(kg)</td>
							<td align="left">
								<input name="weight" id="addCust_Weight" class="table_input easyui-numberbox"
									data-options="min:0,precision:2"/></td>
						</tr>
						<tr>
							<td class="table_text" align="right" >行业</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="workType" id="addCust_WorkType" class="table_input easyui-combobox1"/>
							</td>
							
							<td class="table_text"  align="right" >职业</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="occupationCode" id="addCust_OccupationCode" class="table_input easyui-combobox1"/>
							</td>
						
							<td class="table_text" align="right" >职务</td>
							<td align="left">
								<!-- <span class="comboSpan"></span> -->
								<input name="position" id="addCust_Position" class="table_input easyui-validatebox"/>
							</td>
							</tr>
						<tr>
							<td  class="table_text"  align="right" >工作单位</td>
							<td align="left">
								<input name="companyName" id="addCust_CompanyName" class="table_input easyui-validatebox"
									data-options="validType:['length[0,200]']"/></td>
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="left">&nbsp;</td>
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="right">&nbsp;</td>
						</tr>
						<tr>
							<td class="table_text" align="right" >兴趣爱好</td>
							<td align="left" valign="middle" colspan=5>
								<!-- <span class="comboSpan"></span>
								<input name="hobby" id="hobby" class="table_input"/> -->
								<div id="hobbyCheckBox"></div>
							</td>			
						</tr>
					</table>
				</div>
				<!-- <div>
					<a href="#" onclick="submitCustPersonalInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
				</div>
				<br> -->
			</div>	
	</div>
	<div class="tableOuterDiv"></div>
	<div id="addCust_custAssetInfo">
		<!-- <form id="addCust_custAssetInfoForm"> -->
			<div id="smsaccordion" class="easyui-panel" title="个人财富状况" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right" >年收入（万元）</td>
							<td align="left"><input name="annualIncome" id="addCust_AnnualIncome" 
								class="table_input easyui-numberbox" data-options="min:0,precision:2"/>
							</td>
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="left" >&nbsp;</td>	
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="left" >&nbsp;</td>				
						</tr>
						<tr>
							<td class="table_text" align="right" >收入来源</td>
							<td align="left" colspan=5>
								<div id="incomeSource" class="createTable"></div>
							</td>				
						</tr>
						<tr>
							<td class="table_text"  align="right" >可投资资金（万元）</td>
							<td align="left"><input name="investMoney" id="addCust_InvestMoney" 
								class="table_input easyui-numberbox" data-options="min:0,precision:2"/>
							</td>
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="left">&nbsp;</td>
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="left">&nbsp;</td>
						</tr>
						<tr>
							<td class="table_text" align="right" >目前资产构成 </td>
							<td align="left" colspan=5>
								<div id="assetCodeCheckBox" class="createTable"></div>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right" >投资风险偏好</td>
							<td align="left"><input name="ventureCapital" id="addCust_VentureCapital" class="table_input"/></td>
							<td class="table_text"  align="right" >投资规模（万元）</td>
							<td align="left">
								<input name="investScale" id="addCust_InvestScale" class="table_input easyui-numberbox"
									data-options="min:0,precision:2"/></td>	
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="left">&nbsp;</td>		
						</tr>
						
						<tr>
							<td class="table_text" align="right" >投资金融产品类型 </td>
							<td align="left" colspan=5>
								<div id="investCheckbox"></div>
							</td>				
						</tr>
						<tr>
							<td class="table_text" align="right" >投资过往经历 </td>
							<td align="left" colspan=5>
								<span class="comboSpan"></span>
								<textarea name="investExp" id="addCust_InvestExp" class="table_textarea"cols="80" rows="2"></textarea>
							</td>
						</tr>
						<tr>
							<td class="table_text"  align="right" >备注</td>
							<td align="left" colspan = 5>
								<span class="comboSpan"></span>
								<textarea name="remark" id="addCust_Remark" class="table_textarea" cols="80" rows="2"></textarea>
							</td>	
						</tr>
					</table>
				</div>
				<!-- <div style="margin-bottom: 3px;" id="addCust_CustPersonalInfoButton">
					<a href="#" onclick="submitCustWealthInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
					<a href="#" onclick="submitCustPersonalInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
					
				</div> -->
			</div>
		
	</div>	
	</form>	
	<div class="tableOuterDiv"></div>
	<div id="smsaccordion" class="easyui-panel" title="家庭信息" collapsible="true">
		<div class="top_table">
			<table id="customerFamilyTable"></table>
			<div id="customerFamilyTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="custFamilyTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="custFamilyTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="custFamilyTableLockOneRow()">锁定</a>
			</div>
			<!-- <div style="margin-bottom: 3px;" id="customerFamilyTableButton">
				<a href="#" onclick="submitCustFamilyInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			</div> -->
		</div>
	</div>
	<div class="tableOuterDiv"></div>	
	<div id="smsaccordion" class="easyui-panel" title="房产信息" collapsible="true">
		<div class="top_table">
			<table id="customerHouseTable"></table>
			<div id="customerHouseTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="custHouseTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="custHouseTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="custHouseTableLockOneRow()">锁定</a>
			</div>
			<!-- <div style="margin-bottom: 3px;" id="customerHouseTableButton">
				<a href="#" onclick="submitCustHouseInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			</div> -->
		</div>
	</div>
	<div class="tableOuterDiv"></div>	
	<div id="smsaccordion" class="easyui-panel" title="车辆信息" collapsible="true">
		<div class="top_table">
			<table id="customerCarTable"></table>
			<div id="customerCarTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="custCarTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="custCarTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="custCarTableLockOneRow()">锁定</a>
			</div>
			<!-- <div style="margin-bottom: 3px;">
				<a href="#" onclick="submitCustCarInfo()" id="addCust_submitCarInfoButton" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
				<a href="#" onclick="back()" id="addCust_backButton" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</div> -->
		</div>
	</div>
</div>
<div style="margin-bottom: 3px;">
	<a href="#" onclick="saveCustAllInfo()" id="addCust_submitCarInfoButton" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
	<a href="#" onclick="back()" id="addCust_backButton" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
</div>
<input type="hidden" name="custOthInfoId" id="addCust_CustOthInfoId">