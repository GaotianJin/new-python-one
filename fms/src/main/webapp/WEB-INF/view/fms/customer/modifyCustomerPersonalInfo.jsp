<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/modifyCustomerPersonalInfoInit.js"></script>

<input type="hidden" name="custBaseInfoId" 	id="modifyCustomerBaseInfo_Id" 			value="${custBaseInfoId}"/>
<input type="hidden" name="agentId" 				id="modifyCustPersonal_agentId" 		value="${agentId}"/>
<input type="hidden" name="loadFlag" 			id="modifyCustomerBaseInfo_loadFlag" 		value="${loadFlag}" />
<input type="hidden" name="tradeLoadFlag" id="modifyCustPersonal_tradeLoadFlag" value="${tradeLoadFlag}">

<div id = "modifyCustomerPersonalInfoDiv" class="outerPanel">
     <div id = "modifyCustomerPersonalInfoDiv1">
	      <form id = "modifyCustomerPersonalWealthInfoForm">
		        <div id = "modifyCustomerPersonalInfoDiv2" class="easyui-panel" title="个人财富状况" style="height:auto;" collapsible="true">
			         <div class="top_table">
						    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
							       <tr>
							             <td class="table_text" align="right">年收入（万）</td>
										 <td align="left">
											<input name="annualIncome" id="modifyCustomerPersonalInfo_AnnualIncome" class="table_input easyui-numberbox" data-options="min:0,precision:2"/>
										 </td>
										 <td class="table_text" align="right">客户可投资资产预估</td>
										 <td align="left">
										    <span class="comboSpan"></span>
											<input name="custAssetEstimate" id="modifyCustomerPersonalInfo_CustAssetEstimate" class="table_input easyui-combobox1">
										 </td>
										 <td class="table_text" align="right">客户重要性</td>
										 <td align="left">
										    <span class="comboSpan"></span>
											<input name="custQuality" id="modifyCustomerPersonalInfo_CustQuality" class="table_input easyui-combobox1">
										 </td>
							       </tr>
					        </table>
				     </div>	
			    </div>			
	      </form>
   </div>
   
   <div id="modifyCustomerPersonalInfoinvestTableDiv">
		<div class="easyui-panel" title="投资意向和建议" collapsible="true">
			<div id="modifyCustomerPersonalInfoinvestTable_tb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true"
				onclick="modifyCustomerPersonalInfoinvestTableAddOneRow()">新增</a> 
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true"
				onclick="modifyCustomerPersonalInfoinvestTableUpdateOneRow()">更新</a> -->
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true"
				onclick="modifyCustomerPersonalInfoinvestTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true"
				onclick="modifyCustomerPersonalInfoinvestTableLockOneRow()">锁定</a>
			</div>
			<div class="top_table">
				<table id="modifyCustomerPersonalInfoinvestTable"></table>
			</div>
		</div>
	</div>
	<div class="tableOuterDiv"></div>
	<form id="modifyCustomerPersonalInfoForm">
	<div id="modifyCustomerPersonalInfo_CustPersonalInfo">
			<div id="smsaccordion" class="easyui-panel" title="个人信息" collapsible="true">
				<div class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right" >人生阶段</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="lifeStage"  id="modifyCustomerPersonalInfo_LifeStage" class="table_input easyui-combobox1"/>
							</td>
							<td class="table_text" align="right" >婚姻状况</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="marriage" id="modifyCustomerPersonalInfo_Marriage" class="table_input easyui-combobox1"/>
							</td>
							<td class="table_text" align="right" >教育水平</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="degree" id="modifyCustomerPersonalInfo_Degree" class="table_input easyui-combobox1"/>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right" >行业</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="workType" id="modifyCustomerPersonalInfo_WorkType" class="table_input easyui-combobox1"/>
							</td>
							<td class="table_text"  align="right" >职业</td>
							<td align="left">
							    <span class="comboSpan"></span>
								<input name="occupationCode" id="modifyCustomerPersonalInfo_OccupationCode" class="table_input easyui-combobox1"/>
							</td>
							<td  class="table_text"  align="right" >工作单位</td>
							<td align="left">
								<input name="companyName" id="modifyCustomerPersonalInfo_CompanyName" class="table_input easyui-validatebox"
									data-options="validType:['length[0,200]']"/></td>
							<td class="table_text" align="right" >&nbsp;</td>
							<td align="left">&nbsp;</td>
						</tr>
						<tr>
							<td class="table_text" align="right" >职务</td>
							<td align="left">
								<!-- <span class="comboSpan"></span> -->
								<input name="position" id="modifyCustomerPersonalInfo_Position" class="table_input easyui-validatebox"/>
							</td>
							
						</tr>
						<tr>
							<td class="table_text" align="right" >兴趣爱好</td>
							<td align="left" valign="middle" colspan=5>
								<!-- <input name="hobby" id="hobby" class="table_input"/>  -->
								<div id="hobbyCheckBox"></div>
							</td>			
						</tr>
					</table>
				</div>
			</div>	
	</div>
   </form>
   <div class="tableOuterDiv"></div>
	<div id="smsaccordion" class="easyui-panel" title="家庭信息" collapsible="true">
		<div class="top_table">
			<table id="modifyCustomerPersonalInfoFamilyTable"></table>
			<div id="modifyCustomerPersonalInfoFamilyTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="modifyCustomerPersonalInfoFamilyTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="modifyCustomerPersonalInfoFamilyTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="modifyCustomerPersonalInfoFamilyTableLockOneRow()">锁定</a>
			</div>
		</div>
	</div>
	<div class="tableOuterDiv"></div>
	<div id="smsaccordion" class="easyui-panel" title="房产信息" collapsible="true">
		<div class="top_table">
			<table id="modifyCustomerPersonalInfoHouseTable"></table>
			<div id="modifyCustomerPersonalInfoHouseTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="modifyCustomerPersonalInfoHouseTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="modifyCustomerPersonalInfoHouseTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="modifyCustomerPersonalInfoHouseTableLockOneRow()">锁定</a>
			</div>
		</div>
	</div>
	<div class="tableOuterDiv"></div>
	<div id="smsaccordion" class="easyui-panel" title="车辆信息" collapsible="true">
		<div class="top_table">
			<table id="modifyCustomerPersonalInfoCarTable"></table>
			<div id="modifyCustomerPersonalInfoCarTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="modifyCustomerPersonalInfoCarTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="modifyCustomerPersonalInfoCarTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="modifyCustomerPersonalInfoCarTableLockOneRow()">锁定</a>
			</div>
		</div>
	</div>
	<div style="margin-bottom: 3px;">
		<a href="#" onclick="submitModifyCustomerPersonalInfo()" id="modifyCustomerPersonalInfo_submitCarInfoButton" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
		<!-- <a href="#" onclick="modifyCustomerPersonalInfo_backButton()" id="modifyCustomerPersonalInfo_backButton" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a> -->
	</div>
</div>

