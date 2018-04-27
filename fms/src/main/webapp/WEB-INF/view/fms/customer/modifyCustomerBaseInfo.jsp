<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入Js文件 -->
<script 	type="text/javascript" 		src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script 	type="text/javascript" 		src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script 	type="text/javascript" 		src="<%=request.getContextPath()%>/js/fms/customer/modifyCustomerBaseInfoInit.js"></script>
<!--接收服务器端参数 -->
<input type="hidden" 	name="tradeId" 				id="modifyCustomerBaseInfo_tradeId" 			value="${tradeId}"   />
<input type="hidden" 	name="agentId"				id="modifyCustomerBaseInfo_agentId" 			value="${agentId}" />
<input type="hidden" 	name="rolePivilege" 		id="modifyCustomerBaseInfo_rolePivilege" 	value="${rolePivilege}" />
<input type="hidden" 	name="loadFlag" 			id="modifyCustomerBaseInfo_loadFlag" 		value="${loadFlag}" />
<!--显示页面-->
<div class="easyui-tabs" id="modifyCustomerBaseInfoTabs" >
	  <div title="基本信息" class="outerPanel">
		  <div  class="easyui-panel" title="基本信息" collapsible="true">
			      <form id = "modifyCustomerBaseInfoForm">
				          <div class="top_table" id = "modifyCustomerBaseInfo_top">
					           <table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						            <tr>
							               <td class="table_text" align="right">客户号</td>
							               <td align="left">
													<input 		type="hidden" 	name="custBaseInfoId"  id="modifyCustomerBaseInfo_Id"  class="inpuntHidden"		value="${custBaseInfoId}"/>
													<input 		type="hidden" 	name="agentId" 				  id="addCust_agentId" 	class="inpuntHidden" value="${agentId}" />
													<input 		name="customerNo"       id="modifyCustomerBaseInfo_CustomerNo" class="table_input" readonly="true" />
													<!-- <span id="modifyCustomerBaseInfo_searchCustButton">
														<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="openSearchCustWindow()"></a>
													</span> -->
										   </td>
										   <td class="table_text" align="right">中文姓名</td>
											  <td align="left">
													<input name="chnName" id="modifyCustomerBaseInfo_ChnName" class="table_input easyui-validatebox"  />
													<!-- data-options=",validType:['length[0,50]']" -->
											  </td>
										   	  <td class="table_text" align="right">性别</td>
											  <td align="left">
												        <span class="comboSpan"></span>
														<input name="sex" id="modifyCustomerBaseInfo_Sex" class="table_input easyui-combobox1"/>
														<input type="hidden" name="sexName" id="modifyCustomerBaseInfo_SexName" class="inpuntHidden"/>
											  </td>
						            </tr>
						            <tr>
							            	
											<td class="table_text" align="right">出生日期</td>
											<td align="left">
												<span class="comboSpan"></span>
												<input type="hidden" name="age" id="addCust_CustAge" class="inpuntHidden"/>
												<input class="table_input easyui-datebox1" name="birthday" id="modifyCustomerBaseInfo_Birthday"  data-options="editable:false,validType:['validDate']"/>
											</td>
											<td class="table_text" align="right">英文姓</td>
											<td align="left">
												<input class = "table_input easyui-validatebox"  name="lastName"  id = "modifyCustomerBaseInfo_LastName"/>
												<!-- data-options="validType:['length[0,50]','validEn']" 验证全英文 -->
											</td>
											<td class="table_text" align="right">英文名</td>
											<td align="left">
												<input name="firstName" id="modifyCustomerBaseInfo_FirstName" class="table_input easyui-validatebox"  />
											</td>
						            </tr>
						            <tr>
							               <td class="table_text" align="right">客户类型</td>
											<td align="left">
												    <span class="comboSpan"></span>
													<input name="custType" id="modifyCustomerBaseInfo_CustType" class="table_input easyui-combobox1" onclick="getIdType(this);"/>
										   </td>
							               <!-- <td class="table_text" align="right">获客方式</td>
													<td align="left">
													    <span class="comboSpan"></span>
														<input name="custObtainWay" id="modifyCustomerBaseInfo_CustObtainWay" class="table_input easyui-combobox1"/>
										   </td> -->
							</td>
										   <td class="table_text" align="right">国籍</td>
													<td align="left">
													    <span class="comboSpan"></span>
														<input name="nativePlace" id="modifyCustomerBaseInfo_NativePlace" class="table_input easyui-combobox1"/>
										   </td>
										   <td align="left">&nbsp;</td>
										   <td align="left">&nbsp;</td>
						            </tr>
						            <tr>
						               <td class="table_text" align="right">证件类型</td>
										<td align="left">
										        <span class="comboSpan"></span>
												<input name="idType" id="modifyCustomerBaseInfo_IdType" class="table_input easyui-combobox1"/>
												<input type="hidden" name="idTypeName" id="modifyCustomerBaseInfo_IdTypeName" class="inpuntHidden"/>
							            </td>
									   <td class="table_text" align="right">证件号码</td>
									   <td align="left">
												<input name="idNo"  id = "modifyCustomerBaseInfo_IdNo" class = "table_input easyui-validatebox"  data-options="validType:['length[0,20]']" onBlur="verifyIdNo()" />
												<span  id="uploadTradeAttachInfoButton">
													<a class="easyui-linkbutton e-cis_button" id="modifyCustomerBaseInfo_IdNoButton" 
													onclick="uploadIdcardimageInfo()" data-options="iconCls:'icon-redo',plain:true">上传身份证复印件</a>
												</span> 
									   </td>
									   <td class="table_text" align="right">证件有效期</td>
										<td align="left">
												<span class="comboSpan"></span>
												<input name="idValidityDate"  id = "modifyCustomerBaseInfo_idValidityDate" class="table_input easyui-datebox"/>
												<input type="radio" name="idNoType" value="0" id="idNoType0"/>永久
												<input type="radio" name="idNoType" value="1" id="idNoType1"/>固定期限</td>
						            </tr>
						            <tr>
							               <td class="table_text" align="right">客户级别</td>
											<td align="left">
											        <span class="comboSpan"></span>
													<input name="custLevel" id="modifyCustomerBaseInfo_CustLevel" class="table_input easyui-combobox1" data-options="valueField:'code',textField:'codeName'"/>
											</td>
											<td class="table_text" align="right">投资者类型</td>
											<td align="left">
											        <span class="comboSpan"></span>
													<input name="investCustomerType" id="modifyCustomerBaseInfo_InvestCustomerType" class="table_input easyui-combobox1" data-options="valueField:'code',textField:'codeName'"/>
											</td>
										   <td class="table_text" align="right">手机号</td>
										   <td align="left">
													<input name="mobile" id="modifyCustomerBaseInfo_Mobile" class="table_input easyui-validatebox"
													data-options="validType:['length[0,20]','validPhone']"/>
										   </td>
										  
						            </tr>
						            <tr>
					          			   <td class="table_text" align="right">固定电话</td>
										   <td align="left">
													<input name="phone" id="modifyCustomerBaseInfo_phone" class="table_input"/>
										   </td>
										   <td class="table_text" align="right">微信号</td>
										   <td align="left">
													<input name="wechat" id="modifyCustomerBaseInfo_Wechat" class="table_input"/>
										   </td>
										   <td class="table_text" align="right">E-Mail</td>
										   <td align="left">
													<input name="email" id="modifyCustomerBaseInfo_Email" class="table_input easyui-validatebox"/>
										   </td>
							         </tr>
							         <tr>
							            	
									</tr>
					            </table>
				            	<div id='modifyCustomerBaseInfo_CustBaseInfoButton'>
										<a href="#" id='modifyCustomerBaseInfo_CustBaseInfoSubmitButton' onclick="submitCustomerBaseInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
										<span id="custOperationPrompt" style="color: red;size:18">（注：录入准客户时可以只输入部分信息，而在录单时则需要补全必要信息;红色框为必录项）</span>
								</div>
								<div class="tableOuterDiv"></div>
				          </div>
			   </form>
		  </div>
		  <!-- 隔开五个像素 -->
		  <div class="tableOuterDiv"></div> 
		  <!-- 客户地址信息 -->
		  <div id="modifyCustomerAddressDiv" class="easyui-panel" title="联系地址信息" collapsible="true">
					<table id="modifyCustomerAddressTable"></table>
			        <div id="modifyCustomerAddressTable_tb" style="height:auto">
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="custAddressTableAddOneRow()">新增</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="custAddressTableRemoveOneRow()">删除</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="custAddressTableLockOneRow()">锁定</a>
				    </div>
		  </div>    
		  <!-- 隔开五个像素 -->
		  <div class="tableOuterDiv"></div>
		  <!-- 客户账户信息 -->
		  <div id="modifyCustomerAccountDiv" class="easyui-panel" title="账户信息" collapsible="true">
				<table id="modifyCustomerAccountTable"></table>
				<div id="modifyCustomerAccountTable_tb" style="height:auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="custAccountTableAddOneRow()" id="custAccountTable_AddOneRow">新增</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="custAccountTableRemoveOneRow()" id="custAccountTable_RemoveOneRow">删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="custAccountTableLockOneRow()" id="custAccountTable_LockOneRow">锁定</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-redo',plain:true"
							onclick="custAccountTableUploadOneRow()">上传银行卡复印件</a>
				</div>
			</div> 
		  <!-- 隔开五个像素 -->
		  <div class="tableOuterDiv"></div>
		  <div id="modifyCustomerAscriptionDiv" class="easyui-panel" title="客户归属" collapsible="true">
				<table id="modifyCustomerAscriptionTable"></table>
				<!-- 隔开五个像素 -->
				 <div class="tableOuterDiv"></div>
				 <div id='modifyCustomerBaseInfo_CustAddAndAcountInfoButton' style="margin-bottom: 3px;">
						<a href="#" onclick="submitCustomerAddressAndAccInfo()" id="modifyCustomerBaseInfo_submitCarInfoButton" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
						<a href="#" onclick="modifyCustomerBaseInfoBack()" id="modifyCustomerBaseInfo_backButton" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
						<a href="#" onclick="modifyCustomerPropertyProve()"
					id="modifyCustomerBaseInfo_propertyProveButton"
					class="easyui-linkbutton e-cis_button" iconCls="icon-redo">上传资产证明复印件</a>
				 </div>
		 </div>
	</div>
</div>
