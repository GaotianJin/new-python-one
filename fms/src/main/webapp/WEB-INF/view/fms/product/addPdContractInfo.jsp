<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addPdContractInfo.js"></script>
<div id="addContractInfoDiv">
	<form id="addContractInfoForm">
		<div id="smsaccordion1" class="easyui-panel" title="合同信息" collapsible="true">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">产品</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox" id="addContractInfoProId" name="productId" data-options="required:true">
						</td>
						<td class="table_text" align="right">合同编号</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-validatebox" id="addContractCode" name="contractCode" >
						</td>
						<td class="table_text" align="right">开始序号</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-validatebox" id="addContractStartNum" name="contractStartNum" >
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">结束序号</td>
						<td>
							<input class="table_input easyui-validatebox" id="addContractEndNum" name="contractEndNum" >
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div style="margin-bottom: 3px;">
		<a href="#" id="addPdContract_submitButton" onclick="savePdContractInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
		<a href="#" onclick="backListContractPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	</div>
</div>

