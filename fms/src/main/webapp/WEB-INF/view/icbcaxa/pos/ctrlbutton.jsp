<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	jQuery(function($) {
		var navH = $(".buttonDiv").offset().top;
		window.onscroll = function() {
			var scroH = $(this).scrollTop();
			if (scroH >= navH) {
				$(".buttonDiv").css({
					"position" : "fixed",
					"top" : 0
				});
			}
		}
		var type = $('#type').val();
		//申请
		if (type == 'apply') {
			$('#checkPassSpan').css('display', 'none');
			$('#checkPassFailedSpan').css('display', 'none');
		}
		//复核
		if (type == 'check') {
			$('#noteSpan').css('display', 'none');
			$('#saveSpan').css('display', 'none');
			$('#submitSpan').css('display', 'none');
			var postype = $('#postype').val();
			if(postype == 'RC' || postype == 'PT' || postype == 'PC'|| postype == 'DA' ){
				$('#computeProductDelSpan').css('display', 'none');
			}
		}
		var flag = '${flag}';
		if(flag == "noOper" || flag== "caseOver" ){
			$('#buttonDiv').css('display', 'none');
			$('#pos_main_div').css('top','10px');
		}
	});
	//保全撤销
	function delTask() {
		var id = $('#posID').val();
		sAlert('正在提交数据，请您耐心等候...');
		$.post('delTaskUrl?id=' + id, function(data) {
			if (data.msg == 'success') {
				cAlert();
				$.messager.alert('提示', '保全撤销成功', 'info',function(){
					parent.addtab('我的任务','nb/listTaskUrl');	
					parent.deletetab('保全处理');
				});

				}
			else {
				cAlert();
				$.messager.alert('提示', data.msg, 'info');
			}});
		}


	//备注
	function remark() {
		parent.addtab('保全备注', 'pos/posRemarkUrl?posID=' + $('#posID').val());
	}

	//复核通过
	function checkPass() {
		var id = $('#posID').val();
		sAlert('正在提交数据，请您耐心等候...');
		$.post('posCheckPassUrl?id=' + id, function(data) {
			if (data.msg == 'success') {
				cAlert();
				$.messager.alert('提示', '操作成功，复核通过。', 'info',function(){
					parent.addtab('我的任务','nb/listTaskUrl');
					parent.deletetab('保全复核');
				});
		} else {
				cAlert();
				$.messager.alert('提示', data.msg, 'info');
			}
		});
	}
	
	//照会
	function note(){
		var dlist = [];	
		dlist.push({
					"policyNo" :$('#policyno').val() ,
					"businessNo" : $('#posID').val(),
					"businessType" : "01"
					
	});
		parent.addtab('照会管理','note/listNote?list='+$.toJSON(dlist));	
	}

	//复核不通过
	function checkPassFailed() {
		var id = $('#posID').val();
		sAlert('正在提交数据，请您耐心等候...');
		$.post('posCheckPassFailedUrl?id=' + id, function(data) {
			if (data.msg == 'success') {
				cAlert();
				$.messager.alert('提示', '操作成功，复核不通过。', 'info',function(){
					parent.addtab('我的任务','nb/listTaskUrl');	
					parent.deletetab('保全复核');
				});
			} else {
				cAlert();
				$.messager.alert('提示', data.msg, 'info');
			}
		});
	}
	
	function hiddenButton(){}

	function policyQuery(){
		var policyNo = $.trim($('#policyno').val());
		if(policyNo!=null && policyNo!="" &&policyNo!="null"){
			$.post('../nb/policyQuery?policyNo='+policyNo,function(data){
				if(data.msg =='successed'){
					parent.addtab('保单查询','nb/policyDetailUrl?queryNo='+policyNo+'&queryFlag=C');
				} else {
					$.messager.alert('提示', data.msg, 'info');

				}
			});	
		} else{
			parent.addtab('保单查询','nb/policyQueryUrl');
		}
	}

	
</script>
<div id="buttonDiv" class='buttonDiv' style="z-index: 1; position:fixed;  height: 50px;  background-color: #E0ECF8;  width: 100%;height: 50px;top: 0px;">
	<span id="policyQuerySpan">
		<a href="#" onclick="policyQuery()" class="easyui-linkbutton e-cis_button"  >保单查询</a>
	</span> 
	<span id="noteSpan">
		<a href="#" onclick="note()" class="easyui-linkbutton e-cis_button"  >照会</a>
	</span> 
	<span id="remarkSpan">
		<a href="#" onclick="remark()" class="easyui-linkbutton e-cis_button" >备注</a>
	</span> 
	<span id="saveSpan">
		<a href="#" onclick="save()" class="easyui-linkbutton e-cis_button" >保存</a>
	</span> 
	<span id="submitSpan">
		<a href="#" onclick="submitTask()" class="easyui-linkbutton e-cis_button" >提交</a> 
	</span> 
	<span id="delTaskSpan">
		<a href="#" onclick="delTask()" class="easyui-linkbutton e-cis_button" >任务撤销</a>
	</span>
	<span id="checkPassSpan">
		<a href="#" onclick="checkPass()" class="easyui-linkbutton e-cis_button"  >复核通过</a>
	</span> 
	<span id="checkPassFailedSpan">
		<a href="#" onclick="checkPassFailed()" class="easyui-linkbutton e-cis_button"  >复核不通过</a>
	</span>
</div>