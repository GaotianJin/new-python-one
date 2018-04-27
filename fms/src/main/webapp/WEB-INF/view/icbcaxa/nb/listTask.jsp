<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/icbcaxa/nb/listTaskInit.js"></script>
<script type="text/javascript">

function xxxx(){
	$.post("policyXXXX",  function(data) {
		if(data.mes=="success"){
			parent.addtab('理赔初审','claim/claimCheckUrl');
		}else{
			$.messager.alert('提示信息','没有理赔初审任务','info');
		}
	});	
}

</script>


      <div>
      <table>
      <tr>
      <td class = "botton_title">查询</td>
      <td>
      <a href="#" onclick="query();" class="easyui-linkbutton e-cis_button" iconCls="icon-search">查询操作</a>
      <a href="#" onclick="detail();" class="easyui-linkbutton e-cis_button" iconCls="icon-search">任务详情</a>
      <a href="#" onclick="toDo();" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">任务处理</a>
      </td>
      </tr>
      <tr>
      <td class = "botton_title">
    	  保全任务 
      </td>
      <td>
      	<a href="#" onclick="applyBQ();" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">申请保全</a>
      	<a href="#" onclick="getBQCheck();" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">复核任务获取</a>
      </td>
      </tr>
      <tr>
      <td class = "botton_title">理赔任务 </td>
      <td><a href="#" onclick="applyLP();" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">理赔领号</a>
      	<a href="#" onclick="applyTaskLP();" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">初审任务获取</a>
        <a href="#" onclick="uwTaskLP();" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">复核任务获取</a></td>
      </tr>
      </table>
       </div>
       
       <!--  
       <div>
         	<a href="#" onclick="xxxx()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">XXXXXX</a>
       </div>
 -->

<div style="width:98%; margin-top: 3px;">
  <div id="tabdiv">
		<div id="taskinfo" class="easyui-panel" title="多项条件查询" iconCls="icon-ok" collapsible="true">
			<div class="top_table">
			<form id="taskForm" style="padding: 0px; margin: 0px;">
			  <table border="0" cellspacing="0" cellpadding="0" style="width:100%;">
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
								<option value="2">团险</option>
								<option value="6">网销</option>
								 -->
						  </select>
						</td>
					</tr>
					<tr>
					    <!-- 任务生成的日期 -->
						<td width="125" class="table_text" align="right">提交日期 从</td>
						<td width="200" align="left">
						<input name="FromsubmitDate" id="FromsubmitDate" type="text" class="easyui-validatebox easyui-my97 table_input" >
						</td>
						<td width="125" class="table_text" align="right">提交日期 到</td>
						<td width="200" align="left">
						<input name="TosubmitDate" id="TosubmitDate" type="text" class="easyui-validatebox easyui-my97 table_input" >
						</td>
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
					   <td width="125" class="table_text" align="right">保全任务状态 <input type="checkbox" id="allCheck1" name="allCheck1" onclick="BQAllChecked();"></td>
					    <td align="left" colspan="5">&nbsp;
						  <input type="checkbox" value="保全申请" name="BQTask" onclick="cancleAllCheck(1)"> 处理中
						  <input type="checkbox" value="保全复核" name="BQTask" onclick="cancleAllCheck(1)"> 复核中
						  <!-- <input type="checkbox" value="复核修改" name="BQTask"> 复核修改中 -->
						  <input type="hidden" name="BQTaskRs" id="BQTaskRs">
						</td>
					</tr>
					<tr>
					   <td width="125" class="table_text" align="right">理赔任务状态 <input type="checkbox" id="allCheck2" name="allCheck2" onclick="LPAllChecked();"></td>
					    <td align="left" colspan="5">&nbsp;
						  <input type="checkbox" value="初审" name="LPTask" onclick="cancleAllCheck(2)"> 初审中
						  <input type="checkbox" value="复核" name="LPTask" onclick="cancleAllCheck(2)"> 复核中
						  <input type="hidden" name="LPTaskRs" id="LPTaskRs">
						</td>
					</tr>
				</table>
				</form>	
			</div>
		</div>
  </div>
</div>
<div style="top: 0px; width: 98%; margin-top: 3px;">
  <table id="taskTable"></table>
</div>  			
