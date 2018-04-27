<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/sales/searchAgentInit.js"></script>

<div id="tabdiv">
	<form id="searchAgent_SearchConditionForm">
		 <div id="smsaccordion" class="easyui-panel" collapsible="true"> 
			<div class="top_table" >
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">姓名</td>
						<td><input name="agentId" id="agent_name" class="table_input easyui-combobox"/></td>
							
						
							<td class="table_text" align="right">所属分公司</td>
							<td align="left">
							<span class="comboSpan"></span>
								<input name="comId" id="agent_belong_sub_com" class="table_input easyui-combobox"/>
							</td>
							
							<!-- <td class="table_text" align="right" style="display:none;">所属网点</td>
							<td align="left" style="display:none;">
								<span class="comboSpan"></span>
								<input name="storeId" id="agent_belong_store" class="table_input easyui-combobox">
							</td> -->
					</tr>
					
				</table>
				<div style="margin-bottom: 3px;">
					<a href="#" onclick="searchAgentomerInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="backAgentInfoPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">确定</a>
				</div>
				<table id="search_agentListTable"></table>
			</div>
		</div> 
	</form>
</div>