<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/cooperation/listAgencyProtocolInit.js"></script>
<div id="tabdiv" class="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="合作协议信息查询" collapsible="true">
		<form id="listAgencyProtocolForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
					    <td class="table_text" align = "right">协议类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name = "protocolCode" id="list_protocolType" class="table_input">
						</td>
						<td class="table_text" align = "right">协议名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name = "protocolName" id="list_protocolCode" class="table_input">
						</td>
						<td class="table_text" align = "right">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id = "list_agencyComId" class = "table_input easyui-combobox">
						</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearAgencyProtocolList()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryAgencyProtocolList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv"> 
			<table id="agencyProtocolTable"></table>
		</div>
	</div>
</div>


<input type="hidden" name="modifyAgencyComId" id="modifyAgencyComId">
<input type="hidden" name="modifyProtocolType" id="modifyProtocolType">
<input type="hidden" name="modifyProtocolId" id="modifyProtocolId">
