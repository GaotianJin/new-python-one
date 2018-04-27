	var i = 0;
	var content = 0;
	var datalist = [];
	jQuery(function($) {
		var setting = {
			edit : {
				drag : {
					isCopy : false,
					isMove : false
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

		var zNodes ;
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
		$.fn.zTree.init($("#tree"), setting, zNodes);
	});
	function beforeDragMenu(treeId, treeNodes) {
		return true;
	}
	function beforeDropMenu(treeId, treeNodes, targetNode, moveType) {
		return true;
	}
	/*
	function zTreeOnClick(treeId, treeNodes, targetNode, moveType) {
		$('#dd').dialog({  
		    title: '节点编辑',  
		    width: 400,  
		    height: 200,  
		    closed: false,  
		    cache: false,  
		    modal: true,
			toolbar:[{
				text:'添加属性',
				iconCls:'icon-add',
				handler:function(){
					$('#dd').append('<div>key<input type="text" id="key"+i name="key" + i />value<input type="text" name="value"+i></div>');
					i++;
				}
			},{
				text:'添加内容',
				iconCls:'icon-add',
				handler:function(){
					if(content==0){
						$('#dd').append('<div>content<input type="text" id="content" name="content" /></div>');
						content++;
					}else{
						alert("不能重复添加");
					}
					
				}
			}],
			buttons:[{
				text: '提交', 
				iconCls: 'icon-ok', 
				handler: function() { 
					//var dlist = [];
					//dlist.push({"key":rows[0].privilege.privilegename,"value":url}); 
					//$.toJSON(dlist);
					var zTree = $.fn.zTree.getZTreeObj("nodetree");
					var aa = zTree.getNodesByParam("name", $("#nodename").val());
					aa[0].content = $("#content");
					aa[0].attr = $.toJSON(dlist);
				} 
				}, { 
				text: '取消', 
				iconCls: 'icon-cancel',
				handler: function() { 
					$('#dd').dialog('close'); 
				} 
			}]
		});  
	}*/
	function addNodes() {
		var name = $("#nodename").val();
		if (name == "" || name == null) {
			alert("节点名不能为空！");
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
				beforeDrag : beforeDragMenu,
				beforeDrop : beforeDropMenu
			},
			edit : {
				drag : {
					isCopy : false,
					isMove : true
				},
				enable : true,
				removeTitle : "remove",
				renameTitle : "编辑节点",
				showRemoveBtn : false,
				showRenameBtn : false
			}

		};
		var zNodes = [ {
			name : $("#nodename").val(),
			attr : "",
			content : "" 
		} ];
		$.fn.zTree.init($("#nodetree"), setting, zNodes);
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
	function saveAdd() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
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
	
	