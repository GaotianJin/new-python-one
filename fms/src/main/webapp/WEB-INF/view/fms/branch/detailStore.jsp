<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/detailStoreInit.js"></script>

<div id="tabdiv">
	<form id="detail_StoreInfoForm">
		<div id="detail_storeBasicInfoDiv" class="easyui-panel" title="基本信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">网点代码</td>
						<td><input name="storeCode" id="detail_storeCode" class="table_input" disabled="disabled"/></td>
						<td class="table_text" align="right">网点名称</td>
						<td align="left"><input name="storeName" id="detail_storeName" class="table_input" disabled="disabled"/></td>
						<td class="table_text" align="right">网点状态</td>
						<td align="left"><span class="comboSpan"/><input name="state" id="detail_state" class="table_select easyui-combobox" disabled="disabled"></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">网点类型</td>
						<td><span class="comboSpan"/><input name="type" id="detail_type" class="table_select easyui-combobox" disabled="disabled"/></td>
						<td class="table_text" align="right">网点面积</td>
						<td align="left"><input name="area" id="detail_area" class="table_input" disabled="disabled"/></td>
						<!--修改-->
						<td class="table_text" align="right"><!-- 所在楼盘 --></td>
						<td align="left"><span class="comboSpan"/> <!-- <input name="buildingId" id="detail_buildingId" class="table_select easyui-combobox" disabled="disabled"> --></td>
						
					</tr>
					<tr>
						<td class="table_text" align="right">开业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="startDate" id="detail_startDate" disabled="disabled"/></td>
						<td class="table_text" align="right">停业时间</td>
						<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="endDate" id="detail_endDate" disabled="disabled"></td>
						<td class="table_text" align="right">&nbsp;</td>
						<td align="left">&nbsp;</td>
						
					</tr>
					<tr>
						<td class="table_text" align="right">省</td>
						<td align="left"><span class="comboSpan"/><input class = "table_select easyui-combobox" name="province"  id="detail_province" disabled="disabled"/></td>
						<td class="table_text" align="right">市</td>
						<td align="left"><span class="comboSpan"/><input class="table_select easyui-combobox" name="city" id="detail_city" disabled="disabled"/></td>
						<td class="table_text" align="right">区/县</td>
						<td align="left"><span class="comboSpan"/><input name="country" id="detail_country" class="table_select easyui-combobox" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right" >详细地址</td>
						<td align="left" colspan='3'><input class = "table_input1" name="street"  id = "detail_street" disabled="disabled"/></td>
						<td class="table_text" align="right">邮政编码</td>
						<td align="left"><input class = "table_input" name="zipCode"  id = "detail_zipCode" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">网点电话</td>
						<td align="left"><input class = "table_input" name="phone"  id = "detail_phone" disabled="disabled"/></td>
						<td class="table_text" align="right">网点传真</td>
						<td align="left"><input class = "table_input" name="fax"  id = "detail_fax" disabled="disabled"/></td>
						<td class="table_text" align="right">工商执照编号</td>
						<td align="left"><input class = "table_input" name="license"  id = "detail_license" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">负责人姓名</td>
						<td align="left"><input class = "table_input" name="managerName"  id = "detail_managerName" disabled="disabled"/></td>
						<td class="table_text" align="right">负责人手机</td>
						<td align="left"><input class = "table_input" name="managerMobile"  id = "detail_managerMobile" disabled="disabled"/></td>
						<td class="table_text" align="right">选址完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="siteSelectionFinishTime" id="detail_siteSelectionFinishTime" 
							data-options="validType:['length[0,10]','validDate']" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td class="table_text" align="right">租赁完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseFinishTime" id="detail_leaseFinishTime" 
							data-options="validType:['length[0,10]','validDate']" disabled="disabled"/>
						</td>
						<td class="table_text" align="right">装修完成时间</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="decorationFinishTime" id="detail_decorationFinishTime" 
							data-options="validType:['length[0,10]','validDate']" disabled="disabled"/>
						</td>
						<td class="table_text" align="right">租赁起始日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseStartDate" id="detail_leaseStartDate" 
							data-options="validType:['length[0,10]','validDate']" disabled="disabled"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">租赁终止日期</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class="easyui-datebox table_input" name="leaseEndDate" id="detail_leaseEndDate" 
							data-options="validType:['length[0,10]','validDate']" disabled="disabled"/>
						</td>
						<td class="table_text" align="right">押金</td>
						<td align="left">
							<input class = "table_input easyui-numberbox" name="deposit"  id = "detail_deposit" 
								data-options="precision:2" disabled="disabled"/>
						</td>
						<td class="table_text" align="right">中介费</td>
						<td align="left">
							<input class="easyui-numberbox table_input" name="agencyFee" id="detail_agencyFee" 
								data-options="precision:2" disabled="disabled"/>
						</td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">发票税费</td>
						<td align="left">
							<input class = "table_input easyui-numberbox" name="invoiceTaxFee"  id = "detail_invoiceTaxFee"
								data-options="precision:2" disabled="disabled"/>
						</td>
						<td class="table_text" align="right">合同状况</td>
						<td align="left">
							<span class="comboSpan"></span>
							<input class = "table_input easyui-combobox1" name="contractCondition"  id = "detail_contractCondition" disabled="disabled"/>
						</td>
						<td class="table_text" align="right">物管费标准</td>
						<td align="left">
							<input class="easyui-numberbox table_input" name="managementFeeStandards" id="detail_managementFeeStandards" 
								data-options="precision:2" disabled="disabled"/>(元/月/㎡)
						</td>
					</tr>
				</table>
			</div>
			<input type="hidden" name="storeId" id="detail_storeId" class="inputHidden">
		</div>
		 
	</form>
	<div class="tableOuterDiv"></div>
	
	<div id="smsaccordion" class="easyui-panel" title="网点租金信息" collapsible="true">
		<table id="storeLeaseMoneyInfoTable"></table>
	</div> 
	<div class="tableOuterDiv"></div>
	<div id="detail_storeBelongInfoDiv" class="easyui-panel" title="归属信息"  collapsible="true">
		<div class="top_table">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
				<tr>
					<td class="table_text" align="right">上级机构</td>
					<td align="left"><span class="comboSpan" align="left"/><select name="comId" id="detail_comId" class="table_select easyui-combobox" disabled="disabled"/></td>
		 			<td class="table_text" align="right">归属开始日期</td>
					<td align="left"><span class="comboSpan"/><input class="easyui-datebox table_input" name="belongStartDate" id="detail_belongStartDate" disabled="disabled"/></td>
					<td class="table_text" align="right">归属结束日期</td>
					<td align="left"><span class="comboSpan"/><select class="easyui-datebox table_input" name="belongEndDate" id="detail_belongEndDate" disabled="disabled"></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="tableOuterDiv"></div>
	<div class="tableOuterDiv"></div>
	<div>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
			onclick="queryStoreAttachInfo()" data-options="iconCls:'icon-redo'">查看附件</a>
		<a class="easyui-linkbutton e-cis_button" id="uploadTradeAttachInfoButton" 
			onclick="openUploadStoreImageWindow()" data-options="iconCls:'icon-redo'">查看图片</a>
		<a href="#" onclick="backListStorePage()" class="easyui-linkbutton e-cis_button" iconCls="icon-back">返回</a>
	</div>
	
</div>