<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/addStoreInit.js"></script>

<div id="tabdiv">
	<form id="add_StoreInfoForm">
		<div id="add_storeBasicInfoDiv" class="easyui-panel" title="基本信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">网点代码</td>
						<td>
							<input type="hidden" id="add_storeId" name="storeId" class="inpuntHidden">
							<input name="storeCode" id="add_storeCode" class="table_input easyui-validatebox" 
							data-options="required:true,validType:['length[0,20]','validCode']" />
						</td>
						<td class="table_text" align="right">网点名称</td>
						<td align="left"><input name="storeName" id="add_storeName" class="table_input easyui-validatebox" 
						data-options="required:true,validType:['length[0,100]']"/></td>
						<td class="table_text" align="right">网点状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="state" id="add_state" class="table_input easyui-combobox1">
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">网点类型</td>
						<td>
							<span class="comboSpan"></span>
							<input name="type" id="add_type" class="table_input easyui-combobox1">
						</td>
						<td class="table_text" align="right">网点面积</td>
						<td align="left"><input name="area" id="add_area" class="table_input easyui-validatebox" 
						data-options="validType:['validDecNum']"/></td>
						<td class="table_text" align="right"><!-- 所在楼盘 --></td>
						<td align="left">
							<span class="comboSpan"></span>
							<!-- <input name="buildingId" id="add_buildingId" class="table_input easyui-combobox1"> -->
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">开业时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="startDate" id="add_startDate" 
								data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">停业时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="endDate" id="add_endDate"
								data-options="validType:['length[0,10]','validDate']">
						</td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "table_input easyui-combobox1" name="province"  id = "add_province" >
						</td>
						<td class="table_text" align="right">市</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox1" name="city" id="add_city">
						</td>
						<td class="table_text" align="right">区/县</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="country" id="add_country" class="table_input easyui-combobox1">
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right" >详细地址</td>
						<td align="left" colspan='3'><input class = "table_input1" name="street"  id = "add_street" /></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="zipCode"  id = "add_zipCode" 
						data-options="validType:['validZip']"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">网点电话</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="phone"  id = "add_phone" 
						data-options="validType:['length[0,20]','validTel']"/></td>
						<td class="table_text" align="right">网点传真</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="fax"  id = "add_fax" 
						data-options="validType:['length[0,20]','validTel']"/></td>
						<td class="table_text" align="right">工商执照编号</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="license"  id = "add_license" 
						data-options="validType:['length[0,100]','validCode']"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">负责人姓名</td>
						<td align="left"><input class = "table_input" name="managerName"  id = "add_managerName" /></td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="managerMobile"  id = "add_managerMobile" 
						data-options="validType:['validPhone']"/></td>
						<td class="table_text" align="right">选址完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="siteSelectionFinishTime" id="add_siteSelectionFinishTime" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">租赁完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseFinishTime" id="add_leaseFinishTime" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">装修完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="decorationFinishTime" id="add_decorationFinishTime" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">租赁起始日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseStartDate" id="add_leaseStartDate" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">租赁终止日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseEndDate" id="add_leaseEndDate" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">押金</td>
						<td align="left">
							<input class = "table_input easyui-numberbox" name="deposit"  id = "add_deposit" 
								data-options="precision:2"/>
						</td>
						<td class="table_text" align="right">中介费</td>
						<td align="left">
							<input class="easyui-numberbox table_input" name="agencyFee" id="add_agencyFee" 
								data-options="precision:2"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">发票税费</td>
						<td align="left">
							<input class = "table_input easyui-numberbox" name="invoiceTaxFee"  id = "add_invoiceTaxFee"
								data-options="precision:2" />
						</td>
						<td class="table_text" align="right">合同状况</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "table_input easyui-combobox1" name="contractCondition"  id = "add_contractCondition"/>
						</td>
						<td class="table_text" align="right">物管费标准</td>
						<td align="left">
							<input class="easyui-numberbox table_input" name="managementFeeStandards" id="add_managementFeeStandards" 
								data-options="precision:2"/>(元/月/㎡)
						</td>
					</tr>
					
				</table>
			</div>
		</div>
	</form>
	<div class="tableOuterDiv"></div>
	<div id="smsaccordion" class="easyui-panel" title="网点租金信息" collapsible="true">
		<table id="storeLeaseMoneyInfoTable"></table>
		<div id="storeLeaseMoneyInfoTable_tb" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-addrow',plain:true" onclick="storeLeaseMoneyInfoTableAddOneRow()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" onclick="storeLeaseMoneyInfoTableRemoveOneRow()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lockedit',plain:true" onclick="storeLeaseMoneyInfoTableLockOneRow()">锁定</a>
		</div>
	</div> 
	<div class="tableOuterDiv"></div>
	<form id="add_StoreBelongComForm">
		<div id="add_storeBelongInfoDiv" class="easyui-panel" title="归属信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">上级机构</td>
						<td align="left">
							<input type="hidden" id="add_blcomId" name="blcomId" class="inpuntHidden">
							<span class="comboSpan"></span>
							<select name="comId" id="add_comId" class="table_select easyui-combobox easyui-validatebox" data-options="required:true"></select>
						</td>
			 			<td class="table_text" align="right">归属开始日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<select class="easyui-datebox table_input easyui-validatebox" name="startDate" id="add_belongStartDate" 
								data-options="validType:['length[0,10]','validDate'],required:true"></select>
						</td>
						<td class="table_text" align="right">归属结束日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<select class="easyui-datebox table_input" name="endDate" id="add_belongEndDate"
								data-options="validType:['length[0,10]','validDate']"></select>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div class="tableOuterDiv"></div>

	<div>
		<a href="#" id="addStoreInfo" onclick="addStoreInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
			onclick="uploadStoreAttachInfo()" data-options="iconCls:'icon-redo'">上传附件</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
			onclick="uploadStoreImage()" data-options="iconCls:'icon-redo'">上传图片</a>
		<a href="#" onclick="backListStorePage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	</div>
	
</div>