<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/updateNetWorthInit.js"></script>

<div class="easyui-panel" title="净值信息修改" collapsible="true" id="updateNetValueDiv">
	<div class="top_table">
	<form id="updateNetValueInfoForm">
			<table class='input_table' width="100%" border="0" cellspacing="0"  cellpadding="0">
				<tr>
				    <td class="table_text" align="right">基金管理人</td>
					<td> <input  id="ThirdagencyComId" name="agencyComId" class="table_input" disabled="disabled"></td>
					<td class="table_text" align="right">产品</td>
					<td> <input  id="ThirdproductId" name="productId" class="table_input "  disabled="disabled"></td>
					<td class="table_text" align="right">公布日期</td>
					<td><span class="comboSpan"></span> <input id ="ThirdpublicDate"  name="publicDate"   class="table_input easyui-datebox " data-options="required:true,validType:['length[0,10]','validDate']"></td>
				</tr>
				<tr>
				    <td class="table_text" align="right">净值类型</td>
					<td align="left"><span class="comboSpan"></span> <input id="thirdnetType"  name="netType" class="table_input easyui-combobox " data-options="required:true"></td>
					<td class="table_text" align="right">净值</td>
					<td><input id="thirdNetValue" name="netValue"  class="table_input easyui-validatebox" data-options="validType:['validDecNum']"  ></td>
					<td class="table_text" align="right" >是否生成预估短信</td> 
					<td><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="updateNetValue_isOrder" name="isOrder" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="table_text" align="right" >是否生成月中/月末短信</td> 
					<td><span class="comboSpan"></span><input class="table_input easyui-combobox"  id="updateNetValue_specialType" name="specialType"></td>	
					<td class="table_text" align="right" >净值涨幅(%)</td> 
				    <td align="left"><input class="table_input easyui-validatebox" name="earnRate" id="updateEarnRate"  data-options="validType:['validDecNum']" /></td>
				</tr>
			</table>
		</form>
		</div>
		<br>
		<a href="#"  onclick="updateNetValueInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">提交</a>
		<a href="#"  onclick="backLisNetValuePage()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
</div>