<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/productAmountDistributeInit.js"></script>

<div id="tabdiv10" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="产品查询条件" collapsible="true">
		<form id="productAmountDisInfo_queryConditionForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="agencyComId" id="productAmountDis_agencyComId" class="table_input">
						</td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productId" id="productAmountDis_productId" class="table_input">
						</td>
						<!-- <td class="table_text" align="right">产品状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="productStatus" id="productStatus" class="table_input easyui-combobox">
						</td> -->
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
				</table>
				<!-- <div style="margin-bottom:4px;margin-top:4px;margin-left:5px;">-->
				<div>
					<a href="#" onclick="clearQueryCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryProductAmountDisInfoList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div> 
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv11">
			<table id="productAmountDisInfoTable"></table>
		</div>
	</div>
	
</div>