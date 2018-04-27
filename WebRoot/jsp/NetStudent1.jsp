<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'NetStudent.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="/EighthCRMItem/js/myjs/easyUIvalidate.js"></script>
<style type="text/css">
.red {
	color: red;
}
</style>
<script type="text/javascript">
	var url;
	function logAction(value, row, index) {
       var data = $("#followList").datagrid('getData');
       var row = data.rows[index];
       if(row.content==null){
       		row.content="";
       }
       return '<a  class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconcls="icon-search" plain="true" onclick="getDetail(' + "'" +row.content+ "'" + ')"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">查看</span><span class="l-btn-icon icon-search">&nbsp;</span></span></a>';
   }
   function getDetail(obj){
       $.messager.alert("内容信息",window.decodeURI(obj),"info");
   }
	function formatAction(value, row, index) {
		var da = $("#stuInfo").datagrid('getData');
		var r = da.rows[index];
		return '<a class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconcls="icon-search" plain="true" onclick="showStu('+ "'"+ index+ "'"+ ')"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">查看</span><span class="l-btn-icon icon-search">&nbsp;</span></span></a>'+
				'<a class="easyui-linkbutton l-btn l-btn-small l-btn-plain" iconcls="icon-edit" plain="true" onclick="addDynamic('+ "'"+ index+ "'"+ ')"><span class="l-btn-left l-btn-icon-left"><span class="l-btn-text">添加动态</span><span class="l-btn-icon icon-edit">&nbsp;</span></span></a>';
	}
	
	function resultSearchStu(){
		$('#s_name').textbox("clear");
		$('#s_phone').textbox("clear");
		$('#isPay').combobox("setText","").combobox("setValue", "");
		$('#isValid').combobox("setText","").combobox("setValue", "");
		$('#isReturnVisit').combobox("setText","").combobox("setValue", "");
		$('#dateA').combo("setValue", "").combo("setText", "");
		$('#dateB').combo("setValue", "").combo("setText", "");
		$('#s_qq').textbox("clear");
		$('#s_asker').combobox("setText","").combobox("setValue", "");
		$("#stuInfo").datagrid('load', {});
	}
	/*
	 * 查询学生
	 */
	function searchStu() {
		//查询条件
		$("#stuInfo").datagrid('load', {
			sname : $('#s_name').val(),
			sphone : $('#s_phone').val(),
			isPay : $('#isPay').combobox("getValue"),
			isValid : $('#isValid').combobox("getValue"),
			isReturnVisit : $('#isReturnVisit').combobox("getValue"),
			dateA : $('#dateA').datebox("getValue"),
			dateB : $('#dateB').datebox("getValue"),
			sqq : $('#s_qq').val(),
			sasker:$('#s_asker').combobox("getText"),
		});
	}
	
	//日期格式转换
			function  formatterDate(value){
				if(value==null){
					return null;
				}else{
					var seperator1 = "-";
    				var seperator2 = ":";
					var date = new Date(value);
					var y=date.getFullYear();
					var m=date.getMonth()+1;
					var d=date.getDate();
					var h=date.getHours();
					var M=date.getMinutes();
					var s=date.getSeconds();
					return y+seperator1+fmt(m)+seperator1+fmt(d)+" "+fmt(h)+seperator2+fmt(M)+seperator2+fmt(s);
					}
				}
			function fmt(number){
				if (number >= 0 && number <= 9) {
	        	return "0" + number;
	    		}else{
	    		return number;
    			}
			}

	function showSetColumn() {
		$("#disColumn").find("input[name=check]").change(function(res) {
			if (this.checked == true) {
				$("#stuInfo").datagrid("showColumn", this.value);
			} else {
				$("#stuInfo").datagrid("hideColumn", this.value);
			}
		});
	}
	//全选
	function allChecked() {
		$("#disColumn").find("input").prop("checked",
				$("#allChecked").prop("checked"));
		var len = $("#disColumn").find("input[name=check]").length;
		for ( var i = 0; i < len; i++) {
			$("#disColumn").find("input[name=check]").eq(i).change();//绑定change事件
		}
	}
	function showSetColumn(){
		$("#disColumn").find("input[name=check]").change(function(res) {
			if (this.checked == true) {
				$("#stuInfo").datagrid("showColumn", this.value);
			} else {
				$("#stuInfo").datagrid("hideColumn", this.value);
			}
		});
	}
  function setShowColumn(){
	  $('#showColumn').window("open");
	  showSetColumn();
  }

	//添加学生
	function openAddStu() {
		$("#addStudentForm").form("clear");
		$("#askerLR").hide();
		$("#createStu").window("open");
		url = ("/EighthCRMItem/addStudent.eighth");
	}
	//添加，编辑的保存按钮
	function saveStudent() {
		if($('#addStudentForm').form("validate")) {
			$.post(url,$('#addStudentForm').serialize(),function(result){
				if(result.success){
					$.messager.alert("系统提示",result.message);
					$('#createStu').window('close');
					$("#stuInfo").datagrid("reload");
				}else{
					$.messager.alert("系统提示",result.message);
					return;
				}
			});
		}
	}
	//查看学生信息
	function showStu(index){
		$("#stuInfo").datagrid("reload");
		var rows=$('#stuInfo').datagrid("getRows");
			var data=rows[index];
			data.firstVisitTime=formatterDate(data.firstVisitTime);
			data.homeTime=formatterDate(data.homeTime);
			data.preMoneyTime=formatterDate(data.preMoneyTime);
			data.payTime=formatterDate(data.payTime);
			data.inClassTime=formatterDate(data.inClassTime);
			$("#LookStu").window("open");
			$('#LookStudentForm').form('load',data);
	}
	//编辑
	function editStu(index){
		$("#stuInfo").datagrid("reload");
		var rows=$('#stuInfo').datagrid("getRows");
			var data=rows[index];
			data.firstVisitTime=formatterDate(data.firstVisitTime);
			data.homeTime=formatterDate(data.homeTime);
			data.preMoneyTime=formatterDate(data.preMoneyTime);
			data.payTime=formatterDate(data.payTime);
			data.inClassTime=formatterDate(data.inClassTime);
			$("#askerLR").show();
			$("#createStu").window("open").window("setTitle","编辑学生信息");
			$('#addStudentForm').form('load',data);
			url = ("/EighthCRMItem/modStudent.eighth");
	}
	//删除
	function deleteStu(stuId){
		$.messager.confirm("系统提示","确定删除？",function(res){
			if(res){
				$.post("/EighthCRMItem/delStudent.eighth",{id:stuId},function(res){
					if(res){
						$.messager.alert("系统提示", "删除成功");
						$("#stuInfo").datagrid("reload");
					}else{
						$.messager.alert("系统提示", "删除失败");
					}
				});
			}});
		}
	//查看日志
	function showLog(stuId){
		$('#followList').datagrid({
			url:"/EighthCRMItem/showFollow.eighth",
			queryParams: {
				stuId:stuId
			}
		});
		$("#searchFollowWindow").window("open");
	}
	//添加动态
	function addDynamic(index){
		var rows=$('#stuInfo').datagrid("getRows");
		var data=rows[index];
		    $("#addDynamicForm").form('clear');//先清空表单数据
    	    $("#d_dyaskerId").val(data.askerId);
    	    $("#d_dyaskerName").val(data.askerName);
    	    $("#d_dystuId").val(data.stuId);
    	    $("#d_dystuName").val(data.stuName);
    	    $("#addDynamic").window('open');    
    	    url = ("/EighthCRMItem/addDynamic.eighth");
	}
	 //保存动态信息
    function saveDynamic(){
      $('#addDynamicForm').form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.errorMsg) {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				} else {
					$.messager.alert("系统提示", "保存成功");
					$('#addDynamic').window('close');
					$("#stuInfo").datagrid("reload");
				}
			}
		});
    }
	  //打开新建网络跟踪窗口
    function createFollow(stuId,stuName){
    	    $("#addFollowForm").form('clear');//先清空表单数据
    	    $("#f_stuId").val(stuId);
    	    $("#f_stuName").val(stuName);
    	    $("#addFollow").window('open');    
    	    url = ("/EighthCRMItem/addFollow.eighth");        
    }
    //保存跟踪信息
    function saveNetFollow(){
      $('#addFollowForm').form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.errorMsg) {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				} else {
					$.messager.alert("系统提示", "保存成功");
					$('#addFollow').window('close');
					$("#stuInfo").datagrid("reload");
				}
			}
		});
    }
    function isMobil(s){
		var patrn=/^1[0-9]{10}$/;
		if(!patrn.exec(s)){
			$('#phone').val("");
			$.messager.alert("系统提示", "手机号码格式错误！");
		}
	}
	//批量操作
	function getStuId(){
		var selectedRows=$("#stuInfo").datagrid('getSelections');
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要操作的数据！");
				return;
			}else{
				$('#changeAsker').window('open');
			}
	}
	
	function savePiLiang(){
		var selectedRows=$("#stuInfo").datagrid('getSelections');
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].stuId);
		}
		var ids=strIds.join(",");
		var askerid=$('#changeName').combobox("getValue");
		var askerName=$('#changeName').combobox("getText");
		$.post("/EighthCRMItem/modStudentAskers.eighth",{ids:ids,askerid:askerid,askerName:askerName},function(res){
			if(res.success){
				$.messager.alert("系统提示", res.message);
				$('#changeAsker').window('close');
				$("#stuInfo").datagrid("reload");
			}else{
				$.messager.alert("系统提示", res.message);
				return;
			}
		});
	}
