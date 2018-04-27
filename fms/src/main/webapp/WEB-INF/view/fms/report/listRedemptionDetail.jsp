<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/report/listRedemptionDetailInit.js"></script>
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="业务列表" collapsible="true">
		 <form id="listRedemption_queryConditionForm">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
					<td class="table_text" align="right">年度</td>
						<td>
							<span class="comboSpan"></span>
							<input name="year" id="listRedemption_yearId" class="table_input"/>
						</td>
						<td class="table_text" align="right">月份</td>
						<td>
							<span class="comboSpan"></span>
							<input name="month" id="listRedemption_monthId" class="table_input"/>
						</td>
							<td align="right" class="table_text">赎回时间</td>
					<td align="left">
							<span class="comboSpan"></span>
							<input name="redemptionStartTime" id="listReport_redemptionStartTime" class="table_input2 easyui-datebox">
							至
							<input name="redemptionEndTime" id="listReport_redemptionEndTime" class="table_input2 easyui-datebox">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">分公司</td>
						<td>
							<span class="comboSpan"></span>
							<input name="comId" id="listRedemption_comId" class="table_input"/>
						</td>
						<td class="table_text" align="right">部门</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="departmentId" id="listRedemption_DepartmentId" class="table_input"/>
						</td>
						
					<td class="table_text" align="right">产品名称</td>
					<td align="left">
					<span class="comboSpan"></span>
					<input name="productId" id="lisRedemption_ProductId" class="table_input"/>
					</td>
					
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryRedemptionDetailList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="exportDetailList()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出</a>
						
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="listRedemption_QueryTable"></table>
		</div>
	</div>	
</div>

