<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/product/updateProductDefInit.js"></script>
<input type="hidden" name="modifyProductId" id="modifyBasicProductId" value="${productId}">
<input type="hidden" name="modifyProductTypeCode" id="modifyBasicProductType" value="${productTypeCode}">
<input type="hidden" name="modifyProductSubTypeCode" id="modifyBasicProductSubType" value="${productSubTypeCode}">
<input type="hidden" name="modifyProductStatus" id="modifyBasicProductStatus" value="${productStatus}">

<!-- 基本信息 -->
<div class="easyui-tabs" id="updateProductTabs" fit="true" >
	<div title="基本信息">
		<div class="easyui-panel"  fit="true" title="产品基本信息" collapsible="true">
			<form id="updateProduct_basicInfoForm">
				<div class="top_table" id="basicInfo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
						<tr>
							<td class="table_text" align="right">基金管理人</td>
							<td><span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="updateProduct_agencyCode" name="agencyComId" data-options="required:true"></td>
							<td class="table_text" align="right">产品代码</td>
							<td align="left">
								<input class="table_input easyui-validatebox" name="productCode" id="updateProduct_productCode" data-options="required:true,validType:['length[0,20]','validCode']" Onchange="productCodeToUpperCase()"/>
							</td>
							<td class="table_text" align="right">产品名称</td>
							<td align="left">
								<input class="table_input easyui-validatebox" name="productName" id="updateProduct_productName" data-options="required:true,validType:['length[0,100]']"/>
							</td>
						</tr>
						<tr>
							<td class="table_text" align="right">产品简称</td>
							<td align="left">
								<input class="table_input easyui-validatebox" name="productShortName" id="updateProduct_productShortName" />
							</td>
							<td class="table_text" align="right">产品类型</td>
							<td><span class="comboSpan"></span>
								<input class="table_input easyui-combobox" name="productType" id="updateProduct_productType" data-options="required:true"  disabled="disabled"/>
							</td>
							<td class="table_text" align="right">产品子类型</td>
							<td><span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="updateProduct_productSubType" name="productSubType" data-options="required:true" disabled="disabled" />
							</td>
						</tr>
						<tr>
						    <td class="table_text" align="right">外部引入</td>
							<td align="left"><span class="comboSpan"></span>
								<input class="table_input easyui-datebox" name="introduceDate" id="updateProduct_introduceDate" data-options="required:true,validType:['length[0,10]','validDate']"/>
							</td>
							<td class="table_text" align="right">销售状态</td>
							<td>
								<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="updateProduct_salesStatus" name="salesStatus" data-options="required:true">
							</td>
							<td class="table_text" align="right">产品备注</td>
							<td align="left">
								<input class="table_input easyui-validatebox" name="remark" id="updateProduct_remark" />
							</td>
		                    <td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="table_text" align="right">财富顾问预约</td>
		                    <td>
		                    	<span class="comboSpan"></span>
								<input class="table_input easyui-combobox1" id="updateProduct_isOrder" name="isOrder">
							</td>
						   <!--  <td class="table_text" align="right">是否生成邀请码</td>
		                    <td>
		                    	<span class="comboSpan"></span>
								<input class="table_input easyui-combobox" id="updateProduct_isInviteCode" name="isInviteCode">
							</td> -->
							<td class="table_text"  align="right">产品经理姓名</td>
							<td>
								<input class="table_input easyui-validatebox" name="productManager" id="productManager" />
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</form>
			<div style="margin-bottom: 3px;">
				<a href="#" onclick="updateProductBasicInfo()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">保存</a>
			</div>
		</div>
	</div>
</div>