</script>

<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules,{
		equaldDate:{
			validator:function (value,param){
			 var start=$(param[0]).datetimebox('getValue');
			 return value>start;
			},
			message:'下次跟踪日期应大于跟踪日期'
		}
	})
</script>
</head>

<body>

	<table id="cxtj" style="font-size: 12px;">
		<tbody>
			<tr>
				<td>姓名关键字:</td>
				<td><input id="s_name" name="s_name" class="easyui-textbox"
					style="border: 1px solid #ccc" /></td>
				<td>电话：</td>
				<td><input
					class="easyui-textbox easyui-validatebox validatebox-text"
					style="border: 1px solid #ccc" id="s_phone" name="s_phone" /></td>
			</tr>
			<tr>
				<td>咨询师:</td>
				<td><input id="s_asker" class="easyui-combobox"
					data-options="valueField:'askerId',textField:'askerName',panelHeight:'auto',url:'/EighthCRMItem/showAllAsker.eighth'" />
				</td>

				<td>是否缴费:</td>
				<td><input id="isPay" name="isPay" class="easyui-combobox"
					data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'','text':'全部','selected':true},{'id':'是','text':'已缴费'},{'id':'否','text':'未缴费'}]" />
				</td>

				<td>是否有效:</td>
				<td><input id="isValid" name="isValid" class="easyui-combobox"
					data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'','text':'全部','selected':true},{'id':'是','text':'是'},{'id':'否','text':'否'},{'id':'待定','text':'待定'}]" />
				</td>
			</tr>
			<tr>
				<td>回访情况:</td>
				<td><input id="isReturnVisit" name="isReturnVisit"
					class="easyui-combobox" style="width:50%;"
					data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'','text':'全部','selected':true},{'id':'是','text':'已回访'},{'id':'否','text':'未回访'}]" />
				</td>
				<td>QQ:</td>
				<td><input id="s_qq" class="easyui-textbox" name="s_qq"
					style="border:1px solid #ccc" />
				</td>
			</tr>
			<tr>
				<td>创建时间：</td>
				<td><input class="easyui-datebox" name="dateA" id="dateA" />
				</td>
				<td style="text-align:center">-</td>
				<td colspan="4"><input class="easyui-datebox" id="dateB"
					name="dateB" />&nbsp;&nbsp; <a class="easyui-linkbutton"
					iconCls="icon-search" onclick="searchStu()">查询</a> <a
					class="easyui-linkbutton" iconCls="icon-search"
					onclick="resultSearchStu()">重置</a></td>
			</tr>
		</tbody>
	</table>
	<table id="stuInfo" title="学生信息" class="easyui-datagrid"
		style="width:auto;height:auto;" rownumbers="true" pagination="true"
		pageSize="20"
		url="/EighthCRMItem/showAllStudentByAskerIdInNetStu.eighth"
		toolbar="#tb">
		<thead>
			<tr>
				<th field="stuId" checkbox="true" width="10px">编号</th>
				<th field="stuName" align="center" width="80px">学员姓名</th>
				<th field="askerName" align="center" width="80px">咨询师姓名</th>
				<th field="createUser" align="center" width="80px">录入人姓名</th>
				<th field="ziXunName" align="center" width="80px">姓名(咨询)</th>
				<th field="stuSex" align="center" width="40px">性别</th>
				<th field="stuAge" align="center" width="40px">年龄</th>
				<th field="stuPhone" align="center" width="100px">学员电话</th>
				<th field="stuStatus" align="center" width="40px">学历</th>
				<th field="perState" align="center" width="80px">个人状态</th>
				<th field="createTime" align="center" formatter="formatterDate"
					width="130px">创建时间</th>
				<th field="msgSource" hidden="true" align="center" width="100px">来源渠道</th>
				<th field="sourceUrl" hidden="true" align="center" width="100px">来源网址</th>
				<th field="sourceKeyWord" hidden="true" align="center" width="100px">来源关键词</th>
				<th field="stuAddress" align="center" width="80px">所在区域</th>
				<th field="stuQQ" align="center" width="100px">学员QQ</th>
				<th field="stuWeiXin" align="center" hidden="true" width="100px">微信</th>
				<th field="fromPart" align="center" width="80px">来源部门</th>
				<th field="isBaoBei" align="center" hidden="true" width="60px">是否报备</th>
				<th field="learnForward" align="center" hidden="true" width="80px">课程方向</th>
				<th field="isValid" align="center" width="60px">是否有效</th>
				<th field="lostValid" align="center" hidden="true" width="120px">无效原因</th>
				<th field="record" align="center" hidden="true" width="40px">打分</th>
				<th field="isReturnVist" align="center" width="60px">是否回访</th>
				<th field="firstVisitTime" align="center" width="130px"
					formatter="formatterDate">首次回访时间</th>
				<th field="isHome" align="center" width="60px">是否上门</th>
				<th field="homeTime" align="center" width="130px"
					formatter="formatterDate">上门时间</th>
				<th field="preMoney" align="center" width="60px">订金金额</th>
				<th field="preMoneyTime" align="center" width="130px"
					formatter="formatterDate">订金时间</th>
				<th field="isPay" align="center" width="60px">是否缴费</th>
				<th field="payTime" align="center" width="130px"
					formatter="formatterDate">缴费时间</th>
				<th field="money" align="center" width="60px">金额</th>
				<th field="isReturnMoney" align="center" hidden="true" width="60px">是否退费</th>
				<th field="returnMoneyReason" align="center" hidden="true"
					width="120px">退费原因</th>
				<th field="isInClass" align="center" hidden="true" width="60px">是否进班</th>
				<th field="inClassTime" align="center" hidden="true" width="130px"
					formatter="formatterDate">进班时间</th>
				<th field="inClassContent" align="center" hidden="true"
					width="120px">进班备注</th>
				<th field="action" align="center" formatter="formatAction"
					width="320px">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:3px">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="openAddStu()">添加</a> <a class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="setShowColumn()">设置</a>
	</div>


	<div id="showColumn" class="easyui-window" title="要显示的列"
		data-options="iconCls:'icon-add',minimizable:false,maximizable:false,closed:true,modal:true"
		style="padding:10px;min-width:500px;top: 100px;">
		<fieldset>
			<legend>要显示的列</legend>
			<table id="disColumn">
				<tr>
					<td colspan="4"><input type="checkbox" name="allChecked"
						id="allChecked" onchange="allChecked()" />全选</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="check" value="createTime"
						checked="checked" />创建时间</td>
					<td><input type="checkbox" name="check" value="stuName"
						checked="checked" />学员姓名</td>
					<td><input type="checkbox" name="check" value="askerName"
						checked="checked" />咨询师姓名</td>
					<td><input type="checkbox" name="check" value="createUser"
						checked="checked" />录入人姓名</td>
					<td><input type="checkbox" name="check" value="stuPhone"
						checked="checked" />学员电话</td>
					<td><input type="checkbox" name="check" value="stuSex"
						checked="checked" />性别</td>
					<td><input type="checkbox" name="check" value="stuAge"
						checked="checked" />年龄</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="check" value="stuStatus"
						checked="checked" />学历</td>
					<td><input type="checkbox" name="check" value="perState"
						checked="checked" />个人状态</td>
					<td><input type="checkbox" name="check" value="msgSource"
						checked="checked" />来源渠道</td>
					<td><input type="checkbox" name="check" value="sourceUrl" />来源网址</td>
					<td><input type="checkbox" name="check" value="sourceKeyWord" />来源关键词</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="check" value="ziXunName"
						checked="checked" />姓名（咨询）</td>
					<td><input type="checkbox" name="check" value="stuAddress"
						checked="checked" />所在区域</td>
					<td><input type="checkbox" name="check" value="stuWeiXin" />微信</td>
					<td><input type="checkbox" name="check" value="stuQQ"
						checked="checked" />学员QQ</td>
					<td><input type="checkbox" name="check" value="fromPart"
						checked="checked" />来源部门</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="check" value="isBaoBei" />是否报备</td>
					<td><input type="checkbox" name="check" value="learnForward" />课程方向</td>
					<td><input type="checkbox" name="check" value="isValid"
						checked="checked" />是否有效</td>
					<td><input type="checkbox" name="check" value="record" />打分</td>
					<td><input type="checkbox" name="check" value="isReturnVist"
						checked="checked" />是否回访</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="check" value="firstVisitTime"
						checked="checked" />首次回访时间</td>
					<td><input type="checkbox" name="check" value="isHome"
						checked="checked" />是否上门</td>
					<td><input type="checkbox" name="check" value="homeTime"
						checked="checked" />上门时间</td>
					<td><input type="checkbox" name="check" value="lostValid" />无效原因</td>
					<td><input type="checkbox" name="check" value="isPay"
						checked="checked" />是否缴费</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="check" value="payTime"
						checked="checked" />缴费时间</td>
					<td><input type="checkbox" name="check" value="money"
						checked="checked" />金额</td>
					<td><input type="checkbox" name="check" value="isReturnMoney" />是否退费</td>
					<td><input type="checkbox" name="check" value="isInClass" />是否进班</td>
					<td><input type="checkbox" name="check" value="inClassTime" />进班时间</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="check" value="inClassContent" />进班备注</td>
					<td><input type="checkbox" name="check"
						value="returnMoneyReason" />退费原因</td>
					<td><input type="checkbox" name="check" value="preMoney"
						checked="checked" />订金金额</td>
					<td><input type="checkbox" name="check" value="preMoneyTime"
						checked="checked" />订金时间</td>
				</tr>
			</table>
		</fieldset>
	</div>


	<div id="createStu" class="easyui-window" title="新建学员"
		data-options="iconCls:'icon-add',minimizable:false,maximizable:false,closed:true,doSize:false,modal:true"
		style="padding:10px;top: 50px;">
		<form id="addStudentForm" method="post">
			<table border="1" bordercolor="#a0c6e5"
				style="border-collapse:collapse;margin-top:10px;">
				<tr hidden="true">
					<td>ID</td>
					<td><input id="stuId" name="stuId" class="easyui-validatebox" />
					</td>
				</tr>
				<tr>
					<td rowspan="6"
						style="width:20px;padding:7px;font-size:13px;background-color:aliceblue">在线录入</td>
					<td style="min-width:100px">姓名<span class="red">*</span></td>
					<td><input id="name" name="stuName" class="easyui-validatebox"
						data-options="required:true,validType:'chinesename'" /></td>
					<td style="min-width:70px">性别</td>
					<td><input id="sex" name="stuSex" class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'男','text':'男'},{'id':'女','text':'女'}]" />
					</td>
					<td style="min-width:70px">年龄</td>
					<td><input id="age" name="stuAge" class="easyui-validatebox"
						data-options="validType:'age'" /></td>
				</tr>
				<tr>
					<td>电话<span class="red">*</span></td>
					<td><input id="phone" name="stuPhone"
						class="easyui-validatebox" 
						data-options="required:true,validType:'mobile'" /></td>
					<td>学历</td>
					<td><input id="stuStatus" name="stuStatus"
						class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                        data:[{'id':'未知','text':'未知','selected':true},{'id':'大专','text':'大专'},{'id':'高中','text':'高中'},{'id':'中专','text':'中专'},{'id':'初中','text':'初中'},{'id':'本科','text':'本科'},{'id':'其它','text':'其它'}]" />
					</td>
					<td>状态</td>
					<td><input id="perState" name="perState"
						class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                        data:[{'id':'未知','text':'未知','selected':true},{'id':'待业','text':'待业'},{'id':'在职','text':'在职'},{'id':'在读','text':'在读'}]" />
					</td>
				</tr>
				<tr>
					<td>来源渠道<span class="red">*</span></td>
					<td><input id="msgSource" name="msgSource"
						class="easyui-combobox"
						data-options="editable:true,valueField:'id',textField:'text',panelHeight:'auto',required:true,
	                        data:[{'id':'未知','text':'未知','selected':true},{'id':'百度','text':'百度'},{'id':'百度移动端','text':'百度移动端'},{'id':'360','text':'360'},{'id':'360移动端','text':'360移动端'},{'id':'搜狗','text':'搜狗'},
	                              {'id':'搜狗移动端','text':'搜狗移动端'},{'id':'UC移动端','text':'UC移动端'},{'id':'直接输入','text':'直接输入'},{'id':'自然流量','text':'自然流量'},{'id':'直电','text':'直电'},{'id':'微信','text':'微信'},{'id':'QQ','text':'QQ'}]" />
					</td>
					<td>来源网站<span class="red">*</span></td>
					<td><input id="sourceUrl" name="sourceUrl"
						class="easyui-combobox"
						data-options="editable:true,valueField:'id',textField:'text',panelHeight:'auto',required:true,
	                            data:[{'id':'其它','text':'其它'},{'id':'职英B站','text':'职英B站'},{'id':'高考站','text':'高考站'},{'id':'职英A站','text':'职英A站','selected':true}]" />
					</td>
					<td>来源关键词<span class="red">*</span></td>
					<td><input id="sourceKeyWord" name="sourceKeyWord"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>所在区域</td>
					<td><input id="address" name="stuAddress"
						class="easyui-combobox"
						data-options="editable:true,valueField:'id',textField:'text',panelHeight:'auto',
	                      data:[{'id':'未知','text':'未知','selected':true},
		                     {'id':'郑州市','text':'郑州'}, {'id':'开封市','text':'开封'},{'id':'洛阳市','text':'洛阳'},
		                     {'id':'南阳市','text':'南阳'},{'id':'漯河市','text':'漯河'},{'id':'三门峡市','text':'三门峡'},
		                     {'id':'平顶山市','text':'平顶山'},{'id':'周口市','text':'周口'},{'id':'驻马店市','text':'驻马店'},
		                     {'id':'新乡市','text':'新乡'},{'id':'鹤壁市','text':'鹤壁'},{'id':'焦作市','text':'焦作'},
		                     {'id':'濮阳市','text':'濮阳'},{'id':'安阳市','text':'安阳'},{'id':'商丘市','text':'商丘'},
		                     {'id':'信阳市','text':'信阳'},{'id':'济源市','text':'济源'},{'id':'许昌市','text':'许昌'}]" />
					</td>
					<td>学员关注</td>
					<td><input id="stuConcern" name="stuConcern"
						class="easyui-combobox"
						data-options="editable:true,valueField:'id',textField:'text',panelHeight:'auto',
                            data:[{'id':'课程','text':'课程','selected':true},{'id':'学费','text':'学费'},{'id':'学时','text':'学时'},{'id':'学历','text':'学历'},
                                  {'id':'师资','text':'师资'},{'id':'就业','text':'就业'},{'id':'环境','text':'环境'},{'id':'其他','text':'其他'}]" />
					</td>
					<td>来源部门</td>
					<td><input id="fromPart" name="FromPart"
						class="easyui-combobox"
						data-options="editable:true,valueField:'id',textField:'text',panelHeight:'auto',
			                      data:[{'id':'网络','text':'网络','selected':true},
			                            {'id':'市场','text':'市场'},
			                            {'id':'教质','text':'教质'},
			                            {'id':'学术','text':'学术'},
			                            {'id':'就业','text':'就业'}]" />
					</td>
				</tr>
				<tr>
					<td>学员QQ</td>
					<td><input id="qq" name="stuQQ" class="easyui-validatebox"
						data-options="validType:'qq'" /></td>
					<td>微信号</td>
					<td><input id="weixin" name="stuWeiXin"
						class="easyui-validatebox" data-options="validType:'weiXin'" /></td>
					<td>是否报备</td>
					<td><input id="isBaoBei" name="IsBaoBei"
						class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
	                        data:[{'id':'否','text':'否','selected':true},{'id':'是','text':'是'}]" />
					</td>
				</tr>
				<tr style="height:100px">
					<td>在线备注</td>
					<td colspan="5"><input id="content" name="content"
						class="easyui-textbox" data-options="multiline:true"
						style="border:none;width:97%;height:100px;resize:none" /></td>
				</tr>
			</table>
			<table border="1" bordercolor="#a0c6e5"
				style="border-collapse:collapse;" id="askerLR">
				<tr>
					<td rowspan="9"
						style="width:20px;padding:7px;font-size:13px;background-color:aliceblue">咨询师录入</td>
					<td style="min-width:100px">姓名(咨询)</td>
					<td><input class="easyui-validatebox" id="ziXunName"
						type="text" name="ziXunName" /></td>
					<td style="min-width:70px">课程方向</td>
					<td><input id="learnForward" name="learnForward"
						class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'请选择','text':'请选择','selected':true},{'id':'软件开发','text':'软件开发'},{'id':'软件设计','text':'软件设计'},{'id':'网络营销','text':'网络营销'}]" />
					</td>
					<td style="min-width:88px">打分</td>
					<td><input id="record" name="record" class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'请选择','text':'请选择','selected':true},{'id':'A','text':'A、近期可报名'},{'id':'B','text':'B、一个月内可报名'},{'id':'C','text':'C、长期跟踪'},{'id':'D','text':'D、无效'}]" />
					</td>
				</tr>
				<tr>
					<td>是否有效</td>
					<td><input id="isValid" name="isValid" class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'待定','text':'待定','selected':true},{'id':'是','text':'是'},{'id':'否','text':'否'}]" />
					</td>
					<td>无效原因</td>
					<td colspan="3"><input class="easyui-validatebox"
						name="lostValid" id="lostValid" /></td>
				</tr>
				<tr>
					<td>是否回访</td>
					<td><input id="isReturnVist" name="isReturnVist"
						class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'否','text':'否','selected':true},{'id':'是','text':'是'}]" />
					</td>
					<td>首访时间</td>
					<td colspan="3"><input class="easyui-datetimebox" type="text"
						name="firstVisitTime" id="firstVisitTime" /></td>
				</tr>
				<tr>
					<td>是否上门</td>
					<td><input id="isHome" name="isHome" class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'否','text':'否','selected':true},{'id':'是','text':'是'}]" />
					</td>
					<td>上门时间</td>
					<td colspan="3"><input class="easyui-datetimebox" type="text"
						name="homeTime" id="homeTime" /></td>
				</tr>
				<tr>
					<td>定金数额</td>
					<td><input class="easyui-numberbox" type="text" id="preMoney"
						name="preMoney" /></td>
					<td>定金时间</td>
					<td colspan="3"><input class="easyui-datetimebox" type="text"
						id="preMoneyTime" name="preMoneyTime" /></td>
				</tr>
				<tr>
					<td>是否缴费</td>
					<td><input id="isPay" name="isPay" class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'否','text':'否','selected':true},{'id':'是','text':'是'}]" />
					</td>
					<td>缴费时间</td>
					<td><input class="easyui-datetimebox" type="text" id="payTime"
						name="payTime" /></td>
					<td>缴费金额</td>
					<td><input class="easyui-numberbox" type="text" id="money"
						name="money" /></td>
				</tr>
				<tr>
					<td>是否退费</td>
					<td><input id="isReturnMoney" name="isReturnMoney"
						class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'否','text':'否','selected':true},{'id':'是','text':'是'}]" />
					</td>
					<td>退费原因</td>
					<td colspan="3"><input class="easyui-validatebox"
						id="returnMoneyReason" name="returnMoneyReason" /></td>
				</tr>
				<tr>
					<td>是否进班</td>
					<td><input id="isInClass" name="isInClass"
						class="easyui-combobox"
						data-options="editable:false,valueField:'id',textField:'text',panelHeight:'auto',
                    data:[{'id':'否','text':'否','selected':true},{'id':'是','text':'是'}]" />
					</td>
					<td>进班时间</td>
					<td><input class="easyui-datetimebox" type="text"
						id="inClassTime" name="inClassTime" /></td>
					<td>进班备注</td>
					<td><input class="easyui-validatebox" id="inClassContent"
						name="inClassContent" /></td>
				</tr>
				<tr>
					<td>咨询师备注</td>
					<td colspan="5"><input id="askerContent" name="askerContent"
						class="easyui-textbox" data-options="multiline:true"
						style="border:none;width:97%;height:100px;resize:none" /></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center;">
			<a onclick="saveStudent()" class="easyui-linkbutton"
				iconCls="icon-ok">保存</a> <a
				onclick="$('#createStu').window('close');" class="easyui-linkbutton"
				iconCls="icon-cancel">关闭</a>
		</div>
	</div>



	<div id="LookStu" class="easyui-window" title="学员信息"
		data-options="iconCls:'icon-add',minimizable:false,maximizable:false,closed:true,maximized:true,doSize:false,modal:true"
		style="padding-top: 10px;">
		<form id="LookStudentForm">
			<table border="1" bordercolor="#a0c6e5"
				style="border-collapse:collapse;margin:10px auto;">
				<tr>
					<td rowspan="7"
						style="width:20px;padding:7px;font-size:13px;background-color:aliceblue">在线录入</td>
					<td style="min-width:70px">姓名<span class="red">*</span></td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuName" />
					</td>
					<td style="min-width:70px">性别</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuSex" />
					</td>
					<td style="min-width:70px">年龄</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuAge" />
					</td>
				</tr>
				<tr>
					<td>电话<span class="red">*</span></td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuPhone" />
					</td>
					<td>学历</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuStatus" />
					</td>
					<td>状态</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="perState" />
					</td>
				</tr>
				<tr>
					<td>来源渠道<span class="red">*</span></td>
					<td><input style="border: 0px;" readonly="readonly"
						name="msgSource" />
					</td>
					<td>来源网站<span class="red">*</span></td>
					<td><input style="border: 0px;" readonly="readonly"
						name="sourceUrl" />
					</td>
					<td>来源关键词<span class="red">*</span></td>
					<td><input style="border: 0px;" readonly="readonly"
						name="sourceKeyWord" />
					</td>
				</tr>
				<tr>
					<td>所在区域</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuAddress" />
					</td>
					<td>学员关注</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuConcern" />
					</td>
					<td>来源部门</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="FromPart" />
					</td>
				</tr>
				<tr>
					<td>学员QQ</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuQQ" />
					</td>
					<td>微信号</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="stuWeiXin" />
					</td>
					<td>是否报备</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="IsBaoBei" />
					</td>
				</tr>
				<tr>
					<td>咨询师</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="askerName" />
					</td>
					<td>录入人</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="createUser" />
					</td>
				</tr>
				<tr style="height:100px">
					<td>在线备注</td>
					<td colspan="5"><input style="border: 0px;"
						readonly="readonly" name="content" />
					</td>
				</tr>
				<!-- ------------------------------------------------------------------------------------- -->
				<tr>
					<td rowspan="10"
						style="width:20px;padding:7px;font-size:13px;background-color:aliceblue">咨询师录入</td>
				</tr>
				<tr>
					<td>姓名(咨询)</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="ziXunName" name="ziXunName" /></td>
					<td>课程方向</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="learnForward" name="learnForward" /></td>
					<td>打分</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="record" name="record" /></td>
				</tr>
				<tr>
					<td>是否有效</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="isValid" name="isValid" /></td>
					<td>无效原因</td>
					<td colspan="3"><input style="border: 0px;"
						readonly="readonly" name="lostValid" id="lostValid" /></td>
				</tr>
				<tr>
					<td>是否回访</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="isReturnVist" id="isReturnVist" /></td>
					<td>首访时间</td>
					<td colspan="3"><input style="border: 0px;"
						readonly="readonly" name="firstVisitTime" id="firstVisitTime" />
					</td>
				</tr>
				<tr>
					<td>是否上门</td>
					<td><input style="border: 0px;" readonly="readonly"
						name="isHome" id="isHome" /></td>
					<td>上门时间</td>
					<td colspan="3"><input style="border: 0px;"
						readonly="readonly" name="homeTime" id="homeTime" /></td>
				</tr>
				<tr>
					<td>定金数额</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="preMoney" name="preMoney" /></td>
					<td>定金时间</td>
					<td colspan="3"><input style="border: 0px;"
						readonly="readonly" id="preMoneyTime" name="preMoneyTime" /></td>
				</tr>
				<tr>
					<td>是否缴费</td>
					<td><input style="border: 0px;" readonly="readonly" id="isPay"
						name="isPay" /></td>
					<td>缴费时间</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="payTime" name="payTime" /></td>
					<td>缴费金额</td>
					<td><input style="border: 0px;" readonly="readonly" id="money"
						name="money" /></td>
				</tr>
				<tr>
					<td>是否退费</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="isReturnMoney" name="isReturnMoney" /></td>
					<td>退费原因</td>
					<td colspan="3"><input style="border: 0px;"
						readonly="readonly" id="returnMoneyReason"
						name="returnMoneyReason" /></td>
				</tr>
				<tr>
					<td>是否进班</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="isInClass" name="isInClass" /></td>
					<td>进班时间</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="inClassTime" name="inClassTime" /></td>
					<td>进班备注</td>
					<td><input style="border: 0px;" readonly="readonly"
						id="inClassContent" name="inClassContent" /></td>
				</tr>
				<tr>
					<td>咨询师备注</td>
					<td colspan="5"><input style="border: 0px;"
						readonly="readonly" name="content" />
					</td>
				</tr>
				<tr>
					<td colspan="7" style="text-align: center;"><a
						class="easyui-linkbutton" href="javascript:void(0)"
						data-options="iconCls:'icon-cancel'"
						onclick="$('#LookStu').window('close')">关闭</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="addDynamic" class="easyui-window" title="添加动态信息"
		data-options="iconCls:'icon-add',minimizable:false,closed:true,modal:true"
		style="width:400px;padding:10px;top: 100px;">
		<form id="addDynamicForm" method="post">
			<table style="border-collapse:collapse;margin:10px auto;">
				<tr>
					<input id="d_dyaskerId" name="dyaskerId" type="hidden" />
					<input id="d_dyaskerName" name="dyaskerName" type="hidden" />
					<input id="d_dystuId" name="dystuId" type="hidden" />
					<input id="d_dystuName" name="dystuName" type="hidden" />
					<td>请输入内容:</td>
					<td><input id="d_dynamicContext" name="dynamicContext"
						class="easyui-validatebox" data-options="required:true" />
					</td>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><a class="easyui-linkbutton"
						iconCls="icon-save" onclick="saveDynamic()">保存</a> <a
						class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="$('#addDynamic').window('close')">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
