<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/detailPdContractInfo.js"></script>

<div id="tabdiv" class="outerPanel">	
	<div id="smsaccordion" class="easyui-panel" fit="true" title="合同信息查询" collapsible="true">
		<form id="detailContractInfoFrom">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">合同状态</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="detailpdContractStatus" name="contractStatus">
						</td>
						<td class="table_text" align="right"> <input type="hidden" name="contractCode" id="detailContractCode" value="${contractCode}"></td>
						<td class="table_text" align="right"> <input type="hidden" name="productId" id="detailProductId" value="${productId}"></td>
						<td> </td>
						<td class="table_text" align="right"></td>
						<td> </td>	
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearDetailForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="searchDetailContractInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="exportDetailPdContractInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出产品合同详情</a>
				</div>
			</div>
		</form>
		<div style="margin-top: 3px;" id="tabdiv">
			<table id="detailPdContractInfo"></table>
		</div>
	</div>
</div>