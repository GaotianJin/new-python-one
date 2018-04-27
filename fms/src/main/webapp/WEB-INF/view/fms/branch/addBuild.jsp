<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/addBuildInit.js"></script>
<div id="tabdiv">
	<form id="addBuildForm">
		<div id="smsaccordion" class="easyui-panel" title="基本信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">楼盘代码</td>
						<td align="left"><input name="buildingCode" id="addBuildingCode" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,20]','validCode']"></td>
						<td class="table_text" align="right">楼盘名称</td>
						<td align="left"><input name="buildingName" id="addBuildingName"  class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,200]']"></td>
						<td class="table_text" align="right">楼盘类型</td>
						<td align="left"><span class="comboSpan"></span><input name="buildingType" id="addBuildingType" class = "table_input"data-options="required:true"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">户数</td>
						<td align="left"><input name="stayNum" id="addStayNum" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,10]','validNum']"></td>
						<td class="table_text" align="right">入住率(%)</td>
						<td align="left"><input name="stayRate" id="addStayRate" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,12]','validDecNum']"></td>
						<td class="table_text" align="right">开发商</td>
						<td align="left"><input name="developer" id="addDeveloper" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">房屋均价(元/㎡)</td>
						<td align="left"><input name="avgprice" id="addAvgprice" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,12]','validNum']"></td>
						<td class="table_text" align="right">产权年限</td>
						<td align="left"><input name="propertiesYears" id="addPropertiesYears" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,5]','validNum']"></td>
						<td class="table_text" align="right">服务网点</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="serviceStoreId" id="addBuildingServiceStore" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"></span><input name="province"  id = "addProvince" class = "table_input" ></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"></span><input name="city"  id = "addCity" class = "table_input" ></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"></span><input name="country"  id = "addCountry" class = "table_input"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">详细地址</td>
						<td align="left" colspan="3"><input name="street" id="addStreet" class="table_input1" width="400px"></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input name="zipCode" id="addZipCode" class="table_input easyui-validatebox" 
						data-options="validType:['validZip']"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">合作关系</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="isCooperation"  id = "addIsCooperation" class = "table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="smsaccordion" class="easyui-panel" title="物业信息" collapsible="true">
			<div class="top_table">
				<table width="100%" class="input_table"  border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">物业类型</td>
						<td align="left"><span class="comboSpan"></span><input name="estateType" id="addEstateType" class = "table_input" data-options="required:true"></td>
						<td class="table_text" align="right">在管户数</td>
						<td align="left"><input name="controlHourse" id="addControlHourse" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,5]','validNum']"></td>
						<td class="table_text" align="right">实际接管面积(㎡)</td>
						<td align="left"><input name="controlArea" id = "addControlArea" class = "table_input easyui-validatebox" 
						data-options="validType:['length[0,20]','validDecNum']"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">接管状况</td>
						<td align="left"><span class="comboSpan"></span><input name="controlState" id="addControlState" class="table_input"></td>
						<td class="table_text" align="right">交付日期</td>
						<td align="left"><span class="comboSpan"></span><input name="dealDate" id="addDealDate" class="table_input easyui-datebox" data-options="validType:['validDate']"></td>
						<td class="table_text" align="right">物业电话</td>
						<td align="left"><input name="estatePhone" id="addDstatePhone" class="table_input easyui-validatebox" 
						data-options="validType:['validTel']"></td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<a href="#" onclick="addBuild()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="submit">提交</a>
			<a href="#" onclick="backListaddBuildPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</form>
</div>