<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/updateBuildInit.js"></script>

<div id="tabdiv">
	<form id="updateBuildForm">
	<div id="build">
		<div id="buildingBasicInfoDiv" class="easyui-panel"title="基本信息" collapsible="true">
			<div class="top_table">
				<table class="input_table" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_text" align="right">楼盘代码</td>
						<td align="left"><input name="buildingCode" id="updBuildingCode" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,20]','validCode']"/></td>
						<td class="table_text" align="right">楼盘名称</td>
						<td align="left"><input name="buildingName" id="updBuildingName" class="table_input easyui-validatebox" data-options="required:true,validType:['length[0,200]']"/></td>
						<td class="table_text" align="right">楼盘类型</td>
						<td align="left"><span class="comboSpan"></span><input class = "table_select easyui-combobox" name="buildingType" id="updBuildingType" 
						data-options="required:true"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">户数</td>
						<td align="left"><input name="stayNum" id="stayNum" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,10]','validNum']"></td>
						<td class="table_text" align="right">入住率(%)</td>
						<td align="left"><input name="stayRate" id="stayRate" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,12]','validDecNum']"></td>
						<td class="table_text" align="right">开发商</td>
						<td align="left"><input name="developer" id="developer" class="table_input" value=""></td>
					</tr>
					<tr>
						<td class="table_text" align="right">房屋均价(元/m)</td>
						<td align="left"><input name="avgprice" id="avgprice" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,12]','validNum']"></td>
						<td class="table_text" align="right">产权年限</td>
						<td align="left"><input name="propertiesYears" id="propertiesYears" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,5]','validNum']"></td>
						<td class="table_text" align="right">服务网点</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="serviceStoreId" id="updBuildingServiceStore" class="table_input easyui-combobox1">
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"/></span><input class = "table_input easyui-combobox" name="province"  id = "province" /></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"/></span><input class="table_input easyui-combobox" name="city" id="city"/></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"/></span><input name="country" id="country" class="table_input easyui-combobox"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">详细地址</td>
						<td align="left" colspan="3"><input name="street" id="street" class="table_input1"></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input name="zipCode" id="zipCode" class="table_input easyui-validatebox" 
						data-options="validType:['validZip']"></td>
					</tr>
					<td class="table_text" align="right">合作关系</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="isCooperation"  id = "updIsCooperation" class = "table_input easyui-combobox1">
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
						<td align="left"><span class="comboSpan"></span><input name="estateType" id="updEstateType" class = "table_select easyui-combobox" 
						data-options="required:true"></td>
						<td class="table_text" align="right">在管户数</td>
						<td align="left"><input name="controlHourse" id="controlHourse" class="table_input easyui-validatebox" 
						data-options="validType:['length[0,5]','validNum']"></td>
						<td class="table_text" align="right">实际接管面积</td>
						<td align="left"><input  name="controlArea" id = "controlArea" class = "table_input easyui-validatebox" 
						data-options="validType:['length[0,20]','validDecNum']"></td>
					</tr>
					<tr>
						<td class="table_text" align="right">接管状况</td>
						<td align="left"><span class="comboSpan"></span><input name="controlState" id="updControlState" class="table_select easyui-combobox"></td>
						<td class="table_text" align="right">物业电话</td>
						<td align="left"><input name="estatePhone" id = "estatePhone" class="table_input easyui-validatebox" 
						data-options="validType:['validTel']"></td>
						<td class="table_text" align="right">交付日期</td>
						<td align="left"><span class="comboSpan"></span><input name="dealDate" id="dealDate" class="table_input easyui-datebox" data-options="validType:['validDate']"></td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<span style="display:none"><input name="buildingId" id="buildingId" class="table_input"></span>
		</div>
		<div>
			<a href="#" onclick="updateBuildInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
			<a href="#" onclick="backListupdateBuildPage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
		</div>
		</div>
	</form>
</div>