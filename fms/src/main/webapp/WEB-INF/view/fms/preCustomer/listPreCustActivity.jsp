<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/preCustomer/listPreCustActivityInit.js"></script>
<div id="listPreCustActivityTabdiv" class="outerPanel">
    <div id="smsaccordion" class="easyui-panel" fit="true" title="基本信息" collapsible="true">
        <form id="listPreCustActivityForm">
           <div class="top_table">
             <table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
                 <tr>
                    <td class="table_text",align="right">分公司</td>
                    <td align="left">
                            <span class="comboSpan"></span>
							<input name="comId" id="listPreCustActivity_comName" class="table_input easyui-combobox">
					</td>
					<td class="table_text",align="right">团队</td>
                    <td align="left">
                            <span class="comboSpan"></span>
							<input name="departmentId" id="listPreCustActivity_departmentName" class="table_input easyui-combobox">
					</td>
					<td class="table_text",align="right">财富顾问姓名</td>
                    <td align="left">
                            <span class="comboSpan"></span>
							<input name="agentId" id="listPreCustActivity_agentId" class="table_input">
					</td>
					<!-- <td class="table_text",align="right">网点</td>
                    <td align="left">
                            <span class="comboSpan"></span>
							<input name="storeId" id="listPreCustActivity_storeName" class="table_input easyui-combobox">
					</td> -->
                 </tr>
                 <tr>
                    
					<td class="table_text",align="right">财富顾问工号</td>
					<td align="left">
					       <span calss="combospan"></span>
					       <input name="agentCode" id="listPreCustActivity_agentCode" class="table_input">
					<td align="right" class="table_text">拜访时间</td>
					<td align="left">
							<span class="comboSpan"></span>
							<input name="preCustVisitStartTime" id="listPreCustActivity_preCustVisitStartTime" class="table_input2 easyui-datebox">
							至
							<input name="preCustVisitEndTime" id="listPreCustActivity_preCustVisitEndTime" class="table_input2 easyui-datebox">
					</td>
					<td align="left">&nbsp;</td>
					<td align="left">&nbsp;</td>
                 </tr>
             </table>
             <div>
					<a href="#" onclick="clearPreCustActivityCondition()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">清空</a>
					<a href="#" onclick="preCustActivityInfoList()" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询</a>
					<a href="#" onclick="exportDetailList()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出明细表</a>
					<a href="#" onclick="exportActivityManagementList()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出活动量管理打分表</a>
					<!-- <a href="#" onclick="exportSummary()" class="easyui-linkbutton e-cis_button" iconCls="icon-redo">导出汇总表</a> -->
			 </div> 
           </div>
        </form>
           <div style="margin-top: 3px;" id="listPreCustActivityTablediv">
              <table id="listPreCustActivityTable"></table>
           </div>
    </div>
</div>