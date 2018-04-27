<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/updateOpenDayInit.js"></script>
<div id="tabdiv">
	<form id="openDayInfoForm">
		<div class="easyui-panel" title="开放日信息修改" collapsible="true" id="updateOpenDayDiv">
			<div class="top_table">
				<table class='input_table' width="100%" border="0" cellspacing="0"  cellpadding="0">
					<tr>
					    <td class="table_text" align="right">基金管理人</td>
						<td> 
							<input  id="ThirdagencyComId" name="agencyComId" class="table_input easyui-validatebox" disabled="disabled">
						</td>
						<td class="table_text" align="right">产品</td>
						<td> 
							<input  id="ThirdproductId" name="productId" type="hidden">
							<input  id="ThirdproductName" name="productName" class="table_input easyui-validatebox"  disabled="disabled">
						</td>
						<td class="table_text" align="right">开放日</td>
						<td>
							<span class="comboSpan"></span> 
							<input id ="ThirdOpenDate"  name="openDate"   class="table_input easyui-datebox" data-options="required:true,validType:['length[0,10]','validDate']">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">募集起期</td>
						<td>
							<span class="comboSpan"></span>
							<!-- <input name="idValidityDate"  id = "addCust_idValidityDate" class="table_input easyui-datebox"/> -->
							<input class="table_input easyui-datetimebox1" id="ThirdInvestStartDate" name="investStartDate"/>
						</td>
						<td class="table_text" align="right">募集止期</td>
						<td>
							<span class="comboSpan"></span>
							<input class="table_input easyui-datetimebox1" id="ThirdInvestEndDate" name="investEndDate"/>
						</td>
						<td align="right" class="table_text">本期融资规模(万元)</td>
						<td align="left">
							<input class="table_input easyui-validatebox" name="financingScale" id="updfinancingScale" data-options="validType:['validDecNum']" />
						</td>
					</tr> 
				</table>
				<div class="tableOuterDiv"></div>
				<div id="updateOpenDayButton">
					<a href="#"  onclick="updateOpenDayInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">提交</a>
					<a href="#"  onclick="backLisOpenDayPage()" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
				</div>
				<div class="tableOuterDiv"></div>
			</div>
		</div>
	</form>
</div>