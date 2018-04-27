<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/addAgentInit.js"></script>


<input type="hidden" name="operate" id="addAgent_operate" class="inpuntHidden" value="${operate}">
<input type="hidden" name="agentImage" id="addAgent_agentImage">
<div id="tabdiv">
	<!-- <div id="addAgent_agentBaseInfo"> --><!-- </div> -->
	<div id="smsaccordion1" class="easyui-panel" title="基本信息" collapsible="true">
		<form id="addAgent_BaseInfoForm">
			
				<div id="addAgent_agentBaseInfo" class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">工号</td>
							<td align="left">
								<input type="hidden" name="agentId" id="addAgent_agentId" class="inpuntHidden" value="${agentId}">
								<input name="agentCode" id="addAgent_agentCode" class="table_input easyui-validatebox" onBlur="verifyAgentCode()" 
									data-options="required:true,validType:['length[0,20]','validCode']">
								<!-- <input name="agentCode" id="addAgent_agentCode" class="table_input easyui-validatebox" onBlur="verifyAgentCode()"> -->
							</td>
							<td class="table_text" align="right">姓名</td>
							<td align="left"><input name="agentName" id="addAgent_agentName" class="table_input easyui-validatebox"
									data-options="required:true,validType:['length[0,10]']"></td>
							<td class="table_text" align="right">性别</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox"  id="addAgent_sex" name="sex">
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">出生日期</td>
							<td align="left"><span class="comboSpan"></span>
								<input name="birthday" id="addAgent_birthday" class="easyui-datebox table_input"
									data-options="validType:['validDate']"></td>
							<td class="table_text" align="right">证件类型</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_idType" name="idtype"></td>
							<td class="table_text" align="right">证件号码</td>
							<td align="left">
								<input name="idno" id="addAgent_idNo" class="table_input easyui-validatebox"
								data-options="required:true,validType:['length[0,20]']" onBlur="verifyIdNo()"/>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">民族</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_nationality" name="nationality"></td>
							<td class="table_text" align="right">籍贯</td>
							<td align="left"><input class="table_input easyui-validatebox"  id="addAgent_nativeplace" name="nativeplace"
									data-options="validType:['length[0,25]']"></td>
							<td class="table_text" align="right">户籍性质</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_householdNature" name="householdNature"></td>		
							
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">户口所在地</td>
							<td align="left">
								<input name="rgtadress" id="rgtadress" class="table_input easyui-validatebox" 
									data-options="validType:['length[0,20]']"/>
							</td>
							<td class="table_text" align="right">社保地点</td>
							<td align="left">
								<input name="ssyAdress" id="addAgent_ssyAdress" class="table_input easyui-validatebox" 
									data-options="validType:['length[0,20]']"/>
							</td>
							<td class="table_text" align="right">政治面貌</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_political" name="political"/></td>
						</tr>
						<tr>
						    <td class="table_text" align="right">学历</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_degree" name="degree"/></td>
						    <td class="table_text" align="right">毕业院校</td>
							<td align="left">
								<input name="graduate" id="graduate" class="table_input easyui-validatebox"
									data-options="validType:['length[0,120]']">
							</td>
							<td class="table_text" align="right">专业</td>
							<td align="left">
								<input name="profession" id="addAgent_profession" class="table_input easyui-validatebox"
									data-options="validType:['length[0,120]']">
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">婚姻状况</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_maritalStatus" name="maritalStatus"/></td>
							<td class="table_text" align="right">生育状况</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_bearStatus" name="bearStatus"/></td>
							<td class="table_text" align="right">人员类别</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_agentType" name="agentType"/></td>
						</tr>
						<tr>
							<td class="table_text" align="right">省</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_province" name="province"/></td>
							<td class="table_text" align="right">市</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_city" name="city"/></td>
							<td class="table_text" align="right">区/县</td>
							<td align="left"><span class="comboSpan"></span><input class="table_input easyui-combobox1"  id="addAgent_country" name="country"/></td>
						</tr>
						<tr>
							<td class="table_text" align="right" >详细地址</td>
							<td colspan="3">
								<input name="street" id="addAgent_street" class="table_input1 easyui-validatebox"
									data-options="validType:['length[0,100]']"></td>
							<td class="table_text" align="right">邮政编码</td>
							<td align="left" >
								<input name="zipCode" id="addAgent_zipCode" class="table_input easyui-validatebox"
									data-options="validType:['validZip']"></td>
						</tr>
						<tr>
							<td class="table_text" align="right">家庭电话</td>
							<td align="left" >
								<input name="familyPhone" id="addAgent_familyPhone" class="table_input easyui-validatebox"
									data-options="validType:['length[0,20]','validTel']"></td>
							<td class="table_text" align="right">手机</td>
							<td align="left">
								<input name="mobile" id="addAgent_agentMobile" class="table_input easyui-validatebox"
									data-options="required:true,validType:['length[0,20]','validPhone']"></td>
							<td class="table_text" align="right">E-mail</td>
							<td align="left">
								<input name="email" id="addAgent_agentEmail" class="table_input easyui-validatebox"
									data-options="validType:['email','length[0,50]']"></td>
						</tr>
						<tr>
							<td class="table_text" align="right">QQ号</td>
							<td align="left">
								<input name="qq" id="addAgent_agentQq" class="table_input easyui-validatebox"
									data-options="validType:['length[0,20]','validQQ']"></td>
							<td class="table_text" align="right">微信号</td>
							<td align="left">
								<input name="wechat" id="addAgent_familyWechat" class="table_input easyui-validatebox"
									data-options="validType:['length[0,20]','validCode']"></td>
							<td class="table_text" align="right">用户登录帐号</td>
							<td align="left">
								<input type="hidden" name="userId" id="addAgent_userId" class="inpuntHidden">
								<input name="userCode" id="addAgent_userCode" class="table_input easyui-validatebox" 
									data-options="required:true,validType:['length[0,20]','validCode']" onBlur="verifyUserCode()">
								<!-- <input type="checkBox" id="addAgent_existUser" class="commonCheckbox">关联已存在用户 onBlur="verifyUserCode()"-->
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">在职状态</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox"  id="addAgent_workState" name="workState">
							</td>
							<td class="table_text" align="right">入司日期</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="joinDate" id="addAgent_joinDate" class="easyui-datebox table_input"
									data-options="validType:['validDate']"></input>
							</td>
							<td class="table_text" align="right">离职日期</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="leaveDate" id="addAgent_leaveDate" class="easyui-datebox table_input"
									data-options="validType:['validDate']"></input>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">工作地点</td>
								<td align="left">
									<input name="workAdress" id="addAgent_workAdress" class="table_input easyui-validatebox" 
										data-options="validType:['length[0,20]']"/>
								</td>
							<td class="table_text" align="right">公司固话</td>
								<td align="left" >
									<input name="officeTel" id="addAgent_officeTel" class="table_input easyui-validatebox"
										data-options="validType:['length[0,20]','validTel']"></td>
							<td class="table_text" align="right">是否已转正</td>
							<td align="left">
									<span class="comboSpan"></span>
									<input class="table_input easyui-combobox"  id="addAgent_isProbationPeriod" name="isProbationPeriod">
								</td>
						</tr>
						<tr>
						    <td class="table_text" align="right">试用期截止日期</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input name="probationperiodEndDate" id="addAgent_probationperiodEndDate" class="easyui-datebox table_input"
									data-options="validType:['validDate']"></input>
							</td>
							<td align="right">&nbsp;</td>
							<td align="left">&nbsp;</td>
						</tr>
						<tr>
							<td align="right" class="table_text" >获奖经历</td>
					 		<td align="left" colspan="3" class="winningExperience">
							<span class="comboSpan"></span>
							<textArea name="winningExperience" id="winningExperience" rows="2" cols="80" class="table_textarea easyui-validatebox"></textArea></td>
						</tr>
					</table>
				</div>
			</form>
		

		<!-- 家庭信息 -->
		<div class="tableOuterDiv"id="tabdiv2">
			<table id="agentFamilyInfoTable"></table>
			<div id="agentFamilyInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentFamilyInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentFamilyInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentFamilyInfoTableLockOneRow()">锁定</a>
			</div>
		</div>
		<!-- 工资卡卡号信息 -->
		    <div id="tableOuterDiv" id="tabdiv10">
				<table id="agentSalaryAccTable"></table>
				<div id="agentSalaryAccTable_tb" style="height:auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentSalaryAccTableAddOneRow()">新增</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentSalaryAccTableRemoveOneRow()">删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentSalaryAccTableLockOneRow()">锁定</a>
				</div>
			</div> 
		<!--资格证-->
		<div class="tableOuterDiv" id="tabdiv3">
			<table id="agentCertificationTable"></table>
			<div id="agentCertificationTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentCertificationTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentCertificationTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentCertificationTableLockOneRow()">锁定</a>
			</div>
		</div>
		<!--职级信息-->
		<div class="tableOuterDiv" id="tabdiv4">
			<table id="agentPositionInfoTable"></table>
			<div id="agentPositionInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentPositionInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentPositionInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentPositionInfoTableLockOneRow()">锁定</a>
			</div>
		</div>
		
		<!-- 培育关系 -->
		<div class="tableOuterDiv" id="tabdiv7">
			<table id="agentNurserInfoTable"></table>
			<div id="agentNurserInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentNurserInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentNurserInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentNurserInfoTableLockOneRow()">锁定</a>
			</div>
		</div>
		
		<!-- 从业经历 -->
		<div class="tableOuterDiv" id="tabdiv7">
			<table id="agentWorkInfoTable"></table>
			<div id="agentWorkInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentWorkInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentWorkInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentWorkInfoTableLockOneRow()">锁定</a>
			</div>
		</div>
		<!--财富顾问所属网点信息 -->
	<!-- 	<div class="tableOuterDiv" id="tabdiv5" style="display: none;">
			<table id="agentStoreInfoTable"></table>
			<div id="agentStoreInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentStoreInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentStoreInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentStoreInfoTableLockOneRow()">锁定</a>
			</div>
		</div> -->
		<!--财富顾问所属部门信息  -->
		<div class="tableOuterDiv" id="tabdiv6">
			<table id="agentDepartInfoTable"></table>
			<div id="agentDepartInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentDepartInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentDepartInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentDepartInfoTableLockOneRow()">锁定</a>
			</div>
		</div>
		<!-- 其他 -->
		<div class="tableOuterDiv" id="tabdiv9">
			<table id="agentOtherInfoTable"></table>
			<div id="agentOtherInfoTable_tb" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="agentOtherInfoTableAddOneRow()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="agentOtherInfoTableRemoveOneRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="agentOtherInfoTableLockOneRow()">锁定</a>
			</div>
		</div>
		
		<div style="margin-bottom: 3px;">
			<span id="addAgent_submitButton">
				<a href="#" onclick="submit()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			</span>
			<span id="addAgent_uploadImage">
				<a href="#" onclick="openUploadImageWindow()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">上传照片</a>
			</span>
			<span id="addAgent_backButton">
				<a href="#" onclick="backListAgentPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</span>
		</div>
	</div>	

</div>


