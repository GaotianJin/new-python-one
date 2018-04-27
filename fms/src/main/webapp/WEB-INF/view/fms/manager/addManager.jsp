<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/manager/addManagerInit.js"></script>


<input type="hidden" name="operate" id="addManager_operate" class="inpuntHidden" value="${operate}">
<input type="hidden" name="managerId" id="addManager_managerId" class="inpuntHidden" value="${managerId}">
<div id="tabdiv">
	<div id="smsaccordion1" class="easyui-panel" title="内勤人员信息" collapsible="true">
		<form id="addManagerInfoForm">
				<div id="addManagerInfo" class="top_table">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">姓名</td>
							<td align="left"><input name="chnName" id="addManager_chnName" class="table_input easyui-validatebox"
									data-options="required:true,validType:['length[0,10]']"></td>
							<td class="table_text" align="right">电话</td>
							<td align="left">
								<span class="comboSpan"></span>
								<input class="table_input easyui-validatebox"  id="addManager_mobile" name="mobile"
								 data-options="required:true,validType:['validPhone']">
							</td>
						</tr>
					</table>
				</div>
			</form>
		
		<div style="margin-bottom: 3px;">
			<span id="addManager_submitButton">
				<a href="#" onclick="submit()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			</span>
			<span id="addManager_backButton">
				<a href="#" onclick="backListAgentPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			</span>
		</div>
	</div>	

</div>


