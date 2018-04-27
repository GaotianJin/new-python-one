<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/pos/posItemDAInit.js"></script>
<form id="posTypeForm">
<%@include file="ctrlbutton.jsp" %>
<div style="position: absolute; top: 50px; height: 100%;width: 100%" id = "pos_main_div">
<div id="tabdiv" >
	<div id="taskinfo" class="easyui-panel" fit="true" title="任务信息" iconCls="icon-ok" collapsible="true">
		<div class="top_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="table_text" align="right">保单号</td>
					<td align="left">
					<input name="policyno" id="policyno" type="text" class="table_input" value="${policyno}" readonly>
					</td>
					<td class="table_text" align="right">保全项目</td>
					<td align="left">
					<input name="postypename" id="postypename" class="table_input" value="${postypename}" readonly>
					</td>
					<td class="table_text" align="right">申请日期</td>
					<td align="left">
					<input name="appdate" id="appdate" class="easyui-datebox" value="${appdate}" readonly>
					</td>
				</tr>

				<tr>
					<td class="table_text" align="right">任务状态</td>
					<td align="left"><input name="status" id="status" class="table_input" value="${status}" readonly></td>
					<td class="table_text" align="right">当前操作人</td>
					<td align="left"><input name="operator" id="operator" class="table_input" value="${usercode}" readonly></td>
					<td class="table_text" align="right">保全生效日期</td>
					<td align="left"><input name="effencientdate" id="effencientdate" class="easyui-datebox" value="${effencientdate}" required="true"></td>
				</tr>
				<tr>
					<td width="125" class="table_text" align="right">保全任务号</td>
					<td width="200" align="left"><input name="posID" id="posID"
						class="table_input" value="${posID}" disabled="disabled"></td>
					<td class="table_text" align="right">是否照会</td>
					<td align="left"><input id="isNote" name="isNote"
						class="table_input" value="${isNote}" disabled="disabled">
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>

<div style="margin-top: 3px;" id="tabdiv1">
	<table id="productTable"></table>
</div>
<div>
	<span id="computeProductDelSpan"><a href="#" onclick="computeProductDA()" class="easyui-linkbutton e-cis_button" iconCls="icon-sum"> 计算</a></span>
</div>
<div style="margin-top: 3px;" id="tabdiv2">
	<table id="productTableUpdate"></table>
</div>
<div id="divbankinfo">
<%@include file="bankinfo.jsp" %>
</div>
<%@include file="disPlayRemark.jsp" %>
<div id = "hiddenDiv">
<input type="hidden" name = "postype" id  = "postype"  value="${postype}"/>
<input type="hidden" name = "type" id  = "type"  value="${type}"/>

</div>
</div>
</form>