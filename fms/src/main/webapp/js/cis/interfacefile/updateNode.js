jQuery(function($) {
	init ();
	});
var datalist = [];
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
					onClick : click,
					beforeDrag : beforeDragUp,
					beforeDrop : beforeDropUp
				}
			};
			var zNodes =[];
			$.ajax({
				async : false,
				cache : false,
				type : "POST",
				dataType : "json",
				url : "listNodeXML?xmlid="+$("#xmlid").val(),
				error : function() {
					alert("请求失败");
				},
				success : function(data) {
					$.each(data, function(i, n) {
					});
					zNodes = data; //把后台封装好的简单Json格局赋给zNodes
				}
			});
			$.fn.zTree.init($("#upnodetree"), setting, zNodes);
	}
	function beforeDragUp(treeId, treeNodes) {
		if (treeNodes[0].name == $("#oldname").val()) {
			return true;
		} else {
			return false;
		}
	}
	function beforeDropUp(treeId, treeNodes, targetNode, moveType) {
		return true;
	}
	function click(event,treeId, treeNodes) {
		if (treeNodes.name == $("#oldname").val()) {
			var zTree = $.fn.zTree.getZTreeObj("upnodetree");
			zTree.setting.edit.showRenameBtn=true;
		}
	}
	function getNode(node){
		for(var j=0;j<node.length;j++){
			if(0==node[j].check_Child_State){//含有子节点
				getNode(node[j].children);
			}
			getData(node[j]); 
			
		}
	}
	function getData(aa){
		var pre = "";
		if (aa.getPreNode() != null) {
			pre = aa.getPreNode().tId;
		}
		var next = "";
		if (aa.getNextNode() != null) {
			next = aa.getNextNode().tId;
		}
		var pid = "";
		if(aa.getParentNode()!=null){
			pid = aa.getParentNode().tId;
		}
		datalist.push({
			"id" : aa.tId,
			"name" : aa.name,
			"pid" : pid,
			"pre" : pre,
			"next" : next,
			"attr" : aa.attr,
			"content" : aa.content,
			"xmlid" : $("#xmlid").val()
		});
		return datalist;
	}
	function saveUpdate() {
		var zTree = $.fn.zTree.getZTreeObj("upnodetree");
		for ( var i = 0; i < zTree.getNodes().length; i++) {
			if(0==zTree.getNodes()[i].check_Child_State){//含有子节点
				getNode(zTree.getNodes()[i].children);
			}
			getData(zTree.getNodes()[i]);
			
		}
		$.post("saveAddNode?list="+$.toJSON(datalist),function(data){
			$.messager.alert('提示',data.mes,'info');
		});
	
	}