<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/icbcaxa/pos/posRemarkAddInit.js"></script>

<div id="tabdiv">
	<form id="addRemarkForm">
		<div id="smsaccordion" class="easyui-panel" fit="true" title="新增备注"
			iconCls="icon-ok" collapsible="true">
			<div class="top_table">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">

					<tr>
						<td class="table_text" align="right">请录入备注：</td>
						<td align="left"><textarea rows="5" cols="120" name="remark"
								id="remark" validType="length[0,500]" required="true"></textarea>
						</td>
					</tr>
				</table>
				<div>
					<a href="#" onclick="addRemark()"
						class="easyui-linkbutton e-cis_button" iconCls="icon-add">新增</a>
				</div>
			</div>
		</div>
		<div>
			<input type="hidden" name="posID" id="posID" value="${posID}" />
		</div>
	</form>
</div>
