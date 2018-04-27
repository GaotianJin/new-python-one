<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	jQuery(function($) {
		$('#bankId').combobox({
			url : '../common/queryBankList',
			valueField : 'id',
			textField : 'name'
		});
		//初始输入框
		initBankInfo();
	});
	
	//初始化银行信息
	function initBankInfo() {
		var id = $('#posID').val();
		$.post('initBankInfoUrl?id=' + id, function(data) {
			$('#bankId').combobox("setValue",data.bankid);
			$('#bankId').combobox("setText",data.bankname);

			$('#accname').val(data.accname);
			$('#accno1').val(data.accno);
			$('#accno2').val(data.accno);
		});
	}
</script>


<div id="divbankinfo" class="easyui-panel" fit="true" title="银行账号信息"
	iconCls="icon-ok" collapsible="true">
	<div class="top_table">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="table_text" align="right">开户行：</td>
				<td align="left"><select
					class="easyui-validatebox table_select" name="bankId" id="bankId"
					required="true" validType="length[0,20]">
				</select></td>
			</tr>
			<tr>
				<td class="table_text2">账号所有人：</td>
				<td><input id="accname" name="accname"
					class="table_input easyui-validatebox" validType="length[0,20]"
					required="true"></td>
			</tr>
			<tr>
				<td class="table_text2">账号：</td>
				<td><input id="accno1" name="accno1"
					class="table_input easyui-validatebox" validType="length[0,20]"
					required="true"></td>
			</tr>
			<tr>
				<td class="table_text2">请再输入账号：</td>
				<td><input id="accno2" name="accno2"
					class="table_input easyui-validatebox" validType="equalTo['#accno1']"
					required="true" ></td>
			</tr>
		</table>
	</div>
</div>

