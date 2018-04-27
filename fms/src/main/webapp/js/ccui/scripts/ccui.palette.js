/*
 * palette组件，负责提供不同的节点供workflow添加，支持拖拽动作
 */
(function($) {
	$.widget("ccui.palette", $.ui.mouse, {
		_init : function() {
			this.element.addClass('ccui-palette').disableSelection();
			this._initAction();
			this._mouseInit();// 初始鼠标支持
		},

		_initAction : function() {
			this.element.bind('click', function(event) {
				var clickElement = $(event.srcElement || event.target), toggleElement = clickElement
						.parents('li.toggle');
				if (toggleElement.length > 0) {
					$('.selected', this).not(toggleElement)
							.removeClass('selected');
					toggleElement.toggleClass('selected');
				}
			});
		},

		// 鼠标拖拽开始时，生成helper框帮助定位
		_mouseStart : function(event) {
			var dragElement = $(event.srcElement || event.target);
			this.currentDrag = dragElement;
			this.helper = this._createHelper(event, dragElement);
			this.offsetDragX = event.pageX - dragElement.offset().left;
			this.offsetDragY = event.pageY - dragElement.offset().top;

			this.originalPosition = this._generatePosition(event);
			return true;
		},

		// 鼠标拖拽过程中，不停变换helper框显示的位置
		_mouseDrag : function(event) {
			if (this.helper) {
				this.position = this._generatePosition(event);
				this.helper.css(this.position);
			}
			return false;
		},

		// 鼠标停止时，更新helper框位置，并且销毁临时对象
		_mouseStop : function(event) {
			if (this.helper) {
				if (this.currentDrag.hasClass('move')) {
					this.element.css(this.position);
				} else {// 接收
					var targetElement = $(event.srcElement || event.target), drop = targetElement
							.parents('.palette-drop');
					if (drop.length == 1 && $.isFunction(this.options.drop)) {
						this.options.drop.apply(this.element[0], [event, {
											drop : drop,
											text : this.helper.text(),
											type : this.type
										}]);
					}
				}

				this.helper.empty().remove();

				this.position = null;
				this.currentDrag = null;
				this.helper = null;

				this.offsetDragX = null;
				this.offsetDragY = null;
			}
			return false;
		},

		_mouseCapture : function(event) {
			return true;
		},

		_generatePosition : function(event) {
			if (this.currentDrag.hasClass('move')) {
				return {
					left : event.pageX - this.offsetDragX,
					top : event.pageY - this.offsetDragY
				};
			} else {
				return {
					left : event.pageX - this.offsetDragX + 20,
					top : event.pageY - this.offsetDragY + 20
				};
			}
		},

		// 拖拽整个palette或者单个元素的时候，显示阴影框，帮助定位移动的位置
		_createHelper : function(event, dragElement) {
			if (this.helper) {
				return this.helper;
			}
			if (dragElement.parents('li.toggle').length == 1) {
				return null;
			}
			var helper;
			if (dragElement.hasClass('move')) {
				helper = $('<div class="palette-dragging" style="width:'
						+ this.element.width() + 'px;height:'
						+ this.element.innerHeight() + 'px;"></div>');
			} else {
				var item, className;
				if (dragElement.is('.paletteItem>span')) {
					item = dragElement.clone();
					className = dragElement.parent().attr('id');
				} else if (dragElement.is('.paletteItem>span>a')) {
					item = dragElement.parent().clone();
					className = dragElement.parent().parent().attr('id');
				}
				if (item) {
					if (!className) {
						className = item.text();
					}
					helper = $('<div  class="palette-dragging item '
							+ className + '">' + item.text() + '</div>');
				}
				this.type = className;
			}
			if (helper) {
				helper.appendTo($('body', document));
			}
			return helper;
		},

		destroy : function() {
			// TODO
		}
	});

	$.extend($.ccui.palette, {
				defaults : {
					distance : 1,
					delay : 100,
					appendTo : 'body'
				},

				getter : ['addNode']
			});
})(jQuery);