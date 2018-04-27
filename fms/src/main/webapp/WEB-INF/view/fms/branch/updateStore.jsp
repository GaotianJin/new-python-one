<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/updateStoreInit.js"></script>

<div id="tabdiv">
	<form id="update_StoreInfoForm">
		<div id="update_storeBasicInfoDiv" class="easyui-panel" title="基本信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">网点代码</td>
						<td>
							<input name="storeCode" id="update_storeCode" class="table_input easyui-validatebox" 
								data-options="required:true,validType:['length[0,20]','validCode']"/>
						</td>
						<td class="table_text" align="right">网点名称</td>
						<td align="left">
							<input name="storeName" id="update_storeName" class="table_input easyui-validatebox" 
								data-options="required:true,validType:['length[0,100]']"/>
						</td>
						<td class="table_text" align="right">网点状态</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="state" id="update_state" class="table_input easyui-combobox1">
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">网点类型</td>
						<td>
							<span class="comboSpan"></span>
							<input name="type" id="update_type" class="table_input easyui-combobox1" />
						</td>
						<td class="table_text" align="right">网点面积</td>
						<td align="left">
							<input name="area" id="update_area" class="table_input easyui-validatebox" 
								data-options="validType:['validDecNum']"/>
						</td>
						<td class="table_text" align="right"><!-- 所在楼盘 --></td>
						<td align="left">
							<span class="comboSpan"></span>
							<!-- <input name="buildingId" id="update_buildingId" class="table_input easyui-combobox1"> -->
						</td> 
					</tr>
					<tr>
						<td class="table_text" align="right">开业时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox " name="startDate" id="update_startDate" 
								data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">停业时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="endDate" id="update_endDate"
								data-options="validType:['length[0,10]','validDate']"></td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
						
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "table_input easyui-combobox1" name="province"  id = "update_province" />
						</td>
						<td class="table_text" align="right">市</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="table_input easyui-combobox" name="city" id="update_city"/>
						</td>
						<td class="table_text" align="right">区/县</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="country" id="update_country" class="table_input easyui-combobox"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right" >详细地址</td>
						<td align="left" colspan='3'><input class = "table_input1" name="street"  id = "update_street" /></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input class = "table_input easyui-validatebox" name="zipCode"  id = "update_zipCode" 
						data-options="validType:['validZip']"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">网点电话</td>
						<td align="left">
							<input class = "table_input easyui-validatebox" name="phone"  id = "update_phone" 
								data-options="validType:['length[0,20]','validTel']"/>
						</td>
						<td class="table_text" align="right">网点传真</td>
						<td align="left">
							<input class = "table_input easyui-validatebox" name="fax"  id = "update_fax" 
								data-options="validType:['length[0,20]','validTel']"/>
						</td>
						<td class="table_text" align="right">工商执照编号</td>
						<td align="left">
							<input class = "table_input easyui-validatebox" name="license"  id = "update_license" 
								data-options="validType:['length[0,100]','validCode']"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">负责人姓名</td>
						<td align="left">
							<input class = "table_input" name="managerName"  id = "update_managerName" />
						</td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left">
							<input class = "table_input easyui-validatebox" name="managerMobile"  id = "update_managerMobile" 
							data-options="validType:['validPhone']"/>
						</td>
						<td class="table_text" align="right">选址完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="siteSelectionFinishTime" id="update_siteSelectionFinishTime" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">租赁完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseFinishTime" id="update_leaseFinishTime" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">装修完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="decorationFinishTime" id="update_decorationFinishTime" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">租赁起始日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseStartDate" id="update_leaseStartDate" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">租赁终止日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseEndDate" id="update_leaseEndDate" 
							data-options="validType:['length[0,10]','validDate']"/>
						</td>
						<td class="table_text" align="right">押金</td>
						<td align="left">
							<input class = "table_input easyui-numberbox" name="deposit"  id = "update_deposit" 
								data-options="precision:2"/>
						</td>
						<td class="table_text" align="right">中介费</td>
						<td align="left">
							<input class="easyui-numberbox table_input" name="agencyFee" id="update_agencyFee" 
								data-options="precision:2"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">发票税费</td>
						<td align="left">
							<input class = "table_input easyui-numberbox" name="invoiceTaxFee"  id = "update_invoiceTaxFee"
								data-options="precision:2" />
						</td>
						<td class="table_text" align="right">合同状况</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "table_input easyui-combobox1" name="contractCondition"  id = "update_contractCondition"/>
						</td>
						<td class="table_text" align="right">物管费标准</td>
						<td align="left">
							<input class="easyui-numberbox table_input" name="managementFeeStandards" id="update_managementFeeStandards" 
								data-options="precision:2"/>(元/月/㎡)
						</td>
					</tr>
				</table>
			</div>
		</div>
		<input type="hidden" name="storeId" id="update_storeId" class="inputHidden"> 
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
	
	<form id="update_StoreBelongComInfoForm">
	<div id="update_storeBelongInfoDiv" class="easyui-panel" title="归属信息"  collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">上级机构</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input name="comId" id="update_comId" class="table_input easyui-combobox1"/>
						</td>
			 			<td class="table_text" align="right">归属开始日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="startDate" id="update_belongStartDate" 
								data-options="validType:['length[0,10]','validDate'],required:true"/>
						</td>
						<td class="table_text" align="right">归属结束日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="endDate" id="update_belongEndDate"
								data-options="validType:['length[0,10]','validDate']">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<div>
		<a href="#" id="updateStoreInfo" onclick="updateStoreInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">提交</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
			onclick="uploadStoreAttachInfo()" data-options="iconCls:'icon-redo'">上传附件</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
			onclick="openUploadStoreImageWindow()" data-options="iconCls:'icon-redo'">上传图片</a>
		<a href="#" onclick="backListStorePage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
			
	</div>
	
</div>