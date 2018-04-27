<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="../js/fms/product/updateProductDefInit.js"></script>
<script type="text/javascript" src="../js/fms/common/commonutil.js"></script>
<!--产品详细信息查询页面 -->
<div id="tabdiv">
	<form id="updateProductdefInfoForm">
	<div id="basicInfo" class="easyui-panel"  title="基本信息"
			iconCls="icon-tick" collapsible="true">
	<!-- 基本信息 -->
		<div id="basicInfo" class="easyui-panel"  title="基本信息"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">基金管理人</td>
						<td align="left"><select class = "table_select" name="agency"  id = "agency" /></td>
						<td class="table_text" align="right">产品代码</td>
						<td align="left"><input  class="table_input" name="productCode" id="productCode" /></td>
						<td class="table_text" align="right">产品名称</td>
						<td align="left"><input class = "table_input" name="productName"  id = "last_name"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">产品类型</td>
						<td><select name="productType" id="productType" class="table_select"  onchange="showProductType()"/></td>
						<td class="table_text" align="right">产品子类</td>
						<td><select name="productSubType" id="productSubType" class="table_select"/></td>
						<td class="table_text" align="right">外部引入</td>
						<td align="left"><input class="easyui-datetimebox" name="introduceDate" id="introduceDate"/></td>
					</tr>
					<tr>
						<td class="table_text" align="right">销售状态</td>
					    <td><select name="salesStatus" id="salesStatus" class="table_select"/></td>
						<td class="table_text" align="right">开办日期</td>
						<td align="left"><input class="easyui-datetimebox" name="startDate" id="startDate"/></td>
						<td class="table_text" align="right">快速录入参照</td>
					    <td><select name="inputReference" id="inputReference" class="table_select"/></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 财富产品信息 -->
		<div id="wealthProductInfo" class="easyui-panel" title="财富产品信息"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >产品类别</td> 		
						<td><select name="wealthCategory" id="wealthCategory" class="table_select"/></td>
						<td  align="right" >成立日</td>
						<td align="left"><input class="easyui-datetimebox" name="foundDate" id="foundDate"/></td>
						<td  align="right" >成立/融资规模(万元)</td>
						<td align="left"><input class = "table_input" name="financingScale"  id = "financingScale" /></td>
					</tr>
					<tr>
						<td  align="right" >风险等级</td>
						<td><select name="level" id="level" class="table_select"/></td>
						<td  align="right" >封闭期(数值)</td> 		
						<td align="left"><input class = "table_input" name="closedPeriod"  id = "closedPeriod" /></td>
						<td  align="right" >封闭期限(数值单位)</td> 		
						<td><select name="closedPeriodUnit" id="closedPeriodUnit" class="table_select"/></td>
					</tr>
					<tr>
					
						<td  align="right" >小号数量(上限)</td> 		
						<td align="left"><input class = "table_input" name="smallNumber"  id = "smallNumber" /></td>
							<td  align="right" >募集开始日</td>
						<td align="left"><input class="easyui-datetimebox" name="raiseStartDate" id="raiseStartDate"/></td> 
						<td  align="right" >募集结束日</td>
						<td align="left"><input class="easyui-datetimebox" name="raiseEndDate" id="raiseEndDate"/></td>
					</tr>
					 <tr>
						<td align="left"  class="table_text">资金用途</td> 
						<td align="left" colspan="5"  class="table_input"><textArea  name="fundsUsing" id="fundsUsing" rows="1" cols="100"></textArea></td>
					</tr>
					 <tr>
						<td align="left"  class="table_text">其他说明</td> 
						<td align="left" colspan="5"  class="table_input"><textArea  name="fundsUsing" id="fundsUsing" rows="1" cols="100"></textArea></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 寿险产品信息 -->
			<div id="insuranceInfo" class="easyui-panel" title="保险产品信息"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >主附险标记</td> 		
						<td><select name="prflag" id="prflag" class="table_select"/></td>
						<td  align="right" >保障对象</td>
						<td><select name="insurObj" id="insurObj" class="table_select"/></td>
						<td  align="right" >预订利率(%)</td>
						<td align="left"><input class = "table_input" name="interestRate"  id = "interestRate" /></td>
					</tr>
					<tr>
						
						<td  align="right" >最小投保年龄(数值)</td> 		
						<td><select name="smallNumber" id="smallNumber" class="table_select"/></td>
						<td  align="right" >最小投保年龄(数值单位)</td> 		
						<td><select name="smallNumberUnit" id="smallNumberUnit" class="table_select"/></td>
						<td  align="right" >产品特征</td> 		
						<td align="left"><input class = "table_input" name="closedPeriod"  id = "closedPeriod" /></td>
				    </tr>	
				    <tr>
						<td  align="right" >最大投保年龄(数值)</td>
						<td align="left"><input class="easyui-datetimebox" name="raiseStartDate" id="raiseStartDate"/></td> 
						<td  align="right" >最大投保年龄(数值单位)</</td>
						<td><select name="smallNumber" id="smallNumber" class="table_select"/></td>
						<td></td>
						<td></td>
					</tr>
						<tr>
						<td align="left"  class="table_text">其他说明</td> 
						<td align="left" colspan="5"  class="table_input"><textArea  name="fundsUsing" id="fundsUsing" rows="1" cols="100"></textArea></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 分类信息(浮动/股权类) -->
			<div id="fdiffrentInfo" class="easyui-panel" title="(浮动/股权)分类信息"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >认购费比例(%)</td> 		
						<td align="left"><input class = "table_input" name="financingScale"  id = "financingScale" /></td>
						<td  align="right" >认购费比例支付时间</td>
						<td align="left"><input class="easyui-datetimebox" name="financingPayDate" id="financingPayDate"/></td>
						<td  align="right" >浮动管理费比例(%)</td> 		
						<td align="left"><input class = "table_input" name="floatScale"  id = "floatScale" /></td>
					</tr>
					<tr>
						<td  align="right" >浮动管理费支付时间</td>
						<td align="left"><input class="easyui-datetimebox" name="floatScalePayDate" id="floatScalePayDate"/></td>
						<td  align="right" >固定管理费比例(%)</td> 		
						<td align="left"><input class = "table_input" name="fixedScale"  id = "fixedScale" /></td>
						<td  align="right" >固定管理费支付时间</td>
						<td align="left"><input class="easyui-datetimebox" name="fixedScalePayDate" id="fixedScalePayDate"/></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 分类信息(固定类) -->
			<div id="gdiffrentInfo" class="easyui-panel" title="(固定)分类信息"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >咨询服务费支付时间</td> 		
						<td align="left"><input class = "easyui-datetimebox" name="consultServicePayDate"  id = "consultServicePayDate" /></td>
						<td/><td/><td/><td/>
					</tr>
				</table>
			</div>
		</div>
		<!-- 财富类费用比例 -->
		<div id="wealthFeeInfo" class="easyui-panel" title="(固定)费用比例"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table id="wealthRateTable"></table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >费用类型</td> 		
						<td><select name="feeType" id="feeType" class="table_select"/></td>
						<td  align="right" >认购金上限</td> 		
						<td><select name="buyFeeUpper" id="buyFeeUpper" class="table_select"/></td>  
						<td  align="right" >认购金下限</td> 		
						<td><select name="buyFeeLower" id="buyFeeLower" class="table_select"/></td>
					</tr>
						<tr>
						<td  align="right" >费用率(%)</td> 		
						<td><select name="feeRate" id="feeRate" class="table_select"/></td>
						<td  align="right" >客户预期收益率(%)</td> 		
						<td><select name="buyFeeUpper" id="buyFeeUpper" class="table_select"/></td>  
						<td></td>
					</tr>
				</table>
				<div>
				<a href="#" onclick="addWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增</a>
					<a href="#" onclick="deleteWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-remove">删除</a>
				</div>
			</div>
		</div>
		<!-- 保险类费用比例 -->
		<div id="insuraceFeeInfo" class="easyui-panel" title="(保险类)费用比例"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table id="insuraceRateTable"></table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >费用类型</td> 		
						<td><select name="feeType" id="feeType" class="table_select"/></td>
						<td  align="right" >参数类型</td> 		
						<td><select name="buyFeeUpper" id="buyFeeUpper" class="table_select"/></td>  
						<td  align="right" >参数(数值)</td> 		
						<td><select name="buyFeeLower" id="buyFeeLower" class="table_select"/></td>
					</tr>
					<tr>
						<td  align="right" >参数数值(单位)</td> 		
						<td><select name="feeRate" id="feeRate" class="table_select"/></td>
						<td  align="right" >保费上限</td> 		
						<td><select name="buyFeeUpper" id="buyFeeUpper" class="table_select"/></td>  
						<td  align="right" >保费下限</td> 		
						<td><select name="buyFeeUpper" id="buyFeeUpper" class="table_select"/></td>  
					</tr>
					<tr>
						<td  align="right" >费用率(%)</td> 		
						<td><select name="feeRate" id="feeRate" class="table_select"/></td>
						<td  align="right" >执行开始日期</td> 		
						<td><select name="buyFeeUpper" id="buyFeeUpper" class="table_select"/></td>  
						<td  align="right" >执行结束日期</td> 		
						<td><select name="buyFeeUpper" id="buyFeeUpper" class="table_select"/></td>  
					</tr>
				</table>
				<div>
				<a href="#" onclick="addWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增</a>
					<a href="#" onclick="deleteWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-remove">删除</a>
				</div>
			</div>
		</div>
		<!-- 录入信息(公共)-->
		<div id="factorInfo" class="easyui-panel" title="(公共)录入信息"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table id="insuraceRateTable"></table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >录入项名称</td> 		
						<td><select name="factorName" id="factorName" class="table_select"/></td>
						<td  align="right" >是否必录</td> 		
						<td><select name="chooseFlag" id="chooseFlag" class="table_select"/></td>  
						<td  align="right" >录入项类型</td> 		
						<td><select name="factorType" id="factorType" class="table_select"/></td>
					</tr>
					<tr>
						<td  align="right" >下拉项要素值</td> 		
						<td><select name="feeRate" id="feeRate" class="table_select"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<div>
				<a href="#" onclick="addWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增</a>
					<a href="#" onclick="deleteWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-remove">删除</a>
				</div>
			</div>
		</div>
		<div>
				<a href="#" onclick="addProductInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-add">提交</a>
		</div>
		</div>
		
	</form>
	<!-- 附件信息 -->
	<form>
     	<div id="defFileInfo" class="easyui-panel" title="(公共)附件信息"
			iconCls="icon-tick" collapsible="true">
			<div class="top_table">
				<table id="defFileTable"></table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right" >附件类型</td> 		
						<td><select name="businessType" id="businessType" class="table_select"/></td>
						<td  align="right" >附件描述</td> 		
						<td><select name="chooseFlag" id="chooseFlag" class="table_select"/></td>  
						<td  align="right" >协议文件</td> 		
						<td><select name="factorType" id="factorType" class="table_select"/></td>
					</tr>
					<tr>
					<td  align="right" >协议文件</td> 		
						<td><input type="file" name="uploadify" id="uploadify" /></td>
					</tr>
				</table>
				<div>
				<a href="#" onclick="addWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-undo">上传</a>
					<a href="#" onclick="deleteWealthRateInfo()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-cancle">删除</a>
				</div>
			</div>
		</div>	
	</form>
</div>