<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/customer/updateCustomerInit.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>

<div id="tabdiv">
	<form id="customerBaisicInfoForm">
		<div id="smsaccordion" class="easyui-panel" title="基本信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right">客户号</td>
						<td><input name="customer_no" id="customer_no" class="table_input"/></td>
						<td class="table_text" align="right">中文姓名</td>
						<td align="left"><input name="chinese_name" id="chinese_name" class="table_input"/></td>
						<td class="table_text" align="right">英文姓</td>
						<td align="left"><input class = "table_input" name="last_name"  id = "last_name" /></td>
					</tr>
					<tr>
						<td class="table_text" align="right">英文名</td>
						<td><input name="username" id="first_name" class="table_input"></td>
						<td class="table_text" align="right">性别</td>
						<td><select name="sex" id="sex" class="table_select"/></td>
						<td class="table_text" align="right">证件类型</td>
						<td align="left"><select name="idtype" id="idtype" class="table_select"></td>
						
					</tr>
					<tr>
						<td class="table_text" align="right">证件号码</td>
						<td align="left"><input class = "table_input" name="idno"  id = "idno" /></td>
						<td class="table_text" align="right">出生日期</td>
						<td align="left"><input class="easyui-datebox table_input" name="birthday_begin" id="birthday_begin"/></td>
						<td class="table_text" align="right">年龄</td>
						<td><input name="age" id="age" class="table_input"/></td>
					</tr>
					
					<tr>
						<td class="table_text" align="right">国籍</td>
						<td align="left"><select class = "table_select" name="nativeplace"  id = "nativeplace" /></td>
						<td class="table_text" align="right">是否有驾照</td>
						<td align="left"><select class = "table_select" name="driving_licence"  id = "driving_licence" /></td>
						<td class="table_text" align="right"></td>
						<td align="left"></td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
				</div>
			</div>
		</div>
	</form>
	<form id="customerLinkInfoForm">
		<div id="smsaccordion" class="easyui-panel" title="联系信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  class="table_text" align="right" >固定电话</td>
						<td align="left"><input name="customer_no" id="customer_no" class="table_input"/></td>
						<td  class="table_text" align="right" >手机</td>
						<td align="left"><input name="chinese_name" id="chinese_name" class="table_input"/></td>
						<td  class="table_text" align="right" >E-Mail</td>
						<td align="left"><input class = "table_input" name="last_name"  id = "last_name" /></td>
					</tr>
					<tr>
						<td  class="table_text" align="right" >QQ号</td>
						<td align="left"><input name="username" id="first_name" class="table_input"></td>
						<td  class="table_text" align="right" >微信号</td>
						<td align="left"><input name="sex" id="sex" class="table_input"/></td>
						<td  align="right"></td>
						<td align="left"></td>
					</tr>
				</table>
				<table id="customerAddressTable"></table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td  align="right"  >地址类型</td>
						<td align="left" ><select name="customer_no" id="customer_no" class="table_select"/></td>
						<td  align="right"></td>
						<td align="left"></td>
						<td  align="right"></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td class="table_text" align="right"  >省</td>
						<td align="left" ><select name=""province"" id="province" class="table_select"></td>
						<td class="table_text" align="right" >市</td>
						<td align="left"><select name="city" id="city" class="table_select"/></td>
						<td class="table_text" align="right" >区</td>
						<td align="left"><select name="country" id="country" class="table_select"/></td>
					</tr>
				</table>
				
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">添加</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">修改</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">删除</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
				</div>
			</div>
		</div>
	</form>
	
	
	<form id="customerOtherInfoForm">
		<div id="smsaccordion" class="easyui-panel" title="账户信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right" >开户行</td>
						<td align="left"><input name="customer_no" id="bankname" class="table_input"/></td>
						<td class="table_text" align="right" >开户名</td>
						<td align="left"><input name="bankaccname" id="chinese_name" class="table_input"/></td>
						<td class="table_text" align="right"  >开户账户</td>
						<td align="left"><input class = "table_input" name="bankaccno"  id = "last_name" /></td>
					</tr>
				</table>
			</div>
		</div>
		
		<div id="smsaccordion" class="easyui-panel" title="个人信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right" >客户类型</td>
						<td align="left"><input name="customer_no" id="bankname" class="table_input"/></td>
						<td class="table_text" align="right" ><!-- 客户居住楼盘 --></td>
						<td align="left"><!-- <input name="bankaccname" id="chinese_name" class="table_input"/> --></td>
						<td class="table_text" align="right" >人生阶段</td>
						<td align="left"><input class = "table_input" name="bankaccno"  id = "last_name" /></td>
					</tr>
					<tr>
						<td class="table_text" align="right" >婚姻状况</td>
						<td align="left"><input name="customer_no" id="bankname" class="table_input"/></td>
						<td class="table_text" align="right" >教育水平</td>
						<td align="left"><input name="bankaccname" id="chinese_name" class="table_input"/></td>
						<td class="table_text" align="right" ></td>
						<td align="left"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="smsaccordion" class="easyui-panel" title="健康信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" class="input_table" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right" >身高(cm)</td>
						<td align="left"><input name="customer_no" id="bankname" class="table_input"/></td>
						<td class="table_text"  align="right" >体重(kg)</td>
						<td align="left"><input name="bankaccname" id="chinese_name" class="table_input"/></td>
						<td  align="right" ></td>
						<td align="left"></td>
					</tr>
				</table>
			</div>
		</div>
		
		<div id="smsaccordion" class="easyui-panel" title="兴趣爱好信息"
			 collapsible="true">
			<div class="top_table">
                <input type="checkbox" name="qx" value="1"/>养生保健
                <input type="checkbox" name="qx" value="2"/>风水
                <input type="checkbox" name="qx" value="3"/>奢侈品
                <input type="checkbox" name="qx" value="4"/>红酒
                <input type="checkbox" name="qx" value="5"/>雪茄
                <input type="checkbox" name="qx" value="6"/>美容
                <input type="checkbox" name="qx" value="7"/>旅游
                <input type="checkbox" name="qx" value="8"/>收藏
                <input type="checkbox" name="qx" value="9"/>高尔夫
                <input type="checkbox" name="qx" value="10"/>名表
                <input type="checkbox" name="qx" value="11"/>马术
                <input type="checkbox" name="qx" value="12"/>帆船
                <input type="checkbox" name="qx" value="13"/>跑车
                <input type="checkbox" name="qx" value="14"/>品茶
                <input type="checkbox" name="qx" value="15"/>慈善
                <input type="checkbox" name="qx" value="16"/>珠宝
                <input type="checkbox" name="qx" value="17"/>时尚派
                <input type="checkbox" name="qx" value="18"/>极限运动
                <input type="checkbox" name="qx" value="19"/>心里咨询
			</div>
		</div>
		
		
		<div id="smsaccordion" class="easyui-panel" title="家庭信息"
			 collapsible="true">
			<div class="top_table">

				<table id="customerFamilyTable"></table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text"  align="right">家庭成员姓名</td>
						<td align="left" ><input name="customer_no" id="customer_no" class="table_input"/></td>
						<td class="table_text"  align="right">与客户关系</td>
						<td align="left"><select name="username" id="first_name" class="table_select"></td>
						<td class="table_text"  align="right">年龄（周岁）</td>
						<td align="left"><input name="username" id="first_name" class="table_input"></td>
					</tr>
					<tr>
						<td class="table_text"  align="right" >职业</td>
						<td align="left"><input name="username" id="first_name" class="table_input"></td>
						<td class="table_text"  align="right" >年收入</td>
						<td align="left"><input name="sex" id="sex" class="table_input"/></td>
						<td class="table_text"  align="right">手机号</td>
						<td align="left"><input name="sex" id="sex" class="table_input"/></td>
					</tr>
					<tr>
						<td class="table_text"  align="right" >QQ号</td>
						<td align="left"><input name="username" id="first_name" class="table_input"></td>
						<td class="table_text"  align="right" >微信号</td>
						<td align="left"><input name="sex" id="sex" class="table_input"/></td>
						<td  align="right"></td>
						<td align="left"></td>
					</tr>
				</table>
				
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">添加</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">修改</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">删除</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
				</div>
			</div>
		</div>
		
		
		<div id="smsaccordion" class="easyui-panel" title="工作信息"
			 collapsible="true">
			<div class="top_table">
				<table width="100%" class="input_table" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right" >行业</td>
						<td align="left"><select name="customer_no" id="bankname" class="table_select"/></td>
						<td class="table_text"  align="right" >职业</td>
						<td align="left"><select name="bankaccname" id="chinese_name" class="table_select"/></td>
						<td class="table_text" align="right" >职务</td>
						<td align="left"><select name="customer_no" id="bankname" class="table_select"/></td>
						
					</tr>
					
					<tr>
						
						<td  class="table_text"  align="right" >工作单位</td>
						<td align="left"><input name="bankaccname" id="chinese_name" class="table_input"/></td>
						<td  align="right" ></td>
						<td align="left"></td>
						<td  align="right" ></td>
						<td align="left"></td>
					</tr>
				</table>
			</div>
		</div>
		
		
		<div id="smsaccordion" class="easyui-panel" title="资产收入信息"
			 collapsible="true">
			<div class="top_table">
			<table width="100%" class="input_table" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right" >年收入（万元）</td>
						<td align="left"><input name="customer_no" id="bankname" class="table_input"/></td>
						<td class="table_text"  align="right" >可投资资金（万元）</td>
						<td align="left"><input name="bankaccname" id="chinese_name" class="table_input"/></td>
						<td class="table_text" align="right" ></td>
						<td align="left"></td>				
					</tr>
				</table>
			收入来源
                <input type="checkbox" name="qx" value="1"/>养生保健
                <input type="checkbox" name="qx" value="2"/>风水
                <input type="checkbox" name="qx" value="3"/>奢侈品
                <input type="checkbox" name="qx" value="4"/>红酒
                <input type="checkbox" name="qx" value="5"/>雪茄
                <input type="checkbox" name="qx" value="6"/>美容
                <input type="checkbox" name="qx" value="7"/>旅游
                <br>
                        目前资产构成    
                <input type="checkbox" name="qx" value="8"/>收藏
                <input type="checkbox" name="qx" value="9"/>高尔夫
                <input type="checkbox" name="qx" value="10"/>名表
                <input type="checkbox" name="qx" value="11"/>马术
                <input type="checkbox" name="qx" value="12"/>帆船
                <input type="checkbox" name="qx" value="13"/>跑车
			</div>
		</div>
		
		
		
		<div id="smsaccordion" class="easyui-panel" title="房产信息"
			 collapsible="true">
			<div class="top_table">

				<table id="customerHourseTable"></table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text"  align="right">房产证编号</td>
						<td align="left" ><input name="customer_no" id="customer_no" class="table_input"/></td>
						<td class="table_text"  align="right">房产价值</td>
						<td align="left"><select name="username" id="first_name" class="table_select"></td>
						<td class="table_text"  align="right">房产状况</td>
						<td align="left"><input name="username" id="first_name" class="table_input"></td>
					</tr>
				</table>
				
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">添加</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">修改</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">删除</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
				</div>
			</div>
		</div>
		
		
		
		
		<div id="smsaccordion" class="easyui-panel" title="车辆信息"
			 collapsible="true">
			<div class="top_table">

				<table id="customerCarTable"></table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text"  align="right">车辆编号</td>
						<td align="left" ><input name="customer_no" id="customer_no" class="table_input"/></td>
						<td class="table_text"  align="right">车辆种类</td>
						<td align="left"><select name="username" id="first_name" class="table_select"></td>
						<td class="table_text"  align="right">车辆品牌</td>
						<td align="left"><input name="username" id="first_name" class="table_input"></td>
					</tr>
					
					<tr>
						<td class="table_text"  align="right">车牌号码</td>
						<td align="left" ><input name="customer_no" id="customer_no" class="table_input"/></td>
						<td class="table_text"  align="right">车辆重置价（万）</td>
						<td align="left"><select name="username" id="first_name" class="table_select"></td>
						<td  align="right"></td>
						<td align="left"></td>
					</tr>
				</table>
				
				<div>
					<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">添加</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">修改</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">删除</a>
						<a href="#" onclick="clearForm()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
				</div>
			</div>
		</div>
		
		
		<div id="smsaccordion" class="easyui-panel" title="过往投资经历以及爱好"
			 collapsible="true">
			<div class="top_table">
			<table width="100%" class="input_table" border="0" cellspacing="0" cellpadding="0" class="input_table">
					<tr>
						<td class="table_text" align="right" >投资风险偏好</td>
						<td align="left"><input name="customer_no" id="bankname" class="table_input"/></td>
						<td class="table_text"  align="right" >投资规模（万元）</td>
						<td align="left"><input name="bankaccname" id="chinese_name" class="table_input"/></td>
						<td class="table_text" align="right" ></td>
						<td align="left"></td>				
					</tr>
				</table>
			投资金融产品类型
                <input type="checkbox" name="qx" value="1"/>黄金
                <input type="checkbox" name="qx" value="2"/>股票
                <input type="checkbox" name="qx" value="3"/>债券
                <input type="checkbox" name="qx" value="4"/>信托
                <input type="checkbox" name="qx" value="5"/>艺术品
                <br>
                        投资过往经历
                  <textarea name="asdf" cols="50" rows="1"></textarea>
			</div>
		</div>
		
		<div id="smsaccordion" class="easyui-panel" title="其他信息"
			 collapsible="true">
			<div class="top_table">
                        备注
                  <textarea name="asdf" cols="50" rows="1"></textarea>
			</div>
		</div>
		<div>
			<a href="#" onclick="clearForm()" class="easyui-linkbutton e-cis_button" iconCls="icon-reload">提交</a>
		</div>
	</form>
	
</div>