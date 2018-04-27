<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/detailBuildInit.js"></script>
<div id="tabdiv">
	<form id="detailBuildForm">
	<div id="detBuild">
		<div id="buildingBasicInfoDiv" class="easyui-panel"title="基本信息" collapsible="true">
			<div class="top_table">
				<table class="input_table" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">楼盘代码</td>
						<td align="left"><input name="buildingCode" id="buildingCode" class="table_input" disabled="disabled"/></td>
						<td class="table_text" align="right">楼盘名称</td>
						<td align="left"><input name="buildingName" id="buildingName" class="table_input" disabled="disabled"/></td>
						<td class="table_text" align="right">楼盘类型</td>
						<td align="left"><span class="comboSpan"></span><input name="buildingType" id="detBuildingType" class = "table_select easyui-combobox" disabled="disabled"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">户数</td>
						<td align="left"><input name="stayNum" id="stayNum" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">入住率(%)</td>
						<td align="left"><input name="stayRate" id="stayRate" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">开发商</td>
						<td align="left"><input name="developer" id="developer" class="table_input" disabled="disabled"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">房屋均价(元/m)</td>
						<td align="left"><input name="avgprice" id="avgprice" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">产权年限</td>
						<td align="left"><input name="propertiesYears" id="propertiesYears" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">服务网点</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="serviceStoreId" id="detailBuildingServiceStore" class="table_input easyui-combobox1" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"></span><input name="province"  id = "province" class = "table_select easyui-combobox" disabled="disabled"/></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"></span><input name="city" id="city" class="table_select easyui-combobox" disabled="disabled"/></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"></span><input name="country" id="country" class="table_select easyui-combobox" disabled="disabled"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">详细地址</td>
						<td align="left" colspan="3"><input name="street" id="street" class="table_input1" disabled="disabled"></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input name="zipCode" id="zipCode" class="table_input" disabled="disabled"></td>
					</tr>
					<td class="table_text" align="right">合作关系</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="isCooperation"  id = "detailIsCooperation" class = "table_input easyui-combobox1" disabled="disabled">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
				</table>
			</div>
		</div>
		<div id="smsaccordion" class="easyui-panel" title="物业信息" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">物业类型</td>
						<td align="left"><span class="comboSpan"></span><input name="estateType" id="detEstateType" class = "table_select easyui-combobox" disabled="disabled"></td>
						<td class="table_text" align="right">在管户数</td>
						<td align="left"><input name="controlHourse" id="controlHourse" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">实际接管面积</td>
						<td align="left"><input  name="controlArea"  id = "controlArea" class = "table_input" disabled="disabled"></input></td>
					</tr>
					<tr>
						<td class="table_text" align="right">接管状况</td>
						<td align="left"><span class="comboSpan"></span><input name="controlState" id="detControlState" class="table_select easyui-combobox" disabled="disabled"></td>
						<td class="table_text" align="right">交付日期</td>
						<td align="left"><input name="dealDate" id="dealDate" class="table_input" disabled="disabled"></td>
						<td class="table_text" align="right">物业电话</td>
						<td align="left"><input name="estatePhone" id="estatePhone" class="table_input" disabled="disabled"></td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<a href="#" onclick="backListdetailBuildPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
	</div>
	</form>
</div>