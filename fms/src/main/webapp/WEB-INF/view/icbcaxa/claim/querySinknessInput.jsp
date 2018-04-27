<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/claim/querySinknessInputInit.js"></script>
<div id="tabdiv">
	<form id="querySinknessInputForm">
		<div id="querySinknessInput" class="easyui-panel" fit="true" title="疾病代码查询" iconCls="icon-edit" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">疾病代码：</td>
						<td align="left">
						  <input id="sinknessCode" name="sinknessCode" class="table_input" value="" >
						</td>
						<td class="table_text" align="right">疾病名称：</td>
						<td align="left">
						  <input id="sinknessName" name="sinknessName" class="table_input" value="">
						</td>
					</tr>
				</table>

				<div>
					<a href="#" onclick="querySinkness()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="getResult()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
				</div>
	      </div>
        </div>
	</form>
</div>
<div style="margin-top: 3px;" id="sinknessInfo">
	  <table id="SinknessInfoTable"></table>
</div>


