jQuery(function($) {
	init ();
	});
	function init (){
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
				data : {
					simpleData : {
						enable : true
					}
				},
				check : {
					enable : false
				},
				callback : {
					beforeDrag : beforeDragUp,
					beforeDrop : beforeDropUp
				}
			};
			var zNodes = [ {
				id : 23,
				pId : 2,
				name : "随意拖拽 2-3"
			} ];
			$.ajax({
				async : false,
				cache : false,
				type : "POST",
				dataType : "json",
				url : "listScheduler",
				error : function() {
					alert("请求失败");
				},
				success : function(data) {
					zNodes = data;
				}
			});
			$.fn.zTree.init($("#upschedulertree"), setting, zNodes);
	}
	function beforeDragUp(treeId, treeNodes) {
		if (treeNodes[0].name == $("#oldname")[0].value) {
			return true;
		} else {
			return false;
		}
	}
	function beforeDropUp(treeId, treeNodes, targetNode, moveType) {
		return true;
	}
	function saveUpdate() {
		var datalist = [];
		var zTree = $.fn.zTree.getZTreeObj("upschedulertree");
		var aa = zTree.getNodesByParam("name", $("#oldname").val());
		var pre = "";
		if (aa[0].getPreNode() != null) {
			pre = aa[0].getPreNode().id;
		}
		var next = "";
		if (aa[0].getNextNode() != null) {
			next = aa[0].getNextNode().id;
		}
		var pid = "";
		if(aa[0].pId!=null){
			pid = aa[0].pId;
		}
		datalist.push({
			"name" : encodeURI($("#privilegename").val()),
			"url" : $("#url").val(),
			"pid" : pid,
			"method" : aa[0].system,
			"pre" : pre,
			"next" : next,
			"id" : aa[0].id
		});
		$.post("saveUpdate?list="+$.toJSON(datalist),function(data){
			$.messager.alert('提示',data.mes,'info');
			init ();
			$("#oldname")[0].value=$("#privilegename").val();
			//parent.clearScheduler();
			
		});
	}