<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/nb/bqTaskInit.js"></script>
<script type="text/javascript">

</script>


<div id="tabdiv">
	 <div id="claimInfo"  class="easyui-panel" fit="true" title="赔案信息" iconCls="icon-edit" collapsible="true" >
		  <div class="top_table">
			   <table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">理赔任务号：</td>
						<td align="left">
						<input type="hidden" id="id" name="id" value="${dbid}">
						  <input id="claimTaskNo" name="claimTaskNo" class="table_input" value="${claimTaskNo}" disabled="disabled">
						</td>
						<td class="table_text" align="right">保单号：</td>	
						  <td align="left">
						  <input id="policyNo" name="policyNo" class="table_input" value="${policyNo}" disabled="disabled">
						</td>
						<td class="table_text" align="right">是否照会：</td>	
						  <td align="left">
						  <input id="isNote" name="isNote" class="table_input" value="否" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">理赔类型：</td>
						<td align="left">
						  <select id="claimType" name="claimType"  class="easyui-validatebox table_select" required="required" onChange="queryClaimMoneyInfo()" disabled="disabled">
						      <option value=""></option>
			                  <option value="1" <c:if test="${claimType eq '1'}">selected</c:if>>死亡</option>
			                  <option value="2" <c:if test="${claimType eq '2'}">selected</c:if>>伤残</option> 
						</td>
						<td class="table_text" align="right">客户申请日期：</td>	
						  <td align="left">
						  <input id="applyDate" name="applyDate" type="text" class="easyui-datebox"  value="${applyDate}"  disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">出险日期：</td>	
						  <td align="left">
						  <input id="inDangerDate" name="inDangerDate"  type="text" class="easyui-datebox" value="${inDangerDate}" disabled="disabled"></input> 
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">出险原因：</td>
						<td align="left">
                           <input id="inDangerReason" type="text" class="easyui-validatebox table_input" required="required" value="${inDangerReason}" disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">疾病代码：</td>	
						  <td align="left">
						  <input id="sicknessCode" type="text" class="easyui-validatebox table_input" required="required" value="${sicknessCode}" onclick="querySinknessPage()" disabled="disabled"></input>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">意外类型代码：</td>	
						  <td align="left">
						  <input class="easyui-validatebox table_select" name="accClassCode" id="accClassCode" required="true" value="${accClassCode}" disabled="disabled">
						</td>
						<td class="table_text" align="right">意外身体代码：</td>	
						  <td align="left">
						  <input class="easyui-validatebox table_select" name="accBodyCode" id="accBodyCode" required="true"  value="${accBodyCode}" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">领号人：</td>
						<td align="left">
                           <input id="getTaskOperator" type="text" class="table_input" value="${getTaskOperator}" disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">领号日期：</td>	
						  <td align="left">
		                    <input id="getTaskDate" type="text" class="table_input"  value="${getTaskDate}"  disabled="disabled"></input> 
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">初审人：</td>
						<td align="left">
                           <input id="checkOperator" type="text" class="table_input"  value="${checkOperator}" disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">初审日期：</td>	
						  <td align="left">
		                    <input id="checkDate" type="text" class="table_input"  value="${checkDate}" disabled="disabled"></input> 
						</td>
					</tr>
				    <tr>
						<td class="table_text" align="right">复核人：</td>
						<td align="left">
                           <input id="approveOperator" type="text" class="table_input" disabled="disabled"></input> 
						</td>
						<td class="table_text" align="right">复核日期：</td>	
						  <td align="left">
		                    <input id="approveDate" type="text" class="table_input"  disabled="disabled"></input> 
						</td>
					</tr>
				</table>
			</div>
	</div>