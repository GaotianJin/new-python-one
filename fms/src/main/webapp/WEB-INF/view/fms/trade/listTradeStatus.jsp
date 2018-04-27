<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/trade/listTradeStatusInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div class="outerPanel" id="outerPanel">
	<div id="smsaccordion" class="easyui-panel" fit="true" title="交易信息查询" collapsible="true">
		<form id="TradeStausForm">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">交易号码</td>
						<td><input class="table_input" id="statusTradeNo" name="tradeNo"></td>
						<!-- <td class="table_text" align="right">交易状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input  class="table_input" id="statusTradeStaus" name="tradeStaus">
						</td> -->
						<td class="table_text" align="right">合同号</td>
						<td><input class="table_input" id="statusTradeInfoNo" name="tradeInfoNo"></td>
						<td class="table_text" align="right">客户姓名</td>
						<td align="left"><input class="table_input" id="statusCustName" name="chnName"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right">基金管理人</td>
						<td align="left"><span class="comboSpan"></span><input name = "agencyComId" id="stausAgencyComId" class="table_input"></td>
						<td class="table_text" align = "right">产品</td>
						<td align="left"><span class="comboSpan"></span><input name = "productId" id="stausProductId" class="table_input"></td>
						<td class="table_text" align = "right">管理机构</td>
						<td align="left"><span class="comboSpan"></span><input name = "comId" id="stausComId" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align = "right"><!-- 承保日/成立日(时间)起期 -->投保日/认购日起期</td>
						<td align="left"><span class="comboSpan"></span><input name="tradeDateStart" id="stausDateStart" class="table_input easyui-datebox"></td>
						<td class="table_text" align = "right"><!-- 承保日/成立日(时间)止期 -->投保日/认购日止期</td>
						<td align="left"><span class="comboSpan"></span><input name="tradeDateEnd" id="stausDateEnd" class="table_input easyui-datebox"></td>
						<td class="table_text" align = "right">交易状态</td>
						<td align = "left">
							<span class="comboSpan"></span><input name="tradeStatus" id="stausTradeStatus" class="table_input">
						</td>					
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearTradeStaus()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="queryTradeStrusList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
				</div>
			</div>
		</form>
	<div  class="easyui-panel"  style="margin-top: 3px;">
		<table id="tradeStausTable"></table>
	</div>
	</div>
</div>