<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/nb/policyQueryInputInit.js"></script>
<div id="tabdiv">
	<form id="policyQueryInputForm">
		<div style="margin-bottom:  3px;">
	     <a href="#" onclick="queryPolicy()" class="easyui-linkbutton e-cis_button"  iconCls="icon-search">查询</a>
	     </div>
		<div id="searchcordion" class="easyui-panel" fit="true" title="多项查询条件"
			iconCls="icon-ok" collapsible="true" >
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" >保单号码：</td>
						<td >
						  <input id="policyNo" name="policyNo" class="table_input" value="${policyNo}">
						</td>
						<td class="table_text" >投保人：</td>
						<td >
						  <input id="appntName" name="appntName" class="table_input" value="">
						</td>
						<td class="table_text" >被保人：</td>
						<td >
						  <input id="insuredName" name="insuredName" class="table_input" value="">
						</td>
					</tr>
					<tr>
						<td class="table_text" >保单版本日期：</td>
						<td >
						  <input id="policyVersionDate" name="policyVersionDate" type="text" class="easyui-datebox" ></input> 
						</td>
						<td class="table_text" >网点编码：</td>
						<td >
						  <input id="agentCode" name="agentCode" class="table_input" value="">
						</td>
						<td class="table_text" >保单生效日：</td>
						<td >
						  <input id="effectDate" name="effectDate" type="text" class="easyui-datebox" ></input>  
						</td>
					</tr>
					<tr>
						<td class="table_text" >渠道：</td>
						<td >
						   <select id="saleChannel" name="saleChannel"  class="easyui-combobox table_select">
			                   <option value="3" selected="selected">银保</option>
			               </select>
						</td>
						<td class="table_text" >保单满期日：</td>
						<td >
						 	  <input id="endDate" name="endDate" type="text" class="easyui-datebox" ></input> 
						</td>
						<td  class="table_text" >主险险种名称</td>
						   <td >
						  <select class="easyui-combobox table_select" name="mainRiskCode" id="mainRiskCode" ></select>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</div>
<div style="margin-top: 5px;" id="tabdiv">
	<table id="PolicyQueryTable"></table>
</div>
