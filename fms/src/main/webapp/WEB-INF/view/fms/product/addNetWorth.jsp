<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/addNetWorthInit.js"></script>

<div class="easyui-panel" title="净值信息列表" collapsible="true">
	<div class="top_table">
	<form id="netValueInfoForm">
	    <div class="top_table">
			<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="table_text" align="right" >基金管理人</td> 
					<td><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="jzSecondAgentComId" name="agentComId" data-options="required:true"></td>		
					<td class="table_text" align="right">产品</td>
					<td><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="jzSecondProductId" name="productId" data-options="required:true"></td>
					<td class="table_text" align="right" >公布日期</td> 
					<td align="left"><span class="comboSpan"></span><input class="table_input easyui-datebox" id="jzSecondOpenDayDate" name="openDayDate" data-options="required:true,validType:['length[0,10]','validDate']"/></td>
				</tr>
				<tr>
					<td class="table_text" align="right" >净值类型</td> 
					<td><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="jzSecondNetValueType" name="netValueType" data-options="required:true"></td>	
					<td class="table_text" align="right" >净值</td> 
				    <td align="left"><input class="table_input easyui-validatebox" name="netValue" id="jzSecondNetValue"  data-options="validType:['validDecNum']" /></td>
				   <td class="table_text" align="right" >是否生成预估短信</td> 
					<td><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="addNetValue_isOrder" name="isOrder" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="table_text" align="right" >是否生成月中/月末短信</td> 
					<td><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="addNetValue_specialType" name="specialType"></td>	
					<td class="table_text" align="right" >净值涨幅(%)</td> 
				    <td align="left"><input class="table_input easyui-validatebox" name="earnRate" id="jzSecondEarnRate"  data-options="validType:['validDecNum']" /></td>
				</tr>
			</table>
		</div>
		</form>
		</div>
	<div>
	  	<a id="submitButton" href="#"  onclick="submit()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">提交</a>
		<a href="#" onclick="backLisNetValuePage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	</div>
</div>