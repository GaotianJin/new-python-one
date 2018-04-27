var nameArray = new Array();
var id = 1;
jQuery(function($) {
		initMenu();
	});
	
	function initMenu() {
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
				beforeDrag : beforeDragMenu,
				beforeDrop : beforeDropMenu
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
			url : contextPath+"/menu/listMenu",
			error : function() {
				$.messager.alert('提示',"请求失败");
			},
			success : function(data) {
				$.each(data, function(i, n) {
				});
				zNodes = data; //把后台封装好的简单Json格局赋给zNodes
			}
		});
		$.fn.zTree.init($("#menutree"), setting, zNodes);
	}
	function beforeDragMenu(treeId, treeNodes) {
		if ($.inArray(treeNodes[0].name, nameArray)!=-1) {
			return true;
		} else {
			return false;
		}
	}
	function beforeDropMenu(treeId, treeNodes, targetNode, moveType) {
		return true;
	}
	function menuOnDrop(event, treeId, treeNodes, targetNode, moveType) {
		var isAdd = $.fn.zTree.getZTreeObj("menutree").getNodesByParam("name",
				$("#menuname").val()).length;
		if (isAdd == 1) {
			nameArray.push($("#menuname").val());
		}
	}
	function addNodes() {
		var name = $("#menuname")[0].value;
		var url = $("#menuurl")[0].value;
		if (name == "" || name == null) {
			$.messager.alert('提示','菜单名不能为空!','info');
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("menutree");
		var aa = zTree.getNodesByParam("name", name);
		var bb = zTree.getNodesByParam("url", url);
		if (aa.length == 1) {
			$.messager.alert('提示','该菜单已存在!','info');
			return;
		}
		if (bb.length == 1&&url!="") {
			$.messager.alert('提示','该连接已存在!','info');
			return;
		}
		var setting = {
			data : {
				simpleData : {
					enable : true
				//简单json格式
				}
			},
			check : {
				enable : false
			},
			callback : {
				onDrop : menuOnDrop
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
		var zNodes = [ {
			id : "-99"+id++,
			pId : 0,
			name : $("#menuname")[0].value,
			url : $("#menuurl")[0].value,
			system : $("#system")[0].value
		} ];
		$.fn.zTree.init($("#newtree"), setting, zNodes);
	}
	function saveAdd() {
		if (nameArray.length > 0) {
			var datalist = [];
			for ( var i = 0; i < nameArray.length; i++) {
				var zTree = $.fn.zTree.getZTreeObj("menutree");
				var aa = zTree.getNodesByParam("name", nameArray[i]);
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
					"fakeid" : aa[0].id,
					"privilegename" : aa[0].name,
					"url" : aa[0].url,
					"pid" : pid,
					"method" : aa[0].system,
					"pre" : pre,
					"next" : next
				});
			}
			nameArray.length = 0;
			$.post(contextPath+"/menu/saveAdd?list="+encodeURI($.toJSON(datalist)),function(data){
				$.messager.alert('提示',data.mes,'info');
				initMenu();
                //zTree.refresh();
		        //window.document.location.reload();
				parent.clearMenu();
			});
		} else {
			$.messager.alert('提示',"没有新增菜单");
			return;
		};
	}
	
	function backListMenuPage (){
		$('#menuWindow').window('destroy');
		parent.clearForm();
	}