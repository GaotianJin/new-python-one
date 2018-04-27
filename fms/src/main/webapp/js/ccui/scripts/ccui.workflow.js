/*
 * workflow组件，负责中央编辑区域的核心工作流图形元素的初始化、动作等。 TODO <p>初始化的辅助html的生成</p> <p>初始化节点和transition的html生成</p>
 * <p>事件监听</p> <p>transition上的point的移动过程显示</p> <p>transition的click状态显示</p>
 * <p>增加node的resizable</p>
 */
(function($) {
	$.widget("ccui.workflow", $.ui.mouse, {
		GUID : 0,
		/**
		 * widget 组件初始化方法
		 */
		_init : function() {
			var self = this;
			this.element.addClass('ccui-workflow').css({
						width : this.options.width,
						height : this.options.height
					});
			this.element.parent().css({
						overflow : 'auto'
					});
			this.element.disableSelection().selectable({// 初始化多选
				cancel : '.point,.node,.transitionName,.select-dragging',
				filter : '.point,.node',
				delay : 100,
				start : function(event, ui) {
					$('.select-dragging').hide();
					self._enableDrag();
				},
				stop : function(event, ui) {
					$('.select-dragging').css({
						left : $('.ui-selectable-helper').offset().left
								+ this.scrollLeft - $(this).offset().left,
						top : $('.ui-selectable-helper').offset().top
								+ this.scrollTop - $(this).offset().top,
						width : $('.ui-selectable-helper').width(),
						height : $('.ui-selectable-helper').innerHeight()
					});
					$('.select-dragging').show();
					self._disableDrag();
				}
			});
			$('.node,.point', this.element).each(function() {
						self._selectDragging($(this).attr('id'));
					});
			this.GUID = this.element.find('.node').length;
			this._initCanvaContext();// 绘画面板支持
			this._refreshTransitions();
			this._mouseInit();// 初始鼠标支持
			this._initAction();
		},

		refresh : function() {
			this._refreshTransitions();
		},

		_disableDrag : function() {
			$('.ui-selected').each(function() {
						$(this).draggable('disable');
					});
		},

		_enableDrag : function() {
			$('.ui-selected').each(function() {
						$(this).draggable('enable');
					});
		},

		_initAction : function() {
			var self = this;
			this.element.bind('click', function(event) {
						var clickElement = self._getElementFromEvent(event);
						if (!clickElement.is('.point')
								&& !clickElement.is('.node')) {
							$('.select-dragging').hide();
							self._enableDrag();
							$('.ui-selected', this).removeClass('ui-selected');
						}
						if (clickElement.is('.point')) {
							clickElement = clickElement.parent();
						}// 处理point的选中
						self._selected(clickElement, event);

					}).bind('dblclick', function(event) {
						if (self.currentFieldEditor) {
							if (self.currentFieldEditor.fieldEditor) {
								self.currentFieldEditor.fieldEditor('destroy');
								self.currentFieldEditor = {};
							}
						}
						var clickElement = self._getElementFromEvent(event);
						if (clickElement.is('.node')
								|| clickElement.is('.point')) {
							clickElement.fieldEditor({
										editor : 'input',
										editorName : 'text',
										beforeClose : self.editorChange
									});
							self.currentFieldEditor = clickElement;
						}

					});
			$('body', document).click(function() {
						if (self.currentFieldEditor) {
							if (self.currentFieldEditor.fieldEditor) {
								self.currentFieldEditor.fieldEditor('destroy');
								self.currentFieldEditor = {};
							}
						}
					}).live('keydown', function(event) {
				if (event.keyCode == 46) {
					$(".ui-selected,.click").each(function(index, element) {
						if ($(element).is('.node')) {
							$('body', element.ownerDocument).application(
									'executeCommand', 'removeNode', {
										id : element.id,
										offset : {
											left : element.offsetLeft,
											top : element.offsetTop
										},
										text : $(element).text()
										//type : element.classList[1]
									});
						} else if ($(element).is('.transition')) {
							$('body', element.ownerDocument).application(
									'executeCommand', 'removeTransition', {
										id : element.id,
										fromId : $(element).attr('from'),
										toId : $(element).attr('to')
									});
						}
					})
				}
			});
		},

		// 内部方法，选中某个节点的动作
		_selected : function(clickElement, event) {
			if (clickElement[0] != $('.click', this.element)[0]) {
				var type;
				if (clickElement.hasClass('node')) {
					type = 'node';
					if (event && event.shiftKey === true) {
						// 画线
						var fromId = $('.click', this.element).attr('id'), toId = clickElement
								.attr('id'), id = this._addTransition(fromId,
								toId, true, event);
						if ($.isFunction(this.options.addTransitionCommand)) {
							this.options.addTransitionCommand.apply(
									this.element[0], [event, {
												fromId : fromId,
												toId : toId,
												id : id
											}]);
						}
					}
					$('.click', this.element).removeClass('click');
					clickElement.addClass('click');
					// 清理辅助画图面板
					this._clearCanvasHelper();
				} else if (clickElement.hasClass('transition')) {
					type = 'transition';
					$('.click', this.element).removeClass('click');
					clickElement.addClass('click');
					// 画click状态的transition
					this._refreshClickTransition();
				}

				if (type) {
					this._propertiesSupport(event, {
								type : type,
								selectorPath : ['#' + clickElement.attr('id')]
							});
					if (event && $.isFunction(this.options.onSelect)) {
						this.options.onSelect.apply(this.element[0]);
					}
				}
			}
		},

		// 外部方法，调用内部方法
		selected : function(id) {
			var element = this.element.find('#' + id);
			this._selected(element, null);
		},

		// 触发属性编辑器propertyTable的属性变化，以及propertyTable的属性变化后回调workflow的方法
		_propertiesSupport : function(event, data) {
			if (data && data.type) {
				var selectorPath = ['.ccui-workflow#' + this.element.attr('id')]
						.concat(data.selectorPath), pElement = this.element
						.find(data.selectorPath.join(' ')), propertyValue = this
						._getPropertyValue(pElement, data);
				// 闭包函数,供属性编辑器中的属性值修改后调用
				function propertyChange(selectorPath) {
					return function(property, value) {
						// var propertyElement = $(selectorPath.join(' '));
						// 调用属性修改的函数
						$(selectorPath[0]).workflow('setProperty',
								selectorPath.slice(1), property, value, event);
					};
				}
				this.editorChange = propertyChange(selectorPath);
				if (this.options.propertyTable) {// 调用属性编辑器的熟悉填充函数
					$(this.options.propertyTable).propertyTable(
							'fillProperties', propertyValue, this.editorChange);
				}
			}
		},

		// 内部方法，得到某个节点或线的属性
		_getPropertyValue : function(pElement, data) {
			var propertyValue = {
				id : pElement.attr('id'),
				// name : pElement.attr('name') || pElement.attr('id'),
				left : Math.round(pElement.position().left) + 'px',
				top : Math.round(pElement.position().top) + 'px'
			};

			switch (data.type) {
				case 'node' :
					var nodeType = pElement[0].className.split(' ')[1];
					$.extend(propertyValue, {
								nodeType : nodeType,
								text : pElement.text()
							});
					break;
				case 'transition' :
					$.extend(propertyValue, {
								text : pElement.attr('title')
							});
					break;
				default :
			}
			return propertyValue;
		},

		// 外部方法，根据属性变化调节css
		setProperty : function(selectorPath, property, value, event) {
			var propertyElement = this.element.find(selectorPath.join(' ')), oldValue;

			switch (property) {
				case 'text' :
					if (propertyElement.hasClass('transition')) {
						oldValue = propertyElement.attr('title');
						propertyElement.attr('title', value);
					} else {
						oldValue = propertyElement.text();
						propertyElement.text(value);
					}
					break;
				case 'left' :
					oldValue = propertyElement.css('left');
					propertyElement.css('left', value);
					this._refreshTransitions();
					break;
				case 'top' :
					oldValue = propertyElement.css('top');
					propertyElement.css('top', value);
					this._refreshTransitions();
					break;
				default :

			}
			// 调用属性编辑器的属性填充函数
			if (this.options.propertyTable) {
				$(this.options.propertyTable).propertyTable('setProperty',
						property, value);
			}
			if (property == 'text') {
				// 设置树上的文本
				$('#tree-workflow-element').tree('setNodeText',
						selectorPath[0], value);
			}
			// propertyChangeCommand是workflow组件的扩展方法，事件触发时调用，而通过重做命令调用则进入else
			if (event && $.isFunction(this.options.propertyChangeCommand)) {
				this.options.propertyChangeCommand.apply(this.element[0], [
								event, {
									selectorPath : selectorPath,
									oldValue : oldValue,
									property : property,
									value : value
								}]);
			}
		},

		/**
		 * 初始化canva上下文 this.canvaContext 主上下文
		 */
		_initCanvaContext : function() {
			var main = document.createElement('canvas');
			var helper = document.createElement('canvas');
			this.element.append(main).append(helper);

			// ie9以下不支持canvas，要使用excanvas.js
			if ($.browser.msie & $.browser.version < 9) {
				G_vmlCanvasManager.initElement(main);
				G_vmlCanvasManager.initElement(helper);
			}

			this.canvasContext = main.getContext('2d');
			this.canvasContextHelper = helper.getContext('2d');
			main = null;
			helper = null;
		},
		/**
		 * 设置canvas的区域
		 */
		_calCanvasRect : function() {
			var domEl = this.element[0];
			var canvasRect = {
				width : domEl.scrollWidth,
				height : domEl.scrollHeight
			};
			if (!$.browser.mise) {
				// 显示指定canvas的高和宽的属性，防止canvas缩放
				this.element.find('canvas').attr('width', canvasRect.width)
						.attr('height', canvasRect.height);
			}
			this.element.find('canvas,.bg').css(canvasRect);
			return canvasRect;
		},
		/**
		 * 刷新流线条
		 */
		_refreshTransitions : function() {
			var self = this, canvasRect = this._calCanvasRect();
			this.canvasContext.clearRect(0, 0, canvasRect.width,
					canvasRect.height);
			var transitionsArgs = [];
			this.element.find('.transition').each(function() {
						transitionsArgs.push(self._getTransitionArgs(this));
					});

			drawCanvaTransitions(this.canvasContext, transitionsArgs);
			this._refreshClickTransition();
		},

		_refreshClickTransition : function() {
			var transElement = this.element.find('.transition.click');
			if (transElement.length === 0) {
				return;
			}
			this._clearCanvasHelper();
			drawCanvaTransitions(this.canvasContextHelper, [this
							._getTransitionArgs(transElement)], 'red');
		},

		_getTransitionArgs : function(transElement) {
			transElement = $(transElement);
			var id = transElement.attr('id'), fromId = transElement
					.attr('from'), toId = transElement.attr('to'), from = $('#'
							+ fromId, this.element), to = $('#' + toId,
					this.element), g = [];
			$('.point', transElement).each(function() {
						g.push({
									left : this.offsetLeft + 2,
									top : this.offsetTop + 2
								});
					});

			return {
				id : id,
				text : id,
				fromId : fromId,
				toId : toId,
				startPos : getRectCenterPos(calculatePos(from), from.width(),
						from.outerHeight()),
				endPos : getRectCenterPos(calculatePos(to), to.width(), to
								.outerHeight()),
				endRect : {
					width : to.width(),
					height : to.outerHeight()
				},
				g : g
			}
		},

		_traceTransition : function(pointDom, pointPos) {
			// TODO
		},

		_clearCanvasHelper : function() {
			this.canvasContextHelper.clearRect(0, 0, $('.bg', this.element)
							.width(), $('.bg', this.element).innerHeight());
		},

		/** *****************开始节点拖动处理********************************** */
		_mouseStart : function(event) {
			var dragElement = this._getElementFromEvent(event);
			$('.select-dragging', this).hide();//

			if (dragElement) {
				if (event.ctrlKey) {
					$.ccui.log.info(event.ctrlKey);
				} else if (event.shiftKey) {

				} else {
					this.currentDrag = dragElement;
					this.helper = this._createHelper(event, dragElement);
					this.originalPageX = event.pageX;
					this.originalPageY = event.pageY;
					if (dragElement.is('.ui-selected')) {
						this.offsetDragX = event.pageX
								- this.helper.offset().left;
						this.offsetDragY = event.pageY
								- this.helper.offset().top;
					} else {
						this.offsetDragX = event.pageX
								- dragElement.offset().left;
						this.offsetDragY = event.pageY
								- dragElement.offset().top;
					}
					// 原始元素的相对位置
					this.originalPosition = this._generatePosition(event);
				}
			}
			return true;
		},

		_mouseDrag : function(event) {
			this.position = this._generatePosition(event);
			if (this.currentDrag.hasClass('point')) {
				this._traceTransition(this.currentDrag[0], this.position);
			}
			if (this.helper) {
				this.helper.css(this.position);
			}
			return false;
		},

		_mouseStop : function(event) {
			var selectorPath = [];
			if (this.helper) {
				this.helper.empty().hide();
				if (this.helper.is('.select-dragging')) {
					selectorPath.push('.ui-selected');
				}
			} else if (this.currentDrag.hasClass('node')) {
				selectorPath.push('#' + this.currentDrag.attr('id'));
			} else if (this.currentDrag.hasClass('point')) {
				var ponitId = this.currentDrag.attr('id');
				if (!ponitId) {
					ponitId = this.currentDrag.parent().attr('id') + '-p';
					this.currentDrag.attr('id', ponitId);
				}
				selectorPath.push('#' + ponitId);
			}

			this._move(selectorPath, this.position, this.originalPosition,
					event);
			if ($.isFunction(this.options.moveCommand)) {
				this.options.moveCommand.apply(this.element[0], [event, {
									selectorPath : selectorPath,
									position : this.position,
									originalPosition : this.originalPosition
								}]);
			}
			selectorPath = null;
			this.helper = null;
			this.position = null;
			this.currentDrag = null;

			this.offsetDragX = null;
			this.offsetDragY = null;
			this.originalPageX = null;
			this.originalPageY = null;
			this.originalPosition = null;
			return false;
		},

		_mouseCapture : function(event) {
			return true;
		},
		/**
		 * 
		 */
		_move : function(selectorPath, position, originalPosition, event) {
			if (selectorPath.length < 1)
				return;
			if (selectorPath == '.ui-selected'
					|| selectorPath[1] == '.ui-selected') {
				var moveX = position.left - originalPosition.left, moveY = position.top
						- originalPosition.top;
				this.element.find(selectorPath[0]).each(function() {
							$(this).css({
										left : this.offsetLeft + moveX,
										top : this.offsetTop + moveY
									});
						});
				this._refreshTransitions();
			} else {
				this.element.find(selectorPath.join(' ')).css(position);
				this._refreshTransitions();
			}
		},

		move : function(selectorPath, position, originalPosition) {
			this._move(selectorPath, position, originalPosition);
		},

		_getElementFromEvent : function(event) {
			return $(event.srcElement || event.target);
		},

		_generatePosition : function(event) {
			return this._dealwithOutPos({
						left : event.pageX - this.offsetDragX,
						top : event.pageY - this.offsetDragY
					});
		},

		_dealwithOutPos : function(pos) {
			return {
				left : pos.left - this.element.offset().left
						+ this.element.scrollLeft(),
				top : pos.top - this.element.offset().top
						+ this.element.scrollTop()
			};
		},

		_createHelper : function(event, dragElement) {
			var helper;
			if (dragElement.is('.ui-selected')) {
				helper = this.element.find('>.select-dragging');
				$('.select-dragging').show();
			}
			return helper;
		},
		/** *****************结束节点拖动处理********************************** */
		/**
		 * 
		 */
		addNode : function(id, text, type, pos) {
			var self = this;
			type = (type) ? type : 'state';
			// 只能有一个start
			if ((type == 'start' || type == 'end')
					&& this.element.find('.node.' + type).length > 0) {
				return false;
			}
			if (!id) {
				id = type + '-' + this.GUID++;
			}
			pos = $.extend({
						left : 20,
						top : 100
					}, pos);

			this.element.append('<div id="' + id + '" class="node ' + type
					+ '" style="left:' + pos.left + 'px;top:' + pos.top
					+ 'px">' + text + '</div>');
			this._selectDragging(id);
			return {
				id : id,
				text : text
			};

		},

		addNodeTransitions : function(nodeTransitionArgs) {
			// 画线元素
			var self = this;
			var length = nodeTransitionArgs.length, nodeTransitionArg, id, htmls = [];
			for (var i = 0; i < length; i++) {
				nodeTransitionArg = nodeTransitionArgs[i];
				id = nodeTransitionArg.id;
				htmls.push('<div id="' + id + '" ');
				htmls.push(' from="' + nodeTransitionArg.fromId + '"');
				htmls.push(' to="' + nodeTransitionArg.toId + '"');
				htmls.push('class="transition">');
				$(nodeTransitionArg.g).each(function(i) {
					htmls.push('<div id="' + id + '-p' + i
							+ '" class="point" style="left:' + (this.left - 2)
							+ 'px;top:' + (this.top - 2) + 'px;"></div>');
				});
				htmls.push('</div>');
				id = null;
				nodeTransitionArg = null;
			}
			this.element.append(htmls.join(''));
			// 画线条
			drawCanvaTransitions(this.canvasContext, nodeTransitionArgs);
			this._refreshClickTransition();
		},

		// 使某个节点线、或选择的区域可拖动
		_selectDragging : function(id) {
			var self = this;
			$('#' + id).draggable({
				start : function(event, ui) {
					self._mouseStart(event);
				},
				drag : function(event, ui) {
					self.position = self._generatePosition(event);
					var selectorPath = [];
					if (self.currentDrag.hasClass('node')) {
						selectorPath.push('#' + self.currentDrag.attr('id'));
					} else if (self.currentDrag.hasClass('point')) {
						var ponitId = self.currentDrag.attr('id');
						if (!ponitId) {
							ponitId = self.currentDrag.parent().attr('id')
									+ '-p';
							self.currentDrag.attr('id', ponitId);
						}
						selectorPath.push('#' + ponitId);
					}
					self.element.find(selectorPath.join(' '))
							.css(self.position);
					self.canvasContext.clearRect(0, 0,
							self.canvasContext.canvas.scrollWidth,
							self.canvasContext.canvas.scrollHeight);
					var transitionsArgs = [];
					self.element.find('.transition').each(function() {
								transitionsArgs.push(self
										._getTransitionArgs(this));
							});

					drawCanvaTransitions(self.canvasContext, transitionsArgs);
					self._refreshClickTransition();
				},
				stop : function(event, ui) {
					self._mouseStop(event);
				},
				containment : 'parent'// 限制拖动区域
			});
		},

		removeNode : function(id, type) {
			var self = this;
			if (id) {
				// 删除相关线条
				this.element.find('.transition[from=' + id
						+ '],.transition[to=' + id + ']').each(
						function(index, element) {
							if ($
									.isFunction(self.options.removeTransitionCommand)) {
								self.options.removeTransitionCommand.apply(
										element, [event, {
													id : element.id,
													offset : {
														left : element.offsetLeft,
														top : element.offsetTop
													},
													fromId : $(element)
															.attr('from'),
													toId : $(element)
															.attr('to')
												}]);
							}
						});
				this.element.find('#' + id).remove();
				this._refreshTransitions();
			}
		},

		getNodeTransitionArgs : function(nodeId) {
			var self = this, nodeTransitionArgs = [];
			this.element.find('.transition[from=' + nodeId
					+ '],.transition[to=' + nodeId + ']').each(function() {
						nodeTransitionArgs.push(self
								._getTransitionArgs($(this)));
					});
			return nodeTransitionArgs;
		},

		_addTransition : function(fromId, toId, addPoint, event) {
			var id = fromId + '-' + toId, from = $('#' + fromId, this.element), to = $(
					'#' + toId, this.element), htmls = [];
			if (from.length == 1
					&& to.length == 1
					&& this.element.find('.transition[from=' + fromId + '][to='
							+ toId + ']').length == 0) {
				var fromPos = getRectCenterPos(calculatePos(from),
						from.width(), from.outerHeight()), toPos = getRectCenterPos(
						calculatePos(to), to.width(), to.outerHeight()), centerLeft = Math
						.min(toPos.left, fromPos.left)
						+ Math.abs(toPos.left - fromPos.left) / 2 - 2, centerTop = Math
						.min(toPos.top, fromPos.top)
						+ Math.abs(toPos.top - fromPos.top) / 2 - 2;
				htmls.push('<div id="' + id + '" from="' + fromId + '" to="'
						+ toId + '" class="transition">');
				if (addPoint == true) {
					var pointTop, pointLeft;
					if (Math.abs(fromPos.top - toPos.top) > 20
							&& Math.abs(fromPos.left - toPos.left) > 20) {
						pointTop = toPos.top - 2;
						pointLeft = fromPos.left - 2;
					} else {
						pointTop = centerTop;
						pointLeft = centerLeft;
					}
					htmls.push('<div id="' + id
							+ '-p0" class="point" style="left:' + pointLeft
							+ 'px;top:' + pointTop + 'px;"></div>');
				}
				htmls.push('</div>');
				this.element.append(htmls.join(''));
				this._refreshTransitions();
				this._selectDragging(id + '-p0');
				return id;
			} else {
				return false;
			}
		},

		addTransition : function(fromId, toId) {
			return this._addTransition(fromId, toId, true);
		},

		removeTransition : function(fromId, toId) {
			this.element.find('.transition[from=' + fromId + '][to=' + toId
					+ ']').remove();
			this._refreshTransitions();
		},

		removeTransitionById : function(id) {
			this.element.find('#' + id).remove();
			this._refreshTransitions();
		},
		/**
		 * 激活时调用的方法
		 */
		active : function(event) {
			if ($.browser.msie) {
				this._refreshTransitions();
			}
			var clickElement = this.element.find('.click');
			if (clickElement.length > 0) {
				var type = clickElement.hasClass('node')
						? clickElement[0].className.split(' ')[1]
						: 'transition';
				this._propertiesSupport(event, {
							type : type,
							selectorPath : ['#' + clickElement.attr('id')]
						});
			}
		},

		// 执行保存的操作，使用了ajax时，需要在使函数返回值为false 在ajax回调函数中执行afterSave
		save : function(afterSave) {
			$("#dialog-form").dialog({
				modal : true, // 创建模式对话框
				autoOpen : false,
				closeOnEscape : false,
				height : 200,
				width : 400,
				buttons : {
					"Ok" : function() {
						if ($("#processname").val() != "") {
							var processname = $("#processname").val();
							var processid = $("#processid").val();
							var node = [];
							var tran = [];
							var nodes = $("div[class^='node']");
							var transition = $("div[class^='transition']");
							for (var i = 0; i < nodes.length; i++) {
								var id = nodes[i].id;
								var left = nodes[i].offsetLeft;// 左坐标
								var top = nodes[i].offsetTop;// 上坐标
								var width = nodes[i].offsetWidth;//
								var height = nodes[i].offsetHeight;//
								var name = nodes[i].innerText;
								var classname = nodes[i].className;
								node.push({
											"class" : classname,
											"name" : encodeURIComponent(name),
											"id" : id,
											"Coordinate" : left + "," + top
													+ "," + width + ","
													+ height
										});
							}
							for (var i = 0; i < transition.length; i++) {
								var id = transition[i].id;
								var from = $(transition[i]).attr('from');
								var to = $(transition[i]).attr('to');
								var left = transition[i].childNodes[0].offsetLeft;
								var top = transition[i].childNodes[0].offsetTop;
								var id2 = transition[i].childNodes[0].id
										.split(id)[1];
								var name = transition[i].title;
								tran.push({
											"id" : id + "*" + id2,
											"name" : name,
											"from" : from,
											"to" : to,
											"Coordinate" : left + "," + top
										});
							}
							$.post(	"saveAdd?node=" + toJSON(node) + "&tran="
											+ toJSON(tran) + "&name="
											+ processname + "&id=" + processid,
									function(data) {
										$("#processid").val(data.id);
										alert(data.mes);
										$("#dialog-form").dialog('close');
									});

						} else {
							$("#message")[0].style.display = "";
						}

					},
					"Cancel" : function() {
						$(this).dialog('close');
					}
				}
			});
			$("#dialog-form").dialog("open");
			return true;
		},

		_getCopyData : function() {
			var clickElement = this.element.find('.click');
			if (clickElement.length > 0) {

			}
			// ,
			// id = clickElement.attr('id'),
			// element =
			// $('<div></div>').append(clickElement.clone().attr('id',id+'-copy'));//.appendTo(this.element),
			// copyData = element.html();

			// element.remove();
			// alert(copyData);
			return copyData;
		},

		_doCopy : function() {

		},
		_doPaste : function(clipboardData) {

		},
		/**
		 * 注销
		 */
		destory : function() {
			// TODO
			this.canvasContext = null;
			this.canvasContextHelper = null;
			$.widget.prototype.destory.apply(this);
		}
	});

	// $.extend($ccui.workflow,$.ccui.EditorPart);
	$.extend($.ccui.workflow, {
				defaults : {
					width : 800,
					height : 500,
					distance : 1,
					delay : 100,
					appendTo : 'parent'
				},
				commandsCallBack : [{
							name : 'propertyChangeCommand',
							command : 'setProperty'
						}, {
							name : 'moveCommand',
							command : 'move'
						}, {
							name : 'addTransitionCommand',
							command : 'addTransition'
						}],
				getter : ['addNode', 'addTransition', 'getNodeTransitionArgs',
						'getCommandCallbacks', 'getCommands', 'getUndoCommands']
			});
	// 计算定位
	function calculatePos(element) {
		var offsetParent = element.offsetParent(), pos = element.offset();
		// alert(offsetParent.attr('id'));
		return {
			left : pos.left - offsetParent.offset().left
					+ offsetParent.scrollLeft(),
			top : pos.top - offsetParent.offset().top
					+ offsetParent.scrollTop()
		};
	}
	// 获得区域的中心位置
	function getRectCenterPos(pos, width, height) {
		return {
			left : pos.left + width / 2,
			top : pos.top + height / 2
		};
	}
	// 画流程线集合
	function drawCanvaTransitions(context, transitionsArgs, color) {
		$.ccui.time.start();
		context.beginPath();
		if (color)
			context.strokeStyle = color;
		$(transitionsArgs).each(function() {
			_drawCanvaTransition(context, this.startPos, this.endPos,
					this.endRect, this.g);
		});
		context.stroke();
		$.ccui.time.end('画线');
	}
	// 画直线
	function _drawStraightLine(context, startPos, endPos) {
		context.moveTo(startPos.left, startPos.top);
		context.lineTo(endPos.left, endPos.top);
	}
	// 画流程线
	function _drawCanvaTransition(context, startPos, endPos, endRect, g) {
		if (g && g.length > 0) {// 有拐点
			for (var i = 0; i < g.length; i++) {
				_drawStraightLine(context, startPos, g[i]);
				startPos = g[i];
			}
		}

		_drawStraightLine(context, startPos, endPos);

		var r = 5 / Math.sin(30 * Math.PI / 180);
		// 计算正切角度
		var tag = (endPos.top - startPos.top) / (endPos.left - startPos.left);
		tag = isNaN(tag) ? 0 : tag;

		var o = Math.atan(tag) / (Math.PI / 180) - 30;// 计算角度
		var rectTag = endRect.height / endRect.width;
		// 计算箭头位置
		var xFlag = startPos.top < endPos.top ? -1 : 1, yFlag = startPos.left < endPos.left
				? -1
				: 1, arrowTop, arrowLeft;
		// 按角度判断箭头位置
		if (Math.abs(tag) > rectTag && xFlag == -1) {// top边
			arrowTop = endPos.top - endRect.height / 2;
			arrowLeft = endPos.left + xFlag * endRect.height / 2 / tag;
		} else if (Math.abs(tag) > rectTag && xFlag == 1) {// bottom边
			arrowTop = endPos.top + endRect.height / 2;
			arrowLeft = endPos.left + xFlag * endRect.height / 2 / tag;
		} else if (Math.abs(tag) < rectTag && yFlag == -1) {// left边
			arrowTop = endPos.top + yFlag * endRect.width / 2 * tag;
			arrowLeft = endPos.left - endRect.width / 2;
		} else if (Math.abs(tag) < rectTag && yFlag == 1) {// right边
			arrowTop = endPos.top + endRect.width / 2 * tag;
			arrowLeft = endPos.left + endRect.width / 2;
		}

		if (arrowLeft && arrowTop) {
			// 计算低位偏移
			var lowDeltX = r * Math.cos(o * Math.PI / 180);
			var lowDeltY = r * Math.sin(o * Math.PI / 180);
			// 计算高位偏移
			var o = 90 - o - 60;// 计算角度
			var highDeltX = r * Math.sin(o * Math.PI / 180);
			var highDeltY = r * Math.cos(o * Math.PI / 180);
			var flag = 1;
			if (startPos.left > endPos.left) {
				flag = -1;
			}
			// 画箭头
			context.moveTo(arrowLeft, arrowTop);
			context.lineTo(arrowLeft - lowDeltX * flag, arrowTop - lowDeltY
							* flag);
			context.moveTo(arrowLeft, arrowTop);
			context.lineTo(arrowLeft - highDeltX * flag, arrowTop - highDeltY
							* flag);
		}
	}
	function toJSON(object) {
		var type = typeof object;
		if ('object' == type) {
			if (Array == object.constructor)
				type = 'array';
			else if (RegExp == object.constructor)
				type = 'regexp';
			else
				type = 'object';
		}
		switch (type) {
			case 'undefined' :
			case 'unknown' :
				return;
				break;
			case 'function' :
			case 'boolean' :
			case 'regexp' :
				return object.toString();
				break;
			case 'number' :
				return isFinite(object) ? object.toString() : 'null';
				break;
			case 'string' :
				return '"'
						+ object.replace(/(\\|\")/g, "\\$1").replace(
								/\n|\r|\t/g, function() {
									var a = arguments[0];
									return (a == '\n') ? '\\n' : (a == '\r')
											? '\\r'
											: (a == '\t') ? '\\t' : ""
								}) + '"';
				break;
			case 'object' :
				if (object === null)
					return 'null';
				var results = [];
				for (var property in object) {
					var value = toJSON(object[property]);
					if (value !== undefined)
						results.push(toJSON(property) + ':' + value);
				}
				return '{' + results.join(',') + '}';
				break;
			case 'array' :
				var results = [];
				for (var i = 0; i < object.length; i++) {
					var value = toJSON(object[i]);
					if (value !== undefined)
						results.push(value);
				}
				return '[' + results.join(',') + ']';
				break;
		}
	}
})(jQuery);