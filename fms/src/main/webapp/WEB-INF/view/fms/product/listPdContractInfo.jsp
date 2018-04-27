<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/listPdContractInfoInit.js"></script>
<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="合同信息查询" collapsible="true">
		<form id="queryContractInfoCondition">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">产品</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="contractInfoProId" name="productId">
						</td>
						<td class="table_text" align="right"></td>
						<td> </td>
						<td class="table_text" align="right"></td>
						<td> </td>	
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchContractInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="lisPdContractInfo"></table>
		</div>
	</div>
</div>