<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		
		<script>
			function active (active){
				var chiose = "";
				if(active=="autocheck"){
					if(confirm("是否通过自核")){
						chiose="autocheck-yes";
					}else{
						chiose="autocheck-no";
					} 
				}
				if(active=="check"){
					if(confirm("是否通过人核")){
						chiose="check-yes";
					}else{
						chiose="check-no";
					} 
				}
				var key = $("#key").val();
				$.post("showWorkflow?active="+active+"&key="+key+"&chiose="+chiose, function(data) {
					$('a.easyui-linkbutton').linkbutton('disable');
					for(var i in data.mes){
						for(var a in $('a.easyui-linkbutton')){
							if($('a.easyui-linkbutton')[a].innerText==data.mes[i]){
								$($('a.easyui-linkbutton')[a]).linkbutton('enable');
							}
						}
					}
				});
			}
			$(function(){
				var key = $("#key").val();
				$('a.easyui-linkbutton').linkbutton('disable');
				if(key==""){
					$("#start").linkbutton('enable');
				}else{
					$.post("showWorkflowDemo?key="+key, function(data) {
						for(var i in data.mes){
							for(var a in $('a.easyui-linkbutton')){
								if($('a.easyui-linkbutton')[a].innerText==data.mes[i]){
									$($('a.easyui-linkbutton')[a]).linkbutton('enable');
								}
							}
						}
					});
				}
			})
		</script>
	</head>
	  
	<body>
  		
		<div >
  			<ul>
  				<a id="start" href="#" onclick="active('start')" class="easyui-linkbutton" style="disabled:false">开始</a>
  				<input type="text" id="key" name="key" value="${key}" class="table_input"></input>
  			</ul>
  			<ul>
  				<a href="#" onclick="active('load')" class="easyui-linkbutton" style="disabled:true">录入</a>
				<a href="#" onclick="active('charge')" class="easyui-linkbutton" style="disabled:true">财务收付费</a>
  			</ul>
  			<ul>
  				<a href="#" onclick="active('review')" class="easyui-linkbutton" style="disabled:true">复核</a>
  			</ul>
  			<ul>
  				<a href="#" onclick="active('autocheck')" class="easyui-linkbutton" style="disabled:true">自核</a>
  			</ul>
  			<ul>
  				<a href="#" onclick="active('check')" class="easyui-linkbutton" style="disabled:true">人核</a>
  			</ul>
  			<ul>
  				<a href="#" onclick="active('sign')" class="easyui-linkbutton" style="disabled:true">签单</a>
  			</ul>
		</div>
	</body>
</html>