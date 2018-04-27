<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addOpenDayFindInit.js"></script>
<div id="addOpenDayDiv">
	<form id="addOpenDayInfoForm">
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
						<td class="table_text" align="right">开放日</td>
						<td>
							<span class="comboSpan"></span> 
							<input  id="openDaySecOpenDate" name="openDate" class="table_input easyui-datebox" data-options="required:true,validType:['length[0,10]','validDate']">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">募集起期</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-datetimebox" id="investStartDate" name="investStartDate" data-options="required:true">
						</td>
						<td class="table_text" align="right">募集止期</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-datetimebox" id="investEndDate" name="investEndDate" data-options="required:true">
						</td>
						<td align="right" class="table_text">本期融资规模(万元)</td>
						<td align="left">
							<input class="table_input easyui-validatebox" name="financingScale" id="financingScale" data-options="validType:['validDecNum']" />
						</td>
					</tr>
				</table>
			</div>
			<br> <a href="#" onclick="addOpenDayInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-tick'">提交</a>
			     <a href="#" onclick="backLisOpenDayPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>