<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/nb/listAllTaskInit.js"></script>
<script type="text/javascript">

</script>

<div class='hb'style="z-index: 2; background-color: white; width: 100%">
  <table style="font-size: 13px;">
  <!-- 
    <tr>
      <td style="text-align: right; vertical-align: middle;"> <span>查询</span></td>
      <td><a href="#" onclick="query();" class="easyui-linkbutton e-cis_button" style="text-align: right;">查询操作</a></td>
      <td><a href="#" onclick="detail();" class="easyui-linkbutton e-cis_button" style="text-align: right;">
       &nbsp;&nbsp;&nbsp;任务详细&nbsp;&nbsp;&nbsp;</a></td>
      <td><a href="#" onclick="toDo();" class="easyui-linkbutton e-cis_button" style="text-align: right;">任务执行</a></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"> <span>保全任务</span></td>
      <td><a href="#" onclick="applyBQ();" class="easyui-linkbutton e-cis_button" style="text-align: right;">申请保全</a></td>
      <td><a href="#" onclick="" class="easyui-linkbutton e-cis_button" style="text-align: right;">保全复核任务获取</a></td>
    </tr>
    <tr>
      <td style="text-align: right; vertical-align: middle;"> <span>理赔任务</span></td>
      <td><a href="#" onclick="applyLP();" class="easyui-linkbutton e-cis_button" style="text-align: right;">理赔领号</a></td>
      <td><a href="#" onclick="applyTaskLP();" class="easyui-linkbutton e-cis_button" style="text-align: right;">理赔初审任务获取</a></td>
      <td><a href="#" onclick="uwTaskLP();" class="easyui-linkbutton e-cis_button" style="text-align: right;">理赔复核任务获取</a></td>
    </tr>
     -->
     <tr>
        <td><a href="#" onclick="query();" class="easyui-linkbutton e-cis_button" style="text-align: right;"  iconCls="icon-search">查询</a></td>
        <td><a href="#" onclick="cancle();" class="easyui-linkbutton e-cis_button" style="text-align: right;" iconCls="icon-reload">任务撤销</a></td>
        <td><a href="#" onclick="detail();" class="easyui-linkbutton e-cis_button" style="text-align: right;" iconCls="icon-search">任务详情</a></td>
     </tr>
  </table>
</div>

<form id="taskForm">
<div style="position: absolute; top: 60px; width: 98%">
  <div id="tabdiv">
		<div id="taskinfo" class="easyui-panel" fit="true" title="多项条件查询" iconCls="icon-ok" collapsible="true">
			<div class="top_table">
			  <table border="0" cellspacing="0" cellpadding="0" style="width:100%">
					<tr>
						<td width="125" class="table_text" align="right">保单号</td>
						<td width="200" align="left"><input name="policyno" id="policyno" type="text" class="table_input"></td>
						
						<td width="125" class="table_text" align="right">任务号</td>
						<td width="200" align="left"><input name="taskno" id="taskno" type="text" class="table_input"></td>
						
						<td width="125" class="table_text" align="right">渠道</td>
						<td width="200" align="left">
						  <select class="easyui-combobox" name="salechnl" id="salechnl" style="width: 150px;" readonly="readonly">
						        <option value=""></option>
						        <option value="1">个险</option>
						        <option value="3">银保</option>
						        <!-- 
						        <option value="6">网销</option>
								<option value="2">团险</option>
								 -->
						  </select>
						</td>
					</tr>
					<tr>
					    <!-- 任务生成的日期 -->
						<td width="125" class="table_text" align="right">提交日期 从</td>
						<td width="200" align="left"><input name="FromsubmitDate" id="FromsubmitDate" class="easyui-datebox"></td>
						<td width="125" class="table_text" align="right">提交日期 到</td>
						<td width="200" align="left"><input name="TosubmitDate" id="TosubmitDate" class="easyui-datebox"></td>
						<td width="125" class="table_text" align="right">业务类型</td>
						<td width="200" align="left">
						  <select class="easyui-combobox" name="serviceType" id="serviceType" style="width: 150px;">
						        <option value=""></option>
								<option value="BQ">保全</option>
								<option value="LP">理赔</option>
						  </select>
						</td>
					</tr>
					<tr>
					    <td width="125" class="table_text" align="right">机构</td>
					    <td width="200" align="left">
						  <select class="easyui-combobox" name="companyId" id="companyId" style="width: 155px;"></select>
						</td>
						<td width="125" class="table_text" align="right">网点编码</td>
						<td width="200" align="left"><input name="agentCode" id="agentCode" type="text" class="table_input"></td>
						<td width="125" class="table_text" align="right">主险名称</td>
						<td width="200" align="left">
						  <select class="easyui-combobox" name="mainRiskCode" id="mainRiskCode" style="width: 150px;"></select>
						</td>
					</tr>
					<tr>
						<td width="125" class="table_text" align="right">操作人编码</td>
						<td width="200" align="left" colspan="5"><input name="operCode" id="operCode" type="text" class="table_input"></td>
					</tr>
					<tr>
					   <td width="125" class="table_text" align="right">保全任务状态 <input type="checkbox" id="allCheck1" name="allCheck1" onclick="BQAllChecked();"></td>
					    <td align="left" colspan="5">&nbsp;
						  <input type="checkbox" value="保全申请" name="BQTask" onclick="cancleAllCheck(1)"> 处理中
						  <input type="checkbox" value="待复核" name="BQTask" onclick="cancleAllCheck(1)"> 待复核
						  <input type="checkbox" value="保全复核" name="BQTask" onclick="cancleAllCheck(1)"> 复核中
						  <!-- <input type="checkbox" value="复核修改" name="BQTask" onclick="cancleAllCheck(1)"> 复核修改中 -->
						  <input type="checkbox" value="结案" name="BQTask" onclick="cancleAllCheck(1)"> 结案
						  <input type="checkbox" value="已撤销" name="BQTask" onclick="cancleAllCheck(1)"> 已撤销
						  <input type="hidden" name="BQTaskRs" id="BQTaskRs"> 
						</td>
					</tr>
					<tr>
					   <td width="125" class="table_text" align="right">理赔任务状态 <input type="checkbox" id="allCheck2" name="allCheck2" onclick="LPAllChecked();"></td>
					    <td align="left" colspan="5">&nbsp;
					      <input type="checkbox" value="待初审" name="LPTask" onclick="cancleAllCheck(2)"> 待初审
						  <input type="checkbox" value="初审" name="LPTask" onclick="cancleAllCheck(2)"> 初审中
						  <input type="checkbox" value="待复核" name="LPTask" onclick="cancleAllCheck(2)"> 待复核
						  <input type="checkbox" value="复核" name="LPTask" onclick="cancleAllCheck(2)"> 复核中
						  <input type="checkbox" value="结案" name="LPTask" onclick="cancleAllCheck(2)"> 结案
						  <input type="checkbox" value="已撤销" name="LPTask" onclick="cancleAllCheck(2)"> 已撤销
						  <input type="hidden" name="LPTaskRs" id="LPTaskRs">
						</td>
					</tr>
				</table>	
			</div>
		</div>
  </div>
</div>
</form>


<div style="position: absolute; top: 280px; width: 98%">
  <table id="taskTable"></table>
</div>  			
