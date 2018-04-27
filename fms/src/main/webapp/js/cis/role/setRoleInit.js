jQuery(function($) {
		var setting = {
			edit : {
				drag : {
					isCopy : false,
					isMove : true
				},
				enable : true,
				removeTitle : "remove",
				renameTitle : "rename",
				showRemoveBtn : false,
				showRenameBtn : false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			check: {
				enable : false
			},
			callback: {
				beforeDrag: beforeDragR,
				beforeDrop: beforeDropR
			}
		};

		var zNodes =[
			{ id:23, pId:2, name:"随意拖拽 2-3"}
		];
		$.ajax({
		   async : false,
		   cache : false,
		   type : "POST",
		   dataType : "json",
		   url : contextPath+"/menu/listMenu",//查询所有菜单的请求
		   error : function() {
		    	alert("请求失败");
		   },
		   success : function(data) {
		    	zNodes = data; 
		   }
	    });
		$.fn.zTree.init($("#treeRR"), setting, zNodes);
		treeRL();

		var navH = $(".hb").offset().top;
		scrolldiv.onscroll=function(){
			var scroH = $(this).scrollTop();
		if(scroH >= navH){
			$(".hb").css({"position":"fixed","top":0});
		}else{
			$(",hb").css({"position":"static"});
		}}
	});
	function beforeDragR(treeId, treeNodes) {
		var zTree = $.fn.zTree.getZTreeObj("treeRL");
		var aa = zTree.getNodesByParam("id", treeNodes[0].id);
		if (aa.length == 1) {
			alert("该菜单已分配！");
			return false;
		}
		return true;
	}
	function beforeDropR(treeId, treeNodes, targetNode, moveType) {
		return true;
	}
	function beforeDragRL(treeId, treeNodes) {
		return true;
	}
	function treeRL(){
		var setting = {	
			data: {
				simpleData: {
					enable: true//简单json格式
				}
			},
			check: {
				enable : false
			},
			callback: {
				beforeDrag: beforeDragRL,
				beforeDrop: beforeDropR
			},
			edit : {
				
				drag : {
					isCopy : false,
					isMove : true
				},
				enable : true,
				removeTitle : "remove",
				renameTitle : "rename",
				showRemoveBtn : false,
				showRenameBtn : false
			}

		};
		var zNodes =[
		     		{ id:23, pId:2, name:"随意拖拽 2-3"}
		     	];
		$.ajax({
			   async : false,
			   cache : false,
			   type : "POST",
			   dataType : "json",
			   url :  contextPath+"/role/queryMenuWithRemote?roleId="+$("#roleid").val(),//查询角色和菜单对应关系的请求
			   error : function() {
			    	alert("请求失败");
			   },
			   success : function(data) {
			    	zNodes = data; 
			   }
		    });
		$.fn.zTree.init($("#treeRL"), setting,zNodes);
	}
	//保存角色和菜单的对应关系
	function addR2P(){
		$.messager.progress({
			title:'温馨提示',
			msg:'正在提交数据...'
		});
		var zTree = $.fn.zTree.getZTreeObj("treeRL");
		var nodes = zTree.transformToArray(zTree.getNodes());
		var id = $("#roleid").val();
		var dlist = [];
		var list = [];
		for(var i =0;i<nodes.length;i++){
			list.push(nodes[i].id);
		}
		dlist.push({"roleId":id,"menuId":list});
		$.post(contextPath+"/role/saveSet?list="+$.toJSON(dlist),function(data){
			$.messager.alert('提示',data.mes,'info');
			$.messager.progress('close');
		});
	}
	
	function backListRolePage(){
		$('#roleWindow').window('destroy');
		parent.clearRole();
	}