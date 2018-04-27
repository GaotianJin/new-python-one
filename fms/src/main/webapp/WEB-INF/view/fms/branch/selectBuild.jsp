<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/selectBuildInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<div id="tabdiv">
	<form id="selectBuildForm">
		<div id="smsaccordion" class="easyui-panel"title="基本信息" collapsible="true">
			<div class="top_table">
				<table class="input_table" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">楼盘代码</td>
						<td align="left"><input name="buildingCode" id="buildingCode" class="table_input" value=""></td>
						<td class="table_text" align="right">楼盘名称</td>
						<td align="left"><input name="buildingName" id="buildingName" class="table_input" value=""></td>
						<td class="table_text" align="right">楼盘类型</td>
						<td align="left"><select name="buildingType" id = "buildingType" class = "table_select" ></select></td>
					</tr>
					<tr>
						<td class="table_text" align="right">户数</td>
						<td align="left"><input name="stayNum" id="stayNum" class="table_input" value=""></td>
						<td class="table_text" align="right">入住率(%)</td>
						<td align="left"><input name="stayRate" id="stayRate" class="table_input" value=""></td>
						<td class="table_text" align="right">开发商</td>
						<td align="left"><input name="developer" id="developer" class="table_input" value=""></td>
					</tr>
					<tr>
						<td class="table_text" align="right">房屋均价(元/m)</td>
						<td align="left"><input name="avgPrice" id="avgPrice" class="table_input" value=""></td>
						<td class="table_text" align="right">产权年限</td>
						<td align="left"><input name="propertiesYears" id="propertiesYears" class="table_input" value=""></td>
						<td class="table_text" align="right"> </td>
						<td class="table_text" align="right"> </td>
					</tr>
					<tr>
						<td class="table_text" align="right">楼盘地址</td>
						<td class="table_text" colspan="5">
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><select name="province" id = "province" class = "table_select"></select></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><select name="city" id = "city" class = "table_select"></select></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><select name="country" id = "country" class = "table_select"></select></td>
					</tr>
					<tr>
						<td class="table_text" align="right">详细地址</td>
						<td align="left" colspan="3"><input name="street" id="street" class="table_road" value=""></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input name="zipCode" id="zipCode" class="table_input" value=""></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="smsaccordion" class="easyui-panel" title="物业信息" iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">物业类型</td>
						<td align="left"><select name="estateType" id="estateType" class="table_select" value=""></select></td>
						<td class="table_text" align="right">在管户数</td>
						<td align="left"><input name="controlHourse" id="controlHourse" class="table_input" value=""></td>
						<td class="table_text" align="right">实际接管面积</td>
						<td align="left"><input  name="controlArea"  id = "controlArea" class = "table_input" ></input></td>
					</tr>
					<tr>
						<td class="table_text" align="right">接管状况</td>
						<td align="left"><input name="controlState" id="controlState" class="table_input" value=""></td>
						<td class="table_text" align="right">交付日期</td>
						<td align="left"><input name="dealDate" id="dealDate" class="table_input" value=""></td>
						<td class="table_text" align="right">物业电话</td>
						<td align="left"><input name="estatePhone" id="estatePhone" class="table_input" style = "width:40px">-<input name="rolecode" id="rolecode" class="table_inputph" style = "width:100px"></td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<a href="#" onclick="updateBuild()" class="easyui-linkbutton e-cis_button" iconCls="icon-add">返回</a>
		</div>
	</form>
</div>