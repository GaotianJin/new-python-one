<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/nb/bqTaskInit.js"></script>
<script type="text/javascript">

</script>

<div style="position: absolute; top: 10px;">
  <div id="tabdiv">
		<div id="taskinfo" class="easyui-panel" fit="true" title="保全任务信息" iconCls="icon-ok" collapsible="true">
			<div class="top_table">
			  <table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="125" class="table_text" align="right">保单号</td>
						<td width="200" align="left"><input name="policyno" id="policyno" type="text" class="table_input" value="${policyNo }" disabled="disabled"></td>
						
						<td width="125" class="table_text" align="right">任务号</td>
						<td width="200" align="left"><input name="taskno" id="taskno" type="text" class="table_input" value="${bussNo }" disabled="disabled"></td>
						
						<td width="125" class="table_text" align="right">业务类型</td>
						<td width="200" align="left"><input name="ServiceType" id="ServiceType" type="text" class="table_input" value="保全 " disabled="disabled"></td>
					</tr>
					<tr>
					    <!-- 任务生成的日期 -->
						<td width="125" class="table_text" align="right">批改申请日期</td>
						<td width="200" align="left"><input name="FromsubmitDate" id="FromsubmitDate" class="easyui-datebox" value="${appDate }" disabled="disabled"></td>
						<td width="125" class="table_text" align="right">批改生效日期</td>
						<td width="200" align="left"><input name="TosubmitDate" id="TosubmitDate" class="easyui-datebox" value="${effectiveDate }" disabled="disabled"></td>
						<td width="125" class="table_text" align="right">操作人</td>
						<td width="200" align="left"><input name="operator" id="operator" type="text" class="table_input" value="${operator }" disabled="disabled"></td>
					</tr>
					<tr>
					    <td width="125" class="table_text" align="right">保全类型</td>
						<td width="200" align="left"><input name="posType" id="posType" type="text" class="table_input" value="${postype }" disabled="disabled"></td>
					    <td width="125" class="table_text" align="right">保全类型名称</td>
						<td width="200" align="left"><input name="posType" id="posType" type="text" class="table_input" value="${postypename }" disabled="disabled"></td>
					    <td width="125" class="table_text" align="right">任务状态 </td>
					    <td width="200" align="left"><input name="taskStatus" id="taskStatus" type="text" class="table_input" value="${status }" disabled="disabled"></td>
					</tr>
					<tr>
					<td width="125" class="table_text" align="right">补/退费金额</td>
					<td width="200" align="left"><input name="money" id="money" type="text" class="table_input" value="${money }" disabled="disabled"></td>
					<td width="125" class="table_text" align="right">受理机构</td>
					<td width="200" align="left"><input name="posType" id="posType" type="text" class="table_input" value="${companyCode }" disabled="disabled"></td>
					<td width="125" class="table_text" align="right">机构名称</td>
					<td width="200" align="left"><input name="posType" id="posType" type="text" class="table_input" value="${companyName }" disabled="disabled"></td>
					</tr>
					
					<tr>
					<td width="125" class="table_text" align="right">复核状态</td>
					<td width="200" align="left"><input name="money" id="money" type="text" class="table_input" value="${approveStatus }" disabled="disabled"></td>
					<td width="125" class="table_text" align="right">复核时间</td>
					<td width="200" align="left"><input name="posType" id="posType" type="text" class="table_input" value="${approveTime }" disabled="disabled"></td>
					<td width="125" class="table_text" align="right">复核人员</td>
					<td width="200" align="left"><input name="posType" id="posType" type="text" class="table_input" value="${approveOperator }" disabled="disabled"></td>
					</tr>
					
				</table>	
			</div>
		</div>
  </div>
</div>