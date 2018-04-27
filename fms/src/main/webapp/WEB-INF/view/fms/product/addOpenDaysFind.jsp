<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addOpenDaysFindInit.js"></script>
<div id="addOpenDaysDiv">
	<form id="addOpenDaysInfoForm">
		<div id="smsaccordion1" class="easyui-panel" title="开放日信息" collapsible="true">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td><span class="comboSpan"></span><input
							class="table_input easyui-combobox" id="openDaySecAgentComId1"
							name="agentComId" data-options="required:true">
						</td>
						<td class="table_text" align="right">产品</td>
						<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="openDaySecProductId1"
							name="productId" data-options="required:true">
						</td>
						<td class="table_text" align="right">是否指定日期</td>
						<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="isOrder"
							name="isOrder" data-options="required:true">
						</td>
					</tr>
					<tr>
						<td align="right" class="table_text">开放日(每月)</td>
						<td align="left"><input class="table_input "  id="openDaySecOpenDate" name="openDate" data-options="required:true" />01代表每月一日，以此类推</td>
						</td>
						<td class="table_text" align="right">开放日规则</td>
						<td><span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="openDayRules"
							name="openDayRules" >
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">维护起期</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-datetimebox" id="defendStartDate" name="defendStartDate" data-options="required:true">
						</td>
						<td class="table_text" align="right">维护止期</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-datetimebox" id="addOpenDays_defendEndDate" name="defendEndDate" data-options="required:true">
						</td>
					</tr>
				</table>
			</div>
				 <a href="#" onclick="addOpenDaysInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-tick'">提交</a>
			     <a href="#" onclick="backLisOpenDayPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>