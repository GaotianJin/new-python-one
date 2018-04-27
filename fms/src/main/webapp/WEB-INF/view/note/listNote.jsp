<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/cis/note/listNoteInit.js"></script>
<div id="tabdiv" style="width:98%" >

	<div>
		<a href="#" onclick="saveNote()"
			class="easyui-linkbutton e-cis_button" iconCls="icon-save">保存照会</a> <a
			href="#" onclick="closeNote()" class="easyui-linkbutton e-cis_button"
			iconCls="icon-edit">解决照会</a> <a href="#" onclick="cancelNote()"
			class="easyui-linkbutton e-cis_button" iconCls="icon-undo">撤销照会</a> <a
			href="#" onclick="clearNote()" class="easyui-linkbutton e-cis_button"
			iconCls="icon-cancel">清空</a> <a href="#" onclick="searchNote()"
			class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
	</div>
	<form id="noteForm">
		<div id="smsaccordion" class="easyui-panel" title="照会信息" 
			iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">保单号</td>
						<td><input name="policyNo" id="policyNo" class="table_input disableClass"
							value="${policyNo}" readonly disabled="disabled"></td>
						<td class="table_text" align="right">任务类型</td>
						<td align="left"><select class="table_select disableClass"
							name="businessType" id="businessType" onchange="changeType()" disabled>
								<option value="01" <c:if test="${businessType eq '01'}">selected</c:if>>保全</option>
								<option value="02" <c:if test="${businessType eq '02'}">selected</c:if>>理赔</option>
						</select></td>
						<td class="table_text" align="right">任务号码</td>
						<td align="left"><input name="businessNo" id="businessNo"
							class="table_input disableClass" value="${businessNo}" readonly disabled></td>
					</tr>
					<tr>
						<td class="table_text" align="right">代理人编码</td>
						<td align="left"><input class="table_input" name="agentCode"
							id="agentCode disableClass" value="${agentCode}" readonly disabled></td>
						<td class="table_text" align="right">代理人姓名</td>
						<td align="left"><input name="agentName" id="agentName"
							class="table_input disableClass" value="${agentName}" readonly disabled></td>
						<td class="table_text" align="right">照会编号</td>
						<td align="left"><input class="table_input disableClass" name="id" id="id"
							value="${id}" readonly disabled></td>
					</tr>
					<tr>
						<td class="table_text" align="right">照会类型</td>
						<td><input name="noteType" id="noteType"
							class="table_select easyui-validatebox"
							></td>
						<td class="table_text" align="right">照会发起人</td>
						<td align="left"><input class="table_input easyui-validatebox disableClass" name="noteApplyUser"
							id="noteApplyUser" required="true" value = "${noteApplyUser}" disabled></td>
						<td class="table_text" align="right">照会状态</td>
						<td align="left"><select class="table_select disableClass" name="noteStatus"
							id="noteStatus" value="${noteStatus}" disabled>
							<option value="01">处理中</option>
							<option value="02">已解决</option>
							<option value="03">已关闭</option>
							</select></td>
					</tr>
				</table>
			</div>
		</div>

		<div id="smsaccordion" class="easyui-panel" title="照会内容"
			iconCls="icon-ok" collapsible="true">
			<textarea cols=120 rows=10 name="noteContent" id="noteContent" 
							class="easyui-validatebox" required="true" length=10>${noteContent}</textarea>
		</div>

		<input type="hidden" name="noteSerialNo" id="noteSerialNo"
			value="${noteSerialNo}">
		<input type="hidden" name="busType" id="busType"
			value="${businessType}">
	</form>
	<div>
		<table id="noteTable"></table>
	</div>
</div>
