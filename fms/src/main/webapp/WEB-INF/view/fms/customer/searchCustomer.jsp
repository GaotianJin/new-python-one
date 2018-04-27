<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/searchCustomerInit.js"></script>

<div id="tabdiv">
	<form id="searchCust_SearchConditionForm">
		 <div id="smsaccordion" class="easyui-panel" collapsible="true"> 
			<div class="top_table" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">客户姓名</td>
						<td><input name="chnName" id="search_chinesename" class="table_input"/></td>
						<td class="table_text" align="right">证件类型</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="idType" id="search_idtype" class="easyui-combobox1"></td>
						<td class="table_text" align="right">证件号码</td>
						<td align="left"><input class = "table_input" name="idNo"  id = "search_idno" /></td>
					</tr>
					
				</table>
				<div style="margin-bottom: 3px;">
					<a href="#" onclick="searchCustomerInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="backCustomerInfoPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">确定</a>
				</div>
				<table id="search_custListTable"></table>
			</div>
		</div> 
	</form>
</div>