//测试3
	
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
				beforeDrag: beforeDragU,
				beforeDrop: beforeDropU
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
		   url : contextPath+"/role/listRole",//角色显示请求
		   error : function() {
		    	alert("请求失败");
		   },
		   success : function(data) {
		    	zNodes = data; 
		   }
	    });
		$.fn.zTree.init($("#treeUR"), setting, zNodes);
		treeUL();
	});
	function beforeDragU(treeId, treeNodes) {
		var zTree = $.fn.zTree.getZTreeObj("treeUL");
		var aa = zTree.getNodesByParam("id", treeNodes[0].id);
		if (aa.length == 1) {
			alert("该角色已分配！");
			return false;
		}
		return true;
	}
	function beforeDropU(treeId, treeNodes, targetNode, moveType) {
		return true;
	}
	function beforeDragUL(treeId, treeNodes) {
		return true;
	}
	function treeUL(){
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
				beforeDrag: beforeDragUL,
				beforeDrop: beforeDropU
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
			   url : contextPath+"/user/queryRole?userId="+$("#userId").val(),//客户和对应角色关系的请求
			   error : function() {
			    	alert("请求失败");
			   },
			   success : function(data) {
			    	zNodes = data; 
			   }
		    });
		$.fn.zTree.init($("#treeUL"), setting,zNodes);
	}
	//保存客户和对应角色的关系
	function addU2R(){
		/*$.messager.progress({
			title:'温馨提示',
			msg:'正在提交数据...'
		});*/
		var zTree = $.fn.zTree.getZTreeObj("treeUL");
		var nodes = zTree.transformToArray(zTree.getNodes());
		var id = $("#userId").val();
		var dlist = [];
		var list = [];
		for(var i =0;i<nodes.length;i++){
			list.push(nodes[i].id);
		}
		dlist.push({"userId":id,"roleId":list});
		$.post(contextPath+"/user/saveSet?list="+$.toJSON(dlist),function(data){
			$.messager.alert('提示',data.mes,'info');
			if("true"==data.succ)
			{
				$('#userTable').datagrid('cisreload');
			}
		});
	}
	
	function backListUserPage(){
		$('#userWindow').window('destroy');
		parent.clearUserForm();
	}
